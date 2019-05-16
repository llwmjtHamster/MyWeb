package com.myweb.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import com.myweb.dao.UserDao;

import com.myweb.domain.User;
import com.myweb.utils.ServletBeanTools;

public class UserService extends HttpServlet {
    HttpSession _session;

    HttpServletRequest _request;

    HttpServletResponse _response;

    String callback = "";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        _session = request.getSession();

        _request = request;

        _response = response;


        String action = "";

        action = _request.getParameter("action").toString();

        callback = request.getParameter("callback");

        if (action.equals("login")) {

            login();

        } else if (action.equals("checkuser")) {

            checkuser();

        }
        else if (action.equals("zhmm")) {

            zhmm();

        }else if (action.equals("reg")) {

            reg();


        } else if (action.equals("updateuser")) {

            updateuser();

        }


    }

    public void login() throws ServletException, IOException {

        String result = "";// 返回结果集

        String loginname = _request.getParameter("loginname").toString();

        String loginpassword = _request.getParameter("loginpw").toString();

        try {

            UserDao userDAO = new UserDao();


            User user1 = userDAO.getUserByUsernameAndPwd(loginname,
                    loginpassword);

            if (user1 != null) {


                result = JSONObject.toJSONString(user1);

            } else {
                result = "{info : \"fail\"}";
            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
            /* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");

            if (callback != null) {
                result = callback + "(" + result + ")";
            }
            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

    //检查用户名是否重复
    public void checkuser() throws ServletException, IOException {

        String result = "";// 返回结果集

        String loginname = _request.getParameter("loginname").toString();

        try {

            UserDao userDAO = new UserDao();

            User user1 = new User();

            user1 = userDAO.getUseByLoginname(
                    loginname);

            if (user1 != null) {

                result = "{info : \"\"}";

            } else {
                result = "{info : \"success\"}";
            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");

            if (callback != null) {
                result = callback + "(" + result + ")";
            }
            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }


    //找回密码
    public void zhmm() throws ServletException, IOException {

        String result = "";// 返回结果集

        String loginname = _request.getParameter("loginname").toString();

        String name = _request.getParameter("name").toString();

        String idnum = _request.getParameter("idnum").toString();

        try {

            UserDao userDAO = new UserDao();

            User user1 = new User();

            user1 = userDAO.zzmm(
                    loginname, name,idnum);

            if (user1 != null) {

                result = JSONObject.toJSONString(user1);

            }
            else
            {
                result = "{info : \"fail\"}";
            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");

            if (callback != null) {
                result = callback + "(" + result + ")";
            }
            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

    public void reg() throws ServletException, IOException {

        String result = "";// 返回结果集

        User user = new User();

        ServletBeanTools.populateApp(user, _request);//将传入的数据字段自动添加到实体类中

        try {

            UserDao userDAO = new UserDao();

            userDAO.addUser(user);

            result = "{info : \"success\"}";


        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");

            if (callback != null) {
                result = callback + "(" + result + ")";
            }
            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

   //修改个人信息
    public void updateuser() throws ServletException, IOException {

        String result = "";// 返回结果集

        String id = _request.getParameter("id");//获取数据ID


        User user = new User();

        ServletBeanTools.populateApp(user, _request);//将传入的数据字段自动添加到实体类中

        user.setId(Integer.parseInt(id));

        try {

            UserDao userDAO = new UserDao();

            userDAO.updateUser(user);

            result = "{info : \"success\"}";


        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");

            if (callback != null) {
                result = callback + "(" + result + ")";
            }
            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }


    // 查看用户信息
    public void viewUser(String userid) throws ServletException, IOException {

        String result = "";// 返回结果集

        try {

            UserDao userDAO = new UserDao();

            User user = new User();

            user = userDAO.getUserById(userid);

            if (user != null) {

                result = JSONObject.toJSONString(user);

            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");


            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

    // 用户修改
    public void updateUser(String user) throws ServletException, IOException {

        String result = "";// 返回结果集

        try {

            User userObj = JSONObject.parseObject(user, User.class);

            UserDao userDAO = new UserDao();

            userDAO.updateUser(userObj);

            result = "ok";

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");


            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

    // 用户注册
    public void userReg(String user) throws ServletException, IOException {

        String result = "";// 返回结果集

        try {

            User userObj = JSONObject.parseObject(user, User.class);

            UserDao userDAO = new UserDao();

            int rel = userDAO.addUser(userObj);

            if (rel > -1) {

                result = "ok";

            }

        } catch (Exception e) {

            System.out.print(e.getMessage());

        } finally {
			/* 返回数据 */
            _response.setCharacterEncoding("UTF-8");

            _response.setHeader("content-type", "text/html;charset=UTF-8");


            PrintWriter pw = _response.getWriter();

            pw.write(result);

            pw.flush();

            pw.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
