function init() {
    if (window.google && google.gears) {
        try {
			setAttrForCombobox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG","getDmDoiTuong()","","tinhTien()","");
			setAttrForCombobox(prefix_component + "TIENKHAM_MA","DT_DM_TIEN_KHAM_span","DT_DM_TIEN_KHAM","getDtDmTienKham()","","tinhTien()","");
			setAttrForCombobox(prefix_component + "BANKHAM_MA","DT_DM_BAN_KHAM_span","DT_DM_BAN_KHAM","getDtDmBanKham()","","getStt()","");
			setValueOnLoad();
			document.getElementById(prefix_component + "__gio").focus();
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad() {
	myOnblurTextbox(prefix_component + "DOITUONG_MA", "DM_DOI_TUONG");
	myOnblurTextbox(prefix_component + "TIENKHAM_MA", "DT_DM_TIEN_KHAM");
	myOnblurTextbox(prefix_component + "BANKHAM_MA", "DT_DM_BAN_KHAM");
	document.getElementById(prefix_component + "__sothutu").value = document.getElementById(prefix_component + "sttHid").value;
	tinhTien();
}

function tinhTien() {
	if (document.getElementById(prefix_component + "DOITUONG_MA").value != "" && 
		document.getElementById(prefix_component + "TIENKHAM_MA").value != "") {
		
		var dtMa = document.getElementById(prefix_component + "DOITUONG_MA").value;
		var tienKhamMa = document.getElementById(prefix_component + "TIENKHAM_MA").value;
	
		var doiTuong = DmDoiTuong.getByFieldValue("Ma", dtMa);
		var tienKham = DtDmTienKham.getByFieldValue("Ma", tienKhamMa);
		
		var tyleMien = 0;
		if (doiTuong.DMDOITUONG_TYLEMIEN != null && doiTuong.DMDOITUONG_TYLEMIEN != "") {
	        tyleMien = parseInt(doiTuong.DMDOITUONG_TYLEMIEN);
	  	}
	  	//alert("tyleMien: " + tyleMien);
	  	if(tyleMien > 0) {
	  		var temp = (tienKham.DTDMTIENKHAM_DONGIA * tyleMien) / 100;
	    	document.getElementById(prefix_component + "__bntra").value = tienKham.DTDMTIENKHAM_DONGIA - temp;
	    } else {
	        document.getElementById(prefix_component + "__bntra").value = tienKham.DTDMTIENKHAM_DONGIA;
	   	}
	}
}

function getStt() {
	if (document.getElementById(prefix_component + "BANKHAM_MA").value != "") {
		var xmlHttp = createXmlHttpRequest();
		var url = myContextPath + "/actionServlet?";
		var params = "urlAction="+ encodeURI("GetSoDangKyKhamBenhAction") + "&xmlData=" + 
						encodeURI(document.getElementById(prefix_component + "BANKHAM_MA").value);
		xmlHttp.open("POST", url, false);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				
				var jsonExpression = "(" + xmlHttp.responseText + ")";
				var jsonData = eval(jsonExpression);
				document.getElementById(prefix_component + "sttHid").value = jsonData.result;
				document.getElementById(prefix_component + "__sothutu").value = jsonData.result;
			}
		};
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params); 
	}
}