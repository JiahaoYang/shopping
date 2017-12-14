package yjh.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sun.rowset.CachedRowSetImpl;

import yjh.db.DbUtil;
import yjh.entity.Goods;
import yjh.entity.Login;

/**
 * Servlet implementation class GoodsDao
 */
@WebServlet("/GoodsDao")
public class GoodsDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDao() {
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
		response.setContentType("text/html; chartset=utf8");
		request.setCharacterEncoding("utf8");
		
		String keyStr = "";
		keyStr = request.getParameter("key");
		int key = Integer.parseInt(keyStr);
		
		String keyWord = "";
		keyWord = request.getParameter("keyWord");
		
		queryGoods(request, response, key, keyWord);
	}

	private void queryGoods(HttpServletRequest request, HttpServletResponse response, int key, String keyWord) {
		response.setContentType("text/html; charset=utf8");
		try (PrintWriter out = response.getWriter();
				Connection conn = DbUtil.getConn()) {
			CachedRowSetImpl rowSet = new CachedRowSetImpl();
			HttpSession session = request.getSession();
			Login user = (Login) session.getAttribute("loginBean");
			Goods goods = (Goods) session.getAttribute("goods");
			if (user == null) {
				user = new Login();
				session.setAttribute("user", user);
			}
			if (goods == null) {
				goods = new Goods();
			//	System.out.println("new Goods");
				session.setAttribute("goods", goods);
			}
			String userName = user.getUsername();
			if ("".equals(userName)) {
				out.println();
				out.print("<center><font color=#008B8B>请先登录</font>");
				out.println("<a href=/shopping1/jsp/join/login.jsp>登录</a></center>");
			}
			else {
				switch (key) {
				//订单查询 :商品名+数量
				case 1:
					String sql = "select commodity_name, sum(cnt) from orderform "
							+ "where username=? group by commodity_name having sum(cnt)>0 ";
					try (PreparedStatement ps = conn.prepareStatement(sql)) {
							ps.setString(1, userName);
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								rowSet.populate(ps.executeQuery());	
								goods.setRowSetImpl(rowSet);
								request.getRequestDispatcher("/jsp/order/orderForm.jsp").forward(request, response);
							}
							else {
								out.print("<br/><br/><br/><center>");
								out.print("<font color=green>亲,没有订单</font>");
								out.print("<a href=/shopping1/GoodsDao?key=2><font color=red size=6>Go Shopping</font></a>");
								out.print("</center>");
							}
					} catch (SQLException e) {
						System.out.println("查看订单异常");
						e.printStackTrace();
					} catch (ServletException e) {
						System.out.println("request转发异常");
						e.printStackTrace();
					}
					break;
				case 2:
					String sqlList = "select * from commodity";
					try (PreparedStatement ps = conn.prepareStatement(sqlList)) {
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								rowSet.populate(ps.executeQuery());	
								goods.setRowSetImpl(rowSet);
								request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
							}
							else {
								out.print("<br/><br/><br/><center>");
								out.print("<font color=green>亲,卖家未上货</font>");
								out.print("<a href=/shopping1/index.jsp><font color=red size=6>返回首页</font></a>");
								out.print("</center>");
							}
					} catch (SQLException e) {
						System.out.println("查看商品异常");
						e.printStackTrace();
					} catch (ServletException e) {
						System.out.println("request转发异常");
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
