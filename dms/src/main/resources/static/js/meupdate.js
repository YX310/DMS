function subSelectedHeadimg(){
//		console.log("执行了");
//		console.log($(".user-selected-headimg").attr("src"));
	$(".pre-user-headimg").attr('src', $(".user-selected-headimg").attr("src"));
	$(".select-headimg-box").css({
		"display":"none"
	});
	$(".select-headimg").css({
		"display":"none"
	});
	const head_img = document.getElementById("pre-user-headimg");
	document.getElementById("head_img").value=head_img.getAttribute("src");
}
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
//	$(".subSelectedHeadimg").mousedown(function(){
//		$(".select-headimg-box").css({
//			"display":"none"
//		});
//		$(".select-headimg").css({
//			"display":"none"
//		});
//	});

	$(".headimg-box img").hover(function(){        //鼠标悬停时的颜色
		$(this).css("border","1px solid rgba(71,149,243,1)");
	});

	$(".headimg-box img").mouseout(function(){             //鼠标离开后的颜色
		$(this).css("border","1px solid transparent");
	});

	$(".selectHeadimg").mousedown(function(){
		$(".user-selected-headimg").attr("src", $(this).attr("src"));
	});

});




