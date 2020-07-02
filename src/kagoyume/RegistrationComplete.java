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
 * Servlet implementation class RegistrationComplete
 */
@WebServlet("/RegistrationComplete")
public class RegistrationComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationComplete() {
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
			UserData ud = new UserData();
			ud = (UserData)session.getAttribute("ud");
			UserDataDTO dto = new UserDataDTO();
//			住所情報を連結，splitで分割できるように,をつけておく
			String address = ud.getZipcode()+","+ud.getAddress1()+","+ud.getAddress2();
			dto.setName(ud.getName());
			dto.setPassword(ud.getPassword());
			dto.setMail(ud.getMail());
			dto.setAddress(address);
			UserDataDAO.getInstance().makeAcount(dto);
			System.out.println("アカウントの作成が完了しました");

			request.getRequestDispatcher("resistration_complete.jsp").forward(request, response);
		}catch(NullPointerException ne) {
			request.setAttribute("error", ne.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,response);
		}catch(SQLException se) {
			request.setAttribute("error", se.getMessage());
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
