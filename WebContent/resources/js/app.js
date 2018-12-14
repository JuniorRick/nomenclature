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

$(document).ready(function() {
	$('input[type="submit"]').click(function(e) {

		if($('select').val() == "NONE") {
			e.preventDefault();
			$('select').css('border-color', '#dc3545')
			.css('box-shadow', '0px 0px 1px 1px #dc3545');
		}else {
			$('select').css('border-color', '#ced4da')
			.css('box-shadow', 'none');
		}
	})
})
