package com.shun.blog.controller.stack;

import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.stack.StackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/stack")
public class StackRestController {
    private static final Logger LOG = LoggerFactory.getLogger(StackRestController.class);

    private StackService stackService;
    private CommonService commonService;

    @Autowired
    public StackRestController(StackService stackService, CommonService commonService) {
        this.stackService = stackService;
        this.commonService = commonService;
    }
}
