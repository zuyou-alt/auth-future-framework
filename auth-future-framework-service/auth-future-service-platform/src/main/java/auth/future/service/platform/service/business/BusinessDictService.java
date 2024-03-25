package auth.future.service.platform.service.business;

import auth.future.api.platform.model.dict.*;
import auth.future.component.common.utils.ConversionUtil;
import auth.future.component.redis.RedisUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.platform.DictServiceApi;
import auth.future.service.platform.beanconversion.DictDataMapperCvs;
import auth.future.service.platform.beanconversion.DictTypeMapperCvs;
import auth.future.service.platform.constant.BaseConstant;
import auth.future.service.platform.entity.DictData;
import auth.future.service.platform.entity.DictType;
import auth.future.service.platform.service.DictDataService;
import auth.future.service.platform.service.DictTypeService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-09
 **/
@Service
public class BusinessDictService implements DictServiceApi {

    @Resource
    private DictDataService dictDataService;

    @Resource
    private DictTypeService dictTypeService;



    /**
     * 保存数据字典类型
     * @param dictTypeVo 字典类型数据
     * @return 字典类型ID
     */
    @Override
    public String saveDictType(DictTypeVo dictTypeVo){
        DictType dictType = DictTypeMapperCvs.INSTANCE.VoToDb(dictTypeVo);
        // 检查数据完整性
        this.checkDictType(dictType);
        // 检查重复
        this.checkRepeat(dictType);
        DictType dictTypeParent = dictTypeService.getById(dictType.getParentId());
        dictType.setTypeLevel(dictTypeParent==null? 1: dictTypeParent.getTypeLevel()+1);
        return dictTypeService.saveDictType(dictType);
    }

    /**
     * 检查字典类型重复
     * @param dictType 字典类型数据
     */
    private void checkRepeat(DictType dictType){
        String type = dictType.getType();
        DictType oldDictType = dictTypeService.getDictTypeByType(type);
        Assert.isTrue(oldDictType==null || oldDictType.getId().equals(dictType.getId()),"该类型已经存在！不可重复添加");
    }

