package me.jdan.service.impl;

import me.jdan.dao.NewsMapper;
import me.jdan.po.MicroNews;
import me.jdan.po.News;
import me.jdan.po.ShortNews;
import me.jdan.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jdan on 2016/10/31.
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsDao;

    public void insertNews(News news) {
        newsDao.insert(news);
    }

    public void deleteNewsByNewsId(String newsId) {
        newsDao.deleteByPrimaryKey(newsId);
    }

    public void updateNews(News news) {
        newsDao.updateByPrimaryKey(news);
    }

    public News selectNewsByNewsId(String newsId) {
        return newsDao.selectByPrimaryKey(newsId);
    }

    public List<ShortNews> selectAllShortNews() {
        return newsDao.selectAllShortNews();
    }

    public List<ShortNews> selectAllPublicShortNews() {
        return newsDao.selectAllPublicShortNews();
    }

    @Override
    public List<ShortNews> selectAllShortNewsByCategoryId(int id) {
        return newsDao.selectAllShortNewsByCategoryId(id);
    }

    @Override
    public List<ShortNews> selectAllPublicShortNewsByCategoryId(int id) {
        return newsDao.selectAllPublicShortNewsByCategoryId(id);
    }

    public int selectNewsCount() {
        return 0;
    }


    @Override
    public List<MicroNews> selectAllMicroNews() {
        return newsDao.selectAllMicroNews();
    }

    @Override
    public List<MicroNews> selectAllMicroNewsByPrivilege(int privilege) {
        return newsDao.selectAllMicroNewsByPrivilege(privilege);
    }

/*    @Override
    public List<MicroNews> selectAllMicroNewsByCategoryId(int id) {
        return newsDao.selectAllMicroNewsByCategoryId(id);
    }*/

    @Override
    public List<MicroNews> selectAllMicroNewsByCategoryId(List<Integer> list) {
        return newsDao.selectAllMicroNewsByCategoryId(list);
    }

    @Override
    public List<MicroNews> selectAllMicroNewsByCategoryIdAndPrivilege(Map<String, Object> map) {
        return newsDao.selectAllMicroNewsByCategoryIdAndPrivilege(map);
    }
}
