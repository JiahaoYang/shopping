package yjh.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yjh.db.DbUtil;
import yjh.entity.Register;

/**
 * Servlet implementation class HandleRegister
 */
@WebServlet("/HandleRegister")
public class HandleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf8");
		request.setCharacterEncoding("utf8");
		
		Register registerBean = new Register();
		request.setAttribute("userBean", registerBean);
		
		String userName = "";
		String userPwd = "";
		String againPwd = "";
		String phone = "";
		String address = "";
		String realName = "";
		
		userName = request.getParameter("userName").trim();
		userPwd = request.getParameter("userPwd").trim();
		againPwd = request.getParameter("againPwd").trim();
		phone = request.getParameter("phone").trim();
		address = request.getParameter("address").trim();
		realName = request.getParameter("realName").trim();
		
		String regex = "[\\d]{11}";
		if (userName == null)
			userName = "";
		if (userPwd == null || userPwd == "")
			userPwd = "error";
		if (!userPwd.equals(againPwd)) {
			registerBean.setBackNews("两次密码不一致");
			request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
		}
		else if (phone != null && phone.length() > 0 && !phone.matches(regex)) {
			registerBean.setBackNews("请正确填写11位手机号");
			request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
		}
		else {
			String backNews = "";
			String sql = " insert vip(username, user_pwd, phone, address, realname) values(?,?,?,?,?) ";
			try (Connection conn = DbUtil.getConn();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, userName);
				ps.setString(2, userPwd);
				ps.setString(3, phone);
				ps.setString(4, address);
				ps.setString(5, realName);
				
				int rs = ps.executeUpdate();
				if (rs > 0) {
					backNews = "注册成功";
					registerBean.setBackNews(backNews);
					request.getRequestDispatcher("/jsp/join/registerSuccess.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				System.out.println(e);
				backNews = "该用户名已被注册";
				registerBean.setBackNews(backNews);
				request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
			}
		}	
	}
}
