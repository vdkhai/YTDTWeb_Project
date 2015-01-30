var khong_tim_thay_du_lieu = "Không tìm thấy dữ liệu";
var can_dang_nhap_lai = "Cần đăng nhập lại";
function init() {

	
    if (window.google && google.gears) {
    	
        //try {
        	MienGiam.createTable();
           	setAttrForCombobox(prefix_component + "MIENGIAM_KHOA","DM_KHOA_span",prefix_component + "DM_KHOA","getDmKhoa_NoiTru()","","","");
           	setAttrForCombobox(prefix_component + "MIENGIAM_LOAIMIEN","DT_DM_LOAI_MIEN_span",prefix_component + "DT_DM_LOAI_MIEN","getDtDmLoaiMien()","","enableInputsTheoLoaigiam();tabKhiLoaiMienChoSoTien()","");
           	setAttrForCombobox(prefix_component + "MIENGIAM_NGUOIDUYET","DT_DM_NHAN_VIEN_span",prefix_component + "DT_DM_NHAN_VIEN","getDtDmNhanVien()","","","");
           	setAttrForCombobox(prefix_component + "MIENGIAM_DOITUONG","DT_DM_DIEN_MIEN_span",prefix_component + "DT_DM_DIEN_MIEN","getDtDmDienMien()","","","");
            
            //setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1",prefix_component +  "DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");
                 timer.setTimeout(function(){setValueOnLoad();},1000);
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
    
}

function enableInputsTheoLoaigiam() {
			//var loaigiam = document.getElementById(prefix_component + 'MIENGIAM_LOAIMIEN_pk').value;
			var loaigiam = document.getElementById(prefix_component + 'MIENGIAM_LOAIMIEN').value;
			if(loaigiam == '3') {
				document.getElementById(prefix_component + 'MIENGIAM_SOTIEN').disabled = false;
				document.getElementById(prefix_component + 'MIENGIAM_NGAYD').disabled = true;
				document.getElementById('a_MIENGIAM_NGAYD').onclick  = "";
				document.getElementById('a_MIENGIAM_NGAYC').onclick = "";
				
				document.getElementById(prefix_component + 'MIENGIAM_NGAYC').disabled = true;
				document.getElementById(prefix_component + 'MIENGIAM_TYLE').disabled = true;
				
				document.getElementById(prefix_component + 'MIENGIAM_NGAYD').value = '';
				document.getElementById(prefix_component + 'MIENGIAM_NGAYC').value = '';
				document.getElementById(prefix_component + 'MIENGIAM_TYLE').value = '';
			} else if(loaigiam == '2') {
				document.getElementById(prefix_component + 'MIENGIAM_SOTIEN').disabled = true;
				document.getElementById(prefix_component + 'MIENGIAM_NGAYD').disabled = true;
				document.getElementById('a_MIENGIAM_NGAYD').onclick  = "";
				document.getElementById('a_MIENGIAM_NGAYC').onclick = "";
				
				document.getElementById(prefix_component + 'MIENGIAM_NGAYC').disabled = true;
				document.getElementById(prefix_component + 'MIENGIAM_TYLE').disabled = false;
				
				document.getElementById(prefix_component + 'MIENGIAM_NGAYD').value = '';
				document.getElementById(prefix_component + 'MIENGIAM_NGAYC').value = '';
				document.getElementById(prefix_component + 'MIENGIAM_TYLE').value = '100';
				
				
			} else {
				document.getElementById(prefix_component + 'MIENGIAM_SOTIEN').value = '';
				document.getElementById(prefix_component + 'MIENGIAM_SOTIEN').disabled = true;
				document.getElementById(prefix_component + 'MIENGIAM_NGAYD').disabled = false;
				document.getElementById(prefix_component + 'MIENGIAM_NGAYC').disabled = false;
				document.getElementById(prefix_component + 'MIENGIAM_TYLE').disabled = false;
				document.getElementById('a_MIENGIAM_NGAYD').onclick=function () {gfPop.fPopCalendar(document.getElementById(prefix_component + 'MIENGIAM_NGAYD'));document.getElementById(prefix_component + 'MIENGIAM_NGAYD').setActive();return false;};
				document.getElementById('a_MIENGIAM_NGAYC').onclick= function () {gfPop.fPopCalendar(document.getElementById(prefix_component + 'MIENGIAM_NGAYC'));document.getElementById(prefix_component + 'MIENGIAM_NGAYC').setActive();return false;};
			}
		}
		
function setValueOnLoad(){
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", prefix_component +  "DT_DM_NHAN_VIEN__1");
	document.getElementById(prefix_component + "MIENGIAM_KHOA").focus();
}

initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__tieptucnhap').click();});	
}
function tabKhiLoaiMienChoSoTien() {
	if(document.getElementById(prefix_component + 'MIENGIAM_LOAIMIEN').value == '3') {
		iesvn_Tab2Id(prefix_component + 'MIENGIAM_SOTIEN');
	}
}
//---Load info into form---
function loadDB_fromUniqueField(prefix_input,tableClass,maPhu_FieldName,myMaPhu){	
	//var pnk = MienGiam.getByFieldValue("MAPHU", myMaPhu);
	var tableObj = tableClass.getByFieldValue(maPhu_FieldName, myMaPhu);
	var fieldNames = tableClass.getFieldNames();
	
	for(var i=0;i<fieldNames.length;i++){
		try {
		var v = tableObj[fieldNames[i]];
		document.getElementById(prefix_input + fieldNames[i]).value = (v == null || v == 'null') ? "" : v;
		}catch(e) {}
	}
}
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
//---Load info into form---
function loadDB_fromMaPhu(myMaPhu){
	loadDB_fromUniqueField(prefix_component, MienGiam,"MAPHU", myMaPhu);
	document.getElementById(prefix_component + 'MIENGIAM_KHOA').setActive();
	document.getElementById(prefix_component + 'HSBA_SOVAOVIEN').setActive();
	document.getElementById(prefix_component + 'HSBA_SOVAOVIEN').blur();	
}
function updateInfo() {
	var MAPHU_hidden = document.getElementById(prefix_component + "MAPHU").value;
	updateTable(prefix_component,MienGiam,"MAPHU", MAPHU_hidden, "MIENGIAM_MA");	
}
//---Process error results(receive from server)---
function displayErrorSendingResults(tableClass){
	displayErrorSave(tableClass, "Miễn giảm bị lỗi", "MAPHU", "MAPHU");
    	
}
