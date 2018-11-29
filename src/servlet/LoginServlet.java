package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import db.LoginDB;
import model.Login;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String defNumber = request.getParameter("Account");
		String number = new String(defNumber.getBytes("iso8859-1"), "utf-8");

		String defPass = request.getParameter("Password");
		String pass = new String(defPass.getBytes("iso8859-1"), "utf-8");
		
		Login login = new Login(number, pass, 0, "" ,0,"","","");
		
		boolean flag = false;

		LoginDB loginDB = new LoginDB();
		flag = loginDB.login(login);
		loginDB.close();
		

		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		if (flag) {
			jsonObject.put("sucessed", true);
			JSONObject result = new JSONObject();
			result.put("Type", login.getType());
			result.put("Name", login.getName());
			result.put("ClassDescription", login.getClassDes());
			result.put("GradeCode", login.getGradeCode());
			result.put("NewImage", login.getNewImage());
			result.put("OldImage", login.getOldImage());
			result.put("Group", login.getGroup());
			result.put("Phone", login.getPhone());
			result.put("InterestGroupCode", login.getInterestGroupCode());
			jsonObject.put("data", result);
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("data", "登录失败");
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
