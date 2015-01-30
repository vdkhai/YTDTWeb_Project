function init() {
    if (window.google && google.gears) {
        //try {
            timer = google.gears.factory.create('beta.timer');
            
            BenhNhan.createTable();
            TiepDon.createTable();
            
            setAttrForCombobox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG", "getDmDoiTuongTP()", 
            					"", "setTyleBnTra(); checkDoiTuong();setTienKham();", "");
            setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            setAttrForComboboxForHuyen(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            setAttrForComboboxForXa(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
            setAttrForCombobox(prefix_component + "DANTOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");
            
            //setAttrForCombobox(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN_span','DM_BENH_VIEN',"getDmBenhVien()", "", "document.getElementById(prefix_component+'TIEPDON_GIATRI1').focus()", "");
            
            setAttrForComboboxForKCBBHYT(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN_span','DM_BENH_VIEN',"getDmBenhVien()", "", "document.getElementById(prefix_component+'TIEPDON_GIATRI1').focus()", "");
            setAttrForCombobox(prefix_component + 'TIEPDON_BANKHAM','DT_DM_BAN_KHAM_span','DT_DM_BAN_KHAM',"getDtDmBanKhamKoCCL()", "", "getStt()", "");
			setAttrForComboboxForTinhBHYT(prefix_component + 'TINHBHYT_MA','DM_TINH2_span','DM_TINH__2', "getDmTinh()", "myOnblurTextboxDmNoiDKKCBBD(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN',document.getElementById(prefix_component+'TINHBHYT_MA').value);", "", "");
	        setAttrForCombobox(prefix_component + 'KHOIBHYT_MA','DT_DM_KHOI_BHYT_span','DT_DM_KHOI_BHYT', "getDtDmKhoiBhyt()", "", "focusToGiaTri0();", "");
	        setAttrForCombobox(prefix_component + 'TIEPDON_LOAIKHAM','DT_DM_TIEN_KHAM_span','DT_DM_CLS_BANG_GIA', "getDtDmClsBangGiaKham()", "", 
	        					"setTienKham()", "");
	        setValueOnLoad();
	        document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
            //alert(222);
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
	}
}

function lostFocusForBHYT(){

	if(document.getElementById(prefix_component+'KCBBHYT_MA').className.match(/focus/gi))  
		document.getElementById(prefix_component+'KCBBHYT_MA').className = document.getElementById(prefix_component+'KCBBHYT_MA').className.replace(/focus/gi,"");
}

function loadDKonline() {
	//alert("loadDKonline()");
	set_ngaysinh_namsinh(prefix_component + 'BENHNHAN_TUOI',prefix_component + 'BENHNHAN_NGAYSINH',prefix_component + 'BENHNHAN_NAMSINH');
	myOnblurTextbox_DoiTuong(prefix_component + 'DOITUONG_MA','DM_DOI_TUONG',prefix_component + '__tylebntra',prefix_component + 'TIEPDON_TYLEBH');
	
	timer.setTimeout(function(){ checkSoTheBHYT(); },400);
	setMaKhoi();
	testBHYTTrungTrongNgay(false);
	
}


function checkSoTheBHYT() {
	var sotehbhyt = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
	//var hotenbn = document.getElementById(prefix_component + "BENHNHAN_HOTEN").value;
	
	if (sotehbhyt != null && sotehbhyt !=''){
		if (sotehbhyt.length < 15){
			alert("Chiều dài số thẻ BHYT phải bằng 15");
			//document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
		}
	}

}

function focusAfterSave(){
	document.getElementById("__nhaplai").focus();
} 
var nhapthebhyt = false;
function focusToXaMa(){
	
	
	//if(document.getElementById(prefix_component +"TINH_MA").className.match(/focus/gi))              
    //	document.getElementById(prefix_component +"TINH_MA").className = document.getElementById(prefix_component +"TINH_MA").className.replace(/focus/gi,"");
    //document.getElementById(prefix_component + "TINH_MA").focus();
	document.getElementById(prefix_component + "XA_MA").focus();
	document.getElementById(prefix_component + "XA_MA").select();
	//alert(1);
}
function focusToLoaiKham(){
	document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").focus();
	document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").select();
	//alert(1);
}
function focusToGiaTri0(){
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").focus();
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").select();
	//alert(1);
}
function setTienKham() {
	
	var tienKhamMa = document.getElementById(prefix_component + "TIEPDON_LOAIKHAM");
	var tyleBnTra = document.getElementById(prefix_component + "TIEPDON_BNTRA");
	var tienKham = DtDmClsBangGia.getByFieldValue("Ma", tienKhamMa.value);
	var giatriBhTaiBVFrom = document.getElementById(prefix_component + "TIEPDON_GIATRI3").value;
	var giatriBhTaiBVTo = document.getElementById(prefix_component + "TIEPDON_GIATRI4").value;
	
	var giatriBhTheFrom = document.getElementById(prefix_component + "TIEPDON_GIATRI1").value;
	var giatriBhTheTo = document.getElementById(prefix_component + "TIEPDON_GIATRI2").value;
	var ngayBatDauBh = (giatriBhTaiBVFrom != '' ? giatriBhTaiBVFrom : giatriBhTheFrom);
	var ngayHetHanBh = (giatriBhTaiBVTo != '' ? giatriBhTaiBVFrom : giatriBhTheTo);
	if (tienKham) {
		var dtMa = document.getElementById(prefix_component + "DOITUONG_MA").value;
		// neu la cn, th7 thi lay don gia 2
		var dDate = new Date();
		var thu = dDate.getDay(); //Day of the week(0-6). 0 = Sunday, ... , 6 = Saturday
		// Tam thoi khong xet thu 7 & CN
		//if (thu == 0 || thu == 6){
		//	tyleBnTra.value = tienKham.DTDMCLSBG_DONGIA2;
		//}else 		
		if (tienKham.DMCLSBG_MIEN == 1) {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIAMP;
		}else if (dtMa == "BH") {
			if (checkHetHanBH(ngayBatDauBh,ngayHetHanBh)) {
				tyleBnTra.value = tienKham.DTDMCLSBG_DONGIA;
			} else {
				tyleBnTra.value = tienKham.DTDMCLSBG_DONGIABH;
			}
		} else if (dtMa == "MP") {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIAMP;
		} else {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIA;
		}
			
		//
		
		/*
		if (dtMa == "BH" || dtMa == "TE") {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIABH;
		} else if (dtMa == "MP") {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIAMP;
		} else {
			tyleBnTra.value = tienKham.DTDMCLSBG_DONGIA;
		}
		*/
	} else {
		tyleBnTra.value = "";
	}
}


function checkDoiTuong() {
	//alert("checkDoiTuong");
	var ketQuaMa = document.getElementById(prefix_component + "DOITUONG_MA").value;
	
	//alert(ketQuaMa);
	if (ketQuaMa == "BH"){
		enableBH();
		disableTP();
		disableMP();
		disableTE();
		enableTGBH();
		validateBH = true;
		validateTP = false;
		validateMP = false;
		validateTE = false;
		
	    document.getElementById(prefix_component + "KHOIBHYT_MA").disabled = false;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == true){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
	    }
	    
		
	} else if (ketQuaMa == "TE") {
		enableTE();
		disableTP();
		disableMP();
		disableBH();
		//enableTGBH();
		disableTGBH();
		validateBH = false;
		validateTP = false;
		validateMP = false;
		validateTE = true;
		
		document.getElementById(prefix_component + "KHOIBHYT_MA").disabled = true;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
	    }
	    document.getElementById(prefix_component + "KHOIBHYT_MA").value="";
	    myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	    
	    
	    
	    //////////////////
	    document.getElementById(prefix_component + "KCBBHYT_MA").disabled = true;
		if (document.getElementById( "DM_BENH_VIEN").disabled == false){
	        	changeDisabledAttr("DM_BENH_VIEN");  
	    }
	    document.getElementById(prefix_component + "KCBBHYT_MA").value="";
	    myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN');
	    
	    
	    /*
	    document.getElementById("DT_DM_TUYEN_KCB").value = "";		
	    var search = dijit.byId("DT_DM_TUYEN_KCB");
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		search.store = CatalogStore;
		if (document.getElementById( "DT_DM_TUYEN_KCB").disabled == false){
	        	changeDisabledAttr("DT_DM_TUYEN_KCB");  
	    }
	    */
	    //
	    
    	////////////////
    	enableTGTE();
	    
	    
	} else if (ketQuaMa == "MP") {
		disableMP();
		disableTP();
		disableTE();
		disableBH();
		disableTGBH();
		validateBH = false;
		validateTP = false;
		validateMP = true;
		validateTE = false;
		enableMP();
		document.getElementById(prefix_component + "KHOIBHYT_MA").disabled = true;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
	    }
	    //alert(1);
		
		enableTGTE();
	} else if (ketQuaMa == "TP") {
		enableTP();
		disableMP();
		disableTE();
		disableBH();
		disableTGBH();
		validateBH = false;
		validateTP = true;
		validateMP = false;
		validateTE = false;
		
		document.getElementById(prefix_component + "KHOIBHYT_MA").disabled = true;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
	    }
	    document.getElementById(prefix_component + "KHOIBHYT_MA").value="";
	    myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	    
	}
}

