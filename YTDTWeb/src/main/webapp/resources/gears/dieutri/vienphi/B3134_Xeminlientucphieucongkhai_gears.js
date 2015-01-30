function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");												
            timer.setTimeout(function(){focusInit();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function focusInit(){
	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	
}
