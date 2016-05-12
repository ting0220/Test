package com.example.zhaoting.myapplication.bean;

import java.util.List;

/**
 * Created by zhaoting on 16/5/12.
 */
public class StoriesBean {
    /**
     * images : ["http://pic4.zhimg.com/ad14be82ea7946bb4902bab08a6f201f.jpg"]
     * type : 0
     * id : 8247920
     * ga_prefix : 050310
     * title : 问自己这 5 个问题，看看你是不是在歧视不同的群体
     * multipic:true
     * date: 20160512（是把整个的时间设置到某个item上，是自己设置的）
     */
    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;
    private boolean multipic;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
