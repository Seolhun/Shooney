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
import java.util.Set;

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
        List<StackFile> stackFiles = stackFileRepository.selectList(stack);
        if (stackFiles != null) {
            stack.setStackFiles(stackFiles);
        }

        if (stack != null) {
            Hibernate.initialize(stack.getSimilarStacks());
            Hibernate.initialize(stack.getCompanies());
            Hibernate.initialize(stack.getItems());
        }
        return stack;
    }

    @Override
    public Stack selectByName(String name) throws Exception {
        LOG.info("param : selectByName {}", name);
        Stack stack = stackRepository.selectByName(name);
        List<StackFile> stackFiles = stackFileRepository.selectList(stack);
        if (stackFiles != null) {
            stack.setStackFiles(stackFiles);
        }

        if (stack != null) {
            Hibernate.initialize(stack.getSimilarStacks());
            Hibernate.initialize(stack.getCompanies());
            Hibernate.initialize(stack.getItems());
        }
        return stack;
    }

    @Override
    public void insert(Stack stack) {
        LOG.info("param : insert {}", stack.toString());
        stackRepository.insert(stack);
    }

    @Override
    public Set<Stack> selectList(Stack stack) throws Exception {
        return stackRepository.selectList(stack);
    }

    @Override
    public int getCount() throws Exception {
        return stackRepository.getCount();
    }


    @Override
    public void update(Stack stack, int variableCount) throws Exception {
        LOG.info("param : update {}", stack.toString());
        Stack dbStack = null;
        try {
            if (stack.getId() != null) {
                dbStack = stackRepository.selectById(stack.getId());
            } else if (stack.getName() != null) {
                dbStack = stackRepository.selectByName(stack.getName());
            }
        } catch (NullPointerException e) {
            LOG.info("error : update Stack NullPointerException");
        }
        if (dbStack != null) {
            dbStack.setName(stack.getName());
            dbStack.setCreatedBy(stack.getCreatedBy());
            dbStack.setModifiedBy(stack.getModifiedBy());
            dbStack.setLangDepth(stack.getLangDepth());
        }

    }

    @Override
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        stackRepository.deleteById(id);
    }
}
