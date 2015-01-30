function init() {
	if (window.google && google.gears) {
		//try {
			//LoadCatalogFromServer();
            //    alert('135');	
            //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN','10');
            //setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");

			//setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA" + "\")","","","");
			//setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA" + "\")","","","");
			//alert('135');	
			//setAttrForCombobox(prefix_component + "MA_DT","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
			//alert('135');	


            
            //setAttrForCombobox(prefix_component + "BANKHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","","");
            setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN',"getDtDmNhanVien_Duoc()","","","");
            //alert('135');
            //setAttrForComboboxJSFForThuoc_PhieuDuTru(prefix_component + 'DM_THUOC_MA','DM_THUOC_span', 'DM_THUOC','10');
            //setAttrForComboboxJSFForPhieuXuatBh('__listtonkho_span', '__listtonkhoBhyt','10');
          	setValueOnLoad();
		//} catch (e) {
		//	alert("init() error: " + e.description);
		//}
	}
}	
function setDefaultValueForNVPhat(){
  if (document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value == null || document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value ==""){
  	var nvPhatClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B4311_Xuathangchobenhnhan_nvphat");
	if (nvPhatClientDefault) {		
		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value = nvPhatClientDefault.Ten;
		//alert(nvPhatClientDefault.Ten);
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA', 'DT_DM_NHAN_VIEN');		
	}
  }	
}
function luuTruGiaTriClientDefault(){

		var giaTriNVPhat = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value;
		// alert("giaTriNVPhat:"+giaTriNVPhat);
		if (giaTriNVPhat != null && giaTriNVPhat != ""){
			var nvPhatClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B4311_Xuathangchobenhnhan_nvphat");
			//alert("nvPhatClientDefault:"+nvPhatClientDefault);
			if (nvPhatClientDefault == null || nvPhatClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 431101;
				chValues[1] = "B4311_Xuathangchobenhnhan_nvphat";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriNVPhat;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B4311_Xuathangchobenhnhan_nvphat", chNames,chValues );
				
			}    	
		}
	
}	
function maPhieuOnComplete(){
	
	myOnblurTextbox(prefix_component +'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN');
	document.getElementById('div_inphieu').style.display = "block";	
	document.getElementById('div_ghinhan').style.display = "none";	
	//document.getElementById('div_nhapphieu').style.display = "none";		
}

function displayInPhieu(){	
	var sophieu = document.getElementById(prefix_component + '__sophieu').value;	
   	if(sophieu != ''){   		
   		document.getElementById('div_inphieu').style.display = "block";	
   		document.getElementById('div_ghinhan').style.display = "none";	
   	}
   	else{
   		document.getElementById('div_inphieu').style.display = "none";
   		document.getElementById('div_ghinhan').style.display = "block";
   	}
}

function setValueOnLoad(){
	//alert('135');
	
	setDefaultValueForNVPhat();
	
	var matiepdon = document.getElementById(prefix_component + '__matiepdon').value;
	
	var valueFinished = document.getElementById(prefix_component + "hid_ReportFinished").value;
   	//alert('135');
   	var valueReportFileName = document.getElementById(prefix_component + "hid_ReportFileName").value;
   	//alert('135');
   	var sophieu = document.getElementById(prefix_component + '__sophieu').value;
   	if(sophieu != ''){
   		document.getElementById('div_inphieu').style.display = "block";	
   	}
   	else{
   		document.getElementById('div_inphieu').style.display = "none";
   	}
   	//alert('valueFinished: ' + valueFinished);
   	//alert('valueReportFileName: ' + valueReportFileName);
   /*	if (valueFinished != null && valueFinished != ""){
   		 window.open (myContextPath + valueFinished + "report_result.jsp?tenBaoCao="+ valueReportFileName  ,"report","status=1,toolbar=1,resizable=1");
   		document.getElementById(prefix_component + "hid_ReportFinished").value='';
   		document.getElementById(prefix_component + "__sualai").focus();
   	}
	*/
	//alert('246');
	if(matiepdon != ''){
		document.getElementById(prefix_component + "__ghinhan").focus();
	}
	else{
		document.getElementById(prefix_component + "__matiepdon").focus();
		//document.getElementById(prefix_component + "BANKHAM_MA").focus();
		
		/*
		var miengiam = document.getElementById(prefix_component + "__miengiam").value;
		var thatthu = document.getElementById(prefix_component + "__thatthu").value;
		var perbenhnhan = document.getElementById(prefix_component + "__perbenhnhan").value;
		
		
		if(miengiam == ''){
			document.getElementById(prefix_component + "__miengiam").value = 0;
		}
		if(thatthu == ''){
			document.getElementById(prefix_component + "__thatthu").value = 0;
		}
		if(perbenhnhan == '0' || perbenhnhan == ''){
			perbenhnhan = 1;
			document.getElementById(prefix_component + "__perbenhnhan").value = 1;
		}
		*/
		//alert(2);	
		//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value = "";
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN');
		
		//document.getElementById(prefix_component + "DM_THUOC_MA").value = "";	
		//myOnblurTextboxForDmThuoc(prefix_component + 'DM_THUOC_MA','DM_THUOC','','');
		document.getElementById(prefix_component + '__saveResult').value = '';
	}
}
function blockInphieu() {
	//alert(document.getElementById(prefix_component + "__maFinish").value);
	if (document.getElementById(prefix_component + "__maFinish").value != '') {
		document.getElementById("__divIn").style.display = "block";
	} else {
		document.getElementById("__divIn").style.display = "none";
	}
}

