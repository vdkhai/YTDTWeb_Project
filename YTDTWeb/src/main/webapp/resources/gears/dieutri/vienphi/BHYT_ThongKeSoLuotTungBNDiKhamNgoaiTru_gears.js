function init() {
	
    if (window.google && google.gears) {
    	
        //try {
        	
				setAttrForCombobox(prefix_component + "DT_DM_KCB_BHYT_MA","DT_DM_KCB_BHYT_span","DT_DM_KCB_BHYT","getDtDmKcbBhyt()","","","");	
				setAttrForCombobox(prefix_component + "DT_DM_PL_BHYT_MA","DT_DM_PL_BHYT_span","DT_DM_PL_BHYT","getDtDmPlBhyt()","","","");	
											
				timer.setTimeout(function(){setValueOnLoad();},100); 	
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
}
function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();
}

function onComplete(){
	myOnblurTextbox(prefix_component + 'DT_DM_KCB_BHYT_MA', 'DT_DM_KCB_BHYT');
	myOnblurTextbox(prefix_component + 'DT_DM_PL_BHYT_MA', 'DT_DM_PL_BHYT');

}
function testValue(){
	
	var giaTriSoLan =  document.getElementById(prefix_component + "__soLan").value;
	
	var objSoDau =  document.getElementById(prefix_component + "__soDau");
	var objSoCuoi =  document.getElementById(prefix_component + "__soCuoi");
	
	
	if (giaTriSoLan == "1" || giaTriSoLan == "2" || giaTriSoLan == "3"){
		if ( objSoDau.value == "") {
			
			alert("Nhập số lần");
			objSoDau.focus();
			return false;
		}
		
	}else if (giaTriSoLan == "4"){
		if ( objSoDau.value == "" ) {
			alert("Nhập số lần");
			objSoDau.focus();
			return false;
		}
		if ( objSoDau.value == "" ) {
			alert("Nhập số lần");
			objSoCuoi.focus();
			return false;
		}
	}
	return true;

}
function setValueTuDen(){
	
	var giaTriSoLan =  document.getElementById(prefix_component + "__soLan").value;
	
	var objSoDau =  document.getElementById(prefix_component + "__soDau");
	var objSoCuoi =  document.getElementById(prefix_component + "__soCuoi");
	
	
	if (giaTriSoLan == "1"){
		objSoDau.disabled = false;
		objSoCuoi.disabled = true;
	}else if (giaTriSoLan == "2"){
		objSoDau.disabled = false;
		objSoCuoi.disabled = true;
	}else if (giaTriSoLan == "3"){
		objSoDau.disabled = false;
		objSoCuoi.disabled = true;
	}else if (giaTriSoLan == "4"){
		objSoDau.disabled = false;
		objSoCuoi.disabled = false;
	}else if (giaTriSoLan == "5"){
		objSoDau.disabled = true;
		objSoCuoi.disabled = true;
	}

}



