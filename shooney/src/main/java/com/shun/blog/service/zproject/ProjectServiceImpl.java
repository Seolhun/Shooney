package com.shun.blog.service.zproject;
//package com.shun.blog.service.project;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.shun.blog.dao.project.ProjectDao;
//import com.shun.blog.model.project.Project;
//
//@Service("ProjectService")
//@Transactional
//public class ProjectServiceImpl implements ProjectService {
//	
//	@Autowired
//	private ProjectDao pDao;
//
//	private static final AtomicLong counter = new AtomicLong();
//
//	private static List<Project> Projects;
//
//	static {
//		Projects = populateDummyProjects();
//	}
//
//	@Override
//	public List<Project> findAllProjects() {
//		return Projects;
//	}
//
//	@Override
//	public Project findById(int id) {
//		for (Project Project : Projects) {
//			if (Project.getId() == id) {
//				return Project;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Project findByName(String title) {
//		for (Project Project : Projects) {
//			if (Project.getTitle().equalsIgnoreCase(title)) {
//				return Project;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public void saveProject(Project project) {
//		project.setId( counter.incrementAndGet());
//		Projects.add(project);
////		pDao.create(project);
//	}
//
//	@Override
//	public void updateProject(Project project) {
//		int index = Projects.indexOf(project);
//		Projects.set(index, project);
//	}
//
//	@Override
//	public void deleteProjectById(int id) {
//
//		for (Iterator<Project> iterator = Projects.iterator(); iterator.hasNext();) {
//			Project Project = iterator.next();
//			if (Project.getId() == id) {
//				iterator.remove();
//			}
//		}
//	}
//	
//	@Override
//	public boolean isProjectExist(Project Project) {
//		return findByName(Project.getTitle()) != null;
//	}
//
//	private static List<Project> populateDummyProjects() {
//		List<Project> Projects = new ArrayList<Project>();
//		Projects.add(new Project(counter.incrementAndGet(), "Project6", "자바6", new Date()));
//		Projects.add(new Project(counter.incrementAndGet(), "Project5", "자바5", new Date()));
//		Projects.add(new Project(counter.incrementAndGet(), "Project4", "자바4", new Date()));
//		Projects.add(new Project(counter.incrementAndGet(), "Project3", "자바3", new Date()));
//		Projects.add(new Project(counter.incrementAndGet(), "Project2", "자바2", new Date()));
//		Projects.add(new Project(counter.incrementAndGet(), "Project1", "자바1", new Date()));
//		return Projects;
//	}
//
//	public void deleteAllProjects() {
//		Projects.clear();
//	}
//
//}
