   
function init() {
	
    if (window.google && google.gears) {
    	
        try {
            
        	LoadCatalogFromServer_each(DtDmClsBangGia,"GetDmKyThuatAction");
            //setAttrForCombobox(prefix_component + 'DM_DOI_TUONG_MA','DM_DOI_TUONG_span', 'DM_DOI_TUONG',"getDmDoiTuong()","","","");
            setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
           
            //setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN',"getDtDmNhanVien_Kham()","","","");
            
            //alert(1);
            
            //setAttrForComboboxJSF(prefix_component + 'DM_KHOA_MA','DM_KHOA_span',prefix_component+ 'DM_KHOA','10');
            setAttrForCombobox(prefix_component + 'DT_DM_CLS_BANG_GIA_MA','DT_DM_CLS_BANG_GIA_span','DT_DM_CLS_BANG_GIA',"getDtDmClsBangGia_SearchExt(\""+ "DT_DM_CLS_BANG_GIA\" ,\""+prefix_component + "__loai\",document.getElementById(\""+prefix_component + "DM_KHOA_MA\").value)","","setKTC(\""+ prefix_component + "DT_DM_CLS_BANG_GIA_MA\");setInforForKhoaCLS();setPriceForKT();","");
            
            //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_KY_THUAT_MA','DT_DM_KY_THUAT_span', 'DT_DM_KY_THUAT','10', DtDmKyThuat);
            //alert(2);
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getKhoaHavingCLS();","","displayCDHA();","");
            //alert(3);
            
            
            // setAttrForComboboxJSFForPhieuDuTru('__listtonkho_span', '__listtonkho','10');
            
            timer.setTimeout(function(){setOnload();},300);   
                     							  
        	//alert(1);
            document.getElementById(prefix_component + "isShowListCLSBKTruoc").value = "no";
            showListCLSBKTruoc();
        
        } catch (e) {
           alert("error at init");
        }
    }
    
}

function showListCLSBKTruoc(){
	var isShowListCLSBKTruoc = document.getElementById(prefix_component + "isShowListCLSBKTruoc").value;
	//alert(fromMenu);
	if (isShowListCLSBKTruoc == "yes"){
		document.getElementById("divListCLSBKTruoc").style.display = "block";
		document.getElementById(prefix_component + "isShowListCLSBKTruoc").value = "no";
	}else{
		document.getElementById("divListCLSBKTruoc").style.display = "none";
		document.getElementById(prefix_component + "isShowListCLSBKTruoc").value = "yes";
	}
}

function checkDonGia(checkbox) {

	if (!checkbox.checked) {
		// Neu bo chon yeu cau, thi cung bo chon dich vu
		document.getElementById(prefix_component + "__dichVu").checked = false;
	}
	setOtherValueForGetFromServer();
}

function setCheckForDichVu(){
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
	//checkDonGia(document.getElementById(prefix_component + "__yc"));
	setOtherValueForGetFromServer();
}

