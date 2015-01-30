function init() {
	
    if (window.google && google.gears) {
    	
        try {
        	timer.setTimeout(function(){InitSetInfor();},100);
				            
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function InitSetInfor(){
	var loaibaocao = document.getElementById(prefix_component + "__loaibaocao").value;
	if (loaibaocao == 'v06BD') {
		document.getElementById(prefix_component + "__ngaybaocao").focus();
	} else {
		document.getElementById(prefix_component + "__thang").focus();
	}
	
}
