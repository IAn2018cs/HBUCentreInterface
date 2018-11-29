package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.FeedbackDB;
import model.Feedback;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/feedback.do")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String defAccount = request.getParameter("account");
		String account = new String(defAccount.getBytes("iso8859-1"), "utf-8");

		String defMsg = request.getParameter("msg");
		String msg = new String(defMsg.getBytes("iso8859-1"), "utf-8");
		
		String defPhoneBrand = request.getParameter("PhoneBrand");
		String phoneBrand = new String(defPhoneBrand.getBytes("iso8859-1"), "utf-8");
		
		String defPhoneBrandType = request.getParameter("PhoneBrandType");
		String phoneBrandType = new String(defPhoneBrandType.getBytes("iso8859-1"), "utf-8");
		
		String defAndroidVersion = request.getParameter("AndroidVersion");
		String androidVersion = new String(defAndroidVersion.getBytes("iso8859-1"), "utf-8");
		
		String anonymous = new String(request.getParameter("Anonymous").getBytes("iso8859-1"), "utf-8");
		
		boolean flag = false;

		FeedbackDB feedbackDB = new FeedbackDB();
		Feedback feedback = new Feedback(account,msg,phoneBrand,phoneBrandType,androidVersion,Integer.valueOf(anonymous));
		flag = feedbackDB.upFeedback(feedback);
		feedbackDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sucessed", flag);
	
		out.write(jsonObject.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
