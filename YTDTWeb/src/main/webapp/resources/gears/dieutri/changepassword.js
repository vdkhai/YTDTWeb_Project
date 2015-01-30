function init() {
	
    if (window.google && google.gears) {
    	
        try {
           
			 setAttrForCombobox(prefix_component + '__tennguoidung','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1', "getDtDmNhanVien()", "", "", "");
            
            
		     timer.setTimeout(function(){onMyLoad();},100); 
        } catch (e) {
        
         alert(e.description);
         
         }
    }
    
}
function onMyLoad(){
	myOnblurTextbox(prefix_component + "__tennguoidung", "DT_DM_NHAN_VIEN__1");
	 document.getElementById(prefix_component + "__tennguoidung").disabled = true;
			if (document.getElementById( "DT_DM_NHAN_VIEN__1").disabled == false){
		        	changeDisabledAttr("DT_DM_NHAN_VIEN__1");  
		    }
}
function onCompleteSetInfor(){
 	myOnblurTextbox(prefix_component + "__tennguoidung", "DT_DM_NHAN_VIEN__1");
 	document.getElementById(prefix_component + "__manguoidung").value = document.getElementById(prefix_component + "__tennguoidung").value;
	document.getElementById(prefix_component + "__matkhau").focus();
}

