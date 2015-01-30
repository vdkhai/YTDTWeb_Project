function init() {
    //alert(1);
	if (window.google && google.gears) {
		//try {
			//LoadCatalogFromServer();
            //---Load DB and set atrribute for combobox---
           // setAttrForCombobox(prefix_component + "DM_KINH_PHI_MASO","DM_KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
           // setAttrForCombobox(prefix_component + "DT_DM_NGUON_MA", "DT_DM_NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
            /*
            var tenCt = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
            if (tenCt == "QuanLyKhoChinh") {
            	tenCt = "KC";
            } else if (tenCt == "QuanLyKhoLe") {
            	tenCt = "KL";
            } else if (tenCt == "QuanLyKhoBHYT") {
            	tenCt = "BHYT";
            }*/
            
            //alert(tenCt);
            
            //setAttrForCombobox(prefix_component + "DM_KHOA_XUAT","DM_KHO_span2","DM_KHOA__2","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
            //setAttrForCombobox(prefix_component + "DM_KHOA_NHAN","DM_KHO_span1","DM_KHOA__1","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_NHAN").value +"\")","","","");
			 //var khoNhan = document.getElementById(prefix_component + "DM_KHO_NHAN").value;
			/* if (khoNhan == 'KNT'){
			 		setAttrForCombobox(prefix_component + 'DM_KHOA_NHAN','DM_KHO_span1', 'DM_KHOA__1', "getDmKhoa_KhoNoiTru()", "", "", "");
           	 		document.getElementById(prefix_component + "DM_KHOA_NHAN").value = "KNT";
           	 		myOnblurTextbox(prefix_component + "DM_KHOA_NHAN", "DM_KHOA__1");
           	 		
           	 		document.getElementById(prefix_component + "DM_KHOA_NHAN").disabled = true;
           	 		
           	 		
			 }else{
			 	setAttrForCombobox(prefix_component + 'DM_KHOA_NHAN','DM_KHO_span1', 'DM_KHOA__1', "getDmKhoa_KTE_BHYT_KNT()", "", "", "");
           	 }*/
			 
            
         	//setAttrForCombobox(prefix_component + 'DT_DM_LOAI_MA','DT_DM_LOAI_span', 'DM_LOAI_THUOC', "getDmLoaiThuoc()", "", "", "");
            setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_PHAT','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1', "getDtDmNhanVien_Duoc()", "", "", "");
            setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_BASI','DT_DM_NHAN_VIEN_span2', 'DT_DM_NHAN_VIEN__2', "getDtDmNhanVien_Duoc()", "", "", "");
          	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_CN','DT_DM_NHAN_VIEN_span3', 'DT_DM_NHAN_VIEN__3', "getDtDmNhanVien_Duoc()", "", "", "");
            //setAttrForCombobox(prefix_component + 'DM_THUOC_MASO','DM_THUOC_span', 'DM_THUOC', "getDmThuocByLoai(\"" + prefix_component + "DT_DM_LOAI_MA\")", "", "getTonkho()", "");
            
            
            //setAttrForComboboxForNoDB(prefix_component + '__listtonkho_duocpham_ma','__listtonkho_span', '__listtonkho_duocpham',"","mySetValueForPhieuXuat();document.getElementById('" + prefix_component + "__soluong').focus();","statusEnterAnhTabForChon = 0; myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_duocpham_ma\",\"__listtonkho_duocpham\"); mySetValueForPhieuXuat();document.getElementById('" + prefix_component + "__soluong').focus();","statusEnterAnhTabForChon = 1");
            //setAttrForComboboxJSFForPhieuXuat('__listtonkho_span', '__listtonkho_duocpham','10');
            
            
          	//setAttrForCombobox(prefix_component + "__listtonkho_duocpham_ma", "__listtonkho_span", "__listtonkho_duocpham", "getTonkho()", "", "", "");
          	setTimeout(function() {
          		setValueOnLoad();
          	}, 100);
		//} catch (e) {
		//	alert("init() error: " + e.description);
		//}
	}
	
}

