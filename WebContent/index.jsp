<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�û�����ϵͳ</title>

</head>
<body align="center">
<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%
	//������ʽ��
	String driverName="com.mysql.jdbc.Driver";
	//���ݿ��û���
	String userName="root";
	//����
	String userPasswd="1234";
	//���ݿ���
	String dbName="db_user";
	//����
	String tableName="user";
	//�����ַ���
	String url="jdbc:mysql://localhost:3306/"+dbName+"?user="+userName+"&password="+userPasswd;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection connection=DriverManager.getConnection(url);
	Statement statement = connection.createStatement();
	String sql="SELECT * FROM "+tableName;
	ResultSet rs = statement.executeQuery(sql);
	//������ݽ������
	ResultSetMetaData rmeta = rs.getMetaData();
	//ȷ�����ݼ������������ֶ���
	int numColumns=rmeta.getColumnCount();
	// ���ÿһ������ֵ
	out.print("<table align='center' border='1' width='600px' id='tablelist'> <tr> <th>");
	out.print("ID");
	out.print("</th><th>");
	out.print("�û���");
	out.print("</th><th>");
	out.print("����");
	out.print("</th><th>");
	out.print("�ֻ���");
	out.print("</th><th>����</th></tr>");
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
			+"<input type='text' id='newpwd"+i+"' placeholder='����������' value=''>"+"   "
			+"<input type='button' value='�޸�����' onClick='subReset("+i+")' ></form>"+"   "
			+"<input type='button' value='ɾ���û�' onClick='subDel("+i+")'></form></td>");
		out.print("</tr>");
		i++;
	}
	out.print("</table>");
	out.print("<br/>");
	out.print("<form method='POST' action='resetPassword' name='ResetPass' autocomplete='off'><input type='hidden' value='' id='user1' name='user1'/><input type='hidden' value='' id='newpass' name='newpass'/></form>");
	out.print("<form method='POST' action='deleteUser' name='DelUser'><input type='hidden' value='' id='user2' name='user2'/><form>");
	out.print("���ݿ�����ɹ�����ϲ��");
	rs.close();
	statement.close();
	connection.close();
%>
<a href="Login.html">���ص�¼</a>
<script>
function subReset(id){
	var tableId = document.getElementById("tablelist"); 
	var re=tableId.rows[id].cells[1].innerHTML;	
	var newpwd="newpwd"+id;	
	var pwd=document.getElementById(newpwd).value;
	document.getElementById('user1').value=re;
	document.getElementById('newpass').value=pwd;
	if(re == 'admin'){
		alert("���ǹ���ԱŶ,�����޸�!");
	}else{
		if(pwd.length>0){
			if(pwd.length>=6 && pwd.length<=8){
				alert("�������޸�Ϊ"+pwd);
				document.ResetPass.submit();
			}
			else{
				alert("���������6~8λ֮��!");
			}			
		}else{
			alert("�����벻��Ϊ��");
		}
	}
}
function subDel(id){
	var result = confirm("��ȷ����");
	if(result == true){
		var tableId = document.getElementById("tablelist"); 
		var re=tableId.rows[id].cells[1].innerHTML;
	 	if(re == 'admin'){
	 		alert("���ǹ���ԱŶ,�����޸�!");
	 	}else{
			alert("ɾ��"+re);  
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