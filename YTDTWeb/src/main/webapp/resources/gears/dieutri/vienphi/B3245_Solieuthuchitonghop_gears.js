function init() {
	
    if (window.google && google.gears) {
    	
       
               setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");
              
            
 	          
 	            timer.setTimeout(function(){setValueOnLoad();},100); 					  
        
    }
}	 
function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();
}