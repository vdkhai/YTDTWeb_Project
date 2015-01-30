function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
        	setAttrForCombobox(prefix_component + 'DMLOAISINH_MASO','DM_LOAI_SINH_span', prefix_component +  'DM_LOAI_SINH',"getDmLoaiSinh()","","","");
            	setAttrForCombobox(prefix_component + 'HSBASAN_MABENH','DM_BENH_ICD_span', prefix_component +  'DM_BENH_ICD',"getDmBenhIcd()","","","");
				setAttrForCombobox(prefix_component + 'HSBASAN_CHUYENKHOA','DM_KHOA__2_span', prefix_component +  'DM_KHOA__2',"getDmKhoa()","","","");
	 			setAttrForCombobox(prefix_component + 'HSBASAN_MATUVONG','DM_BENH_ICD__2_span', prefix_component +  'DM_BENH_ICD__2',"getDmBenhIcd()","","","");
			
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__nhapmoi').click();});	
}