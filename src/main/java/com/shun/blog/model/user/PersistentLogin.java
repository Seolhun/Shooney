package com.shun.blog.model.user;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The type Persistent login.
 */
@Entity
@Table(name="TB_PERSISTENT_LOGINS")
@Data
@EqualsAndHashCode(callSuper = false)
public class PersistentLogin extends AbstractCommon implements Serializable{

	@Id
	@Column(name="PERSISTENT_SERIES", length=100)
	private String series;

	@Column(name="PERSISTENT_EMAIL", unique=true, nullable=false, length=60)
	private String email;
	
	@Column(name="PERSISTENT_TOKEN", unique=true, nullable=false, length=100)
	private String token;
}
