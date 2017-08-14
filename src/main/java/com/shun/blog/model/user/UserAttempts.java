package com.shun.blog.model.user;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_USER_ATTEMPTS")
public class UserAttempts extends AbstractCommon implements Serializable {
	private static final long serialVersionUID = -6645634619910097302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ATTEMPTS_ID")
	private Long userAttemptsId;

	@Column(name = "USER_ATTEMPTS_EMAIL", nullable = false, length=60)
	private String userAttemptsEmail;

	@Column(name = "USER_ATTEMPTS_COUNTS", nullable = false, length=5)
	private Integer userAttemptsCounts = 1;

	@Column(name = "USER_ATTEMPTS_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date userAttemptsCreatedDate;

	@Column(name = "USER_ATTEMPTS_LOGIN_IP", length=60)
	private String userAttemptsLoginIp;

	@Column(name = "USER_ATTEMPTS_SUCCESS_FLAG", length=5)
	private Integer userAttemptsSuccessFlag=0;

	public UserAttempts() {

	}

	public UserAttempts(String userAttemptsEmail, Integer userAttemptsCounts, String userAttemptsLoginIp) {
		this.userAttemptsEmail=userAttemptsEmail;
		this.userAttemptsCounts=userAttemptsCounts;
		this.userAttemptsLoginIp=userAttemptsLoginIp;
	}
	
	public UserAttempts(String userAttemptsEmail, Integer userAttemptsCounts, String userAttemptsLoginIp, Integer userAttemptsSuccessFlag) {
		this.userAttemptsEmail=userAttemptsEmail;
		this.userAttemptsCounts=userAttemptsCounts;
		this.userAttemptsLoginIp=userAttemptsLoginIp;
		this.userAttemptsSuccessFlag=userAttemptsSuccessFlag;
	}
}
