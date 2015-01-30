var isUpdate = false;

function init() {

	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');			 
            
            setAttrForCombobox(prefix_component + "DTDMNOIBAN_MA", "NOI_BAN_span", "DM_NHA_CUNG_CAP", "getDmNhaCungCap()", "", "", "");
            setAttrForCombobox(prefix_component + "DMKINHPHI_MASO", "KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
            setAttrForCombobox(prefix_component + "DTDMNHANVIEN_CN", "DT_DM_NHAN_VIEN_span1", "DT_DM_NHAN_VIEN__1", "getDtDmNhanVien_Duoc()", "", "", "");
            setAttrForCombobox(prefix_component + "DTDMNHANVIEN_TIEPLIEU", "DT_DM_NHAN_VIEN_span2", "DT_DM_NHAN_VIEN__2", "getDtDmNhanVien_Duoc()", "", "", "");
           
            setAttrForCombobox(prefix_component + "DTDMNGUON_MA", "NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
            setAttrForCombobox(prefix_component + "DMKHO_MASO", "DM_KHO_span", "DT_DM_KHO", "getDtDmKho()", "", "", "");
           
            setAttrForCombobox(prefix_component + "DTDMDONVITINH_MA", "DON_VI_TINH_span", "DM_DON_VI_TINH", "");
            setAttrForCombobox(prefix_component + "DTDMHANGSX_MA", "HANG_SAN_XUAT_span", "DM_NHA_SAN_XUAT", "getDmNhaSanXuat()");            
            setAttrForCombobox(prefix_component + "DMQUOCGIA_MASO", "QUOC_GIA_span", "DM_QUOC_GIA", "getDmQuocGia()");
            setTimeout(function () {
            	setValueOnLoad();
            }, 200);
            initShortcut();
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
    
}

function resetData(){
	document.getElementById("DM_QUOC_GIA").value ="";
	document.getElementById("DM_NHA_SAN_XUAT").value ="";
	document.getElementById("DM_DON_VI_TINH").value ="";
}

function testSoHoaDonNgayHoaDon(){
	var sohoadon = document.getElementById(prefix_component + "PHIEUNHAPKHO_SOHOADON").value;
	var ngayhoadon = document.getElementById(prefix_component + "PHIEUNHAPKHO_NGAYHOADON").value;
	if (sohoadon != '' && ngayhoadon != ''){
		testSoHoaDonNgayHoaDonFromServer("KiemTraSoHoaDonNgayHoaDonAction",sohoadon+","+ngayhoadon);
	}
}

function getThuocInfo(){
	var tenThuoc = document.getElementById(prefix_component + "DM_THUOC"+"comboboxField").value;
	if(tenThuoc == ""){
		document.getElementById(prefix_component + "DMTHUOC_MASO").value ="";
		document.getElementById(prefix_component + "DMTHUOC_MASO").focus();
	}
	var maThuoc = document.getElementById(prefix_component + "DMTHUOC_MASO").value;	
	document.getElementById(prefix_component + "DMTHUOC_MASO").value = maThuoc.toUpperCase();
	getDvt();
	myOnblurTextbox(prefix_component + "DMQUOCGIA_MASO", 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + "DTDMHANGSX_MA", 'DM_NHA_SAN_XUAT');
	tinhTuSoLuongVaThanhTien();
	document.getElementById(prefix_component + "__luongton").value = "";
	document.getElementById(prefix_component + "__luongtonmoi").value = "";
	document.getElementById(prefix_component + "__thanhtien_CT").value = "";
	document.getElementById(prefix_component + "__soluong_vi").value = "";
	document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE").value = "";
	document.getElementById(prefix_component + "__dongia_vi").value = "";
}

function setTonKho(){
	var tonHT = document.getElementById(prefix_component + "tonkho_hid").value;
	document.getElementById(prefix_component + "__luongton").value = parseFloat(tonHT);
	
	var soluongmoi = document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value; 
	soluongmoi =  soluongmoi.replace(/[,]/g, "");
	if (soluongmoi == null || soluongmoi == ''){
		soluongmoi = 0;
	}
	
	var tonkhotongcong = 0;
	tonkhotongcong = parseFloat(tonHT) + parseFloat(soluongmoi) ;
	document.getElementById(prefix_component + "__luongtonmoi").value = tonkhotongcong;
}

function setValueForTonKhoMoi(){
	var luongtoncu = document.getElementById(prefix_component + "__luongton").value;
	luongtoncu =  luongtoncu.replace(/[,]/g, "");
	if (luongtoncu == null || luongtoncu == ''){
		luongtoncu = 0;
	}
	
	var soluongmoi = document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value; 
	soluongmoi =  soluongmoi.replace(/[,]/g, "");
	if (soluongmoi == null || soluongmoi == ''){
		soluongmoi = 0;
	}
	
	var tonkhotongcong = 0;
	tonkhotongcong = parseFloat(luongtoncu) + parseFloat(soluongmoi) ;
	document.getElementById(prefix_component + "__luongtonmoi").value = tonkhotongcong;
	numberFormatBlur(document.getElementById(prefix_component + "__luongtonmoi"));
	document.getElementById(prefix_component + "__luongtonmoi").readOnly = true;
}

function testSoHoaDonNgayHoaDonFromServer(urlAction,myCondition){
   
  	
		 
	var xmlHttp = createXmlHttpRequest();    
	var url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	//alert("myCondition:"+myCondition);
    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	handlerReadyStateChangeForTestSoHoaDonNgayHoaDonFromServer(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 	
	  
}

function handlerReadyStateChangeForTestSoHoaDonNgayHoaDonFromServer(xmlHttp){

	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var dataJson = eval(jsonExpression);
		if ( dataJson.list != null && typeof(dataJson.list) == "object") {
		    var data = dataJson.list;
		    var i  = 0;
	  		if (data != null){
			  while (data.record[i] != null){            	
	                data1 = data.record[i];   
	               	var tontai =  data1.TonTai ;   
	               	if (tontai == 'true'){
	               		//canh bao
	               		var f = confirm(canhBaoSohoadonNgayhoadon);
						if (f == true){
							//do nothing
						}else{
							document.getElementById(prefix_component + "PHIEUNHAPKHO_SOHOADON").focus();
	               		
						}
						
	               	} 
	               	 
	               	 
						       
					i = 1;
					
	       
              }
              if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	         		
	                var tontai =  data1.TonTai ;   
	               	if (tontai == 'true'){
	               		//canh bao
	               		var f = confirm(canhBaoSohoadonNgayhoadon);
						if (f == true){
							//do nothing
						}else{
							document.getElementById(prefix_component + "PHIEUNHAPKHO_SOHOADON").focus();
	               		
						}
	               	} 
	                
	          }
	       }
	  }         
  }
}

function setValueOnLoad(){
	document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = "";
	myOnblurTextbox(prefix_component + "DMQUOCGIA_MASO", 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + "DTDMNOIBAN_MA", 'DM_NHA_CUNG_CAP');
	myOnblurTextbox(prefix_component + "DMKINHPHI_MASO", 'DM_NGUON_KINH_PHI');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_CN", 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_TIEPLIEU", 'DT_DM_NHAN_VIEN__2');
	//myOnblurTextbox(prefix_component + "DMTHUOC_MASO", 'DM_THUOC');
	myOnblurTextbox(prefix_component + "DTDMNGUON_MA", 'DM_NGUON_CHUONG_TRINH');
	myOnblurTextbox(prefix_component + "DMKHO_MASO", 'DT_DM_KHO');
	//myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
	myOnblurTextbox(prefix_component + "DTDMHANGSX_MA", 'DM_NHA_SAN_XUAT');
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
    setQuyCach();
    
    var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "true") {
		document.getElementById("divghinhan").style.display = "none";
		document.getElementById("divinphiep").style.display = "block";
		lockPhieuNhap(document.forms[0], 1);
	} else {
		document.getElementById("divghinhan").style.display = "block";
		document.getElementById("divinphiep").style.display = "none";
		lockPhieuNhap(document.forms[0], 0);
	}
	
	document.getElementById(prefix_component + "DTDMDONVITINH_MA_pk").value = "";
	document.getElementById(prefix_component + "__dvtTen").value = "";
	document.getElementById(prefix_component + "__dongia_vi").value = "";
	document.getElementById(prefix_component + "__soluong_vi").value = "";
	document.getElementById(prefix_component + "__thanhtien_CT").value = "";
	document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN").value = "";
	document.getElementById(prefix_component + "__thanhtienthue").value = "";
	document.getElementById(prefix_component + "__giabq").value = "";
	document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE").value = "";	
	document.getElementById(prefix_component + "DM_LOAI_THUOC").value = "";	
	document.getElementById(prefix_component + "PHIEUNHAPKHO_MA").focus();

}

