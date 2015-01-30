 package com.iesvn.yte.report;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.util.HashMap;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;

 public class XuatReportDuocPham
 {
   public JasperPrint jasperPrintKC;
   public String reportPathKC;
   public String reportTypeKC;

   public void XuatReportPhieuDuTruTuTruc(Logger log, PhieuDuTru phieuDuTru, int index, String loaiPhieu)
   {
     log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       if (loaiPhieu.equals("GN")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_02GayNghien.jrxml";
       } else if (loaiPhieu.equals("HT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_02HTT.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_02.jrxml";
       }
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       if ((phieuDuTru.getPhieudtPhanBiet().intValue() == 0) || (phieuDuTru.getPhieudtPhanBiet().intValue() == 1))
       {
         if (loaiPhieu.equals("GN")) {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_GN);
         } else if (loaiPhieu.equals("HT")) {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HT);
         } else if (loaiPhieu.equals("YC")) {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC);
         } else if (loaiPhieu.equals("HC")) {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC);
         } else {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
         }
         params.put("khoa", phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
       }
       else
       {
         if (loaiPhieu.equals("GN"))
         {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC_GN);
         }
         else if (loaiPhieu.equals("HT"))
         {
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC_HT);
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_06.jrxml";
           params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
         }
         params.put("khoa", phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file templete 5" + pathTemplate);

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieudtNgay()));
       params.put("maphieu", phieuDuTru.getPhieudtMa());
       log.info("Ma phieu :" + phieuDuTru.getPhieudtMa());

       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", this.reportTypeKC);
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public void XuatReportPhieuDuTruKhoaPhong(Logger log, PhieuDuTru phieuDuTru, int index, String loaiPhieu)
   {
     log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       if (loaiPhieu.equals("GN")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_04GayNghien.jrxml";
       } else if (loaiPhieu.equals("HT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_04HTT.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_04.jrxml";
       }
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       if ((phieuDuTru.getPhieudtPhanBiet().intValue() == 0) || (phieuDuTru.getPhieudtPhanBiet().intValue() == 1))
       {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
         log.info(IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
       }
       else
       {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
         log.info(IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
       }
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuDuTru.getDmkhoaMaso().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
       params.put("khoa", khoa.getDmkhoaTen());

       params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieudtNgay()));
       params.put("maphieu", phieuDuTru.getPhieudtMa());
       log.info("Ma phieu :" + phieuDuTru.getPhieudtMa());

       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", this.reportTypeKC);
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public void XuatReportPhieuDuTruKhoLeTraThuoc(Logger log, PhieuTraKho phieuTra, int index, String loaiPhieu)
   {
     log.info("Vao Method XuatReport XuatReportPhieuDuTruKhoLeTraThuoc");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       if (loaiPhieu.equals("GN")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_05GayNghien.jrxml";
       } else if (loaiPhieu.equals("HT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_05HTT.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_05.jrxml";
       }
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();











       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuTra.getDmkhoaTra(true).getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
       params.put("khoa", khoa.getDmkhoaTen());
       params.put("ngayXuat", Utils.reFactorString(phieuTra.getPhieutrakhoNgay()));
       params.put("maphieu", phieuTra.getPhieutrakhoMa());
       log.info("Ma phieu :" + phieuTra.getPhieutrakhoMa());

       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", this.reportTypeKC);
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public void XuatReportPhieuDuTruKhoLeLanhThuoc(Logger log, PhieuXuatKho phieuDuTru, int index, String loaiPhieu)
   {
     log.info("Vao Method XuatReport XuatReportPhieuDuTruKhoLeLanhThuoc");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       log.info("bat dau method XuatReport ");
       if (loaiPhieu.equals("GN")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_GayNghien.jrxml";
       } else if (loaiPhieu.equals("HT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_HTT.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieudutru_03.jrxml";
       }
       log.info("da thay file template 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuDuTru.getDmkhoaNhan().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
       params.put("khoa", khoa.getDmkhoaTen());
       params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieuxuatkhoNgaylap()));
       params.put("maphieu", phieuDuTru.getPhieuxuatkhoMa());
       log.info("Ma phieu :" + phieuDuTru.getPhieuxuatkhoMa());

       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", this.reportTypeKC);
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.report.XuatReportDuocPham

 * JD-Core Version:    0.7.0.1

 */