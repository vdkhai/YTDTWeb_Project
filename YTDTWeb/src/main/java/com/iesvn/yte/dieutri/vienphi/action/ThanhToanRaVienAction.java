 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanUtil;
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
 @Name("B3241_Thanhtoanravien")
 @Synchronized(timeout=6000000L)
 public class ThanhToanRaVienAction
   implements Serializable
 {
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(ThanhToanRaVienAction.class);
   private BenhNhan benhNhan;
   private HsbaBhyt hsbaBhyt;
   private Hsba hsba;
   private HsbaChuyenVien hsbaChuyenvien;
   private HsThtoan hsThtoan;
   private String gioi;
   private String tuoi;
   private String soThe;
   private String ngayNhapvien;
   private String ngayRavien;
   private String maFinish;
   private String sLiDoTT;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private Double ungTra;
   private String ngayTtoan;
   private String cacKhoadt;
   private String loaiFileXuat;
   private String resultHidden = "";
   private String reportFileNameHid = "";
   private String reportFileName;
   private String resultReportName;
   private String resultReportFileName;
   private String reportFinished = "";
   private boolean updateNhapct = false;
   private String gioThanhToan;
   private List<ClsMo> listClsMo;
   private List<ThuocNoiTru> listThuocNT;
   @Out(required=false)
   private String isReport = "false";
   private Double thuocTrongDM = new Double(0.0D);
   private Double thuocNDM = new Double(0.0D);
   private Double vTTHTrongDM = new Double(0.0D);
   private Double vTTHNDM = new Double(0.0D);
   private Double cLSMauTrongDM = new Double(0.0D);
   private Double clsMauNDM = new Double(0.0D);
   private Double tongTrongDM = new Double(0.0D);
   private Double tongNDM = new Double(0.0D);
   private Double nSKhongThu = new Double(0.0D);
   private Double ung = new Double(0.0D);
   private Double tra = new Double(0.0D);
   private Double soDu = new Double(0.0D);
   private Double benhnhanTra = new Double(0.0D);
   private Double conlai = new Double(0.0D);
   private HsThtoank hsttk;
   private ChuyenVaoNoiTru cvnt;
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
   private Double conlai_hid = new Double(0.0D);
   @In(required=false)
   @Out(required=false)
   private String ten3241_3242;
   private Boolean hienThiGhiNhan = Boolean.valueOf(false);
   private Boolean hienThiInPhieu = Boolean.valueOf(false);
   private Boolean hienThiHuyPhieu = Boolean.valueOf(false);
   private Boolean hienThiInPhieuNgT = Boolean.valueOf(false);
   private String role = "";
   private Integer khoaMaso;
   private String khoaMa;

   private void tinhToanChoHSTT(HsThtoan hstt)
   {
     HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.hsba);
     hsthtoanUtil.tinhToanChoHSTT(hstt);
   }

   private DtDmCum cum = null;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private int index = 0;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;

   public void refreshNhanVienThuNgan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   @Begin(join=true)
   public String init(String maForm, String fromRole)
     throws Exception
   {
     log.info("***Starting init ***");

     this.ten3241_3242 = maForm;
     this.role = fromRole;
     refreshNhanVienThuNgan();


     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();

     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null)) {
       this.cum = pc.getDtdmcumMa();
     }
     emtyData();
     log.info("***Finished init ***");

     this.tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;

     return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
   }

   @End
   public void endFuct() {}

   private void emtyData()
   {
     log.info("begin emptyData()");
     this.benhNhan = new BenhNhan();

     this.hsbaBhyt = new HsbaBhyt();

     this.hsba = new Hsba();

     this.hsbaChuyenvien = new HsbaChuyenVien();






     this.hsThtoan = new HsThtoan();


     this.tuoi = "";
     this.soThe = "";
     this.ngayNhapvien = "";
     this.ngayRavien = "";
     this.maFinish = "";

     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayTtoan = formatter.format(cal.getTime());
     this.cacKhoadt = "";
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());


     this.hienThiGhiNhan = Boolean.valueOf(false);
     this.hienThiInPhieu = Boolean.valueOf(false);
     this.hienThiHuyPhieu = Boolean.valueOf(false);
     this.hienThiInPhieuNgT = Boolean.valueOf(false);
     this.khoaMa = "";
     this.ungTra = null;
     this.sLiDoTT = "";
     this.listClsMo = new ArrayList();
     this.listThuocNT = new ArrayList();



     log.info("end emptyData()");
   }

   private void hasNoKhoaOrSoVaoVien()
   {
     emtyData();
   }

   public void ghinhan()
     throws Exception
   {
     refreshNhanVienThuNgan();
     try
     {
       if ((this.hsThtoan.getHsthtoanMa() != null) && (!this.hsThtoan.getHsthtoanMa().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);

         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(true);
         this.hienThiInPhieuNgT = Boolean.valueOf(false);
         return;
       }
       if ((this.hsba.getHsbaYeuCau() == null) || (this.hsba.getHsbaYeuCau().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.GIAYXACNHANRAVIEN_REQUIRED, new Object[0]);

         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         this.hienThiInPhieuNgT = Boolean.valueOf(false);
         return;
       }
       if (MyMenuYTDTAction.vienPhiTaiKhoa.equals(this.tenChuongTrinh))
       {
         if ((this.hsba.getDoituongMa() != null) && (!this.hsba.getDoituongMa().getDmdoituongMa().equals("TE")) && (!this.hsba.getDoituongMa().getDmdoituongMa().equals("BH")) && (!this.hsba.getDoituongMa().getDmdoituongMa().equals("MP")))
         {
           FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thuoc_doi_tuong_tre_em_hoac_bhyt, new Object[0]);
           this.hienThiGhiNhan = Boolean.valueOf(false);
           this.hienThiInPhieu = Boolean.valueOf(false);
           this.hienThiHuyPhieu = Boolean.valueOf(false);
           this.hienThiInPhieuNgT = Boolean.valueOf(false);
           return;
         }
         if ((this.hsThtoan.getHsthtoanThtoan() != null) && (this.hsThtoan.getHsthtoanThtoan().doubleValue() > 0.0D))
         {
           FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi, new Object[0]);
           this.hienThiGhiNhan = Boolean.valueOf(false);
           this.hienThiInPhieu = Boolean.valueOf(false);
           this.hienThiHuyPhieu = Boolean.valueOf(false);
           this.hienThiInPhieuNgT = Boolean.valueOf(false);
           return;
         }
       }
       HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

       RemoveUtil.removeIfNullForHsThtoan(this.hsThtoan);
       RemoveUtil.removeAllNullFromHSBA(this.hsba);

       RemoveUtil.removeAllNullFromHSBACV(this.hsbaChuyenvien);



       removeIfNullForHsbaChuyenVien(this.hsbaChuyenvien);
       Date dNgayGioTT = Calendar.getInstance().getTime();
       if (this.hsThtoan.getHsthtoanNgaytt() == null)
       {
         Calendar dTemp = Utils.getDBDate(this.gioThanhToan, this.ngayTtoan);
         if (dTemp != null) {
           dNgayGioTT = dTemp.getTime();
         }
         this.hsThtoan.setHsthtoanNgaytt(dNgayGioTT);
       }
       this.hsThtoan.setHsthtoanThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);
       this.hsThtoan.setHsthtoanCum(this.cum);
       this.hsThtoan.setHsthtoanLidothanhtoan(this.sLiDoTT);

       this.hsThtoan.setHsthtoanDatt(Boolean.valueOf(true));
       if (this.hsba.getHsbaNgaygiorav() == null) {
         this.hsba.setHsbaNgaygiorav(this.hsThtoan.getHsthtoanNgaytt());
       }
       if (this.ten3241_3242.equals("3242"))
       {
         this.hsThtoan.setHsthtoanBovien(Boolean.valueOf(true));
         this.hsThtoan.setHsthtoanThatthu(this.hsThtoan.getHsthtoanBntra());
       }
       Utils.setInforForHsThToan(this.hsThtoan);

       this.hsThtoan.setHsthtoanKhoa(this.hsba.getHsbaKhoadangdt());

       this.hsThtoan.setHsthtoanNguonkhactra(this.nguonkhactra);
       this.maFinish = hsttDelegate.capNhatTTRaVien(this.hsThtoan, this.hsba, this.hsbaChuyenvien);
       this.hsThtoan.setHsthtoanThtoan(new Double(0.0D));
       if (this.cvnt != null)
       {
         log.info("ma tiep don = " + this.hsttk.getTiepdonMa().getTiepdonMa());
         this.hsttk.setHsthtoankMa(this.maFinish);
         this.hsttk.setHsthtoankThatthu(this.hsThtoan.getHsthtoanThatthu());
         this.hsttk.setHsthtoankKyhieu(this.hsThtoan.getHsthtoanKyhieu());
         this.hsttk.setHsthtoankQuyen(this.hsThtoan.getHsthtoanQuyen());
         this.hsttk.setHsthtoankBienlai(this.hsThtoan.getHsthtoanBienlai());
         this.hsttk.setHsthtoankThungan(this.hsThtoan.getHsthtoanThungan());

         this.hsttk.setHsthtoankNgaygiott(dNgayGioTT);
         ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

         List<ClsKham> clslist = clsKhamDelegate.findByTiepdonma(this.hsttk.getTiepdonMa().getTiepdonMa());


         ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
         List<ThuocPhongKham> listCtTPK_temp = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "1");


         List<ThuocPhongKham> listCtTPK_tempBHYT = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "3");
         listCtTPK_temp.addAll(listCtTPK_tempBHYT);
         HsThtoankDelegate hsttkDel = HsThtoankDelegate.getInstance();
         hsttkDel.capNhatTTHsttk(this.hsttk, clslist, listCtTPK_temp, false);
         if (this.hsttk.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP"))
         {
           HsThtoankBackup hsBK = new HsThtoankBackup();

           hsBK.setHsthtoankBhxh(this.hsttk.getHsthtoankBhxh());
           hsBK.setHsthtoankBhyt(this.hsttk.getHsthtoankBhyt());
           hsBK.setHsthtoankBienlai(this.hsttk.getHsthtoankBienlai());
           hsBK.setHsthtoankBntra(this.hsttk.getHsthtoankBntra());
           hsBK.setHsthtoankBovien(this.hsttk.getHsthtoankBovien());
           hsBK.setHsthtoankCdha(this.hsttk.getHsthtoankCdha());
           hsBK.setHsthtoankCls(this.hsttk.getHsthtoankCls());
           hsBK.setHsthtoankClsndm(this.hsttk.getHsthtoankClsndm());
           hsBK.setHsthtoankCongkham(this.hsttk.getHsthtoankCongkham());
           hsBK.setHsthtoankCpVc(this.hsttk.getHsthtoankCpvc());
           hsBK.setHsthtoankCum(this.hsttk.getHsthtoankCum());
           hsBK.setHsthtoankDatt(this.hsttk.getHsthtoankDatt());

           hsBK.setHsthtoankDienmien(this.hsttk.getHsthtoankDienmien());
           hsBK.setHsthtoankDm(this.hsttk.getHsthtoankDm());
           hsBK.setHsthtoankDvKtc(this.hsttk.getHsthtoankDvktc());
           hsBK.setHsthtoankDvktTt(this.hsttk.getHsthtoankDvkttt());
           hsBK.setHsthtoankHoanthu(this.hsttk.getHsthtoankHoanthu());
           hsBK.setHsthtoankHoanung(this.hsttk.getHsthtoankHoanung());
           hsBK.setHsthtoankKhongthu(this.hsttk.getHsthtoankKhongthu());
           hsBK.setHsthtoankKyhieu(this.hsttk.getHsthtoankKyhieu());
           hsBK.setHsthtoankLydott(this.hsttk.getHsthtoankLydott());

           hsBK.setHsthtoankMabenh(this.hsttk.getHsthtoankMabenh());
           hsBK.setHsthtoankMau(this.hsttk.getHsthtoankMau());
           hsBK.setHsthtoankMiendt(this.hsttk.getHsthtoankMiendt());
           hsBK.setHsthtoankMienmau(this.hsttk.getHsthtoankMienmau());
           hsBK.setHsthtoankMienphong(this.hsttk.getHsthtoankMienphong());
           hsBK.setHsthtoankMiente(this.hsttk.getHsthtoankMiente());
           hsBK.setHsthtoankMienthuoc(this.hsttk.getHsthtoankMienthuoc());
           hsBK.setHsthtoankMienthuoclao(this.hsttk.getHsthtoankMienthuoclao());
           hsBK.setHsthtoankNdm(this.hsttk.getHsthtoankNdm());
           hsBK.setHsthtoankNgansach(this.hsttk.getHsthtoankNgansach());
           hsBK.setHsthtoankNgayc(this.hsttk.getHsthtoankNgayc());
           hsBK.setHsthtoankNgayd(this.hsttk.getHsthtoankNgayd());
           hsBK.setHsthtoankNgaygiocn(this.hsttk.getHsthtoankNgaygiocn());
           hsBK.setHsthtoankNgaygiott(this.hsttk.getHsthtoankNgaygiott());
           hsBK.setHsthtoankNhanviencn(this.hsttk.getHsthtoankNhanviencn());
           hsBK.setHsthtoankPhauthuat(this.hsttk.getHsthtoankPhauthuat());
           hsBK.setHsthtoankPhauthuatndm(this.hsttk.getHsthtoankPhauthuatndm());
           hsBK.setHsthtoankPhong(this.hsttk.getHsthtoankPhong());
           hsBK.setHsthtoankPhongndm(this.hsttk.getHsthtoankPhongndm());
           hsBK.setHsthtoankQuyen(this.hsttk.getHsthtoankQuyen());
           hsBK.setHsthtoankSokhoa(this.hsttk.getHsthtoankSokhoa());
           hsBK.setHsthtoankTamung(this.hsttk.getHsthtoankTamung());
           hsBK.setHsthtoankThatthu(this.hsttk.getHsthtoankThatthu());
           hsBK.setHsthtoankThtoan(this.hsttk.getHsthtoankThtoan());
           hsBK.setHsthtoankThungan(this.hsttk.getHsthtoankThungan());
           hsBK.setHsthtoankThuoc(this.hsttk.getHsthtoankThuoc());
           hsBK.setHsthtoankThuocndm(this.hsttk.getHsthtoankThuocndm());
           hsBK.setHsthtoankTongchi(this.hsttk.getHsthtoankTongchi());
           hsBK.setHsthtoankTylebh(this.hsttk.getHsthtoankTylebh());
           hsBK.setHsthtoankVtth(this.hsttk.getHsthtoankVtth());
           hsBK.setHsthtoankVtthndm(this.hsttk.getHsthtoankVtthndm());
           hsBK.setHsthtoankXetgiam(this.hsttk.getHsthtoankXetgiam());
           hsBK.setHsthtoankXntdcn(this.hsttk.getHsthtoankXntdcn());
           hsBK.setTiepdonMa(this.hsttk.getTiepdonMa());
           hsBK.setHsthtoankMa(this.hsttk.getHsthtoankMa());
           HsThtoankBackupDelegate.getInstance().create(hsBK);
         }
       }
       this.conlai = new Double(0.0D);
       this.conlai_hid = new Double(0.0D);
       if ((this.maFinish != null) && (!this.maFinish.equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(true);
         this.hienThiHuyPhieu = Boolean.valueOf(true);
         this.hienThiInPhieuNgT = Boolean.valueOf(this.cvnt != null);
         this.hsThtoan.setHsthtoanMa(this.maFinish);
       }
       else if ((this.maFinish != null) && (this.maFinish.equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);

         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(true);
         this.hienThiHuyPhieu = Boolean.valueOf(true);
         this.hienThiInPhieuNgT = Boolean.valueOf(this.cvnt != null);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         this.hienThiInPhieuNgT = Boolean.valueOf(false);
       }
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
       log.error("*************loi***********" + e.toString());
       e.printStackTrace();
     }
     log.info("*****End Ghi nhan() *****");
     this.reportFinished = "";
   }

   private void removeIfNullForHsbaChuyenVien(HsbaChuyenVien hsbaChuyenvien2)
   {
     log.info("Begin removeIfNullForHsbaChuyenVien: " + hsbaChuyenvien2);
     try
     {
       if ("".equals(Utils.reFactorString(hsbaChuyenvien2.getHsbacvBschuyen(true).getDtdmnhanvienMa())))
       {
         hsbaChuyenvien2.setHsbacvBschuyen(null);
         log.info("Bac si chuyen  null");
       }
       else
       {
         log.info("bac si chuyen khong null");
       }
       if ("".equals(Utils.reFactorString(hsbaChuyenvien2.getHsbacvChvienden(true).getDmbenhvienMa())))
       {
         hsbaChuyenvien2.setHsbacvChvienden(null);
         log.info("benh vien den null");
       }
       else
       {
         log.info("benh vien den khong null");
       }
       if ("".equals(Utils.reFactorString(hsbaChuyenvien2.getHsbacvLydochuyenv(true).getDtdmlydocvMa())))
       {
         hsbaChuyenvien2.setHsbacvLydochuyenv(null);
         log.info("ly do chuyen vien null");
       }
       else
       {
         log.info("ly do chuyen vien khong null");
       }
       if ("".equals(Utils.reFactorString(hsbaChuyenvien2.getNhanvienMa(true).getDtdmnhanvienMa())))
       {
         hsbaChuyenvien2.setNhanvienMa(null);
         log.info("nhan vien null");
       }
       else
       {
         log.info("nhan vien khong null");
       }
     }
     catch (Exception e)
     {
       log.error("Loi trong removeIfNullForHsbaChuyenVien: " + e);
     }
     log.info("End removeIfNullForHsbaChuyenVien");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       ResetForm();
       this.resultHidden = "";
       this.maFinish = "";
       this.hienThiGhiNhan = Boolean.valueOf(false);
       this.hienThiInPhieu = Boolean.valueOf(false);
       this.hienThiHuyPhieu = Boolean.valueOf(false);
       this.hienThiInPhieuNgT = Boolean.valueOf(false);
       this.reportFinished = "";
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   public void XuatReport()
   {
     this.reportTypeVP = "ThanhToanRaVien";
     log.info("Vao Method Bao cao thanh toan ra vien");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");

       Map<String, Object> params = new HashMap();
       JasperReport jasperReport;
       if ((this.hsba.getDoituongMa() != null) && ("TE".equals(this.hsba.getDoituongMa().getDmdoituongMa())))
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport1.jrxml";
         String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport2.jrxml";
         String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport3.jrxml";
         String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport4.jrxml";
         String sub5Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport5.jrxml";
         String sub6Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bangkechitietchiphikhamchuabenhchotreemduoi6tuoi_subreport6.jrxml";


         log.info("da thay file templete " + pathTemplate);
         log.info("da thay file sub 0 templete " + sub0Template);
         log.info("da thay file sub 1 templete " + sub1Template);
         log.info("da thay file sub 2 templete " + sub2Template);
         log.info("da thay file sub 3 templete " + sub3Template);
         log.info("da thay file sub 4 templete " + sub4Template);
         log.info("da thay file sub 5 templete " + sub5Template);
         log.info("da thay file sub 6 templete " + sub6Template);


         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
         JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
         JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
         JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);
         JasperReport sub5Report = JasperCompileManager.compileReport(sub5Template);
         JasperReport sub6Report = JasperCompileManager.compileReport(sub6Template);


         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
         params.put("sub2", sub2Report);
         params.put("sub3", sub3Report);
         params.put("sub4", sub4Report);
         params.put("sub5", sub5Report);
         params.put("sub6", sub6Report);
       }
       else
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_THUOC.jrxml";
         String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_VTTH.jrxml";
         String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_MAU.jrxml";
         String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_XN_TDCN.jrxml";
         String sub5Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_PT.jrxml";
         String sub6Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_GIUONG.jrxml";
         String sub7Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_TT.jrxml";
         String sub8Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_DVKTC.jrxml";
         String sub9Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_CDHA.jrxml";
         String sub10Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_CPVC.jrxml";
         String sub11Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanRVnoitru_subreport_TONGKET.jrxml";

         String subBienLai1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/HoaDon_sub1.jrxml";
         String subBienLai2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/HoaDon_sub2.jrxml";

         log.info("da thay file templete " + pathTemplate);
         log.info("da thay file sub 0 templete " + sub0Template);
         log.info("da thay file sub 1 templete " + sub1Template);
         log.info("da thay file sub 2 templete " + sub2Template);
         log.info("da thay file sub 3 templete " + sub3Template);

         log.info("da thay file sub 4 templete " + sub4Template);
         log.info("da thay file sub 5 templete " + sub5Template);
         log.info("da thay file sub 6 templete " + sub6Template);
         log.info("da thay file sub 7 templete " + sub7Template);
         log.info("da thay file sub 8 templete " + sub8Template);
         log.info("da thay file sub 9 templete " + sub9Template);
         log.info("da thay file sub 10 templete " + sub10Template);
         log.info("da thay file sub 11 templete " + sub11Template);


         log.info("da thay file sub subBienLai1Template " + subBienLai1Template);
         log.info("da thay file sub subBienLai2Template " + subBienLai2Template);

         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
         JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
         JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

         JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);
         JasperReport sub5Report = JasperCompileManager.compileReport(sub5Template);
         JasperReport sub6Report = JasperCompileManager.compileReport(sub6Template);
         JasperReport sub7Report = JasperCompileManager.compileReport(sub7Template);
         JasperReport sub8Report = JasperCompileManager.compileReport(sub8Template);
         JasperReport sub9Report = JasperCompileManager.compileReport(sub9Template);
         JasperReport sub10Report = JasperCompileManager.compileReport(sub10Template);
         JasperReport sub11Report = JasperCompileManager.compileReport(sub11Template);


         JasperReport subBienLai1Report = JasperCompileManager.compileReport(subBienLai1Template);
         JasperReport subBienLai2Report = JasperCompileManager.compileReport(subBienLai2Template);


         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
         params.put("sub2", sub2Report);
         params.put("sub3", sub3Report);

         params.put("sub4", sub4Report);
         params.put("sub5", sub5Report);
         params.put("sub6", sub6Report);
         params.put("sub7", sub7Report);
         params.put("sub8", sub8Report);
         params.put("sub9", sub9Report);
         params.put("sub10", sub10Report);
         params.put("sub11", sub11Report);

         params.put("subBienLai1Report", subBienLai1Report);
         params.put("subBienLai2Report", subBienLai2Report);
       }
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       log.info("da thay file template ");

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("REPORT_HOADON", IConstantsRes.REPORT_HOADON);


       params.put("SOHOSOBA", this.hsba.getHsbaSovaovien());
       params.put("HOTENBN", this.benhNhan.getBenhnhanHoten());
       params.put("SOPHIEU", this.hsThtoan.getHsthtoanMa());
       if (this.hsThtoan.getHsthtoanLidothanhtoan() != null) {}
       params.put("LIDOTHANHTOAN", this.hsThtoan.getHsthtoanLidothanhtoan());
       if ((this.hsba.getDoituongMa() != null) && ("TE".equals(this.hsba.getDoituongMa().getDmdoituongMa())))
       {
         if (this.hsba.getHsbaMachdravien() != null)
         {
           DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
           DmBenhIcd benh = (DmBenhIcd)dele.findByMa(this.hsba.getHsbaMachdravien().getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
           if (benh != null)
           {
             params.put("CHANDOAN", benh.getDmbenhicdTen());
             params.put("MA_CHANDOAN", benh.getDmbenhicdMa());
           }
           else
           {
             params.put("CHANDOAN", "");
             params.put("MA_CHANDOAN", "");
           }
         }
         else
         {
           params.put("CHANDOAN", "");
           params.put("MA_CHANDOAN", "");
         }
         if ((this.hsba.getHsbaKetqua() != null) && (!this.hsba.getHsbaKetqua().getDmkqdtMa().equals("")))
         {
           DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
           DmKetQuaDieuTri ketQuaDieuTri = (DmKetQuaDieuTri)dele.findByMa(this.hsba.getHsbaKetqua().getDmkqdtMa(), "DmKetQuaDieuTri", "dmkqdtMa");
           params.put("KETQUADIEUTRI", ketQuaDieuTri.getDmkqdtTen());
         }
         params.put("Tuoi", this.benhNhan.getBenhnhanTuoi());

         params.put("DonViTuoi", this.benhNhan.getBenhnhanDonvituoi() == null ? new Integer(1) : new Integer(this.benhNhan.getBenhnhanDonvituoi().shortValue()));




         log.info("hsbaBhyt:" + this.hsbaBhyt);
         if (this.hsbaBhyt != null)
         {
           params.put("MATHEKCB", this.hsbaBhyt.getHsbabhytSothete() == null ? "" : this.hsbaBhyt.getHsbabhytSothete());
           params.put("SOGIAYKHAISINH", this.hsbaBhyt.getHsbabhytKhaisinh() == null ? "" : this.hsbaBhyt.getHsbabhytKhaisinh());
           params.put("SOGIAYCHUNGSINH", this.hsbaBhyt.getHsbabhytChungsinh() == null ? "" : this.hsbaBhyt.getHsbabhytChungsinh());
           params.put("GIAMHO", this.hsbaBhyt.getHsbabhytGiamho() == null ? "" : this.hsbaBhyt.getHsbabhytGiamho());
           params.put("GIAYCHUNGNHANUBNNXA", this.hsbaBhyt.getHsbabhytCnxa() == null ? "" : this.hsbaBhyt.getHsbabhytCnxa());

           log.info("hsbaBhyt.getHsbabhytSothete():" + this.hsbaBhyt.getHsbabhytSothete());
           log.info("hsbaBhyt.getHsbabhytKhaisinh():" + this.hsbaBhyt.getHsbabhytKhaisinh());
           log.info("hsbaBhyt.getHsbabhytChungsinh():" + this.hsbaBhyt.getHsbabhytChungsinh());
           log.info("hsbaBhyt.getHsbabhytGiamho():" + this.hsbaBhyt.getHsbabhytGiamho());
           log.info("hsbaBhyt.getHsbabhytCnxa():" + this.hsbaBhyt.getHsbabhytCnxa());
         }
         log.info("_______________________________________________________");
         params.put("NgoaiTru", "");
         params.put("NoiTru", "");
         params.put("NoiKhoa", "");
         params.put("NgoaiKhoa", "");
         params.put("YHDT", "");
       }
       else if ((this.hsba.getHsbaMachdravien() != null) && (!this.hsba.getHsbaMachdravien().getDmbenhicdMa().equals("")))
       {
         DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
         DmBenhIcd benh = (DmBenhIcd)dele.findByMa(this.hsba.getHsbaMachdravien().getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
         params.put("CHANDOAN", benh.getDmbenhicdMa() + " - " + benh.getDmbenhicdTen());
       }
       else
       {
         params.put("CHANDOAN", "");
       }
       if ((this.hsbaBhyt.getHsbabhytSothebh() != null) && (!this.hsbaBhyt.getHsbabhytSothebh().equals("")))
       {
         String maBV = this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         params.put("MATHEBHYT", this.hsbaBhyt.getHsbabhytSothebh() + "-" + maBV.replace(".", "-"));

         log.info("MATHEBHYT:" + params.get("MATHEBHYT"));
       }
       else
       {
         params.put("MATHEBHYT", "");
       }
       double tongchi = (this.hsThtoan.getHsthtoanNdm() == null ? 0.0D : this.hsThtoan.getHsthtoanNdm().doubleValue()) + (this.hsThtoan.getHsthtoanDm() == null ? 0.0D : this.hsThtoan.getHsthtoanDm().doubleValue());
       log.info("tong chi " + tongchi);
       params.put("TONGCHIPHI", Double.valueOf(tongchi));
       params.put("BANGCHU1", Utils.NumberToString(tongchi));
       params.put("BHCHTHANHTOAN", this.hsThtoan.getHsthtoanBhyt());
       log.info("BHXH thanh toan  " + this.hsThtoan.getHsthtoanBhxh());
       params.put("BANGCHU3", Utils.NumberToString(this.hsThtoan.getHsthtoanBhyt() == null ? 0.0D : this.hsThtoan.getHsthtoanBhyt().doubleValue()));
       params.put("NGUOIBENHTRA", this.hsThtoan.getHsthtoanThtoan());
       double nguoibenhtra = this.hsThtoan.getHsthtoanThtoan() == null ? 0.0D : this.hsThtoan.getHsthtoanThtoan().doubleValue();
       log.info("nguoi benh tra  " + nguoibenhtra);
       if (this.hsThtoan.getHsthtoanThtoan().doubleValue() < 0.0D)
       {
         nguoibenhtra = -nguoibenhtra;
         params.put("BANGCHU2", "(" + Utils.NumberToString(nguoibenhtra) + ")");
       }
       else
       {
         params.put("BANGCHU2", Utils.NumberToString(nguoibenhtra));
       }
       params.put("TAMUNG", this.hsThtoan.getHsthtoanTamung());
       params.put("HOANUNG", this.hsThtoan.getHsthtoanHoanung());

       params.put("KYHIEU", this.hsThtoan.getHsthtoanKyhieu());
       params.put("QUYEN", this.hsThtoan.getHsthtoanQuyen());

       Date currentDate = new Date();
       if ((this.benhNhan.getBenhnhanTuoi() != null) && (this.benhNhan.getBenhnhanDonvituoi() != null))
       {
         int ns;

         if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 1) {
           ns = currentDate.getYear() + 1900 - this.benhNhan.getBenhnhanTuoi().intValue();
         } else {
           ns = currentDate.getYear() + 1900;
         }
         params.put("namsinh", String.valueOf(ns));
       }
       params.put("GIOITINH", this.benhNhan.getDmgtMaso(true).getDmgtTen());
       String diachistr = "";
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhNhan.getBenhnhanDiachi() + ",";
       }
       if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhNhan.getXaMa(true).getDmxaTen() + ",";
       }
       if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhNhan.getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (this.hsbaBhyt != null)
       {
         if (this.hsbaBhyt.getHsbabhytGiatri0() != null) {
           params.put("GTTU", sdf.format(this.hsbaBhyt.getHsbabhytGiatri0()));
         }
         if (this.hsbaBhyt.getHsbabhytGiatri1() != null) {
           params.put("GTDEN", sdf.format(this.hsbaBhyt.getHsbabhytGiatri1()));
         }
       }
       params.put("NOIDANGKY", this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
       log.info("NOIDANGKY:" + params.get("NOIDANGKY"));

       params.put("NOICAPTHE", this.hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
       params.put("NOIGIOITHIEU", this.hsba.getHsbaDonvigoi(true).getDmbenhvienTen());
       params.put("KHOA", this.hsba.getHsbaKhoadangdt(true).getDmkhoaTen());
       if (this.hsba.getHsbaNgaygiovaov() != null) {
         params.put("NGAYVAOVIEN", this.hsba.getHsbaNgaygiovaov());
       }
       if (this.hsba.getHsbaNgaygiorav() != null) {
         params.put("NGAYRAVIEN", this.hsba.getHsbaNgaygiorav());
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       String bienlai = "";
       if (this.hsThtoan.getHsthtoanKyhieu() != null) {
         bienlai = bienlai + this.hsThtoan.getHsthtoanKyhieu();
       }
       if (this.hsThtoan.getHsthtoanQuyen() != null) {
         if (!"".equals(bienlai)) {
           bienlai = bienlai + "/" + this.hsThtoan.getHsthtoanQuyen();
         } else {
           bienlai = bienlai + this.hsThtoan.getHsthtoanQuyen();
         }
       }
       if (this.hsThtoan.getHsthtoanBienlai() != null) {
         if (!"".equals(bienlai)) {
           bienlai = bienlai + "/" + this.hsThtoan.getHsthtoanBienlai();
         } else {
           bienlai = bienlai + this.hsThtoan.getHsthtoanBienlai();
         }
       }
       params.put("BIENLAI", bienlai);
       if ((this.hsba.getDoituongMa() != null) && ("TP".equals(this.hsba.getDoituongMa().getDmdoituongMa()))) {
         params.put("DOITUONG_MA", "TP");
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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "ThanhToanRaVien");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       setIndex(getIndex() + 1);
       log.info("Index :" + getIndex());
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
     log.info("Thoat Method XuatReport");
   }

   public String thuchienAction(int type)
   {
     return XuatPhieuThanhToan(type);
   }

   public String XuatPhieuThanhToanNgoaiTru()
   {
     log.info("begin XuatPhieuThanhToanNgoaiTru() cvnt = " + this.cvnt);
     TiepDon tiepdon = this.cvnt.getTiepDon();
     if (tiepdon == null) {
       return null;
     }
     BenhNhan benhnhan = tiepdon.getBenhnhanMa();
     ThamKham thamkham = new ThamKham();
     if ((tiepdon.getTiepdonBankham() != null) && (tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null) && (tiepdon.getTiepdonMa() != null))
     {
       ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
       thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
     }
     if ((benhnhan == null) || (thamkham == null)) {
       return null;
     }
     this.reportTypeVP = "PhieuThanhToanKCBNgoaiTru_2";

     String baocao1 = null;
     try
     {
       log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("MATIEPDON", tiepdon.getTiepdonMa());
       params.put("HOTENBN", benhnhan.getBenhnhanHoten());

       String diachistr = "";
       if (benhnhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + benhnhan.getBenhnhanDiachi() + ", ";
       }
       if (benhnhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + benhnhan.getXaMa(true).getDmxaTen() + ", ";
       }
       if (benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + benhnhan.getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (benhnhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + benhnhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         params.put("BHYT_CO", "X");
         if (((tiepdon.getTiepdonTuyen() != null) && (tiepdon.getTiepdonTuyen().toString().equals("1"))) || ((tiepdon.getTiepdonCoGiayGioiThieu() != null) && (tiepdon.getTiepdonCoGiayGioiThieu().booleanValue()))) {
           params.put("DUNGTUYEN", "X");
         } else {
           params.put("TRAITUYEN", "X");
         }
       }
       else
       {
         params.put("BHYT_KO", "X");
       }
       if (tiepdon.getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(tiepdon.getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (tiepdon.getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(tiepdon.getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((tiepdon.getTiepdonSothebh() != null) && (!tiepdon.getTiepdonSothebh().equals("")) && (tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null) && (!tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")) && (tiepdon.getKcbbhytMa(true).getDmbenhvienMa() != null))
       {
         params.put("MATHEBHYT", tiepdon.getTiepdonSothebh());
         params.put("MABENHVIEN", tiepdon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
       }
       else
       {
         params.put("MABENHVIEN", "");
         params.put("MATHEBHYT", "");
       }
       if (tiepdon.getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", tiepdon.getKcbbhytMa(true).getDmbenhvienTen());
       }
       if (tiepdon.getTinhbhytMa(true).getDmtinhTen() != null) {
         params.put("NOICAPTHE", tiepdon.getTinhbhytMa(true).getDmtinhTen());
       }
       if (tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMa() != null) {
         params.put("NOIGIOITHIEU", tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen());
       }
       Calendar cal = Calendar.getInstance();
       cal.setTime(thamkham.getThamkhamNgaygio());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));

       params.put("LYDOVAOVIEN", tiepdon.getTiepdonLydovaov());

       HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = new HsThtoank();
       hsttk = thanhToanDel.findBytiepdonMaLast(tiepdon.getTiepdonMa());
       if ((hsttk == null) || ((hsttk.getHsthtoankDatt() != null) && (!hsttk.getHsthtoankDatt().booleanValue())))
       {
         hsttk = new HsThtoank();
         hsttk.setTiepdonMa(tiepdon);

         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(thamkham, hsttk, Boolean.valueOf(false), null, null);
         Utils.setInforForHsThToan(hsttk);
       }
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());

       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
       params.put("NGUONKHAC", hsttk.getHsthtoankNguonkhactra());
       if (hsttk.getHsthtoankNgaygiott() != null)
       {
         params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
         if (thamkham.getThamkhamNgaygio() != null) {
           params.put("SONGAYDT", Long.valueOf(Utils.getSoNgayDieuTri(thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott())));
         }
       }
       if ((tiepdon.getTiepdonBankham() != null) &&
         (tiepdon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")))
       {
         params.put("CAPCUU", "X");
         params.put("DUNGTUYEN", "");
         params.put("TRAITUYEN", "");
       }
       params.put("PHIEUSO", hsttk.getHsthtoankMa());
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
       params.put("TYLEBH", hsttk.getHsthtoankTylebh());
       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       if ("MP".equals(tiepdon.getDoituongMa(true).getDmdoituongMa())) {
         tyleBNtra = "0";
       }
       params.put("PHANTRAMBNTRA", tyleBNtra);

       params.put("BIENLAISO", "");

       String namsinh = "";
       if (tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh() != null) {
         namsinh = sdf.format(tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
       } else {
         namsinh = tiepdon.getBenhnhanMa(true).getBenhnhanNamsinh();
       }
       params.put("namsinh", namsinh);

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

       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tiepdon.getTiepdonMa());


       StringBuffer bufStr = new StringBuffer();
       Double tongTienDV = Double.valueOf(0.0D);
       List<Integer> listMasoKhoa;
       if ((listTk != null) && (listTk.size() > 0))
       {
         listMasoKhoa = new ArrayList(listTk.size());
         for (ThamKham each : listTk) {
           if (each.getThamkhamBankham().getDmkhoaMaso() != null) {
             if (!listMasoKhoa.contains(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso()))
             {
               listMasoKhoa.add(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso());
               if (bufStr.length() > 0) {
                 bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
               } else {
                 bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
               }
             }
           }
         }
       }
       List<ClsKham> listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(tiepdon.getTiepdonMa());
       if ((listClsTmp != null) && (listClsTmp.size() > 0)) {
         for (ClsKham eachCls : listClsTmp) {
           if (((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true)) || ((eachCls.getClskhamNdm() != null) && (eachCls.getClskhamNdm().booleanValue() == true) && (eachCls.getClskhamPhandv() != null))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachCls.getClskhamPhandv().doubleValue());
           }
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "1");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamDongia().doubleValue());
           }
         }
       }
       listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(tiepdon.getTiepdonMa(), "3");
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
         if ((tiepdon.getTiepdonKhaisinh() != null) && (!tiepdon.getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonKhaisinh();
         }
       }
       else if ((tiepdon.getTiepdonKhaisinh() != null) && (!tiepdon.getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + tiepdon.getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((tiepdon.getTiepdonChungsinh() != null) && (!tiepdon.getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = tiepdon.getTiepdonChungsinh();
         }
       }
       else if ((tiepdon.getTiepdonChungsinh() != null) && (!tiepdon.getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + tiepdon.getTiepdonChungsinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       String soTheNgheo = tiepdon.getTiepdonThengheo();
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
       DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(tiepdon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
       if (gioi != null) {
         params.put("GIOI", gioi.getDmgtTen());
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       log.info("tongTienDV = " + tongTienDV);
       JasperReport jasperReport;
       if (tongTienDV.doubleValue() > 0.0D)
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
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
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";

         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "PhieuThanhToanKCB");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       setIndex(getIndex() + 1);
       log.info("Index :" + getIndex());
       if (conn != null)
       {
         conn.close();
         conn = null;
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatPhieuThanhToan!!!");
       e.printStackTrace();
       return null;
     }
     log.info("Thoat Method XuatPhieuThanhToan");
     if (this.nhanVienThungan == null) {
       refreshNhanVienThuNgan();
     }
     return "B3360_Chonmenuxuattaptin";
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B3241_Thanhtoanravien";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);








     this.tuoi = String.valueOf(this.benhNhan.getBenhnhanTuoi());
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
     if ((this.hsbaBhyt.getHsbabhytSothebh() != null) || (this.hsbaBhyt.getHsbabhytSothengheo() != null))
     {
       if (this.hsbaBhyt.getHsbabhytSothebh() != null) {
         this.soThe = this.hsbaBhyt.getHsbabhytSothebh();
       } else {
         this.soThe = this.hsbaBhyt.getHsbabhytSothengheo();
       }
     }
     else {
       this.soThe = "";
     }
     if (this.hsba.getHsbaNgaygiovaov() != null) {
       this.ngayNhapvien = formatter.format(Long.valueOf(this.hsba.getHsbaNgaygiovaov().getTime()));
     } else {
       this.ngayNhapvien = "";
     }
     if (this.hsba.getHsbaNgaygiorav() != null) {
       this.ngayRavien = formatter.format(Long.valueOf(this.hsba.getHsbaNgaygiorav().getTime()));
     } else {
       this.ngayRavien = "";
     }
     if ((this.hsThtoan != null) && (this.hsThtoan.getHsthtoanNgaytt() != null))
     {
       Date ngayGioTT = this.hsThtoan.getHsthtoanNgaytt();
       this.gioThanhToan = Utils.getGioPhut(ngayGioTT);
       this.ngayTtoan = formatter.format(ngayGioTT);
     }
     log.info("End setOtherValue()");
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.hsba.getHsbaSovaovien() == null) || (this.hsba.getHsbaSovaovien().equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsba.getHsbaSovaovien() });
         hasNoKhoaOrSoVaoVien();
         return;
       }
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       Hsba hsbaCur = hsbaDelegate.find(this.hsba.getHsbaSovaovien());
       if (hsbaCur == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsba.getHsbaSovaovien() });
         ResetForm();
         return;
       }
       this.hsba = hsbaCur;
       if (this.hsba.getHsbaKhoadangdt() != null) {
         this.khoaMa = this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa();
       }
       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();

       log.info("Begining displayInfor()");

       List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
       this.cacKhoadt = "";
       for (HsbaKhoa hK : lstHsbaKhoa) {
         this.cacKhoadt = (this.cacKhoadt + hK.getKhoaMa().getDmkhoaMa() + "   ");
       }
       if (this.hsba.getBenhnhanMa() != null) {
         this.benhNhan = this.hsba.getBenhnhanMa();
       } else {
         this.benhNhan = new BenhNhan();
       }
       if ((this.hsba.getHsbaSovaovien() != null) && (!this.hsba.getHsbaSovaovien().equals("")))
       {
         try
         {
           HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
           HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hsba.getHsbaSovaovien());
           if (hsbaBhyt_temp != null) {
             this.hsbaBhyt = hsbaBhyt_temp;
           } else {
             this.hsbaBhyt = new HsbaBhyt();
           }
         }
         catch (Exception ex)
         {
           log.error("Loi trong khi load HSBABHYT : " + ex.toString());
         }
         try
         {
           HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate.getInstance();

           HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
           if (hsbaChuyenVien_temp != null) {
             this.hsbaChuyenvien = hsbaChuyenVien_temp;
           } else {
             this.hsbaChuyenvien = new HsbaChuyenVien();
           }
         }
         catch (Exception ex)
         {
           log.error("Loi trong khi load Hsba chuyen vien : " + ex.toString());
         }
       }
       else
       {
         this.hsbaBhyt = new HsbaBhyt();

         this.hsbaChuyenvien = new HsbaChuyenVien();
       }
       try
       {
         HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

         HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(this.hsba.getHsbaSovaovien());

         this.cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(this.hsba.getHsbaSovaovien());
         if ((hsbaHsThtoan_temp != null) && (hsbaHsThtoan_temp.getHsthtoanNgaytt() != null))
         {
           this.hsThtoan = hsbaHsThtoan_temp;






           this.ungTra = Double.valueOf((this.hsThtoan.getHsthtoanTamung() == null ? 0.0D : this.hsThtoan.getHsthtoanTamung().doubleValue()) - (this.hsThtoan.getHsthtoanHoanung() == null ? 0.0D : this.hsThtoan.getHsthtoanHoanung().doubleValue()));
           if ((this.hsThtoan.getHsthtoanLidothanhtoan() == null) || (this.hsThtoan.getHsthtoanLidothanhtoan().equals(""))) {
             this.sLiDoTT = IConstantsRes.VIEN_PHI;
           } else {
             this.sLiDoTT = this.hsThtoan.getHsthtoanLidothanhtoan();
           }
           if (this.hsThtoan.getHsthtoanThungan() != null) {
             this.nhanVienThungan = this.hsThtoan.getHsthtoanThungan();
           } else {
             this.nhanVienThungan = new DtDmNhanVien();
           }
           log.error("Tim thay ho so thanh toan : " + this.hsThtoan);
           if (this.cvnt != null)
           {
             this.hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(this.cvnt.getTiepDon().getTiepdonMa());
             this.ungTra = Double.valueOf(this.ungTra.doubleValue() + ((this.hsttk.getHsthtoankTamung() == null ? 0.0D : this.hsttk.getHsthtoankTamung().doubleValue()) + (this.hsttk.getHsthtoankHoanung() == null ? 0.0D : this.hsttk.getHsthtoankHoanung().doubleValue())));
           }
           this.conlai = new Double(0.0D);
           this.conlai_hid = new Double(0.0D);
         }
         else
         {
           this.hsThtoan = new HsThtoan();
           this.hsThtoan.setHsbaSovaovien(this.hsba);
           this.sLiDoTT = IConstantsRes.VIEN_PHI;

           HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.hsba);
           hsthtoanUtil.tinhToanChoHSTT(this.hsThtoan);

           Utils.setInforForHsThToan(this.hsThtoan);

           this.hsttk = hsthtoanUtil.getHsttk();
           if (this.hsttk != null) {
             Utils.setInforForHsThToan(this.hsttk);
           }
           this.ungTra = Double.valueOf(this.hsThtoan.getHsthtoanTamung().doubleValue() - this.hsThtoan.getHsthtoanHoanung().doubleValue());
           if (this.cvnt != null) {
             this.ungTra = Double.valueOf(this.ungTra.doubleValue() + ((this.hsttk.getHsthtoankTamung() == null ? 0.0D : this.hsttk.getHsthtoankTamung().doubleValue()) + (this.hsttk.getHsthtoankHoanung() == null ? 0.0D : this.hsttk.getHsthtoankHoanung().doubleValue())));
           }
           log.info("ungTra:" + this.ungTra);
           this.conlai = Double.valueOf(this.hsThtoan.getHsthtoanThtoan().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue()));
           this.conlai_hid = Double.valueOf(this.hsThtoan.getHsthtoanThtoan().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThtoan().doubleValue()));
         }
         this.thuocNDM = Double.valueOf((this.hsThtoan.getHsthtoanThuocndm() == null ? 0.0D : this.hsThtoan.getHsthtoanThuocndm().doubleValue()) + (this.hsttk.getHsthtoankThuocndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuocndm().doubleValue()));
         this.thuocTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanThuoc() == null ? 0.0D : this.hsThtoan.getHsthtoanThuoc().doubleValue()) + (this.hsttk.getHsthtoankThuoc() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuoc().doubleValue()));
         this.vTTHTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanVtth() == null ? 0.0D : this.hsThtoan.getHsthtoanVtth().doubleValue()) + (this.hsttk.getHsthtoankVtth() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtth().doubleValue()));
         this.vTTHNDM = Double.valueOf((this.hsThtoan.getHsthtoanVtthndm() == null ? 0.0D : this.hsThtoan.getHsthtoanVtthndm().doubleValue()) + (this.hsttk.getHsthtoankVtthndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtthndm().doubleValue()));
         this.cLSMauTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanCls() == null ? 0.0D : this.hsThtoan.getHsthtoanCls().doubleValue()) + (this.hsttk.getHsthtoankCls() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankCls().doubleValue()));
         this.clsMauNDM = Double.valueOf((this.hsThtoan.getHsthtoanClsndm() == null ? 0.0D : this.hsThtoan.getHsthtoanClsndm().doubleValue()) + (this.hsttk.getHsthtoankClsndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankClsndm().doubleValue()));
         this.tongTrongDM = Double.valueOf(this.hsThtoan.getHsthtoanDm().doubleValue() + (this.hsttk.getHsthtoankThuoc() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuoc().doubleValue()) + (this.hsttk.getHsthtoankVtth() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtth().doubleValue()) + (this.hsttk.getHsthtoankCls() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankCls().doubleValue()));



         this.tongNDM = Double.valueOf(this.hsThtoan.getHsthtoanNdm().doubleValue() + (this.hsttk.getHsthtoankThuocndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuocndm().doubleValue()) + (this.hsttk.getHsthtoankVtthndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtthndm().doubleValue()) + (this.hsttk.getHsthtoankClsndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankClsndm().doubleValue()));




         this.benhnhanTra = Double.valueOf(this.hsThtoan.getHsthtoanBntra().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue())));

         this.miengiam = Double.valueOf(this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
         this.nSKhongThu = Double.valueOf(this.hsThtoan.getHsthtoanKhongthu().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankKhongthu().doubleValue()));
         this.benhnhanTra_hid = Double.valueOf(this.hsThtoan.getHsthtoanBntra().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue())));
         this.nguonkhactra = this.hsThtoan.getHsthtoanNguonkhactra();
         this.nguonkhactra_hid = this.hsThtoan.getHsthtoanNguonkhactra();
         this.benhnhanTra = Double.valueOf(this.benhnhanTra.doubleValue() - this.nguonkhactra.doubleValue());
         if (this.hsThtoan.getHsthtoanNgaytt() == null) {
           this.conlai = Double.valueOf(this.conlai.doubleValue() - this.nguonkhactra.doubleValue());
         }
         log.info("hsThtoan.getHsthtoanMa():" + this.hsThtoan.getHsthtoanMa());
         if ((this.hsThtoan != null) && (this.hsThtoan.getHsthtoanMa() != null) && (!this.hsThtoan.getHsthtoanMa().equals("")))
         {
           this.hienThiHuyPhieu = Boolean.valueOf(true);
           this.hienThiGhiNhan = Boolean.valueOf(false);
           this.hienThiInPhieu = Boolean.valueOf(true);
           this.hienThiInPhieuNgT = Boolean.valueOf(this.cvnt != null);
         }
         else
         {
           this.hienThiHuyPhieu = Boolean.valueOf(false);
           this.hienThiGhiNhan = Boolean.valueOf(true);
           this.hienThiInPhieu = Boolean.valueOf(false);
           this.hienThiInPhieuNgT = Boolean.valueOf(false);
         }
         ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();
         this.listClsMo = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());

         List<ClsMo> listClsMoTmp = new ArrayList();

         log.info("Begin check trung ClsMo ");
         for (ClsMo eachClsMo : this.listClsMo)
         {
           int trungIndex = checkTrungClsMo(listClsMoTmp, eachClsMo);
           if (trungIndex < 0) {
             listClsMoTmp.add(eachClsMo);
           } else {
             ((ClsMo)listClsMoTmp.get(trungIndex)).setClsmoLan(new Short("" + (((ClsMo)listClsMoTmp.get(trungIndex)).getClsmoLan().shortValue() + eachClsMo.getClsmoLan().shortValue())));
           }
         }
         log.info("End check trung ClsMo listClsMoTmp.size() = " + listClsMoTmp.size());
         ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
         this.listThuocNT = tntDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
         List<ThuocNoiTru> listTntTmp = new ArrayList();
         log.info("Begin check trung Thuoc noi tru ");
         for (ThuocNoiTru eachTnt : this.listThuocNT)
         {
           int trungIndex = checkTrungThuocNT(listTntTmp, eachTnt);
           if (trungIndex < 0)
           {
             eachTnt.setThuocnoitruSoluong(Double.valueOf(eachTnt.getThuocnoitruSoluong().doubleValue() - (eachTnt.getThuocnoitruTra() == null ? 0.0D : eachTnt.getThuocnoitruTra().doubleValue())));
             if (eachTnt.getThuocnoitruSoluong().doubleValue() > 0.0D) {
               listTntTmp.add(eachTnt);
             }
           }
           else
           {
             ((ThuocNoiTru)listTntTmp.get(trungIndex)).setThuocnoitruSoluong(new Double("" + (((ThuocNoiTru)listTntTmp.get(trungIndex)).getThuocnoitruSoluong().doubleValue() + (eachTnt.getThuocnoitruSoluong().doubleValue() - (eachTnt.getThuocnoitruTra() == null ? 0.0D : eachTnt.getThuocnoitruTra().doubleValue())))));

             ((ThuocNoiTru)listTntTmp.get(trungIndex)).setThuocnoitruThanhtien(Double.valueOf(((ThuocNoiTru)listTntTmp.get(trungIndex)).getThuocnoitruThanhtien().doubleValue() + eachTnt.getThuocnoitruThanhtien().doubleValue()));
           }
         }
         log.info("End check trung ThuocNT listTntTmp.size() = " + listTntTmp.size());
         this.listClsMo.clear();
         this.listThuocNT.clear();
         this.listClsMo = listClsMoTmp;
         this.listThuocNT = listTntTmp;
         if (this.cvnt != null) {}
         log.info("-----------------hsba.getHsbaNgaygiovaov222222222222():" + this.hsba.getHsbaNgaygiovaov());
       }
       catch (Exception ex)
       {
         log.error("Loi trong khi load ho so thanh toan : " + ex.toString());

         ex.printStackTrace();
       }
       setOtherValue();
       if ("xemchiphidieutri".equals(this.ten3241_3242))
       {
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         this.hienThiInPhieuNgT = Boolean.valueOf(false);
       }
       if (!this.hienThiGhiNhan.booleanValue()) {
         if ("xemchiphidieutri".equals(this.ten3241_3242))
         {
           this.hienThiInPhieu = Boolean.valueOf(true);
           this.hienThiInPhieuNgT = Boolean.valueOf(this.cvnt != null);
         }
         else
         {
           this.conlai = new Double(0.0D);
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
       }
       if ((this.hsba.getHsbaType() != null) && (IConstantsRes.CHUYEN_VAO_NOI_TRU_OPTION.equals("0")) &&
         (!this.hsba.getHsbaType().equals("")))
       {
         HsThtoank hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(this.hsba.getTiepdonMa());
         TiepDon td = TiepDonDelegate.getInstance().find(this.hsba.getTiepdonMa());
         String capcuu = "";
         if ((td != null) &&
           (td.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC"))) {
           capcuu = IConstantsRes.CAP_CUU;
         }
         if (hsttk != null) {
           FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU, new Object[] { capcuu + "(" + (hsttk.getHsthtoankNgaygiott() != null ? IConstantsRes.DA_THANH_TOAN : IConstantsRes.CHUA_THANH_TOAN) + ")", this.hsba.getTiepdonMa() });
         } else {
           FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THONG_TIN_NGOAI_TRU, new Object[] { capcuu + "(" + IConstantsRes.CHUA_THANH_TOAN + ")", this.hsba.getTiepdonMa() });
         }
       }
       log.info("End displayInfor(), hienThiInPhieuNgT = " + this.hienThiInPhieuNgT);
     }
     catch (Exception e)
     {
       log.error("error on function displayInfor" + e);
     }
     log.info("End displayInfor()");
   }

   private int checkTrungClsMo(List<ClsMo> listClsMoTmp, ClsMo eachClsMo)
   {
     if (listClsMoTmp.size() < 1) {
       return -1;
     }
     for (ClsMo clsMoTmp : listClsMoTmp) {
       if ((clsMoTmp.getClsmoMahang().getDtdmclsbgMa().equalsIgnoreCase(eachClsMo.getClsmoMahang().getDtdmclsbgMa())) &&
         (clsMoTmp.getClsmoDongia().equals(eachClsMo.getClsmoDongia())) && (((clsMoTmp.getClsmoMien() == null) && (eachClsMo.getClsmoMien() == null)) || ((clsMoTmp.getClsmoMien().equals(eachClsMo.getClsmoMien())) && (((clsMoTmp.getClsmoYeucau() == null) && (eachClsMo.getClsmoYeucau() == null)) || ((clsMoTmp.getClsmoYeucau().equals(eachClsMo.getClsmoYeucau())) && (((clsMoTmp.getClsmoKtcao() == null) && (eachClsMo.getClsmoKtcao() == null)) || (clsMoTmp.getClsmoKtcao().equals(eachClsMo.getClsmoKtcao())))))))) {
         return listClsMoTmp.indexOf(clsMoTmp);
       }
     }
     return -1;
   }

   private int checkTrungThuocNT(List<ThuocNoiTru> listTntTmp, ThuocNoiTru eachTnt)
   {
     if (listTntTmp.size() < 1) {
       return -1;
     }
     for (ThuocNoiTru tntTmp : listTntTmp) {
       if ((tntTmp.getThuocnoitruMathuoc().getDmthuocMa().equalsIgnoreCase(eachTnt.getThuocnoitruMathuoc().getDmthuocMa())) && (tntTmp.getThuocnoitruDongia().equals(eachTnt.getThuocnoitruDongia()))) {
         return listTntTmp.indexOf(tntTmp);
       }
     }
     return -1;
   }

   public void huyPhieu()
   {
     log.info("Begin huyPhieu, hsttk = " + this.hsttk + ", cvnt = " + this.cvnt);
     HsThtoanDelegate hsTTDelegate = HsThtoanDelegate.getInstance();
     String maPhieu = this.hsThtoan.getHsthtoanMa();



     this.hsThtoan.setHsthtoanNgaytt(null);
     this.hsThtoan.setHsthtoanDatt(null);
     hsTTDelegate.edit(this.hsThtoan);
     if (this.cvnt != null) {
       this.hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(this.cvnt.getTiepDon().getTiepdonMa());
     }
     if (this.hsttk != null)
     {
       ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
       List<ClsKham> listCls = clsKhamDel.findByMaPhieu(this.hsttk.getHsthtoankMa());
       log.info("listCls.size() = " + listCls.size());
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
       List<ThuocPhongKham> listTpk = tpkDelegate.findByMaPhieu(this.hsttk.getHsthtoankMa());
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
     }
     ResetForm();
     FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { maPhieu });
     if (this.nhanVienThungan == null) {
       refreshNhanVienThuNgan();
     }
   }

   public void findBySoPhieu()
   {
     log.info("Begin findBySoPhieu, phieu so = " + this.hsThtoan.getHsthtoanMa());
     String sophieu = this.hsThtoan.getHsthtoanMa();
     if ((this.hsThtoan.getHsthtoanMa() != null) && (this.hsThtoan.getHsthtoanMa().trim().length() > 0))
     {
       this.hsThtoan = HsThtoanDelegate.getInstance().find(this.hsThtoan.getHsthtoanMa());
       if (this.hsThtoan != null)
       {
         if (this.hsThtoan.getHsthtoanNgaytt() == null)
         {
           ResetForm();
           FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { sophieu });
           return;
         }
         this.khoaMa = (this.hsThtoan.getHsthtoanKhoa() != null ? this.hsThtoan.getHsthtoanKhoa().getDmkhoaMa() : "");
         this.hsba = this.hsThtoan.getHsbaSovaovien();
         this.benhNhan = this.hsba.getBenhnhanMa();
         this.nhanVienThungan = this.hsThtoan.getHsthtoanThungan();
         if (this.nhanVienThungan == null) {
           this.nhanVienThungan = new DtDmNhanVien();
         }
         HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
         HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hsba.getHsbaSovaovien());
         if (hsbaBhyt_temp != null) {
           this.hsbaBhyt = hsbaBhyt_temp;
         } else {
           this.hsbaBhyt = new HsbaBhyt();
         }
         HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
         List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
         this.cacKhoadt = "";
         for (HsbaKhoa hK : lstHsbaKhoa) {
           this.cacKhoadt = (this.cacKhoadt + hK.getKhoaMa().getDmkhoaMa() + "   ");
         }
         HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate.getInstance();
         HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
         if (hsbaChuyenVien_temp != null) {
           this.hsbaChuyenvien = hsbaChuyenVien_temp;
         } else {
           this.hsbaChuyenvien = new HsbaChuyenVien();
         }
         this.ungTra = Double.valueOf((this.hsThtoan.getHsthtoanTamung() == null ? 0.0D : this.hsThtoan.getHsthtoanTamung().doubleValue()) - (this.hsThtoan.getHsthtoanHoanung() == null ? 0.0D : this.hsThtoan.getHsthtoanHoanung().doubleValue()));


         this.cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(this.hsba.getHsbaSovaovien());
         if (this.cvnt != null)
         {
           this.hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(this.cvnt.getTiepDon().getTiepdonMa());
           this.ungTra = Double.valueOf(this.ungTra.doubleValue() + ((this.hsttk.getHsthtoankTamung() == null ? 0.0D : this.hsttk.getHsthtoankTamung().doubleValue()) + (this.hsttk.getHsthtoankHoanung() == null ? 0.0D : this.hsttk.getHsthtoankHoanung().doubleValue())));
         }
         this.thuocNDM = Double.valueOf((this.hsThtoan.getHsthtoanThuocndm() == null ? 0.0D : this.hsThtoan.getHsthtoanThuocndm().doubleValue()) + (this.hsttk.getHsthtoankThuocndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuocndm().doubleValue()));
         this.thuocTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanThuoc() == null ? 0.0D : this.hsThtoan.getHsthtoanThuoc().doubleValue()) + (this.hsttk.getHsthtoankThuoc() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuoc().doubleValue()));
         this.vTTHTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanVtth() == null ? 0.0D : this.hsThtoan.getHsthtoanVtth().doubleValue()) + (this.hsttk.getHsthtoankVtth() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtth().doubleValue()));
         this.vTTHNDM = Double.valueOf((this.hsThtoan.getHsthtoanVtthndm() == null ? 0.0D : this.hsThtoan.getHsthtoanVtthndm().doubleValue()) + (this.hsttk.getHsthtoankVtthndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtthndm().doubleValue()));
         this.cLSMauTrongDM = Double.valueOf((this.hsThtoan.getHsthtoanCls() == null ? 0.0D : this.hsThtoan.getHsthtoanCls().doubleValue()) + (this.hsttk.getHsthtoankCls() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankCls().doubleValue()));
         this.clsMauNDM = Double.valueOf((this.hsThtoan.getHsthtoanClsndm() == null ? 0.0D : this.hsThtoan.getHsthtoanClsndm().doubleValue()) + (this.hsttk.getHsthtoankClsndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankClsndm().doubleValue()));
         this.tongTrongDM = Double.valueOf(this.hsThtoan.getHsthtoanDm().doubleValue() + (this.hsttk.getHsthtoankThuoc() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuoc().doubleValue()) + (this.hsttk.getHsthtoankVtth() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtth().doubleValue()) + (this.hsttk.getHsthtoankCls() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankCls().doubleValue()));



         this.tongNDM = Double.valueOf(this.hsThtoan.getHsthtoanNdm().doubleValue() + (this.hsttk.getHsthtoankThuocndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankThuocndm().doubleValue()) + (this.hsttk.getHsthtoankVtthndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankVtthndm().doubleValue()) + (this.hsttk.getHsthtoankClsndm() == null ? 0.0D : this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankClsndm().doubleValue()));




         this.benhnhanTra = Double.valueOf(this.hsThtoan.getHsthtoanBntra().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue())));

         this.miengiam = Double.valueOf(this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue()));
         this.nSKhongThu = Double.valueOf(this.hsThtoan.getHsthtoanKhongthu().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankKhongthu().doubleValue()));
         this.conlai = new Double(0.0D);

         this.benhnhanTra_hid = Double.valueOf(this.hsThtoan.getHsthtoanBntra().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) - (this.hsThtoan.getHsthtoanMiendt().doubleValue() + (this.cvnt == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue())));
         this.nguonkhactra = this.hsThtoan.getHsthtoanNguonkhactra();
         this.nguonkhactra_hid = this.hsThtoan.getHsthtoanNguonkhactra();
         this.benhnhanTra = Double.valueOf(this.benhnhanTra.doubleValue() - this.nguonkhactra.doubleValue());
         this.conlai = new Double(0.0D);

         this.sLiDoTT = this.hsThtoan.getHsthtoanLidothanhtoan();
         setOtherValue();
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(true);
         this.hienThiInPhieu = Boolean.valueOf(true);
         this.hienThiInPhieuNgT = Boolean.valueOf(this.cvnt != null);
         if ("xemchiphidieutri".equals(this.ten3241_3242))
         {
           this.hienThiInPhieu = Boolean.valueOf(false);
           this.hienThiHuyPhieu = Boolean.valueOf(false);
           this.hienThiInPhieuNgT = Boolean.valueOf(false);
         }
         if ((!this.hienThiGhiNhan.booleanValue()) &&
           (!"xemchiphidieutri".equals(this.ten3241_3242)))
         {
           this.hsThtoan.setHsthtoanThtoan(new Double(0.0D));
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
         log.info("In findBySophieu(), hsba = " + this.hsba);
         if (this.hsba != null)
         {
           ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();
           this.listClsMo = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());

           ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
           this.listThuocNT = tntDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
           log.info("In findBySophieu(), listClsMo.size = " + this.listClsMo.size() + ", listThuocNT.size = " + this.listThuocNT.size());
         }
       }
       else
       {
         ResetForm();
         FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { sophieu });
       }
     }
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     this.benhNhan = new BenhNhan();

     this.hsba = new Hsba();

     this.hsbaBhyt = new HsbaBhyt();


     this.hsbaChuyenvien = new HsbaChuyenVien();

     this.hsThtoan = new HsThtoan();

     this.ngayNhapvien = "";
     this.ngayRavien = "";
     this.gioi = "";
     this.tuoi = "";
     this.soThe = "";
     this.sLiDoTT = "";
     this.ungTra = new Double(0.0D);

     this.cacKhoadt = "";

     this.hienThiGhiNhan = Boolean.valueOf(false);
     this.hienThiHuyPhieu = Boolean.valueOf(false);
     this.hienThiInPhieu = Boolean.valueOf(false);

     this.thuocTrongDM = new Double(0.0D);
     this.thuocNDM = new Double(0.0D);
     this.vTTHTrongDM = new Double(0.0D);
     this.vTTHNDM = new Double(0.0D);
     this.cLSMauTrongDM = new Double(0.0D);
     this.clsMauNDM = new Double(0.0D);
     this.tongTrongDM = new Double(0.0D);
     this.tongNDM = new Double(0.0D);
     this.nSKhongThu = new Double(0.0D);

     this.benhnhanTra = new Double(0.0D);
     this.conlai = new Double(0.0D);
     this.miengiam = new Double(0.0D);
     this.listClsMo = new ArrayList();
     this.listThuocNT = new ArrayList();
     log.info("End ResetForm(): ");
     this.nguonkhactra = new Double(0.0D);
     this.nguonkhactra_hid = new Double(0.0D);
     this.benhnhanTra_hid = new Double(0.0D);
     this.conlai_hid = new Double(0.0D);
     refreshNhanVienThuNgan();
     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayTtoan = formatter.format(cal.getTime());
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());
   }

   public String XuatPhieuThanhToan(int type)
   {
     log.info("begin XuatPhieuThanhToan() hsba = " + this.hsba);
     int IN_BIEN_LAI = 0;
     int IN_PHIEU_THANH_TOAN = 1;
     if (this.hsba == null) {
       return null;
     }
     this.reportTypeVP = "ThanhToanRaVien";
     log.info("Vao Method in phieu thanh toan ra vien");
     String baocao1 = null;
     ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();


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
     Double nguonkhac = Double.valueOf(0.0D);
     Double benhnhantra = Double.valueOf(0.0D);

     HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

     HsThtoan hsThtoan_tmp = hsttDelegate.findBySovaovien(this.hsba.getHsbaSovaovien());
     if ((hsThtoan_tmp != null) && (hsThtoan_tmp.getHsthtoanNgaytt() != null)) {
       this.hsThtoan = hsThtoan_tmp;
     }
     if (this.hsThtoan != null)
     {
       miengiam = Double.valueOf(this.hsThtoan.getHsthtoanMiendt() == null ? 0.0D : this.hsThtoan.getHsthtoanMiendt().doubleValue());
       nguonkhac = Double.valueOf(this.hsThtoan.getHsthtoanNguonkhactra() == null ? 0.0D : this.hsThtoan.getHsthtoanNguonkhactra().doubleValue());
       benhnhantra = Double.valueOf(this.hsThtoan.getHsthtoanBntra() == null ? 0.0D : this.hsThtoan.getHsthtoanBntra().doubleValue());
     }
     TiepDon tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(this.hsba.getTiepdonMa());


     this.listClsMo = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
     for (ClsMo eachCls : this.listClsMo)
     {
       curGia = Double.valueOf(eachCls.getClsmoDongiabntra() == null ? 0.0D : eachCls.getClsmoDongiabntra().doubleValue());
       curGiaDV = Double.valueOf(eachCls.getClsmoPhandv() == null ? 0.0D : eachCls.getClsmoPhandv().doubleValue());
       if ((eachCls.getClsmoYeucau() != null) && (eachCls.getClsmoYeucau().booleanValue() == true))
       {
         hasDV = true;
         if ((tiepdon.getDoituongMa() != null) &&
           (tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")))
         {
           curGia = Double.valueOf(eachCls.getClsmoMahang().getDtdmclsbgDongiamp() == null ? 0.0D : eachCls.getClsmoMahang().getDtdmclsbgDongiamp().doubleValue());

           curGiaDV = Double.valueOf((eachCls.getClsmoDongia() == null ? 0.0D : eachCls.getClsmoDongiabntra().doubleValue()) - curGia.doubleValue());
         }
       }
       tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGiaDV.doubleValue());
       if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA"))
       {
         mau = Double.valueOf(mau.doubleValue() + curGia.doubleValue());
         mauDV = Double.valueOf(mauDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN"))
       {
         xntdcn = Double.valueOf(xntdcn.doubleValue() + curGia.doubleValue());
         xntdcnDV = Double.valueOf(xntdcnDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD"))
       {
         cdha = Double.valueOf(cdha.doubleValue() + curGia.doubleValue());
         cdhaDV = Double.valueOf(cdhaDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT")) || (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")))
       {
         pttt = Double.valueOf(pttt.doubleValue() + curGia.doubleValue());
         ptttDV = Double.valueOf(ptttDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClsmoKtcao() != null) && (eachCls.getClsmoKtcao().booleanValue() == true))
       {
         ktc = Double.valueOf(ktc.doubleValue() + curGia.doubleValue());
         ktcDV = Double.valueOf(ktcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI"))
       {
         dvp = Double.valueOf(dvp.doubleValue() + curGia.doubleValue());
         dvpDV = Double.valueOf(dvpDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClsmoLoaicls() != null) && (eachCls.getClsmoLoaicls().getDtdmclsMaso().intValue() == 12))
       {
         vc = Double.valueOf(vc.doubleValue() + curGia.doubleValue());
         vcDV = Double.valueOf(vcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH"))
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
     log.info("After tinh tong tien CLS noi tru, tongtien = " + tongtien);
     if (this.hsttk != null)
     {
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

       List<ClsKham> listClsKham = clsKhamDelegate.findByMaPhieu(this.hsttk.getHsthtoankMa());
       for (ClsKham eachClsKham : listClsKham)
       {
         curGia = Double.valueOf(eachClsKham.getClskhamDongiabntra() == null ? 0.0D : eachClsKham.getClskhamDongiabntra().doubleValue());
         curGiaDV = Double.valueOf(eachClsKham.getClskhamPhandv() == null ? 0.0D : eachClsKham.getClskhamPhandv().doubleValue());
         if ((eachClsKham.getClskhamYeucau() != null) && (eachClsKham.getClskhamYeucau().booleanValue() == true))
         {
           hasDV = true;
           if ((this.cvnt.getTiepDon().getDoituongMa() != null) &&
             (this.cvnt.getTiepDon().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")))
           {
             curGia = Double.valueOf(eachClsKham.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0D : eachClsKham.getClskhamMahang().getDtdmclsbgDongiamp().doubleValue());

             curGiaDV = Double.valueOf((eachClsKham.getClskhamDongia() == null ? 0.0D : eachClsKham.getClskhamDongiabntra().doubleValue()) - curGia.doubleValue());
           }
         }
         tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGiaDV.doubleValue());
         if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA"))
         {
           mau = Double.valueOf(mau.doubleValue() + curGia.doubleValue());
           mauDV = Double.valueOf(mauDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN"))
         {
           xntdcn = Double.valueOf(xntdcn.doubleValue() + curGia.doubleValue());
           xntdcnDV = Double.valueOf(xntdcnDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD"))
         {
           cdha = Double.valueOf(cdha.doubleValue() + curGia.doubleValue());
           cdhaDV = Double.valueOf(cdhaDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if ((eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT")) || (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")))
         {
           pttt = Double.valueOf(pttt.doubleValue() + curGia.doubleValue());
           ptttDV = Double.valueOf(ptttDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if ((eachClsKham.getClskhamKtcao() != null) && (eachClsKham.getClskhamKtcao().booleanValue() == true))
         {
           ktc = Double.valueOf(ktc.doubleValue() + curGia.doubleValue());
           ktcDV = Double.valueOf(ktcDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI"))
         {
           dvp = Double.valueOf(dvp.doubleValue() + curGia.doubleValue());
           dvpDV = Double.valueOf(dvpDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if ((eachClsKham.getClskhamMaloai() != null) && (eachClsKham.getClskhamMaloai().getDtdmclsMaso().intValue() == 12))
         {
           vc = Double.valueOf(vc.doubleValue() + curGia.doubleValue());
           vcDV = Double.valueOf(vcDV.doubleValue() + curGiaDV.doubleValue());
         }
         else if (eachClsKham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH"))
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
     }
     log.info("After tinh tong tien CLS ngoai tru, tongtien = " + tongtien);





     ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
     Double thuocDV = Double.valueOf(0.0D);
     Double vtthDV = Double.valueOf(0.0D);
     Double thuocBN = Double.valueOf(0.0D);
     Double vtthBN = Double.valueOf(0.0D);
     List<ThuocNoiTru> lstThuocNT = tntDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
     for (ThuocNoiTru eachTnt : lstThuocNT)
     {
       curGia = Double.valueOf(eachTnt.getThuocnoitruTienbntra() == null ? 0.0D : eachTnt.getThuocnoitruTienbntra().doubleValue());
       if ((eachTnt.getThuocnoitruYeucau() != null) && (eachTnt.getThuocnoitruYeucau().booleanValue() == true)) {
         hasDV = true;
       }
       if ((eachTnt.getThuocnoitruMathuoc() != null) && (eachTnt.getThuocnoitruMathuoc().getDmthuocPlbhyt() != null) && (eachTnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10))
       {
         if ((eachTnt.getThuocnoitruYeucau() != null) && (eachTnt.getThuocnoitruYeucau().booleanValue() == true))
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
       else if ((eachTnt.getThuocnoitruYeucau() != null) && (eachTnt.getThuocnoitruYeucau().booleanValue() == true))
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
     log.info("After tinh tong tien Thuoc noi tru, tongtien = " + tongtien);
     if (this.hsttk != null)
     {
       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       List<ThuocPhongKham> listTPK = tpkDelegate.findByMaPhieu(this.hsttk.getHsthtoankMa());
       for (ThuocPhongKham eachTpk : listTPK)
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
     }
     log.info("After tinh tong tien Thuoc ngoai tru, tongtien = " + tongtien);
     String diachistr = "";
     if (this.benhNhan.getBenhnhanDiachi() != null) {
       diachistr = diachistr + this.benhNhan.getBenhnhanDiachi() + ", ";
     }
     if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
       diachistr = diachistr + this.benhNhan.getXaMa(true).getDmxaTen() + ", ";
     }
     if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
       diachistr = diachistr + this.benhNhan.getHuyenMa(true).getDmhuyenTen() + ", ";
     }
     if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
       diachistr = diachistr + this.benhNhan.getTinhMa(true).getDmtinhTen();
     }
     String thungan = "";
     if (this.hsThtoan != null) {
       if (this.hsThtoan.getHsthtoanThungan() != null)
       {
         if (this.hsThtoan.getHsthtoanThungan().getDtdmnhanvienTen() != null) {
           thungan = this.hsThtoan.getHsthtoanThungan().getDtdmnhanvienTen();
         } else {
           thungan = "";
         }
       }
       else {
         thungan = "";
       }
     }
     log.info("hasDV = " + hasDV + ", tongtienDV = " + tongtienDV + ", tong tien = " + tongtien);

     tongtien = Utils.rounDoubleForReport(tongtien);
     log.info("After lam tron tong tien, tongtien = " + tongtien);
     if (((tongtien.doubleValue() - miengiam.doubleValue() < 1.0D) || (benhnhantra.doubleValue() - nguonkhac.doubleValue() < 1.0D)) && (type == IN_BIEN_LAI))
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI, new Object[0]);
       log.info("End InPhieu(): Khong in bien lai !");
       return null;
     }
     tongtienDV = Utils.rounDoubleForReport(tongtienDV);

     SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
     Map<String, Object> params = new HashMap();
     params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
     params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
     params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


     params.put("MATIEPDON", tiepdon.getTiepdonMa());
     params.put("SOBENHAN", this.hsba.getHsbaSovaovien());
     params.put("HOTENBN", this.benhNhan.getBenhnhanHoten());

     params.put("DIACHI", diachistr);
     if (this.hsba.getDoituongMa(true).getDmdoituongMa().equals("BH"))
     {
       params.put("BHYT_CO", "X");
       if (((this.hsbaBhyt.getHsbabhytTuyen() != null) && (this.hsbaBhyt.getHsbabhytTuyen().toString().equals("1"))) || ((this.hsbaBhyt.getHsbabhytCoGiayChuyenVien() != null) && (this.hsbaBhyt.getHsbabhytCoGiayChuyenVien().booleanValue()))) {
         params.put("DUNGTUYEN", "X");
       } else {
         params.put("TRAITUYEN", "X");
       }
       params.put("MATHEBHYT", this.hsbaBhyt.getHsbabhytSothebh() == null ? "" : this.hsbaBhyt.getHsbabhytSothebh());
       params.put("MABENHVIEN", this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa() == null ? "" : this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa().replace(".", "-"));
       params.put("NOIDKKCBBANDAU", this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen() == null ? "" : this.hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
       params.put("NOICAPTHE", this.hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen() == null ? "" : this.hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
       params.put("GTTU", this.hsbaBhyt.getHsbabhytGiatri0() == null ? "" : sdf.format(this.hsbaBhyt.getHsbabhytGiatri0()));
       params.put("GTDEN", this.hsbaBhyt.getHsbabhytGiatri1() == null ? "" : sdf.format(this.hsbaBhyt.getHsbabhytGiatri1()));
       params.put("TYLEBH", this.hsThtoan == null ? new Short("0") : this.hsThtoan.getHsthtoanTylebh());
       params.put("PHANTRAMBNTRA", "" + (100 - (this.hsThtoan == null ? 0 : this.hsThtoan.getHsthtoanTylebh().shortValue())));
     }
     else
     {
       params.put("BHYT_KO", "X");
       params.put("MATHEBHYT", "");
       params.put("MABENHVIEN", "");
       params.put("NOIDKKCBBANDAU", "");
       params.put("NOICAPTHE", "");
       params.put("GTTU", "");
       params.put("GTDEN", "");
       params.put("DUNGTUYEN", "");
       params.put("TRAITUYEN", "");
       params.put("TYLEBH", new Short("0"));
       params.put("PHANTRAMBNTRA", "0");
     }
     if ((this.hsba.getHsbaCapcuu() != null) && (this.hsba.getHsbaCapcuu().booleanValue()))
     {
       params.put("CAPCUU", "X");
       params.put("DUNGTUYEN", "");
       params.put("TRAITUYEN", "");
     }
     else if ((this.hsba.getHsbaKhoavaov() != null) &&
       (this.hsba.getHsbaKhoavaov(true).getDmkhoaMa().startsWith("CC")))
     {
       params.put("CAPCUU", "X");
       params.put("DUNGTUYEN", "");
       params.put("TRAITUYEN", "");
     }
     HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.hsba.getHsbaSovaovien());
     params.put("GIUONG", hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());
     params.put("BUONG", hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());

     Calendar cal = Calendar.getInstance();
     if (this.hsba.getHsbaNgaygiovaov() != null)
     {
       cal.setTime(this.hsba.getHsbaNgaygiovaov());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));
       if (this.hsba.getHsbaNgaygiorav() != null)
       {
         cal.setTime(this.hsba.getHsbaNgaygiorav());
         params.put("NGAYRAVIEN", new Timestamp(cal.getTimeInMillis()));
         params.put("SONGAYDT", Long.valueOf(Utils.getSoNgayDieuTri(this.hsba.getHsbaNgaygiovaov(), this.hsba.getHsbaNgaygiorav())));
       }
     }
     params.put("LYDOVAOVIEN", tiepdon.getTiepdonLydovaov());


     params.put("BHXHTHANHTOAN", Double.valueOf(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanBhyt().doubleValue()));
     params.put("NGUOIBENHTRA", Double.valueOf(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanBntra().doubleValue()));
     params.put("NGUONKHAC", Double.valueOf(this.hsThtoan.getHsthtoanNguonkhactra() == null ? 0.0D : this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanNguonkhactra().doubleValue()));





     params.put("PHIEUSO", this.hsThtoan == null ? "" : this.hsThtoan.getHsthtoanMa());









     String soBienlai = "";
     if (this.hsThtoan.getHsthtoanKyhieu() != null) {
       soBienlai = this.hsThtoan.getHsthtoanKyhieu();
     }
     if (this.hsThtoan.getHsthtoanQuyen() != null) {
       soBienlai = soBienlai + " - " + this.hsThtoan.getHsthtoanQuyen();
     }
     if (this.hsThtoan.getHsthtoanBienlai() != null) {
       soBienlai = soBienlai + " - " + this.hsThtoan.getHsthtoanBienlai();
     }
     params.put("BIENLAISO", soBienlai);

     String namsinh = "";
     if (this.benhNhan.getBenhnhanNgaysinh() != null) {
       namsinh = sdf.format(this.benhNhan.getBenhnhanNgaysinh());
     } else {
       namsinh = this.benhNhan.getBenhnhanNamsinh();
     }
     params.put("namsinh", namsinh);

     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     String maChanDoan = "";
     String tenChanDoan = "";
     if ((this.hsba.getHsbaMachdravien() != null) && (this.hsba.getHsbaMachdravien(true).getDmbenhicdMaso() != null))
     {
       DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.hsba.getHsbaMachdravien(true).getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
       if (benh != null)
       {
         maChanDoan = benh.getDmbenhicdMa();
         tenChanDoan = benh.getDmbenhicdTen();
       }
     }
     else if ((this.hsba.getHsbaMachdoanbd() != null) && (this.hsba.getHsbaMachdoanbd().getDmbenhicdMaso() != null))
     {
       DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.hsba.getHsbaMachdoanbd().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
       if (benh != null)
       {
         maChanDoan = benh.getDmbenhicdMa();
         tenChanDoan = benh.getDmbenhicdTen();
       }
     }
     String chanDoan = maChanDoan + " " + tenChanDoan;

     params.put("MABENHICD", maChanDoan);
     params.put("CHANDOAN", chanDoan);

     params.put("PHONGKHAM", null);

     List<HsbaKhoa> listHsbaKhoa = HsbaKhoaDelegate.getInstance().findBySoVaoVien(this.hsba.getHsbaSovaovien());

     StringBuffer bufStr = new StringBuffer();
     if ((listHsbaKhoa != null) && (listHsbaKhoa.size() > 0)) {
       for (HsbaKhoa each : listHsbaKhoa) {
         if (bufStr.length() > 0) {
           bufStr.append(", " + each.getKhoaMa().getDmkhoaTen());
         } else {
           bufStr.append(each.getKhoaMa().getDmkhoaTen());
         }
       }
     }
     params.put("KHOA", bufStr.toString());
     String soTheTe_KhaiSinh_ChungSinh = "";
     if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
       params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
     } else {
       params.put("SOTHETEKSCS", null);
     }
     String soTheNgheo = "";
     if ((soTheNgheo != null) && (!soTheNgheo.equals(""))) {
       params.put("SOTHENGHEO", soTheNgheo);
     } else {
       params.put("SOTHENGHEO", null);
     }
     params.put("TONGCHIPHI", Double.valueOf(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanTongchi().doubleValue()));

     params.put("BANGCHU1", Utils.NumberToString(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanTongchi().doubleValue()));

     params.put("BANGCHU2", Utils.NumberToString(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanBntra().doubleValue()));

     params.put("BANGCHU3", Utils.NumberToString(this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanBhyt().doubleValue()));
     params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
     if ((this.hsThtoan == null ? 0.0D : this.hsThtoan.getHsthtoanBntra().doubleValue()) + (this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) >= 0.0D) {
       params.put("SNGUOIBENHTRA", "0");
     } else {
       params.put("SNGUOIBENHTRA", "-1");
     }
     DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(this.benhNhan.getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
     if (gioi != null) {
       params.put("GIOI", gioi.getDmgtTen());
     }
     params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
     if ((type == IN_BIEN_LAI) &&
       (this.hsThtoan != null))
     {
       int lanIn = this.hsThtoan.getHsthtoanLanin() == null ? 1 : Integer.valueOf(this.hsThtoan.getHsthtoanLanin()).intValue() + 1;
       this.hsThtoan.setHsthtoanLanin("" + lanIn);
       params.put("LANIN", "" + lanIn);
     }
     try
     {
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_kobienlailephi.jrxml";
       String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
       String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
       String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
       String pathTemplate_sub4 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_subreport0.jrxml";
       String pathTemplate_sub5 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_subreport1.jrxml";
       if ((hasDV) && (tongtienDV.doubleValue() > 0.0D)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_DV.jrxml";
       } else if (tongtien.doubleValue() - miengiam.doubleValue() > 0.0D) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru.jrxml";
       }
       log.info("da thay file templete :" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       if ((tongtien.doubleValue() - miengiam.doubleValue() > 0.0D) && (type == IN_BIEN_LAI))
       {
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);
         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);
       }
       if ((hasDV) && (tongtienDV.doubleValue() > 0.0D))
       {
         String pathTemplate_sub6 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
         String pathTemplate_sub7 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
         String pathTemplate_sub8 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
         String pathTemplate_sub9 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_DV_subreport0.jrxml";
         String pathTemplate_sub10 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieuthanhtoanKCBnoitru_DV_subreport1.jrxml";
         JasperReport jasperSub6 = JasperCompileManager.compileReport(pathTemplate_sub6);
         JasperReport jasperSub7 = JasperCompileManager.compileReport(pathTemplate_sub7);
         JasperReport jasperSub8 = JasperCompileManager.compileReport(pathTemplate_sub8);
         JasperReport jasperSub9 = JasperCompileManager.compileReport(pathTemplate_sub9);
         JasperReport jasperSub10 = JasperCompileManager.compileReport(pathTemplate_sub10);
         if (type == IN_BIEN_LAI)
         {
           params.put("sub6", jasperSub6);
           params.put("sub7", jasperSub7);
           params.put("sub8", jasperSub8);
         }
         if (type == IN_PHIEU_THANH_TOAN)
         {
           params.put("sub9", jasperSub9);
           params.put("sub10", jasperSub10);
         }
       }
       JasperReport jasperSub4 = JasperCompileManager.compileReport(pathTemplate_sub4);
       JasperReport jasperSub5 = JasperCompileManager.compileReport(pathTemplate_sub5);
       log.info("da thay file template ");
       if (type == IN_PHIEU_THANH_TOAN)
       {
         params.put("sub4", jasperSub4);
         params.put("sub5", jasperSub5);
       }
       params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
       params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
       params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
       params.put("HOTEN", this.benhNhan.getBenhnhanHoten());


       params.put("NOIDUNG", this.hsThtoan == null ? "" : this.hsThtoan.getHsthtoanLidothanhtoan());

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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "ThanhToanRaVien");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + getIndex());
       if (conn != null) {
         conn.close();
       }
       if ((this.hsThtoan != null) && (this.hsThtoan.getHsthtoanMa() != null))
       {
         if (this.hsThtoan.getHsthtoanMa().trim().length() > 0) {
           hsttDelegate.edit(this.hsThtoan);
         }
       }
       else {
         this.hsThtoan = new HsThtoan();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
       return null;
     }
     log.info("Thoat Method XuatPhieuThanhToan");
     if (this.nhanVienThungan == null) {
       refreshNhanVienThuNgan();
     }
     return "B3360_Chonmenuxuattaptin";
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

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public String getSoThe()
   {
     return this.soThe;
   }

   public void setSoThe(String soThe)
   {
     this.soThe = soThe;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public String getNgayNhapvien()
   {
     return this.ngayNhapvien;
   }

   public void setNgayNhapvien(String ngayNhapvien)
   {
     this.ngayNhapvien = ngayNhapvien;
   }

   public HsbaChuyenVien getHsbaChuyenvien()
   {
     return this.hsbaChuyenvien;
   }

   public void setHsbaChuyenvien(HsbaChuyenVien hsbaChuyenvien)
   {
     this.hsbaChuyenvien = hsbaChuyenvien;
   }

   public HsThtoan getHsThtoan()
   {
     return this.hsThtoan;
   }

   public void setHsThtoan(HsThtoan hsThtoan)
   {
     this.hsThtoan = hsThtoan;
   }

   public String getMaFinish()
   {
     return this.maFinish;
   }

   public void setMaFinish(String maFinish)
   {
     this.maFinish = maFinish;
   }

   public String getReportFileNameHid()
   {
     return this.reportFileNameHid;
   }

   public void setReportFileNameHid(String reportFileNameHid)
   {
     this.reportFileNameHid = reportFileNameHid;
   }

   public String getNgayTtoan()
   {
     return this.ngayTtoan;
   }

   public void setNgayTtoan(String ngayTtoan)
   {
     this.ngayTtoan = ngayTtoan;
   }

   public String getCacKhoadt()
   {
     return this.cacKhoadt;
   }

   public void setCacKhoadt(String cacKhoadt)
   {
     this.cacKhoadt = cacKhoadt;
   }

   public String getReportFileName()
   {
     return this.reportFileName;
   }

   public void setReportFileName(String reportFileName)
   {
     this.reportFileName = reportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
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

   public Double getTongTrongDM()
   {
     return this.tongTrongDM;
   }

   public void setTongTrongDM(Double tongTrongDM)
   {
     this.tongTrongDM = tongTrongDM;
   }

   public Double getTongNDM()
   {
     return this.tongNDM;
   }

   public void setTongNDM(Double tongNDM)
   {
     this.tongNDM = tongNDM;
   }

   public Double getnSKhongThu()
   {
     return this.nSKhongThu;
   }

   public void setnSKhongThu(Double nSKhongThu)
   {
     this.nSKhongThu = nSKhongThu;
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

   public void setUngTra(Double ungTra)
   {
     this.ungTra = ungTra;
   }

   public Double getUngTra()
   {
     return this.ungTra;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public Integer getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(Integer khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public Boolean getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }

   public void setHienThiGhiNhan(Boolean hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public Boolean getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(Boolean hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public String getGioThanhToan()
   {
     return this.gioThanhToan;
   }

   public void setGioThanhToan(String gioThanhToan)
   {
     this.gioThanhToan = gioThanhToan;
   }

   public Boolean getHienThiHuyPhieu()
   {
     return this.hienThiHuyPhieu;
   }

   public void setHienThiHuyPhieu(Boolean hienThiHuyPhieu)
   {
     this.hienThiHuyPhieu = hienThiHuyPhieu;
   }

   public String getLiDoTT()
   {
     return this.sLiDoTT;
   }

   public void setLiDoTT(String liDoTT)
   {
     this.sLiDoTT = liDoTT;
   }

   public Double getBenhnhanTra()
   {
     return this.benhnhanTra;
   }

   public void setBenhnhanTra(Double benhnhanTra)
   {
     this.benhnhanTra = benhnhanTra;
   }

   public Double getConlai()
   {
     return this.conlai;
   }

   public void setConlai(Double conlai)
   {
     this.conlai = conlai;
   }

   public List<ClsMo> getListClsMo()
   {
     return this.listClsMo;
   }

   public void setListClsMo(List<ClsMo> listClsMo)
   {
     this.listClsMo = listClsMo;
   }

   public List<ThuocNoiTru> getListThuocNT()
   {
     return this.listThuocNT;
   }

   public void setListThuocNT(List<ThuocNoiTru> listThuocNT)
   {
     this.listThuocNT = listThuocNT;
   }

   public Boolean getHienThiInPhieuNgT()
   {
     return this.hienThiInPhieuNgT;
   }

   public void setHienThiInPhieuNgT(Boolean hienThiInPhieuNgT)
   {
     this.hienThiInPhieuNgT = hienThiInPhieuNgT;
   }

   public ChuyenVaoNoiTru getCvnt()
   {
     return this.cvnt;
   }

   public void setCvnt(ChuyenVaoNoiTru cvnt)
   {
     this.cvnt = cvnt;
   }

   public String getRole()
   {
     return this.role;
   }

   public void setRole(String role)
   {
     this.role = role;
   }

   public String getNgayRavien()
   {
     return this.ngayRavien;
   }

   public void setNgayRavien(String ngayRavien)
   {
     this.ngayRavien = ngayRavien;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThanhToanRaVienAction

 * JD-Core Version:    0.7.0.1

 */