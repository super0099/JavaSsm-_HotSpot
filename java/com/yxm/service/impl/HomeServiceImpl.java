package com.yxm.service.impl;

import com.yxm.dao.HomeDao;
import com.yxm.po.*;
import com.yxm.service.HomeService;
import com.yxm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class  HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDao homeDao;

    @Override
    public SysIndividuality selectUserIndividuality(Integer userId) {
        return this.homeDao.selectUserIndividuality(userId);
    }

    @Override
    public List<UserIndividualityVo> selectUserChatAndFriend(Integer userId) {
        return this.homeDao.selectUserChatAndFriend(userId);
    }

    @Override
    public UserIndividualityVo selectChat(Integer relationId) {
        return this.homeDao.selectChat(relationId);
    }

    @Override
    public List<FriendRelation> selectFriendLoad(Integer userId) {
        return this.homeDao.selectFriendLoad(userId);
    }

    @Override
    public FriendRelation selectFriend(String friendNumber) {
        return this.homeDao.selectFriend(friendNumber);
    }

    @Override
    public FriendRelation selectFriendIsOk(Integer userId, Integer friendId) {
        return this.homeDao.selectFriendIsOk(userId,friendId);
    }

    @Override
    public List<SysHint> selectFriendHint(Integer userId) {
        return this.homeDao.selectFriendHint(userId);
    }

    @Override
    public SysHint newFriendHint(Integer userId) {
        return this.homeDao.newFriendHint(userId);
    }

    @Override
    public boolean AddFriend(SysFriend sysFriend) {
        //判断是否存在信息提示表
        SysHint sysHint = this.homeDao.newFriendHint(sysFriend.getbFriend());
        //
        FriendRelation friendRelation = this.homeDao.selectFriendIsOk(sysFriend.getaFriend(),sysFriend.getbFriend());
        //不存在就新建存在就修改
        if (sysHint==null){
            SysHint sysHint1 = new SysHint();
            sysHint1.setUserId(sysFriend.getbFriend());
            sysHint1.setHintType((byte) 3);
            sysHint1.setMessageCount(1);
            sysHint1.setInitiativeId(0);
            boolean isOk = this.homeDao.AddFriendHint(sysHint1)>0;
            if (isOk){
                //新增好友关系
                if (friendRelation==null){
                    boolean isTrue = this.homeDao.AddFriend(sysFriend)>0;
                    if (!isTrue){
                        throw new RuntimeException("新增关系表出错");
                    }
                }else {
                    SysFriend selectFriendRelation = this.homeDao.selectFriendRelation(sysFriend.getaFriend(),sysFriend.getbFriend());
                    boolean isTrue = this.homeDao.updateFriend(selectFriendRelation)>0;
                    if (!isTrue){
                        throw new RuntimeException("修改关系表出错");
                    }
                }
            }else {
                throw new RuntimeException("新增用户提示表出错");
            }
        }else {
            Integer count = sysHint.getMessageCount()+1;
            sysHint.setMessageCount(count);
            boolean isOk = this.homeDao.updateAddFriendHint(sysHint)>0;
            if (isOk){
                //新增好友关系
                if (friendRelation==null){
                    boolean isTrue = this.homeDao.AddFriend(sysFriend)>0;
                    if (!isTrue){
                        throw new RuntimeException("新增关系表出错");
                    }
                }else {
                    SysFriend selectFriendRelation = this.homeDao.selectFriendRelation(sysFriend.getaFriend(),sysFriend.getbFriend());
                    boolean isTrue = this.homeDao.updateFriend(selectFriendRelation)>0;
                    if (!isTrue){
                        throw new RuntimeException("修改关系表出错");
                    }
                }
            }else {
                throw new RuntimeException("修改用户提示表出错");
            }
        }
        return true;
    }

    @Override
    public List<FriendRelation> selectFriendHintFalse(Integer userId) {
        return this.homeDao.selectFriendHintFalse(userId);
    }

    @Override
    public boolean updateMyHint(SysHint sysHint) {
        return this.homeDao.updateAddFriendHint(sysHint)>0;
    }

    @Override
    public Integer updateConsentFriend(SysFriend sysFriend) {

        return this.homeDao.updateConsentFriend(sysFriend);
    }

    @Override
    public boolean establishGroup(String groupName, List<Integer> groupMember, Integer masterId,String groupNumber) {
        //创建群
        SysGroup sysGroup = new SysGroup();
        sysGroup.setGroupName(groupName);
        sysGroup.setMasterId(masterId);
        sysGroup.setGroupNumber(groupNumber);
        boolean isOk = this.homeDao.establishGroup(sysGroup)>0;
        if (isOk){
            //添加群成员
            SysGroupMember sysGroupMember;
            SysUserVo sysUserVo;
            for (Integer memberIds:groupMember) {
                sysGroupMember = new SysGroupMember();
                sysUserVo = this.homeDao.selectUserById(memberIds);
                sysGroupMember.setGroupId(sysGroup.getId());
                sysGroupMember.setUserId(memberIds);
                sysGroupMember.setUserName(sysUserVo.getNickName());
                boolean isTrue = this.homeDao.addGroupMember(sysGroupMember)>0;
                if (!isTrue){
                    throw new RuntimeException("新增成员出错");
                }
                SysGroupChat sysGroupChat = new SysGroupChat();
                //添加好友加入群聊的系统信息
                sysGroupChat.setGroupId(sysGroup.getId());
                sysGroupChat.setChatContent(sysUserVo.getNickName()+"加入群聊");
                sysGroupChat.setChatWay((byte) 4);
                sysGroupChat.setSendId(0);
                boolean isProsperity = this.homeDao.addGroupChat(sysGroupChat)>0;
                if (!isProsperity){
                    throw new RuntimeException("新增群系统信息出错");
                }
            }
        }else {
            throw new RuntimeException("新增群聊表出错");
        }
        return true;
    }

    @Override
    public SysUserVo selectUserById(Integer userId) {
        return this.homeDao.selectUserById(userId);
    }

    @Override
    public boolean selectGroupByGroupNumber(String groupNumber) {
        return this.homeDao.selectGroupByGroupNumber(groupNumber)==null;
    }

    @Override
    public SysGroup selectGroupByGroupNumberVo(String groupNumber) {
        return this.homeDao.selectGroupByGroupNumber(groupNumber);
    }

    @Override
    public List<GroupChatLoadVo> groupChatLoad(Integer userId) {
        return this.homeDao.groupChatLoad(userId);
    }

    @Override
    public GroupChatLoadVo selectGroupChatLast(Integer groupId) {
        return this.homeDao.selectGroupChatLast(groupId);
    }

    @Override
    public SysHint selectGroupHint(Integer userId, Byte hintType, Integer groupId) {
        return this.homeDao.selectGroupHint(userId,hintType,groupId);
    }

    @Override
    public List<SysIndividuality> selectGroupMemberIndividuality(Integer groupId) {
        return this.homeDao.selectGroupMemberIndividuality(groupId);
    }

    @Override
    public SysGroup selectGroupById(Integer groupId) {
        return this.homeDao.selectGroupById(groupId);
    }

    @Override
    public Integer selectGroupMemberCount(Integer groupId) {
        return this.homeDao.selectGroupMemberCount(groupId);
    }

    @Override
    public SysFriend selectFriendData(Integer userId, Integer friendId) {
        return this.homeDao.selectFriendData(userId,friendId);
    }

    @Override
    public boolean updateUserPageState(SysUserVo sysUserVo,Integer majorKeyId,Integer state) {
        boolean isOk = this.homeDao.updateUserPageState(sysUserVo)>0;
        if (isOk){
            if (state==1){
                /*点击的是好友*/
                SysHint sysHint = this.homeDao.selectHintFriendChatObj(sysUserVo.getId(),majorKeyId, (byte) 1);
                if (sysHint==null){
                    SysHint hint = new SysHint();
                    hint.setUserId(sysUserVo.getId());
                    hint.setHintType((byte) 1);
                    hint.setMessageCount(0);
                    hint.setInitiativeId(majorKeyId);
                    boolean ok = this.homeDao.AddFriendHint(hint)>0;
                    if (!ok){
                        throw new RuntimeException("新增提示表出错");
                    }
                }else {
                    sysHint.setMessageCount(0);
                    boolean ok = this.homeDao.updateFriendHint(sysHint)>0;
                    if (!ok){
                        throw new RuntimeException("修改提示表出错");
                    }
                }
            }
            if (state==2){
                /*点击的是群聊*/
                SysHint sysHint = this.homeDao.selectHintFriendChatObj(sysUserVo.getId(),majorKeyId,(byte) 2);
                if (sysHint==null){
                    SysHint hint = new SysHint();
                    hint.setUserId(sysUserVo.getId());
                    hint.setHintType((byte) 2);
                    hint.setMessageCount(0);
                    hint.setInitiativeId(majorKeyId);
                    boolean ok = this.homeDao.AddFriendHint(hint)>0;
                    if (!ok){
                        throw new RuntimeException("新增提示表出错");
                    }
                }else {
                    sysHint.setMessageCount(0);
                    boolean ok = this.homeDao.updateFriendHint(sysHint)>0;
                    if (!ok){
                        throw new RuntimeException("修改提示表出错");
                    }
                }
            }
        }else {
            throw new RuntimeException("系统出错");
        }
        return true;
    }

    @Override
    public boolean insertChatByFriendNoHint(SysChat sysChat) {
        return this.homeDao.insertChatByFriend(sysChat)>0;
    }

    @Override
    public boolean insertChatByFriendYesHint(SysChat sysChat) {
        SysHint sysHint = this.homeDao.selectHintFriendChatObj(sysChat.getReceptionId(),sysChat.getSendId(),(byte)1);
        if (sysHint==null){
            SysHint hint = new SysHint();
            hint.setHintType((byte) 1);
            hint.setMessageCount(1);
            hint.setInitiativeId(sysChat.getSendId());
            hint.setUserId(sysChat.getReceptionId());
            boolean isOk = this.homeDao.AddFriendHint(hint)>0;
            if (isOk){
                boolean isTrue = this.homeDao.insertChatByFriend(sysChat)>0;
                if (!isTrue){
                    throw new RuntimeException("新增聊天信息出错");
                }
            }else {
                throw new RuntimeException("新建用户提示表出错");
            }
        }else {
            Integer count = sysHint.getMessageCount();
            sysHint.setMessageCount(count+=1);
            boolean isOk = this.homeDao.updateFriendHint(sysHint)>0;
            if (isOk){
                boolean isTrue = this.homeDao.insertChatByFriend(sysChat)>0;
                if (!isTrue){
                    throw new RuntimeException("新增聊天信息出错");
                }
            }else {
                throw new RuntimeException("修改用户提示表出错");
            }
        }
        return true;
    }

    @Override
    public List<SysGroupMember> selectGroupMemberByGroupId(Integer groupId) {
        return this.homeDao.selectGroupMemberByGroupId(groupId);
    }

    @Override
    public boolean insertChatByYesGroup(Integer hintUserId,Integer majorKeyId,Byte chatWay) {
        SysHint sysHint = this.homeDao.selectHintFriendChatObj(hintUserId,majorKeyId,chatWay);
        if (sysHint!=null){
            Integer count = sysHint.getMessageCount();
            sysHint.setMessageCount(count+=1);
            boolean isOk = this.homeDao.updateFriendHint(sysHint)>0;
            if (!isOk){
                throw new RuntimeException("修改群聊提示信息出错");
            }
        }else {
            SysHint hint = new SysHint();
            hint.setHintType((byte) 2);
            hint.setMessageCount(1);
            hint.setInitiativeId(majorKeyId);
            hint.setUserId(hintUserId);
            boolean isOk = this.homeDao.AddFriendHint(hint)>0;
            if (!isOk){
                throw new RuntimeException("新建群聊提示表出错");
            }
        }
        return true;
    }

    @Override
    public boolean insertChatByNoGroup(SysGroupChat sysGroupChat) {
        return this.homeDao.insertGroupChat(sysGroupChat)>0;
    }

    @Override
    public boolean updateUserPage(Integer id) {
        return this.homeDao.updateUserPage(id)>0;
    }

    @Override
    public List<FriendAndFriendChat> selectFriendChatById(Integer relationId, Integer page) {
        return this.homeDao.selectFriendChatById(relationId,page);
    }

    @Override
    public List<FriendAndFriendChat> selectGroupChatById(Integer relationId, Integer page) {
        return this.homeDao.selectGroupChatById(relationId,page);
    }

    @Override
    public FriendDataVo selectFriendBase(Integer majorKeyId) {
        return this.homeDao.selectFriendBase(majorKeyId);
    }

    @Override
    public SysFriend selectBusinessCard(Integer userId, Integer majorKeyId) {
        return this.homeDao.selectBusinessCard(userId,majorKeyId);
    }

    @Override
    public boolean deleteFriend(SysFriend friend) {
        Integer isOk = this.homeDao.deleteFriendChat(friend.getId());
        if (isOk>=0){
            boolean isTrue = this.homeDao.deleteFriend(friend.getId())>0;
            if (!isTrue){
                throw new RuntimeException("删除好友出错");
            }
        }else {
            throw new RuntimeException("删除聊天记录出错");
        }
        return true;
    }

}
