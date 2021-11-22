package cn.wyz.wyzmall.coupon.dao;

import cn.wyz.wyzmall.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:44:31
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {
	
}
