function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DM_DAN_TOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");				
				setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");				
				setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
				setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");								
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");								
				setAttrForCombobox(prefix_component + "DT_DM_KCB_BHYT_MA","DT_DM_KCB_BHYT_span","DM_BENH_VIEN","getDmBenhVien()","","","");								
				//setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmTaiNan()","","","");								
				//setAttrForCombobox(prefix_component + "DM_PHUONG_THUC_GAY_TAI_NAN_MA","DM_PHUONG_THUC_GAY_TAI_NAN_span","DM_PHUONG_THUC_GAY_TAI_NAN","getDmPhuongThucGayTaiNan()","","","");
				//setAttrForCombobox(prefix_component + "DM_DIA_DIEM_MA","DM_DIA_DIEM_span","DM_DIA_DIEM","getDmDiaDiem()","","","");								
            	//setAttrForCombobox(prefix_component + 'TINHBHYT_MA','DT_DM_TINH_BHYT_span','DT_DM_TINH_BHYT', "getDtDmTinhBhyt()", "", "", "");
            	
            	setAttrForComboboxForTinhBHYT(prefix_component + 'TINHBHYT_MA','DT_DM_TINH_BHYT_span','DM_TINH__2', "getDmTinh()", "document.getElementById(prefix_component+'DT_DM_KCB_BHYT_MA').value='';myOnblurTextbox(prefix_component + 'DT_DM_KCB_BHYT_MA', 'DM_BENH_VIEN');", "", "");
	       
	       
            	setAttrForCombobox(prefix_component + 'KHOIBHYT_MA','DT_DM_KHOI_BHYT_span','DT_DM_KHOI_BHYT', "getDtDmKhoiBhyt()", "", "", "");
        timer.setTimeout(function(){myOnLoad();},200); 
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setMaKhoi(){
	var sothe = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
	sothe = sothe.toUpperCase();
	document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = sothe;
	if ( sothe != null && sothe != '') {
			document.getElementById(prefix_component + "KHOIBHYT_MA").value = sothe.substr(0,2);
	}
	myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	
}

function myOnLoad(){
	
	if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
		var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
		if (tinhbhyt != null) {
			document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
			dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
		}
	}
	
	if (document.getElementById(prefix_component + "DT_DM_KCB_BHYT_MA").value == ''){
		var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
		if (kcbbhyt != null) {
			document.getElementById(prefix_component + "DT_DM_KCB_BHYT_MA").value = kcbbhyt.Ma;
			dojo.byId("DM_BENH_VIEN").value = kcbbhyt.Ten;
		}
	}
	
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
	//myOnblurTextbox(prefix_component + 'DT_DM_KCB_BHYT_MA', 'DM_BENH_VIEN');
	myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');

	
	document.getElementById(prefix_component + "__sobenhan").focus();
}

function onCompleteGetInfor(){               
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//myOnblurTextbox(prefix_component + 'DM_PHUONG_THUC_GAY_TAI_NAN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	//myOnblurTextbox(prefix_component + 'DM_DIA_DIEM_MA', 'DM_DIA_DIEM');
	//myOnblurTextbox(prefix_component + 'DM_PHAN_LOAI_TAI_NAN_MA', 'DM_PHAN_LOAI_TAI_NAN');
	myOnblurTextbox(prefix_component + 'DT_DM_KCB_BHYT_MA', 'DM_BENH_VIEN');
	
	//myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DT_DM_TINH_BHYT');
	myOnblurTextboxTinhBHYT(document.getElementById(prefix_component + "TINHBHYT_MA").id,'DM_TINH__2');
	 
	myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	if(sobenhan == ''){
		document.getElementById(prefix_component + "__sobenhan").focus();
	}
	
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
}
function checkSoTheBHYT() {
	var sothebhyt = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
	//var hotenbn = document.getElementById(prefix_component + "__hoten").value;
	
	if (sothebhyt != null && sothebhyt != ''){
		
		if (sothebhyt.length < 15){
			alert("Chiều dài số thẻ BHYT phải bằng 15");
			//document.getElementById(prefix_component + "__sothe").value = "";
			document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
			document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
		} else {
			var maTinhBhyt = sothebhyt.substr(3,2) +",";  
    		var listMaTinhBhyt = document.getElementById(prefix_component + "listMaTinhBhyt").value;
			setMaKhoi();
			if(document.getElementById(prefix_component + "KHOIBHYT_MA").value == '') {
				alert("Mã đối tượng bảo hiểm của số thẻ " + sothebhyt.toUpperCase() + " là không hợp lệ.");
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value ="";
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
			} else if (listMaTinhBhyt.indexOf(maTinhBhyt) < 0){
				alert("Mã tỉnh bảo hiểm của số thẻ " + sothebhyt.toUpperCase() + " là không hợp lệ.");
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value ="";
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus(); 
			} 
		}
	}

}