function setTyleBnTra() {
	var objArr = DmDoiTuong.filter("Ten like ?", comboboxValue).toArray();
	var havingData = false;
	for (var i = 0; i < objArr.length; i++) {
	    if (objArr[i].DMDOITUONG_TYLEMIEN != null && objArr[i].DMDOITUONG_TYLEMIEN != "") {
	    	var value = 100 - parseInt(objArr[i].DMDOITUONG_TYLEMIEN);
	       	document.getElementById(prefix_component + "__tylebntra").value = value;
	       	document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = parseInt(objArr[i].DMDOITUONG_TYLEMIEN);
	    	havingData = true;
	    }
	}
	if(havingData == true) {
	    	return;
	}
	document.getElementById(prefix_component + "__tylebntra").value = "";
	document.getElementById(prefix_component + "TIEPDON_TYLEBH") .value = "";
}
function setTuyen(){
	
}
/*
function setTyleBNTraForBHYT(){

	var doituongBHYT = document.getElementById(prefix_component + "DOITUONG_MA").value;
	if (doituongBHYT != 'BH'){
		return;
	}

	var maKhoiBHYT = document.getElementById(prefix_component + "KHOIBHYT_MA").value;
	//alert(maKhoiBHYT);
	if (maKhoiBHYT != null && maKhoiBHYT != ''){
		var khoibhyt = DtDmKhoiBhyt.getByFieldValue("Ma", maKhoiBHYT);
		if (khoibhyt != null && khoibhyt.DTDMKHOIBHYT_TYLEBHYT1 != null){
			//alert(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
			
			document.getElementById(prefix_component + "__tylebntra").value = khoibhyt.DTDMKHOIBHYT_TYLEBHYT1;
			var tyleBHTra = 100 - parseInt(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
			document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = tyleBHTra;
			
			
		}else{
			khoibhyt = DtDmKhoiBhyt.getByFieldValue("Ma", maKhoiBHYT.toLowerCase());
			if (khoibhyt != null  && khoibhyt.DTDMKHOIBHYT_TYLEBHYT1 != null){
				//alert(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
				document.getElementById(prefix_component + "__tylebntra").value = khoibhyt.DTDMKHOIBHYT_TYLEBHYT1;
				var tyleBHTra = 100 - parseInt(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
				document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = tyleBHTra;
			}else{
				khoibhyt = DtDmKhoiBhyt.getByFieldValue("Ma", maKhoiBHYT.toUpperCase());
				if (khoibhyt != null && khoibhyt.DTDMKHOIBHYT_TYLEBHYT1 != null ){
					//alert(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
					document.getElementById(prefix_component + "__tylebntra").value = khoibhyt.DTDMKHOIBHYT_TYLEBHYT1;
					var tyleBHTra = 100 - parseInt(khoibhyt.DTDMKHOIBHYT_TYLEBHYT1);
					document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = tyleBHTra;
				}
			}
		}
		
	}
	
	
}
*/
function setCurrentHourDateFromGetTDFromBHYT(){
	    document.getElementById("NGAYTG").value = getDateSystem_dd_MM_yyyy();
		document.getElementById(prefix_component + "__ngayhientai").value = getDateSystem_dd_MM_yyyy();
		var hourMin = getCurrentHourMin();

		document.getElementById(prefix_component + "GIO").value = hourMin;
		temp();
}

function setValueOnLoad(){
	try{
		document.getElementById("NGAYTG").value = getDateSystem_dd_MM_yyyy();
		document.getElementById(prefix_component + "__ngayhientai").value = getDateSystem_dd_MM_yyyy();
		
		var hourMin = getCurrentHourMin();

		document.getElementById(prefix_component + "GIO").value = hourMin;
		/*
		getDefaultValue("dantoc", "DANTOC_MA");
		getDefaultValue("tinh", "TINH_MA");
		getDefaultValue("huyen", "HUYEN_MA");
		getDefaultValue("xa", "XA_MA");
		getDefaultValue("gioi","BENHNHAN_GIOI");
		*/
		setTimeout(function() {
			var dantoc = DmDanToc.getByFieldValue("DMDANTOC_DEFA", 1);
			if (dantoc != null && document.getElementById(prefix_component + "DANTOC_MA").value == '') {
				document.getElementById(prefix_component + "DANTOC_MA").value = dantoc.Ma;
				dojo.byId("DM_DAN_TOC").value = dantoc.Ten;
			}
			
			var tinh = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
			
			if (tinh !=null && tinh != false && document.getElementById(prefix_component + "TINH_MA").value == '') {
				document.getElementById(prefix_component + "TINH_MA").value = tinh.Ma;
				dojo.byId("DM_TINH").value = tinh.Ten;
			}
			
			var huyen = DmHuyen.getByFieldValue("DMHUYEN_DEFA", 1);
			if (huyen !=null && huyen != false && document.getElementById(prefix_component + "HUYEN_MA").value== '' ) {
				document.getElementById(prefix_component + "HUYEN_MA").value = huyen.Ma;
				dojo.byId("DM_HUYEN").value = huyen.Ten;
			}
			//alert(1);
			var xa = DmXa.getByFieldValue("DMXA_DEFA", 1);
			if (xa != null && xa != false && document.getElementById(prefix_component + "XA_MA").value == '') {
				document.getElementById(prefix_component + "XA_MA").value = xa.Ma;
				dojo.byId("DM_XA").value = xa.Ten;
			}
			
			var gt = DmGioiTinh.getByFieldValue("DMGT_DEFA", 1);
			if (gt != null && document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":0").checked == false
			   && document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":1").checked == false) {
				if (gt.Ma == "0") {
					document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":1").checked = true;
				} else if (gt.Ma == "1") {
					document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":0").checked = true;
				}
			}
			 if (document.getElementById(prefix_component + "KCBBHYT_MA").value == ''){
			var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
			if (kcbbhyt !=null && document.getElementById(prefix_component + "KCBBHYT_MA").value =='') {
				document.getElementById(prefix_component + "KCBBHYT_MA").value = kcbbhyt.Ma;
				dojo.byId("DM_BENH_VIEN").value = kcbbhyt.Ten;
			}
			}
			if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
				var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
				if (tinhbhyt) {
					document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
					dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
				}
			}
			//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").value = (new Date().getFullYear());
			
			
			/*
			var arrayBangGiaKham = getDtDmClsBangGiaKham();
			if (arrayBangGiaKham != null && arrayBangGiaKham.length == 1){
				var catalog = arrayBangGiaKham[0];
				document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value = catalog.Ma;
				myOnblurTextbox(prefix_component + 'TIEPDON_LOAIKHAM', 'DT_DM_CLS_BANG_GIA');
			}
			*/
			
			//document.getElementById(prefix_component + "BENHNHAN_HOTEN").focus();
		
			if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
				document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
				document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
			){
				document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
			}
			
			setDefaultValueForBanKham();
			setDefaultValueForLoaiKham();
		}, 200);
		/*
		if (document.getElementById(prefix_component + "BENHNHAN_GIOI").value == "true") {
			document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":0").checked = true;
		} else if (document.getElementById(prefix_component + "BENHNHAN_GIOI").value == "false") {
		  	document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":1").checked = true;
		}
		*/
		document.getElementById(prefix_component + "__bacsiThamkham").value = "";
		
	
	} catch(e) {
		alert("setValueOnLoad():"+ e.description);
	}
	
}


function setDefaultValueForBanKham(){
	
	
	var bankhamcurrent = document.getElementById(prefix_component + "TIEPDON_BANKHAM").value;
	
	if (bankhamcurrent == null || bankhamcurrent == ''){
		var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B111_dangkykhambenh_bankham");
		if (bankhamClientDefault) {
			document.getElementById(prefix_component + "TIEPDON_BANKHAM").value = bankhamClientDefault.Ten;
			myOnblurTextbox(prefix_component + 'TIEPDON_BANKHAM', 'DT_DM_BAN_KHAM');
			//document.getElementById(prefix_component + "TIEPDON_BANKHAM").select();
			
			if (bankhamClientDefault.Ten == "SAN") {
					
					
						document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP" + ":1").checked = true;
				
			}
				
		}
	}
}
function setDefaultValueForLoaiKham(){
	
	
	var loaikhamcurrent = document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value;
	
	if (loaikhamcurrent == null || loaikhamcurrent == ''){
		var loaikhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B111_dangkykhambenh_loaikham");
		if (loaikhamClientDefault) {
			document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value = loaikhamClientDefault.Ten;
			myOnblurTextbox(prefix_component + 'TIEPDON_LOAIKHAM', 'DT_DM_CLS_BANG_GIA');
			//document.getElementById(prefix_component + "TIEPDON_BANKHAM").select();
			
		}
	}
}
// luu y : chi goi khi nhan tu menu
function luuTruGiaTriClientDefaultForBanKham(){
		//alert(1);
		var giaTriBanKhamMa = document.getElementById(prefix_component + "TIEPDON_BANKHAM").value;
		//alert("giaTriBanKhamMa:"+giaTriBanKhamMa);
		if (giaTriBanKhamMa != null && giaTriBanKhamMa != ""){
			var bankhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B111_dangkykhambenh_bankham");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bankhamClientDefault == null || bankhamClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 111001;
				chValues[1] = "B111_dangkykhambenh_bankham";
				chValues[2] = document.getElementById(prefix_component + "TIEPDON_BANKHAM").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBanKhamMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B111_dangkykhambenh_bankham", chNames,chValues );
				
			}  
			
			document.getElementById(prefix_component + "maBanKhamOut").value = giaTriBanKhamMa;
		}
	
}
function luuTruGiaTriClientDefaultForLoaiKham(){
	//alert(1);
	var giaTriLoaiKhamMa = document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value;
	//alert("giaTriBanKhamMa:"+giaTriBanKhamMa);
	if (giaTriLoaiKhamMa != null && giaTriLoaiKhamMa != ""){
		var loaikhamClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B111_dangkykhambenh_loaikham");
		//alert("bankhamClientDefault:"+bankhamClientDefault);
		if (loaikhamClientDefault == null || loaikhamClientDefault == false) { //insert
			var chNames = new Array();
			chNames[0] = "MaSo";
			chNames[1] = "Ma";
			chNames[2] = "Ten";
			var chValues = new Array();
			chValues[0] = 111001;
			chValues[1] = "B111_dangkykhambenh_loaikham";
			chValues[2] = document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value;	
			insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			//alert("insert");
		}else{ //update
			
			var chNames = new Array();
			chNames[0] = "Ten";
			
			var chValues = new Array();
			chValues[0] = giaTriLoaiKhamMa;
			
			updateObjectWithTrueValue(DtDmClientDefault,"Ma","B111_dangkykhambenh_loaikham", chNames,chValues );
			
		}
		
		
	}
	
}

//return giogio:phutphut
function getCurrentHourMin(){

    var curhour = new Date().getHours() + "";
    if (curhour.length == 1){
      curhour = "0" + curhour;
    }
    
    var curmin = new Date().getMinutes() + "";
    if (curmin.length == 1){
      curmin = "0" + curmin;
    }
	return  curhour  + ":" +  curmin ;
}


function temp() {
	document.getElementById(prefix_component + "TIEPDON_NGAYGIO").value = document.getElementById("NGAYTG").value + " " + document.getElementById(prefix_component + "GIO").value;
}

