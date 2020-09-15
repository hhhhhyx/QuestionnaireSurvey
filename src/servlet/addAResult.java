package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionnaireDao;
import entity.Question;
import entity.Questionnaire;
import entity.ResultInformation;

/**
 * Servlet implementation class addAResult
 */
@WebServlet("/addAResult")
public class addAResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addAResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer questionnaireId = Integer.parseInt(request.getParameter("questionnaire_id"));
		Questionnaire questionnaire = QuestionnaireDao.getQuestionnaireDao().getAQuestionnaire(questionnaireId);
		List<String> results = new ArrayList<String>();
		
		for(Question question:questionnaire.getQuestions()) {
			results.add(request.getParameter(question.getId()+",result"));
		}
		
		ResultInformation resultInformation = new ResultInformation();
		resultInformation.setQuestions(questionnaire.getQuestions());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date day=new Date();
		resultInformation.setReportTime(df.format(day));
		resultInformation.setResults(results);
		resultInformation.setUserIP(request.getRemoteAddr());
		resultInformation.setQuestionnaire_id(questionnaire.getId());
		Boolean result = QuestionnaireDao.getQuestionnaireDao().addAResult(resultInformation);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result) {
			out.print("<script>alert('提交成功！');window.location.href='./main'</script>;");
		}
		else {
			out.print("<script>alert('提交失败！');window.location.href='./main'</script>;");
		}
	}

}
