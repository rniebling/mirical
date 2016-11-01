/** Prevent Enter-Key */
var stopSubmit = false;

$('#contentBox').find('input').bind('keydown', function(e) {
	if(!($(this).hasClass('ui-autocomplete-input'))) {
           if (e.keyCode == 13 || e.which == 13 ) {
                  stopSubmit = true;
                  return false;
           }
    }
});


$("#mainForm").submit(function(e) {
	if (stopSubmit) {
		e.preventDefault();
	}
})