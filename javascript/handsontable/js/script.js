var grid = document.getElementById('grid');

var table = new Handsontable(grid, {
    data: [
        ['one', 'two', 'three'],
        ['four', 'five', 'six'],
        ['seven', 'eight', 'nine']
    ],
    search: true
});

table.search.query('T', null, function (query, value) {
    console.dir(arguments);
  
    if (typeof query == 'undefined' || query == null || !query.toLowerCase || query.length === 0) {
        return false;
    }
    
    return value.toString().toLowerCase().indexOf(query.toLowerCase()) !== -1;
});
