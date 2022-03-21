package com.yxm.po;

import java.io.Serializable;
import java.util.Date;

public class SysGroupChat implements Serializable {
    //id  groupId  chatContent  chatWay  sendId  sendDate
    private Integer id;
    private Integer groupId;
    private String chatContent;
    private Byte chatWay;
    private Integer sendId;
    private Date sendDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
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

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "SysGroupChat{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", chatContent='" + chatContent + '\'' +
                ", chatWay=" + chatWay +
                ", sendId=" + sendId +
                ", sendDate=" + sendDate +
                '}';
    }
}