/*
var dataList;

function reloadValueForCombobox(){    
    try{
		document.getElementById(prefix_component + "DM_THUOC_MA").value = "";
		//blockInphieu();
		dataList = null;
	    listCatalogJSF_PhieuXuatBh("__listtonkhoBhyt",null); 
	}
	catch(e){
		alert("reloadValueForCombobox():"+e);
	}
}



function getTonkho() {
  try{	
  	var form = document.forms[0];
    //var valid = iesvn_ValidateRequired(form)  ; 
    //if (valid == false){
    //   return;
    //}
  	//alert(11);
	var maHang = document.getElementById(prefix_component + "DM_THUOC_MA").value;	
	
	if (maHang == ""){
	  //alert(12);
	  //document.getElementById(prefix_component + "DM_THUOC_MA").focus();
	  return;
	}
    //alert(13);  
	var request = maHang;	
    var xml;
    var data;
    
    
    var xmlHttp = createXmlHttpRequest();
    var url =  myContextPath + "/actionServlet?";
    var params = "urlAction="+ encodeURI("GetTonKhoBHYTAction") + "&xmlData=" + encodeURI(request);
	xmlHttp.open("POST", url, false);
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
			    var jsonExpression = "(" + xmlHttp.responseText + ")";
				var data = eval(jsonExpression);
				if ( data.list != null && typeof(data.list) == "object") {
					dataList = data.list;
       				listCatalogJSF_PhieuXuatBh("__listtonkhoBhyt",dataList);  
				}	
			    
		}
	};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
	xmlHttp.send(params); 	
	
  }catch(e){
    alert(":(getTonkho():" + e);
  }	
}  	

function checkTonkho(){		
	tonkho = document.getElementById(prefix_component + '__tonkho').value;
	var thuocMaso = document.getElementById(prefix_component + 'DM_THUOC_MA').value;	
	
	var check = true;
	if(tonkho == '' || thuocMaso == ''){
		check = false;
	}
	if(thuocMaso != ''){
		var dongia = document.getElementById(prefix_component + '__dongia').value;
		var xuat = document.getElementById(prefix_component + "__xuat").value;
		if(xuat != ''){
			document.getElementById(prefix_component + "__thanhtien").value = dongia*xuat;
		}
	}
	if(check){
		if (iesvn_ValidateFloatRange(document.forms[0])) {
			document.getElementById(prefix_component + '__thanhtien').focus();
		}
		else{
			document.getElementById(prefix_component + "__xuat").select();
		}
	}
	else{
		document.getElementById(prefix_component + "__ghinhan").focus();
	}
}
*/
var lock = false;

function displayAfterGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__matiepdon").focus();		
		return;
	}
	document.getElementById("div_inphieu").style.display = "block";
	//document.getElementById("div_nhapphieu").style.display = "none";
	document.getElementById("div_ghinhan").style.display = "none";
	document.getElementById(prefix_component + "__inphieu").focus();
	lockUnlockControlB4311(document.forms[0],1);
}

function resetAfterNhapMoiXuatHang(){

	document.getElementById("div_inphieu").style.display = "none";
	//document.getElementById("div_nhapphieu").style.display = "block";
	document.getElementById("div_ghinhan").style.display = "block";
	
	//document.getElementById("DT_DM_BAN_KHAM").value="";
	//document.getElementById("DM_TINH").value="";
	//document.getElementById("DM_HUYEN").value="";
	//document.getElementById("DM_XA").value="";	
	//document.getElementById("DM_DOI_TUONG").value="";	//DT_DM_DOI_TUONG
	//document.getElementById("DT_DM_NHAN_VIEN").value="";
	
	lockUnlockControlB4311(document.forms[0],0);
	
	setDefaultValueForNVPhat();
	document.getElementById(prefix_component + "__matiepdon").focus();
	//myOnblurTextbox(prefix_component + 'BANKHAM_MA', 'DT_DM_BAN_KHAM');
	
}

function lockUnlockControlB4311(form, type) {   
 	var elem = form.elements; 
 	for (var i = 0; i < elem.length; i++) {  
  		var strID = elem[i].id;
  		var strType = elem[i].type.toLowerCase();
  		if(strID!=''&&(strID==prefix_component + 'BANKHAM_MA' || strID=='DT_DM_BAN_KHAM' || strID==prefix_component + '__matiepdon' || strID==prefix_component + 'DT_DM_NHAN_VIEN_MA' || strID=='DT_DM_NHAN_VIEN')){ //Bat loi khi co dojo
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
