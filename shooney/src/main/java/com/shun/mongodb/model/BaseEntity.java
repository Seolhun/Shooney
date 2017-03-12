package com.shun.mongodb.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Super class for all entity classes.
 * 
 * @author Zouhir OUFTOU
 */
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Data
public abstract class BaseEntity implements Serializable {
	// id will be used for storing MongoDB _id
	@Id
	private String id;

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BaseEntity other = (BaseEntity) obj;
//		if (id == null)
//			return other.id == null;
//		return id.equals(other.id);
//	}
}