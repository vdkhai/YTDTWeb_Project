var canfocus = true;    
function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD_span1', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_CLS_BANG_GIA_MA_1','DT_DM_CLS_BANG_GIA_span1', 'DT_DM_CLS_BANG_GIA__1',"getDmPhauThuatThuThuatCls()","","setMaSoPttt('DT_DM_CLS_BANG_GIA_MA_1','DT_DM_CLS_BANG_GIA_MA_1_pk');","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_VO_CAM_MA_1','DT_DM_VO_CAM_span1', 'DT_DM_VO_CAM__1',"getDtDmVoCam()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmNhanVien()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_2','DT_DM_NHAN_VIEN_span2', 'DT_DM_NHAN_VIEN__2',"getDtDmNhanVien()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_4','DT_DM_NHAN_VIEN_span4', 'DT_DM_NHAN_VIEN__4',"getDtDmNhanVien()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_3','DT_DM_NHAN_VIEN_span3', 'DT_DM_NHAN_VIEN__3',"getDtDmNhanVien()","","","");
        	
        	setAttrForCombobox(prefix_component + 'DM_KHOA_MA_1','DM_KHOA_span1', 'DM_KHOA__1',"getDmKhoa_NoiTru()","","","");
        	
        	
            timer.setTimeout(function(){focusInit();},300);
        } catch (e) {
        	alert("init error: " + e.description); 
        }
    }    
}

function setMaSoPttt(IdMa,IdMaSo){
	  try{	
		var maKTObj = document.getElementById(prefix_component + IdMa);
		getMaSoPttt(maKTObj.value,IdMaSo);
	  }catch(e){
	    alert ("error at getMaPttt()");
	  }
	}

function focusInit(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById(prefix_component + "__magiay").focus(); 
	
	myOnblurTextbox( prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
	myOnblurTextbox( prefix_component + 'DT_DM_CLS_BANG_GIA_MA_1', 'DT_DM_CLS_BANG_GIA__1');
	myOnblurTextbox( prefix_component + 'DT_DM_VO_CAM_MA_1', 'DT_DM_VO_CAM__1');
	myOnblurTextbox( prefix_component + 'DM_KHOA_MA_1', 'DM_KHOA__1');
	
	myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
	myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_4', 'DT_DM_NHAN_VIEN__4');
	myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
	
}

function oncompleteOfHSBA(){
//onTuoi();
//	alert('oncompleteOfHSBA');
	if (document.getElementById(prefix_component + "nofoundHSBA").value == "true") {
		document.getElementById(prefix_component + "nofoundHSBA").value = "false";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById(prefix_component + "__sobenhan").focus();
		
		
		return;
	}
	timer.setTimeout(function(){
		myOnblurTextbox( prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
		myOnblurTextbox( prefix_component + 'DT_DM_CLS_BANG_GIA_MA_1', 'DT_DM_CLS_BANG_GIA__1');
		myOnblurTextbox( prefix_component + 'DT_DM_VO_CAM_MA_1', 'DT_DM_VO_CAM__1');
		myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
		myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_2', 'DT_DM_NHAN_VIEN__2');
		myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_4', 'DT_DM_NHAN_VIEN__4');
		myOnblurTextbox( prefix_component + 'DT_DM_NHAN_VIEN_MA_3', 'DT_DM_NHAN_VIEN__3');
		myOnblurTextbox( prefix_component + 'DM_KHOA_MA_1', 'DM_KHOA__1');
	},300);
}

function oncompleteOfPhieuPhauThuatThuThuat(){
//	alert('oncompleteOfPhieuPhauThuatThuThuat');
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__magiay").value = "";
		document.getElementById(prefix_component + "__magiay").focus();
		return;
	} 
	document.getElementById("__divInPhieu").style.display = "block";
	
	//onTuoi();
//	alert('oncompleteOfPhieuPhauThuatThuThuat');
}

function oncompleteOfTiepTucNhap(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
	
	document.getElementById("DM_BENH_ICD__1").value = "";
	document.getElementById("DT_DM_CLS_BANG_GIA__1").value = "";
	document.getElementById("DT_DM_VO_CAM__1").value = "";
	document.getElementById("DT_DM_NHAN_VIEN__4").value = "";
	document.getElementById("DT_DM_NHAN_VIEN__1").value = "";
	document.getElementById("DT_DM_NHAN_VIEN__2").value = "";
	document.getElementById("DT_DM_NHAN_VIEN__3").value = "";
	document.getElementById("DM_KHOA__1").value = "";

}

function oncompleteOfGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__magiay").focus();		
		return;
	}
	document.getElementById("__divGhiNhan").style.display = "none";
	document.getElementById("__divInPhieu").style.display = "block";
	//onTuoi();
//	alert('oncompleteOfGhiNhan');
}

function onChangeTK(value){
	var now = new Date();	
	var d = new Date(now.getTime() + (value * 24 * 60 * 60 * 1000));	
	var rs = "";
	var ndate = d.getDate();	
	if (ndate<10) {
		rs = rs + "0" + ndate + "/";
	}else{
		rs = rs + ndate + "/";
	}
	var nmonth = d.getMonth();
	nmonth++;
	
	if (nmonth<10) {
		rs = rs + "0" + nmonth + "/";
	}else{
		rs = rs + nmonth + "/";
	}	
	var nyear = d.getFullYear();
	var rs = rs + nyear;
	///onTuoi();
}

function onChangeNTK(value){
	var str = value.split("/");
	var crr = new Date();
	var d = new Date();
	d.setDate(str[0]);
	d.setMonth(str[1]-1);
	d.setFullYear(str[2]);
	var rs = (d.getTime() - crr.getTime())/(24 * 60 * 60 * 1000);
	//onTuoi();
}

function onTuoi(){
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Nam)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Nam)";
	}
		
}



