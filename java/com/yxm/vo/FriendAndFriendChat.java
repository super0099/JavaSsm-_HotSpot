package com.yxm.vo;

import java.io.Serializable;
import java.util.Date;

public class FriendAndFriendChat implements Serializable {
    //chatContent                             chatWay  relationId  sendId  receptionId  sendDate
    private Integer relationId;
    private Integer sendId;
    private String chatContent;
    private Date sendDate;
    private byte chatWay;
    private String portrait;
    private String portraitFrame;
    private String nickName;
    private Integer groupId;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
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

    public byte getChatWay() {
        return chatWay;
    }

    public void setChatWay(byte chatWay) {
        this.chatWay = chatWay;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPortraitFrame() {
        return portraitFrame;
    }

    public void setPortraitFrame(String portraitFrame) {
        this.portraitFrame = portraitFrame;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