var MAPHU_hidden;
var MAPHU_TD_hidden;
function getMAPHU() {
	MAPHU_hidden = document.getElementById(prefix_component + "MAPHU").value;
	MAPHU_TD_hidden = document.getElementById(prefix_component + "MAPHU1").value;
}
//---"Sá»­a láº¡i" button---
function sua_lai_event(){
	maphu = document.getElementById(prefix_component + "MAPHU").value;
	maphu1 = document.getElementById(prefix_component + "MAPHU1").value;

	if (maphu1 != null && maphu1 != "") {
	 	var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", maphu1).remove();
	 	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU1 = ?", maphu1).remove();
	 	//add here because of can not be deleted
	 	var listBn1 = BenhNhan.filter("BENH_NHAN_MAPHU = ?", maphu).remove();
	 }
	 else if (maphu != null && maphu != ""){
	 	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", maphu).remove();
	 }
	 deleteTableValue("TIEP_DON");
	 deleteTableValue("BENH_NHAN");
	 myResetAll(); 
	  
	 MAPHU_hidden = "";
	 document.getElementById(prefix_component + "MAPHU").value = "";
	 MAPHU_TD_hidden = ""; 
	 document.getElementById(prefix_component + "MAPHU1").value = "";
	 document.getElementById("__msg").innerHTML = "";
	 setValueOnLoad();
	 document.getElementById(prefix_component + "__bacsiThamkham").value = "";
	 document.getElementById(prefix_component + "BENHNHAN_HOTEN").focus();
	 document.getElementById("div_ghinhan").style.display = "block";
	 document.getElementById("div_huykham").style.display = "block";
}

//---"Ghi nháº­n" button---
function ghi_nhan_event(){
	
	//alert(0);
	var ret = onSubmit();
	if (ret == true){
	  //return;
	 
	
		//myConfirm = confirm("Ghi nháº­n ÄÄng kÃ½ khÃ¡m chá»¯a bá»nh?");
		//if(myConfirm == false) {
		//	return; 
		//}
		/*
		if (!google.gears.factory || !db) {
		  //alert("error google.gears.factory  or db");
	        return;
	    }
	  */
	  
		if (document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU_TEMP").checked == true){
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU").value = 1;
		}else{
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU").value = 0;
		}
	  
		if (document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU_TEMP").checked == true){
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU").value = 1;
		}else{
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU").value = 0;
		}
	  
	  //////////////////////////////////////////////////////
	  // 							20110520 bao.ttc: them yeu cau set TUYEN theo TINH/HUYEN --> set trong SendTiepDonAction.java
	  //Cùng tuyến: nơi DK KCB trùng với mã bệnh viện khám.
	  //Khác tuyến: cùng tỉnh khác nơi DK KCB.
	  //Tuyến khác: đổi thành Ngoại tỉnh, khác tỉnh với nhau
//	  var kcbbhyt = document.getElementById(prefix_component + "KCBBHYT_MA").value ; 
//	  if (kcbbhyt == mabenhvien){
//	  	document.getElementById(prefix_component + "TIEPDON_TUYEN").value = 1;
//	  }else{ //kcbbhyt != mabenhvien
//	  	 var tinh = document.getElementById(prefix_component + "TINHBHYT_MA").value ; 
//	  	if (tinhbenhvien == tinh){
//	  		document.getElementById(prefix_component + "TIEPDON_TUYEN").value = 2;
//	  	}else{
//	  		document.getElementById(prefix_component + "TIEPDON_TUYEN").value = 3;
//	  	}
//	  }
	  
	  //////////////////////////////////////////////////////
	    MAPHU_instant = new Date().getTime();
		getMAPHU();
		if (MAPHU_TD_hidden == ""){
			//alert(2);
		    if(document.getElementById(prefix_component + "TIEPDON_MA").value == ""){
		    	if (MAPHU_hidden == "") {
		    		if (document.getElementById(prefix_component + "BENHNHAN_MA").value == "") {
		    			try {
		        			if(document.getElementById(prefix_component + "BENHNHAN_HOTEN").value !="" &&document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value !="" 
		        				&& document.getElementById(prefix_component + "TIEPDON_BANKHAM").value!=""){
			        			//---Set value for MAPHU_hidden---
			        			
			            		MAPHU_hidden = new Date().getTime();
			            		document.getElementById(prefix_component + "MAPHU").value = MAPHU_hidden;
			            		MAPHU_TD_hidden = new Date().getTime() + 1;
			            		
			            		document.getElementById(prefix_component + "MAPHU1").value = MAPHU_TD_hidden;
			            		
			        			//---Insert TIEP_DON & BENH_NHAN---
			        			document.getElementById(prefix_component + "BENHNHAN_GIOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_GIOI_TEMP");
			        			
			        			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_DONVITUOI_TEMP");
			        			
			        			//alert(BENH_NHAN);
			        			try {
			        			/*
			        			  update don_vi_tinh
			        			*/
			        				var bnNames = new Array();
			        				bnNames[0] = "BENH_NHAN_MAPHU";
			        				bnNames[1] = "BENH_NHAN_MAPHU1";
			        				var bnValues = new Array();
			        				bnValues[0] = MAPHU_hidden;
			        				bnValues[1] = MAPHU_TD_hidden;
			        				
			        			    insertObject(BenhNhan, bnNames, bnValues);
			        				//insertTableDB ( BENH_NHAN);
				        			//alert(TIEP_DON);
				        			var tdNames = new Array();
			        				tdNames[0] = "TIEP_DON_MAPHU";
			        				tdNames[1] = "TIEP_DON_MAPHU1";
			        				
				        			insertObject(TiepDon, tdNames, bnValues);
				        			//insertTableDB ( TIEP_DON );
				        		    //alert(1);	
				        		    //document.getElementById("__msg").innerHTML = success ;
			        			} catch (e) {
			        				alert("Loi khi insert xuong database local" + e.description);
			        				document.getElementById("__msg").innerHTML = "" ;
			        			}
			        			
			        			
			        	
			        		}
			       	 		else{
			        			//alert("ChÆ°a nháº­p Äá»§ thÃ´ng tin");
			        			document.getElementById(prefix_component + 'GIO').focus();
			        		}		            
		                    
		        		} catch (ex){}
		    		}	
		    	}
		    	 else {
		    		try { 
		    			if(document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value !="" 
		        			&& document.getElementById(prefix_component + "TIEPDON_BANKHAM").value!=""){
			        		//---Set value for MAPHU_hidden---
			        		
			            	MAPHU_TD_hidden = new Date().getTime();
			            	document.getElementById(prefix_component + "MAPHU1").value = MAPHU_TD_hidden;
			            	document.getElementById(prefix_component + "BENHNHAN_GIOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_GIOI_TEMP");
			            	document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_DONVITUOI_TEMP");
			        		//alert("Don vi tuoi" + document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value);
			        		//---Insert TIEP_DON & BENH_NHAN---
			        		try {
			        			var bnValues = new Array();
			        			bnValues[0] = MAPHU_hidden;
			        			bnValues[1] = MAPHU_TD_hidden;
			        			var tdNames = new Array();
			        			tdNames[0] = "TIEP_DON_MAPHU";
			        			tdNames[1] = "TIEP_DON_MAPHU1";
				        		insertObject(TiepDon, tdNames, bnValues);
			        			updateObject(BenhNhan, "BENH_NHAN_MAPHU", document.getElementById(prefix_component + "MAPHU").value);
			        			//insertTableDB ( TIEP_DON );
			        			//updateTableDB ( BENH_NHAN);  
			        			//document.getElementById("__msg").innerHTML = success ;
			        		} catch (e) {
			        			alert("Loi khi insert xuong database local" + e);
			        		}
			        		   	
			        	
			        	}
			       	 	else{
			        		//alert("ChÆ°a nháº­p Äá»§ thÃ´ng tin");
			        		document.getElementById(prefix_component + 'GIO').focus();
			        	}	
		    		} catch (ex){} 
		    	} 
		        
		    }		//bao.ttc: END  IF (TIEPDON_MA.value == "")
	    }		//bao.ttc: END 	if (MAPHU_TD_hidden == "")
	    else {
	    	//alert(3);
	    	try {
	    		if(document.getElementById(prefix_component + "BENHNHAN_HOTEN").value !="" &&document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value !="" 
		        				&& document.getElementById(prefix_component + "TIEPDON_BANKHAM").value!=""){
		        	
		        	//---Update BENH_NHAN & TIEP_DON---
		        	document.getElementById(prefix_component + "BENHNHAN_GIOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_GIOI_TEMP");
		        	document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value = getValueCheckOrRadioList(prefix_component + "BENHNHAN_DONVITUOI_TEMP");
		        	//alert("Don vi tuoi2" + document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value);
		        	try {
		        		//alert("update benh nhan");
		        		//alert("document.getElementById(prefix_component + 'MAPHU').value:"+document.getElementById(prefix_component + "MAPHU").value);
		        		updateObject(BenhNhan, "BENH_NHAN_MAPHU", document.getElementById(prefix_component + "MAPHU").value);
		        		updateObject(TiepDon, "TIEP_DON_MAPHU", document.getElementById(prefix_component + "MAPHU").value);
		        		//updateTableDB(BENH_NHAN);	            
		           		//updateTableDB(TIEP_DON);
		           		//document.getElementById("__msg").innerHTML = success ;
		        	} catch (e) {
		        		alert("Loi khi update benh nhan va tiep don xuong database local" + e);
		        	}
		        	
		        } else {
		        	//alert("ChÆ°a nháº­p Äá»§ thÃ´ng tin");
		        	//alert(1);
		        	document.getElementById(prefix_component + 'GIO').focus();
		        }
	                      
	        } catch (ex) {}
	    }			//bao.ttc: END ELSE   <-->   (MAPHU_TD_hidden != "")
	    
	   
		try{
			var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", MAPHU_hidden).toArray();
			for (var i = 0; i < listBn.length; i++) {
				listBn[i].SANSANG_CAPNHAT = 1;
				listBn[i].save();
			}
			//db.execute("update BENH_NHAN set SANSANG_CAPNHAT = 1 where BENH_NHAN_MAPHU=\'" + MAPHU_hidden + "\'");	
			
		}catch(e){
			alert(e.description);
		}
		
		try{
			var maBN = null;
			var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", MAPHU_hidden).toArray();
			for (var i = 0; i < listBn.length; i++) {
				//alert("BENHNHAN_CMND: " + listBn[i].BENHNHAN_CMND);
				maBN = listBn[i].BENHNHAN_MA;
			}
			if(document.getElementById(prefix_component + "TIEPDON_MA").value == ""){ //them moi
		     	//alert("them moi");
		     	
				getMaTiepDon(MAPHU_TD_hidden,maBN);
			 }else{
			 	//alert("update");
				sendDataToServer();
			 }
			
		}catch(e){
	    
	    }
		//alert(xml);
		//document.getElementById(prefix_component + "MAPHU").value = "";
		document.getElementById(prefix_component + "MAPHU1").value = "";
		myResetAll();
		displayErrorSendingResults();
		
	} else { 		// bao.ttc: END  if (ret == true)
		//alert(110000);
		return;
	}
	
    // bao.ttc: focus vao Ho Ten sau khi nhan Ghi Nhan
    document.getElementById(prefix_component + "BENHNHAN_HOTEN").focus();
    
}

