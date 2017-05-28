package com.shun.blog.service.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.repository.stack.StackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Autowired
    public StackServiceImpl(StackRepository stackRepository) {
        this.stackRepository = stackRepository;
    }

    @Override
    public Stack selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        return stackRepository.selectById(id);
    }

    @Override
    public Stack selectByName(String name) throws Exception {
        LOG.info("param : selectByName {}", name);
        return stackRepository.selectByName(name);
    }

    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
    public void insert(Stack stack) {
        LOG.info("param : insert {}", stack.toString());
        stackRepository.insert(stack);
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "'selectListBoardType'", value = "selectListBoardType")})
    public List<Stack> selectList() throws Exception {
        return stackRepository.selectList();
    }

    @Override
    public int getCount() throws Exception {
        return stackRepository.getCount();
    }


    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
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
            dbStack.setDepth(stack.getDepth());
        }

    }

    @Override
    @Caching(put = {@CachePut(key = "'selectListBoardType'", value = "selectListBoardType")})
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        stackRepository.deleteById(id);
    }
}
