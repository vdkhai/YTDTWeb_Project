var tutruc_pdtValue = "0";

function init() {
    if (window.google && google.gears) {
        try {        	        
            //setAttrForComboboxForNoDB(prefix_component + '__listtonkho_duocpham_ma','__listtonkho_span', '__listtonkho_duocpham',"","onBlurThuoc(); document.getElementById(prefix_component + '__soluong').focus();","statusEnterAnhTabForChon = 0;myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_duocpham_ma\",\"__listtonkho_duocpham\");onBlurThuoc(); document.getElementById(prefix_component + '__soluong').focus(); ","statusEnterAnhTabForChon = 1");
        	setAttrForCombobox(prefix_component + 'DM_QUOC_GIA_MA','DM_QUOC_GIA_span','DM_QUOC_GIA',"getDmQuocGia()","","","");
            setAttrForCombobox(prefix_component + 'DM_NHA_SAN_XUAT_MA','DM_NHA_SAN_XUAT_span','DM_NHA_SAN_XUAT',"getDmNhaSanXuat()","","","");  
            setAttrForCombobox(prefix_component + 'DM_BAI_THUOC_MA','DM_BAI_THUOC_span','DM_BAI_THUOC',"getDmBaiThuoc()","myOnblurCombobox('" + prefix_component + "DM_BAI_THUOC_MA','DM_BAI_THUOC');","","");
            setAttrForComboboxNoOnBlurCombobox(prefix_component + 'DT_DM_CHI_DAN_MA','DT_DM_CHI_DAN_span',  'DT_DM_CHI_DAN',"getDtDmChiDan()","","","");
            //setAttrForCombobox(prefix_component + 'DT_DM_CHI_DAN_MA','DT_DM_CHI_DAN_span',  'DT_DM_CHI_DAN',"getDtDmChiDan()","","","");
            
            document.getElementById(prefix_component + '__nhapphanso').value = false;
            document.getElementById(prefix_component + "__soluong").style.display = "block";
    		document.getElementById(prefix_component + "__sochia").style.display = "none";
    		document.getElementById(prefix_component + "dauchia").style.display = "none";
    		document.getElementById(prefix_component + "__sobichia").style.display = "none";
    		document.getElementById(prefix_component + "__dvt").style.display = "block";
    		
    		displayChiDanFields();
    		//Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
            initshorcut();
            timer.setTimeout(function(){myOnLoad();},100); 
        } catch (e) {
        	alert("init():"+e.description);
        }
    }
    
}

