package com.shun.blog.service.notice;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.notice.Notice;

import java.util.List;

public interface NoticeService {

	Notice selectById(Long id) throws Exception;

	void insert(Notice notice) throws Exception;

	void update(Notice notice) throws Exception;

	void deleteById(Long id) throws Exception;

	List<Notice> selectList(Paging paging) throws Exception;
	
	int getCount(Paging paging) throws Exception;
}