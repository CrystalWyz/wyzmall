package cn.wyz.wyzmall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.wyz.common.utils.PageUtils;
import cn.wyz.common.utils.Query;

import cn.wyz.wyzmall.product.dao.CategoryDao;
import cn.wyz.wyzmall.product.entity.CategoryEntity;
import cn.wyz.wyzmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> getMenu() {

        List<CategoryEntity> categoryList = baseMapper.selectList(null);

        List<CategoryEntity> treeList = categoryList.parallelStream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> item.setChildren(getChildren(item, categoryList)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());

        return treeList;
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        // TODO 检查菜单
        baseMapper.deleteBatchIds(catIds);
    }

    private List<CategoryEntity> getChildren(CategoryEntity categoryEntity, List<CategoryEntity> categoryList) {
        List<CategoryEntity> children = categoryList.parallelStream()
                .filter(item -> item.getParentCid().equals(categoryEntity.getCatId()))
                .peek(item -> item.setChildren(getChildren(item, categoryList)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());

        return children;
    }

}