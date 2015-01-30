
function init() {
	
    if (window.google && google.gears) {
    	
       // try {
             	//setAttrForCombobox(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH',"getDmTinh()","","","");
                
             	//setAttrForCombobox(prefix_component + 'HUYEN_MA','DM_HUYEN_span','DM_HUYEN',"getDmHuyen(\"" + prefix_component + "TINH_MA" + "\")","","","");
                //setAttrForCombobox(prefix_component + 'XA_MA','DM_XA_span','DM_XA',"getDmXa(\"" + prefix_component + "HUYEN_MA" + "\")","","","");
                
                setAttrForCombobox(prefix_component + 'DM_NGHE_NGHIEP_MA','DM_NGHE_NGHIEP_span', 'DM_NGHE_NGHIEP',"getDmNgheNghiep()","","","");
                //setAttrForCombobox(prefix_component + 'DANTOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC',"getDmDanToc()","","","");
             
                //setAttrForCombobox(prefix_component + 'DM_DOI_TUONG_MA','DM_DOI_TUONG_span', 'DM_DOI_TUONG',"getDmDoiTuong()","","","");
               
             	toSoNgay();
                setAttrForCombobox(prefix_component + 'DM_BENH_VIEN_MA_1','DM_BENH_VIEN_span1', 'DM_BENH_VIEN__1',"getDmBenhVien()","","","");
                setAttrForCombobox(prefix_component + 'DM_BENH_VIEN_MA_2','DM_BENH_VIEN_span2', 'DM_BENH_VIEN__2',"getDmBenhVien_search_chuyen_di()","","","");
                setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmBacSi()","","","");
                setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2','DT_DM_NHAN_VIEN_span2', 'DT_DM_NHAN_VIEN__2',"getDtDmBacSi()","","","");
                setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");
                setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_2','DM_BENH_ICD_span2', 'DM_BENH_ICD__2',"getDmBenhIcd()","","","");
                //setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_3','DM_BENH_ICD_span3', 'DM_BENH_ICD__3',"getDmBenhIcd()","","","");
                
                setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_3','DM_BENH_ICD_span3', 'DM_BENH_ICD__3',"getDmBenhIcd()","","","");
				
				setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_4','DM_BENH_ICD_span4', 'DM_BENH_ICD__4',"getDmBenhIcd()","","","");
	
                setAttrForCombobox(prefix_component + 'DT_DM_KET_QUA_MA','DT_DM_KET_QUA_span', 'DM_KET_QUA_DIEU_TRI',"getDmKetQuaDieuTri()","","checkForKetQua()","");
              	setAttrForCombobox(prefix_component + 'DM_LY_DO_CV_MA','DM_LY_DO_CV_span', 'DT_DM_LY_DO_CV',"getDtDmLyDoCv()","","","");
                
                setAttrForCombobox(prefix_component + 'DM_PHUONG_TIEN_MA','DM_PHUONG_TIEN_span', 'DM_PHUONG_THUC_GAY_TAI_NAN',"getDmPhuongThucGayTaiNanByTaiNanMa(\"" + prefix_component + "DM_PL_TAI_NAN_MA_pk" + "\")","","","");
                setAttrForCombobox(prefix_component + 'DM_PL_TAI_NAN_MA','DM_PL_TAI_NAN_span', 'DM_TAI_NAN',"getDmTaiNan()","onChangePhuongThucGayTaiNan()","","");
               	//setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA_2','DT_DM_BAN_KHAM_span2', 'DT_DM_BAN_KHAM__2',"getDtDmBanKham()","","","");
                setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
                setAttrForCombobox(prefix_component + 'DM_DIEU_TRI_MA','DM_DIEU_TRI_span', 'DM_DIEU_TRI',"getDmDieuTri()","","checkForHuongXuLy(true)","");
                setAttrForCombobox(prefix_component + 'DM_DIA_DIEM_MA','DM_DIA_DIEM_span', 'DM_DIA_DIEM',"getDmDiaDiem()","","","");
                
                var maBanKham = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value;
                
                if (maBanKham == 'CCL'){
                	setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA_1','DT_DM_BAN_KHAM_span1', 'DT_DM_BAN_KHAM__1',"getDtDmBanKhamCCL()","","","");
             	} else{
             		setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA_1','DT_DM_BAN_KHAM_span1', 'DT_DM_BAN_KHAM__1',"getDtDmBanKhamKoCCL()","","","");
             	}
                setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA_2','DT_DM_BAN_KHAM_span2', 'DT_DM_BAN_KHAM__2',"getDtDmBanKham()","","","");

                setAttrForComboboxForNoDB(prefix_component + '__benhnhanduoctiepdon_ma','__benhnhanduoctiepdon_span', '__benhnhanduoctiepdon',""," mySetValueForBenhNhanTiepDon(\'__benhnhanduoctiepdon\',\'__matiepdon\'); document.getElementById('" + prefix_component + "__matiepdon').focus(); ","statusEnterAnhTabForChon = 0; ","statusEnterAnhTabForChon = 1");
                
			    //setAttrForComboboxBenhNhanTiepDon('__benhnhanduoctiepdon_span', '__benhnhanduoctiepdon','10','__matiepdon');
               
               	//---Set value on load---
               	 timer.setTimeout(function(){tempOnload();},1000); 
               	 
              //document.getElementById(prefix_component + "__matiepdon").focus();
        //} catch (e) {        
         //		alert("eror at init" + e.description);
        //}
    }    
}
function setDonViTuoi(){
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

}
function getMaTiepDonFromGoiBN(){	
	if(document.getElementById(prefix_component + "__matiepdonforcall").value.trim() != ""){				
		goiBNJS();
	}else
	{	
		alert("Không có mã tiếp đón");
	/*
	var xmlHttp = createXmlHttpRequest();
    var xml;
    var maBanKham = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value; //
    if (maBanKham != null && maBanKham != ''){
   		xml = maBanKham;
    } else {
    	alert("Bàn khám bắt buộc nhập.");
    	return;
    }
    //alert("xml: " + xml);
    var url = myContextPath + "/actionServlet";
    var params = "urlAction=GetMaTDFromGoiBNAction&xmlData=" + xml;
    xmlHttp.open("POST", url, true);

    xmlHttp.onreadystatechange = function(){
    	handleStateChangeForMaTiepDonTuGoiBN(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlHttp.send(params); 
    */
	}
}
function handleStateChangeForMaTiepDonTuGoiBN(xmlHttp){
     if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
        	var jsonExpression = "(" + xmlHttp.responseText + ")";
        	
        	var data = eval(jsonExpression);        	
        	var myResponse = data.result;        	 
   			
   			if(myResponse != "null" && myResponse != null){  
   			 
   			 		document.getElementById(prefix_component + "__matiepdon").value = myResponse ;
   			 	
   			   document.getElementById(prefix_component + "aftergoibnBtn").onclick();
               xoaSoThuTuFromGoiBN();
             }else{
             	alert("Không có bệnh nhân.");
             }
             
        }        
    }
}

