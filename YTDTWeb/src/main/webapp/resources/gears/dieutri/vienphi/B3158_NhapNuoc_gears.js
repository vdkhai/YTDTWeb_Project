function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//LoadCatalogFromServer_each(DtDmLoaiAn,"GetDtDmLoaiAnAction");
        	//LoadCatalogFromServer_each(DtDmLoaiAn2,"GetDtDmLoaiAn2Action");
    		//LoadCatalogFromServer_each(DtDmMucAn,"GetDtDmMucAnAction");		
    		//LoadCatalogFromServer_each(DtDmCheDoAn,"GetDtDmCheDoAnAction");	    		
    		//LoadCatalogFromServer_each(DtDmDoiTuongAn,"GetDtDmDoiTuongAnAction");    		
    		//LoadCatalogFromServer_each(DtDmDongThem,"GetDtDmDongThemAction");    		
        	        	        	
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NhapNuoc()","resetDmBuong();","","");            
            setAttrForCombobox(prefix_component + "BUONG_MA","DT_DM_BUONG_span","DT_DM_BUONG", "getDtDmBuong(\"" + prefix_component + "DM_KHOA_MA_pk\")","", "", "");                        
                
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e.description);
        	
        }
    }    
}




function onCompleteGetInfor(){
	try { 				
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		myOnblurTextbox(prefix_component + 'BUONG_MA', 'DT_DM_BUONG');						
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
}




function setOnload(){
	 try{
		 document.getElementById(prefix_component + "__ngay").focus();		 		 		
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}

function reloadValueForCombobox()
{	
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	myOnblurTextbox(prefix_component + 'BUONG_MA', 'DT_DM_BUONG');	
}


function resetDmBuong() {
	document.getElementById(prefix_component + 'BUONG_MA').value = "";
 	resetForCombobox('DT_DM_BUONG',prefix_component + 'BUONG_MA');
}





