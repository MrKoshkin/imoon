function createCookie(name, value, mins) {
    if (mins) {
        var date = new Date();
        date.setTime(date.getTime() + (mins * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    } else {
        var expires = "";
    }
    var decodeValue = encodeURI(value);
    document.cookie = name + "=" + decodeValue + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return unescape(c.substring(nameEQ.length, c.length));
    }
    return null;
}

function selectizeSimple(selector, options, items, onChangeFunc) {
    $(selector).selectize({
        persist: false,
        plugins: ['remove_button'],
        maxItems: 1,
        valueField: 'id',
        labelField: 'name',
        searchField: ['name'],
        sortField: "sortOrder",
        options: options,
        items: items,
        render: {
            item: function (item, escape) {
                return '<div><span>' + item.name + '</span></div>';
            },
            option: function (item, escape) {
                return '<div class="custom-option"><div>' + item.name + '</div></div>';
            }
        }
    }).on("change", onChangeFunc);
}

function selectizeNameAndDesc(selector, options, items, onChangeFunc) {
    $(selector).selectize({
        persist: false,
        plugins: ['remove_button'],
        maxItems: 1,
        valueField: 'id',
        labelField: 'name',
        searchField: ['name', 'desc'],
        sortField: "sortOrder",
        options: options,
        items: items,
        render: {
            item: function (item, escape) {
                return '<div><span>' + item.name + '</span></div>';
            },
            option: function (item, escape) {
                return '<div class="custom-option"><div>' + item.name + '</div><div>' + item.desc + '</div></div>';
            }
        }
    }).on("change", onChangeFunc);
}

function selectizeNameAndDescAndGroups(selector, options, optgroups, items, onChangeFunc) {
    $(selector).selectize({
        persist: false,
        plugins: ['remove_button'],
        maxItems: 1,
        valueField: 'id',
        optgroupField: 'class',
        labelField: 'name',
        searchField: ['name', 'desc'],
        sortField: "sortOrder",
        options: options,
        optgroups: optgroups,
        items: items,
        render: {
            item: function (item, escape) {
                return '<div><span>' + item.name + '</span></div>';
            },
            option: function (item, escape) {
                return '<div class="custom-option"><div>' + item.name + '</div><div>' + item.desc + '</div></div>';
            },
            optgroup_header: function(data, escape) {
                return '<div class="optgroup-header">' + escape(data.label)  + '</div>';
            }
        }
    }).on("change", onChangeFunc);
}

function selectizeMultiply(selector, options, items, onChangeFunc)  {
    $(selector).selectize({
        plugins: ['remove_button'],
        delimiter: ',',
        persist: false,
        maxItems: 20,
        valueField: 'id',
        labelField: 'name',
        searchField: ['name', 'desc'],
        sortField: "sortOrder",
        options: options,
        items: items,
        render: {
            item: function (item, escape) {
                return '<div><span>' + item.name + '</span></div>';
            },
            option: function (item, escape) {
                return '<div class="custom-option"><div>' + item.name + '</div><div>' + item.desc + '</div></div>';
            }
        }
    }).on("change", onChangeFunc);
}

function getCommonValidateRules(rules, messages, formID) {
    return {
        rules: rules,
        messages: messages,
        ignore: "",
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass);
            $(formID + " select.required").each(function () {
                if ($(this).val() === "") {
                    $(this).parent().children(".selectize-control").addClass("error");
                }
            });
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass);
            $(formID + " select.required").each(function () {
                if ($(this).val() !== "") {
                    $(this).parent().children(".selectize-control").removeClass("error");
                }
            });
        }
    };
}

