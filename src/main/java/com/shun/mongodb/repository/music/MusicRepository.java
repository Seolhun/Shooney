package com.shun.mongodb.repository.music;

import java.util.List;

import com.shun.blog.model.portfolio.music.Music;

public interface MusicRepository {

	Music findById(Long id);

	List<Music> findAllMusics();

	void saveMusic(Music music);

	void deleteUserById(Long id);
}
