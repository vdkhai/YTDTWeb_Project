function init() {
	
    if (window.google && google.gears) {
        try {
			setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien_Duoc()","","","");
           
			setAttrForCombobox(prefix_component + "DM_KHOA_MA_1","DM_KHOA__1_span","DM_KHOA__1","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
			
			var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
			//alert(kc_value);
			if (kc_value == 'BHYT'){
				setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_KhoaThanhLy()","","","");
			}if (kc_value == 'TE'){
				setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_KhoaThanhLy()","","","");
			}else {
				var thanhly = document.getElementById(prefix_component + "__thanhly").value;
				if (thanhly == 'TL'){
					setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_KhoaThanhLy()","","","");
				}else if (thanhly == 'TD'){
					setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA__2_span","DM_KHOA__2","getDmKhoaPhongDTNhom12()","","","");
				}else{
					setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_NoiTru()","","","");
				}
				
			}
			
			setAttrForCombobox(prefix_component + "NGUONKP_MA","DM_NGUON_KINH_PHI_span","DM_NGUON_KINH_PHI","getDmNguonKinhPhi()","","","");
			setAttrForCombobox(prefix_component + "NGUOILAP_MA","DT_DM_NHAN_VIEN__1_span","DT_DM_NHAN_VIEN__1","getDtDmNhanVien_Duoc()","","","");
			setAttrForCombobox(prefix_component + "NGUOIKY_MA","DT_DM_NHAN_VIEN__2_span","DT_DM_NHAN_VIEN__2","getDtDmNhanVien_Duoc()","","","");
			setAttrForCombobox(prefix_component + "CHUONGTRINH_MA","DM_NGUON_CHUONG_TRINH_span","DM_NGUON_CHUONG_TRINH","getDmNguonChuongTrinh()","","","");
        	setAttrForComboboxForNoDB(prefix_component + '__listtonkho_ma_2','__listtonkho_span', '__listtonkho',"","mySetValueForPXuatKhoaPhong(\'__listtonkho\');document.getElementById('" + prefix_component + "__xuat').focus();","statusEnterAnhTabForChon = 0; myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_ma_2\",\"__listtonkho\"); mySetValueForPXuatKhoaPhong(\'__listtonkho\');document.getElementById('" + prefix_component + "__xuat').focus(); ","statusEnterAnhTabForChon = 1");
            
        	 //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
            initshorcut();
        	
        	if (document.getElementById(prefix_component + "noinphieu").value == "true") {
				timer.setTimeout(function(){trove();},100);				
			}else{        	
				timer.setTimeout(function(){focusInit();},100);
            }
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }      
}

function initshorcut(){
    shortcut.add("Ctrl+A", function() {
        document.getElementById(prefix_component + "__themchinhsau").onclick();
    },{'type':'keydown',"propagate":false,'target':document});

    shortcut.add("Ctrl+C", function() {
    	document.getElementById(prefix_component + "__maso").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function resetData1(){
	document.getElementById(prefix_component + "__maso").value ="";
	document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value ="";
	var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	
	document.getElementById(prefix_component + "__listtonkho_ma_2").value="";
	document.getElementById(prefix_component + "__tonkhoma").value = "";
	document.getElementById(prefix_component + "__tonkhomalk").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__tongtien").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__xuat").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";	
	document.getElementById(prefix_component + "tmp_DM_THUOC").value ="";
}

function resetData2(){
	var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	
	document.getElementById(prefix_component + "__listtonkho_ma_2").value="";
	document.getElementById(prefix_component + "__tonkhoma").value = "";
	document.getElementById(prefix_component + "__tonkhomalk").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__tongtien").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__xuat").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";	
	document.getElementById(prefix_component + "tmp_DM_THUOC").value ="";
}

function resetData3(){	
	var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	document.getElementById(prefix_component + "__listtonkho_ma_2").value ="";
	document.getElementById(prefix_component + "__maso_pk").value ="";
	document.getElementById(prefix_component + "tmp_DM_THUOC").value ="";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
}

function resetData(){
	document.getElementById(prefix_component + "LOAITHUOC_MA_pk").value ="";
	document.getElementById(prefix_component + "LOAITHUOC_MA").value ="";
	document.getElementById(prefix_component + "DM_LOAI_THUOC"+'comboboxField').value ="";
	
	document.getElementById(prefix_component + "__maso").value ="";
	document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value ="";
	
	var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	
	document.getElementById(prefix_component + "__listtonkho_ma_2").value ="";
	document.getElementById(prefix_component + "__maso_pk").value ="";
	document.getElementById(prefix_component + "tmp_DM_THUOC").value ="";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";	
}

function getSoluongXuat() {
  try{
	var slXuat = 0;
	var countCt = document.getElementById(prefix_component + "__count").value;
	var malk = document.getElementById(prefix_component + "__tonkhomalk").value;
	//alert("malk: " + malk);
	for (var i = 0; i < countCt; i++) {
		var malkCol = document.getElementById(prefix_component + "listKetqua:" + i + ":malk").value;
		if (malkCol == malk) {
			slXuat += document.getElementById(prefix_component + "listKetqua:" + i + ":xuat").innerHTML;
		}
	}
	return slXuat;
  }catch(e){
  	return 0;
  }	
}


 function clearDmt() {
	//document.getElementById(prefix_component + "__maso").value = "";
	//myOnblurTextboxForDmThuoc(prefix_component + "__maso", 'DM_THUOC', document.getElementById(prefix_component + 'LOAITHUOC_MA').value,document.getElementById(prefix_component + 'PHANLOAI_MA').value);
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__donvi_hid").value = "";
	
	document.getElementById(prefix_component + "__dongia").value = "";
	   document.getElementById(prefix_component + "__dongia_hid").value = "";
	//  alert(1); 
	    document.getElementById(prefix_component + "__tonkhoma").value = "";
	    
	     document.getElementById(prefix_component + "__tonkhomalk").value = "";
	   
	//alert(2);
	
	//alert(3);
	var search = dijit.byId("__listtonkho");
	var jsonData = { identifier: "id", items: [], label: "title" };
	//alert(3);
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	search.store = CatalogStore;
	
	//alert(3);
	
	document.getElementById("__listtonkho").value = "";
	document.getElementById(prefix_component + "__listtonkho_ma_2").value = "";
	
	document.getElementById(prefix_component + "__maso").focus();
}

function mySetValueForPXuatKhoaPhong(comboboxId){
	//try{ 
	   
	   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
	   
	   //alert(myValue);
	   if (myValue == null || myValue == "" || myValue == "undefined") {
      		document.getElementById(prefix_component + '__listtonkho_ma_2').value = "";
      		clearDmt();
      		//alert(1);
      		return;
   	    }
	   
	   var i = myValue.indexOf("___"); //1 _
	   var tenHang = myValue.substring(0,i);   
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //2 _
	   var donVi = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__donvi").value = donVi;
	   document.getElementById(prefix_component + "__donvi_hid").value = donVi;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var tonKho = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__tonkho").value = tonKho;
	   numberFormatBlur(document.getElementById(prefix_component + "__tonkho"));
	     
	   document.getElementById(prefix_component + "__tonkho_hid").value = tonKho;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //4 _
	   var donGia = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__dongia").value = donGia;
	   numberFormatBlur(document.getElementById(prefix_component + "__dongia"));
	   
	   document.getElementById(prefix_component + "__dongia_hid").value = donGia;
	   myValue = myValue.substring(i+3);  
	
	   i = myValue.indexOf("___"); //5 _
	   var tonKhoMa = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__tonkhoma").value = tonKhoMa;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //6 _ ma lien ket
	   var malk = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__tonkhomalk").value = malk;
	   
	   //myValue = myValue.substring(i+3);
	    
     //}catch(e){
       //alert("error at mySetValueForPXuatKhoaPhong: " + e.description);
     //}  
   
   
}

function trove(){
	document.getElementById(prefix_component + "noinphieu").value = "false";
	document.getElementById("__divInPhieu").style.display = "block";
	document.getElementById("__divGhiNhan").style.display = "none";
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA", "DT_DM_NHAN_VIEN");
	//myOnblurTextbox(prefix_component + "LOAITHUOC_MA", "DM_LOAI_THUOC");
	//myOnblurTextbox(prefix_component + "PHANLOAI_MA", "DM_PHAN_LOAI_THUOC");
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_1", "DM_KHOA__1");
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_2", "DM_KHOA__2");
	myOnblurTextbox(prefix_component + "NGUONKP_MA", "DM_NGUON_KINH_PHI");
	myOnblurTextbox(prefix_component + "NGUOILAP_MA", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "NGUOIKY_MA", "DT_DM_NHAN_VIEN__2");
	myOnblurTextbox(prefix_component + "CHUONGTRINH_MA", "DM_NGUON_CHUONG_TRINH");
	lockUnlockControlB4123(document.forms[0],1);
	
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KTE";
	}else if(kc_value == 'NT'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KNT";
	}
	
	
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_1", "DM_KHOA__1");
	
	var thanhly = document.getElementById(prefix_component + "__thanhly").value;
				
	//var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'BHYT' || thanhly == 'TL'){
		document.getElementById(prefix_component + "DM_KHOA_MA_2").value = "KTL";
		myOnblurTextbox(prefix_component + "DM_KHOA_MA_2", "DM_KHOA__2");
	}
}
 
function focusInit(){
	document.getElementById("__divInPhieu").style.display = "none";
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA","DT_DM_NHAN_VIEN");
	document.getElementById(prefix_component + "LOAITHUOC_MA").focus();
	
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KTE";
	}else if(kc_value == 'NT'){
		document.getElementById(prefix_component + "DM_KHOA_MA_1").value = "KNT";
	}
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_1", "DM_KHOA__1");
	//alert(kc_value);
	var thanhly = document.getElementById(prefix_component + "__thanhly").value;
	if (kc_value == 'BHYT' || thanhly == 'TL'){
		document.getElementById(prefix_component + "DM_KHOA_MA_2").value = "KTL";
		myOnblurTextbox(prefix_component + "DM_KHOA_MA_2", "DM_KHOA__2");
	}
}

