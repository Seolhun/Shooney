package com.shun.blog.service.blog;

import com.shun.blog.model.blog.BlogType;

import java.util.List;

public interface BlogTypeService {
    BlogType selectById(Long id) throws Exception;

    BlogType selectByName(String name) throws Exception;

    void insert(BlogType blogType);

    void update(BlogType blogType, int variableCount) throws Exception;

    void deleteById(Long id);

    List<BlogType> selectList() throws Exception;

    int getCount() throws Exception;
}
