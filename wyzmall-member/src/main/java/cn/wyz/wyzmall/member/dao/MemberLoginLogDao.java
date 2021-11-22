package cn.wyz.wyzmall.member.dao;

import cn.wyz.wyzmall.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author wyz
 * @email 806543499@qq.com
 * @date 2021-11-22 22:56:45
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
