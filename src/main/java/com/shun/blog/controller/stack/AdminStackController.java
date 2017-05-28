package com.shun.blog.controller.stack;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
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
    public AjaxResult saveNews(@RequestBody Stack stack, AjaxResult ajaxResult) {
        LOG.info("return : getNewsThread : {}", stack.toString());

        getStackUsingThread(stack.getName().toLowerCase()).start();

        ajaxResult.setResult("success");
        return ajaxResult;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public AjaxResult stopThreadNews(AjaxResult ajaxResult) {
        stopStackUsingThread();
        ajaxResult.setResult("success");
        return ajaxResult;
    }

    private Thread getStackUsingThread(String stackName) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    String address = "https://stackshare.io/" + stackName;
                    LOG.info("return : getNewsThread : address {}", address);

                    //Jsoup Crawling connect
                    Document doc = null;
                    doc = Jsoup.connect(address).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
                    if (doc == null) {
                        return;
                    }

                    Stack rootStack = new Stack();
                    StackFile rootStackFile = new StackFile();

                    //Crawl root Stack
                    String rootStackName = doc.select("meta[name=keywords]").attr("content");
                    String rootImgSrc = doc.getElementsByClass("sp-service-logo").select("div > a > img").attr("src");

                    //insert root Stack  to DB
                    rootStack.setName(rootStackName);

                    //insert root Stack img Info to DB
                    String rootFilePath = commonService.getImgUsingJsoup(rootImgSrc, stackName);
                    rootStackFile.setSavedPath(rootFilePath);
                    rootStackFile.setSavedName(stackName);
                    rootStackFile.setStackInFile(rootStack);

                    Set<Stack> similarStacks = new HashSet<>();
                    //Crawl similar Stack
                    Elements similars = doc.getElementsByClass("similar-services-items").select("div > div > a > img");
                    for (Element el : similars) {
                        Stack similarStack = new Stack();
                        StackFile similarStackFile = new StackFile();

                        String itemName = el.attr("alt");
                        String imgSrc = el.attr("src");

                        //insert root similarStack  to DB
                        similarStack.setName(itemName);
                        stackService.insert(similarStack);

                        //insert root similarStack img Info to DB
                        String path = commonService.getImgUsingJsoup(imgSrc, itemName);
                        similarStackFile.setSavedPath(path);
                        similarStackFile.setSavedName(itemName);
                        similarStackFile.setStackInFile(similarStack);
                        stackFileService.insert(similarStackFile);

                        similarStacks.add(similarStack);
                    }

                    rootStack.setSimilarStacks(similarStacks);
                    stackService.insert(rootStack);
                    stackFileService.insert(rootStackFile);

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