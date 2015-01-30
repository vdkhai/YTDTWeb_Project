function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	//setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	setAttrForCombobox(prefix_component + "DMKHO_MASO", "DM_KHO_span", "DT_DM_KHO", "getDtDmKho()", "", "", "");
            	setAttrForCombobox(prefix_component + "DMKINHPHI_MASO", "KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
            	setAttrForCombobox(prefix_component + "DTDMNGUON_MA", "NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
            	setAttrForCombobox(prefix_component + 'DT_DM_LOAI_MA','DT_DM_LOAI_span', 'DM_LOAI_THUOC', "getDmLoaiThuoc()", "", "", "");
            	setAttrForCombobox(prefix_component + "DTDMHANGSX_MA", "HANG_SAN_XUAT_span", "DM_NHA_SAN_XUAT", "getDmNhaSanXuat()", "", "", "");
            	setAttrForCombobox(prefix_component + "DMQUOCGIA_MASO", "QUOC_GIA_span", "DM_QUOC_GIA", "getDmQuocGia()", "", "", "");
            	setAttrForCombobox(prefix_component + 'DM_THUOC_MASO','DM_THUOC_span', 'DM_THUOC', "getDmThuocByLoai(\"" + prefix_component + "DT_DM_LOAI_MA\")", "", "", "");
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
