package com.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.dao.NewsDao;
import com.myweb.domain.News;
import com.myweb.utils.Pager;
import com.myweb.utils.ServletBeanTools;

public class NewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		action = request.getParameter("action").toString();
		
		if (action.equals("list")) {// 查询所有数据

			listNews();

		} else if (action.equals("delete")) {// 删除数据

			delNews();

		} else if (action.equals("editinit")) {// 编辑初始化

			editInitNews();

		} else if (action.equals("editSave")) {// 保存修改的数据

			editSaveNews();

		} else if (action.equals("add")) {// 添加数据

			addNews();

		} else if (action.equals("view")) {// 查看数据

			viewNews();

		} else if (action.equals("page")) {// 分页

			pageNews();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	//查看某条数据
	public void viewNews() throws ServletException, IOException {

		String id = _request.getParameter("id");//传入数据id

		NewsDao dao = new NewsDao();

		News news = null;

		try {
			news = dao.getNewsById(id);

			_request.setAttribute("news",news);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/news/news_View.jsp")
				.forward(_request, _response);

	}
	
  //编辑前初始化数据，返回到页面
	public void editInitNews() throws ServletException, IOException {

		String id = _request.getParameter("id");//获取数据ID

		NewsDao dao = new NewsDao();

		News news = null;

		try {
			news = dao.getNewsById(id);

			_request.setAttribute("news",news);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/news/news_Edit.jsp").forward(_request,
				_response);

	}
	
	//保存编辑后的数据
	public void editSaveNews() throws ServletException, IOException {
	
		String id = _request.getParameter("id");//获取数据ID

		NewsDao dao = new NewsDao();

		News news = new News();

		ServletBeanTools.populate(news, _request);

		news.setId(Integer.parseInt(id));

		try {
			dao.updateNews(news);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/NewsServlet?action=editinit&id=" + id)
				.forward(_request, _response);
	}
	//查询所有数据
	public void listNews() throws ServletException, IOException {

		NewsDao dao = new NewsDao();

		try {

			List<Map<String, Object>> list = dao.getNewsList();

			_request.setAttribute("NewsList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/news/news_List.jsp").forward(
				_request, _response);

	}
	
	// 分页
	public void pageNews() throws ServletException, IOException {

		int toPage = 1;// 这是当前页码，如果没有传入页码则默认为第一页

		if (_request.getParameter("toPage") != null) {
			toPage = Integer
					.valueOf(_request.getParameter("toPage").toString());// 获取跳转页码
		}

		NewsDao dao = new NewsDao();

		try {

			Pager page = dao.getPage(toPage);

			_request.setAttribute("page", page);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/news/news_List.jsp")
				.forward(_request, _response);

	}
	
	//根据数据ID,删除数据
	public void delNews() throws ServletException, IOException {

		String ids = _request.getParameter("ids");//获取数据ID集合

		NewsDao dao = new NewsDao();

		try {
			dao.delNewsByIds(ids);

			List<Map<String, Object>> list = dao.getNewsList();

			_request.setAttribute("alertNote", "1");

			Pager page = dao.getPage(1);

			_request.setAttribute("page", page);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/news/news_List.jsp").forward(
				_request, _response);
	}
  
  //添加一条数据
	public void addNews() throws ServletException, IOException {

		NewsDao dao = new NewsDao();

		News news = new News();

		ServletBeanTools.populate(news, _request);//将传入的数据字段自动添加到实体类中

		try {
			dao.addNews(news);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

			_request.getRequestDispatcher("/admin/news/news_Add.jsp")
				.forward(_request, _response);
	}

}