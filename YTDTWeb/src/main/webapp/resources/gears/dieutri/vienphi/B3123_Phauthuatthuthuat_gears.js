function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	//setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	 //setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","",""); 
           		setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            	setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            	setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
            
           		setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");  
           		setAttrForCombobox(prefix_component + "__mabacsi","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
            	 setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");  
            	setAttrForCombobox(prefix_component + "__mathuchien","DM_KHOA1_span","DM_KHOA__1","getDtDmBanKham()","","","");
				
				
			
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

