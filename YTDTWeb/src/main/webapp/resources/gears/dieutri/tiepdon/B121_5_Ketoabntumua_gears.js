        
function init() {
	
    if (window.google && google.gears) {setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
                setAttrForComboboxNoOnBlurCombobox(prefix_component + 'DT_DM_LOI_DAN_MA','DT_DM_LOI_DAN_span','DT_DM_LOI_DAN',"getDtDmLoiDan()","","","");
                setAttrForCombobox(prefix_component + 'DM_BAI_THUOC_MA','DM_BAI_THUOC_span','DM_BAI_THUOC',"getDmBaiThuoc()","myOnblurCombobox('" + prefix_component + "DM_BAI_THUOC_MA','DM_BAI_THUOC');","","");
                //setAttrForCombobox(prefix_component + 'DM_QUOC_GIA_MA','DM_QUOC_GIA_span','DM_QUOC_GIA',"getDmQuocGia()","","","");
                //setAttrForCombobox(prefix_component + 'DM_NHA_SAN_XUAT_MA','DM_NHA_SAN_XUAT_span','DM_NHA_SAN_XUAT',"getDmNhaSanXuat()","","","");
               
                //setAttrForCombobox(prefix_component + 'DM_CACH_DUNG_THUOC_MA','DM_CACH_DUNG_THUOC_span', 'DM_CACH_DUNG_THUOC',"getDmCachDungThuoc()","","","");
                setAttrForComboboxNoOnBlurCombobox(prefix_component + 'DT_DM_CHI_DAN_MA','DT_DM_CHI_DAN_span',  'DT_DM_CHI_DAN',"getDtDmChiDan()","","","");
                var catalog = new DmKhoa({});
                var khoa = catalog.getMaKhoaKham();  
                document.getElementById(prefix_component + "hid_KhoaMaSo").value = khoa.MaSo;
                document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "no";
                showListTPKBKTruoc();
                timer.setTimeout(function(){setOnload(); document.getElementById(prefix_component + 'temp_button_add').click();},100);        
        
    }
    
}

function showListTPKBKTruoc(){
	var isShowListCLSBKTruoc = document.getElementById(prefix_component + "isShowListTPKBKTruoc").value;
	//alert(fromMenu);
	if (isShowListCLSBKTruoc == "yes"){
		document.getElementById("divListTPKBKTruoc").style.display = "block";
		document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "no";
	}else{
		document.getElementById("divListTPKBKTruoc").style.display = "none";
		document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "yes";
	}
}

function onCompleteThemChinhSau(){	
	document.getElementById('DT_DM_CHI_DAN').value="";
}

function setInforForThuoc(){
  	
  try{	
    //alert(1);
	var maThuocObj = document.getElementById(prefix_component + "DM_THUOC_MA_pk");
	
	try{
	   
		if (maThuocObj.value == "" || maThuocObj.value == ""  ){
		    
    	    //alert(2);
    		document.getElementById(prefix_component + "DM_THUOC_MA").focus;
    		return;	
		}
	}catch(e){
	        //alert(3);
	    	document.getElementById(prefix_component + "DM_THUOC_MA").focus;
	    	return;	    
	}
	//alert(maKTObj.value);
	var catalog = new DmKhoa({});
	
	//var khoa = catalog.getMaKhoaKham();
	//alert(document.getElementById(prefix_component + "DM_THUOC_MA").value);
	
	
	
		
  }catch(e){
    alert("error at setInforForThuoc()");
  }	
}

function checkBeforeUpdateChiTiet(){
	document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value = document.getElementById( 'DT_DM_CHI_DAN').value;
		
	var maThuoc = document.getElementById(prefix_component + "DM_THUOC_MA").value;
	if (maThuoc == null || maThuoc == ""){
	   alert("Nhập mã thuốc.");
	   document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	   return false;
	}
	
	var soluong = document.getElementById(prefix_component + "__soluong").value;
	if (soluong == null || soluong== ""){
	 document.getElementById(prefix_component + "__soluong").focus();
	  alert("Nhập số lượng.");
	  return false;
	}
	if (parseFloat(soluong) <= 0){
	  alert("Nhập số lượng.");
	  document.getElementById(prefix_component + "__soluong").focus();
	  return false;
	}
	
	//alert("true");
	return true;

}

function setOnload_tiepdon(){
 //alert("begin setOnload()");	
  
 highlightOnFocus();
  //myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
  //alert(2);
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  
 // myOnblurTextbox(prefix_component + 'DM_THUOC_MA', 'DM_THUOC');
  //myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
  //myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
  //myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
  //myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN');
  //set_tuoi();
  document.getElementById(prefix_component + "DT_DM_LOI_DAN_MA").focus();
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
	
		
	var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
	}
	
	setDefaultValueForBanKham();

}

