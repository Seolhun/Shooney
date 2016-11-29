package com.shun.blog.model.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MiddleBoss {
	private List<Object> rewardItems;
	private Integer clearA;
	private Integer clearB;
	private List<Object> subBNpcNames;
	private Integer clearC;
	private List<Treasure> treasures;
	private List<Long> bNpcNames;

	@JsonProperty("RewardItems")
	public List<Object> getRewardItems() {
		return rewardItems;
	}

	@JsonProperty("RewardItems")
	public void setRewardItems(List<Object> rewardItems) {
		this.rewardItems = rewardItems;
	}

	@JsonProperty("ClearA")
	public Integer getClearA() {
		return clearA;
	}

	@JsonProperty("ClearA")
	public void setClearA(Integer clearA) {
		this.clearA = clearA;
	}

	@JsonProperty("ClearB")
	public Integer getClearB() {
		return clearB;
	}

	@JsonProperty("ClearB")
	public void setClearB(Integer clearB) {
		this.clearB = clearB;
	}

	@JsonProperty("SubBNpcNames")
	public List<Object> getSubBNpcNames() {
		return subBNpcNames;
	}

	@JsonProperty("SubBNpcNames")
	public void setSubBNpcNames(List<Object> subBNpcNames) {
		this.subBNpcNames = subBNpcNames;
	}

	@JsonProperty("ClearC")
	public Integer getClearC() {
		return clearC;
	}

	@JsonProperty("ClearC")
	public void setClearC(Integer clearC) {
		this.clearC = clearC;
	}

	@JsonProperty("Treasure")
	public List<Treasure> getTreasures() {
		return treasures;
	}

	@JsonProperty("Treasure")
	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}

	@JsonProperty("BNpcNames")
	public List<Long> getbNpcNames() {
		return bNpcNames;
	}

	@JsonProperty("BNpcNames")
	public void setbNpcNames(List<Long> bNpcNames) {
		this.bNpcNames = bNpcNames;
	}

}
