(function ($) {
    $.fn.daterange = function (options) {
        var defaults = {
            firstDay: 1,
            changeFirstDay: false,
            dateFormat: "yy-mm-dd",
            showOn: "button",
            buttonImage: '',
            buttonImageOnly: true,
            startDatePickerId: 'startDate',
            endDatePickerId: 'endDate',
            minDate: new Date(2011, 0, 1),
            maxDate: new Date(),
            dayRange: 365
        };
        options = $.extend(defaults, options);

        function customRange(input) {
            var dateMin = options.minDate;
            var dateMax = null;

            if (input.id == options.startDatePickerId) {
                if ($("#" + options.startDatePickerId).datepicker("getDate") != null) {
                    dateMax = $("#" + options.endDatePickerId).datepicker("getDate");
                    dateMin = $("#" + options.endDatePickerId).datepicker("getDate");
                    dateMin.setDate(dateMin.getDate() - options.dayRange);
                    if (dateMin < options.min) {
                        dateMin = options.min;
                    }
                } else {
                    dateMax = options.maxDate; //Set this to your absolute maximum date
                }
            } else if (input.id == options.endDatePickerId) {
                dateMax = new Date(); //Set this to your absolute maximum date
                if ($("#" + options.startDatePickerId).datepicker("getDate") != null) {
                    dateMin = $("#" + options.startDatePickerId).datepicker("getDate");
                    var rangeMax = new Date(dateMin.getFullYear(), dateMin.getMonth(), dateMin.getDate() + options.dayRange);

                    if (rangeMax < dateMax) {
                        dateMax = rangeMax;
                    }
                }
            }
            return {
                minDate: dateMin,
                maxDate: dateMax
            };
        }

        return this.each(function () {
            $('#' + options.startDatePickerId + ', #' + options.endDatePickerId).datepicker(
            {
                firstDay: options.firstDay,
                beforeShow: customRange,
                changeFirstDay: options.changeFirstDay,
                dateFormat: options.dateFormat,
                showOn: options.showOn,
                buttonImage: options.buttonImage,
                buttonImageOnly: options.buttonImageOnly
            });
        });
    };
    
})(jQuery);
