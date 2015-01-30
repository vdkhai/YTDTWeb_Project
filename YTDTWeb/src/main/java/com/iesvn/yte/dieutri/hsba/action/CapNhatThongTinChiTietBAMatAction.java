 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietMatDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietMat;
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
 import com.iesvn.yte.entity.DmThuoc;
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
 @Name("B211_3_CapNhatThongTinMat")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBAMatAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBAMatAction.class);
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
   private HsbaChiTietMat hsbaCTMat;
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
     log.info("*** Starting init Chi tiet BA MAT ***");

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

         HsbaChiTietMat hsbaCTMatTemp = null;
         try
         {
           hsbaCTMatTemp = HsbaChiTietMatDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTMatTemp != null) {
           this.hsbaCTMat = hsbaCTMatTemp;
         } else {
           this.hsbaCTMat = new HsbaChiTietMat();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBAMat";
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
     log.info("*** Starting ghinhan Chi tiet BA MAT ***");
     this.ghiNhanException = null;

     this.hsbaCTMat.setHsbacmMa(this.hsbaChuyenMon);

     RemoveUtil.removeAllNullFromHSBACTMat(this.hsbaCTMat);
     if (this.hsbaCTMat.getHsbactmatMa() == null)
     {
       HsbaChiTietMatDelegate.getInstance().create(this.hsbaCTMat);

       HsbaChiTietMat hsbaCTMatTemp = null;
       try
       {
         hsbaCTMatTemp = HsbaChiTietMatDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Ghi nhan -- HsbaChiTietMatDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTMatTemp != null) {
         this.hsbaCTMat = hsbaCTMatTemp;
       }
     }
     else
     {
       HsbaChiTietMatDelegate.getInstance().edit(this.hsbaCTMat);
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     log.info("*** Finished ghinhan **");
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
     this.loaiBCDT = "CapNhatThongTinChiTietBAMat";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an mat");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanmat_main.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanmat_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanmat_sub2.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanmat_sub3.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhanmat_sub4.jrxml";

       String hinh1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/img_benhanmat.jpg";

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

       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       log.info("Hinh : " + hinh1);
       params.put("HINH1", hinh1);

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);
       params.put("sub4", sub4Report);


       HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();


       HsbaChiTietMat hsbaCTMatTemp = null;
       if ((this.hsbaCTMat != null) && (this.hsbaCTMat.getHsbactmatMa() != null)) {
         try
         {
           hsbaCTMatTemp = HsbaChiTietMatDelegate.getInstance().find(this.hsbaCTMat.getHsbactmatMa());
         }
         catch (Exception e)
         {
           log.info("error Xuat Report HsbaChiTietMatDelegate.getInstance().find: " + e);
         }
       } else {
         try
         {
           hsbaCTMatTemp = HsbaChiTietMatDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error Xuat Report HsbaChiTietMatDelegate.getInstance().findByHsbaCM: " + e);
         }
       }
       if (hsbaCTMatTemp != null) {
         this.hsbaCTMat = hsbaCTMatTemp;
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

       params.put("GIOITINH", this.benhnhan.getDmgtMaso(true).getDmgtMa().equals("0") ? "1" : "2");

       String sNgaySinh = "";
       if (this.benhnhan.getBenhnhanNgaysinh() != null) {
         sNgaySinh = sdf.format(this.benhnhan.getBenhnhanNgaysinh());
       } else if (this.benhnhan.getBenhnhanNamsinh() != null) {
         sNgaySinh = this.benhnhan.getBenhnhanNamsinh();
       }
       params.put("NGAYSINH", sNgaySinh);

       String sNgheNghiep = "";
       if (this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null)
       {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
         params.put("NGHENGHIEP", sNgheNghiep);
         params.put("NGHENGHIEPMA", this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepMa());
       }
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
       params.put("TUYENDUOI", maTuyenduoi);
       params.put("TUYENDUOI_TEN", tenTuyenduoi);


       String maChandoanCC = "";
       String tenChandoanCC = "";
       if (this.hosobenhan.getHsbaMachdoanbd() != null)
       {
         maChandoanCC = this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdMa();
         tenChandoanCC = this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_CAPCUU", maChandoanCC);
       params.put("CHUANDOAN_CAPCUU_TEN", tenChandoanCC);


       String maChuanDoanVaoKhoa = "";
       String tenChuanDoanVaoKhoa = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
       {
         maChuanDoanVaoKhoa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
         tenChuanDoanVaoKhoa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_VAOKHOA", maChuanDoanVaoKhoa);
       params.put("CHUANDOAN_VAOKHOA_TEN", tenChuanDoanVaoKhoa);




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
       if (sBenhChinhMa.equals("")) {
         params.put("GIAIPHAUBENH", "2");
       } else {
         params.put("GIAIPHAUBENH", "1");
       }
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
       String sTmp = "";
       String sBienChung = "2";
       if (this.hsbaChuyenMon.getHsbacmBienchung() != null)
       {
         sTmp = this.hsbaChuyenMon.getHsbacmBienchung();
         if (!sTmp.equals(""))
         {
           sBienChung = "1    ";
           if (sTmp.toLowerCase().indexOf("gÃ¢y mÃª") != -1) {
             sBienChung = sBienChung + "a";
           } else if (sTmp.toLowerCase().indexOf("nhiá»…m khuáº©n") != -1) {
             sBienChung = sBienChung + "b";
           } else {
             sBienChung = sBienChung + "c";
           }
         }
       }
       params.put("BIENCHUNG", sBienChung);

       String sNguyenNhanTuVong = "";
       String sNguyenNhanTuVongMa = "";
       if (this.hosobenhan.getHsbaTuvong() != null)
       {
         sNguyenNhanTuVong = this.hosobenhan.getHsbaTuvong(true).getDmbenhicdTen();
         sNguyenNhanTuVongMa = this.hosobenhan.getHsbaTuvong(true).getDmbenhicdMa();
         params.put("NGUYENNHANTUVONG", sNguyenNhanTuVong);
         params.put("NGUYENNHANTUVONGMA", sNguyenNhanTuVongMa);
       }
       String sTuVongDo = "";
       String sTuVongDoChiTiet = "";
       if (iKetqua == 5)
       {
         if (sNguyenNhanTuVong.equals("")) {
           sTuVongDo = "2";
         } else {
           sTuVongDo = "1";
         }
         if (this.hosobenhan.getHsbaTuvvong24g().booleanValue()) {
           sTuVongDoChiTiet = "a";
         }
         params.put("TUVONGDO", sTuVongDo);
         params.put("TUVONGDOCHITIET", sTuVongDoChiTiet);
       }
       String sNgayGioTuVong = "";
       if (this.hosobenhan.getHsbaNgaygiotv() != null)
       {
         sNgayGioTuVong = Utils.getGioPhutNgay(this.hosobenhan.getHsbaNgaygiotv());
         params.put("NGAYGIOTUVONG", sNgayGioTuVong);
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
           params.put("NGAY_PHAUTHUAT", sdf.format(ppttt.getHsbapptttNgaypttt()));
           if (this.hosobenhan.getHsbaNgaygiovaov() != null) {
             params.put("NGAYDT_TRUOC_PHAUTHUAT", Long.valueOf(daysBetween(this.hosobenhan.getHsbaNgaygiovaov(), ppttt.getHsbapptttNgaypttt())));
           }
         }
         if (ppttt.getHsbapptttPhuongphap() != null) {
           params.put("PHUONGPHAP_PHAUTHUAT", ppttt.getHsbapptttPhuongphap().getDtdmclsbgDiengiai());
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


       String sBenhcoquankhac = "";
       if (this.hsbaCTMat.getHsbactmatCoquankhac() != null) {
         sBenhcoquankhac = this.hsbaCTMat.getHsbactmatCoquankhac();
       }
       params.put("BENHCOQUANKHAC", sBenhcoquankhac);

       String sChitietbenhcoquankhac = "";
       if (this.hsbaCTMat.getHsbactmatChitietbenhcoquankhac() != null) {
         sChitietbenhcoquankhac = this.hsbaCTMat.getHsbactmatChitietbenhcoquankhac();
       }
       params.put("CHITIETBENHCOQUANKHAC", sChitietbenhcoquankhac);
       if (this.hsbaCTMat.getHsbactmatLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTMat.getHsbactmatLydovaov());
       }
       if (this.hsbaCTMat.getHsbactmatNgaybenhthu() != null) {
         params.put("VAONGAYTHU", this.hsbaCTMat.getHsbactmatNgaybenhthu());
       }
       if (this.hsbaCTMat.getHsbactmatQtbenhly() != null) {
         params.put("QTBENHLY", this.hsbaCTMat.getHsbactmatQtbenhly());
       }
       if (this.hsbaCTMat.getHsbactmatTiensubenhbt() != null) {
         params.put("TSBBANTHAN", this.hsbaCTMat.getHsbactmatTiensubenhbt());
       }
       if (this.hsbaCTMat.getHsbactmatTiensubenhgd() != null) {
         params.put("GIADINH", this.hsbaCTMat.getHsbactmatTiensubenhgd());
       }
       if ((this.hsbaCTMat.getHsbactmatDdDiung() != null) && (this.hsbaCTMat.getHsbactmatDdDiung().booleanValue() == true)) {
         params.put("DIUNG", "1");
       } else {
         params.put("DIUNG", "2");
       }
       if ((this.hsbaCTMat.getHsbactmatDdMatuy() != null) && (this.hsbaCTMat.getHsbactmatDdMatuy().booleanValue() == true)) {
         params.put("MATUY", "1");
       } else {
         params.put("MATUY", "2");
       }
       if ((this.hsbaCTMat.getHsbactmatDdRuoubia() != null) && (this.hsbaCTMat.getHsbactmatDdRuoubia().booleanValue() == true)) {
         params.put("RUOUBIA", "1");
       } else {
         params.put("RUOUBIA", "2");
       }
       if ((this.hsbaCTMat.getHsbactmatDdThuocla() != null) && (this.hsbaCTMat.getHsbactmatDdThuocla().booleanValue() == true)) {
         params.put("THUOCLA", "1");
       } else {
         params.put("THUOCLA", "2");
       }
       if ((this.hsbaCTMat.getHsbactmatDdThuoclao() != null) && (this.hsbaCTMat.getHsbactmatDdThuoclao().booleanValue() == true)) {
         params.put("THUOCLAO", "1");
       } else {
         params.put("THUOCLAO", "2");
       }
       if ((this.hsbaCTMat.getHsbactmatDdKhac() != null) && (this.hsbaCTMat.getHsbactmatDdKhac().booleanValue() == true)) {
         params.put("KHAC", "1");
       } else {
         params.put("KHAC", "2");
       }
       if (this.hsbaCTMat.getHsbactmatDdDiungTg() != null) {
         params.put("DIUNGTG", this.hsbaCTMat.getHsbactmatDdDiungTg());
       }
       if (this.hsbaCTMat.getHsbactmatDdMatuyTg() != null) {
         params.put("MATUYTG", this.hsbaCTMat.getHsbactmatDdMatuyTg());
       }
       if (this.hsbaCTMat.getHsbactmatDdRuoubiaTg() != null) {
         params.put("RUOUBIATG", this.hsbaCTMat.getHsbactmatDdRuoubiaTg());
       }
       if (this.hsbaCTMat.getHsbactmatDdThuoclaTg() != null) {
         params.put("THUOCLATG", this.hsbaCTMat.getHsbactmatDdThuoclaTg());
       }
       if (this.hsbaCTMat.getHsbactmatDdThuoclaoTg() != null) {
         params.put("THUOCLAOTG", this.hsbaCTMat.getHsbactmatDdThuoclaoTg());
       }
       if (this.hsbaCTMat.getHsbactmatDdKhacTg() != null) {
         params.put("KHACTG", this.hsbaCTMat.getHsbactmatDdKhacTg());
       }
       if (this.hsbaCTMat.getHsbactmatToanthan() != null) {
         params.put("TOANTHAN", this.hsbaCTMat.getHsbactmatToanthan());
       }
       if (this.hsbaCTMat.getHsbactmatTuanhoan() != null) {
         params.put("TUANHOAN", this.hsbaCTMat.getHsbactmatTuanhoan());
       }
       if (this.hsbaCTMat.getHsbactmatHohap() != null) {
         params.put("HOHAP", this.hsbaCTMat.getHsbactmatHohap());
       }
       if (this.hsbaCTMat.getHsbactmatTieuhoa() != null) {
         params.put("TIEUHOA", this.hsbaCTMat.getHsbactmatTieuhoa());
       }
       if ((this.hsbaChuyenMon.getHsbacmHamax() != null) && (this.hsbaChuyenMon.getHsbacmHamin() != null)) {
         params.put("HUYETAP", this.hsbaChuyenMon.getHsbacmHamax() + " / " + this.hsbaChuyenMon.getHsbacmHamin());
       }
       if (this.hsbaCTMat.getHsbactmatThantietnieusinhhoc() != null) {
         params.put("THANTNSD", this.hsbaCTMat.getHsbactmatThantietnieusinhhoc());
       }
       if (this.hsbaCTMat.getHsbactmatThankinh() != null) {
         params.put("THANKINH", this.hsbaCTMat.getHsbactmatThankinh());
       }
       if (this.hsbaCTMat.getHsbactmatCoxuongkhop() != null) {
         params.put("COXUONGKHOP", this.hsbaCTMat.getHsbactmatCoxuongkhop());
       }
       if (this.hsbaCTMat.getHsbactmatTmh() != null) {
         params.put("TMH", this.hsbaCTMat.getHsbactmatTmh());
       }
       if (this.hsbaCTMat.getHsbactmatRhm() != null) {
         params.put("RHM", this.hsbaCTMat.getHsbactmatRhm());
       }
       if (this.hsbaCTMat.getHsbactmatMat() != null) {
         params.put("MAT", this.hsbaCTMat.getHsbactmatMat());
       }
       if (this.hsbaCTMat.getHsbactmatNtDdBlk() != null) {
         params.put("NTDDBLK", this.hsbaCTMat.getHsbactmatNtDdBlk());
       }
       if (this.hsbaCTMat.getHsbactmatTtba() != null) {
         params.put("TTBA", this.hsbaCTMat.getHsbactmatTtba());
       }
       if (this.hsbaChuyenMon.getHsbacmHuongdieutri() != null) {
         params.put("HUONGDT", this.hsbaChuyenMon.getHsbacmHuongdieutri());
       }
       if (this.hsbaCTMat.getHsbactmatQtblDbls() != null) {
         params.put("QTBLDBLS", this.hsbaCTMat.getHsbactmatTtba());
       }
       if (this.hsbaCTMat.getHsbactmatTtkqxncls() != null) {
         params.put("TTKQCLSCD", this.hsbaCTMat.getHsbactmatTtkqxncls());
       }
       if (this.hsbaCTMat.getHsbactmatPpdieutri() != null) {
         params.put("PPDT", this.hsbaCTMat.getHsbactmatPpdieutri());
       }
       if (this.hsbaCTMat.getHsbactmatTtnguoibenhrav() != null) {
         params.put("TTNBRV", this.hsbaCTMat.getHsbactmatTtnguoibenhrav());
       }
       if (this.hsbaCTMat.getHsbactmatHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTMat.getHsbactmatHuongdtCdtt());
       }
       if (this.hsbaCTMat.getHsbactmatSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTMat.getHsbactmatSotoxquang());
       }
       if (this.hsbaCTMat.getHsbactmatSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTMat.getHsbactmatSotoctscanner());
       }
       if (this.hsbaCTMat.getHsbactmatSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTMat.getHsbactmatSotosieuam());
       }
       if (this.hsbaCTMat.getHsbactmatSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTMat.getHsbactmatSotoxn());
       }
       if (this.hsbaCTMat.getHsbactmatSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTMat.getHsbactmatSotokhac());
       }
       if (this.hsbaCTMat.getHsbactmatTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTMat.getHsbactmatTongsoto());
       }
       if (this.hsbaCTMat.getHsbactmatNguoigiaoba() != null) {
         params.put("NGUOIGIAOHS", this.hsbaCTMat.getHsbactmatNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTMat.getHsbactmatNguoinhanba() != null) {
         params.put("NGUOINHANHS", this.hsbaCTMat.getHsbactmatNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTMat.getHsbactmatBsdieutri() != null) {
         params.put("BASIDT", this.hsbaCTMat.getHsbactmatBsdieutri(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTMat.getHsbactmatBslamba() != null) {
         params.put("BASILAMBA", this.hsbaCTMat.getHsbactmatBslamba(true).getDtdmnhanvienTen());
       }
       String sNguoigiaoBa = "";
       String sNguoinhanBa = "";
       if (this.hsbaCTMat.getHsbactmatNguoigiaoba() != null) {
         sNguoigiaoBa = this.hsbaCTMat.getHsbactmatNguoigiaoba(true).getDtdmnhanvienTen();
       }
       if (this.hsbaCTMat.getHsbactmatNguoinhanba() != null) {
         sNguoinhanBa = this.hsbaCTMat.getHsbactmatNguoinhanba(true).getDtdmnhanvienTen();
       }
       params.put("NGUOIGIAOBA", sNguoigiaoBa);
       params.put("NGUOINHANBA", sNguoinhanBa);

       String sBSLAMBA = "";
       if (this.hsbaCTMat.getHsbactmatBslamba() != null) {
         sBSLAMBA = this.hsbaCTMat.getHsbactmatBslamba(true).getDtdmnhanvienTen();
       }
       params.put("BSLAMBA", sBSLAMBA);

       String sChdoankhoa_benhchinh = "";
       String sChdoankhoa_benhphu = "";
       String sChdoankhoa_benhchinh_ma = "";
       String sChdoankhoa_benhphu_ma = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
       {
         sChdoankhoa_benhchinh_ma = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa();
         sChdoankhoa_benhchinh = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
       }
       if (this.hsbaChuyenMon.getHsbacmBenhphu() != null)
       {
         sChdoankhoa_benhphu_ma = this.hsbaChuyenMon.getHsbacmBenhphu().getDmbenhicdMa();
         sChdoankhoa_benhphu = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
       }
       params.put("CHDOANKHOA_BENHCHINH", sChdoankhoa_benhchinh);
       params.put("CHDOANKHOA_BENHPHU", sChdoankhoa_benhphu);
       params.put("CHDOANKHOA_BENHCHINH_MA", sChdoankhoa_benhchinh_ma);
       params.put("CHDOANKHOA_BENHPHU_MA", sChdoankhoa_benhphu_ma);

       String sChdoanrav = "";
       String sChdoanrav_ma = "";
       if (this.hosobenhan.getHsbaMachdravien() != null)
       {
         sChdoanrav_ma = this.hosobenhan.getHsbaMachdravien(true).getDmbenhicdMa();
         sChdoanrav = this.hosobenhan.getHsbaMachdravien(true).getDmbenhicdTen();
       }
       params.put("CHDOANRAV", sChdoanrav);
       params.put("CHDOANRAV_MA", sChdoanrav_ma);

       String sTienluong = "";
       if (this.hsbaCTMat.getHsbactmatTienluong() != null) {
         sTienluong = this.hsbaCTMat.getHsbactmatTienluong().getDmthuocTen();
       }
       params.put("TIENLUONG", sTienluong);

       params.put("HSBACMMA", this.hsbaChuyenMon.getHsbacmMa());

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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "CapNhatThongTinChiTietBAMat");



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

   public String getICDCode(String code)
   {
     if (code != null)
     {
       if (code.length() == 3) {
         code = code.charAt(0) + String.format("%6s", new Object[] { Character.valueOf(code.charAt(1)) }) + String.format("%6s", new Object[] { Character.valueOf(code.charAt(2)) });
       } else if (code.length() == 5) {
         code = code.charAt(0) + String.format("%6s", new Object[] { Character.valueOf(code.charAt(1)) }) + String.format("%6s", new Object[] { Character.valueOf(code.charAt(2)) }) + String.format("%6s", new Object[] { Character.valueOf(code.charAt(4)) });
       }
     }
     else {
       return "";
     }
     return code;
   }

   public String getICDCode(String code, int pos1, int pos2, int pos3)
   {
     if (code != null)
     {
       if (code.length() == 3) {
         code = code.charAt(0) + String.format(new StringBuilder().append("%").append(pos1).append("s").toString(), new Object[] { Character.valueOf(code.charAt(1)) }) + String.format(new StringBuilder().append("%").append(pos2).append("s").toString(), new Object[] { Character.valueOf(code.charAt(2)) });
       } else if (code.length() == 5) {
         code = code.charAt(0) + String.format(new StringBuilder().append("%").append(pos1).append("s").toString(), new Object[] { Character.valueOf(code.charAt(1)) }) + String.format(new StringBuilder().append("%").append(pos2).append("s").toString(), new Object[] { Character.valueOf(code.charAt(2)) }) + String.format(new StringBuilder().append("%").append(pos3).append("s").toString(), new Object[] { Character.valueOf(code.charAt(4)) });
       }
     }
     else {
       return "";
     }
     return code;
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

   public HsbaChiTietMat getHsbaCTMat()
   {
     return this.hsbaCTMat;
   }

   public void setHsbaCTMat(HsbaChiTietMat hsbaCTMat)
   {
     this.hsbaCTMat = hsbaCTMat;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBAMatAction

 * JD-Core Version:    0.7.0.1

 */