package com.shun.blog.controller.portfolio;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.model.portfolio.project.Greeting;

@RestController
@RequestMapping("/")
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
	
	@RequestMapping(value = "/po/project/list", method = RequestMethod.GET)
	public String webp(ModelMap model) {
		
		return "portfolio/project/list";
	}
	
//	ObjectMapper mapper = cFn.setJSONMapper();
//	for (int i = 0; i < items.size(); i++) {
//		String jsonData = items.get(i).getData();
//		ItemData itemdata = mapper.readValue(jsonData, ItemData.class);
//		items.get(i).setItemData(itemdata);
//	}
}
