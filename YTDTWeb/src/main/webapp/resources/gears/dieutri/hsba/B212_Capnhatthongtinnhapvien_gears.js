
function init() {

    if (window.google && google.gears) {
    	
        try {
         	setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
         	setAttrForComboboxForHuyen(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
         	setAttrForComboboxForXa(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           
        	setAttrForCombobox(prefix_component + 'DM_DAN_TOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC',"getDmDanToc()","","","");
			setAttrForCombobox(prefix_component + 'DM_NGHE_NGHIEP_MA','DM_NGHE_NGHIEP_span', 'DM_NGHE_NGHIEP',"getDmNgheNghiep()","","","");
			
			//setAttrForCombobox(prefix_component + 'DM_NGHE_NGHIEP_MA','DM_NGHE_NGHIEP_span', 'DM_NGHE_NGHIEP',"getTuoi(\"" + prefix_component + "__tuoi\");getDmNgheNghiepByYearOld(\"_TuoiHid\")","","","");
            
            setAttrForCombobox(prefix_component + 'DM_BENH_VIEN_MA','DM_BENH_VIEN_span', 'DM_BENH_VIEN',"getDmBenhVien()","","","");
            setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD__1_span', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");
            setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_2','DM_BENH_ICD__2_span', 'DM_BENH_ICD__2',"getDmBenhIcd()","","","");
 			setAttrForCombobox(prefix_component + 'DM_TAI_NAN_MA','DM_PL_TAI_NAN_span', 'DM_TAI_NAN',"getDmTaiNan()","onChangePhuongThucGayTaiNan()","","");
 			setAttrForCombobox(prefix_component + 'DM_PHUONG_TIEN_MA','DM_PHUONG_TIEN_span', 'DM_PHUONG_THUC_GAY_TAI_NAN',"getDmPhuongThucGayTaiNanByTaiNanMa(\"" + prefix_component + "DM_TAI_NAN_MA_pk" + "\")","","","");
			setAttrForCombobox(prefix_component + 'DM_DOI_TUONG_MA','DM_DOI_TUONG_span', 'DM_DOI_TUONG',"getDmDoiTuongTP()","","checkDoiTuong();","");
			
			setAttrForComboboxForKCBBHYT(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN2_span','DM_BENH_VIEN__2',"getDmBenhVien()", "", "", "");
			setAttrForComboboxForTinhBHYT(prefix_component + 'TINHBHYT_MA','DM_TINH_BHYT_span', 'DM_TINH__2',"getDmTinh()","myOnblurTextboxDmNoiDKKCBBD(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN__2',document.getElementById(prefix_component+'TINHBHYT_MA').value);","","");
			
		   
			setAttrForCombobox(prefix_component + 'DM_KHOI_BHYT_MA','DM_KHOI_BHYT_span', 'DT_DM_KHOI_BHYT',"getDtDmKhoiBhyt()","","","");
			setAttrForCombobox(prefix_component + 'DM_DIEU_TRI_MA','DM_DIEU_TRI_span', 'DM_DIEU_TRI',"getDmDieuTri()","","","");
						
            //onInitSetDefault();
            //setValueOnLoad();
            timer.setTimeout(function(){onCompleteGetInforNhapMoi();},500); 
            
        } catch (e) {
          alert("init error: " + e.description);

        }
    }
}

function checkDoiTuong() {
	var ketQuaMa = document.getElementById(prefix_component + "DM_DOI_TUONG_MA").value;
	
	if (ketQuaMa == "BH"){
		disableTP();
		disableMP();
		disableTE();
		enableBH();
		enableTGBH();
		validateBH = true;
		validateTP = false;
		validateMP = false;
		validateTE = false;
		
//	    document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = false;
//		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == true){
//	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
//	    }
		
	} else if (ketQuaMa == "TE") {
		disableTP();
		disableMP();
		disableBH();
		disableTGBH();
		enableTE();
		validateBH = false;
		validateTP = false;
		validateMP = false;
		validateTE = true;
		
//		document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").value = "";
//	    myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
//		document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = true;
//		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
//	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
//	    }
	    
	    document.getElementById(prefix_component + "DM_DIEU_TRI_MA").focus();
	    
	} else if (ketQuaMa == "MP") {
		disableTP();
		disableTE();
		disableBH();
		disableTGBH();
		enableMP();
		validateBH = false;
		validateTP = false;
		validateMP = true;
		validateTE = false;
		
	     document.getElementById(prefix_component + "DM_DIEU_TRI_MA").focus();
	     
	} else if (ketQuaMa == "TP") {
		disableMP();
		disableTE();
		disableBH();
		disableTGBH();
		enableTP();
		validateBH = false;
		validateTP = true;
		validateMP = false;
		validateTE = false;
	    
	    document.getElementById(prefix_component + "DM_DIEU_TRI_MA").focus();
	}
}



function disableBH() {
	document.getElementById(prefix_component + "__sothe").value="";
	document.getElementById(prefix_component + "__sothe").disabled = true;
	
	document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").value = "";
    myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
	document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = true;
	if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
        	changeDisabledAttr("DT_DM_KHOI_BHYT");
    }
    
	document.getElementById(prefix_component + "TINHBHYT_MA").value = "";
	myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
	document.getElementById(prefix_component + "TINHBHYT_MA").disabled = true;
	if (document.getElementById( "DM_TINH__2").disabled == false){
        	changeDisabledAttr("DM_TINH__2");
    }
	
	document.getElementById(prefix_component + "KCBBHYT_MA").value = "";
	myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN__2');
	//myOnblurTextboxDmNoiDKKCBBD(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN__2',document.getElementById(prefix_component+'TINHBHYT_MA').value);
	document.getElementById(prefix_component + "KCBBHYT_MA").disabled = true;
	if (document.getElementById( "DM_BENH_VIEN__2").disabled == false){
        	changeDisabledAttr("DM_BENH_VIEN__2");  
    }
    
	document.getElementById(prefix_component + "__coquan").value = "";
    document.getElementById(prefix_component + "__coquan").disabled = true;
    
}

function disableTE() {
/*
	document.getElementById(prefix_component + "TIEPDON_SOTHETE").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_SOTHETE").value="";
    
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").value="";
    
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value="";
*/    
}

function disableTP() {
/*
	document.getElementById(prefix_component + "TIEPDON_KYHIEU").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_KYHIEU").value="";
    
    document.getElementById(prefix_component + "TIEPDON_QUYEN").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_QUYEN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").value="";
*/    
}

function disableMP() {

	//document.getElementById(prefix_component + "__sothengheo").disabled = true;
    //document.getElementById(prefix_component + "__sothengheo").value="";

}

function enableMP() {

	//document.getElementById(prefix_component + "__sothengheo").disabled = false;
    //document.getElementById(prefix_component + "__sothengheo").value="";

}
function enableTP() {
/*
	document.getElementById(prefix_component + "TIEPDON_KYHIEU").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_KYHIEU").value="";
    
    document.getElementById(prefix_component + "TIEPDON_QUYEN").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_QUYEN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_BIENLAI").value="";
*/    
}

function enableTE() {
/*
	document.getElementById(prefix_component + "TIEPDON_SOTHETE").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_SOTHETE").value="";
    
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_KHAISINH").value="";
    
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value="";
*/    
}

function enableBH() {
	//document.getElementById(prefix_component + "__nambh").disabled = false;
	//document.getElementById(prefix_component + "__nambh").value="";
	
	document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = false;
	if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == true){
        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
    }
	
	document.getElementById(prefix_component + "TINHBHYT_MA").disabled = false;
	if (document.getElementById( "DM_TINH__2").disabled == true){
        	changeDisabledAttr("DM_TINH__2");  
    }
	
	if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
		var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
		if (tinhbhyt) {
			document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
			dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
			myOnblurTextboxTinhBHYT(prefix_component + "TINHBHYT_MA", 'DM_TINH__2');
		}
	}
	
	document.getElementById(prefix_component + "KCBBHYT_MA").disabled = false;
	if (document.getElementById( "DM_BENH_VIEN__2").disabled == true){
        	changeDisabledAttr("DM_BENH_VIEN__2");  
    }
	
	if (document.getElementById(prefix_component + "KCBBHYT_MA").value == ''){
	var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
		if (kcbbhyt !=null && document.getElementById(prefix_component + "KCBBHYT_MA").value =='') {
			document.getElementById(prefix_component + "KCBBHYT_MA").value = kcbbhyt.Ma;
			dojo.byId("DM_BENH_VIEN__2").value = kcbbhyt.Ten;
			
		}
	}
	
    document.getElementById(prefix_component + "__coquan").disabled = false;
    document.getElementById(prefix_component + "__sothe").disabled = false;
    
}

