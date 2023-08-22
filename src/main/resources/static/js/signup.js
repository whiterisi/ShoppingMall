$(document).ready(function() {
	var checked="N";
	//아이디 중복 체크
	$("#checkId").click(function() {			
		$.ajax({
			url : 'idCheck',
			type : 'get',
			data : {
				id : $("#userid").val()
			},
			dataType : 'text', 
			success : function(responseData, status, xhr) {	
				var message = $("#idMessage");
				if(responseData=="사용가능"){
					message.css("color", "blue");
					checked = "Y";
				}else{
					message.css("color", "red");
					checked = "N";
				}				
				message.text(responseData);					
			},
			error : function(xhr, status, error) {
			}
		});			
	});
	
	//패스워드, 패스워드 확인 처리
	$("#passwdConfirm").keyup(function(){			
		let message = $("#passwordMessage");
		
		if($(this).val()==$("#passwd").val()){
			message.css("color", "blue").text("일치");					
		}else{				
			message.css("color", "red").text("불일치");
		}
	});
	
	$("#form").submit(function(){		
		if(checked !="Y"){
			alert("아이디 중복 확인이 필요합니다.");
			return false;
		}	
		if($("#passwd").val() == $("#passwdConfirm").val()){				
			return;
		}
		$("#passwordMessage").css("color", "red").text("불일치");
		alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.");
		return false;
	});
	
});