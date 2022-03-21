package com.yxm.dao;

import com.yxm.po.*;
import com.yxm.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeDao {
    SysIndividuality selectUserIndividuality(Integer userId);
    List<UserIndividualityVo> selectUserChatAndFriend(Integer userId);
    UserIndividualityVo selectChat(Integer relationId);
    List<FriendRelation> selectFriendLoad(Integer userId);
    FriendRelation selectFriend(String friendNumber);
    FriendRelation selectFriendIsOk(@Param("userId") Integer userId, @Param("friendId") Integer friendId);
    List<SysHint> selectFriendHint(Integer userId);
    SysUserVo selectUserById(Integer userId);
    SysHint newFriendHint(Integer userId);
    Integer AddFriend(SysFriend sysFriend);
    Integer AddFriendHint(SysHint sysHint);
    Integer updateAddFriendHint(SysHint sysHint);
    Integer updateFriend(SysFriend sysFriend);
    SysFriend selectFriendRelation(@Param("userId") Integer userId, @Param("friendId") Integer friendId);
    List<FriendRelation> selectFriendHintFalse(Integer userId);
    Integer updateConsentFriend(SysFriend sysFriend);
    Integer establishGroup(SysGroup sysGroup);
    Integer addGroupMember(SysGroupMember sysGroupMember);
    Integer addGroupChat(SysGroupChat sysGroupChat);
    SysGroup selectGroupByGroupNumber(String groupNumber);
    List<GroupChatLoadVo> groupChatLoad(Integer userId);
    GroupChatLoadVo selectGroupChatLast(Integer groupId);
    SysHint selectGroupHint(@Param("userId") Integer userId,@Param("hintType")Byte hintType,@Param("groupId")Integer groupId);
    List<SysIndividuality> selectGroupMemberIndividuality(Integer groupId);
    SysGroup selectGroupById(Integer groupId);
    Integer selectGroupMemberCount(Integer groupId);
    SysFriend selectFriendData(@Param("userId")Integer userId,@Param("friendId")Integer friendId);
    Integer updateUserPageState(SysUserVo sysUserVo);
    Integer insertChatByFriend(SysChat sysChat);
    SysHint selectHintFriendChatObj(@Param("userId")Integer userId,@Param("initiativeId")Integer initiativeId,@Param("hintType")Byte hintType);
    Integer updateFriendHint(SysHint sysHint);
    List<SysGroupMember> selectGroupMemberByGroupId(Integer groupId);
    Integer insertGroupChat(SysGroupChat sysGroupChat);
    Integer updateUserPage(Integer id);
    List<FriendAndFriendChat> selectFriendChatById(@Param("relationId")Integer relationId, @Param("page")Integer page);
    List<FriendAndFriendChat> selectGroupChatById(@Param("relationId")Integer relationId, @Param("page")Integer page);
    FriendDataVo selectFriendBase(Integer majorKeyId);
    SysFriend selectBusinessCard(@Param("userId") Integer userId,@Param("majorKeyId") Integer majorKeyId);
    Integer deleteFriendChat(Integer majorKeyId);
    Integer deleteFriend(Integer majorKeyId);
}
