    function getSortResult() {
    	
    	var FlightSearchDTO = {
    			schDeptCityCode:$("#schDeptCityCode").val(),
    			schArrvCityCode: $("#schArrvCityCode").val(),
    			schDate: $("#schDate").val()
    	    };
    	
        $.ajax({
            type: "POST",
            url: "/api/flight",
            data: JSON.stringify(FlightSearchDTO),
            contentType: "application/json"
        })
            .done(function (result) {
                console.log(result);
                $("#resultFlight").replaceWith(result);
            })
            .fail(function(jqXHR) {
                console.log(jqXHR);
            })
            .always(function() {
                console.log("ajax 응답");
            })
    }
    