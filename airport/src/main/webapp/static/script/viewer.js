/**
 * 
 */
function test() {
	alert('Hello, world!')
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
			console.log('ok. DocumentFragment.length=' + $(msg).length);
		},
		error: function(response) { // Данные не отправлены
			console.log('bad: ' + response.statusText);
		}
	}).done(function(msg) {
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
		
	});

}

var getKeys = function(obj) {
	var keys = [];
	for (var key in obj) {
		keys.push(key);
	}
	return keys;
}

function runInterval(url, timeOut) {
	let i = 0;
	setInterval(function() {
		if(i<4) {++i;} else {i=0;}
		ContentRotator(url, i);
	}, timeOut);
	
}

