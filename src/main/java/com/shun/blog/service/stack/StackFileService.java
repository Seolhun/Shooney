package com.shun.blog.service.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;

import java.util.List;

public interface StackFileService {
    StackFile selectById(Long id) throws Exception;

    StackFile selectByName(String name) throws Exception;

    void insert(StackFile stackFile);

    void update(StackFile stackFile, int variableCount) throws Exception;

    void deleteById(Long id);

    List<StackFile> selectList(Stack stack) throws Exception;

    int getCount() throws Exception;
}
