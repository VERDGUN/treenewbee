package com.treenewbee.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class JdbcTool {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	/**
	 * 数据库连接
	 *  mysql: jdbc:mysql://localhost:3306/
	 * oracle: oracle:thin:@localhost:1521:
	 */
	private final String url = "jdbc:oracle:thin:@49.140.71.152:1521:TreeNewBee";
	/** 用户名 */
	private final String userName = "sm_manager";
	/** 用户密码 */
	private final String password = "manager";
	
	
	/** 数据库连接对象 */
	private Connection con;
	/** 执行sql语句的对象 */
	private Statement st;
	/** 结果集，存放数据的对象 */
	private ResultSet rs;
	
	
	
	
	
	static {
		//第一步，加载驱动
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author 钱昊
	 * @date 2013-7-9 上午10:36:20
	 */
	public JdbcTool() {
		 try {
			//第二部，创建连接
			 con = (Connection)DriverManager.getConnection(url,
				userName, password);
			 st = con.createStatement();
		 }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 }
	}
	
	/**
	 * @author 钱昊
	 * @title: update
	 * @date 2013-7-9 下午07:21:15
	 * @param sql void
	 */
	public int update(String sql) {
		try {
			st.executeUpdate(sql);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}
	/**
	 * @author 钱昊
	 * @title: query
	 * @date 2013-7-9 上午10:36:45
	 * @param sql
	 * @param n
	 * @return List<List<String>>
	 */
	public List<List<String>> query (String sql) {
		List<List<String>> tableList = new ArrayList<List<String>>();
		int column;
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsget = rs.getMetaData();
			column = rsget.getColumnCount();
			while(rs.next()) {
				List<String> rowList = new ArrayList<String>();
				for(int i = 1 ;i <= column ;i++){
					System.out.print(rs.getString(i) + "\t");
					rowList.add(rs.getString(i));
				}
				System.out.println();
				tableList.add(rowList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tableList;
	}
	
	public List<Map<String, String>> executeQuery(String sql) {
		List<Map<String, String>> rsList = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			rsList = new ArrayList<Map<String, String>>();
			int count = rsmd.getColumnCount();
			
			while(rs.next()){
				Map<String, String> rsMap = new TreeMap<String, String>();
				for(int i = 0; i < count; i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					String columnValue = rs.getString(columnLabel);
					rsMap.put(columnLabel, columnValue);
				}
				rsList.add(rsMap);
			}
			return rsList;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @author 钱昊
	 * @title: query2
	 * @date 2013-7-24 上午08:54:00
	 * @param sql
	 * @return List<String>
	 */
	public List<String> query2 (String sql) {
		List<String> tableList = new ArrayList<String>();
		int column;
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsget = rs.getMetaData();
			column = rsget.getColumnCount();
			while(rs.next()) {
				for(int i = 1 ;i <= column ;i++){
					System.out.print(rs.getString(i) + "\t");
					tableList.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tableList;
	}
	
	/**
	 * 取一个整数量
	 * @author 钱昊
	 * @title: getCode
	 * @date 2013-7-9 下午07:18:49
	 * @param sql
	 * @return int
	 */
	public int getCode (String sql) {
		int m = 0;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				m = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return m;	
	}
	public double getCode4 (String sql) {
		double m = 0;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				m = rs.getDouble(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return m;	
	}
	/**
	 * 取总的整数量
	 * @author 钱昊
	 * @title: getCode3
	 * @date 2013-7-20 上午10:32:45
	 * @param sql
	 * @return int
	 */
	public int getCode3 (String sql) {
		int vsum = 0;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				vsum  += rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return vsum;	
	}
	
	/**
	 * 取一个String量
	 * @author 钱昊
	 * @title: getCode2
	 * @date 2013-7-20 上午10:12:25
	 * @param sql
	 * @return String
	 */
	public String getCode2 (String sql) {
		String k = null;
		try {
			
			rs = st.executeQuery(sql);
			while(rs.next()) {
				k = rs.getString(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return k;	
	}
	/**
	 * @author 钱昊
	 * @title: deleteS
	 * @date 2013-7-9 下午07:18:34
	 * @param sql void
	 */
	public int deleteS (String sql) {
		try {
			st.execute(sql);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	public boolean findCode2 (String sql,String key){
		int m = 0;
		boolean flag = false;
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData mrs = rs.getMetaData();
			m = mrs.getColumnCount();
			while(rs.next()) {
					if(key.equals(rs.getString(1)) == true){
						flag = true;
						break;
						}else {
							flag =  false;
						}
					}
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return flag;
		
	}
	public boolean check (String sql,String username,String key){
		int m = 0;
		boolean flag = false;
		try {
			
			rs = st.executeQuery(sql);
			ResultSetMetaData mrs = rs.getMetaData();
			m = mrs.getColumnCount();
			while(rs.next()) {
				System.out.println("key="+key+rs.getString(3)+"name="+username+rs.getString(2));
					if(key.equals(rs.getString(3)) && username.equals(rs.getString(2))){
						flag = true;
						break;
						}else {
							flag =  false;
						}
					}
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return flag;
		
	}
	public int check2 (String sql,String username,String key,String th){
		int m = -1;
		try {
			
			rs = st.executeQuery(sql);
			ResultSetMetaData mrs = rs.getMetaData();
			m = mrs.getColumnCount();
			while(rs.next()) {
					if(th.equals(rs.getString(2)) && key.equals(rs.getString(4)) && username.equals(rs.getString(3))){
						if(th.equals("管理员")){
							m = 0;
							break;
						}else if(th.equals("收银员")){
							m = 1;
							break;
						}
						
						}else {
							m = -1;
						}
					}
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return m;
		
	}
	/**
	 * @author 钱昊
	 * @title: findCode
	 * @date 2013-7-9 下午07:39:34
	 * @param sql
	 * @param key void
	 */
	public void findCode (String sql,String key){
		int m = 0;
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData mrs = rs.getMetaData();
			m = mrs.getColumnCount();
			while(rs.next()) {
				for(int i = 1; i <= m; i++){
					if(key.equals(rs.getString(i))){
						for(int j = 1; j <= m ; j++){
							System.out.print(rs.getString(j) + "\t");
						}System.out.println();
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * @author 钱昊
	 * @title: close
	 * @date 2013-7-9 下午07:18:28 void
	 */
	public void close() {
		
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