function initShortcut() {
	shortcut.add('Ctrl+Shift+g',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__nhapmoi').click();});	
	shortcut.add('Ctrl+Shift+i',function() {document.getElementById(prefix_component + '__in').click();});	
}

function setQuyCach() {
	if (document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value == "") {
		document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value = "1";
		//donGia_vi_value();
		//soLuong_vi_value();
		thanhTien_CT_value();
	}
}

function getDvt() {	
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
}

function onCompleteThemCt() {
	thanhTien_thue_value();//@Trung
	//myOnblurTextbox(prefix_component + "DMTHUOC_MASO", 'DM_THUOC');
	myOnblurTextbox(prefix_component + "DMQUOCGIA_MASO", 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + "DTDMHANGSX_MA", 'DM_NHA_SAN_XUAT');
	document.getElementById(prefix_component + "DTDMDONVITINH_MA_pk").value = "";
	document.getElementById(prefix_component + "__dvtTen").value = "";
	document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = "";
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
	document.getElementById(prefix_component + "__dongia_vi").value = "";
	document.getElementById(prefix_component + "__soluong_vi").value = "";
	document.getElementById(prefix_component + "__thanhtien_CT").value = "";
	
	document.getElementById(prefix_component + "__giabq").value = "";
	document.getElementById(prefix_component + "__giacuoi").value = "";
	document.getElementById(prefix_component + "__luongton").value = "";
	document.getElementById(prefix_component + "__luongtonmoi").value = "";
	
	setQuyCach();
}

function onCompleteGhiNhan() {
	setQuyCach();
    
    var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "true") {
		document.getElementById("divghinhan").style.display = "none";
		document.getElementById("divinphiep").style.display = "block";
		lockPhieuNhap(document.forms[0], 1);
	} else {
		document.getElementById("divghinhan").style.display = "block";
		document.getElementById("divinphiep").style.display = "none";
		lockPhieuNhap(document.forms[0], 0);
	}
	document.getElementById(prefix_component + "DTDMDONVITINH_MA_pk").value = "";
	document.getElementById(prefix_component + "__dvtTen").value = "";
	document.getElementById(prefix_component + "__dongia_vi").value = "";
	document.getElementById(prefix_component + "__soluong_vi").value = "";
}

