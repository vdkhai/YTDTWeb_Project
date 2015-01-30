function init() {
	
    if (window.google && google.gears) {
    	
        try {
          //  setAttrForCombobox(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA',"getDmKhoa_NoiTru()","","","");
            
           //document.getElementById(prefix_component + "__ngay").value = getDateSystem_dd_MM_yyyy();
                
          // timer.setTimeout(function(){setOnload()},10); 
            
        } catch (e) {
        	alert("init():" + e);
        	
        }
    }    
}

function onCompleteGetInfor(wheretofocus){
	try { 
		
		myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
		var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
		if (donViTuoi == "1"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
		}else if (donViTuoi == "2"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
		}else if (donViTuoi == "3"){
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		}else{
			document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
		}
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}
function setOnload(){
  //return;
	
	 try{
	
	 //myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	  //alert(1);
	 //alert(2);
	 // myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	  //alert(3);	
	  //
	  
	  //myOnblurTextboxJSF(prefix_component + 'DT_DM_DOI_TUONG_MA',prefix_component + 'DT_DM_DOI_TUONG');
	  
	   //alert(4);
	  //myOnblurTextbox(prefix_component + '__madantoc', 'DM_DAN_TOC');
	  
	 // myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	 
		
		
		
		
	  }catch(e){
	    alert("error at setOnload()");
	  }	
	}






