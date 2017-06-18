package com.shun.mongodb.controller.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.common.Paging;
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
    public GitHubRestController(CommonService commonService, GithubService githubService) {
        this.commonService = commonService;
        this.githubService = githubService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    public GithubData githubSearch(@RequestBody GitSearch gitSearch, HttpServletRequest request) throws Exception {
        LOG.info("param : githubSearch {}", gitSearch.toString());
        String searchUrl = searchGithub(gitSearch);
        GithubData githubData = null;
        if(!(searchUrl.equals("error"))) {
            JsonObject json = commonService.getResponseAPI(searchUrl);
            ObjectMapper mapper = commonService.setJSONMapper();
            githubData = mapper.readValue(json.toString(), GithubData.class);

            String clientIp = commonService.getUserIP(request);
            gitSearch.getSearchUser().setIp(clientIp);

            githubData.setGitSearch(gitSearch);
            githubService.save(githubData);

            //(int totalCount, int currentPage, Integer limit, Integer blockLimit) throws NullPointerException {
            Paging paging = commonService.lastestSetPaging(githubData.getTotalCount(), gitSearch.getCurrentPage(), 30, 10);
            githubData.setPaging(paging);
        }
        return githubData;
    }

    private String searchGithub(GitSearch gitSearch) {
        //repositories?q=topic:python+topic:flask&sort=score&order=desc
        //repositories?q=blog+home+topic:django+topic:flask+language:python
        //repositories?q=rest+flask+topic:flask+python+mongodb
        //&sort=stars&order=desc
        //&page=1 (0,30)

        //if you insert "&" to search data, you must get the unnecessary result you wrote criteria.
        //but, if you wrote "+" criteria:, you can get what you want
        String searchUrl = "error";
        if (!(gitSearch.getNames() == null && gitSearch.getTopics() == null && gitSearch.getLanguages() == null)) {
            searchUrl = GITHUB_API + "/search" + gitSearch.getSearchType();

            String paramStr = "?q=";
            searchUrl+=buildSearchUrl(paramStr, gitSearch);
        }

        LOG.info("return : searchUrl {}", searchUrl);
        return searchUrl;
    }

    private String buildSearchUrl(String paramStr, GitSearch gitSearch) {
        List<String> list;
        // Name Part
        int globalIndex = 0;
        if (gitSearch.getNames() != null && gitSearch.getNames().size() > 0) {
            int searchUrlIndex = 0;
            list = gitSearch.getNames();

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex,"");
                searchUrlIndex++;
                paramStr += list.get(i);
            }
            globalIndex++;
        }

        // Topic Part
        if (gitSearch.getTopics() != null && gitSearch.getTopics().size() > 0) {
            int searchUrlIndex = 0;
            list = gitSearch.getTopics();

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex, "topic:");
                globalIndex++;
                searchUrlIndex++;
                paramStr += list.get(i);
            }
        }

        // language Part
        if (gitSearch.getLanguages() != null && gitSearch.getLanguages().size() > 0) {
            int searchUrlIndex = 0;
            list = gitSearch.getLanguages();

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            for (int i = 0; i < list.size(); i++) {
                paramStr = checkSearchIndex(paramStr, searchUrlIndex, "language:");
                globalIndex++;
                searchUrlIndex++;
                paramStr += list.get(i);
            }
        }

        Integer minSize = gitSearch.getMinSize();
        Integer maxSize = gitSearch.getMaxSize();
        Integer minForks = gitSearch.getMinForks();
        Integer maxForks = gitSearch.getMaxForks();
        Integer minStars = gitSearch.getMinStars();
        Integer maxStars = gitSearch.getMaxStars();


        if (minSize != null && maxSize != null) {
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "size:");
            if(minSize >= 0 && maxSize >= 0){
                paramStr += minSize+".."+maxSize;
            }
        } else if(maxSize == null && minSize != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "size:>=");
            if(minSize >= 0) {
                paramStr += minSize;
            }
        } else if(maxSize != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "size:<=");
            if(maxSize >= 0) {
                paramStr += maxSize;
            }
        }

        if (minForks != null && maxForks != null) {
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex,"forks:");
            if(minForks >= 0 && maxForks >= 0){
                paramStr += minForks+".."+maxForks;
            }
        } else if(maxForks == null && minForks != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex,"forks:>=");
            if(minForks >= 0) {
                paramStr += minForks;
            }
        } else if(maxForks != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "forks:<=");
            if(maxForks >= 0) {
                paramStr += maxForks;
            }
        }

        if (minStars != null && maxStars != null) {
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "+starts:");
            if(minStars >= 0 && maxStars >= 0){
                paramStr += minStars+".."+maxStars;
            }
        } else if(maxStars == null && minStars != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "starts:>=");
             if(minStars >= 0) {
                paramStr += minStars;
            }
        } else if(maxStars != null){
            int searchUrlIndex = 0;

            paramStr = checkFirstParam(paramStr, globalIndex);
            globalIndex++;

            paramStr = checkSearchIndex(paramStr, searchUrlIndex, "starts:<=");
            if(maxStars >= 0) {
                paramStr += maxStars;
            }
        }

        Integer currentPage = gitSearch.getCurrentPage();
        if(currentPage == null){
            currentPage = 1;
        }
        paramStr +="&page=" + currentPage;
        return paramStr;
    }

    private String checkFirstParam(String paramStr, int globalIndex) {
        if(globalIndex > 0){
            paramStr += "+";
        }
        return paramStr;
    }

    private String checkSearchIndex(String paramStr, int searchUrlIndex, String serarchParam) {
        if(searchUrlIndex <= 0){
            paramStr+=serarchParam;
        } else  {
            paramStr += "+";
        }
        return paramStr;
    }
}