function onCompleteDeleteCt() {
	onCompleteThemCt();
}

function onCompleteLoadPN() {
	document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = "";
	myOnblurTextbox(prefix_component + "DMQUOCGIA_MASO", 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + "DTDMNOIBAN_MA", 'DM_NHA_CUNG_CAP');
	myOnblurTextbox(prefix_component + "DMKINHPHI_MASO", 'DM_NGUON_KINH_PHI');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_CN", 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_TIEPLIEU", 'DT_DM_NHAN_VIEN__2');
	//myOnblurTextbox(prefix_component + "DMTHUOC_MASO", 'DM_THUOC');
	myOnblurTextbox(prefix_component + "DTDMNGUON_MA", 'DM_NGUON_CHUONG_TRINH');
	myOnblurTextbox(prefix_component + "DMKHO_MASO", 'DT_DM_KHO');
	//myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
	myOnblurTextbox(prefix_component + "DTDMHANGSX_MA", 'DM_NHA_SAN_XUAT');
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
    setQuyCach();
    
    var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "true") {
		document.getElementById("divghinhan").style.display = "none";
		document.getElementById("divinphiep").style.display = "block";
		lockPhieuNhap(document.forms[0], 1);
	} else {
		document.getElementById("divghinhan").style.display = "block";
		document.getElementById("divinphiep").style.display = "none";
		lockPhieuNhap(document.forms[0], 0);
	}
	document.getElementById(prefix_component + "DTDMDONVITINH_MA_pk").value = "";
	document.getElementById(prefix_component + "__dvtTen").value = "";
	document.getElementById(prefix_component + "__dongia_vi").value = "";
	document.getElementById(prefix_component + "__soluong_vi").value = "";
	document.getElementById(prefix_component + "__thanhtien_CT").value = "";
}

