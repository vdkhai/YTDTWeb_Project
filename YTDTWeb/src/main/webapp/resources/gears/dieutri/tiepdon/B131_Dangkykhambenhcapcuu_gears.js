function init() {	
    if (window.google && google.gears) {    	
        try { 	 
	setAttrForCombobox(prefix_component + "DANTOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");
	setAttrForCombobox(prefix_component + "NGHENGHIEP_MA","DM_NGHE_NGHIEP_span","DM_NGHE_NGHIEP","getDmNgheNghiepByYearOld(\"" + prefix_component + "__tuoi" + "\")","","","");
	setAttrForCombobox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuongTP()","","onChangeDoiTuong()","");
	
	setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
	setAttrForComboboxForHuyen(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
    setAttrForComboboxForXa(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
 	
 	
	setAttrForCombobox(prefix_component + 'BANKHAM_MA','DT_DM_BAN_KHAM_span', 'DT_DM_BAN_KHAM',"getDtDmBanKhamCCL()","","","" );
	setAttrForCombobox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1','DT_DM_NHAN_VIEN_span1', 'DT_DM_NHAN_VIEN__1',"getDtDmBacSi()","","","");
    //            alert(1);
	setAttrForComboboxForTinhBHYT(prefix_component + 'TINHBHYT_MA','DM_TINH2_span','DM_TINH__2', "getDmTinh()", "", "", "");
	        
	setAttrForCombobox(prefix_component + "KHOIBHYT_MA","DT_DM_KHOI_BHYT_span","DT_DM_KHOI_BHYT", "getDtDmKhoiBhyt()", "", "", "");
	
	//setAttrForCombobox(prefix_component + "KCB_BHYT_MA","DT_DM_KCB_BHYT_span","DT_DM_KCB_BHYT","getDtDmKcbBhyt()","","","");
	setAttrForCombobox(prefix_component + "KCB_BHYT_MA","DM_BENH_VIEN5_span","DM_BENH_VIEN__5","getDmBenhVien()","","","");
	
	
	setAttrForCombobox(prefix_component + "TAINAN_MA","DM_TAI_NAN_span","DM_TAI_NAN","getDmTaiNan()","onChangePhuongThucGayTaiNan()","showMuBH()","");
	
	
	
	setAttrForCombobox(prefix_component + "PHUONGTIEN_MA","DM_PHUONG_THUC_GAY_TAI_NAN_span","DM_PHUONG_THUC_GAY_TAI_NAN","getDmPhuongThucGayTaiNanByTaiNanMa(\"" + prefix_component + "TAINAN_MA_pk" + "\")","","","");
	setAttrForCombobox(prefix_component + "DIADIEM_MA","DM_DIA_DIEM_span","DM_DIA_DIEM","getDmDiaDiem()","","","");
	setAttrForCombobox(prefix_component + "BENHVIEN_MA","DM_BENH_VIEN_span","DM_BENH_VIEN","getDmBenhVien()","","","");
	setAttrForCombobox(prefix_component + "BENHICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");			
	timer.setTimeout(function(){focusInit();},1000);           	
        } catch (e) {
        	alert("init error: " + e.description); 
        }
    }     
}
function onChangePhuongThucGayTaiNan(){
	document.getElementById(prefix_component + "PHUONGTIEN_MA_pk").value = "";
	document.getElementById(prefix_component + "PHUONGTIEN_MA").value = "";
	document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value = "";
}

var colorInput="white";
var colorReadonly="#e8e8e8";

function onChangeDoiTuong(){
	var cond = document.getElementById(prefix_component + "DOITUONG_MA").value;
	if (cond=="BH"){
		readonly("BH",false,colorInput);
		readonly("MP",true,colorReadonly);
		readonly("TE",true,colorReadonly);
		//document.getElementById(prefix_component + "__nam").value = (new Date()).getFullYear();
		
		if (document.getElementById(prefix_component + "TINHBHYT_MA").value ==''){
			var tinhbhyt = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
			if (tinhbhyt) {
				document.getElementById(prefix_component + "TINHBHYT_MA").value = tinhbhyt.MaTinhBHYT;
				dojo.byId("DM_TINH__2").value = tinhbhyt.Ten;
			}
		}
		
		if (document.getElementById(prefix_component + "KCB_BHYT_MA").value == ''){
			var kcbbhyt = DmBenhVien.getByFieldValue("DMBENHVIEN_DEFAULT", 1);
			if (kcbbhyt) {
				document.getElementById(prefix_component + "KCB_BHYT_MA").value = kcbbhyt.Ma;
				dojo.byId("DM_BENH_VIEN__5").value = kcbbhyt.Ten;
			}
		}
		
		
	}else if (cond=="MP"){
		readonly("BH",true,colorReadonly);
		readonly("MP",false,colorInput);
		readonly("TE",true,colorReadonly);
		//document.getElementById(prefix_component + "__nam").value = "";
		
	}else if (cond=="TE"){
		readonly("BH",true,colorReadonly);
		readonly("MP",true,colorReadonly);
		readonly("TE",false,colorInput);
		//document.getElementById(prefix_component + "__nam").value = "";
	}else{
		readonly("BH",true,colorReadonly);
		readonly("MP",true,colorReadonly);
		readonly("TE",true,colorReadonly);
		//document.getElementById(prefix_component + "__nam").value = "";
	}
	setTyleBnTra(cond);
}	

function setTyleBnTra(cond) {
	var objArr = DmDoiTuong.filter("Ma = '" + cond + "'").toArray();
	var havingData = false;
	for (var i = 0; i < objArr.length; i++) {
	    if (objArr[i].DMDOITUONG_TYLEMIEN != null && objArr[i].DMDOITUONG_TYLEMIEN != "") {
	    	var value = 100 - parseInt(objArr[i].DMDOITUONG_TYLEMIEN);
	       	//document.getElementById(prefix_component + "__perbenhnhantra").value = value;
	    	havingData = true;
	    }
	}
	if(havingData == true) {
	    	return;
	}
	//document.getElementById(prefix_component + "__perbenhnhantra").value = "";
}

function readonly(cond,b,color){
	if (cond=="BH"){
		var obj = document.getElementById(prefix_component + "__sothe"); 		
		obj.disabled=b;obj.style.background=color;
		//obj = document.getElementById(prefix_component + "__nam");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "TINHBHYT_MA");
		obj.disabled=b;obj.style.background=color;
		obj = dijit.byId("DM_TINH__2");
		obj.disabled=b;obj.setAttribute("style","background:" + color);
		document.getElementById("DM_TINH__2").disabled=b;
		obj = document.getElementById(prefix_component + "KHOIBHYT_MA");
		obj.disabled=b;obj.style.background=color;
		obj = dijit.byId("DT_DM_KHOI_BHYT");
		obj.disabled=b;obj.setAttribute("style","background:" + color);
		document.getElementById("DT_DM_KHOI_BHYT").disabled=b;
		obj = document.getElementById(prefix_component + "__coquan");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "KCB_BHYT_MA");
		obj.disabled=b;obj.style.background=color;
		obj = dijit.byId("DM_BENH_VIEN__5");
		obj.disabled=b;obj.setAttribute("style","background:" + color);
		document.getElementById("DM_BENH_VIEN__5").disabled=b;
		//obj = document.getElementById(prefix_component + "__tuyen");
		//obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__tungaydt");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__denngaydt");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__tubv");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__denbv");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__tumoc");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__denmoc2");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__denmoc3");
		obj.disabled=b;obj.style.background=color;			
	}else if (cond=="TE"){
		obj = document.getElementById(prefix_component + "__sothete");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__khaisinh");
		obj.disabled=b;obj.style.background=color;
		obj = document.getElementById(prefix_component + "__chungsinh");
		obj.disabled=b;obj.style.background=color;
	}else if (cond=="MP"){
		obj = document.getElementById(prefix_component + "__thengheo");
		obj.disabled=b;obj.style.background=color;
	}
}

function focusInit(){
	loadDefault();	
	//readonly("BH",true,colorReadonly);
	//readonly("MP",true,colorReadonly);
	//readonly("TE",true,colorReadonly);	
	//setDefaultValueForBS();
	
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN__1");
	myOnblurTextboxTinhBHYT(prefix_component + "TINHBHYT_MA", 'DM_TINH__2');
	document.getElementById(prefix_component + "__hoten").focus();
}

function loadDefault(){
	myOnblurTextbox(prefix_component + "BANKHAM_MA","DT_DM_BAN_KHAM");
	
//	var bk = document.getElementById(prefix_component + "BANKHAM_MA").value;	
//	if (bk != null && bk != ''){
//		var arrBanKham = DtDmBanKham.filter("Ma = '" + bk + "'").toArray();
//		if (arrBanKham != null && arrBanKham.length > 0){
//			document.getElementById(prefix_component + "BANKHAM_MA_pk").value=arrBanKham[0].MaSo;	
//			document.getElementById(prefix_component + "DT_DM_BAN_KHAM").value=arrBanKham[0].Ten;
//		}
//	}
	
	//bao.ttc: set ban kham CCL hay CCN
	//setBankham();
		
	var arrDanToc = DmDanToc.filter("DMDANTOC_DEFA = 1").toArray();
	if (document.getElementById(prefix_component + "DANTOC_MA").value == null||
		document.getElementById(prefix_component + "DANTOC_MA").value == '')
	{
		document.getElementById(prefix_component + "DANTOC_MA_pk").value=arrDanToc[0].MaSo;
		document.getElementById(prefix_component + "DANTOC_MA").value=arrDanToc[0].Ma;
		document.getElementById("DM_DAN_TOC").value=arrDanToc[0].Ten;
	}

	
	var arrTinh = DmTinh.filter("DMTINH_DEFA = 1").toArray();
	
	if (document.getElementById(prefix_component + "TINH_MA").value == null ||
	document.getElementById(prefix_component + "TINH_MA").value == '')
	{
		if (arrTinh != null && arrTinh.length > 0){
			document.getElementById(prefix_component + "TINH_MA_pk").value=arrTinh[0].MaSo;
			document.getElementById(prefix_component + "TINH_MA").value=arrTinh[0].Ma;
			document.getElementById("DM_TINH").value=arrTinh[0].Ten;	
		}
		
	}

	
	var arrHuyen = DmHuyen.filter("DMHUYEN_DEFA = 1").toArray();
	if (document.getElementById(prefix_component + "HUYEN_MA").value == null ||
	document.getElementById(prefix_component + "HUYEN_MA").value == '' ){
	if (arrHuyen != null && arrHuyen.length > 0){
		document.getElementById(prefix_component + "HUYEN_MA_pk").value=arrHuyen[0].MaSo;
		document.getElementById(prefix_component + "HUYEN_MA").value=arrHuyen[0].Ma;
		document.getElementById("DM_HUYEN").value=arrHuyen[0].Ten;
	}
	}
	
	
	var arrXa = DmXa.filter("DMXA_DEFA = 1").toArray();
	if (document.getElementById(prefix_component + "XA_MA").value == null ||
	document.getElementById(prefix_component + "XA_MA").value == ''){
	if (arrXa != null && arrXa.length > 0){
		document.getElementById(prefix_component + "XA_MA_pk").value=arrXa[0].MaSo;
		document.getElementById(prefix_component + "XA_MA").value=arrXa[0].Ma;
		document.getElementById("DM_XA").value=arrXa[0].Ten;
		}
	}

	
	var arrGioiTinh = DmGioiTinh.filter("DMGT_DEFA = 1").toArray();
	//alert(arrGioiTinh[0].Ma);
	if (document.getElementById(prefix_component + "gioitinh:0").checked == false &&
	  document.getElementById(prefix_component + "gioitinh:1").checked == false){
		if (arrGioiTinh[0].Ma==0){ //nu
			document.getElementById(prefix_component + "gioitinh:1").checked = true;
		}else if (arrGioiTinh[0].Ma==1){ //nam
			document.getElementById(prefix_component + "gioitinh:0").checked = true;
		}
	}
	
}

function oncompleteOfNhapMoi(){
	loadDefault();
	document.getElementById(prefix_component + "__ngaysinh").value="";
	//document.getElementById("DM_NGHE_NGHIEP").value="";
	//document.getElementById("DM_DOI_TUONG").value="";
	//document.getElementById("DM_TINH__2").value="";
	//document.getElementById("DT_DM_KHOI_BHYT").value="";
	document.getElementById(prefix_component + "__tungaydt").value="";
	document.getElementById(prefix_component + "__denngaydt").value="";
	//document.getElementById("DM_BENH_VIEN__5").value="";
	//document.getElementById("DM_PHAN_LOAI_TAI_NAN").value="";
	//document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value="";
	//document.getElementById("DM_DIA_DIEM").value="";
	//document.getElementById("DM_BENH_VIEN").value="";
	//document.getElementById("DM_BENH_ICD").value="";
	
	
	myOnblurTextbox(prefix_component + "DANTOC_MA","DM_DAN_TOC");
	myOnblurTextbox(prefix_component + "NGHENGHIEP_MA","DM_NGHE_NGHIEP");
	myOnblurTextbox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG");
	myOnblurTextbox(prefix_component + "TINH_MA","DM_TINH");
	myOnblurTextbox(prefix_component + "HUYEN_MA","DM_HUYEN");
	myOnblurTextbox(prefix_component + "XA_MA","DM_XA");
	myOnblurTextboxTinhBHYT(prefix_component + "TINHBHYT_MA","DM_TINH__2");
	myOnblurTextbox(prefix_component + "KHOIBHYT_MA","DT_DM_KHOI_BHYT");
	myOnblurTextbox(prefix_component + "KCB_BHYT_MA","DM_BENH_VIEN__5");
	myOnblurTextbox(prefix_component + "TAINAN_MA","DM_TAI_NAN");
	myOnblurTextbox(prefix_component + "PHUONGTIEN_MA","DM_PHUONG_THUC_GAY_TAI_NAN");
	myOnblurTextbox(prefix_component + "DIADIEM_MA","DM_DIA_DIEM");
	myOnblurTextbox(prefix_component + "BENHVIEN_MA","DM_BENH_VIEN");
	myOnblurTextbox(prefix_component + "BENHICD_MA","DM_BENH_ICD");
	
}

function oncompleteOfGhiNhan(){	
	luuTruGiaTriClientDefaultForBS();
	oncompleteOfNhapMoi();
}

function onCompleteOfFound(){
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		oncompleteOfNhapMoi();
		document.getElementById(prefix_component + "__matiepdon").value="";
		document.getElementById(prefix_component + "__mabenhnhan").value="";
		document.getElementById(prefix_component + "__matiepdon").focus();
		return;
	} 
	myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN__1");
	myOnblurTextbox(prefix_component + "DANTOC_MA","DM_DAN_TOC");
	myOnblurTextbox(prefix_component + "NGHENGHIEP_MA","DM_NGHE_NGHIEP");
	myOnblurTextbox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG");
	myOnblurTextbox(prefix_component + "TINH_MA","DM_TINH");
	myOnblurTextbox(prefix_component + "HUYEN_MA","DM_HUYEN");
	myOnblurTextbox(prefix_component + "XA_MA","DM_XA");
	myOnblurTextboxTinhBHYT(prefix_component + "TINHBHYT_MA","DM_TINH__2");
	myOnblurTextbox(prefix_component + "KHOIBHYT_MA","DT_DM_KHOI_BHYT");
	myOnblurTextbox(prefix_component + "KCB_BHYT_MA","DM_BENH_VIEN__5");
	myOnblurTextbox(prefix_component + "TAINAN_MA","DM_TAI_NAN");
	myOnblurTextbox(prefix_component + "PHUONGTIEN_MA","DM_PHUONG_THUC_GAY_TAI_NAN");
	myOnblurTextbox(prefix_component + "DIADIEM_MA","DM_DIA_DIEM");
	myOnblurTextbox(prefix_component + "BENHVIEN_MA","DM_BENH_VIEN");
	myOnblurTextbox(prefix_component + "BENHICD_MA","DM_BENH_ICD");
	onChangeDoiTuong();
}

