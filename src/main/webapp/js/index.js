var t=n=0, count;
$(document).ready(function(){
	count = $("#banner_list a").length;
	// count = 4
	$("#banner_list a:not(:first-child)").hide();
	$("#banner_info").html($("#banner_list a:first-child").html()).find("img").attr("alt");
	
	$("#banner_info").bind('click',function(){
		window.open($("#banner_list a:first-child").attr('href'),"_blank");
	});
	
	$("#banner li").bind('click', function(){
		var i = $(this).text() - 1;
		n = i;
		if(i >= count){
			return;
		}
		$('#banner li').css({
			background: '#808080'
		});
		$('#banner_list a').filter(':visible').fadeOut(2000);
		$('#banner_list a').eq(i).fadeIn(2000);
		$('#banner li').eq(i).css({
			background: 'blue'
		});
		
	});
	t = setInterval("showAuto()", 5000);
	$("banner").hover(function(){
		clearInterval(t);
	}, function(){
		t = setInterval("showAuto()", 5000);
	});
});

function showAuto(){
	n= n >= (count - 1) ? 0 : ++n;
	$("#banner li").eq(n).trigger('click');
}













