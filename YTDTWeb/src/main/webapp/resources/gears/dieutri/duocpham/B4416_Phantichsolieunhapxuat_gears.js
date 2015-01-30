function init() {
	if (window.google && google.gears) {
		try {
            
            setAttrForCombobox(prefix_component + "DMKHO_MASO", "DM_KHO_span", "DT_DM_KHO", "getDtDmKho()", "", "", "");
            
        } catch (e) {
        	alert("error in init(): " + e.description);
        }
    }
}
