function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	//setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	
				setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","","");
				setAttrForCombobox(prefix_component + "KHONHAP_MA","DT_DM_KHO_span","DT_DM_KHO","getDtDmKho()","","","");
				setAttrForCombobox(prefix_component + "CHUONGTRINH_MA","DM_NGUON_CHUONG_TRINH_span","DM_NGUON_CHUONG_TRINH","getDmNguonChuongTrinh()","","","");
				setAttrForCombobox(prefix_component + "NGUONKP_MA","DM_NGUON_KINH_PHI_span","DM_NGUON_KINH_PHI","getDmNguonKinhPhi()","","","");
				setAttrForCombobox(prefix_component + "NGUOILAP_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");
				setAttrForCombobox(prefix_component + "NGUOIKY_MA","DT_DM_NHAN_VIEN1_span","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");
				setAttrForCombobox(prefix_component + "BOPHANTRA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");
				
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

