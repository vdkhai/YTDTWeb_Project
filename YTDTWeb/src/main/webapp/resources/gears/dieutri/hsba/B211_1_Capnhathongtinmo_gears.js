
                                              
function init() {
	
    if (window.google && google.gears) {
    	
	try {
		setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa()","","","");
	 	setAttrForCombobox(prefix_component + 'DANTOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC',"getDmDanToc()","","","");
	 	
	 	
	 	setAttrForCombobox(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH',"getDmTinh()","","","");
        setAttrForCombobox(prefix_component + 'HUYEN_MA','DM_HUYEN_span', 'DM_HUYEN',"getDmHuyen(\"" + prefix_component + "TINH_MA\")","","","");
        setAttrForCombobox(prefix_component + 'XA_MA','DM_XA_span', 'DM_XA',"getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
		
		
		setAttrForCombobox(prefix_component + 'NGHENGHIEP_MA','DM_NGHE_NGHIEP_span', 'DM_NGHE_NGHIEP',"getDmNgheNghiep()","","","");
			
    	setAttrForCombobox(prefix_component + 'DT_DM_PHAU_THUAT_MA','DT_DM_PHAU_THUAT_span', 'DT_DM_PHAU_THUAT',"getDtDmPhauThuat()","","","");
    	setAttrForCombobox(prefix_component + 'DT_DM_VO_CAM_MA','DT_DM_VO_CAM_span', 'DT_DM_VO_CAM',"getDtDmVoCam()","","","");   
		setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmNhanVien_Mo()","","","");  
		
		setAttrForCombobox(prefix_component + 'DT_DM_CAP_CUU_PHIEN_MA','DT_DM_CAP_CUU_PHIEN_span', 'DT_DM_CAP_CUU_PHIEN',"getDtDmCapCuuPhien()","","","");
		            //---Load DB and set atrribute for combobox--- 
           
                //setAttrForCombobox_StoreValue(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH','10', DmTinh);
                //setAttrForCombobox_StoreValue(prefix_component + 'HUYEN_MA','DM_HUYEN_span','DM_HUYEN','10', DmHuyen);
                //setAttrForCombobox_StoreValue(prefix_component + 'XA_MA','DM_XA_span','DM_XA','10', DmXa);
                //setAttrForCombobox_StoreValue(prefix_component + 'NGHENGHIEP_MA','DM_NGHE_NGHIEP_span','DM_NGHE_NGHIEP','10', DmNgheNghiep);
                //setAttrForCombobox_StoreValue(prefix_component + 'DANTOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC','10', DmDanToc);
              
              
                //setAttrForComboboxJSF(prefix_component + 'DM_BENH_ICD_MA','DM_BENH_ICD_span',prefix_component+ 'DM_BENH_ICD','10');
                //setAttrForCombobox_StoreValue(prefix_component + 'DM_BENH_ICD_MA','DM_BENH_ICD_span', 'DM_BENH_ICD','10', DmBenhIcd);
               
                //setAttrForCombobox_StoreValue(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA','10', DmKhoa);
            
              // setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1','10', DtDmNhanVien);
               
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA_2','DT_DM_NHAN_VIEN_span2', 'DT_DM_NHAN_VIEN__2','10', DtDmNhanVien);
               
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA_3','DT_DM_NHAN_VIEN_span3', 'DT_DM_NHAN_VIEN__3','10', DtDmNhanVien);
               
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA_4','DT_DM_NHAN_VIEN_span4', 'DT_DM_NHAN_VIEN__4','10', DtDmNhanVien);
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_PHAU_THUAT_MA','DT_DM_PHAU_THUAT_span', 'DT_DM_PHAU_THUAT','10', DtDmPhauThuat);
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_VO_CAM_MA','DT_DM_VO_CAM_span', 'DT_DM_VO_CAM','10', DtDmVoCam);
               
         		//chonTaiBien();
                
                timer.setTimeout(function(){setValueOnLoad();},100); 
               
                          							  

        } catch (e) {
        
         alert("init():" + e);
         
         }
    }
    
}
function onCompleteGetInforForAddorUpdate(){

	//document.getElementById(prefix_component + "__capcuu_phien").focus(); 
}
function chonTaiBien(){
 

}
function onCompleteGetInforBegin(){
  try{
  
 
	
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	myOnblurTextbox(prefix_component + 'DANTOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'NGHENGHIEP_MA', 'DM_NGHE_NGHIEP');
	
	

	document.getElementById(prefix_component + "__mathoigianmo").focus(); 
	
	//var hoTen = document.getElementById(prefix_component + "__hoten").value;


	//if (hoTen == null || hoTen ==""){
	  //document.getElementById(prefix_component + "DM_KHOA_MA").focus(); 
	//}
	
  }catch(e){
    alert(e);
  }	
}
function onCompleteGetInfor(){
   try{
		
	
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');

	
	myOnblurTextbox(prefix_component + 'DT_DM_PHAU_THUAT_MA', 'DT_DM_PHAU_THUAT');
	myOnblurTextbox(prefix_component + 'DT_DM_VO_CAM_MA',  'DT_DM_VO_CAM');
	
	myOnblurTextbox(prefix_component + 'DT_DM_CAP_CUU_PHIEN_MA',  'DT_DM_CAP_CUU_PHIEN');

	
	
	
	
	document.getElementById(prefix_component + "__mathoigianmo").focus(); 
	//alert(10);
	}catch(e){
	   alert(e);
	}
}

function setValueOnLoad(){
    //alert(1);
    
    myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	myOnblurTextbox(prefix_component + 'DANTOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'NGHENGHIEP_MA', 'DM_NGHE_NGHIEP');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	
	
	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	chonTaiBien();
	
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
		document.getElementById(prefix_component + "__mathoigianmo").focus(); 
	
	try{
	/*
		var msgObj = document.getElementById(prefix_component + "message_infor");
		if(msgObj != null){
			var resultHiddenValue =  document.getElementById(prefix_component + "resultHidden").value;
			//alert(resultHiddenValue);
			if (resultHiddenValue == "success"){
				msgObj.className="mysucess";
			}else if (resultHiddenValue == "fail"){
				msgObj.className="myfail";
			}else{
				msgObj.value="";
			}			
		}
	*/	
	}catch(e){
		alert("in setValueOnLoad()");
	}
	
}


function testCalendarFirst(var1){
  var valid1 = is_wellformed_Date(var1);
  if (valid1 == "undefined" || valid1 == false){
    return valid1;
  }
  var form = document.forms[0];
  
  var valid2 = iesvn_ValidateIntRange(form)  ;
  if (valid2 == "undefined" || valid2 == false){
    return valid2;
  }
  //alert(1);
  var valid3 = iesvn_ValidateComparedDates(form);
  //alert(2);
  if (valid3 == "undefined" || valid3 == false){  	
    document.getElementById(prefix_component + "__thoigianketthuc").focus();
    document.getElementById(prefix_component + "__thoigianketthuc").select();
    return valid3;
  }   	
  //alert(2);

  var valid4 = iesvn_ValidateTwoDatesWithHours();
  return valid4;
}



