function init() {
	
    if (window.google && google.gears) {
    	
       
        	//db = google.gears.factory.create('beta.database');
				setAttrForCombobox(prefix_component + "KHOA_MA","DM_KHOA_span",prefix_component + "DM_KHOA","getDmKhoa_NoiTru()","","","");
				setAttrForCombobox(prefix_component + "HSBABBHC_CHUTOA","DT_DM_NHAN_VIEN_span",prefix_component + "DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");								
				setAttrForCombobox(prefix_component + "HSBABBHC_THUKY","DT_DM_NHAN_VIEN1_span",prefix_component + "DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");								
				setAttrForCombobox(prefix_component + "thanhVienThamGia","DT_DM_NHAN_VIEN2_span",prefix_component + "DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");												
            
       
    }    
}
initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__tieptucnhap').click();});	
}

