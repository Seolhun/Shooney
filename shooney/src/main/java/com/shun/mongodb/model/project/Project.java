package com.shun.mongodb.model.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shun.mongodb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document(collection = "project")
@Getter
@Setter
public class Project extends BaseEntity implements Serializable {

	@Indexed(unique = true)
	private String idx;

	private String title;

	private String content;

	private Date latestDate;

	private List<String> images;

	public Project() {
		// id = 0;
	}
}
