package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.qiniu.util.Auth;



/**
 * Servlet implementation class AuthServelt
 */
@WebServlet("/auth.do")
public class AuthServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServelt() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String accessKey = "RcFs5mqrosy08vcgB6d8G6Fucw1-Jp6tn6EWE3t2";
		String secretKey = "2fAya6NYGkd4fJ05R8TzvwB3DrN5vS-zL3CLkwZN";
		// String bucket = "hbucentre";
		String bucket = new String(request.getParameter("Bucket").getBytes("iso8859-1"), "utf-8");
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		System.out.println(upToken);
		
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		// 如果Token不为空，就说明成功
		if (!upToken.equals("")) {
			jsonObject.put("sucessed", true);
			jsonObject.put("data", upToken);			
		} else {
			jsonObject.put("sucessed", false);
			jsonObject.put("data", "获取Token失败");						
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
