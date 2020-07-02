<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="kagoyume.UserData"
    import="javax.servlet.http.HttpSession"%>
    <%HttpSession hs = request.getSession();
    UserData ud = new UserData();
    if(hs.getAttribute("ud")!=null){
    ud = (UserData)hs.getAttribute("ud");}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>会員登録</title>
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
<div class="text-center">
<form action="RegistrationConfirm" method="post">
<%if(request.getAttribute("nondata")!=null){%>
<font color="red">郵便番号の値が不正です</font><%} %>
<ul>
<li>ユーザー名:<input type="text" name="name" value="<%if(hs.getAttribute("ud")!=null){out.print(ud.getName());}%>"></li>
<li>パスワード:<input type="password" name="password"></li>
<li>メールアドレス:<input type="text" name="mail" value="<%if(hs.getAttribute("ud")!=null){out.print(ud.getMail());}%>"></li>

<li>郵便番号<input type="text" name="zipcode" value="<%if(hs.getAttribute("ud")!=null){out.print(ud.getZipcode());}%>"><input type="submit" name="code" value="郵便番号で検索"></li>
<li>住所</li>
<li><input type="text" name="address1" placeholder="住所" value="<%if(hs.getAttribute("ud")!=null){out.print(ud.getAddress1());}%>"></li>
<li><input type="text" name="address2" placeholder="アパート名など" value="<%if(hs.getAttribute("ud")!=null){out.print(ud.getAddress2());}%>"></li>
<li><input type="submit" name="submit" value="確認画面へ"></li>
</ul>
</form>
</div>
<a href="top.jsp">戻る</a>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>