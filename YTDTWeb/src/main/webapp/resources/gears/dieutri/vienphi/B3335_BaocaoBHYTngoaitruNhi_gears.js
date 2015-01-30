function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	
				timer.setTimeout(function(){setValueOnLoad();},100); 	
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();
}



