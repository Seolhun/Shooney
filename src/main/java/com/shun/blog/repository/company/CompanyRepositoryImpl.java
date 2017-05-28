package com.shun.blog.repository.company;

import com.shun.blog.model.stack.Company;
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
public class CompanyRepositoryImpl extends AbstractRepository<Long, Company> implements CompanyRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyRepository.class);

    @Override
    public void insert(Company company) {
        persist(company);
    }

    @Override
    public Company selectById(Long id) throws Exception {
        return getByLong(id);
    }

    @Override
    public Company selectByName(String name) throws Exception {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Company) crit.uniqueResult();
    }

    @Override

    public List<Company> selectList() throws Exception {
        // 검색 로직
        Criteria criteria = createEntityCriteria()
                .addOrder(Order.desc("counts"))
                .add(Restrictions.eq("delFlag", "N"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Company> companys = (List<Company>) criteria.list();
        LOG.info("return : selectList {}", companys);
        return companys;
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
        Company blog = (Company) crit.uniqueResult();
        delete(blog);
    }
}
