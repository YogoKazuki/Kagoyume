<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="kagoyume.UserData"
    %>
    <%HttpSession hs = request.getSession();
    UserData ud = new UserData();
    ud = (UserData)hs.getAttribute("userdataafter");%>
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
		<a href="myupdate.jsp">戻る</a>
		<%}else{ %>
	以上の内容で更新しますよろしいでしょうか？
<ul>
<li><%=ud.getName() %></li>
<li><%=ud.getPassword() %></li>
<li><%=ud.getMail() %></li>
<li><%=ud.getZipcode() %></li>
<li><%=ud.getAddress1() %></li>
<li><%=ud.getAddress2() %></li>
</ul>
<a href="MyUpdateResult">はい</a>
<a href="myupdate.jsp">戻る</a>
<%} %>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>