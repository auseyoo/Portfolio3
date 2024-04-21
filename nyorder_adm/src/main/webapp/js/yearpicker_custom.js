
$(document).ready(function () {
	$('.yearpicker').yearpicker({
		year: 2022,
		startYear: 2019,
	});

	$(".datepickerBtn").click(function (event) {
		event.preventDefault();
	
		if ($(this).data("target-id")) {
		  $("#" + $(this).data("target-id")).click();
		}
	});
});

