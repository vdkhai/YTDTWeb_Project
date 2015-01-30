        
function init() {
	
    if (window.google && google.gears) {
    	setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
        setAttrForComboboxNoOnBlurCombobox(prefix_component + 'DT_DM_LOI_DAN_MA','DT_DM_LOI_DAN_span','DT_DM_LOI_DAN',"getDtDmLoiDan()","","","");
        setAttrForCombobox(prefix_component + 'DM_BAI_THUOC_MA','DM_BAI_THUOC_span','DM_BAI_THUOC',"getDmBaiThuoc()","myOnblurCombobox('" + prefix_component + "DM_BAI_THUOC_MA','DM_BAI_THUOC');","","");
        setAttrForCombobox(prefix_component + 'DM_QUOC_GIA_MA','DM_QUOC_GIA_span','DM_QUOC_GIA',"getDmQuocGia()","","","");
        setAttrForCombobox(prefix_component + 'DM_NHA_SAN_XUAT_MA','DM_NHA_SAN_XUAT_span','DM_NHA_SAN_XUAT',"getDmNhaSanXuat()","","","");                               
        setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");                
        setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmBacSi()","","","");
        setAttrForComboboxNoOnBlurCombobox(prefix_component + 'DT_DM_CHI_DAN_MA','DT_DM_CHI_DAN_span',  'DT_DM_CHI_DAN',"getDtDmChiDan()","","","");
        //setAttrForComboboxForNoDB(prefix_component + '__listtonkho_ma_2','__listtonkho_span', '__listtonkho',"","mySetValueForThuocPhongKham(); document.getElementById('" + prefix_component + "DT_DM_CHI_DAN_MA').focus();  ","statusEnterAnhTabForChon = 0; myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_ma_2\",\"__listtonkho\");mySetValueForThuocPhongKham(); document.getElementById('" + prefix_component + "DT_DM_CHI_DAN_MA').focus(); ","statusEnterAnhTabForChon = 1");
        
        //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
        initshorcut();
        document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "no";
        showListTPKBKTruoc();
        timer.setTimeout(function(){setOnload(); document.getElementById(prefix_component + 'temp_button_add').click(); },100);  
    }
}

function showListTPKBKTruoc(){
	var isShowListCLSBKTruoc = document.getElementById(prefix_component + "isShowListTPKBKTruoc").value;
	//alert(fromMenu);
	if (isShowListCLSBKTruoc == "yes"){
		document.getElementById("divListTPKBKTruoc").style.display = "block";
		document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "no";
	}else{
		document.getElementById("divListTPKBKTruoc").style.display = "none";
		document.getElementById(prefix_component + "isShowListTPKBKTruoc").value = "yes";
	}
}

function setFocus_soluong(){
	document.getElementById(prefix_component + '__soluong').focus();
}

function setFocus_Mathuoc(){
	var mathuoc = document.getElementById(prefix_component + 'DM_THUOC_MA').value;
	if(mathuoc == null || mathuoc == ""){
		document.getElementById(prefix_component + 'DM_THUOC_MA').focus();
	}
}

