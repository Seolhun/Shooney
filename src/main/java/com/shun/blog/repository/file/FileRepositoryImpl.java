package com.shun.blog.repository.file;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.file.FileData;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class FileRepositoryImpl extends AbstractRepository<Long, FileData> implements FileRepository {
	static final Logger LOG = LoggerFactory.getLogger(FileRepositoryImpl.class);

	@Override
	public void insert(FileData fileData) {
		persist(fileData);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileData> selectListByBlog(Blog blog) {
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("fileDataId"))
				.add(Restrictions.eq("fileDataDelCheck", 0))
				.add(Restrictions.eq("blogInFile", blog))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FileData> fileDatas =criteria.list();
		return fileDatas;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileData> selectList(FileData fileData) {
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("fileDataId"))
				.add(Restrictions.eq("fileDataDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FileData> fileDatas =criteria.list();
		return fileDatas;
	}
	
	@Override
	public FileData selectById(Long id) {
		FileData fileData = getByKey(id);
		return fileData;
	}

	@Override
	public boolean delete(Long id) {
		return delete(id);
	}
}
