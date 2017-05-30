package com.shun.mongodb.controller.github;

import com.google.gson.JsonObject;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.model.github.GitSearch;
import com.shun.mongodb.service.github.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/github")
public class GitHubRestController {
	private static final Logger LOG = LoggerFactory.getLogger(GitHubRestController.class);

	private static final String GITHUB_API = "https://api.github.com";

	private CommonService commonService;
	private GithubService githubService;

	@Autowired
	public GitHubRestController(CommonService commonService, GithubService githubService){
		this.commonService=commonService;
		this.githubService=githubService;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public JsonObject githubSearch(@RequestBody GitSearch gitSearch) throws Exception {
	    LOG.info("param : githubSearch {}", gitSearch.toString());
		JsonObject json = commonService.getResponseAPI(searchGithub(gitSearch));
		return json;
	}

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AjaxResult test(GitSearch gitSearch, AjaxResult ajaxResult) throws Exception {
        LOG.info("param : githubSearch {}", gitSearch.toString());
        List<String> lists = new ArrayList<>();
        lists.add("python");
        lists.add("django");
        gitSearch.setTopcis(lists);
        JsonObject json = commonService.getResponseAPI(searchGithub(gitSearch));
        ajaxResult.setResult(json.toString());
        return ajaxResult;
    }

    private String searchGithub(GitSearch gitSearch){
//		https://api.github.com/search/repositories?q=blog&topic:python+topic:java&language:java&sort=stars&order=desc
        String buildUrl = GITHUB_API+"/search"+gitSearch.getSearchType()+"?q=";
        buildUrl+=putStringFromList(gitSearch);
        LOG.info("return : buildUrl {}", buildUrl);
//        if(gitSearch!=null){
//            String sort=gitSearch.getSort();
//            String order=gitSearch.getOrder();
//            String fork=gitSearch.getFork();
//            List<String> topcis=gitSearch.getTopcis();
//
//            Integer forks=gitSearch.getForks();
//            Integer minSize=gitSearch.getMinSize();
//            Integer maxSize=gitSearch.getMaxSize();
//            Integer minStars=gitSearch.getMinStars();
//            Integer maxStars=gitSearch.getMaxStars();
//
//            buildUrl+="topic:"+topcis
//
//        }
        return buildUrl;
    }

    private String putStringFromList(GitSearch gitSearch){
        String param = "";
        List<String> list = null;
        if(gitSearch.getTopcis() != null){
            list = gitSearch.getTopcis();
        }
        if(list !=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                if(i==0){
                    param = "topic:"+list.get(i);
                } else if(i>0){
                    param+="+topic:"+list.get(i);
                }
            }
        }
        return param;
    }
}
