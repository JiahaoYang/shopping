package yjh.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yjh.db.DbUtil;
import yjh.entity.Login;

/**
 * Servlet implementation class BuyGoods
 */
@WebServlet("/BuyGoods")
public class BuyGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyGoods() {
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
		
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("loginBean");
		if (user == null) {
			response.sendRedirect("/jsp/join/login.jsp");
		}
		String userName = user.getUsername();
		List<String> car = user.getCar();
		if (car.size() != 0) {
			String sqlCommodity = "update commodity set commodity_balance=? where commodity_number=? ";
			String sqlOrderForm = "insert into orderForm(username,commodity_name,commodity_price,sum) values(?,?,?,?)";
			try (Connection conn = DbUtil.getConn();
					PreparedStatement psCommodity = conn.prepareStatement(sqlCommodity);
					PreparedStatement psOrderForm = conn.prepareStatement(sqlOrderForm)) {
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
