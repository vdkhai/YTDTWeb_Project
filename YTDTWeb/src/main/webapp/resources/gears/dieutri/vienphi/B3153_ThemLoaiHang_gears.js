function init() {
	
    if (window.google && google.gears) {
    	
        try {        	   	    		    		
           setAttrForCombobox(prefix_component + "DTDMDONVITINH_MA", "DON_VI_TINH_span", "DM_DON_VI_TINH", "getDmDonViTinh()","","","");        	    
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}

function setOnload(){
	 try{
		 document.getElementById(prefix_component + "__mahang").focus();
		 
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}






