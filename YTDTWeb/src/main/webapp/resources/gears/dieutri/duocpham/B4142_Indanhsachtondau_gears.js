function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","","");
        	setAttrForCombobox(prefix_component + "DMKHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
        	timer.setTimeout(function(){InitSetInfor();},100);
        	
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "LOAIPHIEU_MA").focus();
	document.getElementById("DM_KHOA").disabled = true;
	document.getElementById(prefix_component + "DMKHOA_MA").disabled = true;
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KTE";
	}
	myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
	

}

