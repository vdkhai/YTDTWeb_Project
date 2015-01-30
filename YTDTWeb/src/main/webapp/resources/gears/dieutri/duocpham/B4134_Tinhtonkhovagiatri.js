function init() {
	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');
            
            //alert(document.getElementById(prefix_component + "DM_KHO_XUAT").value);
            if (document.getElementById(prefix_component + "DM_KHO_XUAT").value != "ALL"){
            	//alert(document.getElementById(prefix_component + "DM_KHO_XUAT").value);
            	setAttrForCombobox(prefix_component + "DMKHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
			}else{
				setAttrForCombobox(prefix_component + "DMKHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");
			}
            
			setAttrForCombobox(prefix_component + 'DMQUOCGIA_MA','DM_QUOC_GIA_span', 'DM_QUOC_GIA',"getDmQuocGia()","","","");
			setAttrForCombobox(prefix_component + 'DTDMNGUON_MA','DM_NGUON_CHUONG_TRINH_span', 'DM_NGUON_CHUONG_TRINH',"getDmNguonChuongTrinh()","","","");
			setAttrForCombobox(prefix_component + 'DMKINHPHI_MASO','DM_NGUON_KINH_PHI_span', 'DM_NGUON_KINH_PHI',"getDmNguonKinhPhi()","","","");
            setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","document.getElementById(prefix_component + '__temp').focus();document.getElementById(prefix_component + 'PHANLOAI_MA').focus()","");
          	setAttrForCombobox(prefix_component + "PHANLOAI_MA","DM_PHAN_LOAI_THUOC_span","DM_PHAN_LOAI_THUOC","getDmPhanLoaiThuocByLoaiThuoc(\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","","","");
          	setAttrForCombobox(prefix_component + "NHOMTHUOC_MA","DM_PHAN_NHOM_THUOC_span","DM_PHAN_NHOM_THUOC","getDmPhanNhomThuoc()","","","");
			timer.setTimeout(function(){InitSetInfor();},100);
		} catch (e) {
			alert("init error: " + e.description);
		}
	}
}


function InitSetInfor(){
	document.getElementById(prefix_component + "DMQUOCGIA_MA").focus();	
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KTE";
	}else  if (kc_value == 'NT'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KNT";
	}
	
	 if (document.getElementById(prefix_component + "DM_KHO_XUAT").value == "ALL" ){
	 		document.getElementById(prefix_component + "DMKHOA_MA").value = "";
	 		myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
			document.getElementById(prefix_component + "DMKHOA_MA").disabled = true;
			if (document.getElementById( "DM_KHOA").disabled == false){
		        changeDisabledAttr("DM_KHOA");  
		   	}
	 }else if (	document.getElementById(prefix_component + "DM_KHO_XUAT").value == "KC" 
	            || document.getElementById(prefix_component + "DM_KHO_XUAT").value == "BHYT" ){
			document.getElementById(prefix_component + "DMKHOA_MA").disabled = true;
			if (document.getElementById( "DM_KHOA").disabled == false){
		        changeDisabledAttr("DM_KHOA");  
		   	}
   	}
	myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
}


