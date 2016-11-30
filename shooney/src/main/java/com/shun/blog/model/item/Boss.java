package com.shun.blog.model.item;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Boss implements Serializable {
	private List<RewardItems> rewardItems;
	private int clearB;
	private List<Object> subBNpcNames;
	private int clearC;
	private int clearA;
	private List<Treasure> treasure;
	private List<Long> bNpcNames;

	@JsonProperty("Treasure")
	public List<Treasure> getTreasure() {
		return treasure;
	}

	@JsonProperty("Treasure")
	public void setTreasure(List<Treasure> treasure) {
		this.treasure = treasure;
	}

	@JsonProperty("RewardItems")
	public List<RewardItems> getRewardItems() {
		return rewardItems;
	}

	@JsonProperty("RewardItems")
	public void setRewardItems(List<RewardItems> rewardItems) {
		this.rewardItems = rewardItems;
	}

	@JsonProperty("SubBNpcNames")
	public List<Object> getSubBNpcNames() {
		return subBNpcNames;
	}

	@JsonProperty("SubBNpcNames")
	public void setSubBNpcNames(List<Object> subBNpcNames) {
		this.subBNpcNames = subBNpcNames;
	}

	@JsonProperty("ClearB")
	public int getClearB() {
		return clearB;
	}

	@JsonProperty("ClearB")
	public void setClearB(int clearB) {
		this.clearB = clearB;
	}

	@JsonProperty("ClearC")
	public int getClearC() {
		return clearC;
	}

	@JsonProperty("ClearC")
	public void setClearC(int clearC) {
		this.clearC = clearC;
	}

	@JsonProperty("ClearA")
	public int getClearA() {
		return clearA;
	}

	@JsonProperty("ClearA")
	public void setClearA(int clearA) {
		this.clearA = clearA;
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
