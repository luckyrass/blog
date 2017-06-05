package me.jdan.controller;

import me.jdan.po.MicroNews;
import me.jdan.po.News;
import me.jdan.po.Newscategory;
import me.jdan.service.NewsCategoryService;
import me.jdan.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by jdan on 2017/5/30.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);
    @Resource
    NewsService newsService;

    @Resource
    NewsCategoryService categoryService;


/*    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        List<Newscategory> categories = categoryService.selectAllSubNewsCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("news", new News());
        return "news_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute("newsAddForm") News news) {
        news.setNewsid(UUID.randomUUID().toString());
        news.setReleasedate(new Date());
        newsService.insertNews(news);
        return "redirect:list";
    }


    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("newsid") String newsId) {
        newsService.deleteNewsByNewsId(newsId);
        return "redirect:list";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Model model, News news) {
        newsService.updateNews(news);
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "category", required = false) String supCategory,
                       @RequestParam(value = "studyCategory", required = false) String studyCategory,
                       @RequestParam(value = "lifeCategory", required = false) String lifeCategory,
                       @RequestParam(value = "privilege", required = false) Integer privilege) {
        String category = supCategory;
        if (category != null) {
            if (category.equals("study") && studyCategory != null && !studyCategory.equals("all")) {
                category = studyCategory;
            }
            if (category.equals("life") && lifeCategory != null && !lifeCategory.equals("all")) {
                category = lifeCategory;
            }
        }
        List<MicroNews> microNewsList = null;
        if (category == null || category.equals("all")) {
            if (privilege == null || privilege == -1) {
                microNewsList = newsService.selectAllMicroNews();
            }else {
                microNewsList = newsService.selectAllMicroNewsByPrivilege(privilege);
            }
        }else {
            Integer id = categoryService.selectCategoryByName(category).get(0).getTypeid();
            List<Integer> list = new ArrayList<>();
            if (id == 1 || id == 2) {
                List<Newscategory> newscategories = categoryService.selectSubNewsCategoryBySuperId(id);
                for (Newscategory newscategory : newscategories) {
                    list.add(newscategory.getTypeid());
                }
            }else {
                list.add(id);
            }
            if (privilege == null || privilege == -1) {
                microNewsList = newsService.selectAllMicroNewsByCategoryId(list);
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("newsType", list);
                map.put("privilege", privilege);
                microNewsList = newsService.selectAllMicroNewsByCategoryIdAndPrivilege(map);
            }
        }
        model.addAttribute("newsList", microNewsList);
        model.addAttribute("JsupCategory", supCategory);
        model.addAttribute("JstudyCategory", studyCategory);
        model.addAttribute("JlifeCategory", lifeCategory);
        model.addAttribute("Jprivilege", privilege);
        return "manage";
    }
}
