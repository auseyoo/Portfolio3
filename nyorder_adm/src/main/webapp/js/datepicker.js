
$(document).ready(function(){
    $("#datepicker").daterangepicker({
        locale: {
"separator": " ~ ",                     // 시작일시와 종료일시 구분자
"format": 'YYYY-MM-DD HH:mm:ss',     // 일시 노출 포맷
"applyLabel": "확인",                    // 확인 버튼 텍스트
"cancelLabel": "취소",                   // 취소 버튼 텍스트
"daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
"monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
},
        singleDatePicker: true,
        //showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10)
        }, function(start, end, label) {
        var years = moment().diff(start, 'years');
        //alert("You are " + years + " years old!");
    });
});