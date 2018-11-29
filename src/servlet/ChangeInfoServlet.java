package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.LoginDB;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/changeInfo.do")
public class ChangeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInfoServlet() {
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
		String name = new String(request.getParameter("Name").getBytes("iso8859-1"), "utf-8");
		String gradeCode = new String(request.getParameter("GradeCode").getBytes("iso8859-1"), "utf-8");
		String classDes = new String(request.getParameter("ClassDescription").getBytes("iso8859-1"), "utf-8");
		String group = new String(request.getParameter("Group").getBytes("iso8859-1"), "utf-8");
		String phone = new String(request.getParameter("Phone").getBytes("iso8859-1"), "utf-8");
		String imageUrl = new String(request.getParameter("ImageUrl").getBytes("iso8859-1"), "utf-8");

		boolean flag = false;

		LoginDB loginDB = new LoginDB();
		flag = loginDB.changeInfo(account, name, Integer.valueOf(gradeCode), classDes, Integer.valueOf(group),phone,imageUrl);
		loginDB.close();
		
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
