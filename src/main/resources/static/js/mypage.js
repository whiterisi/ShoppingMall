$(document).ready(function() {

	//패스워드, 패스워드 확인 처리
	$("#passwdConfirm").blur(function(){			
		let message = $("#passwordMessage");
		
		if($(this).val()==$("#passwd").val()){
			message.css("color", "blue").text("일치");					
		}else{				
			message.css("color", "red").text("불일치");
		}
	});
	
	$("#form").submit(function(){		
		if($("#passwd").val() == $("#passwdConfirm").val()){				
			return;
		}
		$("#passwordMessage").css("color", "red").text("불일치");
		alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.");
		return false;
	});
	
	$("#cancel").click(function() {
		location.href = "/";
	});

});
