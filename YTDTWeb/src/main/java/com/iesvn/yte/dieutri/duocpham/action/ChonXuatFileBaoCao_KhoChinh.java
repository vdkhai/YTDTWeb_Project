 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperPrintManager;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("ChonXuatFileBaoCao_KhoChinh")
 @Scope(ScopeType.CONVERSATION)
 public class ChonXuatFileBaoCao_KhoChinh
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC;
   private String chonFileXuat = null;
   private boolean reportFinish = false;
   private String duongdanFileXuat = null;

   @Begin(join=true)
   public String init()
   {
     setChonFileXuat("DOC");
     return "B4160_Chonmenuxuattaptin";
   }

   @End
   public void EndFunc() {}

   @Create
   public void initForm()
   {
     setChonFileXuat("DOC");
   }

   public void inbaocao()
   {
     this.log.info("bat dau in bao cao", new Object[0]);
     try
     {
       JasperPrintManager.printReport(this.jasperPrintKC, true);
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
     ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/";
     if (this.reportTypeKC.equals("BCnhapxuat1mathang"))
     {
       this.log.info("-------Bao cao tinh hinh nhap xuat mot mat hang-----------", new Object[0]);
       tenfile = "BCnhapxuat1mathang";
     }
     else if (this.reportTypeKC.equals("Mathangkhongsd"))
     {
       this.log.info("-------Bao cao mat hang khogn su dung-----------", new Object[0]);
       tenfile = "Mathangkhongsd";
     }
     else if (this.reportTypeKC.equals("Canhbaohangganhethan"))
     {
       this.log.info("-------Bao cao mat hang dan het han-----------", new Object[0]);
       tenfile = "Canhbaohangganhethan";
     }
     else if (this.reportTypeKC.equals("Dstonkhokhonghandung"))
     {
       this.log.info("-------Bao cao mat hang khong han dung-----------", new Object[0]);
       tenfile = "Tonkhokhonghandung";
     }
     else if (this.reportTypeKC.equals("Indanhsachtondau"))
     {
       this.log.info("-------In danh sach ton dau-----------", new Object[0]);
       tenfile = "Solieutondau";
     }
     else if (this.reportTypeKC.equals("tinhhinhkinhphinhap"))
     {
       this.log.info("-------Tinh hinh kinh phi nhap-----------", new Object[0]);
       tenfile = "Tinhhinhkinhphinhap";
     }
     else if (this.reportTypeKC.equals("Inbctiensudungtaikhoa"))
     {
       this.log.info("-------Bao cao tien su dung-----------", new Object[0]);
       tenfile = "Baocaotiensudung";
     }
     else if (this.reportTypeKC.equals("Phantichsolieunhapxuat"))
     {
       this.log.info("-------Phan tich so lieu nhap xuat----------", new Object[0]);
       tenfile = "Phantichsolieunhapxuat";
     }
     else if (this.reportTypeKC.equals("Inthephieu"))
     {
       this.log.info("-------In the kho----------", new Object[0]);
       tenfile = "Inthekho";
     }
     else if (this.reportTypeKC.equals("Inbenhnhanlanhthuoc"))
     {
       this.log.info("-------In benh nhan lanh thuoc----------", new Object[0]);
       tenfile = "Inbenhnhanlanhthuoc";
     }
     else if (this.reportTypeKC.equals("Inthongtinchitiet1mathang"))
     {
       this.log.info("-------In thong tin chi tiet mot mat hang----------", new Object[0]);
       tenfile = "Inthongtinchitiet1mathang";
     }
     else if (this.reportTypeKC.equals("TKsophieuthuchien"))
     {
       this.log.info("-------Thong ke so phieu thuc hien----------", new Object[0]);
       tenfile = "TKsophieuthuchien";
     }
     else if (this.reportTypeKC.equals("BCkettoanthuocchongtamthan"))
     {
       this.log.info("-------bao cao ket toan chong tam than----------", new Object[0]);
       tenfile = "BCkettoanthuocchongtamthan";
     }
     else if (this.reportTypeKC.equals("Inbanglietkechungtu"))
     {
       this.log.info("-------In bang liet ke chung tu----------", new Object[0]);
       tenfile = "Inbanglietkechungtu";
     }
     else if (this.reportTypeKC.equals("Inbaocaonhapxuattrongngay"))
     {
       this.log.info("-------In bao cao nhap xuat trong ngay----------", new Object[0]);
       tenfile = "Inbaocaonhapxuattrongngay";
     }
     else if (this.reportTypeKC.equals("BCxuattrongngay"))
     {
       this.log.info("-------In bao cao xuat trong ngay----------", new Object[0]);
       tenfile = "BCxuattrongngay";
     }
     else if (this.reportTypeKC.equals("Inchitietnhaphang"))
     {
       this.log.info("-------In chi tiet nhap hang----------", new Object[0]);
       tenfile = "Inchitietnhaphang";
     }
     else if (this.reportTypeKC.equals("Capnhatbangkiemke"))
     {
       this.log.info("-------Cap nhat bang kiem ke----------", new Object[0]);
       tenfile = "Capnhatbangkiemke";
     }
     else if (this.reportTypeKC.equals("inbangkiemke"))
     {
       this.log.info("-------in bang kiem ke----------", new Object[0]);
       tenfile = "inbangkiemke";
     }
     else if (this.reportTypeKC.equals("Tinhtonkhovagiatri"))
     {
       this.log.info("-------Tinh ton kho va gia tri----------", new Object[0]);
       tenfile = "Tinhtonkhovagiatri";
     }
     else if (this.reportTypeKC.equals("InphieuXHBNBHYT"))
     {
       this.log.info("-------InphieuXHBNBHYT----------", new Object[0]);
       tenfile = "InphieuXHBNBHYT";
     }
     else if (this.reportTypeKC.equals("D01_Phieunhapkhothuocthuong"))
     {
       this.log.info("-------D01_Phieunhapkhothuocthuong----------", new Object[0]);
       tenfile = "D01_Phieunhapkhothuocthuong";
     }
     else if (this.reportTypeKC.equals("D03_phieuxuatkho"))
     {
       this.log.info("-------D03_phieuxuatkho----------", new Object[0]);
       tenfile = "D03_phieuxuatkho";
     }
     else if (this.reportTypeKC.equals("D03_phieuxuatkho_khoaphong"))
     {
       this.log.info("-------D03_phieuxuatkho_khoaphong----------", new Object[0]);
       tenfile = "D03_phieuxuatkho_khoaphong";
     }
     else if (this.reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru"))
     {
       this.log.info("-------B4122_XuatHangTheoPhieuDuTru----------", new Object[0]);
       tenfile = "B4122_XuatHangTheoPhieuDuTru";
     }
     else if (this.reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru_Tra"))
     {
       this.log.info("-------B4122_XuatHangTheoPhieuDuTru_Tra----------", new Object[0]);
       tenfile = "B4122_XuatHangTheoPhieuDuTru_Tra";
     }
     else if (this.reportTypeKC.equals("Inbaocaonhapxuathang"))
     {
       this.log.info("-------Inbaocaonhapxuathang----------", new Object[0]);
       tenfile = "Inbaocaonhapxuathang";
     }
     else if (this.reportTypeKC.equals("xuathangdenkhokhac"))
     {
       this.log.info("-------xuathangdenkhokhac----------", new Object[0]);
       tenfile = "xuathangdenkhokhac";
     }
     else if (this.reportTypeKC.equals("phieutrahangtheodutrutra"))
     {
       this.log.info("-------phieutrahangtheodutrutra----------", new Object[0]);
       tenfile = "phieutrahangtheodutrutra";
     }
     else if (this.reportTypeKC.equals("Canhbaoluongtonkho"))
     {
       this.log.info("-------Canhbaoluongtonkho----------", new Object[0]);
       tenfile = "Canhbaoluongtonkho";
     }
     else if (this.reportTypeKC.equals("PhieuXuatTraNhaCungCap"))
     {
       this.log.info("-------PhieuXuatTraNhaCungCap----------", new Object[0]);
       tenfile = "PhieuXuatTraNhaCungCap";
     }
     else if (this.reportTypeKC.equals("D27_phieutraBHYTChoKhoChinh"))
     {
       this.log.info("-------PhieuXuatTraNhaCungCap----------", new Object[0]);
       tenfile = "D27_phieutraBHYTChoKhoChinh";
     }
     else if (this.reportTypeKC.equals("PhieuDuTruTraThuoc"))
     {
       this.log.info("-------B4444_Phieutrahangbhytchokhochinh : PhieuXuatTraNhaCungCap----------", new Object[0]);
       tenfile = "PhieuDuTruTraThuoc";
     }
     else if (this.reportTypeKC.equals("PhieuDuTruLanhThuocCuaKhoLe"))
     {
       this.log.info("-------PhieuDuTruLanhThuocCuaKhoLe----------", new Object[0]);
       tenfile = "PhieuDuTruLanhThuocCuaKhoLe";
     }
     else if (this.reportTypeKC.equals("TraHangKhoChinh"))
     {
       this.log.info("-------TraHangKhoChinh----------", new Object[0]);
       tenfile = "TraHangKhoChinh";
     }
     else if (this.reportTypeKC.equals("PhieuThanhToanKCB"))
     {
       this.log.info("-------PhieuThanhToanKCB----------", new Object[0]);
       tenfile = "PhieuThanhToanKCB";
     }
     else if (this.reportTypeKC.equals("PhieuThanhToanKCB_Thuphi"))
     {
       this.log.info("-------PhieuThanhToanKCB_Thuphi----------", new Object[0]);
       tenfile = "PhieuThanhToanKCB_Thuphi";
     }
     int index = 0;
     String tempStr = null;
     tempStr = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, ketquaPath, this.chonFileXuat.trim(), tenfile);
     setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE, ""));
     this.log.info("duong dan----" + getDuongdanFileXuat(), new Object[0]);
     setReportFinish(true);
   }

   public String troveAction()
   {
     this.log.info("bat dau xuat file" + this.chonFileXuat, new Object[0]);
     String ketquaPath = null;
     String tenfile = null;
     ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/";
     if (this.reportTypeKC.equals("BCnhapxuat1mathang"))
     {
       this.log.info("-------Bao cao tinh hinh nhap xuat mot mat hang-----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang";
     }
     if (this.reportTypeKC.equals("Mathangkhongsd"))
     {
       this.log.info("-------Bao cao mat hang khong su dung-----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangKhongSuDung";
     }
     if (this.reportTypeKC.equals("Canhbaohangganhethan"))
     {
       this.log.info("-------Bao cao mat hang dan het han-----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangGanHetHan";
     }
     if (this.reportTypeKC.equals("Dstonkhokhonghandung"))
     {
       this.log.info("-------Bao cao mat hang khong han dung-----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangTonKhoKhongHanDung";
     }
     if (this.reportTypeKC.equals("Indanhsachtondau"))
     {
       this.log.info("-------In danh sach ton dau-----------", new Object[0]);
       return "QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau";
     }
     if (this.reportTypeKC.equals("tinhhinhkinhphinhap"))
     {
       this.log.info("-------Tinh hinh kinh phi nhap-----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap";
     }
     if (this.reportTypeKC.equals("Inbctiensudungtaikhoa"))
     {
       this.log.info("-------Bao cao tien su dung-----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa";
     }
     if (this.reportTypeKC.equals("Phantichsolieunhapxuat"))
     {
       this.log.info("-------Phan tich so lieu nhap xuat----------", new Object[0]);
       return "";
     }
     if (this.reportTypeKC.equals("Inthephieu"))
     {
       this.log.info("-------In the kho----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
     }
     if (this.reportTypeKC.equals("Inbenhnhanlanhthuoc"))
     {
       this.log.info("-------In benh nhan lanh thuoc----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoBHYT_InDanhSachBenhNhanLanhThuoc";
     }
     if (this.reportTypeKC.equals("Inthongtinchitiet1mathang"))
     {
       this.log.info("-------In thong tin chi tiet mot mat hang----------", new Object[0]);
       return "";
     }
     if (this.reportTypeKC.equals("InphieuXUATTHUOCBNBHYT"))
     {
       this.log.info("-------Thong ke so phieu thuc hien----------", new Object[0]);
       return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
     }
     if (this.reportTypeKC.equals("TKsophieuthuchien"))
     {
       this.log.info("-------Thong ke so phieu thuc hien----------", new Object[0]);
       return "";
     }
     if (this.reportTypeKC.equals("BCkettoanthuocchongtamthan"))
     {
       this.log.info("-------bao cao ket toan chong tam than----------", new Object[0]);
       return "";
     }
     if (this.reportTypeKC.equals("Inbanglietkechungtu"))
     {
       this.log.info("-------In bang liet ke chung tu----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu";
     }
     if (this.reportTypeKC.equals("Inbaocaonhapxuattrongngay"))
     {
       this.log.info("-------In bao cao nhap xuat trong ngay----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay";
     }
     if (this.reportTypeKC.equals("BCxuattrongngay"))
     {
       this.log.info("-------In bao cao xuat trong ngay----------", new Object[0]);
       return "";
     }
     if (this.reportTypeKC.equals("Inchitietnhaphang"))
     {
       this.log.info("-------In chi tiet nhap hang----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang";
     }
     if (this.reportTypeKC.equals("Inbangkiemke"))
     {
       this.log.info("-------In bang kiem ke----------", new Object[0]);
       return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
     }
     if (this.reportTypeKC.equals("Tinhtonkhovagiatri"))
     {
       this.log.info("-------Tinh ton kho va gia tri----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri";
     }
     if (this.reportTypeKC.equals("InphieuXHBNBHYT"))
     {
       this.log.info("-------InphieuXHBNBHYT----------", new Object[0]);

       return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
     }
     if (this.reportTypeKC.equals("D01_Phieunhapkhothuocthuong"))
     {
       this.log.info("-------D01_Phieunhapkhothuocthuong----------", new Object[0]);
       return "QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap";
     }
     if (this.reportTypeKC.equals("D03_phieuxuatkho"))
     {
       this.log.info("-------D03_phieuxuatkho----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
     }
     if (this.reportTypeKC.equals("D03_phieuxuatkho_khoaphong"))
     {
       this.log.info("-------D03_phieuxuatkho_khoaphong----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
     }
     if (this.reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru"))
     {
       this.log.info("-------B4122_XuatHangTheoPhieuDuTru----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_XuatHangTheoPhieuDuTru";
     }
     if (this.reportTypeKC.equals("B4164_Phieuxuathangtutruc"))
     {
       this.log.info("-------B4164_Phieuxuathangtutruc----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTruc";
     }
     if (this.reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru_Tra"))
     {
       this.log.info("-------B4122_XuatHangTheoPhieuDuTru_Tra----------", new Object[0]);
       return "B4122_XuatHangTheoPhieuDuTru_Tra";
     }
     if (this.reportTypeKC.equals("Inbaocaonhapxuathang"))
     {
       this.log.info("-------Inbaocaonhapxuathang----------", new Object[0]);
       return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang";
     }
     if (this.reportTypeKC.equals("xuathangdenkhokhac"))
     {
       this.log.info("-------xuathangdenkhokhac----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
     }
     if (this.reportTypeKC.equals("TraHangKhoChinh"))
     {
       this.log.info("-------TraHangKhoChinh----------", new Object[0]);
       return "QuanLyKhoChinh_NhapKhoChinh_KhoLeTraHang";
     }
     if (this.reportTypeKC.equals("phieutrahangtheodutrutra"))
     {
       this.log.info("-------phieutrahangtheodutrutra----------", new Object[0]);
       return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTheoPhieuDuTru";
     }
     if (this.reportTypeKC.equals("phieutrahangcuatutruc"))
     {
       this.log.info("-------phieutrahangcuatutruc----------", new Object[0]);
       return "QuanLyKhoChinh_XuatKhoChinh_TraHangCuaTuTruc";
     }
     if (this.reportTypeKC.equals("Canhbaoluongtonkho"))
     {
       this.log.info("-------Canhbaoluongtonkho----------", new Object[0]);
       return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_Canhbaoluongtonkho";
     }
     if (this.reportTypeKC.equals("PhieuXuatTraNhaCungCap"))
     {
       this.log.info("-------PhieuXuatTraNhaCungCap----------", new Object[0]);
       return "Tranhacungcap";
     }
     if (this.reportTypeKC.equals("D27_phieutraBHYTChoKhoChinh"))
     {
       this.log.info("-------D27_phieutraBHYTChoKhoChinh----------", new Object[0]);
       return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
     }
     if (this.reportTypeKC.equals("PhieuDuTruTraThuoc"))
     {
       this.log.info("-------B4444_Phieutrahangbhytchokhochinh: PhieuDuTruTraThuoc----------", new Object[0]);
       return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
     }
     if (this.reportTypeKC.equals("PhieuDuTruLanhThuocCuaKhoLe"))
     {
       this.log.info("-------PhieuDuTruLanhThuocCuaKhoLe----------", new Object[0]);
       return "QuanLyKhoChinh_NhapKhoChinh_DuTruLanhThuoc";
     }
     if (this.reportTypeKC.equals("PhieuThanhToanKCB"))
     {
       this.log.info("-------PhieuThanhToanKCB----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
     }
     if (this.reportTypeKC.equals("PhieuThanhToanKCB_Thuphi"))
     {
       this.log.info("-------PhieuThanhToanKCB_Thuphi----------", new Object[0]);
       return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
     }
     if (this.reportTypeKC.equals("D03_phieutrakhoTD"))
     {
       this.log.info("-------D03_phieutrakhoTD----------", new Object[0]);
       return "QuanLyKhoChinh_NhapKhoChinh_TraHangTuyenDuoi";
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

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.ChonXuatFileBaoCao_KhoChinh

 * JD-Core Version:    0.7.0.1

 */