function init() {
    if (window.google && google.gears) {
        try {
        	//db = google.gears.factory.create('beta.database');
            //setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            setAttrForCombobox(prefix_component + "DM_KHOA_MA","DM_KHOA_span","DM_KHOA","getDmKhoa_NoiTru()","","",""); 
            //setAttrForCombobox(prefix_component + 'DM_THUOC_MASO','DM_THUOC_span', 'DM_THUOC', "getDmThuoc()", "", "", "");
            document.getElementById(prefix_component + "__ngaycn").focus();            
            
            //Thuc hien phim tat giup user thuan tien add thuoc hay chon thuoc
            initshorcut();
        } catch (e) {
        	alert("init error: " + e.description);
        }
	}
}

function initshorcut(){
    shortcut.add("Ctrl+F", function() {
    	document.getElementById(prefix_component + "__ngaycn").focus();
    },{'type':'keydown',"propagate":false,'target':document});
}

function onCompleteLoadHsba() {
    //alert(1);
	myOnblurTextbox(prefix_component + "DM_KHOA_MA", 'DM_KHOA');
	//alert(1);
}

function setSoLuongFocus(){
	highlightOnFocus();
	document.getElementById(prefix_component + '__sudung').focus();
}

function setSoluongField(){
	alert("Vui lòng nhập số lượng thuốc trả của bệnh nhân chính xác.");
	changeCursorDefault ();
	document.getElementById(prefix_component + "__sochia").focus();
}

function onCompleteTiepTucNhap() {
	document.getElementById(prefix_component + "__maso").value = "";
	document.getElementById(prefix_component + "__tenhang").value = "";
	document.getElementById(prefix_component + "__donvi").value = "";
	document.getElementById(prefix_component + "__dongia").value = "";
	document.getElementById(prefix_component + "__soluong").value = "";
}

function onCompleteSelect() {
	var maThuoc = document.getElementById(prefix_component + "dmt").value;
	if (maThuoc != "") {
		var thuoc = DmThuoc.getByFieldValue("Ma", maThuoc);
		if (thuoc) {
			document.getElementById(prefix_component + "__maso").value = thuoc.Ma;
			document.getElementById(prefix_component + "__tenhang").value = thuoc.Ten;
			var donvi = DmDonViTinh.getByFieldValue("MaSo", thuoc.DMDONVITINH_MASO);
			if (donvi) {
				document.getElementById(prefix_component + "__donvi").value = thuoc.Ma;
			}
			document.getElementById(prefix_component + "__dongia").value = document.getElementById(prefix_component + "dg").value;
			document.getElementById(prefix_component + "__soluong").value = document.getElementById(prefix_component + "soluong").value;
		}
		
	} else {
		document.getElementById(prefix_component + "__maso").value = "";
		document.getElementById(prefix_component + "__tenhang").value = "";
		document.getElementById(prefix_component + "__donvi").value = "";
		document.getElementById(prefix_component + "__dongia").value = "";
		document.getElementById(prefix_component + "__soluong").value = "";
	}
	
}

function onCompleteReset() {
	myOnblurTextbox(prefix_component + "DM_KHOA_MA", "DM_KHOA");
	onCompleteSelect();
}