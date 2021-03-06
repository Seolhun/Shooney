package com.shun.blog.repository.blog;

import com.shun.blog.model.blog.BlogType;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogTypeRepositoryImpl extends AbstractRepository<Long, BlogType> implements BlogTypeRepository {
    private static final Logger LOG = LoggerFactory.getLogger(BlogTypeRepositoryImpl.class);

    @Override
    public void insert(BlogType blogType) {
        persist(blogType);
    }

    @Override
    public BlogType selectById(Long id) throws Exception {
        return getByLong(id);
    }

    @Override
    public BlogType selectByName(String name) throws Exception {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (BlogType) crit.uniqueResult();
    }

    @Override

    public List<BlogType> selectList() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria()
                .addOrder(Order.desc("counts"))
                .add(Restrictions.eq("deletedFlag", false))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<BlogType> blogTypes = (List<BlogType>) criteria.list();
        LOG.info("return : selectList {}", blogTypes);
        return blogTypes;
    }

    @Override
    public int getCount() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria()
                .add(Restrictions.eq("deletedFlag", false));
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public void deleteById(Long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        BlogType blog = (BlogType) crit.uniqueResult();
        delete(blog);
    }
}
