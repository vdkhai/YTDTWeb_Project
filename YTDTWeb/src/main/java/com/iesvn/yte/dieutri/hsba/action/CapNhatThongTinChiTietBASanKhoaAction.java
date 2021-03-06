 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietSankhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
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
 @Name("B211_3_CapNhatThongTinSanKhoa")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBASanKhoaAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBASanKhoaAction.class);
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
   private HsbaChiTietSankhoa hsbaCTSankhoa;
   private Hsba hosobenhan;
   private TiepDon tiepDon;
   private BenhNhan benhnhan;
   private String kinhcuoitungay;
   private String kinhcuoidenngay;
   private String chuandoan_ngayde;
   private String gioVoOi;
   private String ngayVoOi;
   private String gioChuyenda;
   private String ngayChuyenda;
   private String gioVaobuong;
   private String ngayVaobuong;
   private String gioDe;
   private String ngayDe;
   private String gioRauso;
   private String ngayRauso;
   private int index = 0;
   private static final long ONE_HOUR = 3600000L;

   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init chi tiet BA San khoa ***");

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

         HsbaChiTietSankhoa hsbaCTSankhoaTemp = null;
         try
         {
           hsbaCTSankhoaTemp = HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTSankhoaTemp != null)
         {
           this.hsbaCTSankhoa = hsbaCTSankhoaTemp;
           SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
           if (this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay() != null) {
             this.kinhcuoitungay = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay() != null) {
             this.kinhcuoidenngay = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaChuandoanngayde() != null) {
             this.chuandoan_ngayde = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaChuandoanngayde());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaOivoluc() != null)
           {
             this.gioVoOi = Utils.getGioPhut(this.hsbaCTSankhoa.getHsbactsankhoaOivoluc());
             this.ngayVoOi = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaOivoluc());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaChuyendaluc() != null)
           {
             this.gioChuyenda = Utils.getGioPhut(this.hsbaCTSankhoa.getHsbactsankhoaChuyendaluc());
             this.ngayChuyenda = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaChuyendaluc());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc() != null)
           {
             this.gioVaobuong = Utils.getGioPhut(this.hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc());
             this.ngayVaobuong = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaDeluc() != null)
           {
             this.gioDe = Utils.getGioPhut(this.hsbaCTSankhoa.getHsbactsankhoaDeluc());
             this.ngayDe = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaDeluc());
           }
           if (this.hsbaCTSankhoa.getHsbactsankhoaRausoluc() != null)
           {
             this.gioRauso = Utils.getGioPhut(this.hsbaCTSankhoa.getHsbactsankhoaRausoluc());
             this.ngayRauso = sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaRausoluc());
           }
         }
         else
         {
           this.hsbaCTSankhoa = new HsbaChiTietSankhoa();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBASankhoa";
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
     log.info("***Starting ghinhan Chi tiet BA San khoa ***");
     this.ghiNhanException = null;

     this.hsbaCTSankhoa.setHsbacmMa(this.hsbaChuyenMon);
     SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
     try
     {
       if ((this.kinhcuoitungay != null) && (!this.kinhcuoitungay.trim().equals(""))) {
         this.hsbaCTSankhoa.setHsbactsankhoaKinhcuoicungtungay(sdf.parse(this.kinhcuoitungay));
       }
       if ((this.kinhcuoidenngay != null) && (!this.kinhcuoidenngay.trim().equals(""))) {
         this.hsbaCTSankhoa.setHsbactsankhoaKinhcuoicungdenngay(sdf.parse(this.kinhcuoidenngay));
       }
       if ((this.chuandoan_ngayde != null) && (!this.chuandoan_ngayde.trim().equals(""))) {
         this.hsbaCTSankhoa.setHsbactsankhoaChuandoanngayde(sdf.parse(this.chuandoan_ngayde));
       }
       Calendar vooiluc = Utils.getDBDate(this.gioVoOi, this.ngayVoOi);
       if (vooiluc != null) {
         this.hsbaCTSankhoa.setHsbactsankhoaOivoluc(vooiluc.getTime());
       }
       Calendar chuyendaluc = Utils.getDBDate(this.gioChuyenda, this.ngayChuyenda);
       if (chuyendaluc != null) {
         this.hsbaCTSankhoa.setHsbactsankhoaChuyendaluc(chuyendaluc.getTime());
       }
       Calendar vaobuongluc = Utils.getDBDate(this.gioVaobuong, this.ngayVaobuong);
       if (vaobuongluc != null) {
         this.hsbaCTSankhoa.setHsbactsankhoaVaobuongdeluc(vaobuongluc.getTime());
       }
       Calendar deluc = Utils.getDBDate(this.gioDe, this.ngayDe);
       if (deluc != null) {
         this.hsbaCTSankhoa.setHsbactsankhoaDeluc(deluc.getTime());
       }
       Calendar rausoluc = Utils.getDBDate(this.gioRauso, this.ngayRauso);
       if (rausoluc != null) {
         this.hsbaCTSankhoa.setHsbactsankhoaRausoluc(rausoluc.getTime());
       }
     }
     catch (ParseException e)
     {
       log.info("*** PARSE NGAY THANG BI ERROR **");e.printStackTrace();
     }
     RemoveUtil.removeAllNullFromHSBACTSankhoa(this.hsbaCTSankhoa);
     if (this.hsbaCTSankhoa.getHsbactsankhoaMa() == null) {
       HsbaChiTietSankhoaDelegate.getInstance().create(this.hsbaCTSankhoa);
     } else {
       HsbaChiTietSankhoaDelegate.getInstance().edit(this.hsbaCTSankhoa);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBASankhoa";
     log.info("Vao Method XuatReport bao cao cap nhat thong tin chi tiet benh an sankhoa");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport0.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport1.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport2.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N07_benhansankhoa_subreport3.jrxml";

       log.info("da thay file templete " + pathTemplate);
       log.info("da thay file sub 1 templete " + sub1Template);
       log.info("da thay file sub 2 templete " + sub2Template);
       log.info("da thay file sub 3 templete " + sub3Template);
       log.info("da thay file sub 4 templete " + sub4Template);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
       JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);

       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);
       params.put("sub4", sub4Report);


       HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();


       HsbaChiTietSankhoa hsbaCTSankhoaTemp = null;
       try
       {
         hsbaCTSankhoaTemp = HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietSankhoaDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTSankhoaTemp != null) {
         this.hsbaCTSankhoa = hsbaCTSankhoaTemp;
       }
       String sMaTiepDon = "";
       if (this.hosobenhan.getTiepdonMa() != null) {
         sMaTiepDon = this.hosobenhan.getTiepdonMa();
       }
       params.put("MATIEPDON", sMaTiepDon);

       String sHoTenBN = "";
       if (this.benhnhan.getBenhnhanHoten() != null) {
         sHoTenBN = this.benhnhan.getBenhnhanHoten();
       }
       params.put("HOTENBN", sHoTenBN);

       params.put("BUONG", this.hsbaChuyenMon.getHsbacmSobuong());
       params.put("GIUONG", this.hsbaChuyenMon.getHsbacmSogiuong());

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

       params.put("SOVAOVIEN", this.hosobenhan.getHsbaSovaovien());

       int iTuoi = this.benhnhan.getBenhnhanTuoi().intValue();
       int iDonviTuoi = this.benhnhan.getBenhnhanDonvituoi().shortValue();
       String sDonViTuoi = "";
       if (iDonviTuoi == 1) {
         sDonViTuoi = "";
       } else if (iDonviTuoi == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (iDonviTuoi == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", iTuoi + " " + sDonViTuoi);

       params.put("GIOITINH", this.benhnhan.getDmgtMaso(true).getDmgtTen());

       String sNgaySinh = "";
       if (this.benhnhan.getBenhnhanNgaysinh() != null) {
         sNgaySinh = sdf.format(this.benhnhan.getBenhnhanNgaysinh());
       } else if (this.benhnhan.getBenhnhanNamsinh() != null) {
         sNgaySinh = this.benhnhan.getBenhnhanNamsinh();
       }
       params.put("NGAYSINH", sNgaySinh);

       String sDanToc = "";
       if (this.benhnhan.getDantocMa(true).getDmdantocTen() != null)
       {
         sDanToc = this.benhnhan.getDantocMa(true).getDmdantocTen();
         params.put("DANTOC", sDanToc);
         params.put("DANTOCMA", this.benhnhan.getDantocMa(true).getDmdantocMa());
       }
       String sDoiTuong = "";
       if (this.hosobenhan.getDoituongMa(true).getDmdoituongMa() != null)
       {
         sDoiTuong = this.hosobenhan.getDoituongMa(true).getDmdoituongMa();
         if (sDoiTuong.equals("BH")) {
           sDoiTuong = "1";
         } else if (sDoiTuong.equals("MP")) {
           sDoiTuong = "3";
         } else {
           sDoiTuong = "2";
         }
       }
       params.put("DOITUONG", sDoiTuong);

       String sNgheNghiep = "";
       if (this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null)
       {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
         params.put("NGHENGHIEP", sNgheNghiep);
         params.put("NGHENGHIEPMA", this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepMa());
       }
       String sNgoaiKieu = "";
       if (this.benhnhan.getQuocgiaMa(true).getDmquocgiaMa() != null) {
         sNgoaiKieu = this.benhnhan.getQuocgiaMa(true).getDmquocgiaMa();
       }
       params.put("NGOAIKIEU", sNgoaiKieu);

       String sKhiCanBaoTin = "";
       if (this.hosobenhan.getHsbaBaotin() != null) {
         sKhiCanBaoTin = this.hosobenhan.getHsbaBaotin();
       }
       params.put("BAOTIN", sKhiCanBaoTin);






       String sSoVaoVien = "";
       if (this.hosobenhan.getHsbaSovaovien() != null) {
         sSoVaoVien = this.hosobenhan.getHsbaSovaovien();
       }
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
       String sTiepnhantai = "2";
       if (this.tiepDon.getTiepdonBankham() != null) {
         sTiepnhantai = this.tiepDon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC") ? "1" : "2";
       } else {
         sTiepnhantai = this.hosobenhan.getHsbaKhoavaov(true).getDmkhoaMa().startsWith("CC") ? "1" : "2";
       }
       params.put("TIEPNHANTAI", sTiepnhantai);





























       List<HsbaChuyenMon> listHSBAChuyenKhoa = hsbadel.findBySoVaoVien(this.soBenhAn);
       if ((listHSBAChuyenKhoa != null) && (listHSBAChuyenKhoa.size() > 0)) {
         for (int i = 0; (i < listHSBAChuyenKhoa.size()) && (i <= 3); i++)
         {
           String makhoa = "";
           String ngaygiovaok = "";
           if (((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getKhoaMa() != null)
           {
             makhoa = ((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getKhoaMa(true).getDmkhoaMa();
             params.put("CHUYENKHOA_" + i, makhoa);
           }
           if (((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiovaok() != null)
           {
             ngaygiovaok = Utils.getGioPhutNgay(((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiovaok());
             params.put("CHUYENKHOALUC_" + i, ngaygiovaok);
             if (((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiorak() != null) {
               params.put("SONGAYDTKHOA_" + i, Long.valueOf(daysBetween(((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiovaok(), ((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiorak())));
             }
           }
         }
       }
       String sDonViGoi = "";
       if (this.hosobenhan.getHsbaDonvigoi() != null)
       {
         sDonViGoi = this.hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
         params.put("NOIGIOITHIEU", "1");
       }
       else
       {
         params.put("NOIGIOITHIEU", "2");
       }
       params.put("DONVIGOI", sDonViGoi);
       if (getHsbaChuyenVien() != null)
       {
         HsbaChuyenVien hsbacv = getHsbaChuyenVien();
         params.put("CHUYENVIEN", hsbacv.getHsbacvChvienden(true).getDmbenhvienTen());
       }
       else
       {
         params.put("CHUYENVIEN", "");
       }
       String maTuyenduoi = "";
       String tenTuyenduoi = "";
       if (this.hosobenhan.getHsbaMachdoantuyent() != null)
       {
         maTuyenduoi = this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa();
         tenTuyenduoi = this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_NOICHUYENDEN_MA", maTuyenduoi);
       params.put("CHUANDOAN_NOICHUYENDEN", tenTuyenduoi);


       String maChandoanCC = "";
       String tenChandoanCC = "";
       if (this.hosobenhan.getHsbaMachdoanbd() != null)
       {
         maChandoanCC = this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdMa();
         tenChandoanCC = this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_CAPCUU_MA", maChandoanCC);
       params.put("CHUANDOAN_CAPCUU", tenChandoanCC);


       String maChuanDoanVaoKhoa = "";
       String tenChuanDoanVaoKhoa = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
       {
         maChuanDoanVaoKhoa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
         tenChuanDoanVaoKhoa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_VAOKHOA_MA", maChuanDoanVaoKhoa);
       params.put("CHUANDOAN_VAOKHOA", tenChuanDoanVaoKhoa);




       String sBenhChinhMa = "";
       String sBenhChinh = "";
       String sBenhPhuMa = "";
       String sBenhKemTheo = "";
       if (this.hosobenhan.getHsbaKhoarav() != null)
       {
         if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
         {
           sBenhChinh = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
           sBenhChinhMa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
         }
         if (this.hsbaChuyenMon.getHsbacmBenhphu() != null)
         {
           sBenhKemTheo = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
           sBenhPhuMa = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa();
         }
       }
       params.put("BENHCHINH", sBenhChinh);
       params.put("BENHCHINHMA", sBenhChinhMa);
       params.put("BENHPHU", sBenhKemTheo);
       params.put("BENHPHUMA", sBenhPhuMa);






       String maKetQua = "";
       String tenKetQua = "";
       int iKetqua = 0;
       if (this.hsbaChuyenMon.getKetquaMa() != null)
       {
         iKetqua = this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso().intValue();
         maKetQua = "" + iKetqua;
         tenKetQua = this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen();
         params.put("KETQUA", maKetQua);
         params.put("KETQUA_TEN", tenKetQua);
       }
       String sNguyenNhanTuVong = "";
       String sNguyenNhanTuVongMa = "";
       if (this.hosobenhan.getHsbaTuvong() != null)
       {
         sNguyenNhanTuVong = this.hosobenhan.getHsbaTuvong(true).getDmbenhicdTen();
         sNguyenNhanTuVongMa = this.hosobenhan.getHsbaTuvong(true).getDmbenhicdMa();
         params.put("NGUYENNHANTUVONG", sNguyenNhanTuVong);
         params.put("NGUYENNHANTUVONGMA", sNguyenNhanTuVongMa);
       }
       String sNgayGioTuVong = "";
       if (this.hosobenhan.getHsbaNgaygiotv() != null)
       {
         sNgayGioTuVong = Utils.getGioPhutNgay(this.hosobenhan.getHsbaNgaygiotv());
         params.put("NGAYGIOTUVONG", sNgayGioTuVong);
       }
       if (this.hsbaChuyenMon.getHsbacmLathuthuat() != null) {
         params.put("CHUANDOAN_COTHUTHUAT", "X");
       }
       if (this.hsbaChuyenMon.getHsbacmLaphauthuat() != null) {
         params.put("CHUANDOAN_COPHAUTHUAT", "X");
       }
       if ((this.hsbaChuyenMon.getHsbacmBienchung() != null) && (!this.hsbaChuyenMon.getHsbacmBienchung().trim().equals(""))) {
         params.put("CHUANDOAN_COBIENCHUNG", "X");
       }
       if ((this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso() != null) && (this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtMaso().intValue() == 5)) {
         if (this.hsbaChuyenMon.getHsbaSovaovien(true).getHsbaTuvvong24g().booleanValue()) {
           params.put("TUVONGTRONG24H", "X");
         } else {
           params.put("TUVONGSAU24H", "X");
         }
       }
       HsbaPhieuPhauThuatThuThuat ppttt = null;
       HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate = HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
       ppttt = hsbaPhieuPhauThuatThuThuatDelegate.findByHsba(this.hosobenhan.getHsbaSovaovien());
       if (ppttt != null)
       {
         params.put("LAN_PHAUTHUAT", "1");
         if (ppttt.getHsbapptttChuandoanmaTruoc() != null)
         {
           params.put("CHUANDOAN_TRUOCPT", ppttt.getHsbapptttChuandoanmaTruoc().getDmbenhicdMa());
           params.put("CHUANDOAN_TRUOCPT_TEN", ppttt.getHsbapptttChuandoanmaTruoc().getDmbenhicdTen());
         }
         if (ppttt.getHsbapptttChuandoanmaSau() != null)
         {
           params.put("CHUANDOAN_SAUPT", ppttt.getHsbapptttChuandoanmaSau().getDmbenhicdMa());
           params.put("CHUANDOAN_SAUPT_TEN", ppttt.getHsbapptttChuandoanmaSau().getDmbenhicdTen());
         }
         if (ppttt.getHsbapptttNgaypttt() != null)
         {
           params.put("NGAY_PHAUTHUAT", ppttt.getHsbapptttNgaypttt());
           if (this.hosobenhan.getHsbaNgaygiovaov() != null) {
             params.put("NGAYDT_TRUOC_PHAUTHUAT", Long.valueOf(daysBetween(this.hosobenhan.getHsbaNgaygiovaov(), ppttt.getHsbapptttNgaypttt())));
           }
         }
         if (ppttt.getHsbapptttPhuongphap() != null) {
           params.put("PHUONGPHAP_PHAUTHUAT", ppttt.getHsbapptttPhuongphap().getDtdmclsbgDiengiai());
         }
         if (ppttt.getHsbapptttPhuongphapVocam() != null) {
           params.put("PHUONGPHAP_VOCAM", ppttt.getHsbapptttPhuongphapVocam().getDtdmvocamDiengiai());
         }
         List<DtDmNhanVien> bacsigaymeList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(ppttt.getHsbapptttMa());
         if ((bacsigaymeList != null) && (bacsigaymeList.size() > 0)) {
           params.put("BACSI_GAYME", ((DtDmNhanVien)bacsigaymeList.get(0)).getDtdmnhanvienTen());
         }
         List<DtDmNhanVien> bacsiList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(ppttt.getHsbapptttMa());
         if ((bacsiList != null) && (bacsiList.size() > 0)) {
           params.put("PHAUTHUATVIEN", ((DtDmNhanVien)bacsiList.get(0)).getDtdmnhanvienTen());
         }
       }
       else
       {
         params.put("LAN_PHAUTHUAT", "0");
       }
       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());


       params.put("CHUANDOAN_NGAYDE", this.chuandoan_ngayde);
       params.put("CHUANDOAN_NGOITHAI", this.hsbaCTSankhoa.getHsbactsankhoaChuandoanngoithai());
       params.put("CHUANDOAN_CACHTHUCDE", this.hsbaCTSankhoa.getHsbactsankhoaChuandoancachthucde());
       params.put("CHUANDOAN_KIEMSOATTUCUNG", this.hsbaCTSankhoa.getHsbactsankhoaChuandoankiemsoattucung());
       params.put("CHUANDOAN_DITAT", this.hsbaCTSankhoa.getHsbactsankhoaChuandoanditat());
       params.put("CHUANDOAN_CANNANG", this.hsbaCTSankhoa.getHsbactsankhoaChuandoancannang());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatcapcuu() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatcapcuu().booleanValue() == true)) {
         params.put("CHUANDOAN_PHAUTHUATCAPCUU", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatchudong() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoanphauthuatchudong().booleanValue() == true)) {
         params.put("CHUANDOAN_PHAUTHUATCHUDONG", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoandonthai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoandonthai().booleanValue() == true)) {
         params.put("CHUANDOAN_DONTHAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoandathai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoandathai().booleanValue() == true)) {
         params.put("CHUANDOAN_DATHAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoantrai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoantrai().booleanValue() == true)) {
         params.put("CHUANDOAN_TRAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoangai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoangai().booleanValue() == true)) {
         params.put("CHUANDOAN_GAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoansong() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoansong().booleanValue() == true)) {
         params.put("CHUANDOAN_SONG", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChuandoanchet() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChuandoanchet().booleanValue() == true)) {
         params.put("CHUANDOAN_CHET", "X");
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTSankhoa.getHsbactsankhoaLydovaov());
       }
       params.put("NGAYTHUBENH", this.hsbaCTSankhoa.getHsbactsankhoaNgaythubenh());
       if (this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay() != null) {
         params.put("KINHCUOITUNGAY", sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungtungay()));
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay() != null) {
         params.put("KINHCUOIDENNGAY", sdf.format(this.hsbaCTSankhoa.getHsbactsankhoaKinhcuoicungdenngay()));
       }
       params.put("TUOITHAITUAN", this.hsbaCTSankhoa.getHsbactsankhoaTuoithaituan());
       params.put("KHAMTHAITAI", this.hsbaCTSankhoa.getHsbactsankhoaKhamthaitai());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTiemphonguonvan() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTiemphonguonvan().booleanValue() == true)) {
         params.put("COTIEMPHONGUONGVAN", "X");
       }
       params.put("SOLANTIEMUONGVAN", this.hsbaCTSankhoa.getHsbactsankhoaSolantiemuonvan());
       if (this.hsbaCTSankhoa.getHsbactsankhoaChuyendaluc() != null) {
         params.put("LUCCHUYENDA", Utils.getGioPhutNgay(this.hsbaCTSankhoa.getHsbactsankhoaChuyendaluc()));
       }
       params.put("DAUHIEULUCDAU", this.hsbaCTSankhoa.getHsbactsankhoaDauhieulucdau());
       params.put("BIENCHUYEN", this.hsbaCTSankhoa.getHsbactsankhoaChuyenbien());
       if (this.hsbaCTSankhoa.getHsbactsankhoaTiensubenhbt() != null) {
         params.put("TSBBANTHAN", this.hsbaCTSankhoa.getHsbactsankhoaTiensubenhbt());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaTiensubenhgd() != null) {
         params.put("GIADINH", this.hsbaCTSankhoa.getHsbactsankhoaTiensubenhgd());
       }
       params.put("THAYKINHNAM", this.hsbaCTSankhoa.getHsbactsankhoaThaykinhnam());
       params.put("THAYKINHTUOI", this.hsbaCTSankhoa.getHsbactsankhoaThaykinhtuoi());
       params.put("TINHCHATKINHNGUYET", this.hsbaCTSankhoa.getHsbactsankhoaTinhchatkinhnguyet());
       params.put("CHUKY", this.hsbaCTSankhoa.getHsbactsankhoaChukykinh());
       params.put("LUONGKINH", this.hsbaCTSankhoa.getHsbactsankhoaLuongkinh());
       params.put("LAYCHONGNAM", this.hsbaCTSankhoa.getHsbactsankhoaLaychongnam());
       params.put("LAYCHONGTUOI", this.hsbaCTSankhoa.getHsbactsankhoaLaychongtuoi());
       params.put("BENHPHUKHOADADIEUTRI", this.hsbaCTSankhoa.getHsbactsankhoaBenhsankhoadadieutri());
       params.put("TINHTRANG", this.hsbaCTSankhoa.getHsbactsankhoaTinhtrang());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTinhtrangphu() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTinhtrangphu().booleanValue() == true)) {
         params.put("COPHU", "X");
       }
       params.put("TUANHOAN", this.hsbaCTSankhoa.getHsbactsankhoaTuanhoan());
       params.put("HOHAP", this.hsbaCTSankhoa.getHsbactsankhoaHohap());
       params.put("TIEUHOA", this.hsbaCTSankhoa.getHsbactsankhoaTieuhoa());
       params.put("TIETNIEU", this.hsbaCTSankhoa.getHsbactsankhoaThantietnieusinhhoc());
       params.put("BOPHANKHAC", this.hsbaCTSankhoa.getHsbactsankhoaCoquankhac());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaBungcoseophauthuatcu() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaBungcoseophauthuatcu().booleanValue() == true)) {
         params.put("COSEOPHAUTHUATCU", "X");
       }
       params.put("HINHDANGTUCUNG", this.hsbaCTSankhoa.getHsbactsankhoaHinhdangtc());
       params.put("TUTHE", this.hsbaCTSankhoa.getHsbactsankhoaTuthetc());
       params.put("CHIEUCAOTC", this.hsbaCTSankhoa.getHsbactsankhoaChieucaotc());
       params.put("VONGBUNG", this.hsbaCTSankhoa.getHsbactsankhoaVongbung());
       params.put("CONCOTC", this.hsbaCTSankhoa.getHsbactsankhoaConcotc());
       params.put("TIMTHAI", this.hsbaCTSankhoa.getHsbactsankhoaTimthai());
       params.put("VU", this.hsbaCTSankhoa.getHsbactsankhoaVu());
       params.put("DUONGKINHNGOAIKHUNGCHAU", this.hsbaCTSankhoa.getHsbactsankhoaDkngoaikhungchau());
       params.put("BISHOP", this.hsbaCTSankhoa.getHsbactsankhoaBishop());
       params.put("AMHO", this.hsbaCTSankhoa.getHsbactsankhoaAmho());
       params.put("TANGSINHMON", this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmon());
       params.put("AMDAO", this.hsbaCTSankhoa.getHsbactsankhoaAmdao());
       params.put("COTUCUNG", this.hsbaCTSankhoa.getHsbactsankhoaCotucung());
       params.put("PHANPHU", this.hsbaCTSankhoa.getHsbactsankhoaPhanphu());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaOiphong() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaOiphong().booleanValue() == true)) {
         params.put("OIPHONG", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaOidet() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaOidet().booleanValue() == true)) {
         params.put("OIDET", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaOiquale() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaOiquale().booleanValue() == true)) {
         params.put("OIQUALE", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaOivotunhien() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaOivotunhien().booleanValue() == true)) {
         params.put("OIVOTUNHIEN", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaOivobamoi() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaOivobamoi().booleanValue() == true)) {
         params.put("OIVOBAMOI", "X");
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaOivoluc() != null) {
         params.put("VOOILUC", Utils.getGioPhutNgay(this.hsbaCTSankhoa.getHsbactsankhoaOivoluc()));
       }
       params.put("MAUSACNUOCOI", this.hsbaCTSankhoa.getHsbactsankhoaMausacnuocoi());
       params.put("NUOCOINHIEUHAYIT", this.hsbaCTSankhoa.getHsbactsankhoaNuocoinhieuhayit());
       params.put("NGOI", this.hsbaCTSankhoa.getHsbactsankhoaNgoi());
       params.put("THE", this.hsbaCTSankhoa.getHsbactsankhoaThe());
       params.put("KIEUTHE", this.hsbaCTSankhoa.getHsbactsankhoaKieuthe());
       params.put("DUONGKINHNHOHAVE", this.hsbaCTSankhoa.getHsbactsankhoaDknhohave());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDolotcao() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDolotcao().booleanValue() == true)) {
         params.put("DOLOT_CAO", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDolotchuc() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDolotchuc().booleanValue() == true)) {
         params.put("DOLOT_CHUC", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDolotchat() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDolotchat().booleanValue() == true)) {
         params.put("DOLOT_CHAT", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDolotlot() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDolotlot().booleanValue() == true)) {
         params.put("DOLOT_LOT", "X");
       }
       params.put("XETNGHIEMCLS", this.hsbaCTSankhoa.getHsbactsankhoaXetnghiemcanlam());
       params.put("TIENLUONG", this.hsbaCTSankhoa.getHsbactsankhoaTienluong());
       params.put("HUONGDIEUTRI", this.hsbaCTSankhoa.getHsbactsankhoaHuongdieutri());
       if (this.hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc() != null) {
         params.put("VAOBUONGDELUC", Utils.getGioPhutNgay(this.hsbaCTSankhoa.getHsbactsankhoaVaobuongdeluc()));
       }
       params.put("TENNGUOITHEODOI", this.hsbaCTSankhoa.getHsbactsankhoaTennguoitheodoi());
       params.put("CHUCDANH", this.hsbaCTSankhoa.getHsbactsankhoaChucdanhnguoitheodoi());
       if (this.hsbaCTSankhoa.getHsbactsankhoaDeluc() != null) {
         params.put("DELUC", Utils.getGioPhutNgay(this.hsbaCTSankhoa.getHsbactsankhoaDeluc()));
       }
       params.put("APGAR1PHUT", this.hsbaCTSankhoa.getHsbactsankhoaApgar1phut());
       params.put("APGAR5PHUT", this.hsbaCTSankhoa.getHsbactsankhoaApgar5phut());
       params.put("APGAR10PHUT", this.hsbaCTSankhoa.getHsbactsankhoaApgar10phut());
       params.put("CANNANGTHAI", this.hsbaCTSankhoa.getHsbactsankhoaCannang());
       params.put("CAO", this.hsbaCTSankhoa.getHsbactsankhoaCao());
       params.put("VONGDAU", this.hsbaCTSankhoa.getHsbactsankhoaVongdau());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDonthaitrai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDonthaitrai().booleanValue() == true)) {
         params.put("DONTHAITRAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDonthaigai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDonthaigai().booleanValue() == true)) {
         params.put("DONTHAIGAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDathaitrai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDathaitrai().booleanValue() == true)) {
         params.put("DATHAITRAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDathaigai() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDathaigai().booleanValue() == true)) {
         params.put("DATHAIGAI", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTatbamsinh() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTatbamsinh().booleanValue() == true)) {
         params.put("TATBAMSINH", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaCohaumon() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaCohaumon().booleanValue() == true)) {
         params.put("COHAUMON", "X");
       }
       params.put("CUTHETATBAMSINH", this.hsbaCTSankhoa.getHsbactsankhoaCuthetatbamsinh());
       params.put("TTSS_SAUDE", this.hsbaCTSankhoa.getHsbactsankhoaTtsssaude());
       params.put("XULY_KETQUA", this.hsbaCTSankhoa.getHsbactsankhoaXulyketqua());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaRauboc() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaRauboc().booleanValue() == true)) {
         params.put("RAU_BOC", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaRauso() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaRauso().booleanValue() == true)) {
         params.put("RAU_SO", "X");
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaRausoluc() != null) {
         params.put("RAUSOLUC", Utils.getGioPhutNgay(this.hsbaCTSankhoa.getHsbactsankhoaRausoluc()));
       }
       params.put("CACHSORAU", this.hsbaCTSankhoa.getHsbactsankhoaCachsorau());
       params.put("MATMANG", this.hsbaCTSankhoa.getHsbactsankhoaMatmang());
       params.put("MATMUI", this.hsbaCTSankhoa.getHsbactsankhoaMatmui());
       params.put("BANHRAU", this.hsbaCTSankhoa.getHsbactsankhoaBanhrau());
       params.put("BANHRAU_CANNANG", this.hsbaCTSankhoa.getHsbactsankhoaBanhraucannang());
       params.put("CUONGRAUDAI", this.hsbaCTSankhoa.getHsbactsankhoaCuongraudai());
       params.put("LUONGMAUMAT", this.hsbaCTSankhoa.getHsbactsankhoaLuongmaumat());
       if ((this.hsbaCTSankhoa.getHsbactsankhoaRaucuonco() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaRaucuonco().booleanValue() == true)) {
         params.put("RAUCUONCO", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaChaymausauso() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaChaymausauso().booleanValue() == true)) {
         params.put("COCHAYMAUSAUSO", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaKiemsoattucung() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaKiemsoattucung().booleanValue() == true)) {
         params.put("KIEMSOATTUCUNG", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDethuong() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDethuong().booleanValue() == true)) {
         params.put("DETHUONG", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDechihuy() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDechihuy().booleanValue() == true)) {
         params.put("DECHIHUY", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaForceps() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaForceps().booleanValue() == true)) {
         params.put("FORCEPS", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDekhac() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDekhac().booleanValue() == true)) {
         params.put("TTKHAC", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaDephauthuat() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaDephauthuat().booleanValue() == true)) {
         params.put("DEPHAUTHUAT", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaGiachut() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaGiachut().booleanValue() == true)) {
         params.put("GIACHUT", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonKhongrach() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonKhongrach().booleanValue() == true)) {
         params.put("TANGSINHMON_KHONGRACH", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonRach() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonRach().booleanValue() == true)) {
         params.put("TANGSINHMON_RACH", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonCat() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaTangsinhmonCat().booleanValue() == true)) {
         params.put("TANGSINHMON_CAT", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaCotucungKhongrach() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaCotucungKhongrach().booleanValue() == true)) {
         params.put("COTUCUNG_KHONGRACH", "X");
       }
       if ((this.hsbaCTSankhoa.getHsbactsankhoaCotucungRach() != null) && (this.hsbaCTSankhoa.getHsbactsankhoaCotucungRach().booleanValue() == true)) {
         params.put("COTUCUNG_RACH", "X");
       }
       params.put("RAU_XULY_KETQUA", this.hsbaCTSankhoa.getHsbactsankhoaRauXulyketqua());
       params.put("DANIEMMAC", this.hsbaCTSankhoa.getHsbactsankhoaDaniemmac());
       params.put("LYDOCANTHIEP", this.hsbaCTSankhoa.getHsbactsankhoaLydocanthiep());
       params.put("PPKHAU_LOAICHI", this.hsbaCTSankhoa.getHsbactsankhoaPpkhauvaloaichi());
       params.put("SOMUIKHAU", this.hsbaCTSankhoa.getHsbactsankhoaSomuikhau());
       if ((this.hsbaChuyenMon.getHsbacmBienchung() != null) && (!this.hsbaChuyenMon.getHsbacmBienchung().trim().equals(""))) {
         params.put("COBIENCHUNG", "X");
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaBslamba() != null) {
         params.put("BACSILAMBA", this.hsbaCTSankhoa.getHsbactsankhoaBslamba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTSankhoa.getHsbactsankhoaHuongdtCdtt());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTSankhoa.getHsbactsankhoaSotoxquang());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTSankhoa.getHsbactsankhoaSotoctscanner());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTSankhoa.getHsbactsankhoaSotosieuam());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTSankhoa.getHsbactsankhoaSotoxn());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTSankhoa.getHsbactsankhoaSotokhac());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTSankhoa.getHsbactsankhoaTongsoto());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaNguoigiaoba() != null) {
         params.put("NGUOIGIAOBA", this.hsbaCTSankhoa.getHsbactsankhoaNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaNguoinhanba() != null) {
         params.put("NGUOINHANBA", this.hsbaCTSankhoa.getHsbactsankhoaNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTSankhoa.getHsbactsankhoaBsdieutri() != null) {
         params.put("BASIDT", this.hsbaCTSankhoa.getHsbactsankhoaBsdieutri(true).getDtdmnhanvienTen());
       }
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);


         this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
         log.info("fillReport THANHCONG");
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
         e.printStackTrace();
       }
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBASankhoa");


       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   public HsbaChiTietSankhoa getHsbaCTSankhoa()
   {
     return this.hsbaCTSankhoa;
   }

   public void setHsbaCTSankhoa(HsbaChiTietSankhoa hsbaCTSankhoa)
   {
     this.hsbaCTSankhoa = hsbaCTSankhoa;
   }

   public String getHoTen()
   {
     return this.hoTen;
   }

   public void setHoTen(String hoTen)
   {
     this.hoTen = hoTen;
   }

   public String getKinhcuoitungay()
   {
     return this.kinhcuoitungay;
   }

   public void setKinhcuoitungay(String kinhcuoitungay)
   {
     this.kinhcuoitungay = kinhcuoitungay;
   }

   public String getKinhcuoidenngay()
   {
     return this.kinhcuoidenngay;
   }

   public void setKinhcuoidenngay(String kinhcuoidenngay)
   {
     this.kinhcuoidenngay = kinhcuoidenngay;
   }

   public String getGioVoOi()
   {
     return this.gioVoOi;
   }

   public void setGioVoOi(String gioVoOi)
   {
     this.gioVoOi = gioVoOi;
   }

   public String getNgayVoOi()
   {
     return this.ngayVoOi;
   }

   public void setNgayVoOi(String ngayVoOi)
   {
     this.ngayVoOi = ngayVoOi;
   }

   public String getGioChuyenda()
   {
     return this.gioChuyenda;
   }

   public void setGioChuyenda(String gioChuyenda)
   {
     this.gioChuyenda = gioChuyenda;
   }

   public String getNgayChuyenda()
   {
     return this.ngayChuyenda;
   }

   public void setNgayChuyenda(String ngayChuyenda)
   {
     this.ngayChuyenda = ngayChuyenda;
   }

   public String getGioVaobuong()
   {
     return this.gioVaobuong;
   }

   public void setGioVaobuong(String gioVaobuong)
   {
     this.gioVaobuong = gioVaobuong;
   }

   public String getNgayVaobuong()
   {
     return this.ngayVaobuong;
   }

   public void setNgayVaobuong(String ngayVaobuong)
   {
     this.ngayVaobuong = ngayVaobuong;
   }

   public String getGioDe()
   {
     return this.gioDe;
   }

   public void setGioDe(String gioDe)
   {
     this.gioDe = gioDe;
   }

   public String getNgayDe()
   {
     return this.ngayDe;
   }

   public void setNgayDe(String ngayDe)
   {
     this.ngayDe = ngayDe;
   }

   public String getGioRauso()
   {
     return this.gioRauso;
   }

   public void setGioRauso(String gioRauso)
   {
     this.gioRauso = gioRauso;
   }

   public String getNgayRauso()
   {
     return this.ngayRauso;
   }

   public void setNgayRauso(String ngayRauso)
   {
     this.ngayRauso = ngayRauso;
   }

   public String getChuandoan_ngayde()
   {
     return this.chuandoan_ngayde;
   }

   public void setChuandoan_ngayde(String chuandoan_ngayde)
   {
     this.chuandoan_ngayde = chuandoan_ngayde;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBASanKhoaAction

 * JD-Core Version:    0.7.0.1

 */