function initshorcut(){
    shortcut.add("Ctrl+A", function() {
        document.getElementById(prefix_component + "__themchinhsau").onclick();
    },{'type':'keydown',"propagate":false,'target':document});

    shortcut.add("Ctrl+C", function() {
    	document.getElementById(prefix_component + "DM_THUOC_MA").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function reloadValueForComboboxForEnter(){	
	document.getElementById( 'DT_DM_CHI_DAN').value = document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value;
		
	highlightOnFocus();
	document.getElementById(prefix_component + "__donvi").value=document.getElementById(prefix_component + "__donvi_HIDDEN").value;
	
    myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	myOnblurTextbox(prefix_component + 'DM_CACH_DUNG_THUOC_MA', 'DM_CACH_DUNG_THUOC');
    document.getElementById(prefix_component + "DM_THUOC_MA").focus();
}


function onCompletebtn() {
	 highlightOnFocus();
}

function checkSoLuong(){
	var ton = document.getElementById(prefix_component + "__ton").value;
	var soluong = document.getElementById(prefix_component + "__soluong").value;
	var mathuoc = document.getElementById(prefix_component + "DM_THUOC_MA").value;
	if(mathuoc != "" && soluong == ""){
		alert("Vui lòng nhập số lượng thuốc cấp cho bệnh nhân.");
		changeCursorDefault ();
		document.getElementById(prefix_component + "__soluong").focus();
		return false;
	}
	if (parseInt(soluong) > parseInt(ton)){
		alert("Số lượng cấp phải nhỏ hơn hoặc bằng tồn.");
		changeCursorDefault ();
		document.getElementById(prefix_component + "__soluong").select();
		document.getElementById(prefix_component + "__soluong").focus();
		return false;	
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
	return true;
}

function checkBeforeUpdateChiTiet(){
	document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value = document.getElementById( 'DT_DM_CHI_DAN').value;
		
	changeCursorWait ();
	var maThuoc = document.getElementById(prefix_component + "DM_THUOC_MA").value;
	if (maThuoc == null || maThuoc == ""){
	   alert("Vui lòng nhập mã thuốc.");
	   changeCursorDefault ();
	   document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	   return false;
	}
	
	var ton = document.getElementById(prefix_component + "__ton").value;
	var soluong = document.getElementById(prefix_component + "__soluong").value;
	if (soluong == null || soluong== ""){
	  alert("Vui lòng nhập số lượng.");
	  changeCursorDefault ();
	  document.getElementById(prefix_component + "__soluong").focus();
	  return false;
	}
	if (parseFloat(soluong) <= 0){
	  alert("Vui lòng nhập số lượng.");
	  changeCursorDefault ();
	   document.getElementById(prefix_component + "__soluong").focus();
	  return false;
	}
	//alert(ton);
	if (parseInt(soluong) > parseInt(ton)){
	  alert("Số lượng cấp phải nhỏ hơn hoặc bằng tồn.");
	  changeCursorDefault ();
	  document.getElementById(prefix_component + "__soluong").select();
	  document.getElementById(prefix_component + "__soluong").focus();
	  return false;	
	}
	//alert("true");
	return true;
}

function onCompleteThemChinhSau(){	
	document.getElementById('DT_DM_CHI_DAN').value="";
	document.getElementById('DM_QUOC_GIA_2').value="";
	document.getElementById('DM_NHA_SAN_XUAT_2').value="";
}

function clearListTonkho(){		
	// 20110116 bao.ttc: Clear tonkho list (dojo)
    var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	// 20110122 bao.ttc: phai xoa __listtonkho_ma_2 de xoa hoan toan thong tin Thuoc truoc
	document.getElementById(prefix_component + '__listtonkho_ma_2').value = "";
	// 20110116 bao.ttc: Clear tonkho list (dojo) --- END
	
	//reset all data
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__dongia_HIDDEN").value =  "";
	document.getElementById(prefix_component + "__yeucau").checked = false;
	document.getElementById(prefix_component + "__ndm").checked = false;
	document.getElementById(prefix_component + "__ndm_hid").value = false;
	document.getElementById(prefix_component + "__mien").checked = false;
	document.getElementById(prefix_component + "__mien_hid").value = false;
	document.getElementById(prefix_component + "__khongThu").checked = false;
		
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA").value = "";	 
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA_HIDDEN").value = "";
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value = "";	
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = "";	 
	document.getElementById(prefix_component + "hid_NCT").value = "";
	document.getElementById(prefix_component + "hid_NKP").value = "";
	document.getElementById(prefix_component + "hid_MaLK").value = "";
	document.getElementById(prefix_component + "hid_NCT_Ma").value = "";
	document.getElementById(prefix_component + "__ton").value = "" ;	
	document.getElementById(prefix_component + "__ton_HIDDEN").value = "";	

	myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	document.getElementById('DM_QUOC_GIA_2').value = document.getElementById('DM_QUOC_GIA').value;
	document.getElementById("DM_NHA_SAN_XUAT_2").value = document.getElementById("DM_NHA_SAN_XUAT").value;
}
function getTonkho() {
  	try {
  		clearListTonkho();
  		// bao.ttc: Xoa hoan toan cac thong tin cua thuoc truoc bang 2 dong duoi
		arrayValueReceivedFromServer= new Array(50);
		arrayIDReceivedFromServer= new Array(50);  	
	  	
		var maHang = document.getElementById(prefix_component + "DM_THUOC_MA").value;
		var tenHang = document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value;
		if (maHang == "") {
	    	document.getElementById(prefix_component + "DM_THUOC_MA").focus();
			return;
		}
		
		if (tenHang == ""){
			document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
			document.getElementById(prefix_component + "DM_THUOC_MA").focus();
			return;
		}
		
		var maKhoa = document.getElementById(prefix_component + "hid_KhoaMa").value;
		if (maKhoa == "") {
			maKhoa = " ";
		}
		
		var request = maHang + "___" + maKhoa;
    	var xml;
    	var data;
    
    	var xmlHttp = createXmlHttpRequest();
    	var url =  myContextPath + "/actionServlet?";
    	var params = "urlAction="+ encodeURI("GetPriceThuocAction") + "&xmlData=" + encodeURI(request);
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
					    if(data1.MaSo != null && data1.MaSo != "") {
					    	havingData = true;
				            break;
				        }
				        i=i+1;
				    }
				    if (i == 0) { // truong hop nay chi co' 1 record
						data1 = data.list.record;
					   	//alert("data1: " + data1.MaHang);
					 	if(data1.MaSo != null && data1.MaSo != "") {  
				          	havingData = true;
				        }
				    }
				 }                       
				
				  //alert(havingData);
				     if (havingData == false){				    	
				    	var myTextboxId = document.getElementById(prefix_component + "DM_THUOC_MA");
						if (myTextboxId.value != null && myTextboxId.value != ""){
							alert("Số lượng tồn kho đã hết.");
							
							// phuc.lc add code below
							// Clear tonkho list (dojo)
							CatalogStore.newItem({id: "" , title: "" });
							search.store = CatalogStore;
							// Clear listtonkho textfile				
							document.getElementById("__listtonkho").value = "";
							// Disabled addd button
							document.getElementById(prefix_component + "__themchinhsau").disabled = true;
							
							//20110116 bao.ttc: xoa Ma Thuoc, Ten Thuoc, NSX, HSX
							document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
							document.getElementById(prefix_component + "DM_THUOC").value = "";
						}
						
				    	myTextboxId.focus();
				    	myTextboxId.select();
				    	//highlightOnFocus();
				    	xyz = true;
				    	return;
				    }else{
				    	//phuc.lc : enable add button
					    document.getElementById(prefix_component + "__themchinhsau").disabled = false;
					    dijit.byId('__listtonkho').intermediateChanges=true;
				    	dijit.byId('__listtonkho').focus();
				     }
				    xyz = false;
				// ket thuc kiem tra			
				
				
	       		var search = dijit.byId("__listtonkho");
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng","Lượng tồn", "Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
				CatalogStore.newItem({id: "", title: firstTitle});
				i = 0;				
				
				if (data.list.record) {
					while (data.list.record[i] != null) {      // truong hop nay co' >=2 record      
					    //alert(data.list.record[i]);	
				    	data1 = data.list.record[i];
					    if(data1.MaSo != null && data1.MaSo != "") {
					    	var myId =  data1.MaSo + "___" + data1.DonGia + "___" + data1.DonGiaBH + "___" +
							data1.DonGiaYC + "___" +  data1.NDM + "___" +  data1.DonGiaMP + "___" +  data1.KhongThu + "___" + data1.HSX  + "___" + data1.QG + 
							 "___" + data1.NCT + "___" +  data1.NKP + "___" + data1.MaLK  +
							 "___" +  data1.NCT_Ma + "___" + data1.NCT_Ma  +
							 "___" + data1.Ton + "___" + data1.NgayChinhSua  +							
							 "___" + data1.NamNhap  + "___" + data1.NgayHanDung + 
							 "___" + data1.ThangHanDung  + "___" + data1.NamHanDung ;    
							       
					    	var myTitle =  formatStringDuocPham(data1.tenThuoc, data1.hamLuong, groupingNumber(data1.Ton), 
																data1.Dvt, data1.tenQG, data1.tenHSX, 
																data1.DonGia, data1.hanDung);
					    	CatalogStore.newItem({id: myId , title: myTitle  });
				       						    				       	
					       	arrayIDReceivedFromServer[i]=  myId;
					    	arrayValueReceivedFromServer[i]=  myTitle;
					    
					  	}
						i = i + 1;
					}
				            
				    if (i == 0) { // truong hop nay chi co' 1 record
				    	data1 = data.list.record;
					 	if(data1.MaSo != null && data1.MaSo != "") {       
					 		var myId =  data1.MaSo + "___" + data1.DonGia + "___" + data1.DonGiaBH + "___" +
							data1.DonGiaYC + "___" +  data1.NDM + "___" +  data1.DonGiaMP + "___" +  data1.KhongThu + "___" + data1.HSX  + "___" + data1.QG + 
							 "___" + data1.NCT + "___" +  data1.NKP + "___" + data1.MaLK  +
							 "___" +  data1.NCT_Ma + "___" + data1.NCT_Ma  +
							 "___" + data1.Ton  + "___" + data1.NgayChinhSua  +							
							 "___" + data1.NamNhap  + "___" + data1.NgayHanDung +
							 "___" + data1.ThangHanDung  + "___" + data1.NamHanDung ;         
							
		                    var myTitle =  formatStringDuocPham(data1.tenThuoc, data1.hamLuong, groupingNumber(data1.Ton), 
																data1.Dvt, data1.tenQG, data1.tenHSX, 
																data1.DonGia, data1.hanDung);
				       		CatalogStore.newItem({id: myId , title: myTitle  });
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					    	arrayValueReceivedFromServer[i]=  myTitle;
						}
					}
				} 
				
				search.store = CatalogStore;
				
				dijit.byId('__listtonkho')._myfunction();
	  		    dijit.byId('__listtonkho')._showResultList();
	  		    
			}
		};
		
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);
		
	} catch(e) {
		alert("getTonkho():" + e.description);
	}
}

