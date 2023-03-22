/**
 * the small airport infoboard
 */

function change(msg) {
	for (i = 0; i < $(msg).length; i++) {
		if ($(msg)[i].nodeName == "TITLE" || $(msg)[i].nodeName == "FOOTER" || $(msg)[i].nodeName == "MAIN" || $(msg)[i].nodeName == "HEADER") {
			if ($(msg)[i].nodeName == "MAIN") {
				$('main').html($(msg)[i].innerHTML);
			}
			if ($(msg)[i].nodeName == "TITLE") {
				$('TITLE').html($(msg)[i].innerHTML);
			}
			if ($(msg)[i].nodeName == "HEADER") {
				$('HEADER').html($(msg)[i].innerHTML);
			}
			if ($(msg)[i].nodeName == "FOOTER") {
				$('FOOTER').html($(msg)[i].innerHTML);
			}
		}
	}
}

function ContentRotator(url, param) {
	paramId = JSON.parse(param);
	$.ajax({
		url: url, 
		type: "POST", 
		dataType: "html", 
		cache: false,
		context: document,
		data: paramId,
		success: function(msg) { 
			console.log('ok. DocumentFragment.length=' + $(msg).length+ ' ' + param);
		},
		error: function(response) { 
			console.log('bad: ' + response.statusText + " " + param);
		}
	}).done(function(msg) {
		change(msg);
	});
}

function runInterval(langUrl, timeOut, languagesArray, announcmentsArray) {
	let languageIndex = 0;//langs array increment index
	let hasAnnouncments; 
	let announcmentIndex = -1;//announcments array increment index
	let infoUrl = "info.html";
	
	jsonActiveLangs = JSON.parse(languagesArray);
	jsonAnnouncments = JSON.parse(announcmentsArray);
	
	if(jsonAnnouncments.length == 0) {
		hasAnnouncments = false;
	} else {
		hasAnnouncments = true;
	}
	
	
	setInterval(function() {
		if (jsonActiveLangs.length > 0) {
			if (languageIndex >= jsonActiveLangs.length) {
				if(hasAnnouncments) {
					languageIndex = -1;
				} else {
					languageIndex = 0;
				}
			}
			
			if (languageIndex == -1) {
				announcmentIndex++;
				if(announcmentIndex >= jsonAnnouncments.length) {
					announcmentIndex = 0;
				}
				
				console.log("announcmentIndex: " + announcmentIndex);
				param = JSON.stringify({ blockid: "" + jsonAnnouncments[announcmentIndex] });
				url = infoUrl;
				
			} else {
				param = JSON.stringify({ langid: "" + jsonActiveLangs[languageIndex] });
				url = langUrl;
			}
			
			ContentRotator(url, param);
			languageIndex++;
		}
		
	}, timeOut);
}