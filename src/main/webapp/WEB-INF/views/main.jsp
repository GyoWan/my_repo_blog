<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Stag Beetle Blog</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="/css/blog-home.css" rel="stylesheet">
<link href="/css/androidstudio.css" rel="stylesheet">
<script src="/js/highlight.pack.js"></script>
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="/include/navigation.jsp" />

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8">
			
			<c:choose>
				<c:when test="${ empty list }">
					<label style = "font-size : 20px; color : gray;">게시물이 존재하지 않습니다.</label>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ list }" var="board">
					<!-- Blog Post -->
					<div class="card mb-4">
						<div class="card-body">
							<h2 class="card-title">
							<c:if test = "${ board.bImgTag == 1 }">
								<img src="/img/imgIcon.png" alt="Card image cap" width = "35" height = "35">
							</c:if>
							<c:if test = "${ board.bType == 3 }">
							<label style = "color : #d1d1d1">[ 공지사항 ]</label>
							</c:if>
							  ${ board.bTitle }</h2>
							<p class="card-text">${ board.bContent }</p>
							<a href="viewForm?bId=${ board.bId }&nowPageNum=${ nowPageNum }&bType=${ bType }&sContent=${ sContent }" class="btn btn-primary">Read More &rarr;</a>
							<div style = "float : right;">
								<img src="/img/commentIcon.png" alt="Card image cap" width = "22" height = "35">
								<label>  ${ board.bCommentCount } </label>
								<img src="/img/goodIcon.png" alt="Card image cap" width = "22" height = "22">
								<label>  ${ board.bGood } </label>
							</div>
						</div>
						<div class="card-footer text-muted">
							Posted on ${ board.bMod_Date } by <a href="#">${ board.bAccount }</a>
						</div>
					</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
					<!-- Pagination -->
					<c:if test = "${ pageCount >0 }">
					<ul class="pagination justify-content-center mb-4">
						<c:choose>
							<c:when test="${ currentPageNum == 1 }">
								<li class="page-item disabled"><a class="page-link" href="list?&bType=${ bType }&sContent=${ sContent }"><<</a></li>
							</c:when>
							
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="list?&bType=${ bType }&sContent=${ sContent }"><<</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test = "${ startPageBlock > pageBlock }">
								<li class="page-item"><a class="page-link" href="list?nowPageNum=${ startPageBlock - 1 }&bType=${ bType }&sContent=${ sContent }"><</a></li>
							</c:when>
							
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link" href="#"><</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="pageIndex" begin = "${ startPageBlock }" end = "${ endPageBlock }" varStatus="status">
							<c:choose>
								<c:when test="${ currentPageNum == status.index }">
									<li class="page-item disabled"><a class="page-link" href="list?nowPageNum=${ status.index }&bType=${ bType }&sContent=${ sContent }">${ status.index }</a></li>
								</c:when>
									
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="list?nowPageNum=${ status.index }&bType=${ bType }&sContent=${ sContent }">${ status.index }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:choose>
							<c:when test="${ endPageBlock < pageCount }">
								<li class="page-item"><a class="page-link" href="list?nowPageNum=${ startPageBlock + 5 }&bType=${ bType }&sContent=${ sContent }">></a></li>
							</c:when>
							
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link" href="#">></a></li>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${ currentPageNum == pageCount }">
								<li class="page-item disabled"><a class="page-link">>></a></li>
							</c:when>
								
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="list?nowPageNum=${ pageCount }&bType=${ bType }&sContent=${ sContent }">>></a></li>
							</c:otherwise>
						</c:choose>
						
					</ul>
					</c:if>
			</div>

			<!-- SideBar -->
			<jsp:include page="/include/sidebar.jsp" />
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>
	<script src="/js/login.js"></script>
	<script src="/js/myboard.js"></script>
	<script>hljs.initHighlightingOnLoad();</script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			$('.card-text > img').remove();
		}, false);
	</script>
</body>
</html>