function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	LoadCatalogFromServer_each(DtDmLoaiAn,"GetDtDmLoaiAnAction");
        	LoadCatalogFromServer_each(DtDmLoaiAn2,"GetDtDmLoaiAn2Action");
    		LoadCatalogFromServer_each(DtDmMucAn,"GetDtDmMucAnAction");		
    		LoadCatalogFromServer_each(DtDmCheDoAn,"GetDtDmCheDoAnAction");	    		
    		LoadCatalogFromServer_each(DtDmDoiTuongAn,"GetDtDmDoiTuongAnAction");    		
    		LoadCatalogFromServer_each(DtDmDongThem,"GetDtDmDongThemAction");    		
        	//    		
    		setAttrForCombobox(prefix_component + "DOITUONGAN_MA","DT_DM_DOI_TUONG_AN_span","DT_DM_DOI_TUONG_AN", "getDtDmDoiTuongAn()","", "checkDoituongan();", "");    		
        	setAttrForCombobox(prefix_component + "LOAIAN_MA","DT_DM_LOAI_AN_span","DT_DM_LOAI_AN", "getDtDmLoaiAn()","resetDmLoaiAn2();", "checkLoaiAn();", "");
        	setAttrForCombobox(prefix_component + "LOAIAN2_MA","DT_DM_LOAI_AN2_span","DT_DM_LOAI_AN2", "getDtDmLoaiAn2(\"" + prefix_component + "LOAIAN_MA_pk\")","", "checkLoaiAn2();", "");
        	
        	setAttrForCombobox(prefix_component + "MUCAN_MA","DT_DM_MUC_AN_span","DT_DM_MUC_AN", "getDtDmMucAn()","", "", "");
        	
        	//setAttrForCombobox(prefix_component + "CHEDOAN_MA","DT_DM_CHE_DO_AN_span","DT_DM_CHE_DO_AN", "getDtDmCheDoAn()","", "", "");        	        	
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","seachPhieubaoan()","");            
            setAttrForCombobox(prefix_component + "DONGTHEM_MA","DT_DM_DONG_THEM_span","DT_DM_DONG_THEM","getDtDmDongThem()","","","");            
            //document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
                
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}




function onCompleteGetInfor(){
	try { 
	
		//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
		
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		myOnblurTextbox(prefix_component + 'LOAIAN_MA', 'DT_DM_LOAI_AN');
		myOnblurTextbox(prefix_component + 'LOAIAN2_MA', 'DT_DM_LOAI_AN2');
		myOnblurTextbox(prefix_component + 'MUCAN_MA', 'DT_DM_MUC_AN');
		//myOnblurTextbox(prefix_component + 'CHEDOAN_MA', 'DT_DM_CHE_DO_AN');
		//myOnblurTextbox(prefix_component + 'PHONG_MA', 'DT_DM_PHONG');
		
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
		
		
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
}




function setOnload(){
	 try{
		 document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		 
		 var gt = DmGioiTinh.getByFieldValue("DMGT_DEFA", 1);
			if (gt  && (document.getElementById(prefix_component + "__gioitinh" + ":1").checked == false && document.getElementById(prefix_component + "__gioitinh" + ":0").checked == false)) {
				if (gt.Ma == "0") {
					document.getElementById(prefix_component + "__gioitinh" + ":1").checked = true;
				} else if (gt.Ma == "1") {
					document.getElementById(prefix_component + "__gioitinh" + ":0").checked = true;
				}
			}
	  
			
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
	//myOnblurTextbox(prefix_component + 'PHONG_MA', 'DT_DM_PHONG');
	myOnblurTextbox(prefix_component + 'LOAIAN_MA', 'DT_DM_LOAI_AN');
	myOnblurTextbox(prefix_component + 'LOAIAN2_MA', 'DT_DM_LOAI_AN2');
	myOnblurTextbox(prefix_component + 'MUCAN_MA', 'DT_DM_MUC_AN');
	//myOnblurTextbox(prefix_component + 'CHEDOAN_MA', 'DT_DM_CHE_DO_AN');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	//document.getElementById(prefix_component + "__loai").focus();
}



function formatHoTen(){
	
	var hoTen = document.getElementById(prefix_component + "__hoten").value;
	hoTen = hoTen.trim();

	var result = "";
	if (hoTen != null && hoTen != ""){
	
		var index = hoTen.indexOf(' ');
		if (index != null && parseInt(index) >= 0){
		
			while (index != null && parseInt(index) >= 0  && hoTen != null && hoTen.length > 0){
				var temp =  hoTen.substring(0,index + 1);
				//alert(temp);
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				//alert(temp1);
				temp1 = temp1.toUpperCase();
				
				var temp2 = temp.substring(1,temp.lengh); 
				//alert(temp2);
				temp2=temp2.toLowerCase();
				
				
				result = result + temp1 + temp2;
				 
				hoTen = hoTen.substring(index + 1, hoTen.length );
				
				
								
				
				index = hoTen.indexOf(' ');
			}	
			if (hoTen != null && hoTen != ""){
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
			}
			
		}else{
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
		}	
		
		
		document.getElementById(prefix_component + "__hoten").value = result;	
	}

}

function checkAdded()
{
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	var maloaian =  document.getElementById(prefix_component + "LOAIAN_MA").value;
	var mamucan =  document.getElementById(prefix_component + "MUCAN_MA").value;
	//var machedoan =  document.getElementById(prefix_component + "CHEDOAN_MA").value;
	//var maphong =  document.getElementById(prefix_component + "PHONG_MA").value;
	
	if (sobenhan == null || sobenhan == ""){
		  alert("Vui lòng nhập số bệnh án");
		  return false;
		}
	
	if (maphong == null || maphong == ""){
		  alert("Vui lòng chọn phòng");
		  return false;
		}
	
	if (maloaian == null || maloaian == ""){
	  alert("Vui lòng chọn loại ăn");
	  return false;
	}
	
	if (mamucan == null || mamucan == ""){
		  alert("Vui lòng chọn mức ăn");
		  return false;
		}
	if (machedoan == null || machedoan == ""){
		  alert("Vui lòng chọn chế độ ăn");
		  return false;
		}
	return true;
}

function resetDmLoaiAn2() {
	document.getElementById(prefix_component + 'LOAIAN2_MA').value = "";
 	resetForCombobox('DT_DM_LOAI_AN2',prefix_component + 'LOAIAN2_MA');
}