function handleStateChangeForXoaSTTTuGoiBN(xmlHttp){
     if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
        	             goiBNJS();
        }        
    }
}
function xoaSoThuTuFromGoiBN(){
	var xmlHttp = createXmlHttpRequest();
    var xml;
    var maTiepDon = document.getElementById(prefix_component + "__matiepdon").value; //
    if (maTiepDon != null && maTiepDon != ''){
   		xml = maTiepDon;
    } else {
    	alert("Mã tiếp đón bắt buộc nhập.");
    	return;
    }
    //alert("xml: " + xml);
    var url = myContextPath + "/actionServlet";
    var params = "urlAction=XoaSoThuTuFromGoiBNAction&xmlData=" + xml;
    xmlHttp.open("POST", url, true);

    xmlHttp.onreadystatechange = function(){
    	handleStateChangeForXoaSTTTuGoiBN(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlHttp.send(params); 
}
function goiBNJS(){		
	if (thietbiquanbao == 'YES'){
	
		//lay gia tri quang bao
		
		//var soquangbaoClientDefault = DtDmClientDefault.getByFieldValue("Ma", "GiaTriSoQuangBao");
		//var giatrisoqb = "0";
		//if (soquangbaoClientDefault) {
			//giatrisoqb = soquangbaoClientDefault.Ten;					
		//}
		
		/////////////////		
		document.xyz.pingpong(getListOfValue(document.getElementById(prefix_component + '__sothutu').value )+",0,0,0");
		//alert(getListOfValue(document.getElementById(prefix_component + '__sothutu').value )+",0,0,0");
		timer.setTimeout(function(){goiBNJS_001();},4000);
		
		
//		var server = DtDmClientDefault.getByFieldValue("Ma", "GiaTriCongQuangBao");
//		var serverIp = "localhost";
//		if (server) {
//			serverIp = server.Ten;					
//		}
//		var port = DtDmClientDefault.getByFieldValue("Ma", "GiaTriSoQuangBao");
//		var soPort = "19999";
//		if (server) {
//			soPort = port.Ten;					
//		}
//		var xmlHttpReq = false;
//	    var self = this;
//	    // Mozilla/Safari
//	    if (window.XMLHttpRequest) {
//	        self.xmlHttpReq = new XMLHttpRequest();
//	    }
//	    // IE
//	    else if (window.ActiveXObject) {
//	        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
//	    }
//		var url = "http://"+serverIp+":"+soPort+"/pingpong?stt="+ getListOfValue(document.getElementById(prefix_component + '__sothutu').value ) +",0,0,0&";
//		self.xmlHttpReq.open('POST', url, true);
//		
//	    self.xmlHttpReq.onreadystatechange = function(){
//			if(self.xmlHttpReq.readyState == 4){				
//				goiBNJS_001();
//			}
//	    };		
//	    self.xmlHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");		
//	    self.xmlHttpReq.send();		
		
	}
}
function goiBNJS_001(){
	var ten = document.getElementById(prefix_component + '__hotenforcall').value;
	var bk = document.getElementById( 'DT_DM_BAN_KHAM__1').value;
	
			//lay gia tri quang bao
		
		
		//var soquangbaoClientDefault = DtDmClientDefault.getByFieldValue("Ma", "GiaTriSoQuangBao");
		//var giatrisoqb = "0";
		//if (soquangbaoClientDefault) {
			//giatrisoqb = soquangbaoClientDefault.Ten;					
		//}
//		var server = DtDmClientDefault.getByFieldValue("Ma", "GiaTriCongQuangBao");
//		var serverIp = "localhost";
//		if (server) {
//			serverIp = server.Ten;					
//		}
//		var port = DtDmClientDefault.getByFieldValue("Ma", "GiaTriSoQuangBao");
//		var soPort = "19999";
//		if (server) {
//			soPort = port.Ten;					
//		}
//		var xmlHttpReq = false;
//	    var self = this;
//	    // Mozilla/Safari
//	    if (window.XMLHttpRequest) {
//	        self.xmlHttpReq = new XMLHttpRequest();
//	    }
//	    // IE
//	    else if (window.ActiveXObject) {
//	        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
//	    }
//		var url = "http://"+serverIp+":"+soPort+"/callBenhNhan?stt="+ getListOfValue(ten +" " + bk)+"&";
//		self.xmlHttpReq.open('POST', url, true);
//		
//	    self.xmlHttpReq.onreadystatechange = function(){
//			if(self.xmlHttpReq.readyState == 4){				
//				
//			}
//	    };		
//	    self.xmlHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");		
//	    self.xmlHttpReq.send();
	
		/////////////////	
	//alert(getListOfValue(ten +" " + bk));
	document.xyz.outputToLed(getListOfValue(ten +" " + bk));

}
function onChangePhuongThucGayTaiNan(){
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA_pk").value = "";
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA").value = "";
	document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value = "";
}

function checkValidateChoNhomMau(myThis){ // A B AB O
			
			myThis.value=myThis.value.toUpperCase();
			var nhomMau = myThis.value;
			if (nhomMau != '' ){
				if (nhomMau != 'a' && nhomMau != 'A' && 
				  nhomMau != 'b' && nhomMau != 'B' && 
				  nhomMau != 'o' && nhomMau != 'O' && 
				  nhomMau != 'ab' && nhomMau != 'AB' &&  nhomMau != 'aB' && nhomMau != 'Ab' 
				     ){
					alert("Nhóm máu phải là A, B, AB , O");
					myThis.select();
					myThis.focus();
					
				}
		}
}
function tempOnload(){
  	
	onCompleteGetInfor();
	
  	var maTiepDon = document.getElementById(prefix_component + "__matiepdon").value;
  	
    if (maTiepDon != null && maTiepDon != ''){
    	//document.getElementById(prefix_component + "__matiepdon").focus();
    	//document.getElementById(prefix_component + "__matiepdon").onblur();
    	saveMaOnload();
    	checkForHuongXuLy(false);
    	lockBanKham();
    } else {
    	disabledInit();
    	unlockBanKham();
    	clear();
    }
    
}

// ham nay duoc goi khi nhan nut "nhap lai"

function disabledInit(){
    //alert(1);
    if (document.getElementById( "DM_BENH_ICD__3").disabled == false){
        //alert(1);
        changeDisabledAttr("DM_BENH_ICD__3");  
   	}
   	//alert(1);
	document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").disabled=true; 
	
	//alert(1);
	if (document.getElementById( "DM_KHOA").disabled == false){
        changeDisabledAttr("DM_KHOA");  
   	}
	document.getElementById(prefix_component + "DM_KHOA_MA").disabled=true; 
	//alert(1);
	if (document.getElementById( "DT_DM_BAN_KHAM__2").disabled == false){
        changeDisabledAttr("DT_DM_BAN_KHAM__2");  
   	}
	document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").disabled=true; 
	
	if (document.getElementById( "DM_BENH_VIEN__2").disabled == false){
        changeDisabledAttr("DM_BENH_VIEN__2");  
   	}
	document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").disabled=true; 
	
	
	//alert(1);
	if (document.getElementById( "DT_DM_NHAN_VIEN__2").disabled == false){
        changeDisabledAttr("DT_DM_NHAN_VIEN__2");  
   	}
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").disabled=true; 
							  
    if (document.getElementById( "DT_DM_LY_DO_CV").disabled == false){
        changeDisabledAttr("DT_DM_LY_DO_CV");  
   	}
	document.getElementById(prefix_component + "DM_LY_DO_CV_MA").disabled=true; 
	        
    //checkForKetQuaGetInfor();
}

function checkForKetQuaGetInfor(){
	var ketQuaMa = document.getElementById(prefix_component + "DT_DM_KET_QUA_MA").value;
	//alert(2222);
	if (ketQuaMa == "5"){
	 //alert(document.getElementById(prefix_component + "DM_BENH_ICD_MA_3"));
		 document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").disabled=false;
		
        document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").className="myinput";
        // alert(21);
        if (document.getElementById( "DM_BENH_ICD__3").disabled == true){
        	changeDisabledAttr("DM_BENH_ICD__3");  
      	}
      	 
      	
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value="C";
      	 
      	 myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_MA', 'DM_DIEU_TRI');
      	 
      	
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").disabled = true;
      	 // alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	 if (document.getElementById( "DM_DIEU_TRI").disabled == false){
      	 // alert(11);
        	changeDisabledAttr("DM_DIEU_TRI");  
        	// alert(22);
      	}
       	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	
      	 
      	   
	}else{
	 //alert(31);
		if (document.getElementById( "DM_BENH_ICD__3").disabled == false){
        	changeDisabledAttr("DM_BENH_ICD__3");  
      	}
      	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").value="";
      	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_3', 'DM_BENH_ICD__3');
      	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").disabled=true; 
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").className="myreadonly"; 
		 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
		
		
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").disabled = false;
      	
      	 if (document.getElementById( "DM_DIEU_TRI").disabled == true){
      	  //alert(11);
        	changeDisabledAttr("DM_DIEU_TRI");  
        	// alert(22);
      	 }
      	
		 
	}	
}
function checkForKetQua(){
	
	var ketQuaMa = document.getElementById(prefix_component + "DT_DM_KET_QUA_MA").value;
	//alert(2222);
	if (ketQuaMa == "5"){
	 //alert(document.getElementById(prefix_component + "DM_BENH_ICD_MA_3"));
		 document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").disabled=false;
		
        document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").className="myinput";
        // alert(21);
        if (document.getElementById( "DM_BENH_ICD__3").disabled == true){
        	changeDisabledAttr("DM_BENH_ICD__3");  
      	}
      	 document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").focus();
      	
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value="C";
      	 
      	 myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_MA', 'DM_DIEU_TRI');
      	 
      	
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").disabled = true;
      	 // alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	 if (document.getElementById( "DM_DIEU_TRI").disabled == false){
      	 // alert(11);
        	changeDisabledAttr("DM_DIEU_TRI");  
        	// alert(22);
      	}
       	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	
      	 
      	   
	}else{
	 //alert(31);
		if (document.getElementById( "DM_BENH_ICD__3").disabled == false){
        	changeDisabledAttr("DM_BENH_ICD__3");  
      	}
      	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
      	document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").value="";
      	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_3', 'DM_BENH_ICD__3');
      	 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").disabled=true; 
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_3").className="myreadonly"; 
		 //alert(document.getElementById(prefix_component + "DM_DIEU_TRI_MA"));
		
		
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").disabled = false;
      	
      	 if (document.getElementById( "DM_DIEU_TRI").disabled == true){
      	  //alert(11);
        	changeDisabledAttr("DM_DIEU_TRI");  
        	// alert(22);
      	 }
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_MA").focus();
		 
	}	
}
var maBanKhamTmp = "";
var maKhoaTmp = "";
// 20110407 bao.ttc: save ma khoa noi tru & ma ban kham chuyen khi load Thamkham
//          de tranh truong hop user chon di chon lai huong xu ly
//          (se xoa ma khi chon huong khac va get lai chuoi rong khi chon lai huong xu ly truoc)
function saveMaOnload(){
	maKhoaTmp = document.getElementById(prefix_component + "DM_KHOA_MA").value;
	maBanKhamTmp = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").value;
}

