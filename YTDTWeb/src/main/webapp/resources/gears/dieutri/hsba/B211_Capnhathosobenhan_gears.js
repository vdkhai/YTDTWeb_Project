var canfocus = true;                   
function init() {
	
    if (window.google && google.gears) {
    	
        //try {
        
        
            //setAttrForCombobox(prefix_component + 'DM_KHOA_MA_1','DM_KHOA_span1', 'DM_KHOA__1',"getDmKhoa_NoiTru()","","","");
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA_1','DM_KHOA_span1', 'DM_KHOA__1',"getDmKhoa_NoiTru()","clearNhanVien()","","");
            
            setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");
			setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_3','DM_BENH_ICD_span3', 'DM_BENH_ICD__3',"getDmBenhIcd()","","",""); 
			setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_2','DM_BENH_ICD_span2', 'DM_BENH_ICD__2',"getDmBenhIcd()","","","");
			setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_4','DM_BENH_ICD_span4', 'DM_BENH_ICD__4',"getDmBenhIcd()","","","");
			
			setAttrForCombobox(prefix_component + 'DT_DM_KET_QUA_MA','DT_DM_KET_QUA_span', 'DM_KET_QUA_DIEU_TRI',"getDmKetQuaDieuTri()","","checkForKetQua()","");
			setAttrForCombobox(prefix_component + 'DM_DIEU_TRI_HSBA_MA','DM_DIEU_TRI_HSBA_span', 'DM_DIEU_TRI_HSBA',"getDmDieuTriHSBA()","","checkForHuongXuLy()","");
			
			//setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN',"getDtDmNhanVien()","","","");
			setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN',"getDtDmNhanVienByMaKhoa(\"" + prefix_component + "DM_KHOA_MA_1\")","","","");
			
			setAttrForCombobox(prefix_component + 'DM_KHOA_MA_2','DM_KHOA_span2', 'DM_KHOA__2',"getDmKhoa_NoiTru_NgoaiTruKhoa(\"" + prefix_component + "DM_KHOA_MA_2\")","","","");	
			setAttrForCombobox(prefix_component + 'BENHVIEN_MA_2','DM_BENH_VIEN_span2', 'DM_BENH_VIEN__2',"getDmBenhVien()","","","");	
			setAttrForCombobox(prefix_component + 'DM_LY_DO_CV_MA','DM_LY_DO_CV_span', 'DT_DM_LY_DO_CV',"getDtDmLyDoCv()","","","");
			
			setAttrForComboboxForNoDB(prefix_component + '__benhnhanduoctiepdon_ma','__benhnhanduoctiepdon_span', '__benhnhanduoctiepdon',"","mySetValueForBenhNhanTiepDon(\'__benhnhanduoctiepdon\',\'__sobenhan\'); document.getElementById('" + prefix_component + "__sobenhan').focus(); ","statusEnterAnhTabForChon = 0; ","statusEnterAnhTabForChon = 1");
             
             
             	canfocus = false; 
             	 timer.setTimeout(function(){onCompleteGetInfor();},100); 
			    canfocus = true; 
	 
            document.getElementById(prefix_component + "__tuvong24g").checked = false;
		 document.getElementById(prefix_component + "__tuvong24g").disabled=true; 
		 
		 document.getElementById(prefix_component + "__giotuvong").value=""; 
      	 document.getElementById(prefix_component + "__ngaytuvong").value=""; 
      	 
      	 document.getElementById(prefix_component + "__giotuvong").disabled=true; 
      	 document.getElementById(prefix_component + "__ngaytuvong").disabled=true; 
      	 
      	 //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
      	 //initshorcut();
    }
    
}

function clearNhanVien(){
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_pk").value = "";	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value = "";	
	document.getElementById("DT_DM_NHAN_VIEN").value = "";
}

function initshorcut(){    
    shortcut.add("Backspace", function() {
        //no action for disable Backspace
    },{'type':'keydown',"propagate":false,'readonly_in_input':true,'target':document});
}

