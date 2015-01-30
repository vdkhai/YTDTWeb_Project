 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNhikhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;
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
 @Name("B211_3_CapNhatThongTinNhikhoa")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBANhikhoaAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBANhikhoaAction.class);
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
   private HsbaChiTietNhikhoa hsbaCTNhikhoa;
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
     log.info("*** Starting init Chi tiet BA Nhi khoa ***");

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

         HsbaChiTietNhikhoa hsbaCTNhikhoaTemp = null;
         try
         {
           hsbaCTNhikhoaTemp = HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTNhikhoaTemp != null) {
           this.hsbaCTNhikhoa = hsbaCTNhikhoaTemp;
         } else {
           this.hsbaCTNhikhoa = new HsbaChiTietNhikhoa();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBANhikhoa";
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
     log.info("*** Starting ghinhan Chi tiet BA Nhi khoa ***");
     this.ghiNhanException = null;

     this.hsbaCTNhikhoa.setHsbacmMa(this.hsbaChuyenMon);
     RemoveUtil.removeAllNullFromHSBACTNhikhoa(this.hsbaCTNhikhoa);
     if (this.hsbaCTNhikhoa.getHsbactnhikhoaMa() == null) {
       HsbaChiTietNhikhoaDelegate.getInstance().create(this.hsbaCTNhikhoa);
     } else {
       HsbaChiTietNhikhoaDelegate.getInstance().edit(this.hsbaCTNhikhoa);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBANhikhoa";
     log.info("Vao Method XuatReport bao cao cap nhat thong tin chi tiet benh an Nhi khoa");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_main.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport2.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport3.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N05_benhannhikhoa_subreport4.jrxml";

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
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("GIAMDOCBENHVIEN", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);
       params.put("sub4", sub4Report);


       HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();


       HsbaChiTietNhikhoa hsbaCTNhikhoaTemp = null;
       try
       {
         hsbaCTNhikhoaTemp = HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietNhikhoaDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTNhikhoaTemp != null) {
         this.hsbaCTNhikhoa = hsbaCTNhikhoaTemp;
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
       if (this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null)
       {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
         params.put("NGHENGHIEP", sNgheNghiep);
       }
       String sDanToc = "";
       if (this.benhnhan.getDantocMa(true).getDmdantocTen() != null)
       {
         sDanToc = this.benhnhan.getDantocMa(true).getDmdantocTen();
         params.put("DANTOC", sDanToc);
         params.put("DANTOCMA", this.benhnhan.getDantocMa(true).getDmdantocMa());
       }
       String sDoiTuong = "";
       if (this.hosobenhan.getDoituongMa() != null) {
         sDoiTuong = this.hosobenhan.getDoituongMa(true).getDmdoituongTen();
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
       params.put("CHUANDOAN_NOICHUYENDEN_MA", maTuyenduoi);
       params.put("CHUANDOAN_NOICHUYENDEN", tenTuyenduoi);


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
       params.put("CHUANDOAN_KHIVAOKHOA", maChuanDoanVaoKhoa);
       params.put("CHUANDOAN_KHIVAOKHOA_TEN", tenChuanDoanVaoKhoa);




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







       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());
       if (this.hsbaChuyenMon.getKetquaMa() != null) {
         params.put("KETQUA", this.hsbaChuyenMon.getKetquaMa(true).getDmkqdtTen());
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
       params.put("HOTENBO", this.hsbaCTNhikhoa.getHsbactnhikhoaHotenBo());
       params.put("HOTENME", this.hsbaCTNhikhoa.getHsbactnhikhoaHotenMe());
       params.put("NGHENGHIEPBO", this.hsbaCTNhikhoa.getHsbactnhikhoaNghenghiepBo());
       params.put("NGHENGHIEPME", this.hsbaCTNhikhoa.getHsbactnhikhoaNghenghiepMe());
       params.put("TRINHDOVHBO", this.hsbaCTNhikhoa.getHsbactnhikhoaTdvhBo());
       params.put("TRINHDOVHME", this.hsbaCTNhikhoa.getHsbactnhikhoaTdvhMe());
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
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTNhikhoa.getHsbactnhikhoaLydovaov());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaNgaybenhthu() != null) {
         params.put("VAONGAYTHU", this.hsbaCTNhikhoa.getHsbactnhikhoaNgaybenhthu());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaQtbenhly() != null) {
         params.put("QTBENHLY", this.hsbaCTNhikhoa.getHsbactnhikhoaQtbenhly());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhbt() != null) {
         params.put("TSBBANTHAN", this.hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhbt());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhgd() != null) {
         params.put("GIADINH", this.hsbaCTNhikhoa.getHsbactnhikhoaTiensubenhgd());
       }
       params.put("CONTHU", this.hsbaCTNhikhoa.getHsbactnhikhoaConthumay());
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaCannanglucsinh() != null) {
         params.put("CANNANGLUCSINH", this.hsbaCTNhikhoa.getHsbactnhikhoaCannanglucsinh() + " kg");
       }
       params.put("TATBAMSINH", this.hsbaCTNhikhoa.getHsbactnhikhoaTatbamsinh());
       params.put("PHATTRIENTINHTHAN", this.hsbaCTNhikhoa.getHsbactnhikhoaPtTinhthan());
       params.put("PHATTRIENVANDONG", this.hsbaCTNhikhoa.getHsbactnhikhoaPtVandong());
       params.put("CACBENHLYKHAC", this.hsbaCTNhikhoa.getHsbactnhikhoaSinhtruongBlkhac());
       params.put("CAISUATHANGTHU", this.hsbaCTNhikhoa.getHsbactnhikhoaCaisuathangthu());
       params.put("BENHKHACDUOCTIEMCHUNG", this.hsbaCTNhikhoa.getHsbactnhikhoaBenhkhacduoctiemchung());
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaChieucao() != null) {
         params.put("CHIEUCAO", this.hsbaCTNhikhoa.getHsbactnhikhoaChieucao() + " cm\t");
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaVongnguc() != null) {
         params.put("VONGNGUC", this.hsbaCTNhikhoa.getHsbactnhikhoaVongnguc() + " cm\t");
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaVongdau() != null) {
         params.put("VONGDAU", this.hsbaCTNhikhoa.getHsbactnhikhoaVongdau() + " cm\t");
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSinhduthang() != null) {
         params.put("SINHDUTHANG", this.hsbaCTNhikhoa.getHsbactnhikhoaSinhduthang());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSinhdenon() != null) {
         params.put("SOMDENON", this.hsbaCTNhikhoa.getHsbactnhikhoaSinhdenon());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSinhnaohut() != null) {
         params.put("SAYNAOHUT", this.hsbaCTNhikhoa.getHsbactnhikhoaSinhnaohut());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSinhsong() != null) {
         params.put("SONG", this.hsbaCTNhikhoa.getHsbactnhikhoaSinhsong());
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaDethuong() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaDethuong().booleanValue() == true)) {
         params.put("DETHUONG", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaDechihuy() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaDechihuy().booleanValue() == true)) {
         params.put("DECHIHUY", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaForceps() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaForceps().booleanValue() == true)) {
         params.put("FORCEPS", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaDekhac() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaDekhac().booleanValue() == true)) {
         params.put("TTKHAC", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaDephauthuat() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaDephauthuat().booleanValue() == true)) {
         params.put("DEPHAUTHUAT", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaGiachut() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaGiachut().booleanValue() == true)) {
         params.put("GIACHUT", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaDitatbamsinh() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaDitatbamsinh().booleanValue() == true)) {
         params.put("CODITATBAMSINH", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongSuame() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongSuame().booleanValue() == true)) {
         params.put("NUOISUAME", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongHonhop() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongHonhop().booleanValue() == true)) {
         params.put("NUOIHONHOP", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongNhantao() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaNuoiduongNhantao().booleanValue() == true)) {
         params.put("NUOINHANTAO", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainhatre() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainhatre().booleanValue() == true)) {
         params.put("CHAMSOCTAINHATRE", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainha() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaChamsocTainha().booleanValue() == true)) {
         params.put("CHAMSOCTAINHA", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungLao() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungLao().booleanValue() == true)) {
         params.put("TIEMCHUNGLAO", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBailiet() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBailiet().booleanValue() == true)) {
         params.put("TIEMCHUNGBAILIET", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungSoi() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungSoi().booleanValue() == true)) {
         params.put("TIEMCHUNGSOI", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungHoga() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungHoga().booleanValue() == true)) {
         params.put("TIEMCHUNGHOGA", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungUonvan() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungUonvan().booleanValue() == true)) {
         params.put("TIEMCHUNGUONVAN", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBachhau() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungBachhau().booleanValue() == true)) {
         params.put("TIEMCHUNGBACHHAU", "X");
       }
       if ((this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungKhac() != null) && (this.hsbaCTNhikhoa.getHsbactnhikhoaTiemchungKhac().booleanValue() == true)) {
         params.put("TIEMCHUNGKHAC", "X");
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaToanthan() != null) {
         params.put("TOANTHAN", this.hsbaCTNhikhoa.getHsbactnhikhoaToanthan());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTuanhoan() != null) {
         params.put("TUANHOAN", this.hsbaCTNhikhoa.getHsbactnhikhoaTuanhoan());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaBslamba() != null) {
         params.put("BASILAMBA", this.hsbaCTNhikhoa.getHsbactnhikhoaBslamba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaHohap() != null) {
         params.put("HOHAP", this.hsbaCTNhikhoa.getHsbactnhikhoaHohap());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTieuhoa() != null) {
         params.put("TIEUHOA", this.hsbaCTNhikhoa.getHsbactnhikhoaTieuhoa());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaThantietnieusinhhoc() != null) {
         params.put("THANTNSD", this.hsbaCTNhikhoa.getHsbactnhikhoaThantietnieusinhhoc());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaThankinh() != null) {
         params.put("THANKINH", this.hsbaCTNhikhoa.getHsbactnhikhoaThankinh());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaCoxuongkhop() != null) {
         params.put("COXUONGKHOP", this.hsbaCTNhikhoa.getHsbactnhikhoaCoxuongkhop());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTmhRhmMatDinhduongKhac() != null) {
         params.put("RHM-TMH-MAT-BENHLYKHAC", this.hsbaCTNhikhoa.getHsbactnhikhoaTmhRhmMatDinhduongKhac());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTtba() != null) {
         params.put("TTBA", this.hsbaCTNhikhoa.getHsbactnhikhoaTtba());
       }
       params.put("HUONGDIEUTRI", this.hsbaCTNhikhoa.getHsbactnhikhoaHuongdieutri());
       params.put("XETNGHIEMCANLAM", this.hsbaCTNhikhoa.getHsbactnhikhoaXetnghiemcanlam());
       params.put("TIENLUONG", this.hsbaCTNhikhoa.getHsbactnhikhoaTienluong());
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaQtblDbls() != null) {
         params.put("QTBLDBLS", this.hsbaCTNhikhoa.getHsbactnhikhoaQtblDbls());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTtkqxncls() != null) {
         params.put("TTKQCLSCD", this.hsbaCTNhikhoa.getHsbactnhikhoaTtkqxncls());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaPpdieutri() != null) {
         params.put("PPDT", this.hsbaCTNhikhoa.getHsbactnhikhoaPpdieutri());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTtnguoibenhrav() != null) {
         params.put("TTNBRV", this.hsbaCTNhikhoa.getHsbactnhikhoaTtnguoibenhrav());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTNhikhoa.getHsbactnhikhoaHuongdtCdtt());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTNhikhoa.getHsbactnhikhoaSotoxquang());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTNhikhoa.getHsbactnhikhoaSotoctscanner());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTNhikhoa.getHsbactnhikhoaSotosieuam());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTNhikhoa.getHsbactnhikhoaSotoxn());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTNhikhoa.getHsbactnhikhoaSotokhac());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTNhikhoa.getHsbactnhikhoaTongsoto());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaNguoigiaoba() != null) {
         params.put("NGUOIGIAOBA", this.hsbaCTNhikhoa.getHsbactnhikhoaNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaNguoinhanba() != null) {
         params.put("NGUOINHANBA", this.hsbaCTNhikhoa.getHsbactnhikhoaNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNhikhoa.getHsbactnhikhoaBsdieutri() != null) {
         params.put("BASIDT", this.hsbaCTNhikhoa.getHsbactnhikhoaBsdieutri(true).getDtdmnhanvienTen());
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBANhikhoa");


       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
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

   public HsbaChiTietNhikhoa getHsbaCTNhikhoa()
   {
     return this.hsbaCTNhikhoa;
   }

   public void setHsbaCTNhikhoa(HsbaChiTietNhikhoa hsbaCTNhikhoa)
   {
     this.hsbaCTNhikhoa = hsbaCTNhikhoa;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBANhikhoaAction

 * JD-Core Version:    0.7.0.1

 */