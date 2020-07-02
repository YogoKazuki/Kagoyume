<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.HttpSession"%>
    <%HttpSession hs = request.getSession(); %>
    <%if(hs.getAttribute("check")!="ok"){
    	hs.setAttribute("check","no");  }
    	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--  Bootstrap CSS  -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="stylesheet.css">
<title>かごゆめ</title>
</head>
<body class="body">

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

<h1 class="top pt-5">ショッピングサイトを使っている時<br>こんな経験ありませんか？</h1>
<p class="top">「あれいいな」<br>
「これいいな」<br>
「あっ、関連商品のこれもいい」<br>
「20%オフセールだって！？買わなきゃ！」・・・<br>
そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。<br>
「こんなに買ってたのか・・・しょうがない。いくつか減らそう・・・」<br>
仕方がありません。無駄遣いは厳禁です。<br>
でも、一度買うと決めたものを諦めるなんて、ストレスじゃあありませんか？<br>
できればお金の事なんか考えずに好きなだけ買い物がしたい・・・。<br>

このサービスは、そんなフラストレーションを解消するために生まれた『金銭取引が絶対に発生しない』<br>
『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です。</p>



<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>