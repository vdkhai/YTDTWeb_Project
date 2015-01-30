function init() {
	
    if (window.google && google.gears) { 
    	
        try {
        	//db = google.gears.factory.create('beta.database');
			var cbTmp = document.getElementById(prefix_component + "DM_DAN_TOC");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_TINH");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_HUYEN");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_XA");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DT_DM_KCB_BHYT");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_KHOA");
			cbTmp.dojoType="dijit.form.ComboBox";
			cbTmp = document.getElementById(prefix_component + "DM_KHOA__1");
			cbTmp.dojoType="dijit.form.ComboBox";								
            timer.setTimeout(function(){focusInit();},100);
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){	
	document.getElementById(prefix_component + "__sobenhan").focus(); 
}

function searchAction(){			
	var __sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	var __hoten = document.getElementById(prefix_component + "__hoten").value;
	try{
		var request = __sobenhan + " ;" + __hoten + " ;";
    	var url = myContextPath + "/actionServlet?";
    	var xmlHttp = createXmlHttpRequest();
    	var params = "urlAction="+ encodeURI("GetTimKiemBenhNhanHSThuPhiAction") +  "&xmlData=" + encodeURI(request);
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

