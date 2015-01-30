function init() {
	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');
            setAttrForCombobox(prefix_component + "DMKHOA_MA", "DM_KHOA_span", "DM_KHOA", "getDmKhoa_Ct(\""+ document.getElementById(prefix_component + "DM_KHO_XUAT").value +"\")", "", "", "");            
        	setAttrForComboboxForNoDB(prefix_component + '__listtonkho_duocpham_ma','__listtonkho_span', '__listtonkho_duocpham',"","onBlurThuoc(); document.getElementById('"+prefix_component + "__tienhanh').focus();","statusEnterAnhTabForChon = 0;myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_duocpham_ma\",\"__listtonkho_duocpham\");//; document.getElementById('"+prefix_component + "__tienhanh').focus(); ","statusEnterAnhTabForChon = 1");
            
        	timer.setTimeout(function(){onloadsetinfor();},100);	
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
}

function myoncomplete(){
	myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
	myOnblurTextbox(prefix_component + "LOAIPHIEU_MA", "DM_LOAI_THUOC");
	myOnblurTextbox(prefix_component + "DMTHUOC_MASO", "DM_THUOC");
}

function onloadsetinfor(){
	document.getElementById(prefix_component + "__tungay").value = document.getElementById(prefix_component + "__tungay_bean").value;
	document.getElementById(prefix_component + "__denngay").value = document.getElementById(prefix_component + "__denngay_bean").value;
	
	
	InitSetInfor();
	document.getElementById(prefix_component + "__thang").focus();
}

function clearListMaLienKet(){
	var search = dijit.byId("__listtonkho_duocpham");
    var jsonData = { identifier: "id", items: [], label: "title" };
    var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
    CatalogStore.newItem({id: "" , title: "" });
	search.store = CatalogStore;
	// Clear listtonkho textfile				
	document.getElementById("__listtonkho_duocpham").value = "";
	// phai xoa __listtonkho_duocpham_ma de xoa hoan toan thong tin Thuoc truoc
	document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value = "";
	document.getElementById(prefix_component + "__tonkhoma").value = "";
	document.getElementById(prefix_component + "__tonkhomalk").value = "";
}

function resetData(){				
	document.getElementById(prefix_component +"DM_LOAI_THUOC"+'comboboxField').value = "";
	document.getElementById(prefix_component +"LOAIPHIEU_MA").value = "";
	document.getElementById(prefix_component +"LOAIPHIEU_MA_pk").value = "";
	
	document.getElementById(prefix_component +"DM_THUOC"+'comboboxField').value = "";
	document.getElementById(prefix_component +"DMTHUOC_MASO").value = "";
	document.getElementById(prefix_component +"DMTHUOC_MASO_pk").value = "";
	clearListMaLienKet();
}

function resetData1(){
	document.getElementById(prefix_component +"DM_THUOC"+'comboboxField').value = "";
	document.getElementById(prefix_component +"DMTHUOC_MASO").value = "";
	document.getElementById(prefix_component +"DMTHUOC_MASO_pk").value = "";
}

function InitSetInfor(){
	//document.getElementById(prefix_component + "__thang").focus();
	//document.getElementById("DM_KHOA").disabled = true;
	//document.getElementById(prefix_component + "DMKHOA_MA").disabled = true;
	var kc_value = document.getElementById(prefix_component + "DM_KHO_XUAT").value;
	if (kc_value == 'KC'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KCH";
	}else  if (kc_value == 'BHYT'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KBH";
	}else  if (kc_value == 'TE'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KTE";
	}else  if (kc_value == 'NT'){
		document.getElementById(prefix_component + "DMKHOA_MA").value = "KNT";
	}
	myOnblurTextbox(prefix_component + "DMKHOA_MA", "DM_KHOA");
	myOnblurTextbox(prefix_component + "DMTHUOC_MASO", 'DM_THUOC');	
	resetData();
}

