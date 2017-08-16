package com.shun.blog.controller.stack;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.blog.service.stack.StackFileService;
import com.shun.blog.service.stack.StackService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        return "admin/stack/admin-stack-list";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult insertStack(@RequestBody Stack stack, Authentication auth, AjaxResult ajaxResult) throws Exception {
        LOG.info("return : getNewsThread : {}", stack.toString());
        stack.setName(validationStackName(stack.getName()));
        getStackUsingThread(stack.getName(), auth).start();

        ajaxResult.setResult("success");
        return ajaxResult;
    }

    @RequestMapping(value = "/insert/list", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult insertStackList(@RequestBody Stack stack, Authentication auth, AjaxResult ajaxResult) throws Exception {
        getStackListUsingThread(stack, auth).start();
        ajaxResult.setResult("success");
        return ajaxResult;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public AjaxResult stopThreadNews(AjaxResult ajaxResult) {
        stopStackUsingThread();
        ajaxResult.setResult("success");
        return ajaxResult;
    }

    private Thread getStackUsingThread(String stackName, Authentication auth) {
        Thread thread = new Thread(() -> {
            try {
                Stack tempStack = new Stack();
                tempStack.setName(stackName);
                LOG.info("test similarStackName {}", stackName);
                getStackAndSaveStack(commonService, stackService, stackFileService, auth, stackName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return thread;
    }

    private Thread getStackListUsingThread(Stack stack, Authentication auth) {
        Thread thread = new Thread(() -> {
            List<Stack> stackList = null;
            try {
                stackList = stackService.selectListForAdmin(stack);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (stackList.size() > 0) {
                for (Stack tempStacks : stackList) {
                    try {
                        LOG.info("return : getNewsThread : {}", tempStacks.getName());
                        getStackAndSaveStack(commonService, stackService, stackFileService, auth, tempStacks.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            tempStacks.setErrorFlag("Y");
                            stackService.update(tempStacks);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        continue;
                    }
                }
            }
        });
        return thread;
    }

    private void stopStackUsingThread() {
        // 현재 돌고있는 쓰레드를 객체로 반환.
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("현재 쓰레드 이름 : " + name);
        thread.run();
    }

    private void getStackAndSaveStack(CommonService commonService, StackService stackService, StackFileService stackFileService, Authentication auth, String validStackName) throws Exception {
        String address = "https://stackshare.io/" + validStackName;
        //Jsoup Crawling connect
        Document doc = null;
        doc = Jsoup.connect(address).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
        if (doc == null) {
            return;
        }

        //Crawl Root Stack
//                    String rootStackName = doc.select("meta[name=keywords]").attr("content");
//                    String rootImgSrc = doc.getElementsByClass("sp-service-logo").select("div > a > img").attr("src");
        String rootUrl = doc.getElementById("service-name").select("div > a").select("a[itemprop=name]").attr("href");
        String rootStackName = doc.getElementById("service-name").select("div > a").select("a[itemprop=name]").html();
        rootStackName = validationStackName(rootStackName);
        String rootImgSrc = doc.getElementsByClass("sp-service-logo").select("div > a > img").attr("src");
        String rootFilePath = commonService.getImgUsingJsoup(rootImgSrc, rootStackName);

        //Crawl Similar Stack
        List<Stack> similarStacks = new ArrayList<>();
        Elements similars = doc.getElementsByClass("similar-services-items").select("div > div > a > img");
        int counts = 0;
        for (Element el : similars) {
            String similarStackName = el.attr("alt");
            similarStackName = validationStackName(similarStackName);
            LOG.info("test similarStackName {}", similarStackName);
            String similarImgSrc = el.attr("src");
            String similarFilePath = commonService.getImgUsingJsoup(similarImgSrc, similarStackName);

            //insert Similar Stack  to DB
            Stack similarStack = stackService.selectByName(similarStackName);
            if (similarStack == null) {
                similarStack = new Stack();
                similarStack.setName(similarStackName);
                similarStack.setCreatedBy(auth.getName());
                stackService.insert(similarStack);
            }

            similarStacks.add(similarStack);
            counts += 1;

            //insert Similar Stack File to DB
            StackFile similarStackFile = stackFileService.selectByName(similarStackName);
            insertStackFile(similarStackFile, similarStack, similarFilePath, auth);
        }

        //Insert root Stack img to DB
        Stack rootStack = stackService.selectByName(rootStackName);
        if (rootStack == null) {
            LOG.info("test insert(rootStack)1");
            rootStack = new Stack();
            rootStack.setName(rootStackName);
            rootStack.setCreatedBy(auth.getName());
            rootStack.setLangDepth(counts);
            rootStack.setUrl(rootUrl);

            rootStack.setSimilarStacks(similarStacks);
            stackService.insert(rootStack);
        } else if (rootStack != null && rootStack.getSimilarStacks() == null) {
            LOG.info("test insert(rootStack)2");
            rootStack.setName(rootStackName);
            rootStack.setCreatedBy(auth.getName());
            rootStack.setLangDepth(counts);
            rootStack.setUrl(rootUrl);

            rootStack.setSimilarStacks(similarStacks);
            stackService.update(rootStack);
        } else if (rootStack != null && rootStack.getSimilarStacks() != null && (rootStack.getUrl() == null || rootStack.getLangDepth() == 0)){
            LOG.info("test insert(rootStack)3");
            rootStack.setLangDepth(counts);
            rootStack.setUrl(rootUrl);

            rootStack.setSimilarStacks(similarStacks);
            stackService.update(rootStack);
        }

        StackFile rootStackFile = stackFileService.selectByName(rootStackName);
        insertStackFile(rootStackFile, rootStack, rootFilePath, auth);
    }

    private String validationStackName(String stackName) {
        LOG.info("param : stackName1 {}", stackName);
        if (stackName.contains("-")) {
            stackName = stackName.replace("-", " ");
            LOG.info("param : contains(\"-\") {}", stackName);
        }

        if (stackName.contains(" ")) {
            stackName = stackName.replaceAll(" ", "-");
            LOG.info("param : contains(\" \") {}", stackName);
        }

        if (stackName.contains(".")) {
            stackName = stackName.replace(".", "-");
            LOG.info("param : contains(\".\") {}", stackName);
        }

        if (stackName.contains("+")) {
            stackName = stackName.replaceAll("\\+", "plus");
            LOG.info("param : contains(\"+\") {}", stackName);
        }

        if (stackName.contains("#")) {
            stackName = stackName.replaceAll("#", "-sharp");
            LOG.info("param : contains(\"#\") {}", stackName);
        }

        if (stackName.contains(",")) {
            stackName = stackName.split(",")[0];
            LOG.info("param : contains(\",\") {}", stackName);
        }

        LOG.info("return : stackName {}", stackName);
        return stackName;
    }

    private void insertStackFile(StackFile stackFile, Stack stack, String filePath, Authentication auth) {
        if (stackFile == null) {
            stackFile = new StackFile();
            stackFile.setOriginName(stack.getName());
            stackFile.setSavedName(stack.getName());
            stackFile.setCreatedBy(auth.getName());
            stackFile.setSavedPath(filePath);

            stackFile.setStackInFile(stack);
            stackFileService.insert(stackFile);
        }
    }
}