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
@WebServlet("/changeActivity.do")
public class ChangeActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String nid = new String(request.getParameter("Nid").getBytes("iso8859-1"), "utf-8");
		String activityname = new String(request.getParameter("ActivityName").getBytes("iso8859-1"), "utf-8");
		String activitydec = new String(request.getParameter("ActivityDescription").getBytes("iso8859-1"), "utf-8");
		String time = new String(request.getParameter("Time").getBytes("iso8859-1"), "utf-8");
		String endtime = new String(request.getParameter("EndTime").getBytes("iso8859-1"), "utf-8");
		String location = new String(request.getParameter("Location").getBytes("iso8859-1"), "utf-8");
	
		Activity activity = new Activity();
		activity.setId(Integer.valueOf(nid));
		activity.setActivityName(activityname);
		activity.setActivityDes(activitydec);
		activity.setTime(time);
		activity.setEndTime(endtime);
		activity.setLocation(location);
		
		ActivityDB activityDB = new ActivityDB();
		boolean flag = activityDB.changeActivity(activity);
		activityDB.close();
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sucessed", flag);
		if (flag) {
			jsonObject.put("msg", "修改成功");			
		} else {
			jsonObject.put("msg", "修改失败");						
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
