package com.shun.blog.service.portfolio.music;

import java.util.List;

import com.shun.blog.model.portfolio.music.Music;

public interface MusicService {
	
	Music findById(int id);
	
	void saveMusic(Music music);
	
	void updateMusic(Music music);
	
	void deleteUserById(int id);

	List<Music> findAllMusics(); 
}