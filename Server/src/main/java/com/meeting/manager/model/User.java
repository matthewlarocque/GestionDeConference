
package com.meeting.manager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {

    /**
     * 登录名
     */
    @Column(name = "USERNAME_", length = 60, unique = true)
    private String username;

    /**
     * 用来显示的用户名
     */
    @Column(name = "REALNAME_", length = 60)
    private String realname;

    /**
     * 员工工号
     */
    @Column(name = "CODE_", length = 60)
    private String code;

    /**
     * 角色
     */
    @Column(name = "ROLE_", length = 60)
    private String role;

    /**
     * 性别
     */
    @Column(name = "SEX_", length = 10)
    private String sex;
    /**
     * 手机号
     */
    @Column(name = "PHONE_", length = 60)
    private String phone;

    /**
     * 住址
     */
    @Column(name = "ADDRESS_", length = 60)
    private String address;

    /**
     * 密码
     */
    @Column(name = "PASSWORD_", length = 60)
    private String password;


    /**
     * 用户状态
     */
    @Column(name = "STATUS_", length = 1)
    private String status;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 封装了权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 帐户密码是否未过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
