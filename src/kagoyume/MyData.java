package kagoyume;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyData
 */
@WebServlet("/MyData")
public class MyData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyData() {
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
		try {
			UserData ud = new UserData();
//			ログインする時にセッションに格納したnameを使って
//			データベースに検索をかけてユーザーデータを取得
			ud.setName(session.getAttribute("name").toString());
			UserDataDTO uddto  = new UserDataDTO();
			uddto = UserDataDAO.getInstance().getUserData(ud);
			ud = ud.userDataMapping(uddto);
			session.setAttribute("userdata", ud);
			request.getRequestDispatcher("mydata.jsp").forward(request,response);
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
