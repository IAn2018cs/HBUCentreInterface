package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.RepairDB;
import model.Repair;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetActivityServlet
 */
@WebServlet("/addRepair.do")
public class SetRepairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRepairServlet() {
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
		String article = new String(request.getParameter("Article").getBytes("iso8859-1"), "utf-8");
		String handle = new String(request.getParameter("Handle").getBytes("iso8859-1"), "utf-8");
		String time = new String(request.getParameter("Time").getBytes("iso8859-1"), "utf-8");
		String description = new String(request.getParameter("Description").getBytes("iso8859-1"), "utf-8");
	
		Repair repair = new Repair(account,article,handle,time,description);
		
		RepairDB repairDB = new RepairDB();
		boolean flag = repairDB.addRepair(repair);
		repairDB.close();
		
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
