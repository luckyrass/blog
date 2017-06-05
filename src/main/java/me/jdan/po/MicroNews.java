package me.jdan.po;

import java.util.Date;

/**
 * Created by jdan on 2017/5/30.
 */
public class MicroNews {
    private String newsid;
    private String title;
    private Date releasedate;
    private Integer privilege;

    public MicroNews() {
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }
}
