package com.shun.blog.model.portfolio.music;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_MUSIC")
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MUSIC_ID")
	private Long id;
	
	@NotEmpty
	@Column(name = "MUSIC_IMAGE", nullable = false)
	@JsonProperty
	private String image;

	@NotEmpty
	@Column(name = "MUSIC_SINGER", nullable = false)
	@JsonProperty
	private String singer;

	@NotEmpty
	@Column(name = "MUSIC_TITLE", nullable = false)
	@JsonProperty
	private String title;
	
	@Column(name = "MUSIC_LYRICS")
	private String lyrics;
	
	@Column(name = "MUSIC_URL")
	private String url;

	@Column(name = "MUSIC_CREATED_BY", nullable = false, length = 60)
	private String boardCreatedBy;

	@Column(name = "MUSIC_MODIFIED_BY", length = 60)
	private String boardModifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MUSIC_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MUSIC_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "MUSIC_DELCHECK", nullable=false)
	private int delCheck=0;
}
