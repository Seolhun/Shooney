package com.shun.blog.model.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemData implements Serializable {
	private Boss boss;
	private List<MiddleBoss> middleBosses;
	private List<Map> maps;

	@JsonProperty("Boss")
	public Boss getBoss() {
		return boss;
	}

	@JsonProperty("Boss")
	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	@JsonProperty("MiddleBoss")
	public List<MiddleBoss> getMiddleBosses() {
		return middleBosses;
	}

	@JsonProperty("MiddleBoss")
	public void setMiddleBosses(List<MiddleBoss> middleBosses) {
		this.middleBosses = middleBosses;
	}

	@JsonProperty("Map")
	public List<Map> getMaps() {
		return maps;
	}

	@JsonProperty("Map")
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

}