function checkForKetQua(){

	var ketQuaMa = document.getElementById(prefix_component + "DT_DM_KET_QUA_MA").value;
	
	//alert(ketQuaMa);
	if (ketQuaMa == "5"){
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").disabled=false;
        document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").className="myinput";
        if (document.getElementById( "DM_BENH_ICD__4").disabled == true){
        	changeDisabledAttr("DM_BENH_ICD__4");  
      	}
      	 
      	 document.getElementById(prefix_component + "__tuvong24g").disabled=false; 
      	 document.getElementById(prefix_component + "__giotuvong").disabled=false; 
      	 document.getElementById(prefix_component + "__ngaytuvong").disabled=false; 
      	 
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA_pk").value="";
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").value="";
      	 myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_HSBA_MA', 'DM_DIEU_TRI_HSBA');
      	 document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").disabled=true; 
      	 if (document.getElementById( "DM_DIEU_TRI_HSBA").disabled == false){
         	changeDisabledAttr("DM_DIEU_TRI_HSBA");  
      	 }
      	 //checkForHuongXuLy();
      	if (canfocus == true)
         	 document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").focus();
             
	}else{
	
		if (document.getElementById( "DM_BENH_ICD__4").disabled == false){
        	changeDisabledAttr("DM_BENH_ICD__4");  
      	}
      	document.getElementById(prefix_component + "DM_BENH_ICD_MA_4_pk").value="";
      	document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").value="";
      	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_4', 'DM_BENH_ICD__4');
      	
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").disabled=true; 
		document.getElementById(prefix_component + "DM_BENH_ICD_MA_4").className="myreadonly"; 
		
		
		document.getElementById(prefix_component + "__tuvong24g").checked = false;
		document.getElementById(prefix_component + "__tuvong24g").disabled=true; 
		 
		document.getElementById(prefix_component + "__giotuvong").value=""; 
      	document.getElementById(prefix_component + "__ngaytuvong").value=""; 
      	 
      	document.getElementById(prefix_component + "__giotuvong").disabled=true; 
      	document.getElementById(prefix_component + "__ngaytuvong").disabled=true; 
      	 
      	 
      	//document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").value="";
      	//myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_HSBA_MA', 'DM_DIEU_TRI_HSBA');
      	document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").disabled=false; 
      	if (document.getElementById( "DM_DIEU_TRI_HSBA").disabled == true){
      		changeDisabledAttr("DM_DIEU_TRI_HSBA");  
      	}
      	checkForHuongXuLy();
      	if (canfocus == true)
      		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").focus();
	}	
}




function checkForHuongXuLy(){
  try{
	var huongXuLy = document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").value;
	//alert(huongXuLy);
	if (huongXuLy == "1" ){ // chuyen khoa
		
		document.getElementById(prefix_component + "DM_KHOA_MA_2").disabled=false;
        document.getElementById(prefix_component + "DM_KHOA_MA_2").className="myinput";
        if (document.getElementById( "DM_KHOA__2").disabled == true){
        	changeDisabledAttr("DM_KHOA__2");  
      	}
        
        clearRaVien();
        clearChuyenTuyenTren();
        
        if (canfocus == true)
        	document.getElementById(prefix_component + "DM_KHOA_MA_2").focus();
	}else{
		
			if (huongXuLy == "2" ){ //chuyen tuyen tren
				
				document.getElementById(prefix_component + "BENHVIEN_MA_2").disabled=false;
		        document.getElementById(prefix_component + "BENHVIEN_MA_2").className="myinput";
		        if (document.getElementById( "DM_BENH_VIEN__2").disabled == true){
		        	changeDisabledAttr("DM_BENH_VIEN__2");  
		      	}
		        
			    document.getElementById(prefix_component + "DM_LY_DO_CV_MA").disabled=false;
		        document.getElementById(prefix_component + "DM_LY_DO_CV_MA").className="myinput";
		        if (document.getElementById( "DT_DM_LY_DO_CV").disabled == true){
		        	changeDisabledAttr("DT_DM_LY_DO_CV");  
		      	}
		        document.getElementById(prefix_component + "__gioravien").disabled=false;
		    	document.getElementById(prefix_component + "__ngayrv").disabled=false;
		    	
		      	clearKhoaMa();
		      	
		      	if (canfocus == true)
		      		document.getElementById(prefix_component + "BENHVIEN_MA_2").focus();
			
			}else{
			   
			    if (huongXuLy == "3" ){ //ra vien
			    	document.getElementById(prefix_component + "__gioravien").disabled=false;
			    	document.getElementById(prefix_component + "__ngayrv").disabled=false;
			        
			         clearChuyenTuyenTren();
			         clearKhoaMa();
			         if (canfocus == true)
					    document.getElementById(prefix_component + "__gioravien").focus();
			    }else{
			        // ma is empty
			        clearChuyenTuyenTren();
			        clearKhoaMa();
			        clearRaVien(); 
			        
			    	if (canfocus == true)
			    		document.getElementById(prefix_component + "__sogiuong").focus();	
			    }
				
			}
		
		//
	}	
  }catch(e){
    alert("error: " + e.description);
  }
}
function clearRaVien(){
  document.getElementById(prefix_component + "__gioravien").value="";
  document.getElementById(prefix_component + "__ngayrv").value="";
  document.getElementById(prefix_component + "__gioravien").disabled=true;
  document.getElementById(prefix_component + "__ngayrv").disabled=true;			    

}
function clearChuyenTuyenTren(){
    //alert("clearChuyenTuyenTren");
	// chuyen tuyen tren
	if (document.getElementById( "DM_BENH_VIEN__2").disabled == false){
        	changeDisabledAttr("DM_BENH_VIEN__2");  
      	}
      	document.getElementById(prefix_component + "BENHVIEN_MA_2_pk").value="";
      	document.getElementById(prefix_component + "BENHVIEN_MA_2").value="";
      	myOnblurTextbox(prefix_component + 'BENHVIEN_MA_2', 'DM_BENH_VIEN__2');
      	
		document.getElementById(prefix_component + "BENHVIEN_MA_2").disabled=true; 
		document.getElementById(prefix_component + "BENHVIEN_MA_2").className="myreadonly";
		
		//alert(document.getElementById(prefix_component + "BENHVIEN_MA_2").disabled)
		
	
	//ly do chuyen
       if (document.getElementById( "DT_DM_LY_DO_CV").disabled == false){
        	changeDisabledAttr("DT_DM_LY_DO_CV");  
      	}
      	document.getElementById(prefix_component + "DM_LY_DO_CV_MA_pk").value="";
      	document.getElementById(prefix_component + "DM_LY_DO_CV_MA").value="";
      	myOnblurTextbox(prefix_component + 'DM_LY_DO_CV_MA', 'DT_DM_LY_DO_CV');
      	
		document.getElementById(prefix_component + "DM_LY_DO_CV_MA").disabled=true; 
		document.getElementById(prefix_component + "DM_LY_DO_CV_MA").className="myreadonly";
}

