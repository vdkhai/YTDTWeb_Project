function init() {
    if (window.google && google.gears) {
        try {
        	
            setAttrForCombobox(prefix_component + "CHUANDOAN_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");
			setAttrForCombobox(prefix_component + "KETQUADT_MA","DM_KET_QUA_DIEU_TRI_span","DM_KET_QUA_DIEU_TRI","getDmKetQuaDieuTri()","","","");
			
            document.getElementById(prefix_component + "__sobenhan").focus();
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad() {
	
	myOnblurTextbox(prefix_component + "CHUANDOAN_MA", 'DM_BENH_ICD');
	myOnblurTextbox(prefix_component + "KETQUADT_MA", 'DM_KET_QUA_DIEU_TRI');
	
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	if (donViTuoi == "1") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = " ";
	} else if (donViTuoi == "2") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	} else if (donViTuoi == "3") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
	} else {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = " ";
	}
}