var thi_1 = "thị";
var thi_2 = "Thị";
		
var van_1 = "văn";
var van_2 = "Văn";

function checkForGioi(){
		
	var valueTen = document.getElementById(prefix_component + "__hoten").value;	
	if (valueTen != null && valueTen != "" && ( valueTen.indexOf(thi_1) >= 0 || valueTen.indexOf(thi_2) >= 0)){
		document.getElementById(prefix_component + "gioitinh:1").checked = true;
	}
	
	if (valueTen != null && valueTen != "" && ( valueTen.indexOf(van_1) >= 0 || valueTen.indexOf(van_2) >= 0)){
		document.getElementById(prefix_component + "gioitinh:0").checked = true;
	}
		
}
/*function setDefaultValueForBS(){

		var giaTriBSMa = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBSMa != null && giaTriBSMa != ""){
		
		}else{
			
			var bsClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B131_dangkykhambenhcc_bacsi");
			if (bsClientDefault) {
				document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value = bsClientDefault.Ten;
				myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
				//document.getElementById(prefix_component + "DT_DM_BAN_KHAM_MA_1").select();
			}	
		}
	
}*/
function luuTruGiaTriClientDefaultForBS(){
	
		var giaTriBSMa = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;
		// alert("giaTriBacSiMa:"+giaTriBacSiMa);
		if (giaTriBSMa != null && giaTriBSMa != ""){
			var bsClientDefault = DtDmClientDefault.getByFieldValue("Ma", "B131_dangkykhambenhcc_bacsi");
			//alert("bankhamClientDefault:"+bankhamClientDefault);
			if (bsClientDefault == null || bsClientDefault == false) { //insert
				var chNames = new Array();
				chNames[0] = "MaSo";
				chNames[1] = "Ma";
				chNames[2] = "Ten";
				var chValues = new Array();
				chValues[0] = 131001;
				chValues[1] = "B131_dangkykhambenhcc_bacsi";
				chValues[2] = document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").value;	
			    insertObjectWithTrueValue(DtDmClientDefault, chNames, chValues);
			    //alert("insert");
			}else{ //update
				
				var chNames = new Array();
				chNames[0] = "Ten";
				
				var chValues = new Array();
				chValues[0] = giaTriBSMa;
				
				updateObjectWithTrueValue(DtDmClientDefault,"Ma","B131_dangkykhambenhcc_bacsi", chNames,chValues );
				
			}    	
		}
	
}

