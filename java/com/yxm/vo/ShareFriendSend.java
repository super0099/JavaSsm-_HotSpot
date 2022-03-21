package com.yxm.vo;

import java.io.Serializable;
import java.util.Date;

public class ShareFriendSend implements Serializable {
    private Integer sendId;//发送信息用户Id
    private Integer majorKeyId;//发送对象Id
    private Integer stateCode;
    private Integer state;//区别websocket的信息状态
    private Byte chatWay;//区别聊天内容是文字还是图片
    private String chatContent;//信息内容
    private String portrait;//发送信息的用户头像
    private String portraitFrame;//发送信息的用户头像框
    private String chatBubble;//发送信息的用户聊天气泡
    private String nickName;//发送信息的用户昵称
    private boolean noProblem;//
    private String ShareFriendPortrait;
    private String ShareFriendName;
    private String ShareFriendUserName;
    private Integer opposite;
    private Integer shareFriendId;

    public Integer getShareFriendId() {
        return shareFriendId;
    }

    public void setShareFriendId(Integer shareFriendId) {
        this.shareFriendId = shareFriendId;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getShareFriendUserName() {
        return ShareFriendUserName;
    }

    public void setShareFriendUserName(String shareFriendUserName) {
        ShareFriendUserName = shareFriendUserName;
    }

    public Integer getOpposite() {
        return opposite;
    }

    public void setOpposite(Integer opposite) {
        this.opposite = opposite;
    }

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

    public String getShareFriendPortrait() {
        return ShareFriendPortrait;
    }

    public void setShareFriendPortrait(String shareFriendPortrait) {
        ShareFriendPortrait = shareFriendPortrait;
    }

    public String getShareFriendName() {
        return ShareFriendName;
    }

    public void setShareFriendName(String shareFriendName) {
        ShareFriendName = shareFriendName;
    }
}
