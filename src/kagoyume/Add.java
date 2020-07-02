package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			if(request.getAttribute("login")==null) {
			List<ItemData> cartList = new ArrayList<ItemData>();
			if(session.getAttribute("cartlist")!=null) {
				cartList = (ArrayList<ItemData>)session.getAttribute("cartlist");
			}
//			カートの中身をArrayListで管理
			cartList.add((ItemData)session.getAttribute("oneitem"));
			session.setAttribute("cartlist", cartList);
			}
			response.sendRedirect("add.jsp");
		}catch(NullPointerException ne) {
			request.setAttribute("error",ne.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