var dataList;

function getTonkho() {
  try{
	resetData3();
	var maHang = document.getElementById(prefix_component + "__maso").value;	
	var tenHang = document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value;
	if (tenHang == "") {
    	document.getElementById(prefix_component + "__maso").focus();	    	
		return;
	}
	var kinhphiMa = document.getElementById(prefix_component + "NGUONKP_MA").value;
	var nguonMa = document.getElementById(prefix_component + "CHUONGTRINH_MA").value;
	var khoaXuat = document.getElementById(prefix_component + "DM_KHOA_MA_1").value;
	
	/*
	if (maHang == ""){
	  document.getElementById(prefix_component + "__maso").focus();
	  return;
	}
	*/

	var request = maHang + " ;" + kinhphiMa + " ;" + nguonMa + " ;" + khoaXuat + " ; ";
    var url = myContextPath + "/actionServlet?";
    var xmlHttp = createXmlHttpRequest();
    var params = "urlAction="+ encodeURI("GetTonKhoDuocPhamAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    
    	handleStateChangeForB4123(xmlHttp);
    
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
	
	
  }catch(e){
    alert("getTonkho():" + e);
  }	
}  


function handleStateChangeForB4123(xmlHttp){
	/*
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
		
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		if ( data.list != null && typeof(data.list) == "object") {
	  		dataList = data.list;
      		listCatalogJSF_PXuatKhoaPhong("__listtonkho",dataList); 
        }
        
        
	 }*/
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
	
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
				    	
				    	var myTextboxId = document.getElementById(prefix_component + "__maso");
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
				    	 //document.getElementById('__listtonkho').focus();
				    	 dijit.byId('__listtonkho').focus();
				     }
				    xyz = false;
				// ket thuc kiem tra
				
	       		var search = dijit.byId("__listtonkho");
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
					    	var myId =  data1.TenHang + "___" + data1.DonVi + "___" + data1.TonKho  + "___" + data1.DonGia + "___" + data1.TonKhoMa + "___" + data1.Malk  + "___" + i; 
							
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = groupingNumber(data1.TonKho);
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					      	var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
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
							var myId =  data1.TenHang + "___" + data1.DonVi + "___" + data1.TonKho  + "___" + data1.DonGia + "___" + data1.TonKhoMa + "___" + data1.Malk  + "___" + i; 
							
					    	var tenHang = data1.TenHang;
					    	var hamLuong = data1.HamLuong;
					     	var tonKho = groupingNumber(data1.TonKho);
					    	var nuocSx = data1.NuocSx;
					      	var hangSx = data1.HangSx;
					      	var donVi = data1.DonVi;
					        var hanDung = data1.HanDung;
					      	var donGia = data1.DonGia;
					      	
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
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
 }
 
 /*
function listCatalogJSF_PXuatKhoaPhong(myCombobox, data){
	
	var myComboboxId = document.getElementById(myCombobox);
	try{
	   
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		var i = 0;
		var havingData = false;
		if (data != null){
			while (data.record[i] != null){            	
	                data1 = data.record[i];   
	                if(data1.TenHang != null && data1.TenHang != ""){       
	                  var myId =  data1.TenHang + "___" + data1.DonVi + "___" + data1.TonKho + "___" +
						data1.DonGia + "___" +  data1.TonKhoMa;       
	                    CatalogStore.newItem({id: myId , title:   data1.TenHang + "  " + data1.DonVi + "  " + data1.TonKho + "  " + data1.DonGia + "  " + data1.TonKhoMa});  
	                     havingData == true;
	                }
					i=i+1;
	         }
	         
	         if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	               if(data1.TenHang != null && data1.TenHang != ""){       
	                  var myId =  data1.TenHang + "___" + data1.DonVi + "___" + data1.TonKho + "___" +
						data1.DonGia + "___" +  data1.TonKhoMa;       
	                    CatalogStore.newItem({id: myId , title:   data1.TenHang + "  " + data1.DonVi + "  " + data1.TonKho + "  " + data1.DonGia + "  " + data1.TonKhoMa});  
	                     havingData == true;
	                }
	         
	         }
	    }else{
	    	myComboboxId.value = "";
	    }
	     if (havingData == false){
	    	myComboboxId.value = "";
	    }
		search.store = CatalogStore;	
	} catch(e){
	   alert("catch .... in....listCatalogJSF_PXuatKhoaPhong():"+e );
	}

}
*/
function  setAttrForComboboxJSFForPXuatKhoaPhong(_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPXuatKhoaPhong(\'"+ comboboxId + "\')";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxJSFForPXuatKhoaPhong :" +  e);
  }
}


