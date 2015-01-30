var khong_tim_thay_du_lieu = "Không tìm thấy dữ liệu";
var can_dang_nhap_lai = "Cần đăng nhập lại";

var db = google.gears.factory.create('beta.database');
var timer = google.gears.factory.create('beta.timer');
var number_max_recode=500;

var arrayValueReceivedFromServer= new Array(50); 
var arrayIDReceivedFromServer= new Array(50); 

var phuong_phap_tim_kiem_thuoc;
var phuong_phap_tim_kiem_cls;

//var xmlHttp;
var DM_pk = "_pk";
String.prototype.isUpperCase = function(){
    return this.toUpperCase() == this;
};
function resetTonKho(){
 	arrayValueReceivedFromServer= new Array(50); 
 	arrayIDReceivedFromServer= new Array(50); 
}
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};
function changeCursorDefault (){

	var forms = document.forms; //build array of all forms
	if (forms != null){
		for(y=0;y<forms.length;y++){
	      var formEls = document.forms[y].elements; //find all elements within each form
	      if (formEls != null){
	       	  for(g=0;g<formEls.length;g++){
	       	  	if(formEls[g].type != null){
			      if(formEls[g].type=="button"){
			      	var element = document.getElementById(formEls[g].id);
			      	 if (element != null){
		         		element.disabled = false;
		         	 }
			      }
			    }  
		      }	 
	      }		           
	    }   
	}
	
	document.body.style.cursor= 'default';
	
}
function changeCursorWait (){

	var forms = document.forms; //build array of all forms
    for(y=0;y<forms.length;y++){
      var formEls = document.forms[y].elements; //find all elements within each form
      for(g=0;g<formEls.length;g++){
         if(formEls[g].type != null){
		      if(formEls[g].type=="button"){
		         var element = document.getElementById(formEls[g].id);
		         if (element != null){
		         	element.disabled = true;
		         }
		       	
		      }
	     }
      }
       
    }   
    
	
	document.body.style.cursor= 'wait';

}
function getDtDmLoiDan(){
	var catalog = new DtDmLoiDan({});
	return catalog.getDataList();
}
function getDtDmMucAn(){
	var catalog = new DtDmMucAn({});
	return catalog.getDataList();
}
function getDtDmCum(){
	var catalog = new DtDmCum({});
	return catalog.getDataList();
}
function getDtDmPhong(){
	var catalog = new DtDmPhong({});
	return catalog.getDataList();
}
function getDtDmLoaiAn(){
	var catalog = new DtDmLoaiAn({});
	return catalog.getDataList();
}
function getDtDmLoaiAn2(textbox_loaian1Id){	
	var loaian1MaSo = document.getElementById(textbox_loaian1Id).value;
	//var LA1MaSo = "0";
	//var listLoaiAn1 = DtDmLoaiAn.filter("MaSo = ?", loaian1Ma).toArray();
	//if (listLoaiAn1.length > 0) {
	//	LA1MaSo = listLoaiAn1[listLoaiAn1.length - 1].MaSo;
	//}	
	var catalog = new DtDmLoaiAn2({ "MaSo2" : (loaian1MaSo != ''? loaian1MaSo : 0)});
	return catalog.getDataList();
}
function getDtDmCheDoAn(){
	var catalog = new DtDmCheDoAn({});
	return catalog.getDataList();
}
function getDtDmDoiTuongAn(){
	var catalog = new DtDmDoiTuongAn({});
	return catalog.getDataList();
}
function getDtDmDongThem(){
	var catalog = new DtDmDongThem({});
	return catalog.getDataList();
}
function getDtDmNhaSxSpdd(){
	var catalog = new DtDmNhaSxSpdd({});
	return catalog.getDataList();
}
function getDtDmLoaiTp(){
	var catalog = new DtDmLoaiTp({});
	return catalog.getDataList();
}
function getDtDmCapCuuPhien(){
	var catalog = new DtDmCapCuuPhien({});
	return catalog.getDataList();
}

function getDtDmPbCls(){
	var catalog = new DtDmPbCls({});
	return catalog.getDataList();
}

function getDmPhanLoaiThuoc(){
	var catalog = new DmPhanLoaiThuoc({});
	return catalog.getDataList();
}

function getDmPhanLoaiThuocByLoaiThuoc(maLoaiThuocId){
	var _maLoaiThuocId = document.getElementById(maLoaiThuocId).value;
	var catalog = new DmPhanLoaiThuoc({});
	return catalog.getDataListByLoaiThuoc(_maLoaiThuocId);
}


function getDmLoaiBaoCaoHSBADangCN(){
	var catalog = new DmLoaiBaoCaoHSBADangCN({});
	return catalog.getDataList();
}

function getDtDmLyDoCv(){
	var catalog = new DtDmLyDoCv({});
	return catalog.getDataList();
}

function getDtDmNoiSinh(){
	var catalog = new DtDmNoiSinh({});
	return catalog.getDataList();
}

function getDtDmDienMien(){
	var catalog = new DtDmDienMien({});
	return catalog.getDataList();
}

function getDtDmHuongDt(){
	var catalog = new DtDmHuongDt({});
	return catalog.getDataList();
}


function getDmDieuTriHSBA(){
	var catalog = new DmDieuTriHSBA({});
	//alert(catalog)
	return catalog.getDataList();
}
function getDmLoaiThuoc(){
	var catalog = new DmLoaiThuoc({});
	return catalog.getDataList();
}

function getDmLoaiSinh(){
	var catalog = new DmLoaiSinh({});
	return catalog.getDataList();
}

function getDmNhaSanXuat(){
	var catalog = new DmNhaSanXuat({});
	return catalog.getDataList();
}

function getDmNhaSanXuat_Search(seach_text){
	var catalog = new DmNhaSanXuat({"Ten" : seach_text});
	//alert(catalog);
	return catalog.getDataList_Search();
}



function getDmQuocGia(){
	var catalog = new DmQuocGia({});
	return catalog.getDataList();
}

function getDmPhanNhomThuoc(){
	var catalog = new DmPhanNhomThuoc({});
	return catalog.getDataList();
}

function getDmBaiThuoc(){	
	var catalog = new DmBaiThuoc({});	
	return catalog.getDataList();
}

function getDmThuoc(){
	var catalog = new DmThuoc({});
	return catalog.getDataList();
}

function getDmThuocByLoai(maLoai) {
	var loaiThuoc = DmLoaiThuoc.getByFieldValue("Ma", document.getElementById(maLoai).value);
	var listPhanLoai = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ? AND DT = 1 ", loaiThuoc.MaSo).toArray();
	var catalog = new DmThuoc({});
	//alert(listPhanLoai);
	return catalog.getDataListByLoai(listPhanLoai); 
}

function getDmThuocByLoai_Search(maLoai, seach_text) {
	var loaiThuoc = DmLoaiThuoc.getByFieldValue("Ma", document.getElementById(maLoai).value);
	var listPhanLoai = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ? AND DT = 1 ", loaiThuoc.MaSo).toArray();
	
	var catalog = new DmThuoc({});
	
	return catalog.getDataListByLoai_search(listPhanLoai,seach_text); 
}

function getDmThuocByPhanLoaiThuoc(maPLThuocId){
	var catalog = new DmThuoc({});
	var _maPLThuocId = document.getElementById(maPLThuocId).value;
	return catalog.getDataListByPhanLoaiThuoc(_maPLThuocId);
}

function getDmThuocByPhanLoaiThuocAndLoaiThuoc(maPLThuocId, maLoaiThuocId){
	var catalog = new DmThuoc({});
	var _maPLThuocId = document.getElementById(maPLThuocId).value;
	var _maLoaiThuocId = document.getElementById(maLoaiThuocId).value;
	return catalog.getDataListByPhanLoaiThuocAndLoaiThuoc(_maPLThuocId, _maLoaiThuocId);
}

function getDmThuocByPhanLoaiThuocAndLoaiThuoc_Search(maPLThuocId, maLoaiThuocId, seach_text){
	var catalog = new DmThuoc({});
	var _maPLThuocId = document.getElementById(maPLThuocId).value;
	var _maLoaiThuocId = document.getElementById(maLoaiThuocId).value;
	return catalog.getDataListByPhanLoaiThuocAndLoaiThuoc_Search(_maPLThuocId, _maLoaiThuocId, seach_text);
}

function getDmNguonKinhPhi(){
	var catalog = new DmNguonKinhPhi({});
	return catalog.getDataList();
}

function getDmNguonChuongTrinh(){
	var catalog = new DmNguonChuongTrinh({});
	return catalog.getDataList();
}

function getDmNhaCungCap(){
	var catalog = new DmNhaCungCap({});
	return catalog.getDataList();
}

function getDtDmVoCam(){
	var catalog = new DtDmVoCam({});
	return catalog.getDataList();
}

function getDtDmPlBhyt(){
	var catalog = new DtDmPlBhyt({});
	return catalog.getDataList();
}

function getDtDmPlBhytWithoutCB(){
	var catalog = new DtDmPlBhyt({});
	return catalog.getDataListWithoutCB();
}

function getDtDmPhongMo(){
	var catalog = new DtDmPhongMo({});
	return catalog.getDataList();
}
function getGiaDauThau(mathuoc){
	var catalog = new DmThuoc({});
	return catalog.getGiaDauThau(mathuoc);
}
//////////////////////////////
function getSoDangKy(mathuoc){ // tra ve string
	var catalog = new DmThuoc({});
	return catalog.getSoDangKy(mathuoc);
}
function getNuocSanXuat(mathuoc){ // tra ve ma
	var catalog = new DmThuoc({});
	return catalog.getNuocSanXuat(mathuoc);
}
function getHangSanXuat(mathuoc){ // tra ve ma
	var catalog = new DmThuoc({});
	return catalog.getHangSanXuat(mathuoc);
}
///////////////////////////////
function getDtDmPhauThuat(){
	var catalog = new DtDmPhauThuat({});
	return catalog.getDataList();
}
function getDtDmPhauThuat_search(seach_text){
	var catalog = new DtDmPhauThuat({"Ten" : seach_text});
	return catalog.getDataList_search();
}

function getDtDmCls(){
	var catalog = new DtDmCls({});
	return catalog.getDataList();
}

function getDtDmClsBangGia(){
	var catalog = new DtDmClsBangGia({});
	return catalog.getDataList();
}

function getDtDmClsBangGia_Search(textboxValue, MaCls){ // ten, ko phai ma
	//alert(1);
	var catalog = new DtDmClsBangGia({});
	//alert("focus nhan enter");
	var _textboxValue = document.getElementById(textboxValue).value;
	var _MaCls = document.getElementById(MaCls).value;
	
	//alert(_textboxValue);
	//alert(_MaCls);
	
	if (_textboxValue != null && _textboxValue !=""){
		//_textboxValue = _textboxValue.toLowerCase();
	}
	return catalog.getDataList_CLS(_textboxValue, _MaCls);
}

function getDtDmClsBangGia_SearchExt(textboxValue, MaCls, MaKhoa){ // ten, ko phai ma
	//alert(1);
	var catalog = new DtDmClsBangGia({});
	//alert("focus nhan enter");
	var _textboxValue = document.getElementById(textboxValue).value;
	var _MaCls = document.getElementById(MaCls).value;
	
	//alert(_textboxValue);
	//alert(_MaCls);
	
	if (_textboxValue != null && _textboxValue !=""){
		//_textboxValue = _textboxValue.toLowerCase();
	}
	return catalog.getDataList_CLS_Ext(_textboxValue, _MaCls,MaKhoa);
}

function getDtDmClsBangGiaKham() {
	var catalog = new DtDmClsBangGia({});
	var pbCls = DtDmPbCls.getByFieldValue("Ma", "KH");
	//alert(pbCls.Ma);
	var listCls = DtDmCls.filter("DTDMCLS_PHANBIET = ? AND DT = 1 ", pbCls.MaSo).toArray();
	var clsStr = "";
	if (listCls) {
		for (var i = 0; i < listCls.length; i++) {
			var cls = listCls[i];
			if (i == 0) {
				clsStr += cls.MaSo;
			} else {
				clsStr += ", " + cls.MaSo;
			}
		}
	}
	//clsStr = "10";
	//alert(clsStr);
	return catalog.getDataList_Kham(clsStr);
}


function getDtDmLoaiPhauThuat(){
	var catalog = new DtDmLoaiPhauThuat({});
	return catalog.getDataList();
}

function getDtDmLoaiMien(){
	var catalog = new DtDmLoaiMien({});
	return catalog.getDataList();
}

function getDtDmLoaiBhyt(){
	var catalog = new DtDmLoaiBhyt({});
	return catalog.getDataList();
}

function getDtDmTuyenKcb(){
	var catalog = new DtDmTuyenKcb({});
	return catalog.getDataList();
}

function getDmLoaiKhoa(){
	var catalog = new DmLoaiKhoa({});
	return catalog.getDataList();
}

function getDmLoaiBenhVien(){
	var catalog = new DmLoaiBenhVien({});
	return catalog.getDataList();
}

function getDmKhoaByt(){
	var catalog = new DmKhoaByt({});
	return catalog.getDataList();
}

function getDmKhoaByNhanVien(MaSoNV){
	var catalog = new DmKhoa({});
	return catalog.getDataListByNhanVien(MaSoNV);
}

function getDmKhoaByNhanVienAndNoiTru(NV_ID){
	var catalog = new DmKhoa({});
	var MaSoNV = document.getElementById(NV_ID).value; 
	//alert(MaSoNV);
	return catalog.getDataListByNhanVienAndNoiTru(MaSoNV);
}

function getDmKhoaBc(){
	var catalog = new DmKhoaBc({});
	return catalog.getDataList();
}

function getDtDmKho(){
	var catalog = new DtDmKho({});
	return catalog.getDataList();
}

function getDmKetQuaDieuTri(){
	var catalog = new DmKetQuaDieuTri({});
	return catalog.getDataList();
}

function getDmHocVi(){
	var catalog = new DmHocVi({});
	return catalog.getDataList();
}

function getDmHoatChat(){
	var catalog = new DmHoatChat({});
	return catalog.getDataList();
}

function getDmDonViTinh(){
	var catalog = new DmDonViTinh({});
	return catalog.getDataList();
}

function getDmDieuTri(){
	var catalog = new DmDieuTri({});
	return catalog.getDataList();
}

function getDtDmChiDan(){
	var catalog = new DtDmChiDan({});
	
	return catalog.getDataList();
}

function getDmNgheNghiep(){
	var catalog = new DmNgheNghiep({});
	return catalog.getDataList();
}

function getDmNgheNghiepByYearOld(yearOldId){
	var catalog = new DmNgheNghiep({});
	return catalog.getDataListByYearOld(document.getElementById(yearOldId).value);
}

function getDmNhomBaoCaoThuoc(){
	var catalog = new DmNhomBaoCaoThuoc({});
	return catalog.getDataList();
}

function getDmChuongBenh(){
	var catalog = new DmChuongBenh({});
	return catalog.getDataList();
}

function getDtDmNhomBhyt(){
	var catalog = new DtDmNhomBhyt({});
	return catalog.getDataList();
}

function getDtDmTramYTeBhyt(){
	
	var catalog = new DtDmTramYTeBhyt({});
	return catalog.getDataList();
}

function getDmNhomHocVi(){
	var catalog = new DmNhomHocVi({});
	return catalog.getDataList();
}

function getDmCachDungThuoc(){
	var catalog = new DmCachDungThuoc({});
	return catalog.getDataList();
}

function getDmNhomKhoa(){
	var catalog = new DmNhomHocVi({});
	return catalog.getDataList();
}

function getDmBenhVN(){
	var catalog = new DmBenhVN({});
	return catalog.getDataList();
}

function getDtDmNhanVien(){
	var catalog = new DtDmNhanVien({});
	
	return catalog.getDataList();
}
function getDtDmBacSi(){
	var catalog = new DtDmNhanVien({});
	return catalog.getDtDmBacSi();
}
function getDtDmNhanVien_Duoc(){
	var catalog = new DtDmNhanVien({});
	return catalog.getDataList_Duoc();
}
function getDtDmNhanVien_Kham(){
	var catalog = new DtDmNhanVien({});
	return catalog.getDataList_Kham();
}
function getDtDmNhanVienByMaKhoa(khoaId){
	//alert(khoaId);
	var catalog = new DtDmNhanVien({});
	return catalog.getDataListByMaKhoa(document.getElementById(khoaId).value);
}
function getDtDmNhanVienByMaKhoa_ThuNgan(){
	//alert(khoaId);
	var catalog = new DtDmNhanVien({});
	return catalog.getDataListByMaKhoa('TCK');
}

function getDtDmNhanVien_Mo(){
	var catalog = new DtDmNhanVien({});
	return catalog.getDataList_Mo();
}

function getDtDmNhanVien_BanKham(bankhamId){
	var catalog = new DtDmNhanVien({});
	return catalog.getDataListByMaBanKham(document.getElementById(bankhamId).value);
}

function getDmBenhIcd(){
	var catalog = new DmBenhIcd({});
	return catalog.getDataList();
}

function getDmBenhIcd_search(seach_text){
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhIcd({"Ten" : seach_text});
	
	return catalog.getDataList_search();
}

function getDmPhauThuatThuThuatCls(){
	//alert('getDmPhauThuatThuThuatCls');
	var catalog = new DtDmClsBangGia({});
	return catalog.getDataList_Phauthuatthuthuat();
}

function getDmPhauThuatThuThuatCls_search(seach_text){
	//alert('getDmPhauThuatThuThuatCls_search');
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DtDmClsBangGia({"Ten" : seach_text});
	return catalog.getDataList_Phauthuatthuthuat_search();
}

function getDmNhaSanXuat_search(seach_text){
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmNhaSanXuat({"Ten" : seach_text});
	
	return catalog.getDataList_Search();
}

function getDmThuoc_search(seach_text){
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmThuoc({"Ten" : seach_text});
	//alert(1);
	return catalog.getDataList_search();
}

function getDmTacNhan_search(seach_text){
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhIcd({"Ten" : seach_text, "DMBENHICD_TACNHAN" : "1"});
	//alert(catalog);
	return catalog.getDataList_TacNhan();
}

function getDmBenhVien(){
	var catalog = new DmBenhVien({});
	return catalog.getDataList();
}

function replaceVNChar(str){
	var firstChar = str.substring(0,1);
	  firstChar= firstChar.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/gi,"_");  
	  firstChar= firstChar.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/gi,"_");  
	  firstChar= firstChar.replace(/ì|í|ị|ỉ|ĩ/gi,"_");  
	  firstChar= firstChar.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/gi,"_");  
	  firstChar= firstChar.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/gi,"_");  
	  firstChar= firstChar.replace(/ỳ|ý|ỵ|ỷ|ỹ/gi,"_"); 
	  firstChar= firstChar.replace(/đ/gi,"_"); 
	  //alert("first char "+ firstChar);
	  str = firstChar + str.substring(1);
	  str= str.replace(/ à| á| ạ| ả| ã| â| ầ| ấ| ậ| ẩ| ẫ| ă| ằ| ắ| ặ| ẳ| ẵ/gi," _");  
	  str= str.replace(/ è| é| ẹ| ẻ| ẽ| ê| ề| ế| ệ| ể| ễ/gi," _");  
	  str= str.replace(/ ì| í| ị| ỉ| ĩ/gi," _");  
	  str= str.replace(/ ò| ó| ọ| ỏ| õ| ô| ồ| ố| ộ| ổ| ỗ| ơ| ờ| ớ| ợ| ở| ỡ/gi," _");  
	  str= str.replace(/ ù| ú| ụ| ủ| ũ| ư| ừ| ứ| ự| ử| ữ/gi," _");  
	  str= str.replace(/ ỳ| ý| ỵ| ỷ| ỹ/gi," _");  
	  str= str.replace(/đ/gi,"_"); 
	  return str;
}

function getDmBenhVien_search(seach_text){

	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhVien({"Ten" : seach_text});
	return catalog.getDataList_search();
}

function getDmBenhVien_search_chan_doan(seach_text){
	
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhVien({"Ten" : seach_text});
	return catalog.getDataList_search_chan_doan();
}

function getDmBenhVien_search_chuyen_di(seach_text){

	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhVien({"Ten" : seach_text});
	return catalog.getDataList_search_with_chuyen_di();
}

function getDtDmNhanVien_search(seach_text){

	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DtDmNhanVien({"Ten" : seach_text});
	return catalog.getDataList_search();
}
function getDmBenhVien_search_with_tinh(seach_text,matinhbhyt){
	
	if (seach_text != null && seach_text !=""){
		seach_text = seach_text.toLowerCase();
	}
	var catalog = new DmBenhVien({"Ten" : seach_text});
	//alert(123);
	return catalog.getDataList_search_with_tinh(matinhbhyt);
}

function getDmDiaDiem(){
	var catalog = new DmDiaDiem({});
	return catalog.getDataList();
}

function getDmPhuongThucGayTaiNan(){
	var catalog = new DmPhuongThucGayTaiNan({});
	return catalog.getDataList();
}
function getDmPhuongThucGayTaiNanByTaiNanMa(maTaiNanId){
	var _masoTaiNan = document.getElementById(maTaiNanId).value;
	var catalog = new DmPhuongThucGayTaiNan({});
	return catalog.getDataListByTaiNanMaso(_masoTaiNan);
}
function getDmTaiNan(){
	var catalog = new DmTaiNan({});
	return catalog.getDataList();
}

function getDmPhanLoaiTaiNan(){
	var catalog = new DmPhanLoaiTaiNan({});
	return catalog.getDataList();
}

function getDtDmTienKham(){
	var catalog = new DtDmTienKham({});
	return catalog.getDataList();
}

function getDtDmBanKham(){
	var catalog = new DtDmBanKham({});
	return catalog.getDataList();
}
function getDtDmBanKhamCCL(){
	var catalog = new DtDmBanKham({});
	return catalog.getDataList_CCL();
}

function getDtDmBanKhamKoCCL(){
	var catalog = new DtDmBanKham({});
	return catalog.getDataList_KoCCL();
}

function getDtDmKcbBhyt(){
	var catalog = new DtDmKcbBhyt({});
	return catalog.getDataList();
}

function getDmDanToc(){
	var catalog = new DmDanToc({});
	return catalog.getDataList();
}

function getDmDoiTuong(){
	var catalog = new DmDoiTuong({});
	return catalog.getDataList();
}

function getDmDoiTuongTP(){
	var catalog = new DmDoiTuong({});
	return catalog.getDataListTP();
}

function getDmTinh(){
	var catalog = new DmTinh({});
	return catalog.getDataList();
}

function getDtDmTinhBhyt(){
	var catalog = new DtDmTinhBhyt({});
	return catalog.getDataList();
}

function getDtDmKhoiBhyt(){
	var catalog = new DtDmKhoiBhyt({});
	return catalog.getDataList();
}

function getDmKhoa(){
	var catalog = new DmKhoa({});
	return catalog.getDataList();
}

function getDmKhoa_Ct(tenCt){
	var catalog = new DmKhoa({});
	//alert("tenCt: " + tenCt);
	return catalog.getDataList_Ct(tenCt);
}
function getDmKhoa_KhoChinh_KhoLe(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_KhoChinh_KhoLe();
}
/* ===== lay danh muc 4 kho: kho chinh, kho BHYT, kho Tre em, kho noi tru ===== */
function getDmKhoa_KhoChinh_KhoLe_BHYT_TE(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_KhoChinh_KhoLe_BHYT_TE();
}

/* ===== lay danh muc tu truc cua cac khoa phong ===== */
function getDmKhoa_TuTrucKhoaPhong(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_TuTrucKhoaPhong();
}
function getDmKhoa_KhoChinh(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_KhoChinh();
}
function getDmKhoa_KhoChinh_KhoLe_BHYT(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_KhoChinh_KhoLe_BHYT();
}

function getDmKhoa_KhoNoiTru(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_KhoNoiTru();
}
function getDmKhoa_KhoChinh_KhoLe_BHYT_TuTruc_ThanhLy(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_DmKhoa_KhoChinh_KhoLe_BHYT_TuTruc_ThanhLy();
}
function getDmKhoa_NotCt(kho){
	var catalog = new DmKhoa({});
	//alert("tenCt: " + kho);
	return catalog.getDataList_NotCt(kho);
}

function getDmKhoa_KL_BHYT(){
	var catalog = new DmKhoa({});
	//alert("tenCt: " + kho);
	return catalog.getDataList_KL_BHYT();
}
function getDmKhoa_KTE_BHYT_KNT(){
	var catalog = new DmKhoa({});
	//alert("tenCt: " + kho);
	return catalog.getDmKhoa_KTE_BHYT_KNT();
}


function getDmKhoa_NoiTru(){
	var catalog = new DmKhoa({});	
	
	return catalog.getDataList_NoiTru();
}
function getDmKhoa_NgoaiTru(){
	var catalog = new DmKhoa({});		
	return catalog.getDataList_NgoaiTru();
}
function getDmKhoa_NoiTru_ThanhLy(){
	var catalog = new DmKhoa({});	
	
	return catalog.getDataList_NoiTru_ThanhLy();
}
function getDmKhoa_KhoaTuyenDuoi(){
	var catalog = new DmKhoa({});	
	
	return catalog.getDataList_KhoaTuyenDuoi();
}
function getDmKhoa_KhoaThanhLy(){
	var catalog = new DmKhoa({});	
	
	return catalog.getDataList_KhoaThanhLy();
}
function getDmKhoa_Le_BHYT(){
	var catalog = new DmKhoa({});	
	
	return catalog.getDataList_Le_BHYT();
}
function getDmKhoa_NoiTru_NgoaiTru(){
	var catalog = new DmKhoa({});		
	return catalog.getDataList_NoiTru_NgoaiTru();
}
function getDmKhoaPhongDTNhom12(){
	var catalog = new DmKhoa({});		
	return catalog.getDataList_KhoaPhongNhom12();
}
function getDmKhoa_NoiTru_NgoaiTruKhoa(MaKhoaId){
	var catalog = new DmKhoa({});	
	var MaKhoa =  document.getElementById(MaKhoaId).value; 
	return catalog.getDataList_NoiTru_NgoaiTruKhoa(MaKhoa);
}

function getDmKhoa_Duoc(){
	var catalog = new DmKhoa({});
	return catalog.getDataList_Duoc();
}
/*
function getDmKhoa_Kho(){
	var catalog = new DmKhoa({});
	return catalog.getDataList_Kho();
}
*/
// phuc.lc 22-12-2010 : Lay danh muc khoa nhap nuoc (Dinh duong)
function getDmKhoa_NhapNuoc(){

	var catalog = new DmKhoa({});
	return catalog.getDataList_NhapNuoc();
}
function getDmKhoaByCLS(MaSoCLSBG){
	if (MaSoCLSBG == null || MaSoCLSBG == ''){
		return;
	}
	var catalog = new DmKhoa({});
	//alert(1);
	var _MaSoCLSBG = document.getElementById(MaSoCLSBG).value;
	var array_CLS = DtDmClsBangGia.filter("MaSo = ?", _MaSoCLSBG).toArray(); 
	var maSoCLS;
	if (array_CLS != null && array_CLS.length > 0) {
	        
			maSoCLS = array_CLS[0].DTDMCLSBG_MALOAI;
	}
	
	return catalog.getDataList_DmKhoaByCLS(maSoCLS);
}
function getLoaiPttt(MaClsBangGia){
	//var clsbg_list = new DtDmClsBangGia({})
	//alert(MaClsBangGia);
	//alert(document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_1_pk").value);
	
	
	var clsbg_list = DtDmClsBangGia.filter("Ma = ?", MaClsBangGia).toArray();
	//alert(clsbg_list);
	//alert(clsbg_list.length);
	if (clsbg_list != null && clsbg_list.length > 0) {
		//alert(clsbg_list[0].DMCLSBG_LOAIPTTT);
		document.getElementById(prefix_component + "DT_DM_CLS_BANG_GIA_1_pk").value = clsbg_list[0].MaSo;
		var loai_list = DtDmLoaiPhauThuat.filter("MaSo = ?",clsbg_list[0].DMCLSBG_LOAIPTTT).toArray();
		if(loai_list !=null && loai_list.length>0)
		{
			document.getElementById(prefix_component + "__loaipttt").value = loai_list[0].Ten;
		}
	}
}
function getMaSoPttt(MaClsBangGia,IdMaSo)
{
	var clsbg_list = DtDmClsBangGia.filter("Ma = ?", MaClsBangGia).toArray();
	if (clsbg_list != null && clsbg_list.length > 0) {
		document.getElementById(prefix_component + IdMaSo).value = clsbg_list[0].MaSo;
	}
}

