package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Admin;
import com.myweb.domain.User;
import com.myweb.utils.DB;
import com.myweb.utils.Pager;
import com.myweb.utils.SQLUtil;

public class UserDao {

    //构造函数，初始化数据库
    public UserDao() {


        SQLUtil.tableName = "user";//数据库表名

        SQLUtil.pkColumnName = "id";//主键字段
    }


    // 普通用户登录验证
    public User getUserByUsernameAndPwd(String username, String password) {


        User user = null;

        DB db = new DB();

        db.getConnection();

        String sql = "select *  from user where loginname=(?) and loginpw=(?)";

        List<Object> list = new ArrayList<Object>();

        list.add(username);

        list.add(password);

        try {
            user = (User) db.findSimpleRefResult(sql, list,
                    User.class);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return user;
    }


    // 根据用户名查询用户是否存在
    public User getUseByLoginname(String username) {

        User user = null;

        DB db = new DB();

        db.getConnection();

        String sql = "select *  from user where loginname=(?)";

        List<Object> list = new ArrayList<Object>();

        list.add(username);

        try {
            user = (User) db.findSimpleRefResult(sql, list,
                    User.class);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return user;
    }


    //找回密码
    public User zzmm(String username, String name, String idnum) {

        User user = null;

        DB db = new DB();

        db.getConnection();

        String sql = "select *  from user where loginname=(?) and name=(?) and  idnum=(?)";

        List<Object> list = new ArrayList<Object>();

        list.add(username);

        list.add(name);

        list.add(idnum);

        try {
            user = (User) db.findSimpleRefResult(sql, list,
                    User.class);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return user;
    }

    //添加数据，并返回主键ID
    public int addUser(User user) {

        DB db = new DB();

        db.getConnection();

        int id = 0;

        //获取插入数据sql
        String sql = SQLUtil.genInsertSQL(user);

        List<Object> params = new ArrayList<Object>();

        try {
            boolean flag = db.updateByPreparedStatement(sql, params);

            System.out.println(flag);

            id = db.getMaxId("user");

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return id;

    }

    //查询所有数据
    public List<Map<String, Object>> getUserList() {

        DB db = new DB();

        db.getConnection();

        String sql2 = "select * from User  order by id ";

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
    public void delUserByIds(String ids) {
        DB db = new DB();

        db.getConnection();

        String sql = "delete from User where id in (" + ids + ")";

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
    public User getUserById(String id) {
        DB db = new DB();

        db.getConnection();

        User user = null;

        String sql = "select *  from User where id=(?)";

        List<Object> list = new ArrayList<Object>();

        list.add(id);

        try {
            user = (User) db.findSimpleRefResult(sql, list, User.class);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            db.closed();
        }
        return user;
    }

    //传入数据实体进行修改
    public boolean updateUser(User user) {
        DB db = new DB();

        db.getConnection();

        boolean flag = false;

        //获取修改数据sql
        String sql = SQLUtil.genUpdateSQL(user);

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

        String sql = "select * from User  order by id ";

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
            sql1 = "select * from User   order by id limit " + (pager.getPageNumber() - 1) * pager.getLimit() + "," + pager.getLimit();
        } else {
            sql1 = "select * from User   order by id limit " + (pager.getPageNumber()) * pager.getLimit() + "," + pager.getLimit();
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