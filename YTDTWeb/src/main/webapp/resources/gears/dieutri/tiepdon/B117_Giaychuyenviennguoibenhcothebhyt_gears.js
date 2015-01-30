        
function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmBacSi()","","","");
        	setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_3","DT_DM_NHAN_VIEN_span3","DT_DM_NHAN_VIEN__3","getDtDmBacSi()","","","");
        	visibleButton();
                //alert(1);
                
                //setAttrForComboboxJSF(prefix_component + 'DM_KHOA_MA','DM_KHOA_span',prefix_component+ 'DM_KHOA','10');
                // setAttrForComboboxJSF_CLS(prefix_component + 'DTDMKYTHUAT_MA','DT_DM_KY_THUAT_span','DT_DM_KY_THUAT','10');
                
                //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_KY_THUAT_MA','DT_DM_KY_THUAT_span', 'DT_DM_KY_THUAT','10', DtDmKyThuat);
                //alert(2);
                // setAttrForCombobox_StoreValue(prefix_component + 'DM_KHOA_MA','DM_KHOA_span', 'DM_KHOA','10', DmKhoa);
                //alert(3);
                
                
                 timer.setTimeout(function(){setOnload();},200);         	            							  
            
            
        } catch (e) {
           alert("error at init");
        }
    }
    
}

function setOnload(){

  //alert("begin setOnload()");	
  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
  myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
  
  setDonViTuoi();		
}

function setDonViTuoi(){
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
	var ngaysinh = document.getElementById(prefix_component + "__ngaytg").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaytg").value = namsinh;
	}
}

function reloadValueForCombobox(){
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA', 'DM_KHOA');
	//document.getElementById(prefix_component + "donGiaHid").value = "";
	
//	document.getElementById(prefix_component + "__benhsu").focus();
}



function onCompleteGetInfor(){
	try { 
	
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
		
		
		
		
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}
 //block is show
 //none is  hidden
 // lam bi nguoc

function visibleButton() {
	if (document.getElementById(prefix_component + "hid_ShowPrint").value != 'false') {
		document.getElementById("__divPrint").style.display = "block";
	} else if (document.getElementById(prefix_component + "hid_ShowPrint").value != 'true'){
		document.getElementById("__divPrint").style.display = "none";
	}
	
	if (document.getElementById(prefix_component + "hid_ShowDel").value != 'false') {
		document.getElementById("__divDelete").style.display = "block";
	} else if (document.getElementById(prefix_component + "hid_ShowDel").value != 'true') {
		document.getElementById("__divDelete").style.display = "none";
	}
}



