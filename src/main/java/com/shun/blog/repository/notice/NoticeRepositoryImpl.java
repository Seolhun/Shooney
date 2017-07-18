package com.shun.blog.repository.notice;

import com.shun.blog.model.notice.Notice;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepositoryImpl extends AbstractRepository<Long, Notice> implements NoticeRepository {
	static final Logger LOG = LoggerFactory.getLogger(NoticeRepositoryImpl.class);

	@Override
	public void insertNotice(Notice notice) throws Exception {
		LOG.info("param : insertNotice {}", notice.toString());
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
	public List<Notice> findAllByAdmin(Notice notice) throws Exception {
		LOG.info("param : findAllByAdmin {}", notice.toString());
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Notice> noticeList =criteria.list();
		return noticeList;
	}
}
