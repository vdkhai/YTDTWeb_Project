 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietPhukhoaDelegate;
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
 import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;
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
 @Name("B211_3_CapNhatThongTinPhuKhoa")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBAPhuKhoaAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBAPhuKhoaAction.class);
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
   private HsbaChiTietPhukhoa hsbaCTPhukhoa;
   private Hsba hosobenhan;
   private TiepDon tiepDon;
   private BenhNhan benhnhan;
   private String kinhlancuoingay;
   private int index = 0;
   private static final long ONE_HOUR = 3600000L;

   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("*** Starting init BA Phu khoa ***");

     HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
     this.hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);
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

         HsbaChiTietPhukhoa hsbaCTPhukhoaTemp = null;
         try
         {
           hsbaCTPhukhoaTemp = HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTPhukhoaTemp != null)
         {
           this.hsbaCTPhukhoa = hsbaCTPhukhoaTemp;

           SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
           if (this.hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay() != null) {
             this.kinhlancuoingay = sdf.format(this.hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay());
           }
         }
         else
         {
           this.hsbaCTPhukhoa = new HsbaChiTietPhukhoa();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBAPhukhoa";
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
     log.info("***Starting ghinhan Chi tiet BA Phu khoa ***");
     this.ghiNhanException = null;

     this.hsbaCTPhukhoa.setHsbacmMa(this.hsbaChuyenMon);
     SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
     try
     {
       this.hsbaCTPhukhoa.setHsbactphukhoaKinhlancuoingay(sdf.parse(this.kinhlancuoingay));
     }
     catch (ParseException e) {}
     RemoveUtil.removeAllNullFromHSBACTPhukhoa(this.hsbaCTPhukhoa);
     if (this.hsbaCTPhukhoa.getHsbactphukhoaMa() == null) {
       HsbaChiTietPhukhoaDelegate.getInstance().create(this.hsbaCTPhukhoa);
     } else {
       HsbaChiTietPhukhoaDelegate.getInstance().edit(this.hsbaCTPhukhoa);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBAPhukhoa";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an phukhoa");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub2.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub3.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanphukhoa_sub4.jrxml";

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


       HsbaChiTietPhukhoa hsbaCTPhukhoaTemp = null;
       try
       {
         hsbaCTPhukhoaTemp = HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietPhukhoaDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTPhukhoaTemp != null) {
         this.hsbaCTPhukhoa = hsbaCTPhukhoaTemp;
       }
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
       if (this.hosobenhan.getDoituongMa(true).getDmdoituongMa() != null) {
         sDoiTuong = this.hosobenhan.getDoituongMa(true).getDmdoituongTen();
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
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSinhduthang() != null) {
         params.put("SINHDUTHANG", this.hsbaCTPhukhoa.getHsbactphukhoaSinhduthang());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSinhdenon() != null) {
         params.put("SOMDENON", this.hsbaCTPhukhoa.getHsbactphukhoaSinhdenon());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSinhnaohut() != null) {
         params.put("SAYNAOHUT", this.hsbaCTPhukhoa.getHsbactphukhoaSinhnaohut());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSinhsong() != null) {
         params.put("SONG", this.hsbaCTPhukhoa.getHsbactphukhoaSinhsong());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTPhukhoa.getHsbactphukhoaLydovaov());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaQtbenhly() != null) {
         params.put("HOIBENH", this.hsbaCTPhukhoa.getHsbactphukhoaQtbenhly());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaQtbenhly() != null) {
         params.put("QTBENHLY", this.hsbaCTPhukhoa.getHsbactphukhoaQtbenhly());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaTiensubenhbt() != null) {
         params.put("TSBBANTHAN", this.hsbaCTPhukhoa.getHsbactphukhoaTiensubenhbt());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaTiensubenhgd() != null) {
         params.put("GIADINH", this.hsbaCTPhukhoa.getHsbactphukhoaTiensubenhgd());
       }
       params.put("THAYKINHNAM", this.hsbaCTPhukhoa.getHsbactphukhoaThaykinhnam());
       params.put("THAYKINHTUOI", this.hsbaCTPhukhoa.getHsbactphukhoaThaykinhtuoi());
       params.put("TINHCHATKINHNGUYET", this.hsbaCTPhukhoa.getHsbactphukhoaTinhchatkinhnguyet());
       params.put("CHUKY", this.hsbaCTPhukhoa.getHsbactphukhoaChukykinh());
       params.put("SONGAYTHAYKINH", this.hsbaCTPhukhoa.getHsbactphukhoaSongaythaykinh());
       params.put("LUONGKINH", this.hsbaCTPhukhoa.getHsbactphukhoaLuongkinh());
       if (this.hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay() != null) {
         params.put("KINHLANCUOINGAY", sdf.format(this.hsbaCTPhukhoa.getHsbactphukhoaKinhlancuoingay()));
       }
       if ((this.hsbaCTPhukhoa.getHsbactphukhoaCodaubung() != null) && (this.hsbaCTPhukhoa.getHsbactphukhoaCodaubung().booleanValue() == true)) {
         params.put("CODAUBUNG", "X");
       }
       if ((this.hsbaCTPhukhoa.getHsbactphukhoaThoigiantruoc() != null) && (this.hsbaCTPhukhoa.getHsbactphukhoaThoigiantruoc().booleanValue() == true)) {
         params.put("TGTRUOC", "X");
       }
       if ((this.hsbaCTPhukhoa.getHsbactphukhoaThoigiantrong() != null) && (this.hsbaCTPhukhoa.getHsbactphukhoaThoigiantrong().booleanValue() == true)) {
         params.put("TGTRONG", "X");
       }
       if ((this.hsbaCTPhukhoa.getHsbactphukhoaThoigiansau() != null) && (this.hsbaCTPhukhoa.getHsbactphukhoaThoigiansau().booleanValue() == true)) {
         params.put("TGSAU", "X");
       }
       params.put("LAYCHONGNAM", this.hsbaCTPhukhoa.getHsbactphukhoaLaychongnam());
       params.put("LAYCHONGTUOI", this.hsbaCTPhukhoa.getHsbactphukhoaLaychongtuoi());
       params.put("HETKINHNAM", this.hsbaCTPhukhoa.getHsbactphukhoaHetkinhnam());
       params.put("HETKINHTUOI", this.hsbaCTPhukhoa.getHsbactphukhoaKetkinhtuoi());
       params.put("BENHPHUKHOADADIEUTRI", this.hsbaCTPhukhoa.getHsbactphukhoaBenhphukhoadadieutri());
       params.put("DANIEMMAC", this.hsbaCTPhukhoa.getHsbactphukhoaDaniemmac());
       params.put("HACH", this.hsbaCTPhukhoa.getHsbactphukhoaHach());
       params.put("VU", this.hsbaCTPhukhoa.getHsbactphukhoaVu());
       params.put("TUANHOAN", this.hsbaCTPhukhoa.getHsbactphukhoaTuanhoan());
       params.put("HOHAP", this.hsbaCTPhukhoa.getHsbactphukhoaHohap());



       params.put("TIEUHOA", this.hsbaCTPhukhoa.getHsbactphukhoaTieuhoa());
       params.put("THANKINH", this.hsbaCTPhukhoa.getHsbactphukhoaThankinh());
       params.put("COXUONGKHOP", this.hsbaCTPhukhoa.getHsbactphukhoaCoxuongkhop());
       params.put("THANTIETNIEU", this.hsbaCTPhukhoa.getHsbactphukhoaThantietnieusinhhoc());
       params.put("COQUANKHAC", this.hsbaCTPhukhoa.getHsbactphukhoaCoquankhac());
       params.put("DAUHIEUSINHDUCTHUPHAT", this.hsbaCTPhukhoa.getHsbactphukhoaDauhieusinhducthuphat());
       params.put("MOILON", this.hsbaCTPhukhoa.getHsbactphukhoaMoilon());
       params.put("MOIBE", this.hsbaCTPhukhoa.getHsbactphukhoaMoibe());
       params.put("AMVAT", this.hsbaCTPhukhoa.getHsbactphukhoaAmvat());
       params.put("AMHO", this.hsbaCTPhukhoa.getHsbactphukhoaAmho());
       params.put("MANTRINH", this.hsbaCTPhukhoa.getHsbactphukhoaMantrinh());
       params.put("TANGSINHMON", this.hsbaCTPhukhoa.getHsbactphukhoaTangsinhmon());
       params.put("AMDAO", this.hsbaCTPhukhoa.getHsbactphukhoaAmdao());
       params.put("COTUCUNG", this.hsbaCTPhukhoa.getHsbactphukhoaCotucung());
       params.put("THANTUCUNG", this.hsbaCTPhukhoa.getHsbactphukhoaThantucung());
       params.put("PHANPHU", this.hsbaCTPhukhoa.getHsbactphukhoaPhanphu());
       params.put("CACTUICUNG", this.hsbaCTPhukhoa.getHsbactphukhoaCactuicung());
       params.put("TOMTATBENHAN", this.hsbaCTPhukhoa.getHsbactphukhoaTtba());
       params.put("XETNGHIEMCLS", this.hsbaCTPhukhoa.getHsbactphukhoaXetnghiemcanlam());


       params.put("TIENLUONG", this.hsbaCTPhukhoa.getHsbactphukhoaTienluong());
       params.put("HUONGDIEUTRI", this.hsbaCTPhukhoa.getHsbactphukhoaHuongdieutri());
       if (this.hsbaCTPhukhoa.getHsbactphukhoaQtblDbls() != null) {
         params.put("QTBLDBLS", this.hsbaCTPhukhoa.getHsbactphukhoaQtblDbls());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaTtkqxncls() != null) {
         params.put("TTKQCLSCD", this.hsbaCTPhukhoa.getHsbactphukhoaTtkqxncls());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaPpdieutri() != null) {
         params.put("PPDT", this.hsbaCTPhukhoa.getHsbactphukhoaPpdieutri());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaTtnguoibenhrav() != null) {
         params.put("TTNBRV", this.hsbaCTPhukhoa.getHsbactphukhoaTtnguoibenhrav());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTPhukhoa.getHsbactphukhoaHuongdtCdtt());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTPhukhoa.getHsbactphukhoaSotoxquang());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTPhukhoa.getHsbactphukhoaSotoctscanner());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTPhukhoa.getHsbactphukhoaSotosieuam());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTPhukhoa.getHsbactphukhoaSotoxn());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTPhukhoa.getHsbactphukhoaSotokhac());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTPhukhoa.getHsbactphukhoaTongsoto());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaBslamba() != null) {
         params.put("BACSILAMBA", this.hsbaCTPhukhoa.getHsbactphukhoaBslamba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaNguoigiaoba() != null) {
         params.put("NGUOIGIAOBA", this.hsbaCTPhukhoa.getHsbactphukhoaNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaNguoinhanba() != null) {
         params.put("NGUOINHANBA", this.hsbaCTPhukhoa.getHsbactphukhoaNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTPhukhoa.getHsbactphukhoaBsdieutri() != null) {
         params.put("BASIDT", this.hsbaCTPhukhoa.getHsbactphukhoaBsdieutri(true).getDtdmnhanvienTen());
       }
       log.info("================= ");
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBAPhukhoa");


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

   public HsbaChiTietPhukhoa getHsbaCTPhukhoa()
   {
     return this.hsbaCTPhukhoa;
   }

   public void setHsbaCTPhukhoa(HsbaChiTietPhukhoa hsbaCTPhukhoa)
   {
     this.hsbaCTPhukhoa = hsbaCTPhukhoa;
   }

   public String getHoTen()
   {
     return this.hoTen;
   }

   public void setHoTen(String hoTen)
   {
     this.hoTen = hoTen;
   }

   public String getKinhlancuoingay()
   {
     return this.kinhlancuoingay;
   }

   public void setKinhlancuoingay(String kinhlancuoingay)
   {
     this.kinhlancuoingay = kinhlancuoingay;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBAPhuKhoaAction

 * JD-Core Version:    0.7.0.1

 */