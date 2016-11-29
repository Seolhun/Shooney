package com.shun.blog.model.portfolio;

public enum MusicType {
	
	DOMESTIC("domestic"),
	OVERSEA("oversea");
	
	private String musicType;
	
	private MusicType(String musicType){
		this.musicType=musicType;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}
	
	
	
}
