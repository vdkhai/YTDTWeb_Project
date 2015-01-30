JStORM.connections = 
{
	"default":
	{
		PROVIDER:"Gears",
		DIALECT:"SQLite",
		PATH:"test_simple",
		HOST:"database-Quan_Ly_Y_Te"
	}
};

var MAX_RESULT = 500;

var DtDmLoiDan = new JStORM.Model({
	name: "DT_DM_LOI_DAN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DtDmLoiDan.prototype.getDataList = function() {
	var objArr = DtDmLoiDan.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmPhong = new JStORM.Model({
	name: "DT_DM_PHONG",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmPhong.prototype.getDataList = function() {
	var objArr = DtDmPhong.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmMucAn = new JStORM.Model({
	name: "DT_DM_MUC_AN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmMucAn.prototype.getDataList = function() {
	var objArr = DtDmMucAn.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmLoaiAn = new JStORM.Model({
	name: "DT_DM_LOAI_AN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmLoaiAn.prototype.getDataList = function() {
	var objArr = DtDmLoaiAn.filter("DT = 1 ").toArray();	
	return objArr;
};

var DtDmLoaiAn2 = new JStORM.Model({
	name: "DT_DM_LOAI_AN2",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		MaSo2: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmLoaiAn2.prototype.getDataList = function() {
	//var objArr = DtDmLoaiAn2.filter("DT = 1 ").toArray();
	var objArr = DtDmLoaiAn2.filter("MaSo2 = " + this.MaSo2 ).toArray();
	return objArr;
};

var DtDmCheDoAn = new JStORM.Model({
	name: "DT_DM_CHE_DO_AN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DtDmCheDoAn.prototype.getDataList = function() {
	var objArr = DtDmCheDoAn.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmDoiTuongAn = new JStORM.Model({
	name: "DT_DM_DOI_TUONG_AN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DtDmDoiTuongAn.prototype.getDataList = function() {
	var objArr = DtDmDoiTuongAn.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmDongThem = new JStORM.Model({
	name: "DT_DM_DONG_THEM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DtDmDongThem.prototype.getDataList = function() {
	var objArr = DtDmDongThem.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmNhaSxSpdd = new JStORM.Model({
	name: "DT_DM_NHASX",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmNhaSxSpdd.prototype.getDataList = function() {
	var objArr = DtDmNhaSxSpdd.filter("DT = 1 ").toArray();	
	return objArr;
};

var DtDmLoaiTp = new JStORM.Model({
	name: "DT_DM_LOAITP",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmLoaiTp.prototype.getDataList = function() {
	var objArr = DtDmLoaiTp.filter("DT = 1 ").toArray();	
	return objArr;
};

// khai bao bang DT_DM_CAP_CUU_PHIEN
var DtDmCapCuuPhien = new JStORM.Model({
	name: "DT_DM_CAP_CUU_PHIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DtDmCapCuuPhien.prototype.getDataList = function() {
	var objArr = DtDmCapCuuPhien.filter("DT = 1").toArray();	
	return objArr;
};

/*Thanh do add here for default value for client*/
var DtDmClientDefault = new JStORM.Model({
	name: "DT_DM_CLIENT_DEFAULT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


var CauHinh = new JStORM.Model({
	name: "CAU_HINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),		
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),		
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
CauHinh.prototype.getDataList = function() {
	var objArr = null;	
	if(countRecord(CauHinh.getTableName(),"")){
		objArr = CauHinh.filter("DT = 1").toArray();
	}	
	return objArr;
};


// khai bao bang DM_BENH_VIEN
var DmBenhVien = new JStORM.Model({
	name: "DM_BENH_VIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMTUYEN_MASO: new JStORM.Field({ type: "Integer"}),
		DMLOAIBV_MASO: new JStORM.Field({ type: "Integer"}),
		DMVUNGMIEN_MASO: new JStORM.Field({ type: "Integer"}),
		DMTINH_MASO: new JStORM.Field({ type: "Integer"}),
		DMHUYEN_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMBENHVIEN_DIENTHOAI: new JStORM.Field({ type: "String", maxLength: 10}),
		DMBENHVIEN_DIACHI: new JStORM.Field({ type: "String", maxLength: 250}),
		DMBENHVIEN_CHUYENDI: new JStORM.Field({ type: "Integer"}),
		DMBENHVIEN_CHUYENDEN: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DMBENHVIEN_DEFAULT: new JStORM.Field({ type: "Integer" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmBenhVien.prototype.getDataList = function() {
	var objArr = null;	
	if(countRecord(DmBenhVien.getTableName(),"")){
		objArr = DmBenhVien.filter("DT = 1").toArray();
	}	
	return objArr;
};

DmBenhVien.prototype.getDataList_search = function() {
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	//alert(1);
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and DT = 1";
	var objArr = null;
	//alert(condition);
	//alert(countRecord(DmBenhVien.getTableName(),condition));
	if(countRecord(DmBenhVien.getTableName(),condition)){
		objArr = DmBenhVien.filter(condition).toArray();
		//alert(objArr);
	}	
	//alert(objArr.length);
	return objArr;
};

DmBenhVien.prototype.getDataList_search_chan_doan = function() {
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and DT = 1";
	var objArr = null;
	if(countRecord(DmBenhVien.getTableName(),condition)){
		objArr = DmBenhVien.filter(condition).toArray();
	}	
	return objArr;
};

DmBenhVien.prototype.getDataList_search_with_chuyen_di = function() {
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' AND DMBENHVIEN_CHUYENDI = 1 AND DT = 1";
	
	var objArr = null;
	
	if(countRecord(DmBenhVien.getTableName(),condition)){
		objArr = DmBenhVien.filter(condition).toArray();
	}	
	return objArr;
};


DmBenhVien.prototype.getDataList_search_with_tinh = function(matinhbhyt) {
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	// tim ma tinh -> maso
	var objArr = DmTinh.filter("MaTinhBHYT = \'" + matinhbhyt + "\'").toArray();
	var masotinh = null;
	if (objArr != null && objArr.length > 0){
		var tinh = objArr[0];
		masotinh = objArr[0].MaSo;
	}
	
	//alert(matinhbhyt);
	//alert(masotinh);
	
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and DT = 1 and DMTINH_MASO = " + masotinh ;
	var objArr = null;
	//alert(condition);
	//alert(countRecord(DmBenhVien.getTableName(),condition));
	if(countRecord(DmBenhVien.getTableName(),condition)){
		objArr = DmBenhVien.filter(condition).toArray();
		//alert(objArr);
	}	
	//alert(objArr.length);
	return objArr;
};

var DmBenhVN = new JStORM.Model({
	name: "DM_BENH_VN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMBENHVN_MACHUNG: new JStORM.Field({ type: "String", maxLength: 50}),
		DMCHUONGBENH_MASO: new JStORM.Field({ type: "Integer"}),
		DMBENHVN_MAICD10: new JStORM.Field({ type: "String", maxLength: 7}),
		DMBENHVN_MABYT: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMBENHVN_MAICD10B: new JStORM.Field({ type: "String", maxLength: 250}),
		DMBENHVN_BAOGOM: new JStORM.Field({ type: "String", maxLength: 5}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmBenhVN.prototype.getDataList = function() {
	var objArr = DmBenhVN.filter("DT = 1").toArray();	
	return objArr;
};
//
var DmTaiNan = new JStORM.Model({
	name: "DM_TAI_NAN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmTaiNan.prototype.getDataList = function() {
	var objArr = DmTaiNan.filter("DT = 1").toArray();	
	return objArr;
};
//
var DmNhomHocVi = new JStORM.Model({
	name: "DM_NHOM_HOC_VI",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		DMNHOMHOCVI_DAIHOC: new JStORM.Field({ type: "Integer" }),
		DMNHOMHOCVI_TENBC: new JStORM.Field({ type: "String", maxLength: 250 }),
		DMNHOMHOCVI_THUTU: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmNhomHocVi.prototype.getDataList = function() {
	var objArr = DmNhomHocVi.filter("DT = 1").toArray();	
	return objArr;
};

var DmNhomKhoa = new JStORM.Model({
	name: "DM_NHOM_KHOA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmNhomKhoa.prototype.getDataList = function() {
	var objArr = DmNhomKhoa.filter("DT = 1").toArray();	
	return objArr;
};

//
var DmCachDungThuoc = new JStORM.Model({
	name: "DM_CACH_DUNG_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMCACHDUNGTHUOC_MAPHU: new JStORM.Field({ type: "String", maxLength: 4}),
		Ten: new JStORM.Field({ type: "String", maxLength: 36}),
		NGAYCHINHSUA: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmCachDungThuoc.prototype.getDataList = function() {
	var objArr = DmCachDungThuoc.filter("DT = 1").toArray();	
	return objArr;
};

var DmNhomBaoCaoLoaiThuoc = new JStORM.Model({
	name: "DM_NHOM_BAO_CAO_LOAI_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		DMNHOMBCTHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		DMLOAITHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmNhomBaoCaoLoaiThuoc.prototype.getDataList = function() {
	var objArr = DmNhomBaoCaoLoaiThuoc.filter("DT = 1").toArray();	
	return objArr;
};
//
var DmNhomBaoCaoThuoc = new JStORM.Model({
	name: "DM_NHOM_BAO_CAO_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMNHOMBCTHUOC_MAPHU: new JStORM.Field({ type: "String", maxLength: 2}),
		Ten: new JStORM.Field({ type: "String", maxLength: 36}),
		DMNHOMBCTHUOC_THUTU: new JStORM.Field({ type: "String", maxLength: 6}),
		DMNHOMBCTHUOC_GHICHU: new JStORM.Field({ type: "String", maxLength: 60}),
		DMNHANVIEN_CN: new JStORM.Field({ type: "String", maxLength: 8}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmNhomBaoCaoThuoc.prototype.getDataList = function() {
	var objArr = DmNhomBaoCaoThuoc.filter("DT = 1").toArray();	
	return objArr;
};
//
var DmChuongBenh = new JStORM.Model({
	name: "DM_CHUONG_BENH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMBENHICD_MA: new JStORM.Field({ type: "Integer"}),
		DMCHUONGBENH_TEN: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});


DmChuongBenh.prototype.getDataList = function() {
	var objArr = DmChuongBenh.filter("DT = 1").toArray();	
	return objArr;
};
//
var DtDmChiDan = new JStORM.Model({
	name: "DT_DM_CHI_DAN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DTDMCHIDAN_MAPHU: new JStORM.Field({ type: "String", maxLength: 12}),
		Ten: new JStORM.Field({ type: "String", maxLength: 60}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DtDmChiDan.prototype.getDataList = function() {
	var objArr = DtDmChiDan.filter("DT = 1").toArray();
	//alert(objArr.length);	
	return objArr;
};

var DtDmChiDanDvt = new JStORM.Model({
	name: "DT_DM_CHI_DAN_DVT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		DTDMCHIDAN_MASO: new JStORM.Field({ type: "Integer"}),
		DMDONVITINH_MASO: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DtDmChiDanDvt.prototype.getDataList = function() {
	var objArr = DtDmChiDanDvt.filter("DT = 1").toArray();	
	return objArr;
};

////
var DtDmNhomBhyt = new JStORM.Model({
	name: "DT_DM_NHOM_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),		
		Ten: new JStORM.Field({ type: "String", maxLength: 60}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DtDmNhomBhyt.prototype.getDataList = function() {
	var objArr = DtDmNhomBhyt.filter("DT = 1").toArray();	
	return objArr;
};

////
var DtDmTramYTeBhyt = new JStORM.Model({
	name: "DT_DM_TRAM_Y_TE_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),		
		Ten: new JStORM.Field({ type: "String", maxLength: 60}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DtDmTramYTeBhyt.prototype.getDataList = function() {
	var objArr = DtDmTramYTeBhyt.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_DAN_TOC
var DmDanToc = new JStORM.Model({
	name: "DM_DAN_TOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMDANTOC_DEFA: new JStORM.Field({ type: "Integer"}),
		DMDANTOC_MIENPHI: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DmDanToc.prototype.getDataList = function() {
	var objArr = DmDanToc.filter("DT = 1").toArray();	
	return objArr;
};

//khai bao bang DM_DIA_DIEM
var DmDiaDiem = new JStORM.Model({
	name: "DM_DIA_DIEM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmDiaDiem.prototype.getDataList = function() {
	var objArr = DmDiaDiem.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_DIEU_TRI
var DmDieuTri = new JStORM.Model({
	name: "DM_DIEU_TRI",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMDIEUTRI_DIENGIAI2: new JStORM.Field({ type: "String", maxLength: 250}),
		DMDIEUTRI_DEFA: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmDieuTri.prototype.getDataList = function() {
	var objArr = DmDieuTri.filter("DT = 1").toArray();	
	return objArr;
};

var DmDieuTriHSBA = new JStORM.Model({
	name: "DM_DIEU_TRI_HSBA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),	
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
		
	},
	connection: "default"
}); 
DmDieuTriHSBA.prototype.getDataList = function() {
	var objArr = DmDieuTriHSBA.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_DOI_TUONG
var DmDoiTuong = new JStORM.Model({
	name: "DM_DOI_TUONG",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DMDOITUONG_MAGOM: new JStORM.Field({ type: "String", maxLength: 4}),
		DMDOITUONG_THUTUBC: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DMDOITUONG_CHON1: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_CHON2: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_KHOBHYT: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_KHOLE: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_KHOCHINH: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_BENHAN: new JStORM.Field({ type: "String", maxLength: 1}),
		DMDOITUONG_THUPHI: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_TIEUHAO: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_DINHDUONG: new JStORM.Field({ type: "Integer"}),
		DMDOITUONG_TYLEMIEN: new JStORM.Field({ type: "String", maxLength: 12}),
		DMDOITUONG_TENBC: new JStORM.Field({ type: "String", maxLength: 18}),
		DMDOITUONG_MAUPHIEU: new JStORM.Field({ type: "String", maxLength: 4}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"}),
		QL: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DmDoiTuong.prototype.getDataList = function() {
	var objArr = DmDoiTuong.filter("DT = 1").toArray();	
	return objArr;
};
DmDoiTuong.prototype.getDataListTP = function() {
	var objArr = DmDoiTuong.filter("DMDOITUONG_THUPHI = 1 and DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_DON_VI_TINH
var DmDonViTinh = new JStORM.Model({
	name: "DM_DON_VI_TINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMDONVITINH_DTICH: new JStORM.Field({ type: "String", maxLength: 250}),
		DMDONVITINH_DACDIEM: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmDonViTinh.prototype.getDataList = function() {
	var objArr = DmDonViTinh.filter("DT = 1").toArray();	
	return objArr;
};

var DmHoatChat = new JStORM.Model({
	name: "DM_HOAT_CHAT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMHOATCHAT_ATCCODE: new JStORM.Field({ type: "String", maxLength: 4}),
		Ten: new JStORM.Field({ type: "String", maxLength: 36}),
		DMHOATCHAT_MAPHU: new JStORM.Field({ type: "String", maxLength: 4}),
		DMHOATCHAT_NHOM: new JStORM.Field({ type: "String", maxLength: 4}),
		DMHOATCHAT_MALOAI: new JStORM.Field({ type: "String", maxLength: 4}),
		DMHOATCHAT_PHLOAI: new JStORM.Field({ type: "String", maxLength: 4}),
		DMHOATCHAT_GHICHU: new JStORM.Field({ type: "String", maxLength: 60}),
		NHANVIEN_CN: new JStORM.Field({ type: "String", maxLength: 8}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmHoatChat.prototype.getDataList = function() {
	var objArr = DmHoatChat.filter("DT = 1").toArray();	
	return objArr;
};

var DmHocVi = new JStORM.Model({
	name: "DM_HOC_VI",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMNHOMHOCVI_MASO: new JStORM.Field({ type: "Integer" }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		DMHOCVI_TENBC: new JStORM.Field({ type: "String", maxLength: 250 }),
		DMHOCVI_DAIHOC: new JStORM.Field({ type: "Integer" }),
		DMHOCVI_THUTU: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmHocVi.prototype.getDataList = function() {
	var objArr = DmHocVi.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_HUYEN
var DmHuyen = new JStORM.Model({
	name: "DM_HUYEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 6}),
		DMTINH_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DMHUYEN_DEFA: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmHuyen.prototype.getDataList = function() {
	var objArr = DmHuyen.filter("DMTINH_MASO = '" + this.DMTINH_MASO + "' and DT = 1").toArray();	
	return objArr;
};

var DmKetQuaDieuTri = new JStORM.Model({
	name: "DM_KET_QUA_DIEU_TRI",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 6}),
		Ten: new JStORM.Field({ type: "String", maxLength: 100}),
		DMKQDT_GHICHU: new JStORM.Field({ type: "String", maxLength: 100}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmKetQuaDieuTri.prototype.getDataList = function() {
	var objArr = DmKetQuaDieuTri.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_KHOA
var DmKhoa = new JStORM.Model({
	name: "DM_KHOA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMKHOA_MAKHOAS: new JStORM.Field({ type: "Integer" }),
		DMLOAIKHOA_MA: new JStORM.Field({ type: "Integer" }),
		DMKHOA_CON: new JStORM.Field({ type: "String", maxLength: 3 }),
		DMKHOA_KYHIEU: new JStORM.Field({ type: "String", maxLength: 3 }),
		DMKHOA_MABYT: new JStORM.Field({ type: "Integer" }),
		DMKHOA_MABYT3: new JStORM.Field({ type: "Integer" }),
		DMKHOA_MAKHOADA: new JStORM.Field({ type: "String", maxLength: 3 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 50 }),
		DMKHOA_TEN2: new JStORM.Field({ type: "String", maxLength: 50 }),
		DMKHOA_TENBC: new JStORM.Field({ type: "String", maxLength: 50 }),
		DMNHOMKHOA_MASO: new JStORM.Field({ type: "Integer" }),
		DMKHOA_CLS: new JStORM.Field({ type: "Integer" }),
		DMKHOA_DIENTHOAI: new JStORM.Field({ type: "String", maxLength: 12 }),
		DMKHOA_NOITRU: new JStORM.Field({ type: "Integer" }),
		DMKHOA_KHAM: new JStORM.Field({ type: "Integer" }),
		DMKHOA_NGOAITRU: new JStORM.Field({ type: "Integer" }),
		DMKHOA_KHAMICD: new JStORM.Field({ type: "Integer" }),
		DMKHOA_KHAMCC: new JStORM.Field({ type: "Integer" }),
		DMKHOA_SOTRET: new JStORM.Field({ type: "Integer" }),
		DMKHOA_PHTHUAT: new JStORM.Field({ type: "Integer" }),
		DMKHOA_NHAPPT: new JStORM.Field({ type: "Integer" }),
		DMKHOA_GIUONGKH: new JStORM.Field({ type: "Integer" }),
		DMKHOA_GIUONGTK: new JStORM.Field({ type: "Integer" }),
		DMKHOA_YEUCAU: new JStORM.Field({ type: "Integer" }),
		DMKHOA_CAPMAU: new JStORM.Field({ type: "Integer" }),
		DMKHOA_DUOC: new JStORM.Field({ type: "Integer" }),
		DMKHOA_THUPHI: new JStORM.Field({ type: "Integer" }),
		DMKHOA_CUM: new JStORM.Field({ type: "Integer" }),
		DMKHOA_GIATRAN: new JStORM.Field({ type: "Float"  }),
		DMKHOA_TIENGANH: new JStORM.Field({ type: "Integer" }),
		DMKHOA_THUTU: new JStORM.Field({ type: "Integer" }),
		DMKHOA_DVVESINH: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DMKHOA_KHO: new JStORM.Field({ type: "String", maxLength: 12 }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmKhoa.prototype.getDataList = function() {
	var objArr = DmKhoa.filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getKhoaHavingCLS = function() {
	
	var objArr = DmKhoa.filter("DMKHOA_CLS = 1 and DT = 1").toArray();	
	return objArr;
};


DmKhoa.prototype.getDataList_NoiTru = function() {
	var objArr = DmKhoa.filter("DMKHOA_NOITRU = 1 and DT = 1").toArray();	
	//alert(objArr);
	return objArr;
};

DmKhoa.prototype.getDataList_NgoaiTru = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHAM = 1 and DT = 1").toArray();	
	//alert(objArr);
	return objArr;
};

DmKhoa.prototype.getDataList_NoiTru_ThanhLy = function() {
	var objArr = DmKhoa.filter("(DMKHOA_NOITRU = 1 OR DMKHOA_KHO = 'TL' ) and DT = 1").toArray();	
	//alert(objArr);
	return objArr;
};

//lay kho tuyen duoi
DmKhoa.prototype.getDataList_KhoaTuyenDuoi = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'TD'").filter("DT = 1").toArray();		
	return objArr;
};
DmKhoa.prototype.getDataList_KhoaThanhLy = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'TL'").filter("DT = 1").toArray();		
	return objArr;
};
DmKhoa.prototype.getDataList_Le_BHYT = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KL' or DMKHOA_KHO = 'BHYT' ").filter("DT = 1").toArray();		
	return objArr;
};
DmKhoa.prototype.getDataList_NoiTru_NgoaiTruKhoa = function(MaKhoa) {
	var objArr = DmKhoa.filter("DMKHOA_NOITRU = 1 and Ma <> ?",MaKhoa).filter("DT = 1").toArray();
	return objArr;
};
DmKhoa.prototype.getDataList_NoiTru_NgoaiTru = function(MaKhoa) {
	var objArr = DmKhoa.filter("DMKHOA_NOITRU = 1 or DMKHOA_KHAM = 1 OR DMKHOA_NGOAITRU").filter("DT = 1").toArray();
	return objArr;
};
DmKhoa.prototype.getDataList_Duoc = function() {
	var objArr = DmKhoa.filter("DMKHOA_DUOC = 1 and DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_Ct = function(tenCt) {
	//alert(tenCt);
	var objArr = null;
	if (tenCt=='KC' || tenCt == 'BHYT'){
		objArr= DmKhoa.filter("DMKHOA_KHO = ? and DT = 1", tenCt).toArray();
	}else if (tenCt=='KL'){
		objArr = DmKhoa.filter("DMKHOA_KHO = ? and DT = 1", tenCt).toArray();
	}else if (tenCt=='TE'){
		objArr = DmKhoa.filter("DMKHOA_KHO = ? and DT = 1", tenCt).toArray();
	}else if (tenCt=='NT'){
		objArr = DmKhoa.filter("DMKHOA_KHO = ? and DT = 1", tenCt).toArray();
	}
	
	/*else { // kho noi tru
		objArr = DmKhoa.filter("DMKHOA_NOITRU = 1 and DT = 1").toArray();
	}*/
		
	return objArr;
};
DmKhoa.prototype.getDataList_KhoChinh_KhoLe = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' or DMKHOA_KHO = 'KL' ").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_KhoChinh = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' ").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_KhoChinh_KhoLe_BHYT = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' or DMKHOA_KHO = 'KL' or DMKHOA_KHO = 'BHYT'").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_DmKhoa_KhoChinh_KhoLe_BHYT_TuTruc_ThanhLy = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' or DMKHOA_KHO = 'KL' or DMKHOA_KHO = 'BHYT' or DMKHOA_KHO = 'TL' or DMKHOA_NOITRU = 1").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_KhoNoiTru = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'NT'").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDataList_KL_BHYT = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KL' or DMKHOA_KHO = 'BHYT' ").filter("DT = 1").toArray();	
	return objArr;
};
DmKhoa.prototype.getDmKhoa_KTE_BHYT_KNT = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'TE' or DMKHOA_KHO = 'BHYT' or DMKHOA_KHO = 'NT'").filter("DT = 1").toArray();	
	return objArr;
};

/* ===== lay danh muc 4 kho: kho chinh, kho BHYT, kho Tre em, kho noi tru ===== */
DmKhoa.prototype.getDataList_KhoChinh_KhoLe_BHYT_TE = function() {
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' or DMKHOA_KHO = 'TE' or DMKHOA_KHO = 'BHYT' or DMKHOA_KHO = 'NT'").filter("DT = 1").toArray();	
	return objArr;
};

/* ===== lay danh muc tu truc cua cac khoa phong ===== */
DmKhoa.prototype.getDataList_TuTrucKhoaPhong = function() {
	var objArr = DmKhoa.filter("DMNHOMKHOA_MASO = 2 ").filter("DT = 1").toArray();	
	return objArr;
};

DmKhoa.prototype.getDataList_KhoaPhongNhom12 = function() {
	var objArr = DmKhoa.filter("DMKHOA_DUOC <> 1 AND (DMNHOMKHOA_MASO = 1 OR DMNHOMKHOA_MASO = 2) ").filter("DT = 1").toArray();	
	return objArr;
};

DmKhoa.prototype.getDataList_NotCt = function(kho) {
	var objArr = DmKhoa.filter("DMKHOA_KHO <> ? AND DMKHOA_KHO <> ''", kho).filter("DT = 1").toArray();	
	return objArr;
};

DmKhoa.prototype.getMaKhoaKham = function() {
    
	var objArr = DmKhoa.filter("Ma = 'KHA' and DT = 1").toArray();	
	
	for (var i = 0; i < objArr.length; i++) {
	   
		return objArr[i];
	}
	return null;
};
DmKhoa.prototype.getKhoaKhoChinh = function() {
    
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'KC' and DT = 1").toArray();	
	
	for (var i = 0; i < objArr.length; i++) {
	   
		return objArr[i];
	}
	return null;
};
DmKhoa.prototype.getMaKhoaBHYT = function() {
    
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'BHYT' and DT = 1").toArray();	
	
	for (var i = 0; i < objArr.length; i++) {
	   
		return objArr[i];
	}
	return null;
};
DmKhoa.prototype.getMaKhoaTreEm = function() {
    
	var objArr = DmKhoa.filter("DMKHOA_KHO = 'TE' and DT = 1").toArray();	
	
	for (var i = 0; i < objArr.length; i++) {
	   
		return objArr[i];
	}
	return null;
};

/*
DmKhoa.prototype.getMaKhoaBHYT = function() {
    
	var objArr = DmKhoa.filter("DMKHOA_KHO = ?","BHYT").toArray();	
	
	for (var i = 0; i < objArr.length; i++) {
	   
		return objArr[i];
	}
	return null;
};
*/
//phuc.lc 22-12-2010 : Lay damh muc khoa nhap nuoc (Dinh duong)
DmKhoa.prototype.getDataList_NhapNuoc = function() {
	var objArr = DmKhoa.filter("Ma <> 'BGD' and Ma <> 'KCH' and Ma <> 'KBH' and Ma <> 'KTE' and Ma <> 'KTL' and Ma <> 'KNT' and Ma <> 'KTD'").filter("DT = 1").toArray();
	return objArr;
};
DmKhoa.prototype.getDataListByNhanVien=function( MaSoNV) {
	var objArr = null;
	
		var listNvKhoa = DtDmNhanVienKhoa.filter("DTDMNHANVIEN_MASO = ? and DT = 1", MaSoNV).toArray();
		//alert(listNvKhoa.length);
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DMKHOA_MASO;
				} else {
					query += ", " + listNvKhoa[i].DMKHOA_MASO;
				}
			}
			query += ")";
			objArr = DmKhoa.filter("MaSo IN " + query).filter("DT = 1").toArray();
		}
	//alert(objArr);

	return objArr;
};

DmKhoa.prototype.getDataListByNhanVienAndNoiTru=function( MaSoNV) {
	var objArr = null;
		//alert(MaSoNV);
		var listNvKhoa = DtDmNhanVienKhoa.filter("DTDMNHANVIEN_MASO = ? and DT = 1", MaSoNV).toArray();
		//alert(listNvKhoa.length);
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DMKHOA_MASO;
				} else {
					query += ", " + listNvKhoa[i].DMKHOA_MASO;
				}
			}
			query += ")";
			
			//alert(query);
			objArr = DmKhoa.filter("DMKHOA_NOITRU = 1 AND MaSo IN " + query).filter("DT = 1").toArray();
		}
	//alert(objArr);

	return objArr;
};



DmKhoa.prototype.getDataList_DmKhoaByCLS=function( MaSoCLS) {
	var objArr = null;
	
		var listNvKhoa = DtDmClsKhoa.filter("DTDMCLS_MASO = ? and DT = 1", MaSoCLS).toArray();
		//alert(listNvKhoa.length);
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DMKHOA_MASO;
				} else {
					query += ", " + listNvKhoa[i].DMKHOA_MASO;
				}
			}
			query += ")";
			objArr = DmKhoa.filter("MaSo IN " + query).filter("DT = 1").toArray();
		}
	//alert(objArr);

	return objArr;
};

// khai bao bang DM_KHO
var DtDmKho = new JStORM.Model({
	name: "DT_DM_KHO",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMKHO_KHOCHA: new JStORM.Field({ type: "Integer"}),
		DTDMKHO_THUKHO: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMKHO_THUTUBC: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
				
	},
	connection: "default"
}); 
DtDmKho.prototype.getDataList = function() {
	var objArr = DtDmKho.filter("DT = 1").toArray();	
	return objArr;
};

var DmKhoaBc = new JStORM.Model({
	name: "DM_KHOA_BC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DMKHOABC_MABYT: new JStORM.Field({ type: "Integer" }),
		DMKHOABC_MABYT3: new JStORM.Field({ type: "Integer" }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMKHOABC_NHOM: new JStORM.Field({ type: "Integer" }),
		DMKHOABC_THUTUCBC: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmKhoaBc.prototype.getDataList = function() {
	var objArr = DmKhoaBc.filter("DT = 1").toArray();	
	return objArr;
};

var DmKhoaByt = new JStORM.Model({
	name: "DM_KHOA_BYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DMKHOABYT_MABYT3: new JStORM.Field({ type: "String", maxLength: 15}),
		Ten: new JStORM.Field({ type: "String", maxLength: 512}),
		DMKHOABYT_NHOM: new JStORM.Field({ type: "Integer"}),
		DMKHOABYT_THUTUBC: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmKhoaByt.prototype.getDataList = function() {
	var objArr = DmKhoaByt.filter("DT = 1").toArray();	
	return objArr;
};

var DmLoaiBenhVien = new JStORM.Model({
	name: "DM_LOAI_BENH_VIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DmLoaiBenhVien.prototype.getDataList = function() {
	var objArr = DmLoaiBenhVien.filter("DT = 1").toArray();	
	return objArr;
};

var DmLoaiKhoa = new JStORM.Model({
	name: "DM_LOAI_KHOA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DMLOAIKHOA_THUTUCBC: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DmLoaiKhoa.prototype.getDataList = function() {
	var objArr = DmLoaiKhoa.filter("DT = 1").toArray();	
	return objArr;
};

var PhieuNhapKho = new JStORM.Model({
	name: "PHIEU_NHAP_KHO",
	fields: {
		PHIEU_NHAP_KHO_MAPHU: new JStORM.Field({ type: "Float" }),
		PHIEU_NHAP_KHO_MAPHU1: new JStORM.Field({ type: "Float" }),
		SANSANG_CAPNHAT: new JStORM.Field({ type: "Integer" }),
		PHIEUNHAPKHO_MA: new JStORM.Field({ type: "String", maxLength: 15}),
		DTDMLOAI_MA: new JStORM.Field({ type: "String", maxLength: 2}),
		PHIEUNHAPKHO_SOHOADON: new JStORM.Field({ type: "String", maxLength: 12}),
		PHIEUNHAPKHO_NGAYHOADON: new JStORM.Field({ type: "String", maxLength: 10}),
		PHIEUNHAPKHO_CHUNGTU: new JStORM.Field({ type: "String", maxLength: 50}),
		DTDMNGUON_MA: new JStORM.Field({ type: "String", maxLength: 2}),
		DMKINHPHI_MASO: new JStORM.Field({ type: "String", maxLength: 3}),
		DTDMNOIBAN_MA: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMNHANVIEN_TIEPLIEU: new JStORM.Field({ type: "String", maxLength: 8}),
		PHIEUNHAPKHO_MUCTHUE: new JStORM.Field({ type: "Float"}),
		PHIEUNHAPKHO_THUE: new JStORM.Field({ type: "Float"}),
		PHIEUNHAPKHO_THANHTIEN: new JStORM.Field({ type: "Float"}),
		PHIEUNHAPKHO_TAIKHOAN: new JStORM.Field({ type: "String", maxLength: 10}),
		PHIEUNHAPKHO_DOIUNG: new JStORM.Field({ type: "String", maxLength: 10}),
		PHIEUNHAPKHO_TRONSO: new JStORM.Field({ type: "String", maxLength: 12}),
		PHIEUNHAPKHO_NGAYTT: new JStORM.Field({ type: "String", maxLength: 10}),
		PHIEUNHAPKHO_CHUNGTUTT: new JStORM.Field({ type: "String", maxLength: 18}),
		DTDMNHANVIEN_TT: new JStORM.Field({ type: "String", maxLength: 8}),
		DMKHO_MASO: new JStORM.Field({ type: "String", maxLength: 3}),
		DTDMNHANVIEN_CN: new JStORM.Field({ type: "String", maxLength: 8}),
		PHIEUNHAPKHO_NGAYGIOCN: new JStORM.Field({ type: "String", maxLength: 10}),
		PHIEUNHAPKHO_STT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 

var CtNhapKho = new JStORM.Model({
	name: "CT_NHAP_KHO",
	fields: {
		CT_NHAP_KHO_MAPHU: new JStORM.Field({ type: "Float" }),
		CT_NHAP_KHO_MAPHU1: new JStORM.Field({ type: "Float" }),
		CTNHAPKHO_MA: new JStORM.Field({ type: "Integer"}),
		PHIEUNHAPKHO_MA: new JStORM.Field({ type: "String", maxLength: 15}),
		CTNHAPKHO_MALK: new JStORM.Field({ type: "String", maxLength: 100}),
		DMTHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		DMQUOCGIA_MASO: new JStORM.Field({ type: "Integer"}),
		CTNHAPKHO_THANGNHAP: new JStORM.Field({ type: "String", maxLength: 2}),
		CTNHAPKHO_NAMNHAP: new JStORM.Field({ type: "String", maxLength: 4}),
		CTNHAPKHO_NGAYHANDUNG: new JStORM.Field({ type: "String", maxLength: 2}),
		CTNHAPKHO_THANGHANDUNG: new JStORM.Field({ type: "String", maxLength: 2}),
		CTNHAPKHO_NAMHANDUNG: new JStORM.Field({ type: "String", maxLength: 4}),
		CTNHAPKHO_DONGIA: new JStORM.Field({ type: "Float"}),
		CTNHAPKHO_DONGIABAN: new JStORM.Field({ type: "Float"}),
		CTNHAPKHO_LO: new JStORM.Field({ type: "String", maxLength: 4}),
		CTNHAPKHO_SODANGKY: new JStORM.Field({ type: "String", maxLength: 10}),
		CTNHAPKHO_THUTU: new JStORM.Field({ type: "Integer" }),
		CTNHAPKHO_SOLUONG: new JStORM.Field({ type: "Float"}),
		CTNHAPKHO_QUYCACH: new JStORM.Field({ type: "Integer"}),
		TONKHO_MA: new JStORM.Field({ type: "Integer"}),
		CTNHAPKHO_NGAYGIOCN: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMHANGSX_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		PHIEUNHAPKHO_MAPHU: new JStORM.Field({ type: "Float"})
	},
	connection: "default"
}); 
// khai bao bang DT_DM_KCB_BHYT
var DtDmKcbBhyt = new JStORM.Model({
	name: "DT_DM_KCB_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMTINHBHYT_MASO: new JStORM.Field({ type: "Integer"}),
		DTDMHUYENBHYT_MA: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMTUYENKCB_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMKCBBHYT_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMKCBBHYT_MAUTHE: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DTDMKCBBHYT_DEFA: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});	
DtDmKcbBhyt.prototype.getDataList = function() {
	var objArr = DtDmKcbBhyt.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DT_DM_TIEN_KHAM
var DtDmTienKham = new JStORM.Model({
	name: "DT_DM_TIEN_KHAM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMTIENKHAM_DONGIA: new JStORM.Field({ type: "Float" }),
		DTDMTIENKHAM_MALOAI: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMTIENKHAM_YEUCAU: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DtDmTienKham.prototype.getDataList = function() {
	var objArr = DtDmTienKham.filter("DT = 1").toArray();	
	return objArr;
};
		
// khai bao bang DT_DM_TINH_BHYT
var DtDmTinhBhyt = new JStORM.Model({
	name: "DT_DM_TINH_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMTINHBHYT_NGAYTINH: new JStORM.Field({ type: "String", maxLength:10 }),
		DTDMTINHBHYT_TEN1: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMTINHBHYT_NGAYTINH1: new JStORM.Field({ type: "String", maxLength: 10 }),
		DTDMTINHBHYT_TEN2: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMTINHBHYT_NGAYTINH2: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMTINHBHYT_TEN3: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMTINHBHYT_NGAYTINH3: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMTINHBHYT_NEW: new JStORM.Field({ type: "Integer"}),
		DTDMTINHBHYT_NOITINH: new JStORM.Field({ type: "String", maxLength: 50}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DTDMTINHBHYT_DEFA: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});	
DtDmTinhBhyt.prototype.getDataList = function() {
	var objArr = DtDmTinhBhyt.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmTuyenKcb = new JStORM.Model({
	name: "DT_DM_TUYEN_KCB",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})

	},
	connection: "default"
});	
DtDmTuyenKcb.prototype.getDataList = function() {
	var objArr = DtDmTuyenKcb.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DT_DM_KHOI_BHYT
var DtDmKhoiBhyt = new JStORM.Model({
	name: "DT_DM_KHOI_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMKHOIBHYT_TYLEBHYT1: new JStORM.Field({ type: "Integer" }),
		DTDMKHOIBHYT_TYLEBHYT2: new JStORM.Field({ type: "Integer" }),
		DTDMKHOIBHYT_TYLEKTC: new JStORM.Field({ type: "Integer" }),
		
		DTDMKHOIBHYT_GOMYC: new JStORM.Field({ type: "Integer" }),
		DTDMKHOIBHYT_LOAI: new JStORM.Field({ type: "Integer"}),
		DTDMKHOIBHYT_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMKHOIBHYT_PHANLOAI: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		
		DTDMKHOIBHYT_GHTYLE: new JStORM.Field({ type: "Float" }),
		DTDMKHOIBHYT_MINKTC: new JStORM.Field({ type: "Float" }),
		DTDMKHOIBHYT_TLMINKTC: new JStORM.Field({ type: "Float" }),
		DTDMKHOIBHYT_MAXKTC: new JStORM.Field({ type: "Float" }),
		DTDMKHOIBHYT_TLMAXKTC: new JStORM.Field({ type: "Float" }),
		
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});	
DtDmKhoiBhyt.prototype.getDataList = function() {
	var objArr = DtDmKhoiBhyt.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmLoaiBhyt = new JStORM.Model({
	name: "DT_DM_LOAI_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMLOAIBHYT_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});	
DtDmLoaiBhyt.prototype.getDataList = function() {
	var objArr = DtDmLoaiBhyt.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmLoaiMien = new JStORM.Model({
	name: "DT_DM_LOAI_MIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});	
DtDmLoaiMien.prototype.getDataList = function() {
	var objArr = DtDmLoaiMien.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmLoaiPhauThuat = new JStORM.Model({
	name: "DT_DM_LOAI_PHAU_THUAT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMLOAIPT_PHAUTHUAT: new JStORM.Field({ type: "Integer"}),
		DTDMLOAIPT_MABYTP: new JStORM.Field({ type: "Integer" }),
		DTDMLOAIPT_THUTHUAT: new JStORM.Field({ type: "Integer"}),
		DTDMLOAIPT_MABYTT: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})

	},
	connection: "default"
});	
DtDmLoaiPhauThuat.prototype.getDataList = function() {
	var objArr = DtDmLoaiPhauThuat.filter("DT = 1").toArray();	
	return objArr;
};
       
// khai bao bang DT_DM_BAN_KHAM
var DtDmBanKham = new JStORM.Model({
	name: "DT_DM_BAN_KHAM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMBANKHAM_MA0: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMBANKHAM_MA2: new JStORM.Field({ type: "String", maxLength: 5}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMBANKHAM_KYHIEU: new JStORM.Field({ type: "String", maxLength: 5}),
		DMLOAIKHOA_MASO: new JStORM.Field({ type: "Integer"}),
		DTDMBANKHAM_THOIGIAN: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMBANKHAM_TINHCHAT: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMNHANVIEN_BACSI1: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_BACSI2: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_BACSI3: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_TENBC: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});    
DtDmBanKham.prototype.getDataList = function() {
	var objArr = DtDmBanKham.filter("DT = 1").toArray();	
	return objArr;	
};
DtDmBanKham.prototype.getDataList_KoCCL = function() {
	var objArr = DtDmBanKham.filter("DT = 1 and Ma not like 'CC_'").toArray();	
	return objArr;	
};
DtDmBanKham.prototype.getDataList_CCL = function() {
	//var objArr = DtDmBanKham.filter("Ma like 'CCL' or Ma like 'CCN'").filter("DT = 1").toArray();
	var objArr = DtDmBanKham.filter("DT = 1 and Ma like 'CC_'").toArray();
	return objArr;	
};
var DtDmBacSiBanKham = new JStORM.Model({
	name: "DT_DM_BAC_SI_BAN_KHAM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		DTDMBANKHAM_MASO: new JStORM.Field({ type: "Integer" }),
		DTDMNHANVIEN_MASO: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 

// khai bao bang DM_PHAN_LOAI_TAI_NAN
var DmPhanLoaiTaiNan = new JStORM.Model({
	name: "DM_PHAN_LOAI_TAI_NAN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMPLTAINAN_TEN2: new JStORM.Field({ type: "String", maxLength: 250}),
		DMPLTAINAN_BHYT: new JStORM.Field({ type: "Integer"}),
		DMPLTAINAN_BHYT2: new JStORM.Field({ type: "Integer"}),
		DMPLTAINAN_TAPTIN: new JStORM.Field({ type: "String", maxLength: 250}),
		DMTAINAN_MASO: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"}),
		QL: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmPhanLoaiTaiNan.prototype.getDataList = function() {
	var objArr = DmPhanLoaiTaiNan.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DT_DM_CLS_BANG_GIA
var DtDmClsBangGia = new JStORM.Model({
	name: "DT_DM_CLS_BANG_GIA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMCLSBG_MA2: new JStORM.Field({ type: "String", maxLength: 4}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMCLSBG_PHANDV: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIA: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIAMP: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIABH: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIA2: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIA3: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIAYC: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_DONGIANN: new JStORM.Field({ type: "Float" }),
		DTDMCLSBG_KHACTUYEN: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_VATTU: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_BAOTRI: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_DIENNUOC: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_CONG: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_OXY: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_MALOAI: new JStORM.Field({ type: "Integer" }),
		DTDMCLSBG_PHANLOAI: new JStORM.Field({ type: "Integer"}),
		
		DTDMCLSBG_PHANBIET: new JStORM.Field({ type: "String", maxLength: 250}),
		DMCLSBG_LOAI: new JStORM.Field({ type: "String", maxLength: 250}),
		
		
		DMCLSBG_NDM: new JStORM.Field({ type: "Integer"}),
		DMCLSBG_MIEN: new JStORM.Field({ type: "Integer"}),
		
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"}),
		DMCLSBG_LOAIPTTT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DtDmClsBangGia.prototype.getDataList = function() {
	var objArr = DtDmClsBangGia.filter("DT = 1").toArray();	
	return objArr;
};
DtDmClsBangGia.prototype.getDataList_Kham = function(clsStr) {
	//alert(clsStr);
	var objArr = DtDmClsBangGia.filter("DTDMCLSBG_MALOAI in (" + clsStr + ")").filter("DT = 1").toArray();	
	//alert(objArr.length);
	return objArr;
};
DtDmClsBangGia.prototype.getDataList_DtDmClsBangGiaByKhoa = function(arrayDtDmClsMaSo) {
	//alert(clsStr);
	var objArr ;
	if (arrayDtDmClsMaSo == ""){
		objArr = DtDmClsBangGia.filter("DT = 1").toArray();		
	}else{
		objArr = DtDmClsBangGia.filter("DTDMCLSBG_MALOAI in (" + arrayDtDmClsMaSo + ")").filter("DT = 1").toArray();	
	}
	//alert(objArr.length);
	return objArr;
};
//
DtDmClsBangGia.prototype.getDataList_Phauthuatthuthuat = function() {
	//alert(clsStr);
	var objArr ;
	//objArr = DtDmClsBangGia.filter(" (DTDMCLSBG_MALOAI = 6 or  DTDMCLSBG_MALOAI= 8) and DT = 1 ").toArray();	
	objArr = DtDmClsBangGia.filter(" (DTDMCLSBG_MALOAI = 6 or  DTDMCLSBG_MALOAI= 8)  and DT = 1 ").toArray();	
	
	//alert(objArr.length);
	return objArr;
};

DtDmClsBangGia.prototype.getDataList_Phauthuatthuthuat_search = function() {

	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and (DTDMCLSBG_MALOAI = 6 or  DTDMCLSBG_MALOAI= 8)  and DT = 1";
	var objArr = null;
	
	if(countRecord(DtDmClsBangGia.getTableName(),condition)){
		objArr = DtDmClsBangGia.filter(condition).filter("DT = 1").toArray();
	}
	//alert(condition);
	return objArr;
}
DtDmClsBangGia.prototype.getDataList_CLS = function(textboxValue, MaCls) {
	if (textboxValue != null && textboxValue !=""){
		//textboxValue = textboxValue.toLowerCase();
	}
    var myMaso = getMaSo("DT_DM_CLS","Ma",MaCls);
    
    //var condition_ext = "Ten like \'" + process_searchText(textboxValue) + "\'";
    var condition_ext = process_searchTextExt("Ten", textboxValue);
    
    var objCauHinhArr = CauHinh.filter("Ma like 'PHUONG_PHAP_SEARCH_THUOC'").filter("DT = 1").toArray();
	var phuongphapsearchthuoc = "0";
	if (objCauHinhArr != null && objCauHinhArr.length > 0){
		phuongphapsearchthuoc = objCauHinhArr[0].Ten;
	}
	if (phuongphapsearchthuoc == '1'){
		condition_ext = process_searchTextExt("Ten", "%"+textboxValue);
	}
	
    //alert(condition_ext);
    //alert(condition_ext);
	var objArr = null;
	if (myMaso != null && myMaso != ""){
				if (textboxValue != null && textboxValue != ""){
					
					if(countRecord(DtDmClsBangGia.getTableName(),"DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 AND " + condition_ext)){
						objArr = DtDmClsBangGia.filter(condition_ext ).filter ("DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
					}
										
				}else{
						if(countRecord(DtDmClsBangGia.getTableName(),"DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 ")){
							objArr = DtDmClsBangGia.filter ("DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
							
						}
				}		
	}else{
		//alert(condition_ext);
		if (textboxValue != null && textboxValue != ""){
			//alert(condition_ext);
			var testrow = countRecord(DtDmClsBangGia.getTableName(),"DT = 1 AND " + condition_ext );
			//alert(condition_ext);
			//alert(testrow);
			if(countRecord(DtDmClsBangGia.getTableName(),"DT = 1 AND " + condition_ext )){
				objArr = DtDmClsBangGia.filter( "DT = 1 AND " + condition_ext).toArray();
				//alert(objArr.toArray());
			}
			
		}else{
			//alert(2+textboxValue);
			if(countRecord(DtDmClsBangGia.getTableName(),"DT = 1 " )){
				objArr = DtDmClsBangGia.filter("DT = 1").toArray();						
			}					
		}			
	}			
	
	return objArr;
};

DtDmClsBangGia.prototype.getDataList_CLS_Ext = function(textboxValue, MaCls,MaKhoa) {
	// bao.ttc
	//if (textboxValue != null && textboxValue !=""){
		//textboxValue = textboxValue.toLowerCase();
	//}
    var myMaso = getMaSo("DT_DM_CLS","Ma",MaCls);
    
    //var condition_ext = "Ten like \'" + process_searchText(textboxValue) + "\'";
    var condition_ext = process_searchTextExt("Ten", textboxValue);
    //alert(condition_ext);

    // lay ma so cua bang gia
    var  arrayDtDmClsMaSo = getDtDmClsMaSoFromMaKhoa(MaKhoa); // co' dang:      1,3,4,6
    //alert(arrayDtDmClsMaSo);
    
    if(arrayDtDmClsMaSo==null||arrayDtDmClsMaSo==''){
    
    	if (MaKhoa!=null && MaKhoa!=''){
    		arrayDtDmClsMaSo = "-1";
    	}
    }
    
    
    
	var objArr = null;
	if (myMaso != null && myMaso != ""){
				if (textboxValue != null && textboxValue != ""){
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						if(countRecord(DtDmClsBangGia.getTableName()," DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 AND " + condition_ext)){
							objArr = DtDmClsBangGia.filter(condition_ext ).filter (" DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
						}
					}else{
						if(countRecord(DtDmClsBangGia.getTableName(),"MaSo in ("+arrayDtDmClsMaSo+") and DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 AND " + condition_ext)){
							objArr = DtDmClsBangGia.filter(condition_ext ).filter ("MaSo in ("+arrayDtDmClsMaSo+") and DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
						}					
					}										
				}else{
				
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						if(countRecord(DtDmClsBangGia.getTableName()," DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 ")){
							objArr = DtDmClsBangGia.filter (" DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
						}
					}else{
						if(countRecord(DtDmClsBangGia.getTableName(),"MaSo in ("+arrayDtDmClsMaSo+") and DTDMCLSBG_MALOAI  = " + myMaso + " AND DT = 1 ")){
							objArr = DtDmClsBangGia.filter ("MaSo in ("+arrayDtDmClsMaSo+") and DTDMCLSBG_MALOAI  = ? and DT = 1 " , myMaso).toArray();
						}
					}
						
				}		
	}else{
		//alert(condition_ext);
		if (textboxValue != null && textboxValue != ""){
			//alert(condition_ext);
			//var testrow = countRecord(DtDmClsBangGia.getTableName(),"DTDMCLSBG_MALOAI in ("+arrayDtDmClsMaSo+") and DT = 1 AND " + condition_ext );
			//alert(condition_ext);
			//alert(testrow);
			if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
				if(countRecord(DtDmClsBangGia.getTableName()," DT = 1 AND " + condition_ext )){
					objArr = DtDmClsBangGia.filter( " DT = 1 AND " + condition_ext).toArray();
					//alert(objArr.toArray());
				}
			}else{
				if(countRecord(DtDmClsBangGia.getTableName(),"MaSo in ("+arrayDtDmClsMaSo+") and DT = 1 AND " + condition_ext )){
					objArr = DtDmClsBangGia.filter( "MaSo in ("+arrayDtDmClsMaSo+") and DT = 1 AND " + condition_ext).toArray();
					//alert(objArr.toArray());
				}
			}
			
			
		}else{
			//alert(2+textboxValue);
			if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
				if(countRecord(DtDmClsBangGia.getTableName(),"DT = 1 " )){
					objArr = DtDmClsBangGia.filter("DT = 1").toArray();						
				}
			}else{
				if(countRecord(DtDmClsBangGia.getTableName(),"MaSo in ("+arrayDtDmClsMaSo+") and DT = 1 " )){
					objArr = DtDmClsBangGia.filter("MaSo in ("+arrayDtDmClsMaSo+") and DT = 1").toArray();						
				}
			}
								
		}			
	}			
	
	return objArr;
};
// khai bao bang DM_BENH_ICD
var DmBenhIcd = new JStORM.Model({
	name: "DM_BENH_ICD",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DMBENHICD_MACHUNG: new JStORM.Field({ type: "String", maxLength: 7}),
		DMCHUONGBENH_MASO: new JStORM.Field({ type: "Integer"}),
		DMBENHICD_TACNHAN: new JStORM.Field({ type: "Integer"}),
		DMBENHICD_LIENHE: new JStORM.Field({ type: "String", maxLength: 5}),
	
		DMBENHICD_BENHVN: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"}),
		QL: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 256})
	},
	connection: "default"
});  

DmBenhIcd.prototype.getDataList = function() {
	var objArr = null;
	if(countRecord(DmBenhIcd.getTableName(),"")){
		objArr = DmBenhIcd.filter("DT = 1").toArray();
	}		
	return objArr;
};

DmBenhIcd.prototype.getDataList_search = function() {
	// bao.ttc
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and DT = 1";
	var objArr = null;
	
	if(countRecord(DmBenhIcd.getTableName(),condition)){
		objArr = DmBenhIcd.filter(condition).filter("DT = 1").toArray();
	}
	//alert(condition);
	return objArr;
};


DmBenhIcd.prototype.getDataList_TacNhan = function() {

	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	var condition = "DMBENHICD_TACNHAN = 1 and DT = 1 and Ten like \'" + process_searchText(searchText) + "\'";
	//alert(countRecord(DmBenhIcd.getTableName(),condition));
	var objArr = null;
	if(countRecord(DmBenhIcd.getTableName(),condition)){
		objArr = DmBenhIcd.filter(condition).filter("DT = 1").toArray();
	}	
	return objArr;
	
	
};

// khai bao bang DM_DM_CLS
var DtDmCls = new JStORM.Model({
	name: "DT_DM_CLS",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		
		DTDMCLS_PHANBIET: new JStORM.Field({ type: "Integer"}),
		DTDMCLS_CHUDAU: new JStORM.Field({ type: "String", maxLength: 1}),
		DTDMCLS_THUTUBC: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"}),
		DTDMPBCLS_MA: new JStORM.Field({ type: "String", maxLength: 3})
	},
	connection: "default"
});
DtDmCls.prototype.getDataList = function() {
	var objArr = DtDmCls.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmClsKhoa = new JStORM.Model({
	name: "DT_DM_CLS_KHOA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		DTDMCLS_MASO: new JStORM.Field({ type: "Integer"}),
		DMKHOA_MASO: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});

// khai bao bang DT_DM_PHAU_THUAT
var DtDmPhauThuat = new JStORM.Model({
	name: "DT_DM_PHAU_THUAT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 1024}),
		DTDMPHAUTHUAT_LOAI1: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_LOAI2: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_PHANBIET: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMPHAUTHUAT_PHANLOAI1: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_PHANLOAI2: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_GOPCA: new JStORM.Field({ type: "Integer" }),
		DTDMPHAUTHUAT_MO: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_CHON1: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_CHON2: new JStORM.Field({ type: "Integer"}),
		DTDMPHAUTHUAT_LIENHE: new JStORM.Field({ type: "String", maxLength: 50}),
		DTDMPHAUTHUAT_MAMO: new JStORM.Field({ type: "String", maxLength: 4}),
		DTDMPHAUTHUAT_ORDER: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	
	},
	connection: "default"
}); 
DtDmPhauThuat.prototype.getDataList = function() {
	var objArr = DtDmPhauThuat.filter("DT = 1").toArray();	
	return objArr;
};

DtDmPhauThuat.prototype.getDataList_search = function() {
	var searchText = this.Ten;
	//alert(1);
	var condition = "Ten like \'" + process_searchText(searchText) + "\'";
	var objArr = null;
	if(countRecord(DtDmPhauThuat.getTableName(),condition)){
		objArr = DtDmPhauThuat.filter(condition).toArray();
	}	
	return objArr;
};


var DtDmPhongMo = new JStORM.Model({
	name: "DT_DM_PHONG_MO",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		KHOA_LIENHE: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMPHONGMO_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DtDmPhongMo.prototype.getDataList = function() {
	var objArr = DtDmPhongMo.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmPlBhyt = new JStORM.Model({
	name: "DT_DM_PL_BHYT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMPHLOAIBHYT_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})

	},
	connection: "default"
}); 

DtDmPlBhyt.prototype.getDataList = function() {
	var objArr = DtDmPlBhyt.filter("DT = 1").toArray();	
	return objArr;
};


DtDmPlBhyt.prototype.getDataListWithoutCB = function() {
	var objArr = DtDmPlBhyt.filter("DT = 1 AND Ma <> 'CB' ").toArray();	
	return objArr;
};

var DtDmPlPhauThuat = new JStORM.Model({
	name: "DT_DM_PL_PHAU_THUAT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DTDMPLPT_MAPHU: new JStORM.Field({ type: "String", maxLength: 3}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMPLPT_THUOCKHOA: new JStORM.Field({ type: "String", maxLength: 50}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DtDmPlPhauThuat.prototype.getDataList = function() {
	var objArr = DtDmPlBhyt.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DT_DM_VO_CAM
var DtDmVoCam = new JStORM.Model({
	name: "DT_DM_VO_CAM",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DTDMVOCAM_MAPHU: new JStORM.Field({ type: "String", maxLength: 3}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMVOCAM_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMVOCAM_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});    
DtDmVoCam.prototype.getDataList = function() {
	var objArr = DtDmVoCam.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_TINH
var DmTinh = new JStORM.Model({
	name: "DM_TINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 3}),
		MaTinhBHYT: new JStORM.Field({ type: "String", maxLength: 3}),		
		DMHUYEN_MA: new JStORM.Field({ type: "Integer"}),
		DMXA_MA: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DMTINH_DEFA: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmTinh.prototype.getDataList = function() {
	var objArr = DmTinh.filter("DT = 1").toArray();	
	return objArr;
};

DmTinh.prototype.getDataList_search = function() {
	var searchText = this.Ten;
	var condition = "Ten like \'" + process_searchText(searchText) + "\'";
	var objArr = null;
	if(countRecord(DmTinh.getTableName(),condition)){
		objArr = DmTinh.filter(condition).filter("DT = 1").toArray();
	}	
	return objArr;
};

// khai bao bang DM_XA
var DmXa = new JStORM.Model({
	name: "DM_XA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 9}),
		DMHUYEN_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DMXA_DEFA: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmXa.prototype.getDataList = function() {
	var objArr = DmXa.filter("DMHUYEN_MASO = '" + this.DMHUYEN_MASO + "'").filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_NGHE_NGHIEP
var DmNgheNghiep = new JStORM.Model({
	name: "DM_NGHE_NGHIEP",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMNGHENGHIEP_PHANLOAI: new JStORM.Field({ type: "Integer"}),
		DMNGHENGHIEP_PHANLOAI2: new JStORM.Field({ type: "String", maxLength: 2}),
		DMNGHENGHIEP_AGEMIN: new JStORM.Field({ type: "Integer"}),
		DMNGHENGHIEP_AGEMAX: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});
DmNgheNghiep.prototype.getDataList = function() {
	var objArr = DmNgheNghiep.filter("DT = 1").toArray();	
	return objArr;
};
DmNgheNghiep.prototype.getDataListByYearOld = function(YearOld) {
	var objArr = null;
	if (YearOld != null && YearOld != ""){
		objArr = DmNgheNghiep.filter("DMNGHENGHIEP_AGEMIN <= " + YearOld + " AND DMNGHENGHIEP_AGEMAX >= " + YearOld).filter("DT = 1").toArray();
	}else{
		objArr = DmNgheNghiep.filter("DT = 1").toArray();	
	}
	return objArr;
};

var DmNgheNghiepBaoCao = new JStORM.Model({
	name: "DM_NGHE_NGHIEP_BAO_CAO",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});


// khai bao bang DM_NHA_CUNG_CAP
var DmNhaCungCap = new JStORM.Model({
	name: "DM_NHA_CUNG_CAP",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		DMTINH_MASO: new JStORM.Field({ type: "Integer"}),
		DMNCT_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMNHACUNGCAP_DIACHI: new JStORM.Field({ type: "String", maxLength: 30}),
		DMNHACUNGCAP_DIENTHOAI: new JStORM.Field({ type: "String", maxLength: 18}),
		DMNHACUNGCAP_MASOTHUE: new JStORM.Field({ type: "String", maxLength: 25}),
		DMNHACUNGCAP_FAX: new JStORM.Field({ type: "String", maxLength: 18}),
		DMNHACUNGCAP_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 4}),
		DMNHACUNGCAP_PHANBIET: new JStORM.Field({ type: "String", maxLength: 4}),
		DMNHACUNGCAP_NGAYLV: new JStORM.Field({ type: "String", maxLength: 10 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmNhaCungCap.prototype.getDataList = function() {
	var objArr = DmNhaCungCap.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_NGUON_CHUONG_TRINH
var DmNguonChuongTrinh = new JStORM.Model({
	name: "DM_NGUON_CHUONG_TRINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 100}),
		DMLNCT_MASO: new JStORM.Field({ type: "Integer"}),
		DMNCT_THUTUCBC: new JStORM.Field({ type: "Integer"}),
		DMNCT_DEFA: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmNguonChuongTrinh.prototype.getDataList = function() {
	var objArr = DmNguonChuongTrinh.filter("DT = 1").toArray();	
	return objArr;
};

//
// khai bao bang DM_NGUON_KINH_PHI
var DmNguonKinhPhi = new JStORM.Model({
	name: "DM_NGUON_KINH_PHI",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 100}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer"}),
		DT: new JStORM.Field({ type: "Integer"}),
		DP: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmNguonKinhPhi.prototype.getDataList = function() {
	var objArr = DmNguonKinhPhi.filter("DT = 1").toArray();	
	return objArr;
};
//

DtDmNhanVienKhoa = new JStORM.Model({
	name: "DT_DM_NHANVIEN_KHOA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		DTDMNHANVIEN_MASO: new JStORM.Field({ type: "Integer" }),
		DMKHOA_MASO: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});

// khai bao bang DT_DM_NHAN_VIEN
var DtDmNhanVien = new JStORM.Model({
	name: "DT_DM_NHAN_VIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		HocVi: new JStORM.Field({ type: "Integer"}),
		NHOM_MA: new JStORM.Field({ type: "String", maxLength: 8}),
		Ten: new JStORM.Field({ type: "String", maxLength: 50}),
		DTDMNHANVIEN_GIOITINH: new JStORM.Field({ type: "String", maxLength: 3 }),
		DTDMNHANVIEN_NGAYSINH: new JStORM.Field({ type: "String", maxLength: 10 }),
		DTDMNHANVIEN_MAHH: new JStORM.Field({ type: "String", maxLength: 8}),
		DTDMNHANVIEN_BIENCHE: new JStORM.Field({ type: "String", maxLength: 1}),
		DTDMNHANVIEN_DIACHI: new JStORM.Field({ type: "String", maxLength: 50}),
		DTDMNHANVIEN_MOBILE: new JStORM.Field({ type: "String", maxLength: 12}),
		DTDMNHANVIEN_EMAIL: new JStORM.Field({ type: "String", maxLength: 50}),
		DTDMNHANVIEN_KYPHIEU: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_DUYET: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_MO: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_NGHIVIEC: new JStORM.Field({ type: "Integer"}),
		DTDMNHANVIEN_MATMA: new JStORM.Field({ type: "String", maxLength: 20}),
		DTDMNHANVIEN_TKATM: new JStORM.Field({ type: "String", maxLength: 12}),
		DTDMNHANVIEN_SOBHXH: new JStORM.Field({ type: "String", maxLength: 12}),
		ND_MASO: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DtDmNhanVien.prototype.getDataList = function() {
	var objArr = DtDmNhanVien.filter("DT = 1").toArray();	
	
	return objArr;
};
DtDmNhanVien.prototype.getDtDmBacSi = function() {
	var objArr = DtDmNhanVien.filter("(HocVi = 2 or HocVi = 3 or HocVi = 4) and DT = 1").toArray();	
	
	return objArr;
};

DtDmNhanVien.prototype.getDataList_search = function() {
	var searchText = this.Ten;
	if (searchText != null && searchText !=""){
		searchText = searchText.toLowerCase();
	}
	//alert(1);
	var condition = "Ten like \'%" + process_searchText(searchText) + "\' and DT = 1";
	var objArr = null;
	//alert(condition);
	//alert(countRecord(DmBenhVien.getTableName(),condition));
	if(countRecord(DtDmNhanVien.getTableName(),condition)){
		objArr = DtDmNhanVien.filter(condition).toArray();
		//alert(objArr);
	}	
	//alert(objArr.length);
	return objArr;
};
DtDmNhanVien.prototype.getDataListByMaKhoa = function(maKhoaValue) {
	var objArr = null;
	var khoa = DmKhoa.getByFieldValue("Ma", maKhoaValue);
	if (khoa != null) {
		var listNvKhoa = DtDmNhanVienKhoa.filter("DMKHOA_MASO = ?", khoa.MaSo).filter("DT = 1").toArray();
		//alert(listNvKhoa.length);
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DTDMNHANVIEN_MASO;
				} else {
					query += ", " + listNvKhoa[i].DTDMNHANVIEN_MASO;
				}
			}
			query += ")";
			objArr = DtDmNhanVien.filter("MaSo IN " + query).toArray();
		}
	}

	return objArr;
};

DtDmNhanVien.prototype.getDataListByMaBanKham = function(maBanKhamValue) {
	var objArr = null;
	var listMaNV = DtDmBacSiBanKham.filter("DTDMBANKHAM_MASO = ?", maBanKhamValue).filter("DT = 1").toArray();
	if (listMaNV != null){
		var query = "(";
		for (var i = 0; i < listMaNV.length; i++) {
			if (i == 0) {
				query += listMaNV[i].DTDMNHANVIEN_MASO;
			} else {
				query += ", " + listMaNV[i].DTDMNHANVIEN_MASO;
			}
		}
		query += ")";
		objArr = DtDmNhanVien.filter("MaSo IN " + query).toArray();
	}
	return objArr;
};

DtDmNhanVien.prototype.getDataList_Duoc = function() {
	var objArr = null;
	var queryKhoa = "(";
	var listKhoa = DmKhoa.filter("DMKHOA_DUOC = 1 AND DT = 1 ").toArray();
	
	if (listKhoa != null) {
		queryKhoa = "(";
		for (var i = 0; i < listKhoa.length; i++) {
			if (i == 0) {
				queryKhoa += listKhoa[i].MaSo;
			} else {
				queryKhoa += ", " + listKhoa[i].MaSo;
			}
		}
		queryKhoa += ")";
		
		var listNvKhoa = DtDmNhanVienKhoa.filter("DMKHOA_MASO IN " + queryKhoa).filter("DT = 1").toArray();
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DTDMNHANVIEN_MASO;
				} else {
					query += ", " + listNvKhoa[i].DTDMNHANVIEN_MASO;
				}
			}
			query += ")";
			objArr = DtDmNhanVien.filter("MaSo IN " + query).toArray();
		}
	}

	return objArr;
};


DtDmNhanVien.prototype.getDataList_Kham = function() {
	var objArr = null;
	var queryKhoa = "(";
	var listKhoa = DmKhoa.filter("DMKHOA_KHAM = 1").toArray();
	
	if (listKhoa != null) {
		queryKhoa = "(";
		for (var i = 0; i < listKhoa.length; i++) {
			if (i == 0) {
				queryKhoa += listKhoa[i].MaSo;
			} else {
				queryKhoa += ", " + listKhoa[i].MaSo;
			}
		}
		queryKhoa += ")";
		
		var listNvKhoa = DtDmNhanVienKhoa.filter("DMKHOA_MASO IN " + queryKhoa).filter("DT = 1").toArray();
		if (listNvKhoa != null) {
			var query = "(";
			for (var i = 0; i < listNvKhoa.length; i++) {
				if (i == 0) {
					query += listNvKhoa[i].DTDMNHANVIEN_MASO;
				} else {
					query += ", " + listNvKhoa[i].DTDMNHANVIEN_MASO;
				}
			}
			query += ")";
			objArr = DtDmNhanVien.filter("MaSo IN " + query).toArray();
		}
	}

	return objArr;
};

DtDmNhanVien.prototype.getDataList_Mo = function() {
	var objArr = DtDmNhanVien.filter("DTDMNHANVIEN_MO = 1").toArray();	
	return objArr;
};

// khai bao bang DM_THUOC
var DmThuoc = new JStORM.Model({
	name: "DM_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMDONVITINH_MASO: new JStORM.Field({ type: "Integer" }),
		DMBAOQUAN_MASO: new JStORM.Field({ type: "Integer" }),
		DMPHANNHOMTHUOC_MASO: new JStORM.Field({ type: "Integer" }),
		DMCACHDUNG_MASO: new JStORM.Field({ type: "Integer" }),
		DMTHUOC_TENKH: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMTHUOC_MAPHU: new JStORM.Field({ type: "String", maxLength: 8 }),
		DMTHUOC_SETS: new JStORM.Field({ type: "String", maxLength: 4 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 255 }),
		DMTHUOC_HAMLUONG: new JStORM.Field({ type: "String", maxLength: 10}),
		DMTHUOC_DUNGTICH: new JStORM.Field({ type: "String", maxLength: 10}),
		DMPHANNHOMTHUOC_MASO2: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_CAPPHAT: new JStORM.Field({ type: "String", maxLength: 2}),
		DMTHUOC_NCHU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_TONMAX: new JStORM.Field({ type: "Float" }),
		DMTHUOC_TONMIN: new JStORM.Field({ type: "Float" }),
		DMTHUOC_THUVO: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_YEUCAU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_YCU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_HOICHAN: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_TRUONGKHOA: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_NUOC: new JStORM.Field({ type: "String", maxLength: 3}),
		DMTHUOC_MIEN: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_DAM: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_CORTI: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_KHONGTHU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_TYLEBH: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_DUTRU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_DONGGOI: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_PHATBN: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_DONGIABH: new JStORM.Field({ type: "Float" }),
		DMTHUOC_PHANKHOC: new JStORM.Field({ type: "String", maxLength: 2}),
		DMTHUOC_PHANKHOL: new JStORM.Field({ type: "String", maxLength: 2}),
		DMTHUOC_PHANKHOB: new JStORM.Field({ type: "String", maxLength: 2}),
		DMTHUOC_THUTU: new JStORM.Field({ type: "String", maxLength: 6}),
		DMTHUOC_LIEU: new JStORM.Field({ type: "Float" }),
		DMTHUOC_DACBIET: new JStORM.Field({ type: "String", maxLength: 2}),
		DMTHUOC_MAHANG2: new JStORM.Field({ type: "String", maxLength: 12}),		
		DMTHUOC_NUOCDEFA: new JStORM.Field({ type: "Integer"}),
		DMNHASANXUAT_MASO: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_KETOA: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_LINHGOP: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_INDANHMUC: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_INPLINH: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_INYLENH: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_GCHU: new JStORM.Field({ type: "String", maxLength: 25}),
		DMTHUOC_NGOAITRU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_NOITRU: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_TREEM: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_NGAYBOSUNG: new JStORM.Field({ type: "String", maxLength: 10 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" }),
		DMPHANLOAITHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		DMPHANLOAITHUOC_MASO2: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_DONGGIA_DAUTHAU: new JStORM.Field({ type: "Float" }),
		DMTHUOC_SODK: new JStORM.Field({ type: "String", maxLength: 64}),
		DMTHUOC_KHONGXUAT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
});

DmThuoc.prototype.getGiaDauThau = function(maThuoc) {

    //alert(objArr);	
	var objArr = null;
	var giaDauThau  = null;
	
	objArr = DmThuoc.filter("Ma like ?",maThuoc).toArray(); 
	
	if (objArr != null){
				for (var i = 0; i < objArr.length; i++) {
					giaDauThau = objArr[0].DMTHUOC_DONGGIA_DAUTHAU;
					
				}
	
	}
	return giaDauThau;
	
};

DmThuoc.prototype.getSoDangKy = function(maThuoc) {

    //alert(objArr);	
	var objArr = null;
	var SoDangKy  = null;
	
	objArr = DmThuoc.filter("Ma like ?",maThuoc).toArray(); 
	
	if (objArr != null){
				for (var i = 0; i < objArr.length; i++) {
					SoDangKy = objArr[0].DMTHUOC_SODK;
					
				}
	}
	return SoDangKy;
	
};

DmThuoc.prototype.getNuocSanXuat = function(maThuoc) {

    //alert(objArr);	
	var objArr = null;
	var maSoNuocSX  = null;
	var maNuocSX = null;
	
	objArr = DmThuoc.filter("Ma like ?",maThuoc).toArray(); 
	
	if (objArr != null){
				for (var i = 0; i < objArr.length; i++) {
					maSoNuocSX = objArr[0].DMTHUOC_NUOCDEFA;
					
				}
	}
	//alert(maSoNuocSX);
	/// lay ma tu ma so cua nuoc san xuat
	if (maSoNuocSX != null && maSoNuocSX != ''){
		
		var objArrNuocSX = null;
		objArrNuocSX = DmQuocGia.filter("MaSo = ?",maSoNuocSX).toArray(); 
		
		if (objArrNuocSX != null){
					for (var i = 0; i < objArrNuocSX.length; i++) {
						maNuocSX = objArrNuocSX[0].Ma;
						
					}
		}
	}
	
	return maNuocSX;
	
};

DmThuoc.prototype.getHangSanXuat = function(maThuoc) {

    //alert(objArr);	
	var objArr = null;
	var maSoHangSanXuat  = null;
	var maHangSanXuat = null;
	
	objArr = DmThuoc.filter("Ma like ?",maThuoc).toArray(); 
	
	if (objArr != null){
				for (var i = 0; i < objArr.length; i++) {
					maSoHangSanXuat = objArr[0].DMNHASANXUAT_MASO;
					
				}
	}
	//alert(maSoHangSanXuat);
	/// lay ma tu ma so cua nuoc san xuat
	if (maSoHangSanXuat != null && maSoHangSanXuat != ''){
		
		var objArrHangSX = null;
		objArrHangSX = DmNhaSanXuat.filter("MaSo = ?",maSoHangSanXuat).toArray(); 
		
		if (objArrHangSX != null){
					for (var i = 0; i < objArrHangSX.length; i++) {
						maHangSanXuat = objArrHangSX[0].Ma;
						
					}
		}
	}
	//alert(maHangSanXuat);
	return maHangSanXuat;
	
};
DmThuoc.prototype.getDataList = function() {

    //alert(objArr);	
	var objArr = null;
	
	if(countRecord(DmThuoc.getTableName(),"")){
		objArr = DmThuoc.filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray(); 
	}	
	
	return objArr;
	
};

DmThuoc.prototype.getDataList_search = function() {
	var searchText = this.Ten;
	//if (searchText != null && searchText !=""){
	//	searchText = searchText.toLowerCase();
	//}
	if (searchText == null || searchText == ""){
		return null;
	}
	//alert(1);
	//var condition = "Ten like \'" + process_searchText(searchText) + "\'";
	var condition = process_searchTextExt("Ten", searchText);
	
	
	var objCauHinhArr = CauHinh.filter("Ma like 'PHUONG_PHAP_SEARCH_THUOC'").filter("DT = 1").toArray();
	var phuongphapsearchthuoc = "0";
	if (objCauHinhArr != null && objCauHinhArr.length > 0){
		phuongphapsearchthuoc = objCauHinhArr[0].Ten;
	}
	if (phuongphapsearchthuoc == '1'){
		condition = process_searchTextExt("Ten", "%"+searchText);
	}
	var objArr = null;
	if(countRecord(DmThuoc.getTableName(),condition)){
		objArr = DmThuoc.filter(condition).filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray();
	}	
				
	return objArr;
};

DmThuoc.prototype.getDataListByPhanLoaiThuocAndLoaiThuoc = function(maPLThuoc, maLoaiThuoc) {
	var objArr = null;
	if (maLoaiThuoc != "" && maPLThuoc != ""){
	
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO = " + maPLThuoc + " AND DT = 1 AND DMTHUOC_KHONGXUAT = 0")){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO = ?", maPLThuoc).filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray();
		}	
		
		return objArr;
		
		
	}
	if (maLoaiThuoc != "" && maPLThuoc == ""){
		var listPlMaso = "";
		var listPhanLoai = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ?", maLoaiThuoc).filter("DT = 1").toArray();
		for (var i = 0; i < listPhanLoai.length; i++) {
			if (i == 0) {
				listPlMaso += listPhanLoai[i].MaSo;
			} else {
				listPlMaso += ", " + listPhanLoai[i].MaSo;
			}
		}
		
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ")" + " AND DT = 1 AND DMTHUOC_KHONGXUAT = 0")){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") ").filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray();
		}	
		return objArr;
		
	}else {
	
		if(countRecord(DmThuoc.getTableName()," DT = 1 AND DMTHUOC_KHONGXUAT = 0"  )){
			objArr = DmThuoc.filter(" DT = 1  AND DMTHUOC_KHONGXUAT = 0" ).toArray();	
		}	
		
		return 	objArr;
	}
				
	return null;
};


DmThuoc.prototype.getDataListByPhanLoaiThuocAndLoaiThuoc_Search = function(maPLThuoc, maLoaiThuoc,searchText) {
	var objArr = null;
	
	//var condition_ext = "Ten like \'" + process_searchText(searchText) + "\'";
	var condition_ext = process_searchTextExt("Ten", searchText);
	
	
	if (maLoaiThuoc != "" && maPLThuoc != ""){
	
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO = " + maPLThuoc + " AND DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext)){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO = ?", maPLThuoc).filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext).toArray();
		}	
		
		return objArr;
		
		
	}else if (maLoaiThuoc != "" && maPLThuoc == ""){
		var listPlMaso = "";
		var listPhanLoai = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ?", maLoaiThuoc).toArray();
		for (var i = 0; i < listPhanLoai.length; i++) {
			if (i == 0) {
				listPlMaso += listPhanLoai[i].MaSo;
			} else {
				listPlMaso += ", " + listPhanLoai[i].MaSo;
			}
		}
		
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ")" + " AND DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext)){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") ").filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext).toArray();
		}	
		return objArr;
		
	}else {
	
		if(countRecord(DmThuoc.getTableName()," DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext )){
			objArr = DmThuoc.filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext).toArray();	
		}	
		
		return 	objArr;
	}
				
	return null;
};

DmThuoc.prototype.getDataListByPhanLoaiThuoc = function(maPLThuoc) {

	var objArr = null;
	if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO = " + maPLThuoc + " AND DT = 1 AND DMTHUOC_KHONGXUAT = 0")){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO = ?", maPLThuoc).filter("DT = 1` AND DMTHUOC_KHONGXUAT = 0").toArray();	
	}	
	return objArr;
		
	
	
};

DmThuoc.prototype.getDataListByLoai_search = function(listPhanLoai,searchText) {
	
	var listPlMaso = "";
	for (var i = 0; i < listPhanLoai.length; i++) {
		if (i == 0) {
			listPlMaso += listPhanLoai[i].MaSo;
		} else {
			listPlMaso += ", " + listPhanLoai[i].MaSo;
		}
	}
	
	var objArr = null;	
	//var condition_ext = "Ten like \'" + process_searchText(searchText) + "\'";
	var condition_ext = process_searchTextExt("Ten", "%"+searchText);
	
	if (listPlMaso == "") {
		if(countRecord(DmThuoc.getTableName()," DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext )){
			objArr = DmThuoc.filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0 AND " + condition_ext).toArray();	
		}	
		//if (objArr != null){
		//	alert("pos 3: "+objArr.size());
		//}
		return 	objArr;
	} else {
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") AND DT = 1 AND " + condition_ext)){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") AND DT = 1 AND " + condition_ext).toArray();	
		}	
		//if (objArr != null){
		//	alert("pos 4: "+objArr.size());
		//}
		return 	objArr;		
	}
	
	return null;
};
DmThuoc.prototype.getDataListByLoai = function(listPhanLoai) {
	var listPlMaso = "";
	for (var i = 0; i < listPhanLoai.length; i++) {
		if (i == 0) {
			listPlMaso += listPhanLoai[i].MaSo;
		} else {
			listPlMaso += ", " + listPhanLoai[i].MaSo;
		}
	}
	//alert(listPlMaso);
	var objArr = null;	
	if (listPlMaso == "") {
		if(countRecord(DmThuoc.getTableName()," DT = 1 AND DMTHUOC_KHONGXUAT = 0")){
			objArr = DmThuoc.filter("DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray();	
		}	
		//if (objArr != null){
		//	alert("pos 1: "+objArr.size());
		//}
		return 	objArr;
	} else {
		if(countRecord(DmThuoc.getTableName(),"DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") AND DT = 1 AND DMTHUOC_KHONGXUAT = 0")){
			objArr = DmThuoc.filter("DMPHANLOAITHUOC_MASO IN (" + listPlMaso + ") AND DT = 1 AND DMTHUOC_KHONGXUAT = 0").toArray();	
		}	
		//if (objArr != null){
		//	alert("pos2: "+objArr.size());
		//}
		return 	objArr;		
	}
	
	return null;
};

var DmThuocHoatChat = new JStORM.Model({
	name: "DM_THUOC_HOAT_CHAT",
	fields: {
		DMHOATCHAT_MASO: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_MASO: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 

var DmPhanNhomThuoc = new JStORM.Model({
	name: "DM_PHAN_NHOM_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMNHOMBCTHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		DMPHANNHOMTHUOC_MAPHU: new JStORM.Field({ type: "String", maxLength: 2}),
		Ten: new JStORM.Field({ type: "String", maxLength: 36}),
		DMPHANNHOMTHUOC_THUTU: new JStORM.Field({ type: "String", maxLength: 6}),
		DMPHANNHOMTHUOC_GHICHU: new JStORM.Field({ type: "String", maxLength: 60}),
		DMNHANVIEN_CN: new JStORM.Field({ type: "String", maxLength: 8}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmPhanNhomThuoc.prototype.getDataList = function() {
	var objArr = DmPhanNhomThuoc.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_QUOC_GIA
var DmQuocGia = new JStORM.Model({
	name: "DM_QUOC_GIA",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10}),
		Ten: new JStORM.Field({ type: "String", maxLength: 100}),
		DMQUOCGIA_TENVN: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer"})
	},
	connection: "default"
}); 
DmQuocGia.prototype.getDataList = function() {
	var objArr = DmQuocGia.filter("DT = 1").toArray();	
	return objArr;
};


// khai bao bang DT_DM_LOAI
var DmLoaiThuoc = new JStORM.Model({
	name: "DM_LOAI_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 100}),
		DMLOAITHUOC_QUYEN: new JStORM.Field({ type: "String", maxLength: 3}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmLoaiThuoc.prototype.getDataList = function() {
	var objArr = DmLoaiThuoc.filter("DT = 1").toArray();	
	return objArr;
};

// khai bao bang DM_NHA_SAN_XUAT
var DmNhaSanXuat = new JStORM.Model({
	name: "DM_NHA_SAN_XUAT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMNHASANXUAT_TENVN: new JStORM.Field({ type: "String", maxLength: 250}),
		DMNHASANXUAT_PHANBIET: new JStORM.Field({ type: "String", maxLength: 3}),
		DMNHASANXUAT_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 3}),
		DMNHASANXUAT_HANG: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmNhaSanXuat.prototype.getDataList = function() {
	var objArr = DmNhaSanXuat.filter("DT = 1").toArray();	
	return objArr;
};


DmNhaSanXuat.prototype.getDataList_Search = function() {
	var searchText = this.Ten;
	//alert(1);
	var condition = "Ten like \'" + process_searchText(searchText) + "\'";
	var objArr = null;
	if(countRecord(DmNhaSanXuat.getTableName(),condition)){
		objArr = DmNhaSanXuat.filter(condition).filter("DT = 1").toArray();
	}	
	return objArr;
};


// khai bao bang DM_PHUONG_THUC_GAY_TAI_NAN
var DmPhuongThucGayTaiNan = new JStORM.Model({
	name: "DM_PHUONG_THUC_GAY_TAI_NAN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMTAINAN_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMPTGTN_BHYT: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});   
DmPhuongThucGayTaiNan.prototype.getDataList = function() {
	var objArr = DmPhuongThucGayTaiNan.filter("DT = 1").toArray();	
	return objArr;
};

DmPhuongThucGayTaiNan.prototype.getDataListByTaiNanMaso = function(_masoTaiNan) {
	var objArr = DmPhuongThucGayTaiNan.filter("DMTAINAN_MASO = ?", _masoTaiNan).filter("DT = 1").toArray();	
	return objArr;
};


var DtDmDienMien = new JStORM.Model({
	name: "DT_DM_DIEN_MIEN",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DtDmDienMien.prototype.getDataList = function() {
	var objArr = DtDmDienMien.filter("DT = 1").toArray();	
	return objArr;
};
// khai bao bang DT_DM_HUONG_DT
var DtDmHuongDt = new JStORM.Model({
	name: "DT_DM_HUONG_DT",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DtDmHuongDt.prototype.getDataList = function() {
	var objArr = DtDmHuongDt.filter("DT = 1").toArray();	
	return objArr;
};
//khai bao bang DT_DM_NOI_SINH
var DtDmNoiSinh = new JStORM.Model({
	name: "DT_DM_NOI_SINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DTDMNOISINH_MAPHU: new JStORM.Field({ type: "String", maxLength: 3}),
		DTDMNOISINH_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 5}),
		DTDMNOISINH_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DtDmNoiSinh.prototype.getDataList = function() {
	var objArr = DtDmNoiSinh.filter("DT = 1").toArray();	
	return objArr;
};
//khai bao bang DM_LY_DO_CV
var DtDmLyDoCv = new JStORM.Model({
	name: "DT_DM_LY_DO_CV",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DtDmLyDoCv.prototype.getDataList = function() {
	var objArr = DtDmLyDoCv.filter("DT = 1").toArray();	
	return objArr;
};
	
var DmLoaiBaoCaoHSBADangCN = new JStORM.Model({
	name: "DM_LOAI_BAO_CAO_HSBA_DANG_CN",
	fields: {

		DM_LOAI_BAO_CAO_HSBA_DANG_CN_MAPHU: new JStORM.Field({ type: "Float"	}),
		DM_LOAI_BAO_CAO_HSBA_DANG_CN_MAPHU1: new JStORM.Field({ type: "Float" }),
		MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })

	},
	connection: "default"
}); 



DmLoaiBaoCaoHSBADangCN.prototype.getDataList = function() {
	var objArr = DmLoaiBaoCaoHSBADangCN.filter("DT = 1").toArray();	
	return objArr;
};

var DmLoaiXoaHoSoBenhAn = new JStORM.Model({
	name: "DM_LOAI_XOA_HO_SO_BENH_AN",
	fields: {

		DM_LOAI_XOA_HO_SO_BENH_AN_MAPHU: new JStORM.Field({ type: "Float"	}),
		DM_LOAI_XOA_HO_SO_BENH_AN_MAPHU1: new JStORM.Field({ type: "Float" }),
		MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })

	},
	connection: "default"
}); 
DmLoaiXoaHoSoBenhAn.prototype.getDataList = function() {
	var objArr = DmLoaiXoaHoSoBenhAn.filter("DT = 1").toArray();	
	return objArr;
};

var DtDmLoaiCapNhatMienGiam = new JStORM.Model({
	name: "DT_DM_LOAI_CAP_NHAT_MIEN_GIAM",
	fields: {

		DT_DM_LOAI_CAP_NHAT_MIEN_GIAM_MAPHU: new JStORM.Field({ type: "Float"	}),
		DT_DM_LOAI_CAP_NHAT_MIEN_GIAM_MAPHU1: new JStORM.Field({ type: "Float" }),
		MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DaXoa: new JStORM.Field({ type: "Integer" })

	},
	connection: "default"
}); 
DtDmLoaiCapNhatMienGiam.prototype.getDataList = function() {
	var objArr = DtDmLoaiCapNhatMienGiam.filter("DT = 1").toArray();	
	return objArr;
};

var DmPhanLoaiThuoc = new JStORM.Model({
	name: "DM_PHAN_LOAI_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		DMLOAITHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMPHANLOAITHUOC_NHOM2: new JStORM.Field({ type: "String", maxLength: 2}),
		DMPHANLOAITHUOC_NHOM3: new JStORM.Field({ type: "String", maxLength: 2}),
		DMPHANLOAITHUOC_DUNGTICH: new JStORM.Field({ type: "Integer"}),
		DMPHANLOAITHUOC_GHICHU: new JStORM.Field({ type: "String", maxLength: 8}),
		DMPHANLOAITHUOC_THUTUBC: new JStORM.Field({ type: "Integer"}),
		DMPHANLOAITHUOC_LOAI: new JStORM.Field({ type: "Integer"}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		QL: new JStORM.Field({ type: "Integer" }),
		DT: new JStORM.Field({ type: "Integer" }),
		DP: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
}); 
DmPhanLoaiThuoc.prototype.getDataList = function() {
	var objArr = DmPhanLoaiThuoc.filter("DT = 1").toArray();	
	return objArr;
};
DmPhanLoaiThuoc.prototype.getDataListByLoaiThuoc = function(maLoaiThuoc) {
	var objArr = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ?", maLoaiThuoc).filter("DT = 1").toArray();	
	return objArr;
};

//
/*
DtDmKyThuat = new JStORM.Model({
	name: "DT_DM_KY_THUAT",
			fields: {

				DT_DM_KY_THUAT_MAPHU: new JStORM.Field({ type: "Float"	}),
				DT_DM_KY_THUAT_MAPHU1: new JStORM.Field({ type: "Float" }),
				MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
				Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
				NgayChinhSua: new JStORM.Field({ type: "Float" }),
				DaXoa: new JStORM.Field({ type: "Integer" }),
				LoaiCLS: new JStORM.Field({ type: "String" , maxLength: 15}),
				LoaiCLSFather: new JStORM.Field({ type: "String" , maxLength: 15}),
				ChuDau: new JStORM.Field({ type: "String" , maxLength: 15}),
				ChuDauFather: new JStORM.Field({ type: "String" , maxLength: 15}),
				PhanBietCLS: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa1: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa2: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa3: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa4: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa5: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa6: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa7: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa8: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa9: new JStORM.Field({ type: "String" , maxLength: 15}),
				Khoa10: new JStORM.Field({ type: "String" , maxLength: 15 })

			},
			connection: "default"
	});
*/
//
DtDmPbCls = new JStORM.Model({
	name: "DT_DM_PB_CLS",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DtDmPbCls.prototype.getDataList = function() {
	var objArr = DtDmPbCls.filter("DT = 1").toArray();	
	return objArr;
};



var TamUng = new JStORM.Model({
		name: "TAM_UNG",
		fields: {
			MAPHU: new JStORM.Field({ type: "String", maxLength: 11}),
			TAMUNG_MA: new JStORM.Field({ type: "String", maxLength: 11}),
			HSBA_SOVAOVIEN: new JStORM.Field({ type: "String", maxLength: 17}),
			TAMUNG_KHOA: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNG_SOTIEN: new JStORM.Field({ type: "Float", maxLength: 22}),
			TAMUNG_NGAYGIO: new JStORM.Field({ type: "String", maxLength: 19}),
			TAMUNG_CUM: new JStORM.Field({ type: "Integer", maxLength: 5}),
			TAMUNG_KYHIEU: new JStORM.Field({ type: "String", maxLength: 8}),
			TAMUNG_QUYEN: new JStORM.Field({ type: "String", maxLength: 8}),
			TAMUNG_BIENLAI: new JStORM.Field({ type: "String", maxLength: 8}),
			NGAYCHINHSUA: new JStORM.Field({ type: "String", maxLength: 19}),
			TAMUNG_MAKIEMTRA: new JStORM.Field({ type: "String", maxLength: 9}),
			TAMUNG_LYDO: new JStORM.Field({ type: "String", maxLength: 512}),
			TAMUNG_NHANVIENCN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNG_THUNGAN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNG_IN: new JStORM.Field({ type: "String", maxLength: 2}),
			TAMUNG_LYDOINLAI: new JStORM.Field({ type: "String", maxLength: 512}),
			TAMUNG_STATUS: new JStORM.Field({ type: "String", maxLength: 4})
			
	},
		connection: "default"
	});
	
var TamUngKham = new JStORM.Model({
		name: "TAM_UNG_KHAM",
		fields: {
		MAPHU: new JStORM.Field({ type: "String", maxLength: 11}),	
			TAMUNGKHAM_MA: new JStORM.Field({ type: "String", maxLength: 11}),
			TIEPDON_MA: new JStORM.Field({ type: "String", maxLength: 17}),
			TAMUNGKHAM_BANKHAM: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNGKHAM_KHOA: new JStORM.Field({ type: "String", maxLength: 3}),
			TAMUNGKHAM_SOTIEN: new JStORM.Field({ type: "Float", maxLength: 22}),
			TAMUNGKHAM_NGAY: new JStORM.Field({ type: "String", maxLength: 10}),
			TAMUNGKHAM_CUM: new JStORM.Field({ type: "Integer", maxLength: 5}),
			TAMUNGKHAM_KYHIEU: new JStORM.Field({ type: "String", maxLength: 8}),
			TAMUNGKHAM_QUYEN: new JStORM.Field({ type: "String", maxLength: 8}),
			TAMUNGKHAM_BIENLAI: new JStORM.Field({ type: "String", maxLength: 8}),
			NGAYCHINHSUA: new JStORM.Field({ type: "String", maxLength: 19}),
			TAMUNGKHAM_MAKIEMTRA: new JStORM.Field({ type: "String", maxLength: 9}),
			TAMUNGKHAM_LYDO: new JStORM.Field({ type: "String", maxLength: 40}),
			TAMUNGKHAM_NHANVIENCN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNGKHAM_THUNGAN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			TAMUNGKHAM_INPHIEU: new JStORM.Field({ type: "String", maxLength: 4}),
			TAMUNGKHAM_STATUS: new JStORM.Field({ type: "String", maxLength: 4})
			
	},
		connection: "default"
	});
	
var MienGiam = new JStORM.Model({
		name: "MIEN_GIAM",
		fields: {	
		
			MAPHU: new JStORM.Field({ type: "String", maxLength: 11}),
			MIENGIAM_MA: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_MAPHIEU: new JStORM.Field({ type: "String", maxLength: 10}),
			HSBA_SOVAOVIEN: new JStORM.Field({ type: "String", maxLength: 17}),
			MIENGIAM_KHOA: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_NGAYD: new JStORM.Field({ type: "String", maxLength: 10}),
			MIENGIAM_NGAYC: new JStORM.Field({ type: "String", maxLength: 10}),
			MIENGIAM_TYLE: new JStORM.Field({ type: "Integer", maxLength: 5}),
			MIENGIAM_SOTIEN: new JStORM.Field({ type: "Float", maxLength: 22}),
			MIENGIAM_NGAY: new JStORM.Field({ type: "String", maxLength: 10}),
			MIENGIAM_NGAYGIOTT: new JStORM.Field({ type: "String", maxLength: 19}),
			MIENGIAM_LYDO: new JStORM.Field({ type: "String", maxLength: 512}),
			MIENGIAM_NGUOIDUYET: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_CUM: new JStORM.Field({ type: "Integer", maxLength: 5}),
			NGAYCHINHSUA: new JStORM.Field({ type: "String", maxLength: 19}),
			MIENGIAM_NHANVIENCN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_MAKIEMTRA: new JStORM.Field({ type: "String", maxLength: 9}),
			MIENGIAM_LOAIMIEN: new JStORM.Field({ type: "String", maxLength: 3}),
			MIENGIAM_DOITUONG: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_NGAYKY: new JStORM.Field({ type: "String", maxLength: 10}),
			MIENGIAM_THUNGAN: new JStORM.Field({ type: "Integer", maxLength: 10}),
			MIENGIAM_IN: new JStORM.Field({ type: "String", maxLength: 2}),
			MIENGIAM_STATUS: new JStORM.Field({ type: "String", maxLength: 4})
			},
		connection: "default"
	});
	
var DmGioiTinh = new JStORM.Model({
	name: "DM_GIOI_TINH",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 11}),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		DMGT_DEFA: new JStORM.Field({ type: "Integer" }),
		DMGT_GHICHU: new JStORM.Field({ type: "String", maxLength: 250}),
		DT: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Integer" })
	}, 
	connection: "default"
});

var DtDmCum = new JStORM.Model({
	name: "DT_DM_CUM",
	fields: {
		MaSo: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ma: new JStORM.Field({ type: "String", maxLength: 4 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 45 }),
		DT: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Integer" }),
		DTDMCUM_GHICHU: new JStORM.Field({ type: "String", maxLength: 512 })
	},
	connection: "default"
});
DtDmCum.prototype.getDataList = function() {
	var objArr = DtDmCum.filter("DT = 1").toArray();	
	return objArr;
};

var DmLoaiSinh = new JStORM.Model({
	name: "DM_LOAI_SINH",
	fields: {
		MaSo: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ma: new JStORM.Field({ type: "String", maxLength: 5 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
DmLoaiSinh.prototype.getDataList = function() {
	var objArr = DmLoaiSinh.filter("DT = 1").toArray();	
	return objArr;
};

var BenhNhan = new JStORM.Model({
	name: "BENH_NHAN",
	fields: {
		BENH_NHAN_MAPHU: new JStORM.Field({ type: "Float" }),
		BENH_NHAN_MAPHU1: new JStORM.Field({ type: "Float" }),
		SANSANG_CAPNHAT: new JStORM.Field({ type: "Integer"}),
		BENHNHAN_MA: new JStORM.Field({ type: "String", maxLength: 16}),
		BENHNHAN_HOTEN: new JStORM.Field({ type: "String", maxLength: 128}),
		BENHNHAN_NGAYSINH: new JStORM.Field({ type: "String", maxLength: 10}),
		BENHNHAN_GIOI: new JStORM.Field({ type: "String", maxLength: 10}),
		DANTOC_MA: new JStORM.Field({ type: "String", maxLength: 20}),
		QUOCGIA_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		TINH_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		HUYEN_MA: new JStORM.Field({ type: "String", maxLength: 6}),
		XA_MA: new JStORM.Field({ type: "String", maxLength: 9}),
		BENHNHAN_DIACHI: new JStORM.Field({ type: "String", maxLength: 512}),
		BENHNHAN_NGHE: new JStORM.Field({ type: "String", maxLength: 3}),
		BENHNHAN_NOISINH: new JStORM.Field({ type: "String", maxLength: 10}),
		BENHNHAN_LANVAO: new JStORM.Field({ type: "String", maxLength: 10}),
		BENHNHAN_NGAYGIOCN: new JStORM.Field({ type: "Integer" }),
		NHANVIEN_MA: new JStORM.Field({ type: "Integer", maxLength: 8}),
		BENHNHAN_TUOI: new JStORM.Field({ type: "Integer" }),
		BENHNHAN_NAMSINH: new JStORM.Field({ type: "String", maxLength: 10}),
		
		BENHNHAN_DONVITUOI: new JStORM.Field({ type: "Integer" }),
		BENHNHAN_CMND: new JStORM.Field({ type: "String", maxLength: 10})
		
	},
	connection: "default"
}); 
			
var TiepDon = new JStORM.Model({
	name: "TIEP_DON",
	fields: {
		TIEP_DON_MAPHU: new JStORM.Field({ type: "Float" }),
		TIEP_DON_MAPHU1: new JStORM.Field({ type: "Float" }),
		TIEPDON_MA: new JStORM.Field({ type: "String", maxLength: 16}),
		TIEPDON_MAPHIEUK: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_NGAYGIO: new JStORM.Field({ type: "String", maxLength: 20}),
		BENHNHAN_MA: new JStORM.Field({ type: "String", maxLength: 16}),
		DOITUONG_MA: new JStORM.Field({ type: "String", maxLength: 4}),
		TIEPDON_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 4}),
		TAINAN_MA: new JStORM.Field({ type: "String", maxLength: 20}),
		TIEPDON_DONVIGOI: new JStORM.Field({ type: "String", maxLength: 10}),
		KHOIBHYT_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		TINHBHYT_MA: new JStORM.Field({ type: "String", maxLength: 5}),
		DPBHYT_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		TIEPDON_MACOQUAN: new JStORM.Field({ type: "String", maxLength: 5}),
		TIEPDON_SOTHEBH: new JStORM.Field({ type: "String", maxLength: 18}),
		TIEPDON_NAMBHYT: new JStORM.Field({ type: "String", maxLength: 4}),
		TIEPDON_SONAMBH: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_TUYEN: new JStORM.Field({ type: "String", maxLength: 5}),
		KCBBHYT_MA: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_GIATRI1: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_GIATRI2: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_KHAISINH: new JStORM.Field({ type: "String", maxLength: 15}),
		TIEPDON_CHUNGSINH: new JStORM.Field({ type: "String", maxLength: 20}),
		TIEPDON_SOTHETE: new JStORM.Field({ type: "String", maxLength: 16}),
		TIEPDON_THENGHEO: new JStORM.Field({ type: "String", maxLength: 18}),
		TIEPDON_BANKHAM: new JStORM.Field({ type: "String", maxLength: 4}),
		TIEPDON_THUTU: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_THUTUS: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_LOAIKHAM: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_BNTRA: new JStORM.Field({ type: "String", maxLength: 20}),
		TIEPDON_DOITUONGBHYT: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_TYLEBH: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_BACSI: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_NGUOIDUYET: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_KYHIEU: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_QUYEN: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_BIENLAI: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_MAPHIEUD: new JStORM.Field({ type: "String", maxLength: 12}),
		TIEPDON_NHANVIENCN: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_THUNGAN: new JStORM.Field({ type: "String", maxLength: 8}),
		PHUONGTIEN_MA: new JStORM.Field({ type: "String", maxLength: 20}),
		NGUYENHAN_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		DIADIEM_MA: new JStORM.Field({ type: "String", maxLength: 3}),
		TIEPDON_RUOUBIA: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_LYDOVAOV: new JStORM.Field({ type: "String", maxLength: 60}),
		TIEPDON_MACHDOANB0: new JStORM.Field({ type: "String", maxLength: 6}),
		TIEPDON_DGCHDOANB0: new JStORM.Field({ type: "String", maxLength: 80}),
		TIEPDON_MACHDOANBD: new JStORM.Field({ type: "String", maxLength: 6}),
		TIEPDON_DGCHDOANBD: new JStORM.Field({ type: "String", maxLength: 80}),
		TIEPDON_MADICH: new JStORM.Field({ type: "String", maxLength: 4}),
		TIEPDON_VUOTTUYEN: new JStORM.Field({ type: "String", maxLength: 1}),
		TIEPDON_TRLUONG: new JStORM.Field({ type: "String", maxLength: 100}),
		TIEPDON_CHIEUCAO: new JStORM.Field({ type: "String", maxLength: 100}),
		TIEPDON_NHOMMAU: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_RH: new JStORM.Field({ type: "String", maxLength: 1}),
		TIEPDON_GIUONG: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_BAOTIN: new JStORM.Field({ type: "String", maxLength: 60}),
		KETQUA_MA: new JStORM.Field({ type: "String", maxLength: 1}),
		DIEUTRI_MA: new JStORM.Field({ type: "String", maxLength: 1}),
		TIEPDON_NGHE: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_PARA1: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_PARA2: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_PARA3: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_PARA4: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_CHKHOA: new JStORM.Field({ type: "String", maxLength: 3}),
		TIEPDON_CHVIEN: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_NGAYGIORA: new JStORM.Field({ type: "String", maxLength: 20}),
		TIEPDON_TITRANGRA: new JStORM.Field({ type: "String", maxLength: 40}),
		TIEPDON_BSCHUYEN: new JStORM.Field({ type: "String", maxLength: 8}),
		TIEPDON_LYDOCHVI: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_TAIKHAM: new JStORM.Field({ type: "String", maxLength: 20}),
		TIEPDON_LOIDAN: new JStORM.Field({ type: "String", maxLength: 60}),
		TIEPDON_TUVONG: new JStORM.Field({ type: "String", maxLength: 6}),
		TIEPDON_CUM: new JStORM.Field({ type: "String", maxLength: 2}),
		TIEPDON_NGAYGIOCN: new JStORM.Field({ type: "String", maxLength: 100}),
		TIEPDON_STATUS: new JStORM.Field({ type: "String", maxLength: 4}),
		TIEPDON_NGAYTT: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_MOC1: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_MOC2: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_MOC3: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_GIATRI3: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_GIATRI4: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_THUTU: new JStORM.Field({ type: "String", maxLength: 10}),
		TIEPDON_CO_GIAY_GIOI_THIEU: new JStORM.Field({ type: "Integer"}),
		TIEPDON_KHAM_DA_LIEU: new JStORM.Field({ type: "Integer"})
		
	},
	connection: "default"
}); 

var DmBaiThuoc = new JStORM.Model({
	name: "DM_BAI_THUOC",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 36 }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" }),
		QL: new JStORM.Field({ type: "Integer" }),		
		DP: new JStORM.Field({ type: "Integer" }),
		DMBAITHUOC_GHICHU: new JStORM.Field({ type: "String", maxLength: 255 })
	},
	connection: "default"
});
DmBaiThuoc.prototype.getDataList = function() {	
	var objArr = DmBaiThuoc.filter("DT = 1").toArray();		
	return objArr;
};

var BaithuocThuoc = new JStORM.Model({
	name: "BAITHUOC_THUOC",
	fields: {
		BAITHUOCTHUOC_MASO: new JStORM.Field({ type: "Integer" }),
		DMBAITHUOC_MASO: new JStORM.Field({ type: "Integer"}),
		DMTHUOC_MASO: new JStORM.Field({ type: "Integer" }),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),		
		BAITHUOCTHUOC_SOLUONG: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});
BaithuocThuoc.prototype.getDataList = function() {
	var objArr = BaithuocThuoc.toArray();	
	return objArr;
};

var DtDmBuong = new JStORM.Model({
	name: "DT_DM_BUONG",
	fields: {
		MaSo: new JStORM.Field({ type: "Integer" }),
		MaSo2: new JStORM.Field({ type: "Integer" }),
		Ma: new JStORM.Field({ type: "String", maxLength: 10 }),
		Ten: new JStORM.Field({ type: "String", maxLength: 250}),
		NgayChinhSua: new JStORM.Field({ type: "Float" }),
		DT: new JStORM.Field({ type: "Integer" })
	},
	connection: "default"
});

DtDmBuong.prototype.getDataList = function() {
	//var objArr = DtDmLoaiAn2.filter("DT = 1 ").toArray();
	var objArr = DtDmBuong.filter("MaSo2 = " + this.MaSo2 ).toArray();
	return objArr;
};