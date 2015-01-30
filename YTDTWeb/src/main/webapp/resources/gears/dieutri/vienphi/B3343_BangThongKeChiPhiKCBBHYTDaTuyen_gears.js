function init() {
	
    if (window.google && google.gears) {
    	
        //try {
        	
				setAttrForCombobox(prefix_component + "DT_DM_KCB_BHYT_MA","DT_DM_KCB_BHYT_span","DT_DM_KCB_BHYT","getDtDmKcbBhyt()","","","");								
				timer.setTimeout(function(){setValueOnLoad();},100); 	
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
}
function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();
}



