package yjh.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yjh.entity.Login;

/**
 * Servlet implementation class PutToCar
 */
@WebServlet("/PutToCar")
public class PutToCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutToCar() {
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
		
		String goods = request.getParameter("putToCar");
		if (goods == null) {
			response.sendRedirect("/index.jsp");
		}
		else {
			String[] detail = goods.split(",");
			HttpSession session = request.getSession();
			Login user = (Login) session.getAttribute("loginBean"); 
			LinkedList<String> car = user.getCar();
			car.add(goods);
			user.setCar(car);
			
			backNews(request, response, detail[1]);
		}
	}

	private void backNews(HttpServletRequest request, HttpServletResponse response, String goodName) {
		try {
			PrintWriter out = response.getWriter();
			out.print("<br/><br/><br/>");
			out.print("<center><font color=red><b>" + goodName + "</b></font>&nbsp;已成功添加进购物车");
			out.print("<br/><br/><br/>");
			out.print("<br/><br/><br/>");
			out.print("<a href=/shopping1/jsp/browse/showGoods.jsp>返回继续购物</a>");
			out.print("&nbsp;or&nbsp");
			out.print("<a href=/shopping1/jsp/shoppingCar/lookShoppingCar.jsp>查看购物车</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
