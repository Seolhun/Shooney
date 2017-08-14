package com.shun.blog.repository.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StackFileRepositoryImpl extends AbstractRepository<Long, StackFile> implements StackFileRepository {
    private static final Logger LOG = LoggerFactory.getLogger(StackFileRepository.class);

    @Override
    public void insert(StackFile stackFile) {
        persist(stackFile);
    }

    @Override
    public StackFile selectById(Long id) throws Exception {
        return getByLong(id);
    }

    @Override
    public StackFile selectByName(String name) throws Exception {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("savedName", name));
        return (StackFile) crit.uniqueResult();
    }

    @Override

    public List<StackFile> selectList(Stack stack) throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("stackInFile", stack));
        criteria.add(Restrictions.eq("deletedFlag", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<StackFile> stackFiles = (List<StackFile>) criteria.list();
        return stackFiles;
    }

    @Override
    public int getCount() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedFlag", false));
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public void deleteById(Long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        StackFile blog = (StackFile) crit.uniqueResult();
        delete(blog);
    }
}
