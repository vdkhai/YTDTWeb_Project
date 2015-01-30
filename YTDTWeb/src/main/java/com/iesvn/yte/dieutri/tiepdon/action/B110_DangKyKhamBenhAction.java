 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.SESSION)
 @Name("B110_Dangkykhambenh")
 @Synchronized(timeout=6000000L)
 public class B110_DangKyKhamBenhAction
   implements Serializable
 {
   private static final long serialVersionUID = 4201303779918089532L;
   private static Logger log = Logger.getLogger(B110_DangKyKhamBenhAction.class);
   private static final String urlMySQL = IConstantsRes.DK_ONLINE_DB_URL;
   private static final String userName = IConstantsRes.DATABASE_USER;
   private static final String password = IConstantsRes.DATABASE_PASS;
   Connection conn;
   private TiepDon tiepdon;
   private String gioTD = "";
   private String ngayTD = "";
   private String gioi = "1";
   private String giatri1 = "";
   private String giatri2 = "";
   private String giatri3 = "";
   private String giatri4 = "";
   private String moc1 = "";
   private String moc2 = "";
   private String moc3 = "";
   private String ngaysinh = "";
   private String maBacSiThamKham = "";
   private String hosoDaTT = "false";
   private List<BenhNhanTrungTheBhytDTO> listBN;
   private boolean showListBn;
   private boolean lockDoituong;
   private String doituongHientai;
   private String maTD_online = "";
   private String ngaygioDK = "";
   private String ngayhientai = "";
   private String listMaTinhBhyt;
   private String hid_maTD = "";
   private String hid_maBN = "";
   private String hid_mess = "";
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strDKyKhamBenh;
   @In(required=false)
   @Out(required=false)
   private String benhNhan_ma;
   @In(required=false)
   @Out(required=false)
   private String tiepDon_ma;

   @Factory("strDKyKhamBenh")
   public void init()
   {
     resetValue();
   }

   public void resetValue()
   {
     this.tiepdon = new TiepDon();
     SetInforUtil.setInforIfNullForTiepDon(this.tiepdon);

     BenhNhan benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(benhnhan);

     this.tiepdon.setBenhnhanMa(benhnhan);

     this.gioTD = Utils.getGioPhut(new Date());
     this.ngayTD = Utils.getCurrentDate();
     this.gioi = "1";

     this.giatri1 = "";
     this.giatri2 = "";
     this.giatri3 = "";
     this.giatri4 = "";
     this.moc1 = "";
     this.moc2 = "";
     this.moc3 = "";
     this.ngaysinh = "";
     this.maBacSiThamKham = "";
     this.hosoDaTT = "false";
     this.listBN = new ArrayList();
     this.showListBn = false;
     this.lockDoituong = false;
     this.doituongHientai = "";
     this.maTD_online = "";
     this.ngaygioDK = "";
     this.ngayhientai = Utils.getCurrentDate();
     this.strDKyKhamBenh = "";

     List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     this.listMaTinhBhyt = "";
     for (DmTinh each : listDmTinh) {
       this.listMaTinhBhyt = (this.listMaTinhBhyt + each.getDmtinhBHYT() + ",");
     }
     this.hid_maTD = "";
     this.hid_maBN = "";
     this.hid_mess = "";
   }

   public void loadTiepdon()
   {
     String maTD = this.tiepdon.getTiepdonMa();
     this.tiepdon = TiepDonDelegate.getInstance().find(this.tiepdon.getTiepdonMa());
     if (this.tiepdon == null)
     {
       resetValue();
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD, new Object[0]);
     }
     else if (this.tiepdon.getTiepdonBankham() == null)
     {
       resetValue();
       Hsba hsbaTmp = HsbaDelegate.getInstance().findByTiepDonMa(maTD);
       if (hsbaTmp != null) {
         FacesMessages.instance().add(IConstantsRes.TIEP_DON_DUOC_TAO_TU_NOI_TRU, new Object[] { maTD, hsbaTmp.getHsbaSovaovien() });
       } else {
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD, new Object[0]);
       }
     }
     else
     {
       this.listBN = new ArrayList();
       this.showListBn = false;
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       this.gioTD = Utils.getGioPhut(this.tiepdon.getTiepdonNgaygio());
       this.ngayTD = Utils.reFactorString(this.tiepdon.getTiepdonNgaygio());
       BenhNhan benhnhan = this.tiepdon.getBenhnhanMa();
       if (benhnhan == null) {
         benhnhan = new BenhNhan();
       }
       SetInforUtil.setInforIfNullForBN(benhnhan);
       this.tiepdon.setBenhnhanMa(benhnhan);
       if (this.tiepdon.getBenhnhanMa() != null)
       {
         this.gioi = ("" + this.tiepdon.getBenhnhanMa().getDmgtMaso().getDmgtMa());
         if (this.tiepdon.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
           this.ngaysinh = df.format(this.tiepdon.getBenhnhanMa().getBenhnhanNgaysinh());
         } else {
           this.ngaysinh = "";
         }
       }
       this.giatri1 = Utils.reFactorString(this.tiepdon.getTiepdonGiatri1());
       this.giatri2 = Utils.reFactorString(this.tiepdon.getTiepdonGiatri2());
       this.giatri3 = Utils.reFactorString(this.tiepdon.getTiepdonGiatri3());
       this.giatri4 = Utils.reFactorString(this.tiepdon.getTiepdonGiatri4());
       this.moc1 = Utils.reFactorString(this.tiepdon.getTiepdonMoc1());
       this.moc2 = Utils.reFactorString(this.tiepdon.getTiepdonMoc2());
       this.moc3 = Utils.reFactorString(this.tiepdon.getTiepdonMoc3());









       checkBacsiThamkham(this.tiepdon.getTiepdonMa());

       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();

       HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(this.tiepdon.getTiepdonMa());
       this.hosoDaTT = "false";
       if (hsttk != null)
       {
         this.hosoDaTT = "true";
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
       }
       else if ((this.tiepdon.getTiepdonChkhoa() != null) && (!this.tiepdon.getTiepdonChkhoa().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
         this.hosoDaTT = "true";
       }
       SetInforUtil.setInforIfNullForTiepDon(this.tiepdon);
       this.lockDoituong = false;
       this.doituongHientai = this.tiepdon.getDoituongMa().getDmdoituongMa();

       List<ClsKham> listClsKham = ClsKhamDelegate.getInstance().findByTiepdonma(this.tiepdon.getTiepdonMa());
       for (ClsKham cls : listClsKham) {
         if (cls.getClskhamLoai().equalsIgnoreCase("KH"))
         {
           listClsKham.remove(cls);
           break;
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().find2LoaiByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
       if (((listClsKham != null) && (listClsKham.size() > 0)) || ((listTpk != null) && (listTpk.size() > 0))) {
         this.lockDoituong = true;
       }
     }
     this.hid_maTD = "";
     this.hid_maBN = "";
     this.hid_mess = "";
   }

   public void loadBenhNhan()
   {
     String bnMa = this.tiepdon.getBenhnhanMa().getBenhnhanMa();
     BenhNhan bn = BenhNhanDelegate.getInstance().find(bnMa);
     if (bn != null)
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       this.gioi = ("" + bn.getDmgtMaso().getDmgtMa());
       if (bn.getBenhnhanNgaysinh() != null) {
         this.ngaysinh = df.format(bn.getBenhnhanNgaysinh());
       } else {
         this.ngaysinh = "";
       }
       this.tiepdon.setBenhnhanMa(bn);
     }
     else
     {
       this.tiepdon.setBenhnhanMa(new BenhNhan());
       SetInforUtil.setInforIfNullForBN(this.tiepdon.getBenhnhanMa());
       this.ngaysinh = "";
       this.gioi = "1";
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_TIEPDON, new Object[] { "mã bệnh nhân", bnMa });
     }
   }

   private void tinhToanChoHSTKKham(TiepDon td, HsThtoank hsttk)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(td);
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);
   }

   public void ghinhan()
   {
     log.info("Begin ghinhan, maTD = " + this.tiepdon.getTiepdonMa() + ", identity.getUsername():" + this.identity.getUsername());



     DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
     if (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH"))
     {
       if (this.tiepdon.getTinhbhytMa(true).getDmtinhMaso() != null)
       {
         DmTinh tinhBhyt = (DmTinh)utilDel.findByMaSo(this.tiepdon.getTinhbhytMa(true).getDmtinhMaso(), "DmTinh", "dmtinhMaso");
         if (tinhBhyt == null)
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.TINH_CAP_BHYT, this.tiepdon.getTinhbhytMa(true).getDmtinhBHYT() });
           return;
         }
         this.tiepdon.setTinhbhytMa(tinhBhyt);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.TINH_CAP_BHYT });
         return;
       }
       if (this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa() != null)
       {
         DmBenhVien noiKCB = (DmBenhVien)utilDel.findByMa(this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
         if (noiKCB == null)
         {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.NOI_DK_KCB, this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa() });
           return;
         }
         this.tiepdon.setKcbbhytMa(noiKCB);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.NOI_DK_KCB });
         return;
       }
     }
     DieuTriUtilDelegate delegate = DieuTriUtilDelegate.getInstance();

     this.tiepdon.setTiepdonCum(IConstantsRes.CUM_TIEP_DON);

     this.tiepdon.getBenhnhanMa().setDmgtMaso((DmGioiTinh)delegate.findByMa(this.gioi, "DmGioiTinh", "dmgtMa"));
     if (this.tiepdon.getBenhnhanMa().getDantocMa().getDmdantocMa() != null) {
       this.tiepdon.getBenhnhanMa().setDantocMa((DmDanToc)delegate.findByMa(this.tiepdon.getBenhnhanMa().getDantocMa().getDmdantocMa(), "DmDanToc", "dmdantocMa"));
     } else {
       this.tiepdon.getBenhnhanMa().setDantocMa(null);
     }
     if (this.tiepdon.getBenhnhanMa().getTinhMa().getDmtinhMa() != null) {
       this.tiepdon.getBenhnhanMa().setTinhMa((DmTinh)delegate.findByMa(this.tiepdon.getBenhnhanMa().getTinhMa().getDmtinhMa(), "DmTinh", "dmtinhMa"));
     } else {
       this.tiepdon.getBenhnhanMa().setTinhMa(null);
     }
     if (this.tiepdon.getBenhnhanMa().getHuyenMa().getDmhuyenMa() != null) {
       this.tiepdon.getBenhnhanMa().setHuyenMa((DmHuyen)delegate.findByMa(this.tiepdon.getBenhnhanMa().getHuyenMa().getDmhuyenMa(), "DmHuyen", "dmhuyenMa"));
     } else {
       this.tiepdon.getBenhnhanMa().setHuyenMa(null);
     }
     if (this.tiepdon.getBenhnhanMa().getXaMa().getDmxaMa() != null) {
       this.tiepdon.getBenhnhanMa().setXaMa((DmXa)delegate.findByMa(this.tiepdon.getBenhnhanMa().getXaMa().getDmxaMa(), "DmXa", "dmxaMa"));
     } else {
       this.tiepdon.getBenhnhanMa().setXaMa(null);
     }
     this.tiepdon.getBenhnhanMa().setBenhnhanNghe(null);
     if (this.tiepdon.getDoituongMa().getDmdoituongMa() != null) {
       this.tiepdon.setDoituongMa((DmDoiTuong)delegate.findByMa(this.tiepdon.getDoituongMa().getDmdoituongMa(), "DmDoiTuong", "dmdoituongMa"));
     } else {
       this.tiepdon.setDoituongMa(null);
     }
     if (this.tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null) {
       this.tiepdon.setTiepdonBankham((DtDmBanKham)delegate.findByMa(this.tiepdon.getTiepdonBankham().getDtdmbankhamMa(), "DtDmBanKham", "dtdmbankhamMa"));
     } else {
       this.tiepdon.setTiepdonBankham(null);
     }
     this.tiepdon.setTiepdonBacsi(null);
     if (this.tiepdon.getTinhbhytMa().getDmtinhBHYT() != null) {
       this.tiepdon.setTinhbhytMa((DmTinh)delegate.findByMaSo(this.tiepdon.getTinhbhytMa().getDmtinhMaso(), "DmTinh", "dmtinhMaso"));
     } else {
       this.tiepdon.setTinhbhytMa(null);
     }
     if (this.tiepdon.getKhoibhytMa().getDtdmkhoibhytMa() != null) {
       this.tiepdon.setKhoibhytMa((DtDmKhoiBhyt)delegate.findByMa(this.tiepdon.getKhoibhytMa().getDtdmkhoibhytMa(), "DtDmKhoiBhyt", "dtdmkhoibhytMa"));
     } else {
       this.tiepdon.setKhoibhytMa(null);
     }
     if (this.tiepdon.getKcbbhytMa().getDmbenhvienMa() != null) {
       this.tiepdon.setKcbbhytMa((DmBenhVien)delegate.findByMa(this.tiepdon.getKcbbhytMa().getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa"));
     } else {
       this.tiepdon.setKcbbhytMa(null);
     }
     this.tiepdon.setDiadiemMa(null);
     if ((this.gioTD != null) && (!this.gioTD.equals("")) && (this.ngayTD != null) && (!this.ngayTD.equals("")))
     {
       Calendar ngaygiotiepdon = Utils.getDBDate(this.gioTD, this.ngayTD);
       if (ngaygiotiepdon != null) {
         this.tiepdon.setTiepdonNgaygio(ngaygiotiepdon.getTime());
       } else {
         this.tiepdon.setTiepdonNgaygio(new Date());
       }
     }
     DtDmNhanVien nhanvien = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
     this.tiepdon.setTiepdonNhanviencn(nhanvien);
     this.tiepdon.setTiepdonNgaygiocn(new Date());
     if (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH"))
     {
       Short tuyenBH = new Short("1");
       if ((this.tiepdon.getTiepdonCoGiayGioiThieu() != null) && (this.tiepdon.getTiepdonCoGiayGioiThieu().booleanValue())) {
         tuyenBH = new Short("1");
       } else if (this.tiepdon.getKcbbhytMa().getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN)) {
         tuyenBH = new Short("1");
       } else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH"))
       {
         if (this.tiepdon.getKcbbhytMa().getDmbenhvienMa().startsWith(IConstantsRes.TINH_BHYT_DEFAULT)) {
           tuyenBH = new Short("2");
         } else {
           tuyenBH = new Short("3");
         }
       }
       else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN"))
       {
         if ((this.tiepdon.getKcbbhytMa().getDmhuyenMaso() != null) && (this.tiepdon.getKcbbhytMa().getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI))) {
           tuyenBH = new Short("2");
         } else {
           tuyenBH = new Short("3");
         }
       }
       else {
         tuyenBH = new Short("3");
       }
       this.tiepdon.setTiepdonTuyen(tuyenBH);
     }
     try
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       this.tiepdon.getBenhnhanMa().setBenhnhanNgaysinh(null);
       if (this.ngaysinh.trim().length() > 0) {
         this.tiepdon.getBenhnhanMa().setBenhnhanNgaysinh(df.parse(this.ngaysinh));
       }
       this.tiepdon.setTiepdonGiatri1(null);
       if (this.giatri1.trim().length() > 0) {
         this.tiepdon.setTiepdonGiatri1(df.parse(this.giatri1));
       }
       this.tiepdon.setTiepdonGiatri2(null);
       if (this.giatri2.trim().length() > 0) {
         this.tiepdon.setTiepdonGiatri2(df.parse(this.giatri2));
       }
       this.tiepdon.setTiepdonGiatri3(null);
       if (this.giatri3.trim().length() > 0) {
         this.tiepdon.setTiepdonGiatri3(df.parse(this.giatri3));
       }
       this.tiepdon.setTiepdonGiatri4(null);
       if (this.giatri4.trim().length() > 0) {
         this.tiepdon.setTiepdonGiatri4(df.parse(this.giatri4));
       }
       this.tiepdon.setTiepdonMoc1(null);
       if (this.moc1.trim().length() > 0) {
         this.tiepdon.setTiepdonMoc1(df.parse(this.moc1));
       }
       this.tiepdon.setTiepdonMoc2(null);
       if (this.moc1.trim().length() > 0) {
         this.tiepdon.setTiepdonMoc2(df.parse(this.moc2));
       }
       this.tiepdon.setTiepdonMoc3(null);
       if (this.moc3.trim().length() > 0) {
         this.tiepdon.setTiepdonMoc3(df.parse(this.moc3));
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     ClsKhamDelegate clsKhamDel;
     if ((!this.tiepdon.getTiepdonMa().equals("")) && (!this.doituongHientai.equals("")) && (!this.doituongHientai.equals(this.tiepdon.getDoituongMa().getDmdoituongMa())))
     {
       clsKhamDel = ClsKhamDelegate.getInstance();
       List<ClsKham> listClsKham = clsKhamDel.findByTiepdonma(this.tiepdon.getTiepdonMa());
       for (ClsKham cls : listClsKham) {
         if (cls.getClskhamLoai().equalsIgnoreCase("KH")) {
           try
           {
             if (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH"))
             {
               SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
               Date ngayChiDinhCls = formatter.parse(formatter.format(cls.getClskhamNgaygio()));
               Date ngayBatDauBh = this.tiepdon.getTiepdonGiatri3() == null ? this.tiepdon.getTiepdonGiatri1() : this.tiepdon.getTiepdonGiatri3();
               Date ngayHetHanBh = this.tiepdon.getTiepdonGiatri4() == null ? this.tiepdon.getTiepdonGiatri2() : this.tiepdon.getTiepdonGiatri4();
               if (ngayBatDauBh != null) {
                 ngayBatDauBh = formatter.parse(formatter.format(ngayBatDauBh));
               }
               if (ngayHetHanBh != null) {
                 ngayHetHanBh = formatter.parse(formatter.format(ngayHetHanBh));
               }
               if (cls.getClskhamMien().booleanValue()) {
                 cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
               } else if (((cls.getClskhamNdm() != null) && (cls.getClskhamNdm().booleanValue() == true)) || (ngayBatDauBh == null) || (ngayHetHanBh == null) || (ngayChiDinhCls.before(ngayBatDauBh)) || (ngayChiDinhCls.after(ngayHetHanBh))) {
                 cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
               } else {
                 cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiabh());
               }
             }
             else if (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP"))
             {
               cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
             }
             else if (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP"))
             {
               if (cls.getClskhamMahang().getDtdmclsbgMien().booleanValue()) {
                 cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
               } else {
                 cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
               }
             }
             cls.setClskhamDatt(Boolean.valueOf(this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP")));
             clsKhamDel.edit(cls);
           }
           catch (Exception e) {}
         }
       }
     }
     RemoveUtil.removeAllNullFromTiepDon(this.tiepdon);
     String maTD = TiepDonDelegate.getInstance().createTiepDon(this.tiepdon);
     log.info("After createTiepDon, maTD = " + maTD);
     if (!"".equals(maTD))
     {
       this.tiepdon.setTiepdonMa(maTD);
       HsThtoank hsttk = new HsThtoank();
       hsttk.setTiepdonMa(this.tiepdon);
       tinhToanChoHSTKKham(this.tiepdon, hsttk);

       HsThtoankBackup hsBK = new HsThtoankBackup();

       HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();

       HsThtoankBackup hsBKTemp = hsBKDel.findBytiepdonMa(maTD, 0);
       if (this.tiepdon.getDoituongMa().getDmdoituongMa().equals("TP"))
       {
         hsBK.setHsthtoankBhxh(hsttk.getHsthtoankBhxh());
         hsBK.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());

         hsBK.setHsthtoankBienlai(this.tiepdon.getTiepdonBienlai());

         hsBK.setHsthtoankBntra(hsttk.getHsthtoankBntra());
         hsBK.setHsthtoankBovien(hsttk.getHsthtoankBovien());
         hsBK.setHsthtoankCdha(hsttk.getHsthtoankCdha());
         hsBK.setHsthtoankCls(hsttk.getHsthtoankCls());
         hsBK.setHsthtoankClsndm(hsttk.getHsthtoankClsndm());
         hsBK.setHsthtoankCongkham(hsttk.getHsthtoankCongkham());
         hsBK.setHsthtoankCpVc(hsttk.getHsthtoankCpvc());
         hsBK.setHsthtoankCum(hsttk.getHsthtoankCum());
         hsBK.setHsthtoankDatt(hsttk.getHsthtoankDatt());

         hsBK.setHsthtoankDienmien(hsttk.getHsthtoankDienmien());
         hsBK.setHsthtoankDm(hsttk.getHsthtoankDm());
         hsBK.setHsthtoankDvKtc(hsttk.getHsthtoankDvktc());
         hsBK.setHsthtoankDvktTt(hsttk.getHsthtoankDvkttt());
         hsBK.setHsthtoankHoanthu(hsttk.getHsthtoankHoanthu());
         hsBK.setHsthtoankHoanung(hsttk.getHsthtoankHoanung());
         hsBK.setHsthtoankKhongthu(hsttk.getHsthtoankKhongthu());

         hsBK.setHsthtoankKyhieu(this.tiepdon.getTiepdonKyhieu());

         hsBK.setHsthtoankLydott(hsttk.getHsthtoankLydott());

         hsBK.setHsthtoankMabenh(hsttk.getHsthtoankMabenh());
         hsBK.setHsthtoankMau(hsttk.getHsthtoankMau());
         hsBK.setHsthtoankMiendt(hsttk.getHsthtoankMiendt());
         hsBK.setHsthtoankMienmau(hsttk.getHsthtoankMienmau());
         hsBK.setHsthtoankMienphong(hsttk.getHsthtoankMienphong());
         hsBK.setHsthtoankMiente(hsttk.getHsthtoankMiente());
         hsBK.setHsthtoankMienthuoc(hsttk.getHsthtoankMienthuoc());
         hsBK.setHsthtoankMienthuoclao(hsttk.getHsthtoankMienthuoclao());
         hsBK.setHsthtoankNdm(hsttk.getHsthtoankNdm());
         hsBK.setHsthtoankNgansach(hsttk.getHsthtoankNgansach());
         hsBK.setHsthtoankNgayc(hsttk.getHsthtoankNgayc());
         hsBK.setHsthtoankNgayd(hsttk.getHsthtoankNgayd());
         hsBK.setHsthtoankNgaygiocn(hsttk.getHsthtoankNgaygiocn());
         hsBK.setHsthtoankNgaygiott(hsttk.getHsthtoankNgaygiott());
         hsBK.setHsthtoankNhanviencn(hsttk.getHsthtoankNhanviencn());
         hsBK.setHsthtoankPhauthuat(hsttk.getHsthtoankPhauthuat());
         hsBK.setHsthtoankPhauthuatndm(hsttk.getHsthtoankPhauthuatndm());
         hsBK.setHsthtoankPhong(hsttk.getHsthtoankPhong());
         hsBK.setHsthtoankPhongndm(hsttk.getHsthtoankPhongndm());

         hsBK.setHsthtoankQuyen(this.tiepdon.getTiepdonQuyen());

         hsBK.setHsthtoankSokhoa(hsttk.getHsthtoankSokhoa());
         hsBK.setHsthtoankTamung(hsttk.getHsthtoankTamung());
         hsBK.setHsthtoankThatthu(hsttk.getHsthtoankThatthu());
         hsBK.setHsthtoankThtoan(hsttk.getHsthtoankThtoan());
         hsBK.setHsthtoankThungan(hsttk.getHsthtoankThungan());
         hsBK.setHsthtoankThuoc(hsttk.getHsthtoankThuoc());
         hsBK.setHsthtoankThuocndm(hsttk.getHsthtoankThuocndm());
         hsBK.setHsthtoankTongchi(hsttk.getHsthtoankTongchi());
         hsBK.setHsthtoankTylebh(hsttk.getHsthtoankTylebh());
         hsBK.setHsthtoankVtth(hsttk.getHsthtoankVtth());
         hsBK.setHsthtoankVtthndm(hsttk.getHsthtoankVtthndm());
         hsBK.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam());
         hsBK.setHsthtoankXntdcn(hsttk.getHsthtoankXntdcn());
         hsBK.setTiepdonMa(hsttk.getTiepdonMa());
         hsBK.setHsthtoankNgaygiott(this.tiepdon.getTiepdonNgaygio());
         if (hsBKTemp != null)
         {
           hsBK.setHsthtoankMa(hsBKTemp.getHsthtoankMa());
           hsBKDel.edit(hsBK);
         }
         else
         {
           hsBKDel.create(hsBK);
         }
       }
       else if (hsBKTemp != null)
       {
         hsBKDel.remove(hsBKTemp);
       }
     }
     if (!"".equals(maTD))
     {
       FacesMessages.instance().add(IConstantsRes.dangkykb_thanhcong, new Object[] { this.tiepdon.getBenhnhanMa().getBenhnhanHoten(), maTD });

       resetValue();
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.listBN = new ArrayList();
       this.showListBn = false;
     }
     log.info("End ghinhan");
   }

   private boolean checkTrungMaBnInListBn(String maBN, List<BenhNhanTrungTheBhytDTO> listBN)
   {
     if (listBN == null) {
       return false;
     }
     if (listBN.size() < 1) {
       return false;
     }
     for (BenhNhanTrungTheBhytDTO eachBN : listBN) {
       if (eachBN.getBnMa().equals(maBN)) {
         return true;
       }
     }
     return false;
   }

   private void checkBacsiThamkham(String maTD)
   {
     ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
     List<ThamKham> listTk = tkDel.findAllByMaTiepDon(maTD);
     this.maBacSiThamKham = "";
     if ((listTk != null) && (listTk.size() > 1)) {
       for (ThamKham eachTk : listTk)
       {
         if (this.maBacSiThamKham.trim().length() != 0) {
           break;
         }
         this.maBacSiThamKham = ("" + eachTk.getThamkhamBacsi().getDtdmnhanvienMaso());
       }
     }
   }

   private void checkDKTrongNgay(String sothe, String maTD, TiepDonDelegate tdDel, SimpleDateFormat df, boolean chopheptrungsothe)
   {
     this.hid_maTD = "";
     this.hid_maBN = "";
     this.hid_mess = "";
     try
     {
       Date dNgaytiepdon = df.parse(this.ngayTD);
       df = new SimpleDateFormat(Utils.FORMAT_DATE_TIME_HOUR_FIRST);
       String sNgaygio = IConstantsRes.NGAY_GIO;
       String sBankham = IConstantsRes.BAN_KHAM.toUpperCase();
       if (sBankham.length() > 2) {
         sBankham = sBankham.substring(0, 1) + sBankham.substring(1).toLowerCase() + ": ";
       }
       if (!chopheptrungsothe)
       {
         TiepDon tdTmp = tdDel.testBHYTTrungTrongNgay(sothe, dNgaytiepdon);
         if (tdTmp != null)
         {
           this.hid_maTD = tdTmp.getTiepdonMa();
           this.hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();

           this.hid_mess = (IConstantsRes.DANGKY_BHYT_TRONGNGAY + this.hid_maTD + ". " + sNgaygio + df.format(tdTmp.getTiepdonNgaygio()) + ". " + sBankham + " " + tdTmp.getTiepdonBankham(true).getDtdmbankhamTen());
         }
         else
         {
           tdTmp = tdDel.getTiepDonWithSoTheBHYTLast(sothe);
           if (tdTmp != null)
           {
             this.hid_maTD = tdTmp.getTiepdonMa();
             this.hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();
             ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
             List<ThamKham> lstTK = tkDele.findAllByMaTiepDon(tdTmp.getTiepdonMa());
             String ngayTaiKham = "";
             Date dNgayTaiKham = null;
             if (lstTK != null) {
               for (ThamKham tk : lstTK)
               {
                 dNgayTaiKham = tk.getThamkhamNgaytaikham();
                 if (dNgayTaiKham != null)
                 {
                   ngayTaiKham = df.format(dNgayTaiKham);
                   break;
                 }
               }
             }
             if (dNgayTaiKham != null) {
               if (dNgayTaiKham.after(dNgaytiepdon)) {
                 this.hid_mess = (IConstantsRes.DANGKY_CHUATOINGAY_TAIKHAM + ngayTaiKham);
               }
             }
           }
         }
       }
       else
       {
         TiepDon tdTmp = tdDel.find(maTD);
         if (tdTmp != null)
         {
           Date dNgayDaTiepdon = Utils.removeHourFromDate(tdTmp.getTiepdonNgaygio());
           if (dNgayDaTiepdon != null)
           {
             this.hid_maTD = tdTmp.getTiepdonMa();
             this.hid_maBN = tdTmp.getBenhnhanMa().getBenhnhanMa();
             if (dNgaytiepdon.compareTo(dNgayDaTiepdon) == 0)
             {
               this.hid_mess = (IConstantsRes.DANGKY_BHYT_TRONGNGAY + this.hid_maTD + ". " + sNgaygio + df.format(tdTmp.getTiepdonNgaygio()) + ". " + sBankham + " " + tdTmp.getTiepdonBankham(true).getDtdmbankhamTen());
             }
             else
             {
               ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
               List<ThamKham> lstTK = tkDele.findAllByMaTiepDon(tdTmp.getTiepdonMa());
               String ngayTaiKham = "";
               Date dNgayTaiKham = null;
               if (lstTK != null) {
                 for (ThamKham tk : lstTK)
                 {
                   dNgayTaiKham = tk.getThamkhamNgaytaikham();
                   if (dNgayTaiKham != null)
                   {
                     ngayTaiKham = df.format(dNgayTaiKham);
                     break;
                   }
                 }
               }
               if (dNgayTaiKham != null) {
                 if (dNgayTaiKham.after(dNgaytiepdon)) {
                   this.hid_mess = (IConstantsRes.DANGKY_CHUATOINGAY_TAIKHAM + ngayTaiKham);
                 }
               }
             }
           }
         }
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   private void checkBHYTDieuTriNoiTru(String soTheBHYT)
   {
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

     List<Hsba> listHsba = hsbaDelegate.findBySoTheBHYT(soTheBHYT);
     if ((listHsba != null) && (listHsba.size() > 0)) {
       for (Hsba eachHsba : listHsba) {
         if (eachHsba.getHsbaNgaygiorav() == null)
         {
           this.hid_mess = (this.hid_mess + " - " + IConstantsRes.TIEPDON_YTBH_DANGDIEUTRI_NOITRU);
           break;
         }
       }
     }
   }

   public void checkSoTheBHYT()
   {
     log.info("Begin checkSoTheBHYT(), so the = " + this.tiepdon.getTiepdonSothebh() + ", ngayTD  = " + this.ngayTD);
     String sothe = this.tiepdon.getTiepdonSothebh().trim();
     if (sothe.equals("")) {
       return;
     }
     this.showListBn = false;
     FacesMessages.instance().clear();
     TiepDonDelegate tdDel = TiepDonDelegate.getInstance();
     List<TiepDon> listTD = tdDel.findBySoTheBHYT(sothe);
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     log.info("listTD.size() = " + listTD.size());
     this.hid_maTD = "";
     this.hid_maBN = "";
     this.hid_mess = "";
     if ((listTD != null) && (listTD.size() > 0))
     {
       if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO"))
       {
         TiepDon td = (TiepDon)listTD.get(0);
         if (td.getBenhnhanMa() != null)
         {
           this.gioi = ("" + td.getBenhnhanMa().getDmgtMaso().getDmgtMa());
           if (td.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
             this.ngaysinh = df.format(td.getBenhnhanMa().getBenhnhanNgaysinh());
           } else {
             this.ngaysinh = "";
           }
         }
         this.giatri1 = Utils.reFactorString(td.getTiepdonGiatri1());
         this.giatri2 = Utils.reFactorString(td.getTiepdonGiatri2());
         this.giatri3 = Utils.reFactorString(td.getTiepdonGiatri3());
         this.giatri4 = Utils.reFactorString(td.getTiepdonGiatri4());
         this.moc1 = Utils.reFactorString(td.getTiepdonMoc1());
         this.moc2 = Utils.reFactorString(td.getTiepdonMoc2());
         this.moc3 = Utils.reFactorString(td.getTiepdonMoc3());

         BenhNhan benhnhan = new BenhNhan();
         benhnhan = td.getBenhnhanMa();
         SetInforUtil.setInforIfNullForBN(benhnhan);

         this.tiepdon = new TiepDon();
         this.tiepdon.setDoituongMa(td.getDoituongMa());
         this.tiepdon.setKhoibhytMa(td.getKhoibhytMa());
         this.tiepdon.setTinhbhytMa(td.getTinhbhytMa());
         this.tiepdon.setKcbbhytMa(td.getKcbbhytMa());
         this.tiepdon.setTiepdonMacoquan(td.getTiepdonMacoquan());
         SetInforUtil.setInforIfNullForTiepDon(this.tiepdon);

         this.tiepdon.setBenhnhanMa(benhnhan);



         checkDKTrongNgay(sothe, "", tdDel, df, false);
         checkBHYTDieuTriNoiTru(sothe);
       }
       else
       {
         if (this.listBN == null) {
           this.listBN = new ArrayList();
         } else {
           this.listBN.clear();
         }
         String strNgayHientai = df.format(new Date());
         for (TiepDon eachTD : listTD) {
           if (eachTD.getBenhnhanMa() != null) {
             if (!checkTrungMaBnInListBn(eachTD.getBenhnhanMa().getBenhnhanMa(), this.listBN))
             {
               BenhNhanTrungTheBhytDTO bn = new BenhNhanTrungTheBhytDTO();
               bn.setTiepdonMa(eachTD.getTiepdonMa());
               bn.setBnMa(eachTD.getBenhnhanMa().getBenhnhanMa());
               bn.setBnHoTen(eachTD.getBenhnhanMa().getBenhnhanHoten());
               bn.setBnGioiTinh(eachTD.getBenhnhanMa().getDmgtMaso().getDmgtTen());
               bn.setBnNamSinh(eachTD.getBenhnhanMa().getBenhnhanNamsinh());
               bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());
               bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : df.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" : df.format(eachTD.getTiepdonGiatri2())));
               bn.setNgayTiepdon(eachTD.getTiepdonNgaygio() == null ? "" : df.format(eachTD.getTiepdonNgaygio()));
               bn.setTiepdonTrongNgay(bn.getNgayTiepdon().equals(strNgayHientai));
               this.listBN.add(bn);
             }
           }
         }
         if (this.listBN.size() > 0) {
           this.showListBn = true;
         }
       }
       FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, new Object[] { sothe });
     }
     else if ((!this.tiepdon.getTiepdonMa().equals("")) || (!this.maTD_online.equals("")))
     {
       if (!this.tiepdon.getTiepdonMa().equals("")) {
         checkBacsiThamkham(this.tiepdon.getTiepdonMa());
       }
     }
     else
     {
       DmDoiTuong dmDT = this.tiepdon.getDoituongMa();
       DtDmKhoiBhyt khoiBHYT = this.tiepdon.getKhoibhytMa();
       resetValue();
       this.tiepdon.setDoituongMa(dmDT);
       this.tiepdon.setKhoibhytMa(khoiBHYT);
     }
   }

   public void layThongTinBnCu(int index)
   {
     log.info("Begin layThongTinBnCu, index = " + index + ", ngayTD = " + this.ngayTD);
     TiepDonDelegate tdDel = TiepDonDelegate.getInstance();
     TiepDon td = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(((BenhNhanTrungTheBhytDTO)this.listBN.get(index)).getTiepdonMa());
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     if (td != null) {
       checkDKTrongNgay(td.getTiepdonSothebh(), td.getTiepdonMa(), tdDel, df, true);
     }
     this.gioTD = Utils.getGioPhut(new Date());
     if (td.getBenhnhanMa() != null)
     {
       this.gioi = ("" + td.getBenhnhanMa().getDmgtMaso().getDmgtMa());
       if (td.getBenhnhanMa().getBenhnhanNgaysinh() != null) {
         this.ngaysinh = df.format(td.getBenhnhanMa().getBenhnhanNgaysinh());
       } else {
         this.ngaysinh = "";
       }
     }
     this.giatri1 = Utils.reFactorString(td.getTiepdonGiatri1());
     this.giatri2 = Utils.reFactorString(td.getTiepdonGiatri2());
     this.giatri3 = Utils.reFactorString(td.getTiepdonGiatri3());
     this.giatri4 = Utils.reFactorString(td.getTiepdonGiatri4());
     this.moc1 = Utils.reFactorString(td.getTiepdonMoc1());
     this.moc2 = Utils.reFactorString(td.getTiepdonMoc2());
     this.moc3 = Utils.reFactorString(td.getTiepdonMoc3());

     BenhNhan benhnhan = new BenhNhan();
     benhnhan = td.getBenhnhanMa();
     SetInforUtil.setInforIfNullForBN(benhnhan);

     this.tiepdon = new TiepDon();
     this.tiepdon.setTiepdonSothebh(td.getTiepdonSothebh());
     this.tiepdon.setDoituongMa(td.getDoituongMa());
     this.tiepdon.setKhoibhytMa(td.getKhoibhytMa());
     this.tiepdon.setTinhbhytMa(td.getTinhbhytMa());
     this.tiepdon.setKcbbhytMa(td.getKcbbhytMa());
     this.tiepdon.setTiepdonMacoquan(td.getTiepdonMacoquan());
     SetInforUtil.setInforIfNullForTiepDon(this.tiepdon);

     this.tiepdon.setBenhnhanMa(benhnhan);

     FacesMessages.instance().clear();
     this.showListBn = false;
   }

   public void nhapBnMoi()
   {
     FacesMessages.instance().clear();
     this.tiepdon.getBenhnhanMa().setBenhnhanMa("");
     this.tiepdon.setTiepdonMa("");
     this.showListBn = false;
   }

   public void huyKham()
   {
     log.info("Begin huyKham(), ma tiep don = " + this.tiepdon.getTiepdonMa());
     if (this.tiepdon.getTiepdonMa().trim().length() > 0)
     {
       ClsKhamDelegate ClsKhamDel = ClsKhamDelegate.getInstance();
       List<ClsKham> list = ClsKhamDel.findByTiepdonMaVaLoaiClsKham(this.tiepdon.getTiepdonMa());
       if ((list != null) && (list.size() > 0))
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
         return;
       }
       HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
       Hsba objHSBA = hsbaDel.findByTiepDonMa(this.tiepdon.getTiepdonMa());
       if (objHSBA != null)
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_HSBA, new Object[0]);
         return;
       }
       ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
       List<ThuocPhongKham> lstTPK = tpkDel.findByMaTiepDon(this.tiepdon.getTiepdonMa(), "1");
       if ((lstTPK != null) && (lstTPK.size() > 0))
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
         return;
       }
       lstTPK = tpkDel.findByMaTiepDon(this.tiepdon.getTiepdonMa(), "2");
       if ((lstTPK != null) && (lstTPK.size() > 0))
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
         return;
       }
       lstTPK = tpkDel.findByMaTiepDon(this.tiepdon.getTiepdonMa(), "3");
       if ((lstTPK != null) && (lstTPK.size() > 0))
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
         return;
       }
       TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
       String maBN = this.tiepdon.getBenhnhanMa().getBenhnhanMa();
       String tdID = tdDelegate.delHuyKham(this.tiepdon);
       if (tdID.trim().length() > 0)
       {
         FacesMessages.instance().add(IConstantsRes.HUY_KHAM_THANH_CONG, new Object[] { maBN, tdID });
         resetValue();
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE, new Object[0]);
     }
   }

   public TiepDon getTiepdon()
   {
     return this.tiepdon;
   }

   public void setTiepdon(TiepDon tiepdon)
   {
     this.tiepdon = tiepdon;
   }

   public String getGioTD()
   {
     return this.gioTD;
   }

   public void setGioTD(String gioTD)
   {
     this.gioTD = gioTD;
   }

   public String getNgayTD()
   {
     return this.ngayTD;
   }

   public void setNgayTD(String ngayTD)
   {
     this.ngayTD = ngayTD;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getGiatri1()
   {
     return this.giatri1;
   }

   public void setGiatri1(String giatri1)
   {
     this.giatri1 = giatri1;
   }

   public String getGiatri2()
   {
     return this.giatri2;
   }

   public void setGiatri2(String giatri2)
   {
     this.giatri2 = giatri2;
   }

   public String getGiatri3()
   {
     return this.giatri3;
   }

   public void setGiatri3(String giatri3)
   {
     this.giatri3 = giatri3;
   }

   public String getGiatri4()
   {
     return this.giatri4;
   }

   public void setGiatri4(String giatri4)
   {
     this.giatri4 = giatri4;
   }

   public String getMoc1()
   {
     return this.moc1;
   }

   public void setMoc1(String moc1)
   {
     this.moc1 = moc1;
   }

   public String getMoc2()
   {
     return this.moc2;
   }

   public void setMoc2(String moc2)
   {
     this.moc2 = moc2;
   }

   public String getMoc3()
   {
     return this.moc3;
   }

   public void setMoc3(String moc3)
   {
     this.moc3 = moc3;
   }

   public String getNgaysinh()
   {
     return this.ngaysinh;
   }

   public void setNgaysinh(String ngaysinh)
   {
     this.ngaysinh = ngaysinh;
   }

   public String getMaBacSiThamKham()
   {
     return this.maBacSiThamKham;
   }

   public void setMaBacSiThamKham(String maBacSiThamKham)
   {
     this.maBacSiThamKham = maBacSiThamKham;
   }

   public String getHosoDaTT()
   {
     return this.hosoDaTT;
   }

   public void setHosoDaTT(String hosoDaTT)
   {
     this.hosoDaTT = hosoDaTT;
   }

   public List<BenhNhanTrungTheBhytDTO> getListBN()
   {
     return this.listBN;
   }

   public void setListBN(List<BenhNhanTrungTheBhytDTO> listBN)
   {
     this.listBN = listBN;
   }

   public boolean isShowListBn()
   {
     return this.showListBn;
   }

   public void setShowListBn(boolean showListBn)
   {
     this.showListBn = showListBn;
   }

   public boolean isLockDoituong()
   {
     return this.lockDoituong;
   }

   public void setLockDoituong(boolean lockDoituong)
   {
     this.lockDoituong = lockDoituong;
   }

   public String getDoituongHientai()
   {
     return this.doituongHientai;
   }

   public void setDoituongHientai(String doituongHientai)
   {
     this.doituongHientai = doituongHientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getMaTD_online()
   {
     return this.maTD_online;
   }

   public void setMaTD_online(String maTDOnline)
   {
     this.maTD_online = maTDOnline;
   }

   public String getNgaygioDK()
   {
     return this.ngaygioDK;
   }

   public void setNgaygioDK(String ngaygioDK)
   {
     this.ngaygioDK = ngaygioDK;
   }

   public String getListMaTinhBhyt()
   {
     return this.listMaTinhBhyt;
   }

   public void setListMaTinhBhyt(String listMaTinhBhyt)
   {
     this.listMaTinhBhyt = listMaTinhBhyt;
   }

   public String getHid_maTD()
   {
     return this.hid_maTD;
   }

   public void setHid_maTD(String hidMaTD)
   {
     this.hid_maTD = hidMaTD;
   }

   public String getHid_maBN()
   {
     return this.hid_maBN;
   }

   public void setHid_maBN(String hidMaBN)
   {
     this.hid_maBN = hidMaBN;
   }

   public String getHid_mess()
   {
     return this.hid_mess;
   }

   public void setHid_mess(String hidMess)
   {
     this.hid_mess = hidMess;
   }

   public void search_maTDonline()
   {
     String maTD_online_temp = this.maTD_online;
     resetValue();
     this.maTD_online = maTD_online_temp;
     if (this.maTD_online.trim().length() > 0)
     {
       String tenTD_online = "";
       String tuoi_online = "";
       String donvituoi = "";
       String diachi_online = "";
       String maBH_online = "";
       String ngayTDDK = "";
       String gioTDDK = "";
       if (!urlMySQL.startsWith("jdbc"))
       {
         log.info("### Khong co server Dang ky online");
         resetValue();
         return;
       }
       if (this.maTD_online.length() < 5)
       {
         int add_zero = 5 - this.maTD_online.length();
         for (int i = 0; i < add_zero; i++) {
           this.maTD_online = ("0" + this.maTD_online);
         }
       }
       String query = "SELECT * FROM healthonline_db.regonline r WHERE r.regno LIKE '%" + this.maTD_online + "'" + " order by r.regid desc";
       try
       {
         try
         {
           Class.forName("com.mysql.jdbc.Driver");
         }
         catch (ClassNotFoundException e)
         {
           e.printStackTrace();
         }
         this.conn = DriverManager.getConnection(urlMySQL, userName, password);
         try
         {
           PreparedStatement stmt = this.conn.prepareStatement(query);
           ResultSet rs = stmt.executeQuery();
           if (rs.first())
           {
             tenTD_online = rs.getString("pname");
             this.maTD_online = rs.getString("regno");
             tuoi_online = rs.getString("page");
             donvituoi = rs.getString("agetype");
             diachi_online = rs.getString("paddress");
             maBH_online = rs.getString("pcardno");
             this.gioi = rs.getString("psex");
             ngayTDDK = rs.getString("regdate");
             gioTDDK = rs.getString("regtime");

             log.info("### SQL Query OK !! ### Ma: " + this.maTD_online + " <> Tuoi: " + tuoi_online + " <> DV Tuoi: " + donvituoi + " <> Dia chi: " + diachi_online + " <> Ma BH: " + maBH_online + " <> Ngay DK: " + ngayTDDK + " <> Gio DK: " + gioTDDK + " <> Gioi tinh: " + this.gioi);
           }
           else
           {
             FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, new Object[0]);
             resetValue();
             log.info("### SQL Query OK !! ### No result !! ");
           }
         }
         catch (SQLException er)
         {
           resetValue();
           log.info("### SQL Querry Error !! ### " + er.getMessage());
         }
       }
       catch (SQLException er)
       {
         resetValue();
         log.info("### SQL Connection Error !! ### " + er.getMessage());
       }
       if (this.conn != null) {
         try
         {
           this.conn.close();
         }
         catch (SQLException e)
         {
           log.info("### SQL Connection Close Error !! ### " + e.getMessage());
         }
       }
       if (donvituoi.equals("DAY")) {
         donvituoi = "3";
       } else if (donvituoi.equals("MONTH")) {
         donvituoi = "2";
       } else {
         donvituoi = "1";
       }
       this.tiepdon.getBenhnhanMa().setBenhnhanDonvituoi(new Short(donvituoi));
       if ((tenTD_online != null) && (!tenTD_online.equals(""))) {
         this.tiepdon.getBenhnhanMa().setBenhnhanHoten(tenTD_online.toUpperCase());
       }
       if ((tuoi_online != null) && (!tuoi_online.equals(""))) {
         this.tiepdon.getBenhnhanMa().setBenhnhanTuoi(Integer.valueOf(Integer.parseInt(tuoi_online)));
       }
       if ((diachi_online != null) && (!diachi_online.equals(""))) {
         this.tiepdon.getBenhnhanMa().setBenhnhanDiachi(diachi_online);
       }
       if ((this.gioi == null) || (this.gioi.equals(""))) {
         this.gioi = "1";
       } else {
         this.gioi = "0";
       }
       if ((maBH_online != null) && (!"".equals(maBH_online)))
       {
         this.tiepdon.setTiepdonSothebh(maBH_online.toUpperCase());
         this.tiepdon.getDoituongMa().setDmdoituongMa("BH");
       }
       else
       {
         this.tiepdon.getDoituongMa().setDmdoituongMa("TP");
       }
       if ((ngayTDDK != null) && (!"".equals(ngayTDDK)))
       {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat df_view = new SimpleDateFormat("dd/MM/yyyy");
         String today = df.format(new Date());
         try
         {
           Date ngayDK = df.parse(ngayTDDK);
           Date ngayHT = df.parse(today);
           this.ngaygioDK = df_view.format(ngayDK);
           if (ngayDK.before(ngayHT)) {
             FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENTRE, new Object[] { this.ngaygioDK });
           }
           if (ngayDK.after(ngayHT)) {
             FacesMessages.instance().add(IConstantsRes.DANGKY_ONLINE_DENSOM, new Object[] { this.ngaygioDK });
           }
           if ((gioTDDK != null) && (!"".equals(gioTDDK))) {
             this.ngaygioDK = (gioTDDK + ":00 " + IConstantsRes.NGAY + " " + this.ngaygioDK);
           }
         }
         catch (ParseException e)
         {
           e.printStackTrace();
         }
       }
     }
   }

   public boolean checkKhamTrungNgay(String ngayTD)
   {
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     try
     {
       Date ngayTiepDon = df.parse(ngayTD);
       Date ngayHienTai = df.parse(df.format(new Date()));
       return ngayTiepDon.equals(ngayHienTai);
     }
     catch (Exception e) {}
     return false;
   }

   public String xemLichSuKB(String maTD, String maBN)
   {
     log.info("xemLichSuKB, maTD = " + maTD + ", maBN = " + maBN);
     this.tiepDon_ma = maTD;
     this.benhNhan_ma = maBN;
     return "/B1_Tiepdon/B115_Lichsubenhnhan.xhtml";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.B110_DangKyKhamBenhAction

 * JD-Core Version:    0.7.0.1

 */