 package com.iesvn.yte.dieutri.tiepdon.action;

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

 @Name("ChonXuatFileBaoCao_TiepDon")
 @Scope(ScopeType.SESSION)
 public class ChonXuatFileBaoCao_TiepDon
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD;
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
       JasperPrintManager.printReport(this.jasperPrintTD, true);
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
     ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/";
     if (this.reportTypeTD.equals("Baocaothongketainanthuongtich"))
     {
       this.log.info("-------Bao cao thong ke tai nan thuong tich-----------", new Object[0]);
       tenfile = "Baocaothongketainanthuongtich";
     }
     else if (this.reportTypeTD.equals("Phantichsolieuthuchingoaitru"))
     {
       this.log.info("-------Bao cao Phan tich so lieu thu chi ngoai tru-----------", new Object[0]);
       tenfile = "Phantichsolieuthuchingoaitru";
     }
     else if (this.reportTypeTD.equals("Phantichchiphidieutritaiccl"))
     {
       this.log.info("-------Bao cao Phan tich chi phi dieu tri tai ccl-----------", new Object[0]);
       tenfile = "Phantichchiphidieutritaiccl";
     }
     else if (this.reportTypeTD.equals("CanLamSanPhauThuat"))
     {
       this.log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------", new Object[0]);
       tenfile = "CanLamSanPhauThuat";
     }
     else if (this.reportTypeTD.equals("XuTriThuocTaiBanKham"))
     {
       this.log.info("-------Vao Method XuatReport bao cao xu tri thuoc tai ban kham-----------", new Object[0]);
       tenfile = "XuTriThuocTaiBanKham";
     }
     else if (this.reportTypeTD.equals("KeToaVe"))
     {
       this.log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------", new Object[0]);
       tenfile = "ketoave";
     }
     else if (this.reportTypeTD.equals("Tonghopthuocvtthdadung"))
     {
       this.log.info("-------Vao Method XuatReport tong hop thuoc vtth da dung-----------", new Object[0]);
       tenfile = "Tonghopthuocvtthdadung";
     }
     else if (this.reportTypeTD.equals("Insoluutru"))
     {
       this.log.info("-------Vao Method XuatReport In so luu tru-----------", new Object[0]);
       tenfile = "Insoluutru";
     }
     else if (this.reportTypeTD.equals("indsbnkhamthutien"))
     {
       this.log.info("-------Vao Method XuatReport In benh nhan kham thu tien-----------", new Object[0]);
       tenfile = "indsbnkhamthutien";
     }
     else if (this.reportTypeTD.equals("indsbndkonline"))
     {
       this.log.info("-------Vao Method XuatReport In BN dang ky Online ----------", new Object[0]);
       tenfile = "indsbndkonline";
     }
     else if (this.reportTypeTD.equals("ptsolieuclsngoaitru"))
     {
       this.log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------", new Object[0]);
       tenfile = "ptsolieuclsngoaitru";
     }
     else if (this.reportTypeTD.equals("B123_Inphieulinhthuoc"))
     {
       this.log.info("-------Vao Method XuatReport B123_Inphieulinhthuoc-----------", new Object[0]);
       tenfile = "B123_Inphieulinhthuoc";
     }
     else if (this.reportTypeTD.equals("ThamKhamVaXuTri"))
     {
       this.log.info("-------Vao Method ThamKhamVaXuTri B123_Inphieulinhthuoc-----------", new Object[0]);
       tenfile = "ThamKhamVaXuTri";
     }
     else if (this.reportTypeTD.equals("B141_Phantichbenhnhankhambenh"))
     {
       this.log.info("-------Vao Method  B141_Phantichbenhnhankhambenh-----------", new Object[0]);
       tenfile = "B141_Phantichbenhnhankhambenh";
     }
     else if (this.reportTypeTD.equals("B135_Denghitamungthanhtoan"))
     {
       this.log.info("-------Vao Method  B135_Denghitamungthanhtoan-----------", new Object[0]);
       tenfile = "B135_Denghitamungthanhtoan";
     }
     else if (this.reportTypeTD.equals("indanhsachbenhnhancapcuu"))
     {
       this.log.info("-------Vao Method  indanhsachbenhnhancapcuu-----------", new Object[0]);
       tenfile = "indanhsachbenhnhancapcuu";
     }
     else if (this.reportTypeTD.equals("Insoluutrubhytthuphi"))
     {
       this.log.info("-------Vao Method  Insoluutrubhytthuphi-----------", new Object[0]);
       tenfile = "Insoluutrubhytthuphi";
     }
     else if (this.reportTypeTD.equals("benhanvaovien"))
     {
       this.log.info("-------Vao Method  benhanvaovien-----------", new Object[0]);
       tenfile = "benhanvaovien";
     }
     else if (this.reportTypeTD.equals("benhantuvongtruockhivaovien"))
     {
       this.log.info("-------Vao Method  benhantuvongtruockhivaovien-----------", new Object[0]);
       tenfile = "benhantuvongtruockhivaovien";
     }
     else if (this.reportTypeTD.equals("giaychungthuong"))
     {
       this.log.info("-------Vao Method  giaychungthuong-----------", new Object[0]);
       tenfile = "giaychungthuong";
     }
     else if (this.reportTypeTD.equals("giaychuyenviennguoibenhcobhyt"))
     {
       this.log.info("-------Vao Method  giaychuyenviennguoibenhcobhyt-----------", new Object[0]);
       tenfile = "giaychuyenviennguoibenhcobhyt";
     }
     else if (this.reportTypeTD.equals("thamkhamdieutringoaitru"))
     {
       this.log.info("-------Vao Method  thamkhamdieutringoaitru-----------", new Object[0]);
       tenfile = "thamkhamdieutringoaitru";
     }
     else if (this.reportTypeTD.equals("giaychungnhan"))
     {
       this.log.info("-------Vao Method benhanvaovien-----------", new Object[0]);
       tenfile = "giaychungnhan";
     }
     else if (this.reportTypeTD.equals("phieukhamchuyenkhoa"))
     {
       this.log.info("-------Vao Method  phieukhamchuyenkhoa-----------", new Object[0]);
       tenfile = "phieukhamchuyenkhoa";
     }
     else if (this.reportTypeTD.equals("phieukhambenhvaovien"))
     {
       this.log.info("-------Vao Method  phieukhambenhvaovien-----------", new Object[0]);
       tenfile = "phieukhambenhvaovien";
     }
     else if (this.reportTypeTD.equals("giaychungnhansuckhoe"))
     {
       this.log.info("-------Vao Method  giaychungnhansuckhoe-----------", new Object[0]);
       tenfile = "giaychungnhansuckhoe";
     }
     else if (this.reportTypeTD.equals("ketquaphatmau"))
     {
       this.log.info("-------Vao Method  ketquaphatmau-----------", new Object[0]);
       tenfile = "ketquaphatmau";
     }
     else if (this.reportTypeTD.equals("CapNhatThuocBNSD"))
     {
       this.log.info("-------Vao Method  CapNhatThuocBNSD-----------", new Object[0]);
       tenfile = "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
     }
     int index = 0;
     String tempStr = null;
     tempStr = XuatReportUtil.ReportUtil(this.jasperPrintTD, index, ketquaPath, this.chonFileXuat.trim(), tenfile);
     setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE, ""));
     this.log.info("duong dan----" + getDuongdanFileXuat(), new Object[0]);
     setReportFinish(true);
   }

   public String troveAction()
   {
     if (this.reportTypeTD.equals("Baocaothongketainanthuongtich"))
     {
       this.log.info("-------Bao cao thong ke tai nan thuong tich-----------", new Object[0]);
       return "";
     }
     if (this.reportTypeTD.equals("Phantichsolieuthuchingoaitru"))
     {
       this.log.info("-------Bao cao Phan tich so lieu thu chi ngoai tru-----------", new Object[0]);
       return "TiepDon_PhanTichBaoCao_PhanTichSoLieuThuChiNgoaiTru";
     }
     if (this.reportTypeTD.equals("Phantichchiphidieutritaiccl"))
     {
       this.log.info("-------Bao cao Phan tich chi phi dieu tri tai ccl-----------", new Object[0]);
       return "";
     }
     if (this.reportTypeTD.equals("CanLamSanPhauThuat"))
     {
       this.log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------", new Object[0]);
       return "clsthuthat";
     }
     if (this.reportTypeTD.equals("XuTriThuocTaiBanKham"))
     {
       this.log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------", new Object[0]);
       return "xutrithuoctaibankham";
     }
     if (this.reportTypeTD.equals("KeToaVe"))
     {
       this.log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------", new Object[0]);
       return "ketoave";
     }
     if (this.reportTypeTD.equals("Tonghopthuocvtthdadung"))
     {
       this.log.info("-------Vao Method XuatReport tong hop thuoc vtth da dung-----------", new Object[0]);
       return "TiepDon_KhamBenh_TongHopThuocVTTHDaDung";
     }
     if (this.reportTypeTD.equals("Insoluutru"))
     {
       this.log.info("-------Vao Method XuatReport In so luu tru-----------", new Object[0]);
       return "TiepDon_PhanTichBaoCao_InSoLuuTru";
     }
     if (this.reportTypeTD.equals("indsbnkhamthutien"))
     {
       this.log.info("-------Vao Method XuatReport In benh nhan kham thu tien-----------", new Object[0]);
       return "TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien";
     }
     if (this.reportTypeTD.equals("indsbndkonline"))
     {
       this.log.info("-------Vao Method XuatReport In BN dang ky Online -----------", new Object[0]);
       return "TiepDon_TiepDonBenhNhan_InDSBNDKOnline";
     }
     if (this.reportTypeTD.equals("ptsolieuclsngoaitru"))
     {
       this.log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------", new Object[0]);
       return "TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan";
     }
     if (this.reportTypeTD.equals("B123_Inphieulinhthuoc"))
     {
       this.log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------", new Object[0]);
       return "TiepDon_KhamBenh_InPhieuLinhThuoc";
     }
     if (this.reportTypeTD.equals("ThamKhamVaXuTri"))
     {
       this.log.info("-------Vao Method ThamKhamVaXuTri B123_Inphieulinhthuoc-----------", new Object[0]);
       return "ghinhan";
     }
     if (this.reportTypeTD.equals("B141_Phantichbenhnhankhambenh"))
     {
       this.log.info("-------Vao Method B141_Phantichbenhnhankhambenh-----------", new Object[0]);
       return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh";
     }
     if (this.reportTypeTD.equals("B135_Denghitamungthanhtoan"))
     {
       this.log.info("-------Vao Method B135_Denghitamungthanhtoan-----------", new Object[0]);
       return "TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan";
     }
     if (this.reportTypeTD.equals("indanhsachbenhnhancapcuu"))
     {
       this.log.info("-------Vao Method indanhsachbenhnhancapcuu-----------", new Object[0]);
       return "TiepDon_TiepDonKhamBenhCapCuu_InDanhSachBenhNhanCapCuu";
     }
     if (this.reportTypeTD.equals("Insoluutrubhytthuphi"))
     {
       this.log.info("-------Vao Method Insoluutrubhytthuphi-----------", new Object[0]);
       return "TiepDon_PhanTichBaoCao_InSoLuuTruBHYTThuPhi";
     }
     if (this.reportTypeTD.equals("benhanvaovien"))
     {
       this.log.info("-------Vao Method benhanvaovien-----------", new Object[0]);
       return "lapbenhanngoaitru";
     }
     if (this.reportTypeTD.equals("benhantuvongtruockhivaovien"))
     {
       this.log.info("-------Vao Method benhantuvongtruockhivaovien-----------", new Object[0]);
       return "lapbatuvongtruockhivaovien";
     }
     if (this.reportTypeTD.equals("giaychungthuong"))
     {
       this.log.info("-------Vao giaychungthuong-----------", new Object[0]);
       return "giaychungthuong";
     }
     if (this.reportTypeTD.equals("giaychuyenviennguoibenhcobhyt"))
     {
       this.log.info("-------Vao Method giaychuyenviennguoibenhcobhyt-----------", new Object[0]);
       return "ChuyenVienNguoiBenhCoTheBHYT";
     }
     if (this.reportTypeTD.equals("thamkhamdieutringoaitru"))
     {
       this.log.info("-------Vao Method thamkhamdieutringoaitru-----------", new Object[0]);
       return "ThamKhamDieuTriNgoaiTru";
     }
     if (this.reportTypeTD.equals("phieukhamchuyenkhoa"))
     {
       this.log.info("-------Vao Method phieukhamchuyenkhoa-----------", new Object[0]);
       return "PhieuKhamChuyenKhoa";
     }
     if (this.reportTypeTD.equals("phieukhambenhvaovien"))
     {
       this.log.info("-------Vao Method phieukhambenhvaovien-----------", new Object[0]);
       return "PhieuKhamBenhVaoVien";
     }
     if (this.reportTypeTD.equals("giaychungnhansuckhoe"))
     {
       this.log.info("-------Vao Method giaychungnhansuckhoe-----------", new Object[0]);
       return "GiayChungNhanSucKhoe";
     }
     if (this.reportTypeTD.equals("giaychungnhan"))
     {
       this.log.info("-------Vao Method benhanvaovien-----------", new Object[0]);
       return "giaychungnhan";
     }
     if (this.reportTypeTD.equals("ketquaphatmau"))
     {
       this.log.info("-------Vao Method ketquaphatmau-----------", new Object[0]);
       return "capNhatKetQuaCLS";
     }
     if (this.reportTypeTD.equals("CapNhatThuocBNSD"))
     {
       this.log.info("-------Vao Method  CapNhatThuocBNSD-----------", new Object[0]);
       return "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
     }
     return "";
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

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ChonXuatFileBaoCao_TiepDon

 * JD-Core Version:    0.7.0.1

 */