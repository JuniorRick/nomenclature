$.expr[":"].contains = $.expr.createPseudo(function(arg) {
    return function( elem ) {
        return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
    };
});


var allRows = $("tr");
$("input#search").on("keydown keyup", function() {
  allRows.hide();
  $('thead tr').show();
  $('tfoot tr').show();
  $("tr:contains('" + $(this).val() + "')").show();
});


$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
})