function getKhoaHavingCLS(){
	
	
	/*
	var arrayDtDmClsKhoa; // danh sach gom cac entry la` DtDmClsKhoa	
	arrayDtDmClsKhoa = DtDmClsKhoa.filter("DT = 1").toArray(); 
	
	var arrayKhoaMaSo = ""; // co' dang:         12,3,3,5
	
	if (arrayDtDmClsKhoa != null && arrayDtDmClsKhoa.length > 0) {
	     for (var i = 0 ; i < arrayDtDmClsKhoa.length ; i++){
	     	if (arrayKhoaMaSo == ""){
	        	arrayKhoaMaSo = arrayDtDmClsKhoa[i].DMKHOA_MASO;
	        }else{
	        	arrayKhoaMaSo += ","+arrayDtDmClsKhoa[i].DMKHOA_MASO;
	        }	     
	     }        
	}
	// tu day lay ra duoc 
	var catalog = new DmKhoa({});
	//alert(arrayKhoaMaSo);
	*/
	var catalog = new DmKhoa({});
	return catalog.getKhoaHavingCLS();
}
function getDtDmClsMaSoFromMaKhoa(MaKhoa){
	if (MaKhoa == null || MaKhoa == ''){
		MaKhoa = '%'; // lay tat ca
	}
		
	var arrayDmKhoa = DmKhoa.filter("Ma like ? and DT = 1", MaKhoa).toArray(); 
	//alert("arrayDmKhoa.length:"+arrayDmKhoa.length );
	var arrayDmKhoaMaSo = ""; // co' dang:         12,3,3,5
	
	if (arrayDmKhoa != null && arrayDmKhoa.length > 0) {
	     for (var i = 0 ; i < arrayDmKhoa.length ; i++){
	     	if (arrayDmKhoaMaSo == ""){
	        	arrayDmKhoaMaSo = arrayDmKhoa[i].MaSo;
	        }else{
	        	arrayDmKhoaMaSo += ","+arrayDmKhoa[i].MaSo;
	        }	     
	     }        
	}
	
	//alert("arrayDmKhoaMaSo:"+arrayDmKhoaMaSo);
	
	var arrayDtDmClsKhoa; // danh sach gom cac entry la` DtDmClsKhoa
	if (arrayDmKhoaMaSo == ""){
		arrayDtDmClsKhoa = DtDmClsKhoa.filter("DT = 1").toArray(); 
	}else{
		arrayDtDmClsKhoa = DtDmClsKhoa.filter("DMKHOA_MASO in (?) and DT = 1 ", arrayDmKhoaMaSo).toArray(); 
	}
	 
	
	var arrayDtDmClsMaSo = ""; // co' dang:         12,3,3,5
	
	if (arrayDtDmClsKhoa != null && arrayDtDmClsKhoa.length > 0) {
	     for (var i = 0 ; i < arrayDtDmClsKhoa.length ; i++){
	     	if (arrayDtDmClsMaSo == ""){
	        	arrayDtDmClsMaSo = arrayDtDmClsKhoa[i].DTDMCLS_MASO;
	        }else{
	        	arrayDtDmClsMaSo += ","+arrayDtDmClsKhoa[i].DTDMCLS_MASO;
	        }	     
	     }        
	}
	// tra ve ma so cua dtdmclsbanggia
	return arrayDtDmClsMaSo;
}
function getDmCLSBangGiaByKhoa(MaKhoa){
	
	var arrayDtDmClsMaSo = getDtDmClsMaSoFromMaKhoa(MaKhoa);
	
	var catalog = new DtDmClsBangGia({});
	return catalog.getDataList_DtDmClsBangGiaByKhoa(arrayDtDmClsMaSo);
}
function getDmHuyen(textbox_TinhId){
	var tinhMa = document.getElementById(textbox_TinhId).value;
	var tinhMaSo = "0";
	var listTinh = DmTinh.filter("Ma = ? AND DT = 1 ", tinhMa).toArray();
	
	
	//alert(listTinh);
	
	
	for (var i = 0; i < listTinh.length; i++) {
		tinhMaSo = listTinh[i].MaSo;
	}
	
	var catalog = new DmHuyen({ "DMTINH_MASO" : tinhMaSo });
	return catalog.getDataList();
}

function getDmXa(textbox_HuyenId){
	var huyenMa = document.getElementById(textbox_HuyenId).value;
	var huyenMaSo = "0";
	var listHuyen = DmHuyen.filter("Ma = ? AND DT = 1 ", huyenMa).toArray();
	
	for (var i = 0; i < listHuyen.length; i++) {
		huyenMaSo = listHuyen[i].MaSo;
	}
	var catalog = new DmXa({ "DMHUYEN_MASO" : huyenMaSo });
	return catalog.getDataList();
}

function getDmLoaiXoaHoSoBenhAn(){
	var catalog = new DmLoaiXoaHoSoBenhAn({});
	return catalog.getDataList();
}

function getDtDmLoaiCapNhatMienGiam(){
	var catalog = new DtDmLoaiCapNhatMienGiam({});
	return catalog.getDataList();
}

function getDtDmBuong(textbox_khoaId){	
	var khoaMaSo = document.getElementById(textbox_khoaId).value;	
	var catalog = new DtDmBuong({ "MaSo2" : (khoaMaSo != ''? khoaMaSo : 0)});
	return catalog.getDataList();
}
function LoadCatalogFromServerExt() {

/*
	PhieuNhapKho.createTable();
	CtNhapKho.createTable();

	DtDmClsKhoa.createTable();
	DtDmKho.createTable();
    DmNhaCungCap.createTable();
    DmNguonChuongTrinh.createTable();
	DmNguonKinhPhi.createTable();
	DtDmNhanVien.createTable();
	DmThuoc.createTable();
	DmQuocGia.createTable();
	DmKhoa.createTable();
	DmLoaiThuoc.createTable();
	DmNhaSanXuat.createTable();
	DmDonViTinh.createTable();
	DmDoiTuong.createTable();
	DmTaiNan.createTable();
	
	DmDanToc.createTable();
    DmTinh.createTable();
    DmHuyen.createTable();
    DmXa.createTable();
    DmNgheNghiep.createTable();
    DmBenhVien.createTable();
    DmBenhIcd.createTable();
    DtDmPhauThuat.createTable();
    DtDmVoCam.createTable();
    
    DtDmKcbBhyt.createTable();
    DtDmTienKham.createTable();
    DtDmBanKham.createTable();
    DtDmTinhBhyt.createTable();
    DtDmKhoiBhyt.createTable();
    DmPhuongThucGayTaiNan.createTable();
    
    DmPhanLoaiTaiNan.createTable();
    DmDieuTri.createTable();
    DtDmHuongDt.createTable();
    DtDmNoiSinh.createTable();
    DtDmLyDoCv.createTable();
    DmLoaiBaoCaoHSBADangCN.createTable();
    
    DmPhanLoaiThuoc.createTable();
    //DtDmKyThuat.createTable();
    DtDmKetQua.createTable();
    
    DtDmCls.createTable();
	DtDmPbCls.createTable();
	DmDiaDiem.createTable();
	DtDmClsBangGia.createTable();
	DtDmBacSiBanKham.createTable();
	
	DtDmCapCuuPhien.createTable();
	DtDmNhanVienKhoa.createTable();
	
	
	getCatalogFromServer_new('DT_DM_CLS_KHOA', 'GetDtDmClsKhoaAction', DtDmClsKhoa);
	getCatalogFromServer_new('DT_DM_NHANVIEN_KHOA', 'GetDtDmNhanVienKhoaAction', DtDmNhanVienKhoa);
	getCatalogFromServer_new('DT_DM_CAP_CUU_PHIEN', 'GetCapCuuPhienAction', DtDmCapCuuPhien);
	getCatalogFromServer_new('DT_DM_BAC_SI_BAN_KHAM', 'GetDtDmBacSiBanKhamAction', DtDmBacSiBanKham);    
    getCatalogFromServer_new('DM_LOAI_BAO_CAO_HSBA_DANG_CN','GetLoaiBaoCaoBADangCapNhatAction', DmLoaiBaoCaoHSBADangCN);
    
    getCatalogFromServer_new('DM_TAI_NAN','GetDmTaiNanAction',DmTaiNan);
	getCatalogFromServer_new('DM_QUOC_GIA','GetQuocGiaAction',DmQuocGia);
    getCatalogFromServer_new('DM_NHA_CUNG_CAP','GetNoiBanAction',DmNhaCungCap);
    getCatalogFromServer_new('DM_NGUON_KINH_PHI','GetKinhPhiAction',DmNguonKinhPhi);
    getCatalogFromServer_new('DT_DM_NHAN_VIEN','GetNhanVienAction',DtDmNhanVien);
    getCatalogFromServer_new('DM_THUOC','GetDanhMucThuocAction',DmThuoc);
    getCatalogFromServer_new('DM_NGUON_CHUONG_TRINH','GetNguonAction',DmNguonChuongTrinh);
    getCatalogFromServer_new('DM_KHOA','GetKhoaAction',DmKhoa);
    getCatalogFromServer_new('DT_DM_KHO','GetKhoAction', DtDmKho);
    getCatalogFromServer_new('DM_LOAI_THUOC','GetLoaiAction',DmLoaiThuoc);
    getCatalogFromServer_new('DM_NHA_SAN_XUAT','GetHangSanXuatAction',DmNhaSanXuat);
    getCatalogFromServer_new('DM_DON_VI_TINH','GetDonViTinhAction',DmDonViTinh);
    
    getCatalogFromServer_new('DM_DAN_TOC','GetDanTocAction', DmDanToc);
    getCatalogFromServer_new('DM_TINH','GetTinhAction', DmTinh);
    getCatalogFromServer_new('DM_HUYEN','GetHuyenAction', DmHuyen);
    getCatalogFromServer_new('DM_XA','GetXaAction', DmXa);
    
    getCatalogFromServer_new('DM_NGHE_NGHIEP','GetDmNgheAction', DmNgheNghiep);
    getCatalogFromServer_new('DM_BENH_ICD','GetBenhIcdAction', DmBenhIcd);
    getCatalogFromServer_new('DT_DM_PHAU_THUAT','GetDtDmPhauThuatAction', DtDmPhauThuat);
    getCatalogFromServer_new('DT_DM_VO_CAM','GetDtDmVoCamAction', DtDmVoCam);
    getCatalogFromServer_new('DM_DM_CLS','GetDtDmClsAction', DtDmCls);
    getCatalogFromServer_new('DM_DOI_TUONG','GetDoiTuongAction',DmDoiTuong);
    
	getCatalogFromServer_new('DT_DM_KCB_BHYT','GetKCBBHYTAction', DtDmKcbBhyt);
   	getCatalogFromServer_new('DT_DM_TIEN_KHAM','GetTienKhamAction', DtDmTienKham);
    getCatalogFromServer_new('DT_DM_BAN_KHAM','GetBanKhamAction', DtDmBanKham);
   	getCatalogFromServer_new('DT_DM_TINH_BHYT','GetDtDmTinhAction', DtDmTinhBhyt);
    getCatalogFromServer_new('DT_DM_KHOI_BHYT','GetDtdmKhoiAction', DtDmKhoiBhyt);
    getCatalogFromServer_new('DM_BENH_VIEN','GetBenhVienAction', DmBenhVien);
    getCatalogFromServer_new('DM_PHUONG_THUC_GAY_TAI_NAN','GetPhuongTienAction', DmPhuongThucGayTaiNan);
    
    getCatalogFromServer_new('DM_PHAN_LOAI_TAI_NAN','GetDmTaiNanAction',DmPhanLoaiTaiNan);
    
    getCatalogFromServer_new('DM_DIEU_TRI','GetDieuTriAction',DmDieuTri);
    getCatalogFromServer_new('DT_DM_HUONG_DT','GetHuongDieuTriAction',DtDmHuongDt);
    getCatalogFromServer_new('DT_DM_NOI_SINH','GetDmNoiSinhAction',DtDmNoiSinh);
    getCatalogFromServer_new('DT_DM_LY_DO_CV','GetDmLyDoCVAction',DtDmLyDoCv);

    getCatalogFromServer_new('DM_PHAN_LOAI_THUOC','GetDmPhanLoaiAction',DmPhanLoaiThuoc);
    getCatalogFromServer_new('DT_DM_CLS_BANG_GIA','GetDmKyThuatAction',DtDmClsBangGia);
    
    getCatalogFromServer_new('DT_DM_KET_QUA','GetKQDieuTriAction',DtDmKetQua);

 	getCatalogFromServer_new('DT_DM_CLS','GetDtDmClsAction',DtDmCls);
	getCatalogFromServer_new('DT_DM_PB_CLS','GetDtDmPbClsAction',DtDmPbCls);
	
	getCatalogFromServer_new('DM_DIA_DIEM','GetDmDiaDiemAction',DmDiaDiem);
	//getCatalogFromServer_new('DT_DM_CLS_BANG_GIA','GetGiaPhongAction',DtDmClsBangGia);
	
	
	DmLoaiXoaHoSoBenhAn.createTable();
    getCatalogFromServer_new('DM_LOAI_XOA_HO_SO_BENH_AN','GetLoaiXoaHoSoBenhAnAction', DmLoaiXoaHoSoBenhAn);
  

    DtDmLoaiCapNhatMienGiam.createTable();
    getCatalogFromServer_new('DT_DM_LOAI_CAP_NHAT_MIEN_GIAM','GetLoaiCapNhatMienGiamAction', DtDmLoaiCapNhatMienGiam);
  
  
  
    DmCachDungThuoc.createTable();
    getCatalogFromServer_new('DM_CACH_DUNG_THUOC','GetDmThuocCachDungAction', DmCachDungThuoc);
    
    */
    DmDieuTriHSBA.createTable();
    getCatalogFromServer_new('DM_DIEU_TRI_HSBA','GetDmDieuTriHSBAAction', DmDieuTriHSBA);
    DmDieuTriHSBA.createTable();
    getCatalogFromServer_new('DT_DM_LOAI_PHAU_THUAT','GetDtDmLoaiPhauThuatAction', DtDmLoaiPhauThuat);
}


function getObject(name) {
	
	switch (name) {
		
		case "DT_DM_PL_BHYT":
		   return DtDmPlBhyt;
	    case "DM_KET_QUA_DIEU_TRI":
	       return DmKetQuaDieuTri;
	       
	    case "DT_DM_CLIENT_DEFAULT":
	       return DtDmClientDefault;	   
	       
	    case "DT_DM_TUYEN_KCB":
	    	return DtDmTuyenKcb;   
	       
	    case "DM_DIEU_TRI_HSBA":
	       return DmDieuTriHSBA;
	       
	    case "DT_DM_LOAI_PHAU_THUAT":
	       return DtDmLoaiPhauThuat;
	    case "DM_CACH_DUNG_THUOC":
	       return DmCachDungThuoc; 
	    case "DM_LOAI_XOA_HO_SO_BENH_AN":
			return DmLoaiXoaHoSoBenhAn;
		case "DT_DM_CUM":
			return DtDmCum;
		case "DT_DM_LOAI_CAP_NHAT_MIEN_GIAM":
			return DtDmLoaiCapNhatMienGiam;	
		case "DM_QUOC_GIA":
			return DmQuocGia;
		case "DM_NHA_CUNG_CAP":
			return DmNhaCungCap;
		case "DM_NGUON_KINH_PHI":
			return DmNguonKinhPhi;
		case "DT_DM_NHAN_VIEN":
			return DtDmNhanVien;
		case "DM_THUOC":
			return DmThuoc;
		case "DM_NGUON_CHUONG_TRINH":
			return DmNguonChuongTrinh;
		case "DT_DM_KHO":
			return DtDmKho;
		case "DM_LOAI_THUOC":
			return DmLoaiThuoc;
		case "DM_NHA_SAN_XUAT":
			return DmNhaSanXuat;
		case "DM_DON_VI_TINH":
			return DmDonViTinh;
		case "DM_TINH":
			return DmTinh;
		case "DM_HUYEN":
			return DmHuyen;
		case "DM_XA":
			return DmXa;
		case "DM_DAN_TOC":
			return DmDanToc;
		case "DT_DM_BAN_KHAM":
			return DtDmBanKham;
		case "DM_NGHE_NGHIEP":
			return DmNgheNghiep;
		case "DM_BENH_ICD":
			return DmBenhIcd;
		case "DM_DOI_TUONG":
			return DmDoiTuong;
		case "DM_PHUONG_THUC_GAY_TAI_NAN":
			return DmPhuongThucGayTaiNan;
		case "DM_PHAN_LOAI_TAI_NAN":
			return DmPhanLoaiTaiNan;
		case "DM_BENH_VIEN":
			return DmBenhVien;
		
		case "DT_DM_HUONG_DT":
			return DtDmHuongDt;
		case "DM_KHOA":
			return DmKhoa;
		case "DT_DM_LY_DO_CV":
			return DtDmLyDoCv;
		case "DM_DIEU_TRI":
			return DmDieuTri;
		case "DM_PHAN_LOAI_THUOC":
		    return DmPhanLoaiThuoc;
		case "DT_DM_NOI_SINH":
		    return DtDmNoiSinh; 
		case "DT_DM_KCB_BHYT":
		    return DtDmKcbBhyt;   
		case "DT_DM_CLS":
		    return DtDmCls;      
		case "DT_DM_PB_CLS":
		    return DtDmPbCls;  
		case "DT_DM_CLS_BANG_GIA":
		    return DtDmClsBangGia; 
		case "DM_DIA_DIEM":
		    return DmDiaDiem;      
		case "DT_DM_TIEN_KHAM":
		    return DtDmTienKham;
		case "DT_DM_VO_CAM":
		    return DtDmVoCam;  
		case "DT_DM_PHAU_THUAT":
		    return DtDmPhauThuat;   
		case "DT_DM_KHOI_BHYT":
		    return DtDmKhoiBhyt;   
		case "DM_LOAI_BAO_CAO_HSBA_DANG_CN":
		    return DmLoaiBaoCaoHSBADangCN;  
		case "DT_DM_TINH_BHYT":
		    return DtDmTinhBhyt;
		case "DM_TAI_NAN":
		    return DmTaiNan;
		case "DT_DM_CAP_CUU_PHIEN":
		    return DtDmCapCuuPhien;    
		case "DT_DM_LOAI_MIEN":
		    return DtDmLoaiMien;
		 case "DT_DM_DIEN_MIEN":
		    return DtDmDienMien;
		 case "DM_LOAI_SINH":
		    return DmLoaiSinh;      
		 case "DT_DM_NHOM_BHYT":
		    return DtDmNhomBhyt;
		 case "DT_DM_TRAM_Y_TE_BHYT":
		    return DtDmTramYTeBhyt;    
		 case "DT_DM_CHE_DO_AN":
		    return DtDmCheDoAn;
		 case "DT_DM_LOAI_AN":
			    return DtDmLoaiAn;
		 case "DT_DM_LOAI_AN2":
			    return DtDmLoaiAn2;	    
		 case "DT_DM_MUC_AN":
			    return DtDmMucAn;
		 case "DT_DM_DOI_TUONG_AN":
			    return DtDmDoiTuongAn;
		 case "DT_DM_DONG_THEM":
			    return DtDmDongThem;
		 case "DT_DM_NHASX":
			    return DtDmNhaSxSpdd;
		 case "DT_DM_LOAITP":
			    return DtDmLoaiTp;	    			    	    
		 case "DT_DM_PHONG":
			    return DtDmPhong;
		 case "DT_DM_CHI_DAN":
			    return DtDmChiDan; 
		 case "DT_DM_LOI_DAN":
			    return DtDmLoiDan; 
		 case "CAU_HINH":
		        return CauHinh;	    
		 case "DM_BAI_THUOC":
			return DmBaiThuoc;
		 case "DT_DM_BUONG":
				return DtDmBuong;	
			
		default:
		    //alert("error at getObject():" + name);
			return null;
	}
	return null;
}

function getArrayMaSo(table, field, value){
	var maso = new Array();
	var obj = getObject(table);
	if (obj != null) {
		var listObj = obj.filter(field + " like ? AND DT = 1 ", value).toArray();
		for (var i = 0; i < listObj.length; i++) {
			maso[i] = listObj[i].MaSo;
		}
	}
	return maso;
}

//function getMaso123456(a, b, c) {
    //alert(1);
	//var maso = "";
	//alert(table)
	//var obj = getObject(table);
	//alert(obj);
	//if (obj != null) {
		//var listObj = obj.filter(field + " = ?", value).toArray();
		//for (var i = 0; i < listObj.length; i++) {
		//	maso = listObj[i].MaSo;
		//}
	//}
//	return 1;
//}
function getMaSo(table,field,value){
 	var maso = "";
	//alert(table)
	var obj = getObject(table);
	//alert(obj);
	if (obj != null) {
		var listObj = obj.filter(field + " like ? AND DT = 1 ", value).toArray();
		for (var i = 0; i < listObj.length; i++) {
			maso = listObj[i].MaSo;
		}
	}
    return maso;
}
/*
function getMasoFromString(table, field, value) {
	var maso = "";
	var obj = getObject(table);
	if (obj != null) {
		var listObj = obj.filter(field + " = '" + value + "'").toArray();
		for (var i = 0; i < listObj.length; i++) {
			maso = listObj[i].MaSo;
		}
	}
	return maso;
}
*/
function myOnblurTextboxForPhanLoaiThuoc(textboxId,comboboxId,loai_value){
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var arr2 = comboboxId.split("__");
	var myTableName = "";
	if(arr2.length > 1){
		myTableName = arr2[0];
	} else {
	   myTableName = comboboxId;
	}
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("Ma like ? AND DT = 1 ", textboxValue ).filter("Loai like ? AND DT = 1 ", loai_value ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		var end = false;
		for (var i = 0; i < objectarr.length; i++) {
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", objectarr[i].Ten: " + objectarr[i].Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
			}
			catch(e){}
			return;
		}
	    
	    mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	if (mycombobox != null){
    		mycombobox.value = "";
    	}
    	
    }
    //alert(2);
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
 	//db.close();     
}

function myOnblurTextbox(textboxId,comboboxId){
	//alert("myOnblurTextbox");
	//alert("textboxId="+textboxId+";"+"comboboxId="+comboboxId);
	if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
   
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var myTableName = "";
	var at_prefix_component =  comboboxId.indexOf(prefix_component);
	if (at_prefix_component >= 0) {
		myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		if (myTableName.indexOf("__") > 0) {
		  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
		}
	} else {	
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		} else {
			var arr2 = comboboxId.split("__");
			if(arr2.length > 1){
				myTableName = arr2[0];
			} else {
			   myTableName = comboboxId;
			}
		}
	}	
	
	if(textboxValue != ""){
		//alert("textboxValue"+textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("Ma like ? AND DT = 1 ", textboxValue ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    try{
		    mytextbox.focus();
		    }
			catch(e){}
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	try{
    	mycombobox.value = "";
    	}catch(e){
    	
    	}
    		try{
    	mycombobox.innerHTML = "";
    	}catch(e){
    	
    	}
    	
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
	
           
 	//db.close();     
}


function myOnblurTextboxDmNoiDKKCBBD(textboxId,comboboxId,matinhbhyt){
	
		if(document.getElementById(textboxId).className.match(/focus/gi))  document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
   //alert(2);
	mytextbox = document.getElementById(textboxId);
	
	textboxValue = mytextbox.value;
	
	//alert(mytextbox);
	//alert(textboxId,comboboxId,matinhbhyt);
	//Duyen.LP kiem tra ma tinh DK KCB co trung voi tinh cap ko?
//	if (textboxValue.length > 2 && textboxValue.substring(0,2) != matinhbhyt){
//		document.getElementById(textboxId + DM_pk).value = "";
//		document.getElementById(textboxId).value = "";
//		document.getElementById(comboboxId).value = "";
    	
//		return;
//	}
	//alert( textboxValue.indexOf("."));
	if (textboxValue != null && textboxValue != '' && textboxValue.indexOf(".") < 0){
		document.getElementById(textboxId).value = matinhbhyt+ "." + textboxValue;
		//alert(matinhbhyt+ "." + textboxValue);
		textboxValue=matinhbhyt+ "." + textboxValue;
	}
	var myTableName = "";
	var at_prefix_component =  comboboxId.indexOf(prefix_component);
	if (at_prefix_component >= 0) {
		myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		if (myTableName.indexOf("__") > 0) {
		  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
		}
	} else {	
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		} else {
			var arr2 = comboboxId.split("__");
			if(arr2.length > 1){
				myTableName = arr2[0];
			} else {
			   myTableName = comboboxId;
			}
		}
	}	
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		
		
		// tim ma tinh -> maso
	var objArr = DmTinh.filter("MaTinhBHYT = \'" + matinhbhyt + "\'").toArray();
	var masotinh = null;
	if (objArr != null && objArr.length > 0){
		var tinh = objArr[0];
		masotinh = objArr[0].MaSo;
	}
	
	var objectarr = Object.filter("Ma like ? AND DT = 1 " , textboxValue ).toArray();
		//if (objectarr == null){
		//	objectarr = Object.filter("Ma like ? AND DT = 1 AND DMTINH_MASO = " + masotinh, matinhbhyt+ "."+textboxValue ).toArray();
		//}
		
		//alert(matinhbhyt+ "."+textboxValue);
		
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    try{
		    //mytextbox.focus();
		    }
			catch(e){}
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	try{
    	mycombobox.value = "";
    	}catch(e){
    	
    	}
    		try{
    	mycombobox.innerHTML = "";
    	}catch(e){
    	
    	}
    	
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
	
           
 	//db.close();     
}

function myOnblurTextboxTinhBHYT(textboxId,comboboxId){
	//alert(1);
		if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
   
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var myTableName = "";
	var at_prefix_component =  comboboxId.indexOf(prefix_component);
	if (at_prefix_component >= 0) {
		myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		if (myTableName.indexOf("__") > 0) {
		  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
		}
	} else {	
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);		
		} else {
			var arr2 = comboboxId.split("__");
			if(arr2.length > 1){
				myTableName = arr2[0];
			} else {
			   myTableName = comboboxId;
			}
		}
	}	
	//alert(textboxValue);
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("MaTinhBHYT like ? AND DT = 1 ", textboxValue ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    try{
		    mytextbox.focus();
		    }
			catch(e){}
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	try{
    	mycombobox.value = "";
    	}catch(e){
    	
    	}
    		try{
    	mycombobox.innerHTML = "";
    	}catch(e){
    	
    	}
    	
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
	
           
 	//db.close();     
}


