package com.shun.mongodb.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.mongodb.model.project.Project;
import com.shun.mongodb.model.project.ProjectDao;

@Service("ProjectService")
@Transactional(noRollbackFor = { Exception.class, NullPointerException.class })
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao pDao;

	@Override
	public List<Project> findAllProjects() {
		return pDao.findAll();
	}
	
	@Override
	public Project findById(String id) {
		return pDao.findProjectById(id);
	}

	@Override
	public void saveProject(Project Project) {

	}

	@Override
	public void updateProject(Project Project) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProjectById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllProjects() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isProjectExist(Project Project) {
		// TODO Auto-generated method stub
		return false;
	}

}
