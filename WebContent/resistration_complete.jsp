<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.HttpSession"
    import="kagoyume.UserData"%>
    <%HttpSession hs = request.getSession();
    UserData ud = new UserData();
    ud = (UserData)hs.getAttribute("ud");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>アカウントを作成しました</title>
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
以上の内容でアカウントの作成が完了いたしました。<br>
<ul>
<li>ユーザーID:<%=ud.getName() %></li>
<li>メールアドレス:<%=ud.getMail() %></li>
<li>住所:<%=ud.getZipcode() %><br><%=ud.getAddress1() %><br><%=ud.getAddress2() %></li>
</ul>
<a href="top.jsp"><button>トップへ戻る</button></a>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>