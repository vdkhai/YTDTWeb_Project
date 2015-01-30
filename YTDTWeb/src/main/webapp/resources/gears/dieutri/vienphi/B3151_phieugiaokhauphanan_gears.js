function init() {
	
    if (window.google && google.gears) {
    	
        try {        	        	        
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");                                       
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}


function setOnload(){
	 try{
		 document.getElementById(prefix_component + "__ngay").focus();		 		 
	  }catch(e){
	    alert("error at setOnload()");
	  }	
}