 package com.iesvn.yte.dieutri.ajax;

 import java.io.Serializable;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.core.Manager;
 import org.jboss.seam.security.Identity;

 @Name("YTDT_Menu")
 @Scope(ScopeType.SESSION)
 public class MyMenuYTDTAction
   implements Serializable
 {
   public static String tiepNhanKhamBenh = "TiepNhanKhamBenh";
   public static String dieuTri = "DieuTri";
   public static String quanTriHeThong = "QuanTriHeThong";
   public static String vienPhiTaiKhoa = "VienPhiTaiKhoa";
   public static String thuVienPhi = "ThuVienPhi";
   public static String baoCaoVienPhi = "BaoCaoVienPhi";
   public static String quanLyKhoChinh = "QuanLyKhoChinh";
   public static String quanLyKhoLe = "QuanLyKhoLe";
   public static String quanLyKhoNoiTru = "QuanLyKhoNoiTru";
   public static String quanLyKhoBHYT = "QuanLyKhoBHYT";
   public static String baoCaoDuoc = "BaoCaoDuoc";
   public static String quanLyKhoTE = "QuanLyKhoTE";
   public static String kiemTraMenuCapCuu = "YES";
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   private String ten4112_4114;
   @In(required=false)
   @Out(required=false)
   private String ten3241_3242;
   @In(required=false)
   @Out(required=false)
   Identity identity;

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String MyMainForm()
   {
     return "MyMainForm";
   }

   public String MyLogout()
   {
     return "MyLogout";
   }

   public String TiepDon_TiepDonBenhNhan_DangKyKhamBenh()
   {
     this.tenChuongTrinh = tiepNhanKhamBenh;
     return "TiepDon_TiepDonBenhNhan_DangKyKhamBenh";
   }

   public String TiepDon_TiepDonBenhNhan_CapSoDangKyKhamBenh()
   {
     return "TiepDon_TiepDonBenhNhan_CapSoDangKyKhamBenh";
   }

   public String TiepDon_TiepDonBenhNhan_TimKiemBenhNhan()
   {
     return "TiepDon_TiepDonBenhNhan_TimKiemBenhNhan";
   }

   public String TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien()
   {
     return "TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien";
   }

   public String TiepDon_TiepDonBenhNhan_InDSBNDKOnline()
   {
     return "TiepDon_TiepDonBenhNhan_InDSBNDKOnline";
   }

   public String TiepDon_KhamBenh_ThamKhamXuTri()
   {
     return "TiepDon_KhamBenh_ThamKhamXuTri";
   }

   public String TiepDon_KhamBenh_XemChiPhiDieuTri()
   {
     return "TiepDon_KhamBenh_XemChiPhiDieuTri";
   }

   public String TiepDon_KhamBenh_InPhieuLinhThuoc()
   {
     return "TiepDon_KhamBenh_InPhieuLinhThuoc";
   }

   public String TiepDon_KhamBenh_TongHopThuocVTTHDaDung()
   {
     return "TiepDon_KhamBenh_TongHopThuocVTTHDaDung";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_DangKyKhamBenhCapCuu()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_DangKyKhamBenhCapCuu";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_ThamKhamXuTri()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_ThamKhamXuTri";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_XemChiPhiDieuTri()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_XemChiPhiDieuTri";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_InDanhSachBenhNhanCapCuu()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_InDanhSachBenhNhanCapCuu";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_InPhieuLinhThuoc()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_InPhieuLinhThuoc";
   }

   public String QuanTriHeThong()
   {
     return "QuanTriHeThong";
   }

   public String QuanTriHeThong_NguoiDung()
   {
     return "QuanTriHeThong_NguoiDung";
   }

   public String QuanTriHeThong_Quyen_NguoiDung()
   {
     return "QuanTriHeThong_Quyen_NguoiDung";
   }

   public String QuanTriHeThong_PhanCongCumThuPhi()
   {
     return "QuanTriHeThong_PhanCongCumThuPhi";
   }

   public String QuanTriHeThong_QLPhanCongCumThuPhi()
   {
     return "QuanTriHeThong_QLPhanCongCumThuPhi";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_TongHopThuocVTTHDaDung()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_TongHopThuocVTTHDaDung";
   }

   public String TiepDon_TiepDonKhamBenhCapCuu_ChuyenSoLieuVaoNoiTru()
   {
     return "TiepDon_TiepDonKhamBenhCapCuu_ChuyenSoLieuVaoNoiTru";
   }

   public String TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh()
   {
     return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh";
   }

   public String TiepDon_PhanTichBaoCao_PhanTichBenhNhanVaoCapCuu()
   {
     return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanVaoCapCuu";
   }

   public String TiepDon_PhanTichBaoCao_BaoCaoThongKeTaiNanThuongTich()
   {
     return "TiepDon_PhanTichBaoCao_BaoCaoThongKeTaiNanThuongTich";
   }

   public String TiepDon_PhanTichBaoCao_PhanTichSoLieuThuChiNgoaiTru()
   {
     return "TiepDon_PhanTichBaoCao_PhanTichSoLieuThuChiNgoaiTru";
   }

   public String TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan()
   {
     return "TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan";
   }

   public String TiepDon_PhanTichBaoCao_PhanTichChiPhiDieuTriTaiCCL()
   {
     return "TiepDon_PhanTichBaoCao_PhanTichChiPhiDieuTriTaiCCL";
   }

   public String TiepDon_PhanTichBaoCao_InSoLuuTru()
   {
     return "TiepDon_PhanTichBaoCao_InSoLuuTru";
   }

   public String DieuTri_CapNhat_CapNhatThongTinChung()
   {
     this.tenChuongTrinh = dieuTri;

     return "DieuTri_CapNhat_CapNhatThongTinChung";
   }

   public String DieuTri_CapNhat_CapNhatThongTinMo()
   {
     return "DieuTri_CapNhat_CapNhatThongTinMo";
   }

   public String DieuTri_CapNhat_CapNhatThongTinNhapVien()
   {
     return "DieuTri_CapNhat_CapNhatThongTinNhapVien";
   }

   public String DieuTri_CapNhat_CapNhatThongTinChuyenKhoa()
   {
     return "DieuTri_CapNhat_CapNhatThongTinChuyenKhoa";
   }

   public String DieuTri_CapNhat_XoaThongTinBenhAn()
   {
     return "DieuTri_CapNhat_XoaThongTinBenhAn";
   }

   public String DieuTri_CapNhat_LuuTruBenhAn()
   {
     return "DieuTri_CapNhat_LuuTruBenhAn";
   }

   public String DieuTri_CapNhat_CapNhatThongTinHanhChinhBenhNhan()
   {
     return "DieuTri_CapNhat_CapNhatThongTinHanhChinhBenhNhan";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat";
   }

   public String DieuTri_Index_Page()
   {
     return "DieuTri_Index_Page";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnKetThucCapNhat()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnKetThucCapNhat";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_BaoCaoHoatDongDieuTri()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoHoatDongDieuTri";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatTuVong()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatTuVong";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_DanhSachPhauThuatThuThuat()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_DanhSachPhauThuatThuThuat";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_TongSoPhauThuatThuThuat()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_TongSoPhauThuatThuThuat";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_PhanLoaiPhauThuatThuThuat()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_PhanLoaiPhauThuatThuThuat";
   }

   public String DieuTri_BaoCaoHoatDongDieuTri_TimDanhSachBenhNhanTheoNgayGioNhapVien()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_TimDanhSachBenhNhanTheoNgayGioNhapVien";
   }

   public String DieuTri_LapVanBanTheoMau_GiayChungThuong()
   {
     return "DieuTri_LapVanBanTheoMau_GiayChungThuong";
   }

   public String DieuTri_LapVanBanTheoMau_GiayChungNhanPhauThuat()
   {
     return "DieuTri_LapVanBanTheoMau_GiayChungNhanPhauThuat";
   }

   public String DieuTri_LapVanBanTheoMau_TomTatBenhAn()
   {
     return "DieuTri_LapVanBanTheoMau_TomTatBenhAn";
   }

   public String DieuTri_LapVanBanTheoMau_GiayRaVien()
   {
     return "DieuTri_LapVanBanTheoMau_GiayRaVien";
   }

   public String DieuTri_LapVanBanTheoMau_GiayChuyenVien()
   {
     return "DieuTri_LapVanBanTheoMau_GiayChuyenVien";
   }

   public String DieuTri_LapVanBanTheoMau_SoRaVaoVien()
   {
     return "DieuTri_LapVanBanTheoMau_SoRaVaoVien";
   }

   public String DieuTri_LapVanBanTheoMau_SoChuyenVien()
   {
     return "DieuTri_LapVanBanTheoMau_SoChuyenVien";
   }

   public String DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn()
   {
     return "DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn";
   }

   public String DieuTri_LapVanBanTheoMau_BienBanHoiChan()
   {
     Manager.instance().endConversation(true);
     return "DieuTri_LapVanBanTheoMau_BienBanHoiChan";
   }

   public String DieuTri_PhanTichSoLieu_PhauThuatThuThuat()
   {
     return "DieuTri_PhanTichSoLieu_PhauThuatThuThuat";
   }

   public String DieuTri_PhanTichSoLieu_BenhNhanRaVien()
   {
     return "DieuTri_PhanTichSoLieu_BenhNhanRaVien";
   }

   public String DieuTri_PhanTichSoLieu_BenhNhanTuVong()
   {
     return "DieuTri_PhanTichSoLieu_BenhNhanTuVong";
   }

   public String DieuTri_PhanTichSoLieu_TinhHinhBenh()
   {
     return "DieuTri_PhanTichSoLieu_TinhHinhBenh";
   }

   public String DieuTri_PhanTichSoLieu_TinhHinhBenhTruyenNhiem()
   {
     return "DieuTri_PhanTichSoLieu_TinhHinhBenhTruyenNhiem";
   }

   public String DieuTri_PhanTichSoLieu_LuotBenhNhanNgayDieuTri()
   {
     return "DieuTri_PhanTichSoLieu_LuotBenhNhanNgayDieuTri";
   }

   public String DieuTri_PhanTichSoLieu_TaiNanGiaoThong()
   {
     return "DieuTri_PhanTichSoLieu_TaiNanGiaoThong";
   }

   public String DieuTri_PhanTichSoLieu_TaiBienSanKhoa()
   {
     return "DieuTri_PhanTichSoLieu_TaiBienSanKhoa";
   }

   public String DieuTri_PhanTichSoLieu_BenhSoSinh()
   {
     return "DieuTri_PhanTichSoLieu_BenhSoSinh";
   }

   public String DieuTri_DichVuTienIch_TimBenhAnSoLuuTru()
   {
     return "DieuTri_DichVuTienIch_TimBenhAnSoLuuTru";
   }

   public String DieuTri_DichVuTienIch_TimBenhAnKhongVaoKhoa()
   {
     return "DieuTri_DichVuTienIch_TimBenhAnKhongVaoKhoa";
   }

   public String DieuTri_DichVuTienIch_TimBenhNhanTheoNhieuTieuChi()
   {
     return "DieuTri_DichVuTienIch_TimBenhNhanTheoNhieuTieuChi";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachVoCam()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachVoCam";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachKhoaPhong()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachKhoaPhong";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachNhanVien()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachNhanVien";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DinhNghiaChuTat()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DinhNghiaChuTat";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachPhauThuatThuThuat()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachPhauThuatThuThuat";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachPhanLoaiTaiNan()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachPhanLoaiTaiNan";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachNguyenNhanTaiNan()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachNguyenNhanTaiNan";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachMaBenh()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachMaBenh";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachNgheNghiep()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachNgheNghiep";
   }

   public String DieuTri_QuanTriDanhSachBangMa_DanhSachBenhVien()
   {
     return "DieuTri_QuanTriDanhSachBangMa_DanhSachBenhVien";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_ThuocYDungCuSuDung()
   {
     return "ThuVienPhi_SoLieuDieuTriTamUng_ThuocYDungCuSuDung";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng()
   {
     Manager.instance().endConversation(true);
     return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_CapNhatMienGiam()
   {
     Manager.instance().endConversation(true);
     return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatMienGiam";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_SoLieuChiPhiBNDeDuyetMienGiam()
   {
     return "ThuVienPhi_SoLieuDieuTriTamUng_SoLieuChiPhiBNDeDuyetMienGiam";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_BenhNhanChuyenKhoa()
   {
     return "ThuVienPhi_SoLieuDieuTriTamUng_BenhNhanChuyenKhoa";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_ChiTraBotTienTamUng()
   {
     Manager.instance().endConversation(true);
     return "ThuVienPhi_SoLieuDieuTriTamUng_ChiTraBotTienTamUng";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_CapNhatSoTheBHYT()
   {
     return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatSoTheBHYT";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_XacNhanTreEm()
   {
     Manager.instance().endConversation(true);
     return "ThuVienPhi_SoLieuDieuTriTamUng_XacNhanTreEm";
   }

   public String ThuVienPhi_SoLieuDieuTriTamUng_DanhSachBNTinhHinhChiPhi()
   {
     return "ThuVienPhi_SoLieuDieuTriTamUng_DanhSachBNTinhHinhChiPhi";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_CanLamSan()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_CanLamSan";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_CapMau()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_CapMau";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_PhauThuatThuThuat()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_PhauThuatThuThuat";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_PhauThuatTheoYeuCau()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_PhauThuatTheoYeuCau";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_DanhSachBNCapNhatTrongNgay()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_DanhSachBNCapNhatTrongNgay";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_TongHopThuocYDungCuTheoPhong()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_TongHopThuocYDungCuTheoPhong";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_TongHopThuocYDungCuTheoNgaySuDung()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_TongHopThuocYDungCuTheoNgaySuDung";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_SoLieuSuDungNhieuNgayCuaBenhNhan()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_SoLieuSuDungNhieuNgayCuaBenhNhan";
   }

   public String ThuVienPhi_SoLieuCLSPhauThuat_PhieuLinhThuocHangNgay()
   {
     return "ThuVienPhi_SoLieuCLSPhauThuat_PhieuLinhThuocHangNgay";
   }

   public String ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham()
   {
     return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
   }

   public String ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham()
   {
     this.tenChuongTrinh = thuVienPhi;
     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   public String ThuVienPhi_SoLieuKhamBenh_ThuThuatPhongKham()
   {
     return "ThuVienPhi_SoLieuKhamBenh_ThuThuatPhongKham";
   }

   public String ThuVienPhi_SoLieuKhamBenh_TamUngPhongCapCuu()
   {
     Manager.instance().endConversation(true);
     return "ThuVienPhi_SoLieuKhamBenh_TamUngPhongCapCuu";
   }

   public String ThuVienPhi_SoLieuKhamBenh_ThanhToanPhongCapCuu()
   {
     return "ThuVienPhi_SoLieuKhamBenh_ThanhToanPhongCapCuu";
   }

   public String ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam()
   {
     return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
   }

   public String ThuVienPhi_SoLieuKhamBenh_HoanThuTienThuThuat()
   {
     return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienThuThuat";
   }

   public String ThuVienPhi_SoLieuKhamBenh_HoanThuTienTaiPhongCapCuu()
   {
     return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienTaiPhongCapCuu";
   }

   public String ThuVienPhi_SoLieuKhamBenh_BaoCaoThuTaiPhongCapCuu()
   {
     return "ThuVienPhi_SoLieuKhamBenh_BaoCaoThuTaiPhongCapCuu";
   }

   public String ThuVienPhi_SoLieuKhamBenh_XemInSoLieuHoanThu()
   {
     return "ThuVienPhi_SoLieuKhamBenh_XemInSoLieuHoanThu";
   }

   public String ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien()
   {
     this.ten3241_3242 = "3241";
     return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
   }

   public String ThuVienPhi_ThanhToanRaVien_ThanhToanBenhAnBNBoVien()
   {
     this.ten3241_3242 = "3242";
     return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
   }

   public String ThuVienPhi_ThanhToanRaVien_DieuChinhThongTinHanhChinhBenhNhan()
   {
     return "ThuVienPhi_ThanhToanRaVien_DieuChinhThongTinHanhChinhBenhNhan";
   }

   public String ThuVienPhi_ThanhToanRaVien_SoLieuThuChiTheoCum()
   {
     return "ThuVienPhi_ThanhToanRaVien_SoLieuThuChiTheoCum";
   }

   public String ThuVienPhi_ThanhToanRaVien_SoLieuThuChiTongHop()
   {
     return "ThuVienPhi_ThanhToanRaVien_SoLieuThuChiTongHop";
   }

   public String ThuVienPhi_ThanhToanRaVien_KiemTraNhanhHoSoNhapVien()
   {
     return "ThuVienPhi_ThanhToanRaVien_KiemTraNhanhHoSoNhapVien";
   }

   public String ThuVienPhi_ThanhToanRaVien_KiemTraNhanhHoSoPhongKham()
   {
     return "ThuVienPhi_ThanhToanRaVien_KiemTraNhanhHoSoPhongKham";
   }

   public String ThuVienPhi_QuanTriDanhSachBangMa_DanhSachCanLamSan()
   {
     return "ThuVienPhi_QuanTriDanhSachBangMa_DanhSachCanLamSan";
   }

   public String ThuVienPhi_QuanTriDanhSachBangMa_DanhSachPhauThuatThuThuat()
   {
     return "ThuVienPhi_QuanTriDanhSachBangMa_DanhSachPhauThuatThuThuat";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung()
   {
     this.tenChuongTrinh = vienPhiTaiKhoa;
     return "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanTraThuoc()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanTraThuoc";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_SoLieuChiPhiBNDeDuyetMienGiam()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_SoLieuChiPhiBNDeDuyetMienGiam";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanChuyenKhoa()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanChuyenKhoa";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_DonXinMienGiamVienPhi()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_DonXinMienGiamVienPhi";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_KiemTraNhanhHoSoNhapVien()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_KiemTraNhanhHoSoNhapVien";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_KiemTraNhanhHoSoPhongKham()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_KiemTraNhanhHoSoPhongKham";
   }

   public String VienPhiTaiKhoa_SoLieuBNSuDung_TimBenhNhanCoHoSoThuPhi()
   {
     return "VienPhiTaiKhoa_SoLieuBNSuDung_TimBenhNhanCoHoSoThuPhi";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_CapMau()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CapMau";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_PhauThuatThuThuat()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_PhauThuatThuThuat";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_PhauThuatTheoYeuCau()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_PhauThuatTheoYeuCau";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTru()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTru";
   }

   public String VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTruTra()
   {
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTruTra";
   }

   public String VienPhiTaiKhoa_XemInPhieu_DanhSachBNTinhHinhChiPhi()
   {
     return "VienPhiTaiKhoa_XemInPhieu_DanhSachBNTinhHinhChiPhi";
   }

   public String VienPhiTaiKhoa_XemInPhieu_DanhSachBNCapNhatTrongNgay()
   {
     return "VienPhiTaiKhoa_XemInPhieu_DanhSachBNCapNhatTrongNgay";
   }

   public String VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN()
   {
     return "VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN";
   }

   public String VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai()
   {
     return "VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai";
   }

   public String VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong()
   {
     return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong";
   }

   public String VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoNgaySuDung()
   {
     return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoNgaySuDung";
   }

   public String VienPhiTaiKhoa_XemInPhieu_SoLieuSuDungNhieuNgayCuaBenhNhan()
   {
     return "VienPhiTaiKhoa_XemInPhieu_SoLieuSuDungNhieuNgayCuaBenhNhan";
   }

   public String VienPhiTaiKhoa_XemInPhieu_PhieuLinhThuocHangNgay()
   {
     return "VienPhiTaiKhoa_XemInPhieu_PhieuLinhThuocHangNgay";
   }

   public String VienPhiTaiKhoa_XemInPhieu_InSoThuoc()
   {
     return "VienPhiTaiKhoa_XemInPhieu_InSoThuoc";
   }

   public String BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiTamUngTheoNgay()
   {
     this.tenChuongTrinh = baoCaoDuoc;
     return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiTamUngTheoNgay";
   }

   public String BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiThanhToanTheoNgay()
   {
     return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiThanhToanTheoNgay";
   }

   public String BaoCaoVienPhi_SoLieuThanhToan_TongHopThuChiThanhToanRaVien()
   {
     return "BaoCaoVienPhi_SoLieuThanhToan_TongHopThuChiThanhToanRaVien";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_SoLieuBNDuocXetMienGiam()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuBNDuocXetMienGiam";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_SoLieuThuChiToanVien()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuChiToanVien";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_SoLieuThuPhiCacKhoa()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuPhiCacKhoa";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_SoLieuThuTaiCacPhongKham()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuTaiCacPhongKham";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongMoYeuCau()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongMoYeuCau";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_PhanTichSuDungYeuCauCuaBenhNhanBHYT()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichSuDungYeuCauCuaBenhNhanBHYT";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongToanVien()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongToanVien";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNMienPhiNgoaiTru()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNMienPhiNgoaiTru";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNTEKhongHuongCheDoMienPhi()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNTEKhongHuongCheDoMienPhi";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_TomTatSoLieuBHYTSeChiTra()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_TomTatSoLieuBHYTSeChiTra";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTCLSNgoaiTru()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTCLSNgoaiTru";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongCapCuuLuu()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongCapCuuLuu";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc";
   }

   public String BaoCaoVienPhi_BaoCaoBHYT_TimKiemBNBHYT()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_TimKiemBNBHYT";
   }

   public String BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNMienPhiNoiTru()
   {
     return "BaoCaoVienPhi_HoSoBaoCao_BaoCaoBNMienPhiNoiTru";
   }

   public String QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap()
   {
     this.tenChuongTrinh = quanLyKhoChinh;
     return "QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap";
   }

   public String QuanLyKhoChinh_NhapKhoChinh_KhoLeKhoBHYTTraLaiHang()
   {
     this.ten4112_4114 = "4112";
     return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTuTuTruc";
   }

   public String QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTheoPhieuDuTru()
   {
     return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTheoPhieuDuTru";
   }

   public String QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTuTuTruc()
   {
     this.ten4112_4114 = "4114";
     return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTuTuTruc";
   }

   public String QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT()
   {
     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
   }

   public String QuanLyKhoChinh_XuatKhoChinh_XuatHangTheoPhieuDuTru()
   {
     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangTheoPhieuDuTru";
   }

   public String QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy()
   {
     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoXuatTrongNgay()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoXuatTrongNgay";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangKhongSuDung()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangKhongSuDung";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangGanHetHan()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangGanHetHan";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangTonKhoKhongHanDung()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangTonKhoKhongHanDung";
   }

   public String QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoLuongTonKho()
   {
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoLuongTonKho";
   }

   public String QuanLyKhoChinh_KiemKeKhoChinh_CapNhatTonDau()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatTonDau";
   }

   public String QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau";
   }

   public String QuanLyKhoChinh_KiemKeKhoChinh_TaoBangKiemKeDinhKy()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_TaoBangKiemKeDinhKy";
   }

   public String QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe";
   }

   public String QuanLyKhoChinh_KiemKeKhoChinh_InBangKiemKe()
   {
     return "QuanLyKhoChinh_KiemKeKhoChinh_InBangKiemKe";
   }

   public String QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachMatHang()
   {
     return "QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachMatHang";
   }

   public String QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachNoiBan()
   {
     return "QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachNoiBan";
   }

   public String QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachQuyCachDongGoi()
   {
     return "QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachQuyCachDongGoi";
   }

   public String QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachHangSanXuat()
   {
     return "QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachHangSanXuat";
   }

   public String QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachBoPhanLanhHang()
   {
     return "QuanLyKhoChinh_QuanTriDanhSachBangMa_CapNhatDanhSachBoPhanLanhHang";
   }

   public String QuanLyKhoLe_XuatKhoLe_XuatHangTheoPhieuDuTru()
   {
     this.tenChuongTrinh = quanLyKhoLe;
     return "QuanLyKhoLe_XuatKhoLe_XuatHangTheoPhieuDuTru";
   }

   public String QuanLyKhoLe_XuatKhoLe_XuatHangDenTuTrucThanhLy()
   {
     return "QuanLyKhoLe_XuatKhoLe_XuatHangDenTuTrucThanhLy";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_InBaoCaoNhapXuatTrongNgay()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_InBaoCaoNhapXuatTrongNgay";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_InTheKho()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_InTheKho";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_TinhTonKhoVaGiaTri()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_TinhTonKhoVaGiaTri";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_InChiTietNhapXuatMotMatHang()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_InChiTietNhapXuatMotMatHang";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangKhongSuDung()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangKhongSuDung";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangGanHetHan()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangGanHetHan";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangTonKho()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoMatHangTonKho";
   }

   public String QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoLuongTonKho()
   {
     return "QuanLyKhoLe_TruyXuatThongTinKhoLe_CanhBaoLuongTonKho";
   }

   public String QuanLyKhoLe_KiemKeKhoLe_CapNhatTonDau()
   {
     return "QuanLyKhoLe_KiemKeKhoLe_CapNhatTonDau";
   }

   public String QuanLyKhoLe_KiemKeKhoLe_InDanhSachTonDau()
   {
     return "QuanLyKhoLe_KiemKeKhoLe_InDanhSachTonDau";
   }

   public String QuanLyKhoLe_KiemKeKhoLe_TaoBangKiemKeDinhKy()
   {
     return "QuanLyKhoLe_KiemKeKhoLe_TaoBangKiemKeDinhKy";
   }

   public String QuanLyKhoLe_KiemKeKhoLe_CapNhatSoLieuKiemKeThucTe()
   {
     return "QuanLyKhoLe_KiemKeKhoLe_CapNhatSoLieuKiemKeThucTe";
   }

   public String QuanLyKhoLe_KiemKeKhoLe_InBangKiemKe()
   {
     return "QuanLyKhoLe_KiemKeKhoLe_InBangKiemKe";
   }

   public String QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan()
   {
     this.tenChuongTrinh = quanLyKhoBHYT;
     return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
   }

   public String QuanLyKhoBHYT_XuatKhoBHYT_ThanhLy()
   {
     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InBaoCaoNhapXuatTrongNgay()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InBaoCaoNhapXuatTrongNgay";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InTheKho()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InTheKho";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_TinhTonKhoVaGiaTri()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_TinhTonKhoVaGiaTri";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InChiTietNhapXuatMotMatHang()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_InChiTietNhapXuatMotMatHang";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangKhongSuDung()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangKhongSuDung";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangGanHetHan()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangGanHetHan";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangTonKhoKhongHanDung()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoMatHangTonKhoKhongHanDung";
   }

   public String QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoLuongTonKho()
   {
     return "QuanLyKhoBHYT_TruyXuatThongTinKhoBHYT_CanhBaoLuongTonKho";
   }

   public String QuanLyKhoBHYT_KiemKeKhoBHYT_CapNhatTonDau()
   {
     return "QuanLyKhoBHYT_KiemKeKhoBHYT_CapNhatTonDau";
   }

   public String QuanLyKhoBHYT_KiemKeKhoBHYT_InDanhSachTonDau()
   {
     return "QuanLyKhoBHYT_KiemKeKhoBHYT_InDanhSachTonDau";
   }

   public String QuanLyKhoBHYT_KiemKeKhoBHYT_TaoBangKiemKeDinhKy()
   {
     return "QuanLyKhoBHYT_KiemKeKhoBHYT_TaoBangKiemKeDinhKy";
   }

   public String QuanLyKhoBHYT_KiemKeKhoBHYT_CapNhatSoLieuKiemKeThucTe()
   {
     return "QuanLyKhoBHYT_KiemKeKhoBHYT_CapNhatSoLieuKiemKeThucTe";
   }

   public String QuanLyKhoBHYT_KiemKeKhoBHYT_InBangKiemKe()
   {
     return "QuanLyKhoBHYT_KiemKeKhoBHYT_InBangKiemKe";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_TinhTonKhoVaGiaTri()
   {
     this.tenChuongTrinh = baoCaoDuoc;
     return "BaoCaoDuoc_InBaoCaoKhoChinh_TinhTonKhoVaGiaTri";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTheoMauSo7BoYTe()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTheoMauSo7BoYTe";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoQuyetToanThuocHuongThan()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoQuyetToanThuocHuongThan";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu";
   }

   public String BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatNhieuThang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatNhieuThang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoLe_TinhTonKhoVaGiaTri()
   {
     return "BaoCaoDuoc_InBaoCaoKhoLe_TinhTonKhoVaGiaTri";
   }

   public String BaoCaoDuoc_InBaoCaoKhoLe_InBaoCaoNhapXuatTrongThang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoLe_InBaoCaoNhapXuatTrongThang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoLe_InChiTietXuatHang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoLe_InChiTietXuatHang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoLe_InSoLieuTraKhoChinh()
   {
     return "BaoCaoDuoc_InBaoCaoKhoLe_InSoLieuTraKhoChinh";
   }

   public String BaoCaoDuoc_InBaoCaoKhoLe_InBaoCaoTheoMauSo7BoYTe()
   {
     return "BaoCaoDuoc_InBaoCaoKhoLe_InBaoCaoTheoMauSo7BoYTe";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_TinhTonKhoVaGiaTri()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_TinhTonKhoVaGiaTri";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_InBaoCaoNhapXuatTrongThang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_InBaoCaoNhapXuatTrongThang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_InChiTietXuatHang()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_InChiTietXuatHang";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_InSoLieuTraKhoChinh()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_InSoLieuTraKhoChinh";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_InBaoCaoTheoMauSo7BoYTe()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_InBaoCaoTheoMauSo7BoYTe";
   }

   public String BaoCaoDuoc_InBaoCaoKhoBHYT_InDanhSachBenhNhanLanhThuoc()
   {
     return "BaoCaoDuoc_InBaoCaoKhoBHYT_InDanhSachBenhNhanLanhThuoc";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InThongTinChiTietMotMatHang()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InThongTinChiTietMotMatHang";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InChiTietXuatHang()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InChiTietXuatHang";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_ThongKeSoPhieuThucHien()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_ThongKeSoPhieuThucHien";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_TinhTonKhoVaGiaTri()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_TinhTonKhoVaGiaTri";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoNhapXuatTrongThang()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoNhapXuatTrongThang";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoTienSuDungTaiKhoa()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoTienSuDungTaiKhoa";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoTheoMauSo7BoYTe()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoTheoMauSo7BoYTe";
   }

   public String BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoNhapXuatNhieuThang()
   {
     return "BaoCaoDuoc_InBaoCaoChungCacKho_InBaoCaoNhapXuatNhieuThang";
   }

   public String getTiepNhanKhamBenh()
   {
     return tiepNhanKhamBenh;
   }

   public void setTiepNhanKhamBenh(String tiepNhanKhamBenh)
   {
     tiepNhanKhamBenh = tiepNhanKhamBenh;
   }

   public String getDieuTri()
   {
     return dieuTri;
   }

   public void setDieuTri(String dieuTri)
   {
     dieuTri = dieuTri;
   }

   public String getQuanTriHeThong()
   {
     return quanTriHeThong;
   }

   public void setQuanTriHeThong(String quanTriHeThong)
   {
     quanTriHeThong = quanTriHeThong;
   }

   public String getVienPhiTaiKhoa()
   {
     return vienPhiTaiKhoa;
   }

   public void setVienPhiTaiKhoa(String vienPhiTaiKhoa)
   {
     vienPhiTaiKhoa = vienPhiTaiKhoa;
   }

   public String getThuVienPhi()
   {
     return thuVienPhi;
   }

   public void setThuVienPhi(String thuVienPhi)
   {
     thuVienPhi = thuVienPhi;
   }

   public String getBaoCaoVienPhi()
   {
     return baoCaoVienPhi;
   }

   public void setBaoCaoVienPhi(String baoCaoVienPhi)
   {
     baoCaoVienPhi = baoCaoVienPhi;
   }

   public String getQuanLyKhoChinh()
   {
     return quanLyKhoChinh;
   }

   public void setQuanLyKhoChinh(String quanLyKhoChinh)
   {
     quanLyKhoChinh = quanLyKhoChinh;
   }

   public String getQuanLyKhoLe()
   {
     return quanLyKhoLe;
   }

   public void setQuanLyKhoLe(String quanLyKhoLe)
   {
     quanLyKhoLe = quanLyKhoLe;
   }

   public String getQuanLyKhoBHYT()
   {
     return quanLyKhoBHYT;
   }

   public void setQuanLyKhoBHYT(String quanLyKhoBHYT)
   {
     quanLyKhoBHYT = quanLyKhoBHYT;
   }

   public String getBaoCaoDuoc()
   {
     return baoCaoDuoc;
   }

   public void setBaoCaoDuoc(String baoCaoDuoc)
   {
     baoCaoDuoc = baoCaoDuoc;
   }

   public String getTen4112_4114()
   {
     return this.ten4112_4114;
   }

   public void setTen4112_4114(String ten4112_4114)
   {
     this.ten4112_4114 = ten4112_4114;
   }

   public String getTen3241_3242()
   {
     return this.ten3241_3242;
   }

   public void setTen3241_3242(String ten3241_3242)
   {
     this.ten3241_3242 = ten3241_3242;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String QuanTriHeThong_KhoChinh()
   {
     return "QuanTriDanhMuc_KhoChinh";
   }

   public String QuanTriHeThong_DieuTri()
   {
     return "QuanTriDanhMuc_DieuTri";
   }

   public String QuanTriHeThong_ThuVienPhi()
   {
     return "QuanTriDanhMuc_ThuVienPhi";
   }

   public String QuanTriHeThong_TiepDon()
   {
     return "QuanTriDanhMuc_TiepDon";
   }

   public String BaoCaoVienPhi_SoLieuThanhToan_BangKeThanhToanVienPhi()
   {
     return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThanhToanVienPhi";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction

 * JD-Core Version:    0.7.0.1

 */