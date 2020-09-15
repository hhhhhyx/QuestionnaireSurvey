package dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import dao.DBUtil;
import entity.User;

public class UserDao {
	private static UserDao userDao;
	
	public static UserDao getUserDao(){
		if(userDao==null){
			userDao=new UserDao();
		}
		return userDao;
	}
	
	private List<User> getAllUser(){
		String sql="select * from user";
		List<User> users=new ArrayList<User>();
		
		try{
			PreparedStatement stmt=DBUtil.getConnection().prepareStatement(sql);
			users=DBUtil.executeUserQuery(stmt);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public boolean login(String username,String password){
		if(username!=null && !username.equals("")){
			List<User> users=new ArrayList<User>();
			users=getAllUser();
			
			for(int i=0;i<users.size();i++){
				if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password))
					return true;
			}
		}
		
		return false;
	}
	

}
