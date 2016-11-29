package com.shun.blog.service.music;

import java.util.List;

import com.shun.blog.model.portfolio.Music;

public interface MusicService {
	
	Music findById(int id);
	
	void saveMusic(Music music);
	
	void updateMusic(Music music);
	
	void deleteUserById(int id);

	List<Music> findAllMusics(); 
}