function onChangeForYC(){
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
	var soLuongValue = document.getElementById(prefix_component + "__soluong").value;
	if (soLuongValue == "" ||soLuongValue == null || parseFloat(soLuongValue) <= 0){
		document.getElementById(prefix_component + "__soluong").value = 1;
	}
	document.getElementById(prefix_component + "__soluong").select();	
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
function setPriceForKT(){
  	
  try{	
    //alert(1);
	var maKTObj = document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA_pk");
	var soluongObj = document.getElementById(prefix_component + "__soluong");
	//alert(1111);
	if (parseInt(soluongObj.value,10) <= 0) {
		soluongObj.value = 1;
	}
	try{
	    //alert("maKTObj.value:"+maKTObj.value);
	    //alert("soluongObj.value:"+soluongObj.value);
	    //alert("maKTObj.value"+maKTObj.value);
	     
		if (maKTObj.value == "" || soluongObj.value == ""){
		    
    	    //alert(2);
    		document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA").focus;
    		return;	
		}
	}catch(e){
	        //alert(3);
	    	document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA").focus;
	    	return;	    
	}
	//alert(maKTObj.value);
	
	getPriceKyThuatFromServer('GetPriceDtDmKyThuatAction',maKTObj.value);
	
	
	
	
	
  }catch(e){
    alert("error at setPriceForKT()");
  }	

	
}

function setOtherValueForGetFromServer (){

	var maKTObj = document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA_pk");

    var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	
	var obj = document.getElementById(prefix_component + "__dongia");
	
	var yeuCauObject = document.getElementById(prefix_component + "__yc");
	var mienPhiObject = document.getElementById(prefix_component + "__mien");
	var ktcObject = document.getElementById(prefix_component + "__ktc");
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
	/*
	var minktc = 0;
	var maxktc = 0;
	var tylebhyt =0;
	var tylebhyt2 =0;
	var tylemin=0; // ty le min ktc
	var tylemax=0; // ty le max ktc
	var tyleKTC = 0;
	var gioiHanTyLe = 0;
	
	var khoiBHYT = document.getElementById(prefix_component + "hid_khoiBHYT").value;
	if (khoiBHYT != null && khoiBHYT != ""){
		var listKhoiBHYT = DtDmKhoiBhyt.filter("Ma = ?", khoiBHYT).toArray();
		if (listKhoiBHYT != null) {
			for (var i = 0; i < listKhoiBHYT.length; i++) {
				 minktc = listKhoiBHYT[i].DTDMKHOIBHYT_MINKTC;	
				 maxktc = listKhoiBHYT[i].DTDMKHOIBHYT_MAXKTC;	
				 tylebhyt = listKhoiBHYT[i].DTDMKHOIBHYT_TYLEBHYT1;	
				 tylebhyt2 = listKhoiBHYT[i].DTDMKHOIBHYT_TYLEBHYT2;	
				 tylemin = listKhoiBHYT[i].DTDMKHOIBHYT_TLMINKTC;	
				 tylemax = listKhoiBHYT[i].DTDMKHOIBHYT_TLMAXKTC;	
				 tyleKTC = listKhoiBHYT[i].DTDMKHOIBHYT_TYLEKTC;	
				 gioiHanTyLe = listKhoiBHYT[i].DTDMKHOIBHYT_GHTYLE;	
				 break;			
			}		
		}
	}	
	
	
	if (bnBaoHiem.value == "BH"){
	    if (ktcObject.checked == true){ // la ky thuat cao
	    	//tinh theo cong thuc ky thuat cao
	    	var tongchiphi = donGia; // Nguyen: tong chi phi
	    	if (parseFloat(tongchiphi) < parseFloat(minktc) ){
	    		//%bhyt thanh toan la tylebhyt2
	    		obj.value = parseFloat(tongchiphi) - parseFloat(tylemin)*parseFloat(tongchiphi)/100 ;
	    	}else if (parseFloat(tongchiphi) >= parseFloat(minktc) && parseFloat(tongchiphi) <= parseFloat(maxktc) ){
	    		obj.value = parseFloat(tongchiphi) - parseFloat(tyleKTC)*parseFloat(tongchiphi)/100 ;
	    	}else if (parseFloat(tongchiphi) >= parseFloat(maxktc)){
	    		obj.value = parseFloat(tongchiphi) - parseFloat(maxktc)*parseFloat(tyleKTC)/100 - (parseFloat(maxktc) -  parseFloat(tongchiphi))*parseFloat(tylemax)/100;
	    	}
	    }else{
	    	if (yeuCauObject.checked == true){ // co yeu cau hay ko
				obj.value = parseFloat(donGiaYC) - parseFloat(donGia); // phan benh nhan tra
				//BN tra tiep (sau khi BHYT thanh toan 1 phan)
				var tongchiphi = donGiaBH;
				
				if (parseFloat(tongchiphi) < parseFloat(gioiHanTyLe)){
					obj.value = parseFloat(obj.value) + parseFloat(tongchiphi) - parseFloat(tylebhyt2)* parseFloat(tongchiphi)/100;
				}else {
					obj.value = parseFloat(obj.value) + parseFloat(tongchiphi) - parseFloat(tylebhyt)* parseFloat(tongchiphi)/100;
				}
				
			}else{
				var tongchiphi = donGiaBH;
				
				if (parseFloat(tongchiphi) < parseFloat(gioiHanTyLe)){
					obj.value =  parseFloat(tongchiphi) - parseFloat(tylebhyt2)* parseFloat(tongchiphi)/100;
				}else {
					obj.value =  parseFloat(tongchiphi) - parseFloat(tylebhyt)* parseFloat(tongchiphi)/100;
				}
			}		    
	    }
	    	
	}else{ // ko phai doi tuong BH
		if (yeuCauObject.checked == true){
			obj.value = donGiaYC;
		}else{
			obj.value = donGia;	
		}	
	}
	
	// neu ko vuot qua moc 1 thi BN phai thanh toan ktc
	//
	var vuotQuaMoc1 = document.getElementById(prefix_component + "hid_vuotMoc1").value;
	if (vuotQuaMoc1 == "true"){
		//do nothing
	}else{
		if (yeuCauObject.checked == true){
			obj.value = donGiaYC;
		}else{
			obj.value = donGia;	
		}		
	}
	
	if (mienPhiObject.checked == true){
		obj.value = donGiaMP;	
	}
	*/
	/* phuc.lc 01-10-2010
	* Cach tinh don gia khi thuc hien CLS nhu sau (don gia nay se duoc luu vao truong don gia cua bang ClsKham)
	* + Doi voi doi tuong BH
	*		- Neu thuc hien CLS mien, thi don gia = don gia mien cua CLS 
	*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
	*		- Neu thuc hien CLS (trong danh muc), thi don gia = don gia bao hiem cua CLS
	*		- Neu thuc hien CLS (ngoai danh muc), thi don gia = don gia thuong cua CLS
	* + Doi voi doi tuong MP		 
	*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
	*		- Neu thuc hien CLS (khong yeu cau), thi don gia = don gia mien cua CLS
	* + Doi voi doi tuong TP
	*		- Neu thuc hien CLS thuong, thi don gia = don gia thuong cua CLS 
	*		- Neu yeu cau thuc hien CLS(bao gom CLS mien, ngoai DM, va CLS thuong), thi don gia = don gia yeu cau cua CLS
	*		- Neu thuc hien CLS mien phi, thi don gia = don gia mien cua CLS	
	*/
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
			} else if (ngoaiDanhMucObject.checked == true
					|| checkHetHanBH(ngayBatDauBh,ngayHetHanBh) == true) {
				// CLS ngoai danh muc ==> lay gia thuong
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
		}
		
	}
	
	
	
	document.getElementById(prefix_component + "donGiaHid").value = obj.value;
	numberFormatBlur(document.getElementById(prefix_component + "donGiaHid"));
	//alert(obj.value);
	
	//alert(maKTObj.value);
	var tenCLS = getTenFromMaSo( 'DT_DM_CLS_BANG_GIA' , maKTObj.value );
	document.getElementById(prefix_component + "tenDmKyThuatHid").value = tenCLS;
	//alert(tenCLS);

}
function setOnload_tiepdon(){
	
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
 
  
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
	
	var ngaysinh = document.getElementById(prefix_component + "__ngaytg").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaytg").value = namsinh;
	}
	
	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	
	
}
function setOnload(){

  
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
 
  
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
	
	
	var ngaysinh = document.getElementById(prefix_component + "__ngaytg").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaytg").value = namsinh;
	}
	
	var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	//alert(fromMenu);
	if (fromMenu == "yes"){
		document.getElementById("divNhapMoi").style.display = "block";
		document.getElementById("divQuaylai").style.display = "none";
		 
		setDefaultValueForBanKham();
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").focus();
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").select();
	}else{
	//alert(2);
		document.getElementById("divNhapMoi").style.display = "none";
		document.getElementById("divQuaylai").style.display = "block";
		
		document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		 
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").disabled = true;
		if (document.getElementById( "DT_DM_BAN_KHAM").disabled == false){
			changeDisabledAttr("DT_DM_BAN_KHAM");  
   		} 
		document.getElementById(prefix_component + "__matiepdon").disabled = true;
		//document.getElementById(prefix_component + "__inphieu").disabled = true;//chua lam chuc nang in phieu
	}
	
	// 20110322 bao.ttc: Kiem tra xem da Thanh toan hay chua de disable nut Ghi nhan
	var daThtoan = document.getElementById(prefix_component + "daThanhtoan").value;
	if (daThtoan == "true"){
		document.getElementById(prefix_component + "__ghinhan").disabled = true;
		document.getElementById(prefix_component + "__getCLSChooseValue").disabled = true;
		document.getElementById(prefix_component + "__themchinhsau").disabled = true;
	}
	// END -- 20110322 bao.ttc: Kiem tra xem da Thanh toan hay chua de disable nut Ghi nhan
		
}