function clearKhoaMa(){
       if (document.getElementById( "DM_KHOA__2").disabled == false){
        	changeDisabledAttr("DM_KHOA__2");  
      	}
      	document.getElementById(prefix_component + "DM_KHOA_MA_2_pk").value="";
      	document.getElementById(prefix_component + "DM_KHOA_MA_2").value="";
      	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2', 'DM_KHOA__2');
      	
		document.getElementById(prefix_component + "DM_KHOA_MA_2").disabled=true; 
		document.getElementById(prefix_component + "DM_KHOA_MA_2").className="myreadonly"; 		
}



function setValueForThuThuatPhauThuat(){
	 var valueThuThuatHid =  document.getElementById(prefix_component + "ThuThuatPhauThuatHid").value;   
	 
	 //alert(document.getElementById(prefix_component + "__thuthuat").checked);     
	 //if (document.getElementById(prefix_component + "__thuthuat").checked == false){
	 //	if (valueThuThuatHid ==  true || valueThuThuatHid == "true"){
	 //		document.getElementById(prefix_component + "__thuthuat").checked = true;
	 //	}
	 //}
	 
	 //if (document.getElementById(prefix_component + "__thuthuat").checked == true){
	 	
	 	//document.getElementById(prefix_component + "__gotohsbamo").disabled = false;
	 //}else{
		//document.getElementById(prefix_component + "__gotohsbamo").disabled = true;
	 //}
}

function setValueForSinh(){

	 //var valueSinhHid =  document.getElementById(prefix_component + "SinhHid").value;   
	 
	 //alert(document.getElementById(prefix_component + "__thuthuat").checked);     
	 //if (document.getElementById(prefix_component + "__sinhdt").checked == false){
	// 	if (valueSinhHid == true || valueSinhHid == "true"){
	 //		document.getElementById(prefix_component + "__sinhdt").checked = true;
	 //	}
	 //}
	 
	 //if (document.getElementById(prefix_component + "__sinhdt").checked == true){
	 //	
	 //	document.getElementById(prefix_component + "__gotohsbasan").disabled = false;
	 //}else{
	//	document.getElementById(prefix_component + "__gotohsbasan").disabled = true;
	 //}
	 
}


