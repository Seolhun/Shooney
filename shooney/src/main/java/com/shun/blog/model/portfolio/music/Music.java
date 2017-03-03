package com.shun.blog.model.portfolio.music;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TB_MUSIC")
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name = "IMAGE", nullable = false)
	@JsonProperty
	private String image;

	@NotEmpty
	@Column(name = "SINGER", nullable = false)
	@JsonProperty
	private String singer;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	@JsonProperty
	private String title;
	
	@Column(name = "LYRICS")
	private String lyrics;
	
	@Column(name = "URL")
	private String url;

	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Music))
			return false;
		Music other = (Music) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function. It is done here just for
	 * convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", singer=" + singer  + ", url=" + url + ", title=" + title + ", latestDate=" + latestDate + ", image=" + image + ", lyrics=" + lyrics + "]";
	}
}
