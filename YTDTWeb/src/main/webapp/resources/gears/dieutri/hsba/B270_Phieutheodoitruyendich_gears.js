function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	
        	//setAttrForCombobox(prefix_component + "DM_THUOC_MA", "DM_THUOC_span", "DM_THUOC", "getDmThuoc()", "", "getThuoc();setTenDonViTinh()", "");        	
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmBacSi()","","","");    
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2','DT_DM_NHAN_VIEN_span2', 'DT_DM_NHAN_VIEN__2',"getDtDmBacSi()","","","");        	
        	//setAttrForComboboxForNoDB(prefix_component + '__listtonkho_duocpham_ma','__listtonkho_span', '__listtonkho_duocpham',"","onBlurThuoc(); document.getElementById(prefix_component + '__soluong').focus();","myOnblurComboboxWithNoDatabase(\"" +prefix_component + "__listtonkho_duocpham_ma\",\"__listtonkho_duocpham\");onBlurThuoc(); document.getElementById(prefix_component + '__soluong').focus(); ","");
          // timer.setTimeout(function(){setOnload()},10); 
            
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}

function setTenDonViTinh(){
	var ten = "";
	ten = getTenDonViTinhFromDmThuoc(document.getElementById(prefix_component + 'DM_THUOC_MA_pk').value);
	document.getElementById(prefix_component + "__donvi").value = (ten == '' ? ten : "(" + ten + ")");

}
function onBlurThuoc() {
	//alert(mydatatodisplay);
	var val = document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value;	
	if (val != null && val != "" && val != "undefined") {
		var arrVal = val.split("___");
		document.getElementById(prefix_component + "__tonkhoma").value = arrVal[0];		
	} else {
		document.getElementById(prefix_component + "__tonkhoma").value = "";
		document.getElementById("__listtonkho_duocpham").value = "";
	}
	//var myComboboxTmp = dijit.byId("__listtonkho_duocpham");
	//myComboboxTmp._popupWidget.clearResultList();
}
function getThuoc() {
	arrayValueReceivedFromServer= new Array(50);
	arrayIDReceivedFromServer= new Array(50);
    var xml;
    var data;
    var tonkhoMa = document.getElementById(prefix_component + "__tonkhoma").value;
    
    if (document.getElementById(prefix_component + "DM_THUOC_MA").value != "") {
    	var tutruc_pdtValue = "0";  // lay thuoc tu kho
    	    	    	
    	var myCondition = document.getElementById(prefix_component + "DM_THUOC_MA").value;
    	myCondition = myCondition + "___" + tutruc_pdtValue;
    	var khoamaValue = document.getElementById(prefix_component + "THUOCNOITRU_KHOA").value;
    	myCondition = myCondition + "___" + khoamaValue;
    	var xmlHttp = createXmlHttpRequest();
	    var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI("GetThuocAndPhongAction") + "&xmlData=" + encodeURI(myCondition);	    
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function() {
	       if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	       		var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				
				// Phuc comment code below
				// Khong can kiem tra ton kho luc nay, vi form nay chi can cap nhat so lieu
				// khong can xuat kho
				/*
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
				     
				     	var myTextboxId = document.getElementById(prefix_component + "DM_THUOC_MA");
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
				*/
				// Phuc comment end
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
				// alert(giaTriDauTien);
				var textValue = "";
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
					       	
					       	if (data1.TonKhoMa == tonkhoMa) {
					       		document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value = myId;
					       		textValue = myTitle;
					       	}
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
					      	
					      	//alert(myId);					      	
					       	var myTitle =  formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung);
					       	
					       	if (data1.TonKhoMa == tonkhoMa) {
					       		document.getElementById(prefix_component + "__listtonkho_duocpham_ma").value = myId;
					       		textValue = myTitle;
					       	}
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
				search.store = CatalogStore;
				document.getElementById("__listtonkho_duocpham").value = textValue;											
			}
	    };	    
	   	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);     	
    }
}