function disableTGBH() {

	//alert(1);
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC3").value="";

}

function enableTGBH() {

	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = false;
    
    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = false;
    
    //document.getElementById(prefix_component + "TIEPDON_TUYEN").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_TUYEN").value="";

}


function onChangePhuongThucGayTaiNan(){
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA_pk").value = "";
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA").value = "";
	document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value = "";
}
function onInitSetDefault(){

			var dantoc = DmDanToc.getByFieldValue("DMDANTOC_DEFA", 1);
			if (dantoc != null && (document.getElementById(prefix_component + "DM_DAN_TOC_MA").value  == null || document.getElementById(prefix_component + "DM_DAN_TOC_MA").value == "")) {
				document.getElementById(prefix_component + "DM_DAN_TOC_MA").value = dantoc.Ma;
				dojo.byId("DM_DAN_TOC").value = dantoc.Ten;
			}
			
			var tinh = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
			if (tinh != null && tinh != false && (document.getElementById(prefix_component + "TINH_MA").value == null || document.getElementById(prefix_component + "TINH_MA").value == "")) {
				document.getElementById(prefix_component + "TINH_MA").value = tinh.Ma;
				dojo.byId("DM_TINH").value = tinh.Ten;
				
			}
			
			var huyen = DmHuyen.getByFieldValue("DMHUYEN_DEFA", 1);
			if (huyen !=null && huyen != false && (document.getElementById(prefix_component + "HUYEN_MA").value ==null || document.getElementById(prefix_component + "HUYEN_MA").value =="")) {
				document.getElementById(prefix_component + "HUYEN_MA").value = huyen.Ma;
				dojo.byId("DM_HUYEN").value = huyen.Ten;
			}
			
			var xa = DmXa.getByFieldValue("DMXA_DEFA", 1);
			if (xa !=null && xa != false && (document.getElementById(prefix_component + "XA_MA").value ==null || document.getElementById(prefix_component + "XA_MA").value == "")) {
				document.getElementById(prefix_component + "XA_MA").value = xa.Ma;
				dojo.byId("DM_XA").value = xa.Ten;
			}
			
			if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
				var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
				if (tinhbhyt) {
					document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
					dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
					myOnblurTextboxTinhBHYT(prefix_component + "TINHBHYT_MA", 'DM_TINH__2');
				}
			}
			
			if (document.getElementById(prefix_component + "KCBBHYT_MA").value == ''){
			var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
				if (kcbbhyt !=null && document.getElementById(prefix_component + "KCBBHYT_MA").value =='') {
					document.getElementById(prefix_component + "KCBBHYT_MA").value = kcbbhyt.Ma;
					dojo.byId("DM_BENH_VIEN__2").value = kcbbhyt.Ten;
					
				}
			}
		  	var gt = DmGioiTinh.getByFieldValue("DMGT_DEFA", 1);
			if (gt  && (document.getElementById(prefix_component + "__gioitinh" + ":1").checked == false && document.getElementById(prefix_component + "__gioitinh" + ":0").checked == false)) {
				if (gt.Ma == "0") {
					document.getElementById(prefix_component + "__gioitinh" + ":1").checked = true;
				} else if (gt.Ma == "1") {
					document.getElementById(prefix_component + "__gioitinh" + ":0").checked = true;
				}
			}
	
	//document.getElementById(prefix_component + "__nambh").value = (new Date()).getFullYear();
	
			if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
			){
				document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
			}
		
			//setDefaultValueForKhoa();
			document.getElementById(prefix_component + "__hoten").focus();	
}
function onCompleteGetInforNhapMoi(){
	onInitSetDefault();
	onCompleteGetInfor();
	
}
function onCompleteGetInfor(){
	highlightOnFocus();
    
	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DM_NGHE_NGHIEP_MA', 'DM_NGHE_NGHIEP');
	
	myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_1', 'DM_BENH_ICD__1');
	myOnblurTextbox(prefix_component + 'DM_BENH_ICD_MA_2', 'DM_BENH_ICD__2');
	
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	myOnblurTextbox(prefix_component + 'DM_TAI_NAN_MA', 'DM_TAI_NAN');
	
	myOnblurTextbox(prefix_component + 'DM_DOI_TUONG_MA', 'DM_DOI_TUONG');
	myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_MA', 'DM_DIEU_TRI');
	
	myOnblurTextbox(prefix_component + 'DM_BENH_VIEN_MA', 'DM_BENH_VIEN');
	
	//set_tuoi();
	//var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	//if(sobenhan == ''){
	//	document.getElementById(prefix_component + "__sobenhan").focus();
	//}
	//else{
	//	document.getElementById(prefix_component + "__giovao").focus();
	//} 
	
	myOnblurTextbox(document.getElementById(prefix_component + "KCBBHYT_MA").id,'DM_BENH_VIEN__2');
	// phuc.lc 12/09/2011 : Fix bug 3993
	myOnblurTextboxTinhBHYT(prefix_component +'TINHBHYT_MA','DM_TINH__2');
	//myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
	
	//onInitSetDefault();
}

