package com.shun.blog.repository.company;


import com.shun.blog.model.stack.Company;

import java.util.List;

public interface CompanyRepository {
	void insert(Company company);
	
	Company selectById(Long id) throws Exception;

	Company selectByName(String name) throws Exception;
	
	List<Company> selectList() throws Exception;

	int getCount() throws Exception;

	void deleteById(Long id);

}
