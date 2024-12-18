package model.bo;
import java.sql.SQLException;
import java.util.List;

import model.bean.User;

import model.dao.UserDAO;
public class UserBO {
	private UserDAO userDAO;
	public UserBO()
	{
		this.userDAO = new UserDAO();
	}
	public void addUser(User user)
	{
		try
		{
			userDAO.insertUser(user);
	
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public int getIDbyUsername(String username) throws ClassNotFoundException, SQLException
	{
		return userDAO.getIDbyUsername(username);
	}
	public boolean checkUser(String username,String password)
	{
		List<User> users = userDAO.getallUser();
		for (User user : users) {
			
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
				
			{	
				return true;
			}
		}
		return false;
	}

}
