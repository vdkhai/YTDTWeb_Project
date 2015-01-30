function init() {
    if (window.google && google.gears) {
        try {
        	setAttrForCombobox(prefix_component + "DT_DM_BAN_KHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKhamCCL()","","","");
			timer.setTimeout(function(){setOnload();},100);
			
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function setOnload(){
	setDefaultValueForBanKham();
	document.getElementById(prefix_component + "__matiepdon").focus();
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
function setDefaultValueForBanKham(){
	
  if (document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value == null || document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value ==""){
	 //var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B135_1_denghitamung_bankham");
	//if (bankhamClientDefault) {		
		//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = true.Ten;
	  	document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = "CCL"; //setdefaultValueForBanKham = "CCL"
				
	//}
  }	
  //Tienpt modified 07/10/2010
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
}

function luuTruGiaTriClientDefault(){
	
		var giaTriBanKhamMa = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B135_1_denghitamung_bankham");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 135101;
				chValues[1] = "B135_1_denghitamung_bankham";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B135_1_denghitamung_bankham", chNames,chValues );
				
			}    	
		}
		
		
	
}	
function setValueOnLoad() {
	myOnblurTextbox(prefix_component + "DT_DM_BAN_KHAM_MA", "DT_DM_BAN_KHAM");
}