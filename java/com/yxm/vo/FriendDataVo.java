package com.yxm.vo;

import java.io.Serializable;

public class FriendDataVo implements Serializable {
    //nickName userName id  userId  portrait portraitFrame  epitaph  chatBubble  background mood
    private String nickName;
    private String userName;
    private Integer userId;
    private String portrait;
    private String portraitFrame;
    private String epitaph;
    private String chatBubble;
    private String background;
    private byte mood;
    private boolean isOK;

    public boolean isOK(){
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEpitaph() {
        return epitaph;
    }

    public void setEpitaph(String epitaph) {
        this.epitaph = epitaph;
    }

    public String getChatBubble() {
        return chatBubble;
    }

    public void setChatBubble(String chatBubble) {
        this.chatBubble = chatBubble;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public byte getMood() {
        return mood;
    }

    public void setMood(byte mood) {
        this.mood = mood;
    }
}
