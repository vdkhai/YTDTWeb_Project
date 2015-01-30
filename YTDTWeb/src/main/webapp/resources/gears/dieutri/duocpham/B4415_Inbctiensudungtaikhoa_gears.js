function init() {
	if (window.google && google.gears) {
		//try {            
            setAttrForCombobox(prefix_component + "LOAIPHIEU_MA","DM_LOAI_THUOC_span","DM_LOAI_THUOC","getDmLoaiThuoc()","","","");
            setAttrForCombobox(prefix_component + "PHANLOAI_MA","DM_PHAN_LOAI_THUOC_span","DM_PHAN_LOAI_THUOC","getDmPhanLoaiThuocByLoaiThuoc(\"" + prefix_component + "LOAIPHIEU_MA_pk" + "\")","","","");
            setAttrForCombobox(prefix_component + 'DMKHOA','DM_KHOA_span', 'DM_KHOA', "getDmKhoa_NoiTru_NgoaiTru()", "", "", "");
            setAttrForCombobox(prefix_component + "DOITUONG_MA","DM_DOI_TUONG_span","DM_DOI_TUONG", "getDmDoiTuongTP()", "", "", "");
            setTimeout(function() {
          		setValueOnLoad();
          	}, 100);
          	
        //} catch (e) {
        //	alert("error in init(): " + e.description);
        //}
    }
}
function setValueOnLoad(){
	document.getElementById(prefix_component + "__thang").focus();	
}
function myOncomplete(){
	myOnblurTextbox(prefix_component + "LOAIPHIEU_MA", "DM_LOAI_THUOC");
	myOnblurTextbox(prefix_component + "PHANLOAI_MA", "DM_PHAN_LOAI_THUOC");
	myOnblurTextbox(prefix_component + "DMKHOA", "DM_KHOA");
}
