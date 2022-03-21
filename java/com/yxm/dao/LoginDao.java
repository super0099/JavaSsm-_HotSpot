package com.yxm.dao;

import com.yxm.po.SysIndividuality;
import com.yxm.po.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    SysUserVo selectUserName(String userName);
    Integer insertUser(SysUserVo sysUserVo);
    SysIndividuality selectUserPortrait(String userName);
    Integer updateUserState(@Param("userId") Integer userId,@Param("count") Integer count);
    Integer insertIndividuality(SysIndividuality sysIndividuality);
}
