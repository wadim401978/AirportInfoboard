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



//function runInterval(langUrl, timeOut, activeLangs, announcments) {
//	let langArrayId = 0;
//	let annArrayId;
//	let infoUrl = "info.html";
////	console.log("n: " + announcments);
//	jsonActiveLangs = JSON.parse(activeLangs);
//	jsonAnnouncments = JSON.parse(announcments);
//	if(jsonAnnouncments.length==0) {
//		annArrayId = -1;
//	} else {
//		annArrayId = 0;
//	}
//	console.log("ann: " + jsonAnnouncments[0]);
//	setInterval(function() {
//		if (jsonActiveLangs.length > 0) {
//			
//			langArrayId++;
//			if (langArrayId >= jsonActiveLangs.length) {
//				langArrayId = 0;
//			}
//				param = JSON.stringify({ langid: "" + jsonActiveLangs[langArrayId] });
//				url = langUrl;
//			
//			
//			ContentRotator(url, param);
//		}
//	}, timeOut);
//}

function runInterval(langUrl, timeOut, activeLangs, announcments) {
	let langArrayId = 0;//langs array increment index
	let hasAnnouncments; 
	
	let annId = 0;//announcments array increment index
	let infoUrl = "info.html";
//	console.log("n: " + announcments);
	jsonActiveLangs = JSON.parse(activeLangs);
	jsonAnnouncments = JSON.parse(announcments);
	if(jsonAnnouncments.length==0) {
		hasAnnouncments = false;
	} else {
		hasAnnouncments = true;
	}
	
	
	setInterval(function() {
		if (jsonActiveLangs.length > 0) {
			console.log("setInterval: " + langArrayId);
//			langArrayId++;
			if (langArrayId >= jsonActiveLangs.length) {
//				langArrayId = 0;
//				langArrayId = -1;
				if(hasAnnouncments) {
					langArrayId = -1;
				} else {
					langArrayId = 0;
				}
				
				
				
			}
			
			if (langArrayId == -1) {
				param = JSON.stringify({ blockid: "" + jsonAnnouncments[annId] });
				url = infoUrl;
				//?????
					if(annId > jsonAnnouncments.length) {
						annId = 0;
					} else {
						annId++;
					}
				
//				langArrayId = 0;
			} else {
				param = JSON.stringify({ langid: "" + jsonActiveLangs[langArrayId] });
				url = langUrl;
			}
			console.log("sum++: " + langArrayId);
			
			ContentRotator(url, param);
			langArrayId++;
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



