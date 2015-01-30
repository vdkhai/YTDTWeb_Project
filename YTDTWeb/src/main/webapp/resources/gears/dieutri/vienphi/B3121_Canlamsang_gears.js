function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	LoadCatalogFromServer_each(DtDmClsBangGia,"GetDmKyThuatAction");
            //setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");  
            setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");  
            setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
            
            setAttrForCombobox(prefix_component + 'DTDMKYTHUAT_MA','DT_DM_CLS_BANG_GIA_span','DT_DM_CLS_BANG_GIA',"getDtDmClsBangGia_Search(\""+ prefix_component + "DTDMKYTHUAT_MA\" ,\""+prefix_component + "__loai\")","","setKTC(\""+ prefix_component + "DTDMKYTHUAT_MA\");setPriceForKT();","");
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
            setAttrForCombobox(prefix_component + 'DM_KHOA1_MA','DM_KHOA1_span', 'DM_KHOA__1',"getKhoaHavingCLS();","","displayCDHA();","");
            
           //document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
          //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
            //initshorcut();
            
           timer.setTimeout(function(){setOnload();onCompleteGetInfor();},10); 
            
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}

function initshorcut(){    
    shortcut.add("Backspace", function() {
        //no action for disable Backspace
    },{'type':'keydown',"propagate":false,'disable_in_input':true,'target':document});
}

// phuc.lc 24-09-2010
function checkDonGia(checkbox) {
/*	if (checkbox.checked) {
		document.getElementById(prefix_component + "donGiaHid").value = document.getElementById(prefix_component + "hid_GiaYC").value;
	} else {
		document.getElementById(prefix_component + "donGiaHid").value = document.getElementById(prefix_component + "hid_Gia").value;
		// Neu bo chon yeu cau, thi cung bo chon dich vu
		document.getElementById(prefix_component + "__dichVu").checked = false;
	}
	document.getElementById(prefix_component + "__dongia").value = document.getElementById(prefix_component + "donGiaHid").value;
	numberFormatBlur(document.getElementById(prefix_component + "donGiaHid"));
*/	
	if (!checkbox.checked) {
		// Neu bo chon yeu cau, thi cung bo chon dich vu
		document.getElementById(prefix_component + "__dichVu").checked = false;
	}
	setOtherValueForGetFromServer ();
}

function setCheckForDichVu(){
    //alert(document.getElementById(prefix_component + "__dichVu").checked);
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
	//phuc.lc 24-09-2010
	checkDonGia(document.getElementById(prefix_component + "__yc"));
}

function onChangeForYC(){
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
}

