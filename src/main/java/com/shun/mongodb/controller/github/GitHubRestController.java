package com.shun.mongodb.controller.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.model.github.GithubData;
import com.shun.mongodb.model.github.search.GitSearch;
import com.shun.mongodb.service.github.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/github")
public class GitHubRestController {
    private static final Logger LOG = LoggerFactory.getLogger(GitHubRestController.class);

    private static final String GITHUB_API = "https://api.github.com";

    private CommonService commonService;
    private GithubService githubService;

    @Autowired
    public GitHubRestController(CommonService commonService, GithubService githubService) {
        this.commonService = commonService;
        this.githubService = githubService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    public AjaxResult githubSearch(@RequestBody GitSearch gitSearch, AjaxResult ajaxResult, HttpServletRequest request) throws Exception {
        LOG.info("param : githubSearch {}", gitSearch.toString());
        JsonObject json = commonService.getResponseAPI(searchGithub(gitSearch));
        ObjectMapper mapper = commonService.setJSONMapper();
        GithubData githubData = mapper.readValue(json.toString(), GithubData.class);

        String clientIp = commonService.getUserIP(request);
        gitSearch.getSearchUser().setIp(clientIp);

        githubData.setGitSearch(gitSearch);
        githubService.save(githubData);
        ajaxResult.setResult(json.toString());
        return ajaxResult;
    }

    private String searchGithub(GitSearch gitSearch) {
//		https://api.github.com/search/repositories?q=blog&topic:python+topic:java&language:java&sort=stars&order=desc
        String searchUrl = "Not Found Search Value";
        if (gitSearch != null) {
            searchUrl = GITHUB_API + "/search" + gitSearch.getSearchType();

            String paramStr = "?q=";
            searchUrl+=buildSearchUrl(paramStr, gitSearch);
        }

        LOG.info("return : searchUrl {}", searchUrl);
        return searchUrl;
    }

    private String buildSearchUrl(String paramStr, GitSearch gitSearch) {
        int searchUrlIndex = 0;

        // Name Part
        if (gitSearch.getNames().size() > 0) {
            List<String> list = gitSearch.getNames();
            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex);
                searchUrlIndex++;
                paramStr+= list.get(i);
            }
        }

        // Topic Part
        if (gitSearch.getTopics().size() > 0) {
            List<String> list = gitSearch.getTopics();
            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex);
                searchUrlIndex++;
                paramStr += "topic:" + list.get(i);
            }
        }

        // language Part
        if (gitSearch.getLanguages().size() > 0) {
            List<String> list = gitSearch.getLanguages();
            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex);
                searchUrlIndex++;
                paramStr += "language:" + list.get(i);
            }
        }

        Integer minSize = gitSearch.getMinSize();
        Integer maxSize = gitSearch.getMaxSize();
        Integer minForks = gitSearch.getMinForks();
        Integer maxForks = gitSearch.getMaxForks();
        Integer minStars = gitSearch.getMinStars();
        Integer maxStars = gitSearch.getMaxStars();


        if (minSize != null && maxSize != null) {
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(minSize >= 0 && maxSize >= 0){
                paramStr += "size:"+minSize+".."+maxSize;
            }
        } else if(maxSize == null && minSize != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(minSize >= 0) {
                paramStr += "size:>="+minSize;
            }
        } else if(maxSize != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(maxSize >= 0) {
                paramStr += "size:<="+maxSize;
            }
        }

        if (minForks != null && maxForks != null) {
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(minForks >= 0 && maxForks >= 0){
                paramStr += "forks:"+minForks+".."+maxForks;
            }
        } else if(maxForks == null && minForks != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(minForks >= 0) {
                paramStr += "forks:>="+minForks;
            }
        } else if(maxForks != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(maxForks >= 0) {
                paramStr += "forks:<="+maxForks;
            }
        }

        if (minStars != null && maxStars != null) {
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(minStars >= 0 && maxStars >= 0){
                paramStr += "+starts:"+minStars+".."+maxStars;
            }
        } else if(maxStars == null && minStars != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
             if(minStars >= 0) {
                paramStr += "starts:>="+minStars;
            }
        } else if(maxStars != null){
            paramStr = checkSearchIndex(paramStr, searchUrlIndex);
            searchUrlIndex++;
            if(maxStars >= 0) {
                paramStr += "starts:<="+maxStars;
            }
        }

        return paramStr;
    }

    private String checkSearchIndex(String paramStr, int searchUrlIndex) {
        if (searchUrlIndex != 0) {
            paramStr+="+";
        }
        return paramStr;
    }
}
