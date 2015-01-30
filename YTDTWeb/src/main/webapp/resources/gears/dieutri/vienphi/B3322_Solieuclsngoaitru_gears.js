function init() {
	
    	if (window.google && google.gears) {
    	  try {
    	  	//LoadCatalogFromServer();
            	setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_CLS_MA','DT_DM_CLS_span','DT_DM_CLS','10', DtDmCls);
	            //alert('6');
	            setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_PB_CLS_MA','DT_DM_PB_CLS_span', 'DT_DM_PB_CLS','10', DtDmPbCls);
	            //alert('7');
	            setAttrForCombobox_StoreValue(prefix_component + 'DM_KHOA_MA','DM_KHOA_span','DM_KHOA','10', DmKhoa);
				setValueOnLoad();
				//alert('9');            	
           	} catch (e) {        
        		 alert('Exception:-( ' +e );         
         	}
       }
    }


 function setNgayThangBatDauKetThuc(){
 	  var ngayhientai = document.getElementById(prefix_component + '__ngayhientai').value;
	  var thangVaoVien = document.getElementById(prefix_component + '__thang').value;
	  var namVaoVien = document.getElementById(prefix_component + '__nam').value;
	  var thangHt = ngayhientai.substring(3,5);
	  //var ngayHt = ngayhientai.substring(0,2);	
	  var namHt = ngayhientai.substring(6);  
	  
	  if ( namVaoVien == ""){
	     return;
	  }
	  else if(namVaoVien < 1900 || namVaoVien > namHt){
	  	document.getElementById(prefix_component + '__nam').value = '';
	  	document.getElementById(prefix_component + '__nam').focus();
	  	return;
	  }	  
	  else if ( parseInt(namVaoVien) < 1900 || parseInt(namVaoVien) > 2300 ){
	  	 document.getElementById(prefix_component + '__nam').focus();
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
		  
		  document.getElementById(prefix_component + '__tungay').value="01/" + thangVaoVien_Temp + "/" + namVaoVien;
		  if(thangVaoVien == thangHt && namVaoVien == namHt){
		  	document.getElementById(prefix_component + '__denngay').value= ngayhientai;
		  }
		  else{
		  	document.getElementById(prefix_component + '__denngay').value= getLastDayOfMonth (parseInt(thangVaoVien),parseInt(namVaoVien)) + "/" + thangVaoVien_Temp_2 + "/" + namVaoVien;
		  }
		  document.getElementById(prefix_component + '__tungay').focus();
		}
 	}

function setValueOnLoad(){
   // alert(10);
    var valueFinished = document.getElementById(prefix_component + "hid_ReportFinished").value;
   	var valueReportFileName = document.getElementById(prefix_component + "hid_ReportFileName").value;
   	//alert('valueFinished: ' + valueFinished);
   	//alert('valueReportFileName: ' + valueReportFileName);
   	//if (valueFinished != null && valueFinished != ""){
   	//	window.open (myContextPath + valueFinished + "report_result.jsp?tenBaoCao="+ valueReportFileName  ,"report","status=1,toolbar=1,resizable=1");
   	//	document.getElementById(prefix_component + "hid_ReportFinished").value='';
   	//	document.getElementById(prefix_component + '__chonlai').focus();
   //	}
   	//else{
   		//alert('12');
		document.getElementById(prefix_component + "__thang").focus();
	//}
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



