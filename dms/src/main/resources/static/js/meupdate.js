$(document).ready(function(){
	$(".meupdate-headimg").mousedown(function(){
		$(".select-headimg-box").css({
			"display":"block"
		});
		$(".select-headimg").css({
			"display":"block"
		});

	});

	$(".select-headimg-cancel").mousedown(function(){
		$(".select-headimg-box").css({
			"display":"none"
		});
		$(".select-headimg").css({
			"display":"none"
		});

	});



	$(".headimg-box img").hover(function(){        //鼠标悬停时的颜色
		$(this).css("border","1px solid rgba(71,149,243,1)");
	});

	$(".headimg-box img").mouseout(function(){             //鼠标离开后的颜色
		$(this).css("border","1px solid transparent");
	});

});