    /**
     * 检查必填项
     * @param dictType 字典类型
     */
    private void checkDictType(DictType dictType){
        Assert.isTrue(StrUtil.isNotBlank(dictType.getName()),"字典类型名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(dictType.getType()),"字典类型标识不能为空！");
    }

    /**
     * 删除字典类型
     * @param id 字典类型ID
     * @return 删除状态
     */
    @Override
    public boolean removeDictType(String id) {
        DictType byId = dictTypeService.getById(id);
        //判断是否有子集
        Integer childCount = dictTypeService.contDictTypeListByParentId(id);
        Assert.isTrue(childCount==null || childCount==0, "该字典类型还有子集，不允许删除！");
        Integer dataCount = dictDataService.countDictDataListByDictTypeId(byId.getId());
        Assert.isTrue(dataCount==null || dataCount==0, "该字典类型已经存在字典，请先删除字典！");
        return dictTypeService.removeDictType(id);
    }

    @Override
    public DictTypeVo getDictTypeVo(String id) {
        DictType byId = dictTypeService.getById(id);
        return DictTypeMapperCvs.INSTANCE.DbToVo(byId);
    }

    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    @Override
    public List<DictTypeVo> getDictTypeListByParentId(String parentId) {
        List<DictType> dictTypeListByParentId = dictTypeService.getDictTypeListByParentId(parentId);
        return DictTypeMapperCvs.INSTANCE.DbListToVoList(dictTypeListByParentId);
    }

    /**
     * 保存字典数据
     * @param dictDataVo 字典数据
     * @return 字典ID
     */
    @Override
    public String saveDictData(DictDataVo dictDataVo) {
        DictData dictData = DictDataMapperCvs.INSTANCE.VoToDb(dictDataVo);
        return dictDataService.saveDictData(dictData);
    }


    @Transactional
    @Override
    public int saveDictData(List<DictDataVo> dictDataVoList) {
        if (dictDataVoList==null || dictDataVoList.isEmpty()) return 0;
        for (DictDataVo dictDataVo : dictDataVoList) {
            saveDictData(dictDataVo);
        }
        return dictDataVoList.size();
    }

    /**
     * 根据DI获取字典数据信息
     * @param id 主键
     * @return 数据信息
     */
    @Override
    public DictDataVo getDictDataInfo(String id) {
        DictData dictDataInfo = dictDataService.getDictDataInfo(id);
        return DictDataMapperCvs.INSTANCE.DbToVo(dictDataInfo);
    }

    /**
     * 删除数据字典
     * @param id 主键ID
     * @return 删除状态
     */
    @Override
    public boolean removeDictData(String id) {
        return dictDataService.removeDictData(id);
    }

    @Override
    public boolean removeBatchDictData(List<String> ids) {
        return dictDataService.removeBatchByIds(ids);
    }

    /**
     * 根据字典类型获取字典列表
     * @param dictTypeId 字典雷类型
     * @return 字典列表
     */
    public List<DictDataVo> getDictDataListByDictType(String dictTypeId) {
        List<DictData> dictDataListByDictType = dictDataService.getDictDataListByDictTypeId(dictTypeId);
        return DictDataMapperCvs.INSTANCE.DbListToVoList(dictDataListByDictType);
    }

    @Override
    public List<DictDataCommon> getDict(String dictType) {
        DictType dictTypeByType = dictTypeService.getDictTypeByType(dictType);
        List<DictData> dictDataListByDictTypeId = dictDataService.getDictDataListByDictTypeId(dictTypeByType.getId());
        return DictDataMapperCvs.INSTANCE.DbToComDataList(dictDataListByDictTypeId);
    }

    @Override
    public List<BatchDictData> getBatchDict(@RequestParam("dictType") List<String> dictTypes){
        List<DictType> dictTypeList = dictTypeService.getBatchDictTypeByType(dictTypes);
        List<String> typeIds = dictTypeList.stream().map(DictType::getId).toList();
        Map<String, DictType> dictTypeMap = dictTypeList.stream().collect(Collectors.toMap(DictType::getType, o -> o));
        List<DictData> dictDataList = new ArrayList<>();
        if (!typeIds.isEmpty()){
            dictDataList = dictDataService.getDictDataListByDictTypeIds(typeIds);
        }
        Map<String, List<DictData>> dictDataMap = dictDataList.stream().collect(Collectors.groupingBy(DictData::getDictTypeId));
        List<BatchDictData> result = new ArrayList<>();
        for (String dictType : dictTypes) {
            BatchDictData batchDictData  = new BatchDictData();
            DictType dictTypeObj = dictTypeMap.get(dictType);
            List<DictData> dictData = dictDataMap.get(dictTypeObj == null ? "" : dictTypeObj.getId());
            batchDictData.setDictType(dictType);
            batchDictData.setDictDataCommonList(dictData==null ? new ArrayList<>(): DictDataMapperCvs.INSTANCE.DbToComDataList(dictData));
            result.add(batchDictData);
        }
        return  result;
    }

    /**
     * 根据字典类型查询字典值Map
     * @param dictType 字典类型
     * @return 字典值map
     */
    public Map<String, Object> getDictDataMap(String dictType){
        String redisKey = DictRedisKey.DICT_DATA_MAP_KEY+dictType;
        if (RedisUtil.hasKey(redisKey)){
            return ConversionUtil.mapKeyConversion(RedisUtil.hGetAll(redisKey));
        }else {
            Map<String, Object> dictDataMapByDB = getDictDataMapByDB(dictType);
            RedisUtil.hPutAll(redisKey,dictDataMapByDB);
            return dictDataMapByDB;
        }
    }

    @Override
    public Map<String, String> getValueMap(String dictType) {
        List<DictData> dictDataList = getDictDataListByType(dictType);
        Map<String,String> result= new HashMap<>();
        for (DictData dictData : dictDataList) {
            result.put(dictData.getValue(),dictData.getLabel());
        }
        return result;
    }

    private List<DictData> getDictDataListByType(String dictType){
        DictType dictTypeObj = dictTypeService.getDictTypeByType(dictType);
        return dictDataService.getDictDataListByDictTypeId(dictTypeObj.getId());
    }

    public Map<String, String> getLableMap(String dictType) {
        List<DictData> dictDataList = getDictDataListByType(dictType);
        Map<String,String> result= new HashMap<>();
        for (DictData dictData : dictDataList) {
            result.put(dictData.getLabel(),dictData.getValue());
        }
        return result;
    }

    private Map<String, Object> getDictDataMapByDB(String dictType){
        List<DictData> dictDataList = dictDataService.getDictDataListByDictTypeId(dictType);
        if (dictDataList.isEmpty()) return new HashMap<>();
        Map<String, Object> dictDataMap =  new HashMap<>();
        for (DictData dictData : dictDataList) {
            dictDataMap.put(dictData.getLabel(),dictData.getValue());
        }
        return dictDataMap;
    }

    @Override
    public List<DictTypeVo> getDictTypeTree(){
        List<DictType> dictTypes = dictTypeService.queryDictTypeAll();
        Map<String, List<DictType>> dictTypeMap = dictTypes.stream().collect(Collectors.groupingBy(DictType::getParentId, Collectors.toList()));
        return getChild(BaseConstant.ROOT_PATH,1,Integer.MAX_VALUE,dictTypeMap);
    }

    public List<DictTypeVo> getChild(String parentId,int depth,int maxDepth,Map<String, List<DictType>> dictTypeMap){
        List<DictType> dictTypeList = dictTypeMap.get(parentId);
        if (dictTypeList==null || dictTypeList.isEmpty()) return new ArrayList<>();
        List<DictTypeVo> result = new ArrayList<>();
        for (DictType dictType : dictTypeList) {
            DictTypeVo dictTypeVo = DictTypeMapperCvs.INSTANCE.DbToVo(dictType);
            if (depth<maxDepth){
                dictTypeVo.setChildren(getChild(dictTypeVo.getId(), depth, maxDepth,dictTypeMap));
            }
            result.add(dictTypeVo);
        }
        return result;
    }
}
