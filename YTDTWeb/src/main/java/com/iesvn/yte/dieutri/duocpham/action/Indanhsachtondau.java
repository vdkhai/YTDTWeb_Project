 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.faces.model.SelectItem;
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

 @Name("B4142_Indanhsachtondau")
 @Scope(ScopeType.CONVERSATION)
 public class Indanhsachtondau
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
   private String resetFormB4142 = null;
   private int index = 0;
   private List<SelectItem> ttIn = null;
   private Integer inTheo = null;
   private Integer loaihang_maso = null;
   private Integer khoa_maso = null;
   private String loaihang_ma = "";
   private String khoa_ma = "";
   String dmKhoXuat = "";

   @Factory("resetFormB4142")
   public void initresetFormB4142()
   {
     this.log.info("init() B4142", new Object[0]);
     resetForm();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.log.info("init()", new Object[0]);
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau";
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4142 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     setLoaihang_ma("");
     this.resetFormB4142 = "";
     setInTheo(new Integer(1));
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Indanhsachtondau";
     this.log.info("Vao Method XuatReport cap nhat ton dau", new Object[0]);
     this.log.info("loai hang " + this.loaihang_ma, new Object[0]);
     this.log.info("khoa ma  " + this.khoa_ma, new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.inTheo.intValue() == 1) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D12_solieutondau_intachrieng.jrxml";
       } else if (this.inTheo.intValue() == 2) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D12_solieutondau_ingop.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);

       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       if (this.loaihang_ma.trim().equals(""))
       {
         setLoaihang_ma(null);
       }
       else
       {
         DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
         Object objLThuoc = dtUtils.findByMa(this.loaihang_ma, "DmLoaiThuoc", "dmloaithuocMa");
         dmloaithuoc = (DmLoaiThuoc)objLThuoc;
         params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
         params.put("LOAITHUOCMASO", this.loaihang_maso);
       }
       if (!this.khoa_ma.trim().equals(""))
       {
         this.log.info("khoa ma so" + this.khoa_maso, new Object[0]);
         DmKhoa dmkhoa = new DmKhoa();
         Object obj = dtUtils.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         dmkhoa = (DmKhoa)obj;
         params.put("KHO", dmkhoa.getDmkhoaTen());
         params.put("KHOMASO", this.khoa_maso);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "solieutondau");
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public List<SelectItem> getTtIn()
   {
     if (this.ttIn == null)
     {
       this.ttIn = new ArrayList();
       this.ttIn.add(new SelectItem(Integer.valueOf(1), IConstantsRes.In_Tach_Rieng));
       this.ttIn.add(new SelectItem(Integer.valueOf(2), IConstantsRes.In_Gop_Ma_NCT_NSX));
     }
     return this.ttIn;
   }

   public void setTtIn(List<SelectItem> ttIn)
   {
     this.ttIn = ttIn;
   }

   public Integer getInTheo()
   {
     return this.inTheo;
   }

   public void setInTheo(Integer inTheo)
   {
     this.inTheo = inTheo;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Indanhsachtondau

 * JD-Core Version:    0.7.0.1

 */