$(document).ready(function(){		
	$("#input_text").mousedown(function(){
		// $.post("/searchAllList", function(data, status){
		// 	console.log("qqqq" + data);
		// 	for (name in data) {
		// 		var $newElement=$('<li>'+ data[name] + '</li>');
		// 		$newElement.appendTo($(".search-ul"))
		// 	}
		// });

		$("#search-input-box").css({
			"min-height":"100px",
			"max-height":"200px",
			"height":"auto",
			"overflow":"hidden",
			"border":"2px solid rgba(71,149,243,1)",
			"border-radius": "5px 0 5px 5px",
			"background-color":"white"
		});

		$("#input_text").css({
			"width":"300px",
			"border":"2px solid transparent",
			"margin":" 0px",
			"border-bottom":"1px solid rgba(71,149,243,1)",
			"border-radius": "0"
		});
		$(".search-ul").css("display","block");

	});
	
	
	$(document).on('click', function(e) {
		const tmp = $(e.target).attr('id');
		if(tmp !== 'search-input-box' && tmp !== 'input_text') {
			// 点击后的操作
			console.log(e.id);
			$("#search-input-box").css({
				"min-height":"42px",
				"max-height":"42px",
				"height":"42px",
				"border":"2px solid transparent",
				"border-radius": "5px 0 0 5px",
				"overflow":"inherit"
			});
			$("#input_text").css({
				"width":"304px",
				"border":"2px solid rgba(71,149,243,1)",
				"margin": "-2px"
			});

			$(".search-ul").css("display","none");
//	     	$("ul").remove();
		}
 	});
	$("li").hover(function(){        //鼠标悬停时的颜色
		$(this).css("background-color","rgba(71,149,243,.1)");
	});

	$("li").mouseout(function(){             //鼠标离开后的颜色
		$(this).css("background-color","transparent");
	});
});