function myOnblurTextboxForDmThuoc(textboxId,comboboxId, loai_value, phanloai_value){
  try{
  
  if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
   
	var mytextbox = document.getElementById(textboxId);
	//alert(mytextbox);
	var textboxValue = mytextbox.value;
	
	var mycombobox = document.getElementById(prefix_component + comboboxId);
	if (mycombobox == null){
	   mycombobox = document.getElementById( comboboxId);
	}
	//alert("comboboxId1:"+comboboxId);
	var havingData = false;
	if(textboxValue != ""){
		//alert(mytextbox);
		
		//var pos = textboxValue.indexOf("_");
		//if (pos  > 0){
		//	textboxValue = textboxValue.substring(0,pos) + "\\" + textboxValue.substring(pos); 
		//}	
		
		if (mycombobox == null || mycombobox == ""){
			mycombobox = document.getElementById( comboboxId);
		}
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var arr2 = myTableName.split(":");
		
		//alert(comboboxIdJSF);
		if(arr2.length > 1){
	       myTableName = arr2[arr2.length  - 1];
		}else{
		   myTableName = arr2[0];
		}
		
		
		var arr2 = myTableName.split(":");
		
		//alert(comboboxIdJSF);
		if(arr2.length > 1){
	       myTableName = arr2[arr2.length  - 1];
		}else{
		   myTableName = arr2[0];
		}
		var dmthuocArray = null;
		if (loai_value == null || loai_value == "") {
			if (phanloai_value == null || phanloai_value == ""){
				dmthuocArray =   DmThuoc.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			} else {
				var phanLoaiMaSo = getMaSo("DM_PHAN_LOAI_THUOC","Ma",phanloai_value);
				dmthuocArray =   DmThuoc.filter("Ma like ? AND DT = 1 ", textboxValue).filter("DMPHANLOAITHUOC_MASO like ?", phanLoaiMaSo).toArray();
			}			
		} else {
			if (phanloai_value == null || phanloai_value == "") {
				var loai = DmLoaiThuoc.getByFieldValue("Ma", loai_value);
				//alert("loai: " + loai.Ten);
				var listPl = DmPhanLoaiThuoc.filter("DMLOAITHUOC_MASO = ? AND DT = 1 ", loai.MaSo).toArray();
				//alert(listPl);
				var query = "(";
				for (var i = 0; i < listPl.length; i++) {
					if (i == 0) {
						query += listPl[i].MaSo;
					} else {
						query += ", " + listPl[i].MaSo;
					}
				}
				query += ")";
				//alert(query);
				dmthuocArray = DmThuoc.filter("Ma like ? AND DT = 1 ", textboxValue).filter("DMPHANLOAITHUOC_MASO IN " + query).toArray();
			} else {
				var phanLoaiMaSo = getMaSo("DM_PHAN_LOAI_THUOC","Ma",phanloai_value);
				dmthuocArray =   DmThuoc.filter("Ma like ? AND DT = 1 ", textboxValue).filter("DMPHANLOAITHUOC_MASO like ?", phanLoaiMaSo).toArray();
			}		
		}
		
		//
		if(textboxValue != null && textboxValue != "" && dmthuocArray != null && dmthuocArray.length > 0){
			var temp_array = new Array();
			
			var pos = 0;
			
			for (var i = 0; i < dmthuocArray.length; i++) {
				var dmthuoc = dmthuocArray[i];
				
				
				if (dmthuoc.Ma.toUpperCase() != textboxValue.toUpperCase()){
					continue;
				}
				//alert(dmthuoc.Ma + " " + textboxValue);
				temp_array[pos] = dmthuoc;
				pos  = pos + 1;
			}
			dmthuocArray = temp_array;
		}
		
	   // var search = dijit.byId(myCombobox.id);
		//var jsonData = { identifier: "id", items: [], label: "title" };
		//var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		//CatalogStore.newItem({id: "", title: ""});
	    if ( dmthuocArray != null ) {
	    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
	    	 	 mycombobox.value = dmthuocArray[i].Ten;
	    	 	 try{
					document.getElementById(textboxId + DM_pk).value = dmthuocArray[i].MaSo;
				}
				catch(e){}
		        havingData = true;
		        return;
	            
	         }
	    }
		
	    //alert(2);
	    if(dmthuocArray == null){
	    	mytextbox.alt = "";
	    }
	    
	    mycombobox.value = "";   
	    //alert(5);
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
    }else{
        //alert("mycombobox:"+mycombobox);
        //if (mycombobox != null){
       	//  mycombobox.value = "";
        //}
        
        //alert();
    }
    if(havingData == false ){
       mycombobox.value = "";
       try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
    
  }catch(e){
    alert("myOnblurTextboxForDmThuoc():"+e.description);
  }  
      
}



function myOnblurTextboxForDtDmKyThuatExt(textboxId,comboboxId,chuDau,MaKhoa){
  try{
  
  	if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
  	
	mytextbox = document.getElementById(textboxId);
	//alert(mytextbox);
	textboxValue = mytextbox.value;
	var havingData = false;
	if(textboxValue != ""){
		//alert(mytextbox);
		mycombobox = document.getElementById(prefix_component + comboboxId);
		if (mycombobox == null){
			 mycombobox = document.getElementById( comboboxId);
		}
		//alert(mycombobox);
		
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\' and loai like '" + loai_value + "'");
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		
		
		
		
		var Object = getObject(comboboxId);
		var end = false;
		var objArr ;
		 
		
		if (chuDau == null || chuDau == ""){
		
			var  arrayDtDmClsMaSo = getDtDmClsMaSoFromMaKhoa(MaKhoa); // co' dang:      1,3,4,6
			
			if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
				objArr = Object.filter("Ma like ?  AND DT = 1 ", textboxValue).toArray();
			}else{
				objArr = Object.filter("Ma like ?  AND DT = 1 and MaSo in ("+arrayDtDmClsMaSo+")" , textboxValue).toArray();
			}
			
		    
		}else{
		    //alert(123);
			var myMaso = getMaSo("DT_DM_CLS","Ma",chuDau);
			
			var  arrayDtDmClsMaSo = getDtDmClsMaSoFromMaKhoa(MaKhoa); // co' dang:      1,3,4,6
			//alert(myMaso)
			//alert(textboxValue)
			//alert(Object)
			//alert(arrayDtDmClsMaSo)
			if (myMaso != null && myMaso != ""){
				if (textboxValue != null && textboxValue != ""){
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						objArr = Object.filter("Ma like ?  AND DT = 1 ", textboxValue).filter ("DTDMCLSBG_MALOAI  = ?  " , myMaso).toArray();
					
					}else{
						objArr = Object.filter("Ma like ?  AND DT = 1 ", textboxValue).filter ("DTDMCLSBG_MALOAI  = ?  and MaSo in ("+arrayDtDmClsMaSo+")" , myMaso).toArray();
					
					}
					
				}else{
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						objArr = Object.filter ("DTDMCLSBG_MALOAI  = ?   AND DT = 1  " , myMaso).toArray();
					}else{
						objArr = Object.filter ("DTDMCLSBG_MALOAI  = ?   AND DT = 1   and MaSo in ("+arrayDtDmClsMaSo+")" , myMaso).toArray();
					}
				}
			}else{
				if (textboxValue != null && textboxValue != ""){
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						objArr = Object.filter("Ma like ?  AND DT = 1  ", textboxValue).toArray();
					}else{
						objArr = Object.filter("Ma like ?  AND DT = 1    and MaSo in ("+arrayDtDmClsMaSo+")", textboxValue).toArray();
					}
				}else{
					if (arrayDtDmClsMaSo == null || arrayDtDmClsMaSo == ''){
						objArr = Object.filter("DT = 1  ").toArray();
					}else{
						objArr = Object.filter("DT = 1    and MaSo in ("+arrayDtDmClsMaSo+")", textboxValue).toArray();
					}
				}
			}
			
			
			
			//alert(objArr.length)
		}
		
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
			havingData = true;
			try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){
			
			}
			
		}
		if (havingData == true){
      		return;
    	}
		
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
		}
		catch(e){}
			
						
		mytextbox.alt = "";
		mycombobox.value = "";   
	    mytextbox.value = "";		
	    mytextbox.focus();
		
    }
  }catch(e){
    alert("myOnblurTextboxForDtDmKyThuat():"+e.description);
  }  
      
}

function myOnblurTextboxForDtDmKyThuat(textboxId,comboboxId,chuDau){
  try{
  
  	if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
  	
	mytextbox = document.getElementById(textboxId);
	//alert(mytextbox);
	textboxValue = mytextbox.value;
	var havingData = false;
	if(textboxValue != ""){
		//alert(mytextbox);
		mycombobox = document.getElementById(prefix_component + comboboxId);
		if (mycombobox == null){
			 mycombobox = document.getElementById( comboboxId);
		}
		//alert(mycombobox);
		
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\' and loai like '" + loai_value + "'");
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		
		
		
		
		var Object = getObject(comboboxId);
		var end = false;
		var objArr ;
		 
		
		if (chuDau == null || chuDau == ""){
		    objArr = Object.filter("Ma like ?  AND DT = 1 ", textboxValue).toArray();
		}else{
		    //alert(123);
			var myMaso = getMaSo("DT_DM_CLS","Ma",chuDau);
			
			
			//alert(myMaso)
			//alert(textboxValue)
			//alert(Object)
			if (myMaso != null && myMaso != ""){
				if (textboxValue != null && textboxValue != ""){
					objArr = Object.filter("Ma like ?  AND DT = 1 ", textboxValue).filter ("DTDMCLSBG_MALOAI  = ?  " , myMaso).toArray();
				}else{
					objArr = Object.filter ("DTDMCLSBG_MALOAI  = ?   AND DT = 1 " , myMaso).toArray();
				}
			}else{
				if (textboxValue != null && textboxValue != ""){
					objArr = Object.filter("Ma like ?  AND DT = 1    ", textboxValue).toArray();
				}else{
					objArr = Object.filter("DT = 1").toArray();
				}
			}
			//alert(objArr.length)
		}
		
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
			havingData = true;
			try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){
			
			}
		}
		if (havingData == true){
      		return;
    	}
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
						
			mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";		
		    mytextbox.focus();
		
    }
  }catch(e){
    alert("myOnblurTextboxForDtDmKyThuat():"+e.description);
  }  
      
}

function myOnblurTextbox_Huyen_Xa(textboxId,comboboxId,father_id){  //father_id = TINH_MA or father_id = HUYEN_MA 
  try{
    
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){

		mycombobox = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)

		//thanh added
		fathertextbox = document.getElementById(father_id);
		value_father = fathertextbox.value;
		if (value_father == null || value_father == ""){
			mytextbox.value = "";
			//document.getElementById( father_id).focus();
			return;	
		}
		//end thanh added
		
		//alert("value_father:"+value_father);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("Ma like ? and RefMa = ? AND DT = 1 ", textboxValue, value_father).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
	        //alert("mycombobox.value:" + mycombobox.value);
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
				}
				catch(e){}
		    mytextbox.focus();
		/*
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		//var rs = db.execute("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'" + "and RefMa=\'" + value_father + "\'");
	    //alert("mycombobox:" + mycombobox);
	    while (rs.isValidRow()) {
	        mycombobox.value = rs.fieldByName("Ten");
	        //alert("mycombobox.value:" + mycombobox.value);
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        //rs.next();
	    }
	    //alert("rs:" + rs);
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }
	    rs.close();
	    mycombobox.value = "";   
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    mytextbox.focus();
	    */
    }
     
  }catch(e){
    alert("myOnblurTextbox_Huyen_Xa():"+e);
  }    
}


function myOnblurTextbox1(textboxId,comboboxId){
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		
		myCombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var end = false;
		var objArr = Object.filter("MATHANG_MA like ? AND DT = 1 ", textboxValue).toArray();
		for (var i = 0; i < objArr.length; i++) {
			listCatalog1(myCombobox);
			return;
		}
		
		myCombobox.value = "";   
	    	mytextbox.value="";
	   		mytextbox.focus();
		/*
		var rs = db.execute("select * from " + comboboxId + " where MATHANG_MA like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        //myCombobox1.value = rs.fieldByName("Ten");
	        listCatalog1(myCombobox);
	        return;
	        rs.next();
	    }
	    rs.close();
	    myCombobox.value = "";   
	    mytextbox.value="";
	    mytextbox.focus();
	    */
    }
      
}

function listCatalog1(myCombobox){
/*
	try{
	   	rs = db.execute("SELECT * FROM " + myCombobox.id + " WHERE Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	   	
	   }
	   catch(e){}
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myCombobox.id);
	var end = false;
	var objArr = Object.filter(" DT = 1 AND Ten like \'" + myCombobox.getDisplayedValue() + "%\'").toArray();
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].MATHANG_MA, 
							title: objArr[i].MATHANG_MASO + objArr[i].MATHANG_MA + 
									objArr[i].MATHANG_TEN + objArr[i].MATHANG_DONGIA + objArr[i].MATHANG_TON});
	}
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MATHANG_MA"), title: rs.fieldByName("MATHANG_MASO") + rs.fieldByName("MATHANG_MA") + 
		rs.fieldByName("MATHANG_TEN") + rs.fieldByName("MATHANG_DONGIA") + rs.fieldByName("MATHANG_TON")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}


function myOnblurTextboxJSF(textboxId,comboboxId){
  try{
	mytextbox = document.getElementById(textboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1]; 
	
	//alert(comboboxId);
	
	textboxValue = mytextbox.value;	

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	//alert(comboboxIdJSF);
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		    	 myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	
	if(textboxValue != ""){
        //alert(1);
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
		for (var i = 0; i < objArr.length; i++) {
		    var obj = objArr[i];
			mycombobox.value = obj.Ten;
			mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = obj.MaSo;
			}
			catch(e){}
			return;
		}
		
		//alert(1);
		/*
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        mycombobox.value = rs.fieldByName("Ten");
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        //rs.next();
	    }
	    */
	    mytextbox.alt = "";
	    	mycombobox.value = "";   
		    mytextbox.value = "";
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
				}
				catch(e){}
		    mytextbox.focus();
	    
    }else{
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
    
    }catch(e){
      alert(e + "1");
    }
      
}

function myOnblurTextboxJSF_Huyen_Xa(textboxId,comboboxId,father_id,DM_MASO) {  //father_id = TINH_MA or father_id = HUYEN_MA 

	if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   

	mytextbox = document.getElementById(textboxId);
	//thanh added
	fathertextbox = document.getElementById(prefix_component + father_id);
	value_father = fathertextbox.value;
	if (value_father == null || value_father == ""){
		mytextbox.value = "";
		return;	
	}
	//end thanh added
	
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1]; 
	textboxValue = mytextbox.value;	

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		    	 myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	
	if(textboxValue != "") {
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(myTableName);
		var end = false;
		var maso = "";
		var listObj = null;
		if (DM_MASO == "DMTINH_MASO") {
			listObj = DmTinh.filter("Ma = ? AND DT = 1 ", value_father).toArray();
		} else {
			listObj = DmHuyen.filter("Ma = ? AND DT = 1 ", value_father).toArray();
		}
		
		for (var i = 0; i < listObj.length; i++) {
			maso = listObj[i].MaSo;
		}
		
		var objArr = Object.filter(" DT = 1  AND Ma like '%" + textboxValue + "'" + " and "+ DM_MASO +"='" + maso + "'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
			mycombobox.select();
			//alert(objArr[i].Ten);
	        try {
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";
		    try {
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    mytextbox.focus();
    }
}

function myOnblurComboboxWithNoDatabase(textboxId,comboboxId){
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert(comboboxValue);
	mytextbox = document.getElementById(textboxId);
	//alert("mytextbox:"+mytextbox);
	if(comboboxValue != null && comboboxValue != ""){
				
		if ( arrayValueReceivedFromServer != null ) {
			var x=0;
			for (x=0; x<50; x++)
			{
				if (arrayValueReceivedFromServer[x] == null || x >= 20){
					return;
				}
				//alert(arrayValueReceivedFromServer[x]);
				if (comboboxValue.trim() == arrayValueReceivedFromServer[x].trim() ){
					mytextbox.value = arrayIDReceivedFromServer[x];
					//alert("ok");
				}
			} 
			 
		}
	}else{
		
		if ( arrayValueReceivedFromServer != null ) {
			
			
			
			if (arrayValueReceivedFromServer[0] != null){
					mytextbox.value = arrayIDReceivedFromServer[0];
			}else{
				if (arrayValueReceivedFromServer[1] != null){
						mytextbox.value = arrayIDReceivedFromServer[1];
				}
			}
			
		}
	}
}




function myOnblurComboboxForTinhBHYT(textboxId,comboboxId){
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 ", comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			//comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.value = arrayObj[i].MaTinhBHYT;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}


function myOnblurComboboxForHuyen(textboxId,comboboxId){
	//
	
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		
		
		var maSoTinh = getMaSo("DM_TINH","Ma",document.getElementById(prefix_component + 'TINH_MA').value);
		
		
		
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 AND DMTINH_MASO = " + maSoTinh, comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			//comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMTINH_MASO = " + maSoTinh, comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMTINH_MASO = " + maSoTinh, comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMTINH_MASO = " + maSoTinh, comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.value = arrayObj[i].Ma;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}

function myOnblurComboboxForXa(textboxId,comboboxId){
	//
	
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		var maSoHuyen = getMaSo("DM_HUYEN","Ma",document.getElementById(prefix_component + 'HUYEN_MA').value);
	
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 AND DMHUYEN_MASO = " + maSoHuyen, comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			//comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMHUYEN_MASO = " + maSoHuyen, comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMHUYEN_MASO = " + maSoHuyen, comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1  AND DMHUYEN_MASO = " + maSoHuyen, comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.value = arrayObj[i].Ma;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}



function myOnblurComboboxForNoOnBlur(textboxId,comboboxId){
	//alert("myOnblurComboboxForNoOnBlur");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 ", comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			//comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 ////mytextbox.value = arrayObj[i].Ma;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				////document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    //if ( arrayObj == null || arrayObj.length == 0) {
		//	mytextbox.alt = "";
		//}
	   	//mycombobox.focus();
	    //mycombobox.value = "";	
	    //try{
		//		////document.getElementById(textboxId + DM_pk).value = "";
		//	}
		//	catch(e){}
	       
    }
    //else {
    //	//alert(1000);
    //	mytextbox.alt = "";
    //	try{
	//			////document.getElementById(textboxId + DM_pk).value = "";
	//		}
	//		catch(e){}
    //}
   
}

function myOnblurCombobox(textboxId,comboboxId){
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	
	
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 ", comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.value = arrayObj[i].Ma;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}


function myOnblurComboboxKCBBHYT(textboxId,comboboxId){
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = (mycombobox.value).trim();
	//alert("comboboxValue:"+comboboxValue);
	mytextbox = document.getElementById(textboxId);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		var at_prefix_component =  comboboxId.indexOf(prefix_component);
		if (at_prefix_component >= 0) {
			myTableName = comboboxId.substring(at_prefix_component + prefix_component.length);
			if (myTableName.indexOf("__") > 0) {
			  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
			}		
		} else {
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert(myTableName);
		}	
		
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		
		
		var matinhbhyt = document.getElementById(prefix_component + 'TINHBHYT_MA').value;
		// tim ma tinh -> maso
	var objArr = DmTinh.filter("MaTinhBHYT = \'" + matinhbhyt + "\'").toArray();
	var masotinh = null;
	if (objArr != null && objArr.length > 0){
		var tinh = objArr[0];
		masotinh = objArr[0].MaSo;
	}
	
	//alert(matinhbhyt);
	//alert(masotinh);
	
		
		//alert("comboboxValue:"+comboboxValue);
		comboboxValue = comboboxValue.trim();
		var arrayObj =   obj.filter("trim(Ten) like ? AND DT = 1 and DMTINH_MASO = " + masotinh, comboboxValue).toArray();
		//alert("arrayObj1:"+arrayObj);
		if (arrayObj == null || arrayObj.length == 0){
			//comboboxValue = " " + comboboxValue;
			arrayObj =   obj.filter("Ten like ? AND DT = 1 and DMTINH_MASO = " + masotinh, comboboxValue).toArray();
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = comboboxValue + " ";
				arrayObj =   obj.filter("Ten like ? AND DT = 1 and DMTINH_MASO = " + masotinh, comboboxValue).toArray();
			}
			if (arrayObj == null || arrayObj.length == 0){
				comboboxValue = " " + comboboxValue;
				arrayObj =   obj.filter("Ten like ? AND DT = 1 and DMTINH_MASO = " + masotinh, comboboxValue).toArray();
			}
		}
		//alert(arrayObj);
		//alert("2:"+ comboboxValue );
		//alert("3:"+process_searchText(comboboxValue));
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.value = arrayObj[i].Ma;
			 //alert( arrayObj[i].Ma);
			//alert(arrayObj[i].MaSo);
			 //alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
				//alert(document.getElementById(textboxId + DM_pk).value);
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}

function getTrueNameOnPortal(myname){
   		
   		var arr1 = myname.split(":");
		if(arr1.length > 1){
	       myTableName = arr1[arr1.length - 1];
		}else{
		   myTableName = arr1[0];
		}	
		
		
}
//thanhdo
function myOnblurComboboxForDmThuoc(textboxId,comboboxId, loai_value, phanloai_value){

	//alert("comboboxId:"+comboboxId);
	
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){

		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		mytextbox = document.getElementById(textboxId);
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		if (loai_value == null || loai_value == ""){
			loai_value = "%";
		}
		if (phanloai_value == null || phanloai_value == ""){
			phanloai_value = "%";
		}
		
		arr1 = comboboxId.split(":");
		if(arr1.length > 1){
	       myTableName = arr1[arr1.length - 1];
		}else{
		   myTableName = arr1[0];
		}		
	/*
		var strSql = "select * from " + myTableName + " where Ten like \'" + comboboxValue + "\' and loai like '" + loai_value + "'";
		strSql = strSql + " and ( ploai1 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai2 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai3 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai4 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai5 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai6 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai7 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai8 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai9 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai10 like '" + phanloai_value +"')";	
		*/
		var strSql = "Ten like \'" + comboboxValue + "\' and loai like '" + loai_value + "'";
		strSql = strSql + " and ( ploai1 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai2 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai3 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai4 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai5 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai6 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai7 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai8 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai9 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai10 like '" + phanloai_value +"')";	
		//alert(strSql);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter(strSql).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].Ma;
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";	
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		/*
		rs = db.execute(strSql);

	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    rs.close();	    
	    */
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
      
}
function myOnblurCombobox_2(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){

		mytextbox = document.getElementById(textboxId);
		
		var Object = getObject(comboboxId);
		var end = false;
		var objArr = Object.filter(" DT = 1  AND Ten like \"" + comboboxValue + "\"").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].Ma;
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo;
			}
			catch(e){}
			if (mytextbox.type == "text") mytextbox.focus();
	        return;
		}
		
		mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";	
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		/*
		var rs = db.execute("select * from " + comboboxId + " where Ten like \"" + comboboxValue + "\"");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
			if (mytextbox.type == "text") mytextbox.focus();
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    rs.close();	 
	    */   
    }
      
}

/*thanh do add here for B3111*/
function setAttrForCombobox_MATHANG(textboxId,mySpan,comboboxId,pageSize,GetThuocAndPhongAction){
  try{
    //alert("begin setAttrForCombobox_MATHANG()");
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	// alert("begin setAttrForCombobox_MATHANG()");
	var myComboboxId = document.getElementById( prefix_component + comboboxId);
	//alert("begin setAttrForCombobox_MATHANG3():"+myComboboxId);
	//var myTextboxId = document.getElementById(textboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	// alert("begin setAttrForCombobox_MATHANG4()");
	myComboboxId.invalidMessage="";
	// alert("begin setAttrForCombobox_MATHANG5()");
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	//alert("begin setAttrForCombobox_MATHANG()");
	myComboboxId.onblur=" myOnblurCombobox_callAjax(\'" + textboxId + "\',\'" + comboboxId + "\',\'" + GetThuocAndPhongAction +  "\'); assignAltToValue(\'" + textboxId + "\')";
	//alert("begin setAttrForCombobox_MATHANG()");
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
  }catch(e){
  
    alert("setAttrForCombobox_MATHANG():"+e);
  }	
}
/*thanh do add here for B3111*/
function myOnblurCombobox_callAjax(textboxId, comboboxId, GetThuocAndPhongAction){
	//alert("myOnblurCombobox_callAjax");
	try {
		var myform = document.forms[0];
		var validate = iesvn_ValidateRequired(myform);
		if (validate == false) {
	  		return;
		}
	
		mycombobox = document.getElementById(prefix_component + comboboxId);
		comboboxValue = mycombobox.value;
		if(comboboxValue != "") {

			mytextbox = document.getElementById(textboxId);
			//alert("comboboxId:"+comboboxId);
			var Object = getObject(comboboxId);
			var end = false;
			var temp = "";
			
			var objArr = Object.filter("Ten like ? AND DT = 1 ", comboboxValue).toArray();
			for (var i = 0; i < objArr.length; i++) {
				mytextbox.alt = objArr[i].Ma;
				temp = objArr[i].Ma;
	        	sendId_MAHANG(objArr[i].Ma, GetThuocAndPhongAction);
				return;
			}
		
			mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";
		/*
		//alert("mytextbox:" + mytextbox);
		//alert("select:" + "select * from " + comboboxId + " where Ten=\"" + comboboxValue + "\"");
		var rs = db.execute("select * from " + comboboxId + " where Ten like \"" + comboboxValue + "\"");
		//alert("rs:" + rs);
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        //alert("rs.fieldByName:" +rs.fieldByName("MaSo"));
	        sendId_MAHANG(rs.fieldByName("MaSo"),GetThuocAndPhongAction);
	        //alert(1);
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    rs.close();	    
	    */
    	}
   	} catch(e) {
     	alert("myOnblurCombobox_callAjax:" + e.description);
   	}   
}
function myOnblurComboboxJSF(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	//alert(comboboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	




	arr2 = comboboxIdJSF.split("__");
	myTableName = "";

	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}	
	
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	var mytextbox;
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		mytextbox = document.getElementById(textboxId);
		//alert("select * from " + myTableName + " where Ten=\'" + comboboxValue + "\'");
		var Object = getObject(myTableName);
		var end = false;
		//alert(1);
		var objArr = Object.filter("DT = 1  AND Ten like \'" + comboboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].Ma;
			return;
		}
		mytextbox.alt = "";
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \'" + comboboxValue + "\'");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");	    
	        //alert(1);    
	        return;
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }    
	    */
    }
    
    try{
  		mytextbox.value = "";
		mycombobox.focus();
		mycombobox.value = "";	
	}catch(e){
	
	}
	//rs.close();	  
}


function setAttrForCombobox_bk(textboxId,spanId,comboboxId,pageSize, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "dojo.byId(\'" + textboxId + "\').alt=arguments[0]; " + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = " " + onfocus_AdvanceCode;

	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=pageSize;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
}

/**
  thanh do add here
**/
function getTenFromMa( tenTable , Ma ){
	try{    
	    var ten = "";
		var Object = getObject(tenTable);
		var objArr = Object.filter(" DT = 1 AND Ma like \'" + Ma + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			ten = objArr[i].Ten;
			//alert(objArr.length);
			return ten;
		}
		
		/*
			//alert("select Ten from " + tenTable + " where MaSo=\'" + MaSo + "\'");
			var rs = db.execute("select Ten from " + tenTable + " where MaSo like \'" + MaSo + "\'");
			
		    while (rs.isValidRow()) {
		 
				ten = rs.field(0);
				//alert(ten);
				break;
				
		    }
		    rs.close();
		    */
		 //return ten;   
	}catch(e){
	  alert(e + 2);
	  
	}
}

