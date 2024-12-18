package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Documents;

public class DocumentDAO {
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{	
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DULIEU","root","");
		return con;
		
	}
	public Documents getDocumentbyID(int id)
	{
		Documents document = null;
		try
		{
			Connection con = getConnection();
			String sql = "select * from document where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				document = mapResultSettoDocument(rs);
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return document;
	}
	public List<Documents> getDocumentbyUserID(int userid)
	{
		List<Documents> document = new ArrayList<Documents>();
		try
		{
			Connection con = getConnection();
			String sql = "select * from document where user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{	
				System.out.println(rs.getString("file_name"));
				document.add(mapResultSettoDocument(rs)) ;
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return document;
	}
	public int saveDocument(Documents document)
	{int id = -1;
		try
		{
			Connection con = getConnection();
			String sql = "insert into document(user_id,file_name,input_path,output_path,status)"
					+ " values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, document.getUserId());
			ps.setString(2, document.getFileName());
			ps.setString(3, document.getInputPath());
			ps.setString(4, document.getOutputPath());
			
			ps.setString(5, document.getStatus());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				id = rs.getInt(1);
			}
			ps.close();
			con.close();
			System.out.println("Da them vao co so du lieu");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return id;
	}
	public void updateStatus(int docId, String status) throws SQLException, ClassNotFoundException
	{
		Connection con = getConnection();
		String sql = "UPDATE document SET status = ?  WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, status);
		ps.setInt(2, docId);
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	public void updateDocument(int docId, String status, String outputPath) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "UPDATE document SET status = ?, output_path = ?  WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, status);
		ps.setString(2, outputPath);
		
		ps.setInt(3, docId);
		System.out.println(status);
		System.out.println(docId);
		System.out.println(outputPath);
		
		ps.executeUpdate();
		
		System.out.println("Da cap nhat co so du lieu");
		ps.close();
		con.close();
	}
	public void deleteDocument(int docID) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		String sql = "delete from document where id= ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, docID);
		ps.executeUpdate();
		System.out.println("Da cap nhat co so du lieu");
		ps.close();
		con.close();
		
	}
	
	public Documents mapResultSettoDocument(ResultSet rs) throws SQLException
	{
		Documents document = new Documents();
		document.setId(rs.getInt("id"));
		document.setFileName(rs.getString("file_name"));
		document.setInputPath(rs.getString("input_path"));
		document.setOutputPath(rs.getString("output_path"));
		
		document.setStatus(rs.getString("status"));
		return document;
	}

}