function setKTC(danhmuc_banggia_ma){
    //alert("setKTC");
	var valueMa = document.getElementById(danhmuc_banggia_ma).value;
	if (valueMa !=null && valueMa != ""){
		var KTC = getKTCFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		if (KTC == "KTC"){
		    //alert("La KTC" + KTC);
		    //alert(document.getElementById(prefix_component + "__ktc"))
			document.getElementById(prefix_component + "__ktc").checked = true;
			document.getElementById(prefix_component + "__ktc_hid").value = true;
			
		}else{
			document.getElementById(prefix_component + "__ktc").checked = false;
			document.getElementById(prefix_component + "__ktc_hid").value = false;		
		}	
		
		var NDM = getNDMFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		if (NDM == "1"){
		    //alert("La NDM" + NDM);
		    //alert(document.getElementById(prefix_component + "__ndm"))
			document.getElementById(prefix_component + "__ndm").checked = true;
			document.getElementById(prefix_component + "__ndm_hid").value = true;
			
		}else{
			document.getElementById(prefix_component + "__ndm").checked = false;
			document.getElementById(prefix_component + "__ndm_hid").value = false;		
		}	
		
		var Mien = getMIENFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		if (Mien == "1"){
		    //alert("La NDM" + NDM);
		    //alert(document.getElementById(prefix_component + "__ndm"))
			document.getElementById(prefix_component + "__mien").checked = true;
			document.getElementById(prefix_component + "__mien_hid").value = true;
			
		}else{
			document.getElementById(prefix_component + "__mien").checked = false;
			document.getElementById(prefix_component + "__mien_hid").value = false;		
		}	
		
	}

}
function setOtherValueForGetFromServer (){

	var maKTObj = document.getElementById(prefix_component + "DTDMKYTHUAT_MA_pk");

    var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	
	var obj = document.getElementById(prefix_component + "__dongia");
	
	var yeuCauObject = document.getElementById(prefix_component + "__yc");
	var mienPhiObject = document.getElementById(prefix_component + "__mien");
	var ngoaiDanhMucObject = document.getElementById(prefix_component + "__ndm");
	//phuc.lc : 11/08/2011
	var khongThuObject = document.getElementById(prefix_component + "__kthu");
	//alert(obj);
	//alert(yeuCauObject);
	//alert(mienPhiObject);
	//alert(ngoaiDanhMucObject);
	
	var maDoituong= document.getElementById(prefix_component + "DM_DOI_TUONG_MA");
	var ngayBatDauBh = document.getElementById(prefix_component + "__hidBatDauBh").value;
	var ngayHetHanBh = document.getElementById(prefix_component + "__hidHetHanBh").value;
	//alert("maDoituong.value = " + maDoituong.value);
	
	//try{
	//	if (parseInt(donGia) = parseInt(donGiaBH)){
	//		document.getElementById(prefix_component + "__ndm").checked = true;
	//	}
	//}catch(e){
	//
	//}
	// Neu co chon khong thu thi don gia = 0, ap dung cho tat ca cac doi tuong kham benh
	if (khongThuObject.checked == true) {
		obj.value = 0; 
	}else if (yeuCauObject.checked == true){		
	// Neu co chon yeu cau thi lay don gia yeu cau	
		obj.value = donGiaYC;
	}else {
	// Neu khong yeu cau thi xet theo tung doi tuong		
		if (maDoituong.value == "BH"){			
			if (mienPhiObject.checked == true){
				// CLS mien phi ==> lay gia mien phi				
			    obj.value = donGiaMP; 
			} else if (ngoaiDanhMucObject.checked == true ||
					checkHetHanBH(ngayBatDauBh,ngayHetHanBh) == true) {				
				// CLS ngoai danh muc hoac het han bao hiem ==> lay gia thuong
				obj.value = donGia;
			} else {
				// Cac truong hop con lai ==> lay gia bao hiem				
				obj.value = donGiaBH;
			}
		} else if (maDoituong.value == "MP") {
			// Doi tuong mien phi neu khong yeu cau thi luon luon lay gia mien phi			
			obj.value = donGiaMP;
		} else if (maDoituong.value == "TP") {
			if (mienPhiObject.checked == true){
				// CLS mien phi ==> lay gia mien phi
			    obj.value = donGiaMP; 
			} else {
				obj.value = donGia;
			}
		} else {
			obj.value = donGia;
		}
		
	}	
	document.getElementById(prefix_component + "donGiaHid").value = obj.value;
	
	
	var tenCLS = getTenFromMaSo( 'DT_DM_CLS_BANG_GIA' , maKTObj.value );
	
	//alert(tenCLS);
	document.getElementById(prefix_component + "tenDmKyThuatHid").value = tenCLS;
	
	
	//blockInphieu();

}

function getDtDmClsBangGia_KyThuat(){
	
	var catalog = new DtDmClsBangGia({});
	
	var textboxValue = document.getElementById(prefix_component + "DTDMKYTHUAT_MA").value;
	var chuDau = document.getElementById(prefix_component + "__loai").value;
	
	var myMaso = getMaSo("DT_DM_CLS","Ma",chuDau);
			
	return catalog.getDataList_CLS(textboxValue, myMaso);
	
			
}

