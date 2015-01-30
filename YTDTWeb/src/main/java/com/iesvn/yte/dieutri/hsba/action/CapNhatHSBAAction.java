 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.AuthorizationException;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B211_Capnhathosobenhan")
 @Synchronized(timeout=6000000L)
 public class CapNhatHSBAAction
   implements Serializable
 {
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   String userLogedIn = "";
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(CapNhatHSBAAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   private String chonloaiba;
   @DataModel
   private List<DmBenhIcd> benhICDList;
   @In(required=false)
   @Out(required=false)
   private String soBenhAn;
   @In(required=false)
   @Out(required=false)
   private String khoaMa;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     log.info("begin init()");
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayHienTai = formatter.format(new Date());
     resetValue();

     log.info("end init()");
     this.tenChuongTrinh = MyMenuYTDTAction.dieuTri;
     return "DieuTri_CapNhat_CapNhatThongTinChung";
   }

   @In(required=false)
   @Out(required=false)
   private String goToB211_1 = null;
   @In(required=false)
   @Out(required=false)
   private String goToB211_2 = null;
   @In(required=false)
   @Out(required=false)
   private String goToB211_3 = null;

   @End
   public void end() {}

   public String goToHSBAChiTiet()
   {
     log.info("Begin goToHSBAChiTiet(), chonloaiba = " + this.chonloaiba);
     this.soBenhAn = this.hoSoBenhAn.getHsbaSovaovien();
     this.khoaMa = this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();

     HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
     HsbaChuyenMon hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);


     log.info("hsbaChuyenMon = " + hsbaChuyenMon);
     if (hsbaChuyenMon == null)
     {
       FacesMessages.instance().add(IConstantsRes.ghi_nhan_truoc_ghi_nhap_benh_an_chi_tiet, new Object[0]);
       return "";
     }
     log.info("Truoc if, chonloaiba = " + this.chonloaiba);
     if ("benhannoi".equals(this.chonloaiba))
     {
       this.goToB211_3 = null;
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoi";
     }
     if ("benhantmh".equals(this.chonloaiba))
     {
       log.info("Da chon benhantmh = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBATmh";
     }
     if ("benhanmat".equals(this.chonloaiba))
     {
       log.info("Da chon benhanmat = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBAMat";
     }
     if ("benhanrhm".equals(this.chonloaiba))
     {
       log.info("Da chon benhanrhm = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBARhm";
     }
     if ("benhannhikhoa".equals(this.chonloaiba))
     {
       log.info("Da chon benhannhikhoa = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANhikhoa";
     }
     if ("benhansosinh".equals(this.chonloaiba))
     {
       log.info("Da chon benhansosinh = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBASosinh";
     }
     if ("benhansankhoa".equals(this.chonloaiba))
     {
       log.info("Da chon benhansankhoa = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBASankhoa";
     }
     if ("benhanphukhoa".equals(this.chonloaiba))
     {
       log.info("Da chon benhanphukhoa = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBAPhukhoa";
     }
     if ("benhanngoaitru_yhoccotruyen".equals(this.chonloaiba))
     {
       log.info("Da chon benhanngoaitru_yhoccotruyen = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoaitruYhct";
     }
     if ("benhannaophathai".equals(this.chonloaiba))
     {
       log.info("Da chon benhannaophathai = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANaophathai";
     }
     if ("benhannoitru_yhoccotruyen".equals(this.chonloaiba))
     {
       log.info("Da chon benhannoitru_yhoccotruyen = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoitruYhct";
     }
     if ("benhanngoai".equals(this.chonloaiba))
     {
       log.info("Da chon benh an ngoai = " + this.chonloaiba);
       return "DieuTri_CapNhat_CapNhatThongTinChiTietBANgoai";
     }
     return "";
   }

   public String goToHSBAMo()
   {
     this.soBenhAn = this.hoSoBenhAn.getHsbaSovaovien();
     this.khoaMa = this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();

     this.goToB211_1 = null;
     return "DieuTri_CapNhat_CapNhatThongTinMo";
   }

   public String goToHSBASan()
   {
     this.soBenhAn = this.hoSoBenhAn.getHsbaSovaovien();
     this.khoaMa = this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();

     this.goToB211_2 = null;
     return "DieuTri_CapNhat_CapNhatThongTinSan";
   }

   public String getUserLogedIn()
   {
     return this.userLogedIn;
   }

   public void setUserLogedIn(String userLogedIn)
   {
     this.userLogedIn = userLogedIn;
   }

   public String getSoBenhAn()
   {
     return this.soBenhAn;
   }

   public void setSoBenhAn(String soBenhAn)
   {
     this.soBenhAn = soBenhAn;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public boolean ableToSeeSomePage()
   {
     return false;
   }

   private boolean ableToSeeSomePage = true;
   private Boolean thuThuatPhauThuat;
   private Boolean sinh;
   private BenhNhan benhNhan;
   private String tuoi;
   private String ngaySinh;
   private Hsba hoSoBenhAn;
   private TiepDon tiepdon;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioVaoKhoa;
   private String ngayVaoKhoa;
   private String gioRaVien;
   private String ngayRaVien;
   private String gioRaKhoa;
   private String ngayRaKhoa;
   private String gioTuVong;
   private String ngayTuVong;

   public boolean isAbleToSeeSomePage()
   {
     return this.ableToSeeSomePage;
   }

   public void setAbleToSeeSomePage(boolean ableToSeeSomePage)
   {
     this.ableToSeeSomePage = ableToSeeSomePage;
   }

   private void resetValue()
   {
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     this.hoSoBenhAn = new Hsba();
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.tiepdon = new TiepDon();

     this.hoSoBenhAnCM = new HsbaChuyenMon();
     SetInforUtil.setInforIfNullForHSBACM(this.hoSoBenhAnCM);

     this.hoSoBenhAnCV = new HsbaChuyenVien();
     SetInforUtil.setInforIfNullForHsbaChuyenVien(this.hoSoBenhAnCV);

     this.hoSoBenhAnNop = new HsbaNop();
     SetInforUtil.setInforIfNullForHSBANop(this.hoSoBenhAnNop);

     this.tuoi = "";
     this.ngaySinh = "";

     this.gioVaoVien = "";
     this.ngayVaoVien = "";

     this.gioVaoKhoa = "";
     this.ngayVaoKhoa = "";


     this.gioRaVien = "";
     this.ngayRaVien = "";

     this.gioRaKhoa = "";
     this.ngayRaKhoa = "";

     this.gioTuVong = "";
     this.ngayTuVong = "";

     this.ngayNopBA = "";

     this.resultHidden = "";
     this.benhICDList = null;
   }

   private String resultHidden = "";
   private String gioi = "";
   private HsbaChuyenMon hoSoBenhAnCM;
   private HsbaChuyenVien hoSoBenhAnCV;
   private HsbaNop hoSoBenhAnNop;
   private String ngayNopBA;
   private String ngayHienTai;

   public void nhaplai()
     throws Exception
   {
     resetValue();
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

     Date ngayGioVaoVien = this.hoSoBenhAn.getHsbaNgaygiovaov();
     if (ngayGioVaoVien != null)
     {
       this.gioVaoVien = Utils.getGioPhut(ngayGioVaoVien);
       this.ngayVaoVien = formatter.format(ngayGioVaoVien);
     }
     Date ngayGioRaVien = this.hoSoBenhAn.getHsbaNgaygiorav();
     if (ngayGioRaVien != null)
     {
       this.gioRaVien = Utils.getGioPhut(ngayGioRaVien);
       this.ngayRaVien = formatter.format(ngayGioRaVien);
     }
     Date ngayGioVaoKhoa = this.hoSoBenhAnCM.getHsbacmNgaygiovaok();
     if (ngayGioVaoKhoa != null)
     {
       this.gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa);
       this.ngayVaoKhoa = formatter.format(ngayGioVaoKhoa);
     }
     Date ngayGioTV = this.hoSoBenhAn.getHsbaNgaygiotv();
     if (ngayGioTV != null)
     {
       this.gioTuVong = Utils.getGioPhut(ngayGioTV);
       this.ngayTuVong = formatter.format(ngayGioTV);
     }
     if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(this.benhNhan.getBenhnhanNgaysinh());
     }
     if (this.benhNhan.getDmgtMaso() != null)
     {
       if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
         this.gioi = "r1";
       } else {
         this.gioi = "r2";
       }
     }
     else {
       this.gioi = null;
     }
     Date ngayGioNop = this.hoSoBenhAnNop.getHsbanopNgaygionop();
     if (ngayGioNop != null) {
       this.ngayNopBA = formatter.format(ngayGioNop);
     }
   }

   public void displayInfor()
     throws Exception
   {
     log.info("############### hoSoBenhAnCM.getKhoaMa() : " + this.hoSoBenhAnCM.getKhoaMa());
     if (this.hoSoBenhAnCM.getKhoaMa() != null) {
       log.info("############### hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa() : " + this.hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa());
     }
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() == null) || (this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa().equals("")))
     {
       resetValue();
       return;
     }
     String khoa_tmp = "";
     if ((this.hoSoBenhAnCM.getKhoaMa() != null) && (this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() != null)) {
       khoa_tmp = this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa();
     }
     String hsba_tmp = "";
     if (this.hoSoBenhAn.getHsbaSovaovien() != null) {
       hsba_tmp = this.hoSoBenhAn.getHsbaSovaovien();
     }
     HsbaChuyenMon hoSoBenhAnCM_temp = null;
     if (!"".equals(Utils.reFactorString(this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa())))
     {
       HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();



       hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(this.hoSoBenhAn.getHsbaSovaovien(), this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa());
     }
     TiepDonDelegate tdDele = TiepDonDelegate.getInstance();
     if (hoSoBenhAnCM_temp == null)
     {
       if (!"".equals(Utils.reFactorString(this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa())))
       {
         HsbaDelegate hsbadelegate = HsbaDelegate.getInstance();
         Hsba hsba_temp = hsbadelegate.findByHsbaAndKhoaDangDieuTri(hsba_tmp, khoa_tmp);
         if (hsba_temp != null)
         {
           this.hoSoBenhAn = hsba_temp;
           SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

           this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
           SetInforUtil.setInforIfNullForBN(this.benhNhan);
           if ((this.hoSoBenhAn.getTiepdonMa() != null) && (!this.hoSoBenhAn.getTiepdonMa().equals("")))
           {
             this.tiepdon = tdDele.find(this.hoSoBenhAn.getTiepdonMa());
             if (this.tiepdon == null) {
               this.tiepdon = new TiepDon();
             }
           }
           setOtherValue();


           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
           if ((this.gioVaoVien == "") && (this.ngayVaoVien == ""))
           {
             Calendar acc = Calendar.getInstance();
             this.gioVaoVien = Utils.getGioPhut(acc.getTime());
             this.ngayVaoVien = formatter.format(acc.getTime());
           }
           if ((this.gioVaoKhoa == "") && (this.ngayVaoKhoa == ""))
           {
             Calendar acc = Calendar.getInstance();
             this.gioVaoKhoa = Utils.getGioPhut(acc.getTime());
             this.ngayVaoKhoa = formatter.format(acc.getTime());
           }
           return;
         }
         resetValue();
         FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { hsba_tmp, khoa_tmp });
         this.resultHidden = "fail";
         return;
       }
       resetValue();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { hsba_tmp, khoa_tmp });
       this.resultHidden = "fail";
       return;
     }
     if (hoSoBenhAnCM_temp != null)
     {
       this.hoSoBenhAnCM = hoSoBenhAnCM_temp;
       if ((this.benhICDList != null) &&
         (this.benhICDList.size() > 0)) {
         this.benhICDList.clear();
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhphu() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.hoSoBenhAnCM.getHsbacmBenhphu());
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhphu2() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.hoSoBenhAnCM.getHsbacmBenhphu2());
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhphu3() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.hoSoBenhAnCM.getHsbacmBenhphu3());
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhphu4() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.hoSoBenhAnCM.getHsbacmBenhphu4());
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhphu5() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.hoSoBenhAnCM.getHsbacmBenhphu5());
       }
       SetInforUtil.setInforIfNullForHSBACM(this.hoSoBenhAnCM);
       this.hoSoBenhAn = this.hoSoBenhAnCM.getHsbaSovaovien();
       SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

       this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
       SetInforUtil.setInforIfNullForBN(this.benhNhan);
       if ((this.hoSoBenhAn.getTiepdonMa() != null) && (!this.hoSoBenhAn.getTiepdonMa().equals("")))
       {
         this.tiepdon = tdDele.find(this.hoSoBenhAn.getTiepdonMa());
         if (this.tiepdon == null) {
           this.tiepdon = new TiepDon();
         }
       }
       HsbaChuyenVienDelegate delegate = HsbaChuyenVienDelegate.getInstance();

       this.hoSoBenhAnCV = delegate.findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
       if (this.hoSoBenhAnCV == null) {
         this.hoSoBenhAnCV = new HsbaChuyenVien();
       }
       SetInforUtil.setInforIfNullForHsbaChuyenVien(this.hoSoBenhAnCV);




       HsbaNopDelegate hsbaNopDelegate = HsbaNopDelegate.getInstance();

       this.hoSoBenhAnNop = hsbaNopDelegate.findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
       if (this.hoSoBenhAnNop == null) {
         this.hoSoBenhAnNop = new HsbaNop();
       }
       SetInforUtil.setInforIfNullForHSBANop(this.hoSoBenhAnNop);
       setOtherValue();


       SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
       if ((this.gioVaoVien == "") && (this.ngayVaoVien == ""))
       {
         Calendar acc = Calendar.getInstance();
         this.gioVaoVien = Utils.getGioPhut(acc.getTime());
         this.ngayVaoVien = formatter.format(acc.getTime());
       }
       if ((this.gioVaoKhoa == "") && (this.ngayVaoKhoa == ""))
       {
         Calendar acc = Calendar.getInstance();
         this.gioVaoKhoa = Utils.getGioPhut(acc.getTime());
         this.ngayVaoKhoa = formatter.format(acc.getTime());
       }
     }
     if ((this.hoSoBenhAnCM.getHsbacmLaphauthuat() != null) && (this.hoSoBenhAnCM.getHsbacmLaphauthuat().booleanValue() == true)) {
       this.thuThuatPhauThuat = Boolean.valueOf(true);
     }
     if ((this.hoSoBenhAnCM.getHsbacmLathuthuat() != null) && (this.hoSoBenhAnCM.getHsbacmLathuthuat().booleanValue() == true)) {
       this.thuThuatPhauThuat = Boolean.valueOf(true);
     }
     if ((this.hoSoBenhAnCM.getHsbacmLasinh() != null) && (this.hoSoBenhAnCM.getHsbacmLasinh().booleanValue() == true)) {
       this.sinh = Boolean.valueOf(true);
     }
   }

   public void ghiNhan()
     throws Exception
   {
     log.info("############### hoSoBenhAnCM.getKhoaMa() : " + this.hoSoBenhAnCM.getKhoaMa());
     if (this.hoSoBenhAnCM.getKhoaMa() != null) {
       log.info("############### hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa() : " + this.hoSoBenhAnCM.getKhoaMa(true).getDmkhoaMa());
     }
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() == null) || (this.hoSoBenhAnCM.getKhoaMa().getDmkhoaMa().equals("")))
     {
       resetValue();
       return;
     }
     this.hoSoBenhAnCM.setHsbacmBenhphu(null);
     this.hoSoBenhAnCM.setHsbacmBenhphu2(null);
     this.hoSoBenhAnCM.setHsbacmBenhphu3(null);
     this.hoSoBenhAnCM.setHsbacmBenhphu4(null);
     this.hoSoBenhAnCM.setHsbacmBenhphu5(null);
     if ((this.benhICDList != null) && (this.benhICDList.size() > 0)) {
       for (DmBenhIcd benh : this.benhICDList) {
         if (this.hoSoBenhAnCM.getHsbacmBenhphu() == null) {
           this.hoSoBenhAnCM.setHsbacmBenhphu(benh);
         } else if (benh.getDmbenhicdMaso().intValue() != this.hoSoBenhAnCM.getHsbacmBenhphu(true).getDmbenhicdMaso().intValue()) {
           if (this.hoSoBenhAnCM.getHsbacmBenhphu2() == null) {
             this.hoSoBenhAnCM.setHsbacmBenhphu2(benh);
           } else if (this.hoSoBenhAnCM.getHsbacmBenhphu3() == null) {
             this.hoSoBenhAnCM.setHsbacmBenhphu3(benh);
           } else if (this.hoSoBenhAnCM.getHsbacmBenhphu4() == null) {
             this.hoSoBenhAnCM.setHsbacmBenhphu4(benh);
           } else if (this.hoSoBenhAnCM.getHsbacmBenhphu5() == null) {
             this.hoSoBenhAnCM.setHsbacmBenhphu5(benh);
           }
         }
       }
     }
     HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();

     RemoveUtil.removeAllNullFromBN(this.benhNhan);
     RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
     RemoveUtil.removeAllNullFromHSBACM(this.hoSoBenhAnCM);
     RemoveUtil.removeAllNullFromHSBACV(this.hoSoBenhAnCV);
     RemoveUtil.removeAllNullFromHSBANop(this.hoSoBenhAnNop);
     if ((this.gioVaoKhoa != null) && (!this.gioVaoKhoa.equals("")) && (this.ngayVaoKhoa != null) && (!this.ngayVaoKhoa.equals("")))
     {
       Calendar hsbaNgaygiovaok = Utils.getDBDate(this.gioVaoKhoa, this.ngayVaoKhoa);
       this.hoSoBenhAnCM.setHsbacmNgaygiovaok(hsbaNgaygiovaok.getTime());
     }
     else
     {
       this.hoSoBenhAnCM.setHsbacmNgaygiovaok(null);
     }
     if (this.hoSoBenhAnCM.getHsbacmHuongdieutri() != null)
     {
       if ((this.hoSoBenhAnCM.getHsbacmHuongdieutri().equals("3")) || (this.hoSoBenhAnCM.getHsbacmHuongdieutri().equals("2")))
       {
         if ((this.gioRaVien != null) && (!this.gioRaVien.equals("")) && (this.ngayRaVien != null) && (!this.ngayRaVien.equals("")))
         {
           Calendar hsbaNgaygiorav = Utils.getDBDate(this.gioRaVien, this.ngayRaVien);
           this.hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
           this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorav.getTime());
           log.info("Co nhap hsbaNgaygiorav");
         }
         else
         {
           this.ngayRaVien = Utils.getCurrentDate();
           this.gioRaVien = Utils.getGioPhut(new Date());
           Calendar hsbaNgaygiorav = Utils.getDBDate(this.gioRaVien, this.ngayRaVien);
           this.hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiorav.getTime());
           this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorav.getTime());
           log.info("Lay ngay gio hien tai la hsbaNgaygiorav");
         }
         if (this.hoSoBenhAn.getHsbaKhoadangdt() != null) {
           this.hoSoBenhAn.setHsbaKhoarav(this.hoSoBenhAn.getHsbaKhoadangdt());
         }
         if (this.hoSoBenhAnCM.getHsbacmBenhchinh() != null) {
           this.hoSoBenhAn.setHsbaMachdravien(this.hoSoBenhAnCM.getHsbacmBenhchinh());
         }
       }
       if (this.hoSoBenhAnCM.getHsbacmHuongdieutri().equals("1"))
       {
         String ngayChuyenKhoa = Utils.getCurrentDate();
         String gioChuyenKhoa = Utils.getGioPhut(new Date());
         Calendar hsbaNgaygiorak = Utils.getDBDate(gioChuyenKhoa, ngayChuyenKhoa);
         this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());
         log.info("Huong dieu tri = 1");
       }
     }
     if ((this.hoSoBenhAnCM.getKetquaMa() != null) && (this.hoSoBenhAnCM.getKetquaMa().getDmkqdtMaso().intValue() == 5))
     {
       DmKetQuaDieuTri kqdt = new DmKetQuaDieuTri(Integer.valueOf(5));
       this.hoSoBenhAn.setHsbaKetqua(kqdt);
       if ((this.gioTuVong != null) && (!this.gioTuVong.equals("")) && (this.ngayTuVong != null) && (!this.ngayTuVong.equals("")))
       {
         Calendar hsbaNgaygiotuvong = Utils.getDBDate(this.gioTuVong, this.ngayTuVong);
         this.hoSoBenhAn.setHsbaNgaygiotv(hsbaNgaygiotuvong.getTime());
         this.hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiotuvong.getTime());
         this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiotuvong.getTime());
       }
       else
       {
         this.ngayTuVong = Utils.getCurrentDate();
         this.gioTuVong = Utils.getGioPhut(new Date());
         Calendar hsbaNgaygiotuvong = Utils.getDBDate(this.gioTuVong, this.ngayTuVong);
         this.hoSoBenhAn.setHsbaNgaygiotv(hsbaNgaygiotuvong.getTime());
         this.hoSoBenhAn.setHsbaNgaygiorav(hsbaNgaygiotuvong.getTime());
         this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiotuvong.getTime());
       }
       if (this.hoSoBenhAn.getHsbaNgaygiovaov() != null)
       {
         long diff = (this.hoSoBenhAn.getHsbaNgaygiotv().getTime() - this.hoSoBenhAn.getHsbaNgaygiovaov().getTime()) / 3600000L;
         if ((diff >= 0L) && (diff <= 24L))
         {
           this.hoSoBenhAn.setHsbaTuvvong24g(Boolean.valueOf(true));
           log.info("### Tu vong 24G ### - Tu vong sau khi nhap vien (gio): " + diff);
         }
       }
       if (this.hoSoBenhAnCM.getKhoaMa() != null) {
         this.hoSoBenhAn.setHsbaKhoarav(this.hoSoBenhAnCM.getKhoaMa());
       }
       if (this.hoSoBenhAnCM.getHsbacmBenhchinh() != null)
       {
         this.hoSoBenhAn.setHsbaMachdravien(this.hoSoBenhAnCM.getHsbacmBenhchinh());
         if ((this.hoSoBenhAn.getHsbaTuvong() == null) || (this.hoSoBenhAn.getHsbaTuvong().getDmbenhicdMaso().equals(""))) {
           this.hoSoBenhAn.setHsbaTuvong(this.hoSoBenhAnCM.getHsbacmBenhchinh());
         }
       }
     }
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((this.ngayNopBA != null) && (!this.ngayNopBA.equals("")))
     {
       Date d = formatter.parse(this.ngayNopBA);
       Calendar dCalendar = Calendar.getInstance();
       dCalendar.setTime(d);
       this.hoSoBenhAnNop.setHsbanopNgaygionop(dCalendar.getTime());
     }
     else
     {
       this.hoSoBenhAnNop.setHsbanopNgaygionop(null);
     }
     if (this.hoSoBenhAnCM.getHsbacmMa() != null)
     {
       System.out.println("Identity.instance().hasRole('QT_KhoaNoiTru'):" + Identity.instance().hasRole("QT_KhoaNoiTru"));
       if (!Identity.instance().hasRole("QT_KhoaNoiTru")) {
         throw new AuthorizationException("");
       }
     }
     this.hoSoBenhAnCM.setHsbacmNgaygiocn(Calendar.getInstance().getTime());
     if ((this.tiepdon.getTiepdonMa() != null) && (!this.tiepdon.getTiepdonMa().equals(""))) {
       TiepDonDelegate.getInstance().edit(this.tiepdon);
     }
     String soVaoVienInfor = hsbaCMDelegate.capNhatHoSoBenhAn(this.hoSoBenhAn, this.hoSoBenhAnCM, this.hoSoBenhAnCV, this.hoSoBenhAnNop, this.benhNhan);


     FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + soVaoVienInfor, new Object[0]);
     this.resultHidden = "success";







     displayInfor();
   }

   public void themBenhphu()
   {
     if (this.benhICDList == null) {
       this.benhICDList = new ArrayList();
     }
     if (this.benhICDList.size() >= 5) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();


     DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(this.hoSoBenhAnCM.getHsbacmBenhphu(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
     if (benh == null) {
       return;
     }
     if (!isContain(this.benhICDList, benh))
     {
       this.benhICDList.add(benh);
       this.hoSoBenhAnCM.setHsbacmBenhphu(new DmBenhIcd());
     }
   }

   private boolean isContain(List<DmBenhIcd> lstBenh, DmBenhIcd benhtest)
   {
     boolean ketqua = false;
     if (lstBenh != null) {
       for (DmBenhIcd benh : lstBenh) {
         if (benhtest.getDmbenhicdMaso().intValue() == benh.getDmbenhicdMaso().intValue()) {
           return true;
         }
       }
     }
     return ketqua;
   }

   public void deleteCurrentRow(int vitri)
   {
     if ((this.benhICDList == null) || (this.benhICDList.size() == 0)) {
       return;
     }
     this.benhICDList.remove(vitri);
   }

   public static void main(String[] args) {}

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public Boolean getThuThuatPhauThuat()
   {
     return this.thuThuatPhauThuat;
   }

   public void setThuThuatPhauThuat(Boolean thuThuatPhauThuat)
   {
     this.thuThuatPhauThuat = thuThuatPhauThuat;
   }

   public Boolean getSinh()
   {
     return this.sinh;
   }

   public void setSinh(Boolean sinh)
   {
     this.sinh = sinh;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public TiepDon getTiepdon()
   {
     return this.tiepdon;
   }

   public void setTiepdon(TiepDon tiepdon)
   {
     this.tiepdon = tiepdon;
   }

   public String getGioVaoVien()
   {
     return this.gioVaoVien;
   }

   public void setGioVaoVien(String gioVaoVien)
   {
     this.gioVaoVien = gioVaoVien;
   }

   public String getNgayVaoVien()
   {
     return this.ngayVaoVien;
   }

   public void setNgayVaoVien(String ngayVaoVien)
   {
     this.ngayVaoVien = ngayVaoVien;
   }

   public String getGioVaoKhoa()
   {
     return this.gioVaoKhoa;
   }

   public void setGioVaoKhoa(String gioVaoKhoa)
   {
     this.gioVaoKhoa = gioVaoKhoa;
   }

   public String getNgayVaoKhoa()
   {
     return this.ngayVaoKhoa;
   }

   public void setNgayVaoKhoa(String ngayVaoKhoa)
   {
     this.ngayVaoKhoa = ngayVaoKhoa;
   }

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public String getGioRaKhoa()
   {
     return this.gioRaKhoa;
   }

   public void setGioRaKhoa(String gioRaKhoa)
   {
     this.gioRaKhoa = gioRaKhoa;
   }

   public String getNgayRaKhoa()
   {
     return this.ngayRaKhoa;
   }

   public void setNgayRaKhoa(String ngayRaKhoa)
   {
     this.ngayRaKhoa = ngayRaKhoa;
   }

   public String getGioTuVong()
   {
     return this.gioTuVong;
   }

   public void setGioTuVong(String gioTuVong)
   {
     this.gioTuVong = gioTuVong;
   }

   public String getNgayTuVong()
   {
     return this.ngayTuVong;
   }

   public void setNgayTuVong(String ngayTuVong)
   {
     this.ngayTuVong = ngayTuVong;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public HsbaChuyenMon getHoSoBenhAnCM()
   {
     return this.hoSoBenhAnCM;
   }

   public void setHoSoBenhAnCM(HsbaChuyenMon hoSoBenhAnCM)
   {
     this.hoSoBenhAnCM = hoSoBenhAnCM;
   }

   public HsbaChuyenVien getHoSoBenhAnCV()
   {
     return this.hoSoBenhAnCV;
   }

   public void setHoSoBenhAnCV(HsbaChuyenVien hoSoBenhAnCV)
   {
     this.hoSoBenhAnCV = hoSoBenhAnCV;
   }

   public HsbaNop getHoSoBenhAnNop()
   {
     return this.hoSoBenhAnNop;
   }

   public void setHoSoBenhAnNop(HsbaNop hoSoBenhAnNop)
   {
     this.hoSoBenhAnNop = hoSoBenhAnNop;
   }

   public String getNgayNopBA()
   {
     return this.ngayNopBA;
   }

   public void setNgayNopBA(String ngayNopBA)
   {
     this.ngayNopBA = ngayNopBA;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getGoToB211_1()
   {
     return this.goToB211_1;
   }

   public void setGoToB211_1(String goToB211_1)
   {
     this.goToB211_1 = goToB211_1;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getGoToB211_2()
   {
     return this.goToB211_2;
   }

   public void setGoToB211_2(String goToB211_2)
   {
     this.goToB211_2 = goToB211_2;
   }

   public String getChonloaiba()
   {
     return this.chonloaiba;
   }

   public void setChonloaiba(String chonloaiba)
   {
     this.chonloaiba = chonloaiba;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatHSBAAction

 * JD-Core Version:    0.7.0.1

 */