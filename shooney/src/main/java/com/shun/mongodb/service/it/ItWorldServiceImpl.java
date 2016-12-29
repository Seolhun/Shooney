package com.shun.mongodb.service.it;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.mongodb.model.it.ItWorld;
import com.shun.mongodb.model.it.ItWorldDao;


@Service("ItWorldService")
@Transactional
public class ItWorldServiceImpl implements ItWorldService {

	@Autowired
	private ItWorldDao iDao;

	@Override
	public ItWorld findItWorldById(String id) {
		return iDao.findItById(id);
	}

	@Override
	public void saveItWorld(ItWorld itWorld) {
		iDao.save(itWorld);
	}

	@Override
	public void updateItWorld(ItWorld itWorld) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItWorldById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItWorld> findAllItWorlds() {
		// TODO Auto-generated method stub
		return null;
	}
}
