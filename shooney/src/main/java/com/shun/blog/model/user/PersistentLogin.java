package com.shun.blog.model.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="TB_PERSISTENT_LOGINS")
@Data
public class PersistentLogin implements Serializable{

	@Id
	@Column(name="PERSISTENT_SERIES", length=100)
	private String series;

	@Column(name="PERSISTENT_EMAIL", unique=true, nullable=false, length=60)
	private String email;
	
	@Column(name="PERSISTENTTOKEN", unique=true, nullable=false, length=100)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PERSISTENT_CREATED_DATE", nullable=false)
	@UpdateTimestamp
	private Date createdDate;

}
