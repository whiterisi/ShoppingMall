$(document).ready(function(){

    	 //수정버튼
        $(".updateBtn").on("click",function(){
           	var num= $(this).attr("data-xxx");
        	var gAmount = $("#cartAmount"+num).val();
        	var gPrice= $(this).attr("data-price");
        	var inventory = $(this).attr("data-inventory");
        	console.log(num, gAmount, inventory);
        	
        	//0개이하 처리
        	if(gAmount < 1){
        		alert("상품 수량은 1개 이상이어야 합니다.");
        		return location.href="cartList";
        	}
        	console.log(inventory);
        	//재고초과 처리
        	if(Number(gAmount) > Number(inventory)){
        		alert("상품이 재고수량 " + inventory + " 개를 초과합니다.");
        		return location.href="cartList";
        	}
        	
        	location.href="updateCart?num="+num+"&gAmount="+gAmount;
        	
        	
//        	$.ajax({
//        		url:'updateCart',
//        		type:'get',
//        		dataType:'text',
//        		data:{
//        			num:num,
//        			gAmount:gAmount
//        		},
//        		success:function(data,status,xhr){
//        			var sum=gAmount*gPrice;
//        			$("#sum"+num).text(sum+"원");
//        			alert("수량을 수정하였습니다.");
//        		},
//        		error:function(xhr,status,error){
//        			
//        		}
//        	});//end ajaxgkgk
        });
    	 
        //삭제버튼
        $(".delBtn").on("click",function(){
        	var num= $(this).attr("data-xxx");
        	var xxx = $(this);
        	
        	location.href="delCart?num="+num;
        	/*$.ajax({
        		url:'delCart',
        		type:'get',
        		dataType:'text',
        		data:{
        			num:num
        		},
        		success:function(data,status,xhr){
        			//현재 선택한 레코드의 부모태그 찾기
        			xxx.parents().filter("tr").remove();        			
        			var itemCount=$(".items").length;
        			if(itemCount>0){
        				alert("상품을 삭제하였습니다.");
        			}else{
						alert("상품을 삭제하였습니다. 장바구니가 비어 메인화면으로 이동합니다.");
						location.href="/shop/main";	
					}
        		},
        		error:function(xhr,status,error){
        			
        		}
        	});//end ajaxgkgk
*/        });
        
        //체크박스 선택시
        $(".check").on("change", function(){
        	
        	let pPrices = 0;
            let dPrices = 0;
            let payPrices = 0;
       		for(i=0; i < $(".check").length; i++){
       			var checked = $(".check")[i].checked;
       			var checknum = $(".check")[i].defaultValue;
       			//console.dir(checked);
       			
       			//payPrice가격
       			if(checked){
            		var checkedPayPrice = 'sumPrice'+ checknum;
            		var checkedpPrice = 'price'+ checknum;
            		var checkeddPrice = 'discount'+ checknum;
            		
            		var payPrice = $("#"+checkedPayPrice).attr("data-price");
            		var pPrice = $("#"+checkedpPrice).attr("data-price");
            		var dPrice = $("#"+checkeddPrice).attr("data-price");


       				//'sumPrice'+${item.num}
       				//console.dir(target2);
       				//console.dir(target2, $(target2));
       				//console.log(target3);
       				payPrices += Number(payPrice);
       				pPrices += Number(pPrice);
       				dPrices += Number(dPrice);
       			}
       			//console.log(pPrice);
       			
       			
       		}
       		let exchange = $("#exchange").attr("data-exchange");
       		
       		var exPPrice = pPrices * exchange;
       		var exDPrice = dPrices * exchange;
       		var exPayPrice = payPrices * exchange;
       		
       		payPrices = addComma(payPrices);
       		pPrices = addComma(pPrices);
       		dPrice = addComma(dPrice);
       		
       		exPPrice = addComma(exPPrice);
       		exDPrice = addComma(exDPrice);
       		exPayPrice = addComma(exPayPrice);
       		
       		$(".pPrice").text('$' + pPrices + '('  + exPPrice +"원)");
       		$(".dPrice").text('$' + dPrices + '('  + exDPrice +"원)");
    		$(".payPrice").text('$' + payPrices + '('  + exPayPrice +"원)");
        	
   	  });
        
        
        
        
 
        $("#mainBtn").click(function(){
			location.href="/shop/main";
		});
		$("#logoutBtn").click(function() {			
			location.href = "/shop/logout";	
			alert("로그아웃 되었습니다.");		
		});
		$("#myPageBtn").click(function() {
			location.href = "/shop/mypage";
		});
		$("#cartListBtn").click(function() {
			location.href = "/shop/cartList";
		});
		
		
        //전체선택
        $("#allCheck").on("click",function(){
        	
        	//이전 코드
        	var result = this.checked;
        	$(".check").each(function(idx,data){
        		this.checked=result;
        	});
        	
        	//내코드
        	let pPrices = 0;
            let dPrices = 0;
            let payPrices = 0;
       		for(i=0; i < $(".check").length; i++){
       			var checked = $(".check")[i].checked;
       			var checknum = $(".check")[i].defaultValue;
       			//console.dir(checked);
       			
       			//payPrice가격
       			if(checked){
            		var checkedPayPrice = 'sumPrice'+ checknum;
            		var checkedpPrice = 'price'+ checknum;
            		var checkeddPrice = 'discount'+ checknum;
            		
            		var payPrice = $("#"+checkedPayPrice).attr("data-price");
            		var pPrice = $("#"+checkedpPrice).attr("data-price");
            		var dPrice = $("#"+checkeddPrice).attr("data-price");


       				//'sumPrice'+${item.num}
       				//console.dir(target2);
       				//console.dir(target2, $(target2));
       				//console.log(target3);
       				payPrices += Number(payPrice);
       				pPrices += Number(pPrice);
       				dPrices += Number(dPrice);
       			}
       			//console.log(pPrice);
       			
       			
       		}
       		let exchange = $("#exchange").attr("data-exchange");
       		var exPPrice = pPrices * exchange;
       		var exDPrice = dPrices * exchange;
       		var exPayPrice = payPrices * exchange;
       		
       		payPrices = addComma(payPrices);
       		pPrices = addComma(pPrices);
       		dPrice = addComma(dPrice);
       		
       		exPPrice = addComma(exPPrice);
       		exDPrice = addComma(exDPrice);
       		exPayPrice = addComma(exPayPrice);
       		
       		$(".pPrice").text('$' + pPrices + '('  + exPPrice +"원)");
       		$(".dPrice").text('$' + dPrices + '('  + exDPrice +"원)");
    		$(".payPrice").text('$' + payPrices + '('  + exPayPrice +"원)");
        	
        });
        
       
        $("#delAllCart").on("click",function(){
        	
        	$("form").attr("action", "delAllCart");
        	$("form").submit();// trigger
        });
        
        
       
        $(".orderBtn").on("click",function(){
        	var num= $(this).attr("data-xxx");
        	location.href="cartOrderConfirm?num="+num;
        });
     
        $("#orderAllConfirm").on("click",function(){
        	$("form").attr("action", "payment");
        	$("form").attr('method', 'post');
        	$("form").submit();// trigger
        });
        
        
        function addComma(value){
            value = value.toString();
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            return value; 
        }
        
   });