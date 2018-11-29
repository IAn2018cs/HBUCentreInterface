package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SignDB;
import model.SignInfo;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/getSignByDate.do")
public class GetSignByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSignByDateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String activeID = new String(request.getParameter("ActiveID").getBytes("iso8859-1"), "utf-8");
		String date = new String(request.getParameter("Date").getBytes("iso8859-1"), "utf-8");
		
		boolean flag = false;

		SignDB signDB = new SignDB();
		List<SignInfo> list = new ArrayList<SignInfo>();
		try {
			list = signDB.getSignInfoByTime(Integer.parseInt(activeID), date);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		signDB.close();
		
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