function onBlurThuoc() {
	//alert(mydatatodisplay);
	var val = document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value;	
	//alert(val);
	if (val != null && val != "" && val != "undefined") {
		var arrVal = val.split("___");
		document.getElementById(prefix_component + "__tonkhoma").value = arrVal[0];
		document.getElementById(prefix_component + "__tonkhomalk").value = arrVal[7];	
	} else {
		document.getElementById(prefix_component + "DMTHUOC_MASO").focus();
	}

}
function setNgayThangBatDauKetThuc(thang,nam,tungay,denngay){
	  var thangVaoVien = document.getElementById(prefix_component + thang).value;
	  var namVaoVien = document.getElementById(prefix_component + nam).value;
	  var ngayhientai = document.getElementById(prefix_component + '__ngayhientai').value;
	  var thangHt = ngayhientai.substring(3,5);
	  var namHt = ngayhientai.substring(6);
	   
	  if ( namVaoVien == "" && namVaoVien.length == 0){
	     return;
	  }
	  
	  else if(namVaoVien < 1900 || namVaoVien > namHt){
		  	document.getElementById(prefix_component + nam).value = '';
		  	document.getElementById(prefix_component + nam).focus();
		  	return;
	  }
	  else if ( parseInt(namVaoVien) < 1900 || parseInt(namVaoVien) > 2300 ){
		  	 document.getElementById(prefix_component + nam).focus();
		     return;
	  }
	  else{
  
		  var thangVaoVien_Temp = thangVaoVien;
		  var thangVaoVien_Temp_2 = thangVaoVien;
		  
		  if (thangVaoVien != "" && thangVaoVien.length > 0){
		  	if (thangVaoVien.length == 1){
		  	   thangVaoVien_Temp = "0" + thangVaoVien;
		  	   thangVaoVien_Temp_2 = "0" + thangVaoVien;
		  	}
		  }
		  else{
		  	   thangVaoVien_Temp = "01";  
		  	   thangVaoVien_Temp_2="12";
		  	   thangVaoVien="1";
		  	   //document.getElementById(prefix_component + thang).value = "1";
		  }

		  document.getElementById(prefix_component + tungay).value="01/" + thangVaoVien_Temp + "/" + namVaoVien;  
	  	  if(thangVaoVien == thangHt && namVaoVien == namHt){
			  	document.getElementById(prefix_component + denngay).value= ngayhientai;
		  }
		  else{
			  	document.getElementById(prefix_component + denngay).value= getLastDayOfMonth (parseInt(thangVaoVien),parseInt(namVaoVien)) + "/" + thangVaoVien_Temp_2 + "/" + namVaoVien;
		  }

  	}
}


