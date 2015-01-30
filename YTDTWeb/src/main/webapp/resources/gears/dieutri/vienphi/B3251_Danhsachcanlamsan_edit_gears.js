function init(){
	
	setInfo();
}

function setInfo(){
	document.getElementById(prefix_component + '__ten').className = 'focus';
	document.getElementById(prefix_component + '__ten').focus();
}

function iesvn_Required() {
	this.aa = new Array(prefix_component + "__ma", "Chưa nhập mã cận lâm sàn", new Function ("varName",  "return this[varName];"));
	this.ab = new Array(prefix_component + "__ten", "Chưa nhập tên cận lâm sàn", new Function ("varName",  "return this[varName];"));
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