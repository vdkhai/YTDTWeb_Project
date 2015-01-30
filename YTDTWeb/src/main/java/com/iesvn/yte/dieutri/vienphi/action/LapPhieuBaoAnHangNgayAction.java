 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.BenhNhanCheDoAnDelegate;
 import com.iesvn.yte.dieutri.delegate.BenhNhanGioAnDelegate;
 import com.iesvn.yte.dieutri.delegate.BenhNhanPhieuBaoAnDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmCheDoAnDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmDoiTuongAnDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmDongThemDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmGioAnDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmLoaiAn2Delegate;
 import com.iesvn.yte.dieutri.delegate.DtDmLoaiAnDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmMucAnDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuBaoAnDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
 import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
 import com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn;
 import com.iesvn.yte.dieutri.entity.DtDmCheDoAn;
 import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;
 import com.iesvn.yte.dieutri.entity.DtDmDongThem;
 import com.iesvn.yte.dieutri.entity.DtDmGioAn;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
 import com.iesvn.yte.dieutri.entity.DtDmMucAn;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3140_Lapphieubaoanhangngay")
 @Synchronized(timeout=6000000L)
 public class LapPhieuBaoAnHangNgayAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static int CHE_DO_AN_LAT = 1;
   private static int CHE_DO_AN_DD = 2;
   private static int CHE_DO_AN_STM = 3;
   private static int CHE_DO_AN_THUONG = 4;
   private static int CHE_DO_AN_CMO = 5;
   private static int CHE_DO_AN_BDUONG = 6;
   private static int GIO_AN_6H = 1;
   private static int GIO_AN_11H = 2;
   private static int GIO_AN_16H = 3;
   private static int GIO_AN_20H = 4;
   private String khoaMaso;
   private String ngayan;
   private String ngayLayPbaOld;
   private PhieuBaoAnCheDoAn pbacda;
   private String ngayhientai;
   private String sohsbenhan;
   private String doituonganMaso;
   private String loaian1Maso;
   private String loaian2Maso;
   private String mucanMaso;
   private String dongthemMaso;
   private boolean cdaLat;
   private boolean cdaDd;
   private boolean cdaStm;
   private boolean cdaThuong;
   private boolean cdaCmo;
   private boolean cdaBduong;
   private boolean phutroi;
   private boolean gioan6;
   private boolean gioan11;
   private boolean gioan16;
   private boolean gioan20;
   private int count;
   private int selectedIndex;
   private String gioitinh;
   private String phong;
   private boolean updatePba;
   private boolean lockGhinhanBtn;
   private boolean lockInphieuBtn;
   private String strIDRemove;
   private List<PhieuBaoAnCheDoAn> listPbaCda;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strLapPba;
   @Logger
   private Log log;
   private SimpleDateFormat sdf;

   public LapPhieuBaoAnHangNgayAction()
   {
     this.reportTypeVP = null;
     this.jasperPrintVP = null;
     this.reportPathVP = null;
     this.sdf = new SimpleDateFormat("dd/MM/yyyy");
   }

   @Factory("strLapPba")
   public void init()
   {
     this.ngayhientai = this.sdf.format(new Date());
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     cal.add(5, 1);
     this.ngayan = this.sdf.format(cal.getTime());
     reset(false);
     this.listPbaCda = new ArrayList();
     this.count = 0;
     this.updatePba = false;
     this.strLapPba = "";
     this.strIDRemove = "";
     this.ngayLayPbaOld = "";
   }

   public void reset(boolean isSearchPba)
   {
     this.pbacda = new PhieuBaoAnCheDoAn();
     BenhNhanPhieuBaoAn bnphieubaoan = new BenhNhanPhieuBaoAn();
     bnphieubaoan.setBnpbaDangsua(new Short("0"));
     bnphieubaoan.setBnpbaSoluong(new Integer("2500"));

     Hsba hsBenhan = new Hsba();
     hsBenhan.setBenhnhanMa(new BenhNhan());

     bnphieubaoan.setHsbaSovaovien(hsBenhan);
     this.pbacda.setBnphieubaoan(bnphieubaoan);
     this.sohsbenhan = "";
     this.doituonganMaso = (this.loaian1Maso = this.loaian2Maso = this.mucanMaso = this.dongthemMaso = "");
     if (!isSearchPba) {
       this.khoaMaso = "";
     }
     this.cdaLat = this.cdaDd = this.cdaStm = this.cdaCmo = this.cdaBduong = false;
     this.cdaThuong = true;
     this.phutroi = false;
     this.gioan6 = this.gioan11 = this.gioan16 = this.gioan20 = false;
     this.gioitinh = "";
     this.phong = "";
     this.lockGhinhanBtn = false;
     this.lockInphieuBtn = true;
     this.selectedIndex = -1;
   }

   public String initFromMenu(String fromMenu)
   {
     return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
   }

   public void searchPhieubaoan()
   {
     this.log.info("--enter searchPhieubaoan()---", new Object[0]);
     reset(true);
     try
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");
       PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
       PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn2(dmKhoa.getDmkhoaMa(), this.sdf.parse(this.ngayan));

       this.listPbaCda.clear();
       if (pbaTmp != null)
       {
         System.out.println("ngay an search = " + pbaTmp.getPhieubaoanNgayan() + ", equal = " + this.sdf.format(pbaTmp.getPhieubaoanNgayan()).equals(this.ngayan));
         if (this.sdf.format(pbaTmp.getPhieubaoanNgayan()).equals(this.ngayan))
         {
           this.updatePba = true;
           this.ngayLayPbaOld = "";
         }
         else
         {
           this.updatePba = false;
           this.ngayLayPbaOld = this.sdf.format(pbaTmp.getPhieubaoanNgayan());
         }
         BenhNhanPhieuBaoAnDelegate bnPbaDel = BenhNhanPhieuBaoAnDelegate.getInstance();
         List<BenhNhanPhieuBaoAn> listBnPba = bnPbaDel.findByPbaMaso(pbaTmp.getPhieubaoanMaso());
         this.listPbaCda = new ArrayList(listBnPba.size());
         BenhNhanCheDoAnDelegate bncdaDel = BenhNhanCheDoAnDelegate.getInstance();
         BenhNhanGioAnDelegate bngaDel = BenhNhanGioAnDelegate.getInstance();
         for (BenhNhanPhieuBaoAn eachBnPba : listBnPba)
         {
           PhieuBaoAnCheDoAn pbacdaTmp = new PhieuBaoAnCheDoAn();
           pbacdaTmp.setBnphieubaoan(eachBnPba);
           pbacdaTmp.setListBnCda(bncdaDel.findByBnPbaMaso(eachBnPba.getBnpbaMaso()));
           pbacdaTmp.setListBnGa(bngaDel.findByBnPbaMaso(eachBnPba.getBnpbaMaso()));
           this.listPbaCda.add(pbacdaTmp);
         }
         this.count = this.listPbaCda.size();
         this.lockGhinhanBtn = false;
         this.lockInphieuBtn = (!this.ngayLayPbaOld.equals(""));
         FacesMessages.instance().clear();
       }
       else
       {
         this.updatePba = false;
         this.lockGhinhanBtn = false;
         this.lockInphieuBtn = true;
         this.ngayLayPbaOld = "";

         FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
       }
       this.strIDRemove = "";
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     this.log.info("--exit searchPhieubaoan()---", new Object[0]);
   }

   public void searchBenhnhan()
     throws Exception
   {
     try
     {
       this.log.info("--enter searchBenhnhan(), sohsbenhan = " + this.sohsbenhan, new Object[0]);
       if (this.sohsbenhan.trim().length() < 1)
       {
         FacesMessages.instance().clear();
         return;
       }
       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
       HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienLastHsbaKhoa(this.sohsbenhan);
       if (hsbaKhoa == null)
       {
         this.log.info("hsbaKhoa1:" + hsbaKhoa, new Object[0]);
         this.sohsbenhan = "";
         FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });

         reset(false);
         return;
       }
       if (!this.khoaMaso.equals("" + hsbaKhoa.getKhoaMa().getDmkhoaMaso()))
       {
         this.log.info("hsbaKhoa1:" + hsbaKhoa, new Object[0]);
         this.sohsbenhan = "";
         FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });

         reset(false);
         return;
       }
       this.pbacda.getBnphieubaoan().setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());

       this.gioitinh = hsbaKhoa.getHsbaSovaovien().getBenhnhanMa().getDmgtMaso().getDmgtMa();

       HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.sohsbenhan);
       if (hsbaCm != null) {
         this.phong = hsbaCm.getHsbacmSobuong();
       }
     }
     catch (Exception e)
     {
       System.out.println("error on function searchBenhnhan" + e);
     }
     this.log.info("--end searchBenhnhan()---", new Object[0]);
   }

   public void enter()
     throws Exception
   {
     this.log.info("*****Begin Enter(), selectedIndex = " + this.selectedIndex, new Object[0]);
     if (this.doituonganMaso.trim().length() > 0) {
       this.pbacda.getBnphieubaoan().setDtdmdtaMaso(DtDmDoiTuongAnDelegate.getInstance().find(new Integer(this.doituonganMaso)));
     } else {
       this.pbacda.getBnphieubaoan().setDtdmdtaMaso(null);
     }
     if (this.loaian1Maso.trim().length() > 0) {
       this.pbacda.getBnphieubaoan().setDtdmlaMaso(DtDmLoaiAnDelegate.getInstance().find(new Integer(this.loaian1Maso)));
     } else {
       this.pbacda.getBnphieubaoan().setDtdmlaMaso(null);
     }
     if (this.loaian2Maso.trim().length() > 0) {
       this.pbacda.getBnphieubaoan().setDtdmla2Maso(DtDmLoaiAn2Delegate.getInstance().find(new Integer(this.loaian2Maso)));
     } else {
       this.pbacda.getBnphieubaoan().setDtdmla2Maso(null);
     }
     if (!this.doituonganMaso.trim().equals("4"))
     {
       if (this.mucanMaso.trim().length() > 0) {
         this.pbacda.getBnphieubaoan().setDtdmmaMaso(DtDmMucAnDelegate.getInstance().find(new Integer(this.mucanMaso)));
       } else {
         this.pbacda.getBnphieubaoan().setDtdmmaMaso(null);
       }
     }
     else {
       this.pbacda.getBnphieubaoan().setDtdmmaMaso(null);
     }
     if (this.dongthemMaso.trim().length() > 0) {
       this.pbacda.getBnphieubaoan().setDtdmdtMaso(DtDmDongThemDelegate.getInstance().find(new Integer(this.dongthemMaso)));
     } else {
       this.pbacda.getBnphieubaoan().setDtdmdtMaso(null);
     }
     this.pbacda.getBnphieubaoan().setBnpbaPhutroi(this.phutroi ? new Short("1") : new Short("0"));


     this.pbacda.setListBnCda(new ArrayList());

     DtDmCheDoAnDelegate cdaDel = DtDmCheDoAnDelegate.getInstance();
     if (this.cdaLat)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_LAT)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     if (this.cdaDd)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_DD)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     if (this.cdaStm)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_STM)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     if (this.cdaThuong)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_THUONG)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     if (this.cdaCmo)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_CMO)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     if (this.cdaBduong)
     {
       BenhNhanCheDoAn bnCda = new BenhNhanCheDoAn();
       bnCda.setDtdmcdaMaso(cdaDel.find(new Integer(CHE_DO_AN_BDUONG)));
       this.pbacda.getListBnCda().add(bnCda);
     }
     this.pbacda.setListBnGa(new ArrayList());
     DtDmGioAnDelegate gaDel = DtDmGioAnDelegate.getInstance();
     if (this.gioan6)
     {
       BenhNhanGioAn bnGa = new BenhNhanGioAn();
       bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_6H)));
       this.pbacda.getListBnGa().add(bnGa);
     }
     if (this.gioan11)
     {
       BenhNhanGioAn bnGa = new BenhNhanGioAn();
       bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_11H)));
       this.pbacda.getListBnGa().add(bnGa);
     }
     if (this.gioan16)
     {
       BenhNhanGioAn bnGa = new BenhNhanGioAn();
       bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_16H)));
       this.pbacda.getListBnGa().add(bnGa);
     }
     if (this.gioan20)
     {
       BenhNhanGioAn bnGa = new BenhNhanGioAn();
       bnGa.setDtdmgaMaso(gaDel.find(new Integer(GIO_AN_20H)));
       this.pbacda.getListBnGa().add(bnGa);
     }
     if (this.selectedIndex < 0) {
       this.listPbaCda.add(this.pbacda);
     } else {
       this.listPbaCda.set(this.selectedIndex, this.pbacda);
     }
     this.count = this.listPbaCda.size();
     reset(false);
     this.log.info("*****End Enter(),listPbaCda size = " + this.listPbaCda.size(), new Object[0]);
   }

   public String showMucan(PhieuBaoAnCheDoAn phieu)
   {
     String mucan = "";
     if (phieu.getBnphieubaoan().getDtdmmaMaso() != null)
     {
       mucan = phieu.getBnphieubaoan().getDtdmmaMaso().getDtdmmaTen();
       mucan = mucan.replaceAll("[.]", "");
     }
     String dongthem = phieu.getBnphieubaoan().getDtdmdtMaso() != null ? phieu.getBnphieubaoan().getDtdmdtMaso().getDtdmdtTen() : "";
     String numDongthem = dongthem.replaceAll("[.]", "");

     int imucan = 0;
     if (mucan.trim().length() > 0) {
       imucan = new Integer(mucan).intValue();
     }
     int idongthem = 0;
     if (dongthem.trim().length() > 0) {
       idongthem = new Integer(numDongthem).intValue();
     }
     imucan += idongthem;
     mucan = "" + imucan;
     if (mucan.length() > 0)
     {
       String reverse = new StringBuffer(mucan).reverse().toString();
       mucan = new StringBuffer(insertPeriodically(reverse, ".", 3)).reverse().toString();
     }
     return (imucan > 0 ? "" + mucan : "") + (idongthem > 0 ? " (" + dongthem + ")" : "");
   }

   public String showChedoan(PhieuBaoAnCheDoAn phieu)
   {
     String strReturn = "";
     for (BenhNhanCheDoAn each : phieu.getListBnCda()) {
       strReturn = strReturn + (strReturn.trim().length() > 0 ? ", " + each.getDtdmcdaMaso().getDtdmcdaTen() : each.getDtdmcdaMaso().getDtdmcdaTen());
     }
     return strReturn;
   }

   public String showGioan(PhieuBaoAnCheDoAn phieu)
   {
     String strReturn = "";
     for (BenhNhanGioAn each : phieu.getListBnGa()) {
       strReturn = strReturn + (strReturn.trim().length() > 0 ? ", " + each.getDtdmgaMaso().getDtdmgaTen() : each.getDtdmgaMaso().getDtdmgaTen());
     }
     return strReturn;
   }

   private String insertPeriodically(String text, String insert, int period)
   {
     StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);


     int index = 0;
     String prefix = "";
     while (index < text.length())
     {
       builder.append(prefix);
       prefix = insert;
       builder.append(text.substring(index, Math.min(index + period, text.length())));

       index += period;
     }
     return builder.toString();
   }

   public void edit(int index)
   {
     this.pbacda = ((PhieuBaoAnCheDoAn)this.listPbaCda.get(index));
     this.sohsbenhan = this.pbacda.getBnphieubaoan().getHsbaSovaovien().getHsbaSovaovien();
     this.doituonganMaso = this.pbacda.getBnphieubaoan().getDtdmdtaMaso().getDtdmdtaMaso().toString();
     this.loaian1Maso = this.pbacda.getBnphieubaoan().getDtdmlaMaso().getDtdmlaMaso().toString();
     this.loaian2Maso = "";
     if (this.pbacda.getBnphieubaoan().getDtdmla2Maso() != null) {
       this.loaian2Maso = this.pbacda.getBnphieubaoan().getDtdmla2Maso().getDtdmla2Maso().toString();
     }
     this.cdaLat = this.cdaDd = this.cdaStm = this.cdaCmo = this.cdaBduong = this.cdaThuong = false;
     for (BenhNhanCheDoAn cda : this.pbacda.getListBnCda()) {
       if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_LAT))) {
         this.cdaLat = true;
       } else if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_DD))) {
         this.cdaDd = true;
       } else if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_STM))) {
         this.cdaStm = true;
       } else if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_CMO))) {
         this.cdaCmo = true;
       } else if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_BDUONG))) {
         this.cdaBduong = true;
       } else if (cda.getDtdmcdaMaso().getDtdmcdaMaso().equals(new Integer(CHE_DO_AN_THUONG))) {
         this.cdaThuong = true;
       }
     }
     this.gioan6 = this.gioan11 = this.gioan16 = this.gioan20 = false;
     for (BenhNhanGioAn ga : this.pbacda.getListBnGa()) {
       if (ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_6H))) {
         this.gioan6 = true;
       } else if (ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_11H))) {
         this.gioan11 = true;
       } else if (ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_16H))) {
         this.gioan16 = true;
       } else if (ga.getDtdmgaMaso().getDtdmgaMaso().equals(new Integer(GIO_AN_20H))) {
         this.gioan20 = true;
       }
     }
     this.mucanMaso = (this.pbacda.getBnphieubaoan().getDtdmmaMaso() == null ? "" : this.pbacda.getBnphieubaoan().getDtdmmaMaso().getDtdmmaMaso().toString());
     this.dongthemMaso = "";
     if (this.pbacda.getBnphieubaoan().getDtdmdtMaso() != null) {
       this.dongthemMaso = this.pbacda.getBnphieubaoan().getDtdmdtMaso().getDtdmdtMaso().toString();
     }
     this.phutroi = (this.pbacda.getBnphieubaoan().getBnpbaPhutroi().intValue() == 1);
     this.gioitinh = this.pbacda.getBnphieubaoan().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso().getDmgtMa();
     HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.sohsbenhan);
     if (hsbaCm != null) {
       this.phong = hsbaCm.getHsbacmSobuong();
     }
     this.selectedIndex = index;
   }

   public void delete(int index)
     throws Exception
   {
     this.log.info("*****Begin delete() *****", new Object[0]);
     this.strIDRemove = ("" + ((PhieuBaoAnCheDoAn)this.listPbaCda.get(index)).getBnphieubaoan().getBnpbaMaso());
     this.listPbaCda.remove(index);
     this.count = this.listPbaCda.size();

     reset(false);
     this.log.info("*****End delete() *****", new Object[0]);
   }

   private void luuBnPhieuBaoAn(PhieuBaoAn pba, boolean isUpdate)
   {
     for (PhieuBaoAnCheDoAn eachPbaCda : this.listPbaCda)
     {
       BenhNhanPhieuBaoAn bnPba = eachPbaCda.getBnphieubaoan();
       if (!isUpdate) {
         bnPba.setBnpbaMaso(null);
       }
       bnPba.setPhieubaoanMaso(pba);
       BenhNhanPhieuBaoAnDelegate.getInstance().myCreate(bnPba, eachPbaCda.getListBnCda(), eachPbaCda.getListBnGa(), isUpdate);
     }
   }

   public String ghinhan()
     throws Exception
   {
     this.log.info("*****Begin Ghi nhan() *****, updatePba = " + this.updatePba, new Object[0]);
     this.log.info("*****so phan tu insert *****" + this.listPbaCda.size(), new Object[0]);
     if ((this.listPbaCda == null) || (this.listPbaCda.size() == 0))
     {
       this.listPbaCda = new ArrayList();
       FacesMessages.instance().add(IConstantsRes.PBA_EMPTY, new Object[] { "", "" });

       return "";
     }
     try
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");

       PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
       PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), this.sdf.parse(this.ngayan));
       this.log.info("pbaTmp = " + pbaTmp, new Object[0]);
       if (pbaTmp == null)
       {
         pbaTmp = new PhieuBaoAn();
         pbaTmp.setKhoaMaso(dmKhoa);
         pbaTmp.setPhieubaoanNgayan(this.sdf.parse(this.ngayan));
         pbaTmp = PhieuBaoAnDelegate.getInstance().myCreate(pbaTmp, false);

         luuBnPhieuBaoAn(pbaTmp, false);
         this.lockGhinhanBtn = true;
         this.lockInphieuBtn = false;
         FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
       }
       else if (this.updatePba)
       {
         if (this.strIDRemove.trim().length() > 0)
         {
           int i = BenhNhanPhieuBaoAnDelegate.getInstance().removeByPbaMaso(pbaTmp.getPhieubaoanMaso());
           this.log.info("Xoa " + i + " benh nhan khoi phieu bao an", new Object[0]);
           luuBnPhieuBaoAn(pbaTmp, false);
         }
         else
         {
           luuBnPhieuBaoAn(pbaTmp, true);
         }
         this.lockGhinhanBtn = true;
         this.lockInphieuBtn = false;
         FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
       }
       else
       {
         this.lockGhinhanBtn = false;
         this.lockInphieuBtn = true;

         FacesMessages.instance().add(IConstantsRes.PBA_EXISTS, new Object[] { this.ngayan });
       }
     }
     catch (Exception e)
     {
       if (this.updatePba) {
         FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.INSERT_FAIL, new Object[0]);
       }
       e.printStackTrace();
       this.log.error("*************loi***********" + e.toString(), new Object[0]);
     }
     this.ngayLayPbaOld = "";
     this.log.info("*****End Ghi nhan() *****", new Object[0]);
     return null;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "lapphieubaoanhangngay_form";
     this.log.info("Vao Method XuatReport phieu bao an ngay", new Object[0]);

     DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
     DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");
     PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
     try
     {
       PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), this.sdf.parse(this.ngayan));

       String baocao1 = null;

       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3140_Phieubaoan_form.jrxml";




       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();
       params.put("khoa", pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaTen() : "");
       params.put("khoamaso", pbaTmp != null ? pbaTmp.getKhoaMaso().getDmkhoaMaso() : new Integer(0));
       params.put("ngayan", pbaTmp != null ? pbaTmp.getPhieubaoanNgayan() : this.sdf.parse(this.ngayan));

       this.log.info("================= ", new Object[0]);
       Class.forName("com.mysql.jdbc.Driver");
       this.log.info("da thay driver mysql", new Object[0]);
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         this.log.info(e.getMessage(), new Object[0]);
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Xeminlientucphieucongkhai");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public String getSohsbenhan()
   {
     return this.sohsbenhan;
   }

   public void setSohsbenhan(String sohsbenhan)
   {
     this.sohsbenhan = sohsbenhan;
   }

   public boolean isCdaLat()
   {
     return this.cdaLat;
   }

   public void setCdaLat(boolean cdaLat)
   {
     this.cdaLat = cdaLat;
   }

   public boolean isCdaDd()
   {
     return this.cdaDd;
   }

   public void setCdaDd(boolean cdaDd)
   {
     this.cdaDd = cdaDd;
   }

   public boolean isCdaStm()
   {
     return this.cdaStm;
   }

   public void setCdaStm(boolean cdaStm)
   {
     this.cdaStm = cdaStm;
   }

   public boolean isCdaThuong()
   {
     return this.cdaThuong;
   }

   public void setCdaThuong(boolean cdaThuong)
   {
     this.cdaThuong = cdaThuong;
   }

   public boolean isCdaCmo()
   {
     return this.cdaCmo;
   }

   public void setCdaCmo(boolean cdaCmo)
   {
     this.cdaCmo = cdaCmo;
   }

   public boolean isCdaBduong()
   {
     return this.cdaBduong;
   }

   public void setCdaBduong(boolean cdaBduong)
   {
     this.cdaBduong = cdaBduong;
   }

   public boolean isPhutroi()
   {
     return this.phutroi;
   }

   public void setPhutroi(boolean phutroi)
   {
     this.phutroi = phutroi;
   }

   public int getCount()
   {
     return this.count;
   }

   public void setCount(int count)
   {
     this.count = count;
   }

   public int getSelectedIndex()
   {
     return this.selectedIndex;
   }

   public void setSelectedIndex(int selectedIndex)
   {
     this.selectedIndex = selectedIndex;
   }

   public String getGioitinh()
   {
     return this.gioitinh;
   }

   public void setGioitinh(String gioitinh)
   {
     this.gioitinh = gioitinh;
   }

   public String getNgayan()
   {
     return this.ngayan;
   }

   public void setNgayan(String ngayan)
   {
     this.ngayan = ngayan;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getPhong()
   {
     return this.phong;
   }

   public void setPhong(String phong)
   {
     this.phong = phong;
   }

   public PhieuBaoAnCheDoAn getPbacda()
   {
     return this.pbacda;
   }

   public void setPbacda(PhieuBaoAnCheDoAn pbacda)
   {
     this.pbacda = pbacda;
   }

   public List<PhieuBaoAnCheDoAn> getListPbaCda()
   {
     return this.listPbaCda;
   }

   public void setListPbaCda(List<PhieuBaoAnCheDoAn> listPbaCda)
   {
     this.listPbaCda = listPbaCda;
   }

   public String getDoituonganMaso()
   {
     return this.doituonganMaso;
   }

   public void setDoituonganMaso(String doituonganMaso)
   {
     this.doituonganMaso = doituonganMaso;
   }

   public String getLoaian1Maso()
   {
     return this.loaian1Maso;
   }

   public void setLoaian1Maso(String loaian1Maso)
   {
     this.loaian1Maso = loaian1Maso;
   }

   public String getLoaian2Maso()
   {
     return this.loaian2Maso;
   }

   public void setLoaian2Maso(String loaian2Maso)
   {
     this.loaian2Maso = loaian2Maso;
   }

   public String getMucanMaso()
   {
     return this.mucanMaso;
   }

   public void setMucanMaso(String mucanMaso)
   {
     this.mucanMaso = mucanMaso;
   }

   public String getDongthemMaso()
   {
     return this.dongthemMaso;
   }

   public void setDongthemMaso(String dongthemMaso)
   {
     this.dongthemMaso = dongthemMaso;
   }

   public String getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(String khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }

   public boolean isGioan6()
   {
     return this.gioan6;
   }

   public void setGioan6(boolean gioan6)
   {
     this.gioan6 = gioan6;
   }

   public boolean isGioan11()
   {
     return this.gioan11;
   }

   public void setGioan11(boolean gioan11)
   {
     this.gioan11 = gioan11;
   }

   public boolean isGioan16()
   {
     return this.gioan16;
   }

   public void setGioan16(boolean gioan16)
   {
     this.gioan16 = gioan16;
   }

   public boolean isGioan20()
   {
     return this.gioan20;
   }

   public void setGioan20(boolean gioan20)
   {
     this.gioan20 = gioan20;
   }

   public boolean isLockGhinhanBtn()
   {
     return this.lockGhinhanBtn;
   }

   public void setLockGhinhanBtn(boolean lockGhinhanBtn)
   {
     this.lockGhinhanBtn = lockGhinhanBtn;
   }

   public boolean isLockInphieuBtn()
   {
     return this.lockInphieuBtn;
   }

   public void setLockInphieuBtn(boolean lockInphieuBtn)
   {
     this.lockInphieuBtn = lockInphieuBtn;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public String getNgayLayPbaOld()
   {
     return this.ngayLayPbaOld;
   }

   public void setNgayLayPbaOld(String ngayLayPbaOld)
   {
     this.ngayLayPbaOld = ngayLayPbaOld;
   }

   public class PhieuBaoAnCheDoAn
     implements Serializable
   {
     private static final long serialVersionUID = 10L;
     private BenhNhanPhieuBaoAn bnphieubaoan;
     private List<BenhNhanCheDoAn> listBnCda;
     private List<BenhNhanGioAn> listBnGa;

     public PhieuBaoAnCheDoAn() {}

     public List<BenhNhanGioAn> getListBnGa()
     {
       return this.listBnGa;
     }

     public void setListBnGa(List<BenhNhanGioAn> listBnGa)
     {
       this.listBnGa = listBnGa;
     }

     public BenhNhanPhieuBaoAn getBnphieubaoan()
     {
       return this.bnphieubaoan;
     }

     public void setBnphieubaoan(BenhNhanPhieuBaoAn bnphieubaoan)
     {
       this.bnphieubaoan = bnphieubaoan;
     }

     public List<BenhNhanCheDoAn> getListBnCda()
     {
       return this.listBnCda;
     }

     public void setListBnCda(List<BenhNhanCheDoAn> listBnCda)
     {
       this.listBnCda = listBnCda;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.LapPhieuBaoAnHangNgayAction

 * JD-Core Version:    0.7.0.1

 */