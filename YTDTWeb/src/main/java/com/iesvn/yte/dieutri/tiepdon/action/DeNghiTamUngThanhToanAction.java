 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B135_Denghitamungthanhtoan")
 @Synchronized(timeout=6000000L)
 public class DeNghiTamUngThanhToanAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(DeNghiTamUngThanhToanAction.class);
   private String ngayHt;
   private String gioHt;
   private String soTien;
   private String maBanKham;
   private String maTiepDon;
   private String tamUngThToan;
   private TiepDon td;
   private ThamKham thamKham;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";

   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     reset();
     return "TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan";
   }

   @End
   public void end() {}

   @Factory("resetFormB135")
   public void initresetFormB145()
   {
     reset();
   }

   public void reset()
   {
     logger.info("-----reset()-----");
     this.ngayHt = Utils.getCurrentDate();
     this.gioHt = Utils.getGioPhut(new Date());
     this.maBanKham = IConstantsRes.PHONGKHAMCC_DEFAULT;
     this.maTiepDon = "";
     this.tamUngThToan = "0";
     this.soTien = "0";
     this.td = new TiepDon();
     this.thamKham = new ThamKham();
   }

   public void loadTiepDon()
   {
     logger.info("-----loadTiepDon()-----");
     if ((!"".equals(this.maTiepDon)) && (!"".equals(this.maBanKham)))
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       try
       {
         this.thamKham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKham, this.maTiepDon);
         if (this.thamKham != null)
         {
           this.td = this.thamKham.getTiepdonMa();
           this.maTiepDon = this.td.getTiepdonMa();
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, new Object[] { "bệnh nhân", "bàn khám " + this.maBanKham });
         }
       }
       catch (Exception e)
       {
         logger.error(e);
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, new Object[] { "bệnh nhân", "bàn khám " + this.maBanKham });

         reset();
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB135 = null;
     return "B160_Chonmenuxuattaptin";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB135 = null;
   int index = 0;

   public void XuatReport()
   {
     this.reportTypeTD = "B135_Denghitamungthanhtoan";
     logger.info("Vao Method XuatReport B135_Denghitamungthanhtoan");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/B135_Denghitamungthanhtoan.jrxml";
       logger.info("da thay file templete" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("loaidenghi", this.tamUngThToan);
       params.put("ngayDeNghi", this.ngayHt);

       params.put("maTiepDon", this.maTiepDon);
       params.put("TEN", this.td.getBenhnhanMa(true).getBenhnhanHoten());

       String diachistr = "";
       if ((this.td.getBenhnhanMa(true).getBenhnhanDiachi() != null) && (!this.td.getBenhnhanMa(true).getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + this.td.getBenhnhanMa(true).getBenhnhanDiachi() + ",";
       }
       if ((this.td.getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null) && (!this.td.getBenhnhanMa(true).getXaMa(true).getDmxaTen().equals(""))) {
         diachistr = diachistr + this.td.getBenhnhanMa(true).getXaMa(true).getDmxaTen() + ",";
       }
       if ((this.td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null) && (!this.td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen().equals(""))) {
         diachistr = diachistr + this.td.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if ((this.td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null) && (!this.td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen().equals(""))) {
         diachistr = diachistr + this.td.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       params.put("DANTOC", this.td.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen());
       params.put("KCB", this.td.getKcbbhytMa(true).getDmbenhvienTen());
       if (this.td.getTiepdonTuyen() == null) {
         params.put("TUYEN", "");
       } else {
         params.put("TUYEN", String.valueOf(this.td.getTiepdonTuyen()));
       }
       params.put("GIOITINH", this.td.getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());

       Double soTienTmp = null;
       try
       {
         soTienTmp = new Double(this.soTien);
       }
       catch (Exception e)
       {
         soTienTmp = new Double(0.0D);
       }
       params.put("SOTIEN", soTienTmp);
       params.put("TIENBANGCHU", Utils.NumberToString(soTienTmp.doubleValue()));

       logger.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       logger.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "B135_Denghitamungthanhtoan");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");

       logger.info("duong dan -------------------- :" + this.reportPathTD);
       this.index += 1;
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "B135_Denghitamungthanhtoan";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getGioHt()
   {
     return this.gioHt;
   }

   public void setGioHt(String gioHt)
   {
     this.gioHt = gioHt;
   }

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
   }

   public String getMaTiepDon()
   {
     return this.maTiepDon;
   }

   public void setMaTiepDon(String maTiepDon)
   {
     this.maTiepDon = maTiepDon;
   }

   public TiepDon getTd()
   {
     return this.td;
   }

   public void setTd(TiepDon td)
   {
     this.td = td;
   }

   public String getSoTien()
   {
     return this.soTien;
   }

   public void setSoTien(String soTien)
   {
     this.soTien = soTien;
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

   public ThamKham getThamKham()
   {
     return this.thamKham;
   }

   public void setThamKham(ThamKham thamKham)
   {
     this.thamKham = thamKham;
   }

   public String getTamUngThToan()
   {
     return this.tamUngThToan;
   }

   public void setTamUngThToan(String tamUngThToan)
   {
     this.tamUngThToan = tamUngThToan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.DeNghiTamUngThanhToanAction

 * JD-Core Version:    0.7.0.1

 */