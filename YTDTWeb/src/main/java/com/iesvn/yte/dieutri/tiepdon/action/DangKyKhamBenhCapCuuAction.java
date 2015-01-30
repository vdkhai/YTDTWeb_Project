 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDiaDiem;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
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
 @Name("B131_Dangkykhambenhcapcuu")
 @Synchronized(timeout=6000000L)
 public class DangKyKhamBenhCapCuuAction
   implements Serializable
 {
   private static final long serialVersionUID = 5156076455688325728L;
   private static Logger log = Logger.getLogger(DangKyKhamBenhCapCuuAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private TiepDon tiepdon;
   private String gioTiepDon;
   private String ngayTiepDon;
   private String gioi;
   private String ngaysinh;
   private String bhytGiatri1;
   private String bhytGiatri2;
   private String bhytGiatri3;
   private String bhytGiatri4;
   private String bhytMoc1;
   private String bhytMoc2;
   private String bhytMoc3;
   private String donvituoi;
   private String nofound;
   private List<BenhNhanTrungTheBhytDTO> listBN;
   private boolean showListBn;
   private boolean lockDoituong;
   private boolean lockBanKham;
   private String doituongHientai;
   private String ngayhientai = "";
   private String listMaTinhBhyt;
   private String hosoDaTT = "false";
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strDKyCapCuu;

   @Factory("strDKyCapCuu")
   public void init()
   {
     resetValue();
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.tiepdon = new TiepDon();

     String phongkhamccDefault = IConstantsRes.PHONGKHAMCC_DEFAULT;
     if ((phongkhamccDefault != null) && (!phongkhamccDefault.equals(""))) {
       this.tiepdon.getTiepdonBankham(true).setDtdmbankhamMa(phongkhamccDefault);
     }
     this.gioTiepDon = Utils.getGioPhut(new Date());
     this.ngayTiepDon = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     String donvituoiDefault = IConstantsRes.DON_VI_TUOI_DEFAULT;
     if ((donvituoiDefault != null) && (!donvituoiDefault.equals(""))) {
       this.donvituoi = donvituoiDefault;
     }
     this.bhytGiatri1 = "";
     this.bhytGiatri2 = "";
     this.bhytGiatri3 = "";
     this.bhytGiatri4 = "";
     this.bhytMoc1 = "";
     this.bhytMoc2 = "";
     this.bhytMoc3 = "";

     this.gioi = "true";

     this.nofound = "false";
     this.listBN = new ArrayList();
     this.showListBn = false;
     this.lockDoituong = false;
     this.lockBanKham = false;
     this.doituongHientai = "";
     this.ngayhientai = Utils.getCurrentDate();
     this.strDKyCapCuu = "";

     List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     this.listMaTinhBhyt = "";
     for (DmTinh each : listDmTinh) {
       this.listMaTinhBhyt = (this.listMaTinhBhyt + each.getDmtinhBHYT() + ",");
     }
     this.hosoDaTT = "false";
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

   public void checkSoTheBHYT()
   {
     String sothe = this.tiepdon.getTiepdonSothebh().trim();
     if (sothe.equals("")) {
       return;
     }
     this.showListBn = false;
     List<TiepDon> listTD = TiepDonDelegate.getInstance().findBySoTheBHYT(sothe);
     if ((listTD != null) && (listTD.size() > 0))
     {
       if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO"))
       {
         this.tiepdon = ((TiepDon)listTD.get(0));
         displayTiepDon(this.tiepdon);
         this.tiepdon.setTiepdonMa(null);this.tiepdon.setTiepdonNgaygio(null);
         this.tiepdon.setTainanMa(null);this.tiepdon.setTiepdonDonvigoi(null);
         this.tiepdon.setTiepdonThutu(null);this.tiepdon.setTiepdonBntra(null);this.tiepdon.setTiepdonLoaikham(null);

         this.tiepdon.setTiepdonTylebh(null);this.tiepdon.setTiepdonBacsi(null);
         this.tiepdon.setTiepdonKyhieu(null);this.tiepdon.setTiepdonQuyen(null);this.tiepdon.setTiepdonBienlai(null);
         this.tiepdon.setTiepdonNhanviencn(null);this.tiepdon.setTiepdonThungan(null);

         this.tiepdon.setDmptgtnMaso(null);this.tiepdon.setNguyenhanMa(null);this.tiepdon.setDiadiemMa(null);
         this.tiepdon.setTiepdonRuoubia(null);this.tiepdon.setTiepdonLydovaov(null);
         this.tiepdon.setTiepdonMach(null);this.tiepdon.setTiepdonMachdoanbd(null);

         this.tiepdon.setTiepdonCodoimu(null);
         this.tiepdon.setTiepdonMuvo(null);
         this.tiepdon.setTiepdonKhongcaiday(null);
         this.tiepdon.setTiepdonKhongronguongoc(null);

         this.tiepdon.setTiepdonTrluong(null);this.tiepdon.setTiepdonChieucao(null);this.tiepdon.setTiepdonNhommau(null);
         this.tiepdon.setTiepdonNhietdo(null);this.tiepdon.setTiepdonNhiptho(null);this.tiepdon.setTiepdonHamax(null);
         this.tiepdon.setTiepdonHamin(null);this.tiepdon.setTiepdonRh(null);this.tiepdon.setTiepdonBaotin(null);

         this.tiepdon.setKetquaMa(null);this.tiepdon.setDieutriMa(null);this.tiepdon.setTiepdonNghe(null);
         this.tiepdon.setTiepdonChkhoa(null);this.tiepdon.setTiepdonChvien(null);this.tiepdon.setTiepdonBschuyen(null);
         this.tiepdon.setTiepdonLydochvi(null);this.tiepdon.setTiepdonTuvong(null);this.tiepdon.setTiepdonCum(null);

         this.tiepdon.setTiepdonSoTT(null);this.tiepdon.setTiepdonCoGiayGioiThieu(Boolean.valueOf(false));this.tiepdon.setTiepdonKhamDaLieu(Boolean.valueOf(false));

         DtDmBanKham bkcc = new DtDmBanKham(this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso());
         if (bkcc != null) {
           this.tiepdon.setTiepdonBankham(bkcc);
         } else {
           this.tiepdon.setTiepdonBankham(new DtDmBanKham(Integer.valueOf(Integer.parseInt(IConstantsRes.MA_BAN_KHAM_CAP_CUU_1))));
         }
       }
       else
       {
         if (this.listBN == null) {
           this.listBN = new ArrayList();
         } else {
           this.listBN.clear();
         }
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
               log.info("eachTD.getKcbbhytMa() = " + eachTD.getKcbbhytMa());
               bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());
               bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : formatter.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" : formatter.format(eachTD.getTiepdonGiatri2())));
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
   }

   public void nhapBnMoi()
   {
     FacesMessages.instance().clear();
     this.tiepdon.getBenhnhanMa().setBenhnhanMa("");
     this.showListBn = false;
     this.lockDoituong = false;
     this.lockBanKham = false;
   }

   public void layThongTinBnCu(int index)
   {
     log.info("Begin layThongTinBnCu, index = " + index);
     this.tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(((BenhNhanTrungTheBhytDTO)this.listBN.get(index)).getTiepdonMa());
     displayTiepDon(this.tiepdon);
     this.gioTiepDon = Utils.getGioPhut(new Date());
     this.ngayTiepDon = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     this.tiepdon.setTiepdonMa(null);this.tiepdon.setTiepdonNgaygio(null);
     this.tiepdon.setTainanMa(null);this.tiepdon.setTiepdonDonvigoi(null);
     this.tiepdon.setTiepdonThutu(null);this.tiepdon.setTiepdonBntra(null);this.tiepdon.setTiepdonLoaikham(null);

     this.tiepdon.setTiepdonTylebh(null);this.tiepdon.setTiepdonBacsi(null);
     this.tiepdon.setTiepdonKyhieu(null);this.tiepdon.setTiepdonQuyen(null);this.tiepdon.setTiepdonBienlai(null);
     this.tiepdon.setTiepdonNhanviencn(null);this.tiepdon.setTiepdonThungan(null);

     this.tiepdon.setDmptgtnMaso(null);this.tiepdon.setNguyenhanMa(null);this.tiepdon.setDiadiemMa(null);
     this.tiepdon.setTiepdonRuoubia(null);this.tiepdon.setTiepdonLydovaov(null);
     this.tiepdon.setTiepdonMach(null);this.tiepdon.setTiepdonMachdoanbd(null);

     this.tiepdon.setTiepdonCodoimu(null);
     this.tiepdon.setTiepdonMuvo(null);
     this.tiepdon.setTiepdonKhongcaiday(null);
     this.tiepdon.setTiepdonKhongronguongoc(null);

     this.tiepdon.setTiepdonTrluong(null);this.tiepdon.setTiepdonChieucao(null);this.tiepdon.setTiepdonNhommau(null);
     this.tiepdon.setTiepdonNhietdo(null);this.tiepdon.setTiepdonNhiptho(null);this.tiepdon.setTiepdonHamax(null);
     this.tiepdon.setTiepdonHamin(null);this.tiepdon.setTiepdonRh(null);this.tiepdon.setTiepdonBaotin(null);

     this.tiepdon.setKetquaMa(null);this.tiepdon.setDieutriMa(null);this.tiepdon.setTiepdonNghe(null);
     this.tiepdon.setTiepdonChkhoa(null);this.tiepdon.setTiepdonChvien(null);this.tiepdon.setTiepdonBschuyen(null);
     this.tiepdon.setTiepdonLydochvi(null);this.tiepdon.setTiepdonTuvong(null);this.tiepdon.setTiepdonCum(null);

     this.tiepdon.setTiepdonSoTT(null);this.tiepdon.setTiepdonCoGiayGioiThieu(Boolean.valueOf(false));this.tiepdon.setTiepdonKhamDaLieu(Boolean.valueOf(false));

     DtDmBanKham bkcc = new DtDmBanKham(this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMaso());
     if (bkcc != null) {
       this.tiepdon.setTiepdonBankham(bkcc);
     } else {
       this.tiepdon.setTiepdonBankham(new DtDmBanKham(Integer.valueOf(Integer.parseInt(IConstantsRes.MA_BAN_KHAM_CAP_CUU_1))));
     }
     this.nofound = "false";
     FacesMessages.instance().clear();
     this.showListBn = false;
     this.lockDoituong = false;
     this.lockBanKham = false;
   }

   private void displayTiepDon(TiepDon td)
   {
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     Date ngayGio = this.tiepdon.getTiepdonNgaygio();
     if (ngayGio != null)
     {
       this.ngayTiepDon = formatter.format(Long.valueOf(ngayGio.getTime()));
       this.gioTiepDon = Utils.getGioPhut(ngayGio);
     }
     Date dateBhytGiatri1 = this.tiepdon.getTiepdonGiatri1();
     if (dateBhytGiatri1 != null) {
       this.bhytGiatri1 = formatter.format(Long.valueOf(dateBhytGiatri1.getTime()));
     }
     Date dateBhytGiatri2 = this.tiepdon.getTiepdonGiatri2();
     if (dateBhytGiatri2 != null) {
       this.bhytGiatri2 = formatter.format(Long.valueOf(dateBhytGiatri2.getTime()));
     }
     Date dateBhytGiatri3 = this.tiepdon.getTiepdonGiatri3();
     if (dateBhytGiatri3 != null) {
       this.bhytGiatri3 = formatter.format(Long.valueOf(dateBhytGiatri3.getTime()));
     }
     Date dateBhytGiatri4 = this.tiepdon.getTiepdonGiatri4();
     if (dateBhytGiatri4 != null) {
       this.bhytGiatri4 = formatter.format(Long.valueOf(dateBhytGiatri4.getTime()));
     }
     if (this.tiepdon.getTiepdonMoc1() != null) {
       this.bhytMoc1 = formatter.format(this.tiepdon.getTiepdonMoc1());
     }
     if (this.tiepdon.getTiepdonMoc2() != null) {
       this.bhytMoc2 = formatter.format(this.tiepdon.getTiepdonMoc2());
     }
     if (this.tiepdon.getTiepdonMoc3() != null) {
       this.bhytMoc3 = formatter.format(this.tiepdon.getTiepdonMoc3());
     }
     BenhNhan bn_tmp = this.tiepdon.getBenhnhanMa();
     if (bn_tmp != null)
     {
       if (bn_tmp.getDmgtMaso() != null) {
         this.gioi = ("1".equals(bn_tmp.getDmgtMaso().getDmgtMa()) ? "true" : "false");
       } else {
         this.gioi = null;
       }
       Short dvt = bn_tmp.getBenhnhanDonvituoi();
       this.donvituoi = (dvt != null ? String.valueOf(dvt) : null);
       this.ngaysinh = ((bn_tmp.getBenhnhanNgaysinh() != null) && (!bn_tmp.getBenhnhanNgaysinh().equals("")) ? formatter.format(Long.valueOf(bn_tmp.getBenhnhanNgaysinh().getTime())) : null);
     }
   }

   private void checkThamkham(String maTD)
   {
     ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
     List<ThamKham> listTk = tkDel.findAllByMaTiepDon(maTD);
     this.lockBanKham = ((listTk != null) && (listTk.size() > 1));
   }

   public void displayTiepDonCC(boolean flag)
   {
     log.info("---displayTiepDonCC");
     String ma = this.tiepdon.getTiepdonMa();
     String mabn = this.tiepdon.getBenhnhanMa(true).getBenhnhanMa();
     String maBanKham = this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa();
     if ((ma != null) && (maBanKham != null))
     {
       TiepDon tiepdon_tmp = null;
       if (flag) {
         tiepdon_tmp = TiepDonDelegate.getInstance().findTiepDonCCByMaTiepDon(ma, maBanKham);
       } else {
         tiepdon_tmp = TiepDonDelegate.getInstance().findTiepDonCCByMaBenhNhan(mabn, maBanKham);
       }
       if (tiepdon_tmp != null)
       {
         this.tiepdon = tiepdon_tmp;

         displayTiepDon(this.tiepdon);

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
       }
       else
       {
         this.nofound = "true";
         if (flag) {
           FacesMessages.instance().add(IConstantsRes.TDCC_NULL_MATD, new Object[] { ma });
         } else {
           FacesMessages.instance().add(IConstantsRes.TDCC_NULL_MABN, new Object[] { mabn });
         }
       }
     }
     this.showListBn = false;
     this.listBN = new ArrayList();
     this.lockDoituong = false;
     this.doituongHientai = this.tiepdon.getDoituongMa().getDmdoituongMa();

     List<ClsKham> listClsKham = ClsKhamDelegate.getInstance().findByTiepdonma(ma);
     List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().find2LoaiByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
     if (((listClsKham != null) && (listClsKham.size() > 0)) || ((listTpk != null) && (listTpk.size() > 0))) {
       this.lockDoituong = true;
     }
     checkThamkham(this.tiepdon.getTiepdonMa());
     log.info("End displayTiepDonCC, lockDoituong = " + this.lockDoituong + ", lockBanKham = " + this.lockBanKham);
   }

   public void ghiNhan()
   {
     log.info("ghiNhan");



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
       if (this.tiepdon.getKcbbhytMa(true).getDmbenhvienMaso() != null)
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
     TiepDonDelegate delegate = TiepDonDelegate.getInstance();
     if ((this.gioi == null) || (this.gioi.equals("")))
     {
       this.tiepdon.getBenhnhanMa(true).setDmgtMaso(null);
     }
     else if ("true".equals(this.gioi))
     {
       DmGioiTinh gioiTinh = new DmGioiTinh();
       gioiTinh.setDmgtMaso(Integer.valueOf(2));
       gioiTinh.setDmgtMa("1");
       this.tiepdon.getBenhnhanMa(true).setDmgtMaso(gioiTinh);
     }
     else
     {
       DmGioiTinh gioiTinh = new DmGioiTinh();
       gioiTinh.setDmgtMaso(Integer.valueOf(1));
       gioiTinh.setDmgtMa("0");
       this.tiepdon.getBenhnhanMa(true).setDmgtMaso(gioiTinh);
     }
     if ((this.donvituoi == null) || (this.donvituoi.equals(""))) {
       this.tiepdon.getBenhnhanMa(true).setBenhnhanDonvituoi(null);
     } else {
       this.tiepdon.getBenhnhanMa(true).setBenhnhanDonvituoi(new Short(this.donvituoi));
     }
     if ((this.ngaysinh != null) && (!this.ngaysinh.equals(""))) {
       this.tiepdon.getBenhnhanMa(true).setBenhnhanNgaysinh(Utils.getDBDate("00:00", this.ngaysinh).getTime());
     } else {
       this.tiepdon.getBenhnhanMa(true).setBenhnhanNgaysinh(null);
     }
     removeAllNullForBN(this.tiepdon.getBenhnhanMa(true));
     if ((this.gioTiepDon != null) && (!this.gioTiepDon.equals("")) && (this.ngayTiepDon != null) && (!this.ngayTiepDon.equals("")))
     {
       Calendar ngaygiotiepdon = Utils.getDBDate(this.gioTiepDon, this.ngayTiepDon);
       if (ngaygiotiepdon != null) {
         this.tiepdon.setTiepdonNgaygio(ngaygiotiepdon.getTime());
       } else {
         this.tiepdon.setTiepdonNgaygio(new Date());
       }
     }
     this.tiepdon.setTiepdonNgaygiocn(new Date());
     this.tiepdon.setTiepdonGiatri1(null);
     this.tiepdon.setTiepdonGiatri2(null);
     this.tiepdon.setTiepdonGiatri3(null);
     this.tiepdon.setTiepdonGiatri4(null);
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     this.tiepdon.setTiepdonMoc1(null);
     this.tiepdon.setTiepdonMoc2(null);
     this.tiepdon.setTiepdonMoc3(null);
     if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
     {
       if ((this.bhytGiatri1 != null) && (!this.bhytGiatri1.equals(""))) {
         this.tiepdon.setTiepdonGiatri1(Utils.getDBDate("00:00", this.bhytGiatri1).getTime());
       }
       if ((this.bhytGiatri2 != null) && (!this.bhytGiatri2.equals(""))) {
         this.tiepdon.setTiepdonGiatri2(Utils.getDBDate("00:00", this.bhytGiatri2).getTime());
       }
       if ((this.bhytGiatri3 != null) && (!this.bhytGiatri3.equals(""))) {
         this.tiepdon.setTiepdonGiatri3(Utils.getDBDate("00:00", this.bhytGiatri3).getTime());
       }
       if ((this.bhytGiatri4 != null) && (!this.bhytGiatri4.equals(""))) {
         this.tiepdon.setTiepdonGiatri4(Utils.getDBDate("00:00", this.bhytGiatri4).getTime());
       }
       try
       {
         if ((this.bhytMoc1 != null) && (!this.bhytMoc1.equals(""))) {
           this.tiepdon.setTiepdonMoc1(formatter.parse(this.bhytMoc1));
         }
         if ((this.bhytMoc2 != null) && (!this.bhytMoc2.equals(""))) {
           this.tiepdon.setTiepdonMoc2(formatter.parse(this.bhytMoc2));
         }
         if ((this.bhytMoc3 != null) && (!this.bhytMoc3.equals(""))) {
           this.tiepdon.setTiepdonMoc3(formatter.parse(this.bhytMoc3));
         }
       }
       catch (Exception ex)
       {
         ex.printStackTrace();
       }
     }
     else
     {
       this.tiepdon.setTiepdonSothebh(null);
       this.tiepdon.setKhoibhytMa(new DtDmKhoiBhyt());
       this.tiepdon.setTinhbhytMa(new DmTinh());
       this.tiepdon.setKcbbhytMa(new DmBenhVien());
     }
     if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
     {
       Short tiepdonTuyen = Short.valueOf((short)1);










       this.tiepdon.setTiepdonTuyen(tiepdonTuyen);
     }
     else
     {
       this.tiepdon.setTiepdonTuyen(null);
     }
     this.tiepdon.setTiepdonCoGiayGioiThieu(Boolean.valueOf(false));


     removeAllNullForTD(this.tiepdon);
     String rs = delegate.dangkyTiepDonCapCuu(this.tiepdon, null);
     this.doituongHientai = this.tiepdon.getDoituongMa(true).getDmdoituongMa();
     if ((this.tiepdon.getTiepdonMa() != null) && (!this.tiepdon.getTiepdonMa().equals("")))
     {
       HsThtoank hsttk = new HsThtoank();
       hsttk.setTiepdonMa(this.tiepdon);
       HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.tiepdon);
       hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     }
     if (rs != null)
     {
       FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + rs, new Object[0]);
       resetValue();
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
     }
     this.listBN = new ArrayList();
     this.showListBn = false;
   }

   private void removeAllNullForBN(BenhNhan bn)
   {
     if ("".equals(Utils.reFactorString(bn.getDantocMa().getDmdantocMaso()))) {
       bn.setDantocMa(null);
     }
     if ("".equals(Utils.reFactorString(bn.getTinhMa().getDmtinhMaso()))) {
       bn.setTinhMa(null);
     }
     if ("".equals(Utils.reFactorString(bn.getHuyenMa().getDmhuyenMaso()))) {
       bn.setHuyenMa(null);
     }
     if ("".equals(Utils.reFactorString(bn.getXaMa().getDmxaMaso()))) {
       bn.setXaMa(null);
     }
     if ("".equals(Utils.reFactorString(bn.getBenhnhanNghe().getDmnghenghiepMaso()))) {
       bn.setBenhnhanNghe(null);
     }
   }

   private void removeAllNullForTD(TiepDon td)
   {
     if ("".equals(Utils.reFactorString(td.getDoituongMa().getDmdoituongMaso()))) {
       td.setDoituongMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getTinhbhytMa().getDmtinhMaso()))) {
       td.setTinhbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getKhoibhytMa().getDtdmkhoibhytMaso()))) {
       td.setKhoibhytMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getKcbbhytMa().getDmbenhvienMaso()))) {
       td.setKcbbhytMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getTainanMa().getDmtainanMaso()))) {
       td.setTainanMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getDmptgtnMaso().getDmptgtnMaso()))) {
       td.setDmptgtnMaso(null);
     }
     if ("".equals(Utils.reFactorString(td.getDiadiemMa().getDmdiadiemMaso()))) {
       td.setDiadiemMa(null);
     }
     if ("".equals(Utils.reFactorString(td.getTiepdonDonvigoi().getDmbenhvienMaso()))) {
       td.setTiepdonDonvigoi(null);
     }
     if ("".equals(Utils.reFactorString(td.getTiepdonMachdoanbd().getDmbenhicdMaso()))) {
       td.setTiepdonMachdoanbd(null);
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

   public String getGioTiepDon()
   {
     return this.gioTiepDon;
   }

   public void setGioTiepDon(String gioTiepDon)
   {
     this.gioTiepDon = gioTiepDon;
   }

   public String getNgayTiepDon()
   {
     return this.ngayTiepDon;
   }

   public void setNgayTiepDon(String ngayTiepDon)
   {
     this.ngayTiepDon = ngayTiepDon;
   }

   public String getBhytGiatri1()
   {
     return this.bhytGiatri1;
   }

   public void setBhytGiatri1(String bhytGiatri1)
   {
     this.bhytGiatri1 = bhytGiatri1;
   }

   public String getBhytGiatri2()
   {
     return this.bhytGiatri2;
   }

   public void setBhytGiatri2(String bhytGiatri2)
   {
     this.bhytGiatri2 = bhytGiatri2;
   }

   public String getDonvituoi()
   {
     return this.donvituoi;
   }

   public void setDonvituoi(String donvituoi)
   {
     this.donvituoi = donvituoi;
   }

   public String getNgaysinh()
   {
     return this.ngaysinh;
   }

   public void setNgaysinh(String ngaysinh)
   {
     this.ngaysinh = ngaysinh;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getBhytGiatri3()
   {
     return this.bhytGiatri3;
   }

   public void setBhytGiatri3(String bhytGiatri3)
   {
     this.bhytGiatri3 = bhytGiatri3;
   }

   public String getBhytGiatri4()
   {
     return this.bhytGiatri4;
   }

   public void setBhytGiatri4(String bhytGiatri4)
   {
     this.bhytGiatri4 = bhytGiatri4;
   }

   public String getBhytMoc1()
   {
     return this.bhytMoc1;
   }

   public void setBhytMoc1(String bhytMoc1)
   {
     this.bhytMoc1 = bhytMoc1;
   }

   public String getBhytMoc2()
   {
     return this.bhytMoc2;
   }

   public void setBhytMoc2(String bhytMoc2)
   {
     this.bhytMoc2 = bhytMoc2;
   }

   public String getBhytMoc3()
   {
     return this.bhytMoc3;
   }

   public void setBhytMoc3(String bhytMoc3)
   {
     this.bhytMoc3 = bhytMoc3;
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

   public String getListMaTinhBhyt()
   {
     return this.listMaTinhBhyt;
   }

   public void setListMaTinhBhyt(String listMaTinhBhyt)
   {
     this.listMaTinhBhyt = listMaTinhBhyt;
   }

   public String getHosoDaTT()
   {
     return this.hosoDaTT;
   }

   public void setHosoDaTT(String hosoDaTT)
   {
     this.hosoDaTT = hosoDaTT;
   }

   public boolean isLockBanKham()
   {
     return this.lockBanKham;
   }

   public void setLockBanKham(boolean lockBanKham)
   {
     this.lockBanKham = lockBanKham;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.DangKyKhamBenhCapCuuAction

 * JD-Core Version:    0.7.0.1

 */