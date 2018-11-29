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
import model.BackDuty;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetActivityServlet
 */
@WebServlet("/addBackDuty.do")
public class SetBackDutyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetBackDutyServlet() {
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
		String time = new String(request.getParameter("time").getBytes("iso8859-1"), "utf-8");
		String endtime = new String(request.getParameter("endtime").getBytes("iso8859-1"), "utf-8");
		String account = new String(request.getParameter("account").getBytes("iso8859-1"), "utf-8");
		String week = new String(request.getParameter("week").getBytes("iso8859-1"), "utf-8");
		String studentNum = new String(request.getParameter("studentNum").getBytes("iso8859-1"), "utf-8");
		String backActiveID = new String(request.getParameter("BackActiveID").getBytes("iso8859-1"), "utf-8");
		String leaveTime = new String(request.getParameter("LeaveTime").getBytes("iso8859-1"), "utf-8");
		String leaveActiveID = new String(request.getParameter("LeaveActiveID").getBytes("iso8859-1"), "utf-8");
	
		Activity activity = new Activity();
		activity.setActivityName(activityname);
		activity.setTime(time);
		activity.setEndTime(endtime);
		activity.setAccount(account);
		activity.setWeek(Integer.valueOf(week));
		activity.setStudentNum(studentNum);
		
		BackDuty backDuty = new BackDuty(studentNum,leaveTime,Integer.valueOf(leaveActiveID),time,Integer.valueOf(backActiveID));
		
		ActivityDB activityDB = new ActivityDB();
		boolean flag = activityDB.addBackDuty(activity,backDuty);
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
