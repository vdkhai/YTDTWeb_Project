function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			
				setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");												
            timer.setTimeout(function(){focusInit();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById(prefix_component + "DM_KHOA_MA").focus();
	
}

function setTemp(){
			setSave(); 
			if(document.getElementById( prefix_component+ "__chonthuoc").checked == false && document.getElementById(prefix_component + "__chonydc").checked == false)
				alert ("Chọn thuốc hoặc Chọn YDC phải được chọn");
			else {
				var form = document.forms[0];
				return onSubmit(form);
			}
}	



