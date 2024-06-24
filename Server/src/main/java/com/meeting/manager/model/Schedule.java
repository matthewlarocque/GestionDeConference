
package com.meeting.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 会议室预定实体类
 */
@Entity
@Table(name = "SCHEDULE")
public class Schedule extends BaseEntity {

    /**
     * 预约用户id
     */
    @Column(name = "USERID_", length = 60)
    private String userid;

    /**
     * 会议室id
     */
    @Column(name = "MEETINGID_", length = 60)
    private String meetingid;

    /**
     * 开始使用时间
     */
    @Column(name = "STARTDATETIME_", length = 60)
    private String startdatetime;

    /**
     * 结束使用时间
     */
    @Column(name = "ENDDATETIME_", length = 60)
    private String enddatetime;

    /**
     * 实际结束时间
     */
    @Column(name = "REALITYDATETIME_", length = 60)
    private String realitydatetime;

    /**
     * 状态
     */
    @Column(name = "STATUS_", length = 10)
    private String status;

    /**
     * 备注
     */
    @Column(name = "REMARK_", length = 200)
    private String remark;

    /**
     * 用户姓名
     */
    @Transient
    private String username;

    /**
     * 联系方式
     */
    @Transient
    private String phone;

    /**
     * 预约会议室
     */
    @Transient
    private String meetingname;


    /**
     * 预约起始日期（查询用）
     */
    @Transient
    private String starttime;

    /**
     * 预约结束日期（查询用）
     */
    @Transient
    private String endtime;

    /**
     * 验证预约时间（验证用）
     */
    @Transient
    private String scheduletime;

    /**
     * 预约开始使用时间（验证用）
     */
    @Transient
    private String startscheduletime;

    /**
     * 预约结束使用时间（验证用）
     */
    @Transient
    private String endscheduletime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(String meetingid) {
        this.meetingid = meetingid;
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getRealitydatetime() {
        return realitydatetime;
    }

    public void setRealitydatetime(String realitydatetime) {
        this.realitydatetime = realitydatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartscheduletime() {
        return startscheduletime;
    }

    public void setStartscheduletime(String startscheduletime) {
        this.startscheduletime = startscheduletime;
    }

    public String getEndscheduletime() {
        return endscheduletime;
    }

    public void setEndscheduletime(String endscheduletime) {
        this.endscheduletime = endscheduletime;
    }

    public String getScheduletime() {
        return scheduletime;
    }

    public void setScheduletime(String scheduletime) {
        this.scheduletime = scheduletime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
