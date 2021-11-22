package cn.wyz.wyzmall.order.dao;

import cn.wyz.wyzmall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:58:48
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