function onCompleteSelectCt() {
	myOnblurTextbox(prefix_component + "DMQUOCGIA_MASO", 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + "DTDMHANGSX_MA", 'DM_NHA_SAN_XUAT');
	getDvt();
	
	document.getElementById(prefix_component + "DMTHUOC_MASO").focus();
	donGia_vi_value();
	soLuong_vi_value();
	thanhTien_CT_value();
	DonGiasauthue_CT();
	//setValueForGiaDauThau();
	//setValueForGiaCuoi();
	//setValueForTonKho();
	//setValueForTonKhoMoi();
	setTonKho();
}

function tinhTien() {
	/*
	var countCt = document.getElementById(prefix_component + "count").value;
	//alert("countCt: " + countCt);
	var temp = 0;
	for (var i = 0; i < countCt; i++) {
		var tt = parseFloat(document.getElementById(prefix_component + "__myTable:" + i + ":colTT").innerHTML);
		//alert("tt: " + tt);
		if (tt) {
			temp = temp + tt;
		}
	}
	document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN").value = temp;
	*/
}

function tinhTuSoLuongVaThanhTien() {
	
	
	var soluong = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	var thanhtien = parseFloat(formatRealNumber(document.getElementById(prefix_component + "__thanhtien_CT")));
	if (soluong != 0){
		var dongia = thanhtien/soluong;
		var dongiasauthue = 0;
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIA").value = dongia;
		numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
		
		var thue = parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_MUCTHUE")));
		dongiasauthue = dongia + dongia*thue/100;
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE").value = dongiasauthue;
		numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE"));
		
		// kiem tra so voi don gia dau thau
		var dongiadauthau = parseFloat(formatRealNumber(document.getElementById(prefix_component + "__giabq")));
		if (dongiasauthue <= dongiadauthau){
			
		}else{ // dongiasauthue > dongiadauthau
			var fGiaTriCanhBao = parseFloat(giaTriCanhBao);
			if (dongiasauthue - dongiadauthau >= giaTriCanhBao){
				alert(dongiavadongiadauthau);
			}
			
		}
	}
	document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE").readOnly = true;	
}
function DonGiasauthue_CT() {
	var thue = parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_MUCTHUE")));
	var dongia = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	var dongiasauthue = dongia + dongia*thue/100;
	document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE").value = dongiasauthue;
	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE"));	
	
	// kiem tra so voi don gia dau thau
	var dongiadauthau = parseFloat(formatRealNumber(document.getElementById(prefix_component + "__giabq")));
	if (dongiadauthau != null && dongiadauthau != "null" && dongiadauthau!=0){
		if (dongiasauthue <= dongiadauthau){
		
		}else{ // dongiasauthue > dongiadauthau
			var fGiaTriCanhBao = parseFloat(giaTriCanhBao);
			if (dongiasauthue - dongiadauthau >= giaTriCanhBao){
				alert(dongiavadongiadauthau);
			}
		}
	}
	document.getElementById(prefix_component + "CTNHAPKHO_DONGIASAUTHUE").readOnly = true;	
}

