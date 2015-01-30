function onClick(index) {
	//_form:j_id15:0:dvtTen
	opener.document.getElementById(prefix_component + "DM_THUOC").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":dmtTen").innerHTML;
	opener.document.getElementById(prefix_component + "__tonkhoMa").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":tonkhoMa").innerHTML;
	opener.document.getElementById(prefix_component + "__donvi").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":dvtTen").innerHTML;
	opener.document.getElementById(prefix_component + "__tonkho").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":tonkhoTon").innerHTML;
	opener.document.getElementById(prefix_component + "__dongia").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":tonkhoDongia").innerHTML;
	opener.document.getElementById(prefix_component + "__tonkhoMa").value = 
					document.getElementById(prefix_component + "tableTk:" + index + ":tonkhoMa").value;
	opener.document.getElementById(prefix_component + "__xuat").focus();
	self.close();
}

function onBlur(count, index) {
	index = index + 1;
	if (index == count)
		document.getElementById("__tenThuoc0").focus();
	else
		document.getElementById("__tenThuoc" + index).focus();
}

