<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="/js/highlightjs-line-numbers.js"></script>
</head>

<body>
	<!-- Navigation -->
	<jsp:include page="${pageContext.request.contextPath}/include/navigation.jsp" />
	
	<!-- Page Content -->
	<div class="container">
		<div class="row">

			<div class="col-md-12">
				<div class="card mb-4">
					<!-- Post Content Column -->
					<div class="card-body main">
						<!-- Title -->
						<h1 class="mt-4">${ viewContent.bTitle }</h1>
						<label style = "color : gray; font-size : 20px;">[ 
						<c:forEach items="${ selectlist }" var="select_list">
							<c:if test="${ select_list.liValue == viewContent.bType}">
								${ select_list.liName }
							</c:if>
						</c:forEach> ]
						</label>
						<!-- Author -->
						<p class="lead">
							by <a href="#">${ viewContent.bAccount }</a>
							
						</p>
						<hr class = "viewHr">
						<!-- Date/Time -->
						<p>Posted on ${ viewContent.bMod_Date }</p>
						<hr class = "viewHr">
						<!-- Post Content -->
						<div id="viewContent">
							<c:choose>
								<c:when test="${empty imgSrcList }">
									${ viewContent.bContent }
								</c:when>
								
								<c:otherwise>
									<p><button class="img_View" onclick = "imgView()">이미지 모아보기</button></p> ${ viewContent.bContent } 
								</c:otherwise>
							</c:choose>
							
						</div>
						
						<hr class = "viewHr">
						
						<c:if test = "${ (viewContent.bAccount == sessionScope.login_userName) || sessionScope.isAdmin == true}">
							<a class="btn btn-warning" href="modifyForm?bId=${ viewContent.bId }&nowPageNum=${ nowPageNum }">수정</a> 
							<a id = "board_delete_bt" class="btn btn-danger" href="delete?bId=${ viewContent.bId }">삭제</a>	
						</c:if>
						<c:choose>
							<c:when test = "${ sContent == ''}">
								<a class="btn btn-warning" href="list?nowPageNum=${ nowPageNum }&bType=${ bType }">목록</a> 
							</c:when>
							
							<c:otherwise>
								<a class="btn btn-warning" href="list?nowPageNum=${ nowPageNum }&sContent=${ sContent }">목록</a> 
							</c:otherwise>
						</c:choose>
						
						<hr class = "viewHr">
						<span id = "comment">
							<img src="/img/commentIcon.png" alt="Card image cap" width = "22" height = "34">
							<label> ${ viewContent.bCommentCount } </label>
						</span>
						<span id = "good">
						<c:choose>
							<c:when test = "${ empty sessionScope.login_userName }">
								<c:choose>
									<c:when test = "${ goodAccountCheck == true }">
										<a style = "text-decoration : none;"> <img src="/img/good_clickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>
									</c:when>
									<c:otherwise>
										<a style = "text-decoration : none;"> <img src="/img/good_noneclickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test = "${ goodAccountCheck == true }">
										<a href = "#none" style = "text-decoration : none;" onclick = "good_bt('${ viewContent.bId  }')"> <img src="/img/good_clickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>
									</c:when>
									<c:otherwise>
										<a href = "#none" style = "text-decoration : none;" onclick = "good_bt('${ viewContent.bId  }')"> <img src="/img/good_noneclickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<label> ${ goodCount } </label>
						</span>
						
						<!-- Comments Form -->
						<div class="card my-4">
							<h5 class="card-header">Leave a Comment:</h5>
							<div class="card-body">
								<div class="form-group">
									<textarea name = "cContent" class="form-control" id="comment_Content" rows="3" placeholder="댓글을 입력해 주세요."></textarea>
								</div>
								<input name = "bId" id = "comment_Id" type = "hidden" value = "${ viewContent.bId }">
								<input name = "cAccount" id = "comment_Name" type = "hidden" value = "${ sessionScope.login_userName }">
								<c:choose>
									<c:when test="${empty sessionScope.login_userName}">
										<input type="button" class="btn btn-primary"
											onclick="alert('로그인 후 이용가능합니다.')" value="등록">
									</c:when>
									<c:otherwise>
										<input id = "comment_bt" type="button" class="btn btn-primary" value="등록">
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<input type = "hidden" id = "paging_Num">
						<input type = "hidden" id = "comment_Str">
						<div id="comment_List">
							<!-- Comment  -->
							<c:if test = "${ empty viewComment }">등록된 댓글이 없습니다.</c:if>
							<c:forEach items="${ viewComment }" var="comment_List">
								<div class="media mb-4" id = "${ comment_List.cId }">
									<div class="media-body">
										<h5 class="mt-0">${ comment_List.cAccount }&nbsp;&nbsp;<label style = "font-size : 15px; color : gray;">${ comment_List.strDate }</label>
										</h5>
										${ comment_List.cContent }
										<c:if test = "${ sessionScope.login_userName == comment_List.cAccount }">
											<a style = "text-decoration : none; font-size : 12px; float : right;" href = "#none" onclick = "comment_Delete('${ comment_List.cId }', '${ viewContent.bId }')">삭제</a> <label style="font-size : 12px; float : right;">|</label> <a style = "text-decoration : none; font-size : 12px; float : right;" onclick = "comment_Mod_View('${ comment_List.cId }', '${ comment_List.cAccount }' , '${ comment_List.cContent }')" href = "#none">수정</a>
										</c:if>
									</div>
								</div>
								<hr>
							</c:forEach>
						</div>
						
						
						<div id = "cpaging">
						<c:if test = "${ cpageCount > 0 }">
						<ul class="pagination justify-content-center mb-4">
								
								<c:choose>
									<c:when test="${ ccurrentPageNum == 1 }">
										<li class="page-item disabled"><a class="page-link" href = "#"><<</a></li>
									</c:when>
									
									<c:otherwise>
										<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('1')"><<</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test = "${ cstartPageBlock > cpageBlock }">
										<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('${ cstartPageBlock - 1 }')"><</a></li>
									</c:when>
									
									<c:otherwise>
										<li class="page-item disabled"><a class="page-link" href = "#"><</a></li>
									</c:otherwise>
								</c:choose>
								<c:forEach var="cpageIndex" begin = "${ cstartPageBlock }" end = "${ cendPageBlock }" varStatus="status">
									<c:choose>
										<c:when test="${ ccurrentPageNum == status.index }">
											<li class="page-item disabled"><a class="page-link" href = "#" onclick = "comment_paging('${ status.index }')">${ status.index }</a></li>
										</c:when>
											
										<c:otherwise>
											<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('${ status.index }')">${ status.index }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:choose>
									<c:when test="${ cendPageBlock < cpageCount }">
										<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('${ cstartPageBlock + 10 }')">></a></li>
									</c:when>
									
									<c:otherwise>
										<li class="page-item disabled"><a class="page-link" href = "#">></a></li>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${ ccurrentPageNum == cpageCount }">
										<li class="page-item disabled"><a class="page-link" href = "#">>></a></li>
									</c:when>
										
									<c:otherwise>
										<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('${ cpageCount }')">>></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
							</c:if>
							</div>
							
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>
	<script src="/js/login.js"></script>
	<script src="/js/myboard.js"></script>
	<script>
		$('#comment_bt').click(function() {
			
			var comment_Str = "댓글등록";
			$('#comment_Str').val('댓글등록');
			
			if($('#comment_Content').val() != ""){
				$.ajax({
					type : "GET",
					url : "comment?bId="+ $("#comment_Id").val() + "&cAccount=" + $("#comment_Name").val() + "&cContent=" + $("#comment_Content").val(),
					dataType : "json",
					success : function(responseData) {
						
							updateCommentcount(responseData.commentCount);
							showCommentList($("#comment_Id").val(), comment_Str, 1);
							$('#comment_Content').val('');
							$('#paging_Num').val(1);
							$('#comment_Str').val('');
					},
					error : function() {
						alert("통신 오류");	
					}
				});
			}else{
				alert("댓글 내용을 입력해 주세요.");
			}
			
		});
		
		function comment_paging(commentpageIndex){
			
			$('#paging_Num').val(commentpageIndex);
			var session_userName = '${ sessionScope.login_userName }';
			var paging_Num = commentpageIndex;
			var comment_Str = "댓글페이징";
			
			showCommentList($("#comment_Id").val(), comment_Str, paging_Num);
			pagingCheck();
		}
		
		function showCommentList(bId, comment_Str, paging_Num){
			
			var htmls = "";
			var seturl = "";
			var session_userName = '${ sessionScope.login_userName }';
			
			if(comment_Str == "댓글등록"){
				seturl = "commentlist?bId="+ bId + "&cnowPageNum=" + 1;
			}else{
				seturl = "commentlist?bId="+ bId + "&cnowPageNum=" + paging_Num;
			}
			
			$.ajax({
				type : "GET",
				url : seturl,
				dataType : "json",
				success : function(cresponseData) {
					
					if(cresponseData == null){
						htmls = "등록된 댓글이 없습니다."
					}else{
						$(cresponseData).each(function(){
							htmls += '<div class="media mb-4" id = "' + this.cId + '">';
							htmls += '<div class="media-body">';
							htmls += '<h5 class="mt-0">' + this.cAccount + '&nbsp;&nbsp;<label style = "font-size : 15px; color : gray;">' + this.strDate + '</label></h5>';
							htmls += this.cContent;
							if( session_userName == this.cAccount ){
								htmls += '<a style = "text-decoration : none; font-size : 12px; float : right;" href = "#none" onclick = "comment_Delete(' + this.cId + ', \'' + this.bId + '\')">삭제</a> <label style="font-size : 12px; float : right;">|</label> <a style = "text-decoration : none; font-size : 12px; float : right;" onclick = "comment_Mod_View(' + this.cId + ', \'' + this.cAccount + '\', \'' + this.cContent + '\' )" href = "#none">수정</a>';
							}
							htmls += '</div>';
							htmls += '</div>';
							htmls += '<hr>';
						});
					}
					
					$("#comment_List").html(htmls).trigger("create");
					pagingCheck();
					
				},
				error : function() {
					alert("통신 오류");	
				}
			});
		}
		
		function updateCommentcount(commentCount){
			var htmls = ""
			
			htmls += '<img src="/img/commentIcon.png" alt="Card image cap" width = "22" height = "34">';
			htmls += '<label>&nbsp;' + commentCount + '</label>';
			
			$('#comment').html(htmls).trigger("create");
		}
		
		function comment_Delete(cId, bId){
			
			var comment_Str = "댓글삭제";
			$('#comment_Str').val('댓글삭제');
			
			if(confirm('정말로 댓글을 삭제하시겠습니까?')){
				$.ajax({
					type : "GET",
					url : "commentdelete?bId="+ bId + "&cId=" + cId,
					dataType : "json",
					success : function(responseData) {
						
						updateCommentcount(responseData.commentCount);
						showCommentList($("#comment_Id").val(), comment_Str, $('#paging_Num').val());
						pagingCheck();
						
					},
					error : function() {
						alert("통신 오류");	
					}
				});
				alert("댓글이 삭제 되었습니다.");
			}else{
				return false;
			}
		}
		
		
		function comment_Mod_View(cId, cAccount, cContent){
			var htmls = ""
			htmls += '<div class="media mb-4" id = "' + cId + '">';
			htmls += '<div class="media-body">';
			htmls += '<h5 class="mt-0">' + cAccount +'</h5>';
			htmls += '<div class="form-group">';
			if($('#paging_Num').val() == null){
				htmls += '<textarea name = "cContent" class="form-control" id="comment_Mod_Content" rows="3">' + cContent + '</textarea><button class="btn btn-primary" style = "float : right;" id = "#comment_Mod" onclick = "comment_Mod(' + cId + ', \'' + 1 + '\')">완료</button>';
			}else{
				htmls += '<textarea name = "cContent" class="form-control" id="comment_Mod_Content" rows="3">' + cContent + '</textarea><button class="btn btn-primary" style = "float : right;" id = "#comment_Mod" onclick = "comment_Mod(' + cId + ', \'' + $('#paging_Num').val() + '\')">완료</button>';
			}
			htmls += '</div>';
			htmls += '</div>';
			htmls += '</div>';
			
			$('#'+ cId).replaceWith(htmls).trigger("create");
		}
		
		function comment_Mod(cId, cnowPageNum){
			
			var htmls = "";
			var comment_Str = "댓글수정";
			$('#comment_Str').val('댓글수정');
			
			$.ajax({
				type : "GET",
				url : "commentmodify?bId="+ $("#comment_Id").val() + "&cContent=" + $("#comment_Mod_Content").val() + "&cId=" + cId + "&cnowPageNum=" + cnowPageNum,
				dataType : "json",
				
				success : function(responseData) {
					
					showCommentList($("#comment_Id").val(), comment_Str, cnowPageNum);
					
				},
				error : function() {
					alert("통신 오류");	
				}
			});
		}
		
		function pagingCheck(){
			
			var htmls = "";
			var seturl = "";
			
			if($('#comment_Str').val() == "댓글등록"){
				seturl = "paging?bId="+ $("#comment_Id").val();
			}else{
				seturl = "paging?bId="+ $("#comment_Id").val() + "&cnowPageNum=" + $('#paging_Num').val();
			}
			
			$.ajax({
				type : "GET",
				url : seturl,
				dataType : "json",
				success : function(responseData) {
					
					htmls += '<ul class="pagination justify-content-center mb-4">';
					
					if(responseData.cpageCount > 0){
						if(responseData.ccurrentPageNum == 1){
							htmls += '<li class="page-item disabled"><a class="page-link" href = "#"><<</a></li>';
						}else{
							htmls += '<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging(' + 1 + ')"><<</a></li>';
						}
						
						if(responseData.cstartPageBlock > responseData.cpageBlock){
							htmls += '<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging('+ (responseData.cstartPageBlock - 1) +')"><</a></li>';
						}else{
							htmls += '<li class="page-item disabled"><a class="page-link" href = "#"><</a></li>';
						}
						
						for(var i = responseData.cstartPageBlock; i <= responseData.cendPageBlock; i++){
							if( i  ==  responseData.ccurrentPageNum){
								htmls += '<li class="page-item disabled"><a class="page-link" href = "#" onclick = "comment_paging(' + i + ')">'+ i +'</a></li>';
							}else{
								htmls += '<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging(' + i + ')">'+ i +'</a></li>';
							}
						}
						
						if(responseData.cendPageBlock < responseData.cpageCount){
							htmls += '<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging(' + (responseData.cstartPageBlock + 10) + ')">></a></li>';
						}else{
							htmls += '<li class="page-item disabled"><a class="page-link">></a></li>';
						}
						
						if(responseData.ccurrentPageNum == responseData.cpageCount){
							htmls += '<li class="page-item disabled"><a class="page-link" href = "#">>></a></li>';
						}else{
							htmls += '<li class="page-item"><a class="page-link" href = "#" onclick = "comment_paging(' + responseData.cpageCount + ')">>></a></li>';
						}
						
						htmls += '</ul>';
					}
	
					$("#cpaging").html(htmls).trigger("create");
				},
				error : function() {
					alert("통신 오류");	
				}
			});
			
		}
		
		function good_bt(bId){
			var htmls = "";
			var session = '${ sessionScope.login_userName }';
			$.ajax({
				type : "GET",
				url : "good?bId=" + bId + "&gAccount=" + '${ sessionScope.login_userName }',
				dataType : "json",
				success : function(responseData) {
					
					if(session == ""){
						if(responseData.goodAccountCheck == true){
							htmls += '<a style = "text-decoration : none;"> <img src="/img/good_noneclickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>';
						}else{
							htmls += '<a style = "text-decoration : none;"> <img src="/img/good_clickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>';
						}
					}else{
						if(responseData.goodAccountCheck == true){
							htmls += '<a href = "#none" style = "text-decoration : none;" onclick = "good_bt(' + ${ viewContent.bId  } + ')"> <img src="/img/good_noneclickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>';
						}else{
							htmls += '<a href = "#none" style = "text-decoration : none;" onclick = "good_bt(' + ${ viewContent.bId  } + ')"> <img src="/img/good_clickIcon.png" alt="Card image cap" width = "20" height = "20"> </a>';
						}
					}
					
					htmls += '<label>' + responseData.goodCount + ' </label>';
					
					$('#good').html(htmls).trigger("create");
				},
				error : function() {
					alert("통신 오류");	
				}
			});
		}
		
		$('#board_delete_bt').click(function() {
			if(confirm('정말로 게시글을 삭제하시겠습니까?')){
				alert("게시글이 삭제 되었습니다.");
			}else{
				return false;
			}
		});
		
		
		function imgView(){
			var htmls = "";
			var json = JSON.parse('${imgSrcList}');
			var popupW = 750;
			var popupH = 600;
			var left = Math.ceil((window.screen.width - popupW)/2);
			var top = Math.ceil((window.screen.height - popupH)/2);
			
			$(json).each(function(index, item){
				htmls += '<div class="mySlides fade">';
				htmls += '<div class="numbertext">' + (index + 1) + ' / ' + json.length + '</div>';
				htmls += '<img src = "' + item + '"/>';
				htmls += '<div class="text"></div>';
				htmls += '</div>';
			});
			
			window.open('imgview?htmls=' + htmls,'','width='+popupW+',height='+popupH+',left='+left+',top='+top+',scrollbars=yes,resizable=no,toolbar=no,titlebar=no,menubar=no,location=no');
		}
	</script>
	<script>
		hljs.initHighlightingOnLoad();
		hljs.initLineNumbersOnLoad();
		$(document).ready(function() {
			$('code.hljs').each(function(i, block) {
				hljs.lineNumbersBlock(block);
			});
		});
	</script>
</body>
</html>