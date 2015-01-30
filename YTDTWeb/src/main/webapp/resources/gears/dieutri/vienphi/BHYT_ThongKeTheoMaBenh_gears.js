function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
				setAttrForCombobox(prefix_component + "DT_DM_PL_BHYT_MA","DT_DM_PL_BHYT_span","DT_DM_PL_BHYT","getDtDmPlBhyt()","","","");							
											
				setAttrForCombobox(prefix_component + "DM_BENH_ICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");								
				timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
}


