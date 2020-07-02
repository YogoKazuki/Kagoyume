package kagoyume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class RegistrationConfirm
 */
@WebServlet("/RegistrationConfirm")
public class RegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationConfirm() {
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
//			郵便番号で住所を検索する処理をしてフォームへ戻る
			if(request.getParameter("code")!=null) {
				UserData ud = new UserData();
				if(!request.getParameter("name").equals("")) {
					ud.setName(request.getParameter("name"));
				}
				if(!request.getParameter("mail").equals("")) {
					ud.setMail(request.getParameter("mail"));
				}
				if(!request.getParameter("zipcode").equals("")) {
					ud.setZipcode(request.getParameter("zipcode"));
				}
//				リクエストURL
				String useURL = "https://zip-cloud.appspot.com/api/search"+ "?zipcode=" + ud.getZipcode();
//				API接続用のURLを作成
				URL con_URL = new URL(useURL);
//				API接続用のURLコネクションの作成
				HttpURLConnection urlc = (HttpURLConnection)con_URL.openConnection();
				urlc.connect();
//				APIから返却されたJSON文字列を取得
				BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
				String json="";
				String receiver="";
				while((receiver = br.readLine())!=null) {
					json+=receiver;
				}
				br.close();

				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(json);
				if(root.get("results").get(0)==null) {
					request.setAttribute("nondata","0");
				}else {
//			郵便番号検索に成功したらJsonNode型のgetメソッドを用いて値を抽出
					String search_zipcode = root.get("results").get(0).get("zipcode").textValue();
					String address1 = root.get("results").get(0).get("address1").textValue();
					String address2 = root.get("results").get(0).get("address2").textValue();
					String address3 = root.get("results").get(0).get("address3").textValue();

					ud.setAddress1(address1+address2+address3);
					session.setAttribute("ud", ud);
				}
				request.getRequestDispatcher("registration.jsp").forward(request,response);
			}
//			送信ボタンを押したらフォームから送信された値をUserDataに格納しフォワード
			if(request.getParameter("submit")!=null) {
				UserData ud = new UserData();
				ud.setName(request.getParameter("name"));
				ud.setPassword(request.getParameter("password"));
				ud.setMail(request.getParameter("mail"));
				ud.setZipcode(request.getParameter("zipcode"));
				ud.setAddress1(request.getParameter("address1"));
				ud.setAddress2(request.getParameter("address2"));
				session.setAttribute("ud", ud);
				request.getRequestDispatcher("registration_confirm.jsp").forward(request, response);
			}

		}catch(NullPointerException ne) {
			request.setAttribute("error", ne.getMessage());
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
