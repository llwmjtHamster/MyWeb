package com.myweb.service;

import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.GzhwzDao;
import com.myweb.dao.NewsDao;
import com.myweb.domain.Gzhwz;
import com.myweb.utils.ServletBeanTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import  com.myweb.dao.SPNewsDao;

public class NewsService extends HttpServlet {
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
        if (action.equals("newslist")) {//通知公告列表

            newslist();

        }


    }

    //新闻公告列表
    public void newslist() throws ServletException, IOException {

        String result = "";// 返回结果集


        String lx = _request.getParameter("lx").toString();


        try {

            if(lx.equals("1"))
            {
                SPNewsDao newsDAO = new SPNewsDao();

                List<Map<String, Object>> list = newsDAO.getNewsList();

                if (list.size() > 0) {

                    result = JSONObject.toJSONString(list);

                }
            }
            else if(lx.equals("2"))
            {

                NewsDao newsDAO = new NewsDao();

                List<Map<String, Object>> list = newsDAO.getNewsList();

                if (list.size() > 0) {

                    result = JSONObject.toJSONString(list);

                }
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


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
