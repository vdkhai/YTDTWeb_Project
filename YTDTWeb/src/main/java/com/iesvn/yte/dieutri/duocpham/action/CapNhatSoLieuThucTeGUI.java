 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.PagedList;
 import java.io.Serializable;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4144_CapnhatSoLieuKiemKeGUI")
 @Synchronized(timeout=6000000L)
 public class CapNhatSoLieuThucTeGUI
   implements Serializable
 {
   private static final long serialVersionUID = -340255448614143263L;
   private static Logger log = Logger.getLogger(CapNhatSoLieuThucTeGUI.class);
   private KiemKeKho kiemkeKho;
   @DataModel
   private List<KiemKeKho> listKiemKeKho = new ArrayList();
   @Out(required=false)
   @In(required=false)
   private String resetFormCapNhatSLGUI = null;
   @In(required=false)
   @Out(required=false)
   private KiemKe kiemkeOut;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private KiemKeKho kiemkeKhoOut;
   private Integer dmtMaso;
   private String dmtMa;
   private String dmtTen;
   private Integer loaihang_maso;
   private String loaihang_ma;
   private Integer plthuoc_maso;
   private String plthuoc_ma;
   private String plthuocTen;
   private Integer dmnctMaso;
   private String dmnctMa;
   private Integer dmnguonkinhphiMaso;
   private String dmnguonkinhphiMa;
   private Double tonhientai;
   private Double tonsauKiemKe;
   private List<SelectItem> listDmThuocs = new ArrayList();
   private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList();
   private HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private DmThuocDelegate dmThuocDelegate;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
   private String dmloaithuocTen = "";
   private HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   private HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap();
   private KiemKe kiemke;
   private int page;
   private int itemsPerPage;
   private List<KiemKeKho> items;
   private int totalPages;

   public int getItemsPerPage()
   {
     return this.itemsPerPage;
   }

   public void setItemsPerPage(int itemsPerPage)
   {
     this.itemsPerPage = itemsPerPage;
   }

   public int getPage()
   {
     return this.page;
   }

   public void setPage(int page)
   {
     this.page = page;
     fetch();
   }

   public int getTotalPages()
   {
     return this.totalPages;
   }

   public void setTotalPages(int totalPages)
   {
     this.totalPages = totalPages;
   }

   public List<KiemKeKho> getList()
   {
     return this.items;
   }

   private void fetch()
   {
     KiemKeKhoDelegate kkkDel = KiemKeKhoDelegate.getInstance();
     int total = kkkDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     int limit = this.itemsPerPage;
     int offset = (this.page - 1) * this.itemsPerPage;
     List<KiemKeKho> list = kkkDel.getItemKiemKeKhos(this.kiemke.getMaphieukiem(), limit, offset);
     this.items = new PagedList(list, total, this.itemsPerPage);
   }

   public void resetItemsPerPage()
   {
     setPage(1);
     KiemKeKhoDelegate kkkDel = KiemKeKhoDelegate.getInstance();
     int total = kkkDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     this.totalPages = (total / this.itemsPerPage + 1);
   }

   public Integer getDmtMaso()
   {
     return this.dmtMaso;
   }

   public void setDmtMaso(Integer dmtMaso)
   {
     this.dmtMaso = dmtMaso;
   }

   public String getDmtMa()
   {
     return this.dmtMa;
   }

   public void setDmtMa(String dmtMa)
   {
     this.dmtMa = dmtMa;
   }

   public String getDmtTen()
   {
     return this.dmtTen;
   }

   public void setDmtTen(String dmtTen)
   {
     this.dmtTen = dmtTen;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihangMaso)
   {
     this.loaihang_maso = loaihangMaso;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihangMa)
   {
     this.loaihang_ma = loaihangMa;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public String getDmloaithuocTen()
   {
     return this.dmloaithuocTen;
   }

   public void setDmloaithuocTen(String dmloaithuocTen)
   {
     this.dmloaithuocTen = dmloaithuocTen;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getPlthuocTen()
   {
     return this.plthuocTen;
   }

   public void setPlthuocTen(String plthuocTen)
   {
     this.plthuocTen = plthuocTen;
   }

   public String getDmnctMa()
   {
     return this.dmnctMa;
   }

   public void setDmnctMa(String dmnctMa)
   {
     this.dmnctMa = dmnctMa;
   }

   public Integer getDmnctMaso()
   {
     return this.dmnctMaso;
   }

   public void setDmnctMaso(Integer dmnctMaso)
   {
     this.dmnctMaso = dmnctMaso;
   }

   public String getDmnguonkinhphiMa()
   {
     return this.dmnguonkinhphiMa;
   }

   public void setDmnguonkinhphiMa(String dmnguonkinhphiMa)
   {
     this.dmnguonkinhphiMa = dmnguonkinhphiMa;
   }

   public Integer getDmnguonkinhphiMaso()
   {
     return this.dmnguonkinhphiMaso;
   }

   public void setDmnguonkinhphiMaso(Integer dmnguonkinhphiMaso)
   {
     this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
   }

   public Double getTonsauKiemKe()
   {
     return this.tonsauKiemKe;
   }

   public void setTonsauKiemKe(Double tonsauKiemKe)
   {
     this.tonsauKiemKe = tonsauKiemKe;
   }

   public Double getTonhientai()
   {
     return this.tonhientai;
   }

   public void setTonhientai(Double tonhientai)
   {
     this.tonhientai = tonhientai;
   }

   private List<SelectItem> listDmLoaiThuocs = new ArrayList();

   public List<SelectItem> getListDmLoaiThuocs()
   {
     return this.listDmLoaiThuocs;
   }

   public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs)
   {
     this.listDmLoaiThuocs = listDmLoaiThuocs;
   }

   public List<SelectItem> getListDmPhanLoaiThuocs()
   {
     return this.listDmPhanLoaiThuocs;
   }

   public void setListDmPhanLoaiThuocs(List<SelectItem> listDmPhanLoaiThuocs)
   {
     this.listDmPhanLoaiThuocs = listDmPhanLoaiThuocs;
   }

   public void onblurMaLoaiThuocAction()
   {
     log.info("-----BEGIN onblurMaLoaiThuocAction()-----");
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(this.loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null)
     {
       setDmloaithuocTen(dmLoaiThuoc.getDmloaithuocTen());
       setLoaihang_maso(dmLoaiThuoc.getDmloaithuocMaso());
     }
     else
     {
       setDmloaithuocTen("");
       setLoaihang_maso(null);
     }
     setDmtMa("");
     setDmtTen("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.listDmPhanLoaiThuocs.clear();
     this.hmPhanLoaiThuoc.clear();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     log.info("-----END onblurMaLoaiThuocAction()-----");
   }

   public void onblurTenLoaiThuocAction()
   {
     log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
     Boolean hasTenLoai = Boolean.valueOf(false);

     Set set = this.hmLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     DmLoaiThuoc dmLoaiThuoc_Finded = new DmLoaiThuoc();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
       if ((dmLoaiThuoc.getDmloaithuocTen() == this.dmloaithuocTen) || (dmLoaiThuoc.getDmloaithuocTen().equals(this.dmloaithuocTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         dmLoaiThuoc_Finded = dmLoaiThuoc;
         break;
       }
     }
     if (hasTenLoai.booleanValue() == true)
     {
       setDmloaithuocTen(dmLoaiThuoc_Finded.getDmloaithuocTen());
       setLoaihang_maso(dmLoaiThuoc_Finded.getDmloaithuocMaso());
     }
     else
     {
       setDmloaithuocTen("");
       setLoaihang_maso(null);
     }
     setDmtMa("");
     setDmtTen("");
     setPlthuoc_ma("");
     setPlthuocTen("");
     setPlthuoc_maso(null);
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
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

   public void onblurMaPhanLoaiThuocAction()
   {
     log.info("-----BEGIN onblurMaPhanLoaiThuocAction()-----");
     DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)this.hmPhanLoaiThuoc.get(this.plthuoc_ma.toUpperCase());
     if (dmPhanLoaiThuoc != null)
     {
       setPlthuocTen(dmPhanLoaiThuoc.getDmphanloaithuocTen());
       setPlthuoc_maso(dmPhanLoaiThuoc.getDmphanloaithuocMaso());
     }
     else
     {
       setPlthuocTen("");
       setPlthuoc_maso(null);
     }
     setDmtMa("");
     setDmtTen("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     log.info("-----END onblurMaLoaiThuocAction()-----");
   }

   public void onblurTenPhanLoaiThuocAction()
   {
     log.info("-----BEGIN onblurTenPhanLoaiThuocAction()-----");
     Boolean hasTenPhanLoai = Boolean.valueOf(false);

     Set set = this.hmPhanLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     DmPhanLoaiThuoc dmPhanLoaiThuoc_Finded = new DmPhanLoaiThuoc();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)me.getValue();
       if ((dmPhanLoaiThuoc.getDmphanloaithuocTen() == this.dmloaithuocTen) || (dmPhanLoaiThuoc.getDmphanloaithuocTen().equals(this.plthuocTen)))
       {
         hasTenPhanLoai = Boolean.valueOf(true);
         dmPhanLoaiThuoc_Finded = dmPhanLoaiThuoc;
         break;
       }
     }
     if (hasTenPhanLoai.booleanValue() == true)
     {
       setPlthuocTen(dmPhanLoaiThuoc_Finded.getDmphanloaithuocTen());
       setPlthuoc_maso(dmPhanLoaiThuoc_Finded.getDmphanloaithuocMaso());
     }
     else
     {
       setPlthuocTen("");
       setPlthuoc_maso(null);
     }
     setDmtMa("");
     setDmtTen("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     log.info("-----END onblurTenLoaiThuocAction()-----");
   }

   public void refreshDmPhanLoaiThuoc()
   {
     this.listDmPhanLoaiThuocs.clear();
     this.dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
     this.hmPhanLoaiThuoc.clear();
     List<DmPhanLoaiThuoc> lstDmPLT = this.dmPhanLoaiThuocDelegate.findByDtdmloaiMa(this.loaihang_ma);
     if ((lstDmPLT != null) || (lstDmPLT.size() > 0)) {
       for (DmPhanLoaiThuoc o : lstDmPLT)
       {
         this.listDmPhanLoaiThuocs.add(new SelectItem(o.getDmphanloaithuocTen()));
         this.hmPhanLoaiThuoc.put(o.getDmphanloaithuocMa(), o);
       }
     }
     refreshDmThuoc();
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
     if (this.dmtMa != null)
     {
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.dmtMa.toUpperCase());
       if (dmThuoc != null)
       {
         setDmtTen(dmThuoc.getDmthuocTen());
         setDmtMaso(dmThuoc.getDmthuocMaso());
         log.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         setDmtTen("");
         setDmtMaso(null);
       }
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = "";
     Integer masoThuoc = null;
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
           masoThuoc = dmThuoc.getDmthuocMaso();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setDmtMa(maThuoc);
       setDmtMaso(masoThuoc);
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
     List<DmThuoc> lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuoc(this.loaihang_ma, this.plthuoc_ma);
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   @Factory("resetFormCapNhatSLGUI")
   public void resetFormCapNhatSLGUI()
   {
     log.info("resetFormCapNhatSLGUI()");
     resetvalue();
     this.itemsPerPage = 20;
     this.kiemke = this.kiemkeOut;
     if (this.kiemkeKhoOut != null)
     {
       this.kiemkeKho = this.kiemkeKhoOut;
       this.listKiemKeKho.add(this.kiemkeKho);
     }
     refreshDmLoaiThuoc();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
   }

   @Begin(join=true)
   public String init(String kho)
     throws Exception
   {
     resetvalue();
     this.itemsPerPage = 20;
     this.kiemke = this.kiemkeOut;
     if (this.kiemkeKhoOut != null)
     {
       this.kiemkeKho = this.kiemkeKhoOut;
       this.listKiemKeKho.add(this.kiemkeKho);
     }
     refreshDmLoaiThuoc();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
   }

   @End
   public void end() {}

   public String quayve()
     throws Exception
   {
     resetvalue();
     this.resetFormCapNhatSLGUI = null;
     return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
   }

   public void resetvalue()
   {
     log.info("---resetValue");
     this.kiemkeKho = new KiemKeKho();
     this.listKiemKeKho = new ArrayList();
     this.loaihang_ma = "";
     this.loaihang_maso = null;
     this.dmloaithuocTen = "";
     this.plthuoc_ma = "";
     this.plthuocTen = "";
     this.plthuoc_maso = null;
     this.dmtMa = "";
     this.dmtMaso = null;
     this.dmtTen = "";
     this.dmnctMaso = null;
     this.dmnctMa = "";
     this.dmnguonkinhphiMaso = null;
     this.dmnguonkinhphiMa = "";
   }

   public void resetFields()
   {
     log.info("---resetFields");
     this.loaihang_ma = "";
     this.loaihang_maso = null;
     this.dmloaithuocTen = "";
     this.plthuoc_ma = "";
     this.plthuocTen = "";
     this.plthuoc_maso = null;
     this.dmtMa = "";
     this.dmtMaso = null;
     this.dmtTen = "";
     this.dmnctMaso = null;
     this.dmnctMa = "";
     this.dmnguonkinhphiMaso = null;
     this.dmnguonkinhphiMa = "";
     this.kiemkeKho = new KiemKeKho();
   }

   public void timkiem()
   {
     log.info("---timkiem---");
     KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
     List<KiemKeKho> lstKiemKeKho = kiemkeKhoDel.findKiemKeKhoForCapNhatSLGUI(this.kiemke.getMaphieukiem(), this.loaihang_maso, this.plthuoc_maso, this.dmtMaso, this.dmnctMaso, this.dmnguonkinhphiMaso);
     if ((lstKiemKeKho != null) || (lstKiemKeKho.size() > 0)) {
       this.listKiemKeKho = lstKiemKeKho;
     } else {
       FacesMessages.instance().add("Không có thuốc trong bảng kiểm kê: " + this.kiemke.getMaphieukiem(), null);
     }
   }

   public void resetForm()
   {
     resetvalue();
   }

   public void ghinhan()
   {
     log.info("---Begin ghinhan---");
     KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv == null)
     {
       FacesMessages.instance().add(this.identity.getUsername() + " không thuộc nhân viên của Bệnh viện", new Object[0]);
       return;
     }
     if (this.kiemkeKho.getKiemkeMa() != null)
     {
       this.kiemkeKho.setDtdmnhanvienCn(nv);
       this.kiemkeKho.setKiemkeNgaygiocn(new Date());
       kiemkeKhoDel.edit(this.kiemkeKho);

       int i = 0;
       for (KiemKeKho kiemkekho : this.listKiemKeKho)
       {
         if (kiemkekho.getKiemkeMa() == this.kiemkeKho.getKiemkeMa())
         {
           this.listKiemKeKho.set(i, this.kiemkeKho);
           break;
         }
         i++;
       }
       resetFields();
       FacesMessages.instance().add("Cập nhật số liệu thành công!", new Object[0]);
     }
     else
     {
       FacesMessages.instance().add("Không có dữ liệu Kiểm kê kho!", new Object[0]);
     }
     log.info("---End ghinhan---");
   }

   public void selectKiemKeKho(int index)
   {
     log.info("---selectKiemKeKho---" + index);
     this.kiemkeKho = ((KiemKeKho)this.listKiemKeKho.get(index));
   }

   public KiemKeKho getKiemkeKho()
   {
     return this.kiemkeKho;
   }

   public void setKiemkeKho(KiemKeKho kiemkeKho)
   {
     this.kiemkeKho = kiemkeKho;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.CapNhatSoLieuThucTeGUI

 * JD-Core Version:    0.7.0.1

 */