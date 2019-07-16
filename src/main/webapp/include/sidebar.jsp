<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar -->
<div class="col-md-4">
  <!-- Search Widget -->
  <div class="card my-4">
    <h5 class="card-header">Search</h5>
    <div class="card-body">
      <div class="input-group">
        <input onkeydown="javascript: if (event.keyCode == 13){ search(); }" type="text" class="form-control" placeholder="Search for..." id = "search_Content">
        <span class="input-group-btn">
          <button id = "search_bt" class="btn btn-primary" type="button" onClick = "search()">Go!</button>
        </span>
      </div>
    </div>
  </div>

  <!-- Sidebar -->
  <div class="card my-4">
    <h5 class="card-header">Category</h5>
    <div class="card-body">
      <div id = "list-group" class="list-group">
      	
      </div>
    </div>
  </div>
 
</div>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		
		var htmls = "";
		
		$.ajax({
			type : "GET",
			url : "selectlist",
			dataType : "json",
			success : function(responseData) {
				
				$(responseData).each(function(){
					var liName = this.liName;
					if(this.liValue != 0){
						if(this.liType == 1){
							htmls += '<a href="${pageContext.request.contextPath}/list?bType=' + this.liValue + '" class="list-group-item list-group-item-action">&nbsp;&nbsp;&nbsp; - '+ liName +'</a>';
						}else if(this.liType == 0 && this.liValue != 1){
							htmls += '<a class="list-group-item list-group-item-action">'+ liName +'</a>';
						}else if(this.liType == 0 && this.liValue == 1){
							htmls += '<a href="${pageContext.request.contextPath}/list?bType=' + 0 + '" class="list-group-item list-group-item-action">'+ liName +'</a>';
						}
					}
				});
				
				$("#list-group").html(htmls).trigger("create");
			},
			error : function() {
				alert("통신 오류");	
			}
		});
		
	}, false);
	
	function search(){
		if($('#search_Content').val() == ""){
			alert("검색어를 입력 하세요.");
		}else{
			location.href = "list?sContent="+$('#search_Content').val();
		}
	}
</script>