package exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String userpass = request.getParameter("password");
		String userph = request.getParameter("phnumber");
		ServletContext ctx = this.getServletContext();
		String server = ctx.getInitParameter("server");
		String dbname = ctx.getInitParameter("dbname");
		String user = ctx.getInitParameter("user");
		String pwd = ctx.getInitParameter("pwd");
		DBoper db = new DBoper();
		try{
			//�������ݿ�
			db.getConn(server, dbname, user, pwd);
			//�����¼
			String sql = "INSERT INTO user(username,password,phnumber) values(?,?,?)";
			int rs = db.executeUpdate(sql, new String[] {username,userpass,userph});
			if(rs>0){
				//����ɹ�
				response.sendRedirect("Login.html");
			}else{
				//����ʧ��
				out.println("ע��ʧ��");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
