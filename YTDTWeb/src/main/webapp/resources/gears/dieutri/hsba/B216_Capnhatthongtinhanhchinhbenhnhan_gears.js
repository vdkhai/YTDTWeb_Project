function init() {
	
    if (window.google && google.gears) {
    	
        //try {
        	//db = google.gears.factory.create('beta.database');
            	//setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	
            	setAttrForCombobox(prefix_component + "DIADIEM_MA","DM_DIA_DIEM_span","DM_DIA_DIEM","getDmDiaDiem()","","","");
				setAttrForCombobox(prefix_component + 'DM_TAI_NAN_MA','DM_TAI_NAN_span', 'DM_TAI_NAN',"getDmTaiNan()","onChangePhuongThucGayTaiNan()","","");
 			setAttrForCombobox(prefix_component + 'DM_PHUONG_TIEN_MA','DM_PHUONG_TIEN_span', 'DM_PHUONG_THUC_GAY_TAI_NAN',"getDmPhuongThucGayTaiNanByTaiNanMa(\"" + prefix_component + "DM_TAI_NAN_MA_pk" + "\")","","","");
		

			setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           
        	setAttrForCombobox(prefix_component + 'DM_DAN_TOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC',"getDmDanToc()","","","");
				
				
		
   		
   		 timer.setTimeout(function(){onCompleteGetInfor();},100); 
            
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
}

function onChangePhuongThucGayTaiNan(){
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA_pk").value = "";
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA").value = "";
	document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value = "";
}


function onCompleteGetInfor(){

 	//setAttrForCombobox_StoreValue(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'HUYEN_MA','DM_HUYEN_span','DM_HUYEN','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'XA_MA','DM_XA_span','DM_XA','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'NGHENGHIEP_MA','DM_NGHE_NGHIEP_span','DM_NGHE_NGHIEP','10');
	//setAttrForCombobox_StoreValue(prefix_component + 'DANTOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC','10');
                
               
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	
	myOnblurTextbox(prefix_component + 'DIADIEM_MA', 'DM_DIA_DIEM');
	//alert(1);
	myOnblurTextbox(prefix_component + 'DM_TAI_NAN_MA', 'DM_TAI_NAN');
	//alert(1);
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	//alert(1);
	//set_tuoi();
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	//alert(sobenhan);
	if(sobenhan == ''){
	//alert(sobenhan);
		document.getElementById(prefix_component + "__sobenhan").focus();
	}
	else{
		//document.getElementById(prefix_component + "__giovao").focus();
	} 
	
	//alert(document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value);
/*
  if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "1" ) {
          //alert(1);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "2") {
		 //alert(2);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked = true ;
			
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "3") {
		 //alert(3);
			 document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked = true;
		} else {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		
		}
    */
  
    //alert(1);
	//document.getElementById(prefix_component + "__sobenhan").focus();
	if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
	){
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
	}
}


function onCompleteGetInfor_Reset(){

 	//setAttrForCombobox_StoreValue(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'HUYEN_MA','DM_HUYEN_span','DM_HUYEN','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'XA_MA','DM_XA_span','DM_XA','10');
    //setAttrForCombobox_StoreValue(prefix_component + 'NGHENGHIEP_MA','DM_NGHE_NGHIEP_span','DM_NGHE_NGHIEP','10');
	//setAttrForCombobox_StoreValue(prefix_component + 'DANTOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC','10');
                
               
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	
	myOnblurTextbox(prefix_component + 'DIADIEM_MA', 'DM_DIA_DIEM');
	//alert(1);
	myOnblurTextbox(prefix_component + 'DM_TAI_NAN_MA', 'DM_TAI_NAN');
	//alert(1);
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	//alert(1);
	//set_tuoi();
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	//alert(sobenhan);
	if(sobenhan == ''){
	//alert(sobenhan);
		document.getElementById(prefix_component + "__sobenhan").focus();
	}
	else{
		//document.getElementById(prefix_component + "__giovao").focus();
	} 
	

 	setValueOnLoad();
 	
}

function checkForGioi(){
	var valueTen = document.getElementById(prefix_component + "__hoten").value;
	
	if (valueTen != null && valueTen != "" && ( valueTen.indexOf(thi_1) >= 0 || valueTen.indexOf(thi_2) >= 0)){
		
		//alert(valueTen.indexOf(thi_1));
		//alert( document.getElementById(prefix_component + "__gioitinh:1"));
		document.getElementById(prefix_component + "__gioitinh:1").checked = true;
		//alert(document.getElementById(prefix_component + "__nu").checked);	
	}
	
}

function setValueOnLoad(){
  try{
  	
  	//alert(document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value);
  	/*
        if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "1" ) {
          //alert(1);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "2") {
		 //alert(2);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked = true ;
			
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "3") {
		 //alert(3);
			 document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked = true;
		} else {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		
		}
    */
  
    //alert(1);
	//document.getElementById(prefix_component + "__sobenhan").focus();
	if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
	){
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
	}
	
	
	setTimeout(function() {
			var dantoc = DmDanToc.getByFieldValue("DMDANTOC_DEFA", 1);
			if (dantoc) {
				document.getElementById(prefix_component + "DM_DAN_TOC_MA").value = dantoc.Ma;
				dojo.byId("DM_DAN_TOC").value = dantoc.Ten;
			}
			
			var tinh = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
			if (tinh) {
				document.getElementById(prefix_component + "TINH_MA").value = tinh.Ma;
				dojo.byId("DM_TINH").value = tinh.Ten;
			}
			
			var huyen = DmHuyen.getByFieldValue("DMHUYEN_DEFA", 1);
			if (huyen) {
				document.getElementById(prefix_component + "HUYEN_MA").value = huyen.Ma;
				dojo.byId("DM_HUYEN").value = huyen.Ten;
			}
			
			var xa = DmXa.getByFieldValue("DMXA_DEFA", 1);
			if (xa) {
				document.getElementById(prefix_component + "XA_MA").value = xa.Ma;
				dojo.byId("DM_XA").value = xa.Ten;
			}
			
			var gt = DmGioiTinh.getByFieldValue("DMGT_DEFA", 1);
			if (gt) {
				if (gt.Ma == "0") {
					document.getElementById(prefix_component + "__gioitinh" + ":1").checked = true;
				} else if (gt.Ma == "1") {
					document.getElementById(prefix_component + "__gioitinh" + ":0").checked = true;
				}
			}
			
			
			
			
		}, 200);
	
	
  }catch(e){
    alert(e.description);
  }	
}


