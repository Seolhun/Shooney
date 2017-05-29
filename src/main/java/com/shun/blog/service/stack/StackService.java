package com.shun.blog.service.stack;


import com.shun.blog.model.stack.Stack;

import java.util.Set;

/**
 * Created by HunSeol on 2017. 5. 28..
 */
public interface StackService {
    Stack selectById(Long id) throws Exception;

    Stack selectByName(String name) throws Exception;

    void insert(Stack stack);

    void update(Stack stack) throws Exception;

    void deleteById(Long id);

    Set<Stack> selectList(Stack stack) throws Exception;

    int getCount() throws Exception;
}
