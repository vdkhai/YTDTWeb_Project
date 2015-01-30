function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	
            	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");  				
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