function onCompleteGetInfor(){
	try { 
	
	    //alert(document.getElementById(prefix_component + "__ndm_hid").value);
	  // alert(111);
		//blockInphieu();
		//alert(112);
	    //var valueTen = document.getElementById(prefix_component + "__hoten").value;
	    //if (valueTen == ''){
	    //	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	    //	//return;
	    //}
	    
	    //alert(0);
	    
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
		//alert(11);
	    
		myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_CLS_BANG_GIA');
		
		//alert(1);
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		
		myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
		myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
 		myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');

		//myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
		//alert(1);
		//alert(5);
		//set_tuoi();
 		myOnblurTextbox(prefix_component + 'DM_KHOA1_MA', 'DM_KHOA__1');
		
		//alert(2);
		var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
		
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
		
		//alert(3);
			if (document.getElementById(prefix_component + "__ktc_hid").value == "true"){		   
			document.getElementById(prefix_component + "__ktc").checked = true;
		}else{
				document.getElementById(prefix_component + "__ktc").checked = false;
		}	
		
		
		//alert(document.getElementById(prefix_component + "__ndm_hid").value);
		if (document.getElementById(prefix_component + "__ndm_hid").value == "true"){		   
				document.getElementById(prefix_component + "__ndm").checked = true;
				//alert(document.getElementById(prefix_component + "__ndm").checked);
		}else{
				document.getElementById(prefix_component + "__ndm").checked = false;
		}
		
		if (document.getElementById(prefix_component + "__mien_hid").value == "true"){		   
				document.getElementById(prefix_component + "__mien").checked = true;
		}else{
				document.getElementById(prefix_component + "__mien").checked = false;
		}
		
		//if (wheretofocus == "__loai"){
		//	document.getElementById(prefix_component + "__loai").focus();
		//}
		var num =  parseFloat( document.getElementById(prefix_component + "__dongia").value) ;
		
		document.getElementById(prefix_component + "donGiaHid").value = num;
		
		numberFormatBlur(document.getElementById(prefix_component + "donGiaHid"));
		
		
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
	//document.getElementById(prefix_component + "BENHVIEN_MA").focus(); 
}
function setValueForSoLuong(){
	if (document.getElementById(prefix_component + "__soluong").value == "" || document.getElementById(prefix_component + "__soluong").value == "0"){		   
			document.getElementById(prefix_component + "__soluong").value = "1";
			document.getElementById(prefix_component + "__soluong").select();
	}
}
function setPriceForKT(){
	
  try{	
	var maKTObj = document.getElementById(prefix_component + "DTDMKYTHUAT_MA_pk");
	var soluongObj = document.getElementById(prefix_component + "__soluong");
	
	// phuc.lc 24-09-2010
	if (parseInt(soluongObj.value,10) <= 0) {
		soluongObj.value = 1;
	}
	
	try{
	    //alert(1112);
		if (maKTObj.value == "" || soluongObj.value == "" ){
    	    //alert(2);
    		//document.getElementById(prefix_component + "__loai").focus;
			document.getElementById("DT_DM_CLS_BANG_GIA").focus();
			
    		return;	
		}
	}catch(e){
	       // alert(3);
	    	//document.getElementById(prefix_component + "__loai").focus;
			document.getElementById("DT_DM_CLS_BANG_GIA").focus();
	    	return;	    
	}		
	getPriceKyThuatFromServer('GetPriceDtDmKyThuatAction',maKTObj.value);
	
	// phuc.lc 24-09-2010 comment
	// phan code duoi day du thua, da thuc hien trong ham setOtherValueForGetFromServer()
	// khi goi ham getPriceKyThuatFromServer trong function_gear.js
	
	/* phuc.lc begin 
	//alert(2);
	var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	//alert(21);
	var obj = document.getElementById(prefix_component + "__dongia");
	
	
	
	var yeuCauObject = document.getElementById(prefix_component + "__yc");
	var mienPhiObject = document.getElementById(prefix_component + "__mien");
	//var ngoaiDanhMucObject = document.getElementById(prefix_component + "__ndm");
	//alert(22);
	var bnBaoHiem = document.getElementById(prefix_component + "__hidBHYT");
	
	
	//alert("donGia:"+donGia);
	//alert("donGiaBH:"+donGiaBH);
	//alert("bnBaoHiem.value:"+bnBaoHiem.value);
	
	//try{
	//	if (parseInt(donGia) == parseInt(donGiaBH)){
	//		document.getElementById(prefix_component + "__ndm").checked = true;
	//	}
	//}
	//catch(e){
	///          
	//}
	//alert(22);
	
	//alert("yeuCauObject"+yeuCauObject);
	//alert("mienPhiObject"+mienPhiObject);
	//alert("bnBaoHiem"+bnBaoHiem);
	//alert(22);
	
	if (yeuCauObject.checked == true){
	    //alert(23);
	    //alert(obj);
		obj.value = donGiaYC;
	}
	//else if (mienPhiObject.checked == true){
	//  	//alert(24);
	//    obj.value = donGiaMP; 
	//}
	else if (bnBaoHiem != null && bnBaoHiem.value != null && bnBaoHiem.value != "" ){
	    //alert(25);
	    // if (ngoaiDanhMucObject.checked == true){
	    // 	 obj.value = donGia;
	    // }else{
	    obj.value = donGiaBH;
	    // }	
	}else{
	    // alert(26);
		obj.value = donGia;	
	}
	
	document.getElementById(prefix_component + "donGiaHid").value = obj.value;
	 phuc.lc end */
  }catch(e){
    alert ("error at setPriceForKT()");
  }
}

function blockInphieu() {

	/*
	var matiepdon = document.getElementById(prefix_component + "__matiepdon").value;
	var mabankham = document.getElementById(prefix_component + "__mabankham").value;
	if (matiepdon == '' || mabankham == '') {
		document.getElementById("__divIn").style.display = "none";
	} else {
		document.getElementById("__divIn").style.display = "block";
	}
	*/
	
	
	var resultHidden = document.getElementById(prefix_component + "resultHidden").value;
	//alert(resultHidden);
	
	var maPhieu =  document.getElementById(prefix_component + "__maphieu").value;
	
	if (resultHidden == "success" && maPhieu != null && maPhieu !=""){
	  //alert(resultHidden);
 	  document.getElementById("__divIn").style.display = "block";
	}else{
	  document.getElementById("__divIn").style.display = "none";
	}
}

function setOnload(){
  //return;
	//blockInphieu();
	 try{
	
	  myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	  //alert(1);
	  myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	  //alert(2);
	  myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	  //alert(3);	
	  //
	  
	  //myOnblurTextboxJSF(prefix_component + 'DT_DM_DOI_TUONG_MA',prefix_component + 'DT_DM_DOI_TUONG');
	  
	   //alert(4);
	  //myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
	  
	  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	  
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
	    
		var chuyenTuManHinhThuocCLS = document.getElementById(prefix_component + "_hidFocusDochuyenTuManHinhThuocCLS").value;
		if (chuyenTuManHinhThuocCLS == "true"){
			document.getElementById(prefix_component + "__loai").focus();
		}else{
			document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		}
		
		
		
		
		
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}
function reloadValueForCombobox(){
	myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_CLS_BANG_GIA');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//document.getElementById(prefix_component + "__loai").focus();
	/*
	var mpObj = document.getElementById(prefix_component + "mp");   		
   		if(mpObj != null){
   		    var mpValue = mpObj.value;
   		    if (mpValue == "true"){
   		       document.getElementById(prefix_component + "mp").value = "X";
   		    }else{
   		    	document.getElementById(prefix_component + "mp").value = "";
   		    }
   	}
   	*/	
   	
   		if (document.getElementById(prefix_component + "__ktc_hid").value == "true"){		   
			document.getElementById(prefix_component + "__ktc").checked = true;
		}else{
				document.getElementById(prefix_component + "__ktc").checked = false;
		}	
		
		//alert(document.getElementById(prefix_component + "__ndm_hid").value);
		
		if (document.getElementById(prefix_component + "__ndm_hid").value == "true"){		   
				document.getElementById(prefix_component + "__ndm").checked = true;
				//alert(document.getElementById(prefix_component + "__ndm").checked);
		}else{
				document.getElementById(prefix_component + "__ndm").checked = false;
		}
		
		if (document.getElementById(prefix_component + "__mien_hid").value == "true"){		   
				document.getElementById(prefix_component + "__mien").checked = true;
		}else{
				document.getElementById(prefix_component + "__mien").checked = false;
		}
}

function getCLSChooseValue() {		
	
	var temp = document.getElementsByName('checkboxclschoose');	
	
	var listCheck = "";
	for(var i =0; i< temp.length; i++){
		if(temp[i].checked == 1){			
			if(listCheck == ""){
				listCheck += temp[i].value;				
			}else{
				listCheck += "," + temp[i].value;
			}
		}		
	}	
	document.getElementById(prefix_component + 'listCLSChecked').value = "";
	document.getElementById(prefix_component + 'listCLSChecked').value += listCheck;
	
}

function displayCDHA() {			
	if(document.getElementById(prefix_component + "DM_KHOA1_MA").value == "CDH"){
		document.getElementById("khoaCDHA").style.display = "block";
	}else{
		document.getElementById("khoaCDHA").style.display = "none";
	}
}




