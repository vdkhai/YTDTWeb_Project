/*
var DT_DM_KET_QUA  = new Array (
   	   ["DT_DM_KET_QUA_MAPHU", "DT_DM_KET_QUA_MAPHU1", "MaSo", "Ten", "NgayChinhSua", "DaXoa"],
         ["double", "double", "varchar(15)", "varchar(250)", "double", "integer"]);           
*/                      
function init() {
	
    if (window.google && google.gears) {
    	
    	
               try{
            	   //setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");
               }catch(e){
               }
               
               //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
               initshorcut();
               
 	            timer.setTimeout(function(){setValueOnLoad();},1000); 					  
    }
}	

function initshorcut(){
    shortcut.add("Ctrl+F", function() {
    	document.getElementById(prefix_component + "__phieuso").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function focusAfterSave(){
	document.getElementById(prefix_component + "__tieptucnhap").focus();
} 

function getMyDataList_long_data(){
var catalog = new DmBenhIcd({});
	var textboxValue  = document.getElementById("DM_BENH_ICD__1").value;
	if (textboxValue == null || textboxValue == "" ){
		return;
	}
	return catalog.getDataList_long_data(textboxValue);
}

function blockInphieu() {
	//alert(document.getElementById(prefix_component + "__maFinish").value);
	/*
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
	*/
}

function setValueOnLoad(){	
	document.getElementById(prefix_component + "__sobenhan").focus();
	blockInphieu();
   var valueFinished = 	document.getElementById(prefix_component + "hid_ReportFinished").value;
   var valueReportFileName = document.getElementById(prefix_component + "hid_ReportFileName").value;
	onCompleteGetInfor();	
	try{
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", "DT_DM_NHAN_VIEN__1");
	  }catch(e){
               }
	    
}

function reloadValueForCombobox(){
    try{
    	//myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_1', 'DM_BENH_VIEN__1');
    	//if (document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1")){
    	///	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
    	//}
    	
    	//myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_2','DM_BENH_VIEN__2');
    	//myOnblurTextbox(prefix_component + 'DT_DM_KET_QUA_MA','DM_KET_QUA_DIEU_TRI');
    	//myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD__1');
    	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA','DM_KHOA');
    	blockInphieu();
	}catch(e){
		alert("reloadValueForCombobox():"+e);
	}
}   

function reloadValueForCombobox_edit(){
	
}

function onCompleteGetInfor(){	
	//myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_1', 'DM_BENH_VIEN__1');
	//if (document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1")){		
    //		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');    		
    //	}	
	//myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_2','DM_BENH_VIEN__2');	
	//myOnblurTextbox(prefix_component + 'DT_DM_KET_QUA_MA','DM_KET_QUA_DIEU_TRI');
	//alert("before");
	//myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD__1');
	//alert("after");
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA','DM_KHOA');	
	blockInphieu();
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	if(sobenhan == ''){
	
		//var khoaMa = document.getElementById(prefix_component + "DM_KHOA_MA").value;
		//alert("khoaMa:"+khoaMa);
		//if(khoaMa != ''){
			document.getElementById(prefix_component + "__sobenhan").focus();
		//}
		//else{
		//	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		//}
	}
	else{
	    //alert(5)
		document.getElementById(prefix_component + "__noidungmo").focus();
	}
	//alert(6);
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
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
	
    var xml;
    var data;
    
    
    var xmlHttp = createXmlHttpRequest();
    var url =  myContextPath + "/actionServlet?";
    var params = "urlAction="+ encodeURI("GetTonKhoAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
       				listCatalogJSF_PDTRU("__listtonkho",dataList); 
				}	
			    
		}
	};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 	
    
    
  }catch(e){
    alert("getTonkho():" + e);
  }	
}          