function onCompleteGetInfor(){

	highlightOnFocus();
	
  //try{	
  	canfocus = false; 
               
	//myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	//myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	//myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	//myOnblurTextbox(prefix_component + 'DANTOC_MA', 'DM_DAN_TOC');
	//myOnblurTextbox(prefix_component + 'NGHENGHIEP_MA', 'DM_NGHE_NGHIEP');
    //myOnblurTextbox(prefix_component + 'BENHVIEN_MA_1', 'DM_BENH_VIEN__1');
    
  	myOnblurTextbox(prefix_component + 'BENHVIEN_MA_2', 'DM_BENH_VIEN__2');
    myOnblurTextbox(prefix_component + 'DM_KHOA_MA_1', 'DM_KHOA__1');
    myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2', 'DM_KHOA__2');
    myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA', 'DT_DM_NHAN_VIEN');
    
    myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
    myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_2', 'DM_BENH_ICD__2');
    myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_3', 'DM_BENH_ICD__3');
    myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_4', 'DM_BENH_ICD__4');
    myOnblurTextbox(prefix_component + 'DT_DM_KET_QUA_MA', 'DM_KET_QUA_DIEU_TRI');
    
    var huongdieutri = document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA_pk").value;
    
	if (huongdieutri != null){
	  document.getElementById(prefix_component + "DM_DIEU_TRI_HSBA_MA").value = huongdieutri;
	}
    myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_HSBA_MA', 'DM_DIEU_TRI_HSBA');
    myOnblurTextbox(prefix_component + 'DM_LY_DO_CV_MA', 'DT_DM_LY_DO_CV');
          
    var hoten =  document.getElementById(prefix_component + "__hoten").value;           
    if (hoten == null || hoten == ""){
  	    document.getElementById(prefix_component + "DM_KHOA_MA_1").focus();  
  	     document.getElementById(prefix_component + "DM_KHOA_MA_1").select();      
    }else {        
       if (canfocus == true)  
    	document.getElementById(prefix_component + "__chuandoanbandau").focus();     
    }   
    
    var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
	
	
		
	var ngaysinh = document.getElementById(prefix_component + "__namsinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	
	if (ngaysinh == null || ngaysinh == ''){
		document.getElementById(prefix_component + "__namsinh").value = namsinh;
	}
	
	//var valueThuThuatHid =  document.getElementById(prefix_component + "ThuThuatPhauThuatHid").value;   
             	
             	//alert(valueThuThuatHid);     
	 
			 	//if (valueThuThuatHid == true || valueThuThuatHid == "true"){
			 	//    //alert("true");
			 	//	document.getElementById(prefix_component + "__gotohsbamo").disabled = false;
			 	//}else{
			 	// 	//alert("false");
			 	//	document.getElementById(prefix_component + "__gotohsbamo").disabled = true;
			 	//}
	//
	
				//var valueSinhHid =  document.getElementById(prefix_component + "SinhHid").value;   
				 
				//if (valueSinhHid == true  || valueSinhHid == "true"){
			 	//	document.getElementById(prefix_component + "__gotohsbasan").disabled = false;
			 	//}else{
			 	//	document.getElementById(prefix_component + "__gotohsbasan").disabled = true;
			 	//}
			 	
		 	
	checkForKetQua();	
	//checkForHuongXuLy(); // 20110323: Da goi checkForHuongXuLy() trong ham checkForKetQua()
	setNgayDieuTri();
	
    canfocus = true; 
  //}catch(e){
  //  alert("onCompleteGetInfor():"+e);
  //}    
}
//---Reset form---
function myResetAll(){
    myReset(50);
    setValueOnLoad();
    document.getElementById(prefix_component + "__sobenhan").focus(); 
}
function setValueOnLoad(){
    //alert(1);
	document.getElementById(prefix_component + "DM_KHOA_MA_1").focus();
	
	//document.getElementById(prefix_component + "__gioitinh").disabled = true;
	//document.getElementById(prefix_component + "__thuthuat").disabled = true;
	//document.getElementById(prefix_component + "__phauthuat").disabled = true;
	//document.getElementById(prefix_component + "__sinhdt").disabled = true;
	
	/*
	try{
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
	}catch(e){
		alert("in setValueOnLoad()");
	}
	*/
}
/*
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
*/
/*
function set_ngaysinh(){
	tuoi = document.getElementById(prefix_component + "__tuoi").value;
	if ( tuoi == ""){
		return;
	}
	else {
		document.getElementById(prefix_component + "__namsinh").value ="01/01/" + ( new Date().getFullYear() - parseInt(tuoi));
	}
}
*/
	/*
	try{
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
	}catch(e){
		alert("in setValueOnLoad()");
	}
	*/
	

var dataList;
   
function getListBenhNhanHSBA() {
  try{	
  	//var form = document.forms[0];
    //var valid = iesvn_ValidateRequired(form)  ; 
    //if (valid == false){
    //   
    //   return;
    //}
	
	// 20110711 bao.ttc: chuyen sang dung Ma so de search truc tiep
	var khoama = document.getElementById(prefix_component + "DM_KHOA_MA_1_pk").value;
	if (khoama == null || khoama == ''){
	   document.getElementById(prefix_component + "DM_KHOA_MA_1").focus();
	   return;
	}

	var request = khoama;
	
    var xml;
    var data;
    
    var url =  myContextPath + "/actionServlet?";
    var xmlHttp = createXmlHttpRequest();
    var params = "urlAction="+ encodeURI("GetDanhSachBenhNhanHSBAAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);
	
	//alert(params);
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
			        //alert(dataList);
			        listCatalogDanhSachBenhNhanTiepDon("__benhnhanduoctiepdon",dataList); 
				}
				document.getElementById( "__benhnhanduoctiepdon").focus();	
		}
	};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
    
  
  }catch(e){
    alert("getListBenhNhanHSBA():" + e);
  }	
}   
function setNgayDieuTri (){
  try{
 	var ngayvao = document.getElementById(prefix_component + "__ngayvv").value;
	var ngayra = document.getElementById(prefix_component + "__ngayrv").value;
	//alert("ngayvao:"+ngayvao);
	//alert("ngayra:"+ngayra);
	if (ngayvao == "" || ngayra == ""){
	   return ;
	}
	var songay = soNgayDieuTri(ngayvao, ngayra);
	document.getElementById(prefix_component + "__songaydt").value = songay;
  }catch(e){
     //do nothing in case of invalid date input
     //alert(e.description);
  }	
	
}
/**
  * ngayVao: 11/11/2008  -> 15/11/2008
**/  	
function soNgayDieuTri(ngayVao, ngayRa){
	
	if (ngayVao == ngayRa){
	   return 1;
	}
	
	//Set 1 day in milliseconds
	var one_day=1000*60*60*24;
	var _year = parseInt( ngayVao.substr(6,4), 10); // 20110719 bao.ttc: dung co so 10 de tranh truong hop parse qua co so 8 voi cac so: 08 va 09 
	var _month = parseInt( ngayVao.substr(3,2), 10); 
	var _day = parseInt( ngayVao.substr(0,2), 10); 
	
	var _year2 = parseInt( ngayRa.substr(6,4), 10); 
	var _month2 = parseInt( ngayRa.substr(3,2), 10); 
	var _day2 = parseInt( ngayRa.substr(0,2), 10); 
	
	
	var _ngayVao = new Date();	
	_ngayVao.setFullYear(_year,_month - 1,_day);
	
	var _ngayRa = new Date();	
	_ngayRa.setFullYear(_year2,_month2 - 1,_day2);
	
	var daybetween = Math.ceil((_ngayRa.getTime() - _ngayVao.getTime())/(one_day)) + 1;
	
	return daybetween;

}