//---"Há»§y khÃ¡m" button---
function huy_kham_event(){
	//alert("huy_kham_event()");
    var maTiepDon = document.getElementById(prefix_component + "TIEPDON_MA").value ;
    if (maTiepDon == null || maTiepDon == ""){
       
       document.getElementById(prefix_component + "BENHNHAN_HOTEN").focus();
       return ;
    }
	
	//myConfirm = confirm("Muá»n há»§y phiáº¿u khÃ¡m nÃ y?");
	//if(myConfirm == false)
	//	return;
	//else{	
		MAPHU_hidden = document.getElementById(prefix_component + "MAPHU1").value;    
		//alert(MAPHU_hidden);
		try{
			var xmlHttp = createXmlHttpRequest();
    		var xml;
    		xml = "<list>";
    		xml += "<TIEP_DON ";
    		xml += "TIEPDON_MA=\'" + document.getElementById(prefix_component + "TIEPDON_MA").value + "\' ";
    		xml += "TIEP_DON_MAPHU=\'" + document.getElementById(prefix_component +  "MAPHU").value + "\' ";
    		 xml += "/>";
    		xml +="</list>";
    		//alert(xml);
    		var url = myContextPath + "/actionServlet";
    		var params = "urlAction=HuyKhamAction&xmlData=" + xml;
   			xmlHttp.open("POST", url, true);
    		xmlHttp.onreadystatechange = function (){
    		   handleStateChangeHuyKham(xmlHttp);
    		 };
    		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
   			xmlHttp.send(params);
   			//alert("1");
		}catch(ex){
			//alert(11000);
		}
		
		document.getElementById(prefix_component + "MAPHU").value = "";
		document.getElementById(prefix_component + "MAPHU1").value = "";
		myResetAll();
		//displayErrorSendingResults('TIEP_DON');	   		
	//}
}
//
function handleStateChangeHuyKham(xmlHttp) {
     if(xmlHttp.readyState == 4) {
     //alert("xmlHttp.status: " + xmlHttp.status);
        if(xmlHttp.status == 200) {
            processSendingResultsHuyKham(xmlHttp);
        }
        else
            alert(commonFail);
    }
}
//
function processSendingResultsHuyKham(xmlHttp) {  
	var benhnhanhoten = "";
	var matiepdon = "";
	var errorlist = " ";
	var jsonData = "(" + xmlHttp.responseText + ")";
	var data = eval(jsonData);
	myResponse = data.result;
    myResponse_temp = myResponse + "test";
    if(myResponse_temp != "test"){        
        IdList = myResponse.split(";;;");
        okIdList = IdList[0].split("---");
        errorIdList = IdList[1].split("---");        
        //alert("okIdList: " + okIdList.length);
        //alert("errorIdList: " + errorIdList.length);
        if(okIdList.length > 1){
			//processOkIdList(okIdList);
			//var msg = "Há»§y khÃ¡m thÃ nh cÃ´ng bá»nh nhÃ¢n ";
			for(var i = 0; i < okIdList.length - 1; i++){
		        try{
		        	tempList = okIdList[i].split(",,,");
		        	maPhuList = tempList[0];
		        	maTdList = tempList[1]; 
		        	BenhNhan.filter("BENH_NHAN_MAPHU = ?", maPhuList).remove();
		        	TiepDon.filter("TIEP_DON_MAPHU = ?", maPhuList).remove();
		        	
		        	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", maPhuList).toArray();
		        	for (var i = 0; i < listBn.length; i++) {
		        		var bn = listBn[i];
		        		benhnhanhoten = bn.BENHNHAN_HOTEN;
		        		//msg = msg + 
		        		//alert("benh nhan remove");
		        		bn.remove();
		        	}
		        	//alert("A TIEP DON:"+maTdList);
		        	var listTd = TiepDon.filter("TIEP_DON_MAPHU = ?", maPhuList).toArray();
		        	
		        	for (var i = 0; i < listTd.length; i++) {
		        		var td = listTd[i];
		        		matiepdon = td.TIEPDON_MA;
		        		//alert("tiep don remove");
		        		td.remove();
		        	}
		        	
		        }
		        catch(e){
		        	alert(e.description);
		        }    			
		    }
		    //document.getElementById("__msg").innerHTML = getMsgHuyKhamThanhCong(benhnhanhoten, matiepdon);
		    document.getElementById("__msg").innerHTML = getMsgHuyKhamThanhCong(benhnhanhoten, maTdList);
	    }
	    if(errorIdList.length > 1){ 
	        for(var i=0;i<errorIdList.length-1;i++){
	            try{
	            	var listTd = TiepDon.filter("TIEP_DON_MAPHU = ?", errorIdList[i]).remove();
	            	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", errorIdList[i]).remove();
	            }
	            catch(e){}
	            //displayErrorSendingResults('BENH_NHAN');    			
	        }
	       document.getElementById("__msg").innerHTML = huyfail + errorlist;
	    }	            
    }  
}
//---Reset form---
function myResetAll(){
    myReset(70);
    setValueOnLoad();
    document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
    
    document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU_TEMP").checked = false;
    document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU").value = 0;
    
    document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU_TEMP").checked = false;
    document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU").value = 0;
    
    document.getElementById(prefix_component + "GIO").focus(); 
}
// reset cac phan tu tren form
function myResetNPart() {
	//var myArr = [element1, element2, element3,...]; set focus for element1
	var myArr = [prefix_component + "BENHNHAN_MA", prefix_component + "BENHNHAN_HOTEN", prefix_component +  "BENHNHAN_GIOI", prefix_component +  "BENHNHAN_TUOI",prefix_component + "BENHNHAN_NGAYSINH", prefix_component +  "DANTOC_MA", prefix_component +  "TINH_MA",
				prefix_component +  "HUYEN_MA", prefix_component +  "XA_MA", prefix_component +  "BENHNHAN_DIACHI"];
	for(var i = 0; i < myArr.length; i++) {
		document.getElementById(myArr[i]).value = "";
	}
	document.getElementById(myArr[0]).focus();	
} 
//---Create data(xml type)---
function createXmlData() {
	//alert("createXmlData()");
	var xml = "<list>";
	try {
		var maphus= "";
		
		//var listBn = BenhNhan.filter("BENHNHAN_MA != '' AND SANSANG_CAPNHAT = 1").toArray();
		var listBn = BenhNhan.filter("BENHNHAN_MA != ''").toArray();
		
		//alert(listBn.length);
		for (var i = 0; i < listBn.length; i++) {
			var bn = listBn[i];
			xml += "<BENH_NHAN";
			var bnFields = BenhNhan.getFieldNames();
			for (var j = 0; j < bnFields.length; j++) {
				var fName = bnFields[j];
				var val = bn[fName];
				if (val == null || val == "null" || val == "NULL") {
					val = "";
				}
				xml += " " + fName + "='" + val + "' ";
			}
			xml += ">";
			
			xml += "<TIEP_DON";
			var listTd = TiepDon.filter("TIEPDON_MA != '' and TIEP_DON_MAPHU = ?", bn.BENH_NHAN_MAPHU).toArray();
			for (var j = 0; j < listTd.length; j++) {
				var td = listTd[j];
				var tdFields = TiepDon.getFieldNames();
				for (var y = 0; y < tdFields.length; y++) {
					var fName = tdFields[y];
					var val = td[fName];
					if (val == null || val == "null" || val == "NULL") {
						val = "";
					}
					xml += " " + fName + "='" + val + "' ";
				}
			}
			xml += " />";
			
			xml += "</BENH_NHAN>";
		}
		
		
	} catch (e) {
		
	}
	xml += "</list>";
    xml += ";;;" + maphus + ";;;";
    //alert("xml: " + xml);
    return xml;
}