function setValueOnLoad(){
//alert("vo setValue");
	document.getElementById(prefix_component + "__maphieu").focus();
	
	try {
	
	/*
	var kc_value = document.getElementById(prefix_component + "DM_KHO_NHAN").value;
		if (kc_value == 'KC'){
			document.getElementById(prefix_component + "DM_KHOA_NHAN").value = "KCH";
		}else  if (kc_value == 'BHYT'){
			document.getElementById(prefix_component + "DM_KHOA_NHAN").value = "KBH";
		}else  if (kc_value == 'NT'){
			document.getElementById(prefix_component + "DM_KHOA_NHAN").value = "KNT";
		}*/
		
		
		//myOnblurTextbox(prefix_component + "DT_DM_LOAI_MA", "DM_LOAI_THUOC");
		myOnblurTextbox(prefix_component + "DM_KHOA_NHAN", "DM_KHOA__1");
		myOnblurTextbox(prefix_component + "DM_KHOA_XUAT", "DM_KHOA__2");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_PHAT", "DT_DM_NHAN_VIEN__1");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_BASI", "DT_DM_NHAN_VIEN__2");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_CN", "DT_DM_NHAN_VIEN__3");
		//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", "DM_THUOC");
		//myOnblurTextbox(prefix_component + "DM_KINH_PHI_MASO", "DM_NGUON_KINH_PHI");
		//myOnblurTextbox(prefix_component + "DT_DM_NGUON_MA", "DM_NGUON_CHUONG_TRINH");
		//document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value = "";
		//document.getElementById("__listtonkho_duocpham").value = "";
		//document.getElementById(prefix_component + "__tonkho").value = "";
		
		var isUpdate = document.getElementById(prefix_component + "__isUpdate").value;
		var isFound = document.getElementById(prefix_component + "__isFound").value;
		//alert("SETVALUE: isUpdate:" + isUpdate);
		//alert("SETVALUE: isFound:" + isFound);
		if (isFound == 'true' && isUpdate == '0') {
			document.getElementById("__divIn").style.display = "block";
			document.getElementById("__divGhiNhan").style.display = "none";
			lockUnlockControl(document.forms[0], 0);
		}
		else if (isFound == 'true' && isUpdate == '1') {
			document.getElementById("__divIn").style.display = "none";
			document.getElementById("__divGhiNhan").style.display = "block";
			lockUnlockControl(document.forms[0], 0);
		}		
		else if (isFound == 'false'){		
			document.getElementById("__divIn").style.display = "none";
			document.getElementById("__divGhiNhan").style.display = "none";
			lockUnlockControl(document.forms[0], 1);
		}
		
		
		
		if(isFound == 'true')
		{
			lockUnlockControl(document.forms[0], 1);
		}
		else {
			lockUnlockControl(document.forms[0], 0);
		}
		/*
		var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
		if (kc_value == 'KC'){
			document.getElementById(prefix_component + "DM_KHOA_XUAT").value = "KCH";
		}else  if (kc_value == 'BHYT'){
			document.getElementById(prefix_component + "DM_KHOA_XUAT").value = "KBH";
		}else  if (kc_value == 'TE'){
			document.getElementById(prefix_component + "DM_KHOA_XUAT").value = "KTE";
		}else  if (kc_value == 'NT'){
			document.getElementById(prefix_component + "DM_KHOA_XUAT").value = "KNT";
		}
		myOnblurTextbox(prefix_component + "DM_KHOA_XUAT", "DM_KHOA__2");
	*/
	
	
	} catch (e) {
		alert("setValueOnLoad error: " + e.description);
	}
}

