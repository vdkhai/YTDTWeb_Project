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
				cbTmp = document.getElementById(prefix_component + "DM_NGHE_NGHIEP");
				cbTmp.dojoType="dijit.form.ComboBox";
				cbTmp = document.getElementById(prefix_component + "DM_KHOA");
				cbTmp.dojoType="dijit.form.ComboBox";
				cbTmp = document.getElementById(prefix_component + "DM_BENH_ICD");
				cbTmp.dojoType="dijit.form.ComboBox";		
				timer.setTimeout(function(){focusInit();},100);				
    			     
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function focusInit(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById(prefix_component + "__magiay").focus(); 
}

function oncompleteOfGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__magiay").focus();		
		return;
	}
	document.getElementById("__divInPhieu").style.display = "block";
	document.getElementById("__divGhiNhan").style.display = "none";
}

function oncompleteOfTiepTucNhap(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
}

function oncompleteOfBATD(){
	if (document.getElementById(prefix_component + "nofoundBATD").value == "true") {
		document.getElementById(prefix_component + "nofoundBATD").value = "false";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById(prefix_component + "__sobenhan").focus();
		return;
	}
}

function oncompleteOfGiayCT(){
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__magiay").value = "";
		document.getElementById(prefix_component + "__magiay").focus();
		return;
	} 
	document.getElementById("__divInPhieu").style.display = "block";
}