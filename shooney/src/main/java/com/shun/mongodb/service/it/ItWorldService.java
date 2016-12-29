package com.shun.mongodb.service.it;

import java.util.List;

import com.shun.mongodb.model.it.ItWorld;

public interface ItWorldService {
	
	ItWorld findItWorldById(String id);
	
	void saveItWorld(ItWorld itWorld);
	
	void updateItWorld(ItWorld itWorld);
	
	void deleteItWorldById(String id);

	List<ItWorld> findAllItWorlds(); 
}