var isUpdate = false;

function init() {

	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');
			setAttrForCombobox(prefix_component + "DTDMLOAI_MA", "LOAI_span", "DM_LOAI_THUOC", "getDmLoaiThuoc()", "", "", "");
			setAttrForCombobox(prefix_component + "DTDMNHANVIEN_CN", "DT_DM_NHAN_VIEN_span1", "DT_DM_NHAN_VIEN__1", "getDtDmNhanVien_Duoc()", "", "", "");
            setAttrForCombobox(prefix_component + "DTDMNHANVIEN_PHAT", "DT_DM_NHAN_VIEN_span2", "DT_DM_NHAN_VIEN__2", "getDtDmNhanVien_Duoc()", "", "", "");
            setAttrForCombobox(prefix_component + "DMKHOA_MASO", "DM_KHOA_span", "DM_KHOA", "getDmKhoaPhongDTNhom12()", "", "", "");
            
            setTimeout(function () {
            	setValueOnLoad();
            }, 200);
            initShortcut();
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
    
}

function setValueOnLoad(){
	myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
	myOnblurTextbox(prefix_component + "DMKHOA_MASO", 'DM_KHOA');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_CN", 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_PHAT", 'DT_DM_NHAN_VIEN__2');
}

function initShortcut() {
	shortcut.add('Ctrl+Shift+t',function() {document.getElementById(prefix_component + '__timkiem').click();});
	shortcut.add('Ctrl+Shift+n',function() {document.getElementById(prefix_component + '__lammoi').click();});
}


function onCompleteLoadPN() {
	myOnblurTextbox(prefix_component + "DTDMLOAI_MA", 'DM_LOAI_THUOC');
	myOnblurTextbox(prefix_component + "DMKHOA_MASO", 'DM_KHOA');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_CN", 'DT_DM_NHAN_VIEN__1');
	myOnblurTextbox(prefix_component + "DTDMNHANVIEN_PHAT", 'DT_DM_NHAN_VIEN__2');
}

	
		