function checkForGioi(){
	var valueTen = document.getElementById(prefix_component + "__hoten").value;
	
	if (valueTen != null && valueTen != "" && ( valueTen.indexOf(thi_1) >= 0 || valueTen.indexOf(thi_2) >= 0)){
		
		//alert(valueTen.indexOf(thi_1));
		//alert( document.getElementById(prefix_component + "__gioitinh:1"));
		document.getElementById(prefix_component + "__gioitinh:1").checked = true;
		//alert(document.getElementById(prefix_component + "__nu").checked);	
	}
	
}

function setValueOnLoad(){
  try{
    //alert(1);
	document.getElementById(prefix_component + "__sobenhan").focus();
	if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
	){
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
	}
	
	
  }catch(e){
    alert(e.description);
  }	
}


function setDefaultValueForKhoa(){
	var dmdieutrima = document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value  ;
	if (dmdieutrima == null || dmdieutrima == ""){
		var hinhthucdieutriClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B212_capnhatthongtinnhapvien_hinhthucdieutri");
		if (hinhthucdieutriClientDefault) {
			document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value = hinhthucdieutriClientDefault.Ten;
			myOnblurTextbox(prefix_component + 'DM_DIEU_TRI_MA', 'DM_DIEU_TRI');
		}
	}
}

