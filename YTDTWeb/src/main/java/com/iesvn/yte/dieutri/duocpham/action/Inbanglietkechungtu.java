 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B4418_Inbanglietkechungtu")
 @Scope(ScopeType.SESSION)
 public class Inbanglietkechungtu
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4418 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private Integer lthuoc_maso = null;
   private Integer kho_maso = null;
   private String lthuoc_ma = "";
   private String kho_ma = "";
   private String inTheo = null;

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     initresetFormB4418();
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu";
   }

   @End
   public void end() {}

   @Factory("resetFormB4418")
   public void initresetFormB4418()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4418 = "";
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.resetFormB4418 = "";
     setInTheo("r1");
     setLthuoc_ma("");
     setKho_ma("");
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Inbanglietkechungtu";
     this.log.info("Vao Method XuatReport in bang liet ke chung tu", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.inTheo.equals("r1")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D25_banlietkechungtunhapkho.jrxml";
       } else if (this.inTheo.equals("r2")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D26_banglietkechungtuxuatkho.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       this.log.info("tu ngay date " + this.tungay, new Object[0]);
       params.put("TUNGAY", sdf.parse(this.tungay));
       this.log.info("den ngay date " + this.denngay, new Object[0]);
       params.put("DENNGAY", sdf.parse(this.denngay));

       this.log.info("truyen tham so loai thuoc " + this.lthuoc_maso, new Object[0]);
       if (!this.lthuoc_ma.equals(""))
       {
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
         DmLoaiThuoc loai = new DmLoaiThuoc();
         Object obj = dtutilDelegate.findByMa(this.lthuoc_ma, "DmLoaiThuoc", "dmloaithuocMa");
         loai = (DmLoaiThuoc)obj;
         params.put("LOAITHUOCTEN", loai.getDmloaithuocTen());
       }
       else
       {
         params.put("LOAITHUOCTEN", "Táº¥t cáº£");
       }
       this.log.info("truyen tham so  khoa " + this.kho_maso, new Object[0]);
       if (!this.kho_ma.equals("")) {
         params.put("KHOMASO", this.kho_maso);
       }
       this.log.info("================= ", new Object[0]);
       Class.forName("com.mysql.jdbc.Driver");
       this.log.info("da thay driver mysql", new Object[0]);
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         this.log.info(e.getMessage(), new Object[0]);
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Inbanglietkechungtu");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
       this.index += 1;
       this.log.info("Index :" + getIndex(), new Object[0]);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public Integer getLthuoc_maso()
   {
     return this.lthuoc_maso;
   }

   public void setLthuoc_maso(Integer lthuoc_maso)
   {
     this.lthuoc_maso = lthuoc_maso;
   }

   public Integer getKho_maso()
   {
     return this.kho_maso;
   }

   public void setKho_maso(Integer kho_maso)
   {
     this.kho_maso = kho_maso;
   }

   public String getLthuoc_ma()
   {
     return this.lthuoc_ma;
   }

   public void setLthuoc_ma(String lthuoc_ma)
   {
     this.lthuoc_ma = lthuoc_ma;
   }

   public String getKho_ma()
   {
     return this.kho_ma;
   }

   public void setKho_ma(String kho_ma)
   {
     this.kho_ma = kho_ma;
   }

   public String getInTheo()
   {
     return this.inTheo;
   }

   public void setInTheo(String inTheo)
   {
     this.inTheo = inTheo;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inbanglietkechungtu

 * JD-Core Version:    0.7.0.1

 */