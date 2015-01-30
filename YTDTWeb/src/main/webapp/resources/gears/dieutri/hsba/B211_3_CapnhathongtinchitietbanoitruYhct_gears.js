function init() {
	
    if (window.google && google.gears) {
    	
        try {
        		setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span',   'DM_KHOA',"getDmKhoa()","","","");
				setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA4','DT_DM_NHAN_VIEN4_span', 'DT_DM_NHAN_VIEN__4',"getDtDmBacSi()","","","");	
	 			
				timer.setTimeout(function(){onCompleteGetInfor();},200); 
				
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function onCompleteGetInfor(){

	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA4', 'DT_DM_NHAN_VIEN__4');
	
 	document.getElementById(prefix_component + "DM_KHOA_MA").disabled = true; 
//    if (document.getElementById(prefix_component +  "DM_KHOA").disabled == true){
//       	changeDisabledAttr("DM_KHOA");  
//    }
    myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
    
}

initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__nhapmoi').click();});	
}