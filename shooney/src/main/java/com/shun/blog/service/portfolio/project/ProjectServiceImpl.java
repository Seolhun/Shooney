package com.shun.blog.service.portfolio.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.portfolio.project.Project;

@Service("ProjectService")
@Transactional
public class ProjectServiceImpl implements ProjectService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Project> Projects;
	
	static{
		Projects= populateDummyProjects();
	}

	public List<Project> findAllProjects() {
		return Projects;
	}
	
	public Project findById(long id) {
		for(Project Project : Projects){
			if(Project.getId() == id){
				return Project;
			}
		}
		return null;
	}
	
	public Project findByName(String title) {
		for(Project Project : Projects){
			if(Project.getTitle().equalsIgnoreCase(title)){
				return Project;
			}
		}
		return null;
	}
	
	public void saveProject(Project Project) {
		Project.setId(counter.incrementAndGet());
		Projects.add(Project);
	}

	public void updateProject(Project Project) {
		int index = Projects.indexOf(Project);
		Projects.set(index, Project);
	}

	public void deleteProjectById(long id) {
		
		for (Iterator<Project> iterator = Projects.iterator(); iterator.hasNext(); ) {
		    Project Project = iterator.next();
		    if (Project.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isProjectExist(Project Project) {
		return findByName(Project.getTitle())!=null;
	}

	private static List<Project> populateDummyProjects(){
		List<Project> Projects = new ArrayList<Project>();
		Projects.add(new Project(counter.incrementAndGet(), "Project5", "자바5", new Date()));
		Projects.add(new Project(counter.incrementAndGet(),"Project4","자바4", new Date()));
		Projects.add(new Project(counter.incrementAndGet(),"Project3","자바3", new Date()));
		Projects.add(new Project(counter.incrementAndGet(),"Project2","자바2", new Date()));
		Projects.add(new Project(counter.incrementAndGet(),"Project1","자바1", new Date()));
		return Projects;
	}

	public void deleteAllProjects() {
		Projects.clear();
	}

}
