package com.shun.mongodb.repository.github;

import java.util.List;

import com.shun.mongodb.model.github.GithubData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubDataRepository extends MongoRepository<GithubData, String> {

    GithubData findByIdx(Long idx);

    long count();

    List<GithubData> findAll();

    Page<GithubData> findAll(Pageable pageable);
}
