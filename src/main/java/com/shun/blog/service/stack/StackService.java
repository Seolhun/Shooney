package com.shun.blog.service.stack;


import com.shun.blog.model.stack.Stack;

import java.util.List;

/**
 * Created by HunSeol on 2017. 5. 28..
 */
public interface StackService {
    Stack selectById(Long id) throws Exception;

    Stack selectByName(String name) throws Exception;

    void insert(Stack stack);

    void update(Stack stack, int variableCount) throws Exception;

    void deleteById(Long id);

    List<Stack> selectList() throws Exception;

    int getCount() throws Exception;
}
