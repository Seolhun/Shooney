package com.shun.blog.model.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Map implements Serializable {
	private List<Integer> currency;
	private List<Integer> item;
	private int weekRestrictionIndex;
	private List<Integer> coordinate;

	@JsonProperty("Item")
	public List<Integer> getItem() {
		return item;
	}

	@JsonProperty("Item")
	public void setItem(List<Integer> item) {
		this.item = item;
	}

	@JsonProperty("WeekRestrictionIndex")
	public int getWeekRestrictionIndex() {
		return weekRestrictionIndex;
	}

	@JsonProperty("WeekRestrictionIndex")
	public void setWeekRestrictionIndex(int weekRestrictionIndex) {
		this.weekRestrictionIndex = weekRestrictionIndex;
	}

	@JsonProperty("Currency")
	public List<Integer> getCurrency() {
		return currency;
	}

	@JsonProperty("Currency")
	public void setCurrency(List<Integer> currency) {
		this.currency = currency;
	}

	@JsonProperty("coordinate")
	public List<Integer> getCoordinate() {
		return coordinate;
	}

	@JsonProperty("coordinate")
	public void setCoordinate(List<Integer> coordinate) {
		this.coordinate = coordinate;
	}

}