//---Send data to server---
function sendDataToServer() {
    var xmlHttp = createXmlHttpRequest();
    var xml = createXmlData();
    //alert(xml);
    xml_temp = xml + "test";
    if(xml_temp != "test"){        
        xmlList = xml.split(";;;");
        xmlString = xmlList[0];
        maphuList = xmlList[1].split("---");
    }
    
    var url = myContextPath + "/actionServlet";
    //alert("xmlString: " + xmlString);
    //alert(xmlString);
    var params = "urlAction=SendTiepDonAction&xmlData=" + xmlString;
    xmlHttp.open("POST", url, true);
    //alert("1000000002");
    xmlHttp.onreadystatechange = function() {
    	onStateChange(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlHttp.send(params); 

   	set_status(maphuList , 2);
}

//---Check status---
function onStateChange(xmlHttp) {
	//alert("xmlHttp.readyState: " + xmlHttp.readyState);
     if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            processSendingResults(xmlHttp);
        }
        else {
        	set_status(maphuList , 1);
            alert(commonFail);
       }
    }
}
function handleStateChangeForMaTiepDon(xmlHttp){
     if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
        	var jsonExpression = "(" + xmlHttp.responseText + ")";
        	//alert("jsonExpression: " + jsonExpression);
        	var data = eval(jsonExpression);
        	
        	 var myResponse = data.result;
        	 //alert("get tiep don ma: " + myResponse);
   			 myResponse_temp = myResponse + "test";
   			 if(myResponse_temp != "test"){  
   			 
   			     var arrayList = myResponse.split("___"); //3 _
   			     var maphu1= arrayList[0];
   			     var matiepdon = arrayList[1];   	
   			     var mabenhnhan = arrayList[2];   
   			     
   			     //alert(matiepdon);	
   			     		          
                 processSendingResultsForMaTiepDon(maphu1,matiepdon,mabenhnhan);
            
             }
        }
        else {
        	set_status(maphuList , 1);
            alert(commonFail);
       }
    }

}
/**
* Get Ma Tiep Don
**/
function getMaTiepDon(maphu1, mabenhnhan){
	//alert("getMaTiepDon");
	//alert(document.getElementById(prefix_component + "TIEPDON_TYLEBH").value);
    var xmlHttp = createXmlHttpRequest();
    var xml;
    //alert("mabenhnhan: " + mabenhnhan);
    if (mabenhnhan != null && mabenhnhan != '' && mabenhnhan != 'null' && mabenhnhan != 'NULL'){
   		xml = maphu1 + "___" + mabenhnhan;
    } else {
    	xml = maphu1;
    }
    //alert("xml: " + xml);
    var url = myContextPath + "/actionServlet";
    var params = "urlAction=GetMaTiepDonAction&xmlData=" + xml;
    xmlHttp.open("POST", url, true);
    //alert("1000000002");
    //alert("xml: " + xml);
    xmlHttp.onreadystatechange = function(){
    	handleStateChangeForMaTiepDon(xmlHttp);
    };
    //alert("1000000003");
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
   // alert("1000000004");
    xmlHttp.send(params); 
   // alert("1000000005");
   // set_status(maphuList , 2);
}
function processSendingResultsForMaTiepDon(maphu1,matiepdon,mabenhnhan){
	try{
		var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", maphu1).toArray();
		for (var i = 0; i < listTd.length; i++) {
			var td = listTd[i];
			td.TIEPDON_MA = matiepdon;
			td.save();
		}
		
		var listBn = BenhNhan.filter("BENH_NHAN_MAPHU1 = ?", maphu1).toArray();
		for (var i = 0; i < listBn.length; i++) {
			var bn = listBn[i];
			bn.BENHNHAN_MA = mabenhnhan;
			bn.save();
		}
		
	    //db.execute("update TIEP_DON set TIEPDON_MA = \'"+matiepdon+"\' where TIEP_DON_MAPHU1=\'" + maphu1 + "\'");                                           
	    //db.execute("update BENH_NHAN set BENHNHAN_MA = \'"+mabenhnhan+"\' where BENH_NHAN_MAPHU1=\'" + maphu1 + "\'");                                           
	
	}
	catch(e){}
	
    sendDataToServer();
    var benhnhanhoten ="" ;
    try{
    	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU1 = ?", maphu1);
    	for (var i = 0; i < listBn.length; i++) {
    		benhnhanhoten = listBn[i].BENHNHAN_HOTEN;
    	}
    }
    catch(e){}
    
	if (benhnhanhoten != null && benhnhanhoten != "") {
    	document.getElementById("__msg").innerHTML = getMsgDkkbThanhCong(benhnhanhoten, matiepdon);
    }
    
}
//---Analyze results(receive from server)---
function processSendingResults(xmlHttp) {
	var jsonExpression = "(" + xmlHttp.responseText + ")";
	//alert(jsonExpression);
	var data = eval(jsonExpression);
	//alert(data.result);
	
     myResponse = data.result;
    myResponse_temp = myResponse + "test";
    if(myResponse_temp != "test"){        
        IdList = myResponse.split(";;;");
        okIdList = IdList[0].split("---");
        errorIdList = IdList[1].split("---");        
        
        //if(okIdList.length > 1){
		//	timer.setTimeout(function() {processOkIdList(okIdList)},10000);
	    //}
	    if(okIdList.length > 1){
	    	var maphu = document.getElementById(prefix_component + "MAPHU").value;
	    	document.getElementById(prefix_component + "__maphu_in").value = maphu;
	    	var td = TiepDon.getByFieldValue("TIEP_DON_MAPHU", maphu);
	    	var bn = BenhNhan.getByFieldValue("BENH_NHAN_MAPHU", maphu);
	    	
	    	document.getElementById("__msg").innerHTML = getMsgDkkbThanhCong(bn.BENHNHAN_HOTEN, td.TIEPDON_MA);
	    	inPhieu();
			timer.setTimeout(function() {
				processOkIdList(okIdList);
				document.getElementById(prefix_component + "MAPHU").value = "";
			},50);
	    }
	    if(errorIdList.length > 1){ 
	        for(var i = 0; i < errorIdList.length - 1; i++){
	            try{
	            	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", errorIdList[i]);
	            	for (var i = 0; i < listBn.length; i++) {
	            		var bn = listBn[i];
	            		bn.SANSANG_CAPNHAT = 0;
	            		bn.save();
	            	}
	            }
	            catch(e){}
	            displayErrorSendingResults();    			
	        }
	    }	            
    }
     
}

//---Process ok results(receive from server)---
function processOkIdList(okIdList) {
	
	for(var i = 0; i < okIdList.length - 1; i++) {
        try {	  
        	tempList = okIdList[i].split(",,,");
        	maPhuList = tempList[0];
        	maTdList = tempList[1];
        	var listTd = TiepDon.filter("TIEP_DON_MAPHU = ?", maPhuList).toArray();
        	for (var i = 0; i < listTd.length; i++) {
        		var td = listTd[i];
        		matiepdon = td.TIEPDON_MA;
        		//alert("tiep don remove");
        		td.remove();
        	}
        	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", maPhuList).toArray();
        	for (var i = 0; i < listBn.length; i++) {
        		var bn = listBn[i];
        		benhnhanhoten = bn.BENHNHAN_HOTEN;
        		bn.remove();
        	}	                	                                           
        }
        catch(e){
        	alert("processOkIdList " + e.description);
        }    			
    }
    /*
    if (benhnhanhoten != null && benhnhanhoten != "") {
    	document.getElementById("__msg").innerHTML = getMsgDkkbThanhCong(benhnhanhoten, matiepdon);
    }
    */
}

//---Process error results(receive from server)---
function displayErrorSendingResults(){
	
    var myHtml = "";
    myHtml += "<div style='width:100%; text-align:center; background:#ffffcc; color:#ff0000; font-weight:bold'>Bá»nh nhÃ¢n bá» lá»i</div>";
    myHtml += "<div style='border:solid 1px #ffffcc; width:99%; overflow:auto; height:35px; background:#ECE9D8'>";
    myHtml += "<table style='background:#ECE9D8; color:#ff0000' border='0' width='91%' cellspacing='0' cellpadding='0'>";
    
	var listObj = BenhNhan.filter("SANSANG_CAPNHAT = 0").toArray();
	for (var i = 0; i < listObj.length; i++) {
		var obj = listObj[i];
		myHtml += "<tr><td style='padding-left:10px'><a href='#' onclick='LoadDB_formMaPhuBN(" + obj.BENH_NHAN_MAPHU + ");LoadDB_formMaPhuTD(" + obj.BENH_NHAN_MAPHU1 + ")'>" + obj.BENHNHAN_HOTEN + "</a></td></tr>";
	}
	
    myHtml += "</table>";
    myHtml += "</div>";
    
    if(i == 0) {
    	document.getElementById("sync_error_display").innerHTML = "";
    } else {	
	    document.getElementById("sync_error_display").innerHTML = myHtml;
	}	
}



//---Shortcut in form---
initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById('__ghinhan').click();});
	
}

function handlerForSendId_BN(xmlHttp,myCondition) {
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
	  	var data = eval(jsonExpression);
      	var i = 0;
      
      	if(typeof(data.list) == "object") {
    		//---Clean form before load form---
    		MAPHU_hidden = document.getElementById(prefix_component + "MAPHU").value;    
			try {
				var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", MAPHU_hidden).remove();
			} catch(e) {
				alert(e.description);
			}
			
			document.getElementById(prefix_component + "MAPHU").value = "";
			myResetNPart();

    	
			data1 = data.list.BENH_NHAN;
            BENH_NHAN_MAPHU = new Date().getTime();
            var bn = new BenhNhan(data1);
            document.getElementById(prefix_component + "MAPHU").value = BENH_NHAN_MAPHU;
            //---Insert BENH_NHAN---
           	//	insertTableDB(BENH_NHAN);
           	bn.BENH_NHAN_MAPHU = BENH_NHAN_MAPHU;
           	bn.BENH_NHAN_MAPHU1 = "";
			BenhNhan.transaction(function() {
				bn.save();
			});
			
		    LoadDB_formMaPhuBN(BENH_NHAN_MAPHU);
		    document.getElementById("__msg").innerHTML = "";				
        } else {
        	MAPHU_hidden = document.getElementById(prefix_component + "MAPHU").value;    
			try{
				var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", MAPHU_hidden).remove();
			} catch(e) {
			}
			
			document.getElementById(prefix_component + "MAPHU").value = "";
			//var mabenhnhan = commonLabelMaTiepDon;
			var mabenhnhan = "mã bệnh nhân";
			document.getElementById("__msg").innerHTML = setMessage(mabenhnhan, myCondition);
			myResetNPart();
        }	
      
   }
}

//---Send id get data from server(get BENH_NHAN)---
function sendId_BN(idText,urlAction){
    var myCondition;
    var url;
    var xml;
    var data;
    
    if(idText != ""){
    	myCondition = idText;
    	
    	var xmlHttp = createXmlHttpRequest();    
		var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	    
	    xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function(){
	    	handlerForSendId_BN(xmlHttp,myCondition);
	    };
	    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlHttp.send(params);
    }
}


function checkHsthtoankham(idText,urlAction){
    var myCondition;
    var url;
    var xml;
    var data;
    
    if(idText != ""){
    	myCondition = idText;
    	
    	var xmlHttp = createXmlHttpRequest();    
		var url = myContextPath + "/actionServlet?";
	    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	    
	    xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function(){
	    	handlerForcheckHsthtoankham(xmlHttp,myCondition);
	    };
	    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlHttp.send(params);
    }
}
function handlerForcheckHsthtoankham(xmlHttp,myCondition) {
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
	  	var data = eval(jsonExpression);      	      
      	if(typeof(data.list) == "object") {      		      		
      		document.getElementById("div_ghinhan").style.display = "none";
      		document.getElementById("div_huykham").style.display = "none";
      		document.getElementById("__msg").innerHTML = setMessage2();
        } else {
        	document.getElementById("__msg").innerHTML = "";
        	document.getElementById("div_ghinhan").style.display = "block";
      		document.getElementById("div_huykham").style.display = "block";
        }	
      
   }
}
//---Load info into form---
function LoadDB_formMaPhuBN(myMaPhu){
	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", myMaPhu).toArray();
	for(var i = 0; i < listBn.length; i++) {
		var bn = listBn[i];
		document.getElementById(prefix_component + "BENHNHAN_MA").value = bn.BENHNHAN_MA;
		document.getElementById(prefix_component + "BENHNHAN_HOTEN").value = bn.BENHNHAN_HOTEN;
		document.getElementById(prefix_component + "BENHNHAN_GIOI").value = bn.BENHNHAN_GIOI;
		document.getElementById(prefix_component + "BENHNHAN_CMND").value = bn.BENHNHAN_CMND;
		if ( document.getElementById(prefix_component + "BENHNHAN_GIOI").value == "1" ) {
			document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP:0").checked = true ;
		} else {
			document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP:1").checked = true;
		}
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value = bn.BENHNHAN_DONVITUOI;
		if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value == "1" ) {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI").value == "2") {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked = true ;
		} else {
			 document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked = true;
		}
		document.getElementById(prefix_component + "BENHNHAN_NGAYSINH").value = bn.BENHNHAN_NGAYSINH;
		document.getElementById(prefix_component + "BENHNHAN_TUOI").value = bn.BENHNHAN_TUOI;
		document.getElementById(prefix_component + "BENHNHAN_NAMSINH").value = bn.BENHNHAN_NAMSINH;
		document.getElementById(prefix_component + "DANTOC_MA").value = bn.DANTOC_MA;
		myOnblurTextbox(prefix_component + "DANTOC_MA", 'DM_DAN_TOC');
		document.getElementById(prefix_component + "TINH_MA").value = bn.TINH_MA;
		myOnblurTextbox(prefix_component + "TINH_MA", 'DM_TINH');
		document.getElementById(prefix_component + "HUYEN_MA").value = bn.HUYEN_MA;
		myOnblurTextbox(prefix_component + "HUYEN_MA",'DM_HUYEN');
		document.getElementById(prefix_component + "XA_MA").value = bn.XA_MA;
		myOnblurTextbox(prefix_component + "XA_MA",'DM_XA');
		document.getElementById(prefix_component + "BENHNHAN_DIACHI").value = bn.BENHNHAN_DIACHI;
		document.getElementById(prefix_component + "MAPHU").value = myMaPhu;	
	}
}