function setMaKhoi(){
	var sothe = document.getElementById(prefix_component + "__sothe").value;
	sothe = sothe.toUpperCase();
	document.getElementById(prefix_component + "__sothe").value = sothe;
	if ( sothe != null && sothe != '') {
			document.getElementById(prefix_component + "KHOIBHYT_MA").value = sothe.substr(0,2);
	}
	myOnblurTextbox(prefix_component + 'KHOIBHYT_MA', 'DT_DM_KHOI_BHYT');
	
}

function checkSoTheBHYT() {
	var sothebhyt = document.getElementById(prefix_component + "__sothe").value;
	//var hotenbn = document.getElementById(prefix_component + "__hoten").value;
	
	if (sothebhyt != null && sothebhyt != ''){
		//document.getElementById(prefix_component + "DOITUONG_MA").value = "BH";
		if (sothebhyt.length < 15){
			alert("Chiều dài số thẻ BHYT phải bằng 15");
			//document.getElementById(prefix_component + "__sothe").value = "";
			document.getElementById(prefix_component + "__sothe").focus();
			document.getElementById(prefix_component + "__sothe").focus();
		} else {
			var maTinhBhyt = sothebhyt.substr(3,2) +",";  
    		var listMaTinhBhyt = document.getElementById(prefix_component + "listMaTinhBhyt").value;
			setMaKhoi();
			if(document.getElementById(prefix_component + "KHOIBHYT_MA").value == '') {
				alert("Mã đối tượng bảo hiểm của số thẻ " + sothebhyt.toUpperCase() + " là không hợp lệ.");
				document.getElementById(prefix_component + "__sothe").value ="";
				document.getElementById(prefix_component + "__sothe").focus();
			} else if (listMaTinhBhyt.indexOf(maTinhBhyt) < 0){
				alert("Mã tỉnh bảo hiểm của số thẻ " + sothebhyt.toUpperCase() + " là không hợp lệ.");
				document.getElementById(prefix_component + "__sothe").value ="";
				document.getElementById(prefix_component + "__sothe").focus(); 
			} else {
				document.getElementById(prefix_component + "DOITUONG_MA").value = "BH";
				//checkDoiTuong();
				document.getElementById(prefix_component + "btnCheckSoThe").click();
			}
			//document.getElementById(prefix_component + "btnCheckSoThe").click();
		}
	} 

}

