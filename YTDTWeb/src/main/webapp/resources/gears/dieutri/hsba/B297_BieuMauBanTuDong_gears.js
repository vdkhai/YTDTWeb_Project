var canfocus = true;    
function init() {
	
    if (window.google && google.gears) {
    	
        try {
            timer.setTimeout(function(){focusInit();},300);
        } catch (e) {
        	alert("init error: " + e.description); 
        }
    }    
}

function focusInit(){
	document.getElementById("__divInPhieu").style.display = "none";
}

function oncompleteOfHSBA(){
//onTuoi();
	if (document.getElementById(prefix_component + "nofoundHSBA").value == "true") {
		document.getElementById(prefix_component + "nofoundHSBA").value = "false";
		document.getElementById(prefix_component + "__sobenhan").value = "";
		document.getElementById(prefix_component + "__sobenhan").focus();
		
		
		return;
	}
	
}

function oncompleteOfGiayChuyenXac(){
	if (document.getElementById(prefix_component + "nofound").value == "true") {
		document.getElementById(prefix_component + "nofound").value = "false";
		document.getElementById(prefix_component + "__magiay").value = "";
		document.getElementById(prefix_component + "__magiay").focus();
		return;
	} 
	document.getElementById("__divGhiNhan").style.display = "none";
	document.getElementById("__divInPhieu").style.display = "block";
	//onTuoi();
}

function oncompleteOfTiepTucNhap(){
	document.getElementById("__divInPhieu").style.display = "none";
	document.getElementById("__divGhiNhan").style.display = "block";
	//onTuoi();
}

function oncompleteOfGhiNhan(){
	if (document.getElementById(prefix_component + "nosuccess").value == "true") {
		document.getElementById(prefix_component + "nosuccess").value = "false";
		document.getElementById(prefix_component + "__magiay").focus();		
		return;
	}
	document.getElementById("__divGhiNhan").style.display = "none";
	document.getElementById("__divInPhieu").style.display = "block";
	//onTuoi();
}

function onChangeTK(value){
	var now = new Date();	
	var d = new Date(now.getTime() + (value * 24 * 60 * 60 * 1000));	
	var rs = "";
	var ndate = d.getDate();	
	if (ndate<10) {
		rs = rs + "0" + ndate + "/";
	}else{
		rs = rs + ndate + "/";
	}
	var nmonth = d.getMonth();
	nmonth++;
	
	if (nmonth<10) {
		rs = rs + "0" + nmonth + "/";
	}else{
		rs = rs + nmonth + "/";
	}	
	var nyear = d.getFullYear();
	var rs = rs + nyear;
	///onTuoi();
}

function onChangeNTK(value){
	var str = value.split("/");
	var crr = new Date();
	var d = new Date();
	d.setDate(str[0]);
	d.setMonth(str[1]-1);
	d.setFullYear(str[2]);
	var rs = (d.getTime() - crr.getTime())/(24 * 60 * 60 * 1000);
	//onTuoi();
}

function onTuoi(){
	var donViTuoi = document.getElementById(prefix_component + "__donViTuoiHid").value;
	//alert(donViTuoi);
	if (donViTuoi == "1"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}else if (donViTuoi == "2"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Tháng)";
	}else if (donViTuoi == "3"){
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Ngày)";
		//alert("3");
	}else{
		document.getElementById(prefix_component + "__donViTuoi").innerHTML = "(Năm)";
	}
		
}
