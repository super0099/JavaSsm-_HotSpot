package com.yxm.vo;

import com.yxm.po.SysIndividuality;

import java.io.Serializable;

public class FriendRelation extends SysIndividuality implements Serializable {
    private String spell;
    private String userName;
    private String nickName;
    private Byte state;

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FriendRelation{" +
                "spell='" + spell + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", state=" + state +
                '}';
    }
}
