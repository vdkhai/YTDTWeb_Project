/*
var DT_DM_KY_THUAT = new Array (
   	   ["DT_DM_KY_THUAT_MAPHU", "DT_DM_KY_THUAT_MAPHU1", "MaSo", "Ten", "NgayChinhSua", "DaXoa","LoaiCLS","LoaiCLSFather","ChuDau","ChuDauFather","PhanBietCLS","Khoa1","Khoa2","Khoa3","Khoa4","Khoa5","Khoa6","Khoa7","Khoa8","Khoa9","Khoa10"],
         ["double", "double", "varchar(15)", "varchar(250)", "double", "integer", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)", "varchar(15)"]);         
*/
function init() {
	
    if (window.google && google.gears) {
    	
        try {
          	//LoadCatalogFromServer();
          	
          	setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           
                
            //setAttrForComboboxJSF(prefix_component + '__mabankham','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM','10');
          	//setAttrForCombobox_StoreValue(prefix_component + '__mabankham','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM','10', DtDmBanKham);
            //setAttrForCombobox(prefix_component + "__mabankham","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","","");    
                
            //alert(1);  
            //setAttrForComboboxJSF(prefix_component + '__madantoc','DM_DAN_TOC_span','DM_DAN_TOC','10');
            //setAttrForCombobox_StoreValue(prefix_component + '__madantoc','DM_DAN_TOC_span', 'DM_DAN_TOC','10', DmDanToc);
            //setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");  
           
            //alert(2);
                
            //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1','DT_DM_NHAN_VIEN__1','10', DtDmNhanVien);
            //setAttrForCombobox_StoreValue(prefix_component + '__mabacsi','DT_DM_NHAN_VIEN_span2','DT_DM_NHAN_VIEN__2','10', DtDmNhanVien);
            
            //setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");  
            setAttrForCombobox(prefix_component + "__mabacsi","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
                
        
        
            //setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");  
            //setAttrForCombobox(prefix_component + "DTDMKYTHUAT_MA","DT_DM_CLS_BANG_GIA","DT_DM_CLS_BANG_GIA","getDtDmClsBangGia_KyThuat()","set_diengiai(\'DTDMKYTHUAT_MA\',\'DT_DM_CLS_BANG_GIA\')","","");
                
            //setAttrForCombobox(prefix_component + 'DTDMKYTHUAT_MA','DT_DM_CLS_BANG_GIA_span','DT_DM_CLS_BANG_GIA',"getDtDmClsBangGia_Search(\""+ prefix_component + "DTDMKYTHUAT_MA\" ,\""+prefix_component + "__loai\")","","setKTC(\""+ prefix_component + "DTDMKYTHUAT_MA\");","");
            //setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoaByCLS(\""+ prefix_component + "DTDMKYTHUAT_MA_pk\")","","","");
                    
                
                
                
           	//setAttrForComboboxJSF(prefix_component + '__madoituong','DT_DM_DOI_TUONG_span','DT_DM_DOI_TUONG','10');
          	//alert(5);
           //setAttrForCombobox_StoreValue(prefix_component + '__madoituong','DT_DM_DOI_TUONG_span', 'DT_DM_DOI_TUONG','10', DtDmDoiTuong);
           //  setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","",""); 
            //alert(6);
            document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
            //alert(7);
            
            //setAttrForCombobox_StoreValue(prefix_component + 'TINH_MA','DM_TINH_span', 'DM_TINH','10', DmTinh);
            //setAttrForCombobox_StoreValue(prefix_component + 'HUYEN_MA','DM_HUYEN_span','DM_HUYEN','10', DmHuyen);
            //setAttrForCombobox_StoreValue(prefix_component + 'XA_MA','DM_XA_span','DM_XA','10', DmXa); 
            
            //alert(8);        
                
                //setAttrForComboboxBenhNhanTiepDon('__benhnhanduoctiepdon_span', '__benhnhanduoctiepdon','10','__matiepdon');
              
               // setAttrForComboboxForNoDB(prefix_component + '__benhnhanduoctiepdon_ma','__benhnhanduoctiepdon_span', '__benhnhanduoctiepdon',"","mySetValueForBenhNhanTiepDon(\'__benhnhanduoctiepdon\',\'__matiepdon\');document.getElementById('" + prefix_component + "__matiepdon').focus(); ","statusEnterAnhTabForChon = 0; ","statusEnterAnhTabForChon = 1");
            
                timer.setTimeout(function(){setOnload();},100); 
                //setOnload(); 	            							  
            
            
        } catch (e) {
        	alert("init():" + e);
        }
    }    
}

/*function setCheckForDichVu(){
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
}

function onChangeForYC(){
	if (document.getElementById(prefix_component + "__dichVu").checked == true){
		document.getElementById(prefix_component + "__yc").checked = true;	
	}
}*/

function setKTC(danhmuc_banggia_ma){
    //alert("setKTC");
	var valueMa = document.getElementById(danhmuc_banggia_ma).value;
	if (valueMa !=null && valueMa != ""){
		/*var KTC = getKTCFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		if (KTC == "KTC"){
		    //alert("La KTC" + KTC);
		    //alert(document.getElementById(prefix_component + "__ktc"))
			document.getElementById(prefix_component + "__ktc").checked = true;
			document.getElementById(prefix_component + "__ktc_hid").value = true;
			
		}else{
			document.getElementById(prefix_component + "__ktc").checked = false;
			document.getElementById(prefix_component + "__ktc_hid").value = false;		
		}*/	
		
		/*var NDM = getNDMFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		if (NDM == "1"){
		    //alert("La NDM" + NDM);
		    //alert(document.getElementById(prefix_component + "__ndm"))
			document.getElementById(prefix_component + "__ndm").checked = true;
			document.getElementById(prefix_component + "__ndm_hid").value = true;
			
		}else{
			document.getElementById(prefix_component + "__ndm").checked = false;
			document.getElementById(prefix_component + "__ndm_hid").value = false;		
		}*/	
		
		//var Mien = getMIENFromCLSBangGia(valueMa);
		//alert("???KTC" + KTC);
		//if (Mien == "1"){
		    //alert("La NDM" + NDM);
		    //alert(document.getElementById(prefix_component + "__ndm"))
			//document.getElementById(prefix_component + "__mien").checked = true;
			//document.getElementById(prefix_component + "__mien_hid").value = true;
			
		//}else{
			//document.getElementById(prefix_component + "__mien").checked = false;
			//document.getElementById(prefix_component + "__mien_hid").value = false;		
		//}	
		
	}

}
function setOtherValueForGetFromServer (){

	//var maKTObj = document.getElementById(prefix_component + "DTDMKYTHUAT_MA_pk");

    var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	
	//var obj = document.getElementById(prefix_component + "__dongia");
	
	//var yeuCauObject = document.getElementById(prefix_component + "__yc");
	//var mienPhiObject = document.getElementById(prefix_component + "__mien");
	//var ngoaiDanhMucObject = document.getElementById(prefix_component + "__ndm");
	
	//alert(obj);
	//alert(yeuCauObject);
	//alert(mienPhiObject);
	//alert(ngoaiDanhMucObject);
	
	var bnBaoHiem = document.getElementById(prefix_component + "__madoituong");
	
	//alert(1);
	
	//try{
	//	if (parseInt(donGia) = parseInt(donGiaBH)){
	//		document.getElementById(prefix_component + "__ndm").checked = true;
	//	}
	//}catch(e){
	//
	//}
	
	//if (yeuCauObject.checked == true){
	//	obj.value = donGiaYC;
	//}
	
	
	//else if (mienPhiObject.checked == true){
	//  	
	//    obj.value = donGiaMP; 
	//}
	
	//else if (bnBaoHiem.value == "BH"){
	     //if (ngoaiDanhMucObject.checked == true){
	     //	 obj.value = donGia;
	     //}else{
	     	//obj.value = donGiaBH;
	     //}	
	//}else{
		//obj.value = donGia;	
	//}
	//document.getElementById(prefix_component + "donGiaHid").value = obj.value;
	
	
	//var tenCLS = getTenFromMaSo( 'DT_DM_CLS_BANG_GIA' , maKTObj.value );
	
	//alert(tenCLS);
	//document.getElementById(prefix_component + "tenDmKyThuatHid").value = tenCLS;
	
	
	blockInphieu();

}
function tinhToanForThatThu(){
	var thatthu = document.getElementById(prefix_component + "__thatthu").value;
	var tongcong = document.getElementById(prefix_component + "__thanhtien1").value;
	var bnyttra = document.getElementById(prefix_component + "__bhytchi").value;
	var miengiam = document.getElementById(prefix_component + "__miengiam").value; 
	var bntra =  parseFloat(tongcong) -  parseFloat(miengiam) -  parseFloat(thatthu);
	document.getElementById(prefix_component + "__bntra").value = bntra;
	
	
}
function tinhToanForMienGiam(){
	//alert("tinhToanForMienGiam");
	var tongcong = document.getElementById(prefix_component + "__thanhtien1").value;
	var phantrammiengiam =  document.getElementById(prefix_component + "__permiengiam").value; 
	
	var miengiam =  parseFloat(tongcong) * parseFloat(phantrammiengiam) /100;
	document.getElementById(prefix_component + "__miengiam").value = miengiam;     
	
	var thatthu = document.getElementById(prefix_component + "__thatthu").value;
	
	var bnyttra = document.getElementById(prefix_component + "__bhytchi").value;
	var bntra =  parseFloat(tongcong) -  parseFloat(miengiam) -  parseFloat(thatthu);
	document.getElementById(prefix_component + "__bntra").value = bntra;

}
//function getDtDmClsBangGia_KyThuat(){
	
	//var catalog = new DtDmClsBangGia({});
	
	//var textboxValue = document.getElementById(prefix_component + "DTDMKYTHUAT_MA").value;
	//var chuDau = document.getElementById(prefix_component + "__loai").value;
	
	//var myMaso = getMaSo("DT_DM_CLS","Ma",chuDau);
			
	//return catalog.getDataList_CLS(textboxValue, myMaso);
	
			
//}

function onCompleteGetInfor(){

	try { 
		blockInphieu();
	    
		//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox(prefix_component + '__mabacsi', 'DT_DM_NHAN_VIEN__2');
		
		myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
		myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
 		myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');

		//myOnblurTextbox(prefix_component + '__madoituong', 'DM_DOI_TUONG');
  		//myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
		
		var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
		//alert(donViTuoi);
		if (donViTuoi == "1"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
		}else if (donViTuoi == "2"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
		}else if (donViTuoi == "3"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		}else{
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
		}
		
		var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
		var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
		//alert(namsinh);
		//alert(ngaysinh);
		if (ngaysinh == null || ngaysinh == ''){
			
			document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
			//alert("null");
		}
		//alert("All done!");
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}

}

function setPriceForKT(){
	
  try{	
	//var maKTObj = document.getElementById(prefix_component + "DTDMKYTHUAT_MA_pk");
	//var soluongObj = document.getElementById(prefix_component + "__soluong");
	//alert(1111);
	//try{
	    //alert(1112);
		//if (maKTObj.value == "" || soluongObj.value == "" || parseInt(soluongObj.value) <= 0 ){
    	    //alert(2);
    		//document.getElementById(prefix_component + "__loai").focus;
    		//return;	
		//}
	//}catch(e){
	        //alert(3);
	    	//document.getElementById(prefix_component + "__loai").focus;
	    	//return;	    
	//}	
	//alert(4);
	//getPriceKyThuatFromServer('GetPriceDtDmKyThuatAction',maKTObj.value);
	
	//alert(2);
	var donGia = document.getElementById(prefix_component + "hid_Gia").value;
	var donGiaBH = document.getElementById(prefix_component + "hid_GiaBH").value;
	var donGiaYC = document.getElementById(prefix_component + "hid_GiaYC").value;
	var donGiaMP = document.getElementById(prefix_component + "hid_GiaMP").value;
	//alert(21);
	//var obj = document.getElementById(prefix_component + "__dongia");
	
	//var yeuCauObject = document.getElementById(prefix_component + "__yc");
	//var mienPhiObject = document.getElementById(prefix_component + "__mien");
	//var ngoaiDanhMucObject = document.getElementById(prefix_component + "__ndm");
	//alert(22);
	var bnBaoHiem = document.getElementById(prefix_component + "__madoituong");
	//try{
		//if (parseInt(donGia) == parseInt(donGiaBH)){
			//document.getElementById(prefix_component + "__ndm").checked = true;
		//}
	//}
	//catch(e){
	           
	//}
	//alert(22);
	
	//alert("yeuCauObject"+yeuCauObject);
	//alert("mienPhiObject"+mienPhiObject);
	//alert("bnBaoHiem"+bnBaoHiem);
	//alert(22);
	
	/*if (yeuCauObject.checked == true){
	    //alert(23);
	    //alert(obj);
		obj.value = donGiaYC;
	}else if (mienPhiObject.checked == true){
	  	//alert(24);
	    obj.value = donGiaMP; 
	}else if (bnBaoHiem.value == "BH"){
	    //alert(25);
	     if (ngoaiDanhMucObject.checked == true){
	     	 obj.value = donGia;
	     }else{
	     	obj.value = donGiaBH;
	     }	
	}else{
	    // alert(26);
		obj.value = donGia;	
	}*/
	
	//var num = 0;
		//if (document.getElementById(prefix_component + "__dongia").value != ''){
		//	num = parseFloat( document.getElementById(prefix_component + "__dongia").value);
		//}
		//alert(document.getElementById(prefix_component + "__dongia_HIDDEN").value);
		//document.getElementById(prefix_component + "donGiaHid").value = num;
		//numberFormatBlur(document.getElementById(prefix_component + "donGiaHid"));
		
		
	//document.getElementById(prefix_component + "donGiaHid").value = obj.value;
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
	document.getElementById("__divGhiNhan").style.display = "block";
	document.getElementById("__divIn").style.display = "block";
	document.getElementById("__divHuyphieu").style.display = "block";
	document.getElementById("__divInphieuTT").style.display = "block";
	//document.getElementById("__divThuoc").style.display = "block";
	//document.getElementById("__divThuocBHYT").style.display = "block";
	
	var resultHidden = document.getElementById(prefix_component + "resultHidden").value;
	//alert(resultHidden);
	
	var maPhieu =  document.getElementById(prefix_component + "__maphieu").value;
	
	if (resultHidden == "success" && maPhieu != null && maPhieu !=""){
	  //alert(resultHidden);
 	  document.getElementById("__divIn").style.display = "block";
 	  document.getElementById("__divHuyphieu").style.display = "block";
 	 document.getElementById("__divInphieuTT").style.display = "block";
 	  document.getElementById("__divGhiNhan").style.display = "none";
	}else{
	  //alert(resultHidden);
	  document.getElementById("__divIn").style.display = "none";
	  document.getElementById("__divHuyphieu").style.display = "none";
	  document.getElementById("__divInphieuTT").style.display = "none";
	  document.getElementById("__divGhiNhan").style.display = "block";
	}
	
	var disabledHuyPhieu =  document.getElementById(prefix_component + "disabledHuyPhieu").value;
	if (disabledHuyPhieu == "true") {
		document.getElementById("__divHuyphieu").style.display = "none";
	}
	//var bnBaoHiemValue = document.getElementById(prefix_component + "__madoituong").value;
	//alert(bnBaoHiem);
	//if (bnBaoHiemValue == 'BH' || bnBaoHiemValue == 'TE' || bnBaoHiemValue == 'MP'){
		//document.getElementById("__divThuoc").style.display = "none";
	//}if (bnBaoHiemValue == 'TP'){
	//	document.getElementById("__divThuocBHYT").style.display = "none";
	//}
}

function setOnload(){
  //return;
	blockInphieu();
	 try{
	
	  myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	  //alert(1);
	  myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	  //alert(2);
	  myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	  //alert(3);	
	  //
	  
	  //myOnblurTextboxJSF(prefix_component + 'DT_DM_DOI_TUONG_MA',prefix_component + 'DT_DM_DOI_TUONG');
	  
	  //myOnblurTextbox(prefix_component + '__madoituong', 'DM_DOI_TUONG');
	  //alert(4);
	  //myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
	  
	  //myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	  
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
	   	document.getElementById(prefix_component + "__matiepdon").focus();	
		//}	
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}
//function reloadValueForCombobox(){
	//myOnblurTextbox(prefix_component + 'DTDMKYTHUAT_MA', 'DT_DM_CLS_BANG_GIA');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
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
   	
   	
   	
//}



var dataList;
   
function getListBenhNhanTiepDon() {
  try{	

  
	//var banKhamMa = document.getElementById(prefix_component + "__mabankham").value;
	//if (banKhamMa == null || banKhamMa==""){
	//  return;
	//}
	var ngayThamKham = document.getElementById(prefix_component + "__ngay").value;
	if (ngayThamKham == null || ngayThamKham==""){
	  return;
	}


	var request = banKhamMa + " ;" + ngayThamKham + " ; ";
	
    var xml;
    var data;
    
    var xmlHttp = createXmlHttpRequest();
    var url =  myContextPath + "/actionServlet?";
    var params = "urlAction="+ encodeURI("GetDanhSachBenhNhanTiepDonAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
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



