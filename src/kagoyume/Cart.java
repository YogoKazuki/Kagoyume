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
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try{
//			カートから削除がクリックされていればその配列のインデックスが送信されるのでそこから削除
			if(request.getParameter("index")!=null) {
				List<ItemData> itemList = new ArrayList<ItemData>();
				itemList = (List<ItemData>)session.getAttribute("cartlist");
				itemList.remove(Integer.parseInt(request.getParameter("index")));
				session.setAttribute("cartList",itemList);
			}

//			ログインしていれば買い物かごページへ、していなければログインページへ
			if(session.getAttribute("check")=="no") {
				request.getRequestDispatcher("Login").forward(request, response);
			}else {
				response.sendRedirect("cart.jsp");
			}

		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
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