function checkForHuongXuLy(isComboBlur){
  //try{
	
	var huongXuLy = document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value;
	// 20110407 bao.ttc: save ma khoa cu khi load thamkham de tranh truong hop user chon di chon lai huong xu ly (se xoa ma khoa va get lai chuoi rong)
	//maKhoaTmp = document.getElementById(prefix_component + "DM_KHOA_MA").value;
	
	if (huongXuLy == "v" || huongXuLy == "V" || huongXuLy == "n" || huongXuLy == "N"){
		// phuc.lc begin add code
		
		if (document.getElementById(prefix_component + "__hasChKhoa").value == "true") {
			if (isComboBlur) {
				document.getElementById(prefix_component + "DM_KHOA_MA").value=maKhoaTmp;
				myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
			}
			if (document.getElementById( "DM_KHOA").disabled == false){
	        	changeDisabledAttr("DM_KHOA");  
	      	}      	
      	
			document.getElementById(prefix_component + "DM_KHOA_MA").disabled=true; 
			document.getElementById(prefix_component + "DM_KHOA_MA").className="myreadonly"; 
		// phuc.lc end
		} else {
			document.getElementById(prefix_component + "DM_KHOA_MA").disabled=false;
			document.getElementById(prefix_component + "DM_KHOA_MA").className="myinput";
	        if (document.getElementById( "DM_KHOA").disabled == true){
	        	changeDisabledAttr("DM_KHOA");  
	      	}
	        document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		}
        
        clearBanKham2();
        clearChuyenTuyenTren();
        
        document.getElementById(prefix_component + "__chuyensolieuvaonoitru").disabled=false;
        
	}else{
	    document.getElementById(prefix_component + "__chuyensolieuvaonoitru").disabled=true;
	    document.getElementById(prefix_component + "__chuyensolieuvaonoitru").checked=false;
	    // 20110407 bao.ttc: save ma ban kham chuyen cu khi load thamkham de tranh truong hop user chon di chon lai huong xu ly (se xoa ma khoa va get lai chuoi rong)
	    // maBanKhamTmp = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").value;
		clearKhoaMa();
		 if (document.getElementById(prefix_component + "__chuyensolieuvaonoitru").disabled == true){			    
			    	document.getElementById(prefix_component + "__khicanbaotincho").focus();	
			    }else{
			    	document.getElementById(prefix_component + "__chuyensolieuvaonoitru").focus();	
			    }
		if (huongXuLy == "b" || huongXuLy == "B"){ // chuyen ban kham
			// phuc.lc begin add code			
			if(document.getElementById(prefix_component + "__hasChBanKham").value == "true") {
				if (isComboBlur) {
					document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").value = maBanKhamTmp;
					myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA_2', 'DT_DM_BAN_KHAM__2');
				}
				if (document.getElementById( "DT_DM_BAN_KHAM__2").disabled == false){
		        	changeDisabledAttr("DT_DM_BAN_KHAM__2");  
		      	}					      	
				document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").disabled=true; 
				document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").className="myreadonly";				
				
			} else { //phuc.lc end
				
				document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").disabled=false;
		        document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").className="myinput";
		        //alert(1);
		        if (document.getElementById( "DT_DM_BAN_KHAM__2").disabled == true){
		        	changeDisabledAttr("DT_DM_BAN_KHAM__2");  
		      	} 
		        document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").focus();
			}
	      	clearChuyenTuyenTren();
	      	
	      	//alert(2);	      	
	      	//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").focus();	      	
	        //alert(3);  
	        
	        
	        
		}else{
			clearBanKham2();
			 if (document.getElementById(prefix_component + "__chuyensolieuvaonoitru").disabled == true){			    
			    	document.getElementById(prefix_component + "__khicanbaotincho").focus();	
			    }else{
			    	document.getElementById(prefix_component + "__chuyensolieuvaonoitru").focus();	
			    }
			
			if (huongXuLy == "p" || huongXuLy == "P"){ //chuyen tuyen tren
				///////////////chuyen vien
				document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").disabled=false;
		        document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").className="myinput";
		        //alert(1);
		        if (document.getElementById( "DM_BENH_VIEN__2").disabled == true){
		        	changeDisabledAttr("DM_BENH_VIEN__2");  
		      	}
		      	//alert(2);
		      	document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").focus();
		        //alert(3);  
		        ////////////////BS chuyen vien
		        
		        document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").disabled=false;
		        document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").className="myinput";
		        //alert(1);
		        if (document.getElementById( "DT_DM_NHAN_VIEN__2").disabled == true){
		        	changeDisabledAttr("DT_DM_NHAN_VIEN__2");  
		      	}
		        
		        ///////////////ly do chuyen vien
			
			    document.getElementById(prefix_component + "DM_LY_DO_CV_MA").disabled=false;
		        document.getElementById(prefix_component + "DM_LY_DO_CV_MA").className="myinput";
		        //alert(1);
		        if (document.getElementById( "DT_DM_LY_DO_CV").disabled == true){
		        	changeDisabledAttr("DT_DM_LY_DO_CV");  
		      	}
			
			}else{
			    clearChuyenTuyenTren();
			    if (document.getElementById(prefix_component + "__chuyensolieuvaonoitru").disabled == true){			    
			    	document.getElementById(prefix_component + "__khicanbaotincho").focus();	
			    }else{
			    	document.getElementById(prefix_component + "__chuyensolieuvaonoitru").focus();	
			    }
			    
				
			}
		}
		//
	}	
  //}catch(e){
  //  alert("error: " + e.description);
  //}
}
function clearChuyenTuyenTren(){
    //alert("clearChuyenTuyenTren");
	// chuyen tuyen tren
	if (document.getElementById( "DM_BENH_VIEN__2").disabled == false){
        	changeDisabledAttr("DM_BENH_VIEN__2");  
      	}
      	document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").value="";
      	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_2', 'DM_BENH_VIEN__2');
      	
		document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").disabled=true; 
		document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").className="myreadonly";
		
		//alert(document.getElementById(prefix_component + "DM_BENH_VIEN_MA_2").disabled)
		
	// bs chuyen
	
	    if (document.getElementById( "DT_DM_NHAN_VIEN__2").disabled == false){
        	changeDisabledAttr("DT_DM_NHAN_VIEN__2");  
      	}
      	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").value="";
      	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
      	
		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").disabled=true; 
		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").className="myreadonly";
	
	//ly do chuyen
       if (document.getElementById( "DT_DM_LY_DO_CV").disabled == false){
        	changeDisabledAttr("DT_DM_LY_DO_CV");  
      	}
      	document.getElementById(prefix_component + "DM_LY_DO_CV_MA").value="";
      	myOnblurTextbox(prefix_component + 'DM_LY_DO_CV_MA', 'DT_DM_LY_DO_CV');
      	
		document.getElementById(prefix_component + "DM_LY_DO_CV_MA").disabled=true; 
		document.getElementById(prefix_component + "DM_LY_DO_CV_MA").className="myreadonly";
}
function clearBanKham2(){
	   if (document.getElementById( "DT_DM_BAN_KHAM__2").disabled == false){
        	changeDisabledAttr("DT_DM_BAN_KHAM__2");  
      	}
      	document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").value="";
      	myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA_2', 'DT_DM_BAN_KHAM__2');
      	
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").disabled=true; 
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_2").className="myreadonly";

}
function clearKhoaMa(){
       if (document.getElementById( "DM_KHOA").disabled == false){
        	changeDisabledAttr("DM_KHOA");  
      	}
      	document.getElementById(prefix_component + "DM_KHOA_MA").value="";
      	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
      	
		document.getElementById(prefix_component + "DM_KHOA_MA").disabled=true; 
		document.getElementById(prefix_component + "DM_KHOA_MA").className="myreadonly"; 
		
}
//function onCompleteForNhapLai()
//{
//	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
//	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_2', 'DM_BENH_ICD__2');
//	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_3', 'DM_BENH_ICD__3');
//
//}

