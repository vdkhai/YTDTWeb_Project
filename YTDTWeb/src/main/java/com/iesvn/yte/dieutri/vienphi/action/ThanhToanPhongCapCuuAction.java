 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.TamUngKham;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.ThamKhamUtil;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
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
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3235_Thanhtoanphongcapcuu")
 @Synchronized(timeout=6000000L)
 public class ThanhToanPhongCapCuuAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(ThanhToanPhongCapCuuAction.class);
   private HsThtoank hsttk;
   private TiepDon tiepDon;
   private String maTiepDon;
   private String ngayHt;
   private String gioHt;
   private String ngaySinh;
   private Double ungTra;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   private String formTitle;
   List<ClsKham> listCLSKham;
   List<ThuocPhongKham> listTPK;
   @Out(required=false)
   private String isReport = "false";
   private String isUpdate;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   DmBenhIcd phu1_temp = new DmBenhIcd();
   private DtDmCum cum = null;
   @In(required=false)
   @Out(required=false)
   Identity identity;

   @Begin(join=true)
   public String init(String strTmp)
   {
     logger.info("-----init()-----");

     refreshnhanvienthungan();
     logger.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     logger.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       logger.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     this.formTitle = strTmp;

     reset();
     return "TiepDon_KhamBenh_XemChiPhiDieuTri";
   }

   private Double thuocTrongDM = new Double(0.0D);
   private Double thuocNDM = new Double(0.0D);
   private Double vTTHTrongDM = new Double(0.0D);
   private Double vTTHNDM = new Double(0.0D);
   private Double cLSMauTrongDM = new Double(0.0D);
   private Double clsMauNDM = new Double(0.0D);
   private Double pTTTTrongDM = new Double(0.0D);
   private Double pTTTNDM = new Double(0.0D);
   private Double nSKhongThu = new Double(0.0D);
   private Double ung = new Double(0.0D);
   private Double tra = new Double(0.0D);
   private Double soDu = new Double(0.0D);
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Double nguonkhactra = new Double(0.0D);
   private Double nguonkhactra_hid = new Double(0.0D);
   private Double benhnhanTra_hid = new Double(0.0D);
   private Double conlai = new Double(0.0D);
   private Double conlai_hid = new Double(0.0D);

   @End
   public void endConversation() {}

   private void resetPhanTinhToan()
   {
     this.thuocTrongDM = new Double(0.0D);
     this.thuocNDM = new Double(0.0D);
     this.vTTHTrongDM = new Double(0.0D);
     this.vTTHNDM = new Double(0.0D);
     this.cLSMauTrongDM = new Double(0.0D);
     this.clsMauNDM = new Double(0.0D);
     this.pTTTTrongDM = new Double(0.0D);
     this.pTTTNDM = new Double(0.0D);
     this.nSKhongThu = new Double(0.0D);

     this.ung = new Double(0.0D);
     this.tra = new Double(0.0D);
     this.soDu = new Double(0.0D);


     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
   }

   private List<ClsKham> clslist = null;

   private void tinhToanChoHSTKKham(HsThtoank hsttk)
   {
     HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(this.tiepDon);
     hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
     this.clslist = hsthtoankUtil.getListCtkq_temp();
   }

   public void reset()
   {
     refreshnhanvienthungan();
     logger.info("-----reset()-----");
     this.hsttk = new HsThtoank();

     this.tiepDon = new TiepDon();

     this.maTiepDon = "";
     this.ngaySinh = "";
     this.ngayHt = Utils.getCurrentDate();
     this.gioHt = Utils.getGioPhut(new Date());
     this.ungTra = null;

     this.isUpdate = "0";
     this.listCLSKham = new ArrayList();
     this.listTPK = new ArrayList();
     this.phu1_temp = new DmBenhIcd();

     this.bntra = new Double(0.0D);
     this.nguonkhactra = new Double(0.0D);
     this.nguonkhactra_hid = new Double(0.0D);
     this.benhnhanTra_hid = new Double(0.0D);
     this.conlai = new Double(0.0D);
     this.conlai_hid = new Double(0.0D);
   }

   public void loadMaPhieu()
   {
     logger.info("-----loadMaPhieu()----- " + this.hsttk.getHsthtoankMa());
     String maPhieu = this.hsttk.getHsthtoankMa();
     if (maPhieu.trim().length() > 0)
     {
       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       this.hsttk = hsttkDelegate.findByMaPhieu(maPhieu);
       if ((this.hsttk == null) || (this.hsttk.equals("")) || (this.hsttk.getHsthtoankDatt() == null))
       {
         FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { maPhieu });
         reset();
       }
       else
       {
         logger.info("-----Tim thay hsttk-----");

         this.tiepDon = this.hsttk.getTiepdonMa();
         if (this.tiepDon != null)
         {
           this.maTiepDon = this.tiepDon.getTiepdonMa();
           ThamKham tk = ThamKhamDelegate.getInstance().findByMaTiepDon(this.maTiepDon);
           if (tk != null)
           {
             if ((this.formTitle.equals("ThuVienPhi")) &&
               (!tk.getThamkhamBankham().getDtdmbankhamMa().equalsIgnoreCase("CCL")) && (!tk.getThamkhamBankham().getDtdmbankhamMa().equalsIgnoreCase("CCN")))
             {
               FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY, new Object[0]);
               reset();
               return;
             }
             if (tk.getBenhicd10phu1() != null) {
               this.phu1_temp = tk.getBenhicd10phu1();
             }
           }
           else
           {
             FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.maTiepDon, new Object[0]);
             reset();
             return;
           }
           Date d = this.tiepDon.getBenhnhanMa(true).getBenhnhanNgaysinh();
           if (d != null)
           {
             Calendar cal = Calendar.getInstance();
             cal.setTime(d);
             this.ngaySinh = Utils.convertCalendar2Str(cal);
           }
           this.hsttk = hsttkDelegate.findBytiepdonMa(this.maTiepDon);
           if (this.hsttk != null)
           {
             logger.info("-----Tim thay hsttk-----");


             this.hsttk.setHsthtoankThtoan(new Double(0.0D));

             this.bntra = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
             this.benhnhanTra_hid = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
             this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
             this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
             this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
             this.conlai = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
             this.conlai_hid = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
             FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
             this.isUpdate = "1";
           }
           else
           {
             logger.info("-----Khong tim thay hsttk, tao moi-----");
             FacesMessages.instance().clear();
             this.hsttk = new HsThtoank();
             this.hsttk.setTiepdonMa(this.tiepDon);
             this.isUpdate = "0";


             tinhToanChoHSTKKham(this.hsttk);
             Utils.setInforForHsThToan(this.hsttk);
             this.ungTra = Double.valueOf(this.hsttk.getHsthtoankTamung().doubleValue() - this.hsttk.getHsthtoankHoanung().doubleValue());
             this.bntra = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
             this.benhnhanTra_hid = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
             this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
             this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
             this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
             this.conlai = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
             this.conlai_hid = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           }
           ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
           this.listCLSKham = clsKhamDelegate.findByTiepdonma(this.tiepDon.getTiepdonMa());
           ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
           this.listTPK = thuocDel.find2LoaiByMaTiepDon(this.tiepDon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
         }
       }
     }
     else
     {
       FacesMessages.instance().clear();
       reset();
     }
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
   }

   public void loadTiepDon()
   {
     logger.info("-----loadTiepDon()-----, formTitle = " + this.formTitle);
     if (!this.maTiepDon.equals(""))
     {
       TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       this.tiepDon = tdDelegate.find(this.maTiepDon);
       if (this.tiepDon != null)
       {
         this.maTiepDon = this.tiepDon.getTiepdonMa();


         ThamKham tk = ThamKhamDelegate.getInstance().findByMaTiepDon(this.maTiepDon);
         if (tk != null)
         {
           if (this.formTitle.equals("ThuVienPhi"))
           {
             ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(this.maTiepDon);
             if (cvnt != null)
             {
               FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
               reset();
               return;
             }
             if ((!"CCL".equals(this.tiepDon.getTiepdonBankham(true).getDtdmbankhamMa())) && (!"CCN".equals(this.tiepDon.getTiepdonBankham(true).getDtdmbankhamMa())))
             {
               FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY, new Object[0]);
               reset();
               return;
             }
           }
           if (tk.getBenhicd10phu1() != null) {
             this.phu1_temp = tk.getBenhicd10phu1();
           }
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.maTiepDon, new Object[0]);
           reset();
           return;
         }
         Date d = this.tiepDon.getBenhnhanMa(true).getBenhnhanNgaysinh();
         if (d != null)
         {
           Calendar cal = Calendar.getInstance();
           cal.setTime(d);
           this.ngaySinh = Utils.convertCalendar2Str(cal);
         }
         this.hsttk = hsttkDelegate.findBytiepdonMa(this.maTiepDon);
         if ((this.hsttk != null) && (!this.tiepDon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("TP")))
         {
           logger.info("-----Tim thay hsttk-----, ma doi tuong = " + this.tiepDon.getDoituongMa().getDmdoituongMa());
           if ((this.formTitle.equals("TiepNhanKhamBenh")) && (this.tiepDon.getDoituongMa().getDmdoituongMa().equals("TP")))
           {
             tinhToanChoHSTKKham(this.hsttk);
             Utils.setInforForHsThToan(this.hsttk);
             this.ungTra = Double.valueOf(this.hsttk.getHsthtoankTamung().doubleValue() - this.hsttk.getHsthtoankHoanung().doubleValue());
           }
           this.hsttk.setHsthtoankThtoan(new Double(0.0D));

           this.bntra = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.benhnhanTra_hid = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
           this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
           this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
           this.conlai = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           this.conlai_hid = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           this.conlai = Double.valueOf(this.conlai.doubleValue() - this.nguonkhactra.doubleValue());
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
           this.isUpdate = "1";
         }
         else
         {
           logger.info("-----Khong tim thay hsttk, tao moi-----");

           Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(this.tiepDon.getTiepdonMa());
           if (hsba != null)
           {
             HsThtoan hstt = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
             if (hstt != null) {
               FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + (hstt.getHsthtoanNgaytt() != null ? IConstantsRes.DA_THANH_TOAN : IConstantsRes.CHUA_THANH_TOAN) + ")", hsba.getHsbaSovaovien() });
             } else {
               FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + IConstantsRes.CHUA_THANH_TOAN + ")", hsba.getHsbaSovaovien() });
             }
           }
           this.hsttk = new HsThtoank();
           this.hsttk.setTiepdonMa(this.tiepDon);
           this.isUpdate = "0";


           tinhToanChoHSTKKham(this.hsttk);
           Utils.setInforForHsThToan(this.hsttk);
           this.ungTra = Double.valueOf(this.hsttk.getHsthtoankTamung().doubleValue() - this.hsttk.getHsthtoankHoanung().doubleValue());

           this.bntra = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.benhnhanTra_hid = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
           this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
           this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
           this.conlai = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           this.conlai_hid = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           this.conlai = Double.valueOf(this.conlai.doubleValue() - this.nguonkhactra.doubleValue());
         }
         ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
         this.listCLSKham = clsKhamDelegate.findByTiepdonma(this.tiepDon.getTiepdonMa());
         ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
         this.listTPK = thuocDel.find2LoaiByMaTiepDon(this.tiepDon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.maTiepDon, new Object[0]);
         reset();
       }
       logger.info("hsttk = " + this.hsttk);
     }
   }

   public void refreshnhanvienthungan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   public void end()
   {
     refreshnhanvienthungan();
     logger.info("-----end()-----nhanVienThungan = " + this.nhanVienThungan);
     if (this.hsttk != null) {
       if ((this.hsttk.getHsthtoankMa() != null) && (!this.hsttk.getHsthtoankMa().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HOSODATHTOAN, new Object[0]);
       }
       else
       {
         HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
         removeInfoForHsThtoank();
         try
         {
           Date d = Utils.getDBDate(this.gioHt, this.ngayHt).getTime();
           this.hsttk.setHsthtoankNgaygiott(d);
           this.hsttk.setHsthtoankDatt(Boolean.valueOf(true));
           logger.info("-----ngay gio tt: " + d);
         }
         catch (Exception e)
         {
           logger.error(e);
         }
         Double bnTra = this.hsttk.getHsthtoankBntra();











         logger.info("-----bnTra: " + bnTra);

         this.hsttk.setHsthtoankThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);
         this.hsttk.setHsthtoankCum(this.cum);
         try
         {
           Utils.setInforForHsThToan(this.hsttk);
           this.hsttk.setHsthtoankNguonkhactra(this.nguonkhactra);
           String maPhieuTTK = hsttkDelegate.capNhatTTHsttk(this.hsttk, this.clslist, null, false);
           this.hsttk.setHsthtoankMa(maPhieuTTK);


           this.hsttk.setHsthtoankThtoan(new Double(0.0D));


           TamUngKhamDelegate tamUngKhamDele = TamUngKhamDelegate.getInstance();

           List<TamUngKham> lstTamUngKham = tamUngKhamDele.getListTamUngChuaTT(this.maTiepDon);
           if ((lstTamUngKham != null) && (lstTamUngKham.size() > 0)) {
             for (TamUngKham tamUngKham : lstTamUngKham)
             {
               tamUngKham.setTamungkhamStatus("TT");
               tamUngKhamDele.edit(tamUngKham);
             }
           }
           this.bntra = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.benhnhanTra_hid = Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
           this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
           this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
           this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());

           this.conlai = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());
           this.conlai_hid = Double.valueOf(this.hsttk.getHsthtoankThtoan() == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue());

           FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
           this.isUpdate = "1";
         }
         catch (Exception e)
         {
           logger.info(e);
           FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
           this.isUpdate = "0";
         }
       }
     }
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;

   public String inPhieu()
   {
     logger.info("Begin inPhieu(), tiepdon = " + this.tiepDon + ", Ma TD = " + this.tiepDon.getTiepdonMa());
     String tdMa = this.tiepDon.getTiepdonMa();
     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     this.hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
     if (this.hsttk == null)
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[0]);
       logger.info("End InPhieu(): Khong tim thay phieu !");
       return "";
     }
     Double thtoan = Double.valueOf(0.0D);
     if (this.tiepDon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
     {
       if (this.hsttk.getHsthtoankBntra() == null) {
         thtoan = new Double(0.0D);
       } else {
         thtoan = this.hsttk.getHsthtoankBntra();
       }
     }
     else if (this.hsttk.getHsthtoankThtoan() == null) {
       thtoan = new Double(0.0D);
     } else {
       thtoan = this.hsttk.getHsthtoankThtoan();
     }
     Double ndm = Double.valueOf(0.0D);
     if (this.hsttk.getHsthtoankNdm() == null) {
       ndm = new Double(0.0D);
     } else {
       ndm = this.hsttk.getHsthtoankNdm();
     }
     Double nguonkhac = Double.valueOf(this.hsttk.getHsthtoankNguonkhactra() == null ? 0.0D : this.hsttk.getHsthtoankNguonkhactra().doubleValue());
     Double benhnhantra = Double.valueOf(this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue());
     Double xetgiam = Double.valueOf(this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue());
     if (((thtoan.doubleValue() == 0.0D) || (benhnhantra.doubleValue() - xetgiam.doubleValue() - nguonkhac.doubleValue() < 1.0D)) && (ndm.doubleValue() == 0.0D))
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI, new Object[0]);
       logger.info("End InPhieu(): Khong in bien lai !");
       return "";
     }
     XuatReport();
     logger.info("End inPhieu() ---------------------------");
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
     return "B3360_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport()
   {
     this.reportTypeVP = "thanhtoanphongcapcuu";
     String baocao1 = null;
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();


     Double mau = Double.valueOf(0.0D);
     Double xntdcn = Double.valueOf(0.0D);
     Double cdha = Double.valueOf(0.0D);
     Double pttt = Double.valueOf(0.0D);
     Double ktc = Double.valueOf(0.0D);
     Double dvp = Double.valueOf(0.0D);
     Double vc = Double.valueOf(0.0D);
     Double ck = Double.valueOf(0.0D);
     Double clskhac = Double.valueOf(0.0D);


     Double mauDV = Double.valueOf(0.0D);
     Double xntdcnDV = Double.valueOf(0.0D);
     Double cdhaDV = Double.valueOf(0.0D);
     Double ptttDV = Double.valueOf(0.0D);
     Double ktcDV = Double.valueOf(0.0D);
     Double dvpDV = Double.valueOf(0.0D);
     Double vcDV = Double.valueOf(0.0D);
     Double ckDV = Double.valueOf(0.0D);
     Double clskhacDV = Double.valueOf(0.0D);

     boolean hasDV = false;
     Double curGia = Double.valueOf(0.0D);
     Double curGiaDV = Double.valueOf(0.0D);
     Double tongtien = Double.valueOf(0.0D);
     Double tongtienDV = Double.valueOf(0.0D);
     Double miengiam = Double.valueOf(0.0D);

     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     this.hsttk = hsttkDelegate.findBytiepdonMa(this.tiepDon.getTiepdonMa());
     if (this.hsttk != null) {
       miengiam = Double.valueOf(this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue());
     }
     List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonma(this.tiepDon.getTiepdonMa());
     for (ClsKham eachCls : listClsKham)
     {
       curGia = Double.valueOf(eachCls.getClskhamDongiabntra() == null ? 0.0D : eachCls.getClskhamDongiabntra().doubleValue());
       curGiaDV = Double.valueOf(eachCls.getClskhamPhandv() == null ? 0.0D : eachCls.getClskhamPhandv().doubleValue());
       if ((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true))
       {
         hasDV = true;
         if ((this.tiepDon.getDoituongMa() != null) &&
           (this.tiepDon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")))
         {
           curGia = Double.valueOf(eachCls.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0D : eachCls.getClskhamMahang().getDtdmclsbgDongiamp().doubleValue());

           curGiaDV = Double.valueOf((eachCls.getClskhamDongia() == null ? 0.0D : eachCls.getClskhamDongiabntra().doubleValue()) - curGia.doubleValue());
         }
       }
       tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGiaDV.doubleValue());
       if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA"))
       {
         mau = Double.valueOf(mau.doubleValue() + curGia.doubleValue());
         mauDV = Double.valueOf(mauDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN"))
       {
         xntdcn = Double.valueOf(xntdcn.doubleValue() + curGia.doubleValue());
         xntdcnDV = Double.valueOf(xntdcnDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD"))
       {
         cdha = Double.valueOf(cdha.doubleValue() + curGia.doubleValue());
         cdhaDV = Double.valueOf(cdhaDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT")) || (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")))
       {
         pttt = Double.valueOf(pttt.doubleValue() + curGia.doubleValue());
         ptttDV = Double.valueOf(ptttDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamKtcao() != null) && (eachCls.getClskhamKtcao().booleanValue() == true))
       {
         ktc = Double.valueOf(ktc.doubleValue() + curGia.doubleValue());
         ktcDV = Double.valueOf(ktcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI"))
       {
         dvp = Double.valueOf(dvp.doubleValue() + curGia.doubleValue());
         dvpDV = Double.valueOf(dvpDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamMaloai() != null) && (eachCls.getClskhamMaloai().getDtdmclsMaso().intValue() == 12))
       {
         vc = Double.valueOf(vc.doubleValue() + curGia.doubleValue());
         vcDV = Double.valueOf(vcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH"))
       {
         ck = Double.valueOf(ck.doubleValue() + curGia.doubleValue());
         ckDV = Double.valueOf(ckDV.doubleValue() + curGiaDV.doubleValue());
       }
       else
       {
         clskhac = Double.valueOf(clskhac.doubleValue() + curGia.doubleValue());
         clskhacDV = Double.valueOf(clskhacDV.doubleValue() + curGiaDV.doubleValue());
       }
     }
     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
     Double thuocDV = Double.valueOf(0.0D);
     Double vtthDV = Double.valueOf(0.0D);
     Double thuocBN = Double.valueOf(0.0D);
     Double vtthBN = Double.valueOf(0.0D);
     List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaTiepDon(this.tiepDon.getTiepdonMa(), "1");
     for (ThuocPhongKham eachTpk : listTpk_Bankham)
     {
       curGia = Double.valueOf(eachTpk.getThuocphongkhamTienbntra() == null ? 0.0D : eachTpk.getThuocphongkhamTienbntra().doubleValue());
       if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) {
         hasDV = true;
       }
       if ((eachTpk.getThuocphongkhamMathuoc() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10))
       {
         if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
         {
           vtthDV = Double.valueOf(vtthDV.doubleValue() + curGia.doubleValue());
           tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
         }
         else
         {
           vtthBN = Double.valueOf(vtthBN.doubleValue() + curGia.doubleValue());
           tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         }
       }
       else if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
       {
         thuocDV = Double.valueOf(thuocDV.doubleValue() + curGia.doubleValue());
         tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
       }
       else
       {
         thuocBN = Double.valueOf(thuocBN.doubleValue() + curGia.doubleValue());
         tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       }
     }
     List<ThuocPhongKham> listTpk_BHYT = tpkDelegate.findByMaTiepDon(this.tiepDon.getTiepdonMa(), "3");
     for (ThuocPhongKham eachTpk : listTpk_BHYT)
     {
       curGia = Double.valueOf(eachTpk.getThuocphongkhamTienbntra() == null ? 0.0D : eachTpk.getThuocphongkhamTienbntra().doubleValue());
       if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) {
         hasDV = true;
       }
       if ((eachTpk.getThuocphongkhamMathuoc() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10))
       {
         if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
         {
           vtthDV = Double.valueOf(vtthDV.doubleValue() + curGia.doubleValue());
           tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
         }
         else
         {
           vtthBN = Double.valueOf(vtthBN.doubleValue() + curGia.doubleValue());
           tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         }
       }
       else if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
       {
         thuocDV = Double.valueOf(thuocDV.doubleValue() + curGia.doubleValue());
         tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
       }
       else
       {
         thuocBN = Double.valueOf(thuocBN.doubleValue() + curGia.doubleValue());
         tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       }
     }
     String diachistr = "";
     if (this.tiepDon.getBenhnhanMa().getBenhnhanDiachi() != null) {
       diachistr = diachistr + this.tiepDon.getBenhnhanMa().getBenhnhanDiachi() + ",";
     }
     if (this.tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
       diachistr = diachistr + this.tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
     }
     if (this.tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
       diachistr = diachistr + this.tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
     }
     if (this.tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
       diachistr = diachistr + this.tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
     }
     String thungan = "";
     if (this.hsttk.getHsthtoankThungan() != null)
     {
       if (this.hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null) {
         thungan = this.hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
       } else {
         thungan = "";
       }
     }
     else {
       thungan = "";
     }
     String sNoiDungThu = "";
     if (this.tiepDon.getDoituongMa().getDmdoituongMa().equals("BH"))
     {
       if (this.hsttk != null) {
         sNoiDungThu = "Thu " + (100 - (this.hsttk.getHsthtoankTylebh() == null ? 0 : this.hsttk.getHsthtoankTylebh().shortValue())) + IConstantsRes.NOI_DUNG_THU_BHYT;
       }
     }
     else {
       sNoiDungThu = "Thu tiá»�n cá»§a bá»‡nh nhÃ¢n";
     }
     logger.info("hasDV = " + hasDV);
     tongtien = Utils.rounDoubleForReport(tongtien);
     tongtienDV = Utils.rounDoubleForReport(tongtienDV);

     int lanIn = this.hsttk.getHsthtoankLanin() == null ? 1 : Integer.valueOf(this.hsttk.getHsthtoankLanin()).intValue() + 1;
     this.hsttk.setHsthtoankLanin("" + lanIn);
     if ((hasDV) && (tongtienDV.doubleValue() > 0.0D)) {
       try
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         String pathTemplate_sub4 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
         String pathTemplate_sub5 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
         String pathTemplate_sub6 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
         logger.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);

         JasperReport jasperSub4 = JasperCompileManager.compileReport(pathTemplate_sub4);
         JasperReport jasperSub5 = JasperCompileManager.compileReport(pathTemplate_sub5);
         JasperReport jasperSub6 = JasperCompileManager.compileReport(pathTemplate_sub6);
         logger.info("da thay file template ");

         Map<String, Object> params = new HashMap();

         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);
         params.put("sub4", jasperSub4);
         params.put("sub5", jasperSub5);
         params.put("sub6", jasperSub6);


         params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
         params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
         params.put("HOTEN", this.tiepDon.getBenhnhanMa().getBenhnhanHoten());
         params.put("DIACHI", diachistr);
         params.put("THUNGAN", thungan);



         params.put("NOIDUNG", sNoiDungThu);
         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam.doubleValue()));
         params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
         params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
         params.put("MAU", Utils.formatNumberWithSeprator(mau));
         params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
         params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
         params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
         params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
         params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
         params.put("DVP", Utils.formatNumberWithSeprator(Double.valueOf(dvp.doubleValue() + dvpDV.doubleValue())));
         params.put("CV", Utils.formatNumberWithSeprator(vc));
         params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));


         params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         params.put("THUOCDV", Utils.formatNumberWithSeprator(thuocDV));
         params.put("VTTHDV", Utils.formatNumberWithSeprator(vtthDV));
         params.put("CLSDV", Utils.formatNumberWithSeprator(clskhacDV));
         params.put("MAUDV", Utils.formatNumberWithSeprator(mauDV));
         params.put("XNTDCNDV", Utils.formatNumberWithSeprator(xntdcnDV));
         params.put("CDHADV", Utils.formatNumberWithSeprator(cdhaDV));
         params.put("PTTTDV", Utils.formatNumberWithSeprator(ptttDV));
         params.put("DVKTCDV", Utils.formatNumberWithSeprator(ktcDV));
         params.put("DVPDV", Utils.formatNumberWithSeprator(new Double(0.0D)));
         params.put("CVDV", Utils.formatNumberWithSeprator(vcDV));
         params.put("CONGKHAMDV", Utils.formatNumberWithSeprator(ckDV));
         params.put("SOTIENDV", Utils.formatNumberWithSeprator(tongtienDV));
         params.put("TIENBANGCHUDV", Utils.NumberToString(tongtienDV.doubleValue()));

         params.put("LANIN", "" + lanIn);

         Class.forName("com.mysql.jdbc.Driver");
         logger.info("da thay driver mysql");
         Connection conn = null;
         try
         {
           conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
         }
         catch (Exception e)
         {
           logger.info(e.getMessage());
         }
         this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "thanhtoanphongcapcuu");
         this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         logger.info("duong dan file xuat report :" + baocao1);
         logger.info("duong dan -------------------- :" + this.reportPathVP);
         this.index += 1;
         if (conn != null) {
           conn.close();
         }
         hsttkDelegate.edit(this.hsttk);
       }
       catch (Exception e)
       {
         logger.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     } else {
       try
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         logger.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);
         logger.info("da thay file template ");

         Map<String, Object> params = new HashMap();

         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);



         params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
         params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
         params.put("HOTEN", this.tiepDon.getBenhnhanMa().getBenhnhanHoten());

         params.put("DIACHI", diachistr);
         params.put("NOIDUNG", sNoiDungThu);

         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam.doubleValue()));



         params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
         params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
         params.put("MAU", Utils.formatNumberWithSeprator(mau));
         params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
         params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
         params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
         params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
         params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
         params.put("DVP", Utils.formatNumberWithSeprator(Double.valueOf(dvp.doubleValue() + dvpDV.doubleValue())));
         params.put("CV", Utils.formatNumberWithSeprator(vc));
         params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));
         params.put("THUNGAN", thungan);

         params.put("LANIN", "" + lanIn);

         Class.forName("com.mysql.jdbc.Driver");
         logger.info("da thay driver mysql");
         Connection conn = null;
         try
         {
           conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
         }
         catch (Exception e)
         {
           logger.info(e.getMessage());
         }
         this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "thanhtoanphongcapcuu");
         this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         logger.info("duong dan file xuat report :" + baocao1);
         logger.info("duong dan -------------------- :" + this.reportPathVP);
         this.index += 1;
         if (conn != null) {
           conn.close();
         }
         hsttkDelegate.edit(this.hsttk);
       }
       catch (Exception e)
       {
         logger.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
   }

   public String inPhieuThanhToan()
   {
     return XuatPhieuThanhToan();
   }

   public String XuatPhieuThanhToan()
   {
     logger.info("begin XuatPhieuThanhToan() tiepDon = " + this.tiepDon);
     if (this.tiepDon == null) {
       return null;
     }
     this.reportTypeVP = "thanhtoanphongcapcuu";


     String baocao1 = null;
     try
     {
       logger.info("Vao Method XuatPhieuThanhToan() kham chua benh cap cuu ngoai tru");

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("MATIEPDON", this.tiepDon.getTiepdonMa());
       params.put("HOTENBN", this.tiepDon.getBenhnhanMa().getBenhnhanHoten());

       String diachistr = "";
       if (this.tiepDon.getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.tiepDon.getBenhnhanMa().getBenhnhanDiachi() + ",";
       }
       if (this.tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.tiepDon.getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
       }
       if (this.tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.tiepDon.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.tiepDon.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (this.tiepDon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         params.put("BHYT_CO", "X");
         if (((this.tiepDon.getTiepdonTuyen() != null) && (this.tiepDon.getTiepdonTuyen().toString().equals("1"))) || ((this.tiepDon.getTiepdonCoGiayGioiThieu() != null) && (this.tiepDon.getTiepdonCoGiayGioiThieu().booleanValue()))) {
           params.put("DUNGTUYEN", "X");
         } else {
           params.put("TRAITUYEN", "X");
         }
       }
       else
       {
         params.put("BHYT_KO", "X");
       }
       if (this.tiepDon.getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(this.tiepDon.getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (this.tiepDon.getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(this.tiepDon.getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((this.tiepDon.getTiepdonSothebh() != null) && (!this.tiepDon.getTiepdonSothebh().equals("")) && (this.tiepDon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null) && (!this.tiepDon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")) && (this.tiepDon.getKcbbhytMa(true).getDmbenhvienMa() != null))
       {
         params.put("MATHEBHYT", this.tiepDon.getTiepdonSothebh());
         params.put("MABENHVIEN", this.tiepDon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
       }
       else
       {
         params.put("MABENHVIEN", "");
         params.put("MATHEBHYT", "");
       }
       if (this.tiepDon.getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", this.tiepDon.getKcbbhytMa(true).getDmbenhvienTen());
       }
       if (this.tiepDon.getTinhbhytMa(true).getDmtinhTen() != null) {
         params.put("NOICAPTHE", this.tiepDon.getTinhbhytMa(true).getDmtinhTen());
       }
       if (this.tiepDon.getTiepdonDonvigoi(true).getDmbenhvienMa() != null) {
         params.put("NOIGIOITHIEU", this.tiepDon.getTiepdonDonvigoi(true).getDmbenhvienTen());
       }
       params.put("GIUONG", this.tiepDon.getTiepdonGiuong());
       ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
       ThamKham thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(this.tiepDon.getTiepdonBankham().getDtdmbankhamMa(), this.tiepDon.getTiepdonMa());
       Calendar cal = Calendar.getInstance();
       cal.setTime(thamkham.getThamkhamNgaygio());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));

       params.put("LYDOVAOVIEN", this.tiepDon.getTiepdonLydovaov());

       HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = new HsThtoank();
       hsttk = thanhToanDel.findBytiepdonMaLast(this.tiepDon.getTiepdonMa());
       if ((hsttk == null) || ((hsttk.getHsthtoankDatt() != null) && (!hsttk.getHsthtoankDatt().booleanValue())))
       {
         hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.tiepDon);

         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, Boolean.valueOf(false), null, null);
         Utils.setInforForHsThToan(hsttk);
       }
       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
       params.put("NGUONKHAC", hsttk.getHsthtoankNguonkhactra());
       if (hsttk.getHsthtoankNgaygiott() != null)
       {
         params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
         if (thamkham.getThamkhamNgaygio() != null) {
           params.put("SONGAYDT", Long.valueOf(Utils.getDaysBetween(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott())));
         }
       }
       if ((this.tiepDon.getTiepdonBankham() != null) &&
         (this.tiepDon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")))
       {
         params.put("CAPCUU", "X");
         params.put("DUNGTUYEN", "");
         params.put("TRAITUYEN", "");
       }
       params.put("PHIEUSO", hsttk.getHsthtoankMa());
       logger.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
       params.put("TYLEBH", hsttk.getHsthtoankTylebh());
       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       if ("MP".equals(this.tiepDon.getDoituongMa(true).getDmdoituongMa())) {
         tyleBNtra = "0";
       }
       params.put("PHANTRAMBNTRA", tyleBNtra);
       String bienlai = "";
       if (hsttk.getHsthtoankKyhieu() != null) {
         bienlai = bienlai + hsttk.getHsthtoankKyhieu();
       }
       if (hsttk.getHsthtoankQuyen() != null) {
         if (!"".equals(bienlai)) {
           bienlai = bienlai + "/" + hsttk.getHsthtoankQuyen();
         } else {
           bienlai = bienlai + hsttk.getHsthtoankQuyen();
         }
       }
       if (hsttk.getHsthtoankBienlai() != null) {
         if (!"".equals(bienlai)) {
           bienlai = bienlai + "/" + hsttk.getHsthtoankBienlai();
         } else {
           bienlai = bienlai + hsttk.getHsthtoankBienlai();
         }
       }
       params.put("BIENLAISO", bienlai);
       String thungan = "";
       if (hsttk.getHsthtoankThungan() != null)
       {
         if (hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null) {
           thungan = hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
         } else {
           thungan = "";
         }
       }
       else {
         thungan = "";
       }
       params.put("THUNGAN1", thungan);
       params.put("namsinh", this.tiepDon.getBenhnhanMa().getBenhnhanNamsinh());

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((thamkham.getBenhicd10() != null) && (thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           params.put("MABENHICD", maChanDoan);
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((thamkham.getThamkhamGhichu() != null) && (!thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + thamkham.getThamkhamGhichu();
       }
       if ((thamkham.getBenhicd10phu1() != null) && (thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((thamkham.getBenhicd10phu2() != null) && (thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((thamkham.getBenhicd10phu3() != null) && (thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((thamkham.getBenhicd10phu4() != null) && (thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((thamkham.getBenhicd10phu5() != null) && (thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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

       params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen());

       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(this.tiepDon.getTiepdonMa());


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
       List<ClsKham> listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(this.tiepDon.getTiepdonMa());
       if ((listClsTmp != null) && (listClsTmp.size() > 0)) {
         for (ClsKham eachCls : listClsTmp) {
           if (((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true)) || ((eachCls.getClskhamNdm() != null) && (eachCls.getClskhamNdm().booleanValue() == true) && (eachCls.getClskhamPhandv() != null))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachCls.getClskhamPhandv().doubleValue());
           }
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.tiepDon.getTiepdonMa(), "1");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamDongia().doubleValue());
           }
         }
       }
       listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.tiepDon.getTiepdonMa(), "3");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamDongia().doubleValue());
           }
         }
       }
       params.put("KHOA", bufStr.toString());
       String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.tiepDon.getTiepdonKhaisinh() != null) && (!this.tiepDon.getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.tiepDon.getTiepdonKhaisinh();
         }
       }
       else if ((this.tiepDon.getTiepdonKhaisinh() != null) && (!this.tiepDon.getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.tiepDon.getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.tiepDon.getTiepdonChungsinh() != null) && (!this.tiepDon.getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.tiepDon.getTiepdonChungsinh();
         }
       }
       else if ((this.tiepDon.getTiepdonChungsinh() != null) && (!this.tiepDon.getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.tiepDon.getTiepdonChungsinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       String soTheNgheo = this.tiepDon.getTiepdonThengheo();
       if ((soTheNgheo != null) && (!soTheNgheo.equals(""))) {
         params.put("SOTHENGHEO", soTheNgheo);
       } else {
         params.put("SOTHENGHEO", null);
       }
       params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
       params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi().doubleValue()));
       params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankBntra().doubleValue()));
       params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt().doubleValue()));
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (hsttk.getHsthtoankBntra().doubleValue() >= 0.0D) {
         params.put("SNGUOIBENHTRA", "0");
       } else {
         params.put("SNGUOIBENHTRA", "-1");
       }
       DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(this.tiepDon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
       if (gioi != null) {
         params.put("GIOI", gioi.getDmgtTen());
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       logger.info("tongTienDV = " + tongTienDV);
       JasperReport jasperReport;
       if (tongTienDV.doubleValue() > 0.0D)
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";



         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";
         String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
         String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";

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
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBcapcuu_subreport1.jrxml";

         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
       }
       logger.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       logger.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "PhieuThanhToanKCBcapcuu");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan -------------------- :" + this.reportPathVP);
       if (conn != null)
       {
         conn.close();
         conn = null;
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatPhieuThanhToan!!!");
       e.printStackTrace();
       return null;
     }
     logger.info("Thoat Method XuatPhieuThanhToan");
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
     return "B3360_Chonmenuxuattaptin";
   }

   public void huyPhieu()
   {
     logger.info("Begin huyPhieu(), hsttk = " + this.hsttk + ", maPhieu = " + this.hsttk.getHsthtoankMa());
     if (this.hsttk != null) {
       if ((this.hsttk.getHsthtoankDatt() != null) && (this.hsttk.getHsthtoankDatt().booleanValue() == true)) {
         try
         {
           ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
           List<ClsKham> listCls = clsKhamDel.findByTiepdonma(this.hsttk.getTiepdonMa().getTiepdonMa());
           if ((listCls != null) && (listCls.size() > 0)) {
             for (ClsKham eachCls : listCls)
             {
               eachCls.setClskhamDatt(null);
               eachCls.setClskhamNgaygiott(null);
               eachCls.setClskhamMaphieu(null);
               clsKhamDel.edit(eachCls);
             }
           }
           ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
           List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "1");
           if ((listTpk != null) && (listTpk.size() > 0)) {
             for (ThuocPhongKham eachTpk : listTpk)
             {
               eachTpk.setThuocphongkhamDatt(null);
               eachTpk.setThuocphongkhamNgaygiott(null);
               eachTpk.setThuocphongkhamMaphieud(null);
               tpkDelegate.edit(eachTpk);
             }
           }
           listTpk = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "3");
           if ((listTpk != null) && (listTpk.size() > 0)) {
             for (ThuocPhongKham eachTpk : listTpk)
             {
               eachTpk.setThuocphongkhamDatt(null);
               eachTpk.setThuocphongkhamNgaygiott(null);
               eachTpk.setThuocphongkhamMaphieud(null);
               tpkDelegate.edit(eachTpk);
             }
           }
           this.hsttk.setHsthtoankDatt(null);
           this.hsttk.setHsthtoankNgaygiott(null);
           this.hsttk.setHsthtoankLanin(null);
           HsThtoankDelegate.getInstance().edit(this.hsttk);

           TamUngKhamDelegate tamUngKhamDele = TamUngKhamDelegate.getInstance();

           List<TamUngKham> lstTamUngKham = tamUngKhamDele.getListTamUngDaTT(this.maTiepDon);
           if ((lstTamUngKham != null) && (lstTamUngKham.size() > 0)) {
             for (TamUngKham tamUngKham : lstTamUngKham)
             {
               tamUngKham.setTamungkhamStatus(null);
               tamUngKhamDele.edit(tamUngKham);
             }
           }
           FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.hsttk.getHsthtoankMa() });
           reset();
         }
         catch (Exception ex)
         {
           ex.printStackTrace();
         }
       }
     }
     logger.info("End huyPhieu()");
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "/B3_Vienphi/ThuVienPhi/B3235_Thanhtoanphongcapcuu.xhtml";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   private void removeInfoForHsThtoank()
   {
     logger.info("Begin removeInfoForHsThtoank() ");
     if (this.hsttk != null)
     {
       if (this.hsttk.getHsthtoankMabenh() != null) {
         if ("".equals(Boolean.valueOf(Utils.reFactorString(this.hsttk.getHsthtoankMabenh().getDmbenhicdMa()) == null))) {
           this.hsttk.setHsthtoankMabenh(null);
         }
       }
       if (this.hsttk.getHsthtoankNhanviencn() != null) {
         if ("".equals(Boolean.valueOf(Utils.reFactorString(this.hsttk.getHsthtoankNhanviencn().getDtdmnhanvienMa()) == null))) {
           this.hsttk.setHsthtoankNhanviencn(null);
         }
       }
       if (this.hsttk.getHsthtoankThungan() != null) {
         if ("".equals(Boolean.valueOf(Utils.reFactorString(this.hsttk.getHsthtoankThungan().getDtdmnhanvienMa()) == null))) {
           this.hsttk.setHsthtoankThungan(null);
         }
       }
     }
   }

   public void setMaTiepDon(String maTiepDon)
   {
     this.maTiepDon = maTiepDon;
   }

   public String getMaTiepDon()
   {
     return this.maTiepDon;
   }

   public void setHsttk(HsThtoank hsttk)
   {
     this.hsttk = hsttk;
   }

   public HsThtoank getHsttk()
   {
     return this.hsttk;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setGioHt(String gioHt)
   {
     this.gioHt = gioHt;
   }

   public String getGioHt()
   {
     return this.gioHt;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public Double getUngTra()
   {
     return this.ungTra;
   }

   public void setUngTra(Double ungTra)
   {
     this.ungTra = ungTra;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getIsUpdate()
   {
     return this.isUpdate;
   }

   public static Logger getLogger()
   {
     return logger;
   }

   public static void setLogger(Logger logger)
   {
     logger = logger;
   }

   public Double getThuocTrongDM()
   {
     return this.thuocTrongDM;
   }

   public void setThuocTrongDM(Double thuocTrongDM)
   {
     this.thuocTrongDM = thuocTrongDM;
   }

   public Double getThuocNDM()
   {
     return this.thuocNDM;
   }

   public void setThuocNDM(Double thuocNDM)
   {
     this.thuocNDM = thuocNDM;
   }

   public Double getVTTHTrongDM()
   {
     return this.vTTHTrongDM;
   }

   public void setVTTHTrongDM(Double trongDM)
   {
     this.vTTHTrongDM = trongDM;
   }

   public Double getVTTHNDM()
   {
     return this.vTTHNDM;
   }

   public void setVTTHNDM(Double vtthndm)
   {
     this.vTTHNDM = vtthndm;
   }

   public Double getCLSMauTrongDM()
   {
     return this.cLSMauTrongDM;
   }

   public void setCLSMauTrongDM(Double mauTrongDM)
   {
     this.cLSMauTrongDM = mauTrongDM;
   }

   public Double getClsMauNDM()
   {
     return this.clsMauNDM;
   }

   public void setClsMauNDM(Double clsMauNDM)
   {
     this.clsMauNDM = clsMauNDM;
   }

   public Double getPTTTTrongDM()
   {
     return this.pTTTTrongDM;
   }

   public void setPTTTTrongDM(Double trongDM)
   {
     this.pTTTTrongDM = trongDM;
   }

   public Double getPTTTNDM()
   {
     return this.pTTTNDM;
   }

   public void setPTTTNDM(Double ptttndm)
   {
     this.pTTTNDM = ptttndm;
   }

   public Double getNSKhongThu()
   {
     return this.nSKhongThu;
   }

   public void setNSKhongThu(Double khongThu)
   {
     this.nSKhongThu = khongThu;
   }

   public Double getUng()
   {
     return this.ung;
   }

   public void setUng(Double ung)
   {
     this.ung = ung;
   }

   public Double getTra()
   {
     return this.tra;
   }

   public void setTra(Double tra)
   {
     this.tra = tra;
   }

   public Double getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(Double soDu)
   {
     this.soDu = soDu;
   }

   public int getPermiengiam()
   {
     return this.permiengiam;
   }

   public void setPermiengiam(int permiengiam)
   {
     this.permiengiam = permiengiam;
   }

   public Double getMiengiam()
   {
     return this.miengiam;
   }

   public void setMiengiam(Double miengiam)
   {
     this.miengiam = miengiam;
   }

   public Double getThatthu()
   {
     return this.thatthu;
   }

   public void setThatthu(Double thatthu)
   {
     this.thatthu = thatthu;
   }

   public int getPerbhytchi()
   {
     return this.perbhytchi;
   }

   public void setPerbhytchi(int perbhytchi)
   {
     this.perbhytchi = perbhytchi;
   }

   public Double getBhytchi()
   {
     return this.bhytchi;
   }

   public void setBhytchi(Double bhytchi)
   {
     this.bhytchi = bhytchi;
   }

   public Double getThanhtien1()
   {
     return this.thanhtien1;
   }

   public void setThanhtien1(Double thanhtien1)
   {
     this.thanhtien1 = thanhtien1;
   }

   public int getPerbntra()
   {
     return this.perbntra;
   }

   public void setPerbntra(int perbntra)
   {
     this.perbntra = perbntra;
   }

   public Double getBntra()
   {
     return this.bntra;
   }

   public void setBntra(Double bntra)
   {
     this.bntra = bntra;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getFormTitle()
   {
     return this.formTitle;
   }

   public void setFormTitle(String formTitle)
   {
     this.formTitle = formTitle;
   }

   public List<ClsKham> getListCLSKham()
   {
     return this.listCLSKham;
   }

   public void setListCLSKham(List<ClsKham> listCLSKham)
   {
     this.listCLSKham = listCLSKham;
   }

   public List<ThuocPhongKham> getListTPK()
   {
     return this.listTPK;
   }

   public void setListTPK(List<ThuocPhongKham> listTPK)
   {
     this.listTPK = listTPK;
   }

   public Double getNguonkhactra()
   {
     return this.nguonkhactra;
   }

   public void setNguonkhactra(Double nguonkhactra)
   {
     this.nguonkhactra = nguonkhactra;
   }

   public Double getNguonkhactra_hid()
   {
     return this.nguonkhactra_hid;
   }

   public void setNguonkhactra_hid(Double nguonkhactraHid)
   {
     this.nguonkhactra_hid = nguonkhactraHid;
   }

   public Double getBenhnhanTra_hid()
   {
     return this.benhnhanTra_hid;
   }

   public void setBenhnhanTra_hid(Double benhnhanTraHid)
   {
     this.benhnhanTra_hid = benhnhanTraHid;
   }

   public Double getConlai()
   {
     return this.conlai;
   }

   public void setConlai(Double conlai)
   {
     this.conlai = conlai;
   }

   public Double getConlai_hid()
   {
     return this.conlai_hid;
   }

   public void setConlai_hid(Double conlaiHid)
   {
     this.conlai_hid = conlaiHid;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThanhToanPhongCapCuuAction

 * JD-Core Version:    0.7.0.1

 */