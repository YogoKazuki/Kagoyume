<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="kagoyume.UserData"%>
    <% HttpSession hs = request.getSession();
       UserData ud = new UserData();
	   ud = (UserData)hs.getAttribute("userdata");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>ユーザー情報の更新</title>
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
<form action="MyUpdateConfirm" method="post">
<input type="hidden" name="userID" value="<%=ud.getUserID()%>">
<%if(request.getAttribute("nondata")!=null){%>
<font color="red">郵便番号の値が不正です</font><%} %>
<ul>
<li>ユーザー名:<input type="text" name="name" value="<%=ud.getName() %>"></li>
<li>パスワード:<input type="password" name="password"></li>
<li>メールアドレス:<input type="text" name="mail" value="<%=ud.getMail()%>"></li>

<li>郵便番号<input type="text" name="zipcode" value="<%if(request.getAttribute("zipcode")!=null){out.print(request.getAttribute("zipcode").toString());}else{out.print(ud.getZipcode());}%>"><input type="submit" name="code" value="郵便番号で検索"></li>
<li>住所</li>
<li><input type="text" name="address1" placeholder="住所" value="<%if(request.getAttribute("address")!=null){out.print(request.getAttribute("address").toString());}else{out.print(ud.getAddress1());}%>"></li>
<li><input type="text" name="address2" placeholder="アパート名など" value="<%if(!ud.getAddress2().equals("")){out.print(ud.getAddress2());}%>"></li>
<li><input type="submit" name="submit" value="送信"></li>
</ul>
</form>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>