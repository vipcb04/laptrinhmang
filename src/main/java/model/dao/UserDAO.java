package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.User;

public class UserDAO {
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DULIEU","root","");
		return con;
		
	}
	public List<User> getallUser()
	{
		List<User> users= new ArrayList<User>();
		try
		{
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select * from user";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	

				users.add(mapResultSetToUser(rs));
			}
			rs.close();
			con.close();
			
		}catch(Exception ex)
		
		{
			ex.printStackTrace();
		}
		
		
		return users;
	}
	public void insertUser(User user)
	{
		try
		{
			Connection con = getConnection();
			
			String sql = "insert into user(username,email,password) value (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
			ps.close();
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void deleteUser(int id)
	{
		try
		{	
			
			Connection con = getConnection();
			String sql = "delete from user where id = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			con.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void updateUser(User user) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "update user set username = ? email = ? password = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getId());
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}
	public User getUser(int id)
	{
		User user = null;
		try
		{	
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select * from user where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
		
			ResultSet rs = ps.executeQuery();
			while(!rs.next())
			{	
				user = mapResultSetToUser(rs);
			}
			
			ps.close();
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	public int getIDbyUsername(String username) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		int id = -1;
		String sql = "select id from user where username = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			id = rs.getInt("id");
		}
		return id;
	}
	private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }

}
