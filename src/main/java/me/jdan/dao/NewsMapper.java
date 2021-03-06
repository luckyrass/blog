package me.jdan.dao;

import java.util.List;
import java.util.Map;

import me.jdan.po.MicroNews;
import me.jdan.po.News;
import me.jdan.po.NewsExample;
import me.jdan.po.ShortNews;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int countByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int deleteByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int deleteByPrimaryKey(String newsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int insert(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int insertSelective(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    List<News> selectByExampleWithBLOBs(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    List<News> selectByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    News selectByPrimaryKey(String newsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByPrimaryKeySelective(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbggenerated Sun May 28 11:20:05 CST 2017
     */
    int updateByPrimaryKey(News record);

    /*
    * this is created by author
    * */
    List<ShortNews> selectAllShortNews();

    List<ShortNews> selectAllPublicShortNews();

    List<ShortNews> selectAllShortNewsByCategoryId(int id);

    List<ShortNews> selectAllPublicShortNewsByCategoryId(int id);

    List<MicroNews> selectAllMicroNews();

    List<MicroNews> selectAllMicroNewsByPrivilege(int privilege);

//    List<MicroNews> selectAllMicroNewsByCategoryId(int id);
    List<MicroNews> selectAllMicroNewsByCategoryId(List<Integer> list);

    List<MicroNews> selectAllMicroNewsByCategoryIdAndPrivilege(Map<String, Object> map);
}