function luuTruGiaTriClientDefault(){
	
//		var giaTrikhoaMa = document.getElementById(prefix_component + "DM_KHOA_MA").value;
//		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
//		if (giaTrikhoaMa != null && giaTrikhoaMa != ""){
//			var khoaClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B212_capnhatthongtinnhapvien_khoa");
//			//alert("bankhamClientDefault:"+bankhamClientDefault);
//			if (khoaClientDefault == null || khoaClientDefault == false) { //insert
//				var chNames = new Array();
//				chNames[0] = "MaSo";
//				chNames[1] = "Ma";
//				chNames[2] = "Ten";
//				var chValues = new Array();
//				chValues[0] = 212002;
//				chValues[1] = "B212_capnhatthongtinnhapvien_khoa";
//				chValues[2] = document.getElementById(prefix_component + "DM_KHOA_MA").value;	
//			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
//			    //alert("insert");
//			}else{ //update
//				
//				var chNames = new Array();
//				chNames[0] = "Ten";
//				
//				var chValues = new Array();
//				chValues[0] = giaTrikhoaMa;
//				
//				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B212_capnhatthongtinnhapvien_khoa", chNames,chValues );
//				
//			}    	
//		}
		
		/// for hinh thuc dieu tri
		var giaTridieutriMa = document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTridieutriMa != null && giaTridieutriMa != ""){
			var dieutriClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B212_capnhatthongtinnhapvien_hinhthucdieutri");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (dieutriClientDefault == null || dieutriClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 212002;
				chValues[1] = "B212_capnhatthongtinnhapvien_hinhthucdieutri";
				chValues[2] = document.getElementById(prefix_component + "DM_DIEU_TRI_MA").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTridieutriMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B212_capnhatthongtinnhapvien_hinhthucdieutri", chNames,chValues );
				
			}    	
		}
	
}


function formatHoTen(){
	
	var hoTen = document.getElementById(prefix_component + "__hoten").value;
	hoTen = hoTen.trim();

	var result = "";
	if (hoTen != null && hoTen != ""){
	
		var index = hoTen.indexOf(' ');
		if (index != null && parseInt(index) >= 0){
		
			while (index != null && parseInt(index) >= 0  && hoTen != null && hoTen.length > 0){
				var temp =  hoTen.substring(0,index + 1);
				//alert(temp);
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				//alert(temp1);
				temp1 = temp1.toUpperCase();
				
				var temp2 = temp.substring(1,temp.lengh); 
				//alert(temp2);
				temp2=temp2.toLowerCase();
				
				
				result = result + temp1 + temp2;
				 
				hoTen = hoTen.substring(index + 1, hoTen.length );
				
				
								
				
				index = hoTen.indexOf(' ');
			}	
			if (hoTen != null && hoTen != ""){
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
			}
			
		}else{
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
		}	
		
		
		document.getElementById(prefix_component + "__hoten").value = result;	
	}

}

function setMaKhoi(){
	var sothe = document.getElementById(prefix_component + "__sothe").value;
	sothe = sothe.toUpperCase();
	document.getElementById(prefix_component + "__sothe").value = sothe;
	if ( sothe != null && sothe != '') {
			document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").value = sothe.substr(0,2);
	}
	myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
	
}

function lockDoiTuong() {	
    document.getElementById(prefix_component + "DM_DOI_TUONG_MA").disabled=(document.getElementById(prefix_component + "lockDoituong").value == "true");

    var widget = dijit.byId("DM_DOI_TUONG");
    if (widget == null){		    	
    	document.getElementById("DM_DOI_TUONG").disabled = (document.getElementById(prefix_component + "lockDoituong").value == "true");		    	
    }else{
     	widget.setAttribute('disabled',(document.getElementById(prefix_component + "lockDoituong").value == "true"));  
    }
	
}

function unlockDoiTuong() {
	
	if (document.getElementById("DM_DOI_TUONG").disabled == true){
        changeDisabledAttr("DM_DOI_TUONG");  
   	}
    document.getElementById(prefix_component + "DM_DOI_TUONG_MA").disabled = false;
	
}

