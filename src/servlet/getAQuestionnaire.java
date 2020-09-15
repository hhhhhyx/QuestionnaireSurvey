package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionnaireDao;
import entity.Questionnaire;

/**
 * Servlet implementation class getAQuestionnaire
 */
@WebServlet("/getAQuestionnaire")
public class getAQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAQuestionnaire() {
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
		Integer id = Integer.parseInt(request.getParameter("questionnaire_id"));
		Questionnaire questionnaire = QuestionnaireDao.getQuestionnaireDao().getAQuestionnaire(id);
		
		request.setAttribute("questionnaire", questionnaire);
		request.getRequestDispatcher("showQuestionnaire.jsp").forward(request, response);
	}

}
