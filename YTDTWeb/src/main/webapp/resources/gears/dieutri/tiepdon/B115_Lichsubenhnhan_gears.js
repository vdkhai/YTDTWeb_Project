function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	//db = google.gears.factory.create('beta.database');
            
				setAttrForCombobox(prefix_component + "DANTOC_MA","DM_DAN_TOC_span","DM_DAN_TOC","getDmDanToc()","","","");
				setAttrForCombobox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","","","","");
				setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","","","");
				setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA" + "\")","","","");
				setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA" + "\")","","","");
				timer.setTimeout(function(){onCompleteRefresh();},500);
            	document.getElementById(prefix_component + "__mabenhnhan").focus();
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function searchAction(){			
	var __mabenhnhan = document.getElementById(prefix_component + "__mabenhnhan").value;
	var __matiepdon = document.getElementById(prefix_component + "__matiepdon").value;
	//var __hoten = document.getElementById(prefix_component + "__hoten").value;
	var __ho = document.getElementById(prefix_component + "__ho").value;
	var __ten = document.getElementById(prefix_component + "__ten").value;
	
	if( (__mabenhnhan != null && __mabenhnhan != '') || (__matiepdon != null && __matiepdon != '') || (__ho != null && __ho != '') || (__ten != null && __ten != '') ){
		try{
			var request = __mabenhnhan + " ;" + __matiepdon + " ;" + __ho + " ;" + __ten + " ;";
	    	var url = myContextPath + "/actionServlet?";
	    	var xmlHttp = createXmlHttpRequest();
	    	var params = "urlAction="+ encodeURI("GetLichSuBenhNhanAction") +  "&xmlData=" + encodeURI(request);
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
	else 
		alert("Cần nhập ít nhất 1 trong 4 tiêu chí để tìm kiếm: (1)Mã BN - (2)Họ - (3)Tên - (3)Mã TĐ");	
	
}
		
function handleStateChangeForTimKiem(xmlHttp){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	  	document.getElementById(prefix_component + "result").value = xmlHttp.responseText;
    }
}

function onCompleteChonLai(){
	document.getElementById("DM_DAN_TOC").value = "";
	document.getElementById("DM_DOI_TUONG").value = "";
	document.getElementById("DM_TINH").value = "";
	document.getElementById("DM_HUYEN").value = "";
	document.getElementById("DM_XA").value = "";
	document.getElementById(prefix_component + "__ho").value = "";
	document.getElementById(prefix_component + "__ten").value = "";
}

function onCompleteRefresh(){
	myOnblurTextbox(prefix_component + "DANTOC_MA","DM_DAN_TOC");	
	myOnblurTextbox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG");	
	myOnblurTextbox(prefix_component + "TINH_MA","DM_TINH");	
	myOnblurTextbox(prefix_component + "HUYEN_MA","DM_HUYEN");	
	myOnblurTextbox(prefix_component + "XA_MA","DM_XA");	
}



