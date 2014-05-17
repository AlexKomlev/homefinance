/**
 * Created with IntelliJ IDEA.
 * User: Пользователь
 * Date: 27.06.13
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */

/**
 * SSS.
 * @param data
 * @param hidden
 * @returns {string}
 */
function withTd(data, hidden) {
    if (hidden == true) {
        return "<td style='display: none'>" + data + "</td>"
    } else
        return "<td>" + data + "</td>"
}

/**
 *
 * @param id
 * @param name
 * @returns {string}
 */
function createOption(id, name) {
    return '<option value="' + id + '">' + name + '</option>';
}


function hfAjax(type, url, successCallback, errorCallback, requestData) {
    if (type == "GET") {
        $.ajax(
            {
                type: "GET",
                cache: false,
                url: url,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: successCallback,
                error: errorCallback
            }
        )
    } else {
        $.ajax(
            {
                type: "POST",
                cache: false,
                url: url,
                data: requestData,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: successCallback,
                error: errorCallback
            }
        )
    }
}

function buildHash(data, id, value) {
    var newArr = [];
    for (i in data) {
        var elem = data[i];
        newArr[elem[id]] = elem[value];
    }
    return newArr;
}

function getElemByKey(data, key, vkey, value) {
    for (i in data) {
        var elem = data[i];
        if (elem[key] == vkey) {
            return elem[value]
        }
    }
    return "";
}