function setInforForThuoc(){
	//clearListTonkho();
    var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	document.getElementById("__listtonkho").value = "";
	document.getElementById(prefix_component + '__listtonkho_ma_2').value = "";
	
	//20110210: giu lai thong tin Yeu cau, khong thu
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__dongia_HIDDEN").value =  "";
	//document.getElementById(prefix_component + "__yeucau").checked = false;
	document.getElementById(prefix_component + "__ndm").checked = false;
	document.getElementById(prefix_component + "__ndm_hid").value = false;
	document.getElementById(prefix_component + "__mien").checked = false;
	document.getElementById(prefix_component + "__mien_hid").value = false;
	//document.getElementById(prefix_component + "__khongThu").checked = false;
		
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA").value = "";	 
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA_HIDDEN").value = "";
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value = "";	
	document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = "";	 
	document.getElementById(prefix_component + "hid_NCT").value = "";
	document.getElementById(prefix_component + "hid_NKP").value = "";
	document.getElementById(prefix_component + "hid_MaLK").value = "";
	document.getElementById(prefix_component + "hid_NCT_Ma").value = "";
	document.getElementById(prefix_component + "__ton").value = "" ;	
	document.getElementById(prefix_component + "__ton_HIDDEN").value = "";	

	myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	document.getElementById('DM_QUOC_GIA_2').value = document.getElementById('DM_QUOC_GIA').value;
	document.getElementById("DM_NHA_SAN_XUAT_2").value = document.getElementById("DM_NHA_SAN_XUAT").value;
	
    //try{	
    //alert(1);
	var maThuocObj = document.getElementById(prefix_component + "DM_THUOC_MA_pk");
	//if (maThuocObj.value == "" || maThuocObj.value == ""  ){
	//	
	//}
	try{
	    if (maThuocObj.value == "" || maThuocObj.value == ""  ){
		    
    	    //alert(2);
    		document.getElementById(prefix_component + "DM_THUOC_MA").focus;
    		return;	
		}
	}catch(e){
	        //alert(3);
	    	document.getElementById(prefix_component + "DM_THUOC_MA").focus;
	    	return;	    
	}
	
	var maKhoa = document.getElementById(prefix_component + "hid_KhoaMa").value;
	var dieuKien = document.getElementById(prefix_component + "DM_THUOC_MA").value +"___"+ maKhoa;
	getPriceThuocFromServer('GetPriceThuocAction',dieuKien);//3 _		
}

