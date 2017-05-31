package com.shun.mongodb.service.github;

import com.google.gson.JsonObject;
import com.shun.mongodb.model.github.GithubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GithubService {

    void save(GithubRepository github);

    GithubRepository findById(String id);

    GithubRepository findByIdx(Long idx);

    Page<GithubRepository> findAll(Pageable pageable);

    long count();

    void update(GithubRepository githubRepository);

    void delete(String id);

    List<GithubRepository> selectList();

    JsonObject getGitAPI(GithubRepository githubRepository) throws Exception;
}