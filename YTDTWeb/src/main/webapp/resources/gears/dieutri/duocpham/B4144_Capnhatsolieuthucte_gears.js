function init() {
	
    if (window.google && google.gears) {
    	
        try {        		
            	setAttrForCombobox(prefix_component + "KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
				setAttrForCombobox(prefix_component + "LOAIHANG_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","","");
				setAttrForCombobox(prefix_component + "MATHANG_MA","DM_THUOC_span","DM_THUOC","getDmThuocByLoai(\"" + prefix_component + "LOAIHANG_MA\")","","setDonViForThuoc(prefix_component + 'LOAIHANG_MA')","");
				setAttrForCombobox(prefix_component + "NUOCSX_MA","DM_QUOC_GIA_span","DM_QUOC_GIA","getDmQuocGia()","","","");
				setAttrForCombobox(prefix_component + "HANGSX_MA","DM_NHA_SAN_XUAT_span","DM_NHA_SAN_XUAT","getDmNhaSanXuat()","","","");
				setAttrForCombobox(prefix_component + "CHUONGTRINH_MA","DM_NGUON_CHUONG_TRINH_span","DM_NGUON_CHUONG_TRINH","getDmNguonChuongTrinh()","","","");
				setAttrForCombobox(prefix_component + "NGUONKP_MA","DM_NGUON_KINH_PHI_span","DM_NGUON_KINH_PHI","getDmNguonKinhPhi()","","","");
				setAttrForCombobox(prefix_component + "DTDMNOIBAN_MA","DM_NHA_CUNG_CAP_span","DM_NHA_CUNG_CAP","getDmNhaCungCap()","","","");
            	 timer.setTimeout(function(){focusInit();},100); 
            	 alert(1);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById(prefix_component + "LOAIHANG_MA").focus(); 
	document.getElementById("DM_KHOA").disabled = true;
	document.getElementById(prefix_component + "KHOA_MA").disabled = true;
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "KHOA_MA").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "KHOA_MA").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KTE";
	}
	myOnblurTextbox(prefix_component + "KHOA_MA", "DM_KHOA");
	
	
}

function onCompleteNhapMoi(){
	document.getElementById("DM_KHOA").value="";
	document.getElementById("DM_LOAI_THUOC").value="";
	document.getElementById("DM_THUOC").value="";
	document.getElementById("DM_NHA_CUNG_CAP").value="";
	document.getElementById("DM_QUOC_GIA").value="";
	document.getElementById("DM_NHA_SAN_XUAT").value="";
	document.getElementById("DM_NGUON_CHUONG_TRINH").value="";
	document.getElementById("DM_NGUON_KINH_PHI").value="";
	document.getElementById(prefix_component + "LOAIHANG_MA").value="";
	document.getElementById(prefix_component + "__donvi").value="";		
}

function setDonViForThuoc(thuocid){	
	var value = document.getElementById(prefix_component + "MATHANG_MA_pk").value;
	if (value != "") {
		var objArrThuoc = DmThuoc.filter("MaSo = " + value).toArray();
		var madv = objArrThuoc[0].DMDONVITINH_MASO;
		var objArr = DmDonViTinh.filter("MaSo = " +  madv).toArray();
		var ten = objArr[0].Ten;
		document.getElementById(prefix_component + "__donvi").value= ten;	
	}  
}


