package com.yxm.po;

import java.io.Serializable;

public class SysIndividuality implements Serializable {
    //id  userId  portrait  portraitFrame  epitaph  chatBubble  background    mood
    private Integer id;
    private Integer userId;
    private String portrait;
    private String portraitFrame;
    private String epitaph;
    private String chatBubble;
    private String background;
    private Byte mood;

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

    public Byte getMood() {
        return mood;
    }

    public void setMood(Byte mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "SysIndividuality{" +
                "id=" + id +
                ", userId=" + userId +
                ", portrait='" + portrait + '\'' +
                ", portraitFrame='" + portraitFrame + '\'' +
                ", epitaph='" + epitaph + '\'' +
                ", chatBubble='" + chatBubble + '\'' +
                ", background='" + background + '\'' +
                ", mood=" + mood +
                '}';
    }
}
