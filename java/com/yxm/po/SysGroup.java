package com.yxm.po;

import java.io.Serializable;

public class SysGroup implements Serializable {
    private Integer id;
    private String groupName;
    private Integer masterId;
    private String groupNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "SysGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", masterId=" + masterId +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
