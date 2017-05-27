package com.shun.blog.service.blog;

import com.shun.blog.model.blog.BlogType;
import com.shun.blog.repository.blog.BlogTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(transactionManager = "txManager")
public class BlogTypeServiceImpl implements BlogTypeService {
    private static final Logger LOG = LoggerFactory.getLogger(BlogTypeServiceImpl.class);

    private BlogTypeRepository blogTypeRepository;

    @Autowired
    public BlogTypeServiceImpl(BlogTypeRepository blogTypeRepository) {
        this.blogTypeRepository = blogTypeRepository;
    }

    @Override
    public BlogType selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        return blogTypeRepository.selectById(id);
    }

    @Override
    public BlogType selectByName(String name) throws Exception {
        LOG.info("param : selectByName {}", name);
        return blogTypeRepository.selectByName(name);
    }

    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
    public void insert(BlogType blogType) {
        LOG.info("param : insert {}", blogType.toString());
        blogTypeRepository.insert(blogType);
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "'selectListBoardType'", value = "selectListBoardType")})
    public List<BlogType> selectList() throws Exception {
        return blogTypeRepository.selectList();
    }

    @Override
    public int getCount() throws Exception {
        return blogTypeRepository.getCount();
    }


    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
    public void update(BlogType blogType, int variableCount) throws Exception {
        LOG.info("param : update {}", blogType.toString());
        BlogType dbBlogType = null;
        try {
            if (blogType.getId() != null) {
                dbBlogType = blogTypeRepository.selectById(blogType.getId());
            } else if (blogType.getName() != null) {
                dbBlogType = blogTypeRepository.selectByName(blogType.getName());
            }
        } catch (NullPointerException e) {
            LOG.info("error : update BlogType NullPointerException");
        }
        if (dbBlogType != null) {
            dbBlogType.setName(blogType.getName());
            dbBlogType.setCounts(dbBlogType.getCounts() + (variableCount));
            dbBlogType.setCreatedBy(blogType.getCreatedBy());
            dbBlogType.setModifiedBy(blogType.getModifiedBy());
            dbBlogType.setDepth(blogType.getDepth());
        }

    }

    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        blogTypeRepository.deleteById(id);
    }
}
