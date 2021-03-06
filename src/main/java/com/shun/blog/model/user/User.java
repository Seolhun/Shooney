package com.shun.blog.model.user;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_USER")
public class User extends AbstractCommon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	private Long id;

	@Email
	@Column(name = "USER_EMAIL", unique = true, nullable = false, length=60)
	private String email;

	@Column(name = "USER_NICKNAME", unique = true, nullable = false, length=30)
	@NotEmpty
	private String nickname;

	@Column(name = "USER_PASSWORD", nullable = false, length=100)
	private String password;
	
	@Column(name = "USER_LOCKED_AUTH", length=100)
	private String lockedAuth;

	@Column(name = "USER_STATE", length=20, nullable=false)
	private String state;

	@BatchSize(size=2)
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USER_PROFILE_REFER", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<>();

	@Column(name = "USER_RECEIVE_EMAIL", nullable=false)
	private int receiveEmail=0;
	
	@Transient
	private int type;

//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof User))
//			return false;
//		User other = (User) obj;
//		if (id != other.id)
//			return false;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (nickname == null) {
//			if (other.nickname != null)
//				return false;
//		} else if (!nickname.equals(other.nickname))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", password=" + password + ", NickName =" + nickname + ", email =" + email
//				+ ", state=" + state + ", userProfiles =" + userProfiles + "]";
//	}
}