function init() {
    if (window.google && google.gears) {
        try {
        	setAttrForCombobox(prefix_component + 'DMDOITUONG_MA_1','DM_DOI_TUONG_span1', 'DM_DOI_TUONG__1', "getDmDoiTuong()", "", "", "");
        	setAttrForCombobox(prefix_component + 'DMDOITUONG_MA_2','DM_DOI_TUONG_span2', 'DM_DOI_TUONG__2', "getDmDoiTuong()", "", "", "");
        	setAttrForCombobox(prefix_component + 'DMKHOA_1','DM_KHOA_span1', 'DM_KHOA__1', "getDmKhoa()", "", "", "");
        	setAttrForCombobox(prefix_component + 'DMKHOA_2','DM_KHOA_span2', 'DM_KHOA__2', "getDmKhoa()", "", "", "");
        	setAttrForCombobox(prefix_component + "DM_PHAN_LOAI_TAI_NAN_MA","DM_PHAN_LOAI_TAI_NAN_span","DM_PHAN_LOAI_TAI_NAN","getDmPhanLoaiTaiNan()","","","");
        	setAttrForCombobox(prefix_component + "BENHICD_MA","DM_BENH_ICD_span","DM_BENH_ICD","getDmBenhIcd()","","","");
        	//setAttrForCombobox(prefix_component + 'DMKETQUADIEUTRI_MA','DM_KET_QUA_DIEU_TRI_span', 'DM_KET_QUA_DIEU_TRI', "getDmKetQuaDieuTri()", "", "", "");
        } catch (e) {
        	alert("init error: " + e.description);
        }
    }    
}

