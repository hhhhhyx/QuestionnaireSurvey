package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Question;
import entity.Questionnaire;
import entity.ResultInformation;
import entity.User;

public class DBUtil {

	public static Connection getConnection() throws Exception
	{
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/try3";
		String userName = "root";
		String userPWD = "root";
		
		Class.forName(driverName);
		
		Connection connection = DriverManager.getConnection(url,userName,userPWD);
		
		return connection;
	}
	
	
	public static List<Question> executeQueryOfQuestion(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setStem(rs.getString("stem"));
	            list.add(question);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static List<String> executeQueryOfOptions(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				String option = new String();
				option = rs.getString("test");
	            list.add(option);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static List<Questionnaire> executeQueryOfQuestionnaire(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				Questionnaire questionnaire = new Questionnaire();
				questionnaire.setId(rs.getInt("id"));
				questionnaire.setName(rs.getString("name"));
	            list.add(questionnaire);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static List<ResultInformation> executeQueryOfResult(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				ResultInformation resultInformation = new ResultInformation();
				resultInformation.setId(rs.getInt("id"));
				resultInformation.setUserIP(rs.getString("user_ip"));
				resultInformation.setReportTime(rs.getString("report_time"));
				resultInformation.setQuestionnaire_id(rs.getInt("questionnaire_id"));
	            list.add(resultInformation);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static List<String> executeQueryOfResults(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				String text = rs.getString("text");
	            list.add(text);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public static List<Integer> executeQueryOfResultInformationId(PreparedStatement stmt)
	{
		List list = new ArrayList();
		
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { 
				Integer id = rs.getInt("id");
	            list.add(id);
	        } 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}


	public static List<User> executeUserQuery(PreparedStatement stmt){
		List list=new ArrayList();
		
		try{
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return list;
	}
}
