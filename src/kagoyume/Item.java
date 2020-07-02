package kagoyume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class Item
 */
@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
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

			String target = request.getParameter("itemcode");
			String appid = "dj00aiZpPXhsSzRNR211c05GWCZzPWNvbnN1bWVyc2VjcmV0Jng9MGY-";
			String encodedResult = URLEncoder.encode(target, "UTF-8");

//			リクエスト用URLを作成
			String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid="+appid+"&itemcode="+encodedResult+"&responsegroup=medium";
//			API接続用のURLを作成
			URL con_URL = new URL(url);
//			API接続用のURLコネクションを作成
			HttpURLConnection urlc = (HttpURLConnection)con_URL.openConnection();
			urlc.connect();
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
	        itemData.setItemCode(target);
	        itemData.setName(name);
	        itemData.setPrice(price);
	        itemData.setImageURL(imageURL);
	        itemData.setDescription(description);
	        itemData.setScore(score);
	        session.setAttribute("oneitem",itemData);
	        request.getRequestDispatcher("item.jsp").forward(request,response);

		}catch(NullPointerException ne) {
			System.out.println(ne.getMessage());
			request.setAttribute("erroe", ne.getMessage());
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
