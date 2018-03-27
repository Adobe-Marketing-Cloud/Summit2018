/*global jQuery: false, moment: false, Granite: false*/
jQuery(function ($) {
    "use strict";

    function applyComponentStyles() {
        moment.locale("en");

        // Teaser - Hero style
        $(".cmp-teaser--hero").not("[data-cmp-teaser-hero-processed='true']").each(function () {
            var hero = $(this).attr("data-cmp-teaser-hero-processed", true),
                image = hero.find(".cmp-image__image");

            if (image && image.attr("src")) {
                hero.css("background-image", "url('" + image.attr("src") + "')");
            }
        });


        // Teaser - Post style
        $(".cmp-teaser--post .cmp-teaser").not("[data-cmp-teaser-posted-at-processed='true']").each(function () {
            var teaser = $(this).attr("data-cmp-teaser-posted-at-processed", true),
                // Get the teaser's Link element
                link = teaser.find(".cmp-teaser__link"),
                // Determine get the teaser's link if it exists
                linkHref = link ? link.attr('href') : null,
                dateUrl;

            if (linkHref) {
                // Get the current page's path and append Content Services model.json
                dateUrl = Granite.HTTP.getPath(linkHref) + ".model.json";

                // Make an AJAX call to the page's AEM Content Services end-point to retrieve the data.
                $.getJSON(dateUrl, function (jsonData) {
                    var dateObject, title, postedAtElement;

                    // Create a date object using the open source MomentJS library
                    dateObject = moment(jsonData.lastModifiedDate);

                    // Find the component's Title element
                    title = teaser.find('.cmp-teaser__title');

                    // Create a new Paragraph <p> element to display the formatted date.
                    postedAtElement = $("<p>")
                    // Assign a BEM CSS Class name so it can be styled via CSS
                        .addClass("cmp-teaser__posted-at")
                        // Add the formatted date text to the Paragraph
                        .text("Posted " + dateObject.fromNow());


                    // Inject the new paragraph tag into the DOM after the title element.
                    title.after(postedAtElement);
                });
            }
        });
    }

    applyComponentStyles();

    $(".responsivegrid").bind("DOMNodeInserted", applyComponentStyles);
});
