function init() {
	
    if (window.google && google.gears) {
	
	 	setAttrForCombobox(prefix_component + "__KhoaHienTai_Ma","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");  
	 	     
		timer.setTimeout(function(){myOnLoad();},100); 
		
    }
}

function myOnLoad(){
	document.getElementById(prefix_component + "HSBA_SOVAOVIEN").focus();
}
function setValueOnLoad(){
	myOnblurTextbox(prefix_component + "__KhoaHienTai_Ma", "DM_KHOA");
	var hidGhiNhan = document.getElementById(prefix_component + "__hidGhiNhan").value;
	if(hidGhiNhan == 'true'){		
		document.getElementById("divGhiNhan").style.display = "none";
	}else{
		document.getElementById("divGhiNhan").style.display = "block";
	}
}
