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
	xmlHttp.open("GET", url, false); 
	xmlHttp.send(null);
	return xmlHttp.responseText;
 }
 
function postRestWebServiceValue(url, param) {
	jsonParam = JSON.parse(param);
	let http = new XMLHttpRequest();
	let body = "langid=" + "" + encodeURIComponent(jsonParam.langid) + "";
	http.open("POST", url, false); 
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	http.send(body);
	return http.responseText;
 }
 
function runRotator(langUrl) {
	let deflangid = parseInt(getRestWebServiceValue('deflangid'), 10);
	let langTimeOut = 0;
	const singleLangTimeOut = parseInt(getRestWebServiceValue('timeout'), 10);
	const doubleLangTimeOut = singleLangTimeOut * 2;
	let currlanguageIndex = deflangid;//langs array increment index
	let nextlanguageIndex = 0;//langs array increment index
	let announcmentsIndex = 0;
	
	setTimeout(function run() {
		nextlanguageIndex = getRestWebServiceValue('nextlang' + currlanguageIndex);
		param = JSON.stringify({ langid: "" + currlanguageIndex });
		msg = postRestWebServiceValue(langUrl, param);
		change(msg);
		
		if (nextlanguageIndex == deflangid) {
			announcmentsIndex = parseInt(getRestWebServiceValue('nexttb' + announcmentsIndex), 10); 
			if (announcmentsIndex > 0) {
				announcement = getRestWebServiceValue('tb' + announcmentsIndex);
				langTimeOut = doubleLangTimeOut;
				document.getElementById('modalBody').innerHTML = announcement;
				showAnnotation(singleLangTimeOut);
			}
		} else {
			document.getElementById('modalBody').innerHTML = '';
			langTimeOut = singleLangTimeOut;
		}
		
		currlanguageIndex = nextlanguageIndex;
		setTimeout(run, langTimeOut);
		
	}, langTimeOut);
}

function showAnnotation(langTimeOut) {
	let fadeTimeOut = 0;
	let delayTimeOut = 0;
	if (langTimeOut >= 3000) {
		fadeTimeOut = 1000;
		delayTimeOut = langTimeOut - fadeTimeOut * 2;
	} else {
		delayTimeOut = langTimeOut;
	}
	$("#testId")
		.hide()
		.delay(langTimeOut) 
		.fadeIn(fadeTimeOut) 
		.delay(delayTimeOut) 
		.fadeOut(fadeTimeOut); 
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

