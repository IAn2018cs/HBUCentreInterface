package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignDB;
import model.Sign;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SignServlet
 */
@WebServlet("/sign.do")
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String account = new String(request.getParameter("account").getBytes("iso8859-1"), "utf-8");
		String activityId = new String(request.getParameter("activityid").getBytes("iso8859-1"), "utf-8");
		String inTime = new String(request.getParameter("intime").getBytes("iso8859-1"), "utf-8");
		String outTime = new String(request.getParameter("outtime").getBytes("iso8859-1"), "utf-8");
		String inLocation = new String(request.getParameter("InLocation").getBytes("iso8859-1"), "utf-8");
	
		Sign sign = new Sign();
		sign.setStudentNum(account);
		sign.setActivityId(Integer.valueOf(activityId));
		sign.setInTime(inTime);
		sign.setOutTime(outTime);
		sign.setInLocation(inLocation);
		
		SignDB signDB = new SignDB();
		int id = signDB.signIn(sign);
		signDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		if(id > 0) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", id);
			jsonObject.put("msg", "签到成功");
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("msg", "签到失败");
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
