 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNoitruYhctDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;
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
 @Name("B211_3_CapNhatThongTinNoiTruYhct")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBANoiTruYhctAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBANoiTruYhctAction.class);
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
   private HsbaChiTietNoitruYhct hsbaCTNoitruYhct;
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
     log.info("*** Starting init Chi tiet BA Noi tru YHCT ***");

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

         HsbaChiTietNoitruYhct hsbaCTNoitruYhctTemp = null;
         try
         {
           hsbaCTNoitruYhctTemp = HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTNoitruYhctTemp != null) {
           this.hsbaCTNoitruYhct = hsbaCTNoitruYhctTemp;
         } else {
           this.hsbaCTNoitruYhct = new HsbaChiTietNoitruYhct();
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
     log.info("*** Finished init **");
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoitruYhct";
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
     log.info("***Starting ghinhan **");
     this.ghiNhanException = null;
     this.hsbaCTNoitruYhct.setHsbacmMa(this.hsbaChuyenMon);
     RemoveUtil.removeAllNullFromHSBACTNoitruYhct(this.hsbaCTNoitruYhct);
     if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctMa() == null) {
       HsbaChiTietNoitruYhctDelegate.getInstance().create(this.hsbaCTNoitruYhct);
     } else {
       HsbaChiTietNoitruYhctDelegate.getInstance().edit(this.hsbaCTNoitruYhct);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBANoitruYhct";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an noitruYhct");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport0.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport1.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport2.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport3.jrxml";
       String sub5Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport4.jrxml";
       String sub6Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport5.jrxml";
       String sub7Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoitru_N09_subreport6.jrxml";
       log.info("da thay file templete " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
       JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);
       JasperReport sub5Report = JasperCompileManager.compileReport(sub5Template);
       JasperReport sub6Report = JasperCompileManager.compileReport(sub6Template);
       JasperReport sub7Report = JasperCompileManager.compileReport(sub7Template);

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
       params.put("sub5", sub5Report);
       params.put("sub6", sub6Report);
       params.put("sub7", sub7Report);


       HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();

       HsbaChiTietNoitruYhct hsbaCTNoitruYhctTemp = null;
       try
       {
         hsbaCTNoitruYhctTemp = HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietNoitruYhctDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTNoitruYhctTemp != null) {
         this.hsbaCTNoitruYhct = hsbaCTNoitruYhctTemp;
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

       String sNgheNghiep = "";
       if (this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null) {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
       }
       params.put("NGHENGHIEP", sNgheNghiep);


       String sDanToc = "";
       if (this.benhnhan.getDantocMa(true).getDmdantocTen() != null) {
         sDanToc = this.benhnhan.getDantocMa(true).getDmdantocTen();
       }
       params.put("DANTOC", sDanToc);


       String sDoiTuong = "";
       if (this.hosobenhan.getDoituongMa(true).getDmdoituongTen() != null) {
         sDoiTuong = this.hosobenhan.getDoituongMa(true).getDmdoituongTen();
       }
       params.put("DOITUONG", sDoiTuong);

       String sNgoaiKieu = "";
       if (this.benhnhan.getQuocgiaMa(true).getDmquocgiaTen() != null) {
         sNgoaiKieu = this.benhnhan.getQuocgiaMa(true).getDmquocgiaTen();
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

       String sVaoKhoa = "";
       if (this.hosobenhan.getHsbaKhoavaov() != null) {
         sVaoKhoa = this.hosobenhan.getHsbaKhoavaov(true).getDmkhoaMa();
       }
       params.put("VAOKHOA", sVaoKhoa);

       String sVaoKhoaLuc = "";
       if (this.hsbaChuyenMon.getHsbacmNgaygiovaok() != null)
       {
         sVaoKhoaLuc = Utils.getGioPhutNgay(this.hsbaChuyenMon.getHsbacmNgaygiovaok());
         params.put("VAOKHOALUC", sVaoKhoaLuc);
       }
       List<HsbaChuyenMon> listHSBAChuyenKhoa = hsbadel.findBySoVaoVien(this.soBenhAn);
       if ((listHSBAChuyenKhoa != null) && (listHSBAChuyenKhoa.size() > 1)) {
         for (int i = 1; (i < listHSBAChuyenKhoa.size()) && (i <= 3); i++)
         {
           String makhoa = "";
           String ngaygiovaok = "";
           int stt = i - 1;
           if (((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getKhoaMa() != null)
           {
             makhoa = ((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getKhoaMa(true).getDmkhoaMa();
             params.put("CHUYENKHOA_" + stt, makhoa);
           }
           if (((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiovaok() != null)
           {
             ngaygiovaok = Utils.getGioPhutNgay(((HsbaChuyenMon)listHSBAChuyenKhoa.get(i)).getHsbacmNgaygiovaok());
             params.put("CHUYENKHOALUC_" + stt, ngaygiovaok);
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
       String tenTuyenduoi = "";
       if (this.hosobenhan.getHsbaMachdoantuyent() != null)
       {
         tenTuyenduoi = this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdTen();
         params.put("CHUANDOAN_NOICHUYENDEN_MA", this.hosobenhan.getHsbaMachdoantuyent(true).getDmbenhicdMa());
       }
       params.put("CHUANDOAN_NOICHUYENDEN", tenTuyenduoi);


       String tenChandoanCC = "";
       if (this.hosobenhan.getHsbaMachdoanbd() != null)
       {
         tenChandoanCC = this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdTen();
         params.put("CHUANDOAN_CAPCUU_MA", this.hosobenhan.getHsbaMachdoanbd(true).getDmbenhicdMa());
       }
       params.put("CHUANDOAN_CAPCUU", tenChandoanCC);
       if (this.hosobenhan.getHsbaMachdravien() != null)
       {
         params.put("CHUANDOAN_RAVIEN", this.hosobenhan.getHsbaMachdravien(true).getDmbenhicdTen());
         params.put("CHUANDOAN_RAVIEN_MA", this.hosobenhan.getHsbaMachdravien(true).getDmbenhicdMa());
       }
       String tenChuanDoanVaoKhoa = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null) {
         tenChuanDoanVaoKhoa = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
       }
       params.put("CHUANDOAN_VAOKHOA", tenChuanDoanVaoKhoa);

       String sBenhChinh = "";
       String sBenhKemTheo = "";
       if (this.hsbaChuyenMon.getHsbacmBenhchinh() != null)
       {
         sBenhChinh = this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
         params.put("BENHCHINH_MA", this.hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa());
       }
       if (this.hsbaChuyenMon.getHsbacmBenhphu() != null)
       {
         sBenhKemTheo = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
         params.put("BENHPHU_MA", this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa());
       }
       params.put("BENHCHINH", sBenhChinh);
       params.put("BENHPHU", sBenhKemTheo);

       String sKetQua = "";
       if (this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen() != null) {
         sKetQua = this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen();
       }
       params.put("KETQUA", sKetQua);

       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());

       params.put("YHCT_KHOAKHAMBENH", this.hsbaChuyenMon.getKhoaMa(true).getDmkhoaTen());
       params.put("CHANDOANBATCUONG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanbatchuong());
       params.put("CHANDOANNGUYENNHAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChandoannguyennhan());
       if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctBsdieutri() != null) {
         params.put("BACSIDT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBsdieutri(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctLydovaov());
       }
       if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctQtbenhly() != null) {
         params.put("QUATRINHBENHLY", this.hsbaCTNoitruYhct.getHsbactnoitruYhctQtbenhly());
       }
       if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhbt() != null) {
         params.put("TIEUSUBENHBANTHAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhbt());
       }
       if (this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhgd() != null) {
         params.put("TIEUSUBENHGIADINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiensubenhgd());
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctThuocla() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctThuocla().booleanValue() == true)) {
         params.put("THUOCLA", "X");
       } else {
         params.put("THUOCLA", "");
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctRuou() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctRuou().booleanValue() == true)) {
         params.put("RUOU", "X");
       } else {
         params.put("RUOU", "");
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctMatuy() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctMatuy().booleanValue() == true)) {
         params.put("MATUY", "X");
       } else {
         params.put("MATUY", "");
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDiung() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDiung().booleanValue() == true)) {
         params.put("DIUNG", "X");
       } else {
         params.put("DIUNG", "");
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctKhac() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctKhac().booleanValue() == true)) {
         params.put("KHAC", "X");
       } else {
         params.put("KHAC", "");
       }
       params.put("NOITIET", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNoitiet());
       params.put("DINHDUONG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDinhduong());
       params.put("THANKINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctThankinh());
       params.put("MAT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMat());
       params.put("TMH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTmh());
       params.put("RHM", this.hsbaCTNoitruYhct.getHsbactnoitruYhctRhm());
       params.put("TUANHOAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTuanhoan());
       params.put("HOHAP", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHohap());
       params.put("TIEUHOA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTieuhoa());
       params.put("DAVAMO", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDavamo());
       params.put("COXUONGKHOP", this.hsbaCTNoitruYhct.getHsbactnoitruYhctCoxuongkhop());
       params.put("TIETNIEU", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTietnieu());
       params.put("SINHDUC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduc());
       params.put("COQUANKHAC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctCoquankhac());
       params.put("CHITIETBENHLY", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChitietbenhly());
       params.put("HUYETHOC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHuyethoc());
       params.put("HUYETHOCKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHuyethockq());
       params.put("HOASINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoasinh());
       params.put("HOASINHKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoasinhkq());
       params.put("VISINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctVisinh());
       params.put("VISINHKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctVisinhkq());
       params.put("XQUANG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXquang());
       params.put("XQUANGKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXquangkq());
       params.put("SIEUAM", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSieuam());
       params.put("SIEUAMKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSieuamkq());
       params.put("DIENTIM", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDientim());
       params.put("DIENTIMKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDientimkq());
       params.put("NOISOI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNoisoi());
       params.put("NOISOIKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNoisoikq());
       params.put("GIAIPHAUBENH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctGiaiphaubenh());
       params.put("GIAIPHAUBENHKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctGiaiphaubenhkq());
       params.put("XETNGHIEMKHAC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXetnghiemkhac());
       params.put("XETNGHIEMKHACKQ", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXetnghiemkhackq());



       params.put("TOMTATTRIEUCHUNG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTomtattrieuchung());
       params.put("VONGCHAN_HINHTHAI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctVongchanHinhthai());
       params.put("VONGCHAN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctVongchanMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTinhtao() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTinhtao().booleanValue() == true)) {
         params.put("THANSAC_TINHTAO", "X");
       } else {
         params.put("THANSAC_TINHTAO", "");
       }
       params.put("THANSAC_SAC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctThansacSac());
       params.put("THANSAC_TRACH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctThansacTrach());
       params.put("THANSAC_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctThansacMota());
       params.put("LUOI_CHATLUOI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiChatluoi());
       params.put("LUOI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiMota());
       params.put("LUOI_SAC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctLuoiSac());
       params.put("SACLUOI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSacluoiMota());
       params.put("REULUOI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctReuluoi());
       params.put("REULUOI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctReuluoiMota());
       params.put("BOPHANBIBENH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBophanbibenh());
       params.put("TIENGNOI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiengnoi());
       params.put("TIENGNOI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTiengnoiMota());
       params.put("HOITHO", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoitho());
       params.put("HOITHO_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoithoMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctHo() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctHo().booleanValue() == true)) {
         params.put("HO", "X");
       } else {
         params.put("HO", "");
       }
       params.put("HO_TINHCHAT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoTinhchat());



       params.put("HO_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctOnac() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctOnac().booleanValue() == true)) {
         params.put("ONAC", "X");
       } else {
         params.put("ONAC", "");
       }
       params.put("ONAC_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctOnacMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctMui() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctMui().booleanValue() == true)) {
         params.put("MUI", "X");
       } else {
         params.put("MUI", "");
       }
       params.put("MUI_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMuiTc());
       params.put("MUI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMuiMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomui() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomui().booleanValue() == true)) {
         params.put("HOITHOCOMUI", "X");
       } else {
         params.put("HOITHOCOMUI", "");
       }
       params.put("HOITHOCOMUI_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomuiTc());
       params.put("HOITHOCOMUI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHoithocomuiMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctCohannhiet() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctCohannhiet().booleanValue() == true)) {
         params.put("COHANNHIET", "X");
       } else {
         params.put("COHANNHIET", "");
       }
       params.put("HANNHIET", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHannhiet());
       params.put("HANNHIET_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHannhietMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctCobenhthaydoitheomua() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctCobenhthaydoitheomua().booleanValue() == true)) {
         params.put("COBENHTHAYDOITHEOMUA", "X");
       } else {
         params.put("COBENHTHAYDOITHEOMUA", "");
       }
       params.put("BENHTHAYDOITHEOMUA_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBenhthaydoitheomuaMota());
       params.put("MOHOI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMohoi());
       params.put("MOHOI_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMohoiMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatcobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatcobenhly().booleanValue() == true)) {
         params.put("DAUMATCOBENHLY", "X");
       } else {
         params.put("DAUMATCOBENHLY", "");
       }
       params.put("DAUMATBENHLY", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatbenhly());
       params.put("DAUMAT_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaumatMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctLungcobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctLungcobenhly().booleanValue() == true)) {
         params.put("LUNGCOBENHLY", "X");
       } else {
         params.put("LUNGCOBENHLY", "");
       }
       params.put("LUNG_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctLungMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguccobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguccobenhly().booleanValue() == true)) {
         params.put("BUNGNGUCCOBENHLY", "X");
       } else {
         params.put("BUNGNGUCCOBENHLY", "");
       }
       params.put("BUNGNGUC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBungnguc());
       params.put("BUNGNGUC_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBungngucMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctChantaycobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctChantaycobenhly().booleanValue() == true)) {
         params.put("CHANTAYCOBENHLY", "X");
       } else {
         params.put("CHANTAYCOBENHLY", "");
       }
       params.put("CHANTAY_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChantayMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctAncobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctAncobenhly().booleanValue() == true)) {
         params.put("ANCOBENHLY", "X");
       } else {
         params.put("ANCOBENHLY", "");
       }
       params.put("AN_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctAnTc());
       params.put("AN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctAnMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctUongcobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctUongcobenhly().booleanValue() == true)) {
         params.put("UONGCOBENHLY", "X");
       } else {
         params.put("UONGCOBENHLY", "");
       }
       params.put("UONG_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctUongTc());
       params.put("UONG_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctUongMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutiencobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutiencobenhly().booleanValue() == true)) {
         params.put("DAITIEUTIENCOBENHLY", "X");
       } else {
         params.put("DAITIEUTIENCOBENHLY", "");
       }
       params.put("TIEUTIEN_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTieutienTc());
       params.put("DAITIEN_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaitienTc());
       params.put("DAITIEUTIEN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaitieutienMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctNgucobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctNgucobenhly().booleanValue() == true)) {
         params.put("NGUCOBENHLY", "X");
       } else {
         params.put("NGUCOBENHLY", "");
       }
       params.put("NGU_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNguTc());
       params.put("NGU_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNguMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctKinhnguyetcobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctKinhnguyetcobenhly().booleanValue() == true)) {
         params.put("KINHNGUYETCOBENHLY", "X");
       } else {
         params.put("KINHNGUYETCOBENHLY", "");
       }
       params.put("ROILOANKINHNGUYET", this.hsbaCTNoitruYhct.getHsbactnoitruYhctRoiloankinhnguyet());
       params.put("DAUBUNGKINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDaubungkinh());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDoihacobenhly() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDoihacobenhly().booleanValue() == true)) {
         params.put("DOIHACOBENHLY", "X");
       } else {
         params.put("DOIHACOBENHLY", "");
       }
       params.put("DOIHA_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDoihaTc());
       params.put("DOIHA_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDoihaMota());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduccoroiloan() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctSinhduccoroiloan().booleanValue() == true)) {
         params.put("SINHDUCCOROILOAN", "X");
       } else {
         params.put("SINHDUCCOROILOAN", "");
       }
       params.put("SINHDUC_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSinhducTc());
       params.put("SINHDUC_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctSinhducMota());

       params.put("DIEUKIENXUATHIEN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieukienxuathien());
       params.put("DIEUKIENXUATHIEN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieukienxuathienMota());



       params.put("XUCCHAN_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXucchanTc());
       params.put("XUCCHAN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXucchanMota());
       params.put("CONHUC_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctConhucTc());
       params.put("BUNG_TC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBungTc());
       params.put("MACHTAYTRAI_THON", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiThon());
       params.put("MACHTAYTRAI_QUAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiQuan());
       params.put("MACHTAYTRAI_XICH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtaytraiXich());
       params.put("MACHTAYPHAI_THON", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiThon());
       params.put("MACHTAYPHAI_QUAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiQuan());
       params.put("MACHTAYPHAI_XICH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachtayphaiXich());
       params.put("TONGKHAMBENPHAI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTongkhambenphai());
       params.put("TONGKHAMBENTRAI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTongkhambentrai());
       params.put("MACHCHAN_MOTA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctMachchanMota());
       params.put("BIENCHUNGLUANTRI", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBienchungluantri());
       params.put("BENHDANH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBenhdanh());
       params.put("BATCUONG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctBatcuong());
       params.put("TANGPHU", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTangphu());
       params.put("NGUYENNHAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctNguyennhan());
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutridonthuanyhct() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutridonthuanyhct().booleanValue() == true)) {
         params.put("DIEUTRIDONTHUANYHCT", "X");
       }
       if ((this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutrikethopyhhd() != null) && (this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutrikethopyhhd().booleanValue() == true)) {
         params.put("DIEUTRIKETHOPYHHD", "X");
       }
       params.put("PHEPCHUA", this.hsbaCTNoitruYhct.getHsbactnoitruYhctPhepchua());
       params.put("PHUONGTHUOC", this.hsbaCTNoitruYhct.getHsbactnoitruYhctPhuongthuoc());
       params.put("PHUONGHUYET", this.hsbaCTNoitruYhct.getHsbactnoitruYhctPhuonghuyet());
       params.put("XOABOP", this.hsbaCTNoitruYhct.getHsbactnoitruYhctXoabop());
       params.put("CHEDOAN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChedoan());
       params.put("CHEDOHOLY", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChedoholy());
       params.put("TIENLUONG", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTienluong());


       params.put("QTBLDBCLS", this.hsbaCTNoitruYhct.getHsbactnoitruYhctQtbldbcls());
       params.put("KETQUACLSCHINH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctKetquaclschinh());
       params.put("KETQUAGIAIPHAUBENH", this.hsbaCTNoitruYhct.getHsbactnoitruYhctKetquagiaiphaubenh());
       params.put("DIEUTRI_YHHD", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutriYhhd());
       params.put("DIEUTRI_YHCT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctDieutriYhct());
       params.put("CHANDOANRAVIEN_YHHD", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanravienYhhd());
       params.put("CHANDOANRAVIEN_YHCT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctChandoanravienYhct());
       params.put("TINHTRANGNBRAVIEN", this.hsbaCTNoitruYhct.getHsbactnoitruYhctTinhtrangnbravien());
       params.put("HDTCCDTT", this.hsbaCTNoitruYhct.getHsbactnoitruYhctHdtccdtt());

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBANoitruYhct");


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
     return (d2.getTime() - d1.getTime() + 3600000L) / 86400000L;
   }

   public HsbaChiTietNoitruYhct getHsbaCTNoitruYhct()
   {
     return this.hsbaCTNoitruYhct;
   }

   public void setHsbaCTNoitruYhct(HsbaChiTietNoitruYhct hsbaCTNoitruYhct)
   {
     this.hsbaCTNoitruYhct = hsbaCTNoitruYhct;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBANoiTruYhctAction

 * JD-Core Version:    0.7.0.1

 */