function getPriceThuocFromServer(urlAction,myCondition){
  //alert("getPriceKyThuatFromServer"); 
  	try{
  		var xmlHttp = createXmlHttpRequest();    
  		var url = myContextPath + "/actionServlet?";
    
  		var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	
  		xmlHttp.open("POST", url, false);

  		xmlHttp.onreadystatechange = function(){
  			handlerReadyStateChangeForGetPriceThuocFromServer(xmlHttp);
  		};
  		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  		xmlHttp.send(params); 	   
	 }catch(e){
	    alert("getPriceFromServer: error with params: " + urlAction + ","+ myCondition );
	 }    
}

var dataList;

function handlerReadyStateChangeForGetPriceThuocFromServer(xmlHttp){

	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		if ( data.list != null && typeof(data.list) == "object") {
	  		dataList = data.list;
      		listCatalogJSF_ThuocPhongKham("__listtonkho",dataList); 
        }
	 }
}

function listCatalogJSF_ThuocPhongKham(myCombobox, data){
	
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);	
	
	var myComboboxId = document.getElementById(myCombobox);
	
	/// kiem tra luong ton kho truoc khi lam 
	var i = 0;
	var havingData = false;
	if (data != null){
			while (data.record[i] != null){            	
	                data1 = data.record[i];   
	                //alert(data1);             
	                if(data1.MaSo != null && data1.MaSo != ""){   
	                	havingData = true;
	                	break;
	                }
	                i=i+1;
	        }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	         		
	                if(data1.MaSo != null && data1.MaSo != ""){    
	                	havingData = true;
	                }
	        }
	 }                       
	var search = dijit.byId(myComboboxId.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	  //alert(havingData);
	     if (havingData == false){
	    	
	    	
	    	var myTextboxId = document.getElementById(prefix_component + "DM_THUOC_MA");
			if (myTextboxId.value != null && myTextboxId.value != ""){
				alert("Số lượng tồn kho đã hết.");
				// phuc.lc add code below
				// Clear tonkho list (dojo)
				CatalogStore.newItem({id: "" , title: "" });
				search.store = CatalogStore;
				// Clear listtonkho textfile				
				document.getElementById("__listtonkho").value = "";
				// Disabled addd button
				document.getElementById(prefix_component + "__themchinhsau").disabled = true;
				
				//20110116 bao.ttc: xoa Ma Thuoc, Ten Thuoc, NSX, HSX
				document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
				document.getElementById(prefix_component + "DM_THUOC").value = "";
			}
				     	
	    	
	    	myTextboxId.focus();
	    	myTextboxId.select();
	    	//statusEnterAnhTabForChon = 0; 
	    	xyz = true;
	    	//highlightOnFocus();
	    	return;
	    } else {
	    	//phuc.lc : enable add button
	    	document.getElementById(prefix_component + "__themchinhsau").disabled = false;
	    	//Ly them
	    	dijit.byId('__listtonkho').intermediateChanges=true;
	    	dijit.byId('__listtonkho').focus();
	    }
	    xyz == false;
	//alert("comboboxId:"+ myComboboxId);
	//alert(2);
   // var rs ;
	//try{
	var firstID_popup ="";
	var firstTitle_popup="";
	   
		
		var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "Lượng tồn", 
												"Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
		CatalogStore.newItem({id: "", title: firstTitle});
		//alert(100);
		i = 0;
		
		
		
		if (data != null){
			while (data.record[i] != null){            	
				data1 = data.record[i];   
	            //alert(data1);             
	            if(data1.MaSo != null && data1.MaSo != ""){    
	            	var khongThu = "";
					if (data1.KhongThu == "true"){
						khongThu = "0 thu";
					}
						
					var DonGiaBH = "";
					if (DonGiaBH != "false"){
						DonGiaBH = data1.DonGiaBH;
					}
						
					var DonGiaYC = "";
					if (DonGiaYC != "false"){
						DonGiaYC = data1.DonGiaYC;
					}
						
					var myId =  data1.MaSo + "___" + data1.DonGia + "___" + data1.DonGiaBH + "___" +
						data1.DonGiaYC + "___" +  data1.NDM + "___" +  data1.DonGiaMP + "___" +  data1.KhongThu + "___" + data1.HSX  + "___" + data1.QG + 
						 "___" + data1.NCT + "___" +  data1.NKP + "___" + data1.MaLK  +
							 "___" +  data1.NCT_Ma + "___" + data1.NCT_Ma  +
						"___" + data1.Ton + "___" + data1.NgayChinhSua  
						
						 + "___" + data1.NamNhap  + "___" + data1.NgayHanDung 
						  + "___" + data1.ThangHanDung  + "___" + data1.NamHanDung ;    
						       
					var myTitle =  formatStringDuocPham(data1.tenThuoc, data1.hamLuong, groupingNumber(data1.Ton), 
															data1.Dvt, data1.tenQG, data1.tenHSX, 
															data1.DonGia, data1.hanDung);
			       	CatalogStore.newItem({id: myId , title: myTitle  });
	                  
	                //havingData = true;
	                    
	                arrayIDReceivedFromServer[i]=  myId;
					arrayValueReceivedFromServer[i]=  myTitle;
					
					//if (firstID_popup == "" && myTitle != null && myTitle != "undefined"){
	                //   	firstID_popup = myId;
	                //  	firstTitle_popup = myTitle;
	                //}
	            }
				i=i+1;
	         }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	         		
	                if(data1.MaSo != null && data1.MaSo != ""){    
	                
	                    var khongThu = "";
						if (data1.KhongThu == "true"){
							khongThu = "0 thu";
						}
						
						var DonGiaBH = "";
						if (DonGiaBH != "false"){
							DonGiaBH = data1.DonGiaBH;
						}
						
						var DonGiaYC = "";
						if (DonGiaYC != "false"){
							DonGiaYC = data1.DonGiaYC;
						}
						
						   
	                  	var myId =  data1.MaSo + "___" + data1.DonGia + "___" + data1.DonGiaBH + "___" +
						data1.DonGiaYC + "___" +  data1.NDM + "___" +  data1.DonGiaMP + "___" +  data1.KhongThu + "___" + data1.HSX  + "___" + data1.QG + 
						 "___" + data1.NCT + "___" +  data1.NKP + "___" + data1.MaLK  +
							 "___" +  data1.NCT_Ma + "___" + data1.NCT_Ma  +
						"___" + data1.Ton  + "___" + data1.NgayChinhSua  
						
						 + "___" + data1.NamNhap  + "___" + data1.NgayHanDung 
						  + "___" + data1.ThangHanDung  + "___" + data1.NamHanDung ;         
						//alert(myId);  	
						//alert(groupingNumber(data1.Ton));  						
						
	                    var myTitle =  formatStringDuocPham(data1.tenThuoc, data1.hamLuong, groupingNumber(data1.Ton), 
															data1.Dvt, data1.tenQG, data1.tenHSX, 
															data1.DonGia, data1.hanDung);
			       		CatalogStore.newItem({id: myId , title: myTitle  }); 
	                  
	                    //havingData = true;
	                    
	                    //if (myTitle != null &&  myTitle != "undefined"){
	                    // 	firstID_popup = myId;
	                    // 	firstTitle_popup = myTitle;
	                    //}
	                   
	                   arrayIDReceivedFromServer[i]=  myId;
					   arrayValueReceivedFromServer[i]=  myTitle;
	                   
	                }
	         }
	    }else{
	    	myComboboxId.value = "";
	    }
		search.store = CatalogStore;	
}

