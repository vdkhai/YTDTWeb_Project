function init() {
    if (window.google && google.gears) {
        //try {
            	setAttrForCombobox(prefix_component + "HOANUNG_KHOA","DM_KHOA_span",prefix_component + "DM_KHOA","getDmKhoa_NoiTru()","","",""); 
            	//setAttrForCombobox(prefix_component + "DT_DM_NHAN_VIEN_MA_1","DT_DM_NHAN_VIEN_span1",prefix_component + "DT_DM_NHAN_VIEN__1","getDtDmNhanVienByMaKhoa_ThuNgan()","","","");
                timer.setTimeout(function(){setValueOnLoad();},100); 	
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
}
function setValueOnLoad(){
	//myOnblurTextbox(prefix_component + "DT_DM_NHAN_VIEN_MA_1", prefix_component + "DT_DM_NHAN_VIEN__1");
	
	document.getElementById(prefix_component + "HOANUNG_KHOA").focus();
}
initShortcut();
function initShortcut() {
	shortcut.add('Ctrl+Shift+k',function() {document.getElementById(prefix_component + '__ghinhan').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__tieptucnhap').click();});	
}

function onCompleteGetInfor(){
	try { 
	
		//myOnblurTextbox(prefix_component + 'DT_DM_NHAN_VIEN_MA_1', prefix_component+'DT_DM_NHAN_VIEN__1');
		
		
	} catch(e) {
		alert("onCompleteGetInfor()" + e.description);
	}
}


function getLiDo()
{
	var tenkhoa = document.getElementById(prefix_component + "DM_KHOA").value;
	if(tenkhoa!=null && tenkhoa!="")
	{
		document.getElementById(prefix_component + "HOANUNG_LYDO").value = tenkhoa + " trả bớt tiền tạm ứng";
	}
	else
	{
		document.getElementById(prefix_component + "HOANUNG_LYDO").value = "";
	}
}