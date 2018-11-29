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
import model.User;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/getLoadUser.do")
public class GetLoadUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLoadUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String id = new String(request.getParameter("GoodsID").getBytes("iso8859-1"), "utf-8");
		String status = new String(request.getParameter("Status").getBytes("iso8859-1"), "utf-8");
		
		LoanDB loanDB = new LoanDB();
		List<User> userList = loanDB.getLoanUser(Integer.parseInt(id), Integer.parseInt(status));
		loanDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		if (userList.size() > 0) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", userList);			
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
