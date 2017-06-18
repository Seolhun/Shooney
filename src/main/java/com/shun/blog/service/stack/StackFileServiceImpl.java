package com.shun.blog.service.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.repository.stack.StackFileRepository;
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
public class StackFileServiceImpl implements StackFileService {
    private static final Logger LOG = LoggerFactory.getLogger(StackFileService.class);

    private StackFileRepository stackFileReoisitory;

    @Autowired
    public StackFileServiceImpl(StackFileRepository stackFileReoisitory) {
        this.stackFileReoisitory = stackFileReoisitory;
    }

    @Override
    public StackFile selectById(Long id) throws Exception {
        LOG.info("param : selectById {}", id);
        return stackFileReoisitory.selectById(id);
    }

    @Override
    public StackFile selectByName(String name) throws Exception {
        LOG.info("param : selectByName {}", name);
        return stackFileReoisitory.selectByName(name);
    }

    @Override
    public void insert(StackFile stackFile) {
        LOG.info("param : insert {}", stackFile.toString());
        stackFileReoisitory.insert(stackFile);
    }

    @Override
    public List<StackFile> selectList(Stack stack) throws Exception {
        return stackFileReoisitory.selectList(stack);
    }

    @Override
    public int getCount() throws Exception {
        return stackFileReoisitory.getCount();
    }


    @Override
    public void update(StackFile stackFile) throws Exception {
        LOG.info("param : update {}", stackFile.toString());
        StackFile dbStackFile = stackFileReoisitory.selectById(stackFile.getId());
        if (dbStackFile != null) {
            dbStackFile.setCreatedBy(stackFile.getCreatedBy());
            dbStackFile.setModifiedBy(stackFile.getModifiedBy());
        }

    }

    @Override
    public void deleteById(Long id) {
        LOG.info("param : deleteById {}", id);
        stackFileReoisitory.deleteById(id);
    }
}
