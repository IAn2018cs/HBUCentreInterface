package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ActivityDB;
import model.Activity;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ActivityServlet
 */
@WebServlet("/getActivityByAccount.do")
public class GetActivityByAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetActivityByAccountServlet() {
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
		String rule = new String(request.getParameter("rule").getBytes("iso8859-1"), "utf-8");
		
		ActivityDB activityDB = new ActivityDB();
		
		List<Activity> list = activityDB.getActivity(Integer.parseInt(rule),Integer.parseInt(account));
		activityDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		if(list.size() > 0) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", list);
			jsonObject.put("msg", "查询成功");
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("msg", "无数据");
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
