function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
											
				setAttrForCombobox(prefix_component + "DM_LOAI_BAO_CAO_HSBA_DANG_CN_MA","DM_LOAI_BAO_CAO_HSBA_DANG_CN_span","DM_LOAI_BAO_CAO_HSBA_DANG_CN","getDmLoaiBaoCaoHSBADangCN()","","","");
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");								
				            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

