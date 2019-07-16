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
<title>Stag Beetle</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/blog-home.css"
	rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<!-- Navigation -->
	<jsp:include
		page="${pageContext.request.contextPath}/include/navigation.jsp" />

	<div class="container">
		<!-- Login Form -->
		<div class="row">
			<!-- Blog Entries Column -->
			<div class="col-md-8 my-order">
				<div class="content-section">
					<form method="POST" action="write" enctype="multipart/form-data" name = "writeform">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">New Post</legend>
							<div class="form-group">
								<label class="form-control-label">Board Select</label><br>
								<select class="form-control form-control-lg" name="bType" onChange = "selectAdminCheck(this)">
									<c:forEach items="${ selectlist }" var="select_list">
										<c:if test="${ select_list.liType == 1}">
											<option value="${ select_list.liValue }" <c:if test = "${ select_list.liValue == 0}">selected = "selected"</c:if>> ${ select_list.liName }</option>
										</c:if>
									</c:forEach>
								</select> <input
									class="form-control form-control-lg"
									name="bAccount" type = "hidden" value = "${ sessionScope.login_userName }"> <label class="form-control-label">Title</label>
								<input class="form-control form-control-lg" type="text"
									name="bTitle">
							</div>
							<div class="form-group">
								<label class="form-control-label">Content</label>
								<textarea name="bContent" id="content" class="form-control"
									rows="10" cols="50"></textarea>
								<script type="text/javascript">
									CKEDITOR
											.replace(
													'content',
													{
														filebrowserUploadUrl : '${ pageContext.request.contextPath }/fileupload',
														uploadUrl : "${ pageContext.request.contextPath }/fileupload"
														
													});
								</script>
							</div>
							<div class="form-group">
								<button class="btn btn-outline-info" onclick="return availabilityCheck()">Update</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<!-- SideBar -->
			<jsp:include
				page="${pageContext.request.contextPath}/include/sidebar.jsp" />
		</div>
		<!-- ./row -->
	</div>
	<!-- ./container -->
	<!-- Bootstrap core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
	<script src="${pageContext.request.contextPath}/js/myboard.js"></script>
	<script>
		function availabilityCheck(){
			
			if(writeform.bType.value == 0){
				
				alert("게시판 타입을 선택해 주세요.");
				writeform.bType.focus();
				return false;
				
			}
			
			else if(writeform.bTitle.value == ""){
				
				alert("제목을 입력해 주세요.");
				writeform.bTitle.focus();
				return false;
				
			}
			
			else if(CKEDITOR.instances.content.getData() == ''){
				
			    alert("내용을 입력해 주세요.");
			    CKEDITOR.instances.content.focus();
			    return false;    
			    
			 }else{
				return true;
			}
			
		}
		
		function selectAdminCheck(obj){
			var session = ${ sessionScope.isAdmin };
			
			if(obj.value == 3){
				if(!session){
					alert('공지사항 게시판은 관리자만 이용 가능합니다.');
					obj.value = 0;
				}
			}
			
		}
	</script>
</body>
</html>