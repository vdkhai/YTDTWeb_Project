/*
var DT_DM_KET_QUA  = new Array (
   	   ["DT_DM_KET_QUA_MAPHU", "DT_DM_KET_QUA_MAPHU1", "MaSo", "Ten", "NgayChinhSua", "DaXoa"],
         ["double", "double", "varchar(15)", "varchar(250)", "double", "integer"]);           
*/                      
function init() {
	
    if (window.google && google.gears) {
    	
        //try {
               setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","","");
              
              
              //  alert(1012);
               //setAttrForCombobox_StoreValue(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD__1','10', DmBenhIcd);
			   setAttrForCombobox(prefix_component + "DM_BENH_ICD_MA_1","DM_BENH_ICD_span1","DM_BENH_ICD__1","getMyDataList_long_data()","","","");
			   
			  //alert(1);

               //setAttrForCombobox_StoreValue(prefix_component + 'DM_BENH_VIEN_MA_1','DM_BENH_VIEN_span1', 'DM_BENH_VIEN__1','10', DmBenhVien);
               setAttrForCombobox(prefix_component + "DM_BENH_VIEN_MA_1","DM_BENH_VIEN_span1","DM_BENH_VIEN__1","getDmBenhVien()","","","");
              
               //alert(1);
               //setAttrForCombobox_StoreValue(prefix_component + 'DM_BENH_VIEN_MA_2','DM_BENH_VIEN_span2', 'DM_BENH_VIEN__2','10', DmBenhVien);
               setAttrForCombobox(prefix_component + "DM_BENH_VIEN_MA_2","DM_BENH_VIEN_span2","DM_BENH_VIEN__2","getDmBenhVien()","","","");
               
                //alert(1);
               //setAttrForCombobox_StoreValue(prefix_component + 'DT_DM_KET_QUA_MA','DT_DM_KET_QUA_span', 'DT_DM_KET_QUA','10', DtDmKetQua);
               setAttrForCombobox(prefix_component + "DT_DM_KET_QUA_MA","DT_DM_KET_QUA_span","DM_KET_QUA_DIEU_TRI","getDmKetQuaDieuTri()","","","");
               
               setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa(\"" + prefix_component + "DM_KHOA_MA\")","","","");
               
               //alert(1);
               //---Set value on load---
 	          
 	            timer.setTimeout(function(){setValueOnLoad();},100); 					  
          	   //alert(1);
        //} catch (e) {
        // 	alert(" init(): " + e );
        //}
    }
}	 

function getMyDataList_long_data(){
var catalog = new DmBenhIcd({});
	var textboxValue  = document.getElementById("DM_BENH_ICD__1").value;
	if (textboxValue == null || textboxValue == "" ){
		return;
	}
	return catalog.getDataList_long_data(textboxValue);
}

function blockInphieu() {
	//alert(document.getElementById(prefix_component + "__maFinish").value);
	
	if (document.getElementById(prefix_component + "hid_hienThiInPhieu").value != '') {
		document.getElementById("__divIn").style.display = "block";
	} else {
		document.getElementById("__divIn").style.display = "none";
	}
	
	if (document.getElementById(prefix_component + "hid_hienThiGhiNhan").value != '') {
		document.getElementById("__divGhiNhan").style.display = "block";
	} else {
		document.getElementById("__divGhiNhan").style.display = "none";
	}
}

function setValueOnLoad(){
	 
	document.getElementById(prefix_component + "DM_KHOA_MA").focus();

	blockInphieu();


	onCompleteGetInfor();
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", "DT_DM_NHAN_VIEN__1");
}

/**
*
*
	reloadValueForCombobox()
*
*	
**/
function reloadValueForCombobox(){
    //alert("myOnblurTextboxJSF");
    try{
        //alert(1);
		//document.getElementById(prefix_component + "DM_THUOC_MASO").focus();
		//document.getElementById(prefix_component + "DM_THUOC").value = "";
		blockInphieu();
		//dataList = null;
	    //listCatalogJSF_PDTRU("__listtonkho",null); 
	}catch(e){
		alert("reloadValueForCombobox():"+e);
	}
}   

function reloadValueForCombobox_edit(){
	
}

function onCompleteGetInfor(){
	//messageinfo();
	//myOnblurTextbox(prefix_component + 'KCBBHYT_MA','DT_DM_KCB_BHYT');
	//myOnblurTextbox(prefix_component + 'DM_PL_TAI_NAN_MA','DM_TAI_NAN');
	//myOnblurTextbox(prefix_component + 'DT_DM_DOI_TUONG_MA','DM_DOI_TUONG');
	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_1', 'DM_BENH_VIEN__1');
	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA_2','DM_BENH_VIEN__2');
	myOnblurTextbox(prefix_component + 'DT_DM_KET_QUA_MA','DT_DM_KET_QUA');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD__1');
	//alert(3);

	myOnblurTextbox(prefix_component + 'DM_KHOA_MA','DM_KHOA');
	//myOnblurTextbox(prefix_component + 'DM_KHOA_MA_2','DM_KHOA__2');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN__1');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3','DT_DM_NHAN_VIEN__3');
	//myOnblurTextbox(prefix_component + 'DT_DM_NGUON_MA','DT_DM_NGUON');
	//myOnblurTextbox(prefix_component + 'DT_DM_LOAI_MA','DT_DM_LOAI');
	//myOnblurTextbox(prefix_component + 'DM_KINH_PHI_MA','DM_KINH_PHI');
	//myOnblurTextbox(prefix_component + 'DM_PHAN_LOAI_MA','DM_PHAN_LOAI');
	//myOnblurTextboxForDmThuoc(prefix_component + "DM_THUOC_MASO", prefix_component + "DM_THUOC");

	blockInphieu();
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	if(sobenhan == ''){
	
		var khoaMa = document.getElementById(prefix_component + "DM_KHOA_MA").value;
		//alert("khoaMa:"+khoaMa);
		if(khoaMa != ''){
			document.getElementById(prefix_component + "__sobenhan").focus();
		}
		else{
			document.getElementById(prefix_component + "DM_KHOA_MA").focus();
		}
	}
	else{
	    //alert(5)
		document.getElementById(prefix_component + "__noidungmo").focus();
	}
	//alert(6);
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
}



