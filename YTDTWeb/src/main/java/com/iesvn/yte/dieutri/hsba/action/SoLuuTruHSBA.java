 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.entity.DmKhoa;
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
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B238_Soluutrubenhan")
 @Scope(ScopeType.CONVERSATION)
 public class SoLuuTruHSBA
   implements Serializable
 {
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   private int index = 0;
   private String soluutru_tu = "";
   private String soluutru_den = "";
   private String inTheo;
   private Integer masoKhoa;
   private String maKhoa;
   private List ttIn;

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     return "DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn";
   }

   @End
   public void end() {}

   public void loadSoVaoVienTu()
   {
     this.log.info("-----So vao vien-----" + this.soluutru_tu, new Object[0]);
     if ((this.soluutru_tu != null) && (!this.soluutru_tu.equals(""))) {
       try
       {
         HsbaNopDelegate hsbaNopDAO = HsbaNopDelegate.getInstance();
         HsbaNop hsbaNopTmp = hsbaNopDAO.findBySoLuuTru(this.soluutru_tu);
         if (hsbaNopTmp == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru_tu });
           this.soluutru_tu = "";
           return;
         }
         this.soluutru_tu = hsbaNopTmp.getHsbanopSoluutru();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru_tu });
         e.printStackTrace();
       }
     }
   }

   public void loadSoVaoVienDen()
   {
     this.log.info("-----So vao vien-----" + this.soluutru_den, new Object[0]);
     if ((this.soluutru_den != null) && (!this.soluutru_den.equals(""))) {
       try
       {
         HsbaNopDelegate hsbaNopDAO = HsbaNopDelegate.getInstance();
         HsbaNop hsbaNopTmp = hsbaNopDAO.findBySoLuuTru(this.soluutru_den);
         if (hsbaNopTmp == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru_den });
           this.soluutru_den = "";
           return;
         }
         this.soluutru_den = hsbaNopTmp.getHsbanopSoluutru();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru_den });
         e.printStackTrace();
       }
     }
   }

   public void resetForm()
   {
     this.soluutru_den = "";
     this.soluutru_tu = "";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "soluutruhsba";
     this.log.info("Vao Method XuatReport soluutruhsba", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ("1".equals(this.inTheo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/soluutruhsba.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/soluutruhsba_tuvong.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       if (!this.soluutru_tu.trim().equals("")) {
         params.put("SOBATU", this.soluutru_tu.trim());
       }
       if (!this.soluutru_den.trim().equals("")) {
         params.put("SOBADEN", this.soluutru_den.trim());
       }
       if ((this.maKhoa != null) && (!this.maKhoa.equals("")))
       {
         params.put("MASOKHOA", this.masoKhoa);
         DieuTriUtilDelegate dtUtilsDe = DieuTriUtilDelegate.getInstance();
         DmKhoa khoa = (DmKhoa)dtUtilsDe.findByMa(this.maKhoa, "DmKhoa", "");
         if (khoa != null) {
           params.put("TENKHOA", khoa.getDmkhoaTen());
         }
       }
       else
       {
         params.put("MASOKHOA", null);
         params.put("TENKHOA", null);
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "soluutruhsba");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.duongdanStrDT, new Object[0]);
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

   public String getSoluutru_tu()
   {
     return this.soluutru_tu;
   }

   public void setSoluutru_tu(String soluutru_tu)
   {
     this.soluutru_tu = soluutru_tu;
   }

   public String getSoluutru_den()
   {
     return this.soluutru_den;
   }

   public void setSoluutru_den(String soluutru_den)
   {
     this.soluutru_den = soluutru_den;
   }

   public List<SelectItem> getTtIn()
   {
     if (this.ttIn == null)
     {
       this.ttIn = new ArrayList();
       this.ttIn.add(new SelectItem(Integer.valueOf(1), IConstantsRes.so_luu_tru_hsba));
       this.ttIn.add(new SelectItem(Integer.valueOf(2), IConstantsRes.so_luu_tru_hsba_tu_vong));
     }
     return this.ttIn;
   }

   public String getDuongdanStrDT()
   {
     return this.duongdanStrDT;
   }

   public void setDuongdanStrDT(String duongdanStrDT)
   {
     this.duongdanStrDT = duongdanStrDT;
   }

   public String getInTheo()
   {
     return this.inTheo;
   }

   public void setInTheo(String inTheo)
   {
     this.inTheo = inTheo;
   }

   public Integer getMasoKhoa()
   {
     return this.masoKhoa;
   }

   public void setMasoKhoa(Integer masoKhoa)
   {
     this.masoKhoa = masoKhoa;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public void setTtIn(List ttIn)
   {
     this.ttIn = ttIn;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.SoLuuTruHSBA

 * JD-Core Version:    0.7.0.1

 */