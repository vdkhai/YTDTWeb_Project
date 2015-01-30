function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	LoadCatalogFromServer_each(DtDmLoaiAn,"GetDtDmLoaiAnAction"); 
        	setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
        	setAttrForCombobox(prefix_component + "LOAIAN_MA","DT_DM_LOAI_AN_span","DT_DM_LOAI_AN", "getDtDmLoaiAn()","", "", "");        	               
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}


function setOnload(){
	 try{
		 document.getElementById(prefix_component + "DM_KHOA_MA").focus();		 		 
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}

function reloadValueForCombobox()
{
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');	
	myOnblurTextbox(prefix_component + 'LOAIAN_MA', 'DT_DM_LOAI_AN');	
}






