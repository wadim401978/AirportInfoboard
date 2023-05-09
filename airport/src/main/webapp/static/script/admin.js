
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

function setChosenId(id_dest, name_dest, id_src, e) {
	elementDestId = document.getElementById(id_dest);
	elementSrcId = document.getElementById(id_src);
	elementDestId.value = elementSrcId.value;
	elementDestName = document.getElementById(name_dest);
	elementDestName.value = Array.from(e.children)[0].innerHTML.trim();
	//history.back();
}

function delLangRow(rowId) {
	console.log(rowId);
	row = document.getElementById(rowId);
	row.remove();
}

function addRow() {
	alert('under construction');
}
