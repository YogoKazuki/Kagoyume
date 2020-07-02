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
 * Servlet implementation class BuyComplete
 */
@WebServlet("/BuyComplete")
public class BuyComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyComplete() {
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
			HttpSession hs = request.getSession();
			int type =0;
//			配達方法が選択されていなければ確認画面へ戻る
			if(request.getParameter("type")==null) {
				session.setAttribute("nontype","1");
				response.sendRedirect("buyconfirm.jsp");
			}else {
			type = Integer.parseInt(request.getParameter("type"));
			}

		    List<ItemData> itemList = new ArrayList<ItemData>();
		    itemList = (ArrayList<ItemData>)hs.getAttribute("cartlist");
//		    購入するユーザーのUserIDが必要なため取得する処理
		    UserData ud = new UserData();
		    ud.setName(session.getAttribute("name").toString());
		    UserDataDTO userIdGetter = new UserDataDTO();
		    userIdGetter = UserDataDAO.getInstance().getUserData(ud);

//		    buy_tにデータを挿入するためにカートの全商品のitemCodeとフォームから
//		    送られてきた配達方法とUserDataからUserIDを引っ張ってきてUserDataDTO
//		    へマッピングしリストへ挿入
		    List<UserDataDTO> uddtoList = new ArrayList<UserDataDTO>();
		    for(ItemData e : itemList) {
		    	UserDataDTO uddto = new UserDataDTO();
		    	String itemCode = e.getItemCode();
		    	uddto = uddto.itemDataMapping(itemCode,userIdGetter.getUserID(),type);
		    	uddtoList.add(uddto);
		    }
//		    合計金額をuser_tに挿入する用のUserDataDTOを作成
		    UserDataDTO uddto = new UserDataDTO();
		    uddto.setTotal(Integer.parseInt(request.getParameter("total")));
		    uddto.setUserID(userIdGetter.getUserID());

		    UserDataDAO.getInstance().buy(uddtoList,uddto);
		    request.getRequestDispatcher("buycomplete.jsp").forward(request, response);
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
