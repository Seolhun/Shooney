package com.shun.blog.dao.music;

import java.util.List;

import com.shun.blog.model.portfolio.Music;

public interface MusicDao {

	Music findById(int id);

	List<Music> findAllMusics();

	void saveMusic(Music music);

	void deleteUserById(int id);
}
