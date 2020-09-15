package dao;

import java.sql.PreparedStatement;

import entity.ResultInformation;
public class ResultInformationDao {
private static ResultInformationDao resultInformationDao;
	
	public static ResultInformationDao getResultInformationDao()
	{
		if(resultInformationDao == null)
		{
			resultInformationDao = new ResultInformationDao();
		}
		
		return resultInformationDao;
	}
	
	public boolean insert(ResultInformation resultInformation)
	{
		String sql = "INSERT INTO `try`.`result_information`(`id`, `questionnaire_id`, `report_time`) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setInt(1, resultInformation.getId());
			stmt.setString(2, resultInformation.getUserIP());
			stmt.setInt(3, resultInformation.getQuestionnaire_id());
			stmt.setString(4, resultInformation.getReportTime());
		
			return stmt.executeUpdate()>0;//返回更新的行数
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
