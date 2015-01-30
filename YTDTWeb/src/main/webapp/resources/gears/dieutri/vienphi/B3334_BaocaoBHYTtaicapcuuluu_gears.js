function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
															
				setAttrForCombobox(prefix_component + "DT_DM_KHOI_BHYT_MA","DT_DM_KHOI_BHYT_span","DT_DM_KHOI_BHYT","getDtDmKhoiBhyt()","","","");								
				
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

