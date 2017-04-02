package com.shun.blog.repository.portfolio.music;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.portfolio.music.Music;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class MusicRepositoryImpl extends AbstractRepository<Integer, Music> implements MusicRepository {

	static final Logger logger = LoggerFactory.getLogger(MusicRepositoryImpl.class);
	
	public Music findById(Long id) {
		Music music = getByLong(id);
		return music;
	}
	
	@SuppressWarnings("unchecked")
	public List<Music> findAllMusics() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Music> musics =(List<Music>) criteria.list();
		return musics;
	}

	public void saveMusic(Music music) {
		persist(music);
	}

	public void deleteUserById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Music music = (Music)crit.uniqueResult();
		delete(music);
	}
}
