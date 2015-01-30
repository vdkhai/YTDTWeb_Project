function init() {
    if (window.google && google.gears) {
        try {
        	/*
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
            setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
            setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");
        	//setAttrForCombobox(prefix_component + 'DM_THUOC_MASO','DM_THUOC_span', 'DM_THUOC', "getDmThuocByLoai(\"" + prefix_component + "DT_DM_LOAI_MA\")", "", "getTonkho()", "");
            //setAttrForComboboxJSFForPhieuXuat('__listtonkho_span', '__listtonkho_duocpham','10');
            */
            setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","",""); 
            //setAttrForCombobox(prefix_component + "BANKHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","","");
            document.getElementById(prefix_component + "__maphieu").focus();
           
            timer.setTimeout(function(){tempOnLoad();},100); 
            
            
        } catch (e) {
        	alert("init error: " + e.description);
        }
	}
}
function tempOnLoad(){
  onCompleteDisplay();
  onCompleteTiepDon();
}
/*
var dataList;
function getTonkho() {
	//alert("getTonkho();");
  	try {
  		var search = dijit.byId("__listtonkho_duocpham");
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		search.store = CatalogStore;
		dataList = null;

		var maHang = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
		if (maHang == "") {
	    	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
			return;
		}
		
		var kinhphiMa = " ";
		var nguonMa = " ";
		var khoaXuat = "KHA";
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
				var data = eval(jsonExpression);

				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
       				listCatalogJSF_PXUAT("__listtonkho_duocpham", data.list); 
				}
				
			}
		};
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
		xmlHttp.send(params);
		
	} catch(e) {
		alert("getTonkho():" + e.description);
	}
}
*/
/*
function clearDmt() {
	
	var search = dijit.byId("__listtonkho_duocpham");
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	search.store = CatalogStore;
	
	document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";
	document.getElementById("DM_THUOC").value = "";
	document.getElementById("__listtonkho_duocpham").value = "";
	try {
		document.getElementById(prefix_component + "__soluong").value = "";
		document.getElementById(prefix_component + "__tonkhoma_hid").value = "";
		document.getElementById(prefix_component + "__tonkho_hid").value = "";
		document.getElementById(prefix_component + "__tonkho").value = "";
		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	} catch (e) {
		alert(e.description);
	}
	//alert("1");
	document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
}
*/
function onCompleteTiepDon() {
	//var maBanKham = document.getElementById(prefix_component + "BANKHAM_MA");
	var maTiepDon = document.getElementById(prefix_component + "__matiepdon");
	//if (maBanKham.value == "") {
	//	maBanKham.focus();
	//	return;
	//}
	/*
	myOnblurTextbox(prefix_component + "TINH_MA", 'DM_TINH');
	myOnblurTextbox(prefix_component + "HUYEN_MA", 'DM_HUYEN');
	myOnblurTextbox(prefix_component + "XA_MA", 'DM_XA');
	myOnblurTextbox(prefix_component + "__madoituong", 'DM_DOI_TUONG');
	myOnblurTextbox(prefix_component + "__madantoc", 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + "TINH_MA", 'DM_TINH');
	*/
	//myOnblurTextbox(prefix_component + "BANKHAM_MA", 'DT_DM_BAN_KHAM');
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
	
	
	var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
	}
	
	tinhTien();
	//document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
}

function onCompleteTiepTucNhap() {
	tinhTien();
	soluongMax = "0";
	clearDmt();
}

function onCompleteSelect() {
highlightOnFocus();
	//clearDmt();
	tinhTien();
	//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", 'DM_THUOC', '', '');
	
}

function onCompleteDelete() {
highlightOnFocus();
	tinhTien();
	clearDmt();
}


function onCompleteDisplay() {

	highlightOnFocus();
	
	var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "1") {
	//alert(1);
		tinhTien();
		//alert(2);
		onCompleteTiepDon();
		document.getElementById("divGhiNhan").style.display = "none";
		document.getElementById("divIn").style.display = "block";
		document.getElementById("divCLS").style.display = "none";
		//lockUnlockControl(document.forms[0], 1)
	} else {
		//lockUnlockControl(document.forms[0], 0)
		document.getElementById("divGhiNhan").style.display = "block";
		document.getElementById("divIn").style.display = "none";
		document.getElementById("divCLS").style.display = "block";
	}
}

function onCompleteReset() {
highlightOnFocus();
	document.forms[0].reset();
	var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "1") {
		document.getElementById("divGhiNhan").style.display = "none";
		document.getElementById("divIn").style.display = "block";
		document.getElementById("divCLS").style.display = "block";
		//lockUnlockControl(document.forms[0], 1)
	} else {
		//lockUnlockControl(document.forms[0], 0)
		document.getElementById("divGhiNhan").style.display = "block";
		document.getElementById("divIn").style.display = "none";
		document.getElementById("divCLS").style.display = "none";
	}
}

function onCompleteGhiNhan() {
highlightOnFocus();
	var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	if (isUpdate == "1") {
		document.getElementById("divGhiNhan").style.display = "none";
		document.getElementById("divIn").style.display = "block";
		document.getElementById("divCLS").style.display = "block";
		//lockUnlockControl(document.forms[0], 1)
	} else {
		//lockUnlockControl(document.forms[0], 0)
		document.getElementById("divGhiNhan").style.display = "block";
		document.getElementById("divIn").style.display = "none";
		document.getElementById("divCLS").style.display = "none";
	}
}

function tinhTien() {
/*
	var countCt = document.getElementById(prefix_component + "count").value;
	//alert("count: " + countCt);
	var ttCt = 0;
	//alert(1);
	for (var i = 0; i < countCt; i++) {
	    // alert(2);
	    var objTmp = document.getElementById(prefix_component + "listTpk:" + i + ":thanhtien");
	    if (objTmp == null){
	      return;
	    }
		var tt = document.getElementById(prefix_component + "listTpk:" + i + ":thanhtien").innerHTML;
		//alert(3);
		//alert("thanh tien: " + tt);
		ttCt = ttCt + parseInt(tt);
		//alert("tong tien: " + temp);
	}
	//alert(4);
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