function onMyOnblurTextbox(){
	//myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	//myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	//myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	//myOnblurTextbox(prefix_component + 'DANTOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DM_NGHE_NGHIEP_MA', 'DM_NGHE_NGHIEP');
	
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_2', 'DM_BENH_ICD__2');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_3', 'DM_BENH_ICD__3');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_4', 'DM_BENH_ICD__4');
	
	
	myOnblurTextbox(prefix_component + 'DT_DM_KET_QUA_MA', 'DM_KET_QUA_DIEU_TRI');
	myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_MA', 'DM_DIEU_TRI');
	//alert(1)
	myOnblurTextbox(prefix_component + 'DM_LY_DO_CV_MA', 'DT_DM_LY_DO_CV');
	//alert(1)
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	//alert(1)
	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_1', 'DM_BENH_VIEN__1');
	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_2', 'DM_BENH_VIEN__2');
	//alert(1)
	//myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	//alert(1)
	myOnblurTextbox(prefix_component + 'DM_PL_TAI_NAN_MA', 'DM_TAI_NAN');
	myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA_1', 'DT_DM_BAN_KHAM__1');
	myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA_2', 'DT_DM_BAN_KHAM__2');
	myOnblurTextbox(prefix_component + 'DM_DIA_DIEM_MA', 'DM_DIA_DIEM');

}
function onCompleteGetInfor(){

	highlightOnFocus();
	toSoNgay();
	
	onMyOnblurTextbox();
	enableButtonTK();
	
	checkForKetQuaGetInfor();
	setDonViTuoi();
	
	setDefaultValueForBanKham();
	setDefaultValueForBacSi();
	
}

