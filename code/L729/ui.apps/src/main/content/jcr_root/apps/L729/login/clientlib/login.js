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
(function(document){
    'use strict';

    /**
     *  Flush error
     */
    function flushError() {
        var el = document.getElementById('error');
        // Hide the error
        el.hidden = true;
        // clears the text
        el.content.innerHTML = '';
    }

    /**
     * Display error
     *
     * @param {String} message
     */
    function displayError(message) {
        var el = document.getElementById('error');
        // Display the error
        el.hidden = false;
        // adds the text inside the coral-Alert-message
        el.content.innerHTML = message;
    }

    /**
     * Clear all password fields.
     */
    function clearPasswords() {
        var elements = document.querySelectorAll('input[type=password]');
        for(var i = 0; i < elements.length; i++) {
            elements[i].value = '';
        }
    }

    /**
     * Show the change password form.
     */
    function showChangeForm() {
        displayError(document.getElementById('expired_message').value);

        document.getElementById('sign-in-title').innerHTML = document.getElementById('change_title').value;
        document.getElementById('submit-button').value = document.getElementById('change_submit_text').value;
        clearPasswords();

        document.getElementById('new_password').hidden = false;
        document.getElementById('confirm_password').hidden = false;
        document.getElementById('back-button').hidden = false;

        // click listener of the "Back" in the change password form; returns to the login form
        document.getElementById('back-button').on('click', function(e) {
            showLoginForm();
            e.preventDefault();
        });

        document.getElementById('password').focus();
    }

    /**
     * Show the login form.
     */
    function showLoginForm() {
        document.getElementById('sign-in-title').innerHTML = document.getElementById('login_title').value;
        document.getElementById('submit-button').value = document.getElementById('login_submit_text').value;
        clearPasswords();

        document.getElementById('new_password').hidden = true;
        document.getElementById('confirm_password').hidden = true;
        document.getElementById('back-button').hidden = true;

        document.getElementById('username').focus();

        flushError();
    }

    /**
     * Redirects after successful login or password change.
     */
    function redirect() {
        var u = document.getElementById('resource').value;
        if (window.location.hash && u.indexOf('#') < 0) {
            u = u + window.location.hash;
        }
        document.location = u;
    }

    /**
     * Serialize object to query string
     *
     * @param {Object} obj
     */
    function toQueryString(obj) {
        var parts = [];
        for (var i in obj) {
            if (obj.hasOwnProperty(i)) {
                parts.push(encodeURIComponent(i) + "=" + encodeURIComponent(obj[i]));
            }
        }
        return parts.join("&");
    }

    // Bind an event listener on login form to make an ajax call
    document.addEventListener("DOMContentLoaded", function(event) {
        document.getElementById('login').addEventListener('submit', function(event) {
            event.preventDefault();
            var form = this;
            var path = form.action;
            var user = form.j_username.value;
            var pass = form.j_password.value;

            // if no user is given, avoid login request
            if (!user) {
                return true;
            }

            var data = {
                _charset_: "utf-8",
                j_username: user,
                j_password: pass,
                j_validate: true
            };

            if (document.getElementById('new_password').hidden === false && document.getElementById('confirm_password').hidden === false) {
                var new_password = document.getElementById('new_password');
                var confirm_password = document.getElementById('confirm_password');
                // change password: check new and confirm passwords
                if (new_password.value.length === 0) {
                    // new password empty: error
                    clearPasswords();
                    new_password.focus();
                    displayError(document.getElementById('empty_message').value);
                    return false;
                } else if (new_password.value !== confirm_password.value) {
                    // passwords do not match: error
                    clearPasswords();
                    document.getElementById('password').focus();
                    displayError(document.getElementById('not_match_message').value);
                    return false;
                } else {
                    // passwords match: add new password to data
                    data["j_newpassword"] = document.getElementById('new_password').value;
                }
            }

            // Prepare and request the endpoint
            var xhr = new XMLHttpRequest();
            xhr.open('POST', path, false);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            xhr.onload = function() {
                if (xhr.readyState == XMLHttpRequest.DONE ) {
                    if (xhr.status == 200) {
                        if (document.getElementById('new_password').hidden && document.getElementById('confirm_password').hidden) {
                            // login without changing password
                            redirect();
                        }
                        else {
                            // login after changing password: show success dialog
                            var dialog = document.getElementById('success-dialog');

                            dialog.on('coral-overlay:close', function() {
                                redirect();
                            });

                            dialog.show();
                        }
                    }
                    else {
                        var reason = xhr.getResponseHeader("X-Reason-Code");
                        if (reason === "password_expired") {
                            // password expired
                            showChangeForm();
                        }
                        else {
                            // login invalid
                            var messageId = reason === "password_expired_and_new_password_in_history" ? "in_history_message" : "invalid_message";
                            displayError(document.getElementById(messageId).value);
                            clearPasswords();
                            document.getElementById('username').focus();
                        }
                    }
                }
            };

            xhr.send(toQueryString(data));

            return true;
        });
    });
})(document);
