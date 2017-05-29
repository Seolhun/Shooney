package com.shun.blog.controller.stack;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.model.user.User;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.blog.service.stack.StackFileService;
import com.shun.blog.service.stack.StackService;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin/stack")
public class AdminStackController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminStackController.class);

    private MenuService menuService;
    private CommonService commonService;
    private StackService stackService;
    private StackFileService stackFileService;

    @Autowired
    public AdminStackController(MenuService menuService, CommonService commonService, StackService stackService, StackFileService stackFileService) {
        this.menuService = menuService;
        this.commonService = commonService;
        this.stackService = stackService;
        this.stackFileService = stackFileService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String moveNewsList(ModelMap model, HttpServletRequest request) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        return "admin/stack/stack-list";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult saveNews(@RequestBody Stack stack, AjaxResult ajaxResult) throws Exception {
        LOG.info("return : getNewsThread : {}", stack.toString());

        getStackUsingThread(stack.getName().toLowerCase(), commonService.getAccessUserToModel()).start();

        ajaxResult.setResult("success");
        return ajaxResult;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public AjaxResult stopThreadNews(AjaxResult ajaxResult) {
        stopStackUsingThread();
        ajaxResult.setResult("success");
        return ajaxResult;
    }

    private Thread getStackUsingThread(String stackName, User user) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    String address = "https://stackshare.io/" + stackName;
                    //Jsoup Crawling connect
                    Document doc = null;
                    doc = Jsoup.connect(address).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
                    if (doc == null) {
                        return;
                    }

                    //Crawl root Stack img to DB
                    String rootImgSrc = doc.getElementsByClass("sp-service-logo").select("div > a > img").attr("src");
                    String rootFilePath = commonService.getImgUsingJsoup(rootImgSrc, stackName);
                    String rootStackName = doc.select("meta[name=keywords]").attr("content");
                    rootStackName = rootStackName.substring(0,1).toUpperCase()+rootStackName.substring(1,rootStackName.length());

                    List<Stack> similarStacks = new ArrayList<>();
                    //Crawl similar Stack
                    Elements similars = doc.getElementsByClass("similar-services-items").select("div > div > a > img");
                    int counts = 0;
                    for (Element el : similars) {
                        String similarStackName = el.attr("alt");
                        String similarImgSrc = el.attr("src");

                        //insert root similarStack  to DB
                        Stack similarStack = stackService.selectByName(similarStackName);
                        if(similarStack == null){
                            similarStack = new Stack();
                            similarStack.setName(similarStackName);
                            similarStack.setCreatedBy(user.getNickname());
                            stackService.insert(similarStack);
                        }

                        StackFile similarStackFile = stackFileService.selectByName(similarStackName);
                        if(similarStackFile == null) {
                            similarStackFile = new StackFile();
                            //insert root similarStack img Info to DB
                            similarStackFile.setOriginName(similarStackName);
                            similarStackFile.setSavedName(similarStackName);
                            similarStackFile.setCreatedBy(user.getNickname());
                            similarStackFile.setStackInFile(similarStack);
                            String path = commonService.getImgUsingJsoup(similarImgSrc, similarStackName);
                            similarStackFile.setSavedPath(path);
                            stackFileService.insert(similarStackFile);
                        }

                        similarStacks.add(similarStack);
                        counts += 1;
                    }

                    //Insert root Stack img to DB
                    Stack rootStack = stackService.selectByName(rootStackName);
                    if(rootStack == null) {
                        rootStack = new Stack();
                        rootStack.setName(rootStackName);
                        rootStack.setCreatedBy(user.getNickname());
                        rootStack.setLangDepth(counts);
                        rootStack.setSimilarStacks(similarStacks);
                        stackService.insert(rootStack);
                    } else {
                        rootStack.setSimilarStacks(similarStacks);
                        stackService.update(rootStack);
                    }



                    StackFile rootStackFile = stackFileService.selectByName(rootStackName);
                    if(rootStackFile == null) {
                        rootStackFile = new StackFile();
                        rootStackFile.setSavedPath(rootFilePath);
                        rootStackFile.setOriginName(rootStackName);
                        rootStackFile.setSavedName(rootStackName);
                        rootStackFile.setCreatedBy(user.getNickname());
                        stackFileService.insert(rootStackFile);
                    }
                } catch (HttpStatusException e) {
                    LOG.error("ERROR : HttpStatusException");
                    e.printStackTrace();
                } catch (IOException e) {
                    LOG.error("ERROR : IOException");
                    e.printStackTrace();
                } catch (Exception e) {
                    LOG.error("ERROR : Exception");
                    e.printStackTrace();
                }
            }
        };
        return thread;
    }

    private void stopStackUsingThread() {
        // 현재 돌고있는 쓰레드를 객체로 반환.
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("현재 쓰레드 이름 : " + name);
        thread.run();
    }
}