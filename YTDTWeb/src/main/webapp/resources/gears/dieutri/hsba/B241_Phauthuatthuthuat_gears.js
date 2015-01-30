function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
							
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");
				setAttrForCombobox(prefix_component + "DM_KHOA1_MA","DM_KHOA1_span","DM_KHOA__1","getDmKhoa()","","","");				
				            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

