package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoginDB;
import model.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/getUserListByGroup.do")
public class GetUserListByGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserListByGroupServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String group = new String(request.getParameter("Group").getBytes("iso8859-1"), "utf-8");
		String grade = new String(request.getParameter("Grade").getBytes("iso8859-1"), "utf-8");
		
		boolean flag = false;

		LoginDB loginDB = new LoginDB();
		List<User> list = loginDB.getUserByGroup(Integer.valueOf(group),Integer.valueOf(grade));
		loginDB.close();
		
		if(list.size() > 0) {
			flag = true;
		}
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sucessed", flag);
		jsonObject.put("data", list);
	
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
