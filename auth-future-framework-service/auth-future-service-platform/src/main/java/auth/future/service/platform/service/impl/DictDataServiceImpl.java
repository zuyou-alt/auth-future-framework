package auth.future.service.platform.service.impl;

import auth.future.api.platform.model.dict.DictConstant;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.service.platform.entity.DictData;
import auth.future.service.platform.mapper.DictDataMapper;
import auth.future.service.platform.service.DictDataService;

import java.util.List;

/**
 * 字典数据表
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {
    @Transactional
    @Override
    public String saveDictData(DictData dictData) {
        this.checkDictData(dictData);
        this.checkRepeat(dictData);
        if (dictData.getDictSort()==null){
            dictData.setDictSort(DictConstant.DEFAULT_SORT);
        }
        this.saveOrUpdate(dictData);
        return dictData.getId();
    }

    private void checkDictData(DictData dictData){
        Assert.isTrue(StrUtil.isNotBlank(dictData.getDictTypeId()),"所属标签类型不可以为空！");
        Assert.isTrue(StrUtil.isNotBlank(dictData.getLabel()),"标签标示不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(dictData.getValue()),"数据字典值不能为空！");
    }

    /**
     * 检查字典重复
     * @param dictData 字典数据
     */
    private void checkRepeat(DictData dictData){
        String dictValue = dictData.getValue();
        DictData one = this.lambdaQuery().eq(DictData::getValue, dictValue).eq(DictData::getDictTypeId,dictData.getDictTypeId()).one();
        if (one==null) return;
        Assert.isTrue(one.getId().equals(dictData.getId()),"字典重复，请重新输入！");
    }


    @Override
    public DictData getDictDataInfo(String id) {
        return this.getById(id);
    }

    @Override
    public boolean removeDictData(String id) {
        return this.removeById(id);
    }

    @Override
    public List<DictData> getDictDataListByDictTypeId(String dictTypeId) {
        return this.lambdaQuery().eq(DictData::getDictTypeId,dictTypeId).list();
    }

    @Override
    public List<DictData> getDictDataListByDictTypeIds(List<String> dictTypeIds) {
        return this.lambdaQuery().in(DictData::getDictTypeId,dictTypeIds).list();
    }

    @Override
    public Integer countDictDataListByDictTypeId(String dictTypeId) {
        return Integer.parseInt(String.valueOf(this.lambdaQuery().eq(DictData::getDictTypeId,dictTypeId).count()));
    }
}
