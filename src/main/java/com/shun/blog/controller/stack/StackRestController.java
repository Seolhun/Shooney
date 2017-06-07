package com.shun.blog.controller.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.stack.StackFileService;
import com.shun.blog.service.stack.StackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/stack")
public class StackRestController {
    private static final Logger LOG = LoggerFactory.getLogger(StackRestController.class);

    private StackService stackService;
    private StackFileService stackFileService;
    private CommonService commonService;

    @Autowired
    public StackRestController(StackService stackService, StackFileService stackFileService, CommonService commonService) {
        this.stackService = stackService;
        this.stackFileService = stackFileService;
        this.commonService = commonService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Stack> getStackList(Stack stack) throws Exception {
        //build commonService code check Validation about user REST Token
        List<Stack> stackList = stackService.selectList(stack);
        int count = 0;
        for (Stack dbStack : stackList) {
            LOG.info("param : {}", dbStack.getName());
            for (StackFile stackFile : dbStack.getStackImgFiles()) {
                LOG.info("test : {}", stackFile.getSavedName());
            }
            count++;
        }
        LOG.info("param : count {}", count);
        return stackList;
    }

    @RequestMapping(value = "/{stackName}", method = RequestMethod.GET)
    public Stack getStackList(Stack stack, @PathVariable String stackName) throws Exception {
        //build commonService code check Validation about user REST Token

        stack = stackService.selectByName(stackName);
        return stack;
    }
}