function setOnload(){
	highlightOnFocus();
 
	document.getElementById('DT_DM_LOI_DAN').value = document.getElementById( prefix_component + 'DT_DM_LOI_DAN_hidden').value;
	
	myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  
	myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
	myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
    myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');

    document.getElementById(prefix_component + "DT_DM_LOI_DAN_MA").focus();
  
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
	
	var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
	}
		
	var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	
	if (fromMenu == "yes"){
		document.getElementById("divNhapMoi").style.display = "block";
		document.getElementById("divQuaylai").style.display = "none";
		 
		 setDefaultValueForBanKham();
		 
		 document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").focus();
		 document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").select();
	}else{
		document.getElementById("divNhapMoi").style.display = "none";
		document.getElementById("divQuaylai").style.display = "block";
		document.getElementById(prefix_component + "DT_DM_LOI_DAN_MA").focus();
		 
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").disabled = true;
		if (document.getElementById( "DT_DM_BAN_KHAM").disabled == false){
      	  changeDisabledAttr("DT_DM_BAN_KHAM");  
   		} 
		document.getElementById(prefix_component + "__matiepdon").disabled = true;
	}
	
	//document.getElementById(prefix_component+"__listtonkho_ma_2").value = "";
	//document.getElementById("__listtonkho").value = "";
	document.getElementById(prefix_component + "DT_DM_CHI_DAN_MA").value = "";
}

