document.addEventListener("DOMContentLoaded", function() {
  checkboxOn();
});

function checkboxOn(){

	var elements = document.getElementsByClassName("form-check-input");

	for (i=0; i<elements.length ; i++){
		elements[i].checked = true;

	}
}
