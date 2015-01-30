function init() {
	if (window.google && google.gears) {
		var maphu = opener.document.getElementById(prefix_component + "__maphu_in").value;
		//alert("ma phu: " + maphu);
		var td = TiepDon.getByFieldValue("TIEP_DON_MAPHU", maphu);
		if (td) {
			document.getElementById(prefix_component + "__stt").innerHTML = td.TIEPDON_THUTU;
			var bk = DtDmBanKham.getByFieldValue("Ma", td.TIEPDON_BANKHAM);
			document.getElementById(prefix_component + "__bankham").value = bk.Ten;
			var dt = DmDoiTuong.getByFieldValue("Ma", td.DOITUONG_MA);
			document.getElementById(prefix_component + "__doituong").value = dt.Ten;
			var clsBg = DtDmClsBangGia.getByFieldValue("Ma", td.TIEPDON_LOAIKHAM);
			document.getElementById(prefix_component + "__loai").value = clsBg.Ten;
			document.getElementById(prefix_component + "__ngay").value = td.TIEPDON_NGAYGIO;
		}
    }
}