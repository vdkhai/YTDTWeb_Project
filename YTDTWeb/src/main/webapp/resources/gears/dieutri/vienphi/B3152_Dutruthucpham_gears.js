function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	LoadCatalogFromServer_each(DtDmLoaiTp,"GetDtDmLoaiTpAction");        	    	
        	//    		
    		    		
        	setAttrForCombobox(prefix_component + "LOAITP_MA","DT_DM_LOAITP_span","DT_DM_LOAITP", "getDtDmLoaiTp()","", "showDvt()", "");
        	    
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






