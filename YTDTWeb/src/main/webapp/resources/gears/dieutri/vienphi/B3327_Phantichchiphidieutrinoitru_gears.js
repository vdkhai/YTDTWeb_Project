function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
				try{							
					setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");				
				}catch(e){
				
				}
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuongTP()","","","");				
				      
				        timer.setTimeout(function(){setValueOnLoad(); document.getElementById(prefix_component + "__thang").focus();},100);     
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad() {
				try{							
					myOnblurTextbox(prefix_component + "DM_KHOA_MA", 'DM_KHOA');
				}catch(e){
				
				}

	myOnblurTextbox(prefix_component + "DM_DOI_TUONG_MA", 'DM_DOI_TUONG');
	
}