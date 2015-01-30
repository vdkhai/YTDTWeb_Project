function init() {
	// xoa cac bang tiep don va` benh nhan trong sqlite
	deleteTableValue("TIEP_DON");
	deleteTableValue("BENH_NHAN");
	//alert("1");
	var version = document.getElementById(prefix_component + "__version").value;
	//alert(version);
	if (version!=null && version == 'HOIAN') {
		document.getElementById("KhoTE_div").style.display = "none";
		document.getElementById("KhoLe_div").style.display = "none";
	}
		 
		 
}


