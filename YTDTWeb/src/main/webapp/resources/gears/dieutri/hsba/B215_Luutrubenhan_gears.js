function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            //setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            //setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           
			setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD',"getDmBenhIcd()","","","");
			setAttrForCombobox(prefix_component + 'DM_KHOA_MA_1','DM_KHOA_span1', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
			setAttrForCombobox(prefix_component + 'BENHVIEN_MA_2','DM_BENH_VIEN_span2', 'DM_BENH_VIEN',"getDmBenhVien()","","","");	
			
             timer.setTimeout(function(){onCompleteGetInfor();},100); 	
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function onCompleteGetInfor(){
	highlightOnFocus();
	
	//alert("onCompleteGetInfor()");
	
  try{	
  
               
	//myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	//alert(1);
	//myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	//alert(2);
	//myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_1', 'DM_KHOA');
    myOnblurTextbox(prefix_component + 'BENHVIEN_MA_2', 'DM_BENH_VIEN');

    //alert(5);
   //alert(5)
        
    
    
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
	
	
	
	var ngaysinh = document.getElementById(prefix_component + "__namsinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__namsinh").value = namsinh;
	}
	
	document.getElementById(prefix_component + "__sobenhan").focus();
			 	
    
  }catch(e){
    alert("onCompleteGetInfor():"+e);
  }    
}


