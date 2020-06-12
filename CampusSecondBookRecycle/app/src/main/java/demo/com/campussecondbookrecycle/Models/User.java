package demo.com.campussecondbookrecycle.Models;

import java.util.Date;

public class User {
    private Integer id;

    private String userNum;

    private String username;

    private String password;

    private String major;

    private Integer enrollmentTime;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    public User(Integer id, String userNum, String username, String password, String major, Integer enrollmentTime, Integer role, Date createTime, Date updateTime) {
        this.id = id;
        this.userNum = userNum;
        this.username = username;
        this.password = password;
        this.major = major;
        this.enrollmentTime = enrollmentTime;
        this.role = role;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(Integer enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}