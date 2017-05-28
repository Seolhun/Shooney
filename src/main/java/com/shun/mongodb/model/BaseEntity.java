package com.shun.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Super class for all entity classes.
 * 
 * @author Zouhir OUFTOU
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document
@Data
public abstract class BaseEntity implements Serializable {
	// id will be used for storing MongoDB _id
	@Id
	@Field(value="ID")
	private String id;
}