function handlerForSendId_TD(xmlHttp,myCondition){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	 	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		var i = 0;
		if(typeof(data.list) == "object"){
    		//---Clean form before load form---
    		MAPHU_TD_hidden = document.getElementById(prefix_component + "MAPHU1").value;    
			try{
				var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", MAPHU_TD_hidden).remove();
			}catch(e){}
			
			document.getElementById(prefix_component + "MAPHU1").value = "";
			myResetAll();
    	
			data1 = data.list.TIEP_DON;
            TIEPDON_MAPHU = new Date().getTime();
            document.getElementById(prefix_component + "MAPHU1").value = TIEPDON_MAPHU;
            var td = new TiepDon(data1);
            td.TIEP_DON_MAPHU = document.getElementById(prefix_component + "MAPHU").value;
            td.TIEP_DON_MAPHU1 = TIEPDON_MAPHU;

			TiepDon.transaction(function() {
				td.save();
			});
            
		    LoadDB_formMaPhuTD(TIEPDON_MAPHU);	
		    sendId_BN(document.getElementById(prefix_component + "BENHNHAN_MA").value,"GetBenhNhanAction");	
		    getThamKham(document.getElementById(prefix_component + "TIEPDON_MA").value,"GetThamKhamAction");
		    maphu = document.getElementById(prefix_component + "MAPHU").value;
		    maphu1 = document.getElementById(prefix_component + "MAPHU1").value;
		   	
		    try{
		    	var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", maphu1).toArray();
		    	for(var i = 0; i < listTd.length; i++) {
		    		var td = listTd[i];
		    		td.TIEP_DON_MAPHU = maphu;
		    		TiepDon.transaction(function() {
		    			td.save();
		    		});
		    	}
				
			} catch(e) {
				alert(e.description);
			}	
			document.getElementById("__msg").innerHTML = "";
			
			//alert(nhapthebhyt);
			if (nhapthebhyt == true){
				//alert(nhapthebhyt);
				nhapthebhyt = false;
				//alert(document.getElementById(prefix_component + "TIEPDON_MA").value);
				document.getElementById(prefix_component + "TIEPDON_MA").value = "";
				
				document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value = "";
				//alert(nhapthebhyt);
				myOnblurTextbox(document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").id,'DT_DM_CLS_BANG_GIA');
				//alert(nhapthebhyt);
				document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").focus();
				//alert(nhapthebhyt);
			}
			// phuc.lc 05/01/2011 : kiem tra ho so thanh toan kham (theo ma tiep don) da thanh toan hay chua
			checkHsthtoankham(document.getElementById(prefix_component + "TIEPDON_MA").value,"CheckHsThtoanKAction");
        } else {
        	MAPHU_TD_hidden = document.getElementById(prefix_component + "MAPHU1").value;    
			try {
				var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", MAPHU_TD_hidden).remove();
			} catch(e) {
				alert(e.description);
			}
			
			document.getElementById(prefix_component + "MAPHU1").value = "";
			var mabenhnhan = "";
			if (checkSothe) {
				mabenhnhan = "số thẻ bảo hiểm";
			} else {
				mabenhnhan = "mã tiếp đón";
			}
			document.getElementById("__msg").innerHTML = setMessage(mabenhnhan, myCondition);
			
			myResetAll();
			document.getElementById(prefix_component + "BENHNHAN_HOTEN").focus();
        }
	
	}
}


/// lay gia tri bang tiep don
function sendId_TD(idText,urlAction){
	
	if(urlAction == "GetBenhNhanFromTheBHAction"){
		if (idText != null && idText !=''){
			if (idText.length < 15){
				alert("Chiều dài số thẻ BHYT phải bằng 15");
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = "";
				document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
				return;
			}
		}
	}
	
    var myCondition;
    var url;
    var xml;
    var data;
    
    if(idText != ""){
    	myCondition = idText;
    	
    	var xmlHttp = createXmlHttpRequest();
    	var url = myContextPath + "/actionServlet?";
    
   		var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);

    	xmlHttp.open("POST", url, false);

	    xmlHttp.onreadystatechange = function(){
	    	handlerForSendId_TD(xmlHttp,myCondition);
	    };
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xmlHttp.send(params);
    	
        // 20110705 bao.ttc: neu nhap vao so the BH moi thi giu nguyen so the de user tiep tuc nhap thong tin
    	if(urlAction == "GetBenhNhanFromTheBHAction"){
    		document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = idText.toUpperCase();
    		document.getElementById(prefix_component + "DOITUONG_MA").value = "BH";
    		myOnblurTextbox_DoiTuong(prefix_component + 'DOITUONG_MA','DM_DOI_TUONG',prefix_component + '__tylebntra',prefix_component + 'TIEPDON_TYLEBH');
    	}
    	
    }
}
//start: kiểm tra nếu số thẻ bảo hiểm đã tồn tại thì thông báo
function sendId_BHYT(idText,urlAction){
    var myCondition;
    var url;
    var xml;
    var data;
    
    if(idText != ""){
    	myCondition = idText;
    	
    	var xmlHttp = createXmlHttpRequest();
    	var url = myContextPath + "/actionServlet?";
    
   		var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);

    	xmlHttp.open("POST", url, false);

	    xmlHttp.onreadystatechange = function(){
	    	checkExistBHYT(xmlHttp,myCondition);
	    };
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xmlHttp.send(params);
    }
}
function checkExistBHYT(xmlHttp,myCondition) {
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	 	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		var i = 0;
		if(typeof(data.list) == "object"){
			alert('Số thẻ BHYT:' + myCondition + ' đã tồn tại. Hãy nhập lại số thẻ khác');
			document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = "";
			document.getElementById(prefix_component + "TIEPDON_SOTHEBH").focus();
		}
	}
	    
}
//end: kiểm tra nếu số thẻ bảo hiểm đã tồn tại thì thông báo

//---Load info into form---
function LoadDB_formMaPhuTD(myMaPhu){
	var listTd = TiepDon.filter("TIEP_DON_MAPHU1 = ?", myMaPhu).toArray();
	for(var i = 0; i < listTd.length; i++) {
		var td = listTd[i];		
		document.getElementById(prefix_component + "TIEPDON_NGAYGIO").value = td.TIEPDON_NGAYGIO;
		ngaygio = document.getElementById(prefix_component + "TIEPDON_NGAYGIO").value;		
		if ( ngaygio != null ) {
			document.getElementById("NGAYTG").value = ngaygio.substr(0,10);
			document.getElementById(prefix_component + "GIO").value = ngaygio.substr(11,ngaygio.length);
		}
		document.getElementById(prefix_component + "TIEPDON_MA").value = td.TIEPDON_MA;
		document.getElementById(prefix_component + "BENHNHAN_MA").value = td.BENHNHAN_MA;
		document.getElementById(prefix_component + "DOITUONG_MA").value = td.DOITUONG_MA;
		
		//alert(1);
		document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = td.TIEPDON_TYLEBH;
		if (document.getElementById(prefix_component + "TIEPDON_TYLEBH").value == "") {
			document.getElementById(prefix_component + "__tylebntra").value = 100;
		} else {
			document.getElementById(prefix_component + "__tylebntra").value = 100 - parseInt(td.TIEPDON_TYLEBH);
		}
		
		//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").value = td.TIEPDON_NAMBHYT;
		document.getElementById(prefix_component + "TINHBHYT_MA").value = td.TINHBHYT_MA;
		//alert(2);
		document.getElementById(prefix_component + "KHOIBHYT_MA").value = td.KHOIBHYT_MA;
		
		//alert(3);
		document.getElementById(prefix_component + "TIEPDON_MACOQUAN").value = td.TIEPDON_MACOQUAN;
		document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = td.TIEPDON_SOTHEBH;
		document.getElementById(prefix_component + "TIEPDON_SOTHETE").value = td.TIEPDON_SOTHETE;
		document.getElementById(prefix_component + "TIEPDON_KHAISINH").value = td.TIEPDON_KHAISINH;
		document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value = td.TIEPDON_CHUNGSINH;
		document.getElementById(prefix_component + "TIEPDON_THENGHEO").value = td.TIEPDON_THENGHEO;
		document.getElementById(prefix_component + "TIEPDON_GIATRI1").value = td.TIEPDON_GIATRI1;
		document.getElementById(prefix_component + "TIEPDON_GIATRI2").value = td.TIEPDON_GIATRI2;
		document.getElementById(prefix_component + "KCBBHYT_MA").value = td.KCBBHYT_MA;
		
		
		//alert(4);
		document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").value = td.TIEPDON_LOAIKHAM;
		document.getElementById(prefix_component + "TIEPDON_BANKHAM").value = td.TIEPDON_BANKHAM;
		
		document.getElementById(prefix_component + "TIEPDON_KYHIEU").value = td.TIEPDON_KYHIEU;
		document.getElementById(prefix_component + "TIEPDON_QUYEN").value = td.TIEPDON_QUYEN;
		document.getElementById(prefix_component + "TIEPDON_BIENLAI").value = td.TIEPDON_BIENLAI;
		document.getElementById(prefix_component + "TIEPDON_THUTU").value = td.TIEPDON_THUTU;
		document.getElementById(prefix_component + "TIEPDON_BNTRA").value = td.TIEPDON_BNTRA;
		document.getElementById(prefix_component + "TIEPDON_GIATRI3").value = td.TIEPDON_GIATRI3;
		document.getElementById(prefix_component + "TIEPDON_GIATRI4").value = td.TIEPDON_GIATRI4;
		document.getElementById(prefix_component + "TIEPDON_MOC1").value = td.TIEPDON_MOC1;
		document.getElementById(prefix_component + "TIEPDON_MOC2").value = td.TIEPDON_MOC2;
		document.getElementById(prefix_component + "TIEPDON_MOC3").value = td.TIEPDON_MOC3;
		
		var cogiaychuyenvien = td.TIEPDON_CO_GIAY_GIOI_THIEU;
		
		if (cogiaychuyenvien == 1 || cogiaychuyenvien == true){
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU_TEMP").checked = true;
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU").value = 1;
		}else{
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU_TEMP").checked = false;
			document.getElementById(prefix_component + "TIEPDON_CO_GIAY_GIOI_THIEU").value = 0;
		}
		
		var cokhamdalieu = td.TIEPDON_KHAM_DA_LIEU;
		
		if (cokhamdalieu == 1 || cokhamdalieu == true){
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU_TEMP").checked = true;
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU").value = 1;
		}else{
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU_TEMP").checked = false;
			document.getElementById(prefix_component + "TIEPDON_KHAM_DA_LIEU").value = 0;
		}
		
		document.getElementById(prefix_component + "TIEPDON_MOC3").value = td.TIEPDON_MOC3;
		
		document.getElementById(prefix_component + "MAPHU1").value = myMaPhu;
	}

    myOnblurTextbox(document.getElementById(prefix_component + "DOITUONG_MA").id,'DM_DOI_TUONG');
    myOnblurTextboxTinhBHYT(document.getElementById(prefix_component + "TINHBHYT_MA").id,'DM_TINH__2');
    myOnblurTextbox(document.getElementById(prefix_component + "KHOIBHYT_MA").id,'DT_DM_KHOI_BHYT');
    myOnblurTextbox(document.getElementById(prefix_component + "KCBBHYT_MA").id,'DM_BENH_VIEN');
    myOnblurTextbox(document.getElementById(prefix_component + "TIEPDON_LOAIKHAM").id,'DT_DM_CLS_BANG_GIA');
    myOnblurTextbox(document.getElementById(prefix_component + "TIEPDON_BANKHAM").id,'DT_DM_BAN_KHAM');
    checkDoiTuong();
}

