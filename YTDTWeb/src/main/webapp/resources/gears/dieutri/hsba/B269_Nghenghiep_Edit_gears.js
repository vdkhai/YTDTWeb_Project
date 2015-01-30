function init(){
	setInfo();
}

function setInfo(){	
	document.getElementById(prefix_component + '__ma').focus();
}

function iesvn_Required() {
	this.aa = new Array(prefix_component + "__ma", "<h:outputFormat value='#{msg.require_field}' > <f:param value='Mã nghề nghiệp'/> </h:outputFormat>", new Function ("varName",  "return this[varName];"));
	this.ab = new Array(prefix_component + "__ten", "<h:outputFormat value='#{msg.require_field}' > <f:param value='Tên nghề nghiệp'/> </h:outputFormat>", new Function ("varName",  "return this[varName];"));
}

function checkInput(){
	var form = document.forms[0];
	var valid = true;
	valid = iesvn_ValidateRequired(form);	
    return valid;
}

function oncompleteGhinhan(){
	var result = document.getElementById(prefix_component + '__result').value;
	var ma = document.getElementById(prefix_component + '__ma').value;
	if (result == "true"){
		alert("<h:outputFormat value='#{msg.nghenghiep_cr_su}' > <f:param value='" + ma + "'/> </h:outputFormat>");
	}else{
		alert("<h:outputFormat value='#{msg.nghenghiep_cr_fa}' > <f:param value='" + ma + "'/> </h:outputFormat>");
	}
	document.getElementById(prefix_component + '__ma').focus();
}

function oncompleteNhapmoi(){
	document.getElementById('DM_NGHENGHIEP').value="";
	document.getElementById(prefix_component + '__ma').focus();
}