function init() {
	
    if (window.google && google.gears) {
    	
        try {
           	//setAttrForCombobox(prefix_component + "__madoituong","DT_DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","",""); 
          	//setAttrForCombobox(prefix_component + "__madantoc","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");  
          	//setAttrForCombobox(prefix_component + "__manghenghiep","DM_NGHE_NGHIEP_span","DM_NGHE_NGHIEP","getDmNgheNghiep()","","","");  
           	//setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
           	//setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
           	//setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           	//setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVien_Kham()","","",""); //bo truong thu ngan
        	//setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1","DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");  
        	document.getElementById(prefix_component + "__matiepdon").focus();
        
        	 timer.setTimeout(function(){setValueOnLoad();},100); 
        	
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad(){
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", "DT_DM_NHAN_VIEN__1"); //bo truong thu ngan
	displayInphieu();
}

function onCompleteLoadTiepDon() {
	
	//myOnblurTextbox(prefix_component + "__madantoc", "DM_DAN_TOC");
	//myOnblurTextbox(prefix_component + "__manghenghiep", "DM_NGHE_NGHIEP");
	//myOnblurTextbox(prefix_component + "TINH_MA", "DM_TINH");
	//myOnblurTextbox(prefix_component + "HUYEN_MA", "DM_HUYEN");
	//myOnblurTextbox(prefix_component + "XA_MA", "DM_XA");
	//myOnblurTextbox(prefix_component + "__madoituong", "DM_DOI_TUONG");
	//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', 'DT_DM_NHAN_VIEN__1');
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	if (donViTuoi == "1") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	} else if (donViTuoi == "2") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	} else if (donViTuoi == "3") {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
	} else {
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
	
	var ngaysinh = document.getElementById(prefix_component + "__ngaysinh").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	//alert(namsinh);
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "__ngaysinh").value = namsinh;
	}
	displayInphieu();
	
}

function displayInphieu() {
	
	var isUpdate = document.getElementById(prefix_component + "isUpdate").value;
	
	var tenChuongTrinh = document.getElementById(prefix_component + "tenChuongTrinh").value;
	var tiepNhanKhamBenh = document.getElementById(prefix_component + "tiepNhanKhamBenh").value;
	
	if (isUpdate == "0") {
		document.getElementById("divGhiNhan").style.display = "block";		
		if(document.getElementById(prefix_component + "__madoituong").value == 'BH') {
			document.getElementById(prefix_component + "__nguonkhactra").disabled = false;
		} else {
			document.getElementById(prefix_component + "__nguonkhactra").disabled = true;
		}
		
		//if (tenChuongTrinh != tiepNhanKhamBenh){
			document.getElementById("divIn").style.display = "none";
			document.getElementById("div_inphieuTT").style.display = "none";
			document.getElementById("div_huyphieu").style.display = "none";
		//}
		
	} else {
		document.getElementById("divGhiNhan").style.display = "none";
		document.getElementById(prefix_component + "__nguonkhactra").disabled = true;
		//document.getElementById(prefix_component + "outputtextThuNgan").style.display = "none";
		//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").style.display = "none"; //bo truong thu ngan
		//document.getElementById( "DT_DM_NHAN_VIEN_span1").style.display = "none";
		//document.getElementById( "DT_DM_NHAN_VIEN__1").style.display = "none"; //bo truong thu ngan
		if (tenChuongTrinh == "ThuVienPhi") {
			document.getElementById("divIn").style.display = "block";
			document.getElementById("div_inphieuTT").style.display = "block";
			document.getElementById("div_huyphieu").style.display = "block";			
		}
		 //document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").disabled = true; //bo truong thu ngan
      	 //if (document.getElementById( "DT_DM_NHAN_VIEN__1").disabled == false){//bo truong thu ngan
        //	changeDisabledAttr("DT_DM_NHAN_VIEN__1");  //bo truong thu ngan
      	 //}
		//document.getElementById(prefix_component +  "outputStart").style.display = "none";
		
		
	}
	
	if (tenChuongTrinh == tiepNhanKhamBenh || tenChuongTrinh == "VP"){
			document.getElementById("divGhiNhan").style.display = "none";
			document.getElementById(prefix_component + "__nguonkhactra").disabled = true;
					//document.getElementById(prefix_component + "outputtextThuNgan").style.display = "none";
		//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").style.display = "none"; //bo truong thu ngan
		//document.getElementById( "DT_DM_NHAN_VIEN_span1").style.display = "none";
		//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN__1").style.display = "none";//bo truong thu ngan
		//document.getElementById(prefix_component + "DT_DM_NHAN_VIEN_MA_1").disabled = true; //bo truong thu ngan
      	 //if (document.getElementById( "DT_DM_NHAN_VIEN__1").disabled == false){//bo truong thu ngan
        //	changeDisabledAttr("DT_DM_NHAN_VIEN__1");  //bo truong thu ngan
      	 //}
			//document.getElementById(prefix_component +  "outputStart").style.display = "none";
			
			document.getElementById(prefix_component + "__thatthu").readOnly = "readOnly";
			document.getElementById(prefix_component + "__lydo").readOnly = "readOnly";
			document.getElementById(prefix_component + "__bienlai1").readOnly = "readOnly";
			document.getElementById(prefix_component + "__bienlai2").readOnly = "readOnly";
			document.getElementById(prefix_component + "__bienlai3").readOnly = "readOnly";
			// Khi xem chi phi dieu tri thi xoa va disbled truong so phieu
			document.getElementById(prefix_component + "__sophieu").value = "";
			document.getElementById(prefix_component + "__sophieu").disabled = true;
	}
	highlightOnFocus();
	
	if (tenChuongTrinh == tiepNhanKhamBenh){
			document.getElementById(prefix_component + "__thatthu").onblur  = "";
			document.getElementById(prefix_component + "__thatthu").onfocus  = "";
			
			document.getElementById(prefix_component + "__lydo").onblur  = "";
			document.getElementById(prefix_component + "__lydo").onfocus  = "";
			
			
			document.getElementById(prefix_component + "__bienlai1").onblur  = "";
			document.getElementById(prefix_component + "__bienlai1").onfocus  = "";
			
			
			document.getElementById(prefix_component + "__bienlai2").onblur  = "";
			document.getElementById(prefix_component + "__bienlai2").onfocus  = "";
			
			
			document.getElementById(prefix_component + "__bienlai3").onblur  = "";
			document.getElementById(prefix_component + "__bienlai3").onfocus  = "";
			
			
	}
}