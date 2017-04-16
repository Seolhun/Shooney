package com.shun.mongodb.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.portfolio.music.Music;
import com.shun.mongodb.repository.music.MusicRepository;


@Service("musicService")
@Transactional
public class MusicServiceImpl implements MusicService {

	@Autowired
	private MusicRepository musicRepository;

	public Music findById(Long id) {
		return musicRepository.findById(id);
	}

	public void saveMusic(Music music) {
		musicRepository.saveMusic(music);
	}

	public void updateMusic(Music music) {
		Music entity = musicRepository.findById(music.getId());
		if (entity != null) {
			entity.setSinger(music.getSinger());
			entity.setTitle(music.getTitle());
		}
	}

	@Override
	public void deleteUserById(Long id) {
		musicRepository.deleteUserById(id);
	}

	@Override
	public List<Music> findAllMusics() {
		return musicRepository.findAllMusics();
	}
}
