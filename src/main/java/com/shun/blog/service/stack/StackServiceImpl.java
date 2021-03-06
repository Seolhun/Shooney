package com.shun.blog.service.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.repository.stack.StackFileRepository;
import com.shun.blog.repository.stack.StackRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(transactionManager = "txManager")
public class StackServiceImpl implements StackService {
    private static final Logger LOG = LoggerFactory.getLogger(StackServiceImpl.class);

    private StackRepository stackRepository;
    private StackFileRepository stackFileRepository;

    @Autowired
    public StackServiceImpl(StackRepository stackRepository, StackFileRepository stackFileRepository) {
        this.stackRepository = stackRepository;
        this.stackFileRepository = stackFileRepository;
    }

    @Override
    public Stack selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        Stack stack = stackRepository.selectById(id);
        List<StackFile> stackFileList = stackFileRepository.selectList(stack);
        if(stackFileList != null){
            stack.setStackImgFiles(stackFileList);
        }
        return stack;
    }

    @Override
    public Stack selectByName(String name) throws Exception {
        LOG.info("param : selectByName {}", name);
        Stack stack = stackRepository.selectByName(name);
        List<StackFile> stackFileList = stackFileRepository.selectList(stack);
        if(stackFileList != null){
            stack.setStackImgFiles(stackFileList);
        }
        return stack;
    }

    @Override
    public void insert(Stack stack) {
        stackRepository.insert(stack);
    }

    @Override
    public List<Stack> selectListForAdmin(Stack stack) throws Exception {
        return stackRepository.selectListForAdmin(stack);
    }

    @Override
    public List<Stack> selectListForAdminWithError(Stack stack) throws Exception {
        return stackRepository.selectListForAdminWithError(stack);
    }

    @Override
    public List<Stack> selectList(Stack stack) throws Exception {
        List<Stack> stackList = stackRepository.selectList(stack);
        for (Stack dbStack : stackList) {
            List<StackFile> stackFileList = stackFileRepository.selectList(dbStack);
            if(stackFileList != null){
                dbStack.setStackImgFiles(stackFileList);
            }
        }
        return stackList;
    }

    @Override
    public int getCount() throws Exception {
        return stackRepository.getCount();
    }

    @Override
    public void update(Stack stack) throws Exception {
        Stack dbStack = stackRepository.selectByName(stack.getName());
        if (dbStack != null) {
            dbStack.setName(stack.getName());
            dbStack.setModifiedBy(stack.getModifiedBy());
            dbStack.setUrl(stack.getUrl());
            dbStack.setLangDepth(stack.getLangDepth());
            dbStack.setToolDepth(stack.getToolDepth());
            dbStack.setCompanyDepth(stack.getCompanyDepth());
        }

        if(stack.getErrorFlag().equals("Y")){
            dbStack.setErrorFlag(stack.getErrorFlag());
        }
//        else if(stack.getErrorFlag().equals("N")){
//            dbStack.setErrorFlag(stack.getErrorFlag());
//        }

        if(stack.getSimilarStacks() != null){
            dbStack.setSimilarStacks(stack.getSimilarStacks());
        }
    }

    @Override
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        stackRepository.deleteById(id);
    }
}
