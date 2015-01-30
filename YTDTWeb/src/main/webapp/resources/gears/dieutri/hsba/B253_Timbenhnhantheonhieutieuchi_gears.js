function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
											
				//setAttrForCombobox(prefix_component + "DM_DAN_TOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");				
				//setAttrForCombobox(prefix_component + "DM_TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");				
				//setAttrForCombobox(prefix_component + "DM_HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "DM_TINH_MA\")","resetDMXa()","","");
				//setAttrForCombobox(prefix_component + "DM_XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "DM_HUYEN_MA\")","","","");				
				//setAttrForCombobox(prefix_component + "DM_NGHE_NGHIEP_MA","DM_NGHE_NGHIEP_span","DM_NGHE_NGHIEP","getDmNgheNghiep()","","","");				
				//setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa()","","","");				
				//setAttrForCombobox(prefix_component + "DM_BENH_ICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");				
				//setAttrForCombobox(prefix_component + "DM_BENH_ICD1_MA","DM_BENH_ICD1_span","DM_BENH_ICD__1","getDmBenhIcd()","","","");				
				//setAttrForCombobox(prefix_component + "DM_DOI_TUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","","");
				//setAttrForCombobox(prefix_component + "DM_DOI_TUONG1_MA","DM_DOI_TUONG1_span","DM_DOI_TUONG__1","getDmDoiTuong()","","","");				
				//setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmPhanLoaiTaiNan()","","","");
				//setAttrForCombobox(prefix_component + "DM_BENH_VIEN_MA","DM_BENH_VIEN_span","DM_BENH_VIEN","getDmBenhVien()","","","");				
				//setAttrForCombobox(prefix_component + "DT_DM_NOI_SINH_MA","DT_DM_NOI_SINH_span","DT_DM_NOI_SINH","getDtDmNoiSinh()","","","");				
				//setAttrForCombobox(prefix_component + "DT_DM_KET_QUA_MA","DT_DM_KET_QUA_span","DT_DM_KET_QUA","getDtDmKetQua()","","","");				
				            timer.setTimeout(function(){focusInit();},100);    
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}
function focusInit(){	
	document.getElementById(prefix_component + "__thang").focus();
}

function searchAction(){			
	var __sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	var __hoten = document.getElementById(prefix_component + "__hoten").value;
	var __tungay = document.getElementById(prefix_component + "__tungay").value;
	var __denngay = document.getElementById(prefix_component + "__denngay").value;
	try{
		var request = __sobenhan + " ;" + __hoten + " ;" + __tungay + " ;" + __denngay + " ;";
    	var url = myContextPath + "/actionServlet?";
    	var xmlHttp = createXmlHttpRequest();
    	var params = "urlAction="+ encodeURI("GetTimKiemBenhNhanNoiTruAction") +  "&xmlData=" + encodeURI(request);
		xmlHttp.open("POST", url, false);
	    xmlHttp.onreadystatechange = function(){
    		handleStateChangeForTimKiem(xmlHttp);
    	};		
    	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params); 
	}catch (e){
		alert("searchAction: " + e);
	}
}
		
function handleStateChangeForTimKiem(xmlHttp){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	  	document.getElementById(prefix_component + "result").value = xmlHttp.responseText;
    }
}




