package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UpdateDB;
import model.Update;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/changeUpdateInfo.do")
public class ChangeUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUpdateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String appDescribe = new String(request.getParameter("appDescribe").getBytes("iso8859-1"), "utf-8");
		String appVersion = new String(request.getParameter("appVersion").getBytes("iso8859-1"), "utf-8");
		String appUrl = new String(request.getParameter("appUrl").getBytes("iso8859-1"), "utf-8");
		String showScansSensor = new String(request.getParameter("showScansSensor").getBytes("iso8859-1"), "utf-8");

		boolean flag = false;

		UpdateDB updateDB = new UpdateDB();
		Update update = new Update();
		update.setAppDescribe(appDescribe);
		update.setAppVersion(Integer.parseInt(appVersion));
		update.setAppUrl(appUrl);
		update.setShowScansSensor(Integer.parseInt(showScansSensor));
		flag = updateDB.changeUpdateInfo(update);
		updateDB.close();
		
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
