function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
				//setAttrForCombobox(prefix_component + "DT_DM_PL_BHYT_MA","DT_DM_PL_BHYT_span","DT_DM_PL_BHYT","getDtDmPlBhyt()","","","");							
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");								
				setAttrForCombobox(prefix_component + "DT_DM_KHOI_BHYT_MA","DT_DM_KHOI_BHYT_span","DT_DM_KHOI_BHYT","getDtDmKhoiBhyt()","","","");								
				timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
}
