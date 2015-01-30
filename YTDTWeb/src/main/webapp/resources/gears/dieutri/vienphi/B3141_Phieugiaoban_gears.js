function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien()","","","");  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_2","DT_DM_NHAN_VIEN_span2","DT_DM_NHAN_VIEN__2","getDtDmNhanVien()","","","");  
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_3","DT_DM_NHAN_VIEN_span3","DT_DM_NHAN_VIEN__3","getDtDmNhanVien()","","","");
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_4","DT_DM_NHAN_VIEN_span4","DT_DM_NHAN_VIEN__4","getDtDmNhanVien()","","","");
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_5","DT_DM_NHAN_VIEN_span5","DT_DM_NHAN_VIEN__5","getDtDmNhanVien()","","","");
        	setAttrForCombobox(prefix_component + "LOAIAN_MA","DT_DM_LOAI_AN_span","DT_DM_LOAI_AN", "getDtDmLoaiAn()","", "", "");
            setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
            
            //document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
                
           timer.setTimeout(function(){setOnload();},100); 
            
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}

function onCompleteGetInfor(){
	try { 
	
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_4', 'DT_DM_NHAN_VIEN__4');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_5', 'DT_DM_NHAN_VIEN__5');
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		myOnblurTextbox(prefix_component + 'LOAIAN_MA', 'DT_DM_LOAI_AN');
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
	
}

function setOnload(){
	 try{
		 document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		 visibleButton();
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}

function reloadValueForCombobox()
{
	myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	myOnblurTextbox(prefix_component + 'LOAIAN_MA', 'DT_DM_LOAI_AN');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_4', 'DT_DM_NHAN_VIEN__4');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_5', 'DT_DM_NHAN_VIEN__5');
	visibleButton();
}

function visibleButton() {
	if (document.getElementById(prefix_component + "hid_hienThiHuyPhieu").value != '') {
		document.getElementById("__divHuyPhieu").style.display = "block";
	} else {
		document.getElementById("__divHuyPhieu").style.display = "none";
	}
}




