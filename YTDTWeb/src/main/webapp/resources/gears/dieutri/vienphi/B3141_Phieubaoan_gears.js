function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_3","DT_DM_NHAN_VIEN_span3","DT_DM_NHAN_VIEN__3","getDtDmNhanVien()","","","");
            
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
            
            //document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
                
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}




function onCompleteGetInfor(){
	try { 
	
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
}




function setOnload(){
	 try{
		 document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		 
	//  myOnblurTextboxJSF(prefix_component + 'DT_DM_DOI_TUONG_MA',prefix_component + 'DT_DM_DOI_TUONG');
	  
	   //alert(4);
	//  myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
	  
	//  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	//  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	  
	  //alert(5);
	  //myOnblurTextboxJSF(prefix_component + 'DT_DM_BAN_KHAM_MA',prefix_component + 'DT_DM_BAN_KHAM');
	  //myOnblurTextbox(prefix_component + '__mabankham', 'DT_DM_BAN_KHAM');
	  //alert(6);
	  //set_tuoi();
	  //alert(7);
	   //var valueFinished = 	document.getElementById(prefix_component + "hid_ReportFinished").value;
	   //alert("valueFinished:"+valueFinished);
	   //if (valueFinished != null && valueFinished != ""){
	   //		 window.open (myContextPath + "/report/result/dieutri/vienphi/report_Result.jsp?tenBaoCao=" + valueFinished ,"report","status=1,toolbar=1,resizable=1");
	   //		 document.getElementById(prefix_component + "hid_ReportFinished").value = "";
	   //		 //alert(1);
	   //}else{
	   
	   
	   /*
		   try{
				var msgObj = document.getElementById(prefix_component + "message_infor");
				if(msgObj != null){
					var resultHiddenValue =  document.getElementById(prefix_component + "resultHidden").value;
					if (resultHiddenValue == "success"){
						msgObj.className="mysucess";
					}else if (resultHiddenValue == "fail"){
						msgObj.className="myfail";
					}else{
						msgObj.value="";
					}			
				}
			}catch(e){
				alert("in setValueOnLoad()");
			}
		*/
	   	//	document.getElementById(prefix_component + "__mabankham").focus();	
		//}	
		
		/*var chuyenTuManHinhThuocCLS = document.getElementById(prefix_component + "_hidFocusDochuyenTuManHinhThuocCLS").value;
		if (chuyenTuManHinhThuocCLS == "true"){
			document.getElementById(prefix_component + "__loai").focus();
		}else{
			document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		}*/
		
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}

function reloadValueForCombobox()
{
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
	//document.getElementById(prefix_component + "__loai").focus();
}





