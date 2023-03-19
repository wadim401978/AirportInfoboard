/**
 * 
 */
function test() {
	alert('Hello, world!')
}

function ContentRotator(counter) {
	//	++counter;

	let url = "arrdep.html"
	//  test();
	$.ajax({
		url: url, //url страницы (action_ajax_form.php)
		type: "POST", //метод отправки
		dataType: "html", //формат данных
		cache: false,
//		context: document,
		data: { langid: "" + counter },
		//		data: $("#goodform").serialize(),
		success: function(msg) { //Данные отправлены успешно
			console.log('good');
			
//			console.log(div1);
//						$("main").replaceWith("<main>" + counter + "</main>");

		},
		error: function(response) { // Данные не отправлены
			console.log('bad');
		}
	}).done(function(msg) {
//		var body = $("#body", msg);
//		$('#body').html(body);
//		var div1 = $("#div1", msg);
//		$('#div1').html(div1);
//		var footer = $("#footer", msg);
//		$('#footer').html(footer);

$("#div1").load("arrdep.html #div1");		
		console.log(msg);
		
//		var main = $("main", msg);
//		$('main').html(main);
		
		
		
	});

	//let post = JSON.stringify(postObj)


}

var getKeys = function(obj) {
	var keys = [];
	for (var key in obj) {
		keys.push(key);
	}
	return keys;
}



$(document).ready(function() {
	//	ContentRotator();
	//	setInterval(ContentRotator, 4000);
	
//	counter = 0;
//	let timerId = setInterval(() => ContentRotator(++counter), 3000);
	
	
	let i = 0;
	setInterval(function() {
		if(i<4) {++i;} else {i=0;}
		ContentRotator(i);
	}, 1000);
	
});