function initshorcut(){
    shortcut.add("Ctrl+A", function() {
        document.getElementById(prefix_component + "__themchinhsau").onclick();
    },{'type':'keydown',"propagate":false,'target':document});

    shortcut.add("Ctrl+C", function() {
    	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
    },{'type':'keydown',"propagate":false,'target':document});
    
    shortcut.add("Ctrl+1", function() {
        document.getElementById(prefix_component + "__nhapphanso").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function setSoBenhAnFocus(){
	var khoaMa = document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value;
	if(khoaMa == ""){
		document.getElementById(prefix_component + "THUOCNOITRU_KHOA").focus();
	}else{
		document.getElementById(prefix_component + "HSBA_SOVAOVIEN").focus();
	}	
}

function showPhanSoFields(chkbox){
	var displayPhanso = chkbox.checked;	
	document.getElementById(prefix_component + "__phanso_hid").value = displayPhanso;
	displayPhansoFields();
}

function displayPhansoFields(){
	document.getElementById(prefix_component + "__sochia").value ="";
	document.getElementById(prefix_component + "__sobichia").value ="";
	var displayPhanso = document.getElementById(prefix_component + "__phanso_hid").value;
	if(displayPhanso == "true"){
		document.getElementById(prefix_component + "__soluong").style.display = "none";
		document.getElementById(prefix_component + "__sochia").style.display = "block";
		document.getElementById(prefix_component + "dauchia").style.display = "block";
		document.getElementById(prefix_component + "__sobichia").style.display = "block";
	}else{
		document.getElementById(prefix_component + "__soluong").style.display = "block";
		document.getElementById(prefix_component + "__sochia").style.display = "none";
		document.getElementById(prefix_component + "dauchia").style.display = "none";
		document.getElementById(prefix_component + "__sobichia").style.display = "none";
	}
	document.getElementById(prefix_component + "__dvt").style.display = "block";
}

function displayChiDanFields(){
	document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value ="";
	document.getElementById('DT_DM_CHI_DAN').value = "";
	document.getElementById(prefix_component + 'DT_DM_CHI_DAN_MA').value ="";
	if (document.getElementById(prefix_component + "tutrucPdt").value == "2") {
		document.getElementById("chidanDiv").style.display = "block";
	}else{
		document.getElementById("chidanDiv").style.display = "none";
	}
}

function setFocus_soluong(){
	var displayPhanso = document.getElementById(prefix_component + "__phanso_hid").value;
	displayPhansoFields();
	if(displayPhanso == "true"){
		document.getElementById(prefix_component + '__sochia').focus();
	}else{
		document.getElementById(prefix_component + '__soluong').focus();
	}
}

function setFocus_Mathuoc(){
	var mathuoc = document.getElementById(prefix_component + 'DM_THUOC_MASO').value;
	if(mathuoc == null || mathuoc == ""){
		document.getElementById(prefix_component + 'DM_THUOC_MASO').focus();
	}
}

function setSoluongField(){
	var sochia = document.getElementById(prefix_component + "__sochia").value;
	var sobichia = document.getElementById(prefix_component + "__sobichia").value;
	if(sochia == "" || sobichia == "" || sobichia == "0"){
		alert("Vui lòng nhập số lượng thuốc cấp cho bệnh nhân chính xác.");
		changeCursorDefault ();
		document.getElementById(prefix_component + "__sobichia").value = "";
		document.getElementById(prefix_component + "__sochia").focus();
	}else{
		var soluong = sochia/sobichia;	
		document.getElementById(prefix_component + "__soluong").value = soluong;
	}
}

function displayInPhieu(){
	if (document.getElementById(prefix_component + "tutrucPdt").value == "2") {
		document.getElementById("divInphieu").style.display = "block";
	}else{
		document.getElementById("divInphieu").style.display = "none";
	}
}

function checkBeforeUpdateChiTietBaithuoc(){
	var tenbaithuoc = document.getElementById("DM_BAI_THUOC").value;
	if (tenbaithuoc == null || tenbaithuoc== ""){
	  alert("Vui lòng chọn bài thuốc.");
	  changeCursorDefault ();
	  document.getElementById("DM_BAI_THUOC").focus();
	  return false;
	}
	//Kiem tra, neu chon tu truc thi khi add thuoc xuong luoi se thong bao chon khoa 
	if (document.getElementById(prefix_component + "tutrucPdt").value == "1") {
		if(document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value ==""){
			alert("Vui lòng chọn khoa điều trị.");
			document.getElementById(prefix_component + "THUOCNOITRU_KHOA").focus();
			return false;
		}
	}
	return true;
}

function checkBeforeUpdateChiTiet(){
	document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value = document.getElementById( 'DT_DM_CHI_DAN').value;
	changeCursorWait ();
	var maThuoc = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
	if (maThuoc == null || maThuoc == ""){
	   alert("Vui lòng nhập mã thuốc.");
	   changeCursorDefault ();
	   document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	   return false;
	}var ton = document.getElementById(prefix_component + "__tonkho").value;
	var displayPhanso = document.getElementById(prefix_component + "__phanso_hid").value;
	if(displayPhanso == "true"){
		var sochia = document.getElementById(prefix_component + "__sochia").value;
		var sobichia = document.getElementById(prefix_component + "__sobichia").value;
		
		if(sochia == "" || sobichia == ""){
			alert("Vui lòng nhập số lượng thuốc cấp cho bệnh nhân chính xác.");
			changeCursorDefault ();
			document.getElementById(prefix_component + "__sochia").focus();
			return false;
		}
		var soluong1 = sochia/sobichia;
		document.getElementById(prefix_component + "__soluong").value = soluong1;
		
		var soluong = document.getElementById(prefix_component + "__soluong").value;
		var mathuoc = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
		if(mathuoc != "" && soluong == ""){
			alert("Vui lòng nhập số lượng thuốc cấp cho bệnh nhân chính xác.");
			changeCursorDefault ();
			document.getElementById(prefix_component + "__sochia").focus();
			return false;
		}
		if (parseInt(soluong) > parseInt(ton)){
			alert("Số lượng cấp phải nhỏ hơn hoặc bằng tồn.");
			changeCursorDefault ();
			document.getElementById(prefix_component + "__sochia").select();
			document.getElementById(prefix_component + "__sochia").focus();
			return false;	
		}
	}else{
		var soluong = document.getElementById(prefix_component + "__soluong").value;
		var mathuoc = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
		if(mathuoc != "" && soluong == ""){
			alert("Vui lòng nhập số lượng thuốc cấp cho bệnh nhân chính xác.");
			changeCursorDefault ();
			document.getElementById(prefix_component + "__soluong").focus();
			return false;
		}
		if (parseInt(soluong) > parseInt(ton)){
			var doituongMa = document.getElementById(prefix_component + "__doituongHid").value;
			if(doituongMa != "" && doituongMa != "TP"){
				alert("Số lượng cấp phải nhỏ hơn hoặc bằng tồn.");
				changeCursorDefault ();
				document.getElementById(prefix_component + "__soluong").select();
				document.getElementById(prefix_component + "__soluong").focus();
				return false;	
			}
		}
	}
	return true;
}

function onCompleteThemChinhSau(){	
	document.getElementById('DM_QUOC_GIA_2').value="";
	document.getElementById('DM_NHA_SAN_XUAT_2').value="";
	document.getElementById(prefix_component + "__tonkho").value ="";
	document.getElementById(prefix_component + '__sochia').value="";
	document.getElementById(prefix_component + '__sobichia').value="";
	document.getElementById(prefix_component + "__phanso_hid").value ="false";
	document.getElementById(prefix_component + "__nhapphanso").checked =false;
	document.getElementById('DT_DM_CHI_DAN').value="";
	displayPhansoFields();
}

function resetValueAddBaiThuoc(){
	highlightOnFocus();
	document.getElementById(prefix_component + "DM_BAI_THUOC").focus();
}

function setValueForNSXHSX(){
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA").value = document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA_HIDDEN").value;
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value= document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value;
	myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	document.getElementById('DM_QUOC_GIA_2').value = document.getElementById('DM_QUOC_GIA').value;
	document.getElementById("DM_NHA_SAN_XUAT_2").value = document.getElementById("DM_NHA_SAN_XUAT").value;	
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value;
}

function resetInfo(){
	document.getElementById('DM_QUOC_GIA_2').value = "";
	document.getElementById("DM_NHA_SAN_XUAT_2").value = "";
	
	var NDM = document.getElementById(prefix_component + "__ndm_hid").value;
	var mien = document.getElementById(prefix_component + "__mien_hid").value;
	if (NDM == "true"){   		
   		document.getElementById(prefix_component + "__ndm").checked = true;
    }else{
   		document.getElementById(prefix_component + "__ndm").checked = false;
    }
    if (mien == "true"){   		
   		document.getElementById(prefix_component + "__mien").checked = true;
    }else{
   		document.getElementById(prefix_component + "__mien").checked = false;
    }
	
	var checkKiemKeHid = document.getElementById(prefix_component + "checkKiemKeHid").value;
	if(checkKiemKeHid == "true"){
		alert("Kho thuốc đang kiểm kê. Không được phép xuất thuốc cho bệnh nhân.");
		document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";
		document.getElementById(prefix_component + "DM_THUOC").value = "";
		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	}else{
		var ton = document.getElementById(prefix_component + "__tonkho").value;
		if(ton > 0){
			setFocus_soluong();			
		}else{
			var doituongMa = document.getElementById(prefix_component + "__doituongHid").value;
			if(document.getElementById(prefix_component + "tutrucPdt").value == "2"){//xuat vien
				if(doituongMa == "" || doituongMa == "TP"){
					setFocus_soluong();	
				}else{//khac thu phi thi bao loi
					alert("Số lượng tồn kho đã hết.");
					document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";
					document.getElementById(prefix_component + "DM_THUOC").value = "";
					document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
				}				
			}else{
				alert("Số lượng tồn kho đã hết.");
				document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";
				document.getElementById(prefix_component + "DM_THUOC").value = "";
				document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
			}
		}
	} 
	document.getElementById('DT_DM_CHI_DAN').value="";
}

function resetInfoChiDan(){
	//alert("resetInfoChidan");
	document.getElementById(prefix_component + "__themchinhsau").focus();
}

function myOnLoad(){
	
	setValueOnLoad();
	//setDefaultValueForBacSi();

	var chuyenTuManHinhThuocCLS = document.getElementById(prefix_component + "_hidFocusDochuyenTuManHinhThuocCLS").value;
		if (chuyenTuManHinhThuocCLS == "true"){
			document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
		}else{
			document.getElementById(prefix_component + "THUOCNOITRU_KHOA").focus();
		}
		document.getElementById(prefix_component + "DT_DM_CHI_DAN_MA").value = "";
}

function setDefaultValueForBacSi(){
  if (document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value == null || document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value ==""){
  	var khoaClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B3111_1_thuocydungcusudung_khoa");
	if (khoaClientDefault) {		
		document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value = khoaClientDefault.Ten;
	}
  }	
}

function luuTruGiaTriClientDefault(){
	
		var giaTriKhoaMa = document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriKhoaMa != null && giaTriKhoaMa != ""){
			var khoaClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B3111_1_thuocydungcusudung_khoa");
			//alert("khoaClientDefault:"+khoaClientDefault);
			if (khoaClientDefault == null || khoaClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121201;
				chValues[1] = "B3111_1_thuocydungcusudung_khoa";
				chValues[2] = document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriKhoaMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B3111_1_thuocydungcusudung_khoa", chNames,chValues );
				
			}    	
		}
}	
function setValueOnLoad(){	
	
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	//document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value = "";
	//document.getElementById("__listtonkho_duocpham").value = "";
	try {
		if (document.getElementById(prefix_component + "tutrucPdt").value == "1") {
			document.getElementById(prefix_component + "__phieudutru_tutruc:1").checked = true;
			document.getElementById("divInphieu").style.display = "none";
		} else if (document.getElementById(prefix_component + "tutrucPdt").value == "0"){
			document.getElementById(prefix_component + "__phieudutru_tutruc:0").checked = true;
			document.getElementById("divInphieu").style.display = "none";
		}else{
			document.getElementById(prefix_component + "__phieudutru_tutruc:2").checked = true;
			document.getElementById("divInphieu").style.display = "block";
		}
	} catch (e) {
	
	}
		
	if (document.getElementById(prefix_component + "__ndm_hid").value == "true"){		   
		document.getElementById(prefix_component + "__ndm").checked = true;
	}else{
		document.getElementById(prefix_component + "__ndm").checked = false;
	}
		
	if (document.getElementById(prefix_component + "__mien_hid").value == "true"){		   
		document.getElementById(prefix_component + "__mien").checked = true;
	}else{
		document.getElementById(prefix_component + "__mien").checked = false;
	}	
	document.getElementById(prefix_component + "__yc").checked = false;
	document.getElementById(prefix_component + "__khongThu").checked = false;
	document.getElementById("DM_QUOC_GIA_2").value = "";
	document.getElementById("DM_NHA_SAN_XUAT_2").value = "";
	document.getElementById(prefix_component + "DT_DM_CHI_DAN_MA").value = "";
	document.getElementById('DT_DM_CHI_DAN').value="";
	tinhTien();
	
	document.getElementById(prefix_component + '__nhapphanso').value = false;
    document.getElementById(prefix_component + "__soluong").style.display = "block";
	document.getElementById(prefix_component + "__sochia").style.display = "none";
	document.getElementById(prefix_component + "dauchia").style.display = "none";
	document.getElementById(prefix_component + "__sobichia").style.display = "none";
	document.getElementById(prefix_component + "__dvt").style.display = "block";
	
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
}

function onCompleteThemCt() {
	document.getElementById(prefix_component + "__tonkho").value = document.getElementById(prefix_component + "__tonkho_hid").value;
	if (document.getElementById(prefix_component + "__ndm_hid").value == "true"){		   
		document.getElementById(prefix_component + "__ndm").checked = true;
	}else{
		document.getElementById(prefix_component + "__ndm").checked = false;
	}
		
	if (document.getElementById(prefix_component + "__mien_hid").value == "true"){		   
		document.getElementById(prefix_component + "__mien").checked = true;
	}else{
		document.getElementById(prefix_component + "__mien").checked = false;
	}
	
	if (document.getElementById(prefix_component + "tutrucPdt").value == "1") {
		document.getElementById(prefix_component + "__phieudutru_tutruc:1").checked = true;
	} else if (document.getElementById(prefix_component + "tutrucPdt").value == "0"){
		document.getElementById(prefix_component + "__phieudutru_tutruc:0").checked = true;
	}else{
		document.getElementById(prefix_component + "__phieudutru_tutruc:2").checked = true;
	}
	tinhTien();
}

function onBlurThuoc() {
	var val = document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value;
	//alert(val);
	if (val != null && val != "" && val != "undefined") {
		var arrVal = val.split("___");
		document.getElementById(prefix_component + "__tonkhoma_hid").value = arrVal[0];
		document.getElementById(prefix_component + "__tonkho_hid").value = arrVal[1];	
		document.getElementById(prefix_component + "__tonkho").value = arrVal[1];
		document.getElementById(prefix_component + "tntLoai").value = arrVal[2];
		
		var MP= arrVal[3];
	   	if (MP == "true") {
	   		document.getElementById(prefix_component + "__mien").checked = true;
	   		document.getElementById(prefix_component + "__mien_hid").value = true;
	   	} else {
	   		document.getElementById(prefix_component + "__mien").checked = false;
	   		document.getElementById(prefix_component + "__mien_hid").value = false;
	   	}
		var YC = arrVal[4];
	   	//if (YC == "true") {
	   	//	document.getElementById(prefix_component + "__yc").checked = true;
	   	//} else {
	   	//	document.getElementById(prefix_component + "__yc").checked = false;
	   	//}
	   	
	   	var NDM = arrVal[5];
	  	if (NDM == "true") {
	   		document.getElementById(prefix_component + "__ndm").checked = true;
	   		document.getElementById(prefix_component + "__ndm_hid").value = true;
	   	} else {
	   		document.getElementById(prefix_component + "__ndm").checked = false;
	   		document.getElementById(prefix_component + "__ndm_hid").value = false;
	   	}
	   
	   var KhongThu = arrVal[6];
	   
	   //if (KhongThu == "true"){
	   //		document.getElementById(prefix_component + "__khongThu").checked = true;
	   //}else{
	   //		document.getElementById(prefix_component + "__khongThu").checked = false;
	   //}
	   
	   document.getElementById(prefix_component + "__malk").value = arrVal[7];
	} else {
		document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
		document.getElementById(prefix_component + "__tonkho_hid").value = "";
		document.getElementById(prefix_component + "__tonkho").value = "";
		document.getElementById(prefix_component + "tntLoai").value = "";
		document.getElementById("__listtonkho_duocpham").value = "";
		//alert(val);
		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	}
}

function tinhTien() {
	/*
	var countCt = document.getElementById(prefix_component + "__count").value;
	var temp = 0;
	for (var i = 0; i < countCt; i++) {
		var tt = document.getElementById(prefix_component + "__myTable:" + i + ":colTT").innerHTML;
		temp = temp + parseFloat(tt);
		//alert("tong tien: " + temp);
	}
	document.getElementById(prefix_component + "__tongtien").value = temp;
	*/
}

function clearListTonkho(){	
//    var search = dijit.byId("__listtonkho_duocpham");
//    var jsonData = { identifier: "id", items: [], label: "title" };
//    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
//    CatalogStore.newItem({id: "" , title: "" });
//	search.store = CatalogStore;
//	// Clear listtonkho textfile				
//	document.getElementById("__listtonkho_duocpham").value = "";
//	document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value = "";
	
	//reset all data
	document.getElementById(prefix_component + "__tonkho").value = "";
	//document.getElementById(prefix_component + "__soluong").value =  "";
	document.getElementById(prefix_component + "__yc").checked = false;
	document.getElementById(prefix_component + "__ndm").checked = false;
	document.getElementById(prefix_component + "__ndm_hid").value = false;
	document.getElementById(prefix_component + "__mien").checked = false;
	document.getElementById(prefix_component + "__mien_hid").value = false;
	document.getElementById(prefix_component + "__khongThu").checked = false;
}

function checkKhoNoiTru(){
	if (document.getElementById(prefix_component + "__phieudutru_tutruc:1").checked) {
		if(document.getElementById(prefix_component + "THUOCNOITRU_KHOA_pk").value == "" || document.getElementById(prefix_component + "THUOCNOITRU_KHOA_pk").value == null){
			alert("Vui lòng chọn khoa điều trị Nội trú");
			document.getElementById(prefix_component + "THUOCNOITRU_KHOA").focus();
			return;
		}
	} 
}

function getThuoc() {
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
    var xml;
    var data;
    
    clearListTonkho();
    
    if (document.getElementById(prefix_component + "DM_THUOC_MASO") != "") {
    	//0: PDT
    	//1: Tu truc
    	var khoamaValue = 0;
    	if (document.getElementById(prefix_component + "__phieudutru_tutruc:0").checked) {
    		tutruc_pdtValue = "0";
    	} else {
    		tutruc_pdtValue = "1";
    		khoamaValue = document.getElementById(prefix_component + "THUOCNOITRU_KHOA_pk").value;
    		if(khoamaValue == null || khoamaValue == ""){
    			return;
    		}
    	}
    	var tenHang = document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value;
    	if(tenHang == ""){
    		document.getElementById(prefix_component + "DM_THUOC_MASO").value ="";
    		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
    		return;
    	}
    	var myCondition = document.getElementById(prefix_component + "ThuocMaso_hid").value;
    	myCondition = myCondition + "___" + tutruc_pdtValue;
    	myCondition = myCondition + "___" + khoamaValue;    	
    	var xmlHttp = createXmlHttpRequest();
	    var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI("GetTonKhoByPhanLoaiThuocAction") + "&xmlData=" + encodeURI(myCondition);
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function() {
	       if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	       		var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);				
				
				/// kiem tra luong ton kho truoc khi lam 
				
				var i = 0;
				var havingData = false;
				if (data.list.record) {
							while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      	
						    	data1 = data.list.record[i];
							    if(data1.MaHang != null) {
				                	havingData = true;
				                	break;
				                }
				                i=i+1;
				        }
				        if (i == 0) { // truong hop nay chi co' 1 record
						    	data1 = data.list.record;
						    	//alert("data1: " + data1.MaHang);
							 	if(data1.MaHang != null) {  
				                	havingData = true;
				                }
				        }
				 }                       
				
				  //alert(havingData);
				     if (havingData == false){
				     
				     	var myTextboxId = document.getElementById(prefix_component + "DM_THUOC_MASO");
				     	if (myTextboxId.value != null && myTextboxId.value != ""){
				     		alert("Số lượng tồn kho đã hết.");
				     	}			    	
				    	
				    	myTextboxId.focus();
				    	myTextboxId.select();
				    	//highlightOnFocus();
				    	xyz = true;
				    	return;
				    }
				     else{
				    	 dijit.byId('__listtonkho_duocpham').intermediateChanges=true;
				    	 dijit.byId('__listtonkho_duocpham').focus();
				    	 //setTimeout("dijit.byId('__listtonkho_duocpham').focus()",100);
				     }
				    xyz = false;
				// ket thuc kiem tra
	       		var search = dijit.byId("__listtonkho_duocpham");
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "Lượng tồn", 
													"Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
				CatalogStore.newItem({id: "", title: firstTitle});
				i = 0;
				var giaTriDauTien = "";
				var giaTriNameDauTien="";
				var sohangdangco = 0;
				
				if (data.list.record) {
					while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      	
				    	data1 = data.list.record[i];
					    if(data1.MaHang != null) {
					    	var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Loai + "___" 
					    				+ data1.MP + "___" + data1.YC + "___" + data1.NDM + "___" + data1.KhongThu + "___" 
					    				+ data1.Malk + "___" + i;
					    	soluongMax = data1.TonKho;
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = data1.TonKho;
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					      	var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });
					       	
					       	if (giaTriDauTien == ""){
					       		giaTriDauTien = myId ;
					       	}
					       	if (giaTriNameDauTien == ""){
					       		giaTriNameDauTien = myTitle ;
					       	}
					       	sohangdangco = sohangdangco + 1;
					       	 //havingData = true;
					       	arrayIDReceivedFromServer[i]=  myId;
					       	arrayValueReceivedFromServer[i]=  myTitle;
					       	var dmKhoaMaso = data1.DmKhoaMaso;
					       	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = dmKhoaMaso;
					       	
					  	}
						i = i + 1;
					}
				            
				    if (i == 0) { // truong hop nay chi co' 1 record
				    	data1 = data.list.record;
				    	//alert("data1: " + data1.MaHang);
					 	if(data1.MaHang != null) {       
							var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Loai + "___" 
					    				+ data1.MP + "___" + data1.YC + "___" + data1.NDM + "___" + data1.KhongThu + "___" 
					    				+ data1.Malk + "___" + i;
					        soluongMax = data1.TonKho;
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = data1.TonKho;
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					        var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });  
					       
					       	if (giaTriDauTien == ""){
					       		giaTriDauTien = myId ;
					       	} 
					       	if (giaTriNameDauTien == ""){
					       		giaTriNameDauTien = myTitle ;
					       	}
					       	sohangdangco = sohangdangco + 1;
					       	var dmKhoaMaso = data1.DmKhoaMaso;
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					       	arrayValueReceivedFromServer[i]=  myTitle;
					       	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = dmKhoaMaso;
						}
					}
				} 
				search.store = CatalogStore;
				dijit.byId('__listtonkho_duocpham')._myfunction();
	  		    dijit.byId('__listtonkho_duocpham')._showResultList();
			}
	    };
	   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);    		
    }
}

