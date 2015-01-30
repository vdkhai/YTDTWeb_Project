                                             
function init() {
	
    if (window.google && google.gears) {
    	
	  try{
               
                timer.setTimeout(function(){setValueOnLoad();},100); 
               
                     highlightOnFocus();     							  

        } catch (e) {
        
         
         
         }
	}
}

function setValueOnLoad(){
	document.getElementById(prefix_component +"__tusobenhan").focus();
	highlightOnFocus();
}