//---Reset form---
function myResetAll(){
    myReset(50);
    setValueOnLoad();
    //document.getElementById(prefix_component + "__matiepdon").focus(); 
}
function setValueOnLoad(){

   
    enableButtonTK();
	
    //alert(1);
	//document.getElementById(prefix_component + "__matiepdon").focus();
	
	//disabledInit();
	
	
}

function enableButtonTK(){
 	var hadThamKham = document.getElementById(prefix_component + "__HidHadThamKham").value;
 	var daThtoan = document.getElementById(prefix_component + "__daThtoan").value;
 	//alert(hadThamKham);
    if (hadThamKham == null || hadThamKham == "" || daThtoan == "true"){
    	
    	document.getElementById(prefix_component + "__thamkham").disabled = true;
		document.getElementById(prefix_component + "__clsthuthuat").disabled = true;
		document.getElementById(prefix_component + "__xutrithuoc").disabled = true;
		document.getElementById(prefix_component + "__ketoaquaybv").disabled = true;
		document.getElementById(prefix_component + "__ketoave").disabled = true;
		document.getElementById(prefix_component + "__inphieu").disabled = true;
		if (daThtoan == "true") {
			document.getElementById(prefix_component + "__thamkham").disabled = false;
			document.getElementById(prefix_component + "__inphieu").disabled = false; 
			document.getElementById(prefix_component + "__ghinhan").disabled = true;
			document.getElementById(prefix_component + "__clsthuthuat").disabled = false;
		}
    
    }else{
    	document.getElementById(prefix_component + "__thamkham").disabled = false;
		document.getElementById(prefix_component + "__clsthuthuat").disabled = false;
		document.getElementById(prefix_component + "__xutrithuoc").disabled = false;
		//document.getElementById(prefix_component + "__ketoaquaybv").disabled = false;
		document.getElementById(prefix_component + "__ketoave").disabled = false;
		document.getElementById(prefix_component + "__inphieu").disabled = false;        
		document.getElementById(prefix_component + "__ghinhan").disabled = false;
		
		var notBNbaohiem = document.getElementById(prefix_component + "__notBNbaohiem").value;
		if (notBNbaohiem == null || notBNbaohiem == "" || notBNbaohiem == "false"){
			
			// bao.ttc: la BN Bao Hiem, check xem bao hiem con thoi han hay khong de hien thi nut Ke toa Quay BV
			var notDisplayKTQBV = document.getElementById(prefix_component + "__notDisplayKTQBV").value;
			if (notDisplayKTQBV == null || notDisplayKTQBV == "" || notDisplayKTQBV == "false"){
				document.getElementById(prefix_component + "__ketoaquaybv").disabled = false;
			} else{
				document.getElementById(prefix_component + "__ketoaquaybv").disabled = true;
			}
			
		} else{
			document.getElementById(prefix_component + "__ketoaquaybv").disabled = true;
		}
		
    }
    
}

