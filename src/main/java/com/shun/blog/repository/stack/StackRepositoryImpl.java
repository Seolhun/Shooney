package com.shun.blog.repository.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class StackRepositoryImpl extends AbstractRepository<Long, Stack> implements StackRepository {
    private static final Logger LOG = LoggerFactory.getLogger(StackRepositoryImpl.class);

    @Override
    public void insert(Stack stack) {
        persist(stack);
    }

    @Override
    public Stack selectById(Long id) throws Exception {
        LOG.info("param : StackRepository.selectById {}", id);
        return getByLong(id);
    }

    @Override
    public Stack selectByName(String name) throws Exception {
        LOG.info("param : StackRepository.selectByName {}", name.toString());
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Stack) criteria.uniqueResult();
    }

    @Override

    public List<Stack> selectList(Stack stack) throws Exception {
        LOG.info("param : StackRepository.selectList {}", stack.toString());
        // 검색 로직
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("delFlag", "N"));
//        criteria.add(Restrictions.eq("similarStacks", stack));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Stack> stacks = (List<Stack>) criteria.uniqueResult();
        LOG.info("return : StackRepository.selectList {}", stacks.toString());
        return stacks;
    }

    @Override
    public int getCount() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("delFlag", "N"));
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public void deleteById(Long id) {
        LOG.info("param : StackRepository.deleteById {}", id);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        Stack blog = (Stack) criteria.uniqueResult();
        delete(blog);
    }
}
