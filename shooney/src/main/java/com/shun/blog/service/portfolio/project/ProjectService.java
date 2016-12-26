package com.shun.blog.service.portfolio.project;

import java.util.List;

import com.shun.blog.model.portfolio.project.Project;


public interface ProjectService {
	
	Project findById(long id);
	
	Project findByName(String name);
	
	void saveProject(Project Project);
	
	void updateProject(Project Project);
	
	void deleteProjectById(long id);

	List<Project> findAllProjects(); 
	
	void deleteAllProjects();
	
	public boolean isProjectExist(Project Project);
	
}
