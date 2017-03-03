package com.shun.blog.model.user;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TB_USER_ATTEMPTS")
public class UserAttempts {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "EMAIL", nullable = false)
	@NotEmpty
	private String email;
	
	@Column(name = "ATTEMPTS", nullable = false)
	@NotEmpty
	private int attempts;

	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;
	
	@Column(name = "LOGIP")
	private String logIp;
	
	@Column(name = "SUCCESSFLAG")
	private int successFlag;
	
	@Column(name = "AUTH")
	private String auth;
	
	public UserAttempts(int id, int attempts, String logIp) {
		this.logIp = logIp;
		this.id = id;
		this.attempts = attempts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}


	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public int getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(int successFlag) {
		this.successFlag = successFlag;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	
}
