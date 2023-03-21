/**
 * the small airport infoboard
 */

function change(msg) {
		for(i=0;i<$(msg).length;i++) {
			if($(msg)[i].nodeName=="TITLE"||$(msg)[i].nodeName=="FOOTER"||$(msg)[i].nodeName=="MAIN"||$(msg)[i].nodeName=="HEADER") {
//				console.log($(msg)[i].nodeName + ": " + $(msg)[i].innerHTML);
				if($(msg)[i].nodeName=="MAIN") {
					$('main').html($(msg)[i].innerHTML);
				}
				if($(msg)[i].nodeName=="TITLE") {
					$('TITLE').html($(msg)[i].innerHTML);
				}
				if($(msg)[i].nodeName=="HEADER") {
					$('HEADER').html($(msg)[i].innerHTML);
				}
				if($(msg)[i].nodeName=="FOOTER") {
					$('FOOTER').html($(msg)[i].innerHTML);
				}
			}
		}
}

function ContentRotator(url, counter) {

	$.ajax({
		url: url, 
		type: "POST", 
		dataType: "html", 
		cache: false,
		context: document,
		data: { langid: "" + counter },
		success: function(msg) { 
			console.log('ok. DocumentFragment.length=' + $(msg).length+ ' ' + counter);
		},
		error: function(response) { 
			console.log('bad: ' + response.statusText );
		}
	}).done(function(msg) {
		change(msg);
	});

}

//function runInterval(url, timeOut, langCount) {
//	let languageId = 1;
//	setInterval(function() {
//		
//		if(languageId < langCount) {
//			++languageId;
//		} else {
//			languageId = 1;
//		}
//		
//		ContentRotator(url, languageId);
//	}, timeOut);
//}

function runInterval(url, timeOut, activeLangs) {
	let langArrayId = 0;
	
	jsonActiveLangs = JSON.parse(activeLangs);
	console.log(jsonActiveLangs[langArrayId]);
	setInterval(function() {
		if (jsonActiveLangs.length > 0) {
			ContentRotator(url, jsonActiveLangs[langArrayId]);
			if (langArrayId < jsonActiveLangs.length) {
				langArrayId++;
			} else {
				langArrayId = 0;
			}
		}
	}, timeOut);
}



//var getKeys = function(obj) {
//	var keys = [];
//	for (var key in obj) {
//		keys.push(key);
//	}
//	return keys;
//}



