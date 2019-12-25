package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String userPass = request.getParameter("password");
		
		ServletContext ctx = this.getServletContext();
		String server = ctx.getInitParameter("server");
		String dbname = ctx.getInitParameter("dbname");
		String user = ctx.getInitParameter("user");
		String pwd = ctx.getInitParameter("pwd");
		DBoper db = new DBoper();
		try{
			db.getConn(server,dbname,user,pwd);
			String sql = "SELECT username,password FROM user WHERE username=? AND password=? ";
			ResultSet rs = db.executeQuery(sql, new String[] {userName,		userPass});
			
			if(rs != null && rs.next()){
				response.sendRedirect("index.jsp");
			}else{
				out.print("<script>" + "alert('’À∫≈√‹¬Î¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î');"+ "document.location.href='Login.html';</script>");
				//response.sendRedirect("Login.html");
			}
		}
		catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}

}
