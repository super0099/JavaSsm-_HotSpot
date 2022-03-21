package com.yxm.vo;

import java.io.Serializable;

public class ChatStateVo implements Serializable {
    private Integer majorKeyId;
    private String name;
    private Integer state;

    public Integer getMajorKeyId() {
        return majorKeyId;
    }

    public void setMajorKeyId(Integer majorKeyId) {
        this.majorKeyId = majorKeyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChatStateVo{" +
                "majorKeyId=" + majorKeyId +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
