function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
							
				setAttrForCombobox(prefix_component + "DT_DM_BAN_KHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","","");											
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

