 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
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
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.TonKhoUtil;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
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
 @Name("B4121_Phieuxuathangkhole")
 @Synchronized(timeout=6000000L)
 public class XuatHangDenKhoKhac
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(XuatHangDenKhoKhac.class);
   @DataModel
   private ArrayList<CtXuatKhoEx> listCtXuatKhoEx;
   @DataModelSelection
   private CtXuatKhoEx selected;
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
   private String isFound;
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
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   String loaiPhieu = "";
   private List<SelectItem> listDmLoaiPhieus = new ArrayList();

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String khoNhan, String kho)
   {
     log.info("-----init()-----");
     reset();
     this.dmKhoXuat = kho;
     this.dmKhoNhan = khoNhan;

     return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
   }

   @End
   public void endConversation() {}

   @Factory("listCtXuatKhoEx")
   public void createTable()
   {
     log.info(" ***** createTable() ***** ");
     this.listCtXuatKhoEx = new ArrayList();
   }

   public void deleteCt()
   {
     log.info(" ***** deleteCt() ***** ");

     this.listCtXuatKhoEx.remove(this.selected);
     this.count = Integer.valueOf(this.listCtXuatKhoEx.size());
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
     log.info(" ***** selectCt() ***** ");
     log.info(String.format(" ***** selected: %s", new Object[] { this.selected.getCtXuatKho().getCtxuatkhoThutu() }));

     TonKho tk = this.selected.getTonkhoXuat();
     CtXuatKho ctx = this.selected.getCtXuatKho();
     this.updateItem = index;
     this.tonkhoMa = tk.getTonkhoMa().toString();
     log.info(" ***** ton kho: " + this.tonkho);


     this.tonkho = tk.getTonkhoTon();
     this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
     this.xuat = ctx.getCtxuatkhoSoluong();
   }

   public void tiepTucNhap()
   {
     log.info(" ***** tiepTucNhap() ***** ");
     log.info(String.format(" ***** index: %s", new Object[] { this.updateItem }));

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
       log.info(" ***** tonkho ma is null.");
     }
     else
     {
       log.info(String.format(" ***** tonkho ma: %s", new Object[] { this.tonkhoMa }));
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
       for (int i = 0; i < this.listCtXuatKhoEx.size(); i++)
       {
         CtXuatKho ctxk = ((CtXuatKhoEx)this.listCtXuatKhoEx.get(i)).getCtXuatKho();
         if (this.malk.equals(ctxk.getCtxuatkhoMalk()))
         {
           log.info(" ***** malk " + this.malk);
           slXuat = Double.valueOf(slXuat.doubleValue() + ctxk.getCtxuatkhoSoluong().doubleValue());
           this.updateItem = Integer.valueOf(i);
         }
       }
       slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
       ctx.setCtxuatkhoSoluong(slXuat);
       CtXuatKhoEx ctxEx = createCtXuatKho(ctx, tk);
       log.info(" ***** xuat: " + slXuat);
       if (this.updateItem.equals(Integer.valueOf(-1)))
       {
         log.info(" ***** them moi ct");
         this.listCtXuatKhoEx.add(ctxEx);
       }
       else
       {
         log.info(" ***** Cap nhat ct ***** ");
         if (tk != null) {
           this.listCtXuatKhoEx.set(this.updateItem.intValue(), ctxEx);
         } else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, new Object[] { this.dmtMa });
         }
         log.info(String.format(" ***** update ct: %s", new Object[] { ctx.getCtxuatkhoThutu() }));
       }
       this.count = Integer.valueOf(this.listCtXuatKhoEx.size());
       log.info(String.format(" ***** listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtXuatKhoEx.size()) }));
       this.tonkhoMa = "";
       this.dmtMa = "";
       this.tonkho = new Double(0.0D);
       this.xuat = new Double(0.0D);
       this.updateItem = Integer.valueOf(-1);
     }
     tinhTien();
   }

   public void end()
   {
     log.info(" ***** end() ***** ");
     if (this.listCtXuatKhoEx.size() > 0)
     {
       log.info(String.format(" ***** ngayxuat %s", new Object[] { this.ngayXuat }));
       log.info("**** ma phieu xuat" + this.maPhieu);
       PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
       this.phieuXuat = pxkWS.findByPhieuxuatkhoMa(this.maPhieu);
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
         catch (Exception e)
         {
           log.error(String.format(" ***** Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         for (int i = 0; i < this.listCtXuatKhoEx.size(); i++)
         {
           CtXuatKho ct = ((CtXuatKhoEx)this.listCtXuatKhoEx.get(i)).getCtXuatKho();

           boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCtxuatkhoMalk(), this.phieuXuat.getDmkhoaNhan().getDmkhoaMaso());
           if (tinhtrangKiemKe == true)
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, new Object[] { ct.getCtxuatkhoMalk() });
             return;
           }
         }
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtXuatKho> listCtx = new ArrayList();
         for (int i = 0; i < this.listCtXuatKhoEx.size(); i++)
         {
           CtXuatKho ct = ((CtXuatKhoEx)this.listCtXuatKhoEx.get(i)).getCtXuatKho();
           log.info(String.format("-----so luong: %s", new Object[] { ct.getCtxuatkhoSoluong() }));
           tt += ct.getCtxuatkhoSoluong().doubleValue() * ct.getCtxuatkhoDongia().doubleValue();
           ct.setCtxuatkhoThutu(Short.valueOf("" + (i + 1)));
           listCtx.add(ct);
         }
         log.info(String.format(" ***** thanh tien: %s", new Object[] { Double.valueOf(tt) }));

         this.phieuXuat.setPhieuxuatkhoNgaygiocn(new Date());
         this.phieuXuat.setPhieuxuatkhoNgaygiophat(new Date());
         PhieuXuatKhoDelegate pxDelegate = PhieuXuatKhoDelegate.getInstance();
         log.info(String.format(" ***** phieu xuat: %s", new Object[] { this.phieuXuat }));



         this.maPhieu = pxDelegate.thucHienPhieuXuat(this.phieuXuat, listCtx, IConstantsRes.PRIORITY_TON_LO_THUOC);
         if ((this.maPhieu != null) && (!this.maPhieu.equals("")))
         {
           log.info("maPhieu:" + this.maPhieu);
           if (this.maPhieu.indexOf(".") >= 0)
           {
             FacesMessages.instance().add(this.maPhieu, new Object[0]);
           }
           else
           {
             log.info("***** insert thanh cong ma phieu: " + this.maPhieu);
             resetInfo();

             log.info(String.format(" ***** result: %s", new Object[] { this.maPhieu }));
             FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });
             displayPhieuXuatKho();
             this.isUpdate = "0";
             this.isFound = "true";
           }
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         log.error(String.format(" ***** Error: %s", new Object[] { e.toString() }));
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
     log.info(" ***** destroy() ***** ");
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
     setLoaiPhieu("");
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
     setLoaiPhieu("");
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
     if ((this.phieuXuat != null) && (this.phieuXuat.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus) {
           if (this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
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

   public void displayPhieuXuatKho()
   {
     log.info(" ***** displayPhieuXuatKho() ***** ");
     if (!this.maPhieu.equals(""))
     {
       log.info(String.format(" ***** Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       this.listCtXuatKhoEx.clear();
       try
       {
         PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
         CtXuatKhoDelegate ctxWS = CtXuatKhoDelegate.getInstance();
         TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
         this.phieuXuat = pxkWS.findByPhieuxuatkhoMa(this.maPhieu);
         if (this.phieuXuat != null)
         {
           this.maPhieu = this.phieuXuat.getPhieuxuatkhoMa();
           this.loaiPhieu = this.phieuXuat.getPhieuxuatkhoLoaiPhieu();
           this.dmLoaiTen = this.phieuXuat.getDmloaithuocMaso(true).getDmloaithuocTen();
           resetInfo();
           log.info(String.format(" ***** find ct xuat kho by phieu xuat kho ma %s", new Object[] { this.maPhieu }));
           for (CtXuatKho ct : ctxWS.findByphieuxuatkhoMa(this.phieuXuat.getPhieuxuatkhoMa()))
           {
             log.info("Ct xuat kho ma: " + ct.getCtxuatkhoMa());
             CtXuatKhoEx ctxEx = new CtXuatKhoEx();
             ctxEx.setCtXuatKho(ct);
             TonKho tonkhoXuat = tkDelegate.getTonKhoHienTai(ct.getCtxuatkhoMalk(), this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
             ctxEx.setTonkhoXuat(tonkhoXuat);
             this.listCtXuatKhoEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtXuatKhoEx.size());
           this.isFound = "true";
           if (this.phieuXuat.getPhieuxuatkhoNgaygiophat() == null)
           {
             this.isUpdate = "1";
           }
           else
           {
             this.isUpdate = "0";
             FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG, new Object[] { this.maPhieu });
           }
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
         log.error(String.format(" ***** Error: %s", new Object[] { e }));
       }
     }
   }

   private CtXuatKhoEx createCtXuatKho(CtXuatKho ctx, TonKho tk)
   {
     log.info(String.format(" ***** createCtXuatKho() ***** ", new Object[0]));
     log.info(String.format(" ***** Ct xuat kho (input): %s", new Object[] { ctx.getCtxuatkhoMa() }));
     log.info(String.format(" ***** ton kho (input): %s", new Object[] { tk.getTonkhoMa() }));

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
     log.info(String.format(" ***** ct xuat kho: %s", new Object[] { ctx.getCtxuatkhoMa() }));

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
     this.reportTypeKC = "xuathangdenkhokhac";
     log.info("Vao Method XuatReport xuathangdenkhokhac");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.maPhieu.equals("")) {
       try
       {
         log.info("bat dau method XuatReport ");
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_02.jrxml";
         log.info("da thay file templete 5" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         log.info("da thay file template ");
         Map<String, Object> params = new HashMap();

         PhieuXuatKhoDelegate pxkWS = PhieuXuatKhoDelegate.getInstance();
         PhieuXuatKho px = pxkWS.find(this.maPhieu);

         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format(" ***** tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format(" ***** tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
         log.info(String.format(" ***** dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         Calendar cal = Calendar.getInstance();
         cal.setTime(px.getPhieuxuatkhoNgaylap());
         if (cal != null)
         {
           log.info(String.format(" ***** ngay lap: %s", new Object[] { cal.getTime() }));
           params.put("ngayHt", "" + cal.get(5));
           params.put("thangHt", "" + (cal.get(2) + 1));
           params.put("namHt", "" + cal.get(1));
         }
         else
         {
           log.info(" ***** ngay lap is null");
           params.put("ngayHt", "");
           params.put("thangHt", "");
           params.put("namHt", "");
         }
         SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
         String ngayGioHt = df.format(Calendar.getInstance().getTime());
         log.info(String.format(" ***** ngay gio hien tai: %s", new Object[] { ngayGioHt }));
         params.put("gioHt", ngayGioHt);

         params.put("pxMa", px.getPhieuxuatkhoMa());
         if (px.getDmkhoaNhan() != null) {
           params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format(" ***** khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (px.getDmkhoaXuat() != null) {
           params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format(" ***** khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", px.getPhieuxuatkhoThanhtien());
         log.info(String.format(" ***** tongTien: %s", new Object[] { params.get("tongTien") }));

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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "xuathangdenkhokhac");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan  *****  *****  *****  *****  :" + this.reportPathKC);
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
       return "B4121_Phieuxuathangkhole";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     log.info(" ***** reset() ***** ");
     this.isFound = "false";
     this.maPhieu = "";
     this.updateItem = Integer.valueOf(-1);
     this.tonkhoMa = "";
     this.dmtMa = "";
     this.tonkho = new Double(0.0D);
     this.xuat = new Double(0.0D);
     this.nguonMa = "";
     this.kpMa = "";
     this.count = Integer.valueOf(0);
     this.loaiPhieu = "";
     this.dmLoaiTen = "";
     this.phieuXuat = new PhieuXuatKho();
     this.listCtXuatKhoEx = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "0";
     log.info(" ***** identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuXuat.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtXuatKhoEx ctExt : this.listCtXuatKhoEx)
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
     log.info(" ***** tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     log.info(" ***** resetInfo() ***** ");
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
     return this.listCtXuatKhoEx;
   }

   public void setListCtXuatKhoEx(ArrayList<CtXuatKhoEx> listCtXuatKhoEx)
   {
     this.listCtXuatKhoEx = listCtXuatKhoEx;
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

   public String getIsFound()
   {
     return this.isFound;
   }

   public void setIsFound(String notFound)
   {
     this.isFound = notFound;
   }

   public TonKho createTonKhoXuat(CtXuatKhoEx ct)
   {
     TonKho tk = new TonKho();
     try
     {
       tk = TonKhoUtil.getTonKhoHienTai(ct.getCtXuatKho().getCtxuatkhoMalk(), this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
       tk.setTonkhoNhap(null);
       tk.setTonkhoXuat(ct.getCtXuatKho().getCtxuatkhoSoluong());
       tk.setTonkhoTra(null);
       tk.setTonkhoMa(null);
       tk.setDtdmnhanvienCn(this.phieuXuat.getDtdmnhanvienCn());
       tk.setTonkhoNgaygiocn(new Date());
     }
     catch (Exception e1)
     {
       e1.printStackTrace();
     }
     return tk;
   }

   public TonKho createTonKhoNhap(CtXuatKhoEx ct)
   {
     TonKho tk = new TonKho();
     try
     {
       tk = TonKhoUtil.getTonKhoHienTai(ct.getCtXuatKho().getCtxuatkhoMalk(), this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
       tk.setTonkhoTra(null);
       tk.setTonkhoXuat(null);
       tk.setTonkhoNhap(ct.getCtXuatKho().getCtxuatkhoSoluong());
       tk.setTonkhoMa(null);
       tk.setDtdmnhanvienCn(this.phieuXuat.getDtdmnhanvienCn());
       tk.setTonkhoNgaygiocn(new Date());
     }
     catch (Exception e1)
     {
       e1.printStackTrace();
     }
     return tk;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XuatHangDenKhoKhac

 * JD-Core Version:    0.7.0.1

 */