/**
  thanh do add here
**/
function getTenFromMaSo( tenTable , Ma ){
	try{    
	    var ten = "";
		var Object = getObject(tenTable);
		var objArr = Object.filter(" DT = 1 AND MaSo like \'" + Ma + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			ten = objArr[i].Ten;
			//alert(objArr.length);
			return ten;
		}
	
	}catch(e){
	  alert("error at getTenFromMa()");
	  
	}
}

/**
  thanh do add here
**/
function getKTCFromCLSBangGia(  Ma ){
	try{    
	    var KTC = "";
		var Object = getObject("DT_DM_CLS_BANG_GIA");
		var objArr = Object.filter(" DT = 1  AND Ma like \'" + Ma + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			KTC = objArr[i].DTDMCLSBG_PHANBIET;
			//alert(objArr.length);
			return KTC;
		}
		return "";
	
	}catch(e){
	  alert("error at getKTCFromCLSBangGia()");
	  
	}
}

function getNDMFromCLSBangGia(  Ma ){
	try{    
	    var NDM= "";
		var Object = getObject("DT_DM_CLS_BANG_GIA");
		var objArr = Object.filter(" DT = 1  AND Ma like \'" + Ma + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			NDM = objArr[i].DMCLSBG_NDM;
			//alert(objArr.length);
			return NDM;
		}
		return "";
	
	}catch(e){
	  alert("error at getNDMFromCLSBangGia()");
	  
	}
}

function getMIENFromCLSBangGia(  Ma ){
	try{    
	    var MIEN= "";
		var Object = getObject("DT_DM_CLS_BANG_GIA");
		var objArr = Object.filter(" DT = 1  AND Ma like \'" + Ma + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			MIEN = objArr[i].DMCLSBG_MIEN;
			//alert(objArr.length);
			return MIEN;
		}
		return "";
	
	}catch(e){
	  alert("error at getMIENFromCLSBangGia()");
	  
	}
}

function myOnblurCombobox_Ex(textboxId,comboboxId, myTableName, tenField, giaTriField){
	//alert("myOnblurCombobox_Ex");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		
		//alert(myTableName);
		var Object = getObject(myTableName) ;
		//alert(Object);
		mytextbox = document.getElementById(textboxId);
		//alert("comboboxValue:"+comboboxValue);
		var arrayObj =   Object.filter("Ten like ? AND DT = 1 ", comboboxValue).filter(tenField + " like ?", giaTriField).toArray();
		//alert(arrayObj);
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.alt = arrayObj[i].Ma;
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo;
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}


function myOnblurTextbox_Ex (textboxId,comboboxId, myTableName, tenField, giaTriField){ 

if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
	var mytextbox = document.getElementById(textboxId);
	var textboxValue = mytextbox.value;
	
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("Ma like ? AND DT = 1 ", textboxValue ).filter(tenField + " like ?", giaTriField ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}

}


function setAttrForCombobox_StoreValue_Ex(textboxId,mySpan,comboboxId,pageSize,Object, tenField, giaTriField){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ;  assignAltToValue(\'" + textboxId + "\') ; myOnblurCombobox_Ex(\'" + textboxId + "\',\'" + comboboxId + "\','DM_BENH_ICD','TacNhan','1');";
		//alert("3");
		myComboboxId.onblur=" assignAltToValue(\'" + textboxId + "\') ; myOnblurCombobox_Ex(\'" + textboxId + "\',\'" + comboboxId + "\','DM_BENH_ICD','TacNhan','1');";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("Ma like ? AND DT = 1 ", textboxValue).filter(tenField + " like ?", giaTriField).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}


function setAttrForCombobox_StoreValue(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("Ma like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}


function setAttrForCombobox_StoreValue_KetQua(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); HSBA_Enable();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); HSBA_Enable(); ";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}

function setAttrForCombobox_StoreValue_DmThuoc(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); getDvt();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var dmThuoc = getObject(comboboxId);
			var object =   dmThuoc.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description + 13);
	}		    	
}


function setAttrForCombobox_StoreValue_Loai(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetValueForPhanLoaiThuoc();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetValueForPhanLoaiThuoc();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var obj =   Object.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < obj.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = obj[i].Ten;
                // alert(persons[0].firstName)
                }			
		}
	}catch(e){
	  alert(e.description + 12);
	}		    	
}

function resetValueForPhanLoaiThuoc(){

	comboboxId = "DM_PHAN_LOAI";
	var search = dijit.byId(comboboxId);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	search.store = CatalogStore;	
	
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA").value = "";
	document.getElementById( "DM_PHAN_LOAI").value = "";
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA").focus();
       
}

/**
 *
**/
function setAttrForCombobox_StoreValue_CapNhatHSBA(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');  getListBenhNhanHSBA();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		/*
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
		    //alert(textboxValue);
			mycombobox = document.getElementById(comboboxId);
			//alert(2);
			var object =   Object.filter("Ma like ?", textboxValue).toArray();
			//alert("object:"+object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
		
		*/
	}catch(e){
	  alert(e + 3);
	}		    	
}

/*
*
*/
function resetDMHuyenXa(){

  /*
    In case of huyen or xa the same as tinh: do nothing  
  */
  var resetHuyen = true;
  var resetXa = true;
  
  var valueMaTinh = document.getElementById(prefix_component + 'TINH_MA').value;	
  if (valueMaTinh != null && valueMaTinh != ""){
  	var valueMaHuyen = document.getElementById(prefix_component + 'HUYEN_MA').value;
  	
  	//alert("valueMaTinh:"+valueMaTinh);
  	//alert("valueMaHuyen:"+valueMaHuyen);
  	if (valueMaHuyen.indexOf(valueMaTinh + ".") >= 0){
  	   resetHuyen = false;
  	}
  	
  	var valueXa = document.getElementById(prefix_component + 'XA_MA').value;
  	if (valueXa.indexOf(valueMaTinh + ".") >= 0){
  	   resetXa = false;
  	}
  }
  
  /*
    Else reset huyen and xa
  */	
  if (resetHuyen == true){
   document.getElementById('DM_HUYEN').value = "";
   resetForCombobox('DM_XA','HUYEN_MA');
  }
 
  if (resetHuyen == true){
   document.getElementById('DM_XA').value = "";
   resetForCombobox('DM_HUYEN','XA_MA');  	 	
  }
 
 document.getElementById(prefix_component + "HUYEN_MA").focus();
}

/*
*
*
*/
function resetDMXa(){
  var resetXa = true;
  
  var valueMaHuyen = document.getElementById(prefix_component + 'HUYEN_MA').value;
  if (valueMaHuyen != null && valueMaHuyen != ""){
  	var valueXa = document.getElementById(prefix_component + 'Xa_MA').value;
  	if (valueXa.indexOf(valueMaHuyen + ".") >= 0){
  	   resetXa = false;
  	}  
  }	
  
  if (resetXa == true){
  	document.getElementById('DM_XA').value = "";
 	resetForCombobox('DM_XA','XA_MA');
  }
  document.getElementById(prefix_component + "XA_MA").focus();	
}

/*
*
* 
*/
function resetForCombobox(comboboxId, textboxma){

	
	var search = dijit.byId(comboboxId);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	search.store = CatalogStore;	
	//search.store = null;
	try{
		//document.getElementById(prefix_component + textboxma).value = "";
		myOnblurTextbox(prefix_component + textboxma,comboboxId);
	}catch(e){
	
	}
	try{
		try{
		
		myOnblurTextbox(textboxma,comboboxId);
	}catch(e){
	
	}
		myOnblurTextbox(prefix_component + textboxma,comboboxId);
	}catch(e){
	
	}
	try{
		try{
		var tmp = document.getElementById(comboboxId);
    		tmp.innerHTML = "";
    	}catch(e){
    	    	
    	}
	}catch(e){
	
	}
	
	//document.getElementById( comboboxId ).value = "";
	
}

/*
*
*
*/
function setAttrForCombobox_StoreValue_Huyen_Xa(textboxId,mySpan,comboboxId,pageSize, idHuyen, idXa){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMHuyenXa();";
		//alert("3");
		myComboboxId.onblur="(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMHuyenXa();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			
			//alert("mycombobox:"+mycombobox);
			//alert("sql:"+"select Ten from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
			var arr1 = comboboxId.split("__");
			var myTableName = "";
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert("myTableName:" + myTableName);
			var Object = getObject(myTableName);
			var objArr = Object.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			for (var i = 0; i < objArr.length; i++) {
				var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
			}
			
			/*
			var rs = db.execute("select Ten from " + myTableName + " where MaSo=\'" + textboxValue + "\'");
			//alert(1000);
		    while (rs.isValidRow()) {
		    //alert(1001);
		        var myOption = document.getElementById(comboboxId + "_opt");
		        //alert(1002);
				myOption.text = rs.field(0);
				//alert(1003);
				//listCatalog(document.getElementById(comboboxId ));
				rs.next();
		    }
		    rs.close();
		    */
		}
	}catch(e){
	  alert("setAttrForCombobox_StoreValue_Huyen_Xa: " + e.description);
	}		    	
}


/*
*
*
*/
function setAttrForCombobox_StoreValue_Xa(textboxId,mySpan,comboboxId,pageSize,  idXa){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMXa();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');  resetDMXa();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			
			//alert("mycombobox:"+mycombobox);
			//alert("sql:"+"select Ten from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
			var arr1 = comboboxId.split("__");
			var myTableName = "";
			//alert(comboboxIdJSF);
//Manh added
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			var Object = getObject(myTableName);
			var objArr = Object.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			for (var i = 0; i < objArr.length; i++) {
				var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
			}
			
			/*
			//alert("myTableName:" + myTableName);
			var rs = db.execute("select Ten from " + myTableName + " where MaSo=\'" + textboxValue + "\'");
//Manh end
			//alert(1000);
		    while (rs.isValidRow()) {
		    //alert(1001);
		        var myOption = document.getElementById(comboboxId + "_opt");
		        //alert(1002);
				myOption.text = rs.field(0);
				//alert(1003);
				//listCatalog(document.getElementById(comboboxId ));
				rs.next();
		    }
		    rs.close();
		    */
		}
	}catch(e){
	  alert("setAttrForCombobox_StoreValue_Xa: " + e.description);
	}		    	
}
function setAttrForCombobox_StoreValue_2(textboxId,mySpan,comboboxId,pageSize){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var objArr = Object.filter(" DT = 1 AND MaSo like \'" + textboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = objArr[i].Ten;
		} 
		
		/*
		var rs = db.execute("select Ten from " + comboboxId + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = rs.field(0);
			rs.next();
	    }
	    rs.close();
	    */
	}	    	
}

function setAttrForCombobox_StoreValue_N(textboxId,mySpan,comboboxId,pageSize, nextfieldId){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]; document.getElementById('" + nextfieldId + "').focus();";
	myComboboxId.onblur="myOnblurCombobox_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var objArr = Object.filter("Ma like ? AND DT = 1 ", textboxValue);
		for (var i = 0; i < objArr.length; i++) {
			var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
		}
		
		/*
		var rs = db.execute("select Ten from " + comboboxId + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = rs.field(0);
			rs.next();
	    }
	    rs.close();
	    */
	}	    	
}


function setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize){

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0];myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	myComboboxId.onblur="myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
	
}

// B4134_Tinhtonkhovagiatri
function setAttrForComboboxJSFFor_B4134_Tinhtonkhovagiatri(textboxId,mySpan,comboboxId,pageSize){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); listPhanLoaiFocus();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			//alert("comboboxId: " + comboboxId);
			mycombobox = document.getElementById(comboboxId);
			//alert("mycombobox: " + mycombobox);
			var dm = getObject(comboboxId);
			var object =   dm.filter("Ma like ? AND DT = 1 ", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	//alert("Ten: " + object[i].Ten);
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	
                 	//alert("myOption: " + myOption);
                 	myOption.text = object[i].Ten;
                }
			
		}
	}catch(e){
	  alert(e.description + 11);
	}
}

function  setAttrForComboboxJSFForPhieuDuTru (_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	//alert(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	//alert("2");
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPhieuDuTru(\'"+ comboboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxJSFForPhieuDuTru :" +  e);
  }
}



function  setAttrForComboboxBenhNhanTiepDon(_mySpan,comboboxId,pageSize,showId){
  try{
	var mySpan = document.getElementById(_mySpan);
	//alert(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	//alert("2");
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__benhnhanduoctiepdon_ma\').alt=arguments[0]; mySetValueForBenhNhanTiepDon(\'"+ comboboxId + "\',\'"+ showId +"\');";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__benhnhanduoctiepdon_ma\'); mySetValueForBenhNhanTiepDon(\'"+ comboboxId + "\',\'"+ showId +"\');";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxBenhNhanTiepDon :" +  e.description);
  }
}

/**
 *
**/
function mySetValueForBenhNhanTiepDon (comboboxId,showId) { // showId = __matiepdon or __sobenhan

   //alert("call me");
   var myValue = document.getElementById(prefix_component + '__benhnhanduoctiepdon_ma').value;
   //alert(myValue);
   if (myValue == null || myValue ==""){
       try{
   			 document.getElementById(prefix_component + showId).value = "";   
   	   }catch(e){
       		return;  
       }    
   }
   //alert("myValue:"+myValue);
   var i = myValue.indexOf("___"); //3 _
   var maTiepDon = myValue.substring(0,i);   
   //alert("maTiepDon:"+maTiepDon);
   
   try{
   	document.getElementById(prefix_component + showId).value = maTiepDon;  
   		document.getElementById(prefix_component + showId).focus();
   }catch(e){
  	  
   }
   
  
}
 
function mySetValueForPhieuDuTru(comboboxId){
	try { 
	   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
	   //alert(myValue);
	   if (myValue == null || myValue =="") {
	   		document.getElementById(comboboxId).value = "";
	   		//document.getElementById(comboboxId).focus();
	      return;
	   }
	   
	   var i = myValue.indexOf("___"); //3 _
	   var maHang = myValue.substring(0,i);   
	   //alert("maHang:"+maHang);
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var tenHang = myValue.substring(0,i);
	   //alert("tenHang:"+tenHang);
	   document.getElementById(prefix_component + "__tenhang").value = tenHang;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var quyCach = myValue.substring(0,i);
	   //alert("quyCach:"+quyCach);
	   document.getElementById(prefix_component + "__quycach").value = quyCach;
	   //document.getElementById(prefix_component + "__quycach").focus();
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var donVi = myValue.substring(0,i);
	   //alert("donVi:"+donVi);
	   document.getElementById(prefix_component + "__donvi").value = donVi;
	   
	   try{
	    
	    document.getElementById(prefix_component + "__donviMain").value = donVi;
	    	   
	   }catch(e){
	   
	   }
	   
	   myValue = myValue.substring(i+3);  
	   
	   i = myValue.indexOf("___"); //3 _
	   var tonKho = myValue.substring(0,i);
	   //alert("tonKho:"+tonKho);
	   document.getElementById(prefix_component + "__tonkho").value = tonKho;
	   document.getElementById(prefix_component + "__tonkho_hid").value = tonKho;
	   
	   myValue = myValue.substring(i+3);   
	  // document.getElementById(prefix_component + "__xinlinh").focus();
	
	   var donGia = myValue;
	   //alert("donGia:"+donGia);
	    document.getElementById(prefix_component + "__dongia").value = donGia;
     }catch(e){
       alert("error at mySetValueForPhieuDuTru: " + e.description);
     }  
   
   
}



// Manh added

function listCatalogJSF_PDTRU(myCombobox, data){
	var myComboboxId = document.getElementById(myCombobox);
	try {
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		var firstTitle = formatStringDuocPham("Tên thuốc", "Hàm lượng", "Lượng tồn", 
												"Đơn vị", "Nước sx", "Hãng sx", "Đơn giá", "Hạn dùng");
		CatalogStore.newItem({id: "", title: firstTitle});
		//alert(100);
		var i = 0;
		var havingData = false;
		if (data != null){
			while (data.record[i] != null){            	
	        	data1 = data.record[i];
	            if(data1.MaHang != null && data1.MaHang != ""){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						var myTitle =  formatStringDuocPham(data1.TenHang, data1.hamLuong, data1.TonKho, 
															data1.dvt, data1.qg, data1.NSX, data1.DonGia, data1.hanDung);
	                    CatalogStore.newItem({id: myId , title: myTitle });  
	                  // alert(102);
	                     havingData == true;
	                }
					i=i+1;
	         }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	                //alert(data1);             
	                if(data1.MaHang != null && data1.MaHang != "" ){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						var myTitle =  formatStringDuocPham(data1.TenHang, data1.hamLuong, data1.TonKho, 
															data1.dvt, data1.qg, data1.NSX, data1.DonGia, data1.hanDung);
	                    CatalogStore.newItem({id: myId , title: myTitle });
	                     havingData == true;
	                }
	         
	         }
	    }else{
	   	  // CatalogStore.newItem({id: "", title: ""});
	   	    //alert("myComboboxId.options:"+myComboboxId.options);
	    	//myComboboxId.options.length = 0;
	    	myComboboxId.value = "";
	    }
	     if (havingData == false){
	    	myComboboxId.value = "";
	    }
	         
		//alert(103);
		search.store = CatalogStore;	
		//alert(104);
	} catch(e){
	   alert("catch .... in....listCatalogJSF_PDTRU():"+e );
	}
}



function listCatalogDanhSachBenhNhanTiepDon(myCombobox, data){
	
	var myComboboxId = document.getElementById(myCombobox);
	
	
	try{
	   
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		var i = 0;
		var havingData = false;
		
		if (data != null){
			
			while (data.record[i] != null){            	
	                data1 = data.record[i];  
	                
	                if(data1.matiepdon != null){       
	                  var myId =  data1.matiepdon + "___" + data1.hoten + "___" + data1.tuoi + "___" + data1.namsinh + "___" + data1.cmnd;
	                  
					  var hoten = data1.hoten;
					  if (hoten == null || hoten == "null" || hoten == ""){
					    i=i+1;
					    continue;
					  }
					  
//					  var tuoi = data1.tuoi;
//					  if (tuoi == null || tuoi == "null" || tuoi == ""){
//						  tuoi = "";
//					  }
					  
					  var namsinh = data1.namsinh;
					  if (namsinh == null || namsinh == "null" || namsinh == ""){
						  namsinh = "";
					  }
					  
					  var matiepdon = data1.matiepdon;
					  if (matiepdon == null || matiepdon == "null" || matiepdon == ""){
						  matiepdon = "";
					  }
					  
//					  var cmnd = data1.cmnd;
//					  if (cmnd == null || cmnd == "null" || cmnd == ""){
//						  cmnd = "";
//					  }
					  
	                  // 20110623 bao.ttc: them Matiepdon de tranh TH trung ten & nam sinh
					  // CatalogStore.newItem({id: myId , title:   hoten + " " + tuoi + " " + namsinh + " " + cmnd  });  
	                  CatalogStore.newItem({id: myId , title:   hoten + "  -  " + namsinh + "  -  " + matiepdon  });
	                  
	                  havingData = true;
	                }
					i=i+1;
	         }
	         //alert("i:"+i);
	         if (i == 0) { // truong hop nay chi co' 1 record
	         
	          data1 = data.record;  
	                
	                //alert(data1);         
	                if(data1.matiepdon != null){       
	                  var myId =  data1.matiepdon + "___" + data1.hoten + "___" + data1.tuoi + "___" + data1.namsinh + "___" + data1.cmnd ; 
	                  
					  //alert(myId);  	
					  var hoten = data1.hoten;
					  if (hoten == null || hoten == "null" || hoten == ""){
					   
					  } else {
					  
//						  var tuoi = data1.tuoi;
//						  if (tuoi == null || tuoi == "null" || tuoi == ""){
//							  tuoi = "";
//						  }
						  
						  var namsinh = data1.namsinh;
						  if (namsinh == null || namsinh == "null" || namsinh == ""){
							  namsinh = "";
						  }
						  
						  var matiepdon = data1.matiepdon;
						  if (matiepdon == null || matiepdon == "null" || matiepdon == ""){
							  matiepdon = "";
						  }
						  
//						  var cmnd = data1.cmnd;
//						  if (cmnd == null || cmnd == "null" || cmnd == ""){
//							  cmnd = "";
//						  }
						  
						  // 20110623 bao.ttc: them Matiepdon de tranh TH trung ten & nam sinh
		                  // CatalogStore.newItem({id: myId , title:   hoten + " " + tuoi + " " + namsinh + " " + cmnd  });  
		                  CatalogStore.newItem({id: myId , title:   hoten + "  -  " + namsinh + "  -  " + matiepdon  });
		                  
		                  havingData == true;
	                  }
	                }
	         
	         }
	         
	    }else{
	   	  // CatalogStore.newItem({id: "", title: ""});
	   	    //alert("myComboboxId.options:"+myComboboxId.options);
	    	//myComboboxId.options.length = 0;
	    	myComboboxId.value = "";
	    }
	    
	    //alert("havingData:"+havingData);   
	    if (havingData == false){
	    	myComboboxId.value = "";
	    }     
		//alert(103);
		search.store = CatalogStore;	
		//alert(104);
		try{
			dijit.byId(myCombobox)._myfunction();
	    }catch(e){
	    
	    }
	    try{
			 dijit.byId(myCombobox)._showResultList();
	    }catch(e){
	    
	    }
	   
	    
	} catch(e){
	   alert("catch .... in....listCatalogDanhSachBenhNhanTiepDon():"+e.description );
	}
	
	

}
//Manh end

function formatStringDuocPham(tenHang, hamLuong, tonKho, donVi, nuocSx, hangSx, donGia, hanDung) {
  // Tên hàng = 52 + 100
  // Hàm lượng = 63
  // Lượng tồn = 57
  // Đơn vị = 35
  // Nước sx = 46 + 50
  // Hãng sx = 46 + 50
  // Đơn giá = 44 + 20
  // Hạn dùng = 54 + 30
               
	var tenHang = tenHang.trimToPx(52 + 143);	
	var kq =  tenHang ;
	//kq = kq.trimToPx(195);
	var hamLuong = (hamLuong == null || hamLuong == 'null'|| hamLuong == '')?"~".trimToPx(23+90):hamLuong.trimToPx(23+90);
	kq += hamLuong;
	//kq = kq.trimToPx(308);	
	var donVi = (donVi == null || donVi == 'null'|| donVi == '')?"~".trimToPx(25+10):donVi.trimToPx( 25+10);
	kq += donVi;
	kq = kq.trimToPx(343);	
	
	
	
	
	var nuocSx = (nuocSx == null || nuocSx == 'null' || nuocSx == '')?"~".trimToPx(16 + 40):nuocSx.trimToPx( 16 + 40);
	kq += nuocSx;
	//kq = kq.trimToPx(399);	
	var hangSx = (hangSx == null || hangSx == 'null' || hangSx == '')?"~".trimToPx(46 + 30):hangSx.trimToPx( 46 + 30);
	kq = kq + hangSx   ;
	//kq = kq.trimToPx(475);
	
	//alert("han dung:" + hanDung + "END");
	var hanDung;
	if(hanDung == '' || hanDung == '//')
		hanDung = "               ";		
	else
		hanDung = (hanDung == null || hanDung == 'null' || hanDung == '')?"~".trimToPx(30 + 30):hanDung.trimToPx(30 + 30);
	
	kq = kq + hanDung  ;
	//kq = kq.trimToPx(495);
	
	
	var tonKho = (tonKho == null || tonKho == 'null'|| tonKho == '')?"~".trimToPxRight(17 + 40):tonKho.trimToPxRight(17 + 40);
	kq = kq + tonKho;		
	//kq = kq.trimToPx(550);

	//alert(kq.trueLength());
	var donGia = (donGia == null || donGia == 'null' || donGia == '')?"~".trimToPxRight(24 + 30):donGia.trimToPxRight( 24 + 30);
	kq = kq + donGia;
	//alert(kq.trueLength()); = 420
	kq = kq.trimToPx(600);
	
	return kq;
}

String.prototype.trimToPxRight = function(length)
{
    var tmp = this;   
    var trimmed = this;
    if (trimmed.visualLength() < length)
    {
        trimmed += "~";
        while (trimmed.visualLength() < length)
        {
            
            trimmed =  "~" + trimmed ;
        }
    }else{
    	trimmed += "...";
        while (trimmed.visualLength() > length)
        {
            tmp = tmp.substring(0, tmp.length-1);
            trimmed = tmp + "...";
        }
    	
    
    }
    //alert("trimmed:"+trimmed);
	trimmed = trimmed.replace(/[~]/g, " ");
    return trimmed;
};

String.prototype.trimToPx = function(length)
{
    var tmp = this;   
    var trimmed = this;
    if (trimmed.visualLength() < length)
    {
        trimmed += "~";
        while (trimmed.visualLength() < length)
        {
            
            trimmed = trimmed + "~";
        }
    }else{
    	trimmed += "...";
        while (trimmed.visualLength() > length)
        {
            tmp = tmp.substring(0, tmp.length-1);
            trimmed = tmp + "...";
        }
    	
    
    }
    //alert("trimmed:"+trimmed);
	trimmed = trimmed.replace(/[~]/g, " ");
    return trimmed;
};

