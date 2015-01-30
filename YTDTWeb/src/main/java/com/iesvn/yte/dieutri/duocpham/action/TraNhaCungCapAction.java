 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtTraNhaCungCapDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraNhaCungCapDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.TonKhoUtil;
 import com.iesvn.yte.util.Utils;
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
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("Tranhacungcap")
 public class TraNhaCungCapAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(TraNhaCungCapAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @DataModel
   private List<CtTraNhaCungCap> listCtTraNhaCungCap = new ArrayList();
   @DataModelSelection
   private CtTraNhaCungCap selected = new CtTraNhaCungCap();
   private PhieuTraNhaCungCap phieuXuat;
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
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private String isUpdate;
   private DmThuocDelegate dmThuocDelegate;
   private String dmtTen = "";
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private String dmLoaiTen = "";
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu = new HashMap();
   private String dmLoaiPhieuMa = "";
   String loaiPhieu = "";
   private List<SelectItem> listDmLoaiPhieus = new ArrayList();

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String tumenu)
   {
     logger.info("*****init()*****");
     reset();
     if ("KC".equals(tumenu)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     }
     return "Tranhacungcap";
   }

   @End
   public void endFunc() {}

   @Factory("listCtTraNhaCungCap")
   public void createTable()
   {
     logger.info("*****createTable()*****");
     this.listCtTraNhaCungCap = new ArrayList();
   }

   public void deleteCt()
   {
     logger.info("*****deleteCt()*****");

     this.listCtTraNhaCungCap.remove(this.selected);
     this.count = Integer.valueOf(this.listCtTraNhaCungCap.size());
     tinhTien();
   }

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
     logger.info("-----BEGIN onblurMaThuocAction()-----");
     if (this.dmtMa != null)
     {
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.dmtMa.toUpperCase());
       if (dmThuoc != null)
       {
         setDmtTen(dmThuoc.getDmthuocTen());
         logger.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         setDmtTen("");
       }
     }
     logger.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     logger.info("-----BEGIN onblurTenThuocAction()-----");
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = "";
     if (this.hmDmThuoc != null)
     {
       Set set = this.hmDmThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmThuoc dmThuoc = (DmThuoc)me.getValue();
         if ((dmThuoc.getDmthuocTen() == this.dmtTen) || (dmThuoc.getDmthuocTen().equals(this.dmtTen)))
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
       logger.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmtMa("");
     }
     logger.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();

     String loaiMa = "";
     if ((this.loaiPhieu != null) || (!this.loaiPhieu.equals(""))) {
       if (this.phieuXuat.getDmloaithuocMaso(Boolean.valueOf(true)).getDmloaithuocMa() != null) {
         if (this.phieuXuat.getDmloaithuocMaso(Boolean.valueOf(true)).getDmloaithuocMa().toUpperCase().equals("TD"))
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
           loaiMa = this.phieuXuat.getDmloaithuocMaso(Boolean.valueOf(true)).getDmloaithuocMa();
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
     logger.info("-----BEGIN onblurMaLoaiAction()-----" + this.phieuXuat.getDmloaithuocMaso().getDmloaithuocMa());
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
     setDmLoaiPhieuMa("");
     logger.info("-----END onblurMaLoaiAction()-----");
   }

   public void onblurTenLoaiThuocAction()
   {
     logger.info("-----BEGIN onblurTenLoaiThuocAction()-----");
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
     setDmLoaiPhieuMa("");
     logger.info("-----END onblurTenLoaiThuocAction()-----");
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
     logger.info("-----BEGIN onblurTenLoaiPhieuAction()-----");
     String loaiPhieuItem = this.loaiPhieu;
     this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     refreshDmThuoc();
     logger.info("-----END onblurTenLoaiPhieuAction()-----");
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

   public String getLoaiPhieu()
   {
     return this.loaiPhieu;
   }

   public void setLoaiPhieu(String loaiPhieu)
   {
     this.loaiPhieu = loaiPhieu;
   }

   public void selectCt(Integer index)
   {
     logger.info("*****selectCt()*****");



     this.selected = ((CtTraNhaCungCap)this.listCtTraNhaCungCap.get(index.intValue()));
     CtTraNhaCungCap ctx = this.selected;
     this.updateItem = index;
     this.tonkhoMa = this.selected.getTonKhoMa().toString();
     logger.info("*****ton kho: " + this.tonkho);


     this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
     this.dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
     this.xuat = ctx.getCttranhacungcapSoluong();
   }

   public void tiepTucNhap()
   {
     logger.info("*****tiepTucNhap()*****");
     logger.info(String.format("*****index: %s", new Object[] { this.updateItem }));

     logger.info("tonkhoMa:" + this.tonkhoMa);
     logger.info("xuat:" + this.xuat);
     logger.info("dmtMa:" + this.dmtMa);
     logger.info("updateItem:" + this.updateItem);
     logger.info("tonkho:" + this.tonkho);
     if ((this.xuat == null) || (this.xuat.equals("")) || (this.tonkho == null) || (this.tonkho.equals(""))) {
       return;
     }
     if (("".equals(this.tonkhoMa)) && (this.tonkhoMa == null))
     {
       logger.info("*****tonkho ma is null.");
     }
     else
     {
       logger.info(String.format("*****tonkho ma: %s", new Object[] { this.tonkhoMa }));
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
       CtTraNhaCungCap ctx = new CtTraNhaCungCap();

       Double slXuat = new Double("0");
       if (this.updateItem.equals(Integer.valueOf(-1))) {
         for (int i = 0; i < this.listCtTraNhaCungCap.size(); i++)
         {
           String mlktemp = ((CtTraNhaCungCap)this.listCtTraNhaCungCap.get(i)).getCttranhacungcapMalk();
           if (this.malk.equals(mlktemp))
           {
             logger.info("*****malk " + this.malk);
             slXuat = Double.valueOf(slXuat.doubleValue() + ((CtTraNhaCungCap)this.listCtTraNhaCungCap.get(i)).getCttranhacungcapSoluong().doubleValue());
             this.updateItem = Integer.valueOf(i);
           }
         }
       }
       slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
       ctx.setCttranhacungcapSoluong(slXuat);
       CtTraNhaCungCap ctxEx = createCtTraNhaCungCap(ctx, tk);
       logger.info("*****xuat: " + slXuat);
       if (this.updateItem.equals(Integer.valueOf(-1)))
       {
         logger.info("*****them moi ct");
         this.listCtTraNhaCungCap.add(ctxEx);
       }
       else
       {
         logger.info("*****Cap nhat ct*****");
         if (tk != null) {
           this.listCtTraNhaCungCap.set(this.updateItem.intValue(), ctxEx);
         } else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, new Object[] { this.dmtMa });
         }
         logger.info(String.format("*****update ct: %s", new Object[] { ctx.getCttranhacungcapThutu() }));
       }
       this.count = Integer.valueOf(this.listCtTraNhaCungCap.size());
       logger.info(String.format("*****listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtTraNhaCungCap.size()) }));
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
     logger.info("*****end()*****");
     if (this.listCtTraNhaCungCap.size() > 0)
     {
       logger.info(String.format("*****ngayxuat %s", new Object[] { this.ngayXuat }));
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           Date dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuXuat.setPhieutranhacungcapNgaylap(cal.getTime());
         }
         catch (Exception e)
         {
           logger.error(String.format("*****Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtTraNhaCungCap> listCtx = new ArrayList();
         ArrayList<TonKho> listTkXuat = new ArrayList();
         for (int i = 0; i < this.listCtTraNhaCungCap.size(); i++)
         {
           CtTraNhaCungCap ct = (CtTraNhaCungCap)this.listCtTraNhaCungCap.get(i);
           TonKho tk = createTonKho(ct);
           logger.info(String.format("*****so luong: %s", new Object[] { ct.getCttranhacungcapSoluong() }));
           tt += ct.getCttranhacungcapSoluong().doubleValue() * ct.getCttranhacungcapDongia().doubleValue();
           ct.setCttranhacungcapThutu(Short.valueOf("" + (i + 1)));
           listCtx.add(ct);
           listTkXuat.add(tk);
         }
         logger.info(String.format("*****thanh tien: %s", new Object[] { Double.valueOf(tt) }));
         this.phieuXuat.setPhieutranhacungcapThanhtien(Double.valueOf(tt));
         this.phieuXuat.setPhieutranhacungcapNgaygiocn(new Date());
         this.phieuXuat.setPhieutranhacungcapLoaiPhieu(this.loaiPhieu);

         PhieuTraNhaCungCapDelegate pxDelegate = PhieuTraNhaCungCapDelegate.getInstance();
         logger.info(String.format("*****phieu xuat: %s", new Object[] { this.phieuXuat }));

         clearInfor();


         logger.info("PhieutranhacungcapMa: " + this.phieuXuat.getPhieutranhacungcapMa());
         logger.info("DtdmnhanvienMaso: " + this.phieuXuat.getDtdmnhanvienCn(Boolean.valueOf(true)).getDtdmnhanvienMaso());
         logger.info("DmnhacungcapMaso: " + this.phieuXuat.getNhacungcap(Boolean.valueOf(true)).getDmnhacungcapMaso());
         this.maPhieu = pxDelegate.updatePhieuTraNhaCungCap(this.phieuXuat, listCtx, listTkXuat);

         logger.info("***** ma phieu xuat tra NCC: " + this.maPhieu);
         if (!this.maPhieu.equals(""))
         {
           resetInfo();

           logger.info(String.format("*****result: %s", new Object[] { this.maPhieu }));
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
         logger.error(String.format("*****Error: %s", new Object[] { e.toString() }));
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
     logger.info("*****destroy()*****");
   }

   public void displayPhieuXuatKho()
   {
     logger.info("*****displayPhieuXuatKho()*****");
     if (!this.maPhieu.equals(""))
     {
       logger.info(String.format("*****Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       try
       {
         PhieuTraNhaCungCapDelegate pxkWS = PhieuTraNhaCungCapDelegate.getInstance();
         CtTraNhaCungCapDelegate ctxWS = CtTraNhaCungCapDelegate.getInstance();

         this.phieuXuat = pxkWS.findPhieuTraNhaCungCapByMa(this.maPhieu);
         if (this.phieuXuat != null)
         {
           logger.info("***** phieuXuat: " + this.phieuXuat.getPhieutranhacungcapMa());
           this.maPhieu = this.phieuXuat.getPhieutranhacungcapMa();
           resetInfo();
           this.dmLoaiTen = this.phieuXuat.getDmloaithuocMaso(Boolean.valueOf(true)).getDmloaithuocTen();
           this.loaiPhieu = this.phieuXuat.getPhieutranhacungcapLoaiPhieu();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayXuat = df.format(this.phieuXuat.getPhieutranhacungcapNgaylap());
           logger.info(String.format("*****find ct xuat kho by phieu xuat kho ma %s", new Object[] { this.maPhieu }));
           this.listCtTraNhaCungCap = (ctxWS.findCtTraNhaCungCapByMaPhieu(this.maPhieu) == null ? new ArrayList() : ctxWS.findCtTraNhaCungCapByMaPhieu(this.maPhieu));






           this.count = Integer.valueOf(this.listCtTraNhaCungCap.size());
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
         logger.error(String.format("*****Error: %s", new Object[] { e }));
       }
     }
   }

   private CtTraNhaCungCap createCtTraNhaCungCap(CtTraNhaCungCap ctx, TonKho tk)
   {
     logger.info(String.format("*****createCtXuatKho()*****", new Object[0]));
     logger.info(String.format("*****Ct xuat kho (input): %s", new Object[] { ctx.getCttranhacungcapMa() }));
     logger.info(String.format("*****ton kho (input): %s", new Object[] { tk.getTonkhoMa() }));

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
       tkXuat.setTonkhoXuat(ctx.getCttranhacungcapSoluong());
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
       tkNhan.setTonkhoNhap(ctx.getCttranhacungcapSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCttranhacungcapDongia(tk.getTonkhoDongia());
     ctx.setCttranhacungcapDongiaban(tk.getTonkhoDongiaban());
     ctx.setCttranhacungcapLo(tk.getTonkhoLo());
     ctx.setCttranhacungcapMalk(tk.getTonkhoMalienket());
     ctx.setCttranhacungcapNamhandung(tk.getTonkhoNamhandung());
     ctx.setCttranhacungcapNamnhap(tk.getTonkhoNamnhap());
     ctx.setCttranhacungcapNgaygiocn(new Date());
     ctx.setCttranhacungcapNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCttranhacungcapThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieutranhacungcapMa(this.phieuXuat);
     ctx.setTonKhoMa(tk.getTonkhoMa());
     logger.info(String.format("*****ct xuat kho: %s", new Object[] { ctx.getCttranhacungcapMa() }));

     return ctx;
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
     this.reportTypeKC = "PhieuXuatTraNhaCungCap";
     logger.info("Vao Method XuatReport PhieuXuatTraNhaCungCap");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieutrakho_02.jrxml";
       logger.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       logger.info("da thay file template ");

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       PhieuTraNhaCungCapDelegate pxkWS = PhieuTraNhaCungCapDelegate.getInstance();
       PhieuTraNhaCungCap px = pxkWS.findPhieuTraNhaCungCapByMa(this.maPhieu);

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       logger.info(String.format("*****tenSo: %s", new Object[] { params.get("tenSo") }));
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       logger.info(String.format("*****tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       logger.info(String.format("*****dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
       Calendar cal = Calendar.getInstance();
       cal.setTime(px.getPhieutranhacungcapNgaylap());
       if (cal != null)
       {
         logger.info(String.format("*****ngay lap: %s", new Object[] { cal.getTime() }));
         params.put("ngayHt", "" + cal.get(5));
         params.put("thangHt", "" + (cal.get(2) + 1));
         params.put("namHt", "" + cal.get(1));
       }
       else
       {
         logger.info("*****ngay lap is null");
         params.put("ngayHt", "");
         params.put("thangHt", "");
         params.put("namHt", "");
       }
       SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
       String ngayGioHt = df.format(Calendar.getInstance().getTime());
       logger.info(String.format("*****ngay gio hien tai: %s", new Object[] { ngayGioHt }));
       params.put("gioHt", ngayGioHt);

       params.put("pxMa", px.getPhieutranhacungcapMa());
       if (px.getDmkhoaXuat() != null) {
         params.put("khoaNhan", px.getNhacungcap(Boolean.valueOf(true)).getDmnhacungcapTen());
       } else {
         params.put("khoaNhan", "");
       }
       logger.info(String.format("*****khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
       if (px.getDmkhoaXuat() != null) {
         params.put("khoaXuat", px.getDmkhoaXuat().getDmkhoaTen());
       } else {
         params.put("khoaXuat", "");
       }
       logger.info(String.format("*****khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

       params.put("tongTien", px.getPhieutranhacungcapThanhtien());
       logger.info(String.format("*****tongTien: %s", new Object[] { params.get("tongTien") }));
       params.put("loaiMa", px.getDmloaithuocMaso().getDmloaithuocMa());
       params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());


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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "xuattranhacungcap");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan ******************** :" + this.reportPathKC);
       this.index += 1;
       logger.info("Index :" + this.index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public TonKho createTonKho(CtTraNhaCungCap ct)
   {
     TonKho tk = new TonKho();
     try
     {
       tk = TonKhoUtil.getTonKhoHienTai(ct.getCttranhacungcapMalk(), this.phieuXuat.getDmkhoaXuat().getDmkhoaMaso());
       tk.setDtdmkhoMaso(null);
       tk.setDtdmnhanvienCn(this.phieuXuat.getDtdmnhanvienCn(Boolean.valueOf(true)));
       tk.setTonkhoHienthi(Boolean.valueOf(true));
       tk.setTonkhoNhap(null);
       tk.setTonkhoXuat(ct.getCttranhacungcapSoluong());
       tk.setTonkhoTra(null);
       tk.setTonkhoMa(null);
       tk.setTonkhoNgaygiocn(new Date());
     }
     catch (Exception e1)
     {
       e1.printStackTrace();
     }
     return tk;
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "Tranhacungcap";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     logger.info("*****reset()*****");

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
     this.phieuXuat = new PhieuTraNhaCungCap();
     this.listCtTraNhaCungCap = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "0";
     logger.info("*****identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuXuat.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");

     this.phieuXuat.getDmkhoaXuat(Boolean.valueOf(true)).setDmkhoaMa(IConstantsRes.KHOA_KC_MA);

     this.loaiPhieu = "";
     this.dmLoaiTen = "";
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();

     setDmtMa("");
     setDmtTen("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.dmLoaiPhieuMa = "";
     this.hmDmLoaiPhieu.clear();
   }

   public void clearDetail()
   {
     logger.info("***** clearDetail() *****");
     this.listCtTraNhaCungCap = new ArrayList();
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtTraNhaCungCap ctExt : this.listCtTraNhaCungCap)
     {
       Double sl = ctExt.getCttranhacungcapSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCttranhacungcapDongia();
       if (dg == null) {
         dg = new Double("0");
       }
       this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     logger.info("*****tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     logger.info("***** resetInfo() *****");
     if (this.phieuXuat.getNhacungcap() == null) {
       this.phieuXuat.setNhacungcap(new DmNhaCungCap());
     }
     if (this.phieuXuat.getDmnguonkinhphiMaso() == null) {
       this.phieuXuat.setDmnguonkinhphiMaso(new DmNguonKinhPhi());
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
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso()))) {
       this.phieuXuat.setDmnguonkinhphiMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuXuat.getDmnctMaso(Boolean.valueOf(true)).getDmnctMaso()))) {
       this.phieuXuat.setDmnctMaso(null);
     }
   }

   public void setPhieuXuat(PhieuTraNhaCungCap phieuXuat)
   {
     this.phieuXuat = phieuXuat;
   }

   public PhieuTraNhaCungCap getPhieuXuat()
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

   public List<CtTraNhaCungCap> getListCtTraNhaCungCap()
   {
     return this.listCtTraNhaCungCap;
   }

   public void setListCtTraNhaCungCap(ArrayList<CtTraNhaCungCap> listCtXuatKhoTraNhaCungCap)
   {
     this.listCtTraNhaCungCap = listCtXuatKhoTraNhaCungCap;
   }

   public CtTraNhaCungCap getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtTraNhaCungCap selected)
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

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.TraNhaCungCapAction

 * JD-Core Version:    0.7.0.1

 */