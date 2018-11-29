package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoanDB;
import model.Loan;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetActivityServlet
 */
@WebServlet("/getLoanList.do")
public class GetLoanListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLoanListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String account = new String(request.getParameter("Account").getBytes("iso8859-1"), "utf-8");
	
		LoanDB loanDB = new LoanDB();
		List<Loan> loanList = loanDB.getLoanList(account);
		loanDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		if (loanList.size() > 0) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", loanList);			
			jsonObject.put("msg", "查询成功");			
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("msg", "查询失败");						
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