function bindTenThuoc(){
	document.getElementById(prefix_component + "tmp_DM_THUOC").value = document.getElementById("DM_THUOC").value;
}

function reloadValueForCombobox(){
    //try{
		//document.getElementById(prefix_component + "__maso").focus();
		//document.getElementById("DM_THUOC").value = "";
		//document.getElementById(prefix_component + "__tonkho").value = "";
		//document.getElementById(prefix_component + "__donvi").value = "";
		//document.getElementById(prefix_component + "__dongia").value = "";
		//document.getElementById(prefix_component + "__thanhtien").value = "";
		//dataList = null;
	    //listCatalogJSF_PXuatKhoaPhong("__listtonkho",null); 
	    clearDmt();
	    document.getElementById(prefix_component + "__tongtien").value = document.getElementById(prefix_component + "__tongtien_hid").value;
	//}catch(e){
	//	alert("reloadValueForCombobox: "+e.description);
	//}
}

function checkAddDetail(){
	changeCursorWait ();
	
	var result = true;
	if (lock) {
		result = false;
	}
	if (result){
		var maso = document.getElementById(prefix_component + "__maso").value;
		if (maso == "") {
			alert("Phải nhập mã thuốc");
			document.getElementById(prefix_component + "__maso").focus();
			result = false;		
		}
	}
	
	if (result){
		var soluong = document.getElementById(prefix_component + "__xuat").value;
		if (soluong == "") {
			alert("Phải nhập số lượng xuất");
			document.getElementById(prefix_component + "__xuat").focus();
			result = false;			
		}
	}
	if (result){
	    var __xuat = document.getElementById(prefix_component + "__xuat").value;
	    var tonkho = document.getElementById(prefix_component + "__tonkho_hid").value;
	    //alert(tonkho);		
		var dongia = document.getElementById(prefix_component + "__dongia").value;
		if (parseFloat(__xuat) > parseFloat(tonkho)) {
			alert("Số lượng xuất phải bé hơn hoặc bằng số lượng tồn kho");
			document.getElementById(prefix_component + "__xuat").value = "";			
			document.getElementById(prefix_component + "__xuat").focus();
			result = false;			
		}
	}
	
		if (result == false){
	    		changeCursorDefault ();
	    	}
	
	return result;
}

