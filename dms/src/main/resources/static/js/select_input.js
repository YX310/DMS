$(document).ready(function(){		
	$("#toolbar-search-input").mousedown(function(){
		$.post("/searchAllList", function(data, status){
			console.log("qqqq" + data);
			for (name in data) {
				var $newElement=$('<li>'+ data[name] + '</li>');
				$newElement.appendTo($("#search-content-ul"))
			}
		});
		$("#search-input").css("height","200px");
		$("#search-content-ul").css("display","block")
	});
	
	
	$(document).on('click', function(e) { 
		const tmp = $(e.target).attr('id')
		if(tmp != 'toolbar-search-input' && tmp != 'search-input') {
	     	// 点击后的操作
	     	console.log(e.id);
	     	$("#search-input").css("height","30px");
	     	$("#search-content-ul").css("display","none");
	     	$("#search-content-ul").children().remove();
	   	}
 	});
});




