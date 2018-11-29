package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoanDB;
import model.Loan;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/laonArticle.do")
public class LoanArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanArticleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String account = new String(request.getParameter("Account").getBytes("iso8859-1"), "utf-8");
		String articleId = new String(request.getParameter("ArticleId").getBytes("iso8859-1"), "utf-8");
		String time = new String(request.getParameter("Time").getBytes("iso8859-1"), "utf-8");
		String backTime = new String(request.getParameter("BackTime").getBytes("iso8859-1"), "utf-8");
		String handle = new String(request.getParameter("Handle").getBytes("iso8859-1"), "utf-8");
		String quantity = new String(request.getParameter("Quantity").getBytes("iso8859-1"), "utf-8");
		
		Loan loan = new Loan(account,Integer.valueOf(articleId),time,backTime,handle);
		
		LoanDB loanDB = new LoanDB();
		boolean flag = loanDB.addLoan(loan, Integer.valueOf(quantity));
		loanDB.close();

		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("sucessed", flag);
		if (flag) {
			jsonObject.put("msg", "租借成功");
		} else {
			jsonObject.put("msg", "租借失败");
		}
		
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