function onCompleteGetInfor() {
//alert("vo onComp");
	try {
		//document.getElementById("__listtonkho_duocpham").value = "";
		//document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value = "";
		//document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
		//myOnblurTextbox(prefix_component + "DT_DM_LOAI_MA", "DM_LOAI_THUOC");//Trungnh fix bug javascript: Object require 
		myOnblurTextbox(prefix_component + "DM_KHOA_NHAN", "DM_KHOA__1");
		myOnblurTextbox(prefix_component + "DM_KHOA_XUAT", "DM_KHOA__2");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_PHAT", "DT_DM_NHAN_VIEN__1");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_BASI", "DT_DM_NHAN_VIEN__2");
		myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_CN", "DT_DM_NHAN_VIEN__3");
		//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", "DM_THUOC");
		//myOnblurTextbox(prefix_component + "DM_KINH_PHI_MASO", "DM_NGUON_KINH_PHI");//Trungnh fix bug javascript Object require
		//myOnblurTextbox(prefix_component + "DT_DM_NGUON_MA", "DM_NGUON_CHUONG_TRINH");//Trungnh fix bug javascript Object require
		
		var isUpdate = document.getElementById(prefix_component + "__isUpdate").value;
		var isFound = document.getElementById(prefix_component + "__isFound").value;
		//alert("isUpdate:" + isUpdate);
		//alert("isFound:" + isFound);
		if (isFound == 'true' && isUpdate == '0') {
			document.getElementById("__divIn").style.display = "block";
			document.getElementById("__divGhiNhan").style.display = "none";
			lockUnlockControl(document.forms[0], 0);
		}
		else if (isFound == 'true' && isUpdate == '1') {
			document.getElementById("__divIn").style.display = "none";
			document.getElementById("__divGhiNhan").style.display = "block";
			lockUnlockControl(document.forms[0], 0);
		}		
		else if (isFound == 'false'){		
			document.getElementById("__divIn").style.display = "none";
			document.getElementById("__divGhiNhan").style.display = "none";
			lockUnlockControl(document.forms[0], 1);
		}
		
		
		if(isFound == 'true')
		{
			lockUnlockControl(document.forms[0], 1);
		}
		else {
			lockUnlockControl(document.forms[0], 0);
		}
		
	
		/*
		var search = dijit.byId("__listtonkho_duocpham");
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		search.store = CatalogStore;
		*/
		//mySetValueForPhieuXuat("__listtonkho_duocpham");
	} catch (e) {
		alert("onCompleteGetInfor error: " + e.description);
	}
	
	tinhTien();
	soluongMax = "";
	validateSoluong = false;
	dmtFocus();
}

function onBeforeGetInfo() {
	if (document.getElementById(prefix_component + "__soluong").value == "") {
		clearDmt();
	} else {
		soluongMax = document.getElementById(prefix_component + "__tonkho_hid").value;
		if (soluongMax == "") {
			dmtFocus();
			return false;
		}
		if (!onSubmitTmp1()) {
			return false; 
		} else {
			return true;
		}
	}
	return true;
}

function dmtFocus() {
	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	//alert("dm thuoc focus.");
}

function tinhTien() {
	/*
	var countCt = document.getElementById(prefix_component + "__count").value;
	var temp = 0;
	for (var i = 0; i < countCt; i++) {
		var tt = document.getElementById(prefix_component + "tableXuatKho:" + i + ":colTT").innerHTML;
		temp = temp + parseInt(tt);
		//alert("tong tien: " + temp);
	}
	document.getElementById(prefix_component + "__tongtien").value = temp;
	*/
}

function getSoluongXuat() {
	var slXuat = 0;
	var countCt = document.getElementById(prefix_component + "__count").value;
	var malk = document.getElementById(prefix_component + "__malk").value;
	//alert("malk: " + malk);
	for (var i = 0; i < countCt; i++) {
		var malkCol = document.getElementById(prefix_component + "tableXuatKho:" + i + ":malk").value;
		if (malkCol == malk) {
			slXuat +=  parseFloat(formatRealNumberStr(document.getElementById(prefix_component + "tableXuatKho:" + i + ":__soluongXuat").innerHTML)); 
		}
	}
	return slXuat;
}

