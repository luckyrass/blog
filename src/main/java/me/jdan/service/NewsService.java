package me.jdan.service;

import me.jdan.po.MicroNews;
import me.jdan.po.News;
import me.jdan.po.ShortNews;

import java.util.List;
import java.util.Map;

/**
 * Created by jdan on 2016/10/31.
 */
public interface NewsService {
    public void insertNews(News news);
    public void deleteNewsByNewsId(String newsId);
    public void updateNews(News news);
    public News selectNewsByNewsId(String newsId);
    public List<ShortNews> selectAllShortNews();
    public List<ShortNews> selectAllPublicShortNews();
    public List<ShortNews> selectAllShortNewsByCategoryId(int id);
    public List<ShortNews> selectAllPublicShortNewsByCategoryId(int id);
    public int selectNewsCount();

    public List<MicroNews> selectAllMicroNews();

    public List<MicroNews> selectAllMicroNewsByPrivilege(int privilege);

//    public List<MicroNews> selectAllMicroNewsByCategoryId(int id);
    public List<MicroNews> selectAllMicroNewsByCategoryId(List<Integer> list);

    public List<MicroNews> selectAllMicroNewsByCategoryIdAndPrivilege(Map<String, Object> map);
}
