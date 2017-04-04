package com.shun.blog.repository.file;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.file.FileData;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class FileRepositoryImpl extends AbstractRepository<Long, FileData> implements FileRepository {
	static final Logger log = LoggerFactory.getLogger(FileRepositoryImpl.class);

	@Override
	public void insert(FileData fileData) {
		log.info("param : insert : {}", fileData.toString());
		persist(fileData);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileData> selectList(FileData fileData) {
		log.info("param : selectList : {}", fileData.toString());
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("fileDataId"))
				.add(Restrictions.eq("fileDataDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FileData> fileDatas =(ArrayList<FileData>)criteria.list();
		log.info("return : {}",fileDatas.toString());
		return fileDatas;
	}
	
	@Override
	public FileData selectById(Long id) {
		log.info("param : selectById : {}", id);
		FileData fileData = getByKey(id);
		return fileData;
	}

	@Override
	public boolean delete(Long id) {
		log.info("param : delete : {}", id);
		return delete(id);
	}
}