function getThuoc() {
	clearListMaLienKet();
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
    var xml;
    //var data;
   
    if (document.getElementById(prefix_component + "DMTHUOC_MASO") != "") {
    	//alert(1);
    	
    	var masothuoc = document.getElementById(prefix_component + "DMTHUOC_MASO_pk").value;
    	var tenHang = document.getElementById(prefix_component + "DM_THUOC"+'comboboxField').value;
		if (tenHang == ""){
			document.getElementById(prefix_component + "DMTHUOC_MASO_pk").value ="";
			document.getElementById(prefix_component + "DMTHUOC_MASO").value ="";			
			document.getElementById(prefix_component + "DMTHUOC_MASO").focus();
			return;
		}
    	var khoamaValue = document.getElementById(prefix_component + "DMKHOA_MA_pk").value;

    	var tungay = document.getElementById(prefix_component + "__tungay").value;
    	var denngay = document.getElementById(prefix_component + "__denngay").value;
    	
    	var myCondition  = masothuoc + "___" + khoamaValue+"___" + tungay + "___" + denngay + "___" ;
    	
    	//alert(myCondition);
    	var xmlHttp = createXmlHttpRequest();
	    var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI("GetThuocForTheKhoAction") + "&xmlData=" + encodeURI(myCondition);
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function() {
	       if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	       		var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = null;
				data = eval(jsonExpression);
				var search = dijit.byId("__listtonkho_duocpham");
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				
				/// kiem tra co ton tai ma lien ket cua ma thuoc do khong			
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
				
				 if (havingData == false){
					 var myTextboxId = document.getElementById(prefix_component + "DMTHUOC_MASO");
				     
				     if (myTextboxId  == null){
				     		return;
				     }
				     
				     if (myTextboxId.value != null && myTextboxId.value != ""){
				     		alert("Không tồn tại mã liên kết của mã thuốc " + myTextboxId.value );
				     }
				     // Clear tonkho list (dojo)
				     CatalogStore.newItem({id: "" , title: "" });
					 search.store = CatalogStore;
					 // Clear listtonkho_duocpham textfile				
					 document.getElementById("__listtonkho_duocpham").value = "";				     	
				    	
				    myTextboxId.focus();
				    myTextboxId.select();
				    
				    xyz = true;				    	
				    return;
				}else{
					dijit.byId('__listtonkho_duocpham').focus();
				}
				xyz = false;
				// ket thuc kiem tra				  
	       		
				var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "", 
													"Đơn vị", "Nước sx", "Hãng sx", "", "Hạn dùng");
				CatalogStore.newItem({id: "", title: firstTitle});
				   
				i = 0;
				var giaTriDauTien = "";
				var giaTriNameDauTien="";
				var sohangdangco = 0;
				// alert(giaTriDauTien);
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
					      	//alert(i);
					      	//if(i = 0){
					      		//tenHang = ten" a";
					      	//}
					      	//alert(tenHang);
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, "", donVi, nuocSx, hangSx, "", hanDung);
					       	for(var j = 0; j < arrayValueReceivedFromServer.length; j++){
						     	if(myTitle == arrayValueReceivedFromServer[j]){					       		
						       		myTitle = myTitle + " ";
						       	}
					       	}
					       	CatalogStore.newItem({id: myId , title: myTitle  });
					       	//alert(myId);					       	
					      
					       	//alert(myTitle);
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
					      	
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, "", donVi, nuocSx, hangSx, "", hanDung);
					       	CatalogStore.newItem({id: myId , title: myTitle  });  
					       	 //havingData = true;
					       	if (giaTriDauTien == ""){
					       		giaTriDauTien = myId ;
					       	} 
					       	if (giaTriNameDauTien == ""){
					       		giaTriNameDauTien = myTitle ;
					       	}
					       	sohangdangco = sohangdangco + 1;
					       	
					       	arrayIDReceivedFromServer[i]=  myId;
					       	arrayValueReceivedFromServer[i]=  myTitle;
						}
					}
				} 
				// alert(giaTriDauTien);
				search.store = CatalogStore;
				
				
				
				//if (sohangdangco > 1){				
				
				dijit.byId('__listtonkho_duocpham')._myfunction();		
				try{
					dijit.byId('__listtonkho_duocpham')._showResultList();	
			    }catch(e){
			    
			    }
		  			  		
				
				//alert(giaTriNameDauTien);
				//document.getElementById('__listtonkho_duocpham').value = giaTriNameDauTien;
				
				//}else{
				//	document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value=giaTriDauTien;
				//	onBlurThuoc();
				//	document.getElementById(prefix_component + '__soluong').focus();
				//}
				//
				
				
		  		
	  		    
	  		    //alert(giaTriDauTien);
	  		    
	  		    //dijit.byId('__listtonkho_duocpham').value = giaTriDauTien;
	  			//alert(giaTriDauTien);
			}	     
	    };	   
	   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);     	
	   	
    }
}
/*
function getDvt() {
	var thuocMa = document.getElementById(prefix_component + "DMTHUOC_MASO").value;
	if (thuocMa != "") {
		var thuoc = DmThuoc.filter("Ma like ?", thuocMa).first();
		var dvt = DmDonViTinh.getByFieldValue("MaSo", thuoc.DMDONVITINH_MASO);
		document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = dvt.Ma;
	} else {
		document.getElementById(prefix_component + "DTDMDONVITINH_MA").value = "";
	}
	
	myOnblurTextbox(prefix_component + "DTDMDONVITINH_MA", 'DM_DON_VI_TINH');
}
*/
function getLastDayOfMonth(month,year)
{
	var day;

	switch(month)
	{
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10:
		case 12:
			day = 31;
			break;
		case 4 :
		case 6 :
		case 9 :
		case 11:
		   	day = 30;
			break;
		case 2 :
			if( ( (year % 4 == 0) && ( year % 100 != 0) ) 
                           || (year % 400 == 0) )
				day = 29;
			else
				day = 28;
			break;

	}

	return day;
}
