package com.myweb.domain;

import java.io.Serializable;

public class News implements Serializable{

    public int id;

    public String title;


    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public String newstype;

    public String imgpath;

    public String getShipin() {
        return shipin;
    }

    public void setShipin(String shipin) {
        this.shipin = shipin;
    }

    public String shipin;

    public String content;

    public String createtime;

    public int getId(){

        return this.id;
    }
    public void setId(int id){

        this.id = id;
    }
    public String getTitle(){

        return this.title;
    }
    public void setTitle(String title){

        this.title = title;
    }
    public String getImgpath(){

        return this.imgpath;
    }
    public void setImgpath(String imgpath){

        this.imgpath = imgpath;
    }
    public String getContent(){

        return this.content;
    }
    public void setContent(String content){

        this.content = content;
    }
    public String getCreatetime(){

        return this.createtime;
    }
    public void setCreatetime(String createtime){

        this.createtime = createtime;
    }
}
