function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DM_DAN_TOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");				
				setAttrForCombobox(prefix_component + "DM_TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");				
				setAttrForCombobox(prefix_component + "DM_HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "DM_TINH_MA\")","resetDMXa()","","");
				setAttrForCombobox(prefix_component + "DM_XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "DM_HUYEN_MA\")","","","");								
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");								
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

