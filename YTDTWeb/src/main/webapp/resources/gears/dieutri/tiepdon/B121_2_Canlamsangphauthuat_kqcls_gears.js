        
function init() {

    if (window.google && google.gears) {
    	
        try {
           
                
                setAttrForCombobox(prefix_component + 'DT_DM_BAN_KHAM_MA','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKham()","","","");
               
               
                var doc = opener.document;
				document.getElementById(prefix_component + "mabankhamout_hid").value = doc.getElementById(prefix_component + "mabankhamout_hid").value;
				document.getElementById(prefix_component + "matiepdonout_hid").value = doc.getElementById(prefix_component + "matiepdonout_hid").value;
				document.getElementById(prefix_component + "maclsout_hid").value = doc.getElementById(prefix_component + "maclsout_hid").value;
			
				//alert(document.getElementById(prefix_component + "mabankhamout_hid").value + "," + document.getElementById(prefix_component + "matiepdonout_hid").value + "," + document.getElementById(prefix_component + "maclsout_hid").value);
				
				document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").value = doc.getElementById(prefix_component + "mabankhamout_hid").value;
				document.getElementById(prefix_component + "__matiepdon").value = doc.getElementById(prefix_component + "matiepdonout_hid").value;
			
			
				//settimeout(document.getElementById(prefix_component + "refresh").onclick(), 50);
				document.getElementById(prefix_component + "refresh").onclick();
				document.getElementById("Banner_menu_div").style.display ="none";
				
				
                timer.setTimeout(function(){loadFlash();},1000);   
                         							  
            	//alert(document.getElementById(prefix_component + "mabankhamout_hid").value);
            
        } catch (e) {
           alert("error at init");
        }
    }
    
}

function loadFlash(){	
	/*var hinhanh = document.getElementById(prefix_component + "__hinhanh").value;
	//var hinhanh = "TRAN THI DAO 1988||PACS-1168390736||N/A||Nam||2010-08-04 10:04:24.0||||CR||||6619||6710";
	if(hinhanh != "" && hinhanh != null){
	var arrayHinhanh = hinhanh.split("||");
	var studyIDTemp = arrayHinhanh[7];
	var studyPKTemp = arrayHinhanh[9];	
	var flashvars = {
            protocolCrossDomain: 'http',
			adressCrossDomain: 'hgt.vn',
			portCrossDomain: '80',
			fileNameCrossDomain: 'crossDomain.xml',
			protocolServer: 'rtmp',
			addressServer: 'localhost',
			portServer: '1935',
			appServer: 'RTMPServer',
			username: '',
			password: '',
			studyID: studyIDTemp,
			studyPK: studyPKTemp
          };
	var flashvars = {
	            protocolCrossDomain: 'http',
				adressCrossDomain: '192.168.3.129',
				portCrossDomain: '8080',
				fileNameCrossDomain: 'crossDomain.xml',
				protocolServer: 'rtmp',
				addressServer: '192.168.3.129',
				portServer: '1937',
				appServer: 'rStudy',
				username: 'ytebv',
				password: 'ytebv512',
				studyID: studyIDTemp,
				studyPK: studyPKTemp
	          };
	*/
	var studyIDTemp = document.getElementById(prefix_component + "matiepdonout_hid").value;
	var studyPKTemp = document.getElementById(prefix_component + "__macls").value;
	var loaiCLS = document.getElementById(prefix_component + "loaiCLS_hid").value;
	
	//alert(studyPKTemp);
	// server Binh Duong: 192.168.2.251
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
		swfobject.embedSWF("../resources/ThietBiYKhoa/ThietBiYKhoa.swf", 
                     "replaceContent", 
                     "650", "300", 
                     "10.0.0", "../resources/ThietBiYKhoa/playerProductInstall.swf",
                     flashvars, {}, {style:'margin-left:70px;'});
	}
	
}
function hidImage(){
	
	var hasImage = document.getElementById(prefix_component + "hasImage_hid").value;
	//alert("hasImage:"+hasImage);
	if (hasImage == 'true'){
		document.getElementById(prefix_component + "imageCLS").style.display = "block";
	}else{
		document.getElementById(prefix_component + "imageCLS").style.display = "none";
	}
	
	
}
function setOnload(){

  
  myOnblurTextbox(prefix_component + 'DT_DM_BAN_KHAM_MA', 'DT_DM_BAN_KHAM');
 
  
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
		document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA").disabled = true;
		if (document.getElementById( "DT_DM_BAN_KHAM").disabled == false){
      	  changeDisabledAttr("DT_DM_BAN_KHAM");  
   		} 
		document.getElementById(prefix_component + "__matiepdon").disabled = true;

	
		
}





