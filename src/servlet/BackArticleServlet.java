package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoanDB;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/backArticle.do")
public class BackArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackArticleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String loanId = new String(request.getParameter("LoanId").getBytes("iso8859-1"), "utf-8");
		String articleId = new String(request.getParameter("ArticleId").getBytes("iso8859-1"), "utf-8");
		String actualBackTime = new String(request.getParameter("ActualBackTime").getBytes("iso8859-1"), "utf-8");
		
		LoanDB loanDB = new LoanDB();
		boolean flag = loanDB.backArticle(Integer.valueOf(loanId), Integer.valueOf(articleId), actualBackTime);
		loanDB.close();

		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("sucessed", flag);
		if (flag) {
			jsonObject.put("msg", "归还成功");
		} else {
			jsonObject.put("msg", "归还失败");
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
