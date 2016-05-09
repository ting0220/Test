package com.example.zhaoting.myapplication.bean;

import java.util.List;

/**
 * Created by zhaoting on 16/4/29.
 */
public class HomeBean {


    /**
     * date : 20160503
     * stories : [{"images":["http://pic4.zhimg.com/ad14be82ea7946bb4902bab08a6f201f.jpg"],"type":0,"id":8247920,"ga_prefix":"050310","title":"问自己这 5 个问题，看看你是不是在歧视不同的群体"},{"images":["http://pic2.zhimg.com/7d377201020f8087c2ae97d39f1769c1.jpg"],"type":0,"id":8240604,"ga_prefix":"050309","title":"别让对精子和卵子的描述，都充满了「厌女症」的味道"},{"images":["http://pic2.zhimg.com/78d91eb70d17c19c8d26c050a2ead779.jpg"],"type":0,"id":8244451,"ga_prefix":"050308","title":"想当美国总统，可能要先过「撞衫」这一关"},{"images":["http://pic4.zhimg.com/dc920ef3c7a4bfbcefee2fb081075dfb.jpg"],"type":0,"id":8184696,"ga_prefix":"050307","title":"为什么国外一般每周或者每两周就发工资？"},{"images":["http://pic3.zhimg.com/6014157993c171b1efa719262b44857e.jpg"],"type":0,"id":8246366,"ga_prefix":"050307","title":"夏天又来了，把孩子独留车内，到底有多危险？"},{"images":["http://pic2.zhimg.com/2eed73c8ab55aea8d0b1dcfda255b8b1.jpg"],"type":0,"id":8240034,"ga_prefix":"050307","title":"这次「IPHONE」商标不归苹果，归一个中国公司"},{"images":["http://pic1.zhimg.com/96ca85f6fd583c5886513e715f413b2c.jpg"],"type":0,"id":8246804,"ga_prefix":"050307","title":"读读日报 24 小时热门 TOP 5 · 有人自称是比特币创始人"},{"images":["http://pic1.zhimg.com/1a828d0ed35bf304772e0cf0f968dd24.jpg"],"type":0,"id":8241802,"ga_prefix":"050306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/afcbb55298c579c79b8a40630071e7e2.jpg","type":0,"id":8246804,"ga_prefix":"050307","title":"读读日报 24 小时热门 TOP 5 · 有人自称是比特币创始人"},{"image":"http://pic1.zhimg.com/07033f5488ccfb017198ca237113601c.jpg","type":0,"id":8240034,"ga_prefix":"050307","title":"这次「IPHONE」商标不归苹果，归一个中国公司"},{"image":"http://pic2.zhimg.com/8200b156ef64da59be774deffa307849.jpg","type":0,"id":8184696,"ga_prefix":"050307","title":"为什么国外一般每周或者每两周就发工资？"},{"image":"http://pic1.zhimg.com/7520059d3e85da6f34c72fd6c6a959b8.jpg","type":0,"id":8244009,"ga_prefix":"050214","title":"青年魏则西之死背后的「免疫疗法」：专访清华大学专家"},{"image":"http://pic1.zhimg.com/9bbf46ad5257f74bf6a18eaca058060c.jpg","type":0,"id":8243238,"ga_prefix":"050217","title":"每周多上几小时班，法国人就要闹「革命」？"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/ad14be82ea7946bb4902bab08a6f201f.jpg"]
     * type : 0
     * id : 8247920
     * ga_prefix : 050310
     * title : 问自己这 5 个问题，看看你是不是在歧视不同的群体
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic3.zhimg.com/afcbb55298c579c79b8a40630071e7e2.jpg
     * type : 0
     * id : 8246804
     * ga_prefix : 050307
     * title : 读读日报 24 小时热门 TOP 5 · 有人自称是比特币创始人
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
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

    public static class TopStoriesBean {
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
}
