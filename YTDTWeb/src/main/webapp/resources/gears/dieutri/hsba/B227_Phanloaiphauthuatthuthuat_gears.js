function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");				
				setAttrForCombobox(prefix_component + "DT_DM_PHAU_THUAT_MA","DT_DM_PHAU_THUAT_span","DT_DM_PHAU_THUAT","getDtDmPhauThuat()","","","");				
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

