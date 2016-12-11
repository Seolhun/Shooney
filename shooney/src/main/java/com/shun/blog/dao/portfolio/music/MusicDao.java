package com.shun.blog.dao.portfolio.music;

import java.util.List;

import com.shun.blog.model.portfolio.music.Music;

public interface MusicDao {

	Music findById(int id);

	List<Music> findAllMusics();

	void saveMusic(Music music);

	void deleteUserById(int id);
}