function set_tuoi(){
    //alert(document.getElementById(prefix_component + "__namsinh"));
	ngaysinh = document.getElementById(prefix_component + "__namsinh").value;
	//alert(ngaysinh);
	if(ngaysinh == ""){
		return;
	}	
	else {
		document.getElementById(prefix_component + "__tuoi").value = new Date().getFullYear() - parseInt( ngaysinh.substr(6,4)) ;
	}
}
function set_ngaysinh(){
	tuoi = document.getElementById(prefix_component + "__tuoi").value;
	if ( tuoi == ""){
		return;
	}
	else {
		document.getElementById(prefix_component + "__namsinh").value ="01/01/" + ( new Date().getFullYear() - parseInt(tuoi));
	}
}


// var dataList;  20110214 bao.ttc: dua vao trong ham
   
function getListBenhNhanTiepDon() {
  try{
	
	var dataList = '';
	var banKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value;
	var ngayThamKham = document.getElementById(prefix_component + "__ngaytg").value;
	
	//if (banKhamMa == ""){
	//  document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").focus();
	//  return;
	//}

	var request = banKhamMa + " ;" + ngayThamKham + " ; ";
    //bao.ttc: var xml;
    //bao.ttc: var data;
    
    //var url = myContextPath + "/actionServlet?urlAction=GetDanhSachBenhNhanTiepDonAction&xmlData=" + request;
    var url =  myContextPath + "/actionServlet?";
    //xml = new JKL.ParseXML( url );
    //data = xml.parse();    
    //i = 0;
    
    //alert("data:"+data);
    
    //if(typeof(data.list) == "object"){
    //    //alert("call listCatalogDanhSachBenhNhanTiepDon");
    //    dataList = data.list;
    //    listCatalogDanhSachBenhNhanTiepDon("__benhnhanduoctiepdon",dataList); 
    //}
    //xml = new JKL.ParseXML( url );
    var xmlHttp = createXmlHttpRequest();
    //data = xml.parse();    
    
    
    
    var params = "urlAction="+ encodeURI("GetDanhSachBenhNhanTiepDonAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
			        //alert(dataList); //bao.ttc
			        listCatalogDanhSachBenhNhanTiepDon("__benhnhanduoctiepdon",dataList); 
				}	
			    
			  	document.getElementById( "__benhnhanduoctiepdon").focus();
		
		}
	};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
  }catch(e){
    alert("getListBenhNhanTiepDon():" + e);
  }	
}     
function setDefaultValueForBacSi(){
	var giaTriBacSiMa = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;
	// alert("giaTriBacSiMa:"+giaTriBacSiMa);
	if (giaTriBacSiMa != null && giaTriBacSiMa != ""){
	
	}else{
		
		var bacsiClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_thamkham_bacsi");
		if (bacsiClientDefault) {
			document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value = bacsiClientDefault.Ten;
			myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
			//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").select();
		}	
	}
	
}

