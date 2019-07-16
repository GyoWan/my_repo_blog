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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/blog-home.css"
	rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/RSA/jsbn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/RSA/rsa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/RSA/prng4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/RSA/rng.js"></script>
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
					<form method="POST">
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">StagBeetle Login</legend>
							<input type="hidden" id="RSAModulus" value="${RSAModulus}" />
							<input type="hidden" id="RSAExponent" value="${RSAExponent}" />
							<div class="form-group">
								<label class="form-control-label">ID</label> <input id="userId"
									class="form-control form-control-lg" type="text">
							</div>
							<div class="form-group">
								<label class="form-control-label">PW</label> <input id="userPw"
									class="form-control form-control-lg" type="password">
							</div>
							<div class="form-group">
								<button id="login_bt" class="btn btn-outline-info">Login</button>
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
</body>
</html>