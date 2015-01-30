 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HoanUngDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HoanUng;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.TamUng;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.commons.lang.StringUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3216_ChiTraTienTamUng")
 @Synchronized(timeout=6000000L)
 public class ChiTraTienTamUngAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(ChiTraTienTamUngAction.class);
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private BenhNhan benhNhan;
   private HsbaBhyt hsbaBhyt;
   private HoanUng hoanUng;
   private String ghiNhanException;
   private String maPhu;
   private String daUng;
   private String daTra;
   private String soDu;
   private String soTheBH_SoTheNgheo;
   private String donViTuoi;
   private int index = 0;
   private DtDmCum cum = null;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private boolean disabledPrinting = true;
   private boolean disabledGhinhan = false;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Create
   @Begin(join=true)
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");


     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
     log.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     log.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       this.nhanVienThungan = pc.getDtdmnhanvienMa();
       log.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     getHoanUng(true).setHoanungNgaygio(new Date());
     this.maPhu = (new Date().getTime() + "");
     log.info("***Finished init **");
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");

     log.info("***Finished back **");
     return "MyMainForm";
   }

   public String xuatReport()
   {
     XuatReportTmp();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReportTmp()
   {
     this.reportTypeVP = "chitrabottientamung";
     log.info("Vao Method XuatReport thuoc y dung cu phong kham");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/PhieuChi.jrxml";

       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("tenBenhNhan", this.benhNhan.getBenhnhanHoten());

       String diachistr = "";
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhNhan.getBenhnhanDiachi() + ",";
       }
       if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhNhan.getXaMa(true).getDmxaTen() + ",";
       }
       if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhNhan.getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("diaChiBenhNhan", diachistr);

       params.put("LYDO", this.hoanUng.getHoanungLydo() != null ? this.hoanUng.getHoanungLydo() : "");
       params.put("tienThuoc", this.hoanUng.getHoanungSotien());
       params.put("TIENBANGCHU", Utils.NumberToString(this.hoanUng.getHoanungSotien().doubleValue()));


       int lanIn = this.hoanUng.getHoanungIn() == null ? 1 : Integer.valueOf(this.hoanUng.getHoanungIn()).intValue() + 1;
       this.hoanUng.setHoanungIn("" + lanIn);
       params.put("LANIN", "" + lanIn);
       params.put("SOPHIEU", this.hoanUng.getHoanungMa());
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "chitrabottientamung");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + this.index);
       if (conn != null) {
         conn.close();
       }
       HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
       hoanUngDelegate.edit(this.hoanUng);
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public void layTheoSoVaoVien()
     throws Exception
   {
     log.info("***Starting layTheoSoVaoVien ***");
     this.disabledPrinting = true;
     if ((this.hoanUng.getHoanungKhoa() != null) && (this.hoanUng.getHoanungKhoa().getDmkhoaMa() != null) && (this.hoanUng.getHsbaSovaovien() != null) && (this.hoanUng.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       try
       {
         qryBenhNhanBhtyThanhToan(this.hoanUng.getHoanungKhoa().getDmkhoaMa(), this.hoanUng.getHsbaSovaovien().getHsbaSovaovien());
         if (this.benhNhan == null)
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.hoanUng.getHoanungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.hoanUng.getHsbaSovaovien().getHsbaSovaovien() });
           this.hoanUng.getHsbaSovaovien().setHsbaSovaovien("");
         }
         else if (this.disabledGhinhan)
         {
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.hoanUng.getHoanungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.hoanUng.getHsbaSovaovien().getHsbaSovaovien() });
         this.hoanUng.getHsbaSovaovien().setHsbaSovaovien("");
         log.error(e.toString());
       }
       this.maPhu = (new Date().getTime() + "");
       log.debug(this.hoanUng.getHsbaSovaovien());
       log.debug(this.hoanUng.getHoanungKhoa().getDmkhoaMa());
     }
     log.info("***Finished layTheoSoVaoVien **");
   }

   public void layTheoHoanUngMa()
     throws Exception
   {
     log.info("***Starting layTheoHoanUngMa ***");
     this.disabledPrinting = true;
     if (this.hoanUng == null) {
       return;
     }
     log.info("hoanUng.getHoanungMa()=" + this.hoanUng.getHoanungMa());
     String strMaTmp = this.hoanUng.getHoanungMa();
     HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
     try
     {
       this.hoanUng = hoanUngDelegate.find(this.hoanUng.getHoanungMa());
       if (this.hoanUng == null) {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, strMaTmp });
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, strMaTmp });
       log.error(e.toString());
     }
     if ((this.hoanUng != null) && (this.hoanUng.getHoanungKhoa() != null) && (this.hoanUng.getHoanungKhoa().getDmkhoaMa() != null) && (this.hoanUng.getHsbaSovaovien() != null) && (this.hoanUng.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       qryBenhNhanBhtyThanhToan(this.hoanUng.getHoanungKhoa().getDmkhoaMa(), this.hoanUng.getHsbaSovaovien().getHsbaSovaovien());
       this.maPhu = this.hoanUng.getHoanungMa();
       this.disabledPrinting = false;
       if (this.disabledGhinhan) {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
       }
     }
     else
     {
       this.benhNhan = null;
       this.hsbaBhyt = null;
     }
     log.info("***Finished layTheoHoanUngMa **");
   }

   private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien)
   {
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();

     HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
     if (hsbaKhoa == null)
     {
       log.info("##### hsbaKhoa = NULL #####");

       this.benhNhan = null;
       this.hsbaBhyt = null;
       return;
     }
     if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa()))
     {
       this.benhNhan = null;
       this.hsbaBhyt = null;
       return;
     }
     this.hoanUng.getHsbaSovaovien().setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien());

     soVaoVien = hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien();

     this.disabledGhinhan = checkDaTT(soVaoVien);
     this.benhNhan = hsbaKhoa.getHsbaSovaovien().getBenhnhanMa();

     HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
     List<HsbaBhyt> list = hsbaBhytDelegate.findBySoVaoVien(soVaoVien);
     if ((list != null) && (list.size() > 0)) {
       this.hsbaBhyt = ((HsbaBhyt)list.get(0));
     }
     if (this.benhNhan != null)
     {
       int dvt = this.benhNhan.getBenhnhanDonvituoi().shortValue();
       this.donViTuoi = Utils.taoDonViTuoi(dvt);
       if (this.benhNhan.getDantocMa() != null)
       {
         log.info("benhNhan.getDantocMa().getDmdantocMa()=" + this.benhNhan.getDantocMa().getDmdantocMa());
         log.info("benhNhan.getDantocMa().getDmdantocTen()=" + this.benhNhan.getDantocMa().getDmdantocTen());
       }
       if (this.benhNhan.getDmgtMaso() != null) {
         log.info("benhNhan.getDmgtMaso()=" + this.benhNhan.getDmgtMaso().getDmgtMaso());
       }
     }
     if (this.hsbaBhyt != null)
     {
       log.info("hsbaBhyt.getHsbabhytSothebh()=" + this.hsbaBhyt.getHsbabhytSothebh());
       this.soTheBH_SoTheNgheo = this.hsbaBhyt.getHsbabhytSothebh();
       if (StringUtils.isBlank(this.soTheBH_SoTheNgheo))
       {
         log.info("hsbaBhyt.getHsbabhytSothengheo()=" + this.hsbaBhyt.getHsbabhytSothengheo());
         this.soTheBH_SoTheNgheo = this.hsbaBhyt.getHsbabhytSothengheo();
       }
       log.info("hsbaBhyt.getHsbabhytMakcb()=" + this.hsbaBhyt.getHsbabhytMakcb());
       if (this.hsbaBhyt.getHsbabhytMakcb() != null) {
         log.info("hsbaBhyt.getHsbabhytMakcb().getDtdmkcbbhytMa()=" + this.hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa());
       }
       log.info("hsbaBhyt.getHsbabhytSothebh()=" + this.hsbaBhyt.getHsbabhytSothebh());
       log.info("hsbaBhyt.getHsbabhytSothengheo()=" + this.hsbaBhyt.getHsbabhytSothengheo());
     }
     HsThtoan hsThtoan = new HsThtoan();
     hsThtoan.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());

     tinhToanChoHSTT(hsThtoan, hsbaKhoa.getHsbaSovaovien());
     Utils.setInforForHsThToan(hsThtoan);

     this.daUng = Utils.formatLongDouble(hsThtoan.getHsthtoanTamung());
     this.daTra = Utils.formatLongDouble(hsThtoan.getHsthtoanHoanung());
     this.soDu = Utils.formatLongDouble(Double.valueOf(hsThtoan.getHsthtoanThtoan().doubleValue() - (hsThtoan.getHsthtoanNguonkhactra() == null ? 0.0D : hsThtoan.getHsthtoanNguonkhactra().doubleValue())));
   }

   private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba)
   {
     HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
     hsthtoanUtil.tinhToanChoHSTT(hstt);
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     this.disabledPrinting = true;
     log.info(this.hoanUng.getHoanungSotien());
     this.ghiNhanException = null;

     HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
     if (this.hoanUng != null) {
       try
       {
         if (StringUtils.isBlank(this.hoanUng.getHoanungMa()))
         {
           TamUng tu = TamUngDelegate.getInstance().findByMaHsba(this.hoanUng.getHsbaSovaovien().getHsbaSovaovien());
           this.hoanUng.setHoanungMaphieu(tu);
           this.hoanUng.setHoanungThungan(this.nhanVienThungan);

           this.hoanUng.setHoanungNgaygio(Calendar.getInstance().getTime());
           this.hoanUng = hoanUngDelegate.create(this.hoanUng);
         }
         else
         {
           hoanUngDelegate.edit(this.hoanUng);
         }
         FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.PHIEU_SO, this.hoanUng.getHoanungMa() });
         this.disabledPrinting = false;
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         log.error(e.toString());
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
       }
     }
     log.info("***Finished ghinhan **");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       resetForm();
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void resetForm()
   {
     log.info("Begining ResetForm(): ");
     this.hoanUng = null;
     getHoanUng(true).setHoanungNgaygio(new Date());
     this.benhNhan = null;
     this.hsbaBhyt = null;


     this.soDu = null;
     this.daUng = null;
     this.daTra = null;
     this.soTheBH_SoTheNgheo = null;
     this.donViTuoi = null;
     this.maPhu = (new Date().getTime() + "");
     this.disabledPrinting = true;
     this.disabledGhinhan = false;
     log.info("End ResetForm(): ");
   }

   private boolean checkDaTT(String sovaovien)
   {
     HsThtoan hsTmp = HsThtoanDelegate.getInstance().findBySovaovien(sovaovien);
     if (hsTmp != null) {
       return hsTmp.getHsthtoanDatt() == null ? false : hsTmp.getHsthtoanDatt().booleanValue();
     }
     return false;
   }

   public HoanUng getHoanUng(boolean create)
   {
     if ((create) && (this.hoanUng == null)) {
       this.hoanUng = new HoanUng();
     }
     return this.hoanUng;
   }

   public HoanUng getHoanUng()
   {
     return this.hoanUng;
   }

   public void setHoanUng(HoanUng hoanUng)
   {
     this.hoanUng = hoanUng;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public BenhNhan getBenhNhan(boolean create)
   {
     if ((create) && (this.benhNhan == null)) {
       this.benhNhan = new BenhNhan();
     }
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public HsbaBhyt getHsbaBhyt(boolean create)
   {
     if ((create) && (this.hsbaBhyt == null)) {
       this.hsbaBhyt = new HsbaBhyt();
     }
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public String getMaPhu()
   {
     return this.maPhu;
   }

   public void setMaPhu(String maPhu)
   {
     this.maPhu = maPhu;
   }

   public String getSoTheBH_SoTheNgheo()
   {
     return this.soTheBH_SoTheNgheo;
   }

   public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo)
   {
     this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public String getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(String soDu)
   {
     this.soDu = soDu;
   }

   public String getDaUng()
   {
     return this.daUng;
   }

   public void setDaUng(String daUng)
   {
     this.daUng = daUng;
   }

   public String getDaTra()
   {
     return this.daTra;
   }

   public void setDaTra(String daTra)
   {
     this.daTra = daTra;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getReportPathVP()
   {
     return this.reportPathVP;
   }

   public void setReportPathVP(String reportPathVP)
   {
     this.reportPathVP = reportPathVP;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public JasperPrint getJasperPrintVP()
   {
     return this.jasperPrintVP;
   }

   public void setJasperPrintVP(JasperPrint jasperPrintVP)
   {
     this.jasperPrintVP = jasperPrintVP;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public boolean getDisabledPrinting()
   {
     return this.disabledPrinting;
   }

   public void setDisabledPrinting(boolean disabledPrinting)
   {
     this.disabledPrinting = disabledPrinting;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public boolean isDisabledGhinhan()
   {
     return this.disabledGhinhan;
   }

   public void setDisabledGhinhan(boolean disabledGhinhan)
   {
     this.disabledGhinhan = disabledGhinhan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ChiTraTienTamUngAction

 * JD-Core Version:    0.7.0.1

 */