function charCyr2lat(ch) {
    switch (ch) {
        case 'А':
            return "A";
        case 'Б':
            return "B";
        case 'В':
            return "V";
        case 'Г':
            return "G";
        case 'Д':
            return "D";
        case 'Е':
            return "E";
        case 'Ё':
            return "Yo";
        case 'Ж':
            return "Zh";
        case 'З':
            return "Z";
        case 'И':
            return "I";
        case 'Й':
            return "Y";
        case 'К':
            return "K";
        case 'Л':
            return "L";
        case 'М':
            return "M";
        case 'Н':
            return "N";
        case 'О':
            return "O";
        case 'П':
            return "P";
        case 'Р':
            return "R";
        case 'С':
            return "S";
        case 'Т':
            return "T";
        case 'У':
            return "U";
        case 'Ф':
            return "F";
        case 'Х':
            return "Kh";
        case 'Ц':
            return "Ts";
        case 'Ч':
            return "Ch";
        case 'Ш':
            return "Sh";
        case 'Щ':
            return "Shch";
        case 'Ъ':
            return "";
        case 'Ы':
            return "Y";
        case 'Ь':
            return "";
        case 'Э':
            return "E";
        case 'Ю':
            return "Iu";
        case 'Я':
            return "Ia";
        case 'а':
            return "a";
        case 'б':
            return "b";
        case 'в':
            return "v";
        case 'г':
            return "g";
        case 'д':
            return "d";
        case 'е':
            return "e";
        case 'ё':
            return "yo";
        case 'ж':
            return "zh";
        case 'з':
            return "z";
        case 'и':
            return "i";
        case 'й':
            return "y";
        case 'к':
            return "k";
        case 'л':
            return "l";
        case 'м':
            return "m";
        case 'н':
            return "n";
        case 'о':
            return "o";
        case 'п':
            return "p";
        case 'р':
            return "r";
        case 'с':
            return "s";
        case 'т':
            return "t";
        case 'у':
            return "u";
        case 'ф':
            return "f";
        case 'х':
            return "kh";
        case 'ц':
            return "ts";
        case 'ч':
            return "ch";
        case 'ш':
            return "sh";
        case 'щ':
            return "shch";
        case 'ъ':
            return "";
        case 'ы':
            return "y";
        case 'ь':
            return "";
        case 'э':
            return "e";
        case 'ю':
            return "iu";
        case 'я':
            return "ia";
        default:
            return ch;
    }
}

function cyr2lat(str) {
    var newStr = "";
    for (var i = 0; i < str.length; i++) {
        newStr += charCyr2lat(str.charAt(i));
    }

    return newStr;
}

function getTime(hours, minutes) {
    return (hours > 9 ? hours : "0" + hours) + ":" + (minutes > 9 ? minutes : "0" + minutes);
}

function checkDateOnZero(time) {
    return time > 9 ? time : "0" + time;
}

function scrollToAnchor(aid) {
    var aTag = $("a[name='" + aid + "']");
    $('html,body').animate({scrollTop: aTag.offset().top - 60}, 'slow');
}

function upCount(name) {
    var selector = "input[name=" + name + "]";
    $(selector).val(parseInt($(selector).val()) + 1);
    $(selector).change();
}

function downCount(name) {
    var selector = "input[name=" + name + "]";
    if (parseInt($(selector).val()) - 1 >= 0) {
        $(selector).val(parseInt($(selector).val()) - 1);
        $(selector).change();
    }
}

function confirmDeleteAction(url, title) {
    confirmSimpleAction(url, title, "btn-red", "")
}

function confirmDoAction(title, func) {
    confirmSimpleAction("", title, "btn-blue", func)
}

function confirmPositiveAction(url, title) {
    confirmSimpleAction(url, title, "btn-blue", "")
}

function confirmSimpleAction(url, title, btnClass, func) {
    $.confirm({
        backgroundDismiss: true, title: title, content: 'Вы точно уверены в этом?',
        buttons: {
            confirm: {
                text: "Да, уверен!",
                btnClass: btnClass,
                action: function () {
                    $(".global-loader").removeClass("hidden");
                    if (url.length > 0) {
                        document.location.href = url
                    } else {
                        eval(func);
                    }
                }
            },
            cancel: {
                text: "Отмена", action: function () {
                }
            }
        }
    });
}