var khong_tim_thay_du_lieu = "Không tìm thấy dữ liệu";
var can_dang_nhap_lai = "Cần đăng nhập lại";
function init() {

    if (window.google && google.gears) {
    	
        try {
        	TamUngKham.createTable();
             
            //setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1",prefix_component +"DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");
            timer.setTimeout(function(){setValueOnLoad();},100); 
             
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

function setValueOnLoad(){
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", prefix_component + "DT_DM_NHAN_VIEN__1");
	document.getElementById(prefix_component + 'TIEPDON_MA').focus();
	
	
	
}
function setValueOnComplete(){

	var ngaysinh = document.getElementById(prefix_component + "BENHNHAN_NGAYSINH").value;
	var namsinh = document.getElementById(prefix_component + "__namsinhHid").value;
	
	if (ngaysinh == null || ngaysinh == ''){
		
		document.getElementById(prefix_component + "BENHNHAN_NGAYSINH").value = namsinh;
	}
}

initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__tieptucnhap').click();});	
}
function updateInfo() {
	var MAPHU_hidden = document.getElementById(prefix_component + "MAPHU").value;
	updateTable(prefix_component,TamUngKham,"MAPHU", MAPHU_hidden, "TAMUNGKHAM_MA");
	
}
//---Process error results(receive from server)---
function displayErrorSendingResults(tableClass){
	displayErrorSave(tableClass, "Tạm ứng bị lỗi", "MAPHU", "MAPHU");
}
//---Load info into form---
function loadDB_fromMaPhu(myMaPhu){
	loadDB_fromUniqueField(prefix_component, TamUngKham,"MAPHU", myMaPhu);
	document.getElementById(prefix_component + 'TIEPDON_MA').setActive();
	document.getElementById(prefix_component + 'TIEPDON_MA').blur();
}
//---Load info into form---
function deleteTable(tableClass,maPhu_FieldName, myMaPhu){
	try{       
        var ctnk = tableClass.filter(maPhu_FieldName + " = ?", myMaPhu).remove();
		                                
    }
    catch(e){}    
}
function updateTable(prefix_input,tableClass,maPhu_FieldName,myMaPhu, primary_FieldName) {
	
	var MAPHU_hidden = document.getElementById(prefix_input + maPhu_FieldName).value;
	var tableObj = tableClass.getByFieldValue(maPhu_FieldName, myMaPhu);
	//if(iesvn_ValidateForm(document.forms[0])) 
	{
		var arrayNames = new Array([maPhu_FieldName]);
		var arrayValues = new Array([MAPHU_hidden]);
		if ( tableObj == false) {
			insertObject(tableClass,arrayNames,arrayValues);
		} else { // Neu primary != null  thi MAPHU_hidden phai bang primary	
			updateObject(tableClass,primary_FieldName,MAPHU_hidden);
		}
	}
}

function displayErrorSave(tableClass, tableTitle, uniqueFieldName, displayFieldName){
	 var objectArray;
                 
	try{
    	objectArray =   tableClass.all().toArray();                                          
    }
    catch(e){}
    
    var myHtml = "";
    myHtml += "<div style='width:100%; text-align:center; background:#ffffcc; color:#ff0000; font-weight:bold'>" + tableTitle + "</div>";
    myHtml += "<div style='border:solid 1px #ffffcc; width:99%; overflow:auto; height:35px; background:#ECE9D8'>";
    myHtml += "<table style='background:#ECE9D8; color:#ff0000' border='0' width='91%' cellspacing='0' cellpadding='0'>";
    //i = 0;
    for (var  i = 0; i < objectArray.length; i ++) {
       myHtml += "<tr><td style='padding-left:10px'><a href='#' onclick='loadDB_fromMaPhu(" + objectArray[i][uniqueFieldName] + ")'>" + objectArray[i][displayFieldName] + "</a></td></tr>";
    }
    myHtml += "</table>";
    myHtml += "</div>";
    
    if(i == 0)
    	document.getElementById("sync_error_display").innerHTML = "";
    else	
	    document.getElementById("sync_error_display").innerHTML = myHtml;	
}

function loadDB_fromUniqueField(prefix_input,tableClass,maPhu_FieldName,myMaPhu){	
	//var pnk = TamUng.getByFieldValue("MAPHU", myMaPhu);
	var tableObj = tableClass.getByFieldValue(maPhu_FieldName, myMaPhu);
	var fieldNames = tableClass.getFieldNames();
	
	for(var i=0;i<fieldNames.length;i++){
		try {
		var v = tableObj[fieldNames[i]];		
		document.getElementById(prefix_input + fieldNames[i]).value = (v == null || v == 'null') ? "" : v;
		}catch(e) {}
	}
	
}	


function onCompleteGetInfor(){
	try { 
	
		myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', prefix_component+'DT_DM_NHAN_VIEN__1');
	
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}