function init() {
	if (window.google && google.gears) {
		try {
			timer = google.gears.factory.create('beta.timer');
            setAttrForCombobox(prefix_component + "DMKHO_MASO", "DM_KHO_span", "DT_DM_KHO", "getDtDmKho()", "", "", "");
            setAttrForCombobox(prefix_component + "DMKINHPHI_MASO", "KINH_PHI_span", "DM_NGUON_KINH_PHI", "getDmNguonKinhPhi()", "", "", "");
            setAttrForCombobox(prefix_component + "DTDMNGUON_MA", "NGUON_span", "DM_NGUON_CHUONG_TRINH", "getDmNguonChuongTrinh()", "", "", "");
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
}