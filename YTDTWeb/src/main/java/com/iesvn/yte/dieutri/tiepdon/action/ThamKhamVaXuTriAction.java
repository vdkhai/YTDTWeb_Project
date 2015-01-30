 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.ThamKhamUtil;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.rmi.RemoteException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.Timestamp;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.xml.rpc.ServiceException;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B121_Thamkhamvaxutri")
 @Synchronized(timeout=6000000L)
 public class ThamKhamVaXuTriAction
   implements Serializable
 {
   private ThamKham thamkham;
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private String goToPhieuKhamBenhVaoVien;
   private static final long serialVersionUID = 10L;
   private String gioRa;
   private String ngayRa;
   private boolean bChuyenVaoNT;
   private Boolean incls = Boolean.valueOf(true);
   private Boolean inToaThuocBK = Boolean.valueOf(true);
   private Boolean bThuThuatPhauThuat;
   private Boolean bSinh;
   private BenhNhan benhNhan;
   private TiepDon tiepdon;
   private String gioThamKham;
   private String ngayThamKham;
   private String tuoi;
   private String ngaySinh;
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
   private String gioi;
   private String sotheTEorBHYT;
   private Integer maSoBacSi;
   private String maBacSi;
   private String sNgayTaiKham;
   private Integer ngheNghiepMaSo;
   private String ngheNghiepMa;
   private boolean hasChKhoa;
   private boolean hasChBanKham;
   private boolean daThtoankham;
   private boolean notBNbaohiem;
   private boolean notDisplayKTQBV;
   DmBenhIcd phu1_temp = new DmBenhIcd();
   private TiepDon tiepDonForCall;
   private String tenBenhNhanForCall;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   private String chonloaiba;
   private int index = 0;
   String userLogedIn = "";
   private String resultHidden = "";
   private static Logger log = Logger.getLogger(ThamKhamVaXuTriAction.class);
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String goToThamKhamVaXuTri;
   @In(required=false)
   @Out(required=false)
   private String goToPhieuKhamChuyenKhoa;
   @Out(required=false)
   @In(required=false)
   String fromMenu;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToThamKham;
   @In(required=false)
   @Out(required=false)
   private String goToCLSThuThuat;
   @In(required=false)
   @Out(required=false)
   private String initB121_3_Xutrithuoctaibankham;
   @In(required=false)
   @Out(required=false)
   private String loaiToaThuocThamKhamVaXuTri;
   @In(required=false)
   @Out(required=false)
   private String goToLapBANgoaiTru;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungNhan;
   @In(required=false)
   @Out(required=false)
   private String goToChuyenVienNguoiBenhCoTheBHYT;
   @In(required=false)
   @Out(required=false)
   private String goToThamKhamDieuTriNgoaiTru;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungNhanSucKhoe;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChuyenVienSotXuatHuyet;
   @In(required=false)
   @Out(required=false)
   private String bacSiKCB;
   @In(required=false)
   @Out(required=false)
   private String portName;
   @In(required=false)
   @Out(required=false)
   private String goToLapBATuVongTruocKhiVaoVien;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungThuong;
   @DataModel
   private List<DmBenhIcd> benhICDList;
   @DataModel
   private List<DmBenhIcd> benhICDTVList;
   private String soBenhAn;

   public String getGoToGiayChungThuong()
   {
     return this.goToGiayChungThuong;
   }

   public void setGoToGiayChungThuong(String goToGiayChungThuong)
   {
     this.goToGiayChungThuong = goToGiayChungThuong;
   }

   public String getGoToLapBATuvongtruockhivaovien()
   {
     return this.goToLapBATuVongTruocKhiVaoVien;
   }

   public void setGoToLapBATuvongtruockhivaovien(String goToLapBATuVongTruocKhiVaoVien)
   {
     this.goToLapBATuVongTruocKhiVaoVien = goToLapBATuVongTruocKhiVaoVien;
   }

   public String phieukhambenhvaovien()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToPhieuKhamBenhVaoVien = null;



     return "PhieuKhamBenhVaoVien";
   }

   public String getInitB121_3_Xutrithuoctaibankham()
   {
     return this.initB121_3_Xutrithuoctaibankham;
   }

   public void setInitB121_3_Xutrithuoctaibankham(String initB121_3_Xutrithuoctaibankham)
   {
     this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
   }

   public ThamKhamVaXuTriAction()
   {
     this.gioi = "";
   }

   public void comeFromTiepDon()
   {
     if ((this.maBanKhamOut != null) && (this.maBanKhamOut.trim().length() > 0))
     {
       this.goToThamKhamVaXuTri = "gone";
       resetObjectValue();
       resetValue();

       this.thamkham.getThamkhamBankham(true).setDtdmbankhamMa(this.maBanKhamOut);
       this.thamkham.getTiepdonMa(true).setTiepdonMa(this.maTiepDonOut);
     }
   }

   @Begin(join=true)
   public String init(String loaikham, String strPortName)
   {
     resetObjectValue();
     resetValue();
     if (("CCL".equals(loaikham.toUpperCase())) || ("CCN".equals(loaikham.toUpperCase()))) {
       this.thamkham.getThamkhamBankham(true).setDtdmbankhamMa(loaikham.toUpperCase());
     }
     this.portName = strPortName;

     this.loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;

     this.initB121_3_Xutrithuoctaibankham = null;
     this.fromMenu = null;
     this.goToThamKhamVaXuTri = "";
     this.maTiepDonOut = "";

     return "TiepDon_KhamBenh_ThamKhamXuTri";
   }

   @End
   public void end() {}

   public void notFound()
   {
     resetValue();
     resetObjectValue();
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


     DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(this.thamkham.getBenhicd10phu1(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
     if (benh == null) {
       return;
     }
     if (!isContain(this.benhICDList, benh))
     {
       this.benhICDList.add(benh);
       this.thamkham.setBenhicd10phu1(new DmBenhIcd());
     }
   }

   public void themBenhtuvong()
   {
     if (this.benhICDTVList == null) {
       this.benhICDTVList = new ArrayList();
     }
     if (this.benhICDTVList.size() >= 5) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();


     DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMa(this.tiepdon.getTiepdonTuvong(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
     if (benh == null) {
       return;
     }
     if (!isContain(this.benhICDTVList, benh)) {
       this.benhICDTVList.add(benh);
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

   public void resetValue()
   {
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
     this.ngheNghiepMaSo = null;
     this.ngheNghiepMa = "";
     this.sNgayTaiKham = "";
     this.maBacSi = "";
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     Calendar cal = Calendar.getInstance();











     this.gioThamKham = Utils.getGioPhut(cal.getTime());
     this.ngayThamKham = formatter.format(cal.getTime());


     this.bChuyenVaoNT = false;
     this.gioRa = "";
     this.ngayRa = "";
     this.sotheTEorBHYT = "";
     this.resultHidden = "";
     this.soBenhAn = "";
     this.benhICDList = null;
     this.benhICDTVList = null;
     this.hasChKhoa = false;
     this.hasChBanKham = false;
     this.daThtoankham = false;
     this.notBNbaohiem = false;
     this.notDisplayKTQBV = false;

     this.tenBenhNhanForCall = "";
   }

   @Destroy
   public void destroy() {}

   public void XuatReport()
   {
     this.reportTypeTD = "ThamKhamVaXuTri";

     String baocao1 = null;
     try
     {
       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);

       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       String matiepDon = this.thamkham.getTiepdonMa().getTiepdonMa();
       params.put("MATIEPDON", matiepDon);
       params.put("HOTENBN", this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());

       String diachistr = "";
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         params.put("BHYT_CO", "X");
         if (((this.tiepdon.getTiepdonTuyen() != null) && (this.tiepdon.getTiepdonTuyen().toString().equals("1"))) || ((this.tiepdon.getTiepdonCoGiayGioiThieu() != null) && (this.tiepdon.getTiepdonCoGiayGioiThieu().booleanValue()))) {
           params.put("DUNGTUYEN", "X");
         } else {
           params.put("TRAITUYEN", "X");
         }
       }
       else
       {
         params.put("BHYT_KO", "X");
       }
       if (this.thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(this.thamkham.getTiepdonMa().getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (this.thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(this.thamkham.getTiepdonMa().getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((this.thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!this.thamkham.getTiepdonMa().getTiepdonSothebh().equals("")) && (this.thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa() != null) && (!this.thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")) && (this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa() != null))
       {
         params.put("MATHEBHYT", this.thamkham.getTiepdonMa().getTiepdonSothebh());
         params.put("MABENHVIEN", this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
       }
       else
       {
         params.put("MABENHVIEN", "");
         params.put("MATHEBHYT", "");
       }
       if (this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen());
       }
       if (this.thamkham.getTiepdonMa(true).getTinhbhytMa(true).getDmtinhTen() != null) {
         params.put("NOICAPTHE", this.thamkham.getTiepdonMa(true).getTinhbhytMa(true).getDmtinhTen());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonDonvigoi(true).getDmbenhvienMa() != null) {
         params.put("NOIGIOITHIEU", this.thamkham.getTiepdonMa(true).getTiepdonDonvigoi(true).getDmbenhvienTen());
       }
       Calendar cal = Calendar.getInstance();
       cal.setTime(this.thamkham.getThamkhamNgaygio());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));

       params.put("LYDOVAOVIEN", this.thamkham.getTiepdonMa(true).getTiepdonLydovaov());
       HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = new HsThtoank();
       hsttk = thanhToanDel.findBytiepdonMaLast(matiepDon);
       if ((hsttk == null) || (hsttk.getHsthtoankDatt() == null) || ((hsttk.getHsthtoankDatt() != null) && (!hsttk.getHsthtoankDatt().booleanValue())))
       {
         hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.thamkham.getTiepdonMa());

         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(this.thamkham, hsttk, Boolean.valueOf(false), null, null);
         Utils.setInforForHsThToan(hsttk);
       }
       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
       params.put("NGUONKHAC", hsttk.getHsthtoankNguonkhactra());
       if (hsttk.getHsthtoankNgaygiott() != null)
       {
         params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
         if (this.thamkham.getThamkhamNgaygio() != null) {
           params.put("SONGAYDT", Long.valueOf(Utils.getDaysBetween(this.thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott())));
         }
       }
       if ((this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa() != null) &&
         (this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC"))) {
         params.put("CAPCUU", "X");
       }
       params.put("PHIEUSO", hsttk.getHsthtoankMa());

       params.put("TYLEBH", hsttk.getHsthtoankTylebh());
       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       if ("MP".equals(this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())) {
         tyleBNtra = "0";
       }
       params.put("PHANTRAMBNTRA", tyleBNtra);

       params.put("BIENLAISO", "");


       String namsinh = "";
       if (this.tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh() != null) {
         namsinh = sdf.format(this.tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
       } else {
         namsinh = this.tiepdon.getBenhnhanMa(true).getBenhnhanNamsinh();
       }
       params.put("namsinh", namsinh);

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           params.put("MABENHICD", maChanDoan);
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((this.thamkham.getThamkhamGhichu() != null) && (!this.thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + this.thamkham.getThamkhamGhichu();
       }
       if ((this.thamkham.getBenhicd10phu1() != null) && (this.thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       else if ((this.phu1_temp != null) && (this.phu1_temp.getDmbenhicdMaso() != null))
       {
         maChanDoan = this.phu1_temp.getDmbenhicdMa();
         tenChanDoan = this.phu1_temp.getDmbenhicdTen();
         chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
       }
       if ((this.thamkham.getBenhicd10phu2() != null) && (this.thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       params.put("CHANDOAN", chanDoan);

       params.put("PHONGKHAM", this.thamkham.getThamkhamBankham().getDtdmbankhamTen());

       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(matiepDon);


       StringBuffer bufStr = new StringBuffer();
       Double tongTienDV = Double.valueOf(0.0D);
       if ((listTk != null) && (listTk.size() > 0)) {
         for (ThamKham each : listTk) {
           if (bufStr.length() > 0) {
             bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
           } else {
             bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
           }
         }
       }
       List<ClsKham> listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(this.thamkham.getTiepdonMa().getTiepdonMa());
       if ((listClsTmp != null) && (listClsTmp.size() > 0)) {
         for (ClsKham eachCls : listClsTmp) {
           if (((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true)) || ((eachCls.getClskhamNdm() != null) && (eachCls.getClskhamNdm().booleanValue() == true) && (eachCls.getClskhamPhandv() != null))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachCls.getClskhamPhandv().doubleValue());
           }
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.thamkham.getTiepdonMa().getTiepdonMa(), "1");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamDongia().doubleValue());
           }
         }
       }
       listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.thamkham.getTiepdonMa().getTiepdonMa(), "3");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamDongia().doubleValue());
           }
         }
       }
       params.put("KHOA", bufStr.toString());
       String soTheTe_KhaiSinh_ChungSinh = this.thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
         }
       }
       else if ((this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.thamkham.getTiepdonMa(true).getTiepdonChungsinh();
         }
       }
       else if ((this.thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.thamkham.getTiepdonMa(true).getTiepdonChungsinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       String soTheNgheo = this.thamkham.getTiepdonMa(true).getTiepdonThengheo();
       if ((soTheNgheo != null) && (!soTheNgheo.equals(""))) {
         params.put("SOTHENGHEO", soTheNgheo);
       } else {
         params.put("SOTHENGHEO", null);
       }
       params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
       params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi().doubleValue()));
       params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankThtoan().doubleValue()));
       params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt().doubleValue()));
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (hsttk.getHsthtoankThtoan().doubleValue() >= 0.0D) {
         params.put("SNGUOIBENHTRA", "0");
       } else {
         params.put("SNGUOIBENHTRA", "-1");
       }
       DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(this.thamkham.getTiepdonMa(true).getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
       if (gioi != null) {
         params.put("GIOI", gioi.getDmgtTen());
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);


       String pathTemplate = "";
       String sub0Template = "";
       String sub1Template = "";
       JasperReport jasperReport;
       if (tongTienDV.doubleValue() > 0.0D)
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
         sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
         sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
         String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
         String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";
         if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC"))
         {
           sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
           sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
         }
         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
         JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
         JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
         params.put("sub2", sub2Report);
         params.put("sub3", sub3Report);
       }
       else
       {
         if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC"))
         {
           params.put("GIUONG", this.thamkham.getTiepdonMa(true).getTiepdonGiuong());
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu.jrxml";
           sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
           sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
           sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
           sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
         }
         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
       }
       Class.forName("com.mysql.jdbc.Driver");

       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "ThamKhamVaXuTri");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");


       setIndex(getIndex() + 1);
       if (conn != null)
       {
         conn.close();
         conn = null;
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public String phieukhamchuyenkhoa()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToPhieuKhamChuyenKhoa = null;



     return "PhieuKhamChuyenKhoa";
   }

   public String thuchienToaThuocAction()
   {
     if ((this.thamkham.getBenhicd10() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMaso() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMa() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.PHAI_NHAP_CHAN_DOAN, new Object[0]);
       return "";
     }
     if ("TP".equals(this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa()))
     {
       this.reportTypeTD = "ThamKhamVaXuTri";
       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();



       List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.thamkham.getTiepdonMa().getTiepdonMa(), this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), "2");

       ThamKhamUtil tkUtil = new ThamKhamUtil();
       tkUtil.reportTypeTD = this.reportTypeTD;
       String fileName = tkUtil.XuatReportBNVe(log, this.thamkham, null, null, ctThuocPhongKhams, this.index);
       if (fileName.equals(""))
       {
         FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR, new Object[0]);
         return "";
       }
       this.jasperPrintTD = tkUtil.jasperPrintTD;
       this.reportPathTD = tkUtil.reportPathTD;
       this.reportTypeTD = tkUtil.reportTypeTD;

       this.index += 1;
     }
     else if (("BH".equals(this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())) || ("MP".equals(this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())))
     {
       this.reportTypeTD = "ThamKhamVaXuTri";
       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();



       List<ThuocPhongKham> ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.thamkham.getTiepdonMa().getTiepdonMa(), this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), "3");

       ThamKhamUtil tkUtil = new ThamKhamUtil();
       tkUtil.reportTypeTD = this.reportTypeTD;






       String fileName = tkUtil.XuatReport_don_thuoc_ketoa_quay(log, this.thamkham, null, null, ctThuocPhongKhams, this.inToaThuocBK.booleanValue(), this.incls.booleanValue(), this.index);
       if (fileName.equals(""))
       {
         FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR, new Object[0]);
         return "";
       }
       this.jasperPrintTD = tkUtil.jasperPrintTD;
       this.reportPathTD = tkUtil.reportPathTD;
       this.reportTypeTD = tkUtil.reportTypeTD;
       this.index += 1;
     }
     else
     {
       return "";
     }
     return "B160_Chonmenuxuattaptin";
   }

   public void nhaplai()
   {
     resetObjectValue();
     resetValue();
   }

   private void resetObjectValue()
   {
     this.benhNhan = new BenhNhan();

     this.tiepdon = new TiepDon();

     this.tiepdon.setBenhnhanMa(this.benhNhan);

     this.thamkham = new ThamKham();
     this.thamkham.setBenhicd10phu1(new DmBenhIcd());
     this.phu1_temp = new DmBenhIcd();
     this.thamkham.setTiepdonMa(this.tiepdon);




     this.tiepDonForCall = new TiepDon();
   }

   public String clsthuthat()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
     this.goToThamKham = null;


     this.goToCLSThuThuat = null;

















     this.fromMenu = null;
     return "clsthuthat";
   }

   public String thamKham()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);
       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);
       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
     this.goToThamKham = null;



     return "thamkham";
   }

   public String lapbenhan()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToLapBANgoaiTru = null;



     return "lapbenhanngoaitru";
   }

   public String lapbatuvongtruockhivaovien()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToLapBATuVongTruocKhiVaoVien = null;



     return "lapbatuvongtruockhivaovien";
   }

   public String giaychungthuong()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToGiayChungNhan = null;



     return "giaychungthuong";
   }

   public String giaychungnhan()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
     if (this.thamkham.getThamkhamBacsi() != null) {
       this.bacSiKCB = this.thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
     }
     this.goToGiayChungNhan = null;



     return "giaychungnhan";
   }

   public String giaychuyenvien()
     throws ServiceException, RemoteException
   {
     if (this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP"))
     {
       FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI_CNBHYT, new Object[0]);
       return "";
     }
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getBenhicd10() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMaso() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMa() == null) || (this.thamkham.getBenhicd10(true).getDmbenhicdMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.PHAI_NHAP_CHAN_DOAN, new Object[0]);
       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToChuyenVienNguoiBenhCoTheBHYT = null;



     return "ChuyenVienNguoiBenhCoTheBHYT";
   }

   public String thamkhamdieutringoaitru()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToThamKhamDieuTriNgoaiTru = null;



     return "ThamKhamDieuTriNgoaiTru";
   }

   public String giaychungnhansuckhoe()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToGiayChungNhanSucKhoe = null;



     return "GiayChungNhanSucKhoe";
   }

   public String giaychuyenviensotxuathuyet()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.goToGiayChuyenVienSotXuatHuyet = null;



     return "GiayChuyenVienSotXuatHuyet";
   }

   public void lapBAluuBNCapcuu()
   {
     if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().startsWith("CC"))
     {
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
       DmKhoa khoaCapcuu = (DmKhoa)dtDele.findByMa("CCL", "DmKhoa", "dmkhoaMa");
       if (khoaCapcuu != null)
       {
         RemoveUtil.removeAllNullFromTiepDon(this.tiepdon);
         RemoveUtil.removeAllNullFromThamKham(this.thamkham);
         ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
         this.soBenhAn = thamkhamDel.vaoNoiTru_Temp(this.thamkham, this.tiepdon, "BA_LUU", khoaCapcuu);
         if (this.soBenhAn != "") {
           FacesMessages.instance().add(IConstantsRes.BA_LUU_THANH_CONG, new Object[] { this.soBenhAn });
         } else {
           FacesMessages.instance().add(IConstantsRes.BA_LUU_THAT_BAI, new Object[0]);
         }
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.BA_LUU_THAT_BAI, new Object[0]);
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU, new Object[0]);
     }
   }

   public String keToaVe()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_BENH_NHAN_TU_MUA;


     this.fromMenu = null;

     return "ketoave";
   }

   public String xuTriThuocTaiBanKham()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.loaiToaThuocThamKhamVaXuTri = Utils.XU_TRI_THUOC_TAI_BAN_KHAM;

     this.initB121_3_Xutrithuoctaibankham = null;
     this.fromMenu = null;

     return "xutrithuoctaibankham";
   }

   public String keToaQuayBenhVien()
     throws ServiceException, RemoteException
   {
     if ((this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     if ((this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.BANKHAM_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();

     this.loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;

     this.initB121_3_Xutrithuoctaibankham = null;
     this.fromMenu = null;

     return "xutrithuoctaibankham";
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.benhNhan.getBenhnhanNamsinh() != null) && (!this.benhNhan.getBenhnhanNamsinh().equals(""))) {
       this.ngaySinh = this.benhNhan.getBenhnhanNamsinh();
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
     this.sotheTEorBHYT = "";
     if ((this.tiepdon.getTiepdonSothebh() != null) && (!this.tiepdon.getTiepdonSothebh().equals(""))) {
       this.sotheTEorBHYT = this.tiepdon.getTiepdonSothebh();
     } else if ((this.tiepdon.getTiepdonSothete() != null) && (!this.tiepdon.getTiepdonSothete().equals(""))) {
       this.sotheTEorBHYT = this.tiepdon.getTiepdonSothete();
     }
     Date ngayGio = this.thamkham.getThamkhamNgaygiocn();
     if (ngayGio != null)
     {
       this.ngayRa = formatter.format(Long.valueOf(ngayGio.getTime()));
       this.gioRa = Utils.getGioPhut(ngayGio);
     }
     Date ngayGioThamKham = this.thamkham.getThamkhamNgaygio();
     if (ngayGioThamKham != null)
     {
       this.ngayThamKham = formatter.format(Long.valueOf(ngayGioThamKham.getTime()));
       this.gioThamKham = Utils.getGioPhut(ngayGioThamKham);
     }
     if (this.thamkham.getThamkhamBacsi() != null)
     {
       this.maSoBacSi = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();
       this.maBacSi = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
     }
     else
     {
       this.maSoBacSi = null;
       this.maBacSi = "";
     }
     if ((this.maSoBacSi != null) && (this.maBacSi != null) && (!this.maBacSi.equals("")) && (this.tiepdon.getTiepdonBschuyen() == null))
     {
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
       DtDmNhanVien bacsi = (DtDmNhanVien)dtDele.findByMa(this.maBacSi, "DtDmNhanVien", "dtdmnhanvienMa");
       this.tiepdon.setTiepdonBschuyen(bacsi);
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.tiepdon.getTiepdonMa() == null) || (this.tiepdon.getTiepdonMa().equals("")) || (this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepdon.getTiepdonMa(), new Object[0]);
         notFound();
         return;
       }
       ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
       ThamKham thamkham_tmp = thamkhamDelegate.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.tiepdon.getTiepdonMa());
       if (thamkham_tmp == null)
       {
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepdon.getTiepdonMa(), new Object[0]);
         notFound();
         return;
       }
       this.thamkham = thamkham_tmp;
       this.tiepdon = this.thamkham.getTiepdonMa();


       this.hasChKhoa = (this.tiepdon.getTiepdonChkhoa() != null);
       this.hasChBanKham = (this.thamkham.getThamkhamChbankham() != null);


       this.notBNbaohiem = (!this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"));

       this.daThtoankham = false;
       if ((this.tiepdon.getTiepdonChkhoa() != null) && (!this.tiepdon.getTiepdonChkhoa().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
         this.daThtoankham = true;
       }
       if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
         HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(this.tiepdon.getTiepdonMa());
         if (hsttk != null)
         {
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
           this.daThtoankham = true;
         }
         SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
         String dateString = dateformat.format(new Date());
         Date currentDate = dateformat.parse(dateString);

         Date giaTriTheBH_From = this.tiepdon.getTiepdonGiatri1();
         Date giaTriTheBH_To = this.tiepdon.getTiepdonGiatri4();
         if ((giaTriTheBH_To.before(currentDate)) || (giaTriTheBH_From.after(currentDate))) {
           this.notDisplayKTQBV = true;
         } else {
           this.notDisplayKTQBV = false;
         }
       }
       if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("MP"))
       {
         this.notBNbaohiem = false;
         this.notDisplayKTQBV = false;
       }
       this.benhNhan = this.tiepdon.getBenhnhanMa();
       setOtherValue();




       this.ngheNghiepMa = this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa();
       this.ngheNghiepMaSo = this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepMaso();
       if (this.benhICDList != null) {
         if (this.benhICDList.size() > 0) {
           this.benhICDList.clear();
         }
       }
       if (this.thamkham.getBenhicd10phu1() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.thamkham.getBenhicd10phu1());
       }
       if (this.thamkham.getBenhicd10phu2() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.thamkham.getBenhicd10phu2());
       }
       if (this.thamkham.getBenhicd10phu3() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.thamkham.getBenhicd10phu3());
       }
       if (this.thamkham.getBenhicd10phu4() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.thamkham.getBenhicd10phu4());
       }
       if (this.thamkham.getBenhicd10phu5() != null)
       {
         if (this.benhICDList == null) {
           this.benhICDList = new ArrayList();
         }
         this.benhICDList.add(this.thamkham.getBenhicd10phu5());
       }
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       if (this.tiepdon.getTiepdonTuvongphu1() != null)
       {
         if (this.benhICDTVList == null) {
           this.benhICDTVList = new ArrayList();
         }
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.tiepdon.getTiepdonTuvongphu1(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           this.benhICDTVList.add(benh);
           this.tiepdon.setTiepdonTuvongphu1(null);
         }
       }
       if (this.tiepdon.getTiepdonTuvongphu2() != null)
       {
         if (this.benhICDTVList == null) {
           this.benhICDTVList = new ArrayList();
         }
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.tiepdon.getTiepdonTuvongphu2(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           this.benhICDTVList.add(benh);
           this.tiepdon.setTiepdonTuvongphu2(null);
         }
       }
       if (this.tiepdon.getTiepdonTuvongphu3() != null)
       {
         if (this.benhICDTVList == null) {
           this.benhICDTVList = new ArrayList();
         }
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.tiepdon.getTiepdonTuvongphu3(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           this.benhICDTVList.add(benh);
           this.tiepdon.setTiepdonTuvongphu3(null);
         }
       }
       if (this.tiepdon.getTiepdonTuvongphu4() != null)
       {
         if (this.benhICDTVList == null) {
           this.benhICDTVList = new ArrayList();
         }
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.tiepdon.getTiepdonTuvongphu4(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           this.benhICDTVList.add(benh);
           this.tiepdon.setTiepdonTuvongphu4(null);
         }
       }
       if (this.tiepdon.getTiepdonTuvongphu5() != null)
       {
         if (this.benhICDTVList == null) {
           this.benhICDTVList = new ArrayList();
         }
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.tiepdon.getTiepdonTuvongphu5(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           this.benhICDTVList.add(benh);
           this.tiepdon.setTiepdonTuvongphu5(null);
         }
       }
       SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
       if (this.thamkham.getThamkhamNgaytaikham() != null) {
         this.sNgayTaiKham = formatter.format(this.thamkham.getThamkhamNgaytaikham());
       }
       if ((this.tiepdon != null) && (this.tiepdon.getTiepdonMa() != null) && (!this.tiepdon.getTiepdonMa().equals("")))
       {
         HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
         Hsba hsba = hsbaDelegate.findByTiepDonMa(this.tiepdon.getTiepdonMa());
         if (hsba != null) {
           this.soBenhAn = hsba.getHsbaSovaovien();
         } else {
           this.soBenhAn = "";
         }
       }
       if (this.thamkham.getBenhicd10phu1() != null) {
         this.phu1_temp = this.thamkham.getBenhicd10phu1();
       }
       this.thamkham.setBenhicd10phu1(new DmBenhIcd());
     }
     catch (Exception e)
     {
       log.info("error on function displayInfor" + e);
     }
   }

   public void deleteCurrentRow(int vitri)
   {
     if ((this.benhICDList == null) || (this.benhICDList.size() == 0)) {
       return;
     }
     this.benhICDList.remove(vitri);
   }

   public void deleteCurrentRowTV(int vitri)
   {
     if ((this.benhICDTVList == null) || (this.benhICDTVList.size() == 0)) {
       return;
     }
     this.benhICDTVList.remove(vitri);
   }

   public void ghiNhan()
     throws Exception
   {
     try
     {
       DtDmBanKham dtdmbankham = new DtDmBanKham();
       dtdmbankham.setDtdmbankhamMaso(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMaso());
       dtdmbankham.setDtdmbankhamMa(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa());
       this.thamkham.setThamkhamBankham(dtdmbankham);


       ThamKham thamkhamTmp = ThamKhamDelegate.getInstance().findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham().getDtdmbankhamMa(), this.tiepdon.getTiepdonMa());
       if (thamkhamTmp != null) {
         if (thamkhamTmp.getThamkhamMa().intValue() != this.thamkham.getThamkhamMa().intValue())
         {
           FacesMessages.instance().add(IConstantsRes.THAM_KHAM_TRUNG_BAN_KHAM, new Object[] { thamkhamTmp.getThamkhamBankham().getDtdmbankhamTen() });
           return;
         }
       }
       if ((this.maBacSi != null) && (!this.maBacSi.equals("")))
       {
         this.thamkham.getThamkhamBacsi(true).setDtdmnhanvienMaso(this.maSoBacSi);
         this.thamkham.getThamkhamBacsi(true).setDtdmnhanvienMa(this.maBacSi);
       }
       RemoveUtil.removeAllNullFromBN(this.benhNhan);

       RemoveUtil.removeAllNullFromTiepDon(this.tiepdon);
       RemoveUtil.removeAllNullFromThamKham(this.thamkham);
       if ((this.ngayThamKham != null) && (!this.ngayThamKham.equals(""))) {
         this.thamkham.setThamkhamNgaygio(Utils.getDBDate(this.gioThamKham, this.ngayThamKham).getTime());
       }
       if ((this.ngayRa != null) && (!this.ngayRa.equals(""))) {}
       this.thamkham.setThamkhamNgaygiocn(new Date());
       SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

       Date ngayGio = this.thamkham.getThamkhamNgaygiocn();
       if (ngayGio != null)
       {
         this.ngayRa = formatter.format(Long.valueOf(ngayGio.getTime()));
         this.gioRa = Utils.getGioPhut(ngayGio);
       }
       DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
       DtDmNhanVien nhanVienCN = nvDelegate.findByNd(this.identity.getUsername());
       this.thamkham.setThamkhamNhanviencn(nhanVienCN);




       HsThtoank hsttk = new HsThtoank();
       boolean chuyenVaoNT = false;
       if ((this.thamkham.getThamkhamChuyenSoLieuVaoNoiTru() != null) && (this.thamkham.getThamkhamChuyenSoLieuVaoNoiTru().booleanValue() == true))
       {
         chuyenVaoNT = true;
         this.bChuyenVaoNT = true;

         hsttk.setTiepdonMa(this.thamkham.getTiepdonMa());


         hsttk.setHsthtoankThtoan(new Double(0.0D));

         this.thamkham.setThamkhamStatus("NT");
       }
       else
       {
         this.thamkham.setThamkhamStatus("");
       }
       this.thamkham.setBenhicd10phu1(null);
       this.thamkham.setBenhicd10phu2(null);
       this.thamkham.setBenhicd10phu3(null);
       this.thamkham.setBenhicd10phu4(null);
       this.thamkham.setBenhicd10phu5(null);
       if ((this.sNgayTaiKham != null) && (!this.sNgayTaiKham.equals(""))) {
         this.thamkham.setThamkhamNgaytaikham(Utils.getDBDate("00:00", this.sNgayTaiKham).getTime());
       }
       if ((this.benhICDList != null) && (this.benhICDList.size() > 0)) {
         for (DmBenhIcd benh : this.benhICDList) {
           if (this.thamkham.getBenhicd10phu1() == null) {
             this.thamkham.setBenhicd10phu1(benh);
           } else if (benh.getDmbenhicdMaso().intValue() != this.thamkham.getBenhicd10phu1().getDmbenhicdMaso().intValue()) {
             if (this.thamkham.getBenhicd10phu2() == null) {
               this.thamkham.setBenhicd10phu2(benh);
             } else if (this.thamkham.getBenhicd10phu3() == null) {
               this.thamkham.setBenhicd10phu3(benh);
             } else if (this.thamkham.getBenhicd10phu4() == null) {
               this.thamkham.setBenhicd10phu4(benh);
             } else if (this.thamkham.getBenhicd10phu5() == null) {
               this.thamkham.setBenhicd10phu5(benh);
             }
           }
         }
       }
       this.tiepdon.setTiepdonTuvongphu1(null);
       this.tiepdon.setTiepdonTuvongphu2(null);
       this.tiepdon.setTiepdonTuvongphu3(null);
       this.tiepdon.setTiepdonTuvongphu4(null);
       this.tiepdon.setTiepdonTuvongphu5(null);
       if ((this.benhICDTVList != null) && (this.benhICDTVList.size() > 0)) {
         for (DmBenhIcd benh : this.benhICDTVList) {
           if (this.tiepdon.getTiepdonTuvong() == null) {
             this.tiepdon.setTiepdonTuvong(benh);
           } else if ((this.tiepdon.getTiepdonTuvong() == null) || (this.tiepdon.getTiepdonTuvong().getDmbenhicdMaso().intValue() != benh.getDmbenhicdMaso().intValue())) {
             if (this.tiepdon.getTiepdonTuvongphu1() == null) {
               this.tiepdon.setTiepdonTuvongphu1(benh.getDmbenhicdMaso());
             } else if (this.tiepdon.getTiepdonTuvongphu2() == null) {
               this.tiepdon.setTiepdonTuvongphu2(benh.getDmbenhicdMaso());
             } else if (this.tiepdon.getTiepdonTuvongphu3() == null) {
               this.tiepdon.setTiepdonTuvongphu3(benh.getDmbenhicdMaso());
             } else if (this.tiepdon.getTiepdonTuvongphu4() == null) {
               this.tiepdon.setTiepdonTuvongphu4(benh.getDmbenhicdMaso());
             } else if (this.tiepdon.getTiepdonTuvongphu5() == null) {
               this.tiepdon.setTiepdonTuvongphu5(benh.getDmbenhicdMaso());
             }
           }
         }
       }
       ThamKhamDelegate thamKhamDelegate = ThamKhamDelegate.getInstance();
       if (this.ngheNghiepMaSo != null) {
         this.benhNhan.getBenhnhanNghe(true).setDmnghenghiepMaso(this.ngheNghiepMaSo);
       }
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
       if ((this.tiepdon.getTiepdonChkhoa() != null) && (this.tiepdon.getTiepdonChkhoa(true).getDmkhoaMaso() != null))
       {
         FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
         this.daThtoankham = true;
         this.bChuyenVaoNT = true;

         DmKhoa dmkhoa = (DmKhoa)dtDele.findByMaSo(this.tiepdon.getTiepdonChkhoa().getDmkhoaMaso(), "DmKhoa", "dmkhoaMaso");
         this.tiepdon.setTiepdonChkhoa(dmkhoa);
       }
       if ((this.tiepdon.getTiepdonChvien() != null) && (this.tiepdon.getTiepdonChvien().getDmbenhvienMaso() != null))
       {
         DmBenhVien bv = (DmBenhVien)dtDele.findByMaSo(this.tiepdon.getTiepdonChvien().getDmbenhvienMaso(), "DmBenhVien", "dmbenhvienMaso");
         this.tiepdon.setTiepdonChvien(bv);
       }
       if ((this.thamkham.getThamkhamDieutri() != null) &&
         (this.thamkham.getThamkhamDieutri().getDmdieutriMaso() != null)) {
         this.tiepdon.setDieutriMa(this.thamkham.getThamkhamDieutri());
       }
       if ((this.thamkham.getBenhicd10() != null) &&
         (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null) && (
         (this.tiepdon.getTiepdonMachdoanbd() == null) || (this.tiepdon.getTiepdonMachdoanbd().getDmbenhicdMaso() == null))) {
         this.tiepdon.setTiepdonMachdoanbd(this.thamkham.getBenhicd10());
       }
       String soVaoVien = thamKhamDelegate.thamKhamVaXuTri(hsttk, this.thamkham, this.tiepdon, this.benhNhan, Boolean.valueOf(chuyenVaoNT), IConstantsRes.CHUYEN_VAO_NOI_TRU_OPTION);


       this.soBenhAn = soVaoVien;
       if (chuyenVaoNT)
       {
         hsttk.setTiepdonMa(this.thamkham.getTiepdonMa());
         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(this.thamkham, hsttk, Boolean.valueOf(false), null, null);
       }
       if ((soVaoVien != null) && (!soVaoVien.equals(""))) {
         FacesMessages.instance().add(IConstantsRes.GHI_NHAN_THANH_CONG_VOI_SO_VAO_VIEN, new Object[] { soVaoVien });
       } else {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       }
       if (this.thamkham.getBenhicd10phu1() == null) {
         this.thamkham.setBenhicd10phu1(new DmBenhIcd());
       }
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info(e);
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
     }
   }

   public String msg = "";

   public String getMsg()
   {
     return this.msg;
   }

   public void setMsg(String msg)
   {
     this.msg = msg;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public String getNgayRa()
   {
     return this.ngayRa;
   }

   public void setNgayRa(String ngayRa)
   {
     this.ngayRa = ngayRa;
   }

   public Boolean getBThuThuatPhauThuat()
   {
     return this.bThuThuatPhauThuat;
   }

   public void setBThuThuatPhauThuat(Boolean thuThuatPhauThuat)
   {
     this.bThuThuatPhauThuat = thuThuatPhauThuat;
   }

   public Boolean getBSinh()
   {
     return this.bSinh;
   }

   public void setBSinh(Boolean sinh)
   {
     this.bSinh = sinh;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public TiepDon getTiepdon()
   {
     return this.tiepdon;
   }

   public void setTiepdon(TiepDon tiepdon)
   {
     this.tiepdon = tiepdon;
   }

   public String getNgayThamKham()
   {
     return this.ngayThamKham;
   }

   public void setNgayThamKham(String ngayThamKham)
   {
     this.ngayThamKham = ngayThamKham;
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

   public String getNgayVaoVien()
   {
     return this.ngayVaoVien;
   }

   public void setNgayVaoVien(String ngayVaoVien)
   {
     this.ngayVaoVien = ngayVaoVien;
   }

   public String getNgayVaoKhoa()
   {
     return this.ngayVaoKhoa;
   }

   public void setNgayVaoKhoa(String ngayVaoKhoa)
   {
     this.ngayVaoKhoa = ngayVaoKhoa;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public String getNgayTuVong()
   {
     return this.ngayTuVong;
   }

   public void setNgayTuVong(String ngayTuVong)
   {
     this.ngayTuVong = ngayTuVong;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getUserLogedIn()
   {
     return this.userLogedIn;
   }

   public void setUserLogedIn(String userLogedIn)
   {
     this.userLogedIn = userLogedIn;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public Logger getLog()
   {
     return log;
   }

   public void setLog(Logger log)
   {
     log = log;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getSotheTEorBHYT()
   {
     return this.sotheTEorBHYT;
   }

   public void setSotheTEorBHYT(String sotheTEorBHYT)
   {
     this.sotheTEorBHYT = sotheTEorBHYT;
   }

   public String getNgayRaKhoa()
   {
     return this.ngayRaKhoa;
   }

   public void setNgayRaKhoa(String ngayRaKhoa)
   {
     this.ngayRaKhoa = ngayRaKhoa;
   }

   public String getSoBenhAn()
   {
     return this.soBenhAn;
   }

   public void setSoBenhAn(String soBenhAn)
   {
     this.soBenhAn = soBenhAn;
   }

   public String getGioRa()
   {
     return this.gioRa;
   }

   public void setGioRa(String gioRa)
   {
     this.gioRa = gioRa;
   }

   public String getGioThamKham()
   {
     return this.gioThamKham;
   }

   public void setGioThamKham(String gioThamKham)
   {
     this.gioThamKham = gioThamKham;
   }

   public String getGioVaoVien()
   {
     return this.gioVaoVien;
   }

   public void setGioVaoVien(String gioVaoVien)
   {
     this.gioVaoVien = gioVaoVien;
   }

   public String getGioVaoKhoa()
   {
     return this.gioVaoKhoa;
   }

   public void setGioVaoKhoa(String gioVaoKhoa)
   {
     this.gioVaoKhoa = gioVaoKhoa;
   }

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   public String getGioRaKhoa()
   {
     return this.gioRaKhoa;
   }

   public void setGioRaKhoa(String gioRaKhoa)
   {
     this.gioRaKhoa = gioRaKhoa;
   }

   public String getGioTuVong()
   {
     return this.gioTuVong;
   }

   public void setGioTuVong(String gioTuVong)
   {
     this.gioTuVong = gioTuVong;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }

   public String getGoToThamKham()
   {
     return this.goToThamKham;
   }

   public void setGoToThamKham(String goToThamKham)
   {
     this.goToThamKham = goToThamKham;
   }

   public String getGoToCLSThuThuat()
   {
     return this.goToCLSThuThuat;
   }

   public void setGoToCLSThuThuat(String goToCLSThuThuat)
   {
     this.goToCLSThuThuat = goToCLSThuThuat;
   }

   public String getLoaiToaThuocThamKhamVaXuTri()
   {
     return this.loaiToaThuocThamKhamVaXuTri;
   }

   public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri)
   {
     this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
   }

   public Integer getMaSoBacSi()
   {
     return this.maSoBacSi;
   }

   public void setMaSoBacSi(Integer maSoBacSi)
   {
     this.maSoBacSi = maSoBacSi;
   }

   public String getMaBacSi()
   {
     return this.maBacSi;
   }

   public void setMaBacSi(String maBacSi)
   {
     this.maBacSi = maBacSi;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getChonloaiba()
   {
     return this.chonloaiba;
   }

   public void setChonloaiba(String chonloaiba)
   {
     this.chonloaiba = chonloaiba;
   }

   public String getGoToLapBANgoaiTru()
   {
     return this.goToLapBANgoaiTru;
   }

   public void setGoToLapBANgoaiTru(String goToLapBANgoaiTru)
   {
     this.goToLapBANgoaiTru = goToLapBANgoaiTru;
   }

   public String getGoToGiayChungNhan()
   {
     return this.goToGiayChungNhan;
   }

   public void setGoToGiayChungNhan(String goToGiayChungNhan)
   {
     this.goToGiayChungNhan = goToGiayChungNhan;
   }

   public String getGoToChuyenVienNguoiBenhCoTheBHYT()
   {
     return this.goToChuyenVienNguoiBenhCoTheBHYT;
   }

   public void setGoToChuyenVienNguoiBenhCoTheBHYT(String goToChuyenVienNguoiBenhCoTheBHYT)
   {
     this.goToChuyenVienNguoiBenhCoTheBHYT = goToChuyenVienNguoiBenhCoTheBHYT;
   }

   public String getGoToThamKhamDieuTriNgoaiTru()
   {
     return this.goToThamKhamDieuTriNgoaiTru;
   }

   public void setGoToThamKhamDieuTriNgoaiTru(String goToThamKhamDieuTriNgoaiTru)
   {
     this.goToThamKhamDieuTriNgoaiTru = goToThamKhamDieuTriNgoaiTru;
   }

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToPhieuKhamChuyenKhoa()
   {
     return this.goToPhieuKhamChuyenKhoa;
   }

   public void setGoToPhieuKhamChuyenKhoa(String goToPhieuKhamChuyenKhoa)
   {
     this.goToPhieuKhamChuyenKhoa = goToPhieuKhamChuyenKhoa;
   }

   public String getGoToGiayChungNhanSucKhoe()
   {
     return this.goToGiayChungNhanSucKhoe;
   }

   public void setGoToGiayChungNhanSucKhoe(String goToGiayChungNhanSucKhoe)
   {
     this.goToGiayChungNhanSucKhoe = goToGiayChungNhanSucKhoe;
   }

   public String getGoToPhieuKhamBenhVaoVien()
   {
     return this.goToPhieuKhamBenhVaoVien;
   }

   public void setGoToPhieuKhamBenhVaoVien(String goToPhieuKhamBenhVaoVien)
   {
     this.goToPhieuKhamBenhVaoVien = goToPhieuKhamBenhVaoVien;
   }

   public String getGoToGiayChuyenVienSotXuatHuyet()
   {
     return this.goToGiayChuyenVienSotXuatHuyet;
   }

   public void setGoToGiayChuyenVienSotXuatHuyet(String goToGiayChuyenVienSotXuatHuyet)
   {
     this.goToGiayChuyenVienSotXuatHuyet = goToGiayChuyenVienSotXuatHuyet;
   }

   public String getNgayTaiKham()
   {
     return this.sNgayTaiKham;
   }

   public void setNgayTaiKham(String dateNgayTaiKham)
   {
     this.sNgayTaiKham = dateNgayTaiKham;
   }

   public Integer getNgheNghiepMaSo()
   {
     return this.ngheNghiepMaSo;
   }

   public void setNgheNghiepMaSo(Integer ngheNghiepMaSo)
   {
     this.ngheNghiepMaSo = ngheNghiepMaSo;
   }

   public String getNgheNghiepMa()
   {
     return this.ngheNghiepMa;
   }

   public void setNgheNghiepMa(String ngheNghiepMa)
   {
     this.ngheNghiepMa = ngheNghiepMa;
   }

   public Boolean getIncls()
   {
     return this.incls;
   }

   public void setIncls(Boolean incls)
   {
     this.incls = incls;
   }

   public Boolean getInToaThuocBK()
   {
     return this.inToaThuocBK;
   }

   public void setInToaThuocBK(Boolean inToaThuocBK)
   {
     this.inToaThuocBK = inToaThuocBK;
   }

   public boolean isbChuyenVaoNT()
   {
     return this.bChuyenVaoNT;
   }

   public void setbChuyenVaoNT(boolean bChuyenVaoNT)
   {
     this.bChuyenVaoNT = bChuyenVaoNT;
   }

   public boolean isHasChKhoa()
   {
     return this.hasChKhoa;
   }

   public void setHasChKhoa(boolean hasChKhoa)
   {
     this.hasChKhoa = hasChKhoa;
   }

   public boolean isHasChBanKham()
   {
     return this.hasChBanKham;
   }

   public void setHasChBanKham(boolean hasChBanKham)
   {
     this.hasChBanKham = hasChBanKham;
   }

   public boolean isDaThtoankham()
   {
     return this.daThtoankham;
   }

   public void setDaThtoankham(boolean daThtoankham)
   {
     this.daThtoankham = daThtoankham;
   }

   public boolean isNotBNbaohiem()
   {
     return this.notBNbaohiem;
   }

   public void setNotBNbaohiem(boolean notBNbaohiem)
   {
     this.notBNbaohiem = notBNbaohiem;
   }

   public boolean isNotDisplayKTQBV()
   {
     return this.notDisplayKTQBV;
   }

   public void setNotDisplayKTQBV(boolean notDisplayKTQBV)
   {
     this.notDisplayKTQBV = notDisplayKTQBV;
   }

   public void displayInforBenhNhanForCall()
   {
     this.tenBenhNhanForCall = "";
     if ((this.tiepDonForCall.getTiepdonMa() == null) || (this.tiepDonForCall.getTiepdonMa().equals("")) || (this.thamkham.getThamkhamBankham() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepDonForCall.getTiepdonMa(), new Object[0]);
       return;
     }
     ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
     ThamKham thamkham_tmp = thamkhamDelegate.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.tiepDonForCall.getTiepdonMa());
     if (thamkham_tmp == null)
     {
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepDonForCall.getTiepdonMa(), new Object[0]);
       return;
     }
     this.tiepDonForCall = thamkham_tmp.getTiepdonMa();
     if (this.tiepDonForCall.getBenhnhanMa() != null) {
       this.tenBenhNhanForCall = this.tiepDonForCall.getBenhnhanMa(true).getBenhnhanHoten();
     }
   }

   public TiepDon getTiepDonForCall()
   {
     return this.tiepDonForCall;
   }

   public void setTiepDonForCall(TiepDon tiepDonForCall)
   {
     this.tiepDonForCall = tiepDonForCall;
   }

   public String getTenBenhNhanForCall()
   {
     return this.tenBenhNhanForCall;
   }

   public void setTenBenhNhanForCall(String tenBenhNhanForCall)
   {
     this.tenBenhNhanForCall = tenBenhNhanForCall;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ThamKhamVaXuTriAction

 * JD-Core Version:    0.7.0.1

 */