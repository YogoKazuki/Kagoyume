<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="kagoyume.ItemData"
     import="java.util.*"%>
    <% HttpSession hs = request.getSession();
    List<ItemData> itemList = new ArrayList<ItemData>();
    itemList = (ArrayList<ItemData>)hs.getAttribute("cartlist");
    int total = 0;
    for(ItemData e : itemList){total+=Integer.parseInt(e.getPrice());}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>購入確認画面</title>
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
以上の内容で購入しますか？
<%if(hs.getAttribute("nontype")!=null){%>
	<font color="red">配達方法を選択してください</font>
	<%}%>

<ul>
<%for(ItemData e : itemList){%>
<li><%=e.getName() %></li>
<li><%=e.getPrice() %></li>
<%} %>
</ul>
合計金額:<%=total %>円
<form action="BuyComplete" method="post">
発送方法
<input type="radio" name="type" value="1">徒歩
<input type="radio" name="type" value="2">自転車
<input type="radio" name="type" value="3">車
<input type="hidden" name="total" value="<%=total%>">
<input type="submit" value="上記の内容で購入する">
</form>
<a href="Cart"><button>カートに戻る</button></a>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>