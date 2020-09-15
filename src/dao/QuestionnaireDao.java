package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Question;
import entity.Questionnaire;
import entity.ResultInformation;

public class QuestionnaireDao {
	
private static QuestionnaireDao questionnaireDao;
	
	public static QuestionnaireDao getQuestionnaireDao()
	{
		if(questionnaireDao == null)
		{
			questionnaireDao = new QuestionnaireDao();
		}
		
		return questionnaireDao;
	}
	
	/**
	 * 获得所有问卷的名字和ID
	 * @return
	 */
	public List<Questionnaire> getAllQuestionnaire() {
		String sql = "select * from questionnaire";
		List<Questionnaire> questionnaireList = new ArrayList<Questionnaire>();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			questionnaireList = DBUtil.executeQueryOfQuestionnaire(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionnaireList;
	}
	
	/**
	 * 获得一个问卷
	 * @param questionnaire
	 * @return
	 */
	public Questionnaire getAQuestionnaire(Integer questionnaireId) {
		String sql = "select * from questionnaire where id = "+questionnaireId;
		Questionnaire questionnaire = new Questionnaire();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			questionnaire = DBUtil.executeQueryOfQuestionnaire(stmt).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		questionnaire.setQuestions(getQuestions(questionnaireId));
		return questionnaire;
	}
	
	
	private List<Question> getQuestions(Integer questionnaireId){
		String sql = "select * from question where questionnaire_id = "+ questionnaireId ;
		List<Question> questionList = new ArrayList<Question>();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			questionList = DBUtil.executeQueryOfQuestion(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Question temp:questionList) {
			temp.setOptions(getOptions(temp.getId()));
		}
		
		return questionList;
	}
	
	
	private List<String> getOptions(Integer questionId){
		String sql = "select option.test from question_option left join `option` on option.id = question_option.option_id where question_id = " +questionId;
		
		List<String> optionList = new ArrayList<String>();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			optionList = DBUtil.executeQueryOfOptions(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return optionList;
	}
	
	
	public List<ResultInformation> getAllResults(){
		String sql = "select * from result_information";
        List<ResultInformation> resultInformationList = new ArrayList<ResultInformation>();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			resultInformationList = DBUtil.executeQueryOfResult(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultInformationList;
	}
	
	
	public ResultInformation getAResult(Integer resultInformationId) {
		String sql1 = "select text from result where result_information_id = "+resultInformationId;
        List<String> results = new ArrayList<String>();
        String sql2 = "select * from result_information where id = "+resultInformationId;
        ResultInformation resultInformation = new ResultInformation();
		
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql1);
			results = DBUtil.executeQueryOfResults(stmt);
			stmt = DBUtil.getConnection().prepareStatement(sql2);
			resultInformation = DBUtil.executeQueryOfResult(stmt).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultInformation.setResults(results);
		resultInformation.setQuestions(getQuestions(resultInformation.getQuestionnaire_id()));
		
		return resultInformation;
	}
	
	
	public Boolean addAResult(ResultInformation resultInformation) {
		Boolean result = false;
		String sql1 = "insert into result_information(user_ip,questionnaire_id,report_time) "
				+ " values('"+resultInformation.getUserIP()+"',"+resultInformation.getQuestionnaire_id()
				+",'"+resultInformation.getReportTime()+"')";
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql1);
			result = stmt.executeUpdate()>0;
			if(result) {
				String sql2 = "select id from result_information order by id desc LIMIT 1;";
				stmt = DBUtil.getConnection().prepareStatement(sql2);
				Integer result_information_id = DBUtil.executeQueryOfResultInformationId(stmt).get(0);
				
				for(String temp:resultInformation.getResults()) {
					String sql3 = "insert into result(result_information_id,text) "
							+ " values("+result_information_id+",'"+temp+"')";
					stmt = DBUtil.getConnection().prepareStatement(sql3);
					result = stmt.executeUpdate()>0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
