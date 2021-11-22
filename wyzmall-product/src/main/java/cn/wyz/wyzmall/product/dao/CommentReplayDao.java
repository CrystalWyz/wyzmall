package cn.wyz.wyzmall.product.dao;

import cn.wyz.wyzmall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 23:00:23
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