function lockDoiTuong() {
	
    //document.getElementById(prefix_component + "DOITUONG_MA").disabled=(document.getElementById(prefix_component + "__matiepdon").value != "");
	document.getElementById(prefix_component + "DOITUONG_MA").disabled=(document.getElementById(prefix_component + "lockDoituong").value == 'true');
    var widget = dijit.byId("DM_DOI_TUONG");
    if (widget == null){		    	
    	//document.getElementById("DM_DOI_TUONG").disabled = (document.getElementById(prefix_component + "__matiepdon").value != "");
    	document.getElementById("DM_DOI_TUONG").disabled = (document.getElementById(prefix_component + "lockDoituong").value == 'true');    	
    }else{
     	//widget.setAttribute('disabled',(document.getElementById(prefix_component + "__matiepdon").value != ""));
     	widget.setAttribute('disabled',(document.getElementById(prefix_component + "lockDoituong").value == 'true'));
     	
    }
	
}

function unlockDoiTuong() {
	
	if (document.getElementById("DM_DOI_TUONG").disabled == true){
        changeDisabledAttr("DM_DOI_TUONG");  
   	}
    document.getElementById(prefix_component + "DOITUONG_MA").disabled = false;
	
}
function showMuBHFromData()
{
	//alert('ok');
	//alert(document.getElementById(prefix_component + "TAINAN_MA").value.toLowerCase());
	if(document.getElementById(prefix_component + "TAINAN_MA").value.toLowerCase()=="gt")
	{
		//alert("vao trong");
		document.getElementById("__mubaohiem").style.display = "block";
		//alert("mo block doi mu");
		if(document.getElementById(prefix_component + "__codoimu").checked)
		{
			//alert("mo block chi tiet doi mu");
			document.getElementById("__mubaohiemchitiet").style.display = "block";
		}
	}
}
function showMuBH()
{
	//alert("check tai nan giao thong");
	//alert(document.getElementById(prefix_component + "TAINAN_MA").value);
	
	if(document.getElementById(prefix_component + "TAINAN_MA").value.toLowerCase()=="gt")
	{
		document.getElementById("__mubaohiem").style.display = "block";
		
	}else
	{
		document.getElementById("__mubaohiem").style.display = "none";
		document.getElementById("__mubaohiemchitiet").style.display = "none";
	}
	document.getElementById(prefix_component + "__codoimu").checked = false;
	MuBHCTReset();
}

