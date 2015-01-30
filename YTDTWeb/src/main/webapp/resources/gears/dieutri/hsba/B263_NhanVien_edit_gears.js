function init(){
	
	setInfo();
}

function setInfo(){
	//document.getElementById(prefix_component + '__ten').focus();
	//document.getElementById(prefix_component + '__ten').className = 'focus';
}

function iesvn_Required() {
	this.aa = new Array(prefix_component + "__ma", "Chưa nhập mã nhân viên", new Function ("varName",  "return this[varName];"));
	this.ab = new Array(prefix_component + "__ten", "Chưa nhập tên nhân viên", new Function ("varName",  "return this[varName];"));
}

function iesvn_FloatValidations(){
}

function checkInput(){
	var form = document.forms[0];
	var valid = true;
	valid = iesvn_ValidateRequired(form);	

	if(valid == true){
		valid = iesvn_ValidateFloat(form);
	}

	return valid;
}

function oncompleteGhinhan(){
	document.getElementById(prefix_component + '__ten').focus();
}

function oncompleteNhapmoi(){
	document.getElementById(prefix_component + '__ten').focus();
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

function getMaNVformFullName(myFullName) {
	var s1 = "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ";
	var s2 = "AAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";
	var s2Arr = s2.split("");
	var hoTen = myFullName;
	hoTen = trim(hoTen);
	var hoTenArr = hoTen.split(" ");
	var ten = hoTenArr[(hoTenArr.length) - 1];
	var ma_part1 = "";

	if (hoTen != "") {
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
	for ( var i = 0; i < ma_part1_arr.length; i++) {
		if (s1.indexOf(ma_part1_arr[i]) >= 0) {
			ma_part1_ok += s2Arr[s1.indexOf(ma_part1_arr[i])];
		} else {
			ma_part1_ok += ma_part1_arr[i];
		}
	}

	return ma_part1_ok;
}