function checkForGioi(){
	
	var thi_1 = "thị";
	var thi_2 = "Thị";
	var thi_3 = "THỊ";
	
	var van_1 = "văn";
	var van_2 = "Văn";
	var van_3 = "VĂN";
	
	var valueTen = document.getElementById(prefix_component + "BENHNHAN_HOTEN").value;
	
	if ( valueTen != null && valueTen != "" && (valueTen.indexOf(thi_1) >= 0 || valueTen.indexOf(thi_2) >= 0 || valueTen.indexOf(thi_3) >= 0) ) {
		document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP:1").checked = true;
	}
	
	if ( valueTen != null && valueTen != "" && (valueTen.indexOf(van_1) >= 0 || valueTen.indexOf(van_2) >= 0 || valueTen.indexOf(van_3) >= 0) ) {
		document.getElementById(prefix_component + "BENHNHAN_GIOI_TEMP:0").checked = true;
	}
	
}
/*
function set_tyleBH() {
	var tylebh;
	var tylebntra = document.getElementById(prefix_component + "__tylebntra").value;
	if (tylebntra != null && tylebntra != "") {
		tylebh = 100 - parseInt(tylebntra);
		document.getElementById(prefix_component + "TIEPDON_TYLEBH").value = tylebh;
		//alert(tylebh);
	}
}
*/
function onSubmit() {
	try{			
		var form = document.forms[0];
		
		if (bSave){
    		bSave = false;	    	    	
    		valid = iesvn_ValidateRequired(form);  
    		if (valid == true){
    			valid = iesvn_ValidateIntRange(form); 
    			valid = iesvn_ValidateComparedDates(form);
    			if (validateTE) {
	    			var soTe = document.getElementById(prefix_component + "TIEPDON_SOTHETE").value;
	 	 			var khaiSinh = document.getElementById(prefix_component + "TIEPDON_KHAISINH").value;
	 	 			var chungSinh = document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value;
	 	 			if (soTe == "" && khaiSinh == "" && chungSinh == "") {
	 	 				alert(validateTEMsg);
	 	 				valid = false;
	 	 				document.getElementById(prefix_component + "TIEPDON_SOTHETE").focus();
	 	 			}
 	 			}
    		} 	
    	} 	    	    
    	if (bCancel){
    		bCancel = false;
    	}
    	// kiem tra so the BH
//    	if (valid && document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value != "") {
//    		var msgInvalidSoThe = "Số thẻ bảo hiểm không hợp lệ.";    		
//    		var strSothe = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
//    		if (strSothe.length < 3) {
//    			alert(msgInvalidSothe);
//    			valid = false;
//    		} else {
//    			// lay ma doi tuong tu so the bao hiem
//    			var strMaDTFromThe = strSothe.substring(0,2).toUpperCase();
//    			// lay ma quyen loi tu so the bao hiem
//    			var strMaQL = strSothe.substring(2,3).toUpperCase();
//    			if ((strMaQL == "1" && strMaDTFromThe != "CC" && strMaDTFromThe != "TE") ||
//    					(strMaQL == "2" && strMaDTFromThe != "CK") ||
//    					(strMaQL == "3" && strMaDTFromThe != "CA") ||
//    					(strMaQL == "4" && strMaDTFromThe != "BT" && strMaDTFromThe != "HN") ||
//    					(strMaQL == "5" && strMaDTFromThe != "HT") ||
//    					(strMaQL == "6" && strMaDTFromThe != "CN") ||
//    					(strMaQL == "7" && (strMaDTFromThe == "CC" || strMaDTFromThe == "TE" || strMaDTFromThe == "CK" || strMaDTFromThe == "CA" || 
//    							strMaDTFromThe == "BT" || strMaDTFromThe == "HN" || strMaDTFromThe == "HT" || strMaDTFromThe == "CN"))
//    				){
//    				
//    				alert(msgInvalidSoThe + "\n" + "( Mã quyền lợi " + strMaQL + " không sử dụng cho mã đối tượng " +  strMaDTFromThe +" )");
//    				valid = false;
//    				
//    			} else {
//    				// lay ma doi tuong duoc cho o truong Doi tuong BHYT
//        			var strMaDT = document.getElementById(prefix_component + "KHOIBHYT_MA").value;
//        			if (strMaDTFromThe != strMaDT.toUpperCase()) {
//        				alert("Mã đối tượng trong số thẻ BHYT không phù hợp với đối tượng BHYT được chọn");
//        				valid = false;
//        			}
//    			}
//    			
//     		}
//    		
//    	}
   		return valid;
 	} catch(e) {
   		alert("error at onSubmit()" + e.description);
 	}	
}

function set_status(maphuList , status) {
	for(var i = 0; i < maphuList.length - 1; i++){
        try{	      
        	var listBn = BenhNhan.filter("BENH_NHAN_MAPHU = ?", maphuList[i]).toArray();
			for(var i = 0; i < listBn.length; i++) {
				var bn = listBn[i];
				//alert("status"+status);
				bn.SANSANG_CAPNHAT = status;
				BenhNhan.transaction(function() {
					bn.save();
				});
			}
        	//db.execute("delete from TIEP_DON where TIEP_DON_MAPHU=" + maphuList[i]);
        	//alert("maphuList[i]: " + maphuList[i]); 
        	//db.execute("update BENH_NHAN set SANSANG_CAPNHAT = " + status + " where BENH_NHAN_MAPHU=\'" + maphuList[i] + "\'");	                	                                           
        }
        catch(e){
        	alert("Loi trong qua trinh update trang thai: " + e.description);
        }    			
    }
}

function disableBH() {
	//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").disabled = true;
	//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").value="";
	
	document.getElementById(prefix_component + "TINHBHYT_MA").disabled = true;
	if (document.getElementById( "DM_TINH__2").disabled == false){
        	changeDisabledAttr("DM_TINH__2");  
    }
    document.getElementById(prefix_component + "TINHBHYT_MA").value="";
    myOnblurTextboxTinhBHYT(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
    
    document.getElementById(prefix_component + "TIEPDON_MACOQUAN").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MACOQUAN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_SOTHEBH").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value="";
}

function disableTE() {
	document.getElementById(prefix_component + "TIEPDON_SOTHETE").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_SOTHETE").value="";
    
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").value="";
    
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value="";
    
   
    
    
    
}

function disableTP() {
	document.getElementById(prefix_component + "TIEPDON_KYHIEU").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_KYHIEU").value="";
    
    document.getElementById(prefix_component + "TIEPDON_QUYEN").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_QUYEN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").value="";
}

function disableMP() {
	document.getElementById(prefix_component + "TIEPDON_THENGHEO").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_THENGHEO").value="";
}

function enableMP() {
	document.getElementById(prefix_component + "TIEPDON_THENGHEO").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_THENGHEO").value="";
	// bao.ttc
    //if (document.getElementById(prefix_component + "KCBBHYT_MA").value == ''){
    //	var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
	//	if (kcbbhyt) {
	//		document.getElementById(prefix_component + "KCBBHYT_MA").value = kcbbhyt.Ma;
	//		dojo.byId("DM_BENH_VIEN").value = kcbbhyt.Ten;
	//	}
	//}
}

function enableTP() {
	document.getElementById(prefix_component + "TIEPDON_KYHIEU").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_KYHIEU").value="";
    
    document.getElementById(prefix_component + "TIEPDON_QUYEN").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_QUYEN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_BIENLAI").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_BIENLAI").value="";
}

function enableTE() {
	document.getElementById(prefix_component + "TIEPDON_SOTHETE").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_SOTHETE").value="";
    
    document.getElementById(prefix_component + "TIEPDON_KHAISINH").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_KHAISINH").value="";
    
    document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_CHUNGSINH").value="";
    
    
}

function enableBH() {
	//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").disabled = false;
	//document.getElementById(prefix_component + "TIEPDON_NAMBHYT").value="";
	
	document.getElementById(prefix_component + "TINHBHYT_MA").disabled = false;
	if (document.getElementById( "DM_TINH__2").disabled == true){
        	changeDisabledAttr("DM_TINH__2");  
    }
    //document.getElementById(prefix_component + "TINHBHYT_MA").value="";
    //myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
    
    
    //document.getElementById(prefix_component + "KHOIBHYT_MA").value="";
    //myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
    
    document.getElementById(prefix_component + "TIEPDON_MACOQUAN").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_MACOQUAN").value="";
    
    document.getElementById(prefix_component + "TIEPDON_SOTHEBH").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value="";
    
    if (document.getElementById(prefix_component + "KCBBHYT_MA").value == ''){
     	var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
		if (kcbbhyt) {
			document.getElementById(prefix_component + "KCBBHYT_MA").value = kcbbhyt.Ma;
			dojo.byId("DM_BENH_VIEN").value = kcbbhyt.Ten;
		}
    
    }
    
   
  
	if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
		var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
		if (tinhbhyt) {
			document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
			dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
		}	
	}
			
    
}

