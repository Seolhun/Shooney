package com.shun.blog.service.notice;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.notice.Notice;
import com.shun.blog.repository.notice.NoticeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "txManager")
public class NoticeServiceImpl implements NoticeService {
    static final Logger LOG = LoggerFactory.getLogger(NoticeService.class);

    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public void insert(Notice notice) throws Exception {
        LOG.info("param : insert {}", notice.toString());
        noticeRepository.insert(notice);
    }

    @Override
    public Notice selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        Notice notice = noticeRepository.selectById(id);
        return notice;
    }

    @Override
    public int getCount(Paging paging) throws Exception {
        LOG.info("param : selectList {}", paging.toString());
        return noticeRepository.getCount(paging);
    }

    @Override
    public List<Notice> selectList(Paging paging) throws Exception {
        LOG.info("param : selectList {}", paging.toString());
        return noticeRepository.selectList(paging);
    }

    @Override
    public void update(Notice notice) throws Exception {
        LOG.info("param : update {}", notice.toString());
        Notice dbNotice = noticeRepository.selectById(notice.getId());
        //읽을시 쿠키 읽기
        if (dbNotice != null) {
            dbNotice.setContent(notice.getContent());
            dbNotice.setCreatedBy(notice.getCreatedBy());
            dbNotice.setModifiedBy(notice.getModifiedBy());
        }

        if (notice.getDelFlag() != null && notice.getDelFlag().equals("Y")) {
            dbNotice.setDelFlag("Y");
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        LOG.info("param : deleteById {}", id);
        Notice notice = noticeRepository.selectById(id);
        noticeRepository.deleteById(id);
    }
}
