package cn.wyz.wyzmall.coupon.dao;

import cn.wyz.wyzmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:44:31
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
