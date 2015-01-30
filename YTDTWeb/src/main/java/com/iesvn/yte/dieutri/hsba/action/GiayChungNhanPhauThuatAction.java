 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayChungNhanPhauThuatDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhanPhauThuat;
 import com.iesvn.yte.dieutri.entity.HsbaMo;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B232_Giaychungnhanphauthuat")
 @Synchronized(timeout=6000000L)
 public class GiayChungNhanPhauThuatAction
   implements Serializable
 {
   private static final long serialVersionUID = 6112910832819510626L;
   private static Logger log = Logger.getLogger(GiayChungNhanPhauThuatAction.class);
   private HsbaGiayChungNhanPhauThuat cpt;
   private HsbaMo hsbaMo;
   private String ngayCap;
   private String ngayTk;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private boolean isUpdate;
   private String crrDate;
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
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";

   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_GiayChungNhanPhauThuat";
   }

   @End
   public void endFunc() {}

   public HsbaGiayChungNhanPhauThuat getCpt()
   {
     return this.cpt;
   }

   public void setCpt(HsbaGiayChungNhanPhauThuat cpt)
   {
     this.cpt = cpt;
   }

   public HsbaMo getHsbaMo()
   {
     return this.hsbaMo;
   }

   public void setHsbaMo(HsbaMo hsbaMo)
   {
     this.hsbaMo = hsbaMo;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNofoundHSBA()
   {
     return this.nofoundHSBA;
   }

   public void setNofoundHSBA(String nofoundHSBA)
   {
     this.nofoundHSBA = nofoundHSBA;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.cpt = new HsbaGiayChungNhanPhauThuat();
     this.hsbaMo = new HsbaMo();
     this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     this.ngayTk = "";
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
     this.isUpdate = false;
   }

   public void displayGiayCNPT()
   {
     log.info("---displayGiayRaVien");
     String maCpt = this.cpt.getHsbagcnptMa().trim();
     HsbaGiayChungNhanPhauThuat cpt_tmp = null;
     if (!maCpt.equals(""))
     {
       cpt_tmp = HsbaGiayChungNhanPhauThuatDelegate.getInstance().findByHsbagcnptMa(maCpt);
       if (cpt_tmp == null)
       {
         this.nofound = "true";
         cpt_tmp = new HsbaGiayChungNhanPhauThuat();
         FacesMessages.instance().add(IConstantsRes.GCNPT_NULL, new Object[] { maCpt });
       }
       this.cpt = cpt_tmp;
       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.cpt = new HsbaGiayChungNhanPhauThuat();
     }
   }

   public void displayHSBA()
   {
     log.info("---displayHSBA");
     String maHsba = this.cpt.getHsbaSovaovien().getHsbaSovaovien().trim();
     Hsba hsba_tmp = null;
     if (!maHsba.equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
       if (hsba_tmp == null)
       {
         this.nofoundHSBA = "true";
         hsba_tmp = new Hsba();
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { maHsba });
       }
       this.cpt.setHsbaSovaovien(hsba_tmp);
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       this.cpt.setHsbaSovaovien(hsba_tmp);
     }
   }

   public void ghiNhan()
   {
     log.info("---ghiNhan");
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.cpt.setHsbagcnptNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (this.isUpdate)
     {
       System.out.println("Nhom mau = " + this.cpt.getHsbaSovaovien().getHsbaNhommau());
       System.out.println("RH = " + this.cpt.getHsbaSovaovien().getHsbaRh());

       Hsba hsba_tmp = HsbaDelegate.getInstance().find(this.cpt.getHsbaSovaovien().getHsbaSovaovien().trim());
       hsba_tmp.setHsbaNhommau(this.cpt.getHsbaSovaovien().getHsbaNhommau());
       hsba_tmp.setHsbaRh(this.cpt.getHsbaSovaovien().getHsbaRh());
       HsbaDelegate.getInstance().edit(hsba_tmp);

       result = HsbaGiayChungNhanPhauThuatDelegate.getInstance().update(this.cpt);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCNPT_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.cpt.setHsbagcnptMa(result);
         FacesMessages.instance().add(IConstantsRes.GCNPT_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       System.out.println("Nhom mau = " + this.cpt.getHsbaSovaovien().getHsbaNhommau());
       System.out.println("RH = " + this.cpt.getHsbaSovaovien().getHsbaRh());

       Hsba hsba_tmp = HsbaDelegate.getInstance().find(this.cpt.getHsbaSovaovien().getHsbaSovaovien().trim());
       hsba_tmp.setHsbaNhommau(this.cpt.getHsbaSovaovien().getHsbaNhommau());
       hsba_tmp.setHsbaRh(this.cpt.getHsbaSovaovien().getHsbaRh());
       HsbaDelegate.getInstance().edit(hsba_tmp);

       result = HsbaGiayChungNhanPhauThuatDelegate.getInstance().insert(this.cpt);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCNPT_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.cpt.setHsbagcnptMa(result);
         FacesMessages.instance().add(IConstantsRes.GCNPT_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "Giaychungnhanphauthuat";
     log.info("Vao Method XuatReport Giaychungnhanphauthuat");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaychungnhanphauthuat.jrxml";
       String pathTemplate1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaychungnhanphauthuat_subreport0.jrxml";
       String pathTemplate2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaychungnhanphauthuat_subreport1.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("bvTen", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       log.info("-----bvTen: " + params.get("bvTen"));
       params.put("hoTen", this.cpt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten());
       log.info("-----hoTen: " + params.get("hoTen"));
       params.put("diaChi", this.cpt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDiachi());
       log.info("-----diaChi: " + params.get("diaChi"));
       log.info("-----NHOMMAU: " + this.cpt.getHsbaSovaovien().getHsbaNhommau());
       log.info("-----RH: " + this.cpt.getHsbaSovaovien().getHsbaRh());

       params.put("NHOMMAU", this.cpt.getHsbaSovaovien().getHsbaNhommau());
       params.put("RH", this.cpt.getHsbaSovaovien().getHsbaRh());
       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       Date dNgayVaov = this.cpt.getHsbaSovaovien().getHsbaNgaygiovaov();
       String ngayVaov = "";
       if (dNgayVaov != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dNgayVaov);
         ngayVaov = Utils.convertCalendar2Str(cal);
       }
       params.put("ngayVaov", ngayVaov);
       log.info("-----ngayVaov: " + params.get("ngayVaov"));

       Date dNgayRav = this.cpt.getHsbaSovaovien().getHsbaNgaygiorav();
       String ngayRav = "";
       if (dNgayRav != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dNgayRav);
         ngayRav = Utils.convertCalendar2Str(cal);
       }
       params.put("ngayRav", ngayRav);
       log.info("-----ngayRav: " + params.get("ngayRav"));

       JasperReport rpt1 = JasperCompileManager.compileReport(pathTemplate1);
       log.info("-----compiled sub1");
       params.put("sub1", rpt1);

       JasperReport rpt2 = JasperCompileManager.compileReport(pathTemplate2);
       log.info("-----compiled sub2");
       params.put("sub2", rpt2);
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Giaychungnhanphauthuat");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
       this.index += 1;
       log.info("Index :" + getIndex());
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

   public String formatDate(Integer tk, Date date)
   {
     if ((tk != null) && (date != null))
     {
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(5, tk.intValue());
       return new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
     }
     return "";
   }

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public String getNgayTk()
   {
     return this.ngayTk;
   }

   public void setNgayTk(String ngayTk)
   {
     this.ngayTk = ngayTk;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getCrrDate()
   {
     return this.crrDate;
   }

   public void setCrrDate(String crrDate)
   {
     this.crrDate = crrDate;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.GiayChungNhanPhauThuatAction

 * JD-Core Version:    0.7.0.1

 */