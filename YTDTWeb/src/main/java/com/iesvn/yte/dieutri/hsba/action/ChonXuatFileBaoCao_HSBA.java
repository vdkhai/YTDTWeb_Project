 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperPrintManager;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("ChonXuatFileBaoCaoHSBA")
 @Scope(ScopeType.SESSION)
 public class ChonXuatFileBaoCao_HSBA
   implements Serializable
 {
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT;
   private String chonFileXuat = null;
   private boolean reportFinish = false;
   private String duongdanFileXuat = null;

   @Create
   public void init()
   {
     setChonFileXuat("DOC");
   }

   public void inbaocao()
   {
     this.log.info("bat dau in bao cao", new Object[0]);
     try
     {
       JasperPrintManager.printReport(this.jasperPrintDT, true);
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void xuatFileAction()
   {
     this.log.info("bat dau xuat file" + this.chonFileXuat, new Object[0]);
     String tenfile = null;
     String ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/";
     if (this.loaiBCDT.equals("BCTHBenhTatTuVong"))
     {
       this.log.info("-------Bao cao tinh hinh benh tat tu vong-----------", new Object[0]);
       tenfile = "BCTHBenhTatTuVong";
     }
     else if (this.loaiBCDT.equals("Soravaovien"))
     {
       this.log.info("-------So ra vao vien----------", new Object[0]);
       tenfile = "Soravaovien";
     }
     else if (this.loaiBCDT.equals("Sochuyenvien"))
     {
       this.log.info("-------So chuyen vien----------", new Object[0]);
       tenfile = "Sochuyenvien";
     }
     else if (this.loaiBCDT.equals("BaoCaoBADangCapnhat"))
     {
       this.log.info("-------BaoCaoBADangCapnhat----------", new Object[0]);
       tenfile = "BaoCaoBADangCapnhat";
     }
     else if (this.loaiBCDT.equals("BaoCaoBAKetThucCapnhat"))
     {
       this.log.info("-------BaoCaoBAKetThucCapnhat ----------", new Object[0]);
       tenfile = "BaoCaoBAKetThucCapnhat";
     }
     else if (this.loaiBCDT.equals("GiayChuyenVien"))
     {
       this.log.info("-------GiayChuyenVien ----------", new Object[0]);
       tenfile = "GiayChuyenVien";
     }
     else if (this.loaiBCDT.equals("GiayRaVien"))
     {
       this.log.info("-------GiayRaVien ----------", new Object[0]);
       tenfile = "GiayRaVien";
     }
     else if (this.loaiBCDT.equals("GiayChungNhan"))
     {
       this.log.info("-------GiayChungNhan ----------", new Object[0]);
       tenfile = "GiayChungNhan";
     }
     else if (this.loaiBCDT.equals("GiayChuyenXac"))
     {
       this.log.info("-------GiayChuyenXac ----------", new Object[0]);
       tenfile = "GiayChuyenXac";
     }
     else if (this.loaiBCDT.equals("PhieuGuiXac"))
     {
       this.log.info("-------PhieuGuiXac ----------", new Object[0]);
       tenfile = "PhieuGuiXac";
     }
     else if (this.loaiBCDT.equals("BaoCaoThongKeTaiNan"))
     {
       this.log.info("-------Bao cao thong ke tai nan ----------", new Object[0]);
       tenfile = "baocaothongketainan";
     }
     else if (this.loaiBCDT.equals("PhieuPhauThuatThuThuat"))
     {
       this.log.info("-------PhieuPhauThuatThuThuat ----------", new Object[0]);
       tenfile = "PhieuPhauThuatThuThuat";
     }
     else if (this.loaiBCDT.equals("LapTrichBienBanHoiChan"))
     {
       this.log.info("-------LapTrichBienBanHoiChan ----------", new Object[0]);
       tenfile = "LapTrichBienBanHoiChan";
     }
     else if (this.loaiBCDT.equals("Giaytomtatbenhan"))
     {
       this.log.info("-------Giaytomtatbenhan ----------", new Object[0]);
       tenfile = "Giaytomtatbenhan";
     }
     else if (this.loaiBCDT.equals("Bangtomtatbenhan"))
     {
       this.log.info("-------Bangtomtatbenhan ----------", new Object[0]);
       tenfile = "Bangtomtatbenhan";
     }
     else if (this.loaiBCDT.equals("Giaychungnhanphauthuat"))
     {
       this.log.info("-------Giaychungnhanphauthuat ----------", new Object[0]);
       tenfile = "Giaychungnhanphauthuat";
     }
     else if (this.loaiBCDT.equals("Giaychungnhanthuongtich"))
     {
       this.log.info("-------Giaychungnhanthuongtich ----------", new Object[0]);
       tenfile = "Giaychungnhanthuongtich";
     }
     else if (this.loaiBCDT.equals("giaykhambenhvaovien"))
     {
       this.log.info("-------giaykhambenhvaovien ----------", new Object[0]);
       tenfile = "giaykhambenhvaovien";
     }
     else if (this.loaiBCDT.equals("Timkiembenhnhantheongayhionhapvien"))
     {
       this.log.info("-------Timkiembenhnhantheongayhionhapvien ----------", new Object[0]);
       tenfile = "Timkiembenhnhantheongayhionhapvien";
     }
     else if (this.loaiBCDT.equals("soluutruhsba"))
     {
       this.log.info("-------soluutruhsba ----------", new Object[0]);
       tenfile = "soluutruhsba";
     }
     else if (this.loaiBCDT.equals("bienbanhoichanphauthuat"))
     {
       this.log.info("-------bienbanhoichanphauthuat ----------", new Object[0]);
       tenfile = "bienbanhoichanphauthuat";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANoi"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANoi ----------", new Object[0]);
       tenfile = "CapNhatThongTinChiTietBANoi";
     }
     else if (this.loaiBCDT.equals("phieuchamsoc"))
     {
       this.log.info("-------PHIEUCHAMSOC ----------", new Object[0]);
       tenfile = "phieuchamsoc";
     }
     else if (this.loaiBCDT.equals("ToDieuTri"))
     {
       this.log.info("-------ToDieuTri ----------", new Object[0]);
       tenfile = "ToDieuTri";
     }
     else if (this.loaiBCDT.equals("PhieuTheoDoiTruyenDich"))
     {
       this.log.info("-------PhieuTheoDoiTruyenDich ----------", new Object[0]);
       tenfile = "PhieuTheoDoiTruyenDich";
     }
     else if (this.loaiBCDT.equals("DSNguoiBenhVaoRaVien"))
     {
       this.log.info("-------DSNguoiBenhVaoRaVien ----------", new Object[0]);
       tenfile = "DSNguoiBenhVaoRaVien";
     }
     else if (this.loaiBCDT.equals("BCBenhTruyenNhiem"))
     {
       this.log.info("-------BCBenhTruyenNhiem ----------", new Object[0]);
       tenfile = "BCBenhTruyenNhiem";
     }
     else if (this.loaiBCDT.equals("BCCoQuanYTeChuyenDen"))
     {
       this.log.info("-------InDSCoQuanYTeChuyenDen ----------", new Object[0]);
       tenfile = "danhsachcoquanytechuyenden";
     }
     else if (this.loaiBCDT.equals("BCHoatDongKhamBenh"))
     {
       this.log.info("-------BCHoatDongKhamBenh ----------", new Object[0]);
       tenfile = "hoatdongkhambenh";
     }
     else if (this.loaiBCDT.equals("BieuMauBanTuDong"))
     {
       this.log.info("-------BieuMauBanTuDong ----------", new Object[0]);
       tenfile = "bieumaubantudong";
     }
     else if (this.loaiBCDT.equals("BieuMauBanTuDong1"))
     {
       this.log.info("-------BieuMauBanTuDong1 ----------", new Object[0]);
       tenfile = "bieumaubantudong1";
     }
     else if (this.loaiBCDT.equals("BieuMauBanTuDong2"))
     {
       this.log.info("-------BieuMauBanTuDong2 ----------", new Object[0]);
       tenfile = "bieumaubantudong2";
     }
     else if (this.loaiBCDT.equals("BieuMauBanTuDong3"))
     {
       this.log.info("-------BieuMauBanTuDong3 ----------", new Object[0]);
       tenfile = "bieumaubantudong3";
     }
     else if (this.loaiBCDT.equals("BieuMauBanTuDong4"))
     {
       this.log.info("-------BieuMauBanTuDong4 ----------", new Object[0]);
       tenfile = "bieumaubantudong4";
     }
     else if (this.loaiBCDT.equals("BCDuocBenhVien"))
     {
       this.log.info("-------BCDuocBenhVien ----------", new Object[0]);
       tenfile = "duocbenhvien";
     }
     else if (this.loaiBCDT.equals("BCSotXuatHuyet"))
     {
       this.log.info("-------BCSotXuatHuyet----------", new Object[0]);
       tenfile = "sotxuathuyet";
     }
     else if (this.loaiBCDT.equals("BCTinhHinhHoatDongTrongNgay"))
     {
       this.log.info("-------BCTinhHinhHoatDongTrongNgay ----------", new Object[0]);
       tenfile = "tinhhinhhoatdongtrongngay";
     }
     else if (this.loaiBCDT.equals("BCTinhHinhBenhTatVaTuVongTaiBV"))
     {
       this.log.info("-------BCTinhHinhBenhTatVaTuVongTaiBV ----------", new Object[0]);
       tenfile = "tinhhinhbenhtatvatuvongtaibv";
     }
     else if (this.loaiBCDT.equals("BCHoatDongCanLamSan"))
     {
       this.log.info("-------BCHoatDongCanLamSan ----------", new Object[0]);
       tenfile = "hoatdongcanlamsan";
     }
     else if (this.loaiBCDT.equals("BCHoatDongCacKhoa"))
     {
       this.log.info("-------BCHoatDongCacKhoa ----------", new Object[0]);
       tenfile = "hoatdongcackhoa";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBATmh"))
     {
       this.log.info("-------CapNhatThongTinChiTietBATmh ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBATmh";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANhikhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANhikhoa ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBANhikhoa";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBASosinh"))
     {
       this.log.info("-------CapNhatThongTinChiTietBASosinh ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBASosinh";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBAPhukhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBAPhukhoa ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBAPhukhoa";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBASankhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBASankhoa ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBASankhoa";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANgoaitruYhct"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANgoaitruYhct ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBANgoaitruYhct";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANaophathai"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANaophathai ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBANaophathai";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANoitruYhct"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANoitruYhct ----------", new Object[0]);
       tenfile = "capNhatThongTinChiTietBANoitruYhct";
     }
     else if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANgoai"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANgoai ----------", new Object[0]);
       tenfile = "CapNhatThongTinChiTietBANgoai";
     }
     else if (this.loaiBCDT.equals("SoSanhTinhHinhMacBenhTheoNam"))
     {
       this.log.info("-------SoSanhTinhHinhMacBenhTheoNam ----------", new Object[0]);
       tenfile = "SoSanhTinhHinhMacBenhTheoNam";
     }
     int index = 0;
     String tempStr = null;
     tempStr = XuatReportUtil.ReportUtil(this.jasperPrintDT, index, ketquaPath, this.chonFileXuat.trim(), tenfile);
     setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE, ""));
     this.log.info("duong dan----" + getDuongdanFileXuat(), new Object[0]);
     setReportFinish(true);
   }

   public String troveAction()
   {
     setDuongdanFileXuat("");
     if (this.loaiBCDT.equals("Soravaovien"))
     {
       this.log.info("-------So ra vao vien----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_SoRaVaoVien";
     }
     if (this.loaiBCDT.equals("Sochuyenvien"))
     {
       this.log.info("-------So chuyen vien----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_SoChuyenVien";
     }
     if (this.loaiBCDT.equals("BCTHBenhTatTuVong"))
     {
       this.log.info("-------Bao cao tinh hinh benh tat tu vong-----------", new Object[0]);
       return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatTuVong";
     }
     if (this.loaiBCDT.equals("BaoCaoBADangCapnhat"))
     {
       this.log.info("-------So BaoCaoBADangCapnhat vien----------", new Object[0]);
       return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat";
     }
     if (this.loaiBCDT.equals("BaoCaoThongKeTaiNan"))
     {
       this.log.info("-------So BaoCaoThongKeTaiNan ----------", new Object[0]);
       return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoThongKeTaiNan";
     }
     if (this.loaiBCDT.equals("BaoCaoBAKetThucCapnhat"))
     {
       this.log.info("-------BaoCaoBAKetThucCapnhat ----------", new Object[0]);
       return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnKetThucCapNhat";
     }
     if (this.loaiBCDT.equals("GiayChuyenVien"))
     {
       this.log.info("-------GiayChuyenVien ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayChuyenVien";
     }
     if (this.loaiBCDT.equals("GiayRaVien"))
     {
       this.log.info("-------GiayRaVien ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayRaVien";
     }
     if (this.loaiBCDT.equals("GiayChungNhan"))
     {
       this.log.info("-------GiayChungNhan----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayChungNhan";
     }
     if (this.loaiBCDT.equals("GiayChuyenXac"))
     {
       this.log.info("-------GiayChuyenXac ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayChuyenXac";
     }
     if (this.loaiBCDT.equals("PhieuGuiXac"))
     {
       this.log.info("-------PhieuGuiXac ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_PhieuGuiXac";
     }
     if (this.loaiBCDT.equals("BieuMauBanTuDong"))
     {
       this.log.info("-------BieuMauBanTuDong ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong";
     }
     if (this.loaiBCDT.equals("BieuMauBanTuDong1"))
     {
       this.log.info("-------BieuMauBanTuDong1 ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong1";
     }
     if (this.loaiBCDT.equals("BieuMauBanTuDong2"))
     {
       this.log.info("-------BieuMauBanTuDong2 ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong2";
     }
     if (this.loaiBCDT.equals("BieuMauBanTuDong3"))
     {
       this.log.info("-------BieuMauBanTuDong3 ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong3";
     }
     if (this.loaiBCDT.equals("BieuMauBanTuDong4"))
     {
       this.log.info("-------BieuMauBanTuDong4 ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong4";
     }
     if (this.loaiBCDT.equals("PhieuPhauThuatThuThuat"))
     {
       this.log.info("-------PhieuPhauThuatThuThuat ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_PhieuPhauThuatThuThuat";
     }
     if (this.loaiBCDT.equals("LapTrichBienBanHoiChan"))
     {
       this.log.info("-------LapTrichBienBanHoiChan ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_LapTrichBienBanHoiChan";
     }
     if (this.loaiBCDT.equals("Giaytomtatbenhan"))
     {
       this.log.info("-------Giaytomtatbenhan ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_TomTatBenhAn";
     }
     if (this.loaiBCDT.equals("Bangtomtatbenhan"))
     {
       this.log.info("-------Bangtomtatbenhan ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BangTomTatBenhAn";
     }
     if (this.loaiBCDT.equals("Giaychungnhanphauthuat"))
     {
       this.log.info("-------Giaychungnhanphauthuat ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayChungNhanPhauThuat";
     }
     if (this.loaiBCDT.equals("Giaychungnhanthuongtich"))
     {
       this.log.info("-------Giaychungnhanthuongtich ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_GiayChungThuong";
     }
     if (this.loaiBCDT.equals("giaykhambenhvaovien"))
     {
       this.log.info("-------giaykhambenhvaovien ----------", new Object[0]);
       return "DieuTri_CapNhat_CapNhatThongTinNhapVien";
     }
     if (this.loaiBCDT.equals("Timkiembenhnhantheongayhionhapvien"))
     {
       this.log.info("-------Timkiembenhnhantheongayhionhapvien ----------", new Object[0]);
       return "DieuTri_BaoCaoHoatDongDieuTri_TimDanhSachBenhNhanTheoNgayGioNhapVien";
     }
     if (this.loaiBCDT.equals("soluutruhsba"))
     {
       this.log.info("-------soluutruhsba ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn";
     }
     if (this.loaiBCDT.equals("bienbanhoichanphauthuat"))
     {
       this.log.info("-------bienbanhoichanphauthuat ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_BienBanHoiChanPhauThuat";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANoi"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANoi ----------", new Object[0]);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoi";
     }
     if (this.loaiBCDT.equals("phieuchamsoc"))
     {
       this.log.info("-------PHIEUCHAMSOC ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_PhieuChamSoc";
     }
     if (this.loaiBCDT.equals("ToDieuTri"))
     {
       this.log.info("-------ToDieuTri ----------", new Object[0]);
       return "DieuTri_LapVanBanTheoMau_ToDieuTri";
     }
     if (this.loaiBCDT.equals("PhieuTheoDoiTruyenDich"))
     {
       this.log.info("-------PhieuTheoDoiTruyenDich ----------", new Object[0]);
       return "/B2_Dieutri/B270_phieutheodoitruyendich.xhtml";
     }
     if (this.loaiBCDT.equals("DSNguoiBenhVaoRaVien"))
     {
       this.log.info("-------DSNguoiBenhVaoRaVien ----------", new Object[0]);
       return "/B2_Dieutri/B271_DanhSachNguoiBenhRaVaoVien.xhtml";
     }
     if (this.loaiBCDT.equals("BCBenhTruyenNhiem"))
     {
       this.log.info("-------BCBenhTruyenNhiem ----------", new Object[0]);
       return "/B2_Dieutri/B272_BaoCaoBenhTruyenNhiem.xhtml";
     }
     if (this.loaiBCDT.equals("BCHD_PhauThuatThuThuat"))
     {
       this.log.info("-------BCHD_PhauThuatThuThuat ----------", new Object[0]);
       return "/B2_Dieutri/B217_BaoCaoHoatDongPhauThuatThuThuat.xhtml";
     }
     if (this.loaiBCDT.equals("BCCoQuanYTeChuyenDen"))
     {
       this.log.info("-------InDSCoQuanYTeChuyenDen ----------", new Object[0]);
       return "/B2_Dieutri/B274_InDSCoQuanYTeChuyenDen.xhtml";
     }
     if (this.loaiBCDT.equals("BCHoatDongKhamBenh"))
     {
       this.log.info("-------BCHoatDongKhamBenh ----------", new Object[0]);
       return "/B2_Dieutri/B275_BaoCaoHoatDongKhamBenh.xhtml";
     }
     if (this.loaiBCDT.equals("BCDuocBenhVien"))
     {
       this.log.info("-------BCDuocBenhVien ----------", new Object[0]);
       return "/B2_Dieutri/B295_Baocaoduocbenhvien.xhtml";
     }
     if (this.loaiBCDT.equals("BCSotXuatHuyet"))
     {
       this.log.info("-------BCSotXuatHuyet ----------", new Object[0]);
       return "/B2_Dieutri/B296_Baocaosotxuathuyet.xhtml";
     }
     if (this.loaiBCDT.equals("BCTinhHinhHoatDongTrongNgay"))
     {
       this.log.info("-------BCTinhHinhHoatDongTrongNgay ----------", new Object[0]);
       return "/B2_Dieutri/B276_BaoCaoTinhHinhHoatDongTrongNgay.xhtml";
     }
     if (this.loaiBCDT.equals("BCTinhHinhBenhTatVaTuVongTaiBV"))
     {
       this.log.info("-------BCTinhHinhBenhTatVaTuVongTaiBV ----------", new Object[0]);
       return "/B2_Dieutri/B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV.xhtml";
     }
     if (this.loaiBCDT.equals("BCHoatDongCanLamSan"))
     {
       this.log.info("-------BCHoatDongCanLamSan ----------", new Object[0]);
       return "/B2_Dieutri/B278_BaoCaoHoatDongCanLamSan.xhtml";
     }
     if (this.loaiBCDT.equals("BCHoatDongCacKhoa"))
     {
       this.log.info("-------BCHoatDongCacKhoa ----------", new Object[0]);
       return "/B2_Dieutri/B279_BaoCaoHoatDongCacKhoa.xhtml";
     }
     if (this.loaiBCDT.equals("DanhSachBenhAnDangCapNhatTaiKhoa"))
     {
       this.log.info("------- DanhSachBenhAnDangCapNhatTaiKhoa ----------", new Object[0]);
       return "/B2_Dieutri/B280_DanhSachBenhAnDangCapNhatTaiKhoa.xhtml";
     }
     if (this.loaiBCDT.equals("BaoCaoThongKeTaiNan"))
     {
       this.log.info("------- BaoCaoThongKeTaiNan ----------", new Object[0]);
       return "/B2_Dieutri/B300_Baocaothongketainan.xhtml";
     }
     if (this.loaiBCDT.equals("DanhSachBenhNhanChuyenVien"))
     {
       this.log.info("------- DanhSachBenhNhanChuyenVien ----------", new Object[0]);
       return "/B2_Dieutri/B281_DanhSachBenhNhanChuyenVien.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBATmh"))
     {
       this.log.info("-------CapNhatThongTinChiTietBATmh ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbatmh.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBARhm"))
     {
       this.log.info("-------CapNhatThongTinChiTietBARhm ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbarhm.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANhikhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANhikhoa ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanhikhoa.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBASosinh"))
     {
       this.log.info("-------CapNhatThongTinChiTietBASosinh ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbasosinh.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBAPhukhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBAPhukhoa ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbaphukhoa.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBASankhoa"))
     {
       this.log.info("-------CapNhatThongTinChiTietBASankhoa ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbasankhoa.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANgoaitruYhct"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANgoaitruYhct ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbangoaitruyhct.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANaophathai"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANaophathai ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanaophathai.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANoitruYhct"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANoitruYhct ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanoitruyhct.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBAMat"))
     {
       this.log.info("-------CapNhatThongTinChiTietBAMat ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbamat.xhtml";
     }
     if (this.loaiBCDT.equals("CapNhatThongTinChiTietBANgoai"))
     {
       this.log.info("-------CapNhatThongTinChiTietBANgoai ----------", new Object[0]);
       return "/B2_Dieutri/B211_3_Capnhathongtinchitietbangoai.xhtml";
     }
     if (this.loaiBCDT.equals("SoSanhTinhHinhMacBenhTheoNam"))
     {
       this.log.info("-------SoSanhTinhHinhMacBenhTheoNam ----------", new Object[0]);
       return "/B2_Dieutri/B298_SoSanhTinhHinhMacBenhTheoNam.xhtml";
     }
     if (this.loaiBCDT.equals("CanLamSanPhauThuat"))
     {
       this.log.info("-------CanLamSanPhauThuat ----------", new Object[0]);
       return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan";
     }
     return null;
   }

   public void setChonFileXuat(String chonFileXuat)
   {
     this.chonFileXuat = chonFileXuat;
   }

   public String getChonFileXuat()
   {
     return this.chonFileXuat;
   }

   public void setDuongdanFileXuat(String duongdanFileXuat)
   {
     this.duongdanFileXuat = duongdanFileXuat;
   }

   public String getDuongdanFileXuat()
   {
     return this.duongdanFileXuat;
   }

   public void setReportFinish(boolean reportFinish)
   {
     this.reportFinish = reportFinish;
   }

   public boolean isReportFinish()
   {
     return this.reportFinish;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.ChonXuatFileBaoCao_HSBA

 * JD-Core Version:    0.7.0.1

 */