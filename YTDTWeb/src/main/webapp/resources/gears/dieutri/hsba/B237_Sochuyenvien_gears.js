function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DT_DM_LY_DO_CV_MA","DT_DM_LY_DO_CV_span","DT_DM_LY_DO_CV","getDtDmLyDoCv()","","","");				
				setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmPhanLoaiTaiNan()","","","");				
				setAttrForCombobox(prefix_component + "DM_BENH_VIEN_MA","DM_BENH_VIEN_span","DM_BENH_VIEN","getDmBenhVien()","","","");						
				setAttrForCombobox(prefix_component + "DM_BENH_ICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");				
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");						
				 timer.setTimeout(function(){setValueOnLoad();},100); 
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad(){
	 document.getElementById(prefix_component + "__tungay").focus();
}