function getThuocInfo() {
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
    var xml;
    var data;
    
    clearListTonkho();
    
    if (document.getElementById(prefix_component + "DM_THUOC_MASO") != "") {
    	//0: PDT
    	//1: Tu truc
    	var khoamaValue = 0;
    	if (document.getElementById(prefix_component + "__phieudutru_tutruc:0").checked) {
    		tutruc_pdtValue = "0";
    	} else {
    		tutruc_pdtValue = "1";
    		khoamaValue = document.getElementById(prefix_component + "THUOCNOITRU_KHOA_pk").value;
    		if(khoamaValue == null || khoamaValue == ""){
    			return;
    		}
    	}
    	var tenHang = document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value;
    	if(tenHang == ""){
    		document.getElementById(prefix_component + "DM_THUOC_MASO").value ="";
    		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
    		return;
    	}
    	var myCondition = document.getElementById(prefix_component + "ThuocMaso_hid").value;
    	myCondition = myCondition + "___" + tutruc_pdtValue;
    	myCondition = myCondition + "___" + khoamaValue;    	
    	var xmlHttp = createXmlHttpRequest();
	    var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI("GetTonKhoByPhanLoaiThuocAction") + "&xmlData=" + encodeURI(myCondition);
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function() {
	       if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	       		var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);				
				
				/// kiem tra luong ton kho truoc khi lam 
				
				var i = 0;
				var havingData = false;
				if (data.list.record) {
							while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      	
						    	data1 = data.list.record[i];
							    if(data1.MaHang != null) {
				                	havingData = true;
				                	break;
				                }
				                i=i+1;
				        }
				        if (i == 0) { // truong hop nay chi co' 1 record
						    	data1 = data.list.record;
						    	//alert("data1: " + data1.MaHang);
							 	if(data1.MaHang != null) {  
				                	havingData = true;
				                }
				        }
				 }                       
				
				  //alert(havingData);
				     if (havingData == false){
				     
				     	var myTextboxId = document.getElementById(prefix_component + "DM_THUOC_MASO");
				     	if (myTextboxId.value != null && myTextboxId.value != ""){
				     		alert("Số lượng tồn kho đã hết.");
				     	}			    	
				    	
				    	myTextboxId.focus();
				    	myTextboxId.select();
				    	//highlightOnFocus();
				    	xyz = true;
				    	return;
				    }
				     else{
				    	 document.getElementById('__listtonkho_duocpham').focus();
				     }
				    xyz = false;
				// ket thuc kiem tra
				
	       		var search = dijit.byId("__listtonkho_duocpham");
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "Lượng tồn", 
													"Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
				CatalogStore.newItem({id: "", title: firstTitle});
				i = 0;
				var giaTriDauTien = "";
				var giaTriNameDauTien="";
				var sohangdangco = 0;
				
				if (data.list.record) {
					while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      	
				    	data1 = data.list.record[i];
					    if(data1.MaHang != null) {
					    	var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Loai + "___" 
					    				+ data1.MP + "___" + data1.YC + "___" + data1.NDM + "___" + data1.KhongThu + "___" 
					    				+ data1.Malk + "___" + i;
					    	soluongMax = data1.TonKho;
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = data1.TonKho;
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					      	var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });
					       	
					       	if (giaTriDauTien == ""){
					       		giaTriDauTien = myId ;
					       	}
					       	if (giaTriNameDauTien == ""){
					       		giaTriNameDauTien = myTitle ;
					       	}
					       	sohangdangco = sohangdangco + 1;
					       	 //havingData = true;
					       	arrayIDReceivedFromServer[i]=  myId;
					       	arrayValueReceivedFromServer[i]=  myTitle;
					       	var dmKhoaMaso = data1.DmKhoaMaso;
					       	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = dmKhoaMaso;
					       	
					  	}
						i = i + 1;
					}
				            
				    if (i == 0) { // truong hop nay chi co' 1 record
				    	data1 = data.list.record;
				    	//alert("data1: " + data1.MaHang);
					 	if(data1.MaHang != null) {       
							var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Loai + "___" 
					    				+ data1.MP + "___" + data1.YC + "___" + data1.NDM + "___" + data1.KhongThu + "___" 
					    				+ data1.Malk + "___" + i;
					        soluongMax = data1.TonKho;
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = data1.TonKho;
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					        var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });  
					       
					       	if (giaTriDauTien == ""){
					       		giaTriDauTien = myId ;
					       	} 
					       	if (giaTriNameDauTien == ""){
					       		giaTriNameDauTien = myTitle ;
					       	}
					       	sohangdangco = sohangdangco + 1;
					       	var dmKhoaMaso = data1.DmKhoaMaso;
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					       	arrayValueReceivedFromServer[i]=  myTitle;
					       	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = dmKhoaMaso;
						}
					}
				} 
				search.store = CatalogStore;
			}
	    };
	   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);     	
    }
}

