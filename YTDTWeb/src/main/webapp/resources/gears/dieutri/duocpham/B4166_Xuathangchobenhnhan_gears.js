function init() {
	if (window.google && google.gears) {
		setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN_span', 'DT_DM_NHAN_VIEN',"getDtDmNhanVien_Duoc()","","","");
        setValueOnLoad();
	}
}	
function setDefaultValueForNVPhat(){
  if (document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value == null || document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value ==""){
  	var nvPhatClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B4166_Xuathangchobenhnhan_nvphat");
	if (nvPhatClientDefault) {		
		document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value = nvPhatClientDefault.Ten;
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA', 'DT_DM_NHAN_VIEN');		
	}
  }	
}
function luuTruGiaTriClientDefault(){
	var giaTriNVPhat = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value;
	if (giaTriNVPhat != null && giaTriNVPhat != ""){
		var nvPhatClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B4166_Xuathangchobenhnhan_nvphat");
		if (nvPhatClientDefault == null || nvPhatClientDefault == false) { //insert
			var chNames = new Array();
			chNames[0] = "MaSo";
			chNames[1] = "Ma";
			chNames[2] = "Ten";
			var chValues = new Array();
			chValues[0] = 431101;
			chValues[1] = "B4166_Xuathangchobenhnhan_nvphat";
			chValues[2] = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA").value;	
		    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
		}else{ //update				
			var chNames = new Array();
			chNames[0] = "Ten";				
			var chValues = new Array();
			chValues[0] = giaTriNVPhat;				
			updateObjectWithTrueValue(DtDmClientDefault,"Ma","B4166_Xuathangchobenhnhan_nvphat", chNames,chValues );				
		}    	
	}	
}	
function maPhieuOnComplete(){	
	myOnblurTextbox(prefix_component +'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN');
	document.getElementById('div_ghinhan').style.display = "none";	
}

function setValueOnLoad(){
	setDefaultValueForNVPhat();	
	var sovaovien = document.getElementById(prefix_component + '__sovaovien').value;
	if(sovaovien != ''){
		document.getElementById(prefix_component + "__ghinhan").focus();
	}
	else{
		document.getElementById(prefix_component + "__sovaovien").focus();
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA','DT_DM_NHAN_VIEN');
		document.getElementById(prefix_component + '__saveResult').value = '';
	}
}
var lock = false;

function displayAfterGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__sovaovien").focus();		
		return;
	}
}

function resetAfterNhapMoiXuatHang(){
	document.getElementById("div_ghinhan").style.display = "block";	
	setDefaultValueForNVPhat();
}

