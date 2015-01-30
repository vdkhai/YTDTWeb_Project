 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3160_Denghitamungthanhtoan")
 @Synchronized(timeout=6000000L)
 public class B3160_Denghitamungthanhtoan
   implements Serializable
 {
   private String ngayDenghi;
   private String maKhoa = "";
   private Hsba hoSoBenhAn;
   private HsbaBhyt hsbaBHYT;
   private String tamUngThToan;
   private String soTien;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String resetFormB3160 = null;
   private static Logger log = Logger.getLogger(B3160_Denghitamungthanhtoan.class);

   @Factory("resetFormB3160")
   public void init()
   {
     log.info("init()");
     resetForm();
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     this.ngayDenghi = sdf.format(new Date());
     this.maKhoa = "";
     this.hoSoBenhAn = new Hsba();
     this.hsbaBHYT = new HsbaBhyt();
     this.tamUngThToan = "0";
     this.soTien = "0";
     this.resetFormB3160 = "";
   }

   public void displayInfor()
   {
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.maKhoa == null) || (this.maKhoa.equals("")))
     {
       resetForm();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });
       return;
     }
     HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
     this.hoSoBenhAn = hsbaDel.find(this.hoSoBenhAn.getHsbaSovaovien());

     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();



     log.info("hoSoBenhAn.getHsbaSovaovien():" + this.hoSoBenhAn.getHsbaSovaovien());

     HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienLastHsbaKhoa(this.hoSoBenhAn.getHsbaSovaovien());
     if (hsbaKhoa == null)
     {
       resetForm();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });
       return;
     }
     log.info("maKhoa:" + this.maKhoa);
     if (!this.maKhoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa()))
     {
       resetForm();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });
       return;
     }
     HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
     this.hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
     if (this.hsbaBHYT == null) {
       this.hsbaBHYT = new HsbaBhyt();
     }
     this.tamUngThToan = "0";
     this.soTien = "0";
     log.info("-----hsba: " + this.hoSoBenhAn);
     log.info("-----ho ten: " + this.hoSoBenhAn.getBenhnhanMa().getBenhnhanHoten());
   }

   int index = 0;

   public String thuchienAction()
   {
     log.info("Begin thuchienAction(), tamUngThToan: " + this.tamUngThToan);
     this.reportTypeVP = "B3160_Denghitamungthanhtoan";
     log.info("Vao Method XuatReport B3160_Denghitamungthanhtoan");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B135_Denghitamungthanhtoan.jrxml";
       log.info("da thay file templete" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("loaidenghi", this.tamUngThToan);
       params.put("ngayDeNghi", Utils.getCurrentDate());

       params.put("soBenhAn", this.hoSoBenhAn.getHsbaSovaovien());
       params.put("TEN", this.hoSoBenhAn.getBenhnhanMa(true).getBenhnhanHoten());

       String diachistr = "";
       if ((this.hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi() != null) && (!this.hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + this.hoSoBenhAn.getBenhnhanMa(true).getBenhnhanDiachi();
       }
       if ((this.hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen().equals(""))) {
         diachistr = diachistr + ", " + this.hoSoBenhAn.getBenhnhanMa(true).getXaMa(true).getDmxaTen();
       }
       if ((this.hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen().equals(""))) {
         diachistr = diachistr + ", " + this.hoSoBenhAn.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen();
       }
       if ((this.hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen().equals(""))) {
         diachistr = diachistr + ", " + this.hoSoBenhAn.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       params.put("DANTOC", this.hoSoBenhAn.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen());
       params.put("KCB", this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienTen());
       if (this.hsbaBHYT.getHsbabhytTuyen() == null) {
         params.put("TUYEN", "");
       } else {
         params.put("TUYEN", String.valueOf(this.hsbaBHYT.getHsbabhytTuyen()));
       }
       params.put("GIOITINH", this.hoSoBenhAn.getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());

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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B135_Denghitamungthanhtoan");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");

       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     return "B3360_Chonmenuxuattaptin";
   }

   public String getNgayDenghi()
   {
     return this.ngayDenghi;
   }

   public void setNgayDenghi(String ngayDenghi)
   {
     this.ngayDenghi = ngayDenghi;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public HsbaBhyt getHsbaBHYT()
   {
     return this.hsbaBHYT;
   }

   public void setHsbaBHYT(HsbaBhyt hsbaBHYT)
   {
     this.hsbaBHYT = hsbaBHYT;
   }

   public String getTamUngThToan()
   {
     return this.tamUngThToan;
   }

   public void setTamUngThToan(String tamUngThToan)
   {
     this.tamUngThToan = tamUngThToan;
   }

   public String getSoTien()
   {
     return this.soTien;
   }

   public void setSoTien(String soTien)
   {
     this.soTien = soTien;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3160_Denghitamungthanhtoan

 * JD-Core Version:    0.7.0.1

 */