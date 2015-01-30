function init() {
	
    if (window.google && google.gears) {
    	
        try {
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");								
				timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
}
