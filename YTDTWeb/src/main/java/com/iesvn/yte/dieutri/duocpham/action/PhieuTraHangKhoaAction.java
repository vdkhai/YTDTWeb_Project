 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
 import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4113_Phieutrahangkhoa")
 @Synchronized(timeout=6000000L)
 public class PhieuTraHangKhoaAction
   implements Serializable
 {
   private static final long serialVersionUID = 3694949080102104995L;
   private static Logger log = Logger.getLogger(PhieuTraHangKhoaAction.class);
   private PhieuTraKho phieuTraKho;
   private String ngayXuat;
   private PhieuDuTru phieuDuTru;
   private Integer nguonCtMaSo;
   private String nguonCtMa;
   private Integer nguonKpMaSo;
   private String nguonKpMa;
   private String maKho;
   @DataModel
   private List<GridItem> listCtTraKhoB4113;
   @DataModelSelection
   private GridItem selCtTraKhoB4113;
   private GridItem ctTraKhoB4113;
   private boolean isUpdate;
   private String nofoundPDT;
   private String nosuccess;
   private String nofound;
   private String hienThiGhiNhan;
   private String hienThiInPhieu;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private DtDmNhanVien nvCn;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   private String dmLoaiTen;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs;
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc;
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   private List<SelectItem> listDmLoaiPhieus;
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

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNofoundPDT()
   {
     return this.nofoundPDT;
   }

   public void setNofoundPDT(String nofoundPDT)
   {
     this.nofoundPDT = nofoundPDT;
   }

   @Begin(join=true)
   public String init(String loaikho)
   {
     log.info("---init");
     resetValue();
     if ("KhoNoiTru".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
       this.maKho = IConstantsRes.KHOA_NT_MA;
     }
     else if ("BHYT".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
       this.maKho = IConstantsRes.KHOA_BHYT_MA;
     }
     else if ("KC".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       this.maKho = IConstantsRes.KHOA_KC_MA;
     }
     else if ("TE".equals(loaikho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
       this.maKho = IConstantsRes.KHOA_TE_MA;
     }
     log.info("tenChuongTrinh" + this.tenChuongTrinh);
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     setNvCn(nvDelegate.findByNd(this.identity.getUsername()));
     if (getNvCn() == null) {
       setNvCn(new DtDmNhanVien());
     }
     log.info("nvCn" + this.nvCn);
     this.phieuTraKho.setDtdmnhanvienCn(this.nvCn);
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();

     return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTheoPhieuDuTru";
   }

   private void resetValue()
   {
     log.info("---resetValue");
     this.ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
     this.phieuTraKho = new PhieuTraKho();
     this.phieuTraKho.setPhieutrakhoThanhtien(Double.valueOf(0.0D));
     SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
     this.phieuDuTru = new PhieuDuTru();
     SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
     this.nofoundPDT = "false";
     this.nosuccess = "false";
     this.nofound = "false";
     this.listCtTraKhoB4113 = new ArrayList();
     this.ctTraKhoB4113 = new GridItem();
     this.nguonCtMa = "";
     this.nguonKpMa = "";
     this.dmLoaiTen = "";
     this.isUpdate = false;
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
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     this.phieuDuTru.setPhieudtLoaiPhieu("");
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
     this.phieuDuTru.setPhieudtLoaiPhieu("");
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
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus) {
           if (this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public void displayPhieuDuTru()
   {
     log.info("displayPhieuDuTru");
     try
     {
       this.listCtTraKhoB4113.clear();
       if ((this.phieuDuTru.getPhieudtMa() == null) || (this.phieuDuTru.getPhieudtMa().equals("")))
       {
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "";
         return;
       }
       PhieuTraKhoDelegate ptkDel = PhieuTraKhoDelegate.getInstance();
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoaNhan = new DmKhoa();
       dmKhoaNhan = (DmKhoa)dieuTriUtilDelegate.findByMa(this.maKho, "DmKhoa", "dmkhoaMa");
       PhieuTraKho ptk_tmp = ptkDel.findByPhieuDuTruMa(this.phieuDuTru.getPhieudtMa(), dmKhoaNhan.getDmkhoaMaso());
       if (ptk_tmp != null)
       {
         this.phieuTraKho = ptk_tmp;
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
         this.ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(this.phieuTraKho.getPhieutrakhoNgay());
         CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
         for (CtTraKho obj : delegateCt.findByphieutrakhoMa(this.phieuTraKho.getPhieutrakhoMa()))
         {
           GridItem ct = new GridItem();
           ct.setCtTraKho(obj);
           this.listCtTraKhoB4113.add(ct);
         }
         PhieuDuTru pdt_tmp = this.phieuTraKho.getPhieudtMa();
         if (pdt_tmp != null)
         {
           this.phieuDuTru = pdt_tmp;
           SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
           this.dmLoaiTen = ptk_tmp.getDmloaithuocMaso(true).getDmloaithuocTen();
         }
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG, new Object[] { this.phieuDuTru.getPhieudtMa() });
         return;
       }
       PhieuDuTru pdt_temp = CtPhieuDtDelegate.getInstance().findByPhieuDuTruPhanBietKho(this.phieuDuTru.getPhieudtMa(), Integer.valueOf(2), dmKhoaNhan.getDmkhoaMaso());
       if (pdt_temp == null)
       {
         this.nofoundPDT = "true";
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "";
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHO_NOT_FOUND, new Object[] { this.phieuDuTru.getPhieudtMa() });
         return;
       }
       List<CtPhieuDt> listCtpdt_tmp = CtPhieuDtDelegate.getInstance().findByPhieuDuTruMa(this.phieuDuTru.getPhieudtMa());
       if ((listCtpdt_tmp != null) && (listCtpdt_tmp.size() > 0))
       {
         this.phieuDuTru = pdt_temp;
       }
       else
       {
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "";
         return;
       }
       SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
       this.dmLoaiTen = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocTen();

       log.info("-----listCtpdt_tmp: " + listCtpdt_tmp);
       for (CtPhieuDt ctpdt : listCtpdt_tmp)
       {
         log.info("-----cttk ma: " + ctpdt.getCtdtMa());
         log.info("-----cttk soluong: " + ctpdt.getCtdtTra());

         CtTraKho cttk = new CtTraKho();
         cttk.setPhieutrakhoMa(this.phieuTraKho);
         cttk.setCttrakhoDongia(ctpdt.getCtdtDongia());
         cttk.setCttrakhoSoluong(ctpdt.getCtdtTra());
         cttk.setDmthuocMaso(ctpdt.getDmthuocMaso());
         cttk.setCttrakhoMalk(ctpdt.getCtdtMalk());
         SetInforUtil.setInforIfNullForCTPhieuTraKho(cttk);
         this.listCtTraKhoB4113.add(new GridItem(cttk, ctpdt.getCtdtSoluong()));
       }
       log.info("-----listCtTraKhoB4113: " + this.listCtTraKhoB4113.size());

       this.hienThiGhiNhan = "true";
       this.hienThiInPhieu = "";
       tinhTongTien();
       log.info("tong tien phieu: " + this.phieuTraKho.getPhieutrakhoThanhtien());

       PhieuTraKhoDelegate phieuTraKhoDelegate = PhieuTraKhoDelegate.getInstance();

       log.info("phieuDuTru.getPhieudtMa():" + this.phieuDuTru.getPhieudtMa());

       boolean daTraPhieuDuTru = phieuTraKhoDelegate.daTraPhieuDuTru(this.phieuDuTru.getPhieudtMa());
       log.info("-----da tra pdt: " + daTraPhieuDuTru);
       if (daTraPhieuDuTru == true)
       {
         this.hienThiGhiNhan = "";

         this.hienThiInPhieu = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG, new Object[] { this.phieuDuTru.getPhieudtMa() });
         return;
       }
     }
     catch (Exception e)
     {
       log.info(e.toString());
     }
   }

   public void delete()
   {
     log.info("---delete");
     this.listCtTraKhoB4113.remove(this.selCtTraKhoB4113);
     this.ctTraKhoB4113 = new GridItem();
   }

   public void select()
   {
     log.info("---select");
     this.ctTraKhoB4113 = this.selCtTraKhoB4113;
     this.isUpdate = true;
   }

   public void enter()
   {
     log.info("---enter");
     if (this.isUpdate) {
       this.isUpdate = false;
     } else {
       this.listCtTraKhoB4113.add(this.ctTraKhoB4113);
     }
     tinhTongTien();
     this.ctTraKhoB4113 = new GridItem();
   }

   private void tinhTongTien()
   {
     Double tongtien = new Double(0.0D);
     if (this.listCtTraKhoB4113 != null) {
       for (GridItem obj : this.listCtTraKhoB4113)
       {
         log.info("-----obj: " + obj.getCtTraKho());
         tongtien = Double.valueOf(tongtien.doubleValue() + (obj.getCtTraKho().getCttrakhoSoluong() == null ? 0.0D : obj.getCtTraKho().getCttrakhoSoluong().doubleValue()) * (obj.getCtTraKho().getCttrakhoDongia() == null ? 0.0D : obj.getCtTraKho().getCttrakhoDongia().doubleValue()));
       }
     }
     this.phieuTraKho.setPhieutrakhoThanhtien(tongtien);
   }

   public void nhapMoi()
     throws Exception
   {
     log.info("---nhapMoi");
     this.hienThiGhiNhan = "";
     this.hienThiInPhieu = "";
     resetValue();
   }

   public void ghiNhan()
     throws Exception
   {
     log.info("---ghiNhan");
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
     if ((this.phieuDuTru.getPhieudtMa() == null) || (this.phieuDuTru.getPhieudtMa().equals("")) || (this.listCtTraKhoB4113 == null) || (this.listCtTraKhoB4113.size() == 0) || (this.phieuTraKho.getPhieutrakhoThanhtien().doubleValue() == 0.0D))
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
       this.hienThiGhiNhan = "";

       this.hienThiInPhieu = "";
     }
     else
     {
       this.phieuTraKho.setPhieudtMa(this.phieuDuTru);
       this.phieuTraKho.setPhieutrakhoNgaygiocn(new Date());
       if (!this.ngayXuat.equals("")) {
         this.phieuTraKho.setPhieutrakhoNgay(new SimpleDateFormat("dd/MM/yyyy").parse(this.ngayXuat));
       }
       this.phieuTraKho.setDmkhoaNhan(this.phieuDuTru.getPhieudtMakho());
       this.phieuTraKho.setDmkhoaTra(this.phieuDuTru.getDmkhoaMaso());
       this.phieuTraKho.setDmloaithuocMaso(this.phieuDuTru.getDmloaithuocMaso(true));
       this.phieuTraKho.setPhieutrakhoLoaiPhieu(this.phieuDuTru.getPhieudtLoaiPhieu());
       this.phieuTraKho.setPhieutrakhoNgaygiophat(new Date());
       for (GridItem item : this.listCtTraKhoB4113)
       {
         CtTraKho ct = item.getCtTraKho();

         boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCttrakhoMalk(), this.phieuTraKho.getDmkhoaNhan().getDmkhoaMaso());
         if (tinhtrangKiemKe == true)
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, new Object[] { ct.getCttrakhoMalk() });
           return;
         }
       }
       RemoveUtil.removeIfNullForPhieuTraKho(this.phieuTraKho);
       List<CtTraKho> listTraKho = new ArrayList();
       CtTraKho tmp = null;
       for (GridItem item : this.listCtTraKhoB4113)
       {
         tmp = item.getCtTraKho();
         RemoveUtil.removeIfNullForCTPhieuTraKho(tmp);
         listTraKho.add(tmp);
       }
       String result = PhieuTraKhoDelegate.getInstance().TraPhieuDuTru(listTraKho, this.phieuTraKho);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_TRA_HANG, new Object[] { this.phieuDuTru });
         this.hienThiGhiNhan = "";

         this.hienThiInPhieu = "";
       }
       else
       {
         this.phieuTraKho.setPhieutrakhoMa(result);
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { result });
         this.hienThiGhiNhan = "";

         this.hienThiInPhieu = "true";
       }
     }
   }

   public void displayPhieuTraKho()
     throws Exception
   {
     log.info("---displayPhieuTraKho");
     this.ctTraKhoB4113 = new GridItem();
     this.phieuDuTru = new PhieuDuTru();
     SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
     String maPhieuTra = this.phieuTraKho.getPhieutrakhoMa();
     if ((maPhieuTra != null) && (!maPhieuTra.equals("")))
     {
       PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
       PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoMa(maPhieuTra);
       if (ptk_tmp != null)
       {
         this.phieuTraKho = ptk_tmp;
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
         this.ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(this.phieuTraKho.getPhieutrakhoNgay());
         CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
         for (CtTraKho obj : delegateCt.findByphieutrakhoMa(this.phieuTraKho.getPhieutrakhoMa()))
         {
           GridItem ct = new GridItem();
           ct.setCtTraKho(obj);
           this.listCtTraKhoB4113.add(ct);
         }
         PhieuDuTru pdt_tmp = this.phieuTraKho.getPhieudtMa();
         if (pdt_tmp != null)
         {
           this.phieuDuTru = pdt_tmp;
           SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
           this.dmLoaiTen = pdt_tmp.getDmloaithuocMaso(true).getDmloaithuocTen();
         }
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "true";
       }
       else
       {
         this.nofound = "true";
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "";
       }
     }
   }

   public PhieuTraKho getPhieuTraKho()
   {
     return this.phieuTraKho;
   }

   public void setPhieuTraKho(PhieuTraKho phieuTraKho)
   {
     this.phieuTraKho = phieuTraKho;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public PhieuDuTru getPhieuDuTru()
   {
     return this.phieuDuTru;
   }

   public void setPhieuDuTru(PhieuDuTru phieuDuTru)
   {
     this.phieuDuTru = phieuDuTru;
   }

   public Integer getNguonCtMaSo()
   {
     return this.nguonCtMaSo;
   }

   public void setNguonCtMaSo(Integer nguonCtMaSo)
   {
     this.nguonCtMaSo = nguonCtMaSo;
   }

   public String getNguonCtMa()
   {
     return this.nguonCtMa;
   }

   public void setNguonCtMa(String nguonCtMa)
   {
     this.nguonCtMa = nguonCtMa;
   }

   public Integer getNguonKpMaSo()
   {
     return this.nguonKpMaSo;
   }

   public void setNguonKpMaSo(Integer nguonKpMaSo)
   {
     this.nguonKpMaSo = nguonKpMaSo;
   }

   public String getNguonKpMa()
   {
     return this.nguonKpMa;
   }

   public void setNguonKpMa(String nguonKpMa)
   {
     this.nguonKpMa = nguonKpMa;
   }

   public PhieuTraHangKhoaAction()
   {
     this.hienThiGhiNhan = "";
     this.hienThiInPhieu = "";



























     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();

     this.listDmLoaiPhieus = new ArrayList();
































































































































































































































































































































































































































































     this.index = 0;





     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeKC = "phieutrahangtheodutrutra";
     log.info("Vao Method XuatReport D03_phieuxuatkho");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.phieuTraKho.getPhieutrakhoMa().equals("")) {
       try
       {
         PhieuTraKhoDelegate ptkWS = PhieuTraKhoDelegate.getInstance();
         PhieuTraKho px = ptkWS.find(this.phieuTraKho.getPhieutrakhoMa());


         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieutrakho_01.jrxml";
         log.info(String.format("-----pathTemplate: %s", new Object[] { pathTemplate }));

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);


         Map<String, Object> params = new HashMap();
         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

         params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

         log.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         Calendar cal = Calendar.getInstance();
         cal.setTime(px.getPhieutrakhoNgay());
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

         params.put("pxMa", px.getPhieutrakhoMa());
         if (px.getDmkhoaNhan() != null) {
           params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (px.getDmkhoaTra() != null) {
           params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format("-----khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", px.getPhieutrakhoThanhtien());
         log.info(String.format("-----tongTien: %s", new Object[] { params.get("tongTien") }));
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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "phieutrahangtheodutrutra");
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

   public class GridItem
   {
     private CtTraKho ctTraKho;
     private Double soLuong;

     public GridItem()
     {
       this.ctTraKho = new CtTraKho();
       if (this.ctTraKho.getDmthuocMaso() == null) {
         this.ctTraKho.setDmthuocMaso(new DmThuoc());
       }
       this.soLuong = new Double(0.0D);
     }

     public GridItem(CtTraKho _ctTraKho, Double _soLuong)
     {
       this.ctTraKho = _ctTraKho;
       this.soLuong = _soLuong;
     }

     public CtTraKho getCtTraKho()
     {
       return this.ctTraKho;
     }

     public void setCtTraKho(CtTraKho ctTraKho)
     {
       this.ctTraKho = ctTraKho;
     }

     public Double getSoLuong()
     {
       return this.soLuong;
     }

     public void setSoLuong(Double soLuong)
     {
       this.soLuong = soLuong;
     }
   }

   public GridItem getSelCtTraKhoB4113()
   {
     return this.selCtTraKhoB4113;
   }

   public void setSelCtTraKhoB4113(GridItem selCtTraKhoB4113)
   {
     this.selCtTraKhoB4113 = selCtTraKhoB4113;
   }

   public List<GridItem> getListCtTraKhoB4113()
   {
     return this.listCtTraKhoB4113;
   }

   public void setListCtTraKhoB4113(List<GridItem> listCtTraKhoB4113)
   {
     this.listCtTraKhoB4113 = listCtTraKhoB4113;
   }

   public GridItem getCtTraKhoB4113()
   {
     return this.ctTraKhoB4113;
   }

   public void setCtTraKhoB4113(GridItem ctTraKhoB4113)
   {
     this.ctTraKhoB4113 = ctTraKhoB4113;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }

   public void setHienThiGhiNhan(String hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public String getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(String hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public static long getSerialVersionUID()
   {
     return 3694949080102104995L;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public DtDmNhanVien getNvCn()
   {
     return this.nvCn;
   }

   public void setNvCn(DtDmNhanVien nvCn)
   {
     this.nvCn = nvCn;
   }

   @End
   public void end() {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieuTraHangKhoaAction

 * JD-Core Version:    0.7.0.1

 */