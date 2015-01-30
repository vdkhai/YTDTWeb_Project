function init() {
    if (window.google && google.gears) {
        try {
        	//db = google.gears.factory.create('beta.database');
            //setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","",""); 
           	setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");  
           	setAttrForCombobox(prefix_component + "__mabacsi","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
            //setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");  
            setAttrForCombobox(prefix_component + "BANKHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","","");
            setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");
            setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen()","","","");
            setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa()","","","");
            //setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
            //setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");
            onCompleteDisplay();
            document.getElementById(prefix_component + "__maphieu").focus();
        } catch (e) {
        	alert("init error: " + e.description);
        }
	}
}

function onCompleteDisplay() {
	var maBanKham = document.getElementById(prefix_component + "BANKHAM_MA");
	var maTiepDon = document.getElementById(prefix_component + "__matiepdon");
	if (maBanKham.value == "") {
		maBanKham.focus();
		//return;
	}
	myOnblurTextbox(prefix_component + "TINH_MA", 'DM_TINH');
	myOnblurTextbox(prefix_component + "HUYEN_MA", 'DM_HUYEN');
	myOnblurTextbox(prefix_component + "XA_MA", 'DM_XA');
	myOnblurTextbox(prefix_component + "__madoituong", 'DM_DOI_TUONG');
	myOnblurTextbox(prefix_component + "__madantoc", 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + "TINH_MA", 'DM_TINH');
	myOnblurTextbox(prefix_component + "BANKHAM_MA", 'DT_DM_BAN_KHAM');
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	if (donViTuoi == "1") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	} else if (donViTuoi == "2") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	} else if (donViTuoi == "3") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
	} else {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
	tinhTien();
}

function onCompleteReset() {
	document.forms[0].reset();
}

function onCompleteGhiNhan() {
	
}

function tinhTien() {
    /*
	var countCt = document.getElementById(prefix_component + "count").value;
	//alert("count: " + countCt);
	var ttCt = 0;
	for (var i = 0; i < countCt; i++) {
		var tt = document.getElementById(prefix_component + "listTpk:" + i + ":thanhtien").innerHTML;
		//alert("thanh tien: " + tt);
		ttCt = ttCt + parseInt(tt);
		//alert("tong tien: " + temp);
	}
	var congKham = document.getElementById(prefix_component + "__congkham").value;
	var mienGiam = document.getElementById(prefix_component + "__miengiam").value;
	var thatThu = document.getElementById(prefix_component + "__thatthu").value;
	document.getElementById(prefix_component + "__thanhtien1").value = ttCt + congKham - mienGiam - thatThu;
	var bnTra = document.getElementById(prefix_component + "__perbntra").value;
	document.getElementById(prefix_component + "__bntra").value = bnTra * document.getElementById(prefix_component + "__thanhtien1").value;
	*/
}

function lockUnlockControl(form, type) {
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

