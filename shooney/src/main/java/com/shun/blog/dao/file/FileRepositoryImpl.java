package com.shun.blog.dao.file;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.file.FileData;

@Repository("sampleRepository")
public class FileRepositoryImpl extends AbstractDao<Long, FileData> implements FileRepository {
	static final Logger log = LoggerFactory.getLogger(FileRepositoryImpl.class);

	@Override
	public void insert(FileData fileData) {
		persist(fileData);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileData> selectList(FileData fileData) {
		log.info("TEST : selectList : {}", fileData.toString());
		Criteria criteria = createEntityCriteria().
				addOrder(Order.desc("fileDataId"))
				.add(Restrictions.eq("fileDataDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FileData> fileDatas =criteria.list();
		log.info("return : {}",fileDatas.toString());
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
