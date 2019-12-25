<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>欢迎使用</title>
<style type="text/css">
.input_txt{width:398px;border:solid 1px #ccc;box-shadow:0 1px 10px rgba(0,0,0,0.1) inset;height:38px;text-indent:10px;}
.input_txt:focus{box-shadow:0 0 4px rgba(255,153,164,0.8);border:solid 1px #2894FF;}
.border_radius{border-radius:5px;color:#0A0A0A;}
a:link {color: blue} 
a:visited {color: blue} 
a:hover {color: blue} 
a:active {color: blue}
a{text-decoration:none}
</style>
<script language="javascript">
	function sub(){
		var username= document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if(username != null && password != null){
			document.Login.submit();
		}else{
			alert("请输入登陆信息");
			document.getElementById("password").value="";
		}
	}
</script>
</head>
<body align="center">
<div><img src="img9.jpg"></div>
<form method="POST" action="Login" name="Login" autocomplete="off">
<input type="text" placeholder="输入用户名" name="username" id="username" value="" style="position:relative;top:10px;width:400px;height:40px;" class="input_txt border_radius" /><br/>
<input type="password" placeholder="输入密码" name="password" id="password" value="" style="position:relative;top:20px;width:400px;height:40px;" class="input_txt border_radius"/><br/>
<div style="position:relative;top:30px;left:150px;"><a href="resetpwd.html"><font style="font-size:15px;font-weight:bold;font-family:YouYuan">忘记密码啦?</font></a></div>
<input type="button" value="登录" onClick="sub()" style="position:relative;top:40px;font-size:17px;letter-spacing:10px;background-color: #00CED1;border:1px solid #d0d0d0;color: #ffffff;width:400px;height:50px;"/><br/>
<input type="button" value="注册" onclick="javascrtpt:window.location.href='register.html'" style="position:relative;font-size:17px;letter-spacing:10px;top:45px;background-color: #ffffff;border:1px solid #d0d0d0;width:400px;height:50px;"/><br/>
<%
	int i= (int)application.getAttribute("tf");
	if(i>0){
		out.print("alert('aaa')");
	}

%>
</form>
</body>
</html>