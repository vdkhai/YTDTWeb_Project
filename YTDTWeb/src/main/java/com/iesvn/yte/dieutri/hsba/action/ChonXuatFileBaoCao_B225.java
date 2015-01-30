 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import net.sf.jasperreports.engine.JasperPrint;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("ChonXuatFileBaoCaoB225")
 @Scope(ScopeType.SESSION)
 public class ChonXuatFileBaoCao_B225
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPath;
   @Out(required=false)
   @In(required=false)
   private String reportPath5b;
   @Out(required=false)
   @In(required=false)
   private String reportType;
   @Out(required=false)
   @In(required=false)
   JasperPrint jPrint;
   @Out(required=false)
   @In(required=false)
   JasperPrint jPrint1;
   private String chonFileXuat = null;
   private boolean reportFinish = false;
   private String duongdanFileXuat = null;
   private String duongdanFileXuat5b = null;

   @Create
   public void init()
   {
     setChonFileXuat("DOC");
   }

   public void xuatFileAction()
   {
     this.log.info("bat dau xuat file" + this.chonFileXuat, new Object[0]);
     String ketquaPath = null;
     String tenfile = null;
     if (this.reportType.equals("DSBNPhauthuatThuThuat"))
     {
       this.log.info("-------Bao cao tinh hinh benh tat tu vong-----------", new Object[0]);
       ketquaPath = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/";
     }
     int index = 0;
     int index5b = 0;
     String tempStr = null;
     String tempStr5b = null;
     tempStr = XuatReportUtil.ReportUtil(this.jPrint, index, ketquaPath, this.chonFileXuat.trim(), "Danhsachbenhnhanphauthuat");
     tempStr5b = XuatReportUtil.ReportUtil(this.jPrint1, index, ketquaPath, this.chonFileXuat.trim(), "Danhsachbenhnhanphauthuat5b");
     setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE, ""));
     setDuongdanFileXuat5b(tempStr5b.replaceFirst(IConstantsRes.PATH_BASE, ""));
     this.log.info("duong dan----" + getDuongdanFileXuat(), new Object[0]);
     this.log.info("duong dan----5b" + getDuongdanFileXuat5b(), new Object[0]);
     setReportFinish(true);
   }

   public String TroveAction()
   {
     return "DieuTri_BaoCaoHoatDongDieuTri_DanhSachPhauThuatThuThuat";
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

   public String getDuongdanFileXuat5b()
   {
     return this.duongdanFileXuat5b;
   }

   public void setDuongdanFileXuat5b(String duongdanFileXuat5b)
   {
     this.duongdanFileXuat5b = duongdanFileXuat5b;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.ChonXuatFileBaoCao_B225

 * JD-Core Version:    0.7.0.1

 */