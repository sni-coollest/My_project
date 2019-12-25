package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("user2");

		ServletContext ctx = this.getServletContext();
		String server = ctx.getInitParameter("server");
		String dbname = ctx.getInitParameter("dbname");
		String user = ctx.getInitParameter("user");
		String pwd = ctx.getInitParameter("pwd");
		DBoper db = new DBoper();
		try{
			db.getConn(server,dbname,user,pwd);
			String sql = "DELETE FROM user WHERE username=? ";
			int rs = db.executeUpdate(sql, new String[] {userName});
			out.print(rs);
			if(rs > 0 ){
				out.print("<script>" + "alert('É¾³ý³É¹¦!!');"+ "document.location.href='index.jsp';</script>");
				//response.sendRedirect("index.jsp");
			}else{
				out.print("<script>" + "alert('É¾³ýÊ§°Ü!!');"+ "document.location.href='index.jsp';</script>");
				//out.println("É¾³ýÊ§°Ü");
			}
		}
		catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}

}
