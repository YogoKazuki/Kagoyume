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
 * Servlet implementation class MyDeleteResult
 */
@WebServlet("/MyDeleteResult")
public class MyDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDeleteResult() {
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
			ud = (UserData)session.getAttribute("userdata");
			UserDataDTO uddto = new UserDataDTO();
			uddto = ud.userDataMapping(ud);
			UserDataDAO.getInstance().delete(uddto);
			session.invalidate();
			response.sendRedirect("mydeleteresult.jsp");
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
