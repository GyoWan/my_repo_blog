$(document).ready(function() {

	$('#new_post_bt').click(function() {

		$.ajax({
			type : "GET",
			url : "logincheck",
			success : function(responseData) {
				
				if(responseData.login_Check == false){
					alert("로그인이 필요합니다.");
				}else{
					location.href = "writeForm";
				}
			},
			error : function() {
				alert("통신 오류");	
			}
		});
		
	});
	
});