function showMuBHCT()
{
	if(document.getElementById(prefix_component + "__codoimu").checked)
	{
		document.getElementById("__mubaohiemchitiet").style.display = "block";
		
	}else
	{
		document.getElementById("__mubaohiemchitiet").style.display = "none";
	}
}
function MuBHCTReset()
{
	document.getElementById(prefix_component + "__muvo").checked = false;
	document.getElementById(prefix_component + "__khongcaiday").checked = false;
	document.getElementById(prefix_component + "__khongronguongoc").checked = false;
}

function lockBanKham() {
	    
	document.getElementById(prefix_component + "BANKHAM_MA").disabled=(document.getElementById(prefix_component + "lockBanKham").value == 'true');
    var widget = dijit.byId("DT_DM_BAN_KHAM");
    if (widget == null){		    	    	
    	document.getElementById("DT_DM_BAN_KHAM").disabled = (document.getElementById(prefix_component + "lockBanKham").value == 'true');    	
    }else{     	
     	widget.setAttribute('disabled',(document.getElementById(prefix_component + "lockBanKham").value == 'true'));
     	
    }
	
}

function unlockBanKham() {
	
	if (document.getElementById("DT_DM_BAN_KHAM").disabled == true){
        changeDisabledAttr("DT_DM_BAN_KHAM");  
   	}
    document.getElementById(prefix_component + "BANKHAM_MA").disabled = false;
	
}