function checkForm()
{
	if(document.getElementById("searchKey").value==""){
		return false;
	}else{
		return true;
	}
}

//展示当前时间
function show()
{
	time = document.getElementById("time");
	now = new Date();
	year = now.getFullYear();
	month = now.getMonth() + 1;
	date = now.getDate();
	hours = now.getHours();
	minutes = now.getMinutes();
	seconds = now.getSeconds();
	
	if(minutes <= 9){
		minutes = "0" + minutes;
	}
	if(seconds <=9){
		seconds = "0" + seconds;
	}
	time.innerHTML = year + "年" + month + "月" + date + "日" 
	
		+ " 现在时间：" + hours + ":" + minutes + ":" + seconds ; 
	setTimeout("show()",1000);
}