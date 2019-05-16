package com.myweb.domain;

import java.io.Serializable;

public class User implements Serializable{

    public int id;

    public String loginname;

    public String loginpw;

    public String name;

    public String sex;

    public String age;

    public String address;

    public String xueli;

    public String dianhua;
    
    public String filepath;

    public String del;

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String idnum;

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
    public String getLoginpw(){

        return this.loginpw;
    }
    public void setLoginpw(String loginpw){

        this.loginpw = loginpw;
    }
    public String getName(){

        return this.name;
    }
    public void setName(String name){

        this.name = name;
    }
    public String getSex(){

        return this.sex;
    }
    public void setSex(String sex){

        this.sex = sex;
    }
    public String getAge(){

        return this.age;
    }
    public void setAge(String age){

        this.age = age;
    }
    public String getAddress(){

        return this.address;
    }
    public void setAddress(String address){

        this.address = address;
    }
    public String getXueli(){

        return this.xueli;
    }
    public void setXueli(String xueli){

        this.xueli = xueli;
    }
    public String getDianhua(){

        return this.dianhua;
    }
    public void setDianhua(String dianhua){

        this.dianhua = dianhua;
    }
    public String getDel(){

        return this.del;
    }
    public void setDel(String del){

        this.del = del;
    }
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
