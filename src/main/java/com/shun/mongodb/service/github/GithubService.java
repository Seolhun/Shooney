package com.shun.mongodb.service.github;

import com.google.gson.JsonObject;
import com.shun.mongodb.model.github.GithubData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GithubService {

    void save(GithubData github);

    GithubData findById(String id);

    GithubData findByIdx(Long idx);

    Page<GithubData> findAll(Pageable pageable);

    long count();

    void update(GithubData githubData);

    void delete(String id);

    List<GithubData> selectList();

    JsonObject getGitAPI(GithubData githubData) throws Exception;
}