function setOnload_tiepdon(){
	 highlightOnFocus();
	 myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
  	 myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
  	 myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
  	 myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
  	 myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
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
		
  	 var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
  	 var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	
  	 if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
  	 }
	
  	 var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	
  	 if (fromMenu == "yes"){		 
		 setDefaultValueForBanKham();
  	 }
}

function resetValueAddBaiThuoc(){
	highlightOnFocus();
	document.getElementById(prefix_component + "DM_BAI_THUOC").focus();
}

function reloadValueForCombobox(){
	setValueForNSXHSX();
	var checkKiemKeHid = document.getElementById(prefix_component + "checkKiemKeHid").value;
	if(checkKiemKeHid == "true"){
		alert("Kho thuốc đang kiểm kê. Không được phép xuất thuốc cho bệnh nhân.");
		document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
		document.getElementById(prefix_component + "DM_THUOC").value = "";
		document.getElementById(prefix_component + "DM_THUOC_MA").focus();
		return;
	}
	
	highlightOnFocus();
	document.getElementById(prefix_component + "__soluong").select();
	document.getElementById(prefix_component + "__soluong").focus();
	document.getElementById( 'DT_DM_CHI_DAN').value = document.getElementById(prefix_component + 'DT_DM_CHI_DAN_hidden').value;
	numberFormatBlur(document.getElementById(prefix_component + "__dongia"));	
	
//	var num = 0;
//	if (document.getElementById(prefix_component + "__dongia_HIDDEN").value != ''){
//		num = parseFloat( document.getElementById(prefix_component + "__dongia_HIDDEN").value);
//	}
//	document.getElementById(prefix_component + "__dongia").value = num;
	
	myOnblurTextbox(prefix_component + 'DM_BAI_THUOC_MA', 'DM_BAI_THUOC');

    setHiddenValueForCombobox();
    
    //document.getElementById("__listtonkho").value = "";
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
}

function setHiddenValueForCombobox(){
	document.getElementById(prefix_component + "DM_THUOC_HIDDEN").value = document.getElementById(prefix_component + "DM_THUOC").value;
	document.getElementById(prefix_component + "DM_QUOC_GIA_HIDDEN").value = document.getElementById("DM_QUOC_GIA").value;
	document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_HIDDEN").value = document.getElementById("DM_NHA_SAN_XUAT").value;
	//document.getElementById(prefix_component + "__donvi_HIDDEN").value = document.getElementById(prefix_component + "__donvi").value;
}

function setTenDonViTinh(){
	var ten = "";
	ten = getTenDonViTinhFromDmThuoc(document.getElementById(prefix_component + 'DM_THUOC_MA_pk').value);
	document.getElementById(prefix_component + "__donvi").value = ten;
}

