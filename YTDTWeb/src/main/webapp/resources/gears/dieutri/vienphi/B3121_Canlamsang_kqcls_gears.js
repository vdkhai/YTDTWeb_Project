        
function init() {

    if (window.google && google.gears) {    	
        try {                          
                setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span','DM_KHOA',"getDmKhoa_NoiTru()","","","");               
                var doc = opener.document;
				document.getElementById(prefix_component + "maclsout_hid").value = doc.getElementById(prefix_component + "maclsout_hid").value;
				document.getElementById(prefix_component + "makhoakhamout_hid").value = doc.getElementById(prefix_component + "makhoakhamout_hid").value;
				document.getElementById(prefix_component + "sobenhanout_hid").value = doc.getElementById(prefix_component + "sobenhanout_hid").value;
				document.getElementById(prefix_component + "DM_KHOA_MA").value = doc.getElementById(prefix_component + "makhoakhamout_hid").value;
				document.getElementById(prefix_component + "__sobenhan").value = doc.getElementById(prefix_component + "sobenhanout_hid").value;
				document.getElementById(prefix_component + "refresh").onclick();
				document.getElementById("Banner_menu_div").style.display ="none";			
                timer.setTimeout(function(){loadFlash();},1000);   
              
        } catch (e) {
           alert("error at init");
        }
    }
    
}

function loadFlash(){		
	var studyIDTemp = document.getElementById(prefix_component + "sobenhanout_hid").value.replace("BD", "NO");
	var studyPKTemp = document.getElementById(prefix_component + "__macls").value;
	var loaiCLS = document.getElementById(prefix_component + "loaiCLS_hid").value;	

	if (loaiCLS == 2){
		var flashvars = {
            protocolCrossDomain: 'http',
			adressCrossDomain: '192.168.2.251',
			portCrossDomain: '80',
			fileNameCrossDomain: 'crossdomain.xml',
			protocolServer: 'http',
			addressServer: '192.168.2.251',
			portServer: '80',
			appServer: 'webviewer',
			username: 'ytebv',
			password: 'ytebv512',
			studyID: studyIDTemp,
			studyPK: studyPKTemp
          };
		swfobject.embedSWF("../../resources/ThietBiYKhoa/ThietBiYKhoa.swf", 
                     "replaceContent", 
                     "650", "300", 
                     "10.0.0", "../../resources/ThietBiYKhoa/playerProductInstall.swf",
                     flashvars, {}, {style:'margin-left:70px;'});
	}
	
}
function hidImage(){
	
	var hasImage = document.getElementById(prefix_component + "hasImage_hid").value;
	if (hasImage == 'true'){
		document.getElementById(prefix_component + "imageCLS").style.display = "block";
	}else{
		document.getElementById(prefix_component + "imageCLS").style.display = "none";
	}
	
	
}
function setOnload(){

  
  myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
 
  
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
	
		document.getElementById("divQuaylai").style.display = "block";
		document.getElementById(prefix_component + "DM_KHOA_MA").disabled = true;
		if (document.getElementById( "DM_KHOA").disabled == false){
      	  changeDisabledAttr("DM_KHOA");  
   		} 
		document.getElementById(prefix_component + "__sobenhan").disabled = true;

}





