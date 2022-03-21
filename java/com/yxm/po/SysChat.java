package com.yxm.po;

import java.io.Serializable;
import java.util.Date;

public class SysChat implements Serializable {
    //id  chatContent chatWay  relationId  sendId  receptionId  sendDate
    private Integer id;
    private String chatContent;
    private Byte chatWay;
    private Integer relationId;
    private Integer sendId;
    private Integer receptionId;
    private Date sendDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Integer receptionId) {
        this.receptionId = receptionId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "SysChat{" +
                "id=" + id +
                ", chatContent='" + chatContent + '\'' +
                ", chatWay=" + chatWay +
                ", relationId=" + relationId +
                ", sendId=" + sendId +
                ", receptionId=" + receptionId +
                ", sendDate=" + sendDate +
                '}';
    }
}
