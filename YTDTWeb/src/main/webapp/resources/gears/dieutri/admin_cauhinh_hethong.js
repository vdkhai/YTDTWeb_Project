function init() {
	
    if (window.google && google.gears) {
    	
        try {
           
			
            
		     timer.setTimeout(function(){onMyLoad();},100); 
        } catch (e) {
        
         alert(e.description);
         
         }
    }
    
}
function onMyLoad(){
	
}
function onCompleteSetInfor(){
 	
}

