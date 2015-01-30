                                              
function init() {
	
    if (window.google && google.gears) {
    	
        try {
           
			LoadCatalogFromServerExt();
        } catch (e) {
        
         alert(e.description);
         
         }
    }
    
}

