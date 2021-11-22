package cn.wyz.wyzmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.wyz.common.utils.PageUtils;
import cn.wyz.wyzmall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 23:00:23
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

