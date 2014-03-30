package com.jxust.infolab.beans;

public class StudentShowBean {
	private Integer picId;
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
	private String picPath;
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
}
