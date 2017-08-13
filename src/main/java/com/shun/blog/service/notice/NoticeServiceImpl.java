package com.shun.blog.service.notice;

import com.shun.blog.model.notice.Notice;
import com.shun.blog.repository.notice.NoticeRepository;
import com.shun.blog.service.common.CommonService;
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
    private CommonService commonService;

    @Autowired
    NoticeServiceImpl(NoticeRepository noticeRepository, CommonService commonService){
        this.noticeRepository=noticeRepository;
        this.commonService=commonService;
    }

    @Override
    public void insertNotice(Notice notice) throws Exception {
        LOG.info("param : insertNotice : {}", notice.toString());
        notice.setDelFlag("Y");
        noticeRepository.insertNotice(notice);
    }

    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeRepository.selectNoticeById(noticeId);
    }

    @Override
    public List<Notice> selectNoticeByURI(String uri) {
        LOG.info("param : current uri {}", uri);
        return noticeRepository.selectNoticeByURI(uri);
    }

    @Override
    public void updateNotice(Notice notice) throws Exception {
        Notice dbNotice = noticeRepository.selectNoticeById(notice.getId());
        try {
            dbNotice.setId(notice.getId());
            dbNotice.setUri(notice.getUri());
            dbNotice.setContent(notice.getContent());
            dbNotice.setDelFlag(notice.getDelFlag());
        } catch (NullPointerException e){
            LOG.info("ERROR : NullPointException");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNotice(Notice notice) throws Exception {
        Notice dbNotice = noticeRepository.selectNoticeById(notice.getId());
        if (dbNotice.getDelFlag().equals("Y")) {
            dbNotice.setDelFlag("N");
        } else {
            dbNotice.setDelFlag("Y");
        }
    }

    @Override
    public List<Notice> findAllByAdmin(Notice notice) {
        LOG.info("param : findAllByAdmin {}", notice.toString());
        return noticeRepository.findAllByAdmin(notice);
    }
}
