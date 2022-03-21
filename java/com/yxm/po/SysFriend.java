package com.yxm.po;

import java.io.Serializable;

public class SysFriend implements Serializable {
    //id  aFriend  bFriend   state
    private Integer id;
    private Integer aFriend;
    private Integer bFriend;
    private Byte state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaFriend() {
        return aFriend;
    }

    public void setaFriend(Integer aFriend) {
        this.aFriend = aFriend;
    }

    public Integer getbFriend() {
        return bFriend;
    }

    public void setbFriend(Integer bFriend) {
        this.bFriend = bFriend;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SysFriend{" +
                "id=" + id +
                ", aFriend=" + aFriend +
                ", bFriend=" + bFriend +
                ", state=" + state +
                '}';
    }
}
