function init() {
	
//    if (window.google && google.gears) {
//    	
//        try {
//           
//			 //setAttrForCombobox(prefix_component + '__tennguoidung','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1', "getDtDmNhanVien()", "", "", "");
//            
//        } catch (e) {
//        
//         alert(e.description);
//         
//         }
//    }
    
}
function onCompleteSetInfor(){

 	document.getElementById(prefix_component + "__manguoidung").value = document.getElementById(prefix_component + "__tennguoidung").value;
}

