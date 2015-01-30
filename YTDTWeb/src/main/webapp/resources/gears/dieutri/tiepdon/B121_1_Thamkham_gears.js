        
function init() {
	
    if (window.google && google.gears) {
    	
        try {
            	//setAttrForCombobox(prefix_component + 'DM_DOI_TUONG_MA','DM_DOI_TUONG_span', 'DM_DOI_TUONG',"getDmDoiTuong()","","","");
                
                
                setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
               
                
                //alert(1);
                
                //setAttrForComboboxJSF(prefix_component + 'DM_KHOA_MA','DM_KHOA_span',prefix_component+ 'DM_KHOA','10');
                // setAttrForComboboxJSF_CLS(prefix_component + 'DTDMKYTHUAT_MA','DT_DM_KY_THUAT_span','DT_DM_KY_THUAT','10');
                
                //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_KY_THUAT_MA','DT_DM_KY_THUAT_span', 'DT_DM_KY_THUAT','10', DtDmKyThuat);
                //alert(2);
                // setAttrForCombobox_StoreValue(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA','10', DmKhoa);
                //alert(3);
                
                
                 timer.setTimeout(function(){setOnload();},100);         	            							  
            
            
        } catch (e) {
           alert("error at init");
        }
    }
    
}
/*
function setPriceForKT(){
  	
  try{	
    //alert(1);
	var maKTObj = document.getElementById(prefix_component + "DTDMKYTHUAT_MA");
	var soluongObj = document.getElementById(prefix_component + "__soluong");
	//alert(1111);
	try{
	    //alert("maKTObj.value:"+maKTObj.value);
	    //alert("soluongObj.value:"+soluongObj.value);
	    //alert("maKTObj.value"+maKTObj.value);
	     
		if (maKTObj.value == "" || soluongObj.value == "" || parseInt(soluongObj.value) <= 0 ){
		    
    	    //alert(2);
    		document.getElementById(prefix_component + "__loai").focus;
    		return;	
		}
	}catch(e){
	        //alert(3);
	    	document.getElementById(prefix_component + "__loai").focus;
	    	return;	    
	}
	//alert(1);
	
	getPriceKyThuatFromServer('GetPriceDtDmKyThuatAction',maKTObj.value);
	
	var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	
	var obj = document.getElementById(prefix_component + "__dongia");
	
	var yeuCauObject = document.getElementById(prefix_component + "__yc");
	var mienPhiObject = document.getElementById(prefix_component + "__mien");
	var ngoaiDanhMucObject = document.getElementById(prefix_component + "__ndm");
	
	//alert(obj);
	//alert(yeuCauObject);
	//alert(mienPhiObject);
	//alert(ngoaiDanhMucObject);
	
	var bnBaoHiem = document.getElementById(prefix_component + "DM_DOI_TUONG_MA");
	
	//alert(1);
	
	try{
		if (parseInt(donGia) = parseInt(donGiaBH)){
			document.getElementById(prefix_component + "__ndm").checked = true;
		}
	}catch(e){
	
	}
	
	if (yeuCauObject.checked == true){
		obj.value = donGiaYC;
	}else if (mienPhiObject.checked == true){
	  	
	    obj.value = donGiaMP; 
	}else if (bnBaoHiem.value == "BH"){
	     if (ngoaiDanhMucObject.checked == true){
	     	 obj.value = donGia;
	     }else{
	     	obj.value = donGiaBH;
	     }	
	}else{
		obj.value = donGia;	
	}
	document.getElementById(prefix_component + "donGiaHid").value = obj.value;
	
	//alert(maKTObj.value);
	var tenCLS = getTenFromMa( 'DT_DM_KY_THUAT' , maKTObj.value );
	document.getElementById(prefix_component + "tenDmKyThuatHid").value = tenCLS;
	//alert(tenCLS);
	
	
	
  }catch(e){
    alert("error at setPriceForKT()");
  }	

	
}
*/
function setOnload(){

  //alert("begin setOnload()");	
  //myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
  //alert(2);
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  //alert(3);
  
  //set_tuoi();
  document.getElementById(prefix_component + "__benhsu").focus();
  //alert("end setOnload()");	
  
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
		
}
function reloadValueForCombobox(){
	//myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_KY_THUAT');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//document.getElementById(prefix_component + "donGiaHid").value = "";
	
	document.getElementById(prefix_component + "__benhsu").focus();
}




