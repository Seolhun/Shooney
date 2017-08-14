package com.shun.blog.repository.notice;

import com.shun.blog.model.notice.Notice;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepositoryImpl extends AbstractRepository<Long, Notice> implements NoticeRepository {
	private static final Logger LOG = LoggerFactory.getLogger(NoticeRepositoryImpl.class);

	@Override
	public void insertNotice(Notice notice) throws Exception {
		persist(notice);
	}

	@Override
	public Notice selectNoticeById(Long noticeId) {
		Notice notice = getByKey(noticeId);
		LOG.info("return selectNoticeById dbNotice : {}", notice.toString());
		return notice;
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<Notice> selectNoticeByURI(String uri) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("uri", uri));
        criteria.add(Restrictions.eq("deletedFlag", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<Notice> findAllByAdmin(Notice notice) {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
