package kagoyume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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

		String target = request.getParameter("searchword");
		String appid = "dj00aiZpPXhsSzRNR211c05GWCZzPWNvbnN1bWVyc2VjcmV0Jng9MGY-";
		String encodedResult = URLEncoder.encode(target, "UTF-8");

		try {
			if(request.getParameter("searchword").equals("")) {
				throw new Exception("検索ワードが入力されていません");
			}else {
//				検索結果に戻るためにセッションに格納

				session.setAttribute("searchword", request.getParameter("searchword"));
			}
//			リクエストURL
			String url = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid="+appid+"&query="+encodedResult;
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
			List<ItemData> itemList = new ArrayList<ItemData>();
			int totalHit = Integer.parseInt(root.get("ResultSet").get("totalResultsAvailable").textValue());

			if(totalHit==0) {
				throw new Exception("検索結果が0件です");

			}else {
			if(totalHit>20) {
				totalHit = 20;
			}
			for(int i = 0; i < totalHit; i++) {
		        String hitNum = String.valueOf(i);
		        String itemCode = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Code").textValue();
		        String imageURL = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Image").get("Small").textValue();
		        String name = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Name").textValue();
		        String price = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Price").get("_value").textValue();

		        ItemData itemData = new ItemData();
		        itemData.setHitNum(hitNum);
		        itemData.setName(name);
		        itemData.setPrice(price);
		        itemData.setItemCode(itemCode);
		        itemData.setImageURL(imageURL);
		        itemList.add(itemData);
			}
			session.setAttribute("itemlist", itemList);
			request.getRequestDispatcher("search.jsp").forward(request,response);
			}
		}catch(Exception ne) {
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
