<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    <%HttpSession hs = request.getSession(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>ログインページ</title>
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
<%if(hs.getAttribute("check")=="no"||hs.getAttribute("check")==null){ %>
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
<h1>ログインページ</h1>
<%if(request.getAttribute("logincheck")=="no"){ %>
<p><font color="red">IDかパスワードが間違っています。</font></p>
<% }else if(request.getAttribute("logincheck")=="delete"){%>
<p><font color="red">既に削除されたユーザーです。</font></p>
<%} %>
<p>IDとパスワードを入力してください</p>
<form action="LoginCheck" method="post">
ユーザーID:<input type="text" name="userID"><br>
パスワード:<input type="password" name="password"><br>
<input type="submit" value="ログイン">
</form>
<a href="registration.jsp">会員登録</a>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>