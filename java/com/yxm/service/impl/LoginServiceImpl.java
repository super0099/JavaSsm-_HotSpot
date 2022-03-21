package com.yxm.service.impl;

import com.yxm.dao.LoginDao;
import com.yxm.po.SysIndividuality;
import com.yxm.po.SysUserVo;
import com.yxm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public SysUserVo selectUserName(String userName) {
        SysUserVo sysUserVo = this.loginDao.selectUserName(userName);
        return sysUserVo;
    }

    @Override
    public SysIndividuality selectUserPortrait(String userName) {
        return this.loginDao.selectUserPortrait(userName);
    }

    @Override
    public boolean selectUserNameExist(String userName) {
        return this.loginDao.selectUserName(userName)==null;
    }

    @Override
    public boolean insertUser(SysUserVo sysUserVo) {
        //注册的同时也新增个性表
        boolean isOk1 = this.loginDao.insertUser(sysUserVo)>0;
        if (isOk1){
            SysIndividuality sysIndividuality = new SysIndividuality();
            sysIndividuality.setUserId(sysUserVo.getId());
            sysIndividuality.setPortraitFrame("20211011_084647143_316351642199912.png");
            sysIndividuality.setBackground("20211011_084647143_316351642199901.png");
            sysIndividuality.setPortrait("20211011_084647143_316351642199900.png");
            boolean isOk = this.loginDao.insertIndividuality(sysIndividuality)>0;
            if (!isOk){
                throw new RuntimeException("注册失败");
            }
        }else {
            throw new RuntimeException("注册失败");
        }
        return true;
    }

    @Override
    public boolean updateUserState(Integer userId,Integer count) {
        return this.loginDao.updateUserState(userId,count)>0;
    }
}
