package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ActivityDB;
import model.Activity;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetActivityServlet
 */
@WebServlet("/addActivity.do")
public class SetActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String activityname = new String(request.getParameter("activityname").getBytes("iso8859-1"), "utf-8");
		String activitydec = new String(request.getParameter("activitydec").getBytes("iso8859-1"), "utf-8");
		String sensorid = new String(request.getParameter("sensorid").getBytes("iso8859-1"), "utf-8");
		String time = new String(request.getParameter("time").getBytes("iso8859-1"), "utf-8");
		String endtime = new String(request.getParameter("endtime").getBytes("iso8859-1"), "utf-8");
		String location = new String(request.getParameter("location").getBytes("iso8859-1"), "utf-8");
		String rule = new String(request.getParameter("rule").getBytes("iso8859-1"), "utf-8");
		String account = new String(request.getParameter("account").getBytes("iso8859-1"), "utf-8");
	
		Activity activity = new Activity();
		activity.setActivityName(activityname);
		activity.setActivityDes(activitydec);
		activity.setTime(time);
		activity.setEndTime(endtime);
		activity.setLocation(location);
		activity.setRule(Integer.valueOf(rule));
		activity.setAccount(account);
		
		ActivityDB activityDB = new ActivityDB();
		boolean flag = activityDB.addActivity(activity, sensorid);
		activityDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sucessed", flag);
		if (flag) {
			jsonObject.put("msg", "添加成功");			
		} else {
			jsonObject.put("msg", "添加失败");						
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
