package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoginDB;
import model.User;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/signUp.do")
public class SinUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String studentNum = new String(request.getParameter("studentNum").getBytes("iso8859-1"), "utf-8");
		String password = new String(request.getParameter("password").getBytes("iso8859-1"), "utf-8");
		String name = new String(request.getParameter("name").getBytes("iso8859-1"), "utf-8");
		String grade = new String(request.getParameter("grade").getBytes("iso8859-1"), "utf-8");
		String clas = new String(request.getParameter("class").getBytes("iso8859-1"), "utf-8");
		String groupCode = new String(request.getParameter("groupCode").getBytes("iso8859-1"), "utf-8");
		String phone = new String(request.getParameter("Phone").getBytes("iso8859-1"), "utf-8");
		
		boolean flag = false;

		LoginDB loginDB = new LoginDB();
		User user = new User(studentNum,name, Integer.valueOf(grade), clas,Integer.valueOf(groupCode),phone);
		flag = loginDB.signUp(studentNum, password, user);
		loginDB.close();

		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("sucessed", flag);
		if (flag) {
			jsonObject.put("msg", "注册成功");
		} else {
			jsonObject.put("msg", "注册失败");
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
