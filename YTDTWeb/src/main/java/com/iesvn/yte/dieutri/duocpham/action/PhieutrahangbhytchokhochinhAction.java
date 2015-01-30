 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.report.XuatReportDuocPham;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperPrint;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
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
 @Name("B4444_Phieutrahangbhytchokhochinh")
 @Synchronized(timeout=6000000L)
 public class PhieutrahangbhytchokhochinhAction
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(PhieutrahangbhytchokhochinhAction.class);
   @DataModel
   private ArrayList<CtTraKhoExt> listCtTraKhoEx;
   @DataModelSelection
   private CtTraKhoExt selected;
   private PhieuTraKho phieuTra;
   private String maPhieu;
   private String ngayXuat;
   private String dmtMa;
   private Double tonkho;
   private Double xuat;
   private String tonkhoMa;
   private Integer updateItem;
   private Integer count;
   private String ngayHienTai;
   private String nguonMa;
   private String kpMa;
   private String malk;
   private Double tongTien;
   String dmKhoXuat;
   String dmKhoNhan;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   private String isUpdate;
   private Integer dmloaithuocMaso;
   private String dmloaithuocMa;
   private String dmLoaiTen;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs;
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc;
   HashMap<String, DmThuoc> hmDmThuoc;
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu;
   private String dmLoaiPhieuMa;
   String loaiPhieu;
   private List<SelectItem> listDmLoaiPhieus;
   private DmThuocDelegate dmThuocDelegate;
   private String dmtTen;
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

   @Begin(join=true)
   public String init(String khoNhan, String khoXuat)
   {
     log.info("***** init PhieutrahangbhytchokhochinhAction() *****");
     reset();
     if ("KhoNoiTru".equals(khoXuat)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     } else if ("BHYT".equals(khoXuat)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
     } else if ("KC".equals(khoXuat)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     } else if ("KTE".equals(khoXuat)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
     } else if ("NT".equals(khoXuat)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     }
     this.dmKhoXuat = khoXuat;
     this.dmKhoNhan = khoNhan;

     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();

     refreshDmLoaiThuoc();

     return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
   }

   @Factory("listCtTraKhoEx")
   public void createTable()
   {
     log.info("-----createTable()-----");
     this.listCtTraKhoEx = new ArrayList();
   }

   public String getDmtTen()
   {
     return this.dmtTen;
   }

   public void setDmtTen(String dmtTen)
   {
     this.dmtTen = dmtTen;
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
     DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.dmtMa.toUpperCase());
     if (dmThuoc != null)
     {
       setDmtTen(dmThuoc.getDmthuocTen());
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmtTen("");
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
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
         if ((dmThuoc.getDmthuocTen().trim() == this.dmtTen) || (dmThuoc.getDmthuocTen().trim().equals(this.dmtTen)))
         {
           hasTenThuoc = Boolean.valueOf(true);
           maThuoc = dmThuoc.getDmthuocMa();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setDmtMa(maThuoc);
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmtMa("");
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
       if (!this.dmloaithuocMa.equals("")) {
         if (this.dmloaithuocMa.toUpperCase().equals("TD"))
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
           loaiMa = this.dmloaithuocMa;
         }
       }
     }
     System.out.println("loaiMa: " + loaiMa);
     System.out.println("Kho: " + this.phieuTra.getDmkhoaTra().getDmkhoaMaso());

     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.loaiPhieu.equals(""))) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", this.phieuTra.getDmkhoaTra().getDmkhoaMaso());
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
     log.info("-----BEGIN onblurMaLoaiAction()-----");
     String loaihang_ma = this.dmloaithuocMa;
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     setLoaiPhieu("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
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
         setDmloaithuocMaso(dmLoaiThuoc.getDmloaithuocMaso());
         setDmloaithuocMa(dmLoaiThuoc.getDmloaithuocMa());
         break;
       }
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setLoaiPhieu("");
     setDmtMa("");
     setDmtTen("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
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

   public Integer getDmloaithuocMaso()
   {
     return this.dmloaithuocMaso;
   }

   public void setDmloaithuocMaso(Integer dmloaithuocMaso)
   {
     this.dmloaithuocMaso = dmloaithuocMaso;
   }

   public String getDmloaithuocMa()
   {
     return this.dmloaithuocMa;
   }

   public void setDmloaithuocMa(String dmloaithuocMa)
   {
     this.dmloaithuocMa = dmloaithuocMa;
   }

   public String getLoaiPhieu()
   {
     return this.loaiPhieu;
   }

   public void setLoaiPhieu(String loaiPhieu)
   {
     this.loaiPhieu = loaiPhieu;
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
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
     if (this.dmloaithuocMaso != null)
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.dmloaithuocMaso.equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " + o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public void onblurLoaiPhieuAction()
   {
     log.info("-----BEGIN onblurLoaiPhieuAction()-----");
     setDmtMa("");
     setDmtTen("");
     this.hmDmThuoc.clear();
     this.listDmThuocs.clear();
     String loaiPhieuItem = this.loaiPhieu;
     this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     refreshDmThuoc();
     log.info("-----END onblurLoaiPhieuAction()-----");
   }

   public void deleteCt()
   {
     log.info("-----deleteCt()-----");

     this.listCtTraKhoEx.remove(this.selected);
     this.count = Integer.valueOf(this.listCtTraKhoEx.size());
     tinhTien();
   }

   public String getDmKhoNhan()
   {
     return this.dmKhoNhan;
   }

   public void setDmKhoNhan(String dmKhoNhan)
   {
     this.dmKhoNhan = dmKhoNhan;
   }

   public void selectCt(Integer index)
   {
     log.info("-----selectCt()-----");
     log.info(String.format("-----selected: %s", new Object[] { this.selected.getCtTraKho().getCttrakhoThutu() }));
     log.info("***** listCtTraKhoEx.size: " + this.listCtTraKhoEx.size());
     try
     {
       this.selected = ((CtTraKhoExt)this.listCtTraKhoEx.get(index.intValue()));
       log.info("***** ton kho ma: " + ((CtTraKhoExt)this.listCtTraKhoEx.get(index.intValue())).getTonKhoTra().getTonkhoMa());
       TonKho tk = this.selected.getTonKhoTra();
       CtTraKho ctx = this.selected.getCtTraKho();
       this.updateItem = index;
       this.tonkhoMa = tk.getTonkhoMa().toString();
       log.info("-----ton kho: " + this.tonkho);

       this.tonkho = tk.getTonkhoTon();
       this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
       this.dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
       this.xuat = ctx.getCttrakhoSoluong();
     }
     catch (Exception e)
     {
       log.info("****** Error: " + e);
     }
   }

   public void tiepTucNhap()
   {
     log.info("-----tiepTucNhap()-----");
     log.info(String.format("-----index: %s", new Object[] { this.updateItem }));

     log.info("tonkhoMa:" + this.tonkhoMa);
     log.info("xuat:" + this.xuat);
     log.info("dmtMa:" + this.dmtMa);
     log.info("updateItem:" + this.updateItem);
     log.info("tonkho:" + this.tonkho);
     if ((this.xuat == null) || (this.xuat.equals("")) || (this.tonkho == null) || (this.tonkho.equals(""))) {
       return;
     }
     if (("".equals(this.tonkhoMa)) && (this.tonkhoMa == null))
     {
       log.info("-----tonkho ma is null.");
     }
     else
     {
       log.info(String.format("-----tonkho ma: %s", new Object[] { this.tonkhoMa }));
       TonKho tk = null;
       try
       {
         TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();

         tk = tkDelegate.find(Integer.valueOf(this.tonkhoMa));
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
       CtTraKho ctx = new CtTraKho();
       Double slXuat = new Double("0");
       if (this.updateItem.equals(Integer.valueOf(-1))) {
         for (int i = 0; i < this.listCtTraKhoEx.size(); i++)
         {
           CtTraKho ctxk = ((CtTraKhoExt)this.listCtTraKhoEx.get(i)).getCtTraKho();
           if (this.malk.equals(ctxk.getCttrakhoMalk()))
           {
             log.info("-----malk " + this.malk);
             slXuat = Double.valueOf(slXuat.doubleValue() + ctxk.getCttrakhoSoluong().doubleValue());
             this.updateItem = Integer.valueOf(i);
           }
         }
       }
       slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
       ctx.setCttrakhoSoluong(slXuat);
       CtTraKhoExt ctxEx = createCtTraKho(ctx, tk);
       log.info("-----xuat: " + slXuat);
       if (this.updateItem.equals(Integer.valueOf(-1)))
       {
         log.info("-----them moi ct");
         this.listCtTraKhoEx.add(ctxEx);
         this.index = 0;
         System.out.println("***** Ton kho ma: " + ctxEx.getCtTraKho().getTonkhoMa());
       }
       else
       {
         log.info("-----Cap nhat ct-----");
         if (tk != null) {
           this.listCtTraKhoEx.set(this.updateItem.intValue(), ctxEx);
         } else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, new Object[] { this.dmtMa });
         }
         log.info(String.format("-----update ct: %s", new Object[] { ctx.getCttrakhoThutu() }));
       }
       this.count = Integer.valueOf(this.listCtTraKhoEx.size());
       log.info(String.format("-----listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtTraKhoEx.size()) }));
       log.info("***** ton kho ma: " + ((CtTraKhoExt)this.listCtTraKhoEx.get(this.index)).getCtTraKho().getTonkhoMa());
       this.tonkhoMa = "";
       this.dmtMa = "";
       this.dmtTen = "";
       this.tonkho = new Double(0.0D);
       this.xuat = new Double(0.0D);
       this.updateItem = Integer.valueOf(-1);
     }
     tinhTien();
   }

   public void end()
   {
     log.info("-----end()-----");

     Date dtNgayXuat = new Date();
     if (this.listCtTraKhoEx.size() > 0)
     {
       log.info(String.format("-----ngayxuat %s", new Object[] { this.ngayXuat }));
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuTra.setPhieutrakhoNgaygiocn(cal.getTime());
         }
         catch (ParseException e)
         {
           log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtTraKho> listCtTra = new ArrayList();


         CtTraKhoExt obj = new CtTraKhoExt();
         for (int i = 0; i < this.listCtTraKhoEx.size(); i++)
         {
           CtTraKho ct = ((CtTraKhoExt)this.listCtTraKhoEx.get(i)).getCtTraKho();
           log.info(String.format("-----so luong: %s", new Object[] { ct.getCttrakhoSoluong() }));

           log.info(String.format("-----Ton kho ma in List: " + ((CtTraKhoExt)this.listCtTraKhoEx.get(i)).getCtTraKho().getTonkhoMa(), new Object[0]));
           tt += ct.getCttrakhoSoluong().doubleValue() * ct.getCttrakhoDongia().doubleValue();
           ct.setCttrakhoThutu(Short.valueOf("" + (i + 1)));
           listCtTra.add(ct);
         }
         log.info(String.format("-----thanh tien: %s", new Object[] { Double.valueOf(tt) }));
         this.phieuTra.setPhieutrakhoThanhtien(Double.valueOf(tt));
         this.phieuTra.setPhieutrakhoNgaygiocn(new Date());
         this.phieuTra.setPhieutrakhoNgay(dtNgayXuat);
         this.phieuTra.setDmloaithuocMaso(new DmLoaiThuoc(this.dmloaithuocMaso));
         this.phieuTra.setPhieutrakhoLoaiPhieu(this.loaiPhieu);
         PhieuTraKhoDelegate pxDelegate = PhieuTraKhoDelegate.getInstance();
         log.info(String.format("-----phieu xuat: %s", new Object[] { this.phieuTra }));

         clearInfor();


         this.maPhieu = pxDelegate.updatePhieuTraKho(this.phieuTra, listCtTra);
         if (this.maPhieu != "")
         {
           resetInfo();
           this.phieuTra.setPhieutrakhoMa(this.maPhieu);
           log.info(String.format("-----result: %s", new Object[] { this.maPhieu }));
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });

           this.isUpdate = "1";
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("-----destroy()-----");
   }

   public void displayPhieuXuatKho()
   {
     log.info("-----displayPhieuXuatKho()-----");
     if (!this.maPhieu.equals(""))
     {
       log.info(String.format("-----Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       try
       {
         PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
         CtTraKhoDelegate ctxWS = CtTraKhoDelegate.getInstance();


         this.phieuTra = pxkWS.findPhieuTraKhoByKhoaTra(this.maPhieu, this.phieuTra.getDmkhoaTra(true).getDmkhoaMaso());
         if (this.phieuTra != null)
         {
           this.maPhieu = this.phieuTra.getPhieutrakhoMa();
           this.dmloaithuocMaso = this.phieuTra.getDmloaithuocMaso(true).getDmloaithuocMaso();
           this.dmloaithuocMa = this.phieuTra.getDmloaithuocMaso(true).getDmloaithuocMa();
           this.dmLoaiTen = this.phieuTra.getDmloaithuocMaso(true).getDmloaithuocTen();
           this.loaiPhieu = this.phieuTra.getPhieutrakhoLoaiPhieu();
           resetInfo();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayXuat = df.format(this.phieuTra.getPhieutrakhoNgay());
           log.info(String.format("-----find ct xuat kho by phieu xuat kho ma %s", new Object[] { this.maPhieu }));
           for (CtTraKho ct : ctxWS.findByphieutrakhoMa(this.phieuTra.getPhieutrakhoMa()))
           {
             log.info("Ct xuat kho ma: " + ct.getCttrakhoMa());
             CtTraKhoExt ctxEx = new CtTraKhoExt();
             ctxEx.setCtTraKho(ct);
             this.listCtTraKhoEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtTraKhoEx.size());
           this.isUpdate = "1";
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
           reset();
         }
         tinhTien();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
         reset();
         log.error(String.format("-----Error: %s", new Object[] { e }));
       }
     }
   }

   private CtTraKhoExt createCtTraKho(CtTraKho ctx, TonKho tk)
   {
     log.info(String.format("-----createCtTraKho()-----", new Object[0]));
     log.info(String.format("-----Ct xuat kho (input): %s", new Object[] { ctx.getCttrakhoMa() }));
     log.info(String.format("-----ton kho (input): %s", new Object[] { tk.getTonkhoMa() }));

     TonKho tkTra = null;
     try
     {
       tkTra = (TonKho)BeanUtils.cloneBean(tk);
       tkTra.setTonkhoNgaygiocn(new Date());
       tkTra.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkTra != null)
     {
       tkTra.setTonkhoXuat(ctx.getCttrakhoSoluong());
       tkTra.setTonkhoTra(null);
       tkTra.setTonkhoNhap(null);
       tkTra.setTonkhoMa(tk.getTonkhoMa());
     }
     TonKho tkNhan = null;
     try
     {
       tkNhan = (TonKho)BeanUtils.cloneBean(tk);
       tkNhan.setTonkhoNgaygiocn(new Date());
       tkNhan.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(ctx.getCttrakhoSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCttrakhoDongia(tk.getTonkhoDongia());
     ctx.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
     ctx.setCttrakhoLo(tk.getTonkhoLo());
     ctx.setCttrakhoMalk(tk.getTonkhoMalienket());
     ctx.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
     ctx.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
     ctx.setCttrakhoNgaygiocn(new Date());
     ctx.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieutrakhoMa(this.phieuTra);
     ctx.setCttrakhoNgaygiocn(new Date());
     ctx.setCtxuatkhoSodangky(tk.getTonkhoSodangky());
     ctx.setTonkhoMa(tk.getTonkhoMa());
     log.info("***** Ton kho ma:" + ctx.getTonkhoMa());

     CtTraKhoExt ctxEx = new CtTraKhoExt();
     ctxEx.setCtTraKho(ctx);
     ctxEx.setTonKhoTra(tkTra);
     ctxEx.setTonKhoNhan(tkNhan);
     return ctxEx;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public PhieutrahangbhytchokhochinhAction()
   {
     this.listCtTraKhoEx = new ArrayList();

     this.selected = new CtTraKhoExt();









     this.updateItem = Integer.valueOf(-1);







     this.dmKhoXuat = "";
     this.dmKhoNhan = "";




     this.isReport = "false";




     this.dmloaithuocMaso = null;
     this.dmloaithuocMa = "";
     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();
     this.hmDmThuoc = new HashMap();

     this.hmDmLoaiPhieu = new HashMap();
     this.dmLoaiPhieuMa = "";
     this.loaiPhieu = "";
     this.listDmLoaiPhieus = new ArrayList();










































     this.dmtTen = "";
     this.listDmThuocs = new ArrayList();
















































































































































































































































































































































































































































































































































































































     this.index = 0;




     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "PhieuDuTruTraThuoc";
     log.info("Vao Method XuatReport Phieutrahangbhytchokhochinh");
     if ((this.phieuTra.getPhieutrakhoMa() != null) && (!this.phieuTra.getPhieutrakhoMa().equals(""))) {
       try
       {
         XuatReportDuocPham xuatReport = new XuatReportDuocPham();
         xuatReport.reportTypeKC = this.reportTypeKC;
         String loaiThuoc = this.loaiPhieu.substring(0, this.loaiPhieu.indexOf(" - ")).trim();
         xuatReport.XuatReportPhieuDuTruKhoLeTraThuoc(log, this.phieuTra, this.index, loaiThuoc);
         this.jasperPrintKC = xuatReport.jasperPrintKC;
         this.reportPathKC = xuatReport.reportPathKC;
         this.reportTypeKC = xuatReport.reportTypeKC;
         this.index += 1;
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4444_Phieutrahangbhytchokhochinh";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     log.info("-----reset()-----");
     this.maPhieu = "";
     this.updateItem = Integer.valueOf(-1);
     this.tonkhoMa = "";
     this.dmtMa = "";
     this.dmtTen = "";
     this.tonkho = new Double(0.0D);
     this.xuat = new Double(0.0D);
     this.nguonMa = "";
     this.kpMa = "";
     this.count = Integer.valueOf(0);
     this.index = 0;
     this.phieuTra = new PhieuTraKho();
     this.listCtTraKhoEx = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "0";
     log.info("-----identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuTra.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
     this.listDmLoaiPhieus.clear();
     this.listDmThuocs.clear();
     this.loaiPhieu = "";
     this.dmloaithuocMaso = null;
     this.dmloaithuocMa = "";
     this.dmLoaiTen = "";
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuMa = "";
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtTraKhoExt ctExt : this.listCtTraKhoEx)
     {
       Double sl = ctExt.getCtTraKho().getCttrakhoSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCtTraKho().getCttrakhoDongia();
       if (dg == null) {
         dg = new Double("0");
       }
       this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     log.info("-----tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     log.info("-----resetInfo()-----");
     if (this.phieuTra.getDmkhoaNhan() == null) {
       this.phieuTra.setDmkhoaNhan(new DmKhoa());
     }
     if (this.phieuTra.getDmkhoaTra() == null) {
       this.phieuTra.setDmkhoaTra(new DmKhoa());
     }
     if (this.phieuTra.getDmdoituongMaso() == null) {
       this.phieuTra.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuTra.getDtdmnhanvienBacsi() == null) {
       this.phieuTra.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
     if (this.phieuTra.getDtdmnhanvienCn() == null) {
       this.phieuTra.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuTra.getDtdmnhanvienPhat() == null) {
       this.phieuTra.setDtdmnhanvienPhat(new DtDmNhanVien());
     }
   }

   private void clearInfor()
   {
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmkhoaNhan().getDmkhoaMaso()))) {
       this.phieuTra.setDmkhoaNhan(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmkhoaTra().getDmkhoaMaso()))) {
       this.phieuTra.setDmkhoaTra(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmdoituongMaso().getDmdoituongMaso()))) {
       this.phieuTra.setDmdoituongMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienBacsi().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienBacsi(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienCn().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienCn(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienPhat().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienPhat(null);
     }
   }

   public void setPhieuTra(PhieuTraKho phieuXuat)
   {
     this.phieuTra = phieuXuat;
   }

   public PhieuTraKho getPhieuTra()
   {
     return this.phieuTra;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setDmtMa(String dmtMa)
   {
     this.dmtMa = dmtMa;
   }

   public String getDmtMa()
   {
     return this.dmtMa;
   }

   public void setTonkho(Double tonkho)
   {
     this.tonkho = tonkho;
   }

   public Double getTonkho()
   {
     return this.tonkho;
   }

   public void setXuat(Double xuat)
   {
     this.xuat = xuat;
   }

   public Double getXuat()
   {
     return this.xuat;
   }

   public void setTonkhoMa(String tonkhoMa)
   {
     this.tonkhoMa = tonkhoMa;
   }

   public String getTonkhoMa()
   {
     return this.tonkhoMa;
   }

   public void setUpdateItem(Integer updateItem)
   {
     this.updateItem = updateItem;
   }

   public Integer getUpdateItem()
   {
     return this.updateItem;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public static Logger getLogger()
   {
     return log;
   }

   public static void setLogger(Logger logger)
   {
     log = logger;
   }

   public ArrayList<CtTraKhoExt> getListCtXuatKhoEx()
   {
     return this.listCtTraKhoEx;
   }

   public void setListCtXuatKhoEx(ArrayList<CtTraKhoExt> listCtTraKhoEx)
   {
     this.listCtTraKhoEx = listCtTraKhoEx;
   }

   public CtTraKhoExt getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtTraKhoExt selected)
   {
     this.selected = selected;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
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

   public void setIsUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setKpMa(String kpMa)
   {
     this.kpMa = kpMa;
   }

   public String getKpMa()
   {
     return this.kpMa;
   }

   public void setNguonMa(String nguonMa)
   {
     this.nguonMa = nguonMa;
   }

   public String getNguonMa()
   {
     return this.nguonMa;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public String getMalk()
   {
     return this.malk;
   }

   public void setMalk(String malk)
   {
     this.malk = malk;
   }

   public Double getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(Double tongTien)
   {
     this.tongTien = tongTien;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
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
   public void endConversation() {}

   public class CtTraKhoExt
     implements Serializable
   {
     private static final long serialVersionUID = 0L;
     private CtTraKho ctTraKho;
     private TonKho tonKho;
     private TonKho tonKhoTra;
     private TonKho tonKhoNhan;
     private Double thanhTien;

     public Double getThanhTien()
     {
       return this.thanhTien;
     }

     public void setThanhTien(Double thanhTien)
     {
       this.thanhTien = thanhTien;
     }

     public CtTraKhoExt()
     {
       this.ctTraKho = new CtTraKho();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhTien = new Double(0.0D);
     }

     public CtTraKho getCtTraKho()
     {
       return this.ctTraKho;
     }

     public void setCtTraKho(CtTraKho ctTraKho)
     {
       this.ctTraKho = ctTraKho;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho tonKho)
     {
       this.tonKho = tonKho;
     }

     public TonKho getTonKhoTra()
     {
       return this.tonKhoTra;
     }

     public void setTonKhoTra(TonKho tonKhoTra)
     {
       this.tonKhoTra = tonKhoTra;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieutrahangbhytchokhochinhAction

 * JD-Core Version:    0.7.0.1

 */