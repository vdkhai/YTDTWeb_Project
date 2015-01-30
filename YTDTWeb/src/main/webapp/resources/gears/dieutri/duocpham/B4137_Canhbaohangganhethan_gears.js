function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	//setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	setAttrForCombobox(prefix_component + "DMKHOA_MASO", "DM_KHOA_span", "DM_KHOA", "getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")", "", "", "");
            	setAttrForCombobox(prefix_component + "DMKINHPHI_MASO", "KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
            	setAttrForCombobox(prefix_component + "DTDMNGUON_MA", "NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
            	setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","","");
				setAttrForCombobox(prefix_component + "PHANLOAI_MA","DM_PHAN_LOAI_THUOC_span","DM_PHAN_LOAI_THUOC","getDmPhanLoaiThuocByLoaiThuoc(\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","","","");
        	timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}


function InitSetInfor(){
	document.getElementById(prefix_component + "LOAIPHIEU_MA").focus();
	document.getElementById("DM_KHOA").disabled = true;
	document.getElementById(prefix_component + "DMKHOA_MASO").disabled = true;
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DMKHOA_MASO").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DMKHOA_MASO").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DMKHOA_MASO").value = "KTE";
	}else  if (kc_value == 'NT'){
		document.getElementById(prefix_component + "DMKHOA_MASO").value = "KNT";
	}
	myOnblurTextbox(prefix_component + "DMKHOA_MASO", "DM_KHOA");
	

}