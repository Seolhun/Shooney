package com.shun.mongodb.repository.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shun.mongodb.model.news.NewsData;

@Repository
public interface NewsDataRepository extends PagingAndSortingRepository<NewsData, String> {

	List<NewsData> findByIdx(Long idx);

    Page<NewsData> findByIdx(Long idx, Pageable pageable);
}