function getTonkho() {
  	try {
		
		arrayValueReceivedFromServer= new Array(50);
		arrayIDReceivedFromServer= new Array(50);  	
	  	
		var maHang = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
		if (maHang == "") {
	    	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
			return;
		}
		
		var kinhphiMa = document.getElementById(prefix_component + "DM_KINH_PHI_MASO").value;
		if (kinhphiMa == "") {
			kinhphiMa = " ";
		}
		
		var nguonMa = document.getElementById(prefix_component + "DT_DM_NGUON_MA").value;
		if (nguonMa == "") {
			nguonMa = " ";
		}
		
		var khoaXuat = document.getElementById(prefix_component + "DM_KHOA_XUAT").value;
		if (khoaXuat == "") {
			khoaXuat = " ";
		}
		
		var request = maHang + ";" + kinhphiMa + ";" + nguonMa + ";" + khoaXuat + ";";
    	var xml;
    	var data;
    
    	var xmlHttp = createXmlHttpRequest();
    	var url =  myContextPath + "/actionServlet?";
    	var params = "urlAction="+ encodeURI("GetTonKhoDuocPhamAction") + "&xmlData=" + encodeURI(request);
		xmlHttp.open("POST", url, false);
	
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
			    
			    //alert(jsonExpression);
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
				    xyz = false;
				// ket thuc kiem tra
				
				
				
	       		var search = dijit.byId("__listtonkho_duocpham");
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "Lượng tồn", 
													"Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
				CatalogStore.newItem({id: "", title: firstTitle});
				i = 0;
				
				
				if (data.list.record) {
					while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      
					    //alert(data.list.record[i]);	
				    	data1 = data.list.record[i];
					    if(data1.MaHang != null) {
					    	var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Malk + "___" + i; 
					    	
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = groupingNumber(data1.TonKho);
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					      	var hanDung = data1.HanDung;
					      	var donGia = groupingNumber(data1.DonGia);
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					    	arrayValueReceivedFromServer[i]=  myTitle;
					    
					  	}
						i = i + 1;
					}
				            
				    if (i == 0) { // truong hop nay chi co' 1 record
				    	data1 = data.list.record;
					 	if(data1.MaHang != null) {       
							var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + data1.Malk + "___" + i; 
							
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = groupingNumber(data1.TonKho);
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					        var hanDung = data1.HanDung;
					      	var donGia = groupingNumber(data1.DonGia);
					      	
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });  
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					    	arrayValueReceivedFromServer[i]=  myTitle;
					    	
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
		
	} catch(e) {
		alert("getTonkho():" + e.description);
	}
}

function getInfo() {
	try {
		document.getElementById(prefix_component + "__soluong").value =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__soluong"))); 
		
		document.getElementById(prefix_component + "__tonkho").value = document.getElementById(prefix_component + "__tonkho_hid").value;
		myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", "DM_THUOC");
		
		var i = 0;
		while (dataList.record[i] != null) {
			//alert(i);
			if (dataList.record[i].TonKhoMa == document.getElementById(prefix_component + "__tonkhoma_hid").value) {
				//alert("ton kho ma: " + dataList.record[i].TonKhoMa);
				document.getElementById("__listtonkho_duocpham").value = dataList.record[i].TenHang + "  " + 
																	dataList.record[i].TonKho + "  " + 
																	dataList.record[i].DonVi ;
				break;
			}
			i++;
		}
		
	} catch (e) {
		//alert("getInfo() error: " + e.description);
	}
}

// ham nay duoc goi khi __listtonkho_duocpham_ma = null
function clearDmt() {
	document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";
	myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", 'DM_THUOC', 
								document.getElementById(prefix_component + 'DT_DM_LOAI_MA').value,'');
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById("__listtonkho_duocpham").value = "";
	document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
	
	var search = dijit.byId("__listtonkho_duocpham");
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	search.store = CatalogStore;
	
	dmtFocus();
}
/*
function onfocusTonkho() {
	if (document.getElementById(prefix_component + "__tonkhoma_hid").value == "") {
		dmtFocus();
	} else {
		document.getElementById(prefix_component + "__soluong").focus();
	}
}*/
function onfocusMaPhieu() {
	if (document.getElementById(prefix_component + "__maphieu").value == "") {
		document.getElementById(prefix_component + "__maphieu").focus();
	}
}

function lockUnlockControl(form, type) {
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
  		//var ten = elem[i].name;
  		var strType = elem[i].type.toLowerCase();
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



