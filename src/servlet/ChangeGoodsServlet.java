package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ArticleDB;
import model.Article;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SetActivityServlet
 */
@WebServlet("/changeGoods.do")
public class ChangeGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		String id = new String(request.getParameter("ID").getBytes("iso8859-1"), "utf-8");
		String name = new String(request.getParameter("Name").getBytes("iso8859-1"), "utf-8");
		String type = new String(request.getParameter("Type").getBytes("iso8859-1"), "utf-8");
		String quantity = new String(request.getParameter("Quantity").getBytes("iso8859-1"), "utf-8");
		String price = new String(request.getParameter("Price").getBytes("iso8859-1"), "utf-8");
		String des = new String(request.getParameter("Description").getBytes("iso8859-1"), "utf-8");
		String location = new String(request.getParameter("Location").getBytes("iso8859-1"), "utf-8");
		String imageUrl = new String(request.getParameter("ImageUrl").getBytes("iso8859-1"), "utf-8");
	
		Article article = new Article();
		article.setNid(Integer.parseInt(id));
		article.setName(name);
		article.setType(Integer.parseInt(type));
		article.setQuanutity(Integer.parseInt(quantity));
		article.setPrice(Double.parseDouble(price));
		article.setDescription(des);
		article.setLocation(location);
		article.setImageUrl(imageUrl);
		
		ArticleDB articleDB = new ArticleDB();
		boolean flag = articleDB.changeGoods(article);
		articleDB.close();
		
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