function getPdtTuTrucValue(value) {
	document.getElementById(prefix_component + "tutrucPdt").value = value;
	tutruc_pdtValue = value;
	document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkhoDMKhoaMaso_hid").value = "";
	document.getElementById(prefix_component + "tntLoai").value = "";
	document.getElementById("__listtonkho_duocpham").value = "";
	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
}

function getSoluongXuat() {
	var slXuat = 0;
	var countCt = document.getElementById(prefix_component + "__count").value;
	var malk = document.getElementById(prefix_component + "__malk").value;
	//alert("malk: " + malk);
	//alert("count: "+countCt);
	for (var i = 0; i < countCt; i++) {
		var malkCol = document.getElementById(prefix_component + "__myTable:" + i + ":malk").value;
		var isAllowedUpdate = document.getElementById(prefix_component + "__myTable:" + i + ":isAllowedUpdate").value;
		if (malkCol == malk) {
			if (isAllowedUpdate == "true") {
				var sl = parseFloat(document.getElementById(prefix_component + "__myTable:" + i + ":__soluongXuat").innerHTML);
				slXuat += sl;
			}
		}
	}
	
	return slXuat; 
}

function reloadValueForCombobox(){			
	displayPhansoFields();
	setValueForNSXHSX();
	var checkKiemKeHid = document.getElementById(prefix_component + "checkKiemKeHid").value;
	if(checkKiemKeHid == "true"){
		alert("Kho thuốc đang kiểm kê. Không được phép xuất thuốc cho bệnh nhân.");
		document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
		document.getElementById(prefix_component + "DM_THUOC").value = "";
		document.getElementById(prefix_component + "DM_THUOC_MA").focus();
		return;
	}	
	var displayPhanso = document.getElementById(prefix_component + "__phanso_hid").value;
	var isAllowedUpdate = document.getElementById(prefix_component + "__isAllowedUpdateSoLuong_hid").value;
	if(isAllowedUpdate == "true"){     		
		if(displayPhanso == "true"){
			document.getElementById(prefix_component + "__sochia").readOnly = false;
			document.getElementById(prefix_component + "__sobichia").readOnly = false;
			
			document.getElementById(prefix_component + "__sochia").select();
			document.getElementById(prefix_component + '__sochia').focus();
		}else{
			document.getElementById(prefix_component + "__soluong").readOnly = false;
			
			document.getElementById(prefix_component + "__soluong").select();
			document.getElementById(prefix_component + '__soluong').focus();
		}
	}else{
		if(displayPhanso == "true"){
			document.getElementById(prefix_component + "__sochia").readOnly = true;
			document.getElementById(prefix_component + "__sobichia").readOnly = true;
		}else{
			document.getElementById(prefix_component + "__soluong").readOnly = true;
		}
		document.getElementById(prefix_component + 'DT_DM_CHI_DAN_MA').focus();
	}	
	//highlightOnFocus();
	document.getElementById( 'DT_DM_CHI_DAN').value = document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value;
	numberFormatBlur(document.getElementById(prefix_component + "__dongia"));
	myOnblurTextbox(prefix_component + 'DM_BAI_THUOC_MA', 'DM_BAI_THUOC');
	if (document.getElementById(prefix_component + "__tonkho_hid").value != ''){
		document.getElementById(prefix_component + "__tonkho").value = document.getElementById(prefix_component + "__tonkho_hid").value;
	}
	if (document.getElementById(prefix_component + "__ndm_hid").value == "true"){		   
		document.getElementById(prefix_component + "__ndm").checked = true;			
	}else{
		document.getElementById(prefix_component + "__ndm").checked = false;
	}

	if (document.getElementById(prefix_component + "__mien_hid").value == "true"){		   
		document.getElementById(prefix_component + "__mien").checked = true;
	}else{
		document.getElementById(prefix_component + "__mien").checked = false;
	}	
		
	if (document.getElementById(prefix_component + "__yc_hid").value == "true"){		   
		document.getElementById(prefix_component + "__yc").checked = true;
	}else{
		document.getElementById(prefix_component + "__yc").checked = false;
	}	
}