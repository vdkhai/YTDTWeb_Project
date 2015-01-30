function init() {
    if (window.google && google.gears) {
        try {
			setAttrForCombobox(prefix_component + "DM_DAN_TOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");				
			setAttrForCombobox(prefix_component + "DM_TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");				
			setAttrForCombobox(prefix_component + "DM_HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "DM_TINH_MA\")","resetDMXa()","","");
			setAttrForCombobox(prefix_component + "DM_XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "DM_HUYEN_MA\")","","","");				
			setAttrForCombobox(prefix_component + "DM_NGHE_NGHIEP_MA","DM_NGHE_NGHIEP_span","DM_NGHE_NGHIEP","getDmNgheNghiep()","","","");				
			setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");				
			setAttrForCombobox(prefix_component + "DM_BENH_ICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");				
        	setValueOnLoad();
        	resetInPhieu();
        	document.getElementById(prefix_component + "__magiay").focus();
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad() {
	myOnblurTextbox(prefix_component + "DM_DAN_TOC_MA", 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + "DM_TINH_MA", 'DM_TINH');
	myOnblurTextbox(prefix_component + "DM_HUYEN_MA", 'DM_HUYEN');
	myOnblurTextbox(prefix_component + "DM_XA_MA", 'DM_XA');
	myOnblurTextbox(prefix_component + "DM_NGHE_NGHIEP_MA", 'DM_NGHE_NGHIEP');
	myOnblurTextbox(prefix_component + "DM_KHOA_MA", 'DM_KHOA');
	myOnblurTextbox(prefix_component + "DM_BENH_ICD_MA", 'DM_BENH_ICD');
	resetInPhieu();
}
function resetInPhieu(){
	var hienThiInPhieu = document.getElementById(prefix_component + "hienThiInPhieu").value;
	
	if (hienThiInPhieu == 'false'){
		//alert("hienThiInPhieu1:"+hienThiInPhieu);
		document.getElementById("divInPhieu").style.display = "none";
	}else{
		//alert("hienThiInPhieu2:"+hienThiInPhieu);
		document.getElementById("divInPhieu").style.display = "block";
	}
	
}