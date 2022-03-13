"use strict";

function doFillForm() {
	
    var user = window.user;

    if (window.user) {
        $('#id').val(user.id);
        $('#name').val(user.name);
        $('#address').val(user.address);
        $('#email').val(user.email);
        $('#piva').val(user.piva);
        $('#cfisc').val(user.cfisc);
        $('#tel').val(user.tel);

        //alert(JSON.stringify(window.user));
    }
}

function doHome() {
    window.location = "./";
}

function doLogin() {
    var params = {
        email: $('#email').val(),
        password: $('#password').val()
    };

    //alert(JSON.stringify(params));

    $.ajax({
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        async: true,
        data: JSON.stringify(params),
        url: "./api/login",
        success: function (resp) {
            window.user = resp;
            $('html').load('./registrazione', function () {
                doFillForm();
            });
        },
        error: function (err) {
            alert("LOGIN ERRATO...");
        }
    });
}


function doRegistrazione() {
    var params = {
        id: $('#id').val(),
        name: $('#name').val(),
        address: $('#address').val(),
        email: $('#email').val(),
        piva: $('#piva').val(),
        cfisc: $('#cfisc').val(),
        tel: $('#tel').val()
    };

    //alert(JSON.stringify(params));

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        dataType: 'json',
        async: true,
        data: JSON.stringify(params),
        url: "./api/",
        success: function (resp) {
            window.user = resp;
            //alert(JSON.stringify(window.user));
            $('body').load('./registrazione', function () {
                doFillForm();
            });
        },
        error: function (err) {
            alert("REGISTRAZIONE ERRATA");
        }
    });
}
