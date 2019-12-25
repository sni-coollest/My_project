package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class resetPassword
 */
@WebServlet("/resetPassword")
public class resetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetPassword() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String userName = request.getParameter("user1");
		String userPass = request.getParameter("newpass");
		
		ServletContext ctx = this.getServletContext();
		String server = ctx.getInitParameter("server");
		String dbname = ctx.getInitParameter("dbname");
		String user = ctx.getInitParameter("user");
		String pwd = ctx.getInitParameter("pwd");
		DBoper db = new DBoper();
		try{
			db.getConn(server,dbname,user,pwd);
			String sql = "UPDATE user SET password=? WHERE username=? ";
			int rs = db.executeUpdate(sql, new String[] {userPass,	userName});
			
			if(rs > 0){
				out.print("<script>" + "alert('修改成功!!');"+ "document.location.href='index.jsp';</script>");
				//response.sendRedirect("index.jsp");
			}else{
				out.print("<script>" + "alert('修改失败!!');"+ "document.location.href='index.jsp';</script>");
			}
		}
		catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}

}
