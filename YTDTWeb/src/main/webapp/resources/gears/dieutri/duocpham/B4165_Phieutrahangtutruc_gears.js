function init() {
	
    if (window.google && google.gears) {
    	
        try {	setAttrForCombobox(prefix_component + "NGUOINHAP_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien_Duoc()","","","");
            	setAttrForCombobox(prefix_component + "KHONHAP_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");
				setAttrForCombobox(prefix_component + "KHOATRA_MA","DM_KHOA__1_span","DM_KHOA__1","getDmKhoa()","","","");
				setAttrForCombobox(prefix_component + "NGUOILAP_MA","DT_DM_NHAN_VIEN__1_span","DT_DM_NHAN_VIEN__1","getDtDmNhanVien_Duoc()","","","");
				setAttrForCombobox(prefix_component + "NGUOIKY_MA","DT_DM_NHAN_VIEN__2_span","DT_DM_NHAN_VIEN__2","getDtDmNhanVien_Duoc()","","","");
				timer.setTimeout(function(){focusInit();},0);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById(prefix_component + "__phieudutru").focus();
	blockInphieu();
}

function displayPhieuDuTruOnComplete(){
	if (document.getElementById(prefix_component + "nofoundPDT").value == "true") {
		document.getElementById(prefix_component + "nofoundPDT").value = "false";
		document.getElementById(prefix_component + "__phieudutru").value = "";
		document.getElementById(prefix_component + "__phieudutru").focus();
		return;
	}
	myOnblurTextbox(prefix_component + "KHONHAP_MA", "DM_KHOA");
	myOnblurTextbox(prefix_component + "KHOATRA_MA", "DM_KHOA__1");
	document.getElementById(prefix_component + "NGUOILAP_MA").focus();
	blockInphieu();
	
}

function loadInfoChitiet(){
	//myOnblurTextbox(prefix_component + "__maso","DM_THUOC");
	document.getElementById(prefix_component + "__xuat").value="";
	document.getElementById(prefix_component + "__xuat").focus();
}

function tinhThanhTienChiTiet(){
	var soluong = document.getElementById(prefix_component + "__xuat").value;
	var dongia = document.getElementById(prefix_component + "__dongia").value;
	document.getElementById(prefix_component + "__thanhtien").value = dongia*soluong;	
}

function enterOnComplete(){
	//document.getElementById("DM_THUOC").value="";
}

function resetAfterNhapMoi(){
	document.getElementById("DT_DM_NHAN_VIEN").value="";
	document.getElementById("DM_KHOA").value="";
	document.getElementById("DM_KHOA__1").value="";
	document.getElementById("DM_NGUON_KINH_PHI").value="";
	document.getElementById("DM_NGUON_CHUONG_TRINH").value="";
	document.getElementById("DT_DM_NHAN_VIEN__1").value="";
	document.getElementById("DT_DM_NHAN_VIEN__2").value="";
	blockInphieu();
	
	document.getElementById(prefix_component + "__maphieu").focus();
	lockUnlockControlB4113(document.forms[0],0);
	
}

function blockInphieu() {
	
	if (document.getElementById(prefix_component + "hid_hienThiInPhieu").value != '') {
		document.getElementById("__divIn").style.display = "block";
	} else {
		document.getElementById("__divIn").style.display = "none";
	}
	
	if (document.getElementById(prefix_component + "hid_hienThiGhiNhan").value != '') {
		document.getElementById("__divGhiNhan").style.display = "block";
	} else {
		document.getElementById("__divGhiNhan").style.display = "none";
	}		
}

function displayAfterGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		return;
	}
	blockInphieu();
	document.getElementById(prefix_component + "__inphieu").focus();
	lockUnlockControlB4113(document.forms[0],1);
}

var lock = false;

function checkAddChiTiet(){
	var result = true;
	if (lock) {
		result = false;
	}
	if (result){
		var soluong = document.getElementById(prefix_component + "__soluong").value;
		var tra = document.getElementById(prefix_component + "__xuat").value;
		if (parseInt(tra) > parseInt(soluong)) {
			alert("Số lượng trả lại phải nhỏ hơn hoặc bằng số lượng đang có");
			document.getElementById(prefix_component + "__xuat").value = "";			
			document.getElementById(prefix_component + "__xuat").focus();
			result = false;			
		}
	}
	return result;
}

function checkDelChiTiet(){
	var result = true;
	if (lock) {
		result = false;
	}
	return result;
}

function displayPhieuTraKho(){
	blockInphieu();
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__maphieu").value = "";
		return;
	} 
	myOnblurTextbox(prefix_component + "NGUOINHAP_MA", "DT_DM_NHAN_VIEN");
	myOnblurTextbox(prefix_component + "KHONHAP_MA", "DM_KHOA");
	myOnblurTextbox(prefix_component + "KHOATRA_MA", "DM_KHOA__1");
	myOnblurTextbox(prefix_component + "NGUOILAP_MA", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "NGUOIKY_MA", "DT_DM_NHAN_VIEN__2"); 
	//document.getElementById("DM_THUOC").value = "";
	document.getElementById(prefix_component + "__soluong").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";	
	displayAfterGhiNhan();
}

function lockUnlockControlB4113(form, type) {   
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
  		var strType = elem[i].type.toLowerCase();
  		if(strID!='' && strID!='LOAIPHIEU_MA' && strID!='DM_LOAI_THUOC' && strID!='PHANLOAI_MA' && strID!='DM_PHAN_LOAI_THUOC' && strID!='KHONHAP_MA' && strID!='DM_KHOA' && strID!='KHOATRA_MA' && strID!='DM_KHOA__1'){ //Bat loi khi co dojo
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
    			lock = true;
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
    			  lock = false; 
   			}
  		}
 	} 
}