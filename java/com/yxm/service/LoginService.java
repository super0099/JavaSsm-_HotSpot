package com.yxm.service;

import com.yxm.po.SysIndividuality;
import com.yxm.po.SysUserVo;
import com.yxm.vo.FriendRelation;

public interface LoginService {
    SysUserVo selectUserName(String userName);
    SysIndividuality selectUserPortrait(String userName);
    boolean selectUserNameExist(String userName);
    boolean insertUser(SysUserVo sysUserVo);
    boolean updateUserState(Integer userId,Integer count);

}
