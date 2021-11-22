package cn.wyz.wyzmall.order.dao;

import cn.wyz.wyzmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:58:48
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