function thueGTGT_value() {
	
	
	if (document.getElementById(prefix_component + "PHIEUNHAPKHO_MUCTHUE").value == "") {
		document.getElementById(prefix_component + "PHIEUNHAPKHO_MUCTHUE").value = "0";
	}
	var thanhtien = parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN")));
	var thueGTGT_pt = parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE")));
	
	if(thanhtien && thueGTGT_pt){
		document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE").value = (thanhtien * thueGTGT_pt) / 100;
	} else {
		if (thueGTGT_pt == 0) {
			document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE").value = "0";
		} else {
			document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE").value = "";
		}
	}	
	
	//numberFormatBlur(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN"));
	numberFormatBlur(document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE"));
	
}
//
function thanhTien_thue_value(){
	//numberFormatFocus(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN"));
	var thanhtien =parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN")));
	var thueGTGT = parseFloat(formatRealNumber(document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE")));

	if(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN").value != "" && 
		document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE").value != "") {
		document.getElementById(prefix_component + "__thanhtienthue").value = thanhtien + thueGTGT;
	} else {
		document.getElementById(prefix_component + "__thanhtienthue").value = "";
		
	}
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtienthue"));
}

//
function donGia_vi_value() {
	//focus
	
	var dongia_vien = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	
	//alert("dongia_vien: " + dongia_vien);
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	if (dongia_vien) {
		document.getElementById(prefix_component + "__dongia_vi").value = dongia_vien * quycach;
	} else {
		document.getElementById(prefix_component + "__dongia_vi").value = "";
	}
	//numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
	numberFormatBlur(document.getElementById(prefix_component + "__dongia_vi"));
}
//
function donGia_vien_value() {
	var dgVi =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__dongia_vi")));
	var dongia_vien =   parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	if (dgVi && quycach && quycach > 0) {
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIA").value = dgVi / quycach;
	} else {
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIA").value = "";
	}
	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
}
//
function soLuong_vi_value() {
	var soluong_vien = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	
	if(soluong_vien  && quycach > 0){
		document.getElementById(prefix_component + "__soluong_vi").value = soluong_vien / quycach;
	} else {
		document.getElementById(prefix_component + "__soluong_vi").value = "";
	}
	
	numberFormatBlur(document.getElementById(prefix_component + "__soluong_vi"));
}
//
function soLuong_vien_value(){	
	var slVi = parseFloat(formatRealNumber(document.getElementById(prefix_component + "__soluong_vi")));
	var soluong_vien =parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	if(slVi && quycach ){
		document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value = slVi * quycach;
	} else {
		document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value = "";
	}

	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
}
//
function thanhTien_CT_value(){
	var TONKHO_DONGIA = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	var CTNHAPKHO_SOLUONG = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	if(TONKHO_DONGIA && CTNHAPKHO_SOLUONG) {
		document.getElementById(prefix_component + "__thanhtien_CT").value = TONKHO_DONGIA * CTNHAPKHO_SOLUONG;
	} else {
		document.getElementById(prefix_component + "__thanhtien_CT").value = "";
		//return;
	}
	
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtien_CT"));
}

function lockPhieuNhap(form, type) {
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
  		//var ten = elem[i].name;
  		var strType = elem[i].type.toLowerCase();
  
  		//alert('strID: ' + strID + " | name: " + ten + " | type: " + strType);
  		if(strID!=''){ //Bat loi khi co dojo
   			if (type == 1) {
    			if (strType == 'text' || strType == 'textarea') {
     				try{
      					document.getElementById(strID).disabled = true;
      					dijit.byId(strID).disabled = true;
     				} catch(e) {
      					document.getElementById(strID).disabled = false;
      					document.getElementById(strID).readOnly = true;
     				}        
     
    			} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
     				document.getElementById(strID).disabled = true;
    			}
   			} else if (type == 0){
    			if (strID == (prefix_component + "PHIEUNHAPKHO_THANHTIEN") || 
    				strID == (prefix_component + "PHIEUNHAPKHO_THUE") || 
    				strID == (prefix_component + "__thanhtienthue") || 
    				strID == (prefix_component + "DTDMDONVITINH_MA") ||
    				strID == (prefix_component + "DM_DON_VI_TINH")) {
    				
    			} else {
     				if (strType == 'text' || strType == 'textarea') {
      
      					try{
       						document.getElementById(strID).disabled = false;
       						dijit.byId(strID).disabled = false;
      					} catch(e) {
       						document.getElementById(strID).readOnly = false;
      					}        
      
     				} else if (strType == 'checkbox' || strType == 'radio' || strType == 'select-one') {
      					document.getElementById(strID).disabled = false;
     				} 
     			}
   			}
  		}
 	} 
}



function validatethang(){
			var thang = parseInt(document.getElementById(prefix_component + "__handung").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			if(thang > 12 || thang == 0){
				alert("Tháng phải lớn hơn 0 và nhỏ hơn hoặc bằng 12");		
				document.getElementById(prefix_component + "__handung").focus();
				return false;
			}else{
				var a = validatenam();
				//if(a==false) document.getElementById(prefix_component + "__handung").focus();
				return a;
			}
}

function validatenam(){
			var thang = parseInt(document.getElementById(prefix_component + "__handung").value,10);
			var nam = parseInt(document.getElementById(prefix_component + "__namhandung").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			var namhientai = parseInt(ngayhientai.substring(ngayhientai.length-4, ngayhientai.length),10);
			if(nam < namhientai){
				alert("Năm phải lớn hơn hoặc bằng " + namhientai);		
				document.getElementById(prefix_component + "__namhandung").focus();	
				return false;
			}
			else if(thang < thanghientai && nam == namhientai){
				alert("Tháng/năm phải lớn hơn hoặc bằng " + thanghientai + "/" +  namhientai);
				document.getElementById(prefix_component + "__handung").focus();
				return false;
			
			}
			
			return true;
}
		
		