String.prototype.visualLength = function()
{
    var ruler = document.getElementById('ruler');
    ruler.innerHTML = this;
    //alert(ruler.offsetWidth);
    return ruler.offsetWidth;
};
String.prototype.trueLength = function()
{
    var ruler = document.getElementById('ruler');
    ruler.innerHTML = this;
    //alert(ruler.offsetWidth);
    return ruler.offsetWidth;
};
function setAttrForComboboxJSFForThuoc(textboxId,mySpan,comboboxId,pageSize){
	//alert(comboboxId);

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	//alert("2   "+myComboboxId);
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\','',''); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}

function setAttrForComboboxJSFForPhieuXuat (_mySpan,comboboxId,pageSize){
	try {
		var mySpan = document.getElementById(_mySpan);
		//alert(mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		mySpan.jsId="defaultStore";
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
		var myComboboxId = document.getElementById(comboboxId);
	
		//alert(myComboboxId);
		myComboboxId.dojoType="dijit.form.FilteringSelect";
	
		//alert("2");
		myComboboxId.invalidMessage="";
		myComboboxId.onchange="dojo.byId( prefix_component + \'__listtonkho_duocpham_ma\').alt=arguments[0];document.getElementById(prefix_component + \'__listtonkho_duocpham_ma\').value = arguments[0]";
		myComboboxId.onblur="assignAltToValue(prefix_component + \'__listtonkho_duocpham_ma\'); mySetValueForPhieuXuat()";
		//myComboboxId.store="defaultStore";
		myComboboxId.searchAttr="title";
		myComboboxId.autoComplete=false;	
		myComboboxId.pageSize=pageSize;
  	} catch(e) {
    	alert("error at setAttrForComboboxJSFThuocYDungCu :" +  e.description);
  	}
}

function mySetValueForPhieuXuat(){
   	var val = document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value;
   	//alert(val);
   	if (val == null || val == "" || val == "undefined") {
      	document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value = "";
      	clearDmt();
      	return;
   	}
   	
   	var arr = val.split("___");
   	var tonKhoMa = arr[0];
   	document.getElementById(prefix_component + "__tonkhoma_hid").value = tonKhoMa;
   	
   	var soluongton = arr[1];
   	if (soluongton == "" || soluongton == "null" || soluongton == "NULL") {
   		document.getElementById(prefix_component + "__tonkho_hid").value = "";
   		document.getElementById(prefix_component + "__tonkho").value = "";
   	} else {
   		document.getElementById(prefix_component + "__tonkho_hid").value = parseFloat(soluongton);
   		document.getElementById(prefix_component + "__tonkho").value = parseFloat(soluongton);
   		//alert(document.getElementById(prefix_component + "__tonkho").value);
   		numberFormatBlur(document.getElementById(prefix_component + "__tonkho"));
   		//alert(document.getElementById(prefix_component + "__tonkho").value);
   	}
   	
   	var malk = arr[2];
   	var malkTxt = document.getElementById(prefix_component + "__malk");
   	if (malkTxt) {
   		malkTxt.value = malk;
   	}
}

function setAttrForComboboxJSFThuocYDungCu (_mySpan,comboboxId,pageSize){
	try {
		var mySpan = document.getElementById(_mySpan);
		//alert(mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		mySpan.jsId="defaultStore";
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
		var myComboboxId = document.getElementById(comboboxId);
	
		//alert(myComboboxId);
		myComboboxId.dojoType="dijit.form.FilteringSelect";
	
		//alert("2");
		myComboboxId.invalidMessage="";
		myComboboxId.onchange="dojo.byId( prefix_component + \'__listtonkho_duocpham_ma\').alt=arguments[0];document.getElementById(prefix_component + \'__listtonkho_duocpham_ma\').value = arguments[0]";
		myComboboxId.onblur="assignAltToValue(prefix_component + \'__listtonkho_duocpham_ma\'); onBlurThuoc()";
		//myComboboxId.store="defaultStore";
		myComboboxId.searchAttr="title";
		myComboboxId.autoComplete=false;	
		myComboboxId.pageSize=pageSize;
  	} catch(e) {
    	alert("error at setAttrForComboboxJSFThuocYDungCu :" +  e.description);
  	}
}

function setAttrForComboboxForThuocydungcu (_mySpan, comboboxId, pageSize){
	try {
		var mySpan = document.getElementById(_mySpan);
		//alert(mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		mySpan.jsId="defaultStore";
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
		var myComboboxId = document.getElementById(comboboxId);
	
		//alert(myComboboxId);
		myComboboxId.dojoType="dijit.form.FilteringSelect";
	
		//alert("2");
		myComboboxId.invalidMessage="";
		myComboboxId.onchange="document.getElementById(prefix_component + \'__listtonkho_duocpham_ma\').value = arguments[0]";
		myComboboxId.onblur="mySetValueForThuocydungcu(\'"+ comboboxId + "\')";
		//myComboboxId.store="defaultStore";
		myComboboxId.searchAttr="title";
		myComboboxId.autoComplete=false;	
		myComboboxId.pageSize=pageSize;
  	} catch(e) {
    	alert("error at setAttrForComboboxForThuocydungcu :" +  e.description);
  	}
}

function mySetValueForThuocydungcu(comboboxId){
	var myValue = document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value;
   	//alert("myValue:"+myValue);
   	if (myValue == "") {
   		var search = dijit.byId(comboboxId);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		search.store = CatalogStore;
		document.getElementById(prefix_component + "MATHANG_MA").value = "";
		myOnblurTextboxForDmThuoc(prefix_component + "MATHANG_MA", "DM_THUOC", "", "");
		document.getElementById(prefix_component + "MATHANG_MA").focus();
   		return;
   	} else {
   		insertToDB(myValue);
   	}
}

function setAttrForComboboxJSFForThuoc_PhieuDuTru(textboxId,mySpan,comboboxId,pageSize){
	//alert(comboboxId);

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	//alert("2   "+myComboboxId);
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\','',''); assignAltToValue(\'" + textboxId + "\') ; getTonkho(); ";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}
//Manh 
function set_diengiai(textboxId,comboboxId) {
	//alert("2");
    try {
		if (document.getElementById(prefix_component + "__tem") == null || document.getElementById(prefix_component + "__tem") == "undefined"){
		  return ;
		}
		var diengiai = document.getElementById( comboboxId).value;
		document.getElementById(prefix_component + "__tem").value = diengiai;
		//alert(document.getElementById(prefix_component + "__tem").value);
	
	}catch(e){
	   alert('Exception in function set_diengiai');
	}
}
function setAttrForComboboxJSF_CLS(textboxId,mySpan,comboboxId,pageSize){
    
    //alert('thanh01');

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	//alert(20);
	

	myComboboxId.onblur="set_diengiai(\'" + textboxId + "\',\'" + comboboxId + "\');; myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	
	//alert(myComboboxId.onblur);
	//alert(21);
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.autoComplete=false;
	myComboboxId.pageSize=pageSize;
	
}

function setAttrForComboboxJSF_DMTHUOC(textboxId,mySpan,comboboxId,pageSize){
    
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="textHideFocus();myOnblurTextboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}
/*
// Phuc add code below 
function getCatalogFromServer_2(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
      
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){            	
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.Ma,ten,data1.NgayChinhSua,""]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/
function myOnblurComboboxJSF_2(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	//alert(comboboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";

	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}	
			
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		mytextbox = document.getElementById(textboxId);
		//alert("select * from " + myTableName + " where Ten=\'" + comboboxValue + "\'");
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter(" DT = 1 AND Ten like \'" + comboboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			var obj = objArr[i];
			mytextbox.alt = obj.Ma;
	        if (mytextbox.type == "text") mytextbox.focus();
	        return;
		}
		mytextbox.alt = "";
			 mytextbox.value = "";
	    	mycombobox.focus();
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \'" + comboboxValue + "\'");		
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        if (mytextbox.type == "text") mytextbox.focus();
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }    

	    mytextbox.value = "";
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    rs.close();	 
	    */   
    }else{
    	
    }
      
}

function setAttrForComboboxJSF_2(textboxId,mySpan,comboboxId,pageSize){

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxJSF_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;	
}
// code new
function setAttrForComboboxJSF_ext(textboxId,mySpan,comboboxId,pageSize){
        setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize);
        setTimeout(function() {iesvn_LoadAgain();},2000);
}

// Phuc end add code

function setAttrForJSFCombobox(comboboxId,pageSize){
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.autoComplete=false;
	myComboboxId.pageSize=pageSize;
}
// code new
function setAttrForComboboxJSF_ext(textboxId,mySpan,comboboxId,pageSize){
        setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize);
        setTimeout(function() {iesvn_LoadAgain();},2000);
}

// Phuc end add code

function setAttrForJSFCombobox(comboboxId,pageSize){
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.pageSize=pageSize;
}
function listCatalog(myCombobox){
   try{
	//alert(0);
    //alert(1);
	myComboboxId = myCombobox.id;
	//alert(myComboboxId);
	arr1 = myComboboxId.split(":");
	//alert(arr1.length);
	//alert("myComboboxId:"+myComboboxId);
	
	if(arr1.length == 1){
	    //alert("myCombobox: "+myCombobox);
	    
	    //alert(myComboboxId);
	    if (myComboboxId == "DM_HUYEN"){
	    	//alert(2);
	    	listCatalog_Huyen_Xa(myCombobox,"TINH_MA");
	    }else if (myComboboxId ==  "DM_XA"){
	    	listCatalog_Huyen_Xa(myCombobox,"HUYEN_MA");
	    }else if (myComboboxId ==  "DM_DONVIDUPHONG"){
	    	listCatalogDonViByTuyen_CT(myCombobox,"TUYEN_MA", "CHUONGTRINH_MA");
	    }else if(myComboboxId == "DM_TTGDCAP2"){
	    	listCatalog_Huyen_Xa(myCombobox,"TTGDCAP1_MA");	    
	    }else if (myComboboxId ==  "__listtonkho"){
	        
			listCatalogSearchForPDTRU(myCombobox);

		}
		else if (myComboboxId ==  "__listtonkhoBhyt"){
	        
			listCatalogSearchForPhieuXuatBh(myCombobox);
		}
		else if (myComboboxId ==  "__benhnhanduoctiepdon"){
	        
			//ds benh nhan

		}else if (myComboboxId ==  "__listtonkho_duocpham"){
	        
			listCatalogSearchForPXUAT(myCombobox);

		}else if ( myComboboxId ==  "DM_THUOC"){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	if (loaiObj == null){
	    		loaiObj = document.getElementById(prefix_component + "DTDMLOAI_MA");
	    	}
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
	    	var phanLoaiObj = document.getElementById(prefix_component + "DM_PHAN_LOAI_MA");
	    	var valuePhanLoai = "";
	    	if(phanLoaiObj){
	    		valuePhanLoai = phanLoaiObj.value;
	    	}
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DM_Thuoc(myCombobox,valueLoai,valuePhanLoai);	    
	    }else if ( myComboboxId ==  "DM_PHAN_LOAI"){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
   	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalog_DM_PHAN_LOAI(myCombobox,valueLoai);	    
	    }else if (myComboboxId.indexOf("DM_BENH_ICD") >= 0){
			listCatalogWithLongData(myCombobox,"DM_BENH_ICD");
		}
		else if (myComboboxId.indexOf("DM_TAC_NHAN") >= 0){
			//alert(1);
			listCatalog_Ex(myComboboxId,"DM_BENH_ICD",'TacNhan', '1');
			
		}
		else if (myComboboxId.indexOf("DT_DM_PHAU_THUAT") >= 0){
			listCatalogWithLongData(myCombobox,"DT_DM_PHAU_THUAT");
		}else {
		    
			listCatalogNormal(myCombobox);
			
		}	
		
		
	}
	else {
	    //alert(myComboboxId);
	    if (myComboboxId == prefix_component + "DM_HUYEN"){
	    	//alert(2);
	    	listCatalogJSF_Huyen_Xa(myCombobox,"TINH_MA");
	    }else if (myComboboxId == prefix_component + "DM_XA"){
	    	listCatalogJSF_Huyen_Xa(myCombobox,"HUYEN_MA");
	    
	    }else if (myComboboxId == prefix_component + "DM_THUOC" ){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	if (loaiObj == null){
	    		loaiObj = document.getElementById(prefix_component + "DTDMLOAI_MA");
	    	}
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
	    	var phanLoaiObj = document.getElementById(prefix_component + "DM_PHAN_LOAI_MA");
	    	var valuePhanLoai = "";
	    	if(phanLoaiObj){
	    		valuePhanLoai = phanLoaiObj.value;
	    	}
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DM_Thuoc(myCombobox,valueLoai,valuePhanLoai);	    
	    }
	    
	    
	    else if (myComboboxId == prefix_component + "DT_DM_CLS_BANG_GIA"){
	    
	    	var loaiObj = document.getElementById(prefix_component + "__loai");
	    	
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	//alert(valueLoai);
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DT_DM_KY_THUAT(myCombobox,valueLoai);	    
	    }else {
	    	//alert(1);
			listCatalogJSF(myCombobox);
			//alert(0);
		}	
	}	
  }catch(ex){
     alert("error at listcatalog:" + ex.description);
  }	
}

function listCatalogSearchForPDTRU(myCombobox){
  
 //alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    //var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
		    
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		if (dataList == null){
		   //alert("dataList is null, return");
		   return;
		}   		
		   
		var i = 0;
		var data = dataList;
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		   
		
            
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		//while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			//CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			//rs.next();
			
			//i++;
			
		while (data.record[i] != null){            	
                data1 = data.record[i];   
                
                //alert(101);     
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                
                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
                
                if ( myComboboxValue == "" || iIndex == 0 ){
                    
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert("myId:" + myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                }
	            }    
				i=i+1;
            }
        if (i == 0) { // truong hop nay chi co' 1 record
         	data1 = data.record;   
                //alert(101);     
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                
                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
                
                if ( myComboboxValue == "" || iIndex == 0 ){
                    
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert("myId:" + myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                }
	            }    
        }   
            
		//try{
		//	rs.close();
		//}catch(e){
		   //alert("rs close(): " + e);
		//}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogSearchForPDTRU():"+e );
	}
	
}

function listCatalogSearchForPXUAT(myCombobox){
  
 //alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    //var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
		    
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		if (dataList == null){
		   //alert("dataList is null, return");
		   return;
		}   		
		   
		var i = 0;
		var data = dataList;
		
		//alert();
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		   
		
            
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		//while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			//CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			//rs.next();
			
			//i++;
		var  havingData = false;	
		//alert("data.record[i]:"+data.record[i]);
		
		
			while (data.record[i] != null){            	
	                data1 = data.record[i];
	                //alert("data1:"+data1);   
	                //alert(101);     
	                // var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
	                // var iIndex = varTitle.indexOf (myComboboxValue);
	                
	                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
	                
	                //if ( myComboboxValue == "" || iIndex == 0 ){
	                    //alert(1);
		                if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;   
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                   CatalogStore.newItem({id: myId , title: myTitle  });  
			                  // alert(102);
			                   havingData = true;
			             }
			             
		            //}    
					i=i+1;
	            }
	            
	              if (i == 0) { // truong hop nay chi co' 1 record
	              	data1 = data.record;  
	              	if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;   
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                   CatalogStore.newItem({id: myId , title: myTitle  });  
			                  // alert(102);
			                   havingData = true;
			             }	              
	              
	              }
		//try{
		//	rs.close();
		//}catch(e){
		   //alert("rs close(): " + e);
		//}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	  
	} catch(e){
	   alert("catch .... in....listCatalogSearchForPXUAT():"+e );
	}
	
}
/**
 Used for LAP PHIEU DU TRU:  phan loai got depends on DM_LOAI
*/
function listCatalog_DM_PHAN_LOAI(myCombobox,loai_value){
  
    //alert(loai_value);

	if (loai_value == ""){
	  loai_value = "%";
	}

	comboboxId = myCombobox.id;
	

	var dmArray =   DmPhanLoaiThuoc.filter("Loai like ?", loai_value).toArray();
	//alert(dmArray);
    var search = dijit.byId(myCombobox.id);
   
   
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmArray != null ) {
         
    	 for (var  i = 0; i < dmArray.length; i ++) {
            CatalogStore.newItem({id: dmArray[i].Ma , title:   dmArray[i].Ten});
            //alert(dmArray[i].Ma);
         }
    }
    //search.store = null;
   	search.store = CatalogStore;	
   
   
		
		//alert("myTableName2:"+myTableName);
	
	
	

}
function listCatalogJSF_DM_Thuoc(myCombobox,loai_value, phanloai_value){
	
	if (loai_value == ""){
	  loai_value = "%";
	}
	if (phanloai_value == ""){
	  phanloai_value = "%";
	}
	// alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	if (myCombobox.getDisplayedValue() == null || myCombobox.getDisplayedValue() == ''){
	  resetForCombobox(myCombobox.id,'');
	  return;
	}

	//alert("myTableName2:"+myTableName);
	var dmthuocArray =   DmThuoc.filter("Ten like ?", myCombobox.getDisplayedValue() + "%").filter("loai like ?", loai_value).filter("(ploai1 like ? OR ploai2 like ? OR ploai3 like ? OR ploai4 like ? OR ploai5 like ? OR ploai6 like ? OR ploai7 like ? OR ploai8 like ? OR ploai9 like ? OR ploai10 like ?)", phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value).toArray();
    var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmthuocArray != null ) {
    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
            CatalogStore.newItem({id: dmthuocArray[i].Ma , title:   dmthuocArray[i].Ten});
         }
    }
   	search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	
	

}


function listCatalogWithLongData(myCombobox,ten_danh_muc){

	
	comboboxId = myCombobox.id;
	
	
	if (myCombobox.getDisplayedValue() == null || myCombobox.getDisplayedValue() == ''){
	  resetForCombobox(myCombobox.id,'');
	  return;
	}

	//alert("comboboxId:"+comboboxId);
	var obj = getObject(ten_danh_muc);
	var dmthuocArray =   obj.filter("Ten like ? AND DT = 1 ", myCombobox.getDisplayedValue() + "%").toArray();
    var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmthuocArray != null ) {
    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
            CatalogStore.newItem({id: dmthuocArray[i].Ma , title:   dmthuocArray[i].Ten});
         }
    }
   	search.store = CatalogStore;	

}
function listCatalogJSF_DT_DM_KY_THUAT(myCombobox,chuDau){

	if (chuDau == ""){
	  chuDau = "%";
	}

	// alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		// alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' where loai ='" + loai_value +"'");
		
		
		//var strSql = "select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() +  "%\' and ( ChuDau like '" + chuDau + "' or ChuDauFather like '" + chuDau + "' )";
		var strSql = "Ten like \'" + myCombobox.getDisplayedValue() +  "%\' and ( ChuDau like '" + chuDau + "' or ChuDauFather like '" + chuDau + "' )";
		//alert(strSql);
		
		//rs = db.execute(strSql);	   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		var Object = getObject(myTableName);
		var objArr = Object.filter(strSql).toArray();
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].Ma, title:   objArr[i].Ten});
		}
		
		/*
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   //alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		*/
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogJSF_DT_DM_KY_THUAT():"+e );
	}
	
	

}

//XXX_MYLEVEL_1
/*function listCatalogNormal1(myCombobox){
	var comboboxId_arr = (myCombobox.id).split("_MYCAP_");
	var upLevel_condition_str = ""; 
	if(comboboxId_arr.length > 1){
		try{
			var level = parseInt(comboboxId_arr[comboboxId_arr.length - 1]);
			if(level > 1){
				//Xoa trang cac cap con	
				//them dieu kien la ma so cua cap cha vao query
				var comboboxId_upLevel = comboboxId_arr[0] + "_MYCAP_" + level - 1;
				upLevel_condition_str = comboboxId_arr[0] + " like " + document.getElementById(comboboxId_arr).value;				
			}			
		}
		catch(){}		
	}
	var Object = getObject(myCombobox.name);
	if ( Object != null ) {
		var text_search = "";
		text_search = myCombobox.getDisplayedValue() + "%";
		var objArr;
		var filterCondition = "Ten like " + text_search;
		if(upLevel_condition_str != ""){
			filterCondition += " and " + upLevel_condition_str; 
		}		
		objArr = Object.filter(filterCondition).toArray();
		
		if(objArr.length <= parseInt( number_max_recode)){
			var search = dijit.byId(myCombobox.id);
			var jsonData = { identifier: "id", items: [], label: "title" };
			var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
			CatalogStore.newItem({id: "", title: ""});
			for (var i = 0; i < objArr.length; i++) {
				CatalogStore.newItem({id: objArr[i].Ma, title:   objArr[i].Ten});
			}		
			search.store = CatalogStore;	
	    }  
	}	
}*/

function listCatalog_all(comboboxId,getDB_funcName){
	//alert(123);
	var objArr = eval(getDB_funcName);
	//alert(objArr);
	var myCombobox = dijit.byId(comboboxId);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	if(objArr != null){
		for (var i = 0; i < objArr.length; i++) {					
			CatalogStore.newItem({id: objArr[i].Ma, title: objArr[i].Ten});			
		}		
	}		
	myCombobox.store = CatalogStore;		
}

function listCatalog_search(comboxobj){
	
	var myComboboxTmp = dijit.byId(comboxobj.id);
	
	//alert(myComboboxTmp.focusNode.value);
	//var cpos = myComboboxTmp._getCaretPos(myComboboxTmp.focusNode);
	//alert(cpos);
	if (myComboboxTmp.id == "__listtonkho_duocpham" || myComboboxTmp.id  == "__listtonkho" || myComboboxTmp.id  == "__listtonkho"
	|| myComboboxTmp.id  == "__benhnhanduoctiepdon"
	
	){
		return ;
	}
	//alert("name:"+name);
	var objArr ;
	//alert(comboxobj.alt);
	//alert(comboxobj);
	if (comboxobj.alt == null || comboxobj.alt == ""){
		//alert("error");
		// ko bao gio vao day
		var myCombobox = dijit.byId(comboxobj.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		myCombobox.store = CatalogStore;
		
		return;
	}else{
		objArr = eval(comboxobj.alt);
		
	}
	//alert(objArr);
	if (objArr == null){
		var myCombobox = dijit.byId(comboxobj.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		myCombobox.store = CatalogStore;
		return;
	}
	
	//alert(objArr.length);
	
	if (objArr.length > number_max_recode){
		var myCombobox = dijit.byId(comboxobj.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		myCombobox.store = CatalogStore;
		return;
	}
	var myCombobox = dijit.byId(comboxobj.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	if(objArr != null){
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].Ma, title: objArr[i].Ten});
		}			
	}
	myCombobox.store = CatalogStore;	
}
function process_searchTextExt(tencot, searchText){
	// bao.ttc
	if (searchText == null || searchText == ""){
		return tencot + " like '%'";
	}
	searchText= searchText.toLowerCase();
	var ketqua = " ( "+ tencot + " like '%"+searchText+"%' ";
	for (i = 0 ; i < searchText.length; i++){
		var the_char = searchText.charAt(i);
		the_char = the_char.toUpperCase();
		
		var chuoi_moi = searchText.substring(0,i);
		chuoi_moi = chuoi_moi + the_char;
		chuoi_moi = chuoi_moi + searchText.substring(i+1);
		ketqua = ketqua+ " or "+ tencot + " like '%"+chuoi_moi+"%' ";
	}
	ketqua = ketqua+ " or "+ tencot + " like '%"+ searchText.toUpperCase() + "%' ";
	ketqua = ketqua+ " ) ";
	return ketqua;
}
function process_searchText(searchText){
	if (searchText == null){
		searchText = "";
	}
	//searchText = searchText.toLowerCase();
	searchText = searchText.trim();
	searchText = replaceVNChar(searchText);
	searchText = "" + searchText + "%";
	/*
	for(var i=0; i<searchText.length; i++){
		if((searchText.substr(0, 1)) == " "){
			searchText = searchText.slice(1, searchText.length - 1);
		}
		else{
			break;
		}		
	}
	var searchTextArr = searchText.split("");
	var searchTextUse = "%";
	if(searchText != ""){
		if(searchTextArr[0].isUpperCase()){
			for(var i=0; i<searchTextArr.length; i++){
				searchTextUse +=  searchTextArr[i] + "%";
				//searchTextUse += " " + searchTextArr[i] + "%";
			}	
		}
		else{
			//searchTextUse += " " + searchText + "%";
			searchTextUse += searchText + "%";
		}
	}
	*/
	return searchText;
}

function countRecord(tableName,condition){

  try{
	try{
		db.open('database-Quan_Ly_Y_Te');
	}catch(e){
	}
	
	var arr1 = tableName.split("__");
		
		
	tableName = arr1[0];
	
	var arr2 = tableName.split(":");	
	if(arr2.length > 1){
		tableName = arr2[arr2.length - 1];
	} 
	//alert(tableName);
	var rs;
	//alert(condition);
	if(condition == ""){
		rs = db.execute("select count(*) from " + tableName + "  AND DT = 1 ");
	}
	else{
		rs = db.execute("select count(*) from " + tableName + " where " + condition + " AND DT = 1 ");
		//rs = db.execute("select count(*) from " + tableName + " where Ten like '%Điện%'" );
		
	}	
	var countRecord = rs.field(0);
	//alert(countRecord);
	try{
		db.close();
	}catch(e){
	}	
	//alert("condition:"+condition);
	//alert(countRecord);
	
	var result = false;
	//alert("select count(*) from " + tableName + " where " + condition)
	//alert(countRecord)
	if(countRecord <= parseInt( number_max_recode)){
		result = true;
	}
	return result; 
  }catch(e){
  	return false;
  }		
}

