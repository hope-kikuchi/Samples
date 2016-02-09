var grid = document.getElementById('grid');

var data = [
    [1, 2, 3],
    [2, 3, 4],
    [3, 4, 5]
];

new Handsontable(grid, {
    data: data,
    mergeCells: true,
    contextMenu: ['mergeCells']
});