function mySetValueForThuocPhongKham(){
	try{
	   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value ;
	   if (myValue == null || myValue =="" || myValue == "undefined"){
	   		
	   		//reset all data
	   		document.getElementById(prefix_component + "__dongia").value = "";
	   		document.getElementById(prefix_component + "__dongia_HIDDEN").value =  "";
	   		document.getElementById(prefix_component + "__yeucau").checked = false;
	   		document.getElementById(prefix_component + "__ndm").checked = false;
	   		document.getElementById(prefix_component + "__ndm_hid").value = false;
	   		document.getElementById(prefix_component + "__mien").checked = false;
	   		document.getElementById(prefix_component + "__mien_hid").value = false;
	   		document.getElementById(prefix_component + "__khongThu").checked = false;
	   		
	   		document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA").value = "";	 
	 		document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA_HIDDEN").value = "";
	   		document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value = "";	
	   		document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = "";	 
	   		document.getElementById(prefix_component + "hid_NCT").value = "";
	   		document.getElementById(prefix_component + "hid_NKP").value = "";
	   		document.getElementById(prefix_component + "hid_MaLK").value = "";
	   		document.getElementById(prefix_component + "hid_NCT_Ma").value = "";
	   		document.getElementById(prefix_component + "__ton").value = "" ;	
	   		document.getElementById(prefix_component + "__ton_HIDDEN").value = "";	
	  
			myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
			myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
			document.getElementById('DM_QUOC_GIA_2').value = document.getElementById('DM_QUOC_GIA').value;
			document.getElementById("DM_NHA_SAN_XUAT_2").value = document.getElementById("DM_NHA_SAN_XUAT").value;
			
			document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	        return;
	   }
	   
	   // 20110122 bao.ttc: check neu Ma thuoc = "" thi khong load cac thong tin o duoi
//	   if (document.getElementById(prefix_component + "DM_THUOC_MA").value == ""){
//		   //alert("Chưa nhập mã thuốc");
//		   document.getElementById(prefix_component + "DM_THUOC_MA").focus();
//		   return;
//	   }
	   
	   var i = myValue.indexOf("___"); //3 _   //1
	   var maSo = myValue.substring(0,i);   
	   //alert("maHang:"+maHang);
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _         //2
	   var DonGia = myValue.substring(0,i);
	   //alert("tenHang:"+tenHang);
	   document.getElementById(prefix_component + "__dongia").value = DonGia;
	   numberFormatBlur(document.getElementById(prefix_component + "__dongia"));
	   
	   document.getElementById(prefix_component + "__dongia_HIDDEN").value =  DonGia;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _          //3
	   var DonGiaBH = myValue.substring(0,i);
	   //alert("quyCach:"+quyCach);
	   //document.getElementById(prefix_component + "__quycach").value = quyCach;
	   
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _               //4
	   //var DonGiaYC = myValue.substring(0,i);
	   // 20110210 bao.ttc: remove vi THUOCPHONGKHAM_YEUCAU duoc load tu Action & DB
//	   if (DonGiaYC == "true"){
//	   		document.getElementById(prefix_component + "__yeucau").checked = true;
//	   }else{
//	   		document.getElementById(prefix_component + "__yeucau").checked = false;
//	   }
	   
	   
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _                //5
	   var NDM = myValue.substring(0,i);
	   //alert(DonGiaYC);
	   //alert("donVi:"+donVi);
	  if (NDM == "true"){
	   		
	   		document.getElementById(prefix_component + "__ndm").checked = true;
	   		document.getElementById(prefix_component + "__ndm_hid").value = true;
	   }else{
	   		document.getElementById(prefix_component + "__ndm").checked = false;
	   		document.getElementById(prefix_component + "__ndm_hid").value = false;
	   }
	   
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var DonGiaMP= myValue.substring(0,i);
	   //alert(DonGiaYC);
	   //alert("donVi:"+donVi);
	   if (DonGiaMP == "true"){
	   		
	   		document.getElementById(prefix_component + "__mien").checked = true;
	   		document.getElementById(prefix_component + "__mien_hid").value = true;
	   }else{
	   		document.getElementById(prefix_component + "__mien").checked = false;
	   		document.getElementById(prefix_component + "__mien_hid").value = false;
	   }
	   
	   //try{	    
	   // document.getElementById(prefix_component + "__donviMain").value = donVi;	    	   
	   //}catch(e){	   
	   //}
	   
	   myValue = myValue.substring(i+3);  
	   
	   i = myValue.indexOf("___"); //3 _
	   //var KhongThu = myValue.substring(0,i);
	   // 20110210 bao.ttc: remove vi THUOCPHONGKHAM_KHONGTHU duoc load tu Action & DB
//	   if (KhongThu == "true"){
//	   		document.getElementById(prefix_component + "__khongThu").checked = true;
//	   }else{
//	   		document.getElementById(prefix_component + "__khongThu").checked = false;
//	   }
	   
	    myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var maHSX = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA").value = maHSX;	 
	   document.getElementById(prefix_component + "DM_NHA_SAN_XUAT_MA_HIDDEN").value = maHSX;
	  
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var maQG = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value = maQG;	
	   document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = maQG;	  
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var  NCT = myValue.substring(0,i);  
	   //alert(NCT)
	   document.getElementById(prefix_component + "hid_NCT").value = NCT;
	
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var NKP = myValue.substring(0,i);  
	     //alert(NKP)
	   document.getElementById(prefix_component + "hid_NKP").value = NKP;
	
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var MaLK = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "hid_MaLK").value = MaLK;
	
	  myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var  NCT_Ma = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "hid_NCT_Ma").value = NCT_Ma;
	
	     myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var NKP_Ma = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "hid_NKP_Ma").value = NKP_Ma;
	   
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var ton = myValue.substring(0,i);  
	    document.getElementById(prefix_component + "__ton").value = groupingNumber(ton) ;	
	   document.getElementById(prefix_component + "__ton_HIDDEN").value = ton;	
	  
	  //alert(document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value);
		myOnblurTextbox(prefix_component + 'DM_QUOC_GIA_MA', 'DM_QUOC_GIA');
		myOnblurTextbox(prefix_component + 'DM_NHA_SAN_XUAT_MA', 'DM_NHA_SAN_XUAT');
		document.getElementById('DM_QUOC_GIA_2').value = document.getElementById('DM_QUOC_GIA').value;
		document.getElementById("DM_NHA_SAN_XUAT_2").value = document.getElementById("DM_NHA_SAN_XUAT").value;	
		document.getElementById(prefix_component + "DM_QUOC_GIA_MA_HIDDEN").value = document.getElementById(prefix_component + "DM_QUOC_GIA_MA").value;
	
		myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var ngayChinhSua = myValue.substring(0,i);  
	//
	
	 myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var NamNhap = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "NamNhap_HIDDEN").value = NamNhap;	
	//
	
	 myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var NgayHanDung = myValue.substring(0,i);  
	    document.getElementById(prefix_component + "NgayHanDung_HIDDEN").value = NgayHanDung;	
	//
	
	    myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var ThangHanDung = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "ThangHanDung_HIDDEN").value = ThangHanDung;	
	//
	
	   myValue = myValue.substring(i+3); 
	   i = myValue.indexOf("___"); //3 _
	   var NamHanDung = myValue.substring(0,i);  
	   document.getElementById(prefix_component + "NamHanDung_HIDDEN").value = NamHanDung;	
		
     }catch(e){
       alert("error at mySetValueForThuocPhongKham: " + e.description);
     }  
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
		document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
		document.getElementById(prefix_component + "DM_THUOC").value = "";
		document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	}else{
		var ton = document.getElementById(prefix_component + "__ton").value;
		if(ton > 0){
			setFocus_soluong();			
		}else{
			alert("Số lượng tồn kho đã hết.");
			document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
			document.getElementById(prefix_component + "DM_THUOC").value = "";
			document.getElementById(prefix_component + "DM_THUOC_MA").focus();
		}
	}	
}

