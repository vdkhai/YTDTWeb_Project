	
function init() {
	
    if (window.google && google.gears) {
    	
        try {
             setAttrForCombobox(prefix_component + "DM_KHO_MA","DM_KHOA_span1","DM_KHOA__1","getDmKhoa_KhoChinh_KhoLe_BHYT_TE()","","","");
             setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa(\"" + prefix_component + "DM_KHOA_MA_2\")","","","");
             setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVienByMaKhoa(\"" + prefix_component + "DM_KHOA_MA_2\")","","","");
             setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_3","DT_DM_NHAN_VIEN_span3","DT_DM_NHAN_VIEN__3","getDtDmNhanVienByMaKhoa(\"" + prefix_component + "DM_KHOA_MA_2\")","","","");
             setAttrForCombobox(prefix_component + "DT_DM_NGUON_MA","DT_DM_NGUON_span","DM_NGUON_CHUONG_TRINH","getDmNguonChuongTrinh()","","","");               setAttrForCombobox(prefix_component + "DM_KINH_PHI_MA","DM_KINH_PHI_span","DM_NGUON_KINH_PHI","getDmNguonKinhPhi()","","","");
             
           //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
             initshorcut();
             
             timer.setTimeout(function(){setValueOnLoad();},100);             
        } catch (e) {        
         	alert(" init(): " + e );         
         }
    }    
}	

function initshorcut(){
    shortcut.add("Ctrl+F", function() {
    	document.getElementById(prefix_component + "__maphieu").focus();
    },{'type':'keydown',"propagate":false,'target':document});
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
	
	//alert(document.getElementById(prefix_component + "hid_hienThiHuyPhieu").value);
	if (document.getElementById(prefix_component + "hid_hienThiHuyPhieu").value != '') {
		document.getElementById("__divHuyPhieu").style.display = "block";
	} else {
		document.getElementById("__divHuyPhieu").style.display = "none";
	}
}

function setValueOnLoad(){
	document.getElementById(prefix_component + "DT_DM_LOAI_MA").focus();
	blockInphieu();	
	
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN__1');
	
}
/**
*
*
	reloadValueForCombobox()
*
*	
**/
function reloadValueForCombobox(){
    //alert("myOnblurTextboxJSF");
    try{

		document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
		document.getElementById("DM_THUOC").value = "";
		blockInphieu();
		//alert(1);
		dataList = null;
	    listCatalogJSF_PDTRU("__listtonkho",null); 
	    
	    //alert(2);
	}catch(e){
		
		alert("reloadValueForCombobox111():"+e.description);
			
	}
} 
  
function clearNhanVien(){
	//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1_pk").value = "";	
	//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value = "";	
	//document.getElementById("DT_DM_NHAN_VIEN__1").value = "";
	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2_pk").value = "";	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").value = "";	
	document.getElementById("DT_DM_NHAN_VIEN__2").value = "";
	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_3_pk").value = "";	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_3").value = "";	
	document.getElementById("DT_DM_NHAN_VIEN__3").value = "";

}  
function onChangePLThuocByLoaiThuoc(){
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA_pk").value = "";	
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA").value = "";	
	document.getElementById("DM_PHAN_LOAI_THUOC").value = "";
	onChangeThuocByPhanLoaiThuoc();
}



function onChangeThuocByPhanLoaiThuoc(){	
	//document.getElementById(prefix_component + "DM_THUOC_MASO_pk").value = "";
	//document.getElementById(prefix_component + "DM_THUOC_MASO").value = "";	
	//document.getElementById("DM_THUOC").value = "";
}
  
function clear(){

}  
function reloadValueForCombobox_edit(){
	//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO",  "DM_THUOC");
	//document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
}

function onCompleteGetInfor(){
	
	myOnblurTextbox(prefix_component + 'DM_KHO_MA','DM_KHOA__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3','DT_DM_NHAN_VIEN__3');
	myOnblurTextbox(prefix_component + 'DT_DM_NGUON_MA','DM_NGUON_CHUONG_TRINH');
	myOnblurTextbox(prefix_component + 'DM_KINH_PHI_MA','DM_NGUON_KINH_PHI');
}
	

		
var dataList;
   
function getTonkho() {
  try{	
  	var form = document.forms[0];
    var valid = iesvn_ValidateRequired(form)  ; 
    if (valid == false){
       
       return;
    }
  
	var maHang = document.getElementById(prefix_component + "DM_THUOC_MASO").value;
	var kinhphiMa = document.getElementById(prefix_component + "DM_KINH_PHI_MA").value;
	var nguonMa = document.getElementById(prefix_component + "DT_DM_NGUON_MA").value;
	var khoaXuat = document.getElementById(prefix_component + "DM_KHO_MA").value;
	
	if (maHang == ""){
	  document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
	  return;
	}

	var request = maHang + " ;" + kinhphiMa + " ;" + nguonMa + " ;" + khoaXuat + " ; ";
	
	//alert(request);
    var xml;
    
    var url = myContextPath + "/actionServlet?";
    var xmlHttp = createXmlHttpRequest();
    var params = "urlAction="+ encodeURI("GetTonKhoAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    
    	handleStateChangeForB3125(xmlHttp);
    
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
	
	
  }catch(e){
    alert("getTonkho():" + e);
  }	
}          

/**

**/
function handleStateChangeForB3125(xmlHttp){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		if ( data.list != null && typeof(data.list) == "object") {
	  		dataList = data.list;
      		listCatalogJSF_PDTRU("__listtonkho",dataList); 
        }
	 }
 } 
 function checkBeforeUpdateChiTiet(){
   return checkTonkho();
 }
/**

**/ 
function checkTonkho(){	
	tonkho = document.getElementById(prefix_component + '__tonkho').value;
	var thuocMaso = document.getElementById(prefix_component + 'DM_THUOC_MASO').value;
	var check = true;
	if(tonkho == '' || thuocMaso == ''){
		check = false;
	}
	if(check){		
		if (iesvn_ValidateFloatRange(document.forms[0])) {
			document.getElementById(prefix_component + "__temp").focus();
			document.getElementById(prefix_component + 'DM_THUOC_MASO').focus();
		}
		else{
			document.getElementById(prefix_component + "__xinlinh").focus();
		}
	}
	else{
		document.getElementById(prefix_component + "__ghinhan").focus();
	}
	return check;
}

