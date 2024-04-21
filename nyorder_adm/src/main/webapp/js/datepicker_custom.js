$(document).ready(function () {
  $("input[id^='datepicker']").daterangepicker({
      singleDatePicker: true,
      showDropdowns: true,
      changeMonth: true,
      locale: {
        format: "YYYY-MM-DD",
        separator: " - ",
        applyLabel: "Apply",
        cancelLabel: "Cancel",
        fromLabel: "From",
        toLabel: "To",
        customRangeLabel: "Custom",
        weekLabel: "W",
        applyLabel: "확인",
        cancelLabel: "취소",
        daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
        monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월",],
        firstDay: 1,
      },
      showCustomRangeLabel: false,
    //   startDate: "2021-01-05",
      endDate: "2021-01-05",
    },
    function (start, end, label) {
      console.log(
        "New date range selected: " +
          start.format("YYYY-MM-DD") +
          " to " +
          end.format("YYYY-MM-DD") +
          " (predefined range: " +
          label +
          ")"
      );
    }
  );

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
