package com.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.dao.AdminDao;
import com.myweb.domain.Admin;
 
 

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginname = request.getParameter("loginname");

		String loginpassword = request.getParameter("loginpassword");
		/*
		 * String logintype = request.getParameter("logintype");
		 * 
		 * if (logintype.equals("1")) { AdminDao dao = new AdminDao();
		 * 
		 * Admin admin = null;
		 * 
		 * try { admin = dao.getAdminByUsernameAndPwd(loginname, password);
		 * 
		 * } catch (Exception ex) {
		 * 
		 * } if (admin != null) { HttpSession session = request.getSession();
		 * 
		 * session.setAttribute("logintype", "1");
		 * 
		 * session.setAttribute("admin", admin);
		 * 
		 * request.setAttribute("alertNote", "1");
		 * 
		 * request.getRequestDispatcher("/admin/index.jsp").forward(request,
		 * response); } else { request.setAttribute("alertNote", "0");
		 * 
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * } } else {
		 * 
		 * UserDao dao = new UserDao();
		 * 
		 * User user = null;
		 * 
		 * try { user= dao.getUserByLoginNameAndPassword(loginname, password);
		 * 
		 * } catch (Exception ex) {
		 * 
		 * } if (user != null) { HttpSession session = request.getSession();
		 * 
		 * session.setAttribute("logintype", "0");
		 * 
		 * session.setAttribute("user", user);
		 * 
		 * request.setAttribute("alertNote", "1");
		 * 
		 * request.getRequestDispatcher("/user/index.jsp").forward(request,
		 * response); } else { request.setAttribute("alertNote", "0");
		 * 
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * } }
		 * 
		 * 
		 * 
		 * Admin admin = null;
		 * 
		 * try { admin = dao.getAdminByUsernameAndPwd(loginname, password);
		 * 
		 * } catch (Exception ex) {
		 * 
		 * } if (admin != null) { HttpSession session = request.getSession();
		 * 
		 * session.setAttribute("logintype", "1");
		 * 
		 * session.setAttribute("admin", admin);
		 * 
		 * request.setAttribute("alertNote", "1");
		 * 
		 * request.getRequestDispatcher("/admin/index.jsp").forward(request,
		 * response); } else { request.setAttribute("alertNote", "0");
		 * 
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * }
		 */

		AdminDao dao = new AdminDao();
		
		Admin admin = null;

		try {
			admin = dao.getAdminByUsernameAndPwd(loginname, loginpassword);

		} catch (Exception ex) {

		}
		if (admin != null) {
			HttpSession session = request.getSession();

			session.setAttribute("logintype", "admin");

			session.setAttribute("admin", admin);

			request.setAttribute("alertNote", "1");

			request.getRequestDispatcher("/admin/index.jsp").forward(request,
					response);
		} else {
			request.setAttribute("alertNote", "0");

			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
