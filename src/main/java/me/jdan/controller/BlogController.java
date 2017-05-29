package me.jdan.controller;

import me.jdan.po.News;
import me.jdan.po.Newscategory;
import me.jdan.po.ShortNews;
import me.jdan.service.NewsCategoryService;
import me.jdan.service.NewsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jdan on 2016/11/2.
 */
@Controller
public class BlogController {
    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);
    @Resource
    NewsService newsService;
    @Resource
    NewsCategoryService newsCategoryService;

    @RequestMapping("/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        List<ShortNews> shortNewses;
        if (subject.isAuthenticated()) {
            username = (String) subject.getPrincipal();
            System.out.println("yes isAuthenticated");
            shortNewses = newsService.selectAllShortNews();
        }else {
            System.out.println("no isAuthenticated");
            shortNewses = newsService.selectAllPublicShortNews();
        }
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        model.addAttribute("newsList", shortNewses);
        model.addAttribute("recentNewsList", recentNews);
        model.addAttribute("username", username);
        return "homepage";
    }

    @RequestMapping("/study")
    public String study(Model model) {
        int id = 1;
        String name = "学无止境";
        String description = "good good study, day day up";
        List<Newscategory> categories = newsCategoryService.selectSubNewsCategoryBySuperId(id);
        List<ShortNews> shortNewses = new ArrayList<>();
        boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        for (Newscategory category : categories) {
            int sId = category.getTypeid();
            List<ShortNews> list;
            if (isLogin) {
                list = newsService.selectAllShortNewsByCategoryId(sId);
            }else {
                list = newsService.selectAllPublicShortNewsByCategoryId(sId);
            }
            if (list != null && !list.isEmpty()) {
                shortNewses.addAll(list);
            }
        }
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        model.addAttribute("newsList", shortNewses);
        model.addAttribute("recentNewsList", recentNews);
        model.addAttribute("categoryName", name);
        model.addAttribute("categoryDesc", description);
        return "study";
    }

    @RequestMapping("/detailStudy")
    public String detailStudy(Model model, @RequestParam(value = "type", required = true) String categoryName) {
        String name = categoryName;
        Newscategory newscategory  = newsCategoryService.selectCategoryByName(name).get(0);
        int id  = newscategory.getTypeid();
        String namealias = newscategory.getNamealias();
        String description = newscategory.getDescription();
        List<ShortNews> shortNewses;
        boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        if (isLogin) {
            shortNewses = newsService.selectAllShortNewsByCategoryId(id);
        }else {
            shortNewses = newsService.selectAllPublicShortNewsByCategoryId(id);
        }
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        model.addAttribute("newsList", shortNewses);
        model.addAttribute("recentNewsList", recentNews);
        model.addAttribute("categoryName", namealias);
        model.addAttribute("categoryDesc", description);
        return "study";
    }

    @RequestMapping("/life")
    public String life(Model model) {
        int id = 2;
        String name = "慢生活";
        String description = "follow your heart";
        List<Newscategory> categories = newsCategoryService.selectSubNewsCategoryBySuperId(id);
        List<ShortNews> shortNewses = new ArrayList<>();
        boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        for (Newscategory category : categories) {
            int sId = category.getTypeid();
            List<ShortNews> list;
            if (isLogin) {
                list = newsService.selectAllShortNewsByCategoryId(sId);
            }else {
                list = newsService.selectAllPublicShortNewsByCategoryId(sId);
            }
            if (list != null && !list.isEmpty()) {
                shortNewses.addAll(list);
            }
        }
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        model.addAttribute("newsList", shortNewses);
        model.addAttribute("recentNewsList", recentNews);
        model.addAttribute("categoryName", name);
        model.addAttribute("categoryDesc", description);
        return "life";
    }

    @RequestMapping("/detailLife")
    public String detailLife(Model model, @RequestParam(value = "type", required = true) String categoryName) {
        String name = categoryName;
        Newscategory newscategory  = newsCategoryService.selectCategoryByName(name).get(0);
        int id  = newscategory.getTypeid();
        String namealias = newscategory.getNamealias();
        String description = newscategory.getDescription();
        boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
        List<ShortNews> shortNewses;
        if (isLogin) {
            shortNewses = newsService.selectAllShortNewsByCategoryId(id);
        }else {
            shortNewses = newsService.selectAllPublicShortNewsByCategoryId(id);
        }
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        model.addAttribute("newsList", shortNewses);
        model.addAttribute("recentNewsList", recentNews);
        model.addAttribute("categoryName", namealias);
        model.addAttribute("categoryDesc", description);
        return "life";
    }

    @RequestMapping("detailPage")
    public String detailPage(Model model, @RequestParam(value = "id", required = true) String newsId) {
        News news = newsService.selectNewsByNewsId(newsId);
        Newscategory secondCategory = newsCategoryService.selectNewsCategoryById(news.getNewstype());
        Newscategory firstCategory = newsCategoryService.selectNewsCategoryById(secondCategory.getFtype());
        List<ShortNews> shortNewses = newsService.selectAllPublicShortNewsByCategoryId(secondCategory.getTypeid());
        List<Map<String, String>> recentNews = new ArrayList<>();
        for (int i = 0; i < shortNewses.size() && i < 5; i++) {
            Map<String, String> tMap = new HashMap<>();
            tMap.put("title", shortNewses.get(i).getTitle());
            tMap.put("url", shortNewses.get(i).getNewsid());
            recentNews.add(tMap);
        }
        Map<String, String> categoryMap = new HashMap<>();
        String firstCategoryName = firstCategory.getName();
        String secondCategoryName = secondCategory.getName();
        categoryMap.put("firstCategoryName", firstCategoryName);
        categoryMap.put("secondCategoryName", secondCategoryName);
        if (firstCategoryName.equals("学无止境")) {
            categoryMap.put("firstCategoryUrl", "/study");
            categoryMap.put("secondCategoryUrl", "/detailStudy?type=" + secondCategoryName);
        }else {
            categoryMap.put("firstCategoryUrl", "/life");
            categoryMap.put("secondCategoryUrl", "/detailLife?type=" + secondCategoryName);
        }
        String content = new String(news.getNewscontent());
        model.addAttribute("news", news);
        model.addAttribute("content", content);
        model.addAttribute("categoryMap", categoryMap);
        model.addAttribute("recentNewsList", recentNews);
        return "news";
    }
    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/comment")
    public String comment(Model model) {
        return "comment";
    }
}
