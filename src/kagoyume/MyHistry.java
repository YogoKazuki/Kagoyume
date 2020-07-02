package kagoyume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class MyHistry
 */
@WebServlet("/MyHistry")
public class MyHistry extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHistry() {
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
			UserDataDTO uddto = new UserDataDTO();
			ud = (UserData)session.getAttribute("userdata");
			uddto = ud.userDataMapping(ud);
			List<UserDataDTO> itemCodeList = new ArrayList<UserDataDTO>();
			itemCodeList = UserDataDAO.getInstance().purchaseHistory(uddto);
			List<ItemData> itemList = new ArrayList<ItemData>();

			String appid = "dj00aiZpPXhsSzRNR211c05GWCZzPWNvbnN1bWVyc2VjcmV0Jng9MGY-";


			for(int i=0;i<itemCodeList.size();i++) {
				String target = itemCodeList.get(i).getItemCode();
				String encodedResult = URLEncoder.encode(target, "UTF-8");
	//			リクエストURL
				String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid="+appid+"&itemcode="+encodedResult+"&responsegroup=medium";
	//			API接続用のURLを作成
				URL con_URL = new URL(url);
	//			API接続用のURLコネクションの作成
				HttpURLConnection urlc = (HttpURLConnection)con_URL.openConnection();
				urlc.connect();
	//			APIから返却されたJSON文字列を取得
				BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
				String json="";
				String receiver="";
				while((receiver = br.readLine())!=null) {
					json+=receiver;
				}
				br.close();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(json);

				String imageURL = root.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").textValue();
		        String name = root.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue();
		        String price = root.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").textValue();
		        String description = root.get("ResultSet").get("0").get("Result").get("0").get("Description").textValue();
		        String score = root.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").textValue();

			    ItemData itemData = new ItemData();
			    itemData.setName(name);
			    itemData.setPrice(price);
			    itemData.setImageURL(imageURL);
			    itemData.setDescription(description);
			    itemData.setScore(score);
			    itemData.setType(itemCodeList.get(i).getType());
			    itemList.add(itemData);
				}
			session.setAttribute("purchaseHistory",itemList);
			request.getRequestDispatcher("myhistry.jsp").forward(request,response);
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
