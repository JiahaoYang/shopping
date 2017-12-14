package yjh.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import yjh.db.DbUtil;
import yjh.entity.Login;

/**
 * Servlet implementation class HandleLogin
 */
@WebServlet("/HandleLogin")
public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf8");
		request.setCharacterEncoding("utf8");
		
		String userName = "";
		String userPwd = "";
		String isCookie = "";
		userName = request.getParameter("userName");
		userPwd = request.getParameter("userPwd");
		isCookie = request.getParameter("isCookie");
//		System.out.println(isCookie);
		//处理cookie信息
		handleCookies(request, response, userName, userPwd, isCookie);
		
		String sql = "select * from vip where userName=? and user_pwd=? ";
		try (Connection conn = DbUtil.getConn();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userName);
			ps.setString(2, userPwd);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					success(request, response, userName);
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				else {
					String backNews = "用户名或密码错误";
					fail(request, response, backNews);
				}
			} catch (SQLException e) {
				String backNews = "登陆失败" + e;
				fail(request, response, backNews);
			}
		} catch (SQLException e) {
			String backNews = "登陆失败" + e;
			fail(request, response, backNews);
		}
	}

	
	/**
	 * 处理cookie信息
	 * @param request
	 * @param response
	 * @param userName
	 * @param userPwd
	 * @param isCookie
	 */
	public void handleCookies(HttpServletRequest request, HttpServletResponse response, String userName,
			String userPwd, String isCookie) {
		if ("isCookie".equals(isCookie)) {
			Cookie nameCookie = new Cookie("userName", userName);
			Cookie pwdCookie = new Cookie("userPwd", userPwd);
			
			//该工程可以访问cookie，默认设置cookie的路径才能访问
			nameCookie.setPath("/");
			pwdCookie.setPath("/");
			//设置生命周期。默认浏览器关闭则到期
			nameCookie.setMaxAge(60*60*24*10);
			pwdCookie.setMaxAge(60*60*24*10);
			response.addCookie(nameCookie);
			response.addCookie(pwdCookie);
		}
		//未选择记住密码，清除cookie
		else {
			Cookie[] cookies = null;
			cookies = request.getCookies();
			if (cookies != null && cookies.length > 0)
				for (Cookie cookie : cookies)
					if ("userName".equals(cookie.getName()) || "userPwd".equals(cookie.getName())) { 
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
		}
	}
	
	
	/**
	 * 登录成功，存储用户信息
	 * @param request
	 * @param response
	 * @param userName
	 */
	private void success(HttpServletRequest request, HttpServletResponse response, String userName) {
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try {
			//获取可能存在的loginBean信息
			loginBean = (Login) session.getAttribute("loginBean");
			if (loginBean == null) {
				loginBean = new Login();
				session.setAttribute("loginBean", loginBean); //在jsp的useBean作为id唯一标识此对象
				session.setMaxInactiveInterval(60*10); //生存周期
				loginBean = (Login) session.getAttribute("loginBean");
			}
			
			String name = loginBean.getUsername();
			if (userName.equals(name))
				loginBean.setBackNews(name + "您已登录");
			else { 
				loginBean.setBackNews(userName + "登录成功");
				loginBean.setUsername(userName);
			}
		} catch (Exception e) {
			String backNews = e + "登录失败";
			fail(request, response, backNews);
		}
		
	}
	
	private void fail(HttpServletRequest request, HttpServletResponse response, String backNews) {
		try {
			PrintWriter out = response.getWriter();
			out.println(backNews);
			out.print("返回" + "<a href=/shopping1/jsp/join/login.jsp>登录界面</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
