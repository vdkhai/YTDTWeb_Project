var isUpdate = false;

function init() {

	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');
			setAttrForCombobox(prefix_component + "DTDMLOAI_MA", "LOAI_span", "DM_LOAI_THUOC", "getDmLoaiThuoc()", "", "", "");
            
            setTimeout(function () {
            	setValueOnLoad();
            }, 200);
            initShortcut();
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
    
}
function testSoHoaDonNgayHoaDon(){
	var sohoadon = document.getElementById(prefix_component + "PHIEUNHAPKHO_SOHOADON").value;
	var ngayhoadon = document.getElementById(prefix_component + "PHIEUNHAPKHO_NGAYHOADON").value;
	if (sohoadon != '' && ngayhoadon != ''){
		testSoHoaDonNgayHoaDonFromServer("KiemTraSoHoaDonNgayHoaDonAction",sohoadon+","+ngayhoadon);
	}
	
	
}
function setValueOnLoad(){
	myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
}

function initShortcut() {
	shortcut.add('Ctrl+Shift+t',function() {document.getElementById(prefix_component + '__timkiem').click();});
}


function onCompleteLoadPN() {
	myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
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
	//numberFormatBlur(document.getElementById(prefix_component + "PHIEUNHAPKHO_THUE"));
	//numberFormatBlur(document.getElementById(prefix_component + "PHIEUNHAPKHO_THANHTIEN"));
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
	//numberFormatFocus(document.getElementById(prefix_component + "__dongia_vi"));
	//numberFormatFocus(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
	
	var dgVi =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__dongia_vi")));
	var dongia_vien =   parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	if (dgVi && quycach && quycach > 0) {
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIA").value = dgVi / quycach;
	} else {
		document.getElementById(prefix_component + "CTNHAPKHO_DONGIA").value = "";
	}
	//numberFormatBlur(document.getElementById(prefix_component + "__dongia_vi"));
	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
}
//
function soLuong_vi_value() {
	//numberFormatFocus(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
	
	var soluong_vien = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	
	if(soluong_vien  && quycach > 0){
		document.getElementById(prefix_component + "__soluong_vi").value = soluong_vien / quycach;
	} else {
		document.getElementById(prefix_component + "__soluong_vi").value = "";
	}
	
	//numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
	numberFormatBlur(document.getElementById(prefix_component + "__soluong_vi"));
}
//
function soLuong_vien_value(){
	//numberFormatFocus(document.getElementById(prefix_component + "__soluong_vi"));
	//numberFormatFocus(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
	
	var slVi = parseFloat(formatRealNumber(document.getElementById(prefix_component + "__soluong_vi")));
	var soluong_vien =parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	var quycach = parseInt(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH").value);
	if(slVi && quycach ){
		document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value = slVi * quycach;
	} else {
		document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG").value = "";
	}
	
	//numberFormatBlur(document.getElementById(prefix_component + "__soluong_vi"));
	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
}
//
function thanhTien_CT_value(){
	//numberFormatFocus(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
	//numberFormatFocus(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
	
	var TONKHO_DONGIA = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA")));
	var CTNHAPKHO_SOLUONG = parseFloat(formatRealNumber(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG")));
	if(TONKHO_DONGIA && CTNHAPKHO_SOLUONG) {
		document.getElementById(prefix_component + "__thanhtien_CT").value = TONKHO_DONGIA * CTNHAPKHO_SOLUONG;
		//thueGTGT_value();@Trung 21/4
		//thanhTien_thue_value();@Trung 21/4
	} else {
		document.getElementById(prefix_component + "__thanhtien_CT").value = "";
		//return;
	}
	
	//numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_DONGIA"));
	//numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_SOLUONG"));
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtien_CT"));
	/*
	numberFormatBlur(document.getElementById(prefix_component + "CTNHAPKHO_QUYCACH"));
	numberFormatBlur(document.getElementById(prefix_component + "__dongia_vi"));
	numberFormatBlur(document.getElementById(prefix_component + "__soluong_vi"));
	
	*/
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
    				strID == (prefix_component + "__thanhtien_CT") ||
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

	
		