function setDefaultValueForBanKham(){
  if (document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value == null || document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value ==""){
  	var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_3_xutrithuoc_bankham");
	if (bankhamClientDefault) {		
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = bankhamClientDefault.Ten;
		myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');		
	}
  }	
}

function setDefaultValueForBacSi(){
  if (document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value == null || document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value ==""){
  	var bacSiClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_3_xutrithuoc_bacsi");
	if (bacSiClientDefault) {		
		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value = bacSiClientDefault.Ten;
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');		
	}
  }	
}
// luu y : chi goi khi nhan tu menu
function luuTruGiaTriClientDefault(){
	var fromMenu = document.getElementById(prefix_component + "fromMenu").value;
	if (fromMenu == "yes"){
		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_3_xutrithuoc_bankham");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121201;
				chValues[1] = "B121_3_xutrithuoc_bankham";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_3_xutrithuoc_bankham", chNames,chValues );
				
			}    	
		}
		
		
	}
	
	/// bac si mac dinh
		var giaTriBacSiMa = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;
		//alert(giaTriBacSiMa);
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBacSiMa != null && giaTriBacSiMa != ""){
			var bacSiClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B121_3_xutrithuoc_bacsi");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bacSiClientDefault == null || bacSiClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 121302;
				chValues[1] = "B121_3_xutrithuoc_bacsi";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBacSiMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B121_3_xutrithuoc_bacsi", chNames,chValues );
				
			}    	
		}
}


