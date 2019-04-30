function countDown(secs, surl){
	var jumpTo = document.getElementById("jumpTo");
	jumpTo.innerHTML = secs;
	if(--secs > 0){
		setTimeout(function(){
			countDown(secs, surl);
		}, 1000);
	}else{
		location.href = surl;
	}
}