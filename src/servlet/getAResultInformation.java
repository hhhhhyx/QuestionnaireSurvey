package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionnaireDao;
import entity.Questionnaire;
import entity.ResultInformation;

/**
 * Servlet implementation class getAResultInformation
 */
@WebServlet("/getAResultInformation")
public class getAResultInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAResultInformation() {
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
		Integer resultInformationId = Integer.parseInt(request.getParameter("resultInformation_id"));
		ResultInformation resultInformation = QuestionnaireDao.getQuestionnaireDao().getAResult(resultInformationId);
		Questionnaire questionnaire = QuestionnaireDao.getQuestionnaireDao().getAQuestionnaire(resultInformation.getQuestionnaire_id());
		
		request.setAttribute("resultInformation", resultInformation);
		request.setAttribute("questionnaire", questionnaire);
		request.getRequestDispatcher("resultInformationPage.jsp").forward(request, response);
	}

}