function setHiddenMaSo (textboxId,tableName){
	var maValue = document.getElementById(textboxId).value;
	
	
	var arr2 = tableName.split("__");
	var myTableName = "";
	var at_prefix_component =  tableName.indexOf(prefix_component);
	if (at_prefix_component >= 0) {
		myTableName = tableName.substring(at_prefix_component + prefix_component.length);
		if (myTableName.indexOf("__") > 0) {
		  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
		}		
	} else {
		if(arr2.length > 1){
			for(i = 0; i < arr2.length - 1; i++){
			    if (i == 0){
			       myTableName += arr2[i];
			    }else{
			     myTableName += "_" + arr2[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr2[0];
		}
	}
	
	var maSoValue = getMaSo(myTableName,"Ma",maValue);
	
	//alert(textboxId);
	//alert(textboxId + DM_pk);
	try {
		document.getElementById(textboxId + DM_pk).value = maSoValue;
	} catch (e) {
	
	}

}
function setHiddenMaSo_TinhBHYT (textboxId,tableName){
	var maValue = document.getElementById(textboxId).value;	
	
	var arr2 = tableName.split("__");
	var myTableName = "";
	var at_prefix_component =  tableName.indexOf(prefix_component);
	if (at_prefix_component >= 0) {
		myTableName = tableName.substring(at_prefix_component + prefix_component.length);
		if (myTableName.indexOf("__") > 0) {
		  myTableName = myTableName.substring(0,myTableName.indexOf("__"));
		}		
	} else {
		if(arr2.length > 1){
			for(i = 0; i < arr2.length - 1; i++){
			    if (i == 0){
			       myTableName += arr2[i];
			    }else{
			     myTableName += "_" + arr2[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr2[0];
		}
	}
	
	var maSoValue = getMaSo(myTableName,"MaTinhBHYT",maValue);
	
	//alert(textboxId);
	//alert(textboxId + DM_pk);	
	try {
		document.getElementById(textboxId + DM_pk).value = maSoValue;
	} catch (e) {
	
	}

}
function setAttrForCombobox_readOnly(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = onchange_AdvanceCode;
	var onblur_Code = onblur_AdvanceCode;
	var onfocus_Code = onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.readonly=true;
	myCombobox.alt="readonly";	
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
}

function setAttrForCombobox(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurCombobox(\'" + textboxId + "\', \'" + comboboxId + "\'); setHiddenMaSo(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}




function setAttrForComboboxForKCBBHYT(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurComboboxKCBBHYT(\'" + textboxId + "\', \'" + comboboxId + "\'); setHiddenMaSo(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}


function setAttrForComboboxNoOnBlurCombobox(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurComboboxForNoOnBlur(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}


function setAttrForComboboxForHuyen(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurComboboxForHuyen(\'" + textboxId + "\', \'" + comboboxId + "\'); setHiddenMaSo(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}

function setAttrForComboboxForXa(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurComboboxForXa(\'" + textboxId + "\', \'" + comboboxId + "\'); setHiddenMaSo(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}

function setAttrForComboboxForTinhBHYT(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	var onchange_Code = "if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	var onblur_Code = "assignAltToValue(\'" + textboxId + "\'); myOnblurComboboxForTinhBHYT(\'" + textboxId + "\', \'" + comboboxId + "\'); setHiddenMaSo_TinhBHYT(\'" + textboxId + "\', \'" + comboboxId + "\'); " + onblur_AdvanceCode;
	var onfocus_Code = "listCatalog_all(\'" + comboboxId + "\',\'" + getDB_funcName + "\');" + onfocus_AdvanceCode;
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	myCombobox.onfocus = onfocus_Code;
	if (myCombobox.alt == null || myCombobox.alt == ""){
		myCombobox.alt = getDB_funcName;
	}
	//alert(myCombobox.alt);
	//alert(getDB_funcName);
}
/*
var functCall;
var var_combobox ;
function callMeInCaseUserChoose(combobox){
	var_combobox = combobox;
	try{
	    if (functCall == null || functCall == ""){
	    	return;
	    }else{
	    	eval(functCall);
	    }
		
	}catch(e){
	
	}	
}
*/

var mydatatodisplay;	
function setAttrForComboboxForNoDB(textboxId, spanId, comboboxId, getDB_funcName, onchange_AdvanceCode, onblur_AdvanceCode, onfocus_AdvanceCode){
	//var onchange_Code = "alert('arguments[0]:'+arguments[0]);if (arguments[0] != null) { document.getElementById(\'" + textboxId + "\').value=arguments[0]; }" + onchange_AdvanceCode;
	//var onblur_Code = onblur_AdvanceCode ;
	//var onfocus_Code = onfocus_AdvanceCode;
	
	//var onchange_Code = "alert('arguments[0]:'+arguments[0]); if (arguments[0] != null) {dojo.byId(\'" + textboxId + "\').alt=arguments[0]; }" + onchange_AdvanceCode;
	//var onblur_Code = "alert('2_arguments[0]:'+arguments[0]);  assignAltToValue(\'" + textboxId + "\'); " + onblur_AdvanceCode;
	//var onfocus_Code = onfocus_AdvanceCode;
	
	var onchange_Code = "if (arguments[0] != null) {document.getElementById(\'" + textboxId + "\').value=arguments[0]; } ;" + onchange_AdvanceCode;
	var onblur_Code =  onblur_AdvanceCode;
	//var onfocus_Code =  onfocus_AdvanceCode;
	
	
	var mySpan = document.getElementById(spanId);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myCombobox = document.getElementById(comboboxId);
	myCombobox.dojoType="dijit.form.FilteringSelect";
	myCombobox.invalidMessage="";
	myCombobox.searchAttr="title";	
	myCombobox.pageSize=100;
	myCombobox.autoComplete=false;
	
	myCombobox.onchange = onchange_Code;
	myCombobox.onblur = onblur_Code;
	//myCombobox.onfocus = onfocus_Code;
}

function listCatalogNormal_All_bk(comboboxId, conditionId_arr, logic_arr){
	var myCombobox = document.getElementById(comboboxId);
	var tableName = ((myCombobox.name).split("_displayed_"))[0];
	var Object = getObject(tableName);
	var objArr;
	if(conditionId_arr == null){
		objArr = Object.all().toArray();
	}
	else{
		var myCondition = "";
		
		for(var i=0; i<conditionId_arr.length; i++){
			var myConditionId = (conditionId_arr[i].split(":"))[(conditionId_arr[i].split(":")).length - 1];
			if(i == (conditionId_arr.length - 1)){				
				myCondition += myConditionId + " = " + document.getElementById(conditionId_arr[i]).value;
			}
			else{
				myCondition += myConditionId + " = " + document.getElementById(conditionId_arr[i]).value;
				if(conditionId_arr.length > 1){
					myCondition += " " + logic_arr[i] + " ";
				}
			}			
		}
		objArr = Object.filter(myCondition).toArray();	
	}
	if ( Object != null ) {
		if(objArr.length <= parseInt( number_max_recode)){
			var search = dijit.byId(comboboxId);
			var jsonData = { identifier: "id", items: [], label: "title" };
			var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
			CatalogStore.newItem({id: "", title: ""});
			for (var i = 0; i < objArr.length; i++) {
				CatalogStore.newItem({id: objArr[i].Ma, title: objArr[i].Ten});
			}		
			search.store = CatalogStore;	
	    }  
	}	
}
 

//code Ngoc add
function listCatalogDonViByTuyen_CT(myCombobox, tuyenid, chuongtrinhid){
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");
	
	//ngoc added
		tuyenTextbox = document.getElementById(prefix_component + tuyenid);
		tuyenma = tuyenTextbox.value;
		chuongtrinhTextbox = document.getElementById(prefix_component + chuongtrinhid);
		chuongtrinhma = chuongtrinhTextbox.value;
		//if (tuyenma == null || tuyenma == ""){
			//tuyenma.value ="";
		//	document.getElementById(prefix_component + father_id).focus();
		//	return;	
		//}
		//end ngoc added

	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	

    var rs ;
    var rs1;
	try{
			//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  TuyenMa like \'" + tuyenma + "%\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );
			rs1 = db.execute("select MaSo, Ten from " + myTableName + " where   DT = 1  AND Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  MaSo = \'" + tuyenma + "\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );   
		   	rs = db.execute("select MaSo, Ten from " + myTableName + " where  DT = 1  AND Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  TuyenMa = \'" + tuyenma + "\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );	
		   		
		   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs1.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs1.fieldByName("MaSo"), title:   rs1.fieldByName("Ten")});
			rs1.next();
			
			//i++;
			
		}
		try{
			rs1.close();
		}catch(e){
		   alert("rs1 close(): " + e);
		}
		
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogNormal():"+e );
	}
}
function listCatalog_Huyen_Xa(myCombobox , father_id){ //father_id == TINH_MA or father_id == HUYEN_MA
  
    try{
		comboboxId = myCombobox.id;
		arr1 = comboboxId.split(":");
	
		//thanh added
		fathertextbox = document.getElementById(prefix_component + father_id);
		value_father = fathertextbox.value;
		if (value_father == null || value_father == ""){
			fathertextbox.value = "";
			document.getElementById(prefix_component + father_id).focus();
			return;	
		}
		//end thanh added
		
		comboboxIdJSF = arr1[arr1.length - 1];
		arr2 = comboboxIdJSF.split("__");
		myTableName = "";
		if(arr2.length > 1){
			for(i = 0; i < arr2.length - 1; i++){
			    if (i == 0){
			       myTableName += arr2[i];
			    }else{
			     myTableName += "_" + arr2[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr2[0];
		}
	
		
	    var rs ;
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");   
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		 //rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'" );	   	
	   	
		   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		var Object = getObject(myTableName);
		//alert(value_father);
		var objArr = Object.filter("RefMa = \'" + value_father + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].Ma, title:   objArr[i].Ten});
			//alert(objArr[i].Ten);
		}
	
		/*
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		*/
		search.store = CatalogStore;
		//alert(1);
		//document.getElementById(prefix_component + XA_MA).focus();	
		
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	  // alert("catch .... in....listCatalogNormal():"+e );
	}
}


function listCatalogJSF(myCombobox){
    //alert(1);
	myComboboxId = myCombobox.id;
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	/*
	try{
		//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
	   	rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	   	
	   
	   }
	   catch(e){
	    alert(e);
	   }
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myTableName);
	var objArr = Object.filter("Ten like \'" + myCombobox.getDisplayedValue() + "%\'").toArray();
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].Ma, title:   objArr[i].Ten});
	}
		
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MaSo"), title: rs.fieldByName("Ten")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}

function listCatalogJSF_Huyen_Xa(myCombobox, father_id){ //father_id == TINH_MA or father_id == HUYEN_MA
    //alert(1);
	myComboboxId = myCombobox.id;
	
	//thanh added
	fathertextbox = document.getElementById(prefix_component + father_id);
	value_father = fathertextbox.value;
	if (value_father == null || value_father == ""){
		fathertextbox.value = "";
		try{
		document.getElementById(prefix_component + father_id).focus();
		}catch(e){
		  //in case of hidden element
		}
		return;	
	}
	//end thanh added
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	/*
	try{
		//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'");
	   	rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'" );	   	
	   
	   }
	   catch(e){
	    alert(e);
	   }
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myTableName);
	var objArr = Object.filter("RefMa like \'" + value_father + "%\'").toArray();
	//alert(1);
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].Ma, title:   objArr[i].Ten});
	}
		
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MaSo"), title: rs.fieldByName("Ten")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}

function assignAltToValue(compId){
	myComp = dojo.byId(compId);
	//alert("myComp.alt :"+myComp.alt );
	if(myComp.alt != "undefined"){
		myComp.value = myComp.alt;
	}
	//alert("myComp:"+myComp);
}

function createXmlHttpRequest() {
    var xmlHttp;
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp;
}

/*
function getCatalogFromServerIfNotExisted(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select count(*)  from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
      alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        if (myCondition > 0){
          return;
        }
        rs.next();
    }
    rs.close(); 
    
    url = myContextPath + "/actionServlet?urlAction=" + urlAction ;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,""]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/

function handlerReadyStateChangeForGetPriceKyThuatFromServer(xmlHttp){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	

  var jsonExpression = "(" + xmlHttp.responseText + ")";
	//alert(jsonExpression);
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		var i = 0;
		    if(typeof(data.list) == "object"){
		        while (data.list.record[i] != null){
		                
		                data1 = data.list.record[i];
		                //alert("data1:"+data1); 
		                //alert("data1.MaSo:"+data1.MaSo);
		                if(data1.MaSo != null && data1.MaSo != ''){
		                    //alert("into if ");
		                    var donGia;
		                    var donGiaBH;
		                    var donGiaYC;
		                    var donGiaMP;
		                    var phanDV;
		                    //alert("2");
		                    donGia = unescape(data1.DonGia);
		                    //alert("22");
		                    donGiaBH = unescape(data1.DonGiaBH);
		                    //alert("23");
		                    donGiaYC = unescape(data1.DonGiaYC);
		                    //alert("24");
		                    donGiaMP = unescape(data1.DonGiaMP);
		                    //alert("25");
		                    phanDV = unescape(data1.PhanDV);
		                    document.getElementById(prefix_component + "hid_Gia").value = donGia;
		                    //alert("26");
		                    document.getElementById(prefix_component + "hid_GiaBH").value = donGiaBH;
		                    //alert("27");
		                    document.getElementById(prefix_component + "hid_GiaYC").value = donGiaYC;
		                 	//alert("28");
		                 	document.getElementById(prefix_component + "hid_GiaMP").value = donGiaMP;
		                 	
		                 	document.getElementById(prefix_component + "hid_PhanDV").value = phanDV;
		                 	
		                 	document.getElementById(prefix_component + "__mien").checked = (data1.mien== 'false' ? false : true);
		                 	document.getElementById(prefix_component + "__ndm").checked = (data1.ngoaiDM == 'false' ? false : true);
		                 	document.getElementById(prefix_component + "__ktc").checked = (data1.ktc== 'false' ? false : true);
		                 	
		                 	document.getElementById(prefix_component + "__mien_hid").value = data1.mien;
		                 	document.getElementById(prefix_component + "__ndm_hid").value = data1.ngoaiDM;
		                 	document.getElementById(prefix_component + "__ktc_hid").value = data1.ktc;
		                 			                 	
		                 	//alert("donGiaBH:"+donGiaBH);
		                 	//alert("donGiaYC:"+donGiaYC);
		                 	//alert("donGiaMP:"+donGiaMP);  
		                 	
		                 	setOtherValueForGetFromServer();
		                 	  
		                    return;
		                }
						i=i+1;
		            }
		     }
		     
		    
        }
}

/**


**/
function getPriceKyThuatFromServer(urlAction,myCondition){
  //alert("getPriceKyThuatFromServer"); 
  	try{
		//if (!google.gears.factory || !db) {
		//	return;
	    //}
	//      alert("getPriceKyThuatFromServer2"); 
	var xmlHttp = createXmlHttpRequest();    
	var url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	//alert("myCondition:"+myCondition);
    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	handlerReadyStateChangeForGetPriceKyThuatFromServer(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 	
	    
	   
	 }catch(e){
	    alert("getPriceFromServer: error with params: " + urlAction + ","+ myCondition );
	 }    
}
/*
function getDefaultValue(defaultVal, textbox) {
	
}
*/

/**

**/
function getDefaultValue(key,textbox){
	
	var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = key;
    
        
//  alert("myCondition:"+myCondition);

    url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI("GetValueDefaultAction") + "&xmlData=" + encodeURI(myCondition);

  //url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;

    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	var ret =  	handleStateChange_default(xmlHttp);
    	if (ret != null && ret != "") {
    		//alert("ret = " +  ret );
    		if ( ret == "NAM" ) {
    			document.getElementById(prefix_component + textbox).value = "true";
    		} else if ( ret == "NU" ) {
    			document.getElementById(prefix_component + textbox).value = "false";
    		} else {
    			document.getElementById(prefix_component + textbox).value = ret;
    		}
    		
    	}
    };
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 
  
}

function handleStateChange_default(xmlHttp1) {
if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  var jsonExpression = "(" + xmlHttp1.responseText + ")";
	//alert(jsonExpression);
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		var i = 0;
		    if(typeof(data.list) == "object"){
		       while (data.list.record[i] != null){
		       		//alert( data.list.record[i]);
		            data1 = data.list.record[i];                
		            if(data1.value != null && data1.value != ""){                	
		                var value = unescape(data1.value);     
		               //alert("value = " + value);              
		                return value;
		            }
					i=i+1;
		       }
		     }
        }
 

}
/*
function getCatalogFromServer(DBTableName,urlAction){

    //alert(DBTableName);
    //alert(urlAction);
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        //alert("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
        //alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
    
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
   
   //alert("url:"+url);
   //alert(url)
    xml = new JKL.ParseXML( url );
    //alert("xml"+xml);
    data = xml.parse();    
    
   // alert("data;" + data);
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i]; 
                //alert("data1.MaSo:"+data1.MaSo);               
                if(data1.MaSo != null){                	
                    var ten;
                   // alert(99991);
                    ten = unescape(data1.Ten); 
                    // alert("ten:"+ten);
                    // alert();           
                    // alert(99992);         
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,""]);                    
                    // alert(i);
                }
				i=i+1;
            }
        }    
}
*/
/*

//duphong
function getCatalogFromServerForDVDP(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
    
    //var url = myContextPath + "/actionServlet";
    
    //var params = "urlAction="+ urlAction + "&xmlData=" + myCondition;
    //xmlHttp.open("POST", url, true);
    //xmlHttp.onreadystatechange = handleStateChange;
   // xmlHttp.setRequestHeader("Content-Type", "text/x-www-form-urlencoded;");
   // xmlHttp.send(params); 
   // xml= xmlHttp.responseXML;
   // alert(xml);
   // data = $.xml2json(xml);
   // alert(data);
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,"",data1.TuyenMa,data1.ChuongtrinhMa]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function getCatalogFromServerDiaChi(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close();  
    //var url = myContextPath + "/actionServlet";
    
    //var params = "urlAction="+ urlAction + "&xmlData=" + myCondition;
    //xmlHttp.open("POST", url, true);
    //xmlHttp.onreadystatechange = handleStateChange;
   // xmlHttp.setRequestHeader("Content-Type", "text/x-www-form-urlencoded;");
   // xmlHttp.send(params); 
   // xml= xmlHttp.responseXML;
   // alert(xml);
   // data = $.xml2json(xml);
   // alert(data);
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];
                if(data1.MaSo != null){
                    var ten;
                    ten = unescape(data1.Ten);
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,"",data1.RefMa]);
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function getCatalogFromServerDMT(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close();  
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];
                if(data1.MaSo != null){
                    var ten;
                    ten = unescape(data1.Ten);
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.DonVi,data1.NgayChinhSua,""]);
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function sendId_receiveString(idText,urlAction,view_comp_list){
	if (!google.gears.factory || !db) {
        return;
    }
    var myCondition;
    var url;
    var xml;
    var data;
    var myView_comp_list = view_comp_list.split("---");    
    
    if(document.getElementById(idText).value != ""){
    	myCondition = document.getElementById(idText).value;
    	
    	var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    	xml = new JKL.ParseXML( url );
    	data = xml.parse();
    	i = 0;
    	while (data.list.result[i] != null){
    		data1 = data.list.result[i];
    		if(data1.value != null)
	        	document.getElementById(myView_comp_list[i]).value = data1.value;          
	        i=i+1;
	    }
    }   
}
*/
function sendId_receiveStringForPhieuNhap(idDmt, idKho, urlAction, view_comp_list){
	var myCondition;
    //var url;
    //var xml;
   // var data;
   	var xmlHttp = createXmlHttpRequest();
   	var myView_comp_list = view_comp_list.split("---");
    if(document.getElementById(idDmt).value != "" && document.getElementById(idKho).value){
    	myCondition = document.getElementById(idDmt).value;
    	khoMa = document.getElementById(idKho).value;
    }
    
       
     url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition + "|" + khoMa);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){handleStateChangePN(xmlHttp,myView_comp_list);};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
	 
}
/**

**/
function handleStateChangePN(xmlHttp1,myView_comp_list) {
	if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  	var jsonExpression = "(" + xmlHttp1.responseText + ")";
	//alert(jsonExpression)
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		if ( data.list != null && typeof(data.list) == "object") {
			//alert("bbb");
	    i = 0;
	    	
            while (data.list.result[i] != null){ 
            	//alert("aaa" + data.list.record[i]);           
				data1 = data.list.result[i];
				if(data1.value != null)
	        	document.getElementById(myView_comp_list[i]).value = data1.value;          
	        	i=i+1;
            }
        }
  }
 

}

/**

**/
function getNameById_Catalog(myId,Object){
	//alert("myId: " + myId);
	var arName_Catalog = Object.filter("Ma = ? AND DT = 1 ", myId).toArray();
	//var rsName_Catalog = db.execute("select * from " + DBTableName + " where MaSo=\'" + myId + "\'");
    var myTen = "";
    //while (rsName_Catalog.isValidRow()) {
	//	myTen = rsName_Catalog.fieldByName("Ten");
	//	rsName_Catalog.next();
	//}
	//rsName_Catalog.close();
	for (var  i = 0; i < arName_Catalog.length; i ++) {
       myTen = arName_Catalog[i].Ten;
    }
    //alert("myTen: " + myTen);
	return myTen;
}

/**

**/
function createTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
			else
			myTable += arr1[i] + "_";
	}
	var mySql = "";
	mySql += "create table if not exists " + myTable + " (";
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i] + " " + MY_TABLE[1][i];
		else
			mySql += MY_TABLE[0][i] + " " + MY_TABLE[1][i] + ", ";
	}
	mySql += ")";
	db.execute(mySql);
}

/**

**/
function insertTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "insert into " + myTable + " (";
       
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i];
		else
			mySql += MY_TABLE[0][i] + ", ";
	}
	
	mySql += ") values(";
	
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];	
		
		if(i == MY_TABLE[0].length - 1){
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\'";
			}
			else {
				mySql += "\'\'";
			}
		}	
		else {
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\',";
			}
			else {
				mySql += "\'\',";
			}
			
		}	
	}
	
    mySql += ")";
    db.execute(mySql);			
}

/**

**/
function updateTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "update " + myTable + " set ";
    
    //alert(MY_TABLE[0].length);   
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];
	
		if(i == MY_TABLE[0].length -1){
			if ( document.getElementById(prefix_component + myComponent) != null) {
				mySql += MY_TABLE[0][i] + "=\'" + document.getElementById(prefix_component + myComponent).value + "\'";
				//alert("in mysql statement1");
			}
			else {
				//mySql += MY_TABLE[0][i] + "=\'\' ";
			}
		}	
		else{
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += MY_TABLE[0][i] + "=\'" + document.getElementById(prefix_component + myComponent).value + "\', ";
			}
			else {
				//mySql += MY_TABLE[0][i] + "=\'\', ";
			}
		}
	}
	//Manh added
	var length = mySql.length;
	//alert(length);
	var tail = mySql.substr(length-2,1);
	//alert(tail);
	if (tail == ",") {
		mySql = mySql.substr(0, length - 2);
		//alert(mySql);
		
	}
	//Manh end
      mySql += " where " + MY_TABLE[0][0] + "=\'" + 
    		   document.getElementById(prefix_component + 
		   "MAPHU").value + "\'";
	
	//	alert(mySql);
      db.execute(mySql);		
}

/**

**/
function deleteTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	
	db.execute("delete from " + myTable + " where " + MY_TABLE[0][0] + 
			"=\'" + document.getElementById(prefix_component + 
			MY_TABLE[0][0]).value + "\'");
	
	db.execute(mySql);		
}


/**

**/
function getValueCheckOrRadioList(listId){//listId is a selectManyCheckboxId or a selectOneRadioId
	var myArr = [];
   	myArr = document.getElementsByName(listId);
    if(myArr == null || myArr.length == 0){
   		return;
    }
	var myListResult = [];
	var j = 0;
	for(i = 1; i < myArr.length; i++){
		if(myArr[i].checked == true){
			myListResult[j] = myArr[i].value;
			j++;                		
		}     	               	
	}
	
	//myListResult is a array, which has a type: [value1, value2, value3,...];
	if (j == 0){
		return null; 
	}
	else {
		return myListResult;
	}                             
}


/**

**/
function  createXmlDatafromTable(MY_TABLE,rs) { 
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	var xml ="";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	xml += "<" + myTable + " ";
	for(i=0; i<MY_TABLE[0].length; i++){
		myComponent = MY_TABLE[0][i];
		xml += myComponent + "=\'" +  rs.fieldByName(myComponent) + "\' ";	
	}
	xml += ">";
	return xml;
	
}

/**

**/
function getValueCheckOrRadioList(listId){//listId is a selectManyCheckboxId or a selectOneRadioId
	var myArr = [];
   	myArr = document.getElementsByName(listId);
    if(myArr == null || myArr.length == 0){
   		return;
    }
	var myListResult = [];
	var j = 0;
	for(i = 1; i < myArr.length; i++){
		if(myArr[i].checked == true){
			myListResult[j] = myArr[i].value;
			j++;                		
		}     	               	
	}
	
	//myListResult is a array, which has a type: [value1, value2, value3,...];
	if (j == 0){
		return null; 
	}
	else {
		return myListResult;
	}                             
}

/**

**/
function  createXmlDatafromTable1(data, obj) {
	//alert("createXmlDatafromTable1");
	if (obj != null) {
		var xml = "<" + obj.name + " ";
		var fieldNames = obj.getFieldNames();
		for (var i = 0; i < fieldNames.length; i++) {
			xml += fieldNames[i] + "='" + data[fieldNames[i]] + "' ";
		}
		xml += "/>";
	}
	/*
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	var xml="";
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	xml += "<" + myTable + " ";
	for(i=0; i<MY_TABLE[0].length; i++){
		myComponent = MY_TABLE[0][i];
		xml += myComponent + "=\'" +  rs1.fieldByName(myComponent) + "\' ";	
	}
	xml += "/>";
	*/
	return xml;
}

function insertDBTableFrom(MY_TABLE, data1) {
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "insert into " + myTable + " (";
       
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i];
		else
			mySql += MY_TABLE[0][i] + ", ";
	}
	
	mySql += ") values(";
	
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];
			//alert(myComponent);
		if (i == 0 || i == 1){
			if ( document.getElementById(prefix_component + myComponent) != null) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\',";
			}
			else {
				mySql += "\'\',";
			}
		}	
		else if(i == MY_TABLE[0].length - 1){
			if (data1[myComponent] != null && data1[myComponent] != "" ) {
				mySql += "\'" + data1[myComponent] + "\'";
			}
			else {
				mySql += "\'\'";
			}
		}	
		else {
			//alert(data1.length);
			//alert(data1.myComponent);
			if (data1[myComponent] != null && data1[myComponent] != "") {
				mySql += "\'" + data1[myComponent] + "\',";
			}
			else {
				mySql += "\'\',";
			}
			
		}	
	}
	
    mySql += ")";
    db.execute(mySql);
}

//Chi begin

function listCatalogJSF_PhieuXuatBh(myCombobox, data){
	var myComboboxId = document.getElementById(myCombobox);
	
	try{
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("data: " + data);
		
		var i = 0;
		if (data != null){
			while (data.record[i] != null){
	          	data1 = data.record[i];   
	          	//alert('data1: ' + data1);
	            if(data1.MaHang != null){
	               	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon +"___" +data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;     
	                CatalogStore.newItem({id: myId, title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});  
	           	}
				i=i+1;
	         }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         	data1 = data.record;   
	          	//alert('data1: ' + data1);
	            if(data1.MaHang != null){
	               	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon +"___" +data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;     
	                CatalogStore.newItem({id: myId, title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});  
	           	}
	         }
	    }
	    else{	   	  
	    	myComboboxId.value = "";
	    }
		search.store = CatalogStore;
	} 
	catch(e){
	   alert("catch .... in....listCatalogJSF_PhieuXuatBh():"+e );
	}
}

function listCatalogSearchForPhieuXuatBh(myCombobox){
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");
    //alert('arr1: ' + arr1);
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	//alert('arr2: ' + arr1);
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
		}
	}
	else{
	   myTableName = arr2[0];
	}
	
	try{
		if (dataList == null){
		   return;
		}   		
		var i = 0;
		var data = dataList;
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		//alert(1); 
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		//alert(2);	
		
		while (data.record[i] != null){            	
        	    data1 = data.record[i];   
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                //alert(3);
                if ( myComboboxValue == "" || iIndex == 0 ){
                   // alert(4);
	                if(data1.TonKhoMa != null){     
	                	//alert(5);  
	                  	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon + "___" + data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;      
	                    CatalogStore.newItem({id: myId , title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});
	                    //alert(6);  
	                }
	            }    
				i=i+1;
        }
        if (i == 0) { // truong hop nay chi co' 1 record
        		data1 = data.record;   
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                //alert(3);
                if ( myComboboxValue == "" || iIndex == 0 ){
                   // alert(4);
	                if(data1.TonKhoMa != null){     
	                	//alert(5);  
	                  	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon + "___" + data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;      
	                    CatalogStore.newItem({id: myId , title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});
	                    //alert(6);  
	                }
	            }    
        }
        
		search.store = CatalogStore;
		//alert(7);	
	} 
	catch(e){
	   alert("catch .... in....listCatalogSearchForPhieuXuatBh():"+e );
	}
}

function mySetValueForPhieuXuatBh(comboboxId){
   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
   //alert('__listtonkho_ma_2.value:   ' + myValue);
   if (myValue == null || myValue ==""){
      return;
   }
   
   var i = myValue.indexOf("___"); 
   var tonKhoMa = myValue.substring(0,i);   
   myValue = myValue.substring(i+3);
   //alert('tonKhoMa: ' + tonKhoMa);
   document.getElementById(prefix_component + "__tonkhoMa").value = tonKhoMa;
   
   var i = myValue.indexOf("___"); 
   var tonkhoTon = myValue.substring(0,i);   
   //alert('tonkhoTon: ' + tonkhoTon);
   document.getElementById(prefix_component + "__tonkho").value = tonkhoTon;
   myValue = myValue.substring(i+3);
   
   
   var i = myValue.indexOf("___"); 
   var maHang = myValue.substring(0,i);   
   //document.getElementById(prefix_component + "__maHang").value = maHang;
   myValue = myValue.substring(i+3);
   //myOnblurTextboxForDmThuoc(this.id,'DM_THUOC','','');
    
   var i = myValue.indexOf("___"); 
   var tenHang = myValue.substring(0,i);   
   myValue = myValue.substring(i+3);
   //alert('tenHang: ' + tenHang);
   i = myValue.indexOf("___"); 
   var quocGiaSx = myValue.substring(0,i);
   document.getElementById(prefix_component + "__nsx").value = quocGiaSx;
   myValue = myValue.substring(i+3);
   //alert('quocGiaSx: ' + quocGiaSx);
   i = myValue.indexOf("___"); 
   var hangSx = myValue.substring(0,i);
   document.getElementById(prefix_component + "__hsx").value = hangSx;
   myValue = myValue.substring(i+3);
   //alert('hangSx: ' + hangSx);
   
   //i = myValue.indexOf("___"); 
  	var dongia = myValue;
  	document.getElementById(prefix_component + "__dongia").value = dongia;
  // myValue = myValue.substring(i+3);
   //alert('dongia: ' + dongia);
   document.getElementById(prefix_component +'__xuat').focus();
}

