function init() {
	
    if (window.google && google.gears) {    	
        try {            
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","",""); 
            setAttrForCombobox(prefix_component + 'DM_DOI_TUONG_MA','DM_DOI_TUONG_span', 'DM_DOI_TUONG',"getDmDoiTuongTP()","","","");
            timer.setTimeout(function(){setOnload();},100);
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}
function setOnload(){
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
}






