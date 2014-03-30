package com.jxust.infolab.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_info")
public class UserInfo {
	private int id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户昵称，登录名
	 */
	private String userNick;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 照片id
	 */
	private String picId;
	/**
	 * 等级
	 */
	private int level;
	/**
	 * 是否有效 1有效，0删除
	 */
	private Boolean isValid;
	private Date createTime;
	private Date modefyTime;
	private Date lastLoginTime;
	private Date delTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modefy_time")
	public Date getModefyTime() {
		return modefyTime;
	}

	public void setModefyTime(Date modefyTime) {
		this.modefyTime = modefyTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "del_time")
	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@Column(name = "pic_id")
	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_nick")
	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "is_valid")
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
