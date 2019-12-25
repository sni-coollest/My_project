package exam;

import java.sql.*;

public class DBoper {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//��ȡ���ݿ�����
	
	public Connection getConn(String server,String dbname,String user,String pwd) 
			throws ClassNotFoundException,SQLException,InstantiationException, IllegalAccessException{
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://"+server+":3306/"+dbname+"?user="+user+"&password="+pwd+"&useUnicode=true&characterEncoding=utf8";
		//ע������
		Class.forName(DRIVER).newInstance();
		//������ݿ�����
		conn = DriverManager.getConnection(URL);
		//��������
		return conn;
	}
	
	//�ͷ���Դ
	public void closeAll(){
		try{
			//���rs���գ��ر�rs
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				//���pstmt����,�ر�pstmt
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
	//����SQL��ѯ
	public ResultSet executeQuery(String preparedSql,String[] param){
		//����SQL,ִ��SQL
		try{
			//�õ�PreparedStatement����
			pstmt = conn.prepareStatement(preparedSql);
			if(param != null){
				for(int i = 0;i < param.length;i++){
					//ΪԤ����sql���ò���
					pstmt.setString(i+1, param[i]);
				}
			}
			//ִ��SQL���
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			//�����쳣
			e.printStackTrace();
		}
		return rs;
	}
	//����SQL��ɾ�Ĳ�
	public int executeUpdate(String preparedSql,String[] param){
		int num = 0;
		//����SQL,ִ��SQL
		try{
			//�õ�PreparedStatement����
			pstmt = conn.prepareStatement(preparedSql);
			if(param != null){
				for(int i = 0;i < param.length;i++){
					//ΪԤ����sql���ò���
					pstmt.setString(i+1, param[i]);
				}
			}
			//ִ��SQL���
			num = pstmt.executeUpdate();
		}catch(SQLException e){
			//�����쳣
			e.printStackTrace();
		}
		return num;
	}
}
