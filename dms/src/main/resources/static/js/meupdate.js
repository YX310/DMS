$(document).ready(function(){		
	$(".meupdate-headimg").mousedown(function(){
		$(".select-headimg").css({
			"display":"block"
		});
		$("body").css({
			"background-color":"rgba(71,149,243,1)"
		});
		$(".meupdate-content").css({
			"background-color":"rgba(71,149,243,1)",
			
		});
		$(".meupdate-left").css({
			"background-color":"rgba(71,149,243,1)"
		});
		$(".meupdate-right").css({
			"background-color":"rgba(71,149,243,1)"
		});
	});
	
	$(".select-headimg-cancel").mousedown(function(){
		$(".select-headimg").css({
			"display":"none"
		});
		$("body").css({
			"background-color":"#f6f6f8"
		});
		$(".meupdate-content").css({
			"background-color":"white",
			
		});
		$(".meupdate-left").css({
			"background-color":"white"
		});
		$(".meupdate-right").css({
			"background-color":"white"
		});
		
	});
	
	
    
 	$(".headimg-box img").hover(function(){        //鼠标悬停时的颜色
		$(this).css("border","1px solid rgba(71,149,243,1)");
	});
	
	$(".headimg-box img").mouseout(function(){             //鼠标离开后的颜色
		$(this).css("border","1px solid transparent");
	});
	
});




