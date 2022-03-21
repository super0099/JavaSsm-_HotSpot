package com.yxm.po;

import java.io.Serializable;

public class SysHint implements Serializable {
    private Integer id;
    private Integer userId;
    private Byte hintType;
    private Integer initiativeId;
    private Integer messageCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getHintType() {
        return hintType;
    }

    public void setHintType(Byte hintType) {
        this.hintType = hintType;
    }

    public Integer getInitiativeId() {
        return initiativeId;
    }

    public void setInitiativeId(Integer initiativeId) {
        this.initiativeId = initiativeId;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public String toString() {
        return "SysHint{" +
                "id=" + id +
                ", userId=" + userId +
                ", hintType=" + hintType +
                ", initiativeId=" + initiativeId +
                ", messageCount=" + messageCount +
                '}';
    }
}
