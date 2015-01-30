function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            	setAttrForCombobox(prefix_component + 'DM_KHOA_MA_1','DM_KHOA_span1', 'DM_KHOA__1',"getDmKhoa()","","","");
            	setAttrForCombobox(prefix_component + 'DM_KHOA_MA_2','DM_KHOA_span2', 'DM_KHOA__2',"getDmKhoa()","","","");
            	setAttrForCombobox(prefix_component + 'DM_KHOA_MA_3','DM_KHOA_span3', 'DM_KHOA__3',"getDmKhoa()","","","");
            	setAttrForCombobox(prefix_component + 'DM_KHOA_MA_4','DM_KHOA_span4', 'DM_KHOA__4',"getDmKhoa()","","","");
            	
            	setAttrForCombobox(prefix_component + 'DM_LOAI_XOA_HO_SO_BENH_AN_MA','DM_LOAI_XOA_HO_SO_BENH_AN_span', 'DM_LOAI_XOA_HO_SO_BENH_AN',"getDmLoaiXoaHoSoBenhAn()","","","");
				timer.setTimeout(function(){focusInit();},100);
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById(prefix_component + "__sobenhan").focus();
}

function onCompleteGetInfor(){
	highlightOnFocus();
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_1', 'DM_KHOA__1');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2', 'DM_KHOA__2');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_3', 'DM_KHOA__3');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_4', 'DM_KHOA__4');
	myOnblurTextbox(prefix_component + 'DM_LOAI_XOA_HO_SO_BENH_AN_MA', 'DM_LOAI_XOA_HO_SO_BENH_AN');
	
}

