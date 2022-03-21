package com.yxm.vo;

import com.yxm.po.SysIndividuality;

import java.io.Serializable;
import java.util.Date;

public class UserIndividualityVo extends SysIndividuality implements Serializable{
    private String nickName;
    private String userName;
    private String chatContent;
    private Date sendDate;
    private Byte chatWay;
    private Integer friendId;
    private Integer sendId;
    private Integer receptionId;
    private Integer relationId;
    private Integer messageCount = 0;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Byte getChatWay() {
        return chatWay;
    }

    public void setChatWay(Byte chatWay) {
        this.chatWay = chatWay;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Integer receptionId) {
        this.receptionId = receptionId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public String toString() {
        return "UserIndividualityVo{" +
                "nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", chatContent='" + chatContent + '\'' +
                ", sendDate=" + sendDate +
                ", chatWay=" + chatWay +
                ", friendId=" + friendId +
                ", sendId=" + sendId +
                ", receptionId=" + receptionId +
                ", relationId=" + relationId +
                ", messageCount=" + messageCount +
                '}';
    }

}
