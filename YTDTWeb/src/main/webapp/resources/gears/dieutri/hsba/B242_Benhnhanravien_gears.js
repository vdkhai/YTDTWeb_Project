function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
							
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
				setAttrForCombobox(prefix_component + "DT_DM_KET_QUA_MA","DT_DM_KET_QUA_span","DT_DM_KET_QUA","getDtDmKetQua()","","","");
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");
				setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmPhanLoaiTaiNan()","","","");
				            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

