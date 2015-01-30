function init() {
	
    if (window.google && google.gears) {
    	try {
        		setAttrForCombobox(prefix_component + "HSBA_KHOADANGDT","DM_KHOA_span",prefix_component + "DM_KHOA","getDmKhoa()","","","");								
				setAttrForCombobox(prefix_component + "HSBABHYT_MAKCB","DT_DM_KCB_BHYT_span",prefix_component + "DM_BENH_VIEN","getDmBenhVien()","","","");								
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}


initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__tieptucnhap').click();});	
}

