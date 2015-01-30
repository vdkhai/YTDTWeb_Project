 package com.iesvn.yte.dieutri.vienphi.action;

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

 @Name("ChonXuatFileBaoCao_VienPhi")
 @Scope(ScopeType.SESSION)
 public class ChonXuatFileBaoCao_VienPhi
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP;
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
       JasperPrintManager.printReport(this.jasperPrintVP, true);
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void xuatFileAction()
   {
     this.log.info("bat dau xuat file" + this.chonFileXuat, new Object[0]);
     String ketquaPath = null;
     String tenfile = null;
     ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
     if (this.reportTypeVP.equals("Bangkethuchitamungtheongay"))
     {
       this.log.info("-------Bang ke thu chi tam ung theo ngay-----------", new Object[0]);
       tenfile = "Bangkethuchitamungtheongay";
     }
     else if (this.reportTypeVP.equals("Bangkethuchithanhtoantheongay"))
     {
       this.log.info("-------Bang ke thu chi thanh toan theo ngay-----------", new Object[0]);
       tenfile = "Bangkethuchithanhtoantheongay";
     }
     else if (this.reportTypeVP.equals("Tonghopthuchithanhtoanravien"))
     {
       this.log.info("-------Tong hop thu chi thanh toan ra vien-----------", new Object[0]);
       tenfile = "Tonghopthuchithanhtoanravien";
     }
     else if (this.reportTypeVP.equals("Solieubenhnhanduocxetmiengiam"))
     {
       this.log.info("-------So lieu benh nhan duoc xet mien giam-----------", new Object[0]);
       tenfile = "Solieubenhnhanduocxetmiengiam";
     }
     else if (this.reportTypeVP.equals("Solieucanlamsannoitrungoaitru"))
     {
       this.log.info("-------So lieu can lam san noi tru ngoai tru-----------", new Object[0]);
       tenfile = "Solieucanlamsannoitrungoaitru";
     }
     else if (this.reportTypeVP.equals("Solieuthuchitoanvien"))
     {
       this.log.info("-------So lieu thu chi toan vien-----------", new Object[0]);
       tenfile = "Solieuthuchitoanvien";
     }
     else if (this.reportTypeVP.equals("Solieuthuchicackhoa"))
     {
       this.log.info("-------So lieu thu chi cac khoa-----------", new Object[0]);
       tenfile = "Solieuthuchicackhoa";
     }
     else if (this.reportTypeVP.equals("giaytamung"))
     {
       this.log.info("-------Giay tam ung-----------", new Object[0]);
       tenfile = "giaytamung";
     }
     else if (this.reportTypeVP.equals("chitrabottientamung"))
     {
       this.log.info("-------Giay chi tra bot tien tam ung-----------", new Object[0]);
       tenfile = "chitrabottientamung";
     }
     else if (this.reportTypeVP.equals("giaymiengiam"))
     {
       this.log.info("-------Giay mien giam-----------", new Object[0]);
       tenfile = "giaymiengiam";
     }
     else if (this.reportTypeVP.equals("capnhattientamung_pcc"))
     {
       this.log.info("-------Cap nhat tien tam ung----------", new Object[0]);
       tenfile = "capnhattientamung_pcc";
     }
     else if (this.reportTypeVP.equals("Phieucongkhai1bn"))
     {
       this.log.info("-------Phieu cong khai mot benh nhan----------", new Object[0]);
       tenfile = "Phieucongkhai1bn";
     }
     else if (this.reportTypeVP.equals("Xeminlientucphieucongkhai"))
     {
       this.log.info("-------Xem in lien tuc phieu cong khai----------", new Object[0]);
       tenfile = "Xeminlientucphieucongkhai";
     }
     else if (this.reportTypeVP.equals("B3125_Phieulinhthuoc"))
     {
       this.log.info("-------B3125_Phieulinhthuoc-----------", new Object[0]);
       tenfile = "B3125_Phieulinhthuoc";
     }
     else if (this.reportTypeVP.equals("Tonghopthuocydungcutheophong"))
     {
       this.log.info("-------Tong hop thuoc y dung cu theo phong-----------", new Object[0]);
       tenfile = "Tonghopthuocydungcutheophong";
     }
     else if (this.reportTypeVP.equals("Tonghopthuocydungcutheongaysudung"))
     {
       this.log.info("-------Tong hop thuoc y dung cu theo ngay su dung-----------", new Object[0]);
       tenfile = "Tonghopthuocydungcutheongaysudung";
     }
     else if (this.reportTypeVP.equals("B3125_Lapphieudutru"))
     {
       this.log.info("-------B3125_Lapphieudutru-----------", new Object[0]);
       tenfile = "B3125_Lapphieudutru";
     }
     else if (this.reportTypeVP.equals("B3125_2_KhoaPhongTraTheoPDT"))
     {
       this.log.info("-------B3125_2_KhoaPhongTraTheoPDT-----------", new Object[0]);
       tenfile = "B3125_2_KhoaPhongTraTheoPDT";
     }
     else if (this.reportTypeVP.equals("ThanhToanRaVien"))
     {
       this.log.info("-------ThanhToanRaVien-----------", new Object[0]);
       tenfile = "ThanhToanRaVien";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTnoitru"))
     {
       this.log.info("-------BaocaoBHYTnoitru-----------", new Object[0]);
       tenfile = "BaocaoBHYTnoitru";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTCLSngoaitru"))
     {
       this.log.info("-------BaocaoBHYTCLSngoaitru-----------", new Object[0]);
       tenfile = "BaocaoBHYTCLSngoaitru";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTtaiphongphatthuoc"))
     {
       this.log.info("-------BaocaoBHYTtaiphongphatthuoc-----------", new Object[0]);
       tenfile = "BaocaoBHYTtaiphongphatthuoc";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTNgoaitru"))
     {
       this.log.info("-------BaocaoBHYTNgoaitru-----------", new Object[0]);
       tenfile = "BaocaoBHYTNgoaitru";
     }
     else if (this.reportTypeVP.equals("TomtatsolieuBHYTsechitra"))
     {
       this.log.info("-------TomtatsolieuBHYTsechitra-----------", new Object[0]);
       tenfile = "TomtatsolieuBHYTsechitra";
     }
     else if (this.reportTypeVP.equals("Thuocydungcuphongkham"))
     {
       this.log.info("-------Thuocydungcuphongkham-----------", new Object[0]);
       tenfile = "Thuocydungcuphongkham";
     }
     else if (this.reportTypeVP.equals("B3232_Phieuthanhtoanclsphongkham"))
     {
       this.log.info("-------B3232_Phieuthanhtoanclsphongkham-----------", new Object[0]);
       tenfile = "B3232_Phieuthanhtoanclsphongkham";
     }
     else if (this.reportTypeVP.equals("thanhtoanphongcapcuu"))
     {
       this.log.info("-------B3232_Phieuthanhtoanclsphongkham-----------", new Object[0]);
       tenfile = "thanhtoanphongcapcuu";
     }
     else if (this.reportTypeVP.equals("Hoanthutiencanlamsang"))
     {
       this.log.info("-------B3232_Phieuthanhtoanclsphongkham-----------", new Object[0]);
       tenfile = "Hoanthutiencanlamsang";
     }
     else if (this.reportTypeVP.equals("V06_Chiphicanlamsangbaravien"))
     {
       this.log.info("-------V06_Chiphicanlamsangbaravien-----------", new Object[0]);
       tenfile = "V06_Chiphicanlamsangbaravien";
     }
     else if (this.reportTypeVP.equals("V10_PTthucanlamsangbnkhambenh"))
     {
       this.log.info("-------V10_PTthucanlamsangbnkhambenh-----------", new Object[0]);
       tenfile = "V10_PTthucanlamsangbnkhambenh";
     }
     else if (this.reportTypeVP.equals("V11_PTTienphongvamoyeucau"))
     {
       this.log.info("-------V11_PTTienphongvamoyeucau-----------", new Object[0]);
       tenfile = "V11_PTTienphongvamoyeucau";
     }
     else if (this.reportTypeVP.equals("V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD"))
     {
       this.log.info("-------V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD-----------", new Object[0]);
       tenfile = "V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD";
     }
     else if (this.reportTypeVP.equals("BaoCaoTongHopChiPhiKCBHYTDaTuyen"))
     {
       this.log.info("-------BaoCaoTongHopChiPhiKCBHYTDaTuyen-----------", new Object[0]);
       tenfile = "BaoCaoTongHopChiPhiKCBHYTDaTuyen";
     }
     else if (this.reportTypeVP.equals("ThongKeSoLuotBN"))
     {
       this.log.info("-------ThongKeSoLuotBN-----------", new Object[0]);
       tenfile = "ThongKeSoLuotBN";
     }
     else if (this.reportTypeVP.equals("Xacnhanthongtindieutri"))
     {
       this.log.info("-------Xacnhanthongtindieutri-----------", new Object[0]);
       tenfile = "Xacnhanthongtindieutri";
     }
     else if (this.reportTypeVP.equals("V14_Thanhtoanchiphibenhnhandieutridoituongthuphi"))
     {
       this.log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------", new Object[0]);
       tenfile = "V14_Thanhtoanchiphibenhnhandieutridoituongthuphi";
     }
     else if (this.reportTypeVP.equals("lapphieubaoanhangngay"))
     {
       this.log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------", new Object[0]);
       tenfile = "lapphieubaoanhangngay";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTnoitruNhi"))
     {
       this.log.info("-------BaocaoBHYTnoitruNhi-----------", new Object[0]);
       tenfile = "BaocaoBHYTnoitruNhi";
     }
     else if (this.reportTypeVP.equals("BaocaoBHYTNgoaitruNhi"))
     {
       this.log.info("-------BaocaoBHYTNgoaitruNhi-----------", new Object[0]);
       tenfile = "BaocaoBHYTNgoaitruNhi";
     }
     else if (this.reportTypeVP.equals("bangkethanhtoanvienphicactuyen"))
     {
       this.log.info("-------bangkethanhtoanvienphicactuyen-----------", new Object[0]);
       tenfile = "bangkethanhtoanvienphicactuyen";
     }
     else if (this.reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhong"))
     {
       this.log.info("-------PhieuLanhThuocTuTrucKhoaPhong-----------", new Object[0]);
       tenfile = "PhieuLanhThuocTuTrucKhoaPhong";
     }
     else if (this.reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhongNGT"))
     {
       this.log.info("-------PhieuLanhThuocTuTrucKhoaPhongNGT-----------", new Object[0]);
       tenfile = "PhieuLanhThuocTuTrucKhoaPhongNGT";
     }
     else if (this.reportTypeVP.equals("PhieuTraThuocTuTrucKhoaPhong"))
     {
       this.log.info("-------PhieuTraThuocTuTrucKhoaPhong-----------", new Object[0]);
       tenfile = "PhieuTraThuocTuTrucKhoaPhong";
     }
     else if (this.reportTypeVP.equals("lapphieubaoanhangngay_form"))
     {
       this.log.info("-------lapphieubaoanhangngay_form-----------", new Object[0]);
       tenfile = "LapPhieuBaoAnHangNgay";
     }
     else if (this.reportTypeVP.equals("xuatphieubaoan"))
     {
       this.log.info("-------xuatphieubaoan-----------", new Object[0]);
       tenfile = "XuatPhieuBaoAn";
     }
     else if (this.reportTypeVP.equals("xuatphieubangiaokhauphanan"))
     {
       this.log.info("-------xuatphieubangiaokhauphanan-----------", new Object[0]);
       tenfile = "Phieubangiaokhauphanan";
     }
     else if (this.reportTypeVP.equals("PhieugiaobankhoaDD"))
     {
       this.log.info("-------PhieugiaobankhoaDD-----------", new Object[0]);
       tenfile = "PhieugiaobankhoaDD";
     }
     else if (this.reportTypeVP.equals("baocaosuaduongnhi"))
     {
       this.log.info("-------baocaosuaduongnhi-----------", new Object[0]);
       tenfile = "BaoCaoSuaDuongNhi";
     }
     else if (this.reportTypeVP.equals("xuatphieukynhanspdd"))
     {
       this.log.info("-------xuatphieukynhanspdd-----------", new Object[0]);
       tenfile = "PhieuKyNhanSPDD";
     }
     else if (this.reportTypeVP.equals("baocaotienanbn"))
     {
       this.log.info("-------baocaotienanbn-----------", new Object[0]);
       tenfile = "BaoCaoTienAnBN";
     }
     else if (this.reportTypeVP.equals("phieugiaokhauphanan"))
     {
       this.log.info("-------phieugiaokhauphanan-----------", new Object[0]);
       tenfile = "PhieuGiaoKhauPhanAn";
     }
     else if (this.reportTypeVP.equals("xuatdutrutp"))
     {
       this.log.info("-------xuatdutrutp-----------", new Object[0]);
       tenfile = "BangDuTruThucPham";
     }
     else if (this.reportTypeVP.equals("baocaosanphamDD"))
     {
       this.log.info("-------baocaosanphamDD-----------", new Object[0]);
       tenfile = "BaoCaoSanPhamDD";
     }
     else if (this.reportTypeVP.equals("baocaotienanCBTC"))
     {
       this.log.info("-------baocaotienanCBTC-----------", new Object[0]);
       tenfile = "BaoCaoTienAnCBTC";
     }
     else if (this.reportTypeVP.equals("baocaovienphi"))
     {
       this.log.info("-------baocaovienphi-----------", new Object[0]);
       tenfile = "BaoCaoVienPhi";
     }
     else if (this.reportTypeVP.equals("baocaonuoc"))
     {
       this.log.info("-------baocaonuoc-----------", new Object[0]);
       tenfile = "BaoCaoNuoc";
     }
     else if (this.reportTypeVP.equals("B3160_Denghitamungthanhtoan"))
     {
       this.log.info("-------B3160_Denghitamungthanhtoan-----------", new Object[0]);
       tenfile = "B3160_Denghitamungthanhtoan";
     }
     else if (this.reportTypeVP.equals("B3341_DSBenhnhanchothanhtoan"))
     {
       this.log.info("-------B3341_DSBenhnhanchothanhtoan-----------", new Object[0]);
       tenfile = "B3341_DSBenhnhanchothanhtoan";
     }
     else if (this.reportTypeVP.equals("PhieuThanhToanKCBNgoaiTru_2"))
     {
       this.log.info("-------PhieuThanhToanKCBNgoaiTru_2-----------", new Object[0]);
       tenfile = "PhieuThanhToanKCBNgoaiTru";
     }
     int index = 0;
     String tempStr = null;
     tempStr = XuatReportUtil.ReportUtil(this.jasperPrintVP, index, ketquaPath, this.chonFileXuat.trim(), tenfile);
     setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE, ""));

     this.log.info("duong dan----" + getDuongdanFileXuat(), new Object[0]);
     setReportFinish(true);
   }

   public String troveAction()
   {
     setDuongdanFileXuat("");
     if (this.reportTypeVP.equals("Bangkethuchitamungtheongay"))
     {
       this.log.info("-------Bang ke thu chi tam ung theo ngay-----------", new Object[0]);
       return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiTamUngTheoNgay";
     }
     if (this.reportTypeVP.equals("Bangkethuchithanhtoantheongay"))
     {
       this.log.info("-------Bang ke thu chi thanh toan theo ngay-----------", new Object[0]);
       return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiThanhToanTheoNgay";
     }
     if (this.reportTypeVP.equals("Tonghopthuchithanhtoanravien"))
     {
       this.log.info("-------Tong hop thu chi thanh toan ra vien-----------", new Object[0]);
       return "BaoCaoVienPhi_SoLieuThanhToan_TongHopThuChiThanhToanRaVien";
     }
     if (this.reportTypeVP.equals("Solieubenhnhanduocxetmiengiam"))
     {
       this.log.info("-------So lieu benh nhan duoc xet mien giam-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_SoLieuBNDuocXetMienGiam";
     }
     if (this.reportTypeVP.equals("Solieucanlamsannoitrungoaitru"))
     {
       this.log.info("-------So lieu can lam san noi tru ngoai tru-----------", new Object[0]);
       return "";
     }
     if (this.reportTypeVP.equals("Solieuthuchitoanvien"))
     {
       this.log.info("-------So lieu thu chi toan vien-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuChiToanVien";
     }
     if (this.reportTypeVP.equals("Solieuthuchicackhoa"))
     {
       this.log.info("-------So lieu thu chi cac khoa-----------", new Object[0]);
       return "";
     }
     if (this.reportTypeVP.equals("giaytamung"))
     {
       this.log.info("-------Giay tam ung-----------", new Object[0]);
       return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng";
     }
     if (this.reportTypeVP.equals("chitrabottientamung"))
     {
       this.log.info("-------Giay chi tra bot tien tam ung-----------", new Object[0]);
       return "ThuVienPhi_SoLieuDieuTriTamUng_ChiTraBotTienTamUng";
     }
     if (this.reportTypeVP.equals("giaymiengiam"))
     {
       this.log.info("-------Giay mien giam-----------", new Object[0]);
       return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatMienGiam";
     }
     if (this.reportTypeVP.equals("capnhattientamung_pcc"))
     {
       this.log.info("-------Cap nhat tien tam ung----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_TamUngPhongCapCuu";
     }
     if (this.reportTypeVP.equals("Phieucongkhai1bn"))
     {
       this.log.info("-------Phieu cong khai mot benh nhan-----------", new Object[0]);
       return "VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN";
     }
     if (this.reportTypeVP.equals("Xeminlientucphieucongkhai"))
     {
       this.log.info("-------Xem in lien tuc phieu cong khai-----------", new Object[0]);
       return "VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai";
     }
     if (this.reportTypeVP.equals("B3125_Phieulinhthuoc"))
     {
       this.log.info("-------Giay mien giam-----------", new Object[0]);
       return "VienPhiTaiKhoa_XemInPhieu_InSoThuoc";
     }
     if (this.reportTypeVP.equals("Tonghopthuocydungcutheophong"))
     {
       this.log.info("-------Tong hop thuoc y dung cu theo phong-----------", new Object[0]);
       return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong";
     }
     if (this.reportTypeVP.equals("Tonghopthuocydungcutheongaysudung"))
     {
       this.log.info("-------Tong hop thuoc y dung cu theo ngay su dung-----------", new Object[0]);
       return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoNgaySuDung";
     }
     if (this.reportTypeVP.equals("B3125_Lapphieudutru"))
     {
       this.log.info("-------B3125_Lapphieudutru-----------", new Object[0]);
       return "B3125_Lapphieudutru";
     }
     if (this.reportTypeVP.equals("B3125_2_KhoaPhongTraTheoPDT"))
     {
       this.log.info("-------B3125_2_KhoaPhongTraTheoPDT-----------", new Object[0]);
       return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTruTra";
     }
     if (this.reportTypeVP.equals("ThanhToanRaVien"))
     {
       this.log.info("-------ThanhToanRaVien-----------", new Object[0]);
       return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTnoitru"))
     {
       this.log.info("-------BaocaoBHYTnoitru-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTCLSngoaitru"))
     {
       this.log.info("-------BaocaoBHYTCLSngoaitru-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTCLSNgoaiTru";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTtaiphongphatthuoc"))
     {
       this.log.info("-------BaocaoBHYTtaiphongphatthuoc-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTNgoaitru"))
     {
       this.log.info("-------BaocaoBHYTNgoaitru-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru";
     }
     if (this.reportTypeVP.equals("TomtatsolieuBHYTsechitra"))
     {
       this.log.info("-------TomtatsolieuBHYTsechitra-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_TomTatSoLieuBHYTSeChiTra";
     }
     if (this.reportTypeVP.equals("Thuocydungcuphongkham"))
     {
       this.log.info("-------Thuocydungcuphongkham-----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
     }
     if (this.reportTypeVP.equals("B3232_Phieuthanhtoanclsphongkham"))
     {
       this.log.info("-------B3232_Phieuthanhtoanclsphongkham-----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
     }
     if (this.reportTypeVP.equals("thanhtoanphongcapcuu"))
     {
       this.log.info("-------thanhtoanphongcapcuu-----------", new Object[0]);
       return "TiepDon_KhamBenh_XemChiPhiDieuTri";
     }
     if (this.reportTypeVP.equals("Hoanthutiencanlamsang"))
     {
       this.log.info("-------Hoanthutiencanlamsang-----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
     }
     if (this.reportTypeVP.equals("V06_Chiphicanlamsangbaravien"))
     {
       this.log.info("-------V06_Chiphicanlamsangbaravien-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru";
     }
     if (this.reportTypeVP.equals("V10_PTthucanlamsangbnkhambenh"))
     {
       this.log.info("-------V10_PTthucanlamsangbnkhambenh-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuTaiCacPhongKham";
     }
     if (this.reportTypeVP.equals("V11_PTTienphongvamoyeucau"))
     {
       this.log.info("-------V11_PTTienphongvamoyeucau-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongMoYeuCau";
     }
     if (this.reportTypeVP.equals("V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD"))
     {
       this.log.info("-------V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_DanhSachNguoiBenhKCBNgoaiTruDeNghiThanhToanMauC98aHD";
     }
     if (this.reportTypeVP.equals("BaoCaoTongHopChiPhiKCBHYTDaTuyen"))
     {
       this.log.info("-------BaoCaoTongHopChiPhiKCBHYTDaTuyen-----------", new Object[0]);
       return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_BangTongHopChiPhiKCBDaTuyen";
     }
     if (this.reportTypeVP.equals("ThongKeSoLuotBN"))
     {
       this.log.info("-------ThongKeSoLuotBN-----------", new Object[0]);
       return "BaoCaoVienPhi_BHYT_ThongKeSoLuotTungBN";
     }
     if (this.reportTypeVP.equals("Xacnhanthongtindieutri"))
     {
       this.log.info("-------Xacnhanthongtindieutri-----------", new Object[0]);
       return "VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri";
     }
     if (this.reportTypeVP.equals("V14_Thanhtoanchiphibenhnhandieutridoituongthuphi"))
     {
       this.log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------", new Object[0]);
       return "BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru";
     }
     if (this.reportTypeVP.equals("lapphieubaoanhangngay"))
     {
       this.log.info("-------lapphieubaoanhangngay-----------", new Object[0]);
       return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTnoitruNhi"))
     {
       this.log.info("-------BaocaoBHYTnoitruNhi-----------", new Object[0]);
       return "baocaobhyt_noitru_nhi";
     }
     if (this.reportTypeVP.equals("BaocaoBHYTNgoaitruNhi"))
     {
       this.log.info("-------BaocaoBHYTNgoaitruNhi-----------", new Object[0]);
       return "baocaobhyt_ngoaitru_nhi";
     }
     if (this.reportTypeVP.equals("bangkethanhtoanvienphicactuyen"))
     {
       this.log.info("-------bangkethanhtoanvienphicactuyen-----------", new Object[0]);
       return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThanhToanVienPhi";
     }
     if (this.reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhongNGT"))
     {
       this.log.info("-------PhieuLanhThuocTuTrucKhoaPhongNGT-----------", new Object[0]);
       return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTruNGT";
     }
     if (this.reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhong"))
     {
       this.log.info("-------PhieuLanhThuocTuTrucKhoaPhong-----------", new Object[0]);
       return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTru";
     }
     if (this.reportTypeVP.equals("PhieuTraThuocTuTrucKhoaPhong"))
     {
       this.log.info("-------PhieuTraThuocTuTrucKhoaPhong-----------", new Object[0]);
       return "VienPhiTaiKhoa_PhieuDTTraThuocTuTru";
     }
     if (this.reportTypeVP.equals("lapphieubaoanhangngay_form"))
     {
       this.log.info("-------lapphieubaoanhangngay_form-----------", new Object[0]);
       return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
     }
     if (this.reportTypeVP.equals("xuatphieubaoan"))
     {
       this.log.info("-------xuatphieubaoan-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3142_Xuatphieubaoan.xhtml";
     }
     if (this.reportTypeVP.equals("xuatphieubangiaokhauphanan"))
     {
       this.log.info("-------xuatphieubangiaokhauphanan-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3144_Xuatphieubangiaokhauphanan.xhtml";
     }
     if (this.reportTypeVP.equals("PhieugiaobankhoaDD"))
     {
       this.log.info("-------PhieugiaobankhoaDD-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3141_Phieugiaoban.xhtml";
     }
     if (this.reportTypeVP.equals("baocaosuaduongnhi"))
     {
       this.log.info("-------baocaosuaduongnhi-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3148_BaoCaoSuaDuongNhi.xhtml";
     }
     if (this.reportTypeVP.equals("xuatphieukynhanspdd"))
     {
       this.log.info("-------xuatphieukynhanspdd-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3149_PhieuKyNhanSPDD.xhtml";
     }
     if (this.reportTypeVP.equals("baocaotienanbn"))
     {
       this.log.info("-------baocaotienanbn-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3150_BaoCaoTienAnBN.xhtml";
     }
     if (this.reportTypeVP.equals("phieugiaokhauphanan"))
     {
       this.log.info("-------phieugiaokhauphanan-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3151_PhieuGiaoKhauPhanAn.xhtml";
     }
     if (this.reportTypeVP.equals("xuatdutrutp"))
     {
       this.log.info("-------xuatdutrutp-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3154_XuatDuTruThucPham.xhtml";
     }
     if (this.reportTypeVP.equals("baocaosanphamDD"))
     {
       this.log.info("-------baocaosanphamDD-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3155_BaoCaoSanPhamDD.xhtml";
     }
     if (this.reportTypeVP.equals("baocaotienanCBTC"))
     {
       this.log.info("-------baocaotienanCBTC-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3156_BaoCaoTienAnCBTC.xhtml";
     }
     if (this.reportTypeVP.equals("baocaovienphi"))
     {
       this.log.info("-------baocaovienphi-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3157_BaoCaoVienPhi.xhtml";
     }
     if (this.reportTypeVP.equals("baocaonuoc"))
     {
       this.log.info("-------baocaonuoc-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3159_BaoCaoNuoc.xhtml";
     }
     if (this.reportTypeVP.equals("B3160_Denghitamungthanhtoan"))
     {
       this.log.info("-------B3160_Denghitamungthanhtoan-----------", new Object[0]);
       return "/B3_Vienphi/TaiKhoa/B3160_Denghitamungthanhtoan.xhtml";
     }
     if (this.reportTypeVP.equals("B3341_DSBenhnhanchothanhtoan"))
     {
       this.log.info("-------B3341_DSBenhnhanchothanhtoan-----------", new Object[0]);
       return "/B3_Vienphi/BaoCao/B3341_DSBenhNhanChoThanhToan.xhtml";
     }
     if (this.reportTypeVP.equals("PhieuThanhToanKCBNgoaiTru_2"))
     {
       this.log.info("-------PhieuThanhToanKCBNgoaiTru_2-----------", new Object[0]);
       return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ChonXuatFileBaoCao_VienPhi

 * JD-Core Version:    0.7.0.1

 */