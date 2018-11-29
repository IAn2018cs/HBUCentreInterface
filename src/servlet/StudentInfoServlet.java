package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import db.LoginDB;

/**
 * Servlet implementation class GetStudentInfoServlet
 */
@WebServlet("/getInfo.do")
public class StudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String defNumber = request.getParameter("Account");
		String number = new String(defNumber.getBytes("iso8859-1"), "utf-8");
		
		LoginDB loginDB = new LoginDB();
		String name = loginDB.getName(number);
		loginDB.close();
		
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		if (!name.equals("")) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", name);
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("data", "获取信息失败");
		}

		out.write(jsonObject.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
