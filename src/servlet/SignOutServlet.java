package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignDB;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SignServlet
 */
@WebServlet("/signout.do")
public class SignOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String nid = new String(request.getParameter("nid").getBytes("iso8859-1"), "utf-8");
		String outtime = new String(request.getParameter("outtime").getBytes("iso8859-1"), "utf-8");
		String outLocation = new String(request.getParameter("OutLocation").getBytes("iso8859-1"), "utf-8");
		
		SignDB signDB = new SignDB();
		boolean flag = signDB.signOut(Integer.valueOf(nid), outtime,outLocation);
		signDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sucessed", flag);
		
		if(flag) {
			jsonObject.put("msg", "签离成功");
		} else {
			jsonObject.put("msg", "签离失败");
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
