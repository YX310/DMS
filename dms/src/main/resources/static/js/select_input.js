$(document).ready(function(){
	let postFinished = false
	$("#input_text").mousedown(function() {
		if (!postFinished) $.post(
			"/searchAllList", // 请求路由
			{
				"getSearchInput": $(this).val() // 参数名: 参数值
			}, // 请求参数
			function(data, status) { // 请求回调
				// id, title, type
				postFinished = true
				console.log("qqqq" + data);
				for (let i in data) {
					//<ul><a th:href="@{/toWorkbench(id=${session.userId})}">我的工作台</a></ul>
					const link = "/" + data[i].type + "?id=" + data[i].id
				// @{/toDemandListid=${session.projectId})}
					let $newElement=$('<a href=' + link + '><li>'+ data[i].title + '</li></a>');
					$newElement.hover(function(){        //鼠标悬停时的颜色
						$(this).css("background-color","rgba(71,149,243,.1)");
					})
					$newElement.mouseout(function(){             //鼠标离开后的颜色
						$(this).css("background-color","transparent");
					});
					// $newElement.click(function () {
					// 	// $("#input_text").val(data[i])
					// 	// toUpdateDefect(id=${n.defect_id})
					// 	$.get(, {
					// 		// "id": data[i].id
					// 	}, function (data, status) {
					// 		console.log(data)
					// 	})
					// })
					$newElement.appendTo($(".search-ul"))

				}
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
					"border-radius": "5px 0 0 5px"
				});
				$(".search-ul").css("display","block");
			});

	});

	$(document).on('click', function(e) {
		const tmp = $(e.target).attr('id');
		if(tmp !== 'search-input-box' && tmp !== 'input_text') {
			// 点击后的操作
			postFinished = false
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
				"margin": "-2px",
				"border-radius": "5px 0 0 5px"
			});

			$(".search-ul").css("display","none");
	     	$(".search-ul").children().remove();
		}
 	});
	$("li").hover(function(){        //鼠标悬停时的颜色
		$(this).css("background-color","rgba(71,149,243,.1)");
	});

	$("li").mouseout(function(){             //鼠标离开后的颜色
		$(this).css("background-color","transparent");
	});
});




