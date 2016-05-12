package com.example.zhaoting.myapplication.bean;

/**
 * Created by zhaoting on 16/5/12.
 */
public class TopStoriesBean {

    /**
     * image : http://pic3.zhimg.com/afcbb55298c579c79b8a40630071e7e2.jpg
     * type : 0
     * id : 8246804
     * ga_prefix : 050307
     * title : 读读日报 24 小时热门 TOP 5 · 有人自称是比特币创始人
     */


    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
