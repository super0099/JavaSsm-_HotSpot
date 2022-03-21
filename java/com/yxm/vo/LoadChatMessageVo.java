package com.yxm.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoadChatMessageVo implements Serializable {
    private Integer majorKeyId;
    private String chatText;
    private Integer MessageType;//1代表是好友,2是群聊
    private Date sendDate;
    private Byte chatWay;
    private String portraitFrame;//头像框MessageType为1时才有
    private String portrait;
    private String nickName;
    private Integer messageCount = 0;
    private List<String> memberPortrait;//头像MessageType为2时才有

    public Integer getMajorKeyId() {
        return majorKeyId;
    }

    public void setMajorKeyId(Integer majorKeyId) {
        this.majorKeyId = majorKeyId;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public Integer getMessageType() {
        return MessageType;
    }

    public void setMessageType(Integer messageType) {
        MessageType = messageType;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Byte getChatWay() {
        return chatWay;
    }

    public void setChatWay(Byte chatWay) {
        this.chatWay = chatWay;
    }

    public String getPortraitFrame() {
        return portraitFrame;
    }

    public void setPortraitFrame(String portraitFrame) {
        this.portraitFrame = portraitFrame;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public List<String> getMemberPortrait() {
        return memberPortrait;
    }

    public void setMemberPortrait(List<String> memberPortrait) {
        this.memberPortrait = memberPortrait;
    }

    @Override
    public String toString() {
        return "LoadChatMessageVo{" +
                "majorKeyId=" + majorKeyId +
                ", chatText='" + chatText + '\'' +
                ", MessageType=" + MessageType +
                ", sendDate=" + sendDate +
                ", chatWay=" + chatWay +
                ", portraitFrame='" + portraitFrame + '\'' +
                ", portrait='" + portrait + '\'' +
                ", nickName='" + nickName + '\'' +
                ", messageCount=" + messageCount +
                ", memberPortrait=" + memberPortrait +
                '}';
    }
}
