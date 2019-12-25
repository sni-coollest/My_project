<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>用户管理系统</title>

</head>
<body align="center">
<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%
	//驱动程式名
	String driverName="com.mysql.jdbc.Driver";
	//数据库用户名
	String userName="root";
	//密码
	String userPasswd="1234";
	//数据库名
	String dbName="db_user";
	//表名
	String tableName="user";
	//联结字符串
	String url="jdbc:mysql://localhost:3306/"+dbName+"?user="+userName+"&password="+userPasswd;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection connection=DriverManager.getConnection(url);
	Statement statement = connection.createStatement();
	String sql="SELECT * FROM "+tableName;
	ResultSet rs = statement.executeQuery(sql);
	//获得数据结果集合
	ResultSetMetaData rmeta = rs.getMetaData();
	//确定数据集的列数，亦字段数
	int numColumns=rmeta.getColumnCount();
	// 输出每一个数据值
	out.print("<table align='center' border='1' width='600px' id='tablelist'> <tr> <th>");
	out.print("ID");
	out.print("</th><th>");
	out.print("用户名");
	out.print("</th><th>");
	out.print("密码");
	out.print("</th><th>");
	out.print("手机号");
	out.print("</th><th>管理</th></tr>");
	int i=1;
	while(rs.next()) {
		out.print("<tr id='tr"+i+"'><td>");
		out.print(i);
		out.print("</td><td>");
		out.print(rs.getString(1));
		out.print("</td><td>");
		out.print(rs.getString(2));
		out.print("</td><td>");
		out.print(rs.getString(3));
		out.print("</td>");
		out.print("<td>"		
			+"<input type='text' id='newpwd"+i+"' placeholder='输入新密码' value=''>"+"   "
			+"<input type='button' value='修改密码' onClick='subReset("+i+")' ></form>"+"   "
			+"<input type='button' value='删除用户' onClick='subDel("+i+")'></form></td>");
		out.print("</tr>");
		i++;
	}
	out.print("</table>");
	out.print("<br/>");
	out.print("<form method='POST' action='resetPassword' name='ResetPass' autocomplete='off'><input type='hidden' value='' id='user1' name='user1'/><input type='hidden' value='' id='newpass' name='newpass'/></form>");
	out.print("<form method='POST' action='deleteUser' name='DelUser'><input type='hidden' value='' id='user2' name='user2'/><form>");
	out.print("数据库操作成功，恭喜你");
	rs.close();
	statement.close();
	connection.close();
%>
<a href="Login.html">返回登录</a>
<script>
function subReset(id){
	var tableId = document.getElementById("tablelist"); 
	var re=tableId.rows[id].cells[1].innerHTML;	
	var newpwd="newpwd"+id;	
	var pwd=document.getElementById(newpwd).value;
	document.getElementById('user1').value=re;
	document.getElementById('newpass').value=pwd;
	if(re == 'admin'){
		alert("这是管理员哦,不能修改!");
	}else{
		if(pwd.length>0){
			if(pwd.length>=6 && pwd.length<=8){
				alert("将密码修改为"+pwd);
				document.ResetPass.submit();
			}
			else{
				alert("密码必须在6~8位之间!");
			}			
		}else{
			alert("新密码不能为空");
		}
	}
}
function subDel(id){
	var result = confirm("你确定吗");
	if(result == true){
		var tableId = document.getElementById("tablelist"); 
		var re=tableId.rows[id].cells[1].innerHTML;
	 	if(re == 'admin'){
	 		alert("这是管理员哦,不能修改!");
	 	}else{
			alert("删除"+re);  
	 		document.getElementById('user2').value=re;
	 		document.DelUser.submit();
	 	}
	}
	else{
		return;
	}
}
</script>
</body>
</html>