package com.myweb.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.myweb.domain.News;
import com.myweb.utils.DB;
import com.myweb.utils.Pager;
import com.myweb.utils.SQLUtil;

public class NewsDao {

    //构造函数，初始化数据库
    public NewsDao() {


        SQLUtil.tableName = "news";//数据库表名

        SQLUtil.pkColumnName = "id";//主键字段
    }

    //添加数据，并返回主键ID
    public int addNews(News news) {
        DB db = new DB();

        db.getConnection();
        int id = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createtime = df.format(new Date());// new Date()为获取当前系统时间
        news.setCreatetime(createtime);
        news.setNewstype("2");
        //获取插入数据sql
        String sql = SQLUtil.genInsertSQL(news);

        List<Object> params = new ArrayList<Object>();

        try {
            boolean flag = db.updateByPreparedStatement(sql, params);

            System.out.println(flag);

            id = db.getMaxId("news");

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return id;

    }

    //查询所有数据
    public List<Map<String, Object>> getNewsList() {
        DB db = new DB();

        db.getConnection();
        String sql2 = "select * from News  where newstype='2'  order by id desc";

        List<Map<String, Object>> list = null;

        try {
            list = db.findModeResult(sql2, null);

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            db.closed();
        }
        return list;

    }

    //通过主键ID集合，删除数据
    public void delNewsByIds(String ids) {
        DB db = new DB();

        db.getConnection();
        String sql = "delete from News where   newstype='2' id in (" + ids + ")";

        List<Object> list = new ArrayList<Object>();

        //list.add(id);

        try {
            boolean flag = db.updateByPreparedStatement(sql, list);

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
    }

    //根据数据ID，获取一条数据
    public News getNewsById(String id) {
        DB db = new DB();

        db.getConnection();
        News news = null;

        String sql = "select *  from News where id=(?)  and   newstype='2' ";

        List<Object> list = new ArrayList<Object>();

        list.add(id);

        try {
            news = (News) db.findSimpleRefResult(sql, list, News.class);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return news;
    }

    //传入数据实体进行修改
    public boolean updateNews(News news) {
        DB db = new DB();

        db.getConnection();
        boolean flag = false;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createtime = df.format(new Date());// new Date()为获取当前系统时间
        news.setCreatetime(createtime);


        //获取修改数据sql
        String sql = SQLUtil.genUpdateSQL(news);

        List<Object> params = new ArrayList<Object>();

        try {
            flag = db.updateByPreparedStatement(sql, params);

            System.out.println(flag);

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return flag;

    }

    //分页查询类，传入当前页数
    public Pager getPage(int pageNumber) {
        DB db = new DB();

        db.getConnection();

        String sql = "select * from News  where   newstype='2'  order by id ";

        List<Map<String, Object>> list = null;

        try {
            list = db.findModeResult(sql, null);

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        int totalCount = Integer.valueOf(list.size());//获取总页数

        Pager pager = new Pager(totalCount, pageNumber);

        String sql1 = "";

        if (pager.getPageNumber() > 0) {
            sql1 = "select * from News   where   newstype='2'   order by id limit " + (pager.getPageNumber() - 1) * pager.getLimit() + "," + pager.getLimit();
        } else {
            sql1 = "select * from News   where   newstype='2'   order by id limit " + (pager.getPageNumber()) * pager.getLimit() + "," + pager.getLimit();
        }

        List<Map<String, Object>> list1 = null;

        try {
            list1 = db.findModeResult(sql1, null);

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            db.closed();
        }

        pager.setList(list1);

        return pager;//返回数据集合
    }

}