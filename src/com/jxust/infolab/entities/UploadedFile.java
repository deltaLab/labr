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
@Table(name = "uploaded_file")
public class UploadedFile {
	private int id;
	private String userName;
	private String fileName;
	private String saveName;
	private String fileType;
	private Date createTime;
	private Date modefyTime;
	private Date delTime;
	private boolean isValid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "save_name")
	public String getSaveName() {
		return saveName;
	}
	
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	@Column(name = "file_type")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "is_valid")
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
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

}
