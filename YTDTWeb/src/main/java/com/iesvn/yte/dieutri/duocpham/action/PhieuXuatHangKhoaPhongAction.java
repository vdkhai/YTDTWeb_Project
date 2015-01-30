 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.sql.Connection;
 import java.sql.DriverManager;
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
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4123_Phieuxuathangkhoaphong")
 @Synchronized(timeout=6000000L)
 public class PhieuXuatHangKhoaPhongAction
   implements Serializable
 {
   private static final long serialVersionUID = 4366573502725366923L;
   private static Logger log = Logger.getLogger(PhieuXuatHangKhoaPhongAction.class);
   private SimpleDateFormat formatter;
   private PhieuXuatKho pxuatkho;
   private String ngayxuat;
   private DmPhanLoaiThuoc plthuoc;
   private DmNguonKinhPhi nguonkinhphi;
   private DmNguonChuongTrinh nguonchuongtrinh;
   @DataModel
   private List<CtXuatKhoExt> listCtXuatKhoExt;
   @DataModelSelection
   private CtXuatKhoExt selectedCtXuatKhoExt;
   private CtXuatKhoExt ctXuatKhoExt;
   private boolean flagUpdateCtxuatkho;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private String nofound;
   private String nosuccess;
   private String noinphieu;
   private Integer count;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   String dmKhoXuat;
   private String thanhly;
   HashMap<String, DmThuoc> hmDmThuoc;
   private String dmLoaiTen;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs;
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc;
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu;
   private String dmLoaiPhieuMa;
   String loaiPhieu;
   private List<SelectItem> listDmLoaiPhieus;
   private DmThuocDelegate dmThuocDelegate;
   private List<SelectItem> listDmThuocs;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC;

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String kho, String thanhly)
   {
     log.info("***** init() *****");
     resetValue();

     this.dmKhoXuat = kho;
     log.info("***** Kho:? " + kho);
     log.info("***** thanh ly:? :" + thanhly);
     this.thanhly = thanhly;
     if ("NT".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     } else if ("BHYT".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
     } else if ("KC".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     } else if ("TE".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
     }
     this.listDmLoaiThuocs.clear();
     this.listDmLoaiPhieus.clear();
     this.listDmThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();
     refreshDmLoaiThuoc();
     log.info("***** ten chuong trinh: " + this.tenChuongTrinh);
     log.info("***** end init() *****");

     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
   }

   public List<SelectItem> getListDmThuocs()
   {
     return this.listDmThuocs;
   }

   public void setListDmThuocs(List<SelectItem> listDmThuocs)
   {
     this.listDmThuocs = listDmThuocs;
   }

   public void onblurMaThuocAction()
   {
     log.info("-----BEGIN onblurMaThuocAction()-----");
     if ((this.ctXuatKhoExt != null) && (this.ctXuatKhoExt.tonKho != null) && (this.ctXuatKhoExt.tonKho.getDmthuocMaso() != null))
     {
       String maThuoc = this.ctXuatKhoExt.tonKho.getDmthuocMaso().getDmthuocMa();
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(maThuoc.toUpperCase());
       if (dmThuoc != null)
       {
         this.ctXuatKhoExt.tonKho.setDmthuocMaso(dmThuoc);
         log.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.ctXuatKhoExt.tonKho.setDmthuocMaso(new DmThuoc());
         this.ctXuatKhoExt.tonKho.setDmthuocMaso(null);
       }
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
     if ((this.ctXuatKhoExt != null) && (this.ctXuatKhoExt.tonKho != null) && (this.ctXuatKhoExt.tonKho.getDmthuocMaso() != null))
     {
       String tenThuoc = this.ctXuatKhoExt.tonKho.getDmthuocMaso().getDmthuocTen();
       Boolean hasTenThuoc = Boolean.valueOf(false);
       String maThuoc = null;
       if (this.hmDmThuoc != null)
       {
         Set set = this.hmDmThuoc.entrySet();
         Iterator i = set.iterator();
         while (i.hasNext())
         {
           Map.Entry me = (Map.Entry)i.next();
           DmThuoc dmThuoc = (DmThuoc)me.getValue();
           if ((dmThuoc.getDmthuocTen().trim() == tenThuoc) || (dmThuoc.getDmthuocTen().trim().equals(tenThuoc)))
           {
             hasTenThuoc = Boolean.valueOf(true);
             maThuoc = dmThuoc.getDmthuocMa();
             break;
           }
         }
       }
       if (hasTenThuoc.booleanValue())
       {
         DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
         DmThuoc dmThuoc = (DmThuoc)dieutriUtilDelegate.findByMa(maThuoc, "DmThuoc", "dmthuocMa");
         this.ctXuatKhoExt.tonKho.setDmthuocMaso(dmThuoc);
         log.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.ctXuatKhoExt.tonKho.setDmthuocMaso(null);
       }
     }
     log.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();

     String loaiMa = "";
     if ((this.loaiPhieu != null) || (!this.loaiPhieu.equals(""))) {
       if (this.pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa() != null) {
         if (this.pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
         {
           if ((!this.dmLoaiPhieuMa.equals("HT")) && (!this.dmLoaiPhieuMa.equals("GN")))
           {
             loaiMa = "TD-TT";
             DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
             if (lp.getDmloaiphieuDvt() != null) {
               loaiMa = loaiMa + ";" + lp.getDmloaiphieuDvt();
             }
           }
           else
           {
             loaiMa = "TD-" + this.dmLoaiPhieuMa;
           }
         }
         else {
           loaiMa = this.pxuatkho.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     System.out.println("loaiMa: " + loaiMa);
     System.out.println("Kho: " + this.pxuatkho.getDmkhoaXuat().getDmkhoaMaso());

     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.loaiPhieu.equals(""))) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", this.pxuatkho.getDmkhoaXuat().getDmkhoaMaso());
     }
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
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
     log.info("-----BEGIN onblurMaLoaiAction()-----" + this.pxuatkho.getDmloaithuocMaso().getDmloaithuocMa());
     String loaihang_ma = this.pxuatkho.getDmloaithuocMaso().getDmloaithuocMa();
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setLoaiPhieu("");
     setDmLoaiPhieuMa("");
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
       this.pxuatkho.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setLoaiPhieu("");
     setDmLoaiPhieuMa("");
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

   public void onblurTenLoaiPhieuAction()
   {
     log.info("-----BEGIN onblurTenLoaiPhieuAction()-----");
     String loaiPhieuItem = this.loaiPhieu;
     this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     refreshDmThuoc();
     log.info("-----END onblurTenLoaiPhieuAction()-----");
   }

   public void refreshDmLoaiPhieu()
   {
     this.listDmLoaiPhieus.clear();
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
     if ((this.pxuatkho != null) && (this.pxuatkho.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.pxuatkho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " + o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public String getLoaiPhieu()
   {
     return this.loaiPhieu;
   }

   public void setLoaiPhieu(String loaiPhieu)
   {
     this.loaiPhieu = loaiPhieu;
   }

   public String getThanhly()
   {
     return this.thanhly;
   }

   public void setThanhly(String thanhly)
   {
     this.thanhly = thanhly;
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.pxuatkho = new PhieuXuatKho();
     SetInforUtil.setInforIfNullForPhieuXuatKho(this.pxuatkho);
     String format_date = "dd/MM/yyyy";
     this.formatter = new SimpleDateFormat(format_date);
     Calendar cal = Calendar.getInstance();
     this.ngayxuat = this.formatter.format(cal.getTime());
     this.ctXuatKhoExt = new CtXuatKhoExt();
     this.plthuoc = new DmPhanLoaiThuoc();
     this.nguonkinhphi = new DmNguonKinhPhi();
     this.nguonchuongtrinh = new DmNguonChuongTrinh();
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.pxuatkho.setDtdmnhanvienCn(nv);
     }
     this.flagUpdateCtxuatkho = false;
     this.listCtXuatKhoExt = new ArrayList();
     this.resultReportFileName = "";
     this.resultReportName = "";
     this.loaiFileXuat = "";
     this.nofound = "false";
     this.nosuccess = "false";
     this.noinphieu = "false";

     this.loaiPhieu = "";
     this.dmLoaiTen = "";
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();
     this.listDmLoaiPhieus.clear();

     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuMa = "";
   }

   public void enter()
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     log.info("enter");
     updateTonKho(this.ctXuatKhoExt);
     if (this.flagUpdateCtxuatkho) {
       this.flagUpdateCtxuatkho = false;
     } else {
       this.listCtXuatKhoExt.add(this.ctXuatKhoExt);
     }
     tinhTongTien();
     this.ctXuatKhoExt = new CtXuatKhoExt();
     this.count = Integer.valueOf(this.listCtXuatKhoExt.size());
   }

   private void updateTonKho(CtXuatKhoExt ctXuatKhoExt)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     Integer matonkho = ctXuatKhoExt.getTonKho().getTonkhoMa();
     if (matonkho == null) {
       return;
     }
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     TonKho tk = tkDelegate.find(matonkho);


     int updateCaseMalk = -1;

     CtXuatKho ctxk = ctXuatKhoExt.getCtXuatKho();



     String malk = ctXuatKhoExt.getTonKho().getTonkhoMalienket();
     log.info("malk:" + malk);
     Double slXuat = new Double("0");
     for (int i = 0; i < this.listCtXuatKhoExt.size(); i++)
     {
       CtXuatKho ctxk_temp = ((CtXuatKhoExt)this.listCtXuatKhoExt.get(i)).getCtXuatKho();
       if (malk.equals(ctxk_temp.getCtxuatkhoMalk()))
       {
         log.info("-----malk " + malk);
         slXuat = Double.valueOf(slXuat.doubleValue() + ctxk_temp.getCtxuatkhoSoluong().doubleValue());
         this.flagUpdateCtxuatkho = true;
         log.info("flagUpdateCtxuatkho = true");
         updateCaseMalk = i;
       }
     }
     Double xuat = ctXuatKhoExt.getCtXuatKho().getCtxuatkhoSoluong();
     slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(xuat.doubleValue()).doubleValue());
     ctxk.setCtxuatkhoSoluong(slXuat);
     log.info("slXuat:" + slXuat);





     TonKho tkXuat = duplicateTk(tk);
     if (tkXuat != null)
     {
       tkXuat.setTonkhoXuat(ctxk.getCtxuatkhoSoluong());
       tkXuat.setTonkhoNhap(null);
       tkXuat.setTonkhoTra(null);
     }
     TonKho tkNhan = duplicateTk(tk);
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(ctxk.getCtxuatkhoSoluong());
       tkNhan.setTonkhoXuat(null);
       tkNhan.setTonkhoTra(null);
     }
     ctXuatKhoExt.setTonKhoXuat(tkXuat);
     ctXuatKhoExt.setTonKhoNhan(tkNhan);
     ctxk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctxk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctxk.setDmthuocMaso(tk.getDmthuocMaso());
     ctxk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctxk.setDmnctMaso(tk.getDmnctMaso());
     ctxk.setCtxuatkhoDongia(tk.getTonkhoDongia());
     ctxk.setCtxuatkhoDongiaban(tk.getTonkhoDongiaban());
     ctxk.setCtxuatkhoLo(tk.getTonkhoLo());
     ctxk.setCtxuatkhoMalk(tk.getTonkhoMalienket());
     ctxk.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
     ctxk.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
     ctxk.setCtxuatkhoNgaygiocn(new Date());
     ctxk.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
     ctxk.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
     ctxk.setPhieuxuatkhoMa(this.pxuatkho);
     ctxk.setCtxuatkhoNgaygiocn(new Date());
     if (updateCaseMalk > -1) {
       this.listCtXuatKhoExt.set(updateCaseMalk, ctXuatKhoExt);
     }
   }

   private TonKho duplicateTk(TonKho tk)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     log.debug(String.format("-----duplicateTk()-----", new Object[0]));
     if (tk != null)
     {
       TonKho temp = (TonKho)BeanUtils.cloneBean(tk);
       temp.setTonkhoMa(null);
       temp.setTonkhoNgaygiocn(new Date());
       return temp;
     }
     return null;
   }

   private void tinhTongTien()
   {
     Double tongtien = new Double(0.0D);
     for (CtXuatKhoExt obj : this.listCtXuatKhoExt) {
       tongtien = Double.valueOf(tongtien.doubleValue() + obj.getThanhtien().doubleValue());
     }
     this.pxuatkho.setPhieuxuatkhoThanhtien(tongtien);
   }

   public void delete()
   {
     log.info("delete");
     this.listCtXuatKhoExt.remove(this.selectedCtXuatKhoExt);
     tinhTongTien();
     this.ctXuatKhoExt = new CtXuatKhoExt();

     this.count = Integer.valueOf(this.listCtXuatKhoExt.size());
   }

   public void selected()
   {
     log.info("selected");
     this.ctXuatKhoExt = this.selectedCtXuatKhoExt;
     this.flagUpdateCtxuatkho = true;
   }

   public void ghinhan()
     throws Exception
   {
     if ((this.pxuatkho.getPhieuxuatkhoMa() != null) && (!this.pxuatkho.getPhieuxuatkhoMa().equals(""))) {
       return;
     }
     log.info("ghinhan");
     if (this.listCtXuatKhoExt.size() > 0)
     {
       List<CtXuatKho> list = new ArrayList();
       List<TonKho> listTonKhoXuat = new ArrayList();
       List<TonKho> listTonKhoNhan = new ArrayList();
       for (CtXuatKhoExt obj : this.listCtXuatKhoExt)
       {
         list.add(obj.getCtXuatKho());
         listTonKhoXuat.add(obj.getTonKhoXuat());
         listTonKhoNhan.add(obj.getTonKhoNhan());
       }
       this.pxuatkho.setPhieuxuatkhoNgaygiocn(new Date());
       if (!this.ngayxuat.equals("")) {
         this.pxuatkho.setPhieuxuatkhoNgaylap(this.formatter.parse(this.ngayxuat));
       }
       this.pxuatkho.setPhieuxuatkhoLoaiPhieu(this.loaiPhieu);
       RemoveUtil.removeIfNullForPhieuXuatKho(this.pxuatkho);
       PhieuXuatKhoDelegate delegate = PhieuXuatKhoDelegate.getInstance();
       String result = delegate.createPhieuXuat(this.pxuatkho, list, listTonKhoNhan, listTonKhoXuat);
       if (result.equals(""))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
       }
       else if (result.equals("Soluongxuatkhonghople"))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUATTL, new Object[0]);
       }
       else
       {
         this.pxuatkho.setPhieuxuatkhoMa(result);
         SetInforUtil.setInforIfNullForPhieuXuatKho(this.pxuatkho);
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { result });
         displayPhieuXuatKho();
       }
       this.noinphieu = "true";
     }
     else
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
     }
   }

   public void nhapmoi()
     throws Exception
   {
     log.info("nhapmoi");
     resetValue();
   }

   public void displayPhieuXuatKho()
     throws Exception
   {
     log.info("displayPhieuXuatKho");
     this.ctXuatKhoExt = new CtXuatKhoExt();
     this.listCtXuatKhoExt.clear();
     String maPhieuXuat = this.pxuatkho.getPhieuxuatkhoMa();
     if ((maPhieuXuat != null) && (!maPhieuXuat.equals("")))
     {
       PhieuXuatKhoDelegate delegate = PhieuXuatKhoDelegate.getInstance();
       PhieuXuatKho pxk_tmp = delegate.findByPhieuxuatkhoMa(maPhieuXuat);
       if (pxk_tmp != null)
       {
         this.pxuatkho = pxk_tmp;
         SetInforUtil.setInforIfNullForPhieuXuatKho(this.pxuatkho);
         this.dmLoaiTen = this.pxuatkho.getDmloaithuocMaso(true).getDmloaithuocTen();
         this.loaiPhieu = this.pxuatkho.getPhieuxuatkhoLoaiPhieu();
         this.ngayxuat = this.formatter.format(this.pxuatkho.getPhieuxuatkhoNgaylap());
         CtXuatKhoDelegate delegateCt = CtXuatKhoDelegate.getInstance();
         for (CtXuatKho obj : delegateCt.findByphieuxuatkhoMa(this.pxuatkho.getPhieuxuatkhoMa()))
         {
           CtXuatKhoExt ct = new CtXuatKhoExt();
           ct.setCtXuatKho(obj);
           ct.setThanhtien(Double.valueOf(obj.getCtxuatkhoSoluong().doubleValue() * obj.getCtxuatkhoDongia().doubleValue()));
           this.listCtXuatKhoExt.add(ct);
         }
       }
       else
       {
         this.nofound = "true";
         this.noinphieu = "true";
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public PhieuXuatHangKhoaPhongAction()
   {
     this.flagUpdateCtxuatkho = false;




     this.isReport = "false";

















     this.dmKhoXuat = "";











     this.hmDmThuoc = new HashMap();

     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();

     this.hmDmLoaiPhieu = new HashMap();
     this.dmLoaiPhieuMa = "";
     this.loaiPhieu = "";
     this.listDmLoaiPhieus = new ArrayList();











































     this.listDmThuocs = new ArrayList();































































































































































































































































































































































































































































































     this.index = 0;




     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "D03_phieuxuatkho_khoaphong";
     log.info("Vao Method XuatReport D03_phieuxuatkho");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.pxuatkho.getPhieuxuatkhoMa().equals("")) {
       try
       {
         log.info("bat dau method XuatReport ");
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_01.jrxml";
         log.info("da thay file templete 5" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         log.info("da thay file template ");
         Map<String, Object> params = new HashMap();

         PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
         PhieuXuatKho px = pxkWS.find(this.pxuatkho.getPhieuxuatkhoMa());

         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         log.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
         Calendar cal = Calendar.getInstance();
         cal.setTime(px.getPhieuxuatkhoNgaylap());
         if (cal != null)
         {
           log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
           params.put("ngayHt", "" + cal.get(5));
           params.put("thangHt", "" + (cal.get(2) + 1));
           params.put("namHt", "" + cal.get(1));
         }
         else
         {
           log.info("-----ngay lap is null");
           params.put("ngayHt", "");
           params.put("thangHt", "");
           params.put("namHt", "");
         }
         SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
         String ngayGioHt = df.format(Calendar.getInstance().getTime());
         log.info(String.format("-----ngay gio hien tai: %s", new Object[] { ngayGioHt }));
         params.put("gioHt", ngayGioHt);

         params.put("pxMa", px.getPhieuxuatkhoMa());
         if (px.getDmkhoaNhan() != null) {
           params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (px.getDmkhoaXuat() != null) {
           params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format("-----khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", px.getPhieuxuatkhoThanhtien());
         log.info(String.format("-----tongTien: %s", new Object[] { params.get("tongTien") }));
         params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
         params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());

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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "D03_phieuxuatkho_khoaphong");
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
     }
     log.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4123_Phieuxuathangkhoaphong";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public CtXuatKhoExt getSelectedCtXuatKhoExt()
   {
     return this.selectedCtXuatKhoExt;
   }

   public void setSelectedCtXuatKhoExt(CtXuatKhoExt selectedCtXuatKhoExt)
   {
     this.selectedCtXuatKhoExt = selectedCtXuatKhoExt;
   }

   public CtXuatKhoExt getCtXuatKhoExt()
   {
     return this.ctXuatKhoExt;
   }

   public void setCtXuatKhoExt(CtXuatKhoExt ctXuatKhoExt)
   {
     this.ctXuatKhoExt = ctXuatKhoExt;
   }

   public String getNgayxuat()
   {
     return this.ngayxuat;
   }

   public void setNgayxuat(String ngayxuat)
   {
     this.ngayxuat = ngayxuat;
   }

   public PhieuXuatKho getPxuatkho()
   {
     return this.pxuatkho;
   }

   public void setPxuatkho(PhieuXuatKho pxuatkho)
   {
     this.pxuatkho = pxuatkho;
   }

   public DmPhanLoaiThuoc getPlthuoc()
   {
     return this.plthuoc;
   }

   public void setPlthuoc(DmPhanLoaiThuoc plthuoc)
   {
     this.plthuoc = plthuoc;
   }

   public DmNguonKinhPhi getNguonkinhphi()
   {
     return this.nguonkinhphi;
   }

   public void setNguonkinhphi(DmNguonKinhPhi nguonkinhphi)
   {
     this.nguonkinhphi = nguonkinhphi;
   }

   public DmNguonChuongTrinh getNguonchuongtrinh()
   {
     return this.nguonchuongtrinh;
   }

   public void setNguonchuongtrinh(DmNguonChuongTrinh nguonchuongtrinh)
   {
     this.nguonchuongtrinh = nguonchuongtrinh;
   }

   public class CtXuatKhoExt
     implements Serializable
   {
     private static final long serialVersionUID = 5212617651932213622L;
     private CtXuatKho ctXuatKho;
     private TonKho tonKho;
     private Double thanhtien;
     private TonKho tonKhoXuat;
     private TonKho tonKhoNhan;

     public CtXuatKhoExt()
     {
       this.ctXuatKho = new CtXuatKho();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhtien = new Double(0.0D);
     }

     public Double getThanhtien()
     {
       return this.thanhtien;
     }

     public void setThanhtien(Double thanhtien)
     {
       this.thanhtien = thanhtien;
     }

     public CtXuatKho getCtXuatKho()
     {
       return this.ctXuatKho;
     }

     public void setCtXuatKho(CtXuatKho ctXuatKho)
     {
       this.ctXuatKho = ctXuatKho;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho tonKho)
     {
       this.tonKho = tonKho;
     }

     public TonKho getTonKhoXuat()
     {
       return this.tonKhoXuat;
     }

     public void setTonKhoXuat(TonKho tonKhoXuat)
     {
       this.tonKhoXuat = tonKhoXuat;
     }

     public TonKho getTonKhoNhan()
     {
       return this.tonKhoNhan;
     }

     public void setTonKhoNhan(TonKho tonKhoNhan)
     {
       this.tonKhoNhan = tonKhoNhan;
     }
   }

   public List<CtXuatKhoExt> getListCtXuatKhoExt()
   {
     return this.listCtXuatKhoExt;
   }

   public void setListCtXuatKhoExt(List<CtXuatKhoExt> listCtXuatKhoExt)
   {
     this.listCtXuatKhoExt = listCtXuatKhoExt;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
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

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNoinphieu()
   {
     return this.noinphieu;
   }

   public void setNoinphieu(String noinphieu)
   {
     this.noinphieu = noinphieu;
   }

   public SimpleDateFormat getFormatter()
   {
     return this.formatter;
   }

   public void setFormatter(SimpleDateFormat formatter)
   {
     this.formatter = formatter;
   }

   public boolean isFlagUpdateCtxuatkho()
   {
     return this.flagUpdateCtxuatkho;
   }

   public void setFlagUpdateCtxuatkho(boolean flagUpdateCtxuatkho)
   {
     this.flagUpdateCtxuatkho = flagUpdateCtxuatkho;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public static long getSerialVersionUID()
   {
     return 4366573502725366923L;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public String getDmLoaiPhieuMa()
   {
     return this.dmLoaiPhieuMa;
   }

   public void setDmLoaiPhieuMa(String dmLoaiPhieuMa)
   {
     this.dmLoaiPhieuMa = dmLoaiPhieuMa;
   }

   @End
   public void end() {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieuXuatHangKhoaPhongAction

 * JD-Core Version:    0.7.0.1

 */