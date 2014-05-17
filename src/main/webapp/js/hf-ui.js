/**
 *
 *
 *
 *
 */

/**
 * Populates Select Element with data
 * @param selectId Id of Select element
 * @param data JSON data
 * @param idName name of Id element
 * @param valName name of Value element
 */
function prepareSelelect(selectId, data, idName, valName) {
    $(selectId).empty();
    for (var i in data) {
        var elem = data[i];
        $(selectId).append(new Option(elem[valName], elem[idName]));
    }
}
    function prepareTable(tableId, data, headers) {
        var cellData;
        for(var i in data){
            var elem = data[i];
            var column = '<tr>';
            for (var j in headers){
                if(typeof headers[j].perform != 'undefined'){
                    cellData = headers[j].perform(elem[headers[j].id]);
                } else {
                    cellData = elem[headers[j].id];
                }
                column = column + '<td>' + cellData + '</td>';
            }
            column = column + '</tr>';
            $(tableId).append(column);
        }

}

