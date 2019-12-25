package exam;

import java.sql.*;

public class DBoper {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//获取数据库连接
	
	public Connection getConn(String server,String dbname,String user,String pwd) 
			throws ClassNotFoundException,SQLException,InstantiationException, IllegalAccessException{
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://"+server+":3306/"+dbname+"?user="+user+"&password="+pwd+"&useUnicode=true&characterEncoding=utf8";
		//注册驱动
		Class.forName(DRIVER).newInstance();
		//获得数据库连接
		conn = DriverManager.getConnection(URL);
		//返回连接
		return conn;
	}
	
	//释放资源
	public void closeAll(){
		try{
			//如果rs不空，关闭rs
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				//如果pstmt不空,关闭pstmt
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
		}
	}
	//进行SQL查询
	public ResultSet executeQuery(String preparedSql,String[] param){
		//处理SQL,执行SQL
		try{
			//得到PreparedStatement对象
			pstmt = conn.prepareStatement(preparedSql);
			if(param != null){
				for(int i = 0;i < param.length;i++){
					//为预编译sql设置参数
					pstmt.setString(i+1, param[i]);
				}
			}
			//执行SQL语句
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			//处理异常
			e.printStackTrace();
		}
		return rs;
	}
	//进行SQL增删改查
	public int executeUpdate(String preparedSql,String[] param){
		int num = 0;
		//处理SQL,执行SQL
		try{
			//得到PreparedStatement对象
			pstmt = conn.prepareStatement(preparedSql);
			if(param != null){
				for(int i = 0;i < param.length;i++){
					//为预编译sql设置参数
					pstmt.setString(i+1, param[i]);
				}
			}
			//执行SQL语句
			num = pstmt.executeUpdate();
		}catch(SQLException e){
			//处理异常
			e.printStackTrace();
		}
		return num;
	}
}
