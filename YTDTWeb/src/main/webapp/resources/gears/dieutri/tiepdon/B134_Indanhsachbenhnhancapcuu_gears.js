function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
											
				setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuongTP()","","","");				
				//setAttrForCombobox(prefix_component + "DT_DM_PL_BHYT_MA","DT_DM_PL_BHYT_span","DT_DM_PL_BHYT","getDtDmPlBhyt()","","","");		
				            timer.setTimeout(function(){InitSetInfor();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function InitSetInfor(){
	document.getElementById(prefix_component + "__thang").focus();
}
function myOnComplete(){
	myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
	//myOnblurTextbox(prefix_component + 'DT_DM_PL_BHYT_MA', 'DT_DM_PL_BHYT');
}
