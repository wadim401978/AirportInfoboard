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

function extractIds(msg) {
	console.log('ok' + msg);
	return 1;
}

function getRestWebServiceValue(url) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", url, false); // false for synchronous request
	xmlHttp.send(null);
	return xmlHttp.responseText;
 }
 
function postRestWebServiceValue(url, param) {
	jsonParam = JSON.parse(param);
	let http = new XMLHttpRequest();
	let body = "langid=" + "" + encodeURIComponent(jsonParam.langid) + "";
	http.open("POST", url, false); // false for synchronous request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	http.send(body);
	return http.responseText;
 }
 
function runRotator(langUrl, timeOut) {
	let languagesArray = getRestWebServiceValue('langsIds');
	let announcmentsArray = getRestWebServiceValue('blocksIds');
	let languageIndex = 0;//langs array increment index
	let hasAnnouncments; 
	let announcmentIndex = -1;//announcments array increment index
	let infoUrl = "info.html";
	
	console.log(announcmentsArray);
	
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

function runRotator1(langUrl) {
	let languagesArray = getRestWebServiceValue('langsIds');
	let announcmentsArray = getRestWebServiceValue('blocksIds');
	let langTimeOut = parseInt(getRestWebServiceValue('timeout'), 10);
	let languageIndex = 0;//langs array increment index
	let announcmentsIndex = 0;
	
	console.log(announcmentsArray);
	
	jsonActiveLangs = JSON.parse(languagesArray);
	jsonAnnouncments = JSON.parse(announcmentsArray);
	
	let timeOut = langTimeOut * jsonActiveLangs.length;
	if(jsonAnnouncments.length > 0) {
		timeOut += langTimeOut;
		jsonActiveLangs.push(jsonActiveLangs[0]);
	}
	
	
	setTimeout(function run() {
		
			languageIndex = getRestWebServiceValue('nextlang' + languageIndex);
			param = JSON.stringify({ langid: "" + languageIndex });
			
			langTimeOut = parseInt(getRestWebServiceValue('timeout'), 10);
			if((jsonActiveLangs.length - languageIndex) == 1) {//TODO
				isActiveAnnotations = true;//TODO
				announcmentsIndex = 1; //TODO
				announcement = getRestWebServiceValue('tb' + announcmentsIndex);
				console.log(announcement);
				document.getElementById('modalBody').innerHTML = announcement;
				langTimeOut *= 2;
			} else {
				isActiveAnnotations = false;
				document.getElementById('modalBody').innerHTML = '';
			}
			
			
			msg = postRestWebServiceValue(langUrl, param);
			change(msg);
			showAnnotation(isActiveAnnotations);		
			
		setTimeout(run, langTimeOut);
		
	}, langTimeOut);
	
}






function runClock() {
	let timerId = setTimeout(function tick() {
		let now = new Date();
		let clock = document.getElementById('clock');
		if(clock!=null) {
			clock.innerHTML = now.toLocaleTimeString();
		}
		
		timerId = setTimeout(tick,1000);
	}, 1000);
}

//text effect for infoboard letters
function printWriter(selector, newSelectorId, durationValue, delayParameter, className) {
	var textWrapper = document.querySelector(selector);
	let replaceValue= "<span class='"+ className +"' id='" + newSelectorId +"'>$&</span>"; 
	textWrapper.innerHTML = textWrapper.textContent.replace(/\S/g, replaceValue);
	let targetsValue = '' + selector + ' #' +newSelectorId;
	anime.timeline({ loop: false })
		.add({
			targets: targetsValue,
			opacity: [0, 1],
			easing: "easeInOutQuad",
			duration: durationValue,
			delay: (el, i) => delayParameter * (i + 1)
		});
}

function showAnnotation(isActiveAnnotations) {
	if (isActiveAnnotations) {
		$("#testId")
			.hide()// hides it first, or style it with 'display: none;' instead
			.delay(4000) 
			.fadeIn(1000) // fades it in
			.delay(2000) // (optionally) waits
			.fadeOut(1000); // (optionally) fades it out
	}
}



