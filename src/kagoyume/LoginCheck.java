package kagoyume;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	try {
//			UserDataにログインフォームの値を格納
			UserData ud = new UserData();
			ud.setName(request.getParameter("userID"));
			ud.setPassword(request.getParameter("password"));
//			ログインチェックがokならユーザーIDをセッションに格納しログインをクリックしたurlに戻る
			int login = UserDataDAO.getInstance().loginCheck(ud);
			if(login==1) {
				session.setAttribute("check","ok");
				session.setAttribute("name",request.getParameter("userID"));
//				推移元のリファラーを変数に格納
				String url = session.getAttribute("referer").toString();
//				ログインしてるアカウント用のカートを作成しもしログインしていない状態の
//				カートにアイテムが入っていればそれを代入
				List<ItemData> cartList = new ArrayList<ItemData>();
				if(session.getAttribute("cartlist")!=null) {
					List<ItemData> addList = new ArrayList<ItemData>();
					addList = (ArrayList<ItemData>)session.getAttribute("cartlist");
					cartList.addAll(addList);
					session.setAttribute("cartlist", cartList);
				}
				request.setAttribute("login", "1");
				response.sendRedirect(response.encodeURL(url));

			}else {
//				ログインに失敗した場合
				if(login==0) {
					request.setAttribute("logincheck", "no");
				}
//				削除されたアカウントでログインしようとした場合
				if(login==2) {
					request.setAttribute("logincheck", "delete");
				}
				request.getRequestDispatcher("login.jsp").forward(request,response);
			 }
		}catch(SQLException se) {
			System.out.print(se.getMessage());
			request.setAttribute("error",se.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,response);
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			request.setAttribute("error",ne.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,response);
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
