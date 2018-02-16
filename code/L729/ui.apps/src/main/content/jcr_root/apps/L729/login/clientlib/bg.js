/*
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 * Copyright 2012 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */

jQuery(function($){

    /*
     * Methods for changing the background based on a tenant string.
     *
     * AdobePatentID="3254US01"
     *
     * How it works:
     * when the user types the username, a key-up listener validates the input and as soon as the first 'dot' after the 'at'
     * is typed, the tenant string is guessed. After sanitizing the tenant name, it is verified if there is already the
     * background loaded and if not, then a new style sheet based on the tenant's name is loaded. If successful, the
     * style sheet is initialized and a new DIV element with the id <code>"bg_" + tenant</code> is added to the DOM.
     * If the background was already loaded it is ensured that its DIV is at the end of the list.
     *
     * Note: This solution provides an easy way to extend the styling of the background also in respect to media queries.
     * however, due to the nature of how the background is loaded, we don't know when the respective image is loaded and
     * thus it's not possible to provide a fancy transition between the backgrounds.
     *
     * Note: The default background is already included in the style sheet of the clientlib and initialized accordingly.
     */

    // current background object; initialize with default background
    var currentBg = {
        name: "default",
        $el: $("#bg_default")
    };

    // map of all already loaded background
    var backgrounds = {
        "default" : currentBg
    };

    // init key listener on username field + input/blur(ie) listener for autocomplete
    $("#username").on("keyup input blur", function(e) {
        var val = $(this).val();
        var i0 = val.indexOf("@");
        if (i0 < 0) {
            setBackground("default");
            return;
        }
        var i1 = val.indexOf(".", i0);
        if (i1 < 0) {
            setBackground("default");
            return;
        }
        var tenant = val.substring(i0 + 1, i1);
        setBackground(tenant);
    });


    /**
     * Switches the background for the given tenant
     * @param {String} tenant
     */
    function setBackground(tenant) {
        // sanitize tenant
        tenant = tenant.replace(/[^a-zA-Z0-9-_]/g, '');

        if (currentBg && currentBg.name == tenant) {
            return;
        }

        // check if already loaded
        var bg = backgrounds[tenant];
        if (!bg) {
            // create new bg object
            bg = backgrounds[tenant] = { name:tenant };

            // try to load bg
            $.ajax({
                url:"login/clientlib/resources/bg/" + tenant + "/bg.css",
                dataType:"text",
                statusCode: {
                  // Prevent Granite 403 response handling
                  403: function() {}
                },
                success: function(data){
                    // load new style data
                    $("head").append("<style>" + data + "</style>");

                    // create new background element
                    var id = "bg_" + tenant;
                    $("#backgrounds").append("<div class=\"background\" id=\"" + id + "\"></div>");
                    bg.$el = $("#" + id);
                    currentBg = bg;
                },
                error: function() {
                    //console.log("failed to load bg for " + tenant);

                }
            });
        } else {
            if (!bg.$el) {
                // console.log("background for " + tenant + " already loaded but not valid.");
                return;
            }
            // ensure bg is at the end of it's list
            bg.$el.detach();
            $("#backgrounds").append(bg.$el);
            currentBg = bg;
        }
    }

    /**
     * Internally switches to the new background
     * @param bg new background object
     * @private
     */
    function _switchBackground(bg) {
//        if (currentBg) {
//            currentBg.$el.hide();
//        }
//        bg.$el.show();
        currentBg = bg;
    }
});
