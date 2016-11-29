package com.shun.blog.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.music.MusicDao;
import com.shun.blog.model.portfolio.Music;


@Service("musicService")
@Transactional
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicDao dao;

	public Music findById(int id) {
		return dao.findById(id);
	}

	public void saveMusic(Music music) {
		dao.saveMusic(music);
	}

	public void updateMusic(Music music) {
		Music entity = dao.findById(music.getId());
		if (entity != null) {
			entity.setSinger(music.getSinger());
			entity.setTitle(music.getTitle());
			entity.setLatestDate(music.getLatestDate());
		}
	}

	@Override
	public void deleteUserById(int id) {
		dao.deleteUserById(id);
	}

	@Override
	public List<Music> findAllMusics() {
		return dao.findAllMusics();
	}
}
