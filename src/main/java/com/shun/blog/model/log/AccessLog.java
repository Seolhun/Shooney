package com.shun.blog.model.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="TB_ACCESS_LOG")
public class AccessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCESS_LOG_ID")
	private Long id;
	
	@Column(name = "ACCESS_LOG_IP", length=30, nullable = false)
	private String ip;

	@Column(name = "ACCESS_LOG_URL", length=80, nullable = false)
	private String url;
	
	@Column(name = "ACCESS_LOG_TIME_ZONE", length=80, nullable = true)
	private String timeZone;
	
	@Column(name = "ACCESS_LOG_CREATED_BY", length = 60, nullable = true)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCESS_LOG_CREATED_DATE")
	private Date createdDate;
	
	//Repository or service divide
	@Transient
	private int type;
	
	//add or minus for data
	@Transient
	private int calculator;
	
	@Transient
	private Date date;
}
