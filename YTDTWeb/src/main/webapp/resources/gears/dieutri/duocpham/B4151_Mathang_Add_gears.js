function init(){

	setInfo();
}

function setInfo(){	

	document.getElementById(prefix_component + '__ten').focus();
}

function getMaThuocformTenThuoc(myTenThuoc) {
	
	var s1 = "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ";
	var s2 = "AAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";

	var s2Arr = s2.split("");
	var TenThuoc = myTenThuoc;
	
	TenThuoc = trim(TenThuoc);
	
	var TenThuocArr = TenThuoc.split(" ");
	var ten = TenThuocArr[0];
	var ma_part1 = "";

	if (TenThuoc != "") {
		if (ten.length > 2) {
			ma_part1 = ten.substr(0, 3);
		} else if (ten.length == 2) {
			ma_part1 = ten + "-";
		} else if (ten.length == 1) {
			ma_part1 = ten + "--";
		}
	}

	ma_part1 = ma_part1.toUpperCase();

	var ma_part1_arr = ma_part1.split("");

	var ma_part1_ok = "";
	for ( var i = 0; i < ma_part1_arr.length; i++) 
	{
		if (s1.indexOf(ma_part1_arr[i]) >= 0) {
			ma_part1_ok += s2Arr[s1.indexOf(ma_part1_arr[i])];
		} else {
			ma_part1_ok += ma_part1_arr[i];
		}
	}
	
	return ma_part1_ok;
}

function trim(str) {
	while (str.substring(0, 1) == ' ') {
		str = str.substring(1, str.length);
	}
	while (str.substring(str.length - 1, str.length) == ' ') {
		str = str.substring(0, str.length - 1);
	}
	return str;
}