function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
							
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
				setAttrForCombobox(prefix_component + "DT_DM_KET_QUA_MA","DT_DM_KET_QUA_span","DT_DM_KET_QUA","getDtDmKetQua()","","","");
				setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmPhanLoaiTaiNan()","","","");
				setAttrForCombobox(prefix_component + 'DMKHOA_1','DM_KHOA_span1', 'DM_KHOA__1', "getDmKhoa()", "", "", "");
        		setAttrForCombobox(prefix_component + 'DMKHOA_2','DM_KHOA_span2', 'DM_KHOA__2', "getDmKhoa()", "", "", "");
        		setAttrForCombobox(prefix_component + "BENHICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

