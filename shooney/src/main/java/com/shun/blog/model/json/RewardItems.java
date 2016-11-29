package com.shun.blog.model.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RewardItems implements Serializable {
	private Integer has_rate_condition;
	private String item;
	private String is_week_restriction_one;
	private String Quest;

	@JsonProperty("has_rate_condition")
	public Integer getHas_rate_condition() {
		return has_rate_condition;
	}

	@JsonProperty("has_rate_condition")
	public void setHas_rate_condition(Integer has_rate_condition) {
		this.has_rate_condition = has_rate_condition;
	}

	@JsonProperty("Item")
	public String getItem() {
		return item;
	}

	@JsonProperty("Item")
	public void setItem(String item) {
		this.item = item;
	}

	@JsonProperty("is_week_restriction_one")
	public String getIs_week_restriction_one() {
		return is_week_restriction_one;
	}

	@JsonProperty("is_week_restriction_one")
	public void setIs_week_restriction_one(String is_week_restriction_one) {
		this.is_week_restriction_one = is_week_restriction_one;
	}

	@JsonProperty("Quest")
	public String getQuest() {
		return Quest;
	}

	@JsonProperty("Quest")
	public void setQuest(String quest) {
		Quest = quest;
	}

}
