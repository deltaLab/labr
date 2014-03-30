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
@Table(name = "student_show")
public class StudentShow {
	private int id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 入学年份
	 */
	private int year;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 专业
	 */
	private String subject;
	/**
	 * 班级号
	 */
	private String classNo;
	/**
	 * 照片id
	 */
	private String picId;
	private boolean isValid;
	private Date createTime;
	private Date modefyTime;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "year")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "class_no")
	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	@Column(name = "pic_id")
	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	@Column(name = "is_valid")
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
