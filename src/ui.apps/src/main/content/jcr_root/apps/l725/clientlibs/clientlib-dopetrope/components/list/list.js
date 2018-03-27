/*global jQuery: false, moment: false*/

jQuery(function ($) {
    "use strict";

    function applyComponentStyles() {
        var PROCESSED_DATE_ACCENT = "data-cmp-list-date-accent-processed",
            INVALID_DATE = "Invalid date";

        moment.locale("en");

        $(".cmp-list--date-accent .cmp-list").not("[" + PROCESSED_DATE_ACCENT + "='true']").each(function () {
            var list = $(this).attr(PROCESSED_DATE_ACCENT, true);

            list.find(".cmp-list__item").each(function () {
                var item = $(this),
                    itemDate = $(item.find(".cmp-list__item-date")),
                    dateString,
                    dateObject,
                    month,
                    day;

                if (itemDate) {
                    dateString = itemDate.text();
                    if (dateString) {
                        dateObject = moment(dateString);
                        month = dateObject.format("MMM");
                        day = dateObject.format("D");

                        if (INVALID_DATE !== month && INVALID_DATE !== day) {
                            itemDate.html($("<span>" + month + "</span><span class='cmp-list__item-date--large'>" + day + "</span>"));
                        }
                    }
                }
            });
        });
    }

    applyComponentStyles();

    $(".responsivegrid").bind("DOMNodeInserted", applyComponentStyles);
});

