 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietSosinhDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
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
 @Name("B211_3_CapNhatThongTinSoSinh")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBASoSinhAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBASoSinhAction.class);
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
   private HsbaChiTietSosinh hsbaCTSosinh;
   private Hsba hosobenhan;
   private TiepDon tiepDon;
   private BenhNhan benhnhan;
   private String gioVoOi;
   private String ngayVoOi;
   private String gioSinh;
   private String ngaySinh;
   private String ngaysinhBo;
   private String ngaysinhMe;
   private int index = 0;
   private static final long ONE_HOUR = 3600000L;

   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init Chi tiet BA So sinh ***");

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

         HsbaChiTietSosinh hsbaCTSosinhTemp = null;
         try
         {
           hsbaCTSosinhTemp = HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error Xuat Report HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM: " + e);
         }
         if (hsbaCTSosinhTemp != null)
         {
           this.hsbaCTSosinh = hsbaCTSosinhTemp;

           SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
           if (this.hsbaCTSosinh.getHsbactsosinhVooiluc() != null)
           {
             this.gioVoOi = Utils.getGioPhut(this.hsbaCTSosinh.getHsbactsosinhVooiluc());
             this.ngayVoOi = sdf.format(this.hsbaCTSosinh.getHsbactsosinhVooiluc());
           }
           if (this.hsbaCTSosinh.getHsbactsosinhDeluc() != null)
           {
             this.gioSinh = Utils.getGioPhut(this.hsbaCTSosinh.getHsbactsosinhDeluc());
             this.ngaySinh = sdf.format(this.hsbaCTSosinh.getHsbactsosinhDeluc());
           }
           if (this.hsbaCTSosinh.getHsbactsosinhNgaysinhBo() != null) {
             this.ngaysinhBo = sdf.format(this.hsbaCTSosinh.getHsbactsosinhNgaysinhBo());
           }
           if (this.hsbaCTSosinh.getHsbactsosinhNgaysinhMe() != null) {
             this.ngaysinhMe = sdf.format(this.hsbaCTSosinh.getHsbactsosinhNgaysinhMe());
           }
         }
         else
         {
           this.hsbaCTSosinh = new HsbaChiTietSosinh();
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
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBASosinh";
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
     log.info("*** Starting ghinhan Chi tiet BA So Sinh ***");
     this.ghiNhanException = null;
     try
     {
       this.hsbaCTSosinh.setHsbacmMa(this.hsbaChuyenMon);

       Calendar vooiluc = Utils.getDBDate(this.gioVoOi, this.ngayVoOi);
       if (vooiluc != null) {
         this.hsbaCTSosinh.setHsbactsosinhVooiluc(vooiluc.getTime());
       }
       Calendar deluc = Utils.getDBDate(this.gioSinh, this.ngaySinh);
       if (deluc != null) {
         this.hsbaCTSosinh.setHsbactsosinhDeluc(deluc.getTime());
       }
       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       if ((this.ngaysinhBo != null) && (!this.ngaysinhBo.trim().equals(""))) {
         this.hsbaCTSosinh.setHsbactsosinhNgaysinhBo(sdf.parse(this.ngaysinhBo));
       }
       if ((this.ngaysinhMe != null) && (!this.ngaysinhMe.trim().equals(""))) {
         this.hsbaCTSosinh.setHsbactsosinhNgaysinhMe(sdf.parse(this.ngaysinhMe));
       }
     }
     catch (ParseException e) {}
     RemoveUtil.removeAllNullFromHSBACTSosinh(this.hsbaCTSosinh);
     if (this.hsbaCTSosinh.getHsbactsosinhMa() == null) {
       HsbaChiTietSosinhDelegate.getInstance().create(this.hsbaCTSosinh);
     } else {
       HsbaChiTietSosinhDelegate.getInstance().edit(this.hsbaCTSosinh);
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
     this.loaiBCDT = "CapNhatThongTinChiTietBASosinh";
     log.info("Vao Method XuatReport cap nhat thong tin chi tiet benh an So sinh");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport0.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport1.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/benhansosinh_N06_subreport2.jrxml";

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

       params.put("sub0", sub1Report);
       params.put("sub1", sub2Report);
       params.put("sub2", sub3Report);





















       HsbaChuyenMonDelegate hsbadel = HsbaChuyenMonDelegate.getInstance();


       HsbaChiTietSosinh hsbaCTSosinhTemp = null;
       try
       {
         hsbaCTSosinhTemp = HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error Xuat Report HsbaChiTietSosinhDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTSosinhTemp != null) {
         this.hsbaCTSosinh = hsbaCTSosinhTemp;
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
       params.put("HOTENBO", this.hsbaCTSosinh.getHsbactsosinhHotenBo());
       params.put("HOTENME", this.hsbaCTSosinh.getHsbactsosinhHotenMe());
       params.put("NGHENGHIEPBO", this.hsbaCTSosinh.getHsbactsosinhNghenghiepBo());
       params.put("NGHENGHIEPME", this.hsbaCTSosinh.getHsbactsosinhNghenghiepMe());
       if (this.hsbaCTSosinh.getHsbactsosinhNgaysinhMe() != null) {
         params.put("NGAYSINHME", sdf.format(this.hsbaCTSosinh.getHsbactsosinhNgaysinhMe()));
       }
       if (this.hsbaCTSosinh.getHsbactsosinhNgaysinhBo() != null) {
         params.put("NGAYSINHBO", sdf.format(this.hsbaCTSosinh.getHsbactsosinhNgaysinhBo()));
       }
       params.put("DELANMAY", this.hsbaCTSosinh.getHsbactsosinhDelanmay());
       params.put("NHOMMAUME", this.hsbaCTSosinh.getHsbactsosinhNhommaume());
       if ((this.hsbaCTSosinh.getHsbactsosinhSinhduthang() != null) && (this.hsbaCTSosinh.getHsbactsosinhSinhduthang().booleanValue() == true)) {
         params.put("SINHDUTHANG", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhSinhdenon() != null) && (this.hsbaCTSosinh.getHsbactsosinhSinhdenon().booleanValue() == true)) {
         params.put("SOMDENON", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhSinhnaohut() != null) && (this.hsbaCTSosinh.getHsbactsosinhSinhnaohut().booleanValue() == true)) {
         params.put("SAYNAOHUT", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhSinhsong() != null) && (this.hsbaCTSosinh.getHsbactsosinhSinhsong().booleanValue() == true)) {
         params.put("SONG", "X");
       }
       if (this.hsbaCTSosinh.getHsbactsosinhLydovaov() != null) {
         params.put("LYDOVAOVIEN", this.hsbaCTSosinh.getHsbactsosinhLydovaov());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhQtbenhly() != null) {
         params.put("HOIBENH", this.hsbaCTSosinh.getHsbactsosinhQtbenhly());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhVooiluc() != null) {
         params.put("VOOILUC", Utils.getGioPhutNgay(this.hsbaCTSosinh.getHsbactsosinhVooiluc()));
       }
       if (this.hsbaCTSosinh.getHsbactsosinhMausac() != null) {
         params.put("MAUSAC", this.hsbaCTSosinh.getHsbactsosinhMausac());
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhDethuong() != null) && (this.hsbaCTSosinh.getHsbactsosinhDethuong().booleanValue() == true)) {
         params.put("DETHUONG", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhCanthiep() != null) && (this.hsbaCTSosinh.getHsbactsosinhCanthiep().booleanValue() == true)) {
         params.put("DECANTHIEP", "X");
       }
       if (this.hsbaCTSosinh.getHsbactsosinhDeluc() != null) {
         params.put("DELUC", Utils.getGioPhutNgay(this.hsbaCTSosinh.getHsbactsosinhDeluc()));
       }
       params.put("LYDOCANTHIEP", this.hsbaCTSosinh.getHsbactsosinhLydocanthiep());
       if ((this.hsbaCTSosinh.getHsbactsosinhTinhtrangKhocngay() != null) && (this.hsbaCTSosinh.getHsbactsosinhTinhtrangKhocngay().booleanValue() == true)) {
         params.put("TTSS_KHOCNGAY", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhTinhtrangNgat() != null) && (this.hsbaCTSosinh.getHsbactsosinhTinhtrangNgat().booleanValue() == true)) {
         params.put("TTSS_NGAT", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhTinhtrangKhac() != null) && (this.hsbaCTSosinh.getHsbactsosinhTinhtrangKhac().booleanValue() == true)) {
         params.put("TTSS_KHAC", "X");
       }
       params.put("TENNGUOIDODE", this.hsbaCTSosinh.getHsbactsosinhHotennguoidode());
       params.put("APGAR1PHUT", this.hsbaCTSosinh.getHsbactsosinhApgar1phut());
       params.put("APGAR5PHUT", this.hsbaCTSosinh.getHsbactsosinhApgar5phut());
       params.put("APGAR10PHUT", this.hsbaCTSosinh.getHsbactsosinhApgar10phut());
       params.put("TTDDSAUSINH", this.hsbaCTSosinh.getHsbactsosinhTinhtrangdinhduong());
       params.put("CANNANGLUCSINH", this.hsbaCTSosinh.getHsbactsosinhCannanglucsinh());
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhHutdich() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhHutdich().booleanValue() == true)) {
         params.put("HOISINH_HUTDICH", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhXoaboptim() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhXoaboptim().booleanValue() == true)) {
         params.put("HOISINH_XOABOPTIM", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhThoo2() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhThoo2().booleanValue() == true)) {
         params.put("HOISINH_THOO2", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhDatnoikhiquan() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhDatnoikhiquan().booleanValue() == true)) {
         params.put("HOISINH_DATNOIKHIQUAN", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhBopbongo2() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhBopbongo2().booleanValue() == true)) {
         params.put("HOISINH_BOPBONGO2", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhHoisinhKhac() != null) && (this.hsbaCTSosinh.getHsbactsosinhHoisinhKhac().booleanValue() == true)) {
         params.put("HOISINH_KHAC", "X");
       }
       params.put("HOTENCHUYENSOSINH", this.hsbaCTSosinh.getHsbactsosinhHotennguoichuyensosinh());
       if ((this.hsbaCTSosinh.getHsbactsosinhDitatbamsinh() != null) && (this.hsbaCTSosinh.getHsbactsosinhDitatbamsinh().booleanValue() == true)) {
         params.put("TT_DITATBAMSINH", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhCohaumon() != null) && (this.hsbaCTSosinh.getHsbactsosinhCohaumon().booleanValue() == true)) {
         params.put("TT_COHAUMON", "X");
       }
       params.put("CUTHEDITAT", this.hsbaCTSosinh.getHsbactsosinhCutheditat());
       params.put("TINHHINHSOSINHVAOKHOA", this.hsbaCTSosinh.getHsbactsosinhTinhhinhsosinhvaokhoa());
       params.put("TINHTRANGTOANTHAN", this.hsbaCTSosinh.getHsbactsosinhTinhtrangtoanthan());
       params.put("THSS_CANNANG", this.hsbaCTSosinh.getHsbactsosinhCannang());
       params.put("THSS_CHIEUDAI", this.hsbaCTSosinh.getHsbactsosinhChieudai());
       params.put("THSS_VONGDAU", this.hsbaCTSosinh.getHsbactsosinhVongdau());
       params.put("TTTT_NHIETDO", this.hsbaCTSosinh.getHsbactsosinhToanthanNhietdo());
       params.put("TTTT_NHIPTHO", this.hsbaCTSosinh.getHsbactsosinhToanthanNhiptho());
       if ((this.hsbaCTSosinh.getHsbactsosinhMausacdaHonghao() != null) && (this.hsbaCTSosinh.getHsbactsosinhMausacdaHonghao().booleanValue() == true)) {
         params.put("MAUSACDA_HONGHAO", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhMausacdaXanhtai() != null) && (this.hsbaCTSosinh.getHsbactsosinhMausacdaXanhtai().booleanValue() == true)) {
         params.put("MAUSACDA_XANHTAI", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhMausacdaVang() != null) && (this.hsbaCTSosinh.getHsbactsosinhMausacdaVang().booleanValue() == true)) {
         params.put("MAUSACDA_VANG", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhMausacdaTim() != null) && (this.hsbaCTSosinh.getHsbactsosinhMausacdaTim().booleanValue() == true)) {
         params.put("MAUSACDA_TIM", "X");
       }
       if ((this.hsbaCTSosinh.getHsbactsosinhMausacdaKhac() != null) && (this.hsbaCTSosinh.getHsbactsosinhMausacdaKhac().booleanValue() == true)) {
         params.put("MAUSACDA_KHAC", "X");
       }
       params.put("HOHAP_NHIPTHO", this.hsbaCTSosinh.getHsbactsosinhCoquankhacNhiptho());
       params.put("HOHAP_NGHEPHOI", this.hsbaCTSosinh.getHsbactsosinhCoquankhacNghephoi());
       params.put("HOHAP_SILVERMAN", this.hsbaCTSosinh.getHsbactsosinhCoquankhacSilverman());
       params.put("TIMMACH_NHIPTIM", this.hsbaCTSosinh.getHsbactsosinhCoquankhacNhiptim());
       params.put("BUNG", this.hsbaCTSosinh.getHsbactsosinhCoquankhacBung());
       params.put("COQUANSINHDUCNGOAI", this.hsbaCTSosinh.getHsbactsosinhCoquankhacSinhducngoai());




       params.put("XUONGKHOP", this.hsbaCTSosinh.getHsbactsosinhCoquankhacXuongkhop());
       params.put("THANKINH_PHANXA", this.hsbaCTSosinh.getHsbactsosinhCoquankhacThankinhPhanxa());
       params.put("THANKINH_TRUONGLUCCO", this.hsbaCTSosinh.getHsbactsosinhCoquankhacThankinhTruonglucco());
       if (this.hsbaCTSosinh.getHsbactsosinhBslamba() != null) {
         params.put("BASILAMBA", this.hsbaCTSosinh.getHsbactsosinhBslamba(true).getDtdmnhanvienTen());
       }
       params.put("TOMTATBENHAN", this.hsbaCTSosinh.getHsbactsosinhTtba());
       params.put("XETNGHIEMCLS", this.hsbaCTSosinh.getHsbactsosinhXetnghiemcanlamsan());
       params.put("CHIDINHTHEODOI", this.hsbaCTSosinh.getHsbactsosinhChidinhtheodoi());
       if (this.hsbaCTSosinh.getHsbactsosinhQtblDbls() != null) {
         params.put("QTBLDBLS", this.hsbaCTSosinh.getHsbactsosinhQtblDbls());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhTtkqxncls() != null) {
         params.put("TTKQCLSCD", this.hsbaCTSosinh.getHsbactsosinhTtkqxncls());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhPpdieutri() != null) {
         params.put("PPDT", this.hsbaCTSosinh.getHsbactsosinhPpdieutri());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhTtnguoibenhrav() != null) {
         params.put("TTNBRV", this.hsbaCTSosinh.getHsbactsosinhTtnguoibenhrav());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhHuongdtCdtt() != null) {
         params.put("HDTCCDTT", this.hsbaCTSosinh.getHsbactsosinhHuongdtCdtt());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhSotoxquang() != null) {
         params.put("XQUANG", this.hsbaCTSosinh.getHsbactsosinhSotoxquang());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhSotoctscanner() != null) {
         params.put("SCANNER", this.hsbaCTSosinh.getHsbactsosinhSotoctscanner());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhSotosieuam() != null) {
         params.put("SIEUAM", this.hsbaCTSosinh.getHsbactsosinhSotosieuam());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhSotoxn() != null) {
         params.put("XETNGHIEM", this.hsbaCTSosinh.getHsbactsosinhSotoxn());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhSotokhac() != null) {
         params.put("HSKHAC", this.hsbaCTSosinh.getHsbactsosinhSotokhac());
       }
       log.info("### TONGSOTO: " + this.hsbaCTSosinh.getHsbactsosinhTongsoto());
       if (this.hsbaCTSosinh.getHsbactsosinhTongsoto() != null) {
         params.put("TONGHOSO", this.hsbaCTSosinh.getHsbactsosinhTongsoto());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhNguoigiaoba() != null) {
         params.put("NGUOIGIAOBA", this.hsbaCTSosinh.getHsbactsosinhNguoigiaoba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhNguoinhanba() != null) {
         params.put("NGUOINHANBA", this.hsbaCTSosinh.getHsbactsosinhNguoinhanba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTSosinh.getHsbactsosinhBsdieutri() != null) {
         params.put("BASIDT", this.hsbaCTSosinh.getHsbactsosinhBsdieutri(true).getDtdmnhanvienTen());
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBASosinh");


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

   public HsbaChiTietSosinh getHsbaCTSosinh()
   {
     return this.hsbaCTSosinh;
   }

   public void setHsbaCTSosinh(HsbaChiTietSosinh hsbaCTSosinh)
   {
     this.hsbaCTSosinh = hsbaCTSosinh;
   }

   public String getHoTen()
   {
     return this.hoTen;
   }

   public void setHoTen(String hoTen)
   {
     this.hoTen = hoTen;
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

   public String getGioSinh()
   {
     return this.gioSinh;
   }

   public void setGioSinh(String gioSinh)
   {
     this.gioSinh = gioSinh;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgaysinhBo()
   {
     return this.ngaysinhBo;
   }

   public void setNgaysinhBo(String ngaysinhBo)
   {
     this.ngaysinhBo = ngaysinhBo;
   }

   public String getNgaysinhMe()
   {
     return this.ngaysinhMe;
   }

   public void setNgaysinhMe(String ngaysinhMe)
   {
     this.ngaysinhMe = ngaysinhMe;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBASoSinhAction

 * JD-Core Version:    0.7.0.1

 */