function setInforForKhoaCLS(){
  
  /*
	if (document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA_pk").value != null &&
				document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA_pk").value  != ""){
		var arrayKhoa = getDmKhoaByCLS(prefix_component + "DT_DM_CLS_BANG_GIA_MA_pk");
		
		if (arrayKhoa != null && arrayKhoa.length == 1){
			var catalog = arrayKhoa[0];
			document.getElementById(prefix_component + "DM_KHOA_MA").value = catalog.Ma;
			myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		}
		//document.getElementById(prefix_component + "__dichVu").focus();
	
	}
  */
	
}

			
function reloadValueForCombobox(){
	myOnblurTextbox(prefix_component + 'DT_DM_CLS_BANG_GIA_MA', 'DT_DM_CLS_BANG_GIA');
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA', 'DT_DM_NHAN_VIEN');
	//document.getElementById(prefix_component + "donGiaHid").value = "";
	
	//document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_MA").focus();
	
	
	//alert(document.getElementById(prefix_component + "__ktc_hid").value == true);
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
	//alert(document.getElementById(prefix_component + "__dongia").value);
	//alert(document.getElementById(prefix_component + "donGiaHid").value);
	
	var num =  parseFloat( document.getElementById(prefix_component + "__dongia").value) ;
		
	document.getElementById(prefix_component + "donGiaHid").value = num;
	
	numberFormatBlur(document.getElementById(prefix_component + "donGiaHid"));
}

function setDefaultValueForBanKham(){
	var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_2_Canlamsangphauthuat_bankham");
	if (bankhamClientDefault) {
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = bankhamClientDefault.Ten;
		myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
	}
}
// luu y : chi goi khi nhan tu menu
function luuTruGiaTriClientDefault(){
	var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	if (fromMenu == "yes"){
		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_2_Canlamsangphauthuat_bankham");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121201;
				chValues[1] = "B121_2_Canlamsangphauthuat_bankham";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_2_Canlamsangphauthuat_bankham", chNames,chValues );
				
			}    	
		}
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
	if(document.getElementById(prefix_component + "DM_KHOA_MA").value == "CDH"){
		document.getElementById("khoaCDHA").style.display = "block";
	}else{
		document.getElementById("khoaCDHA").style.display = "none";
	}
}



