 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNgoaitruYhctDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
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
 import org.apache.commons.lang.time.DateUtils;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B211_3_CapNhatThongTinNgoaiTruYhct")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBANgoaiTruYhctAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBANgoaiTruYhctAction.class);
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintDT = null;
   @In(required=false)
   @Out(required=false)
   private String soBenhAn;
   @In(required=false)
   @Out(required=false)
   private String khoaMa;
   private String ghiNhanException;
   private String hoTen;
   private HsbaChuyenMon hsbaChuyenMon;
   private HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhct;
   private Hsba hosobenhan;
   private TiepDon tiepDon;
   private BenhNhan benhnhan;
   private int index = 0;
   private static final long ONE_HOUR = 3600000L;

   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init Chi tiet BA Ngoai Tru YHCT ***");

     HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
     this.hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);
     log.info("hsbaChuyenMon = " + this.hsbaChuyenMon);
     if (this.hsbaChuyenMon != null)
     {
       if (this.hsbaChuyenMon.getHsbaSovaovien() != null)
       {
         this.hosobenhan = this.hsbaChuyenMon.getHsbaSovaovien();
         if ((this.hosobenhan.getTiepdonMa() != null) && (!this.hosobenhan.getTiepdonMa().equals("")))
         {
           TiepDonDelegate tiepdonDel = TiepDonDelegate.getInstance();
           this.tiepDon = tiepdonDel.find(this.hosobenhan.getTiepdonMa());
           if (this.tiepDon == null) {
             this.tiepDon = new TiepDon();
           }
         }
         else
         {
           this.tiepDon = new TiepDon();
         }
         if (this.hosobenhan.getBenhnhanMa() != null) {
           this.benhnhan = this.hosobenhan.getBenhnhanMa();
         } else {
           this.benhnhan = new BenhNhan();
         }
         this.hoTen = this.benhnhan.getBenhnhanHoten();

         HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhctTemp = null;
         try
         {
           hsbaCTNgoaitruYhctTemp = HsbaChiTietNgoaitruYhctDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTNgoaitruYhctTemp != null) {
           this.hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhctTemp;
         } else {
           this.hsbaCTNgoaitruYhct = new HsbaChiTietNgoaitruYhct();
         }
       }
       else
       {
         this.hosobenhan = new Hsba();
         this.tiepDon = new TiepDon();
         this.benhnhan = new BenhNhan();
       }
     }
     else {
       return "DieuTri_CapNhat_CapNhatThongTinChung";
     }
     log.info("***Finished init **");
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoaitruYhct";
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");
     log.info("***Finished back **");
     return "DieuTri_CapNhat_CapNhatThongTinChung";
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan Chi tiet BA Ngoai Tru YHCT **");
     this.ghiNhanException = null;
     this.hsbaCTNgoaitruYhct.setHsbacmMa(this.hsbaChuyenMon);
     RemoveUtil.removeAllNullFromHSBACTNgoaitruYhct(this.hsbaCTNgoaitruYhct);
     if (this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctMa() == null) {
       HsbaChiTietNgoaitruYhctDelegate.getInstance().create(this.hsbaCTNgoaitruYhct);
     } else {
       HsbaChiTietNgoaitruYhctDelegate.getInstance().edit(this.hsbaCTNgoaitruYhct);
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
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
     this.ghiNhanException = null;
     log.info("End ResetForm(): ");
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "CapNhatThongTinChiTietBANgoaitruYhct";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an ngoaitru Yhct");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport0.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport1.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanngoaitruYhct_subreport2.jrxml";

       log.info("da thay file templete " + pathTemplate);
       log.info("da thay file sub 1 templete " + sub1Template);
       log.info("da thay file sub 2 templete " + sub2Template);
       log.info("da thay file sub 3 templete " + sub3Template);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);

       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);




       HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhctTemp = null;
       try
       {
         hsbaCTNgoaitruYhctTemp = HsbaChiTietNgoaitruYhctDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error:" + e);
       }
       if (hsbaCTNgoaitruYhctTemp != null) {
         this.hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhctTemp;
       }
       String sHoTenBN = "";
       if (this.benhnhan.getBenhnhanHoten() != null) {
         sHoTenBN = this.benhnhan.getBenhnhanHoten();
       }
       params.put("HOTENBN", sHoTenBN);

       int iTuoi = this.benhnhan.getBenhnhanTuoi().intValue();
       int iDonviTuoi = this.benhnhan.getBenhnhanDonvituoi().shortValue();
       String sDonViTuoi = "";
       if (iDonviTuoi == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (iDonviTuoi == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", iTuoi + " " + sDonViTuoi);

       params.put("GIOITINH", this.benhnhan.getDmgtMaso(true).getDmgtTen());

       String sNgheNghiep = "";
       if (this.benhnhan.getBenhnhanNghe() != null)
       {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
         params.put("NGHENGHIEP", sNgheNghiep);
       }
       String sDanToc = "";
       if (this.benhnhan.getDantocMa() != null)
       {
         sDanToc = this.benhnhan.getDantocMa(true).getDmdantocTen();
         params.put("DANTOC", sDanToc);
       }
       String sNgoaiKieu = "";
       if (this.benhnhan.getQuocgiaMa(true).getDmquocgiaMa() != null) {
         sNgoaiKieu = this.benhnhan.getQuocgiaMa(true).getDmquocgiaMa();
       }
       params.put("NGOAIKIEU", sNgoaiKieu);

       String diachistr = "";
       if (this.benhnhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhnhan.getBenhnhanDiachi();
       }
       if (this.benhnhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getXaMa(true).getDmxaTen();
       }
       if (this.benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getHuyenMa(true).getDmhuyenTen();
       }
       if (this.benhnhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       String sNgaySinh = "";
       if (this.benhnhan.getBenhnhanNgaysinh() != null) {
         sNgaySinh = sdf.format(this.benhnhan.getBenhnhanNgaysinh());
       } else if (this.benhnhan.getBenhnhanNamsinh() != null) {
         sNgaySinh = this.benhnhan.getBenhnhanNamsinh();
       }
       params.put("NGAYSINH", sNgaySinh);
       if (this.hosobenhan.getDoituongMa() != null) {
         params.put("DOITUONG", this.hosobenhan.getDoituongMa(true).getDmdoituongTen());
       }
       String sSoVaoVien = "";
       if (this.hosobenhan.getHsbaSovaovien() != null) {
         sSoVaoVien = this.hosobenhan.getHsbaSovaovien();
       }
       params.put("SOVAOVIEN", sSoVaoVien);

       HsbaBhytDelegate hsbabhyt = HsbaBhytDelegate.getInstance();
       HsbaBhyt objBHYT = hsbabhyt.findBySoVaoVienLastHsbaBhyt(sSoVaoVien);

       String sGiaTriTu = "";
       String sGiaTriDen = "";
       String sMaTheBHYT = "";
       String sNoiLamViec = "";
       if (objBHYT != null)
       {
         if (objBHYT.getHsbabhytGiatri0() != null) {
           sGiaTriTu = sdf.format(objBHYT.getHsbabhytGiatri0());
         }
         if (objBHYT.getHsbabhytGiatri1() != null) {
           sGiaTriDen = sdf.format(objBHYT.getHsbabhytGiatri1());
         }
         if (objBHYT.getHsbabhytSothebh() != null) {
           sMaTheBHYT = objBHYT.getHsbabhytSothebh();
         }
         if (objBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null) {
           sMaTheBHYT = sMaTheBHYT + " - " + objBHYT.getHsbabhytMakcb(true).getDmbenhvienMa();
         }
         if (objBHYT.getHsbabhytCoquanbh() != null) {
           sNoiLamViec = objBHYT.getHsbabhytCoquanbh();
         }
       }
       params.put("GTTU", sGiaTriTu);
       params.put("GTDEN", sGiaTriDen);
       params.put("MATHEBHYT", sMaTheBHYT);
       params.put("NOILAMVIEC", sNoiLamViec);

       String sKhiCanBaoTin = "";
       if (this.hosobenhan.getHsbaBaotin() != null) {
         sKhiCanBaoTin = this.hosobenhan.getHsbaBaotin();
       }
       params.put("BAOTIN", sKhiCanBaoTin);

       Date dVaoVien = new Date();
       Date dRaVien = new Date();
       String sVaoVienLuc = "";
       if (this.hosobenhan.getHsbaNgaygiovaov() != null)
       {
         dVaoVien = this.hosobenhan.getHsbaNgaygiovaov();
         sVaoVienLuc = Utils.getGioPhutNgay(this.hosobenhan.getHsbaNgaygiovaov());
       }
       params.put("NGAYGIOVAOVIEN", sVaoVienLuc);
       if (this.hosobenhan.getHsbaNgaygiorav() != null)
       {
         dRaVien = this.hosobenhan.getHsbaNgaygiorav();
         params.put("GIORAVIEN", Utils.getGioPhutNgay(dRaVien));
         params.put("SONGAYDT", Long.valueOf(daysBetween(dVaoVien, dRaVien)));
       }
       else
       {
         params.put("SONGAYDT", Long.valueOf(daysBetween(dVaoVien, new Date())));
       }
       String sDonViGoi = "";
       if (this.hosobenhan.getHsbaDonvigoi() != null) {
         sDonViGoi = this.hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
       }
       params.put("DONVIGOI", sDonViGoi);

       String chuandoan_noichuyenden = "";
       if (this.hosobenhan.getHsbaMachdoantuyent() != null)
       {
         chuandoan_noichuyenden = this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa() + " - ";
         chuandoan_noichuyenden = chuandoan_noichuyenden + this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_NOICHUYENDEN", chuandoan_noichuyenden);

       params.put("LYDOVAOVIEN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctLydovaov());
       params.put("QUATRINHBENHLY", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctQtbenhly());
       params.put("TIEUSUBENHBANTHAN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTiensubenhbt());
       params.put("TIEUSUBENHGIADINH", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTiensubenhgd());

       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());

       params.put("BOPHANBIBENH", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBophanbibenh());
       params.put("XETNGHIEMCANTHIET", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctXetnghiemcanthiet());
       if (this.hsbaChuyenMon.getKetquaMa() != null) {
         params.put("KETQUA", this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen());
       }
       String sBenhChinh = "";
       String sBenhKemTheo = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
       {
         sBenhChinh = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
         params.put("BENHCHINH_MA", this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa());
       }
       params.put("BENHCHINH", sBenhChinh);
       if (this.hsbaChuyenMon.getHsbacmBenhphu() != null)
       {
         sBenhKemTheo = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
         params.put("BENHPHU_MA", this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa());
       }
       params.put("BENHPHU", sBenhKemTheo);

       params.put("VONGCHAN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVongchan());
       params.put("VANCHAN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVanchan());
       params.put("VANCHAN2", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctVanchan2());
       params.put("THIETCHAN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctThietchan());
       params.put("CHANDOAN_BENHDANH", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanBenhdanh());
       params.put("CHANDOAN_BATCUONG", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanBatcuong());
       params.put("CHANDOAN_TANGPHU", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanTangphu());
       params.put("CHANDOAN_NGUYENNHAN", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanNguyennhan());
       params.put("DIEUTRI_PHEPCHUA", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhepchua());
       params.put("DIEUTRI_PHUONGTHUOC", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhuongthuoc());
       params.put("DIEUTRI_PHUONGHUYET", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriPhuonghuyet());
       params.put("DIEUTRI_XOABOP", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriXoabop());
       params.put("CHEDOANTAINHA", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriChedoantainha());
       params.put("CHEDOHOLYTAINHA", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctDieutriChedoholytainha());
       params.put("TIENLUONG", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctTienluong());


       params.put("KETQUACLSCHINH", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctKetquaclschinh());
       params.put("KETQUAGIAIPHAUBENH", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctKetquagiaiphaubenh());
       params.put("PHAPTRI_YHHD", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctPhaptriYhhd());
       params.put("PHAPTRI_YHCT", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctPhaptriYhct());
       params.put("CHANDOANRAVIEN_YHHD", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanravienYhhd());
       params.put("CHANDOANRAVIEN_YHCT", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctChandoanravienYhct());
       params.put("HDTCCDTT", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctHuongdtCdtt());
       if (this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBsdieutri() != null) {
         params.put("BACSIDT", this.hsbaCTNgoaitruYhct.getHsbactngoaitruYhctBsdieutri(true).getDtdmnhanvienTen());
       }
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);


         log.info("Da connect DATABASE");
         this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
         log.info("fillReport THANHCONG");
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
         e.printStackTrace();
       }
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBANgoaitruYhct");


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

   public Integer getSeconds(Date d)
   {
     long seconds = DateUtils.getFragmentInHours(d, 5) * 60L * 60L + DateUtils.getFragmentInMinutes(d, 11) * 60L;




     return Integer.valueOf(seconds + "");
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public String getReportPathTD()
   {
     return this.duongdanStrDT;
   }

   public void setReportPathTD(String reportPathTD)
   {
     this.duongdanStrDT = reportPathTD;
   }

   public String getReportTypeTD()
   {
     return this.loaiBCDT;
   }

   public void setReportTypeTD(String reportTypeTD)
   {
     this.loaiBCDT = reportTypeTD;
   }

   public JasperPrint getJasperPrintTD()
   {
     return this.jasperPrintDT;
   }

   public void setJasperPrintTD(JasperPrint jasperPrintTD)
   {
     this.jasperPrintDT = jasperPrintTD;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   private HsbaChuyenVien getHsbaChuyenVien()
   {
     HsbaChuyenVienDelegate hsbaChuyenVienDel = HsbaChuyenVienDelegate.getInstance();

     HsbaChuyenVien objhsbaChuyenVien = hsbaChuyenVienDel.findBySoVaoVien(this.soBenhAn);

     return objhsbaChuyenVien;
   }

   private static long daysBetween(Date d1, Date d2)
   {
     return (d2.getTime() - d1.getTime()) / 86400000L + 1L;
   }

   public HsbaChiTietNgoaitruYhct getHsbaCTNgoaitruYhct()
   {
     return this.hsbaCTNgoaitruYhct;
   }

   public void setHsbaCTNgoaitruYhct(HsbaChiTietNgoaitruYhct hsbaCTNgoaitruYhct)
   {
     this.hsbaCTNgoaitruYhct = hsbaCTNgoaitruYhct;
   }

   public String getHoTen()
   {
     return this.hoTen;
   }

   public void setHoTen(String hoTen)
   {
     this.hoTen = hoTen;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBANgoaiTruYhctAction

 * JD-Core Version:    0.7.0.1

 */