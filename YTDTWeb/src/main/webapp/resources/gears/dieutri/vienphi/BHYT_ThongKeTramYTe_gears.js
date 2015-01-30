function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
				setAttrForCombobox(prefix_component + "DT_DM_TRAM_Y_TE_BHYT_MA","DT_DM_TRAM_Y_TE_BHYT_span","DT_DM_TRAM_Y_TE_BHYT","getDtDmTramYTeBhyt()","","","");							
											
										
				timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
}


