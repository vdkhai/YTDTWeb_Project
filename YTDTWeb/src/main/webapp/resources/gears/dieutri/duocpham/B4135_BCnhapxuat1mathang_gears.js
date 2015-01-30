function init() {
	if (window.google && google.gears) {
		try {
			document.getElementById(prefix_component + "LOAIPHIEU_MA").value= "";
			document.getElementById(prefix_component + "DM_LOAI_THUOC" + 'comboboxField').value= "";
			document.getElementById(prefix_component + "PHANLOAI_MA").value= "";
			document.getElementById(prefix_component + "DM_PHAN_LOAI_THUOC" + 'comboboxField').value= "";
			document.getElementById(prefix_component + "DMTHUOC_MASO").value= "";
			document.getElementById(prefix_component + "DM_THUOC" + 'comboboxField').value= "";
			timer = google.gears.factory.create('beta.timer');
            setAttrForCombobox(prefix_component + "DMKHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
            setAttrForCombobox(prefix_component + "DTDMDONVITINH_MA", "DON_VI_TINH_span", "DM_DON_VI_TINH", "getDmDonViTinh()","","","");
            
       		timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
}


function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
	document.getElementById("DM_KHOA").disabled = true;
	document.getElementById(prefix_component + "DMKHOA_MA").disabled = true;
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
	myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
}

function setNgayThangBatDauKetThuc(thang,nam,tungay,denngay){
	  var thangVaoVien = document.getElementById(prefix_component + thang).value;
	  var namVaoVien = document.getElementById(prefix_component + nam).value;
	  var ngayhientai = document.getElementById(prefix_component + '__ngayhientai').value;
	  var thangHt = ngayhientai.substring(3,5);
	  var namHt = ngayhientai.substring(6);
	   
	  if ( namVaoVien == "" && namVaoVien.length == 0){
	     return;
	  }
	  
	  else if(namVaoVien < 1900 || namVaoVien > namHt){
		  	document.getElementById(prefix_component + nam).value = '';
		  	document.getElementById(prefix_component + nam).focus();
		  	return;
	  }
	  else if ( parseInt(namVaoVien) < 1900 || parseInt(namVaoVien) > 2300 ){
		  	 document.getElementById(prefix_component + nam).focus();
		     return;
	  }
	  else{
  
		  var thangVaoVien_Temp = thangVaoVien;
		  var thangVaoVien_Temp_2 = thangVaoVien;
		  
		  if (thangVaoVien != "" && thangVaoVien.length > 0){
		  	if (thangVaoVien.length == 1){
		  	   thangVaoVien_Temp = "0" + thangVaoVien;
		  	   thangVaoVien_Temp_2 = "0" + thangVaoVien;
		  	}
		  }
		  else{
		  	   thangVaoVien_Temp = "01";  
		  	   thangVaoVien_Temp_2="12";
		  	   thangVaoVien="1";
		  	   //document.getElementById(prefix_component + thang).value = "1";
		  }

		  document.getElementById(prefix_component + tungay).value="01/" + thangVaoVien_Temp + "/" + namVaoVien;  
	  	  if(thangVaoVien < 10)
	  	      thangVaoVien = "0"+thangVaoVien;
	  	  if(thangVaoVien == thangHt && namVaoVien == namHt){
			  	document.getElementById(prefix_component + denngay).value= ngayhientai;
		  }
		  else{
			  	document.getElementById(prefix_component + denngay).value= getLastDayOfMonth (parseInt(thangVaoVien),parseInt(namVaoVien)) + "/" + thangVaoVien_Temp_2 + "/" + namVaoVien;
		  }

  	}
}

function getDvt() {
//	var thuocMa = document.getElementById(prefix_component + "DMTHUOC_MASO").value;
//	if (thuocMa != "") {
//		var thuoc = DmThuoc.filter("Ma like ?", thuocMa).first();
//		var dvt = DmDonViTinh.getByFieldValue("MaSo", thuoc.DMDONVITINH_MASO);
//		document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = dvt.Ma;
//	} else {
//		document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = "";
//	}
	
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
}

function getLastDayOfMonth(month,year)
{
	var day;

	switch(month)
	{
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10:
		case 12:
			day = 31;
			break;
		case 4 :
		case 6 :
		case 9 :
		case 11:
		   	day = 30;
			break;
		case 2 :
			if( ( (year % 4 == 0) && ( year % 100 != 0) ) 
                           || (year % 400 == 0) )
				day = 29;
			else
				day = 28;
			break;

	}

	return day;
}


