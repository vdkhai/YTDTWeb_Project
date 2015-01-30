 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
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
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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
 @Name("B3212_CapNhatTienTamUng")
 @Synchronized(timeout=6000000L)
 public class CapNhatTienTamUngAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatTienTamUngAction.class);
   @In(required=false)
   private Identity identity;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private static int index = 0;
   private BenhNhan benhNhan;
   private HsbaBhyt hsbaBhyt;
   private TamUng tamUng;
   private String ghiNhanException;
   private String maPhu;
   private String tamUng_HoanUng;
   private String soDu;
   private String soTheBH_SoTheNgheo;
   private String tienBangChu;
   private String donViTuoi;
   private String sLiDoNop = "";
   private String lanTamung = "";
   private DtDmCum cum = null;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private boolean disabledPrinting = true;
   private boolean disabledGhinhan = false;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Create
   @Begin(join=true)
   public String init()
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
     getTamUng(true).setTamungNgaygio(new Date());
     this.maPhu = (new Date().getTime() + "");
     log.info("***Finished init **");


     return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng";
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
     MyXuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void MyXuatReport()
   {
     this.reportTypeVP = "giaytamung";
     log.info("Vao Method XuatReport thuoc y dung cu phong kham, lanTamung = " + this.lanTamung);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/PhieuThu.jrxml";

       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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

       params.put("tienThuoc", this.tamUng.getTamungSotien());
       params.put("TIENBANGCHU", Utils.NumberToString(this.tamUng.getTamungSotien().doubleValue()));


       params.put("LIDONOP", this.sLiDoNop);
       params.put("LANTAMUNG", this.lanTamung);

       int lanIn = this.tamUng.getTamungIn() == null ? 1 : Integer.valueOf(this.tamUng.getTamungIn()).intValue() + 1;
       this.tamUng.setTamungIn("" + lanIn);
       params.put("LANIN", "" + lanIn);

       params.put("SOPHIEU", this.tamUng.getTamungMa());
       params.put("QUYEN", this.tamUng.getTamungQuyen());

       log.info("nv maso = " + this.nhanVienThungan);
       DtDmNhanVien nv = DtDmNhanVienDelegate.getInstance().find(this.nhanVienThungan.getDtdmnhanvienMaso());
       params.put("nguoiLapPhieu", nv == null ? "" : nv.getDtdmnhanvienTen());
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "giaytamung");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       index += 1;
       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
       TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
       tamUngDelegate.edit(this.tamUng);
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
     if ((this.tamUng.getHsbaSovaovien() != null) && (this.tamUng.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       try
       {
         HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
         Hsba hsbaCur = hsbaDelegate.find(this.tamUng.getHsbaSovaovien().getHsbaSovaovien());
         if (hsbaCur == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.tamUng.getHsbaSovaovien().getHsbaSovaovien() });
           return;
         }
         this.tamUng.setHsbaSovaovien(hsbaCur);
         this.tamUng.setTamungKhoa(hsbaCur.getHsbaKhoadangdt(true));

         qryBenhNhanBhtyThanhToan(this.tamUng.getTamungKhoa().getDmkhoaMa(), this.tamUng.getHsbaSovaovien().getHsbaSovaovien());
         FacesMessages.instance().clear();
         if (this.benhNhan == null) {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.tamUng.getTamungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.tamUng.getHsbaSovaovien().getHsbaSovaovien() });
         } else if (this.disabledGhinhan) {
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.tamUng.getTamungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.tamUng.getHsbaSovaovien().getHsbaSovaovien() });
         log.error(e.toString());
       }
       this.maPhu = (new Date().getTime() + "");
       log.debug(this.tamUng.getHsbaSovaovien());
       log.debug(this.tamUng.getTamungKhoa().getDmkhoaMa());
     }
     log.info("***Finished layTheoSoVaoVien **");
   }

   public void layTheoTamUngMa()
     throws Exception
   {
     log.info("***Starting layTheoTamUngMa ***");
     this.disabledPrinting = true;
     if (this.tamUng == null) {
       return;
     }
     String tamUngMa = this.tamUng.getTamungMa();
     TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
     try
     {
       this.tamUng = tamUngDelegate.find(this.tamUng.getTamungMa());
       if (this.tamUng == null) {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, tamUngMa });
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, tamUngMa });
       log.error(e.toString());
     }
     if ((this.tamUng != null) && (this.tamUng.getTamungKhoa() != null) && (this.tamUng.getTamungKhoa().getDmkhoaMa() != null) && (this.tamUng.getHsbaSovaovien() != null) && (this.tamUng.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       this.sLiDoNop = this.tamUng.getTamungLydo();
       if (this.tamUng.getTamungThungan(true) != null) {
         this.nhanVienThungan = this.tamUng.getTamungThungan(true);
       }
       log.info("*****NHAN VIEN THU NGAN: " + this.nhanVienThungan);

       qryBenhNhanBhtyThanhToan(this.tamUng.getTamungKhoa().getDmkhoaMa(), this.tamUng.getHsbaSovaovien().getHsbaSovaovien());
       this.maPhu = this.tamUng.getTamungMa();
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
     log.info("***Finished layTheoTamUngMa **");
   }

   private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien)
   {
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();


     log.info("hoSoBenhAn.getHsbaSovaovien():" + soVaoVien);


     HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
     if (hsbaKhoa == null)
     {
       log.info("hsbaKhoa11:" + hsbaKhoa);


       this.benhNhan = null;
       this.hsbaBhyt = null;

       return;
     }
     log.info("maKhoa:" + khoa);
     if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa()))
     {
       log.info("hsbaKhoa22:" + hsbaKhoa);


       this.benhNhan = null;
       this.hsbaBhyt = null;

       return;
     }
     this.tamUng.getHsbaSovaovien().setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien());
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

     log.info("hsThtoan.getHsthtoanTamung():" + hsThtoan.getHsthtoanTamung());

     log.info("hsThtoan.getHsthtoanHoanung():" + hsThtoan.getHsthtoanHoanung());


     this.tamUng_HoanUng = Utils.formatLongDouble(Double.valueOf(hsThtoan.getHsthtoanTamung().doubleValue() - hsThtoan.getHsthtoanHoanung().doubleValue()));
     log.info("ungTra:" + this.tamUng_HoanUng);



     this.soDu = Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan());
     System.out.println("CapNhatTienTamUngAction.qryBenhNhanBhtyThanhToan()*****soDu=" + this.soDu);

     TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
     this.lanTamung = ("" + tamUngDelegate.countSolanTamUngByHsba(soVaoVien));
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
     log.info(this.tamUng.getTamungSotien());
     this.ghiNhanException = null;

     TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
     if (this.tamUng != null) {
       try
       {
         DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
         if (userNv != null)
         {
           PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
           PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(this.tamUng.getTamungNgaygio(), userNv.getDtdmnhanvienMa());
           if (pcCumThuPhi != null)
           {
             this.tamUng.setTamungCum(pcCumThuPhi.getDtdmcumMa());
             this.tamUng.setTamungLydo(this.sLiDoNop);
           }
         }
         if (StringUtils.isBlank(this.tamUng.getTamungMa()))
         {
           this.tamUng.setTamungThungan(this.nhanVienThungan);
           this.tamUng.setTamungLydo(this.sLiDoNop);
           this.tamUng.setTamungNgaygio(Calendar.getInstance().getTime());
           this.tamUng = tamUngDelegate.create(this.tamUng);
           this.lanTamung = ("" + (Integer.valueOf(this.lanTamung).intValue() + 1));
         }
         else
         {
           this.tamUng.setTamungNgaygio(Calendar.getInstance().getTime());
           this.tamUng.setTamungLydo(this.sLiDoNop);
           tamUngDelegate.edit(this.tamUng);
         }
         FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.PHIEU_SO, this.tamUng.getTamungMa() });
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
     this.sLiDoNop = "";
     this.tamUng = null;
     this.soTheBH_SoTheNgheo = null;
     getTamUng(true).setTamungNgaygio(new Date());
     this.benhNhan = null;
     this.hsbaBhyt = null;

     this.soDu = null;
     this.tamUng_HoanUng = null;
     this.soTheBH_SoTheNgheo = null;
     this.tienBangChu = null;
     this.donViTuoi = null;
     this.maPhu = (new Date().getTime() + "");
     this.disabledPrinting = true;
     this.disabledGhinhan = false;
     this.lanTamung = "";
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

   public TamUng getTamUng()
   {
     return this.tamUng;
   }

   public TamUng getTamUng(boolean create)
   {
     if ((create) && (this.tamUng == null)) {
       this.tamUng = new TamUng();
     }
     return this.tamUng;
   }

   public void setTamUng(TamUng tamUng)
   {
     this.tamUng = tamUng;
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

   public String getTamUng_HoanUng()
   {
     return this.tamUng_HoanUng;
   }

   public void setTamUng_HoanUng(String tamUng_HoanUng)
   {
     this.tamUng_HoanUng = tamUng_HoanUng;
   }

   public String getSoTheBH_SoTheNgheo()
   {
     return this.soTheBH_SoTheNgheo;
   }

   public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo)
   {
     this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
   }

   public String getTienBangChu()
   {
     return this.tienBangChu;
   }

   public void setTienBangChu(String tienBangChu)
   {
     this.tienBangChu = tienBangChu;
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
   }

   public String getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(String soDu)
   {
     this.soDu = soDu;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
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

   public boolean getDisabledPrinting()
   {
     return this.disabledPrinting;
   }

   public void setDisabledPrinting(boolean disabledPrinting)
   {
     this.disabledPrinting = disabledPrinting;
   }

   public String getLiDoNop()
   {
     return this.sLiDoNop;
   }

   public void setLiDoNop(String liDoNop)
   {
     this.sLiDoNop = liDoNop;
   }

   public boolean isDisabledGhinhan()
   {
     return this.disabledGhinhan;
   }

   public void setDisabledGhinhan(boolean disabledGhinhan)
   {
     this.disabledGhinhan = disabledGhinhan;
   }

   public String getLanTamung()
   {
     return this.lanTamung;
   }

   public void setLanTamung(String lanTamung)
   {
     this.lanTamung = lanTamung;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.CapNhatTienTamUngAction

 * JD-Core Version:    0.7.0.1

 */