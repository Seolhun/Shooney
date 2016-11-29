package com.shun.blog.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable{

	@Id
	private String series;

	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="token", unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date latestdate;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLatestdate() {
		return latestdate;
	}

	public void setLatestdate(Date latestdate) {
		this.latestdate = latestdate;
	}
}
