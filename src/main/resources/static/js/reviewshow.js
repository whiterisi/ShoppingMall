$(document).ready(function(){
	
	$(".review").on("click", function(){
		var xxx = $(this).attr("data-product-id");
		
		$.ajax({
			url:'/review/list',
			type:'get',
			dataType:'json',
			data:{
				product_id:xxx
			
			},
			
			success:function(data,status,xhr){
				
				console.log("", data);
				var html = `<table>`;
				$.each(data, function(idx, value){
					html += `
					      <tr>
					       <td>아이디 : ${value.user_id}</td>
					      </tr>
					       <tr>
					       <td>제목 : ${value.title}</td>
					      </tr>
					      <tr>
					       <td>내용 : ${value.review_content}</td>
					       </tr>
					        <tr>
					       <td>별점 : ${value.score}</td>
					       </tr>
					`;
				});
				
				
				html +='</table>';
				$("#user").html(html);
			},
			error:function(xhr,status,error){
				
				console.log(status);
				console.log(error);
		}
			
		});
		
	});
	
});
//			
	
