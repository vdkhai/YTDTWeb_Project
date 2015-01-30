
function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            setAttrForCombobox(prefix_component + "NHANVIEN_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");  
            	timer.setTimeout(function(){setValueOnLoad();},100); 	
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();
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
	  
	  else if(parseInt(namVaoVien) < 1900 || parseInt(namVaoVien) > parseInt(namHt)){
		  	document.getElementById(prefix_component + nam).value = '';
		  	document.getElementById(prefix_component + nam).focus();
		  	//alert(parseInt(namHt));
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

