
$(document).ready(function(){
    //moment.locale('ko');
	$("input[id^='datepicker']").each(function() {
		var _this = this.id;
		 $("#"+_this).daterangepicker({
	        "singleDatePicker": true,
	        "showDropdowns": true,
	        "changeMonth": true,
	        "showMonthAfterYear": true,
			"autoApply" : true,  //확인, 취소 버튼없고 날짜 클릭시 바로 입력 
	        "locale": {
	            "format": "YYYY-MM-DD",            
	            "separator": " - ",
	            "applyLabel": "Apply",
	            "cancelLabel": "Cancel",
	            "fromLabel": "From",
	            "toLabel": "To",
	            "customRangeLabel": "Custom",
	            "weekLabel": "W",
	            "applyLabel": "확인",            
	            "cancelLabel": "취소", 
	            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
	            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
	            "firstDay": 1
	        },
	            "showCustomRangeLabel": false,
	            "startDate": "2021-01-05",
	            //"endDate": "2021-01-05"
	        }, function(start, end, label) {
	        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
	    });
		
		 var pickerIndex =_this.substr(10, 1);	
		 $('.datepickerBtn'+pickerIndex).click(function(event){
			event.preventDefault();
	        $("#"+_this).click();
	     });
		
	});
   


});

