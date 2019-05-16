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

import com.myweb.dao.UserDao;
import com.myweb.domain.User;
import com.myweb.utils.Pager;
import com.myweb.utils.ServletBeanTools;

public class UserServlet extends HttpServlet {

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

			listUser();

		} else if (action.equals("delete")) {// 删除数据

			delUser();

		} else if (action.equals("editinit")) {// 编辑初始化

			editInitUser();

		} else if (action.equals("grxxsave")) {//保存个人信息

			grxxsave();

		} else if (action.equals("editSave")) {// 保存修改的数据

			editSaveUser();

		} else if (action.equals("add")) {// 添加数据

			addUser();

		} else if (action.equals("view")) {// 查看数据

			viewUser();

		} else if (action.equals("page")) {// 分页

			pageUser();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	//查看某条数据
	public void viewUser() throws ServletException, IOException {

		String id = _request.getParameter("id");//传入数据id

		UserDao dao = new UserDao();

		User user = null;

		try {
			user = dao.getUserById(id);

			_request.setAttribute("user",user);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/user/user_View.jsp")
				.forward(_request, _response);

	}
	
  //编辑前初始化数据，返回到页面
	public void editInitUser() throws ServletException, IOException {

		String id = _request.getParameter("id");//获取数据ID

		UserDao dao = new UserDao();

		User user = null;

		try {
			user = dao.getUserById(id);

			_request.setAttribute("user",user);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/user/user_Edit.jsp").forward(_request,
				_response);

	}
	
	 
	
	//保存编辑后的个人信息
	public void grxxsave() throws ServletException, IOException {

		UserDao dao = new UserDao();

		User user = new User();

		ServletBeanTools.populate(user, _request);
		
		HttpSession session=_request.getSession();
		
		User u=(User)session.getAttribute("user");

		user.setId(u.getId());
		
		user.setLoginname(u.getLoginname());

		try {
			dao.updateUser(user);

			_request.setAttribute("alertNote", "1");
			
			session.setAttribute("user", user);
			

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/user/grxx.jsp")
				.forward(_request, _response);
	}
	
	
	//保存编辑后的数据
	public void editSaveUser() throws ServletException, IOException {
	
		String id = _request.getParameter("id");//获取数据ID

		UserDao dao = new UserDao();

		User user = new User();

		ServletBeanTools.populate(user, _request);

		user.setId(Integer.parseInt(id));

		try {
			dao.updateUser(user);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/UserServlet?action=editinit&id=" + id)
				.forward(_request, _response);
	}
	//查询所有数据
	public void listUser() throws ServletException, IOException {

		UserDao dao = new UserDao();

		try {

			List<Map<String, Object>> list = dao.getUserList();

			_request.setAttribute("UserList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/user/user_List.jsp").forward(
				_request, _response);

	}
	
	// 分页
	public void pageUser() throws ServletException, IOException {

		int toPage = 1;// 这是当前页码，如果没有传入页码则默认为第一页

		if (_request.getParameter("toPage") != null) {
			toPage = Integer
					.valueOf(_request.getParameter("toPage").toString());// 获取跳转页码
		}

		UserDao dao = new UserDao();

		try {

			Pager page = dao.getPage(toPage);

			_request.setAttribute("page", page);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/user/user_List.jsp")
				.forward(_request, _response);

	}
	
	//根据数据ID,删除数据
	public void delUser() throws ServletException, IOException {

		String ids = _request.getParameter("ids");//获取数据ID集合

		UserDao dao = new UserDao();

		try {
			dao.delUserByIds(ids);

			List<Map<String, Object>> list = dao.getUserList();

			_request.setAttribute("alertNote", "1");

			Pager page = dao.getPage(1);

			_request.setAttribute("page", page);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/user/user_List.jsp").forward(
				_request, _response);
	}
  
  //添加一条数据
	public void addUser() throws ServletException, IOException {

		UserDao dao = new UserDao();

		User user = new User();

		ServletBeanTools.populate(user, _request);//将传入的数据字段自动添加到实体类中

		try {
			dao.addUser(user);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

			_request.getRequestDispatcher("/admin/user/user_Add.jsp")
				.forward(_request, _response);
	}

}