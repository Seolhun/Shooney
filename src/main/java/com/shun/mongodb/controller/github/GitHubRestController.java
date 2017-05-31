package com.shun.mongodb.controller.github;

import com.google.gson.JsonObject;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.model.github.search.GitSearch;
import com.shun.mongodb.service.github.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public AjaxResult githubSearch(@RequestBody GitSearch gitSearch, AjaxResult ajaxResult) throws Exception {
        LOG.info("param : githubSearch {}", gitSearch.toString());
        JsonObject json = commonService.getResponseAPI(searchGithub(gitSearch));

        ajaxResult.setResult(json.toString());
        return ajaxResult;
    }

    private String searchGithub(GitSearch gitSearch) {
//		https://api.github.com/search/repositories?q=blog&topic:python+topic:java&language:java&sort=stars&order=desc
        String searchUrl = "Not Found Search Value";
        if (gitSearch != null) {
            searchUrl = GITHUB_API + "/search" + gitSearch.getSearchType() + "?q=";
            searchUrl += buildSearchUrl(searchUrl, gitSearch);
        }

        LOG.info("return : buildUrl {}", searchUrl);
        return searchUrl;
    }

    private String buildSearchUrl(String searchUrl, GitSearch gitSearch) {
        List<String> list;
        int searchUrlIndex = 0;

        // Name Part
        if (gitSearch.getNames() != null) {
            list = gitSearch.getNames();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    checkSearchIndex(searchUrl, searchUrlIndex);
                    searchUrl += list.get(i);
                }
            }
        }

        // language Part
        if (gitSearch.getLanguages() != null) {
            list = gitSearch.getLanguages();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    checkSearchIndex(searchUrl, searchUrlIndex);
                    searchUrl += "language:" + list.get(i);
                }

            }
        }

        // Topic Part
        if (gitSearch.getTopics() != null) {
            list = gitSearch.getTopics();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    checkSearchIndex(searchUrl, searchUrlIndex);
                    searchUrl += "topic:" + list.get(i);

                }
            }
        }

        if (gitSearch.getMinSize() != null && gitSearch.getMaxSize() != null) {
            checkSearchIndex(searchUrl, searchUrlIndex);
            searchUrl += "size:"+gitSearch.getMinSize()+".."+gitSearch.getMaxSize();
        } else {
            checkSearchIndex(searchUrl, searchUrlIndex);
            if (gitSearch.getMinSize() != null || gitSearch.getMaxSize() == null) {
                searchUrl += "size:>="+gitSearch.getMinSize();
            } else if (gitSearch.getMaxSize() != null || gitSearch.getMinSize() == null) {
                searchUrl += "&size:<="+gitSearch.getMinSize()+".."+gitSearch.getMaxSize();
            }
        }

        if (gitSearch.getMinForks() != null && gitSearch.getMaxForks() != null) {
            checkSearchIndex(searchUrl, searchUrlIndex);
            searchUrl += "+forks:"+gitSearch.getMinForks()+".."+gitSearch.getMaxForks();
        } else {
            checkSearchIndex(searchUrl, searchUrlIndex);
            if (gitSearch.getMinForks() != null || gitSearch.getMaxForks() == null) {
                searchUrl += "+forks:>="+gitSearch.getMinForks();
            } else if (gitSearch.getMaxForks() != null || gitSearch.getMinForks() == null) {
                searchUrl += "+forks:<="+gitSearch.getMinForks()+".."+gitSearch.getMinForks();
            }
        }

        if (gitSearch.getMinStars() != null && gitSearch.getMaxStars() != null) {
            checkSearchIndex(searchUrl, searchUrlIndex);
            searchUrl += "+starts:"+gitSearch.getMinStars()+".."+gitSearch.getMaxStars();
        } else {
            checkSearchIndex(searchUrl, searchUrlIndex);
            if (gitSearch.getMinStars() != null || gitSearch.getMaxStars() == null) {
                searchUrl += "+starts:>="+gitSearch.getMinStars();
            } else if (gitSearch.getMaxStars() != null || gitSearch.getMinStars() == null) {
                searchUrl += "+starts:<="+gitSearch.getMaxStars()+".."+gitSearch.getMinStars();
            }
        }
        return searchUrl;
    }

    private String checkSearchIndex(String searchUrl, int searchUrlIndex) {
        if (searchUrlIndex != 0) {
            searchUrl+="+";
        }
        searchUrlIndex++;
        return searchUrl;
    }
}
