
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

function confirmDelete(formId, textConfirm) {
	let thisForm = document.getElementById(formId);
	thisForm.onsubmit = (e) => {
		e.preventDefault()
		let confirmSubmit = confirm(textConfirm);
		if (confirmSubmit) {
			document.getElementById('deleteCheckers').submit();
		}
	}
}

function delLangRow(rowId, confirm_label) {
	console.log(rowId);
	confirmed = confirm(confirm_label);
	if (confirmed) {
		row = document.getElementById(rowId);
		row.remove();
	}
}

/**insert selected value from popup window **/
function setChosenId(id_dest, name_dest, id_src, e) {
	elementDestId = document.getElementById(id_dest);
	elementSrcId = document.getElementById(id_src);
	elementDestId.value = elementSrcId.value;
	elementDestName = document.getElementById(name_dest);
	elementDestName.value = Array.from(e.children)[0].innerHTML.trim();
}

//******INSERT table row functions******/
function getAirNameInput(selectedLanguageId) {
	let inputAirEntityName = document.createElement('input');
	inputAirEntityName.type = "text";
	inputAirEntityName.className = "langname";
	inputAirEntityName.name = "name" + selectedLanguageId;
	return inputAirEntityName;
}

function getDeletingInput(trId, delete_label) {
	let inputDeleteRow = document.createElement('input');
	inputDeleteRow.type = "button";
	inputDeleteRow.value = "-";
	inputDeleteRow.title = delete_label;
	inputDeleteRow.onclick = function() {
		delLangRow(trId);
	};
	return inputDeleteRow;
}

function getKeyInput(selectedLanguageId) {
	let inputHidden = document.createElement('input');
	inputHidden.type = "hidden";
	inputHidden.value = selectedLanguageId
	inputHidden.name = "langId" + selectedLanguageId;
	return inputHidden;
}

function getInputEmpty() {
	let inputEmpty = document.createElement('input');
	inputEmpty.type = 'hidden';
	inputEmpty.name = 'isEmpty';
	inputEmpty.value = '1';
	return inputEmpty;
}

function addLangRow(e, delete_label, alert_label) {
	let elements =  Array.from(e.children);
	let selectedLanguageId = elements[0].value;
	let tbody = document.getElementById('airNamesTbody');
	let tbodyLenght = tbody.children.length;
	
	let trId = 'trid' + selectedLanguageId;
	let addNewRow = true;
	if (tbodyLenght == 0) {
		addNewRow = true; 
	} else {
		let tBodyChildren = Array.from(tbody.children);
		for (let i = 0; i < tbodyLenght; i++) {
			let iterableTrid = tBodyChildren[i].id;
			if (iterableTrid == trId) {
				addNewRow = false;
			}
		}
	}
	
	if (addNewRow) {
		let tr = document.createElement('tr');
		tr.id = trId;
		
		let td1 = document.createElement('td');
		td1.append(getKeyInput(selectedLanguageId));
		td1.append(getInputEmpty());
		
		let span = document.createElement('span');
		span.innerHTML = elements[1].innerHTML;
		td1.append(span);
		tr.append(td1);
		
		let td2 = document.createElement('td');
		td2.append(getAirNameInput(selectedLanguageId));
		tr.append(td2);
		
		let td3 = document.createElement('td');
		td3.append(getDeletingInput(trId, delete_label));
		tr.append(td3);
		
		tbody.append(tr);
	} else {
		alert(alert_label);
	}
	
}
//-------------------------

function showFiles(e) {
	console.log(e);
}

function jFold() {
	
}
