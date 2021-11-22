package cn.wyz.wyzmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.wyz.common.utils.PageUtils;
import cn.wyz.wyzmall.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:58:48
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