function setAttrForComboboxJSFForPhieuXuatBh(_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPhieuXuatBh(\'"+ comboboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }
  catch(e){
    alert("error at setAttrForComboboxJSFForPhieuXuatBh :" +  e);
  }
}

//Chi end
//Manh added
function myOnblurTextbox_DoiTuong(textboxId,comboboxId,textboxId2,hiddenBox){
  try{
  
  if(document.getElementById(textboxId).className.match(/focus/gi))
              document.getElementById(textboxId).className = document.getElementById(textboxId).className.replace(/focus/gi,"");
   
   
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	mycombobox = document.getElementById(comboboxId);
	mytextbox2 = document.getElementById(textboxId2);
	hiddenbox = document.getElementById(hiddenBox);
	if(textboxValue != ""){
		
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("Ma like \'" + textboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			if (objArr[i].Ten != null && objArr[i].Ten != ""){
	        	 mycombobox.value = objArr[i].Ten;
	        	 //alert("TyLeMien: " + rs.fieldByName("TyLeMien"));
	        	 if (objArr[i].TyLeMien != null && objArr[i].TyLeMien != "") {
	        	 	//alert(0);
	        	 	var value = 100 - parseInt(objArr[i].TyLeMien);
	        	 	//alert("value: " + value );
	        	 	mytextbox2.value = value;
	        	 	hiddenbox.value = objArr[i].TyLeMien;
	        	 }
	        	 havingData = true;
	        }
		}
		
		/*
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		var havingData = false;
	    while (rs.isValidRow()) {
	        if (rs.fieldByName("Ten") != null && rs.fieldByName("Ten") != ""){
	        	 mycombobox.value = rs.fieldByName("Ten");
	        	 //alert("TyLeMien: " + rs.fieldByName("TyLeMien"));
	        	 if (rs.fieldByName("TyLeMien") != null && rs.fieldByName("TyLeMien") != "") {
	        	 	//alert(0);
	        	 	var value = 100 - parseInt(rs.fieldByName("TyLeMien"));
	        	 	//alert("value: " + value );
	        	 	mytextbox2.value = value;
	        	 	hiddenbox.value = rs.fieldByName("TyLeMien");
	        	 }
	        	 havingData = true;
	        }
	       
	        //alert(rs.fieldByName("Ten"));
	        
	        //mycombobox.select();
	        //  alert(1);
	        //return;
	        rs.next();
	    }
	    */
	    if (havingData == true){
	       //alert("mycombobox.value__true:"+mycombobox.value);
	       return;
	    }
	    //alert("mycombobox.value:"+mycombobox.value);
	    //alert("----------------");
	    /*
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    	hiddenbox.value = "";
	    }
	    */
	     //alert(3);
	    //rs.close();
	    //  alert(4);
	    mycombobox.value = "";   
	    //alert("mycombobox.value:"+mycombobox.value);
	    mytextbox.value = "";
	    hiddenbox.value = "";
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
    }else{
    	mycombobox.value = ""; 
    	hiddenbox.value = "";
    }
  }catch(e){
    alert("myOnblurTextbox():"+e);
  }  
      
}
//Manh end
//Manh added

//Manh end
// Manh added
function setAttrForCombobox_DoiTuong(textboxId,mySpan,comboboxId,pageSize,textboxId2,hiddenBox){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_DoiTuong(\'" + textboxId + "\',\'" + comboboxId + "\',\'"+ textboxId2+ "\',\'"+ hiddenBox + "\'); assignAltToValue(\'" + textboxId + "\')";


	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
}
// Manh end

//Manh added
/*
function getCatalogFromServer_TienKham(DBTableName,urlAction){

	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
        //alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
   
    xml = new JKL.ParseXML( url );
    data = xml.parse(); 
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];               
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);       
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.DonGia,data1.NgayChinhSua,""]); 
                }
				i=i+1;
            }
        }
    
}
*/
//Manh end
//Manh added
function myOnblurTextbox_TienKham(textboxId, comboboxId){
	myOnblurTextbox(textboxId, comboboxId);
	setTienKham();
}
//Manh end
// Manh added
function setAttrForCombobox_TienKham(textboxId,mySpan,comboboxId,pageSize,textboxId2,textboxId3){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_TienKham(\'" + textboxId + "\',\'" + comboboxId + "\',\'"+ textboxId2+ "\',\'"+ textboxId3+ "\'); assignAltToValue(\'" + textboxId + "\')";


	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
}
// Manh end
//Manh added
function myOnblurCombobox_TienKham(textboxId,comboboxId,textboxId2,textboxId3){
	//alert("myOnblurCombobox_TienKham");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	
	mytextbox = document.getElementById(textboxId);
	mytextbox2 = document.getElementById(textboxId2);
	mytextbox3 = document.getElementById(textboxId3);
	if(comboboxValue != ""){
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("Ten like \"" + comboboxValue + "\"").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].Ma;
	         if (objArr[i].DTDMTIENKHAM_DONGIA != null) {
	        	 	//alert(0);
	        	 	if(mytextbox3.value != null && mytextbox3.value != "") {
	        	 		var value = objArr[i].DTDMTIENKHAM_DONGIA * parseInt(mytextbox3.value)* 0.01;
	        	 		//alert("value: " + value );
	        	 		mytextbox2.value = value;
	        	 	}
	        	 	else {
	        	 		mytextbox2.value = objArr[i].DTDMTIENKHAM_DONGIA;
	        	 	}
	        	 }
	        	 havingData = true;
		}
	    if (havingData == true) {
	    	return;
	    }
	    mytextbox.alt = "";
	    mytextbox2.value = "";
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    //rs.close();	    
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	mytextbox2.value = "";
    }
      
} 
// Manh chuan hoa theo anh bao 
function getDefaultValue(key,textbox){
	//alert(1);
	var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = key;
    
    url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI("GetValueDefaultAction") + "&xmlData=" + encodeURI(myCondition);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	var ret =  	handleStateChange_default(xmlHttp);
    	if (ret != null && ret != "") {
    		//alert("ret = " +  ret );
    		if ( ret == "NAM" ) {
    			document.getElementById(prefix_component + textbox).value = "true";
    		} else if ( ret == "NU" ) {
    			document.getElementById(prefix_component + textbox).value = "false";
    		} else {
    			document.getElementById(prefix_component + textbox).value = ret;
    		}
    		
    	}
    	};
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xmlHttp.send(params); 
  
}

function handleStateChange_default(xmlHttp1) {
if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  var jsonExpression = "(" + xmlHttp1.responseText + ")";
	var data = eval(jsonExpression);
		var i = 0;
		    if(typeof(data.list) == "object"){
		       while (data.list.record[i] != null){
		       		data1 = data.list.record[i];                
		            if(data1.value != null && data1.value != ""){                	
		               var value = unescape(data1.value);     
		               return value;
		            }
					i=i+1;
		       }
		     }
        }
 

}


// Manh chuan hoa theo anh bao 
//getCatalogFromServer_new(GetDmKyThuatAction,DtDmClsBangGia)
function getCatalogFromServer_new(urlAction,obj){
    var myCondition;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = 0;
    
    try {
    /*
    	if (obj.name == "DT_DM_BAC_SI_BAN_KHAM" || 
    		obj.name == "DT_DM_NHANVIEN_KHOA" || 
    		obj.name == "DT_DM_CLS_KHOA") {
			obj.all().remove();
		} 
    */	
    	var max =  obj.all().aggregateFuncSql("max(NgayChinhSua)");
    	//alert(max);
    	if ( max != null ) {
    		myCondition = max;
    	}
    } catch (e) {
    	//alert( "eeeeeeee:" + e.description ) ;
    }
    
     url = myContextPath + "/actionServlet?";
   
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
    // alert("param = " + params);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function() {
    	handleStateChange(xmlHttp, obj);
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
     
}

function removeAll(obj) {
	obj.all().remove();
}

function handleStateChange(xmlHttp1, obj) {
	if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
		var jsonExpression = "(" + xmlHttp1.responseText + ")";
		//alert("json = " + jsonExpression);
		var data = eval(jsonExpression);
		if ( data.list != null && typeof(data.list) == "object") {
			var i = 0;
			
            while (data.list.record[i] != null) {
				var jsonData = data.list.record[i];
				var foundObj = null;
				try {
					foundObj = obj.getByFieldValue("MaSo", jsonData.MaSo);
				} catch (e) {
					
				}
				if (foundObj) {
					var fieldNames = obj.getFieldNames();
     				for(var x = 0; x < fieldNames.length; x++){
     					if(fieldNames[x] == 'Ten'){
							//foundObj[fieldNames[x]] = " " + jsonData[fieldNames[x]];
							foundObj[fieldNames[x]] = jsonData[fieldNames[x]];
						}else{
							foundObj[fieldNames[x]] = jsonData[fieldNames[x]];
						}	
     				}
     				foundObj.save();
				} else {
					obj.transaction(function() {
						var obj1=new obj(jsonData);
						//obj1.Ten = " " + obj1.Ten; 
						obj1.Ten = obj1.Ten; 
						obj1.save();
					});
				}
				
				i = i + 1;
        	}
        	
        	if (i == 0) {
        		var jsonData = data.list.record;
				var foundObj = null;
				try {
					foundObj = obj.getByFieldValue("MaSo", jsonData.MaSo);
				} catch (e) {
					
				}
				if (foundObj) {
					var fieldNames = obj.getFieldNames();
     				for(var x = 0; x < fieldNames.length; x++){
						if(fieldNames[x] == 'Ten'){
							//foundObj[fieldNames[x]] = " " + jsonData[fieldNames[x]];
							foundObj[fieldNames[x]] = jsonData[fieldNames[x]];
						}else{
							foundObj[fieldNames[x]] = jsonData[fieldNames[x]];
						}     					
     				}
     				foundObj.save();
				} else {
					obj.transaction(function() {
						var obj1=new obj(jsonData);
						//obj1.Ten = " " + obj1.Ten; 
						obj1.Ten = obj1.Ten; 
						obj1.save();
					});
				}
				
        	}
    	}
	}
}

function insertObjectFromData(Object, fields, values) {
	var jsonData = "({";
	for (var i = 0; i < fields.length; i++) {
		if (i == 0) {
			jsonData += "'" + fields[i] + "':'" + values[i] + "'" ;
		} else {
			jsonData += ", '" + fields[i] + "':'" + values[i] + "'" ;
		}
	}
	jsonData += ")}";
	var obj = new Object(eval(jsonData));
	Object.transaction(function() {
		obj.save();
	});
}

// Lấy thông tin từ giao diện insert xuống database local
function insertObject(Object,arrayNames,arrayValues){
	
	//alert(Object);
	var fieldNames = Object.getFieldNames();
	//alert(fieldNames);
	var array = "({";
	
	
	for(var i=0;i<fieldNames.length;i++){
		/*
		if (fieldNames[i] == "TIEPDON_TYLEBH") {
			alert(document.getElementById(prefix_component + fieldNames[i]).value);
		}
		*/
		if ( document.getElementById(prefix_component + fieldNames[i]) != null && document.getElementById(prefix_component + fieldNames[i]).value != "") {
			if( array == "({" ) {
				array +=  "\"" + fieldNames[i] + "\"" + ": \"" + document.getElementById(prefix_component + fieldNames[i]).value + "\"" ;
			}
			else {
				array +=  ", \"" + fieldNames[i] + "\"" + ": \"" + document.getElementById(prefix_component + fieldNames[i]).value  + "\"";
			}
		}
		
	}
	
	if ( arrayNames != null ) {
		//alert("arrayNames.length : " + arrayNames.length);
		for (var j = 0; j < arrayNames.length ; j++) {
			if ( arrayValues[j] != null ) {
				array += ", \"" + arrayNames[j] + "\"" + ": \"" +  arrayValues[j] + "\"";
			}
			else {
				//object[arrayNames[j]] = null ;
			}
		}
	}
	array += "})";
	//alert("array :" + array);
	var data = eval(array);
	var object = new Object( data ) ;
	//object.PHIEU_NHAP_KHO_MAPHU = MAPHU_hidden;
	//alert(20000);
	Object.transaction(function(){				
		object.save();
	});
	
	//alert(20001);
}

function insertObjectWithTrueValue(Object,arrayNames,arrayValues){
	
	//alert(Object);
	var fieldNames = Object.getFieldNames();
	//alert(fieldNames);
	var array = "({";
	
	
	for(var i=0;i<fieldNames.length;i++){
		
		if ( arrayValues[i] != null && arrayValues[i] != "") {
			if( array == "({" ) {
				array +=  "\"" + fieldNames[i] + "\"" + ": \"" + arrayValues[i] + "\"" ;
			}
			else {
				array +=  ", \"" + fieldNames[i] + "\"" + ": \"" + arrayValues[i]  + "\"";
			}
		}
		
	}
	
	if ( arrayNames != null ) {
		//alert("arrayNames.length : " + arrayNames.length);
		for (var j = 0; j < arrayNames.length ; j++) {
			if ( arrayValues[j] != null ) {
				array += ", \"" + arrayNames[j] + "\"" + ": \"" +  arrayValues[j] + "\"";
			}
			else {
				//object[arrayNames[j]] = null ;
			}
		}
	}
	array += "})";
	//alert("array :" + array);
	var data = eval(array);
	var object = new Object( data ) ;
	
	Object.transaction(function(){				
		object.save();
	});
	
	//alert(20001);
}

// Ham update 
function updateObject(Object,fieldName,values){
	 var objectarr =   Object.filter(fieldName + " = ?", values).toArray();
	 
     if ( objectarr != null &&  objectarr.length > 0 ) {
     	var object = objectarr[0];
     	
     	var fieldNames = Object.getFieldNames();
     	for(var i=0;i<fieldNames.length;i++){
     		if ( document.getElementById(prefix_component + fieldNames[i]) != null) {
     			//alert(fieldNames[i] + " : " + document.getElementById(prefix_component + fieldNames[i]).value);
     			object[fieldNames[i]] = document.getElementById(prefix_component + fieldNames[i]).value;
				
			}
     	}
     	Object.transaction(function(){
				object.save();
			});
     }
}


// Ham update 
function updateObjectWithTrueValue(Object,fieldName,value,fieldNames,values){
	 var objectarr =   Object.filter(fieldName + " = ?", value).toArray();
	 
     if ( objectarr != null &&  objectarr.length > 0 ) {
     	var object = objectarr[0];
     	
     	
     	for(var i=0;i<fieldNames.length;i++){
     		if ( values[i] != null) {
     			object[fieldNames[i]] = values[i];
				
			}
     	}
     	Object.transaction(function(){
				object.save();
			});
     }
}

function addMonthsToDate(startDate, numMonths) {
    var addYears = Math.floor(numMonths/12);
    var addMonths = numMonths - (addYears * 12);
    var newMonth = startDate.getMonth() + addMonths;
    if (startDate.getMonth() + addMonths > 11) {
      ++addYears;
      newMonth = startDate.getMonth() + addMonths - 12;
    }
    var newDate = new Date(startDate.getYear()+addYears,newMonth,startDate.getDate(),startDate.getHours(),startDate.getMinutes(),startDate.getSeconds());

    // adjust to correct month
    while (newDate.getMonth() != newMonth) {
      newDate = addDaysToDate(newDate, -1);
    }

    return newDate;
}
function addDaysToDate(myDate,days) {
    return new Date(myDate.getTime() + days*24*60*60*1000);
}

function set_ngaysinh(tuoiId,ngaysinhId){
  //try{
	tuoi = document.getElementById(tuoiId).value;
	//alert(1);
	if ( tuoi == ""){
		return;
	}
	if(tuoi == 0 || tuoi > 150){
		document.getElementById(tuoiId).value = '';
		document.getElementById(tuoiId).focus();
		return;
	}
	else {
	
	    var donViTuoi = getDonViTuoi();
	    if (donViTuoi == "1"){ // nam tuoi
	    	//alert(1);
	    	document.getElementById(ngaysinhId).value ="01/01/" + ( new Date().getFullYear() - parseInt(tuoi));
	    }else if (donViTuoi == "2"){ //thang tuoi
	    	//alert(2);
	    	var today = new Date();
	        var newDate = addMonthsToDate(today,-1*parseInt(tuoi));
	        
	        var _year = newDate.getFullYear(); 
			var _month = newDate.getMonth() + 1; 
			if (_month.toString().length == 1){
	           _month = "0" + _month;
	        }
			var _day = "01"; 
	        
	        document.getElementById(ngaysinhId).value =_day + "/" + _month + "/" + _year;
	         
	    }else if (donViTuoi == "3"){ //ngay tuoi
	    	var today = new Date();
	       
	        var newDate = addDaysToDate(today,-1*parseInt(tuoi));
	        
	        var _year = newDate.getFullYear(); 
			var _month = newDate.getMonth() + 1; 
			if (_month.toString().length == 1){
	           _month = "0" + _month;
	        }
			var _day = newDate.getDate() ;
	        if (_day.toString().length == 1){
	           _day = "0" + _day;
	        }
	        document.getElementById(ngaysinhId).value = _day + "/" + _month + "/" + _year;
	    
	    }
		
	}
  //}catch(e){
  //  alert("error at set_ngaysinh():"+e.description);
  //}	
}


function set_ngaysinh_namsinh(tuoiId,ngaysinhId,namsinhId){

  
	tuoi = document.getElementById(tuoiId).value;
	
	if ( tuoi == ""){
		return;
	}
	if(tuoi == 0 || tuoi > 150){
		 var donViTuoi = getDonViTuoi();
		 //alert(donViTuoi);
		 if (donViTuoi == "1"){ // nam tuoi
		document.getElementById(tuoiId).value = '';
		document.getElementById(tuoiId).focus();
		return;
		}
	}
	else {
	
	    var donViTuoi = getDonViTuoi();
	    
	    if (donViTuoi == "1"){ // nam tuoi
	    	
	    	document.getElementById(namsinhId).value =  ( new Date().getFullYear() - parseInt(tuoi,10));
	    	document.getElementById(ngaysinhId).value = '';
	    } else if (donViTuoi == "2"){ //thang tuoi
	    	
	    	var today = new Date();
	        var newDate = addMonthsToDate(today,-1*parseInt(tuoi,10));
	        var _year = newDate.getFullYear(); 
				        
	        document.getElementById(namsinhId).value = _year;
	        document.getElementById(ngaysinhId).value = ''; // 20110210 bao.ttc: xoa phan ngay sinh neu co de tranh khong tuong ung giua 2 truong
	         
	    } else if (donViTuoi == "3"){ //ngay tuoi
	    	
	    	var today = new Date();
	        var newDate = addDaysToDate(today,-1*parseInt(tuoi,10));
	        var _year = newDate.getFullYear(); 
			var _month = newDate.getMonth() + 1;
			
			if (_month.toString().length == 1){
	           _month = "0" + _month;
	        }
			var _day = newDate.getDate() ;
	        if (_day.toString().length == 1){
	           _day = "0" + _day;
	        }
	        
	        document.getElementById(ngaysinhId).value = _day + "/" + _month + "/" + _year;
	        document.getElementById(namsinhId).value =  _year;
	    
	    }
		
	}
	
}

function getDonViTuoi(){
    
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked == true ){
       return 1;
    }
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked == true ){
       return 2;
    }
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked == true ){
       return 3;
	}
}

function getTuoi(tuoiId){
  try{
	 var donViTuoi = getDonViTuoi();
	 var tuoi = document.getElementById(tuoiId).value;
	 
	    if (donViTuoi == "1"){ // nam tuoi
	    	return 	document.getElementById(tuoiId).value ;
	    }else if (donViTuoi == "2"){ //thang tuoi
	    	//alert(2);
	    	var today = new Date();
	        var newDate = addMonthsToDate(today,-1*parseInt(tuoi,10));
	        
	        return  newDate.getFullYear(); 
			
	         
	    }else if (donViTuoi == "3"){ //ngay tuoi
	    	var today = new Date();
	       
	        var newDate = addDaysToDate(today,-1*parseInt(tuoi,10));
	        
	        return _year = newDate.getFullYear(); 
				
	    
	    }else{
	    
	      return "";
	    }
	 }catch(e){
	   alert("error at function getTuoi()");
	 }   
	
}

/**
 * 
**/
function set_tuoi(tuoiId,ngaysinhId,donvituoiId){
	ngaysinh = document.getElementById(ngaysinhId).value;
	//alert(ngaysinh);
	if(ngaysinh == ""){
		return;
	}	
	else {
	
		if (ngaysinh.length == 5){
			ngaysinh = ngaysinh + "/" + new Date().getFullYear();
		}
		if (ngaysinh.length == 8){
			ngaysinh = ngaysinh.substring(0,6)+ (new Date().getFullYear() + "").substring(0,2) + ngaysinh.substring(6,8);
		}
		
	
		document.getElementById(tuoiId).value = new Date().getFullYear() - parseInt( ngaysinh.substr(6,4),10) ;
		
	}
	
	//
	try{
		var myTuoi = parseInt(document.getElementById(tuoiId).value,10);
		if (myTuoi <= 0){
			//
			//Set 1 day in milliseconds
			var one_day=1000*60*60*24;
			
			var _year = parseInt( ngaysinh.substr(6,4),10); //  11/12/2008
			var _month = parseInt( ngaysinh.substr(3,2),10); 
			var _day = parseInt( ngaysinh.substr(0,2),10); 
			//alert();
			var myBirthday = new Date();
			myBirthday.setFullYear(_year,_month - 1,_day);
			//alert(myBirthday);
			var daybetween = Math.ceil((new Date().getTime() - myBirthday.getTime())/(one_day));
			document.getElementById(tuoiId).value = daybetween;
			
			document.getElementById(donvituoiId + ":2").checked = true;
		}else{
			document.getElementById(donvituoiId + ":0").checked = true;
		}
	
	} catch(e) {
	   alert("error at set_tuoi()");
	}
	
}


/**
 * 
**/
function set_tuoi_from_namsinh(tuoiId,namsinhId,donvituoiId){
	
	var namsinh = document.getElementById(namsinhId).value;
	if (namsinh == null || namsinh == ''){
		return;
	}
	if (namsinh.length == 2){
		namsinh = (new Date().getFullYear() + "").substring(0,2) + namsinh ;
		document.getElementById(namsinhId).value = namsinh;
	}
	
	
	var tuoi_tmp = document.getElementById(tuoiId).value;
	if (tuoi_tmp != null && tuoi_tmp != ''){
		return;
	}
	
	
	try{
		var myTuoi = new Date().getFullYear() - parseInt( namsinh,10 ) ;
		if (myTuoi < 7) {
			if(myTuoi == 0) {
				document.getElementById(tuoiId).value = 12 ;  // 12 thang
			} else {
				document.getElementById(tuoiId).value = (myTuoi * 12) ;  // 12 thang
			}
			document.getElementById(donvituoiId + ":1").checked = true;
		} else {
			document.getElementById(tuoiId).value = myTuoi ;
		
			document.getElementById(donvituoiId + ":0").checked = true;
		}	
	
	} catch(e) {
	   alert("set_tuoi_from_ngaysinh");
	}
	
}
/**
 * 
**/
function set_tuoi_has_namsinh(tuoiId,ngaysinhId,donvituoiId,namsinhId){
	ngaysinh = document.getElementById(ngaysinhId).value;
	//alert(ngaysinh);
	if(ngaysinh == ""){
		return;
	}	
	else {
	
		if (ngaysinh.length == 5){
			ngaysinh = ngaysinh + "/" + new Date().getFullYear();
		}
				
		if (ngaysinh.length == 8){
			ngaysinh = ngaysinh.substring(0,6)+ (new Date().getFullYear() + "").substring(0,2) + ngaysinh.substring(6,8);
		}
		 
		 document.getElementById(namsinhId).value =  ngaysinh.substring(6);
		 
	
		document.getElementById(tuoiId).value = new Date().getFullYear() - parseInt( ngaysinh.substr(6,4),10) ;
		
	}
	
	//
	try{
		var myTuoi = parseInt(document.getElementById(tuoiId).value,10);
		var DAY_OF_MONTH = 30;
		var TUOI_TRE_EM = 6;
		
			//
			//Set 1 day in milliseconds
			var one_day=1000*60*60*24;
			
			var _year = parseInt( ngaysinh.substr(6,4),10); //  11/12/2008
			var _month = parseInt( ngaysinh.substr(3,2),10); 
			var _day = parseInt( ngaysinh.substr(0,2),10); 
			//alert();
			var myBirthday = new Date();
			myBirthday.setFullYear(_year,_month - 1,_day);
			//alert(myBirthday);
			var daybetween = Math.ceil((new Date().getTime() - myBirthday.getTime())/(one_day));
			if(daybetween < 0) {
				alert("Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại.");
				document.getElementById(ngaysinhId).value = "";
				document.getElementById(tuoiId).value = "";
				document.getElementById(ngaysinhId).focus();
				return;
			}
		if (myTuoi < TUOI_TRE_EM){
			
			if (daybetween > DAY_OF_MONTH) {
				// Hien thi so thang
				// Cach tinh nhu sau :
				/*
				- Neu thang sinh =  thang hien tai ==> so thang = so nam x 12 : 
					Vi du : Ngay hien tai la 31/08/2011 ==> thang hien tai  = 8
					        Ngay sinh la 20/08/2009 ==> thang sinh = 8
							so nam = 2011- 2009 = 2
							so thang = 2 x 12  = 24
				- Neu thang sinh < thang hien tai ==> so thang = so nam x 12 + (thang hien tai - thang sinh)
					Vi du : Ngay hien tai la 31/08/2011 ==> thang hien tai  = 8
					        Ngay sinh la 20/06/2009 ==> thang sinh = 6
							so nam = 2011- 2009 = 2
							so thang = 2 x 12 + (8 - 6) = 26

				- Neu thang sinh > thang hien tai ==> so thang = (so nam - 1) x 12 + 12 - (thang sinh - thang hien tai)
					Vi du : Ngay hien tai la 31/08/2011 ==> thang hien tai  = 8
					        Ngay sinh la 20/09/2010 ==> thang sinh = 9
							so nam = 2011- 2010 = 1
							so thang = (1-1) x 12 + 12 - (9 - 8) = 11;
				    Vi du : Ngay hien tai la 31/08/2011 ==> thang hien tai  = 8
					        Ngay sinh la 20/09/2009 ==> thang sinh = 9
							so nam = 2011- 2009 = 2
							so thang = (2-1) x 12 + 12 - (9 - 8) = 23;
				 	Vi du : Ngay hien tai la 31/08/2011 ==> thang hien tai  = 8
					        Ngay sinh la 20/12/2009 ==> thang sinh = 12
							so nam = 2011- 2009 = 2
							so thang = (2-1) x 12 + 12 - (12 - 8) = 20;
				*/					
				var namhientai = new Date().getFullYear();
				var thanghientai = new Date().getMonth() + 1;				
				if(_month == thanghientai) {					
					document.getElementById(tuoiId).value = ((namhientai - _year) * 12);
				} else if (_month < thanghientai) {					
					document.getElementById(tuoiId).value = (((namhientai - _year) * 12) + (thanghientai - _month));
				} else {					
					document.getElementById(tuoiId).value = (((namhientai - _year - 1) * 12) + 12 - (_month - thanghientai));
				}				
				document.getElementById(donvituoiId + ":1").checked = true;
			}else {
				document.getElementById(tuoiId).value = (daybetween+1);			
				document.getElementById(donvituoiId + ":2").checked = true;
			}
		} else{
			document.getElementById(donvituoiId + ":0").checked = true;
		}
	
	} catch(e) {
	   alert("error at set_tuoi()");
	}
	
}

function isNullValue(val) {
	if (val == null || val == "null" || val == "NULL") {
		return true;
	} 
	return false;
}

function getTenDonViTinhFromDmThuoc(value){
	var tenDonViTinh = "";
	//alert(value);
	
	var obj = getObject("DM_THUOC");
	var objDmDonViTinh = getObject("DM_DON_VI_TINH");
	try{
		if (obj != null) {
			var listObj = obj.filter("MaSo = ?", value).toArray();
			//alert(listObj[0].DMDONVITINH_MASO);
			var listObjDmDonViTinh = objDmDonViTinh.filter("MaSo = ?", listObj[0].DMDONVITINH_MASO).toArray();
			tenDonViTinh = listObjDmDonViTinh[0].Ten;
			//alert(listObjDmDonViTinh[0].Ten);
		}
	}catch(e){
		tenDonViTinh = "";
	}
    return tenDonViTinh;
}

