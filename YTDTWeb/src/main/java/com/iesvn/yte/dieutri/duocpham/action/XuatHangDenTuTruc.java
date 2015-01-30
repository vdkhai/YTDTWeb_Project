 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
 import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuDuTruDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.NumberFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import javax.faces.model.SelectItem;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4164_Phieuxuathangtutruc")
 @Synchronized(timeout=6000000L)
 public class XuatHangDenTuTruc
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String msgFail = "";
   private String msgSuccess = "";
   private String blockGhinhan = "";
   private String resultHidden = "";
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(XuatHangDenTuTruc.class);
   private PhieuXuatKho phieuXuatKho;
   private boolean updateNhapct = false;
   private String ngayXuat;
   private String maHang;
   private Integer maSoHang;
   private String tenHang;
   private Integer quyCach;
   private String donVi;
   private String tonKho;
   private Double donGia;
   private Double xuat;
   private String tongTien;
   private Integer nguonctMaso;
   private String nguonctMa;
   private Integer nguonkpMaso;
   private String nguonkpMa;
   private String maFinish;
   private String reportFinished = "";
   private String ngayhientai = "";
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private String maPhieuDuTru;
   private String maPhieuXuat;
   private Boolean hienThiGhiNhan = Boolean.valueOf(false);
   private Boolean hienThiHuyPhieu = Boolean.valueOf(false);
   private Boolean hienThiInPhieu = Boolean.valueOf(false);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @DataModel
   private List<CtXuatKho> listCtXuatTuTrucEx = new ArrayList();
   @DataModelSelection
   private CtXuatKho nhapctSelected_B4122;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private DtDmNhanVien nvCn;
   private String dmLoaiTen = "";
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   private List<SelectItem> listDmLoaiPhieus = new ArrayList();
   private DmKhoa dmKhoaXuat = new DmKhoa();

   @Begin(join=true)
   public String init(String loaikho)
     throws Exception
   {
     log.info("***Starting init ***");
     resetValue();
     this.phieuXuatKho = new PhieuXuatKho();
     SetInforUtil.setInforIfNullForPhieuXuatKho(this.phieuXuatKho);

     Calendar cal = Calendar.getInstance();

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayXuat = formatter.format(cal.getTime());
     this.maFinish = "";
     this.blockGhinhan = "";

     SimpleDateFormat formatter1 = new SimpleDateFormat(FORMAT_DATE);

     this.ngayhientai = formatter1.format(new Date());
     this.resultHidden = "";

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     setNvCn(nvDelegate.findByNd(this.identity.getUsername()));
     if (getNvCn() == null) {
       setNvCn(new DtDmNhanVien());
     }
     log.info("nvCn" + this.nvCn);
     this.phieuXuatKho.setDtdmnhanvienCn(this.nvCn);

     this.nguonctMa = "";
     this.nguonkpMa = "";
     this.nguonctMaso = null;
     this.nguonkpMaso = null;
     if (this.phieuXuatKho.getPhieudtMa().getDmloaithuocMaso() == null) {
       this.phieuXuatKho.getPhieudtMa().setDmloaithuocMaso(new DmLoaiThuoc());
     }
     if (this.phieuXuatKho.getPhieudtMa().getDmphanloaithuocMaso() == null) {
       this.phieuXuatKho.getPhieudtMa().setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
     }
     DieuTriUtilDelegate dtriUtil = DieuTriUtilDelegate.getInstance();
     if ("KhoNoiTru".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
       this.dmKhoaXuat = ((DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if ("BHYT".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
       this.dmKhoaXuat = ((DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if ("KC".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       this.dmKhoaXuat = ((DmKhoa)dtriUtil.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
     }
     this.phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
     this.dmLoaiTen = "";
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();
     log.info("***End init ***");
     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTruc";
   }

   @End
   public void end() {}

   public void resetValue()
   {
     log.info("Begin resetValue()");
     this.maHang = "";
     this.tenHang = "";
     this.quyCach = Integer.valueOf(0);
     this.donVi = "";
     this.tonKho = "";
     this.donGia = new Double(0.0D);
     this.xuat = new Double(0.0D);
     log.info("End resetValue()");
   }

   public void Caculation(List<CtXuatKho> ctxklist)
   {
     log.info("Begining Caculation: " + ctxklist);
     Double dTongTien = new Double(0.0D);
     if (ctxklist != null)
     {
       for (CtXuatKho ctxk : ctxklist)
       {
         if (ctxk.getCtxuatkhoDongia() == null) {
           ctxk.setCtxuatkhoDongia(new Double(0.0D));
         }
         if (ctxk.getCtxuatkhoSoluong() == null) {
           ctxk.setCtxuatkhoSoluong(new Double(0.0D));
         }
         dTongTien = Double.valueOf(dTongTien.doubleValue() + ctxk.getCtxuatkhoDongia().doubleValue() * ctxk.getCtxuatkhoSoluong().doubleValue());
       }
       log.info("Tong tien = " + dTongTien);
     }
     NumberFormat numberFormat = NumberFormat.getInstance();
     this.tongTien = numberFormat.format(dTongTien);

     log.info("End Caculation(): ");
   }

   private void updateRow()
   {
     copyTo(this.nhapctSelected_B4122);
     int i = this.listCtXuatTuTrucEx.indexOf(this.nhapctSelected_B4122);
     if (i < 0) {
       insertRow();
     }
     log.info("****i=" + i + "******");
     this.listCtXuatTuTrucEx.set(i, this.nhapctSelected_B4122);
     Caculation(this.listCtXuatTuTrucEx);
     this.updateNhapct = false;
   }

   private void insertRow()
   {
     log.info("begin cache chi tiet ket qua");

     CtXuatKho ctxk = new CtXuatKho();
     SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);
     copyTo(ctxk);
     this.listCtXuatTuTrucEx.add(ctxk);
     Caculation(this.listCtXuatTuTrucEx);
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     if ((this.maPhieuDuTru == null) || (this.maPhieuDuTru.equals(""))) {
       return;
     }
     if ((this.xuat == null) || (this.xuat.doubleValue() == 0.0D)) {
       return;
     }
     if ((this.maHang == null) || (this.maHang.equals(""))) {
       return;
     }
     if (this.updateNhapct) {
       updateRow();
     } else {
       insertRow();
     }
     resetValue();
     this.maFinish = "";
     this.updateNhapct = false;
     log.info("*****End Enter() *****");
   }

   private void copyTo(CtXuatKho ctXuatKho)
   {
     ctXuatKho.getDmthuocMaso().setDmthuocMaso(this.maSoHang);
     ctXuatKho.getDmthuocMaso().setDmthuocMa(this.maHang);
     ctXuatKho.getDmthuocMaso().setDmthuocTen(this.tenHang);

     ctXuatKho.getDmthuocMaso().getDmdonvitinhMaso().setDmdonvitinhTen(this.donVi);

     log.info("donVi:" + this.donVi);
     ctXuatKho.setCtxuatkhoSoluong(this.xuat);
     ctXuatKho.setCtxuatkhoDongia(this.donGia);
   }

   private void copyFrom(CtXuatKho ctXuatKho)
   {
     this.maHang = ctXuatKho.getDmthuocMaso().getDmthuocMa();
     this.maSoHang = ctXuatKho.getDmthuocMaso().getDmthuocMaso();
     this.tenHang = ctXuatKho.getDmthuocMaso().getDmthuocTen();
     this.donVi = ctXuatKho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();

     this.xuat = ctXuatKho.getCtxuatkhoSoluong();
     this.donGia = ctXuatKho.getCtxuatkhoDongia();
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     if (this.nhapctSelected_B4122 != null)
     {
       log.info("nhapctSelected.getCtxuatkhoDongia():" + this.nhapctSelected_B4122.getCtxuatkhoDongia());
       log.info("nhapctSelected.getCtxuatkhoSoluong():" + this.nhapctSelected_B4122.getCtxuatkhoSoluong());
     }
     log.info("listCtxk.indexOf(nhapctSelected):" + this.listCtXuatTuTrucEx.indexOf(this.nhapctSelected_B4122));

     this.listCtXuatTuTrucEx.remove(index);
     Caculation(this.listCtXuatTuTrucEx);
     resetValue();
     this.updateNhapct = false;
     this.maFinish = "";
     log.info("*****End delete() *****");
   }

   public void nhapctAjax()
     throws Exception
   {
     log.info("*****Begin nhapctAjax() *****");

     copyFrom(this.nhapctSelected_B4122);
     this.updateNhapct = true;
     log.info("***********end nhapctAjax***********");
   }

   public void ghinhan()
     throws Exception
   {
     log.info("*****Begin Ghi nhan() *****");

     PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     log.info("phieuXuatKho.getPhieudtMa(): " + this.phieuXuatKho.getPhieudtMa().getPhieudtMa());

     RemoveUtil.removeIfNullForPhieuXuatKho(this.phieuXuatKho);
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     if ((this.ngayXuat != null) && (!this.ngayXuat.trim().equals("")))
     {
       Date d = formatter.parse(this.ngayXuat);
       this.phieuXuatKho.setPhieuxuatkhoNgaylap(d);

       log.info("phieuXuatKho.setPhieuxuatkhoNgaylap(): " + this.phieuXuatKho.getPhieuxuatkhoNgaylap());
     }
     for (CtXuatKho ctxk : this.listCtXuatTuTrucEx) {
       RemoveUtil.removeIfNullForCTPhieuXuatKho(ctxk);
     }
     this.phieuXuatKho.setPhieuxuatkhoNgaygiocn(new Date());
     NumberFormat numberFormat = NumberFormat.getInstance();
     this.phieuXuatKho.setPhieuxuatkhoThanhtien(Double.valueOf(numberFormat.parse(this.tongTien).doubleValue()));
     this.maFinish = pxkWS.XuatPhieuDuTruTuTruc(this.listCtXuatTuTrucEx, this.phieuXuatKho, IConstantsRes.PRIORITY_TON_LO_THUOC);

     log.info("maFinish:" + this.maFinish);

     SetInforUtil.setInforIfNullForPhieuXuatKho(this.phieuXuatKho);
     for (CtXuatKho ctxk : this.listCtXuatTuTrucEx) {
       SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);
     }
     if ((this.maFinish != null) && (!this.maFinish.equals("")))
     {
       log.info("maFinish:" + this.maFinish);
       if (this.maFinish.indexOf(".") >= 0)
       {
         FacesMessages.instance().add(this.maFinish, new Object[0]);
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(true);
         this.maPhieuXuat = this.maFinish;
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.hienThiGhiNhan = Boolean.valueOf(false);
       this.hienThiInPhieu = Boolean.valueOf(false);
     }
     log.info("*****End Ghi nhan() *****");
   }

   public String getDmLoaiTen()
   {
     return this.dmLoaiTen;
   }

   public void setDmLoaiTen(String dmLoaiTen)
   {
     this.dmLoaiTen = dmLoaiTen;
   }

   public List<SelectItem> getListDmLoaiThuocs()
   {
     return this.listDmLoaiThuocs;
   }

   public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs)
   {
     this.listDmLoaiThuocs = listDmLoaiThuocs;
   }

   public void onblurMaLoaiAction()
   {
     log.info("-----BEGIN onblurMaLoaiAction()-----" + this.phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMa());
     String loaihang_ma = this.phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMa();
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     this.phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
     log.info("-----END onblurMaLoaiAction()-----");
   }

   public void onblurTenLoaiThuocAction()
   {
     log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maLoai = null;

     DmLoaiThuoc dmLoaiThuoc_Find = new DmLoaiThuoc();
     Set set = this.hmLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
       if ((dmLoaiThuoc.getDmloaithuocTen() == this.dmLoaiTen) || (dmLoaiThuoc.getDmloaithuocTen().equals(this.dmLoaiTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         dmLoaiThuoc_Find = dmLoaiThuoc;
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       this.phieuXuatKho.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     this.phieuXuatKho.setPhieuxuatkhoLoaiPhieu("");
     log.info("-----END onblurTenLoaiThuocAction()-----");
   }

   public void refreshDmLoaiThuoc()
   {
     this.listDmLoaiThuocs.clear();
     this.dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
     this.hmLoaiThuoc.clear();
     this.hmLoaiThuoc = this.dmLoaiThuocDelegate.findAllDm();
     if (this.hmLoaiThuoc != null)
     {
       Set set = this.hmLoaiThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
         this.listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
       }
     }
   }

   public List<SelectItem> getListDmLoaiPhieus()
   {
     return this.listDmLoaiPhieus;
   }

   public void setListDmLoaiPhieus(List<SelectItem> listDmLoaiPhieus)
   {
     this.listDmLoaiPhieus = listDmLoaiPhieus;
   }

   public void refreshDmLoaiPhieu()
   {
     this.listDmLoaiPhieus.clear();
     this.dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
     if ((this.phieuXuatKho != null) && (this.phieuXuatKho.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus) {
           if (this.phieuXuatKho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4122_Xuathangtheophieudt";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public void nhaplai()
     throws Exception
   {
     log.info("*****Begin sualai() *****");
     init("");
     this.listCtXuatTuTrucEx = new ArrayList();
     this.tongTien = "";
     this.maPhieuDuTru = "";
     this.maPhieuXuat = "";
     this.maFinish = "";

     this.hienThiGhiNhan = Boolean.valueOf(false);
     this.hienThiInPhieu = Boolean.valueOf(false);

     log.info("*****End sualai() *****");
   }

   public void displayInforEditPhieuXuatHangTheoPhieuDuTru()
     throws Exception
   {}

   public void displayPhieuXuatKho()
     throws Exception
   {
     log.info("Begin displayPhieuXuatKho()");
     this.listCtXuatTuTrucEx = new ArrayList();
     if ((this.maPhieuXuat == null) || (this.maPhieuXuat.equals("")))
     {
       this.hienThiGhiNhan = Boolean.valueOf(false);
       this.hienThiHuyPhieu = Boolean.valueOf(false);
       return;
     }
     PhieuXuatKhoDelegate pxkDel = PhieuXuatKhoDelegate.getInstance();
     CtXuatKhoDelegate ctxkDel = CtXuatKhoDelegate.getInstance();

     this.phieuXuatKho = pxkDel.findPhieuXuatKhoByKhoXuat(this.maPhieuXuat, this.dmKhoaXuat.getDmkhoaMaso());
     if (this.phieuXuatKho == null)
     {
       this.phieuXuatKho = new PhieuXuatKho();
       this.dmKhoaXuat = new DmKhoa();
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieuXuat });
       this.resultHidden = "fail";
       this.maPhieuDuTru = "";

       this.hienThiGhiNhan = Boolean.valueOf(false);
       this.hienThiInPhieu = Boolean.valueOf(false);
       return;
     }
     SetInforUtil.setInforIfNullForPhieuXuatKho(this.phieuXuatKho);
     this.hienThiInPhieu = Boolean.valueOf(true);
     this.hienThiGhiNhan = Boolean.valueOf(false);
     setMaPhieuXuat(this.phieuXuatKho.getPhieuxuatkhoMa());
     setMaPhieuDuTru(this.phieuXuatKho.getPhieudtMa().getPhieudtMa());
     SimpleDateFormat frmtDate = new SimpleDateFormat("dd/MM/yyyy");
     setNgayXuat(frmtDate.format(this.phieuXuatKho.getPhieuxuatkhoNgaylap()));
     this.dmLoaiTen = this.phieuXuatKho.getDmloaithuocMaso().getDmloaithuocTen();
     this.listCtXuatTuTrucEx = ctxkDel.findByphieuxuatkhoMa(this.maPhieuXuat);
     Caculation(this.listCtXuatTuTrucEx);
     this.maFinish = this.phieuXuatKho.getPhieuxuatkhoMa();
     log.info("End displayPhieuXuatKho()");
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       this.listCtXuatTuTrucEx = new ArrayList();

       log.info("Begining displayInfor()");


       CtPhieuDtDelegate ctpdtWS = CtPhieuDtDelegate.getInstance();
       if ((this.maPhieuDuTru == null) || (this.maPhieuDuTru.equals("")))
       {
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         return;
       }
       PhieuXuatKhoDelegate pxkDel = PhieuXuatKhoDelegate.getInstance();
       CtXuatKhoDelegate ctxkDel = CtXuatKhoDelegate.getInstance();

       PhieuXuatKho pxk = pxkDel.findByPhieuDuTruAndKhoXuat(this.maPhieuDuTru, this.dmKhoaXuat.getDmkhoaMaso());
       if (pxk != null)
       {
         this.phieuXuatKho = pxk;
         SetInforUtil.setInforIfNullForPhieuXuatKho(this.phieuXuatKho);
         setMaPhieuXuat(this.phieuXuatKho.getPhieuxuatkhoMa());
         setMaPhieuDuTru(this.phieuXuatKho.getPhieudtMa().getPhieudtMa());
         this.dmLoaiTen = this.phieuXuatKho.getDmloaithuocMaso().getDmloaithuocTen();
         SimpleDateFormat frmtDate = new SimpleDateFormat("dd/MM/yyyy");
         setNgayXuat(frmtDate.format(this.phieuXuatKho.getPhieuxuatkhoNgaylap()));
         this.listCtXuatTuTrucEx = ctxkDel.findByphieuxuatkhoMa(this.maPhieuXuat);
         Caculation(this.listCtXuatTuTrucEx);
         this.maFinish = this.phieuXuatKho.getPhieuxuatkhoMa();

         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG, new Object[] { this.maPhieuDuTru });
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(true);
         return;
       }
       PhieuDuTru phieuDt = new PhieuDuTru();
       phieuDt = ctpdtWS.findByPhieuDuTruKhoXuatPhanBiet(this.maPhieuDuTru, Integer.valueOf(1), this.dmKhoaXuat.getDmkhoaMaso());

       List<CtPhieuDt> ctpdt_tmp = ctpdtWS.findByPhieuDuTruMa(this.maPhieuDuTru);
       if ((phieuDt == null) || (ctpdt_tmp == null) || (ctpdt_tmp.size() == 0) || (phieuDt.equals(null)))
       {
         System.out.println("displayInfor   phieu du tru 22 IConstantsRes.PHIEU_DU_TRU_NULL....." + IConstantsRes.PHIEU_DU_TRU_NULL);
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOT_FOUND, new Object[] { this.maPhieuDuTru });
         this.resultHidden = "fail";
         this.maPhieuDuTru = "";

         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(false);
         return;
       }
       this.phieuXuatKho = new PhieuXuatKho();
       this.phieuXuatKho.setPhieudtMa(phieuDt);

       this.phieuXuatKho.setDmkhoaNhan(phieuDt.getDmkhoaMaso());
       this.phieuXuatKho.setDmkhoaXuat(phieuDt.getPhieudtMakho());

       this.maPhieuDuTru = phieuDt.getPhieudtMa();
       if (phieuDt.getDmloaithuocMaso() == null) {
         phieuDt.setDmloaithuocMaso(new DmLoaiThuoc());
       }
       if (phieuDt.getDmphanloaithuocMaso() == null) {
         phieuDt.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
       }
       this.phieuXuatKho.setDmloaithuocMaso(phieuDt.getDmloaithuocMaso());
       this.dmLoaiTen = phieuDt.getDmloaithuocMaso().getDmloaithuocTen();
       this.phieuXuatKho.setPhieuxuatkhoLoaiPhieu(phieuDt.getPhieudtLoaiPhieu());
       this.phieuXuatKho.setDtdmnhanvienCn(this.nvCn);

       SetInforUtil.setInforIfNullForPhieuXuatKho(this.phieuXuatKho);

       this.listCtXuatTuTrucEx = new ArrayList();
       if (ctpdt_tmp != null)
       {
         for (CtPhieuDt ctpdt : ctpdt_tmp)
         {
           CtXuatKho ctxk = new CtXuatKho();
           ctxk.setPhieuxuatkhoMa(this.phieuXuatKho);
           ctxk.setCtxuatkhoDongia(ctpdt.getCtdtDongia());
           ctxk.setCtxuatkhoSoluong(ctpdt.getCtdtSoluong());
           ctxk.setDmthuocMaso(ctpdt.getDmthuocMaso());

           ctxk.setCtxuatkhoMalk(ctpdt.getCtdtMalk());

           SetInforUtil.setInforIfNullForCTPhieuXuatKho(ctxk);

           this.listCtXuatTuTrucEx.add(ctxk);
         }
         Caculation(this.listCtXuatTuTrucEx);
       }
       this.hienThiGhiNhan = Boolean.valueOf(true);
       this.hienThiInPhieu = Boolean.valueOf(false);

       PhieuDuTruDelegate pdtDelegate = PhieuDuTruDelegate.getInstance();
       PhieuDuTru obj = pdtDelegate.find(this.maPhieuDuTru);
       if (obj.getPhieudtDaXuat().booleanValue())
       {
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG, new Object[] { this.maPhieuDuTru });
         this.hienThiGhiNhan = Boolean.valueOf(false);
         this.hienThiHuyPhieu = Boolean.valueOf(false);
         this.hienThiInPhieu = Boolean.valueOf(true);
       }
     }
     catch (Exception e)
     {
       log.info("error on function displayInfor" + e);
     }
     log.info("maFinish : " + this.maFinish);
     log.info("End displayInfor()");
   }

   public String thuchienAction()
   {
     log.info("Begining inphieu()");
     if (!this.maFinish.equals("")) {
       try
       {
         XuatReport();
       }
       catch (Exception e)
       {
         log.info("Loi trong khi xuat report" + e.toString());
       }
     }
     log.info("End inphieu()");

     return "B4160_Chonmenuxuattaptin";
   }

   int index = 0;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;

   public void XuatReport()
   {
     this.reportTypeKC = "B4164_Phieuxuathangtutruc";
     log.info("Vao Method XuatReport B4164_Phieuxuathangtutruc");
     String baocao1 = null;
     Date currentDate = new Date();
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_02.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       String khoa = "";







       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       Calendar cal = Calendar.getInstance();
       if (cal != null)
       {
         log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
         params.put("ngayHt", "" + cal.get(5));
         params.put("thangHt", "" + (cal.get(2) + 1));
         params.put("namHt", "" + cal.get(1));
       }
       SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
       String ngayGioHt = df.format(Calendar.getInstance().getTime());
       log.info(String.format("-----ngay gio hien tai: %s", new Object[] { ngayGioHt }));
       params.put("gioHt", ngayGioHt);

       params.put("pxMa", this.maFinish);

       DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
       this.phieuXuatKho = ((PhieuXuatKho)dieutriUtilDelegate.findByMa(this.maFinish, "PhieuXuatKho", "phieuxuatkhoMa"));
       if (this.phieuXuatKho.getDmkhoaNhan() != null) {
         params.put("khoaNhan", this.phieuXuatKho.getDmkhoaNhan().getDmkhoaTen());
       } else {
         params.put("khoaNhan", "");
       }
       log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
       if (this.phieuXuatKho.getDmkhoaXuat() != null) {
         params.put("khoaXuat", this.phieuXuatKho.getDmkhoaXuat().getDmkhoaTen());
       } else {
         params.put("khoaXuat", "");
       }
       params.put("tongTien", this.phieuXuatKho.getPhieuxuatkhoThanhtien());
       if (this.phieuXuatKho.getDtdmnhanvienCn() != null) {
         params.put("nhanvienCn", this.phieuXuatKho.getDtdmnhanvienCn().getDtdmnhanvienMa());
       }
       if (this.phieuXuatKho.getPhieuxuatkhoNgaylap() != null) {
         params.put("ngayXuat", this.phieuXuatKho.getPhieuxuatkhoNgaylap());
       }
       resetInfo();

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "B4122_XuatHangTheoPhieuDuTru");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
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

   private void resetInfo()
   {
     log.info("-----resetInfo()-----");
     if (this.phieuXuatKho.getDmloaithuocMaso() == null) {
       this.phieuXuatKho.setDmloaithuocMaso(new DmLoaiThuoc());
     }
     if (this.phieuXuatKho.getDtdmnhanvienNhan() == null) {
       this.phieuXuatKho.setDtdmnhanvienNhan(new DtDmNhanVien());
     }
     if (this.phieuXuatKho.getDmdoituongMaso() == null) {
       this.phieuXuatKho.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuXuatKho.getDtdmnhanvienCn() == null) {
       this.phieuXuatKho.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuXuatKho.getPhieudtMa().getDmphanloaithuocMaso() == null) {
       this.phieuXuatKho.getPhieudtMa().setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
     }
     if (this.phieuXuatKho.getDtdmnhanvienBacsi() == null) {
       this.phieuXuatKho.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
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

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getMaHang()
   {
     return this.maHang;
   }

   public void setMaHang(String maHang)
   {
     this.maHang = maHang;
   }

   public Integer getQuyCach()
   {
     return this.quyCach;
   }

   public void setQuyCach(Integer quyCach)
   {
     this.quyCach = quyCach;
   }

   public String getDonVi()
   {
     return this.donVi;
   }

   public void setDonVi(String donVi)
   {
     this.donVi = donVi;
   }

   public String getTonKho()
   {
     return this.tonKho;
   }

   public void setTonKho(String tonKho)
   {
     this.tonKho = tonKho;
   }

   public Double getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(Double donGia)
   {
     this.donGia = donGia;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public String getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(String tongTien)
   {
     this.tongTien = tongTien;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public String getMaFinish()
   {
     return this.maFinish;
   }

   public void setMaFinish(String maFinish)
   {
     this.maFinish = maFinish;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getMsgFail()
   {
     return this.msgFail;
   }

   public void setMsgFail(String msgFail)
   {
     this.msgFail = msgFail;
   }

   public String getMsgSuccess()
   {
     return this.msgSuccess;
   }

   public void setMsgSuccess(String msgSuccess)
   {
     this.msgSuccess = msgSuccess;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public Double getXuat()
   {
     return this.xuat;
   }

   public void setXuat(Double xuat)
   {
     this.xuat = xuat;
   }

   public String getBlockGhinhan()
   {
     return this.blockGhinhan;
   }

   public void setBlockGhinhan(String blockGhinhan)
   {
     this.blockGhinhan = blockGhinhan;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public PhieuXuatKho getPhieuXuatKho()
   {
     return this.phieuXuatKho;
   }

   public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho)
   {
     this.phieuXuatKho = phieuXuatKho;
   }

   public List<CtXuatKho> getListCtxk()
   {
     return this.listCtXuatTuTrucEx;
   }

   public void setListCtxk(List<CtXuatKho> listCtxk)
   {
     this.listCtXuatTuTrucEx = listCtxk;
   }

   public DtDmNhanVien getNvCn()
   {
     return this.nvCn;
   }

   public void setNvCn(DtDmNhanVien nvCn)
   {
     this.nvCn = nvCn;
   }

   public String getMaPhieuDuTru()
   {
     return this.maPhieuDuTru;
   }

   public void setMaPhieuDuTru(String maPhieuDuTru)
   {
     this.maPhieuDuTru = maPhieuDuTru;
   }

   public Integer getNguonctMaso()
   {
     return this.nguonctMaso;
   }

   public void setNguonctMaso(Integer nguonctMaso)
   {
     this.nguonctMaso = nguonctMaso;
   }

   public String getNguonctMa()
   {
     return this.nguonctMa;
   }

   public void setNguonctMa(String nguonctMa)
   {
     this.nguonctMa = nguonctMa;
   }

   public Integer getNguonkpMaso()
   {
     return this.nguonkpMaso;
   }

   public void setNguonkpMaso(Integer nguonkpMaso)
   {
     this.nguonkpMaso = nguonkpMaso;
   }

   public String getNguonkpMa()
   {
     return this.nguonkpMa;
   }

   public void setNguonkpMa(String nguonkpMa)
   {
     this.nguonkpMa = nguonkpMa;
   }

   public Integer getMaSoHang()
   {
     return this.maSoHang;
   }

   public void setMaSoHang(Integer maSoHang)
   {
     this.maSoHang = maSoHang;
   }

   public String getTenHang()
   {
     return this.tenHang;
   }

   public void setTenHang(String tenHang)
   {
     this.tenHang = tenHang;
   }

   public CtXuatKho getNhapctSelected_B4122()
   {
     return this.nhapctSelected_B4122;
   }

   public void setNhapctSelected_B4122(CtXuatKho nhapctSelected_B4122)
   {
     this.nhapctSelected_B4122 = nhapctSelected_B4122;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getMaPhieuXuat()
   {
     return this.maPhieuXuat;
   }

   public void setMaPhieuXuat(String maPhieuXuat)
   {
     this.maPhieuXuat = maPhieuXuat;
   }

   public Boolean getHienThiHuyPhieu()
   {
     return this.hienThiHuyPhieu;
   }

   public void setHienThiHuyPhieu(Boolean hienThiHuyPhieu)
   {
     this.hienThiHuyPhieu = hienThiHuyPhieu;
   }

   public Boolean getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(Boolean hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public void setHienThiGhiNhan(Boolean hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public Boolean getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XuatHangDenTuTruc

 * JD-Core Version:    0.7.0.1

 */