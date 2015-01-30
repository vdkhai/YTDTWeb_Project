 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmDienMienDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmLoaiMienDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmDienMien;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.MienGiam;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
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
 import java.text.SimpleDateFormat;
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
 @Name("B3213_CapNhatMienGiam")
 @Synchronized(timeout=6000000L)
 public class CapNhatMienGiamAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatMienGiamAction.class);
   @In(required=false)
   private Identity identity;
   private BenhNhan benhNhan;
   private HsbaBhyt hsbaBhyt;
   private MienGiam mienGiam;
   private HoSoThanhToanUtil hosoThanhToanUtil;
   private String ghiNhanException;
   private String maPhu;
   private String tamUng_HoanUng;
   private String mienDT;
   private String soDu;
   private String soTheBH_SoTheNgheo;
   private String donViTuoi;
   private Date ngayhientai;
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
   private DtDmCum cum = null;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
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
     this.maPhu = (new Date().getTime() + "");
     this.mienGiam = new MienGiam();
     this.mienGiam.setMiengiamNgay(new Date());
     this.mienGiam.setMiengiamNgayky(new Date());
     this.ngayhientai = new Date();
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

   public void layTheoSoVaoVien()
     throws Exception
   {
     log.info("***Starting layTheoSoVaoVien ***");
     this.disabledPrinting = true;
     if ((this.mienGiam.getMiengiamKhoa() != null) && (this.mienGiam.getMiengiamKhoa().getDmkhoaMa() != null) && (this.mienGiam.getHsbaSovaovien() != null) && (this.mienGiam.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       try
       {
         qryBenhNhanBhtyThanhToan(this.mienGiam.getMiengiamKhoa().getDmkhoaMa(), this.mienGiam.getHsbaSovaovien().getHsbaSovaovien());
         if (this.benhNhan == null) {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.mienGiam.getMiengiamKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.mienGiam.getHsbaSovaovien().getHsbaSovaovien() });
         } else if (this.disabledGhinhan) {
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
       }
       catch (Exception e)
       {
         e.printStackTrace();
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, this.mienGiam.getMiengiamKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, this.mienGiam.getHsbaSovaovien().getHsbaSovaovien() });
         log.error(e.toString());
       }
       this.maPhu = (new Date().getTime() + "");
       log.debug(this.mienGiam.getHsbaSovaovien());
       log.debug(this.mienGiam.getMiengiamKhoa().getDmkhoaMa());
     }
     log.info("***Finished layTheoSoVaoVien **");
   }

   public String xuatReport()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     String gt = "";
     String diachi = "";
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/giaymiengiam.jrxml";
       log.info("da thay file templete 5" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       log.info("Ten benh nhan " + this.benhNhan.getBenhnhanHoten());
       params.put("TEN", this.benhNhan.getBenhnhanHoten());
       params.put("TUOI", this.benhNhan.getBenhnhanTuoi());
       if (this.benhNhan.getDmgtMaso() != null) {
         gt = this.benhNhan.getDmgtMaso().getDmgtTen();
       }
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("GT", gt);
       if (this.benhNhan.getDantocMa() != null)
       {
         log.info("Dan toc " + this.benhNhan.getDantocMa().getDmdantocTen());
         params.put("DANTOC", this.benhNhan.getDantocMa().getDmdantocTen());
       }
       else
       {
         params.put("DANTOC", "");
       }
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachi = diachi + this.benhNhan.getBenhnhanDiachi();
       }
       if ((this.benhNhan.getXaMa() != null) &&
         (this.benhNhan.getXaMa().getDmxaMaso() != null)) {
         diachi = diachi + (diachi.trim().length() > 0 ? ", " + this.benhNhan.getXaMa().getDmxaTen() : this.benhNhan.getXaMa().getDmxaTen());
       }
       if ((this.benhNhan.getHuyenMa() != null) &&
         (this.benhNhan.getHuyenMa().getDmhuyenMaso() != null)) {
         diachi = diachi + (diachi.trim().length() > 0 ? ", " + this.benhNhan.getHuyenMa().getDmhuyenTen() : this.benhNhan.getHuyenMa().getDmhuyenTen());
       }
       if ((this.benhNhan.getTinhMa() != null) &&
         (this.benhNhan.getTinhMa().getDmtinhMaso() != null)) {
         diachi = diachi + (diachi.trim().length() > 0 ? ", " + this.benhNhan.getTinhMa().getDmtinhTen() : this.benhNhan.getTinhMa().getDmtinhTen());
       }
       log.info("Dia chi " + diachi);
       params.put("DIACHI", diachi);
       if (this.mienGiam.getMiengiamKhoa() != null)
       {
         log.info("khoa " + this.mienGiam.getMiengiamKhoa().getDmkhoaTen());
         params.put("KHOA", this.mienGiam.getMiengiamKhoa().getDmkhoaTen());
       }
       else
       {
         params.put("KHOA", "");
       }
       log.info("So the BHYT " + this.hsbaBhyt.getHsbabhytSothebh());
       params.put("SOTHEBH", this.hsbaBhyt.getHsbabhytSothebh() == null ? "" : this.hsbaBhyt.getHsbabhytSothebh());
       if (this.hsbaBhyt.getHsbabhytMakcb() != null)
       {
         log.info("KCB " + this.hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen());
         params.put("KCB", this.hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen());
       }
       else
       {
         params.put("KCB", "");
       }
       log.info("Tuyen " + String.valueOf(this.hsbaBhyt.getHsbabhytTuyen()));
       if (this.hsbaBhyt.getHsbabhytTuyen() == null) {
         params.put("TUYEN", "");
       } else {
         params.put("TUYEN", String.valueOf(this.hsbaBhyt.getHsbabhytTuyen()));
       }
       log.info("mien giam " + String.valueOf(this.mienGiam.getMiengiamSotien()));
       params.put("SOTIEN", this.mienGiam.getMiengiamSotien());
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (this.mienGiam.getMiengiamNgay() != null) {
         params.put("NGAYLAP", sdf.format(this.mienGiam.getMiengiamNgay()));
       }
       if (this.mienGiam.getMiengiamNgayd() != null) {
         params.put("TUNGAY", sdf.format(this.mienGiam.getMiengiamNgayd()));
       }
       if (this.mienGiam.getMiengiamNgayc() != null) {
         params.put("DENNGAY", sdf.format(this.mienGiam.getMiengiamNgayc()));
       }
       params.put("TYLEGIAM", this.mienGiam.getMiengiamTyle() == null ? "" : String.valueOf(this.mienGiam.getMiengiamTyle()));

       log.info("MIEN GIAM MA SO " + this.mienGiam.getMiengiamMa());

       log.info("LOAIGIAM " + this.mienGiam.getMiengiamLoaimien() + " " + this.mienGiam.getMiengiamLoaimien().getDtdmloaimienTen());
       if (this.mienGiam.getMiengiamLoaimien() != null)
       {
         DtDmLoaiMien dmLoaimien = DtDmLoaiMienDelegate.getInstance().find(this.mienGiam.getMiengiamLoaimien().getDtdmloaimienMaso());
         log.info("LOAIGIAM 2, " + dmLoaimien.getDtdmloaimienTen());
         params.put("LOAIGIAM", dmLoaimien.getDtdmloaimienTen());
       }
       else
       {
         params.put("LOAIGIAM", "");
       }
       params.put("LYDOGIAM", this.mienGiam.getMiengiamLydo() == null ? "" : this.mienGiam.getMiengiamLydo());
       if ((this.mienGiam.getMiengiamDoituong() != null) && (this.mienGiam.getMiengiamDoituong().getDtdmdienmienMaso() != null))
       {
         DtDmDienMien dmDienMien = DtDmDienMienDelegate.getInstance().find(this.mienGiam.getMiengiamDoituong().getDtdmdienmienMaso());
         params.put("DOITUONGGIAM", dmDienMien.getDtdmdienmienTen());
       }
       else
       {
         params.put("DOITUONGGIAM", "");
       }
       log.info("NGUOIDUYET " + this.mienGiam.getMiengiamNguoiduyet());
       if ((this.mienGiam.getMiengiamNguoiduyet() != null) && (this.mienGiam.getMiengiamNguoiduyet().getDtdmnhanvienMaso() != null))
       {
         DtDmNhanVien dmNv = DtDmNhanVienDelegate.getInstance().find(this.mienGiam.getMiengiamNguoiduyet().getDtdmnhanvienMaso());
         log.info("NGUOIDUYET 2 " + dmNv.getDtdmnhanvienMaso() + " - " + dmNv.getDtdmnhanvienTen());

         params.put("NGUOIDUYET", dmNv.getDtdmnhanvienTen());
       }
       else
       {
         params.put("NGUOIDUYET", "");
       }
       params.put("SOPHIEU", this.mienGiam.getMiengiamMaphieu() == null ? "" : this.mienGiam.getMiengiamMaphieu());


       int lanIn = this.mienGiam.getMiengiamIn() == null ? 1 : Integer.valueOf(this.mienGiam.getMiengiamIn()).intValue() + 1;
       this.mienGiam.setMiengiamIn("" + lanIn);
       params.put("LANIN", "" + lanIn);

       this.reportTypeVP = "giaymiengiam";
       log.info("Vao Method XuatReport Giay mien giam");
       log.info("================= ");
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "giaymiengiam");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       setIndex(getIndex() + 1);
       log.info("Index :" + getIndex());

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       MienGiam mienGiamTmp = (MienGiam)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(this.mienGiam.getMiengiamMaphieu(), "MienGiam", "miengiamMaphieu");
       if (mienGiamTmp != null)
       {
         MienGiamDelegate mienGiamDelegate = MienGiamDelegate.getInstance();
         mienGiamTmp.setMiengiamIn("" + lanIn);
         mienGiamDelegate.edit(mienGiamTmp);
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
     return "B3360_Chonmenuxuattaptin";
   }

   public void layTheoMienGiamMa()
     throws Exception
   {
     log.info("***Starting layTheoMienGiamMa ***");
     this.disabledPrinting = true;
     if (this.mienGiam == null) {
       return;
     }
     String maPhieu = this.mienGiam.getMiengiamMaphieu();
     log.info("mienGiam.getMiengiamMaphieu()=" + maPhieu);
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     try
     {
       this.mienGiam = ((MienGiam)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(this.mienGiam.getMiengiamMaphieu(), "MienGiam", "miengiamMaphieu"));

       log.info("findByMaWithFormatMaPhieu ... end");
       log.info("mienGiam=" + this.mienGiam);
       if ((this.mienGiam != null) && (this.mienGiam.getHsbaSovaovien() != null))
       {
         this.nhanVienThungan = this.mienGiam.getMiengiamThungan();
         log.info("mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov()=" + this.mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov());
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, maPhieu });
       log.error(e.toString());
     }
     if ((this.mienGiam != null) && (this.mienGiam.getMiengiamKhoa() != null) && (this.mienGiam.getMiengiamKhoa().getDmkhoaMa() != null) && (this.mienGiam.getHsbaSovaovien() != null) && (this.mienGiam.getHsbaSovaovien().getHsbaSovaovien() != null))
     {
       qryBenhNhanBhtyThanhToan(this.mienGiam.getMiengiamKhoa().getDmkhoaMa(), this.mienGiam.getHsbaSovaovien().getHsbaSovaovien());
       if (this.disabledGhinhan) {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
       }
       this.maPhu = (this.mienGiam.getMiengiamMaphieu() + "");
       this.disabledPrinting = false;
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, maPhieu });
       this.benhNhan = null;
       this.hsbaBhyt = null;

       this.hosoThanhToanUtil = null;
     }
     log.info("***Finished layTheoMienGiamMa **");
   }

   private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien)
   {
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();



     log.info("hoSoBenhAn.getHsbaSovaovien():" + soVaoVien);

     HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
     if (hsbaKhoa == null)
     {
       log.info("hsbaKhoa1:" + hsbaKhoa);


       this.benhNhan = null;
       this.hsbaBhyt = null;

       return;
     }
     log.info("maKhoa:" + khoa);
     if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa()))
     {
       log.info("hsbaKhoa1:" + hsbaKhoa);


       this.benhNhan = null;
       this.hsbaBhyt = null;

       return;
     }
     this.mienGiam.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());
     soVaoVien = hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien();
     this.disabledGhinhan = checkDaTT(soVaoVien);
     if ((this.mienGiam != null) && (this.mienGiam.getHsbaSovaovien() != null)) {
       log.info("mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov()=" + this.mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov());
     }
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
     log.info("before tinhToanChoHSTT()");
     tinhToanChoHSTT(hsThtoan, hsbaKhoa.getHsbaSovaovien());
     log.info("After tinhToanChoHSTT()");
     Utils.setInforForHsThToan(hsThtoan);

     log.info("hsThtoan.getHsthtoanTamung(): " + hsThtoan.getHsthtoanTamung());
     log.info("hsThtoan.getHsthtoanHoanung():" + hsThtoan.getHsthtoanHoanung());

     this.tamUng_HoanUng = Utils.formatLongDouble(Double.valueOf(hsThtoan.getHsthtoanTamung().doubleValue() - hsThtoan.getHsthtoanHoanung().doubleValue()));
     log.info("ungTra:" + this.tamUng_HoanUng);

     this.mienDT = Utils.formatLongDouble(hsThtoan.getHsthtoanMiendt());
     log.info("hsThtoan.getHsthtoanMiendt():" + this.mienDT);


     this.soDu = Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan());
     System.out.println("CapNhatTienTamUngAction.qryBenhNhanBhtyThanhToan()*****soDu=" + this.soDu);
   }

   private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba)
   {
     HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
     hsthtoanUtil.tinhToanChoHSTT(hstt);
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     this.ghiNhanException = null;
     this.disabledPrinting = true;
     MienGiamDelegate mienGiamDelegate = MienGiamDelegate.getInstance();
     if (this.mienGiam != null) {
       try
       {
         if (this.mienGiam.getMiengiamDoituong(true).getDtdmdienmienMaso() == null) {
           this.mienGiam.setMiengiamDoituong(null);
         }
         if (this.mienGiam.getMiengiamLoaimien(true).getDtdmloaimienMaso() == null) {
           this.mienGiam.setMiengiamLoaimien(null);
         }
         if (this.mienGiam.getMiengiamNguoiduyet(true).getDtdmnhanvienMaso() == null) {
           this.mienGiam.setMiengiamNguoiduyet(null);
         }
         DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
         if (userNv != null)
         {
           PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
           PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(this.mienGiam.getMiengiamNgay(), userNv.getDtdmnhanvienMa());
           if (pcCumThuPhi != null) {
             this.mienGiam.setMiengiamCum(pcCumThuPhi.getDtdmcumMa());
           }
         }
         log.info("ma : " + this.mienGiam.getMiengiamDoituong(true).getDtdmdienmienMa());
         log.info("maso : " + this.mienGiam.getMiengiamDoituong(true).getDtdmdienmienMaso());
         if (this.mienGiam.getMiengiamMa() == null)
         {
           this.mienGiam.setMiengiamThungan(this.nhanVienThungan);
           this.mienGiam = mienGiamDelegate.create(this.mienGiam);
         }
         else
         {
           mienGiamDelegate.edit(this.mienGiam);
         }
         log.info("save miengiam thanh cong");
         HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
         HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(this.mienGiam.getHsbaSovaovien().getHsbaSovaovien());
         if (hsThtoan != null) {
           hsThtoanDelegate.remove(hsThtoan);
         }
         hsThtoan = new HsThtoan();
         hsThtoan.setHsbaSovaovien(this.mienGiam.getHsbaSovaovien());
         if (this.mienGiam.getMiengiamKhoa() != null) {
           hsThtoan.setHsthtoanKhoa(this.mienGiam.getMiengiamKhoa());
         }
         HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.mienGiam.getHsbaSovaovien());
         hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
         Utils.setInforForHsThToan(hsThtoan);
         hsThtoanDelegate.create(hsThtoan);

         FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.PHIEU_SO, this.mienGiam.getMiengiamMaphieu() });
         this.disabledPrinting = false;
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         log.error(e.toString());
         e.printStackTrace();
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
       }
     }
     log.info("disabledPrinting = " + this.disabledPrinting);
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
     this.mienGiam = new MienGiam();
     this.mienGiam.setMiengiamNgay(new Date());
     this.mienGiam.setMiengiamNgayky(new Date());
     this.benhNhan = null;
     this.hsbaBhyt = null;

     this.hosoThanhToanUtil = null;
     this.tamUng_HoanUng = null;
     this.mienDT = null;
     this.soDu = null;
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

   public String getTamUng_HoanUng()
   {
     return this.tamUng_HoanUng;
   }

   public void setTamUng_HoanUng(String tamUng_HoanUng)
   {
     this.tamUng_HoanUng = tamUng_HoanUng;
   }

   public String getMienDT()
   {
     return this.mienDT;
   }

   public void setMienDT(String mienDT)
   {
     this.mienDT = mienDT;
   }

   public String getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(String soDu)
   {
     this.soDu = soDu;
   }

   public String getSoTheBH_SoTheNgheo()
   {
     return this.soTheBH_SoTheNgheo;
   }

   public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo)
   {
     this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
   }

   public MienGiam getMienGiam()
   {
     return this.mienGiam;
   }

   public MienGiam getMienGiam(boolean create)
   {
     if ((create) && (this.mienGiam == null)) {
       this.mienGiam = new MienGiam();
     }
     return this.mienGiam;
   }

   public void setMienGiam(MienGiam mienGiam)
   {
     this.mienGiam = mienGiam;
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public HoSoThanhToanUtil getHosoThanhToanUtil()
   {
     return this.hosoThanhToanUtil;
   }

   public void setHosoThanhToanUtil(HoSoThanhToanUtil hosoThanhToanUtil)
   {
     this.hosoThanhToanUtil = hosoThanhToanUtil;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
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

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
   }

   public boolean getDisabledPrinting()
   {
     return this.disabledPrinting;
   }

   public void setDisabledPrinting(boolean disabledPrinting)
   {
     this.disabledPrinting = disabledPrinting;
   }

   public Date getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(Date ngayhientai)
   {
     this.ngayhientai = ngayhientai;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.CapNhatMienGiamAction

 * JD-Core Version:    0.7.0.1

 */