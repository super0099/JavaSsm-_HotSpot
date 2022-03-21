package com.yxm.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GroupChatLoadVo implements Serializable {
    private Integer groupId;
    private String groupName;
    private String chatContent;
    private Date sendDate;
    private Byte chatWay;
    private Integer sendId;
    private Integer messageCount = 0;
    private List<String> portrait;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public List<String> getPortrait() {
        return portrait;
    }

    public void setPortrait(List<String> portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "GroupChatLoadVo{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", chatContent='" + chatContent + '\'' +
                ", sendDate=" + sendDate +
                ", chatWay=" + chatWay +
                ", sendId=" + sendId +
                ", messageCount=" + messageCount +
                ", portrait=" + portrait +
                '}';
    }
}
