$(document).ready(function() {

	//Button for profile post
	$('#login_bt').click(function() {
		var uid = $("#userId").val();
		var pwd = $("#userPw").val();
		
		var rsa = new RSAKey();
		rsa.setPublic($("#RSAModulus").val(), $("#RSAExponent").val());
		
		uid = rsa.encrypt(uid);
		pwd = rsa.encrypt(pwd);
		
		$.ajax({
			type : "POST",
			url : "login",
			//form.profile_post = form중에 class가 profile_post인 것
			//serialize된 값 => post_body=값&user_from=값&user_to=값
			data : {userId:uid, userPw:pwd},
			dataType : "json",
			success : function(responseData) {
				
				if(responseData.userStatus == 1){
					alert(responseData.userName + "님 환영합니다.");
					location.href = "main";
				}else if(responseData.userStatus == 2){
					alert("비밀번호를 다시 확인하세요.");
				}else{
					alert("아이디 또는 비밀번호를 확인하세요.");
				}
			},
			error : function() {
				alert("아이디 또는 비밀번호를 확인하세요.");
			}
		});
	});
});