package com.example.zhaoting.myapplication.bean;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentBean {

    /**
     * author : 一边按快门一边等待逆袭
     * content : 居然看到图片里面有骨头，我想问问吃的哪家飞机餐。。。
     * avatar : http://pic1.zhimg.com/290172b4c_im.jpg
     * time : 1413665436
     * id : 547265
     * likes : 0
     */

    private String author;
    private String content;
    private String avatar;
    private int time;
    private int id;
    private int likes;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
