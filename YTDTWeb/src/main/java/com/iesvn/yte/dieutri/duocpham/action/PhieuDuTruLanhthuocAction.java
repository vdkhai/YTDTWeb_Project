 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
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
 import com.iesvn.yte.entity.DmDoiTuong;
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
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Map;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map.Entry;
 import java.util.Set;
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
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4163_Phieudutrulanhthuoc")
 @Synchronized(timeout=6000000L)
 public class PhieuDuTruLanhthuocAction
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(PhieuDuTruLanhthuocAction.class);
   @DataModel
   private ArrayList<CtXuatKhoEx> listCtDtXuatKhoEx;
   @DataModelSelection
   private CtXuatKhoEx selected = new CtXuatKhoEx();
   private PhieuXuatKho phieuXuat;
   private String maPhieu;
   private String ngayXuat;
   private String dmtMa;
   private Double tonkho;
   private Double xuat;
   private String tonkhoMa;
   private Integer updateItem = Integer.valueOf(-1);
   private Integer count;
   private String ngayHienTai;
   private String nguonMa;
   private String kpMa;
   private String malk;
   private Double tongTien;
   String dmKhoXuat = "";
   String dmKhoNhan = "";
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private String isUpdate;
   private String dmLoaiTen = "";
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu = new HashMap();
   private String dmLoaiPhieuMa = "";
   String loaiPhieu = "";
   private List<SelectItem> listDmLoaiPhieus = new ArrayList();
   private DmThuocDelegate dmThuocDelegate;

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String khoNhan, String khoXuat)
   {
     log.info("***** init() *****");

     reset();
     if ("BHYT".equals(khoNhan)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
     } else if ("KC".equals(khoNhan)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     } else if ("TE".equals(khoNhan)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
     } else if ("NT".equals(khoNhan)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     }
     log.info("***** Ten chuong trinh: " + this.tenChuongTrinh);
     this.dmKhoXuat = khoXuat;
     this.dmKhoNhan = khoNhan;

     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();

     refreshDmLoaiThuoc();

     return "QuanLyKhoChinh_NhapKhoChinh_DuTruLanhThuoc";
   }

   @End
   public void endConversation() {}

   @Factory("listCtDtXuatKhoEx")
   public void createTable()
   {
     log.info(" *****createTable() *****");
     this.listCtDtXuatKhoEx = new ArrayList();
   }

   private String dmtTen = "";
   private List<SelectItem> listDmThuocs = new ArrayList();

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
     log.info("-----BEGIN onblurTenThuocAction()-----" + this.dmtTen);
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
       if (this.phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa() != null) {
         if (this.phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
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
           loaiMa = this.phieuXuat.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     System.out.println("loaiMa: " + loaiMa);
     System.out.println("Kho: " + this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());

     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.loaiPhieu.equals(""))) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, "", this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
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
     log.info("-----BEGIN onblurMaLoaiAction()-----" + this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMa());
     String loaihang_ma = this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMa();
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
         hasTenLoai = Boolean.valueOf(true);
         dmLoaiThuoc_Find = dmLoaiThuoc;
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       this.phieuXuat.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     setLoaiPhieu("");
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
     if ((this.phieuXuat != null) && (this.phieuXuat.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
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
     log.info(" *****deleteCt() *****");

     this.listCtDtXuatKhoEx.remove(this.selected);
     this.count = Integer.valueOf(this.listCtDtXuatKhoEx.size());
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
     log.info(" *****selectCt() *****");
     log.info(String.format(" *****selected: %s", new Object[] { this.selected.getCtXuatKho().getCtxuatkhoThutu() }));

     TonKho tk = this.selected.getTonkhoXuat();
     CtXuatKho ctx = this.selected.getCtXuatKho();
     this.updateItem = index;
     this.tonkhoMa = tk.getTonkhoMa().toString();
     log.info(" *****ton kho: " + this.tonkho);


     this.tonkho = tk.getTonkhoTon();
     this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
     this.dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
     this.xuat = ctx.getCtxuatkhoSoluong();
   }

   public void tiepTucNhap()
   {
     log.info(" *****tiepTucNhap() *****");
     log.info(String.format(" *****index: %s", new Object[] { this.updateItem }));

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
       log.info(" *****tonkho ma is null.");
     }
     else
     {
       log.info(String.format(" *****tonkho ma: %s", new Object[] { this.tonkhoMa }));
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
       CtXuatKho ctx = new CtXuatKho();

       Double slXuat = new Double("0");
       if (this.updateItem.equals(Integer.valueOf(-1))) {
         for (int i = 0; i < this.listCtDtXuatKhoEx.size(); i++)
         {
           CtXuatKho ctxk = ((CtXuatKhoEx)this.listCtDtXuatKhoEx.get(i)).getCtXuatKho();
           if (this.malk.equals(ctxk.getCtxuatkhoMalk()))
           {
             log.info(" *****malk " + this.malk);
             slXuat = Double.valueOf(slXuat.doubleValue() + ctxk.getCtxuatkhoSoluong().doubleValue());
             this.updateItem = Integer.valueOf(i);
           }
         }
       }
       slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
       ctx.setCtxuatkhoSoluong(slXuat);
       CtXuatKhoEx ctxEx = createCtXuatKho(ctx, tk);
       log.info(" *****xuat: " + slXuat);
       if (this.updateItem.equals(Integer.valueOf(-1)))
       {
         log.info(" *****them moi ct");
         this.listCtDtXuatKhoEx.add(ctxEx);
       }
       else
       {
         log.info(" *****Cap nhat ct *****");
         if (tk != null) {
           this.listCtDtXuatKhoEx.set(this.updateItem.intValue(), ctxEx);
         } else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, new Object[] { this.dmtMa });
         }
         log.info(String.format(" *****update ct: %s", new Object[] { ctx.getCtxuatkhoThutu() }));
       }
       this.count = Integer.valueOf(this.listCtDtXuatKhoEx.size());
       log.info(String.format(" *****listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtDtXuatKhoEx.size()) }));
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
     log.info(" *****end() *****");
     if (this.listCtDtXuatKhoEx.size() > 0)
     {
       log.info(String.format(" *****ngayxuat %s", new Object[] { this.ngayXuat }));
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           Date dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuXuat.setPhieuxuatkhoNgaylap(cal.getTime());
         }
         catch (ParseException e)
         {
           log.error(String.format(" *****Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtXuatKho> listCtx = new ArrayList();
         ArrayList<TonKho> listTkXuat = new ArrayList();
         ArrayList<TonKho> listTkNhan = new ArrayList();
         for (CtXuatKhoEx ctx : this.listCtDtXuatKhoEx)
         {
           int i = 0;
           CtXuatKho ct = ctx.getCtXuatKho();
           log.info(String.format(" *****so luong: %s", new Object[] { ct.getCtxuatkhoSoluong() }));
           tt += ct.getCtxuatkhoSoluong().doubleValue() * ct.getCtxuatkhoDongia().doubleValue();
           ct.setCtxuatkhoThutu(Short.valueOf("" + (i + 1)));
           listCtx.add(ctx.getCtXuatKho());
           ctx.getTonkhoXuat().setTonkhoMa(null);
           listTkXuat.add(ctx.getTonkhoXuat());
           ctx.getTonkhoNhan().setTonkhoMa(null);
           listTkNhan.add(ctx.getTonkhoNhan());
           i++;
         }
         log.info(String.format(" *****thanh tien: %s", new Object[] { Double.valueOf(tt) }));
         this.phieuXuat.setPhieuxuatkhoThanhtien(Double.valueOf(tt));
         this.phieuXuat.setPhieuxuatkhoNgaygiocn(new Date());
         this.phieuXuat.setPhieuxuatkhoLoaiPhieu(this.loaiPhieu);
         PhieuXuatKhoDelegate pxDelegate = PhieuXuatKhoDelegate.getInstance();
         log.info(String.format(" *****phieu xuat: %s", new Object[] { this.phieuXuat }));

         clearInfor();


         this.maPhieu = pxDelegate.upDatePhieuXuat(this.phieuXuat, listCtx);
         if (this.maPhieu != "")
         {
           resetInfo();
           this.phieuXuat.setPhieuxuatkhoMa(this.maPhieu);
           log.info(String.format(" *****result: %s", new Object[] { this.maPhieu }));
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });

           displayPhieuXuatKho();
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
         log.error(String.format(" *****Error: %s", new Object[] { e.toString() }));
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
     log.info(" *****destroy() *****");
   }

   public void displayPhieuXuatKho()
   {
     log.info(" *****displayPhieuXuatKho() *****");
     if (!this.maPhieu.equals(""))
     {
       log.info(String.format(" *****Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       this.listCtDtXuatKhoEx.clear();
       try
       {
         PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
         CtXuatKhoDelegate ctxWS = CtXuatKhoDelegate.getInstance();

         this.phieuXuat = pxkWS.findPhieuXuatKhoByKhoaNhan(this.maPhieu, this.phieuXuat.getDmkhoaNhan(true).getDmkhoaMaso());
         if (this.phieuXuat != null)
         {
           this.maPhieu = this.phieuXuat.getPhieuxuatkhoMa();
           this.loaiPhieu = this.phieuXuat.getPhieuxuatkhoLoaiPhieu();
           this.dmLoaiTen = this.phieuXuat.getDmloaithuocMaso(true).getDmloaithuocTen();
           resetInfo();
           log.info(String.format(" *****find ct xuat kho by phieu xuat kho ma %s", new Object[] { this.maPhieu }));
           for (CtXuatKho ct : ctxWS.findByphieuxuatkhoMa(this.phieuXuat.getPhieuxuatkhoMa()))
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             this.ngayXuat = df.format(this.phieuXuat.getPhieuxuatkhoNgaylap());
             log.info("Ct xuat kho ma: " + ct.getCtxuatkhoMa());
             log.info("getPhieuxuatkhoNgaylap: " + this.phieuXuat.getPhieuxuatkhoNgaylap());
             CtXuatKhoEx ctxEx = new CtXuatKhoEx();
             ctxEx.setCtXuatKho(ct);
             this.listCtDtXuatKhoEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtDtXuatKhoEx.size());
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
         log.error(String.format(" *****Error: %s", new Object[] { e }));
       }
     }
   }

   private CtXuatKhoEx createCtXuatKho(CtXuatKho ctx, TonKho tk)
   {
     log.info(String.format(" *****createCtXuatKho() *****", new Object[0]));
     log.info(String.format(" *****Ct xuat kho (input): %s", new Object[] { ctx.getCtxuatkhoMa() }));
     log.info(String.format(" *****ton kho (input): %s", new Object[] { tk.getTonkhoMa() }));

     TonKho tkXuat = null;
     try
     {
       tkXuat = (TonKho)BeanUtils.cloneBean(tk);
       tkXuat.setTonkhoNgaygiocn(new Date());
       tkXuat.setTonkhoMa(null);
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
     if (tkXuat != null)
     {
       tkXuat.setTonkhoXuat(ctx.getCtxuatkhoSoluong());
       tkXuat.setTonkhoTra(null);
       tkXuat.setTonkhoNhap(null);
       tkXuat.setTonkhoMa(tk.getTonkhoMa());
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
       tkNhan.setTonkhoNhap(ctx.getCtxuatkhoSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCtxuatkhoDongia(tk.getTonkhoDongia());
     ctx.setCtxuatkhoDongiaban(tk.getTonkhoDongiaban());
     ctx.setCtxuatkhoLo(tk.getTonkhoLo());
     ctx.setCtxuatkhoMalk(tk.getTonkhoMalienket());
     ctx.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
     ctx.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
     ctx.setCtxuatkhoNgaygiocn(new Date());
     ctx.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieuxuatkhoMa(this.phieuXuat);
     ctx.setCtxuatkhoNgaygiocn(new Date());
     ctx.setTonKhoMa(tk.getTonkhoMa());
     log.info(String.format(" *****ct xuat kho: %s", new Object[] { ctx.getCtxuatkhoMa() }));

     CtXuatKhoEx ctxEx = new CtXuatKhoEx();
     ctxEx.setCtXuatKho(ctx);
     ctxEx.setTonkhoXuat(tkXuat);
     ctxEx.setTonkhoNhan(tkNhan);
     return ctxEx;
   }

   public String thuchienAction()
   {
     XuatReport();
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
     this.reportTypeKC = "PhieuDuTruLanhThuocCuaKhoLe";
     log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
     if (this.phieuXuat.getPhieuxuatkhoMa() != null) {
       try
       {
         XuatReportDuocPham xuatReport = new XuatReportDuocPham();
         xuatReport.reportTypeKC = this.reportTypeKC;
         String loaiThuoc = this.loaiPhieu.substring(0, this.loaiPhieu.indexOf(" - ")).trim();
         xuatReport.XuatReportPhieuDuTruKhoLeLanhThuoc(log, this.phieuXuat, this.index, loaiThuoc);
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
       log.info(" ***** troVe()");
       return "B4163_Phieudutrulanhthuoc";
     }
     catch (Exception e)
     {
       log.info(" ***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     log.info(" *****reset() *****");
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
     this.phieuXuat = new PhieuXuatKho();
     this.listCtDtXuatKhoEx = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "0";
     log.info(" *****identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuXuat.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
     this.loaiPhieu = "";
     this.dmLoaiTen = "";
     this.listDmLoaiPhieus.clear();
     this.listDmThuocs.clear();
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuMa = "";
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtXuatKhoEx ctExt : this.listCtDtXuatKhoEx)
     {
       Double sl = ctExt.getCtXuatKho().getCtxuatkhoSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCtXuatKho().getCtxuatkhoDongia();
       if (dg == null) {
         dg = new Double("0");
       }
       this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     log.info(" *****tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     log.info(" ***** resetInfo()  *****");
     if (this.phieuXuat.getDmkhoaNhan() == null) {
       this.phieuXuat.setDmkhoaNhan(new DmKhoa());
     }
     if (this.phieuXuat.getDmkhoaXuat() == null) {
       this.phieuXuat.setDmkhoaXuat(new DmKhoa());
     }
     if (this.phieuXuat.getDmdoituongMaso() == null) {
       this.phieuXuat.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuXuat.getDmloaithuocMaso() == null) {
       this.phieuXuat.setDmloaithuocMaso(new DmLoaiThuoc());
     }
     if (this.phieuXuat.getDtdmnhanvienBacsi() == null) {
       this.phieuXuat.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
     if (this.phieuXuat.getDtdmnhanvienCn() == null) {
       this.phieuXuat.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuXuat.getDtdmnhanvienNhan() == null) {
       this.phieuXuat.setDtdmnhanvienNhan(new DtDmNhanVien());
     }
     if (this.phieuXuat.getDtdmnhanvienPhat() == null) {
       this.phieuXuat.setDtdmnhanvienPhat(new DtDmNhanVien());
     }
   }

   private void clearInfor()
   {
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmkhoaNhan().getDmkhoaMaso()))) {
       this.phieuXuat.setDmkhoaNhan(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso()))) {
       this.phieuXuat.setDmkhoaXuat(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmdoituongMaso().getDmdoituongMaso()))) {
       this.phieuXuat.setDmdoituongMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso()))) {
       this.phieuXuat.setDmloaithuocMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDtdmnhanvienBacsi().getDtdmnhanvienMaso()))) {
       this.phieuXuat.setDtdmnhanvienBacsi(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDtdmnhanvienCn().getDtdmnhanvienMaso()))) {
       this.phieuXuat.setDtdmnhanvienCn(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDtdmnhanvienNhan().getDtdmnhanvienMaso()))) {
       this.phieuXuat.setDtdmnhanvienNhan(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDtdmnhanvienPhat().getDtdmnhanvienMaso()))) {
       this.phieuXuat.setDtdmnhanvienPhat(null);
     }
   }

   public void setPhieuXuat(PhieuXuatKho phieuXuat)
   {
     this.phieuXuat = phieuXuat;
   }

   public PhieuXuatKho getPhieuXuat()
   {
     return this.phieuXuat;
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

   public ArrayList<CtXuatKhoEx> getListCtXuatKhoEx()
   {
     return this.listCtDtXuatKhoEx;
   }

   public void setListCtXuatKhoEx(ArrayList<CtXuatKhoEx> listCtXuatKhoEx)
   {
     this.listCtDtXuatKhoEx = listCtXuatKhoEx;
   }

   public CtXuatKhoEx getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtXuatKhoEx selected)
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

   public String getLoaiPhieu()
   {
     return this.loaiPhieu;
   }

   public void setLoaiPhieu(String loaiPhieu)
   {
     this.loaiPhieu = loaiPhieu;
   }

   public String getDmLoaiPhieuMa()
   {
     return this.dmLoaiPhieuMa;
   }

   public void setDmLoaiPhieuMa(String dmLoaiPhieuMa)
   {
     this.dmLoaiPhieuMa = dmLoaiPhieuMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieuDuTruLanhthuocAction

 * JD-Core Version:    0.7.0.1

 */