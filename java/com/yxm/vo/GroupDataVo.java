package com.yxm.vo;

import java.io.Serializable;
import java.util.List;

public class GroupDataVo implements Serializable {
    private Integer id;
    private String groupName;
    private String groupText;
    private List<String> memberPortrait;

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

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
    }

    public List<String> getMemberPortrait() {
        return memberPortrait;
    }

    public void setMemberPortrait(List<String> memberPortrait) {
        this.memberPortrait = memberPortrait;
    }

    @Override
    public String toString() {
        return "GroupDataVo{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupText='" + groupText + '\'' +
                ", memberPortrait=" + memberPortrait +
                '}';
    }
}