function thanhtienchitiet(){	

	var soluong =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__xuat"))); 
	var dongia =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__dongia")));
	document.getElementById(prefix_component + "__thanhtien").value = dongia*soluong;	
	document.getElementById(prefix_component + "__thanhtien_hid").value = dongia*soluong;
	
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtien"));
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtien_hid"));
}

function onDeleteChiTietComplete(){
	document.getElementById("DM_THUOC").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";
	dataList = null;
	listCatalogJSF_PXuatKhoaPhong("__listtonkho",null); 
	document.getElementById(prefix_component + "__tongtien").value = document.getElementById(prefix_component + "__tongtien_hid").value;
}

function loadInfoChitiet(){
	//myOnblurTextbox(prefix_component + "__maso","DM_THUOC");
	document.getElementById(prefix_component + "__tonkho").value = document.getElementById(prefix_component + "__tonkho_hid").value;
	document.getElementById(prefix_component + "__donvi").value = document.getElementById(prefix_component + "__donvi_hid").value;
	document.getElementById(prefix_component + "__dongia").value = document.getElementById(prefix_component + "__dongia_hid").value;
	document.getElementById(prefix_component + "__thanhtien").value = document.getElementById(prefix_component + "__thanhtien_hid").value;
	getTonkho();
	//document.getElementById(prefix_component + "__xuat").focus();
}

