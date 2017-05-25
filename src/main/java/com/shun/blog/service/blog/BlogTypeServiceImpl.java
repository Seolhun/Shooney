package com.shun.blog.service.blog;

import com.shun.blog.model.blog.BlogType;
import com.shun.blog.repository.blog.BlogTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void insert(BlogType blogType) {
        LOG.info("param : insert {}", blogType.toString());
        blogTypeRepository.insert(blogType);
    }

    @Override
    public BlogType selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        return blogTypeRepository.selectById(id);
    }

    @Override
    public List<BlogType> selectList() throws Exception {
        return blogTypeRepository.selectList();
    }

    @Override
    public int getCount() throws Exception {
        return blogTypeRepository.getCount();
    }

    @Override
    public void update(BlogType blogType, int variableCount) throws Exception {
        LOG.info("param : update {}", blogType.toString());
        BlogType dbBlogType = blogTypeRepository.selectById(blogType.getId());
        //읽을시 쿠키 읽기
        dbBlogType.setCounts(dbBlogType.getCounts()+(variableCount));
        dbBlogType.setTitle(blogType.getTitle());
        dbBlogType.setCreatedBy(blogType.getCreatedBy());
        dbBlogType.setModifiedBy(blogType.getModifiedBy());
        dbBlogType.setDepth(blogType.getDepth());
    }

    @Override
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        blogTypeRepository.deleteById(id);
    }
}