function getTenDonViTinhFromDmThuoc(value){
	var tenDonViTinh = "";
	//alert(value);
	
	var obj = getObject("DM_THUOC");
	var objDmDonViTinh = getObject("DM_DON_VI_TINH");
	try{
		if (obj != null) {
			var listObj = obj.filter("MaSo = ?", value).toArray();
			//alert(listObj[0].DMDONVITINH_MASO);
			var listObjDmDonViTinh = objDmDonViTinh.filter("MaSo = ?", listObj[0].DMDONVITINH_MASO).toArray();
			tenDonViTinh = listObjDmDonViTinh[0].Ten;
			//alert(listObjDmDonViTinh[0].Ten);
		}
	}catch(e){
		tenDonViTinh = "";
	}
    return tenDonViTinh;
}

function myOnblurTextboxForDtDmKho(textboxId,comboboxId,hiddenId){
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var arr2 = comboboxId.split("__");
	var myTableName = "";
	if(arr2.length > 1){
		myTableName = arr2[0];
	} else {
	   myTableName = comboboxId;
	}
	
	if(textboxValue != ""){
		var Object = getObject(myTableName);
		mycombobox = dojo.byId(comboboxId);
		var objectarr = Object.filter("Ma like ?", textboxValue ).toArray();
		var end = false;
		for (var i = 0; i < objectarr.length; i++) {
			mycombobox.value = objectarr[i].Ten;
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo;
				document.getElementById(hiddenId).value = objectarr[i].DTDMKHO_KHOCHA;
			}
			catch(e){}
			return;
		}
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
				document.getElementById(hiddenId).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
		document.getElementById(hiddenId).value = "";
	} catch(e){}
	
 	//db.close();     
}


function getDmKhoaByMaSo(masoid){
	var maso = document.getElementById(masoid).value;
	var objArr = DmKhoa.filter("MaSo = ?", maso).toArray();
	return objArr;
}

function changeDisabledAttr(widgetID){ //widget id
 
    //alert(widgetID);
    var widget = dijit.byId(widgetID);
    //alert(widget);
    if (widget == null){
    	var obj = document.getElementById(widgetID);
    	if (obj.disabled == true){
    		document.getElementById(widgetID).disabled = false;
    	}else{
    		document.getElementById(widgetID).disabled = true;
    	}
    }else{
     	widget.setAttribute('disabled',!widget.disabled);  
    }
   
    //alert(222);  
}

function LoadCatalogFromServerForClient(){
	var comp_quantity = document.forms[0].elements.length;
	var subAlt_arr;
	for(i=0; i<comp_quantity; i++){
		if(document.forms[0].elements[i].type == "checkbox" && document.forms[0].elements[i].alt != "" && document.forms[0].elements[i].checked == true) {
			try{
				subAlt_arr = (document.forms[0].elements[i].alt).split(",");
				eval("LoadCatalogFromServer_each(" + subAlt_arr[0] + ",\'" + subAlt_arr[1] + "\')");
			}
			catch(ex){}						        
        } 	
	}
}

function LoadCatalogFromServer_each(obj, actionName) {
	//alert(obj);
	obj.createTable();
	
	
	getCatalogFromServer_new(actionName,obj); 
	//;
}

function LoadCatalogFromServer(){
	var comp_quantity = document.forms[0].elements.length;
	var subAlt_arr;
	for(i=0; i<comp_quantity; i++){
		
		if(document.forms[0].elements[i].type == "checkbox" && document.forms[0].elements[i].alt != "" && document.forms[0].elements[i].checked == true) {
			//alert(document.forms[0].elements[i].alt);
			try{
				subAlt_arr = (document.forms[0].elements[i].alt).split(",");
				eval("LoadCatalogFromServer_each(" + subAlt_arr[0] + ",\'" + subAlt_arr[1] + "\')");
			}
			catch(ex){}						        
        } 	
	}
}

function setNgayThangBatDauKetThuc(thang,nam,tungay,denngay){
	  var thangVaoVien = document.getElementById(prefix_component + thang).value;
	  var namVaoVien = document.getElementById(prefix_component + nam).value;
	  var ngayhientai = document.getElementById(prefix_component + '__ngayhientai').value;
	  var thangHt = ngayhientai.substring(3,5);
	  var namHt = ngayhientai.substring(6);
	   
	  //alert(namVaoVien);
	  //alert(ngayhientai);
	  //alert(thangVaoVien);
	  //alert(thangHt);
	  
	  if ( thangVaoVien == "" && thangVaoVien.length == 0){
	     return;
	  }
	  else if(thangVaoVien == 0 || thangVaoVien > 12 || (thangVaoVien > parseInt(thangHt,10) && namVaoVien >= parseInt(namHt,10))){ 			
		  	document.getElementById(prefix_component + thang).value = '';
		  	document.getElementById(prefix_component + thang).focus();
		  	return;
	  }
	  if ( namVaoVien == "" && namVaoVien.length == 0){
	     return;
	  }
	  
	  else if(namVaoVien < 1900 || namVaoVien > namHt){
		  	document.getElementById(prefix_component + nam).value = '';
		  	document.getElementById(prefix_component + nam).focus();
		  	return;
	  }
	  else if ( parseInt(namVaoVien,10) < 1900 || parseInt(namVaoVien,10) > 2300 ){
		  	 document.getElementById(prefix_component + nam).focus();
		     return;
	  }
	  else{
  
		  var thangVaoVien_Temp = thangVaoVien;
		  var thangVaoVien_Temp_2 = thangVaoVien;
		  
		  if (thangVaoVien != "" && thangVaoVien.length > 0){
		  	if (thangVaoVien.length == 1){
		  	   thangVaoVien_Temp = "0" + thangVaoVien;
		  	   thangVaoVien_Temp_2 = "0" + thangVaoVien;
		  	}
		  }
		  else{
		  	   thangVaoVien_Temp = "01";  
		  	   thangVaoVien_Temp_2="12";
		  	   thangVaoVien="1";
		  	   //document.getElementById(prefix_component + thang).value = "1";
		  }

		  document.getElementById(prefix_component + tungay).value="01/" + thangVaoVien_Temp + "/" + namVaoVien;  
	  	  if(thangVaoVien < 10)
	  	      thangVaoVien = "0"+thangVaoVien;
	  	      
	  	     // alert(thangVaoVien);
	  	  if(thangVaoVien == thangHt && namVaoVien == namHt){
			  	document.getElementById(prefix_component + denngay).value= ngayhientai;
		  }
		  else{
			  	document.getElementById(prefix_component + denngay).value= getLastDayOfMonth (parseInt(thangVaoVien,10),parseInt(namVaoVien,10)) + "/" + thangVaoVien_Temp_2 + "/" + namVaoVien;
		  }

  	}
}

function getLastDayOfMonth(month,year)
{
	var day;

	switch(month)
	{
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10:
		case 12:
			day = 31;
			break;
		case 4 :
		case 6 :
		case 9 :
		case 11:
		   	day = 30;
			break;
		case 2 :
			if( ( (year % 4 == 0) && ( year % 100 != 0) ) 
                           || (year % 400 == 0) )
				day = 29;
			else
				day = 28;
			break;

	}

	return day;
}

function validatethang(){

			var thang = parseInt(document.getElementById(prefix_component + "__thang").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			if(thang>12 || thang==0){
				alert("Tháng phải lớn hơn 0 và nhỏ hơn hoặc bằng 12");		
				document.getElementById(prefix_component + "__thang").focus();
				return false;
			}else{
				var a = validatenam();
				if(a==false)
					document.getElementById(prefix_component + "__thang").focus();
				return a;
			}
}
function validatenam(){
			var thang = parseInt(document.getElementById(prefix_component + "__thang").value,10);
			var nam = parseInt(document.getElementById(prefix_component + "__nam").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			var namhientai = parseInt(ngayhientai.substring(ngayhientai.length-4, ngayhientai.length),10);
			if(nam > namhientai){
				alert("Năm phải nhỏ hơn hoặc bằng " + namhientai);		
				document.getElementById(prefix_component + "__nam").focus();	
				return false;
			}
			
			else if(thang > thanghientai &&  nam >= namhientai){
				alert("Tháng, năm phải nhỏ hơn " + thanghientai + "/" +  namhientai);
				document.getElementById(prefix_component + "__nam").focus();
				return false;
			
			}
			// phuc.lc 05/01/2011 : fix bug 1964
			/*
			else if(thanghientai==1 && nam == namhientai){     
				alert("Tháng, năm phải nhỏ hơn " + thanghientai + "/" +  namhientai);
				document.getElementById(prefix_component + "__nam").focus();
				return false;		
			}
			*/
			//alert(ngayhientai);
			//alert(namhientai);
			return true;
} 

function validatengayhandung(){
			var ngay = parseInt(document.getElementById(prefix_component + "__ngay").value,10);
			if(ngay>31 || ngay<0){
				alert("Ngày không hợp lệ");		
				document.getElementById(prefix_component + "__ngay").value = "";
				document.getElementById(prefix_component + "__ngay").focus();
				return false;   
			}
}
function validatethanghandung(){
			var thang = parseInt(document.getElementById(prefix_component + "__thang").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			if(thang>12 || thang==0){
				alert("Tháng phải lớn hơn 0 và nhỏ hơn hoặc bằng 12");	
				document.getElementById(prefix_component + "__thang").value="";
				document.getElementById(prefix_component + "__thang").focus();
				return false;
			}else{
				var a = validatenamhandung();
				if(a==false)
					document.getElementById(prefix_component + "__thang").value="";
					document.getElementById(prefix_component + "__thang").focus();
				return a;
			}
}
function validatenamhandung(){
			var thang = parseInt(document.getElementById(prefix_component + "__thang").value,10);
			var nam = parseInt(document.getElementById(prefix_component + "__nam").value,10);
			var ngayhientai = document.getElementById(prefix_component + "__ngayhientai").value;
			var thanghientai = parseInt(ngayhientai.substring(ngayhientai.length-7, ngayhientai.length-5),10);
			var namhientai = parseInt(ngayhientai.substring(ngayhientai.length-4, ngayhientai.length),10);
			if(nam < namhientai){
				alert("Năm phải lớn hơn hoặc bằng " + namhientai);	
				document.getElementById(prefix_component + "__nam").value="";
				document.getElementById(prefix_component + "__nam").focus();	
				return false;
			}
			
			else if(thang < thanghientai &&  nam == namhientai){
				alert("Tháng, năm phải lớn hơn " + thanghientai + "/" +  namhientai);
				document.getElementById(prefix_component + "__nam").value="";
				document.getElementById(prefix_component + "__nam").focus();
				return false;
			
			}
		
			return true;
} 

function formatHoTenFunction(hotenvar){
	
	var hoTen = document.getElementById(hotenvar).value;
	hoTen = hoTen.trim();

	var result = "";
	if (hoTen != null && hoTen != ""){
	
		var index = hoTen.indexOf(' ');
		if (index != null && parseInt(index) >= 0){
		
			while (index != null && parseInt(index) >= 0  && hoTen != null && hoTen.length > 0){
				var temp =  hoTen.substring(0,index + 1);
				//alert(temp);
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				//alert(temp1);
				temp1 = temp1.toUpperCase();
				
				var temp2 = temp.substring(1,temp.lengh); 
				//alert(temp2);
				temp2=temp2.toLowerCase();
				
				
				result = result + temp1 + temp2;
				 
				hoTen = hoTen.substring(index + 1, hoTen.length );
				
				
								
				
				index = hoTen.indexOf(' ');
			}	
			if (hoTen != null && hoTen != ""){
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
			}
			
		}else{
				var temp =  hoTen;
				var temp1 = temp.substring(0,1); // lay ky tu dau tien, chuyen thanh chuoi hoa
				temp1 = temp1.toUpperCase();
				var temp2 = temp.substring(1,temp.lengh); 
				temp2=temp2.toLowerCase();
				
				result = result + temp1 + temp2;
			
		}	
		
		
		document.getElementById(hotenvar).value = result;	
	}

}

function formatHoTenUpperCase(hotenvar){
	
	var hoTen = document.getElementById(hotenvar).value;
	hoTen = hoTen.trim();

	var result = "";
	if (hoTen != null && hoTen != ""){
		result = hoTen.toUpperCase();
		document.getElementById(hotenvar).value = result;	
	}

}

function deleteTableValue(myTable){
	try{
		db.open('database-Quan_Ly_Y_Te');
	}catch(e){
	}
	
	
		
	
	
	
	db.execute("delete from " + myTable );
	
	
	
	try{
		db.close();
	}catch(e){
	}		
}
var STR_TABLE = new Array(136);
for (var i = 0 ; i < 136; i++){
	STR_TABLE[i] = new Array(2);
}

STR_TABLE[0][0] = "Á";
STR_TABLE[0][1] = 95;

STR_TABLE[1][0] = "À";
STR_TABLE[1][1] = 96;

STR_TABLE[2][0] = "Ả";
STR_TABLE[2][1] = 97;

STR_TABLE[3][0] = "Ã";
STR_TABLE[3][1] = 98;

STR_TABLE[4][0] = "Ạ";
STR_TABLE[4][1] = 99;

STR_TABLE[5][0] = "Ă";
STR_TABLE[5][1] = 100;

STR_TABLE[6][0] = "Â";
STR_TABLE[6][1] = 101;

STR_TABLE[7][0] = "Ắ";
STR_TABLE[7][1] = 102;

STR_TABLE[8][0] = "Ằ";
STR_TABLE[8][1] = 103;

STR_TABLE[9][0] = "Ẳ";
STR_TABLE[9][1] = 104;

STR_TABLE[10][0] = "Ẵ";
STR_TABLE[10][1] = 105;

STR_TABLE[11][0] = "Ặ";
STR_TABLE[11][1] = 106;

STR_TABLE[12][0] = "Ấ";
STR_TABLE[12][1] = 107;

STR_TABLE[13][0] = "Ầ";
STR_TABLE[13][1] = 108;

STR_TABLE[14][0] = "Ẩ";
STR_TABLE[14][1] = 109;

STR_TABLE[15][0] = "Ẫ";
STR_TABLE[15][1] = 110;

STR_TABLE[16][0] = "Ậ";
STR_TABLE[16][1] = 111;

STR_TABLE[17][0] = "É";
STR_TABLE[17][1] = 112;

STR_TABLE[18][0] = "È";
STR_TABLE[18][1] = 113;

STR_TABLE[19][0] = "Ẻ";
STR_TABLE[19][1] = 114;

STR_TABLE[20][0] = "Ẽ";
STR_TABLE[20][1] = 115;

STR_TABLE[21][0] = "Ẹ";
STR_TABLE[21][1] = 116;

STR_TABLE[22][0] = "Ê";
STR_TABLE[22][1] = 117;

STR_TABLE[23][0] = "Ế";
STR_TABLE[23][1] = 118;

STR_TABLE[24][0] = "Ề";
STR_TABLE[24][1] = 119;

STR_TABLE[25][0] = "Ể";
STR_TABLE[25][1] = 120;

STR_TABLE[26][0] = "Ễ";
STR_TABLE[26][1] = 121;

STR_TABLE[27][0] = "Ệ";
STR_TABLE[27][1] = 122;

STR_TABLE[28][0] = "Ó";
STR_TABLE[28][1] = 123;

STR_TABLE[29][0] = "Ò";
STR_TABLE[29][1] = 124;

STR_TABLE[30][0] = "Ỏ";
STR_TABLE[30][1] = 125;

STR_TABLE[31][0] = "Õ";
STR_TABLE[31][1] = 126;

STR_TABLE[32][0] = "Ọ";
STR_TABLE[32][1] = 127;

STR_TABLE[33][0] = "Ô";
STR_TABLE[33][1] = 128;

STR_TABLE[34][0] = "Ơ";
STR_TABLE[34][1] = 129;

STR_TABLE[35][0] = "Ố";
STR_TABLE[35][1] = 130;

STR_TABLE[36][0] = "Ồ";
STR_TABLE[36][1] = 131;

STR_TABLE[37][0] = "Ổ";
STR_TABLE[37][1] = 132;

STR_TABLE[38][0] = "Ỗ";
STR_TABLE[38][1] = 133;

STR_TABLE[39][0] = "Ộ";
STR_TABLE[39][1] = 134;

STR_TABLE[40][0] = "Ớ";
STR_TABLE[40][1] = 135; //ok

STR_TABLE[41][0] = "Ờ";
STR_TABLE[41][1] = 136;

STR_TABLE[42][0] = "Ở";
STR_TABLE[42][1] = 137;

STR_TABLE[43][0] = "Ỡ";
STR_TABLE[43][1] = 138;

STR_TABLE[44][0] = "Ợ";
STR_TABLE[44][1] = 139;

STR_TABLE[45][0] = "Í";
STR_TABLE[45][1] = 140;

STR_TABLE[46][0] = "Ì";
STR_TABLE[46][1] = 141;

STR_TABLE[47][0] = "Ỉ";
STR_TABLE[47][1] = 142;

STR_TABLE[48][0] = "Ĩ";
STR_TABLE[48][1] = 143;

STR_TABLE[49][0] = "Ị";
STR_TABLE[49][1] = 144;

STR_TABLE[50][0] = "Ý";
STR_TABLE[50][1] = 145;

STR_TABLE[51][0] = "Ỳ";
STR_TABLE[51][1] = 146;

STR_TABLE[52][0] = "Ỷ";
STR_TABLE[52][1] = 147;

STR_TABLE[53][0] = "Ỹ";
STR_TABLE[53][1] = 148;

STR_TABLE[54][0] = "Ỵ";
STR_TABLE[54][1] = 149;

STR_TABLE[55][0] = "Ú";
STR_TABLE[55][1] = 150;

STR_TABLE[56][0] = "Ù";
STR_TABLE[56][1] = 151;

STR_TABLE[57][0] = "Ủ";
STR_TABLE[57][1] = 152;

STR_TABLE[58][0] = "Ũ";
STR_TABLE[58][1] = 153;

STR_TABLE[59][0] = "Ụ";
STR_TABLE[59][1] = 154;

STR_TABLE[60][0] = "Ư";
STR_TABLE[60][1] = 155;

STR_TABLE[61][0] = "Ứ";
STR_TABLE[61][1] = 156;

STR_TABLE[62][0] = "Ừ";
STR_TABLE[62][1] = 157;

STR_TABLE[63][0] = "Ử";
STR_TABLE[63][1] = 158;

STR_TABLE[64][0] = "Ữ";
STR_TABLE[64][1] = 159;

STR_TABLE[65][0] = "Ự";
STR_TABLE[65][1] = 160;

STR_TABLE[66][0] = "á";
STR_TABLE[66][1] = 161;

STR_TABLE[67][0] = "à";
STR_TABLE[67][1] = 162;

STR_TABLE[68][0] = "ả";
STR_TABLE[68][1] = 163;

STR_TABLE[69][0] = "ã";
STR_TABLE[69][1] = 164;

STR_TABLE[70][0] = "ạ";
STR_TABLE[70][1] = 165;

STR_TABLE[71][0] = "ă";
STR_TABLE[71][1] = 166;

STR_TABLE[72][0] = "â";
STR_TABLE[72][1] = 167;

STR_TABLE[73][0] = "ắ";
STR_TABLE[73][1] = 168;

STR_TABLE[74][0] = "ằ";
STR_TABLE[74][1] = 169;

STR_TABLE[75][0] = "ẳ";
STR_TABLE[75][1] = 170;

STR_TABLE[76][0] = "ẵ";
STR_TABLE[76][1] = 171;

STR_TABLE[77][0] = "ặ";
STR_TABLE[77][1] = 172;

STR_TABLE[78][0] = "ấ";
STR_TABLE[78][1] = 173;

STR_TABLE[79][0] = "ầ";
STR_TABLE[79][1] = 174;

STR_TABLE[80][0] = "ẩ";
STR_TABLE[80][1] = 175;

STR_TABLE[81][0] = "ẫ";
STR_TABLE[81][1] = 176;

STR_TABLE[82][0] = "ậ";
STR_TABLE[82][1] = 177;

STR_TABLE[83][0] = "é";
STR_TABLE[83][1] = 178;

STR_TABLE[84][0] = "è";
STR_TABLE[84][1] = 179;

STR_TABLE[85][0] = "ẻ";
STR_TABLE[85][1] = 180;

STR_TABLE[86][0] = "ẽ";
STR_TABLE[86][1] = 181;

STR_TABLE[87][0] = "ẹ";
STR_TABLE[87][1] = 182;

STR_TABLE[88][0] = "ê";
STR_TABLE[88][1] = 183;

STR_TABLE[89][0] = "ế";
STR_TABLE[89][1] = 184;

STR_TABLE[90][0] = "ề";
STR_TABLE[90][1] = 185;

STR_TABLE[91][0] = "ể";
STR_TABLE[91][1] = 186;

STR_TABLE[92][0] = "ễ";
STR_TABLE[92][1] = 187;

STR_TABLE[93][0] = "ệ";
STR_TABLE[93][1] = 188;

STR_TABLE[94][0] = "ó";
STR_TABLE[94][1] = 189;

STR_TABLE[95][0] = "ò";
STR_TABLE[95][1] = 190;

STR_TABLE[96][0] = "ỏ";
STR_TABLE[96][1] = 191;

STR_TABLE[97][0] = "õ";
STR_TABLE[97][1] = 192;

STR_TABLE[98][0] = "ọ";
STR_TABLE[98][1] = 193;

STR_TABLE[99][0] = "ô";
STR_TABLE[99][1] = 194;

STR_TABLE[100][0] = "ơ";
STR_TABLE[100][1] = 195; //ok

STR_TABLE[101][0] = "ố";
STR_TABLE[101][1] = 196;

STR_TABLE[102][0] = "ồ";
STR_TABLE[102][1] = 197;

STR_TABLE[103][0] = "ổ";
STR_TABLE[103][1] = 198;

STR_TABLE[104][0] = "ỗ";
STR_TABLE[104][1] = 199;

STR_TABLE[105][0] = "ộ";
STR_TABLE[105][1] = 200; //ok

STR_TABLE[106][0] = "ớ";
STR_TABLE[106][1] = 201; //ok

STR_TABLE[107][0] = "ờ";
STR_TABLE[107][1] = 202; //ok

STR_TABLE[108][0] = "ở";
STR_TABLE[108][1] = 203; //ok

STR_TABLE[109][0] = "ỡ";
STR_TABLE[109][1] = 204;

STR_TABLE[110][0] = "ợ";
STR_TABLE[110][1] = 205;

STR_TABLE[111][0] = "í";
STR_TABLE[111][1] = 206;

STR_TABLE[112][0] = "ì";
STR_TABLE[112][1] = 207;

STR_TABLE[113][0] = "ỉ";
STR_TABLE[113][1] = 208;

STR_TABLE[114][0] = "ĩ";
STR_TABLE[114][1] = 209;

STR_TABLE[115][0] = "ị";
STR_TABLE[115][1] = 210;

STR_TABLE[116][0] = "ý";
STR_TABLE[116][1] = 211;

STR_TABLE[117][0] = "ỳ";
STR_TABLE[117][1] = 212;

STR_TABLE[118][0] = "ỷ";
STR_TABLE[118][1] = 213;

STR_TABLE[119][0] = "ỹ";
STR_TABLE[119][1] = 214;

STR_TABLE[120][0] = "ỵ";
STR_TABLE[120][1] = 215;

STR_TABLE[121][0] = "ú";
STR_TABLE[121][1] = 216;

STR_TABLE[122][0] = "ù";
STR_TABLE[122][1] = 217;

STR_TABLE[123][0] = "ủ";
STR_TABLE[123][1] = 218;

STR_TABLE[124][0] = "ũ";
STR_TABLE[124][1] = 219;

STR_TABLE[125][0] = "ụ";
STR_TABLE[125][1] = 220;

STR_TABLE[126][0] = "ư";
STR_TABLE[126][1] = 221;

STR_TABLE[127][0] = "ứ";
STR_TABLE[127][1] = 222;

STR_TABLE[128][0] = "ừ";
STR_TABLE[128][1] = 223;

STR_TABLE[129][0] = "ử";
STR_TABLE[129][1] = 224;

STR_TABLE[130][0] = "ữ";
STR_TABLE[130][1] = 225;

STR_TABLE[131][0] = "ự";
STR_TABLE[131][1] = 226;

STR_TABLE[132][0] = "Đ";
STR_TABLE[132][1] = 227;

STR_TABLE[133][0] = "đ";
STR_TABLE[133][1] = 228;

STR_TABLE[134][0] = "-";
STR_TABLE[134][1] = 13;

STR_TABLE[135][0] = "";
STR_TABLE[135][1] = 0;


function getListOfValue(name){
	if (name == null || name.length == 0){
		return "";
	}
	//alert(name);
	var resultStr ="";
	for (var i = 0 ; i < name.length; i++){
		var mySubStr = name.substr(i,1);
		
		var strTempNumber = "";
		if (mySubStr=='0'){
			strTempNumber = "16";			
		}else if (mySubStr=='1'){
			strTempNumber = "17";
		}else if (mySubStr=='2'){
			strTempNumber = "18";
		}else if (mySubStr=='3'){
			strTempNumber = "19";
		}else if (mySubStr=='4'){
			strTempNumber = "20";
		}else if (mySubStr=='5'){
			strTempNumber = "21";
		}else if (mySubStr=='6'){
			strTempNumber = "22";
		}else if (mySubStr=='7'){
			strTempNumber = "23";
		}else if (mySubStr=='8'){
			strTempNumber = "24";
		}else if (mySubStr=='9'){
			strTempNumber = "25";
		}else if (mySubStr==' '){
			strTempNumber = "0";
		}
		if (strTempNumber != null && strTempNumber != ""){
			if (resultStr == ""){
				resultStr += strTempNumber;
			}else{
				resultStr += ","+strTempNumber;
			}
			continue;
		}
		
		var check = false;
		
		//alert(mySubStr);
		if (mySubStr != null && mySubStr != ""){
			//alert(mySubStr);
			for (var j = 0 ; j < 136;j++){
				var myKey = STR_TABLE[j][0];
				//alert("myKey:"+myKey);
				if (myKey == mySubStr){
					var myValue = STR_TABLE[j][1];
					if (resultStr == ""){
						resultStr += myValue;
					}else{
						resultStr += ","+myValue;
					}
					check = true;
					break;
				}
			}
			
		}		
		
		if (check == false){
			if (resultStr == ""){
				resultStr += mySubStr;
			}else{
				resultStr += ","+mySubStr;
			}
		}
	}
	//alert(resultStr);
	return resultStr;
		
}
//update lai hsttk
function CapNhatHsttk() {
    var xmlHttp = createXmlHttpRequest();
    
    var url = myContextPath + "/actionServlet";
    
    var params = "urlAction=CapNhatHSTTKAction&xmlData=" + "";
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function() {
    	onStateChangeForCNHSBA(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlHttp.send(params); 
}
//update lai hstt
function CapNhatHstt() {
    var xmlHttp = createXmlHttpRequest();
    
    var url = myContextPath + "/actionServlet";
    
    var params = "urlAction=CapNhatHSTTAction&xmlData=" + "";
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function() {
    	onStateChangeForCNHSBA(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
    xmlHttp.send(params); 
}

function onStateChangeForCNHSBA(xmlHttp) {
     if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
			alert("Cập nhật xong.");
        }
        else {
            alert("Thất bại.");
       }
    }
}

function goiBN(sttId, tenId) {   
	var stt = document.getElementById(prefix_component + sttId).value;
	var ten = document.getElementById(prefix_component + tenId).value;
	if(stt != "" && ten != ""){		
		stt = stt.substr(stt.length - 7);		
		document.xyz.pingpong(getListOfValue(stt)+",0,0,0");
		document.xyz.outputToLed(getListOfValue(ten));
	}else{
		alert("Chưa có thông tin bệnh nhân");
	}	
}

function enableButton(id) {
	btnGhinhan = document.getElementById(id);
    btnGhinhan.disabled = false;
}

function disableButton(id) {
	btnGhinhan = document.getElementById(id);
    btnGhinhan.disabled = true;
}

init();