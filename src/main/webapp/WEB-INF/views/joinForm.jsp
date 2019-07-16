<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="${pageContext.request.contextPath}/css/blog-home.css" rel="stylesheet">

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
					<form method="POST" action="join" name = "joinform">
						
						<fieldset class="form-group">
							<legend class="border-bottom mb-4">Join</legend>
							<div class="form-group">
								<label class="form-control-label">ID</label><input name = "idCheck" type = "radio" style = "display : none;"><span id = "idCheckMent"></span><span id = "id_bt"><label id = "idCheck_bt" style = "cursor : pointer ; float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;" onclick = "idCheck()">중복검사</label></span> <input
									class="form-control form-control-lg" type="text" name = "userId">
							</div>
							<div class="form-group">
								<label class="form-control-label">Password</label><span id = "pwCheckMent"></span> <input
									class="form-control form-control-lg" type="password" name = "userPw">
							</div>
							<div class="form-group">
								<label class="form-control-label">Confirm_password</label><span id = "pwconfirmCheckMent"></span> <input
									class="form-control form-control-lg" type="password" name = "userPw_Confirm">
							</div>
							<div class="form-group">
								<label class="form-control-label">Username</label><input name = "nameCheck" type = "radio" style = "display : none;"><span id = "nameCheckMent"></span><span id = "name_bt"><label id = "nameCheck_bt" style = "cursor : pointer ; float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;" onclick = "nameCheck()">중복검사</label></span>  <input
									class="form-control form-control-lg" type="text" name = "userName">
							</div>
							<div class="form-group">
								<label class="form-control-label">Email</label><input name = "emailCheck" type = "radio" style = "display : none;"><span id = "emailCheckMent"></span><span id = "email_bt"><label id = "emailCheck_bt" style = "cursor : pointer ; float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;" onclick = "emailCheck()">중복검사</label></span> <input
									class="form-control form-control-lg" type="email" name = "userEmail" id = "userEmail">
							</div>
							<div class="form-group">
								<label class="form-control-label">Email_인증번호</label>
								<input id = "saveAuthenticationNumber" type = "hidden" value = "">
								<input name = "authenticationNumberCheck" type = "radio" style = "display : none;"><span id = "authenticationNumberCheckMent"></span><span id = "authenticationNumber_bt"><label id = "authenticationNumberCheck_bt" style = "cursor : pointer ; float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;" onclick = "authenticationNumberCheck()">인증하기</label></span> 
								<input class="form-control form-control-lg" type="text" name = "authenticationNumber">
							</div>
							<div class="border-top pt-3">
								<small class="text-muted"> Already Have An Account? <a
									class="ml-2" href="#" data-toggle="modal"
									data-target="#myModal">login</a>
								</small>
							</div>
							<div class="form-group">
								<button class="btn btn-outline-info" onclick = "return availabilityCheck()">Sign
									in</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>

			<!-- SideBar -->
			<jsp:include page="${pageContext.request.contextPath}/include/sidebar.jsp" />
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
	
		function initLabel(){
			var htmls = "";
			$('#idCheckMent').html(htmls).trigger("create");
			$('#pwCheckMent').html(htmls).trigger("create");
			$('#pwconfirmCheckMent').html(htmls).trigger("create");
			$('#nameCheckMent').html(htmls).trigger("create");
			$('#emailCheckMent').html(htmls).trigger("create");
			$('#authenticationNumberCheckMent').html(htmls).trigger("create");
		}
		
		function availabilityCheck(){
			
			var htmls = "";
			
			if(joinform.userId.value == ""){
				
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 아이디를 입력해 주세요. </label>';
				$('#idCheckMent').html(htmls).trigger("create");
				joinform.userId.focus();
				return false;
				
			}
			
			else if(joinform.idCheck.checked == false){
				alert("아이디 중복검사를 실시해 주세요.");
				return false;
			}
			
			else if(joinform.userPw.value == ""){
				initLabel();
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 비밀번호를 입력해 주세요. </label>';
				$('#pwCheckMent').html(htmls).trigger("create");
				joinform.userPw.focus();
				return false;
				
			}
			
			else if(joinform.userPw.value != joinform.userPw_Confirm.value){
				initLabel();
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 비밀번호가 같지 않습니다. </label>';
				$('#pwconfirmCheckMent').html(htmls).trigger("create");
				joinform.userPw_Confirm.focus();
				return false;
				
			}
			
			else if(joinform.userName.value == ""){
				initLabel();
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 닉네임을 입력해 주세요. </label>';
				$('#nameCheckMent').html(htmls).trigger("create");
			    joinform.userName.focus();
			    return false;
			    
			}
			
			else if(joinform.nameCheck.checked == false){
				alert("닉네임 중복검사를 실시해 주세요.");
				return false;
			}
			
			else if(joinform.userEmail.value == ""){
				initLabel();
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 이메일을 입력해 주세요. </label>';
				$('#emailCheckMent').html(htmls).trigger("create");
			    joinform.userEmail.focus();
			    return false; 
			    
			}else if(joinform.emailCheck.checked == false){
				alert("이메일 중복검사를 실시해 주세요.");
				return false;
				
			}else if(joinform.authenticationNumber.value == ""){
				initLabel();
				htmls = '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 인증번호를 입력해 주세요. </label>';
				$('#authenticationNumberCheckMent').html(htmls).trigger("create");
			    joinform.authenticationNumber.focus();
			    return false; 
			    
			}else if(joinform.authenticationNumberCheck.checked == false){
				alert("이메일 인증을 실시해 주세요.");
				return false;
			}
			
			else{
				return true;
			}
		}
		
		function idCheck(){
			
			var htmls = "";
			var idhtmls = "";
			
			if(joinform.userId.value != ""){
				
				
				$.ajax({
					type : "GET",
					url : "idcheck?userId=" + joinform.userId.value,
					dataType : "json",
					success : function(responseData) {
						
						if(responseData.userCheckCount == 0){
							joinform.idCheck.checked = true;
							htmls += '<label style = "color : blue; font-size : 10px; margin-left : 12px;"> 사용 가능한 아이디 입니다. </label>';
							joinform.userId.readOnly = true;
							idhtmls = '<label id = "idCheck_bt" style = "float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;">중복검사</label>';
							$('#id_bt').replaceWith(idhtmls).trigger("create");
						}else{
							htmls += '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 이미 사용중인 아이디 입니다. </label>';
						}
						
						$('#idCheckMent').html(htmls).trigger("create");
						
					},
					error : function() {
						alert("통신 오류");	
					}
				});
				
				return true;
			}else{
				
				alert("아이디를 입력후 중복검사를 실시해 주세요.");
				joinform.idCheck.checked = false;
				joinform.idCheck.focus();
				return false;
			}
			
		}
		
		function nameCheck(){
			
			var htmls = "";
			var namehtmls = "";
			
			if(joinform.userName.value != ""){
				
				
				$.ajax({
					type : "GET",
					url : "namecheck?userName=" + joinform.userName.value,
					dataType : "json",
					success : function(responseData) {
						
						if(responseData.userCheckCount == 0){
							joinform.nameCheck.checked = true;
							htmls += '<label style = "color : blue; font-size : 10px; margin-left : 12px;"> 사용 가능한 닉네임 입니다. </label>';
							joinform.userName.readOnly = true;
							namehtmls = '<label id = "nameCheck_bt" style = "float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;">중복검사</label>';
							$('#name_bt').replaceWith(namehtmls).trigger("create");
						}else{
							htmls += '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 이미 사용중인 닉네임 입니다. </label>';
						}
						
						$('#nameCheckMent').html(htmls).trigger("create");
						
					},
					error : function() {
						alert("통신 오류");	
					}
				});
				
				return true;
			}else{
				
				alert("닉네임을 입력후 중복검사를 실시해 주세요.");
				joinform.nameCheck.checked = false;
				joinform.nameCheck.focus();
				return false;
			}
			
		}
		
		function emailCheck(){
				
			var htmls = "";
			var emailhtmls = "";
			
			if(joinform.userEmail.value != ""){
				
				if(joinform.userEmail.value.indexOf('@') != -1){
					$.ajax({
						type : "GET",
						url : "emailcheck?userEmail=" + joinform.userEmail.value,
						dataType : "json",
						success : function(responseData) {
							
							if(responseData.userCheckCount == 0){
								joinform.emailCheck.checked = true;
								htmls += '<label style = "color : blue; font-size : 10px; margin-left : 12px;"> 사용 가능한 이메일 입니다. </label>';
								joinform.userEmail.readOnly = true;
								emailhtmls = '<label id = "emailCheck_bt" style = "float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;">중복검사</label>';
								$('#email_bt').replaceWith(emailhtmls).trigger("create");
								sendMail();
							}else{
								htmls += '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 이미 사용중인 이메일 입니다. </label>';
							}
							
							$('#emailCheckMent').html(htmls).trigger("create");
							
							
						},
						error : function() {
							alert("통신 오류");	
						}
					});
					
					return true;
				}else{
					alert("이메일 형식에 맞게 입력하세요(@).");
					return false;
				}
			}else{
				
				alert("이메일을 입력후 중복검사를 실시해 주세요.");
				joinform.emailCheck.checked = false;
				joinform.emailCheck.focus();
				return false;
			}
				
		}
		
		function sendMail(){
			var mailSaltNum = "";
			alert("입력하신 이메일로 인증번호를 보냈습니다. 인증번호를 확인해 주세요.");
			
			$.ajax({
				type : "GET",
				url : "sendmail?userEmail=" + joinform.userEmail.value,
				dataType : "json",
				success : function(responseData) {
					mailSaltNum = responseData.authenticationNumber;
					$('#saveAuthenticationNumber').val(mailSaltNum);
				},
				error : function() {
					alert("통신 오류");	
				}
			});
		}
	
		function authenticationNumberCheck(){
			
			var htmls = "";
			var authenticationNumberhtmls = "";
			
			if(joinform.authenticationNumber.value != ""){
				var saveAuthNumber = $('#saveAuthenticationNumber').val();
				$.ajax({
					type : "POST",
					url : "emailnumcheck",
					data : {authenticationNumber : joinform.authenticationNumber.value, saveAuthenticationNumber : saveAuthNumber},
					dataType : "json",
					success : function(responseData) {
						
						if(responseData.authNumberCheck == true){
							joinform.authenticationNumberCheck.checked = true;
							htmls += '<label style = "color : blue; font-size : 10px; margin-left : 12px;"> 이메일이 인증 되었습니다. </label>';
							joinform.authenticationNumber.readOnly = true;
							authenticationNumberhtmls = '<label id = "authenticationNumberCheck_bt" style = "float : right; background-color : white; border : 1px solid #004080; border-radius : 5px; color : gray; padding : 2px 5px 2px 5px;">인증하기</label>';
							$('#authenticationNumber_bt').replaceWith(authenticationNumberhtmls).trigger("create");
						}else{
							htmls += '<label style = "color : red; font-size : 10px; margin-left : 12px;"> 인증번호를 확인하세요. </label>';
							joinform.authenticationNumber.value = "";
						}
						
						$('#authenticationNumberCheckMent').html(htmls).trigger("create");
						
					},
					error : function() {
						alert("통신 오류");	
					}
				});
			}else{
				alert("인증번호를 입력후 인증하기를 실시해 주세요.");
				joinform.authenticationNumberCheck.checked = false;
				joinform.authenticationNumber.focus();
				return false;
			}
		}
	</script>
</body>
</html>