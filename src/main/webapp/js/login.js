$(document).ready(function(){
	var tabS = document.getElementById('tabS');
	var tabA = document.getElementById('tabA');
	var stuLoginF = document.getElementById('stuLoginF');
	var adminLoginF = document.getElementById('adminLoginF');
	tabS.onclick = function(){
		if(adminLoginF.style.display == 'block'){
			adminLoginF.style.display = 'none';
		}
		if(tabA.style.backgroundColor != ''){
			tabA.style.backgroundColor = '';
		}
		tabS.style.backgroundColor = '#2fb4d6';
		stuLoginF.style.display = 'block';
	}
	tabA.onclick = function(){
		if(stuLoginF.style.display == 'block'){
			stuLoginF.style.display = 'none';
		}
		if(tabS.style.backgroundColor != ''){
			tabS.style.backgroundColor = '';
		}
		tabA.style.backgroundColor = '#2fb4d6';
		adminLoginF.style.display = 'block';
	}
});