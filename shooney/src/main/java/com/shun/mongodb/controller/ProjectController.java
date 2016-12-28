package com.shun.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shun.mongodb.model.project.Project;
import com.shun.mongodb.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService pService;

//	@PostConstruct
//	public void initData() {
//		pService.deleteAll();
//		pService.save(new Project("1", "Project 1 Title","Project 1 Content",new Date()));
//		pService.save(new Project("2", "Project 2 Title","Project 2 Content",new Date()));
//		pService.save(new Project("3", "Project 3 Title","Project 3 Content",new Date()));
//	}

	@RequestMapping("/project")
	public List<Project> getAllProjects() {
		List<Project> projects=pService.findAllProjects();
		return projects;
	}

	@RequestMapping(value = "/project/{projectId}")
	public Project getProjectById(@PathVariable("projectId") String id) {
		Project project=pService.findById(id);
		return project;
	}
}