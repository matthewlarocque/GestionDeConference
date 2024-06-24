
package com.meeting.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 会议室违规使用实体类
 */
@Entity
@Table(name = "DISCIPLINE")
public class Discipline extends BaseEntity {

    /**
     * 违规事由
     */
    @Column(name = "CONTENT_", length = 60)
    private String content;

    /**
     * 违规用户id
     */
    @Column(name = "USERID_", length = 60)
    private String userid;

    /**
     * 处罚方式
     */
    @Column(name = "PUNISHMENT_", length = 60)
    private String punishment;

    /**
     * 备注
     */
    @Column(name = "REMARK_", length = 500)
    private String remark;

    /**
     * 用户名
     */
    @Transient
    private String realname;

    /**
     * 联系方式
     */
    @Transient
    private String phone;

    /**
     * 所属会议室
     */
    @Transient
    private String meetingname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }
}
