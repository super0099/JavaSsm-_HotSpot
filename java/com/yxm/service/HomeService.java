package com.yxm.service;

import com.yxm.po.*;
import com.yxm.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HomeService {
    SysIndividuality selectUserIndividuality(Integer userId);
    List<UserIndividualityVo> selectUserChatAndFriend(Integer userId);
    UserIndividualityVo selectChat(Integer relationId);
    List<FriendRelation> selectFriendLoad(Integer userId);
    FriendRelation selectFriend(String friendNumber);
    FriendRelation selectFriendIsOk(Integer userId, Integer friendId);
    List<SysHint> selectFriendHint(Integer userId);
    SysHint newFriendHint(Integer userId);
    boolean AddFriend(SysFriend sysFriend);
    List<FriendRelation> selectFriendHintFalse(Integer userId);
    boolean updateMyHint(SysHint sysHint);
    Integer updateConsentFriend(SysFriend sysFriend);
    boolean establishGroup(String groupName,List<Integer> groupMember,Integer masterId,String groupNumber);
    SysUserVo selectUserById(Integer userId);
    boolean selectGroupByGroupNumber(String groupNumber);
    SysGroup selectGroupByGroupNumberVo(String groupNumber);
    List<GroupChatLoadVo> groupChatLoad(Integer userId);
    GroupChatLoadVo selectGroupChatLast(Integer groupId);
    SysHint selectGroupHint(Integer userId,Byte hintType,Integer groupId);
    List<SysIndividuality> selectGroupMemberIndividuality(Integer groupId);
    SysGroup selectGroupById(Integer groupId);
    Integer selectGroupMemberCount(Integer groupId);
    SysFriend selectFriendData(Integer userId,Integer friendId);
    boolean updateUserPageState(SysUserVo sysUserVo,Integer majorKeyId,Integer state);
    boolean insertChatByFriendNoHint(SysChat sysChat);
    boolean insertChatByFriendYesHint(SysChat sysChat);
    List<SysGroupMember> selectGroupMemberByGroupId(Integer groupId);
    boolean insertChatByYesGroup(Integer hintUserId,Integer majorKeyId,Byte chatWay);
    boolean insertChatByNoGroup(SysGroupChat sysGroupChat);
    boolean updateUserPage(Integer id);
    List<FriendAndFriendChat> selectFriendChatById(Integer relationId,Integer page);
    List<FriendAndFriendChat> selectGroupChatById(Integer relationId,Integer page);
    FriendDataVo selectFriendBase(Integer majorKeyId);
    SysFriend selectBusinessCard(Integer userId,Integer majorKeyId);
    boolean deleteFriend(SysFriend sysFriend);
}
