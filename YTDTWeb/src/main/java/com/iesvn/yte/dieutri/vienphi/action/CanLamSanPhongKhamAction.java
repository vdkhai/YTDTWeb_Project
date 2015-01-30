 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankBackupDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
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
 import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.ThamKhamUtil;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3232_Canlamsanphongkham")
 @Synchronized(timeout=6000000L)
 public class CanLamSanPhongKhamAction
   implements Serializable
 {
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String position = IConstantsRes.WEB_PATH + "/" + IConstantsRes.WEB_NAME + "/" + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
   private String resultReportName = "";
   private String reportFileNameHid = "";
   private String resultReportFileName = "";
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private static final long serialVersionUID = 10L;
   private BenhNhan benhNhan;
   private ThamKham thamKham;
   private ClsKham clskham;
   private String gioi;
   private String tuoi;
   private String ngaySinh;
   private String ngayTt;
   private String gioThanhToan;
   private String kyhieu;
   private String quyen;
   private String bienlai;
   private String sNoiDungThu;
   private String mahsttk;
   private String strBankham;
   @DataModel
   private List<ThuocPhongKham> ctClsPhongKhams;
   private List<ThuocPhongKham> listCtTPK_temp = null;
   private List<ClsKham> clslist = null;
   private String maBanKham;

   private void tinhToanChoHSTKKham(HsThtoank hsttk, Boolean ghinhan)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     if (ghinhan.booleanValue() == true)
     {
       this.listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp();
       this.clslist = hsthtoankUtilTmp.getListCtkq_temp();
     }
   }

   private String loai = "";
   private String maCLS = "";
   private Integer maSoCLS = null;
   private String tenCLS = "";
   private String maKhoa = "";
   private Boolean mien = new Boolean(false);
   private Boolean ngoaiDanhMuc = new Boolean(false);
   private Boolean dichVu = new Boolean(false);
   private Boolean yeuCau = new Boolean(false);
   private Boolean kyThuatCao = new Boolean(false);
   private short soLuong = 0;
   private Double donGia = new Double(0.0D);
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double conlai = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Double bntrakn = new Double(0.0D);
   private Double cls;
   private Double thuoc;
   private Double tienThuocTT = Double.valueOf(0.0D);
   private Double tienThuocDaTT = Double.valueOf(0.0D);
   private String resultHidden = "";
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private String maPhieu = "";
   private String maPhieuTT = "";
   private static Logger log = Logger.getLogger(CanLamSanPhongKhamAction.class);
   @DataModel
   private List<ClsKham> listCtkq = new ArrayList();
   @DataModelSelection("listCtkq")
   @Out(required=false)
   private ClsKham nhapctSelected;
   private boolean updateNhapct = false;
   private String disbledHuyPhieu;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private DtDmCum cum = null;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToCLSPhongKham;

   public void refreshnhanvienthungan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     this.goToCLSPhongKham = "none";
     log.info("***Finished init ***");







     refreshnhanvienthungan();
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null)) {
       this.cum = pc.getDtdmcumMa();
     }
     this.tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
     this.ctClsPhongKhams = new ArrayList();

     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   @Factory("goToCLSPhongKham")
   @Begin(nested=true)
   public String comefromxuatthuocbhyt()
     throws Exception
   {
     this.goToCLSPhongKham = "gone";
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");






     refreshnhanvienthungan();
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null)) {
       this.cum = pc.getDtdmcumMa();
     }
     this.tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;

     this.maBanKham = null;
     this.thamKham.getTiepdonMa(true).setTiepdonMa(this.maTiepDonOut);

     displayInfor();

     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   @End
   public void endFunct() {}

   private void emtyData()
   {
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();

     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     this.thamKham.setTiepdonMa(new TiepDon());
     this.thamKham.getTiepdonMa().setBenhnhanMa(this.benhNhan);
     SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());

     this.clskham = new ClsKham();
     this.clskham.setClskhamThamkham(this.thamKham);

     setInforIfNullForClsKham(this.clskham);
     this.nhapctSelected = new ClsKham();
     setInforIfNullForClsKham(this.nhapctSelected);
     this.tuoi = "";
     this.ngaySinh = "";


     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayTt = formatter.format(cal.getTime());
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());


     this.resultHidden = "";
     this.reportFinished = "";
     this.reportFileNameHid = "";

     this.listCtkq = new ArrayList();
     this.listCtTPK_temp = new ArrayList();
     this.sNoiDungThu = IConstantsRes.NOI_DUNG_THU_CLS;
   }

   private void hasNoMaTiepDon()
   {
     emtyData();
     resetValue();
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void resetValue()
   {
     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.maKhoa = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.dichVu = new Boolean(false);

     this.sNoiDungThu = IConstantsRes.NOI_DUNG_THU_CLS;
     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
   }

   public void CopyFrom(ClsKham cls)
   {
     log.info("Begining CopyFrom()" + cls);
     this.loai = cls.getClskhamLoai();
     this.maSoCLS = cls.getClskhamMahang().getDtdmclsbgMaso();
     this.maCLS = cls.getClskhamMahang().getDtdmclsbgMa();
     this.tenCLS = cls.getClskhamMahang().getDtdmclsbgDiengiai();
     this.maKhoa = cls.getClskhamKhoa().getDmkhoaMa();
     this.mien = cls.getClskhamMien();
     this.ngoaiDanhMuc = cls.getClskhamNdm();
     this.yeuCau = cls.getClskhamYeucau();
     this.kyThuatCao = cls.getClskhamKtcao();
     this.soLuong = cls.getClskhamLan().shortValue();
     this.donGia = cls.getClskhamDongia();
     this.dichVu = cls.getClskhamDichvu();
     log.info("End CopyFrom()");
   }

   public void CopyTo(ClsKham cls)
   {
     log.info("Begining CopyFrom()" + cls);
     cls.setClskhamLoai(this.loai);
     cls.getClskhamMahang().setDtdmclsbgMa(this.maCLS);
     cls.getClskhamMahang().setDtdmclsbgMaso(this.maSoCLS);
     cls.getClskhamMahang().setDtdmclsbgDiengiai(this.tenCLS);

     cls.getClskhamKhoa().setDmkhoaMa(this.maKhoa);
     cls.setClskhamMien(this.mien);
     cls.setClskhamNdm(this.ngoaiDanhMuc);
     cls.setClskhamYeucau(this.yeuCau);
     cls.setClskhamKtcao(this.kyThuatCao);
     cls.setClskhamLan(Short.valueOf(this.soLuong));
     cls.setClskhamDongia(this.donGia);
     cls.setClskhamDichvu(this.dichVu);
     log.info("End CopyFrom()");
   }

   public void Caculation(List<ClsKham> listCtkq)
   {
     this.tienThuocDaTT = Double.valueOf(0.0D);
     for (ThuocPhongKham thuocTT : this.ctClsPhongKhams) {
       if (thuocTT.getThuocphongkhamDatt() == null) {
         this.tienThuocDaTT = Double.valueOf(0.0D);
       } else if (thuocTT.getThuocphongkhamDatt().booleanValue() == true) {
         this.tienThuocDaTT = Double.valueOf(this.tienThuocDaTT.doubleValue() + (thuocTT.getThuocphongkhamThanhtien() == null ? 0.0D : thuocTT.getThuocphongkhamThanhtien().doubleValue()));
       }
     }
     HsThtoank hsttk = new HsThtoank();
     hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());
     HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtil.tinhToanChoHSTKKham(hsttk);

     Utils.setInforForHsThToan(hsttk);
     this.permiengiam = 0;
     this.miengiam = hsthtoankUtil.getMiengiam();
     this.thatthu = hsthtoankUtil.getThatthu();
     this.perbhytchi = hsthtoankUtil.getPerbhytchi();

     this.conlai = hsttk.getHsthtoankThtoan();
     this.thanhtien1 = hsttk.getHsthtoankTongchi();
     this.perbntra = hsthtoankUtil.getPerbntra();

     this.bntra = hsttk.getHsthtoankBntra();

     this.cls = Double.valueOf((hsttk.getHsthtoankCls() == null ? 0.0D : hsttk.getHsthtoankCls().doubleValue()) + (hsttk.getHsthtoankClsndm() == null ? 0.0D : hsttk.getHsthtoankClsndm().doubleValue()));


     this.thuoc = Double.valueOf((hsttk.getHsthtoankThuoc() == null ? 0.0D : hsttk.getHsthtoankThuoc().doubleValue()) + (hsttk.getHsthtoankThuocndm() == null ? 0.0D : hsttk.getHsthtoankThuocndm().doubleValue()) + (hsttk.getHsthtoankVtth() == null ? 0.0D : hsttk.getHsthtoankVtth().doubleValue()) + (hsttk.getHsthtoankVtthndm() == null ? 0.0D : hsttk.getHsthtoankVtthndm().doubleValue()));
   }

   public void caculationThuoc(List<ThuocPhongKham> listThuoc)
   {
     this.tienThuocTT = Double.valueOf(0.0D);
     for (ThuocPhongKham thuoc : listThuoc)
     {
       log.info("In caculationThuoc, quoc gia = " + thuoc.getThuocphongkhamQuocgia());
       if ((thuoc.getThuocphongkhamHangsx() != null) && (thuoc.getThuocphongkhamHangsx().getDmnhasanxuatMaso() == null)) {
         thuoc.setThuocphongkhamHangsx(null);
       }
       if ((thuoc.getThuocphongkhamQuocgia() != null) && (thuoc.getThuocphongkhamQuocgia().getDmquocgiaMaso() == null)) {
         thuoc.setThuocphongkhamQuocgia(null);
       }
       if (thuoc.getThuocphongkhamDatt() == null)
       {
         ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();



         this.tienThuocTT = Double.valueOf(this.tienThuocTT.doubleValue() + (thuoc.getThuocphongkhamThanhtien() == null ? 0.0D : thuoc.getThuocphongkhamThanhtien().doubleValue()));
         thuoc.setThuocphongkhamDatt(Boolean.valueOf(true));

         Calendar dCalendar = Calendar.getInstance();
         thuoc.setThuocphongkhamNgaygiott(dCalendar.getTime());

         thuocDel.edit(thuoc);
       }
       else if (!thuoc.getThuocphongkhamDatt().booleanValue())
       {
         ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();



         this.tienThuocTT = Double.valueOf(this.tienThuocTT.doubleValue() + (thuoc.getThuocphongkhamThanhtien() == null ? 0.0D : thuoc.getThuocphongkhamThanhtien().doubleValue()));
         thuoc.setThuocphongkhamDatt(Boolean.valueOf(true));

         Calendar dCalendar = Calendar.getInstance();
         thuoc.setThuocphongkhamNgaygiott(dCalendar.getTime());

         thuocDel.edit(thuoc);
       }
     }
   }

   public Double getCls()
   {
     return this.cls;
   }

   public void setCls(Double cls)
   {
     this.cls = cls;
   }

   public Double getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(Double thuoc)
   {
     this.thuoc = thuoc;
   }

   public void ghinhan()
     throws Exception
   {
     log.info("*****Begin Ghi nhan(), thu ngan = " + this.nhanVienThungan);
     refreshnhanvienthungan();
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       return;
     }
     if (this.listCtkq == null) {
       this.listCtkq = new ArrayList();
     }
     log.info("*****so phan tu insert *****" + this.listCtkq.size());
     boolean checkThuoc = false;
     if (this.ctClsPhongKhams.size() == 0) {
       checkThuoc = true;
     } else {
       for (ThuocPhongKham thuocTT : this.ctClsPhongKhams)
       {
         if (thuocTT.getThuocphongkhamDatt() == null)
         {
           checkThuoc = false;
           break;
         }
         if (thuocTT.getThuocphongkhamDatt().booleanValue()) {
           checkThuoc = true;
         }
       }
     }
     boolean checked = false;
     if (checkThuoc) {
       if (this.listCtkq.size() == 1)
       {
         if ((((ClsKham)this.listCtkq.get(0)).getClskhamNgaygiott() == null) && (!((ClsKham)this.listCtkq.get(0)).getClskhamDatt().booleanValue())) {
           FacesMessages.instance().add(IConstantsRes.CHUA_CHON_CLS, new Object[0]);
         }
       }
       else {
         for (int i = 0; i < this.listCtkq.size(); i++)
         {
           if ((i > 0) && (((ClsKham)this.listCtkq.get(i)).getClskhamNgaygiott() == null))
           {
             checked = ((ClsKham)this.listCtkq.get(i)).getClskhamDatt().booleanValue();
             if (checked) {
               break;
             }
           }
           if ((i == this.listCtkq.size() - 1) && (!checked))
           {
             FacesMessages.instance().add(IConstantsRes.CHUA_CHON_CLS, new Object[0]);
             return;
           }
         }
       }
     }
     if ((this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa().equals("")) || (this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       resetValue();
       return;
     }
     try
     {
       if (("TP".equals(this.thamKham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())) && (!"CCL".equals(this.thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())) && (!"CCN".equals(this.thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())))
       {
         HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();








         HsThtoank hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());

         hsttk.setHsthtoankXetgiam(this.miengiam);

         hsttk.setHsthtoankThatthu(this.thatthu);
         hsttk.setHsthtoankKyhieu(this.kyhieu);
         hsttk.setHsthtoankQuyen(this.quyen);
         hsttk.setHsthtoankBienlai(this.bienlai);
         log.info("In ghinhan() 1, that thu = " + hsttk.getHsthtoankThatthu() + ", ky hieu = " + hsttk.getHsthtoankKyhieu() + ", quyen = " + hsttk.getHsthtoankQuyen() + ", bien lai = " + hsttk.getHsthtoankBienlai());
         tinhToanChoHSTKKham(hsttk, Boolean.valueOf(true));
         log.info("In ghinhan() 2, that thu = " + hsttk.getHsthtoankThatthu() + ", ky hieu = " + hsttk.getHsthtoankKyhieu() + ", quyen = " + hsttk.getHsthtoankQuyen() + ", bien lai = " + hsttk.getHsthtoankBienlai());
         caculationThuoc(this.ctClsPhongKhams);


         this.tienThuocDaTT = Double.valueOf(0.0D);
         for (ThuocPhongKham thuocTT : this.ctClsPhongKhams) {
           if (thuocTT.getThuocphongkhamDatt() != null) {
             if (thuocTT.getThuocphongkhamDatt().booleanValue() == true)
             {
               this.tienThuocDaTT = Double.valueOf(this.tienThuocDaTT.doubleValue() + thuocTT.getThuocphongkhamThanhtien().doubleValue());
               this.tienThuocDaTT = Double.valueOf(this.tienThuocDaTT.doubleValue() + (thuocTT.getThuocphongkhamThanhtien() == null ? 0.0D : thuocTT.getThuocphongkhamThanhtien().doubleValue()));
             }
           }
         }
         HsThtoankBackup hsBK = new HsThtoankBackup();

         hsBK.setHsthtoankBhxh(hsttk.getHsthtoankBhxh());
         hsBK.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());
         hsBK.setHsthtoankBienlai(hsttk.getHsthtoankBienlai());
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
         hsBK.setHsthtoankKyhieu(hsttk.getHsthtoankKyhieu());
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
         hsBK.setHsthtoankQuyen(hsttk.getHsthtoankQuyen());
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
         log.info("hsBK.getTiepdonMa() = " + hsBK.getTiepdonMa());




         Calendar dTemp = Utils.getDBDate(this.gioThanhToan, this.ngayTt);
         Date dNgayGioTT = Calendar.getInstance().getTime();
         if (dTemp != null) {
           dNgayGioTT = dTemp.getTime();
         }
         hsBK.setHsthtoankNgaygiott(dNgayGioTT);
         hsBK.setHsthtoankThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);




         HsThtoank hsttkTmp = hsttkDelegate.findBytiepdonMaLast(this.thamKham.getTiepdonMa(true).getTiepdonMa());
         if (hsttkTmp != null)
         {
           hsttk = hsttkTmp;
           hsttk.setHsthtoankThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);
           hsttk.setHsthtoankThtoan(Double.valueOf(hsttk.getHsthtoankThtoan().doubleValue() - this.tienThuocDaTT.doubleValue()));
           Utils.setInforForHsThToan(hsttk);
           hsttk.setHsthtoankNgaygiott(dNgayGioTT);
           hsttkDelegate.edit(hsttk);
         }
         else
         {
           hsttk.setHsthtoankThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);
           hsttk.setHsthtoankThtoan(Double.valueOf(hsttk.getHsthtoankThtoan().doubleValue() - this.tienThuocDaTT.doubleValue()));
           Utils.setInforForHsThToan(hsttk);
           hsttk.setHsthtoankNgaygiott(dNgayGioTT);
           hsttkDelegate.create(hsttk);
         }
         log.info("before create (hsttk BK), ma = " + hsBK.getHsthtoankMa());
         HsThtoankBackupDelegate.getInstance().create(hsBK);
         log.info("After create (hsttk BK), ma = " + hsBK.getHsthtoankMa());
         HsThtoankBackup hsBKTemp = HsThtoankBackupDelegate.getInstance().findBytiepdonMa(hsBK.getTiepdonMa().getTiepdonMa(), 1);
         log.info("After findBytiepdonMa, ma = " + hsBKTemp.getHsthtoankMa());
         this.maPhieuTT = hsBKTemp.getHsthtoankMa();
         ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
         List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(this.thamKham.getTiepdonMa(true).getTiepdonMa(), "1");
         if ((listTpk != null) && (listTpk.size() > 0)) {
           for (ThuocPhongKham eachTpk : listTpk) {
             if (eachTpk.getThuocphongkhamMaphieud() == null)
             {
               eachTpk.setThuocphongkhamMaphieud(hsBK.getHsthtoankMa());
               tpkDelegate.edit(eachTpk);
             }
           }
         }
         ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
         for (int i = 0; i < this.listCtkq.size(); i++) {
           removeIfNullForClsKham((ClsKham)this.listCtkq.get(i));
         }
         ArrayList<ClsKham> arrayListHasMaPhieu = new ArrayList();
         for (ClsKham cls : this.listCtkq)
         {
           if ((cls.getClskhamCum() == null) && ((cls.getClskhamMaphieu() == null) || (cls.getClskhamMaphieu().equals(""))) && (cls.getClskhamDatt() != null) && (cls.getClskhamDatt().booleanValue() == true))
           {
             cls.setClskhamCum(this.cum);
             cls.setClskhamThungan(this.nhanVienThungan.getDtdmnhanvienMaso() == null ? null : this.nhanVienThungan);

             cls.setClskhamKyHieu(this.kyhieu);
             cls.setClskhamQuyen(this.quyen);
             cls.setClskhamBienLai(this.bienlai);
             cls.setClskhamNoidungthu(this.sNoiDungThu);
             log.info("*****sNoiDungThu: " + this.sNoiDungThu);









             cls.setClskhamNgaygiott(dNgayGioTT);
           }
           if ((cls.getClskhamMaphieu() != null) && (!cls.getClskhamMaphieu().equals(""))) {
             arrayListHasMaPhieu.add(cls);
           }
         }
         this.maPhieu = clsKhamDelegate.createClsKham_v2(this.listCtkq, this.thamKham.getTiepdonMa().getTiepdonMa(), this.maPhieuTT);

         log.info("maPhieu = " + this.maPhieu);
       }
       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
       log.error("*************loi***********" + e.toString());
       e.printStackTrace();
     }
     try
     {
       for (int i = 0; i < this.listCtkq.size(); i++)
       {
         setInforIfNullForClsKham((ClsKham)this.listCtkq.get(i));

         SetInforUtil.setInforIfNullForThamKham(((ClsKham)this.listCtkq.get(i)).getClskhamThamkham());

         SetInforUtil.setInforIfNullForTiepDon(((ClsKham)this.listCtkq.get(i)).getClskhamThamkham().getTiepdonMa());
       }
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
       e.printStackTrace();
     }
     this.fromGhiNhan = true;
     displayInfor();
     log.info("*****End Ghi nhan() *****");
     this.reportFinished = "";
   }

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   public void sualai()
     throws Exception
   {
     try
     {
       ResetForm();
       this.resultHidden = "";
       this.reportFinished = "";
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   public String inphieu()
     throws Exception
   {
     log.info("Begining inphieu(), displayByMaPhieu = " + this.displayByMaPhieu);
     if (((this.maPhieu == null) || (this.maPhieu.equals(""))) && (this.tienThuocTT.doubleValue() == 0.0D)) {
       return "";
     }
     try
     {
       XuatReport2();
     }
     catch (Exception e)
     {
       log.info("Loi trong khi xuat report" + e.toString());
     }
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
     log.info("End inphieu()");
     return "B3360_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport2()
   {
     this.reportTypeVP = "B3232_Phieuthanhtoanclsphongkham";
     log.info("Vao Method XuatReport B3232_Phieuthanhtoanclsphongkham, displayByMaPhieu = " + this.displayByMaPhieu);
     String baocao1 = null;
     String pathTemplate = null;
     log.info("In XuatReport2(), ctClsPhongKhams.size() = " + this.ctClsPhongKhams.size() + "thamKham = " + this.thamKham);
     ClsKhamDelegate clsDel = ClsKhamDelegate.getInstance();
     List<ClsKham> listClsKham = clsDel.findByMaPhieu(this.maPhieu);

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
     Double miengiam2 = Double.valueOf(0.0D);

     HsThtoankBackupDelegate hsttkBKDelegate = HsThtoankBackupDelegate.getInstance();

     HsThtoankBackup hsttkBK = hsttkBKDelegate.findByMaPhieu(this.maPhieu);
     if (hsttkBK != null) {
       miengiam2 = Double.valueOf(hsttkBK.getHsthtoankXetgiam() == null ? 0.0D : hsttkBK.getHsthtoankXetgiam().doubleValue());
     }
     for (ClsKham eachCls : listClsKham)
     {
       int lan = eachCls.getClskhamLan().intValue();
       int tra = eachCls.getClskhamTra() == null ? 0 : eachCls.getClskhamTra().intValue();
       if ((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true))
       {
         hasDV = true;
         curGia = Double.valueOf(eachCls.getClskhamMahang().getDtdmclsbgDongia().doubleValue() * (this.displayByMaPhieu ? lan : lan - tra));
         curGiaDV = Double.valueOf((eachCls.getClskhamMahang().getDtdmclsbgDongiayc().doubleValue() - eachCls.getClskhamMahang().getDtdmclsbgDongia().doubleValue()) * (this.displayByMaPhieu ? lan : lan - tra));
         tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGiaDV.doubleValue());
       }
       else
       {
         curGia = Double.valueOf(eachCls.getClskhamMahang().getDtdmclsbgDongia().doubleValue() * (this.displayByMaPhieu ? lan : lan - tra));
         curGiaDV = Double.valueOf(0.0D);
       }
       tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
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
     log.info("tongtien = " + tongtien + ", tongtienDV = " + tongtienDV);

     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
     Double thuocDV = Double.valueOf(0.0D);
     Double vtthDV = Double.valueOf(0.0D);
     Double thuocBN = Double.valueOf(0.0D);
     Double vtthBN = Double.valueOf(0.0D);

     List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaPhieu(this.maPhieu);
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
     String diachistr = "";
     BenhNhan benhnhan = this.thamKham.getTiepdonMa().getBenhnhanMa();
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
     String thungan = "";
     if (hsttkBK.getHsthtoankThungan() != null)
     {
       if (hsttkBK.getHsthtoankThungan().getDtdmnhanvienTen() != null) {
         thungan = hsttkBK.getHsthtoankThungan().getDtdmnhanvienTen();
       } else {
         thungan = "";
       }
     }
     else {
       thungan = "";
     }
     log.info("hasDV = " + hasDV);

     int lanIn = hsttkBK.getHsthtoankLanin() == null ? 1 : Integer.valueOf(hsttkBK.getHsthtoankLanin()).intValue() + 1;
     hsttkBK.setHsthtoankLanin("" + lanIn);
     if ((hasDV) && (tongtienDV.doubleValue() > 0.0D)) {
       try
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         String pathTemplate_sub4 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
         String pathTemplate_sub5 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
         String pathTemplate_sub6 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
         log.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);

         JasperReport jasperSub4 = JasperCompileManager.compileReport(pathTemplate_sub4);
         JasperReport jasperSub5 = JasperCompileManager.compileReport(pathTemplate_sub5);
         JasperReport jasperSub6 = JasperCompileManager.compileReport(pathTemplate_sub6);
         log.info("da thay file template ");

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
         params.put("HOTEN", benhnhan.getBenhnhanHoten());
         params.put("DIACHI", diachistr);
         params.put("THUNGAN", thungan);


         params.put("NOIDUNG", this.sNoiDungThu);
         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam2.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam2.doubleValue()));
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
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(this.miengiam));


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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3232_Phieuthanhtoanclsphongkham");
         this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathVP);
         this.index += 1;
         log.info("Index :" + this.index);
         if (conn != null) {
           conn.close();
         }
         hsttkBKDelegate.edit(hsttkBK);
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     } else {
       try
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         log.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);
         log.info("da thay file template ");

         Map<String, Object> params = new HashMap();

         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);


         params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
         params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
         params.put("HOTEN", benhnhan.getBenhnhanHoten());

         params.put("DIACHI", diachistr);
         params.put("NOIDUNG", this.sNoiDungThu);

         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam2.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam2.doubleValue()));



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
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(this.miengiam));
         params.put("THUNGAN", thungan);

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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3232_Phieuthanhtoanclsphongkham");
         this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathVP);
         this.index += 1;
         log.info("Index :" + this.index);
         if (conn != null) {
           conn.close();
         }
         hsttkBKDelegate.edit(hsttkBK);
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
   }

   public void XuatReport()
   {
     this.reportTypeVP = "B3232_Phieuthanhtoanclsphongkham";
     log.info("Vao Method XuatReport B3232_Phieuthanhtoanclsphongkham");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien_sub2.jrxml";
       log.info("da thay file templete " + pathTemplate);


       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       log.info("****Finish creating jasperReport*****");

       Map<String, Object> params = new HashMap();

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);


       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
       params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
       params.put("HOTEN", this.benhNhan.getBenhnhanHoten());

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
       if (this.sNoiDungThu != null) {
         params.put("NOIDUNG", this.sNoiDungThu);
       }
       System.out.println("CanLamSanPhongKhamAction.XuatReport()*******************************matd=" + this.thamKham.getTiepdonMa().getTiepdonMa());
       HsThtoank hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(this.thamKham.getTiepdonMa().getTiepdonMa());

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
       params.put("THUNGAN", thungan);
       System.out.println("CanLamSanPhongKhamAction.XuatReport()********************************thungan= " + thungan);


       ClsKhamDelegate clsKhamDe = ClsKhamDelegate.getInstance();
       Double tienBNTra = clsKhamDe.getSoTienTuPhieu(this.thamKham.getTiepdonMa().getTiepdonMa(), this.maPhieu);
       tienBNTra = Double.valueOf(tienBNTra.doubleValue() + this.tienThuocTT.doubleValue());
       params.put("SOTIEN", Utils.formatNumberWithSeprator(tienBNTra));
       params.put("TIENBANGCHU", Utils.NumberToString(tienBNTra.doubleValue()));



       Double thuoc = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankThuoc() == null) {
         thuoc = new Double(0.0D);
       } else {
         thuoc = hsttk.getHsthtoankThuoc();
       }
       Double thuocndm = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankThuocndm() == null) {
         thuocndm = new Double(0.0D);
       } else {
         thuocndm = hsttk.getHsthtoankThuocndm();
       }
       Double tienthuoc = Double.valueOf(thuoc.doubleValue() + thuocndm.doubleValue());
       params.put("THUOC", Utils.formatNumberWithSeprator(tienthuoc));
       log.info("================= " + tienthuoc);

       Double mau = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankMau() == null) {
         mau = new Double(0.0D);
       } else {
         mau = hsttk.getHsthtoankMau();
       }
       params.put("MAU", Utils.formatNumberWithSeprator(mau));
       log.info("================= " + mau);

       Double xntdcn = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankXntdcn() == null) {
         xntdcn = new Double(0.0D);
       } else {
         xntdcn = hsttk.getHsthtoankXntdcn();
       }
       params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
       log.info("================= " + xntdcn);

       Double cdha = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankCdha() == null) {
         cdha = new Double(0.0D);
       } else {
         cdha = hsttk.getHsthtoankCdha();
       }
       params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
       log.info("================= " + cdha);

       Double pt = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankPhauthuat() == null) {
         pt = new Double(0.0D);
       } else {
         pt = hsttk.getHsthtoankPhauthuat();
       }
       Double ptndm = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankPhauthuatndm() == null) {
         ptndm = new Double(0.0D);
       } else {
         ptndm = hsttk.getHsthtoankPhauthuatndm();
       }
       Double tienpt = Double.valueOf(pt.doubleValue() + ptndm.doubleValue());
       params.put("PTTT", Utils.formatNumberWithSeprator(tienpt));
       log.info("================= " + tienpt);

       Double dvktc = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankDvktc() == null) {
         dvktc = new Double(0.0D);
       } else {
         dvktc = hsttk.getHsthtoankDvktc();
       }
       params.put("DVKTC", Utils.formatNumberWithSeprator(dvktc));
       log.info("================= " + dvktc);

       Double vtth = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankVtth() == null) {
         vtth = new Double(0.0D);
       } else {
         vtth = hsttk.getHsthtoankVtth();
       }
       Double vtthndm = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankVtthndm() == null) {
         vtthndm = new Double(0.0D);
       } else {
         vtthndm = hsttk.getHsthtoankVtthndm();
       }
       Double tienvtth = Double.valueOf(vtth.doubleValue() + vtthndm.doubleValue());
       params.put("VTTH", Utils.formatNumberWithSeprator(tienvtth));
       log.info("================= " + tienvtth);

       Double dvp = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankPhong() == null) {
         dvp = new Double(0.0D);
       } else {
         dvp = hsttk.getHsthtoankPhong();
       }
       Double dvpndm = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankPhongndm() == null) {
         dvpndm = new Double(0.0D);
       } else {
         dvpndm = hsttk.getHsthtoankPhongndm();
       }
       Double tiendvp = Double.valueOf(dvp.doubleValue() + dvpndm.doubleValue());
       params.put("DVP", Utils.formatNumberWithSeprator(tiendvp));
       log.info("================= " + tiendvp);

       Double cv = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankCpvc() == null) {
         cv = new Double(0.0D);
       } else {
         cv = hsttk.getHsthtoankCpvc();
       }
       params.put("CV", Utils.formatNumberWithSeprator(cv));
       log.info("================= " + cv);

       Double cls = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankCls() == null) {
         cls = new Double(0.0D);
       } else {
         cls = hsttk.getHsthtoankCls();
       }
       Double clsndm = Double.valueOf(0.0D);
       if (hsttk.getHsthtoankClsndm() == null) {
         clsndm = new Double(0.0D);
       } else {
         clsndm = hsttk.getHsthtoankClsndm();
       }
       Double tiencls = Double.valueOf(cls.doubleValue() + clsndm.doubleValue());
       params.put("CLS", Utils.formatNumberWithSeprator(tiencls));
       log.info("================= " + tiencls);




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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3232_Phieuthanhtoanclsphongkham");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + this.index);
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

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if (this.thamKham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamKham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
     if ((this.clskham.getClskhamNgaygiott() != null) && (!this.clskham.getClskhamNgaygiott().equals(""))) {
       this.ngayTt = formatter.format(Long.valueOf(this.clskham.getClskhamNgaygiott().getTime()));
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
     log.info("End setOtherValue()");
   }

   public void subDisplayInfor()
   {
     log.info("Begin subDisplayInfor(), tiepDonMa = " + this.thamKham.getTiepdonMa());
     String maTD = this.thamKham.getTiepdonMa().getTiepdonMa();
     log.info("maTD = " + maTD);
     ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(maTD);
     if (cvnt != null)
     {
       hasNoMaTiepDon();
       FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
       this.listCtkq = new ArrayList();
       ResetForm();
       return;
     }
     if ((this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa().equals("")))
     {
       hasNoMaTiepDon();
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD, new Object[0]);
       this.listCtkq = new ArrayList();
       ResetForm();
       return;
     }
     ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
     ThamKham tk = tkDelegate.findByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa());
     if (tk != null)
     {
       this.thamKham = tk;
       SetInforUtil.setInforIfNullForThamKham(this.thamKham);
       SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());
       this.benhNhan = this.thamKham.getTiepdonMa().getBenhnhanMa();
       SetInforUtil.setInforIfNullForBN(this.benhNhan);
     }
     else
     {
       hasNoMaTiepDon();
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + maTD, new Object[0]);
       this.listCtkq = new ArrayList();
       ResetForm();
       return;
     }
   }

   public void SetInforAllCLS(List<ClsKham> clskham_tmp)
   {
     if ((clskham_tmp == null) || (clskham_tmp.size() == 0))
     {
       log.info("displayInfor   tham kham 22 ....." + this.clskham);
       this.listCtkq = new ArrayList();
       log.info("displayInfor   tham kham 23 ....." + this.clskham);
     }
     else
     {
       this.listCtkq = clskham_tmp;



       List<ClsKham> listClsKhamTmp = new ArrayList();
       int lan = 0;
       int tra = 0;
       for (int i = 0; i < this.listCtkq.size(); i++)
       {
         setInforIfNullForClsKham((ClsKham)this.listCtkq.get(i));
         ((ClsKham)this.listCtkq.get(i)).setClskhamThamkham(this.thamKham);
       }
     }
   }

   boolean displayByMaPhieu = false;
   boolean fromGhiNhan;

   public void displayInforMaPhieu()
     throws Exception
   {
     try
     {
       if ((this.maPhieuTT == null) || (this.maPhieuTT.equals(""))) {
         return;
       }
       HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();

       HsThtoankBackup hsttk_temp = hsBKDel.findByMaPhieu(this.maPhieuTT);
       if ((hsttk_temp == null) || (hsttk_temp.equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { this.maPhieuTT });
         ResetForm();
         return;
       }
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.thamKham = tkDelegate.findByMaTiepDon(hsttk_temp.getTiepdonMa().getTiepdonMa());
       if (!this.thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP"))
       {
         FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_THUPHI, new Object[0]);
         ResetForm();
       }
       else
       {
         this.disbledHuyPhieu = "false";
         this.displayByMaPhieu = true;
         this.maPhieuTT = hsttk_temp.getHsthtoankMa();
         this.maPhieu = this.maPhieuTT;
         this.kyhieu = hsttk_temp.getHsthtoankKyhieu();
         this.quyen = hsttk_temp.getHsthtoankQuyen();
         this.bienlai = hsttk_temp.getHsthtoankBienlai();
         this.sNoiDungThu = hsttk_temp.getHsthtoankLydott();
         this.nhanVienThungan = hsttk_temp.getHsthtoankThungan();
         if (this.nhanVienThungan == null) {
           refreshnhanvienthungan();
         }
         SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
         if ((hsttk_temp != null) && (hsttk_temp.getHsthtoankNgaygiott() != null))
         {
           Date ngayGioTT = hsttk_temp.getHsthtoankNgaygiott();
           this.gioThanhToan = Utils.getGioPhut(ngayGioTT);
           this.ngayTt = formatter.format(Long.valueOf(ngayGioTT.getTime()));
         }
         Double thatthu_temp = hsttk_temp.getHsthtoankThatthu();
         log.info("In displayInforMaPhieu(), that thu = " + this.thatthu + ", thatthu_temp = " + thatthu_temp);

         SetInforUtil.setInforIfNullForThamKham(this.thamKham);
         SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());
         this.benhNhan = this.thamKham.getTiepdonMa().getBenhnhanMa();
         SetInforUtil.setInforIfNullForBN(this.benhNhan);
         if (this.benhNhan.getBenhnhanNgaysinh() != null) {
           this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
         }
         this.thatthu = thatthu_temp;
         ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
         List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieu(this.maPhieuTT);
         ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
         this.ctClsPhongKhams = thuocPKDele.findByMaPhieu(this.maPhieuTT);
         SetInforAllCLS(clskham_tmp);

         this.permiengiam = 0;
         this.miengiam = hsttk_temp.getHsthtoankXetgiam();
         this.thatthu = hsttk_temp.getHsthtoankThatthu();
         this.perbhytchi = hsttk_temp.getHsthtoankTylebh().shortValue();

         this.conlai = new Double(0.0D);
         this.cls = new Double(0.0D);
         for (ClsKham eachCls : clskham_tmp)
         {
           this.cls = Double.valueOf(this.cls.doubleValue() + eachCls.getClskhamDongia().doubleValue() * eachCls.getClskhamLan().shortValue());
           if ((eachCls.getClskhamTra() != null) && (eachCls.getClskhamTra().shortValue() > 0)) {
             this.disbledHuyPhieu = "true";
           }
         }
         this.thuoc = new Double(0.0D);
         for (ThuocPhongKham eachTpk : this.ctClsPhongKhams) {
           this.thuoc = Double.valueOf(this.thuoc.doubleValue() + (eachTpk.getThuocphongkhamThanhtien() == null ? 0.0D : eachTpk.getThuocphongkhamThanhtien().doubleValue()));
         }
         this.bntra = (this.thanhtien1 = Double.valueOf(this.cls.doubleValue() + this.thuoc.doubleValue()));
         this.bntrakn = this.bntra;
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
         this.resultHidden = "success";
       }
     }
     catch (Exception e)
     {
       log.info(" ERROR : " + e);
       e.printStackTrace();
     }
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
     log.info("####################  End displayInforMaPhieu() ######################");
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       log.info("Begining displayInfor(), thamkham = " + this.thamKham + "tiepDonMa = " + this.thamKham.getTiepdonMa());

       subDisplayInfor();
       log.info("After subDisplayInfor(), thamkham = " + this.thamKham + "tiepDonMa = " + this.thamKham.getTiepdonMa());
       if ((this.thamKham == null) || (this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa() == null)) {
         return;
       }
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
       List<ClsKham> clskham_tmp;
       if ((this.maBanKham == null) || (this.maBanKham.equals("")))
       {
         log.info("no ban kham");
         clskham_tmp = clsKhamDelegate.findByTiepdonma(this.thamKham.getTiepdonMa().getTiepdonMa());

         this.ctClsPhongKhams = thuocPKDele.findByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
       }
       else
       {
         log.info("having ban kham");
         clskham_tmp = clsKhamDelegate.findByBanKhamVaMaTiepDon(this.maBanKham, this.thamKham.getTiepdonMa().getTiepdonMa());

         this.ctClsPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.thamKham.getTiepdonMa().getTiepdonMa(), this.maBanKham, Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
       }
       SetInforAllCLS(clskham_tmp);
       Caculation(this.listCtkq);
       setOtherValue();


       this.bntrakn = new Double(0.0D);
       for (ClsKham eachCls : this.listCtkq) {
         if ((eachCls.getClskhamDatt() == null) || (!eachCls.getClskhamDatt().booleanValue())) {
           this.bntrakn = Double.valueOf(this.bntrakn.doubleValue() + eachCls.getClskhamDongia().doubleValue() * (eachCls.getClskhamLan().shortValue() - (eachCls.getClskhamTra() == null ? 0 : eachCls.getClskhamTra().shortValue())));
         }
       }
       for (ThuocPhongKham eachTpk : this.ctClsPhongKhams) {
         if ((eachTpk.getThuocphongkhamDatt() == null) || (!eachTpk.getThuocphongkhamDatt().booleanValue())) {
           this.bntrakn = Double.valueOf(this.bntrakn.doubleValue() + (eachTpk.getThuocphongkhamThanhtien() == null ? 0.0D : eachTpk.getThuocphongkhamThanhtien().doubleValue()));
         }
       }
       if (!this.thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals("TP"))
       {
         FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_THUPHI, new Object[0]);
         ResetForm();
       }
       else if (("CCL".equals(this.thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())) || ("CCN".equals(this.thamKham.getTiepdonMa(true).getTiepdonBankham(true).getDtdmbankhamMa())))
       {
         FacesMessages.instance().add(IConstantsRes.DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY, new Object[0]);
         ResetForm();
       }
       else
       {
         Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(this.thamKham.getTiepdonMa().getTiepdonMa());
         if (hsba != null)
         {
           HsThtoan hstt = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
           if (hstt != null) {
             FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + (hstt.getHsthtoanNgaytt() != null ? IConstantsRes.DA_THANH_TOAN : IConstantsRes.CHUA_THANH_TOAN) + ")", hsba.getHsbaSovaovien() });
           } else {
             FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + IConstantsRes.CHUA_THANH_TOAN + ")", hsba.getHsbaSovaovien() });
           }
         }
       }
       if (this.fromGhiNhan)
       {
         this.fromGhiNhan = false;
       }
       else
       {
         this.resultHidden = "";
         this.maPhieu = "";
         this.maPhieuTT = "";
       }
       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa());
       this.strBankham = "";
       for (ThamKham eachTk : listTk) {
         this.strBankham += (eachTk.getThamkhamBankham() == null ? "" : this.strBankham.trim().length() > 0 ? ", " + eachTk.getThamkhamBankham().getDtdmbankhamMa() : eachTk.getThamkhamBankham() == null ? "" : eachTk.getThamkhamBankham().getDtdmbankhamMa());
       }
       this.displayByMaPhieu = false;
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("error on function displayInfor" + e);
     }
     log.info("End displayInfor()");
   }

   private void removeIfNullForClsKham(ClsKham cls)
   {
     log.info("**********Begin setAllNullForClsKham()***********");
     if ("".equals(Utils.reFactorString(cls.getClskhamMahang().getDtdmclsbgMa()))) {
       cls.setClskhamMahang(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa()))) {
       cls.setClskhamKhoa(null);
     }
     log.info("----------cls.getClskhamChedott():" + cls.getClskhamChedott());
     if ((cls.getClskhamChedott() != null) && ("".equals(Utils.reFactorString(cls.getClskhamChedott().getDmdoituongMa()))))
     {
       cls.setClskhamChedott(null);
       log.info("Che do tt null");
     }
     if ((cls.getClskhamKhoa2() != null) && ("".equals(Utils.reFactorString(cls.getClskhamKhoa2().getDmkhoaMa()))))
     {
       cls.setClskhamKhoa2(null);
       log.info("Khoa 2 null");
     }
     if ((cls.getClskhamMabs() != null) && ("".equals(Utils.reFactorString(cls.getClskhamMabs().getDtdmnhanvienMa()))))
     {
       cls.setClskhamMabs(null);
       log.info("Bac si null");
     }
     if ((cls.getClskhamMaloai() != null) && ("".equals(Utils.reFactorString(cls.getClskhamMaloai().getDtdmclsMa()))))
     {
       cls.setClskhamMaloai(null);
       log.info("Ma loai null");
     }
     if ((cls.getClskhamNhanviencn() != null) && ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn().getDtdmnhanvienMa()))))
     {
       cls.setClskhamNhanviencn(null);
       log.info("Nhan vien cn null");
     }
     if ((cls.getClskhamThuchien() != null) && ("".equals(Utils.reFactorString(cls.getClskhamThuchien().getDtdmnhanvienMa()))))
     {
       cls.setClskhamThuchien(null);
       log.info("Thuc hien  null");
     }
     if ((cls.getClskhamThungan() != null) && ("".equals(Utils.reFactorString(cls.getClskhamThungan().getDtdmnhanvienMa()))))
     {
       cls.setClskhamThungan(null);
       log.info("Thu ngan null");
     }
     log.info("**********End setAllNullForClsKham()***********");
   }

   private void setInforIfNullForClsKham(ClsKham cls)
   {
     log.info("Begining setInforIfNullForClsKham (): " + cls);
     if (cls.getClskhamMahang() == null) {
       cls.setClskhamMahang(new DtDmClsBangGia());
     }
     if (cls.getClskhamKhoa() == null) {
       cls.setClskhamKhoa(new DmKhoa());
     }
     if (cls.getClskhamChedott() == null) {
       cls.setClskhamChedott(new DmDoiTuong());
     }
     log.info("----------cls.getClskhamChedott():" + cls.getClskhamChedott());
     if (cls.getClskhamKhoa2() == null) {
       cls.setClskhamKhoa2(new DmKhoa());
     }
     if (cls.getClskhamMabs() == null) {
       cls.setClskhamMabs(new DtDmNhanVien());
     }
     if (cls.getClskhamMaloai() == null) {
       cls.setClskhamMaloai(new DtDmCls());
     }
     if (cls.getClskhamNhanviencn() == null) {
       cls.setClskhamNhanviencn(new DtDmNhanVien());
     }
     if (cls.getClskhamThuchien() == null) {
       cls.setClskhamThuchien(new DtDmNhanVien());
     }
     if (cls.getClskhamThungan() == null) {
       cls.setClskhamThungan(new DtDmNhanVien());
     }
     log.info("End setInforIfNullForClsKham (): ");
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");

     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();
     this.thamKham.setTiepdonMa(new TiepDon());
     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());

     this.clskham = new ClsKham();
     this.clskham.setClskhamThamkham(this.thamKham);
     setInforIfNullForClsKham(this.clskham);
     this.gioi = "";
     this.tuoi = "";
     this.ngaySinh = "";


     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayTt = formatter.format(cal.getTime());
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());


     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.maKhoa = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.conlai = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.bntrakn = new Double(0.0D);
     this.listCtkq = new ArrayList();
     this.ctClsPhongKhams = new ArrayList();
     this.resultHidden = "";
     this.maPhieu = "";

     this.maBanKham = "";

     this.sNoiDungThu = IConstantsRes.NOI_DUNG_THU_CLS;
     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
     this.maPhieuTT = "";

     this.cls = null;
     this.thuoc = null;
     this.strBankham = "";
     refreshnhanvienthungan();
     log.info("End ResetForm(): ");
   }

   public void huyphieu()
   {
     log.info("Begin huyphieu, maPhieuTT = " + this.maPhieuTT);
     if ((this.maPhieuTT == null) || (this.maPhieuTT.equals(""))) {
       return;
     }
     HsThtoankBackupDelegate hsBKDel = HsThtoankBackupDelegate.getInstance();

     HsThtoankBackup hsttk_temp = hsBKDel.findByMaPhieu(this.maPhieuTT);
     if ((hsttk_temp == null) || (hsttk_temp.equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { this.maPhieuTT });
       return;
     }
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
     List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieu(this.maPhieuTT);
     for (ClsKham eachCls : clskham_tmp)
     {
       eachCls.setClskhamMaphieu(null);
       eachCls.setClskhamDatt(Boolean.valueOf(false));
       eachCls.setClskhamNgaygiott(null);
       eachCls.setClskhamTra(null);
       eachCls.setClskhamMaphieud(null);
       clsKhamDelegate.edit(eachCls);
     }
     ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
     List<ThuocPhongKham> listTpk_tmp = thuocPKDele.findByMaPhieu(this.maPhieuTT);
     for (ThuocPhongKham eachTpk : listTpk_tmp)
     {
       eachTpk.setThuocphongkhamMaphieud(null);
       eachTpk.setThuocphongkhamDatt(Boolean.valueOf(false));
       eachTpk.setThuocphongkhamNgaygiott(null);
       thuocPKDele.edit(eachTpk);
     }
     hsBKDel.remove(hsttk_temp);

     HsThtoankDelegate hsThtoankDel = HsThtoankDelegate.getInstance();
     HsThtoank hsThtoank = hsThtoankDel.find(this.maPhieuTT);
     if (hsThtoank != null)
     {
       hsThtoank.setHsthtoankNgaygiott(null);
       hsThtoank.setHsthtoankDatt(Boolean.valueOf(false));
       hsThtoankDel.edit(hsThtoank);
     }
     FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.maPhieuTT });
     ResetForm();
     this.bntrakn = new Double(0.0D);
     this.resultHidden = "";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;

   public String inPhieuThanhToan()
   {
     return XuatPhieuThanhToan();
   }

   public String XuatPhieuThanhToan()
   {
     log.info("begin XuatPhieuThanhToan() tiepdon = " + this.thamKham.getTiepdonMa());
     if (this.thamKham.getTiepdonMa() == null) {
       return null;
     }
     this.reportTypeKC = "PhieuThanhToanKCB_Thuphi";

     String baocao1 = null;
     try
     {
       log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("MATIEPDON", this.thamKham.getTiepdonMa().getTiepdonMa());
       params.put("HOTENBN", this.benhNhan.getBenhnhanHoten());

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
       params.put("DIACHI", diachistr);
       if (this.thamKham.getTiepdonMa().getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         params.put("BHYT_CO", "X");
         if (((this.thamKham.getTiepdonMa().getTiepdonTuyen() != null) && (this.thamKham.getTiepdonMa().getTiepdonTuyen().toString().equals("1"))) || ((this.thamKham.getTiepdonMa().getTiepdonCoGiayGioiThieu() != null) && (this.thamKham.getTiepdonMa().getTiepdonCoGiayGioiThieu().booleanValue()))) {
           params.put("DUNGTUYEN", "X");
         } else {
           params.put("TRAITUYEN", "X");
         }
       }
       else
       {
         params.put("BHYT_KO", "X");
       }
       if (this.thamKham.getTiepdonMa().getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(this.thamKham.getTiepdonMa().getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (this.thamKham.getTiepdonMa().getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(this.thamKham.getTiepdonMa().getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((this.thamKham.getTiepdonMa().getTiepdonSothebh() != null) && (!this.thamKham.getTiepdonMa().getTiepdonSothebh().equals("")) && (this.thamKham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa() != null) && (!this.thamKham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")) && (this.thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienMa() != null))
       {
         params.put("MATHEBHYT", this.thamKham.getTiepdonMa().getTiepdonSothebh());
         params.put("MABENHVIEN", this.thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
       }
       else
       {
         params.put("MABENHVIEN", "");
         params.put("MATHEBHYT", "");
       }
       if (this.thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", this.thamKham.getTiepdonMa().getKcbbhytMa(true).getDmbenhvienTen());
       }
       if (this.thamKham.getTiepdonMa().getTinhbhytMa(true).getDmtinhTen() != null) {
         params.put("NOICAPTHE", this.thamKham.getTiepdonMa().getTinhbhytMa(true).getDmtinhTen());
       }
       if (this.thamKham.getTiepdonMa().getTiepdonDonvigoi(true).getDmbenhvienMa() != null) {
         params.put("NOIGIOITHIEU", this.thamKham.getTiepdonMa().getTiepdonDonvigoi(true).getDmbenhvienTen());
       }
       Calendar cal = Calendar.getInstance();
       cal.setTime(this.thamKham.getThamkhamNgaygio());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));

       params.put("LYDOVAOVIEN", this.thamKham.getTiepdonMa().getTiepdonLydovaov());

       HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = new HsThtoank();
       hsttk = thanhToanDel.findBytiepdonMaLast(this.thamKham.getTiepdonMa().getTiepdonMa());
       if ((hsttk == null) || ((hsttk.getHsthtoankDatt() != null) && (!hsttk.getHsthtoankDatt().booleanValue())))
       {
         hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());

         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(this.thamKham, hsttk, Boolean.valueOf(false), null, null);
       }
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());

       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
       params.put("NGUONKHAC", hsttk.getHsthtoankNguonkhactra());
       if (hsttk.getHsthtoankNgaygiott() != null)
       {
         params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
         if (this.thamKham.getThamkhamNgaygio() != null) {
           params.put("SONGAYDT", Long.valueOf(Utils.getDaysBetween(this.thamKham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott())));
         }
       }
       if ((this.thamKham.getTiepdonMa().getTiepdonBankham() != null) &&
         (this.thamKham.getTiepdonMa().getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")))
       {
         params.put("CAPCUU", "X");
         params.put("DUNGTUYEN", "");
         params.put("TRAITUYEN", "");
       }
       params.put("PHIEUSO", hsttk.getHsthtoankMa());
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
       params.put("TYLEBH", hsttk.getHsthtoankTylebh());
       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       if ("MP".equals(this.thamKham.getTiepdonMa().getDoituongMa(true).getDmdoituongMa())) {
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
       String namsinh = "";
       if (this.thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNgaysinh() != null) {
         namsinh = sdf.format(this.thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNgaysinh());
       } else {
         namsinh = this.thamKham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanNamsinh();
       }
       params.put("namsinh", namsinh);

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamKham.getBenhicd10() != null) && (this.thamKham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           params.put("MABENHICD", maChanDoan);
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((this.thamKham.getThamkhamGhichu() != null) && (!this.thamKham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + this.thamKham.getThamkhamGhichu();
       }
       if ((this.thamKham.getBenhicd10phu1() != null) && (this.thamKham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamKham.getBenhicd10phu2() != null) && (this.thamKham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((this.thamKham.getBenhicd10phu3() != null) && (this.thamKham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((this.thamKham.getBenhicd10phu4() != null) && (this.thamKham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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
       if ((this.thamKham.getBenhicd10phu5() != null) && (this.thamKham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamKham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
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

       params.put("PHONGKHAM", this.thamKham.getThamkhamBankham().getDtdmbankhamTen());

       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa());


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
       List<ClsKham> listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(this.thamKham.getTiepdonMa().getTiepdonMa());
       if ((listClsTmp != null) && (listClsTmp.size() > 0)) {
         for (ClsKham eachCls : listClsTmp) {
           if (((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true)) || ((eachCls.getClskhamNdm() != null) && (eachCls.getClskhamNdm().booleanValue() == true) && (eachCls.getClskhamPhandv() != null))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachCls.getClskhamPhandv().doubleValue());
           }
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa(), "1");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamThanhtien().doubleValue());
           }
         }
       }
       listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa(), "3");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamThanhtien().doubleValue());
           }
         }
       }
       params.put("KHOA", bufStr.toString());
       String soTheTe_KhaiSinh_ChungSinh = this.thamKham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.thamKham.getTiepdonMa().getTiepdonKhaisinh() != null) && (!this.thamKham.getTiepdonMa().getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.thamKham.getTiepdonMa().getTiepdonKhaisinh();
         }
       }
       else if ((this.thamKham.getTiepdonMa().getTiepdonKhaisinh() != null) && (!this.thamKham.getTiepdonMa().getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.thamKham.getTiepdonMa().getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.thamKham.getTiepdonMa().getTiepdonChungsinh() != null) && (!this.thamKham.getTiepdonMa().getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.thamKham.getTiepdonMa().getTiepdonChungsinh();
         }
       }
       else if ((this.thamKham.getTiepdonMa().getTiepdonChungsinh() != null) && (!this.thamKham.getTiepdonMa().getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.thamKham.getTiepdonMa().getTiepdonChungsinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       String soTheNgheo = this.thamKham.getTiepdonMa().getTiepdonThengheo();
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
       DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(this.thamKham.getTiepdonMa().getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "PhieuThanhToanKCB");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
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
       refreshnhanvienthungan();
     }
     return "B4160_Chonmenuxuattaptin";
   }

   private String reportFinished = "";

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

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public List<ClsKham> getListCtkq()
   {
     return this.listCtkq;
   }

   public void setListCtkq(List<ClsKham> listCtkq)
   {
     this.listCtkq = listCtkq;
   }

   public ClsKham getNhapctSelected()
   {
     return this.nhapctSelected;
   }

   public void setNhapctSelected(ClsKham nhapctSelected)
   {
     this.nhapctSelected = nhapctSelected;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public ThamKham getThamKham()
   {
     return this.thamKham;
   }

   public void setThamKham(ThamKham thamKham)
   {
     this.thamKham = thamKham;
   }

   public ClsKham getClskham()
   {
     return this.clskham;
   }

   public void setClskham(ClsKham clskham)
   {
     this.clskham = clskham;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getLoai()
   {
     return this.loai;
   }

   public void setLoai(String loai)
   {
     this.loai = loai;
   }

   public String getMaCLS()
   {
     return this.maCLS;
   }

   public void setMaCLS(String maCLS)
   {
     this.maCLS = maCLS;
   }

   public Integer getMaSoCLS()
   {
     return this.maSoCLS;
   }

   public void setMaSoCLS(Integer maSoCLS)
   {
     this.maSoCLS = maSoCLS;
   }

   public short getSoLuong()
   {
     return this.soLuong;
   }

   public void setSoLuong(short soLuong)
   {
     this.soLuong = soLuong;
   }

   public Double getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(Double donGia)
   {
     this.donGia = donGia;
   }

   public Boolean getYeuCau()
   {
     return this.yeuCau;
   }

   public void setYeuCau(Boolean yeuCau)
   {
     this.yeuCau = yeuCau;
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

   public Double getConlai()
   {
     return this.conlai;
   }

   public void setConlai(Double conlai)
   {
     this.conlai = conlai;
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

   public String getNgayTt()
   {
     return this.ngayTt;
   }

   public void setNgayTt(String ngayTt)
   {
     this.ngayTt = ngayTt;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public Boolean getMien()
   {
     return this.mien;
   }

   public void setMien(Boolean mien)
   {
     this.mien = mien;
   }

   public Boolean getNgoaiDanhMuc()
   {
     return this.ngoaiDanhMuc;
   }

   public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc)
   {
     this.ngoaiDanhMuc = ngoaiDanhMuc;
   }

   public Boolean getKyThuatCao()
   {
     return this.kyThuatCao;
   }

   public void setKyThuatCao(Boolean kyThuatCao)
   {
     this.kyThuatCao = kyThuatCao;
   }

   public String getPosition()
   {
     return this.position;
   }

   public void setPosition(String position)
   {
     this.position = position;
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

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getReportFileNameHid()
   {
     return this.reportFileNameHid;
   }

   public void setReportFileNameHid(String reportFileNameHid)
   {
     this.reportFileNameHid = reportFileNameHid;
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

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
   }

   public Boolean getDichVu()
   {
     return this.dichVu;
   }

   public void setDichVu(Boolean dichVu)
   {
     this.dichVu = dichVu;
   }

   public String getKyhieu()
   {
     return this.kyhieu;
   }

   public void setKyhieu(String kyhieu)
   {
     this.kyhieu = kyhieu;
   }

   public String getQuyen()
   {
     return this.quyen;
   }

   public void setQuyen(String quyen)
   {
     this.quyen = quyen;
   }

   public String getBienlai()
   {
     return this.bienlai;
   }

   public void setBienlai(String bienlai)
   {
     this.bienlai = bienlai;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
   }

   public String getNoiDungThu()
   {
     return this.sNoiDungThu;
   }

   public void setNoiDungThu(String noiDungThu)
   {
     this.sNoiDungThu = noiDungThu;
   }

   public String getMahsttk()
   {
     return this.mahsttk;
   }

   public void setMahsttk(String mahsttk)
   {
     this.mahsttk = mahsttk;
   }

   public String getMaPhieuTT()
   {
     return this.maPhieuTT;
   }

   public void setMaPhieuTT(String maPhieuTT)
   {
     this.maPhieuTT = maPhieuTT;
   }

   public Double getTienThuocTT()
   {
     return this.tienThuocTT;
   }

   public void setTienThuocTT(Double tienThuocTT)
   {
     this.tienThuocTT = tienThuocTT;
   }

   public String getGioThanhToan()
   {
     return this.gioThanhToan;
   }

   public void setGioThanhToan(String gioThanhToan)
   {
     this.gioThanhToan = gioThanhToan;
   }

   public Double getBntrakn()
   {
     return this.bntrakn;
   }

   public void setBntrakn(Double bntrakn)
   {
     this.bntrakn = bntrakn;
   }

   public String getStrBankham()
   {
     return this.strBankham;
   }

   public void setStrBankham(String strBankham)
   {
     this.strBankham = strBankham;
   }

   public String getDisbledHuyPhieu()
   {
     return this.disbledHuyPhieu;
   }

   public void setDisbledHuyPhieu(String disbledHuyPhieu)
   {
     this.disbledHuyPhieu = disbledHuyPhieu;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.CanLamSanPhongKhamAction

 * JD-Core Version:    0.7.0.1

 */