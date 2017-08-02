package com.shun.blog.repository.notice;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.notice.Notice;

import java.util.List;

public interface NoticeRepository {
	void insertNotice(Notice notice) throws Exception;

	Notice selectNoticeById(Long noticeId);

	List<Notice> selectNoticeByURI(String uri);

	List<Notice> findAllByAdmin(Notice notice);
}
