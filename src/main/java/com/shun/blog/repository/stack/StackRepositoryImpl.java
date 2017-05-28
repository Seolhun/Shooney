package com.shun.blog.repository.stack;

import com.shun.blog.model.stack.Stack;
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
public class StackRepositoryImpl extends AbstractRepository<Long, Stack> implements StackRepository {
    private static final Logger LOG = LoggerFactory.getLogger(StackRepositoryImpl.class);

    @Override
    public void insert(Stack stack) {
        persist(stack);
    }

    @Override
    public Stack selectById(Long id) throws Exception {
        return getByLong(id);
    }

    @Override
    public Stack selectByName(String name) throws Exception {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Stack) crit.uniqueResult();
    }

    @Override

    public List<Stack> selectList() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria()
                .addOrder(Order.desc("counts"))
                .add(Restrictions.eq("delFlag", "N"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Stack> stacks = (List<Stack>) criteria.list();
        LOG.info("return : selectList {}", stacks);
        return stacks;
    }

    @Override
    public int getCount() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria()
                .add(Restrictions.eq("delFlag", "N"));
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public void deleteById(Long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Stack blog = (Stack) crit.uniqueResult();
        delete(blog);
    }
}
