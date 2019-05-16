package com.myweb.domain;

import java.io.Serializable;

public class Admin implements Serializable{

    public int id;
    public String loginname;
    public String loginpsw;
    public String username;
    public String usertype;
    public String createtime;
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getLoginname(){
        return this.loginname;
    }
    public void setLoginname(String loginname){
        this.loginname = loginname;
    }
    public String getLoginpsw(){
        return this.loginpsw;
    }
    public void setLoginpsw(String loginpsw){
        this.loginpsw = loginpsw;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsertype(){
        return this.usertype;
    }
    public void setUsertype(String usertype){
        this.usertype = usertype;
    }
    public String getCreatetime(){
        return this.createtime;
    }
    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }
}
