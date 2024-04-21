$(document).ready(function () {
	var options = {
			MonthFormat: 'yy-mm',
			ShowIcon: false,
			i18n: {
				year: '년도',
				prevYear: '이전년도',
				nextYear: '다음년도',
				next12Years: '다음 12년',
				prev12Years: '이전 12년',
				nextLabel: '다음',
				prevLabel: '이전',
				buttonText: 'Open Month Chooser',
				jumpYears: '년도로 이동',
				backTo: '뒤로',
				months: ['1 월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
			},
			value : new Date()
	};

	$("input[id^='monthPicker']").MonthPicker(options);
	$(".monthPicker").val(fnGetToMon());

	$(".datepickerBtn").click(function (event) {
    // 버튼에 속성에 data-target-id="datepicker1" 처럼 Trage이 되는 date picker ID를 넣어줘야함
    // 예시로 UI-PORD-0601.html 에서 data-target-id 로 검색
    event.preventDefault();

    if ($(this).data("target-id")) {
      $("#" + $(this).data("target-id")).click();
    } else {
      $("#datepicker").click();
    }
  });
});

