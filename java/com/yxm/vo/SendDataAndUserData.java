package com.yxm.vo;

import java.io.Serializable;
import java.util.Date;

public class SendDataAndUserData implements Serializable {
    private Integer sendId;//发送信息用户Id
    private Integer majorKeyId;//发送对象Id
    private Integer state;//区别websocket的信息状态
    private Integer stateCode;//区别是好友还是群聊
    private Byte chatWay;//区别聊天内容是文字还是图片
    private String chatContent;//信息内容
    private String portrait;//发送信息的用户头像
    private String portraitFrame;//发送信息的用户头像框
    private String chatBubble;//发送信息的用户聊天气泡
    private String nickName;//发送信息的用户昵称
    private boolean noProblem;//
    private Date sendDate;
    private Integer opposite;

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getMajorKeyId() {
        return majorKeyId;
    }

    public void setMajorKeyId(Integer majorKeyId) {
        this.majorKeyId = majorKeyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public Byte getChatWay() {
        return chatWay;
    }

    public void setChatWay(Byte chatWay) {
        this.chatWay = chatWay;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
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

    public String getChatBubble() {
        return chatBubble;
    }

    public void setChatBubble(String chatBubble) {
        this.chatBubble = chatBubble;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isNoProblem() {
        return noProblem;
    }

    public void setNoProblem(boolean noProblem) {
        this.noProblem = noProblem;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getOpposite() {
        return opposite;
    }

    public void setOpposite(Integer opposite) {
        this.opposite = opposite;
    }
}
