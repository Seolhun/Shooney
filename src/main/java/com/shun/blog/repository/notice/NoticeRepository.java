package com.shun.blog.repository.notice;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.notice.Notice;

import java.util.List;

public interface NoticeRepository {
	void insert(Notice notice);

	Notice selectById(Long id) throws Exception;
	
	List<Notice> selectList(Paging paging) throws Exception;

	int getCount(Paging paging) throws Exception;

	void deleteById(Long id);

}
