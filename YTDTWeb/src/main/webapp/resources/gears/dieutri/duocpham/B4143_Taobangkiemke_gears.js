function init() {
	
    if (window.google && google.gears) {
    	
        try {
				setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","document.getElementById(prefix_component + '__temp').focus()","");
				setAttrForCombobox(prefix_component + "PHANLOAI_MA","DM_PHAN_LOAI_THUOC_span","DM_PHAN_LOAI_THUOC","getDmPhanLoaiThuocByLoaiThuoc(\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","","","");		
								
				timer.setTimeout(function(){InitSetInfor();},100);
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "LOAIPHIEU_MA").focus();
}

function onCompleteGetInfor() {
		try {					
			myOnblurTextbox(prefix_component + "LOAIPHIEU_MA", "DM_LOAI_THUOC");	
		} catch (e) {
			alert("onCompleteGetInfor error: " + e.description);
		}	
		
	}


