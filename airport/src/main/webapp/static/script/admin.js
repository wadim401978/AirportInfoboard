/**
 * switchAllCheckers()
 */

 function changeActive() {
	 
	let chkBox = document.getElementById("activeSwitcher");
	let inputBox = document.getElementById("active");
	inputBox.value = chkBox.checked;
	 
 }
 
function switchAllCheckers(e) {
	let checked = e.checked;
	let chekersCollection = document.forms["deleteCheckers"].getElementsByTagName("input");
	chekersArray = Array.prototype.slice.call(chekersCollection);
	chekersArray.forEach(function(item, index, array) {
		if (item.type == "checkbox" && item != e) {
			item.checked = checked;
		}
	});
}