package com.shun.mongodb.service.github;

import com.google.gson.JsonObject;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.model.github.GithubData;
import com.shun.mongodb.repository.github.GithubDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager="txManager")
public class GithubServiceImpl implements GithubService {
    private static final Logger LOG = LoggerFactory.getLogger(GithubServiceImpl.class);
    private final static String GITHUB_API_URL = "https://api.github.com/";

	private CommonService commonService;
	private GithubDataRepository githubDataRepository;

	@Autowired
	public GithubServiceImpl(CommonService commonService, GithubDataRepository githubDataRepository) {
	    this.commonService=commonService;
	    this.githubDataRepository = githubDataRepository;
	}

    @Override
    public void save(GithubData githubRepository) {
        LOG.info("param : save : {}", githubRepository.toString());
        githubDataRepository.save(githubRepository);
    }

    @Override
    public GithubData findById(String id) {
        LOG.info("param : findOne : {}", id);
        return githubDataRepository.findOne(id);
    }

    @Override
    public GithubData findByIdx(Long idx) {
        LOG.info("param : findByIdx : {}", idx);
        return githubDataRepository.findByIdx(idx);
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "#pageable+'findGithubList'", value = "findGithubList")})
    public Page<GithubData> findAll(Pageable pageable) {
        LOG.info("param : findAll : {}", pageable.toString());
        return githubDataRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return githubDataRepository.count();
    }

    @Override
    public void update(GithubData githubRepository) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String idx) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<GithubData> selectList() {
        return githubDataRepository.findAll();
    }

    @Override
	public JsonObject getGitAPI(GithubData githubRepository) throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject = commonService.getResponseAPI(GITHUB_API_URL+"search/repositories?q=blog&topic:python+topic:java&language:java&sort=stars&order=desc");

        return jsonObject;
	}

}
