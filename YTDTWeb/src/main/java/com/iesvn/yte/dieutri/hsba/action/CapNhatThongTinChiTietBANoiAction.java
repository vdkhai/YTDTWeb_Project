 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNoiDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNoi;
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
 @Name("B211_3_CapNhatThongTinNoi")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBANoiAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBANoiAction.class);
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
   private HsbaChiTietNoi hsbaCTNoi;
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
     log.info("***Starting init chi tiet BA Noi Khoa ***");

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

         HsbaChiTietNoi hsbaCTNoiTemp = null;
         try
         {
           hsbaCTNoiTemp = HsbaChiTietNoiDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTNoiTemp != null) {
           this.hsbaCTNoi = hsbaCTNoiTemp;
         } else {
           this.hsbaCTNoi = new HsbaChiTietNoi();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoi";
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
     log.info("***Starting ghinhan chi tiet BA Noi **");
     this.ghiNhanException = null;

     this.hsbaCTNoi.setHsbacmMa(this.hsbaChuyenMon);

     RemoveUtil.removeAllNullFromHSBACTNoi(this.hsbaCTNoi);
     if (this.hsbaCTNoi.getHsbactnoiMa() == null) {
       HsbaChiTietNoiDelegate.getInstance().create(this.hsbaCTNoi);
     } else {
       HsbaChiTietNoiDelegate.getInstance().edit(this.hsbaCTNoi);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBANoi";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an Noi Khoa");

     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoikhoa_main.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoikhoa_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoikhoa_sub2.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoikhoa_sub3.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhannoikhoa_sub4.jrxml";

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


       HsbaChiTietNoi hsbaCTNoiTemp = null;
       try
       {
         hsbaCTNoiTemp = HsbaChiTietNoiDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietNoiDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTNoiTemp != null) {
         this.hsbaCTNoi = hsbaCTNoiTemp;
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

       String gt = this.benhnhan.getDmgtMaso(true).getDmgtMa();
       if (gt.equals("0")) {
         params.put("NU", "X");
       } else {
         params.put("NAM", "X");
       }
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












       params.put("KHOA_DT", this.hsbaChuyenMon.getKhoaMa(true).getDmkhoaTen());
       if ((this.hsbaChuyenMon.getHsbacmHuongdieutri() != null) && (this.hsbaChuyenMon.getHsbacmHuongdieutri().equals("2"))) {
         params.put("TUYENCHUYENVIEN", "1");
       }
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
         if (!sTmp.equals("")) {
           sBienChung = "1    ";
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
       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());
       if (this.hsbaCTNoi.getHsbactnoiLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTNoi.getHsbactnoiLydovaov());
       }
       if (this.hsbaCTNoi.getHsbactnoiNgaybenhthu() != null) {
         params.put("VAONGAYTHU", this.hsbaCTNoi.getHsbactnoiNgaybenhthu());
       }
       if (this.hsbaCTNoi.getHsbactnoiQtbenhly() != null) {
         params.put("QTBENHLY", this.hsbaCTNoi.getHsbactnoiQtbenhly());
       }
       if (this.hsbaCTNoi.getHsbactnoiTiensubenhbt() != null) {
         params.put("TSBBANTHAN", this.hsbaCTNoi.getHsbactnoiTiensubenhbt());
       }
       if (this.hsbaCTNoi.getHsbactnoiTiensubenhgd() != null) {
         params.put("GIADINH", this.hsbaCTNoi.getHsbactnoiTiensubenhgd());
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdDiung() != null) && (this.hsbaCTNoi.getHsbactnoiDdDiung().booleanValue() == true)) {
         params.put("DIUNG", "X");
       } else {
         params.put("DIUNG", "");
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdMatuy() != null) && (this.hsbaCTNoi.getHsbactnoiDdMatuy().booleanValue() == true)) {
         params.put("MATUY", "X");
       } else {
         params.put("MATUY", "");
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdRuoubia() != null) && (this.hsbaCTNoi.getHsbactnoiDdRuoubia().booleanValue() == true)) {
         params.put("RUOUBIA", "X");
       } else {
         params.put("RUOUBIA", "");
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdThuocla() != null) && (this.hsbaCTNoi.getHsbactnoiDdThuocla().booleanValue() == true)) {
         params.put("THUOCLA", "X");
       } else {
         params.put("THUOCLA", "");
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdThuoclao() != null) && (this.hsbaCTNoi.getHsbactnoiDdThuoclao().booleanValue() == true)) {
         params.put("THUOCLAO", "X");
       } else {
         params.put("THUOCLAO", "");
       }
       if ((this.hsbaCTNoi.getHsbactnoiDdKhac() != null) && (this.hsbaCTNoi.getHsbactnoiDdKhac().booleanValue() == true)) {
         params.put("KHAC", "X");
       } else {
         params.put("KHAC", "");
       }
       if (this.hsbaCTNoi.getHsbactnoiDdDiungTg() != null) {
         params.put("DIUNGTG", this.hsbaCTNoi.getHsbactnoiDdDiungTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiDdMatuyTg() != null) {
         params.put("MATUYTG", this.hsbaCTNoi.getHsbactnoiDdMatuyTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiDdRuoubiaTg() != null) {
         params.put("RUOUBIATG", this.hsbaCTNoi.getHsbactnoiDdRuoubiaTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiDdThuoclaTg() != null) {
         params.put("THUOCLATG", this.hsbaCTNoi.getHsbactnoiDdThuoclaTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiDdThuoclaoTg() != null) {
         params.put("THUOCLAOTG", this.hsbaCTNoi.getHsbactnoiDdThuoclaoTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiDdKhacTg() != null) {
         params.put("KHACTG", this.hsbaCTNoi.getHsbactnoiDdKhacTg());
       }
       if (this.hsbaCTNoi.getHsbactnoiToanthan() != null) {
         params.put("TOANTHAN", this.hsbaCTNoi.getHsbactnoiToanthan());
       }
       if (this.hsbaCTNoi.getHsbactnoiTuanhoan() != null) {
         params.put("TUANHOAN", this.hsbaCTNoi.getHsbactnoiTuanhoan());
       }
       if (this.hsbaCTNoi.getHsbactnoiHohap() != null) {
         params.put("HOHAP", this.hsbaCTNoi.getHsbactnoiHohap());
       }
       if (this.hsbaCTNoi.getHsbactnoiTieuhoa() != null) {
         params.put("TIEUHOA", this.hsbaCTNoi.getHsbactnoiTieuhoa());
       }
       if ((this.hsbaChuyenMon.getHsbacmHamax() != null) && (this.hsbaChuyenMon.getHsbacmHamin() != null)) {
         params.put("HUYETAP", this.hsbaChuyenMon.getHsbacmHamax() + " / " + this.hsbaChuyenMon.getHsbacmHamin());
       }
       if (this.hsbaCTNoi.getHsbactnoiThantietnieusinhhoc() != null) {
         params.put("THANTNSD", this.hsbaCTNoi.getHsbactnoiThantietnieusinhhoc());
       }
       if (this.hsbaCTNoi.getHsbactnoiThankinh() != null) {
         params.put("THANKINH", this.hsbaCTNoi.getHsbactnoiThankinh());
       }
       if (this.hsbaCTNoi.getHsbactnoiCoxuongkhop() != null) {
         params.put("COXUONGKHOP", this.hsbaCTNoi.getHsbactnoiCoxuongkhop());
       }
       if (this.hsbaCTNoi.getHsbactnoiTmh() != null) {
         params.put("TMH", this.hsbaCTNoi.getHsbactnoiTmh());
       }
       if (this.hsbaCTNoi.getHsbactnoiRhm() != null) {
         params.put("RHM", this.hsbaCTNoi.getHsbactnoiRhm());
       }
       if (this.hsbaCTNoi.getHsbactnoiMat() != null) {
         params.put("MAT", this.hsbaCTNoi.getHsbactnoiMat());
       }
       if (this.hsbaCTNoi.getHsbactnoiNtDdBlk() != null) {
         params.put("NTDDBLK", this.hsbaCTNoi.getHsbactnoiNtDdBlk());
       }
       if (this.hsbaCTNoi.getHsbactnoiTtba() != null) {
         params.put("TTBA", this.hsbaCTNoi.getHsbactnoiTtba());
       }
       if (this.hsbaChuyenMon.getHsbacmHuongdieutri() != null) {
         params.put("HUONGDT", this.hsbaChuyenMon.getHsbacmHuongdieutri());
       }
       if (this.hsbaCTNoi.getHsbactnoiBSlamba() != null) {
         params.put("BASILAMBA", this.hsbaCTNoi.getHsbactnoiBSlamba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNoi.getHsbactnoiQtblDbls() != null) {
         params.put("QTBLDBLS", this.hsbaCTNoi.getHsbactnoiQtblDbls());
       }
       if (this.hsbaCTNoi.getHsbactnoiTtkqxncls() != null) {
         params.put("TTKQCLSCD", this.hsbaCTNoi.getHsbactnoiTtkqxncls());
       }
       if (this.hsbaCTNoi.getHsbactnoiPpdieutri() != null) {
         params.put("PPDT", this.hsbaCTNoi.getHsbactnoiPpdieutri());
       }
       if (this.hsbaCTNoi.getHsbactnoiTtnguoibenhrav() != null) {
         params.put("TTNBRV", this.hsbaCTNoi.getHsbactnoiTtnguoibenhrav());
       }
       if (this.hsbaCTNoi.getHsbactnoiHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTNoi.getHsbactnoiHuongdtCdtt());
       }
       if (this.hsbaCTNoi.getHsbactnoiSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTNoi.getHsbactnoiSotoxquang());
       }
       if (this.hsbaCTNoi.getHsbactnoiSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTNoi.getHsbactnoiSotoctscanner());
       }
       if (this.hsbaCTNoi.getHsbactnoiSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTNoi.getHsbactnoiSotosieuam());
       }
       if (this.hsbaCTNoi.getHsbactnoiSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTNoi.getHsbactnoiSotoxn());
       }
       if (this.hsbaCTNoi.getHsbactnoiSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTNoi.getHsbactnoiSotokhac());
       }
       if (this.hsbaCTNoi.getHsbactnoiTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTNoi.getHsbactnoiTongsoto());
       }
       if (this.hsbaCTNoi.getHsbactnoiNguoigiaoba() != null) {
         params.put("NGUOIGIAOHS", this.hsbaCTNoi.getHsbactnoiNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNoi.getHsbactnoiNguoinhanba() != null) {
         params.put("NGUOINHANHS", this.hsbaCTNoi.getHsbactnoiNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNoi.getHsbactnoiBSdieutri() != null) {
         params.put("BASIDT", this.hsbaCTNoi.getHsbactnoiBSdieutri(true).getDtdmnhanvienTen());
       }
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "CapNhatThongTinChiTietBANoi");
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

   public HsbaChiTietNoi getHsbaCTNoi()
   {
     return this.hsbaCTNoi;
   }

   public void setHsbaCTNoi(HsbaChiTietNoi hsbaCTNoi)
   {
     this.hsbaCTNoi = hsbaCTNoi;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBANoiAction

 * JD-Core Version:    0.7.0.1

 */