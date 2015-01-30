function init() {
	
    if (window.google && google.gears) { 
    	
        try {
            	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA","DT_DM_NHAN_VIEN_span","DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");
            	
            	setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","onChangeLoaiPhieu()","","");
				setAttrForCombobox(prefix_component + "PHANLOAI_MA","DM_PHAN_LOAI_THUOC_span","DM_PHAN_LOAI_THUOC","getDmPhanLoaiThuocByLoaiThuoc(\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","onChangePhanLoai()","","");
				setAttrForCombobox(prefix_component + "KHO_MA","DT_DM_KHO_span","DT_DM_KHO","getDtDmKho()","","setHiddenKhoaMaSo()","");
				
				 var tenCt = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	            if (tenCt == "QuanLyKhoChinh") {
	            	tenCt = "KC";
	            } else if (tenCt == "QuanLyKhoLe") {
	            	tenCt = "KL";
	            } else if (tenCt == "QuanLyKhoBHYT") {
	            	tenCt = "BHYT";
	            }
            
            	setAttrForCombobox(prefix_component + "MAKHONHAN_MA","DM_KHOA__1_span","DM_KHOA__1","getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")","","","");
			
				//setAttrForCombobox(prefix_component + "MAKHONHAN_MA","DM_KHOA__1_span","DM_KHOA__1","getDmKhoaByMaSo(\"" + prefix_component + "KHO_CHA" + "\")","","","");
				
				
				var ten4112_4114 = document.getElementById(prefix_component + "ten4112_4114").value;
				
				if (ten4112_4114 == "4112"){
				   
					setAttrForCombobox(prefix_component + "MAKHOTRA_MA","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_Le_BHYT()","onChangeThuoc()","","");
					//alert(ten4112_4114);
				}else if (ten4112_4114 == "4114"){
					setAttrForCombobox(prefix_component + "MAKHOTRA_MA","DM_KHOA__2_span","DM_KHOA__2","getDmKhoa_NoiTru()","onChangeThuoc()","","");
				}
							
				
				setAttrForCombobox(prefix_component + "CHUONGTRINH_MA","DM_NGUON_CHUONG_TRINH_span","DM_NGUON_CHUONG_TRINH","getDmNguonChuongTrinh()","onChangeThuoc()","","");
				setAttrForCombobox(prefix_component + "NGUONKP_MA","DM_NGUON_KINH_PHI_span","DM_NGUON_KINH_PHI","getDmNguonKinhPhi()","onChangeThuoc()","","");
				setAttrForCombobox(prefix_component + "NGUOILAP_MA","DT_DM_NHAN_VIEN__1_span","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");
				setAttrForCombobox(prefix_component + "NGUOIKY_MA","DT_DM_NHAN_VIEN__2_span","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");
				
				setAttrForCombobox(prefix_component + "__maso","DM_THUOC_span","DM_THUOC","getDmThuocByPhanLoaiThuocAndLoaiThuoc(\"" + prefix_component + "PHANLOAI_MA_pk" + "\",\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","onChangeThuoc()","getTonkho()","");
				
				//setAttrForComboboxJSFForPTraKhoaPhong('__listtonkho_span', '__listtonkho','10');
				setAttrForComboboxForNoDB(prefix_component + '__listtonkho_ma_2','__listtonkho_span', '__listtonkho',"","mySetValueForPTraKhoaPhong(); document.getElementById('" + prefix_component + "__xuat').focus(); ","statusEnterAnhTabForChon = 0;myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_ma_2\",\"__listtonkho\"); mySetValueForPTraKhoaPhong(); document.getElementById('" + prefix_component + "__xuat').focus();","statusEnterAnhTabForChon = 1");
            	
				
            	timer.setTimeout(function(){focusInit();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}



function focusInit(){
	
	
		var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
		if (kc_value == 'KC'){
			document.getElementById(prefix_component + "MAKHONHAN_MA").value = "KCH";
		}else  if (kc_value == 'BHYT'){
			document.getElementById(prefix_component + "MAKHONHAN_MA").value = "KBH";
		}
		myOnblurTextbox(prefix_component + "MAKHONHAN_MA", "DM_KHOA__1");
		
		document.getElementById("__divInPhieu").style.display = "none";
		//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA","DT_DM_NHAN_VIEN");
		document.getElementById(prefix_component + "__maphieu").focus();
	
}

var dataList;

function getTonkho() {
	try{	
		var maHang = document.getElementById(prefix_component + "__maso").value;
		var kinhphiMa = document.getElementById(prefix_component + "NGUONKP_MA").value;
		var nguonMa = document.getElementById(prefix_component + "CHUONGTRINH_MA").value;
		var khoaXuat = document.getElementById(prefix_component + "MAKHOTRA_MA").value;
		//if (maHang == ""){
	  	//	document.getElementById(prefix_component + "__maso").focus();
	  	//	return;
		//}
		var request = maHang + " ;" + kinhphiMa + " ;" + nguonMa + " ;" + khoaXuat + " ; ";
    	var url = myContextPath + "/actionServlet?";
    	var xmlHttp = createXmlHttpRequest();
    	var params = "urlAction="+ encodeURI("GetTonKhoDuocPhamAction") + "&xmlData=" + encodeURI(request);
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function(){
    		handleStateChangeForB4114(xmlHttp);
    	};
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params); 
	}catch(e){
    	alert("getTonkho():" + e);
  	}	
}  

function handleStateChangeForB4114(xmlHttp){
	
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
	
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	
	  			var jsonExpression = "(" + xmlHttp.responseText + ")";
			    var data = eval(jsonExpression);
			    //alert(1);
			    
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
				    	//choPhepVuotQuaKhiKhongCoDuLieu = true;
				    	//alert("statusEnterAnhTabForChon:"+statusEnterAnhTabForChon);
				    	//if(myTextboxId.className.match(/focus/gi)) myTextboxId.className = myTextboxId.className.replace(/focus/gi,"");
				    	xyz = true;
				    	return;
				    }
				// ket thuc kiem tra
				xyz = false;
				
			    //alert(jsonExpression);
				
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
function listCatalogJSF_PTraKhoaPhong(myCombobox, data){
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
	   alert("catch .... in....listCatalogJSF_PTraKhoaPhong():"+e );
	}
}
*/

/*
function  setAttrForComboboxJSFForPTraKhoaPhong(_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPTraKhoaPhong(\'"+ comboboxId + "\')";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxJSFForPTraKhoaPhong :" +  e);
  }
}
*/
function mySetValueForPTraKhoaPhong(){
	try{ 
	   
	   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
	   if (myValue == null || myValue ==""){
	      return;
	   }
	   
	   var i = myValue.indexOf("___"); //3 _
	   var tenHang = myValue.substring(0,i);   
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
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
	   
	   i = myValue.indexOf("___"); //3 _
	   var donGia = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__dongia").value = donGia;
	   numberFormatBlur(document.getElementById(prefix_component + "__dongia"));
	   
	   document.getElementById(prefix_component + "__dongia_hid").value = donGia;
	   myValue = myValue.substring(i+3);  
	
	   i = myValue.indexOf("___"); //3 _
	   var tonKhoMa = myValue.substring(0,i);
	   document.getElementById(prefix_component + "__tonkhoma").value = tonKhoMa;
	   
	   i = myValue.indexOf("___"); //3 _
	   var malk = myValue.substring(0,i);
	   
	    
     }catch(e){
       alert("error at mySetValueForPTraKhoaPhong: " + e.description);
     }  
}

function thanhtienchitiet(){
	var soluong =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__xuat")));
	var dongia =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__dongia")));
	document.getElementById(prefix_component + "__thanhtien").value = dongia*soluong;	
	numberFormatBlur(document.getElementById(prefix_component + "__thanhtien"));
	 
	document.getElementById(prefix_component + "__thanhtien_hid").value = dongia*soluong;
}

function onChangeLoaiPhieu(){
	document.getElementById(prefix_component + "PHANLOAI_MA_pk").value = "";
	document.getElementById(prefix_component + "PHANLOAI_MA").value = "";
	document.getElementById("DM_PHAN_LOAI_THUOC").value = "";
	onChangePhanLoai();
}

function onChangeKho(){
	document.getElementById(prefix_component + "MAKHONHAN_MA_pk").value = "";
	document.getElementById(prefix_component + "MAKHONHAN_MA").value = "";
	document.getElementById("DM_KHOA__1").value = "";
}

function onChangePhanLoai(){
	document.getElementById(prefix_component + "__maso_pk").value = "";
	document.getElementById(prefix_component + "__maso").value = "";
	document.getElementById("DM_THUOC").value = "";
	onChangeThuoc();
}

function onChangeThuoc(){
	document.getElementById(prefix_component + "__tonkhoma").value = "";
	document.getElementById("__listtonkho").value = "";	
	//listCatalogJSF_PTraKhoaPhong("__listtonkho",null);
		clearDmt();
	document.getElementById(prefix_component + "__listtonkho_ma_2").value = "";
	document.getElementById(prefix_component + "__xuat").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__donvi_hid").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__dongia_hid").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";
	document.getElementById(prefix_component + "__thanhtien_hid").value = "";
	
}

 function clearDmt() {
 
 /*
	document.getElementById(prefix_component + "__maso").value = "";
	myOnblurTextboxForDmThuoc(prefix_component + "__maso", 'DM_THUOC', document.getElementById(prefix_component + 'LOAITHUOC_MA').value,document.getElementById(prefix_component + 'PHANLOAI_MA').value)
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__tonkho_hid").value = "";
	
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__donvi_hid").value = "";
	
	document.getElementById(prefix_component + "__dongia").value = "";
	   document.getElementById(prefix_component + "__dongia_hid").value = "";
	//  alert(1); 
	    document.getElementById(prefix_component + "__tonkhoma").value = "";
	    
	     document.getElementById(prefix_component + "__tonkhomalk").value = "";
	   */
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
	
	//document.getElementById(prefix_component + "__maso").focus();
}
function newChiTiet(){
    try{
		document.getElementById("DM_THUOC").value = "";
		document.getElementById(prefix_component + "__tonkho").value = "";
		document.getElementById(prefix_component + "__donvi").value = "";
		document.getElementById(prefix_component + "__dongia").value = "";
		document.getElementById(prefix_component + "__thanhtien").value = "";
		dataList = null;
		clearDmt();
	    //listCatalogJSF_PTraKhoaPhong("__listtonkho",null); 
	}catch(e){
		alert("newChiTiet: "+e.description);
	}
}

var lock = false;

function checkAddChiTiet(){
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
		var tonkho =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__tonkho")));//document.getElementById(prefix_component + "__tonkho").value;
		if (tonkho == "") {
			alert("Tồn kho rỗng");
			document.getElementById("__listtonkho").focus();
			result = false;
		
		}
	}
	var soluong = 0;
	if (result){
		soluong =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__xuat")));//document.getElementById(prefix_component + "__xuat").value;
		if (soluong == "") {
			alert("Phải nhập số lượng xuất");
			document.getElementById(prefix_component + "__xuat").focus();
			result = false;			
		}
	}
	if (result){
		var dongia =  parseFloat(formatRealNumber(document.getElementById(prefix_component + "__dongia")));//document.getElementById(prefix_component + "__dongia").value;
		if (parseFloat(tonkho) < parseFloat(soluong)) {
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

function checkDelChiTiet(){
	var result = true;
	if (lock) {
		result = false;
	}
	return result;
}

function loadInfoChitiet(){
	myOnblurTextbox(prefix_component + "__maso","DM_THUOC");
	document.getElementById(prefix_component + "__tonkho").value = document.getElementById(prefix_component + "__tonkho_hid").value;
	document.getElementById(prefix_component + "__donvi").value = document.getElementById(prefix_component + "__donvi_hid").value;
	document.getElementById(prefix_component + "__dongia").value = document.getElementById(prefix_component + "__dongia_hid").value;
	document.getElementById(prefix_component + "__thanhtien").value = document.getElementById(prefix_component + "__thanhtien_hid").value;
	getTonkho();
}

function displayAfterGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__maso").focus();
		return;
	}
	lockUnlockControlB4114(document.forms[0],1);
	document.getElementById("__divInPhieu").style.display = "block";
	document.getElementById("__divGhiNhan").style.display = "none";
}

function displayPhieuTraKho(){
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__maphieu").value = "";
		return;
	}
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA", "DT_DM_NHAN_VIEN");
	myOnblurTextbox(prefix_component + "MAKHONHAN_MA", "DM_KHOA__1");
	myOnblurTextbox(prefix_component + "MAKHOTRA_MA", "DM_KHOA__2");
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA", "DT_DM_NHAN_VIEN");
	myOnblurTextbox(prefix_component + "NGUOILAP_MA", "DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "NGUOIKY_MA", "DT_DM_NHAN_VIEN__2");
	document.getElementById("DM_THUOC").value = "";
	document.getElementById(prefix_component + "__tonkho").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__thanhtien").value = "";
	dataList = null;
	//listCatalogJSF_PTraKhoaPhong("__listtonkho",null); 
		clearDmt();
	displayAfterGhiNhan();
}

function resetAfterNhapMoi(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
	document.getElementById("DM_LOAI_THUOC").value="";	
	document.getElementById("DM_PHAN_LOAI_THUOC").value="";
	document.getElementById("DT_DM_KHO").value="";
	document.getElementById("DM_KHOA__1").value="";
	document.getElementById("DM_KHOA__2").value="";
	document.getElementById("DT_DM_NHAN_VIEN").value="";
	document.getElementById("DM_NGUON_KINH_PHI").value="";
	document.getElementById("DT_DM_NHAN_VIEN__1").value="";
	document.getElementById("DT_DM_NHAN_VIEN__2").value="";
	document.getElementById("DM_NGUON_CHUONG_TRINH").value="";
	document.getElementById("DM_THUOC").value="";
	document.getElementById(prefix_component + "LOAIPHIEU_MA_pk").value="";
	document.getElementById(prefix_component + "LOAIPHIEU_MA").value="";
	document.getElementById(prefix_component + "PHANLOAI_MA_pk").value="";
	document.getElementById(prefix_component + "KHO_MA_pk").value="";
	document.getElementById(prefix_component + "KHO_CHA").value="";
	document.getElementById(prefix_component + "KHO_MA").value="";
	document.getElementById(prefix_component + "PHANLOAI_MA").value="";
	document.getElementById(prefix_component + "NGUONKP_MA_pk").value="";
	document.getElementById(prefix_component + "NGUONKP_MA").value="";
	document.getElementById(prefix_component + "CHUONGTRINH_MA_pk").value="";
	document.getElementById(prefix_component + "CHUONGTRINH_MA").value="";
	document.getElementById(prefix_component + "__listtonkho_ma_2").value="";
	document.getElementById(prefix_component + "__tonkho").value="";
	document.getElementById(prefix_component + "__donvi").value="";
	document.getElementById(prefix_component + "__dongia").value="";
	document.getElementById(prefix_component + "__thanhtien").value="";
	dataList = null;
	//listCatalogJSF_PTraKhoaPhong("__listtonkho",null); 
	clearDmt();
	lockUnlockControlB4114(document.forms[0],0);
}

function lockUnlockControlB4114(form, type) {   
 
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
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

function setHiddenKhoaMaSo(){
	var maso;
	var ma = document.getElementById(prefix_component + "KHO_MA").value;
	var objArr = DtDmKho.filter("Ma like ?", ma).toArray();
	for (var i = 0; i < objArr.length; i++) {
		maso = objArr[i].DTDMKHO_KHOCHA;
	}
	document.getElementById(prefix_component + "KHO_CHA").value = maso; 
	
	
}