function disableTGBH() {
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
    
    document.getElementById(prefix_component + "KCBBHYT_MA").disabled = true;
	if (document.getElementById( "DM_BENH_VIEN").disabled == false){
        	changeDisabledAttr("DM_BENH_VIEN");  
    }
    document.getElementById(prefix_component + "KCBBHYT_MA").value="";
    myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN');
    
    
    /*
    document.getElementById("DT_DM_TUYEN_KCB").value = "";		
    var search = dijit.byId("DT_DM_TUYEN_KCB");
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	search.store = CatalogStore;
	if (document.getElementById( "DT_DM_TUYEN_KCB").disabled == false){
        	changeDisabledAttr("DT_DM_TUYEN_KCB");  
    }
    */
    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_MOC3").value="";
}

function enableTGBH() {
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
    
    document.getElementById(prefix_component + "KCBBHYT_MA").disabled = false;
	if (document.getElementById( "DM_BENH_VIEN").disabled == true){
        	changeDisabledAttr("DM_BENH_VIEN");  
    }
    //document.getElementById(prefix_component + "KCBBHYT_MA").value="";
    //myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN');
    
    //document.getElementById("DT_DM_TUYEN_KCB").value = "";		
    //var search = dijit.byId("DT_DM_TUYEN_KCB");
	//var jsonData = { identifier: "id", items: [], label: "title" };
	//var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	//CatalogStore.newItem({id: "", title: ""});
	//search.store = CatalogStore;
	
	//if (document.getElementById( "DT_DM_TUYEN_KCB").disabled == true){
    //    	changeDisabledAttr("DT_DM_TUYEN_KCB");  
    //}
	
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_MOC1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_MOC2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_MOC3").value="";
}

function disableTGTE() {
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = true;
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
    


}
function enableTGTE() {
	document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
    
  
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
    
    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = false;
    //document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
 }

function getStt() {
	//alert(soThuTu);
	if (soThuTu == 'YES'){
		if (document.getElementById(prefix_component + "TIEPDON_BANKHAM").value != "") {
		var xmlHttp = createXmlHttpRequest();
		var url = myContextPath + "/actionServlet?";
		var params = "urlAction="+ encodeURI("GetSoDangKyKhamBenhAction") + "&xmlData=" + 
						encodeURI(document.getElementById(prefix_component + "TIEPDON_BANKHAM").value);
		xmlHttp.open("POST", url, false);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var jsonExpression = "(" + xmlHttp.responseText + ")";
				var jsonData = eval(jsonExpression);
				document.getElementById(prefix_component + "TIEPDON_THUTU").value = jsonData.result;
			}
		};
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params); 
	}
	}
	
}

function inPhieu() {
	//iesvn_WindowOpen(myContextPath + '/B1_Tiepdon/Phieudangkykhambenh.seam', 1000, 700);
}

function formatHoTenUpper(){
	var hoTen = document.getElementById(prefix_component + "BENHNHAN_HOTEN").value;
	hoTen = hoTen.trim();

	var result = "";
	if (hoTen != null && hoTen != ""){
		result = hoTen.toUpperCase();
		document.getElementById(prefix_component + "BENHNHAN_HOTEN").value = result;	
	}
}

function formatHoTen(){
	
	var hoTen = document.getElementById(prefix_component + "BENHNHAN_HOTEN").value;
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
		
		
		document.getElementById(prefix_component + "BENHNHAN_HOTEN").value = result;	
	}

}

function testBHYTTrungTrongNgay(ghinhan){ // ghi nhan == true or false
	document.getElementById("div_ghinhan").style.display = "block";
	document.getElementById("div_huykham").style.display = "block";
    var doituong = document.getElementById(prefix_component + "DOITUONG_MA").value;
    //alert(doituong);
    if (doituong == 'BH'){
    	var sothebhyt = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
		if (sothebhyt != null && sothebhyt != ''){
			var ngaytd = document.getElementById( "NGAYTG").value;
			
			testBHYTTrungTrongNgayFromServer("TestBHYTTrungTrongNgayAction",sothebhyt+","+ngaytd,ghinhan);
		} else {
			onSubmit();
		}
    }else{
    	if (ghinhan == true){
    		ghi_nhan_event();
    	}
    	
    }

}


function testBHYTTrungTrongNgayFromServer(urlAction,myCondition,ghinhan){
   
  	
		 
	var xmlHttp = createXmlHttpRequest();    
	var url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	handlerReadyStateChangeForTestBHYTTrungTrongNgayFromServer(xmlHttp,ghinhan);
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 	
	  
}


function handlerReadyStateChangeForTestBHYTTrungTrongNgayFromServer(xmlHttp,ghinhan){
	
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	 var checkBHYTTrung = false;
	  	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var dataJson = eval(jsonExpression);
		if (ghinhan == false) {
			if ( dataJson.list != null && typeof(dataJson.list) == "object") {
			    var data = dataJson.list;
			    var i  = 0;
			   
		  		if (data != null){
				  while (data.record[i] != null){            	
		                data1 = data.record[i];   
		               	var matiepdon =  data1.matiepdon ;   
		               	var ngaygio =  data1.ngaygio ;   
		               	var bankham =  data1.bankham ;  
		               	var loaicanhbao =  data1.loaicanhbao ; 
		               	var ngaytaikham =  data1.ngaytaikham ; 
		               	var matdTrenGiaoDien =   document.getElementById(prefix_component + "TIEPDON_MA").value;
		               	//if (matdTrenGiaoDien == matiepdon){
		               	// day la truong hop cap nhat -> cho phep
		               	
		               	//}else{
		               	
		               		checkBHYTTrung = true;
		               		if (loaicanhbao == '0'){
		               			//alert(s_sothebhytdasudungvoimatiepdon + matiepdon + s_ngaygio + ngaygio + s_bankham + bankham + "."); 
		               			if (ghinhan == false) {
			               			var messg = s_sothebhytdasudungvoimatiepdon + matiepdon + s_ngaygio + ngaygio + s_bankham + bankham + ".";
			               			document.getElementById(prefix_component + "__checkbhmessage").value = messg;
			               			khambenhtrongngay(messg,mabenhnhan,matiepdon);
		               			}
		               		}else{
		               			// chua den ngay tai kham
		               			//alert(s_benhnhanchuadenngaytaikham + ngaytaikham + ".");     
		               			if (ghinhan == false) {
			               			var messg = s_benhnhanchuadenngaytaikham + ngaytaikham + ".";
			               			document.getElementById(prefix_component + "__checkbhmessage").value = messg;
			               			khambenhtrongngay(messg,mabenhnhan,matiepdon);
		               			}
		               		}
		               		         	 
		               	
		               //	}
		               	 
							       
						i = 1;
						
		       
	              }
	              if (i == 0) { // truong hop nay chi co' 1 record
		         		data1 = data.record;   
		         		
		                var matiepdon =  data1.matiepdon ;   
		               	var ngaygio =  data1.ngaygio ;   
		               	var bankham =  data1.bankham ;   
		               	
		               	var loaicanhbao =  data1.loaicanhbao ; 
		               	var ngaytaikham =  data1.ngaytaikham ; 
		               	
		               	var matdTrenGiaoDien =   document.getElementById(prefix_component + "TIEPDON_MA").value;
		               	//if (matdTrenGiaoDien == matiepdon){
		               	// day la truong hop cap nhat -> cho phep
		               	
		               	//}else{
		               		checkBHYTTrung = true;
		               		var mabenhnhan =   document.getElementById(prefix_component + "BENHNHAN_MA").value;
		               		if (loaicanhbao == '0'){
		               			var messg = s_sothebhytdasudungvoimatiepdon + matiepdon + s_ngaygio + ngaygio + s_bankham + bankham + ".";
		               			document.getElementById(prefix_component + "__checkbhmessage").value = messg;
		               			khambenhtrongngay(messg,mabenhnhan,matiepdon);
		               			//alert(s_sothebhytdasudungvoimatiepdon + matiepdon + s_ngaygio + ngaygio + s_bankham + bankham + ".");
		               		}else{
		               			// chua den ngay tai kham
		               			var messg = s_benhnhanchuadenngaytaikham + ngaytaikham + ".";
		               			document.getElementById(prefix_component + "__checkbhmessage").value = messg;
		               			khambenhtrongngay(messg,mabenhnhan,matiepdon);
		               			//alert(s_benhnhanchuadenngaytaikham + ngaytaikham + ".");      
		               		}               	 
		              // 	}
		                
		          }
		       }
		      
		  } 
		}
		else if(checkBHYTTrung == false && ghinhan == true){
	       		ghi_nhan_event();
	       }        
  }
}
function setMaKhoi(){
	var sothe = document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value;
	sothe = sothe.toUpperCase();
	document.getElementById(prefix_component + "TIEPDON_SOTHEBH").value = sothe;
	if ( sothe != null && sothe != '') {
			document.getElementById(prefix_component + "KHOIBHYT_MA").value = sothe.substr(0,2);
	}
	myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	
}
function getThamKham(idText,urlAction){
	//alert("begin getThamkham");
    var myCondition;
    var url;
    var xml;
    var data;
    
    if(idText != ""){
    	myCondition = idText;
    	
    	var xmlHttp = createXmlHttpRequest();
    	var url = myContextPath + "/actionServlet?";
    
   		var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);

    	xmlHttp.open("POST", url, false);

	    xmlHttp.onreadystatechange = function(){
	    	handlerForGetThamKham(xmlHttp,myCondition);
	    };
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xmlHttp.send(params);
    
        		       
    }
    //alert("end getThamkham");
}
function handlerForGetThamKham(xmlHttp,myCondition) {
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	 	var jsonExpression = "(" + xmlHttp.responseText + ")";
		var data = eval(jsonExpression);
		var i = 0;
		if(typeof(data.list) == "object"){
			//alert("In handlerForGetThamKham, data.list.THAM_KHAM.THAMKHAM_BACSI = " + data.list.THAM_KHAM.THAMKHAM_BACSI);
			document.getElementById(prefix_component + "__bacsiThamkham").value = data.list.THAM_KHAM.THAMKHAM_BACSI;
		}
	}
	    
}
