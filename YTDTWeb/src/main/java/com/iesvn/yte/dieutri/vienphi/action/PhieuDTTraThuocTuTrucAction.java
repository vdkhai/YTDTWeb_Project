 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuDuTruDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
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
 @Name("B3143_Phieutrathuoctutruc")
 @Synchronized(timeout=6000000L)
 public class PhieuDTTraThuocTuTrucAction
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(PhieuDTTraThuocTuTrucAction.class);
   @DataModel
   private ArrayList<CtPhieuDuTruExt> listCtDTTraThuocEx;
   private ArrayList<CtPhieuDt> listCtDtTraThuocDel;
   @DataModelSelection
   private CtPhieuDuTruExt selected;
   private PhieuDuTru phieuDuTru;
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
   private String hienThiGhiNhan;
   private String hienThiHuyPhieu;
   private String hienThiInPhieu;
   String dmKhoXuat;
   String dmKhoNhan;
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
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   private String isUpdate;
   private String isDeleted;
   private int CHUA_LUU_PHIEU_XUAT = 0;
   private DmThuocDelegate dmThuocDelegate;
   private String dmtTen;
   private List<SelectItem> listDmThuocs;
   private DmKhoaDelegate dmKhoaDelegate;
   private List<SelectItem> listDmKhoXuats;
   HashMap<String, DmKhoa> hmDmKhoXuat;
   private Integer dmkhoaXuatMaso;
   private String dmkhoaXuatMa;
   private String dmkhoaXuatTen;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP;

   @Begin(join=true)
   public String init(String loaiDT, String khoNhan, String khoXuat)
   {
     log.info("***** init PhieuDTTraThuocTuTrucAction() *****");
     reset();
     this.dmKhoXuat = khoXuat;
     this.dmKhoNhan = khoNhan;
     this.listDmKhoXuats.clear();
     this.hmDmKhoXuat.clear();
     if ("NGT".equals(loaiDT))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.tiepNhanKhamBenh;
       refreshDmKhoXuat("NGT");
     }
     else
     {
       this.tenChuongTrinh = MyMenuYTDTAction.dieuTri;
       refreshDmKhoXuat("NT");
     }
     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();

     refreshDmLoaiThuoc();
     return "VienPhiTaiKhoa_PhieuDTTraThuocTuTru";
   }

   @Factory("listCtDTTraThuocEx")
   public void createTable()
   {
     log.info("-----createTable()-----");
     this.listCtDTTraThuocEx = new ArrayList();
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
     String phanloaiMa = "";
     if ((this.loaiPhieu != null) || (!this.loaiPhieu.equals(""))) {
       if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null) {
         if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
         {
           if ((this.dmLoaiPhieuMa != null) && (!this.dmLoaiPhieuMa.equals(""))) {
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
         }
         else if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY"))
         {
           DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
           if (lp.getDmloaiphieuDvt() != null)
           {
             phanloaiMa = lp.getDmloaiphieuDvt();
             loaiMa = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
           }
         }
         else
         {
           loaiMa = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.loaiPhieu.equals("")) && (this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso() != null)) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, phanloaiMa, this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso());
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
     log.info("-----BEGIN onblurMaLoaiAction()-----" + this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa());
     String loaihang_ma = this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa();
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null)
     {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
       this.phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc);
     }
     else
     {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     setLoaiPhieu("");
     setDmLoaiPhieuMa("");
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
       this.phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     setLoaiPhieu("");
     setDmLoaiPhieuMa("");
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
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " + o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public void onblurLoaiPhieuAction()
   {
     log.info("-----BEGIN onblurLoaiPhieuAction()-----");
     this.hmDmThuoc.clear();
     this.listDmThuocs.clear();
     String loaiPhieuItem = this.loaiPhieu;
     this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     refreshDmThuoc();
     log.info("-----END onblurLoaiPhieuAction()-----");
   }

   public Integer getDmkhoaXuatMaso()
   {
     return this.dmkhoaXuatMaso;
   }

   public void setDmkhoaXuatMaso(Integer dmkhoaXuatMaso)
   {
     this.dmkhoaXuatMaso = dmkhoaXuatMaso;
   }

   public String getDmkhoaXuatMa()
   {
     return this.dmkhoaXuatMa;
   }

   public void setDmkhoaXuatMa(String dmkhoaXuatMa)
   {
     this.dmkhoaXuatMa = dmkhoaXuatMa;
   }

   public String getDmkhoaXuatTen()
   {
     return this.dmkhoaXuatTen;
   }

   public void setDmkhoaXuatTen(String dmkhoaXuatTen)
   {
     this.dmkhoaXuatTen = dmkhoaXuatTen;
   }

   public List<SelectItem> getListDmKhoXuats()
   {
     return this.listDmKhoXuats;
   }

   public void setListDmKhoXuats(List<SelectItem> listDmKhoXuats)
   {
     this.listDmKhoXuats = listDmKhoXuats;
   }

   public void onblurMaKhoXuatAction()
   {
     log.info("-----BEGIN onblurMaKhoXuatAction()-----");
     if (this.dmkhoaXuatMa != "")
     {
       DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoXuat.get(this.dmkhoaXuatMa.toUpperCase());
       if (dmKhoa != null)
       {
         this.phieuDuTru.setDmkhoaMaso(dmKhoa);
         setDmkhoaXuatMaso(dmKhoa.getDmkhoaMaso());
         setDmkhoaXuatTen(dmKhoa.getDmkhoaTen());
         log.info("-----DA THAY DMKHOXUAT-----");
       }
       else
       {
         this.phieuDuTru.setDmkhoaMaso(null);
         setDmkhoaXuatMaso(null);
         setDmkhoaXuatTen("");
         setDmkhoaXuatMa("");
       }
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     setDmtMa("");
     setDmtTen("");
     log.info("-----END onblurMaKhoXuatAction()-----");
   }

   public void onblurTenKhoXuatAction()
   {
     log.info("-----BEGIN onblurTenKhoXuatAction()-----");
     Boolean hasTenKhoXuat = Boolean.valueOf(false);
     DmKhoa dmKhoa_Finded = new DmKhoa();
     if ((this.dmkhoaXuatTen != "") &&
       (this.hmDmKhoXuat != null))
     {
       Set set = this.hmDmKhoXuat.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == this.dmkhoaXuatTen) || (dmKhoa.getDmkhoaTen().equals(this.dmkhoaXuatTen)))
         {
           hasTenKhoXuat = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
     }
     if (hasTenKhoXuat.booleanValue())
     {
       this.phieuDuTru.setDmkhoaMaso(dmKhoa_Finded);
       setDmkhoaXuatMa(dmKhoa_Finded.getDmkhoaMa());
       setDmkhoaXuatMaso(dmKhoa_Finded.getDmkhoaMaso());
       log.info("-----DA THAY DMKHOXUAT-----");
     }
     else
     {
       this.phieuDuTru.setDmkhoaMaso(null);
       setDmkhoaXuatMa("");
       setDmkhoaXuatMaso(null);
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     setDmtMa("");
     setDmtTen("");
     log.info("-----END onblurTenKhoXuatAction()-----");
   }

   public void refreshDmKhoXuat(String fromMenu)
   {
     this.listDmKhoXuats.clear();
     this.hmDmKhoXuat.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();

     List<DmKhoa> lstDmKhoa = new ArrayList();
     lstDmKhoa = this.dmKhoaDelegate.getKhoaNhom12NOTINKhoDuoc();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoXuats.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoXuat.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public void deleteCt(int index)
   {
     log.info("-----deleteCt()-----");
     CtPhieuDuTruExt ctTraDel = (CtPhieuDuTruExt)this.listCtDTTraThuocEx.remove(index);
     this.listCtDtTraThuocDel.add(ctTraDel.getCtPhieuDt());
     this.count = Integer.valueOf(this.listCtDTTraThuocEx.size());
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
     try
     {
       this.selected = ((CtPhieuDuTruExt)this.listCtDTTraThuocEx.get(index.intValue()));
       TonKho tk = this.selected.getTonKhoXuat();
       CtPhieuDt ctx = this.selected.getCtPhieuDt();
       this.updateItem = index;
       if (tk.getTonkhoMa() != null)
       {
         this.tonkhoMa = tk.getTonkhoMa().toString();
         this.tonkho = tk.getTonkhoTon();
       }
       this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
       this.dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
       this.xuat = ctx.getCtdtSoluong();
     }
     catch (Exception e)
     {
       log.info("***** error: " + e);
     }
   }

   public void tiepTucNhap()
   {
     log.info("-----tiepTucNhap()-----");
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
       CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
       CtPhieuDt ctx = new CtPhieuDt();
       Double slXuat = new Double("0");



       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       Date dHt = null;
       try
       {
         dHt = df.parse(this.ngayXuat);
       }
       catch (ParseException er)
       {
         return;
       }
       int viTri = -1;
       if (this.updateItem.intValue() != -1) {
         if (this.listCtDTTraThuocEx.size() > this.updateItem.intValue())
         {
           viTri = this.updateItem.intValue();
           this.updateItem = Integer.valueOf(-1);
         }
         else
         {
           this.updateItem = Integer.valueOf(-1);
           return;
         }
       }
       if (tk != null)
       {
         ctx.setCtdtNgaygiocn(dHt);
         slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
         ctx.setCtdtSoluong(slXuat);
         ctx.setCtdtMalk(tk.getTonkhoMalienket());
         if (viTri == -1)
         {
           log.info("-----them moi ct");
           if (this.listCtDTTraThuocEx.size() > 0)
           {
             int vt = -1;
             double sl = 0.0D;
             boolean foundOnNewsList = false;
             for (int i = 0; i < this.listCtDTTraThuocEx.size(); i++)
             {
               CtPhieuDt ctxk = ((CtPhieuDuTruExt)this.listCtDTTraThuocEx.get(i)).getCtPhieuDt();
               if (ctx.getCtdtMalk().equals(ctxk.getCtdtMalk()))
               {
                 if (ctxk.getCtdtMa() == null)
                 {
                   foundOnNewsList = true;
                   vt = i;
                   sl = ctx.getCtdtSoluong().doubleValue() + ctxk.getCtdtSoluong().doubleValue(); break;
                 }
                 if (ctxk.getPhieudtMa().getPhieudtDaXuat() != null) {
                   break;
                 }
                 foundOnNewsList = true;
                 vt = i;
                 sl = ctx.getCtdtSoluong().doubleValue() + ctxk.getCtdtSoluong().doubleValue();
                 ctx.setCtdtMa(ctxk.getCtdtMa()); break;
               }
             }
             if (!foundOnNewsList)
             {
               ctxEx = createCtTraKho(ctx, tk);
               this.listCtDTTraThuocEx.add(ctxEx);
             }
             else
             {
               ctx.setCtdtSoluong(Double.valueOf(sl));
               ctxEx = createCtTraKho(ctx, tk);
               this.listCtDTTraThuocEx.set(vt, ctxEx);
             }
           }
           else
           {
             ctxEx = createCtTraKho(ctx, tk);
             this.listCtDTTraThuocEx.add(ctxEx);
           }
         }
         else
         {
           log.info("-----Cap nhat ct-----");
           System.out.println("ma ctdt:" + this.selected.getCtPhieuDt().getCtdtMa() + " vi tri:" + viTri);
           ctx.setCtdtMa(this.selected.getCtPhieuDt().getCtdtMa());
           ctxEx = new CtPhieuDuTruExt();
           ctxEx = createCtTraKho(ctx, tk);
           this.listCtDTTraThuocEx.set(viTri, ctxEx);
         }
       }
       this.count = Integer.valueOf(this.listCtDTTraThuocEx.size());
       log.info(String.format("-----listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtDTTraThuocEx.size()) }));
       this.tonkhoMa = "";
       this.dmtMa = "";
       this.dmtTen = "";
       this.tonkho = new Double(0.0D);
       this.xuat = new Double(0.0D);
       this.updateItem = Integer.valueOf(-1);
       this.selected = new CtPhieuDuTruExt();
     }
     tinhTien();
   }

   public void end()
   {
     log.info("-----end()-----");
     if (this.listCtDTTraThuocEx.size() > 0)
     {
       log.info(String.format("-----ngayxuat %s", new Object[] { this.ngayXuat }));
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           Date dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuDuTru.setPhieudtNgaygiocn(cal.getTime());
           this.phieuDuTru.setPhieudtNgay(cal.getTime());
         }
         catch (ParseException e)
         {
           log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtPhieuDt> listCtTra = new ArrayList();
         CtPhieuDuTruExt obj = new CtPhieuDuTruExt();
         for (int i = 0; i < this.listCtDTTraThuocEx.size(); i++)
         {
           CtPhieuDt ct = ((CtPhieuDuTruExt)this.listCtDTTraThuocEx.get(i)).getCtPhieuDt();
           log.info(String.format("-----so luong: %s", new Object[] { ct.getCtdtSoluong() }));
           tt += ct.getCtdtSoluong().doubleValue() * ct.getCtdtDongia().doubleValue();
           listCtTra.add(ct);
         }
         log.info(String.format("-----thanh tien: %s", new Object[] { Double.valueOf(tt) }));
         this.phieuDuTru.setPhieudtNgaygiocn(new Date());
         this.phieuDuTru.setPhieudtTrangThai(Integer.valueOf(0));
         this.phieuDuTru.setPhieudtPhanBiet(Integer.valueOf(3));
         this.phieuDuTru.setPhieudtLoaiPhieu(this.loaiPhieu);
         CtPhieuDtDelegate pxDelegate = CtPhieuDtDelegate.getInstance();
         log.info(String.format("-----phieu xuat: %s", new Object[] { this.phieuDuTru }));

         clearInfor();
         this.maPhieu = pxDelegate.updatePhieuDuTru(this.phieuDuTru, listCtTra, this.listCtDtTraThuocDel);
         if (this.maPhieu != "")
         {
           resetInfo();

           log.info(String.format("-----result: %s", new Object[] { this.maPhieu }));
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });

           setIsUpdate("1");
           this.isDeleted = this.phieuDuTru.getPhieudtTrangThai().toString();
           displayPhieuXuatKho();
           this.hienThiGhiNhan = "";
           this.hienThiHuyPhieu = "";
           this.hienThiInPhieu = "true";
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

   public void huyPhieuDT()
   {
     log.info("****** start huyPhieuDT() ******");
     PhieuDuTruDelegate ctPhieuDTDelegate = PhieuDuTruDelegate.getInstance();
     boolean returnKQ = ctPhieuDTDelegate.removeAllPhieuDuTru(this.phieuDuTru);
     if (!returnKQ)
     {
       FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THAT_BAI, new Object[] { this.phieuDuTru.getPhieudtMa() });
       this.hienThiGhiNhan = "";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.phieuDuTru.getPhieudtMa() });
       reset();
       this.hienThiGhiNhan = "true";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
       this.isUpdate = "1";
       this.isDeleted = "0";
     }
     log.info("****** end huyPhieuDT() ******");
   }

   @Destroy
   public void destroy()
   {
     log.info("-----destroy()-----");
   }

   public void displayPhieuXuatKho()
   {
     log.info("-----displayPhieuXuatKho()-----");
     this.listCtDTTraThuocEx = new ArrayList();
     this.listCtDtTraThuocDel = new ArrayList();
     if (!this.maPhieu.equals(""))
     {
       log.info(String.format("-----Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       try
       {
         CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
         this.phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(this.maPhieu, Integer.valueOf(3));
         if (this.phieuDuTru != null)
         {
           this.maPhieu = this.phieuDuTru.getPhieudtMa();
           resetInfo();
           this.dmkhoaXuatMaso = this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso();
           this.dmkhoaXuatMa = this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa();
           this.dmkhoaXuatTen = this.phieuDuTru.getDmkhoaMaso().getDmkhoaTen();
           this.loaiPhieu = this.phieuDuTru.getPhieudtLoaiPhieu();
           this.dmLoaiTen = this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocTen();
           log.info(String.format("-----find ct xuat kho by phieu xuat kho ma %s", new Object[] { this.maPhieu }));
           for (CtPhieuDt ct : pxkWS.findByPhieuDuTruMa(this.maPhieu))
           {
             log.info("Ct xuat kho ma: " + ct.getCtdtMa());
             CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
             ctxEx.setCtPhieuDt(ct);
             this.listCtDTTraThuocEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtDTTraThuocEx.size());
           this.hienThiInPhieu = "true";
           if (this.phieuDuTru.getPhieudtDaXuat() == null)
           {
             setIsUpdate("1");
             this.hienThiHuyPhieu = "true";
             this.hienThiGhiNhan = "true";
           }
           else
           {
             this.hienThiHuyPhieu = "";
             this.hienThiGhiNhan = "";
             setIsUpdate("0");
           }
           this.isDeleted = this.phieuDuTru.getPhieudtTrangThai().toString();
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

   private CtPhieuDuTruExt createCtTraKho(CtPhieuDt ctx, TonKho tk)
   {
     log.info(String.format("-----createCtTraKho()-----", new Object[0]));
     log.info(String.format("-----Ct xuat kho (input): %s", new Object[] { ctx.getCtdtMa() }));
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
       tkTra.setTonkhoXuat(ctx.getCtdtSoluong());
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
       tkNhan.setTonkhoNhap(ctx.getCtdtSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCtdtDongia(tk.getTonkhoDongia());
     ctx.setCtdtLo(tk.getTonkhoLo());

     ctx.setCtdtNamhandung(tk.getTonkhoNamhandung());
     ctx.setCtdtNamnhap(tk.getTonkhoNamnhap());

     ctx.setCtdtNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCtdtThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieudtMa(this.phieuDuTru);

     ctx.setCtdtSodangky(tk.getTonkhoSodangky());


     CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
     ctxEx.setCtPhieuDt(ctx);
     ctxEx.setTonKhoXuat(tkTra);
     ctxEx.setTonKhoNhan(tkNhan);
     return ctxEx;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public PhieuDTTraThuocTuTrucAction()
   {
     this.listCtDTTraThuocEx = new ArrayList();





     this.phieuDuTru = new PhieuDuTru();
     this.maPhieu = "";
     this.ngayXuat = "";
     this.dmtMa = "";


     this.tonkhoMa = "";
     this.updateItem = Integer.valueOf(-1);

     this.ngayHienTai = "";
     this.nguonMa = "";
     this.kpMa = "";
     this.malk = "";

     this.hienThiGhiNhan = "true";
     this.hienThiHuyPhieu = "";
     this.hienThiInPhieu = "";
     this.dmKhoXuat = "";
     this.dmKhoNhan = "";

     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();
     this.hmDmThuoc = new HashMap();

     this.hmDmLoaiPhieu = new HashMap();
     this.dmLoaiPhieuMa = "";
     this.loaiPhieu = "";
     this.listDmLoaiPhieus = new ArrayList();




     this.isReport = "false";




     this.CHUA_LUU_PHIEU_XUAT = 0;
     this.dmtTen = "";
     this.listDmThuocs = new ArrayList();

     this.listDmKhoXuats = new ArrayList();
     this.hmDmKhoXuat = new HashMap();
     this.index = 0;
     this.reportPathVP = null;
     this.reportTypeVP = null;
     this.jasperPrintVP = null;
   }

   public void XuatReport()
   {
     this.reportTypeVP = "PhieuTraThuocTuTrucKhoaPhong";
     log.info("Vao Method XuatReport PhieuTraThuocTuTrucKhoaPhong");
     if ((this.maPhieu != null) && (!this.maPhieu.equals("")))
     {
       CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
       this.phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(this.maPhieu, Integer.valueOf(3));
       try
       {
         XuatReportDuocPham xuatReport = new XuatReportDuocPham();
         xuatReport.reportTypeKC = this.reportTypeVP;
         String loaiThuoc = this.loaiPhieu.substring(0, this.loaiPhieu.indexOf(" - ")).trim();
         xuatReport.XuatReportPhieuDuTruTuTruc(log, this.phieuDuTru, this.index, loaiThuoc);
         this.jasperPrintVP = xuatReport.jasperPrintKC;
         this.reportPathVP = xuatReport.reportPathKC;
         this.reportTypeVP = xuatReport.reportTypeKC;
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
       return "B3143_Phieutrathuoctutruc";
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
     this.tonkho = new Double(0.0D);
     this.xuat = new Double(0.0D);
     this.nguonMa = "";
     this.kpMa = "";
     this.count = Integer.valueOf(0);
     this.phieuDuTru = new PhieuDuTru();
     this.listCtDTTraThuocEx = new ArrayList();
     this.listCtDtTraThuocDel = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "1";
     this.isDeleted = "0";
     log.info("-----identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuDuTru.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
     this.loaiPhieu = "";
     this.listDmThuocs.clear();
     this.listDmLoaiPhieus.clear();
     this.dmkhoaXuatMaso = null;
     this.dmkhoaXuatMa = "";
     this.dmkhoaXuatTen = "";
     this.dmLoaiPhieuMa = "";
     this.hmDmLoaiPhieu.clear();
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtPhieuDuTruExt ctExt : this.listCtDTTraThuocEx)
     {
       Double sl = ctExt.getCtPhieuDt().getCtdtSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCtPhieuDt().getCtdtDongia();
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
     if (this.phieuDuTru.getDmkhoaMaso() == null) {
       this.phieuDuTru.setDmkhoaMaso(new DmKhoa());
     }
     if (this.phieuDuTru.getPhieudtMakho() == null) {
       this.phieuDuTru.setPhieudtMakho(new DmKhoa());
     }
     if (this.phieuDuTru.getDmdoituongMaso() == null) {
       this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuDuTru.getDtdmnhanvienBacsiky() == null) {
       this.phieuDuTru.setDtdmnhanvienBacsiky(new DtDmNhanVien());
     }
     if (this.phieuDuTru.getDtdmnhanvienCn() == null) {
       this.phieuDuTru.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuDuTru.getDtdmnhanvienLapphieu() == null) {
       this.phieuDuTru.setDtdmnhanvienLapphieu(new DtDmNhanVien());
     }
   }

   private void clearInfor()
   {
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso()))) {
       this.phieuDuTru.setDmkhoaMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso()))) {
       this.phieuDuTru.setPhieudtMakho(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmdoituongMaso().getDmdoituongMaso()))) {
       this.phieuDuTru.setDmdoituongMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienBacsiky().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienBacsiky(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienCn().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienCn(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienLapphieu().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienLapphieu(null);
     }
   }

   public void setPhieuDuTru(PhieuDuTru phieuXuat)
   {
     this.phieuDuTru = phieuXuat;
   }

   public PhieuDuTru getPhieuDuTru()
   {
     return this.phieuDuTru;
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

   public CtPhieuDuTruExt getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtPhieuDuTruExt selected)
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

   public String getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }

   public void setHienThiGhiNhan(String hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public String getHienThiHuyPhieu()
   {
     return this.hienThiHuyPhieu;
   }

   public void setHienThiHuyPhieu(String hienThiHuyPhieu)
   {
     this.hienThiHuyPhieu = hienThiHuyPhieu;
   }

   public String getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(String hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public String getIsDeleted()
   {
     return this.isDeleted;
   }

   public void setIsDeleted(String isDeleted)
   {
     this.isDeleted = isDeleted;
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

   public class CtPhieuDuTruExt
     implements Serializable
   {
     private static final long serialVersionUID = 0L;
     private CtPhieuDt ctDuTru;
     private TonKho tonKho;
     private TonKho tonKhoXuat;
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

     public CtPhieuDuTruExt()
     {
       this.ctDuTru = new CtPhieuDt();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhTien = new Double(0.0D);
     }

     public CtPhieuDt getCtPhieuDt()
     {
       return this.ctDuTru;
     }

     public void setCtPhieuDt(CtPhieuDt obj)
     {
       this.ctDuTru = obj;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho obj)
     {
       this.tonKho = obj;
     }

     public TonKho getTonKhoXuat()
     {
       if (this.tonKhoXuat == null) {
         this.tonKhoXuat = new TonKho();
       }
       return this.tonKhoXuat;
     }

     public void setTonKhoXuat(TonKho obj)
     {
       this.tonKhoXuat = obj;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.PhieuDTTraThuocTuTrucAction

 * JD-Core Version:    0.7.0.1

 */