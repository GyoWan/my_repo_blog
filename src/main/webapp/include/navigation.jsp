<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-steel fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/main">Stag
			Beetle Blog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div id="navbarResponsive" class="collapse navbar-collapse">
			<div class="navbar-nav">
				<a class="nav-item active nav-link mr-auto"
					href="${pageContext.request.contextPath}/main">Home <span
					class="sr-only">(current)</span>
				</a> <a id = "new_post_bt" class="nav-item nav-link" href="#">New Post</a>
			</div>
			<div class="navbar-nav ml-auto">
			<c:choose>
				<c:when test = "${ sessionScope.login_userName != null }">
					<label class="nav-item nav-link">닉네임 :</label><a class="nav-item nav-link" href="#">${ sessionScope.login_userName }</a> <a class="nav-item nav-link" href="logout">Logout</a>
				</c:when>
				
				<c:otherwise>
					<a class="nav-item nav-link" href="${pageContext.request.contextPath}/joinForm">Register</a>
					<a class="nav-item nav-link" href="${pageContext.request.contextPath}/loginForm">Login</a>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</nav>

<!-- The Modal -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<form method="POST" class="login_form">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">LOG-IN</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">

					<div class="form-group">
						ID : <input class="form-control" id="login_userId" type = "text"></input> PW : <input
							class="form-control" id="login_userPw" type = "password"></input>
							
							
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="submit" class="btn btn-info" data-dismiss="modal"
						id="submit_login">Login</button>
					&nbsp;
					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				</div>
			</form>

		</div>
	</div>
</div>