function onChangePLThuocByLoaiThuoc(){
	document.getElementById(prefix_component + "PHANLOAI_MA_pk").value = "";	
	document.getElementById(prefix_component + "PHANLOAI_MA").value = "";	
	document.getElementById("DM_PHAN_LOAI_THUOC").value = "";
	onChangeThuocByPhanLoaiThuoc();
}

function onChangeThuocByPhanLoaiThuoc(){	
	document.getElementById(prefix_component + "__maso_pk").value = "";
	document.getElementById(prefix_component + "__maso").value = "";	
	document.getElementById("DM_THUOC").value = "";
}

function reNhapMoi(){
	var search = dijit.byId("__listtonkho");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho").value = "";
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
	document.getElementById("DT_DM_NHAN_VIEN").value="";
	document.getElementById("DM_LOAI_THUOC").value="";
	document.getElementById("DM_PHAN_LOAI_THUOC").value="";
	document.getElementById("DM_NGUON_KINH_PHI").value="";
	document.getElementById("DT_DM_NHAN_VIEN__1").value="";
	document.getElementById("DT_DM_NHAN_VIEN__2").value="";
	document.getElementById("DM_NGUON_CHUONG_TRINH").value="";
	document.getElementById("DM_THUOC").value="";
	document.getElementById(prefix_component + "__listtonkho_ma_2").value="";
	document.getElementById(prefix_component + "__tonkhoma").value = "";
	document.getElementById(prefix_component + "__tonkhomalk").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__tongtien").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";
	
	focusInit();
	lockUnlockControlB4123(document.forms[0],0);
}

function reLoadInfo(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__maso").focus();
		return;
	}
	//lockUnlockControlB4123(document.forms[0],1);
	document.getElementById("__divInPhieu").style.display = "block";
	document.getElementById("__divGhiNhan").style.display = "none";
	document.getElementById(prefix_component + "inphieu").focus();
	
}

function displayPhieuXuatKho(){
	if(document.getElementById(prefix_component + "noinphieu").value == "false"){
		document.getElementById("__divInPhieu").style.display = "block";
	}else{
		document.getElementById("__divInPhieu").style.display = "none";
	}
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__maphieu").value = "";
		document.getElementById(prefix_component + "__maphieu").focus();
		return;
	}
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA", "DT_DM_NHAN_VIEN");
	//myOnblurTextbox(prefix_component + "LOAITHUOC_MA", "DM_LOAI_THUOC");
	//myOnblurTextbox(prefix_component + "PHANLOAI_MA", "DM_PHAN_LOAI_THUOC");
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_1", "DM_KHOA__1");
	myOnblurTextbox(prefix_component + "DM_KHOA_MA_2", "DM_KHOA__2");
	document.getElementById(prefix_component + "__tongtien").value = document.getElementById(prefix_component + "__tongtien_hid").value;
	myOnblurTextbox(prefix_component + "NGUONKP_MA", "DM_NGUON_KINH_PHI");
	myOnblurTextbox(prefix_component + "NGUOILAP_MA", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "NGUOIKY_MA", "DT_DM_NHAN_VIEN__2");
	myOnblurTextbox(prefix_component + "CHUONGTRINH_MA", "DM_NGUON_CHUONG_TRINH");
	
	document.getElementById("DM_THUOC").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";
	dataList = null;
	listCatalogJSF_PXuatKhoaPhong("__listtonkho",null); 
	reLoadInfo();
}

function checkDelChiTiet(){
	var result = true;
	if (lock) {
		result = false;
	}
	return result;
}

var lock = false;
function lockUnlockControlB4123(form, type) {   
 
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