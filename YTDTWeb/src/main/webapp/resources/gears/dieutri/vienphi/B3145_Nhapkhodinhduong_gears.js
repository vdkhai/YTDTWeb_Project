function init() {
	
    if (window.google && google.gears) {
    	
        try {        	
        	LoadCatalogFromServer_each(DtDmLoaiAn2,"GetDtDmLoaiAn2Action");
    		LoadCatalogFromServer_each(DtDmNhaSxSpdd,"GetDtDmNhaSxSpddAction");		
    		    		
        	//    		    		    		        	
        	setAttrForCombobox(prefix_component + "LOAIAN2_MA","DT_DM_LOAI_AN2_span","DT_DM_LOAI_AN2", "getDtDmLoaiAn2(\"" + prefix_component + "LOAIAN_MA_pk\")","", "checkLoaiSp();", "");        	
        	setAttrForCombobox(prefix_component + "NHASX_MA","DT_DM_NHASX_span","DT_DM_NHASX", "getDtDmNhaSxSpdd()","", "", "");        	        	   
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}


function setOnload(){	 
		 document.getElementById(prefix_component + "__ngay").focus();
	}

function reloadValueForCombobox()
{
	
}





