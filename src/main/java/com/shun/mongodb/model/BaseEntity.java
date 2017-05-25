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
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document
@Data
public abstract class BaseEntity implements Serializable {
	// id will be used for storing MongoDB _id
	@Id
	private String id;
}