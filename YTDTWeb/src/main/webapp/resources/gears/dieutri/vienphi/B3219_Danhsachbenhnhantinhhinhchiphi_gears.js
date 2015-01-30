function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	
            	setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");  
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");  
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