function luuTruGiaTriClientDefault(){

	var giaTriBacSiMa = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;
	// alert("giaTriBacSiMa:"+giaTriBacSiMa);
	if (giaTriBacSiMa != null && giaTriBacSiMa != ""){
		var bacsiClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_thamkham_bacsi");
		//alert("bankhamClientDefault:"+bankhamClientDefault);
		if (bacsiClientDefault == null || bacsiClientDefault == false) { //insert
			var chNames = new Array();
			chNames[0] = "MaSo";
			chNames[1] = "Ma";
			chNames[2] = "Ten";
			var chValues = new Array();
			chValues[0] = 121002;
			chValues[1] = "B121_thamkham_bacsi";
			chValues[2] = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;	
		    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
		    //alert("insert");
		}else{ //update
			
			var chNames = new Array();
			chNames[0] = "Ten";
			
			var chValues = new Array();
			chValues[0] = giaTriBacSiMa;
			
			updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_thamkham_bacsi", chNames,chValues );
			
		}    	
	}
}

function setDefaultValueForBanKham(){

		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
		
		}else{
			
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_thamkham_bankham");
			if (bankhamClientDefault) {
				document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value = bankhamClientDefault.Ten;
				myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA_1', 'DT_DM_BAN_KHAM__1');
				//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").select();
			}	
		}
	
}
// luu y : chi goi khi nhan tu menu
function luuTruGiaTriClientDefaultForBanKham(){
	
		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_thamkham_bankham");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121002;
				chValues[1] = "B121_thamkham_bankham";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_thamkham_bankham", chNames,chValues );
				
			}    	
		}
	
}
//

