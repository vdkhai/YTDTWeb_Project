function init() {
	
    if (window.google && google.gears) {    	
        try {            
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");    
            timer.setTimeout(function(){setOnload();},100);
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}
function setOnload(){
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
}

function onCompleteGetInfor(){
	try { 
		var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
		
		if (donViTuoi == "1"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "";
		}else if (donViTuoi == "2"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
		}else if (donViTuoi == "3"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";			
		}else{
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "";
		}
			
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
	//document.getElementById(prefix_component + "__sotien").focus(); 
}




