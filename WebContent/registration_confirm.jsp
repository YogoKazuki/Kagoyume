<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.HttpSession"
    import="kagoyume.UserData"%>

    <%UserData ud = new UserData();
    HttpSession hs = request.getSession();
    ud = (UserData)hs.getAttribute("ud"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>確認画面</title>
</head>
<body>
<header >
<div class="logo mx-3"><a href="top.jsp">かごゆめ</a></div>
<div><form class="form-inline my-3 mx-3 text-center" action="Search" method="get">
          <input class="form-control " type="text" placeholder="商品検索" name="searchword">
          	<span class="input-group-btn">
          		<button class="btn btn-outline-success  " type="submit">検索
          		</button>
          	</span>
         </form></div>
<div class=" text-center my-3 mx-3 p-0"><a href="Cart" >買い物かご</a></div>
<%if(hs.getAttribute("check")=="no"){ %>
			<div class=" text-center my-3 mx-3 p-0">
			<a href="Login" class="login"><button type="button" class="btn btn-primary">ログイン</button></a>
			</div>
			<%}else if(hs.getAttribute("check")=="ok"){ %>
			<div class="text-center my-3 mx-3 p-0">
				<a href="Login" class="login"><button type="button" class="btn btn-secondary">ログアウト</button></a>
				</div>
				<div class="my-3 mx-3 p-0">ようこそ<a href="MyData"><%=hs.getAttribute("name")%></a>さん</div>
			<%} %>
</header>
<%if(ud.getName().equals("")||ud.getPassword().equals("")||ud.getMail().equals("")||
		ud.getZipcode().equals("")||ud.getAddress1().equals("")){ %>
	<%if(ud.getName().equals("")){%>
	  	ユーザーIDが入力されていません<br><%} %>
	<% if(ud.getPassword().equals("")){%>
		パスワードが入力されていません<br><%} %>
	<% if(ud.getMail().equals("")){%>
		メールアドレスが入力されていません<br><%} %>
	<% if(ud.getZipcode().equals("")||ud.getAddress1().equals("")){%>
		住所が入力されていません<br><%} %>
	<a href="registration.jsp">戻る</a>
<%}else{%>
間違いがなければ「はい」訂正する場合は「いいえ」を押してください<br>
名前:<%=ud.getName() %><br>
メールアドレス:<%=ud.getMail() %><br>
住所:<%=ud.getZipcode()%><br>
<%=ud.getAddress1() %><br>
<%=ud.getAddress2() %><br>
<a href="RegistrationComplete"><button>はい</button></a>
<a href="registration.jsp"><button>いいえ</button></a>
<%} %>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>