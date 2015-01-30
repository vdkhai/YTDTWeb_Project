function init() {
    if (window.google && google.gears) {    	
        try {        	 
              setAttrForCombobox(prefix_component + "DM_KHO_MA","DM_KHOA_span1","DM_KHOA__1","getDmKhoa_KhoChinh_KhoLe()","","","");
              setAttrForCombobox(prefix_component + "DM_KHOA_MA_2","DM_KHOA_span2","DM_KHOA__2","","","","");
              setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien_Duoc()","","","");
              setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien_Duoc()","","","");
              setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_3","DT_DM_NHAN_VIEN_span3","DT_DM_NHAN_VIEN__3","getDtDmNhanVien_Duoc()","","","");
  			  
              timer.setTimeout(function(){setValueOnLoad();},100); 
        } catch (e) {        
         	alert('init '+ e);         
        }
    }    
}	 

function clearNhanVien(){	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2_pk").value = "";	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_2").value = "";	
	document.getElementById("DT_DM_NHAN_VIEN__2").value = "";
	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_3_pk").value = "";	
	document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_3").value = "";	
	document.getElementById("DT_DM_NHAN_VIEN__3").value = "";

}  

function blockInphieu() {
	
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
	blockInphieu();
	
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DM_KHO_MA','DM_KHOA__1');	
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2','DM_KHOA__2');
	document.getElementById(prefix_component + "__phieudutru").focus();
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
    
		//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO",  "DM_THUOC");
		//document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
		blockInphieu();
	}catch(e){
		
		alert("reloadValueForCombobox():"+e);
			
	}
}   
 
function reloadValueForComboboxOnBlurSoLuong(){
    //alert("myOnblurTextboxJSF");
    try{
    
		//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO",  "DM_THUOC");
		//document.getElementById(prefix_component + "__xuat").focus();
		
		document.getElementById(prefix_component + "__donviMain").value="";
		document.getElementById(prefix_component + "__dongia").value="";
		//document.getElementById(prefix_component + "DM_THUOC_MASO").value="";
		//document.getElementById( "DM_THUOC").value="";
		//document.getElementById( "__listtonkho").value="";
		blockInphieu();
	}catch(e){
		
		alert("reloadValueForCombobox():"+e);
			
	}
}    
function checkTonkho(){		
	tonkho = document.getElementById(prefix_component + '__tonkho').value;
	var thuocMaso = document.getElementById(prefix_component + 'DM_THUOC_MASO').value;	
	var check = true;
	if(tonkho == '' || thuocMaso == ''){
		check = false;
	}
	if(check){
		if (iesvn_ValidateFloatRange(document.forms[0])) {
			//document.getElementById(prefix_component + "__temp").focus();
		}
		else{
			document.getElementById(prefix_component + "__xuat").focus();
		}
	}
	else{
		document.getElementById(prefix_component + "__ghinhan").focus();
	}
}
		
function onCompleteGetInfor(){
	
	myOnblurTextbox(prefix_component + 'DM_KHO_MA','DM_KHOA__1');
	
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2','DM_KHOA__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3','DT_DM_NHAN_VIEN__3');
	blockInphieu();
	
}

/*
		
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
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
	xmlHttp.send(params); 
	
	
  }catch(e){
    alert("getTonkho():" + e);
  }	
}          

*/
/**

**/
/*
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
 
 */
             