function toNgayTaiKham(){
	 ////////////////////////////////////////////
    var ngaytiepdon =  document.getElementById(prefix_component + "__ngaytg").value;
    var songay =  document.getElementById(prefix_component + "__taikhamsau").value;
	if(songay==null||songay=="") 
	{
		document.getElementById(prefix_component + "__ngaytaikham").value= "";
		return;
	}
	else
	{
		if(parseInt(songay)<=0)
		{
			document.getElementById(prefix_component + "__ngaytaikham").value= "";
			return;
		}
	}
    
    var myDay = ngaytiepdon.substring(0,2);
	var myMonth = ngaytiepdon.substring(3,5);
	var myYear = ngaytiepdon.substring(6,10);
	
	//alert(myDay +","+myMonth+","+myYear);
	 
	var myDate = new Date();
	
	myDate.setDate(parseInt(myDay,10));
	myDate.setMonth(parseInt(myMonth,10) - 1); // true month
	myDate.setFullYear(parseInt(myYear,10) ); 
	//alert(myDate);
	myDate.setDate(myDate.getDate() + parseInt(songay,10));
	//alert(myDate);
	
	var sMyDay = "" + parseInt(myDate.getDate(),10);
	if (sMyDay.length == 1){
		sMyDay = "0" + sMyDay;
	}
	
	var sMyMonth = "" + ( parseInt(myDate.getMonth(),10) + 1 );
	if (sMyMonth.length == 1){
		sMyMonth = "0" + sMyMonth;
	}
	
	var giaTriDen = sMyDay + "/" + sMyMonth + "/" + myDate.getFullYear() ;
	document.getElementById(prefix_component + "__ngaytaikham").value= giaTriDen;
	
	
}

function toSoNgay(){

	var ngaytiepdon =  document.getElementById(prefix_component + "__ngaytg").value;
	var ngaytaikham =  document.getElementById(prefix_component + "__ngaytaikham").value;
	
	if(ngaytaikham==null || ngaytaikham=="")
	{
		document.getElementById(prefix_component + "__taikhamsau").value = "";
		return;
	}
	
	
	var myDay = ngaytiepdon.substring(0, 2);
	var myMonth = ngaytiepdon.substring(3, 5);
	var myYear = ngaytiepdon.substring(6, 10);

	var myDate = new Date();
	myDate.setDate(parseInt(myDay,10));
	myDate.setMonth(parseInt(myMonth,10) - 1); // true month
	myDate.setFullYear(parseInt(myYear,10));

	var dateNgayKham = myDate.getTime();

	var myDay2 = ngaytaikham.substring(0, 2);
	var myMonth2 = ngaytaikham.substring(3, 5);
	var myYear2 = ngaytaikham.substring(6, 10);

	var myDate2 = new Date();
	myDate2.setDate(parseInt(myDay2,10));
	myDate2.setMonth(parseInt(myMonth2,10) - 1); // true month
	myDate2.setFullYear(parseInt(myYear2,10));

	var dateNgayTaiKham = myDate2.getTime();
	
	// The number of milliseconds in one day
    var ONE_DAY = 1000 * 60 * 60 * 24;
    
    var difference_ms = Math.abs(dateNgayKham - dateNgayTaiKham);
    
    document.getElementById(prefix_component + "__taikhamsau").value = Math.round(difference_ms/ONE_DAY);
	
}

function lockBanKham() {
	
    document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").disabled=(document.getElementById(prefix_component + "__matiepdon").value != "");
    

    var widget = dijit.byId("DT_DM_BAN_KHAM__1");
    if (widget == null){		    	
    	document.getElementById("DT_DM_BAN_KHAM__1").disabled = (document.getElementById(prefix_component + "__matiepdon").value != "");		    	
    }else{
     	widget.setAttribute('disabled',(document.getElementById(prefix_component + "__matiepdon").value != ""));  
    }

	// 20110121 bao.ttc: neu khong tim thay BN thi focus vao Ma tiep don
	if( document.getElementById(prefix_component + "__matiepdon").value == "" ){
		document.getElementById(prefix_component + "__matiepdon").focus();
	} else{
		document.getElementById(prefix_component + "DM_NGHE_NGHIEP_MA").focus();
	}
	
}

function unlockBanKham() {
	
	if (document.getElementById( "DT_DM_BAN_KHAM__1").disabled == true){
        changeDisabledAttr("DT_DM_BAN_KHAM__1");  
   	}
    document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").disabled = false;
	document.getElementById(prefix_component + "__matiepdon").focus();
	
}

/*function days_between(date1, date2) {

    // The number of milliseconds in one day
    var ONE_DAY = 1000 * 60 * 60 * 24

    // Convert both dates to milliseconds
    var date1_ms = date1.getTime()
    var date2_ms = date2.getTime()

    // Calculate the difference in milliseconds
    var difference_ms = Math.abs(date1_ms - date2_ms)
    
    // Convert back to days and return
    return Math.round(difference_ms/ONE_DAY)
    
   

}
*/

