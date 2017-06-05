package me.jdan.service;

import me.jdan.po.Newscategory;

import java.util.List;

/**
 * Created by jdan on 2016/11/1.
 */
public interface NewsCategoryService {
    public void insertNewsCategory(Newscategory newscategory);
    public void deleteNewsCategory(int id);
    public void updateNewsCategory(Newscategory newscategory);
    public Newscategory selectNewsCategoryById(int id);
    public List<Newscategory> selectAllNewsCategory();
    public List<Newscategory> selectAllSubNewsCategory();
    public List<Newscategory> selectAllFirstLevelNewsCategory();
    public List<Newscategory> selectSubNewsCategoryBySuperId(int id);
    public List<Newscategory> selectCategoryByName(String name);
}