function testCalendarFirstAcc(var1){
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
    document.getElementById(prefix_component + "__ngayrv").focus();
    document.getElementById(prefix_component + "__ngayrv").select();
    return valid3;
  }   	
  //alert(2);

  var valid4 = iesvn_ValidateTwoDatesWithHours();
  return valid4;
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

function lockKhoa() {
	
    document.getElementById(prefix_component + "DM_KHOA_MA_1").disabled=(document.getElementById(prefix_component + "__sobenhan").value != "");
    

    var widget = dijit.byId("DM_KHOA__1");
    if (widget == null){		    	
    	document.getElementById("DM_KHOA__1").disabled = (document.getElementById(prefix_component + "__sobenhan").value != "");		    	
    }else{
     	widget.setAttribute('disabled',(document.getElementById(prefix_component + "__sobenhan").value != ""));  
    }

	// 20110121 bao.ttc: neu khong tim thay BN thi focus vao Ma tiep don
	if( document.getElementById(prefix_component + "__sobenhan").value == "" ){
		document.getElementById(prefix_component + "__sobenhan").focus();
	}
	
}

function unlockKhoa() {
	
	if (document.getElementById( "DM_KHOA__1").disabled == true){
        changeDisabledAttr("DM_KHOA__1");  
   	}
    document.getElementById(prefix_component + "DM_KHOA_MA_1").disabled = false;
	document.getElementById(prefix_component + "__sobenhan").focus();
	
}