function setOnload(){

  //alert("begin setOnload()");	
   highlightOnFocus();
 
  document.getElementById('DT_DM_LOI_DAN').value = document.getElementById( prefix_component + 'DT_DM_LOI_DAN_hidden').value;
	
	
  //myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
  //alert(2);
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  //myOnblurTextbox(prefix_component + 'DT_DM_LOI_DAN_MA', 'DT_DM_LOI_DAN');
  //myOnblurTextbox(prefix_component + 'DM_THUOC_MA', 'DM_THUOC');
  //myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
  //myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
  //myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
  //myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN');
  //set_tuoi();
  document.getElementById(prefix_component + "DT_DM_LOI_DAN_MA").focus();
  
  
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
	
		
		var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
	}
	
	setDefaultValueForBanKham();
	
	
		var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	//alert(fromMenu);
	if (fromMenu == "yes"){
		document.getElementById("divNhapMoi").style.display = "block";
		document.getElementById("divQuaylai").style.display = "none";
		 
		 
		 document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").focus();
		 document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").select();
	}else{
	//alert(2);
		document.getElementById("divNhapMoi").style.display = "none";
		document.getElementById("divQuaylai").style.display = "block";
		document.getElementById(prefix_component + "DT_DM_LOI_DAN_MA").focus();
		 
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").disabled = true;
		if (document.getElementById( "DT_DM_BAN_KHAM").disabled == false){
      	  changeDisabledAttr("DT_DM_BAN_KHAM");  
   		} 
		document.getElementById(prefix_component + "__matiepdon").disabled = true;
		 
	}
		//alert("end setOnload()");	
}

function reloadValueForComboboxForEnter(){
	//myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_KY_THUAT');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//document.getElementById(prefix_component + "donGiaHid").value = "";
	
	document.getElementById( 'DT_DM_CHI_DAN').value = document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value;
	
	
	highlightOnFocus();
	document.getElementById(prefix_component + "__donvi").value=document.getElementById(prefix_component + "__donvi_HIDDEN").value;
	
		
	//myOnblurTextbox(prefix_component + 'DM_THUOC_MA', 'DM_THUOC');
    //myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	//myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	//myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
	
	//myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN');
	//myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN')
    document.getElementById(prefix_component + "DM_THUOC_MA").focus();

}

function reloadValueForCombobox(){
               
	//myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_KY_THUAT');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//document.getElementById(prefix_component + "donGiaHid").value = "";
	
	document.getElementById( 'DT_DM_CHI_DAN').value = document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value;
	
	
	highlightOnFocus();
	document.getElementById(prefix_component + "__donvi").value=document.getElementById(prefix_component + "__donvi_HIDDEN").value;
	
	
	
	
	
	//myOnblurTextbox(prefix_component + 'DM_THUOC_MA', 'DM_THUOC');
    //myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	//myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	//myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
	myOnblurTextbox(prefix_component + 'DM_BAI_THUOC_MA', 'DM_BAI_THUOC');
	//myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN');
	myOnblurTextbox(prefix_component + 'DT_DM_CHI_DAN_MA', 'DT_DM_CHI_DAN');
    document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	
}

function setHiddenValueForCombobox(){
	//document.getElementById(prefix_component + "DM_THUOC_HIDDEN").value = document.getElementById(prefix_component + "DM_THUOC").value;
	
	//document.getElementById(prefix_component + "__donvi_HIDDEN").value = document.getElementById(prefix_component + "__donvi").value;

	//document.getElementById(prefix_component + "hid_CachDungThuoc_Ma").value = document.getElementById(prefix_component + "DM_CACH_DUNG_THUOC_MA").value;
	//document.getElementById(prefix_component + "hid_CachDungThuoc_Ten").value = document.getElementById( "DM_CACH_DUNG_THUOC").value;
}

function setTenDonViTinh(){
	var ten = "";
	ten = getTenDonViTinhFromDmThuoc(document.getElementById(prefix_component + 'DM_THUOC_MA_pk').value);
	document.getElementById(prefix_component + "__donvi").value = ten;
	
	
	
	//alert(ten);
}

function setCachDungThuoc(){
	
	
//	var obj = getObject("DM_THUOC");
//	var objDmCachDungThuoc = getObject("DM_CACH_DUNG_THUOC");
//	try{
//		if (obj != null) {
//			var listObj = obj.filter("MaSo = ?", document.getElementById(prefix_component + 'DM_THUOC_MA_pk').value).toArray();
//			//alert(listObj[0].DMDONVITINH_MASO);
//			var listObjDmCachDungThuoc = objDmCachDungThuoc.filter("MaSo = ?", listObj[0].DMCACHDUNG_MASO).toArray();
//			if (listObjDmCachDungThuoc!=null && listObjDmCachDungThuoc.length > 0){
//				document.getElementById(prefix_component + 'DM_CACH_DUNG_THUOC_MA').value = listObjDmCachDungThuoc[0].Ma;
//				document.getElementById(prefix_component + 'DM_CACH_DUNG_THUOC_MA_pk').value = listObjDmCachDungThuoc[0].MaSo;
//				 myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
//			}
//			
//			
//		}
//	}catch(e){
//	}
	
	myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
	//alert(ten);
}


function setDefaultValueForBanKham(){
	var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_5_Ketoabntumua");
	if (bankhamClientDefault) {
		if(document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value != null &&
		  document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value != ""){
		
		}else{
			document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = bankhamClientDefault.Ten;
			myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
		}
	}
}
// luu y : chi goi khi nhan tu menu
function luuTruGiaTriClientDefault(){
	var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	if (fromMenu == "yes"){
		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_5_Ketoabntumua");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121201;
				chValues[1] = "B121_3_xutrithuoc_bankham";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_5_ketoabntumua", chNames,chValues );
				
			}    	
		}
	}
}




