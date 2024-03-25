package auth.future.service.platform.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.service.platform.entity.DictType;
import auth.future.service.platform.mapper.DictTypeMapper;
import auth.future.service.platform.service.DictTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典分类表
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {

    @Transactional
    @Override
    public String saveDictType(DictType dictType) {
        this.saveOrUpdate(dictType);
        return dictType.getId();
    }


    /**
     * 检查字典类型重复
     * @param dictType 字典类型数据
     */
    private void checkRepeat(DictType dictType){
        String type = dictType.getType();
        DictType one = this.lambdaQuery().eq(DictType::getType, type).one();
        if (one==null) return;
        Assert.isTrue(one.getId().equals(dictType.getId()),"字典类型重复，请重新输入！");
    }


    @Override
    public List<DictType> getDictTypeListByParentId(String parentId) {
        return this.lambdaQuery().eq(DictType::getParentId,parentId).orderByAsc(DictType::getDictSort).list();
    }

    @Override
    public Integer contDictTypeListByParentId(String parentId){
        return Math.toIntExact(this.lambdaQuery().eq(DictType::getParentId, parentId).count());
    }

    @Override
    public List<DictType> queryDictTypeAll() {
        IPage<DictType> page = new Page<>(1,5000);
        IPage<DictType> result = this.page(page);
        return result.getRecords();
    }

    @Override
    public boolean removeDictType(String id) {
        return this.removeById(id);
    }

    @Override
    public DictType getDictTypeByType(String type) {
        return this.lambdaQuery().eq(DictType::getType,type).one();
    }

    @Override
    public List<DictType> getBatchDictTypeByType(List<String> types) {
        if (types==null) return new ArrayList<>();
        return this.lambdaQuery().in(DictType::getType,types).list();
    }
}
