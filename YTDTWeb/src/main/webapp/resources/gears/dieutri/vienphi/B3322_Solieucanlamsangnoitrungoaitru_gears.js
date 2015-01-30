function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
						 			
				//setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getKhoaHavingCLS()","","","");
				setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_TuTrucKhoaPhong();","","","");
				setAttrForCombobox(prefix_component + "DT_DM_CLS_MA","DT_DM_CLS_span","DT_DM_CLS","getDtDmCls()","","","");
				setAttrForCombobox(prefix_component + "DT_DM_PB_CLS_MA","DT_DM_PB_CLS_span","DT_DM_PB_CLS","getDtDmPbCls()","","","");
				setAttrForCombobox(prefix_component + "DT_DM_CLS_MA","DT_DM_CLS_span","DT_DM_CLS","getDtDmCls()","","","");
				timer.setTimeout(function(){setValueOnLoad();},100);     
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function setValueOnLoad(){
	
	myOnblurTextbox(prefix_component + "DM_KHOA_MA", 'DM_KHOA');
	myOnblurTextbox(prefix_component + "DT_DM_CLS_MA", 'DT_DM_CLS');
	myOnblurTextbox(prefix_component + "DT_DM_PB_CLS_MA", 'DT_DM_PB_CLS');
	myOnblurTextbox(prefix_component + "DT_DM_CLS_MA", 'DT_DM_CLS');
	document.getElementById(prefix_component + "__thang").focus();
document.getElementById(prefix_component + "__thang").select();
}

