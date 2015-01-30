 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtNhapKho;
 import com.iesvn.yte.dieutri.entity.DtDmKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4111_phieunhapkhochinh")
 public class PhieuNhapKhoAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(PhieuNhapKhoAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private PhieuNhapKho pnk;
   private CtNhapKho cnk;
   private DmKhoa khoa;
   private String ngayHt;
   private String ngayLap = "";
   private String ngayHD;
   private String thangHD;
   private String namHD;
   private String namNhap;
   private Boolean isUpdate;
   private Integer count;
   private String maPhieu;
   @DataModel
   private ArrayList<CtNhapKho> listCtNhapKho;
   @DataModelSelection
   private CtNhapKho selected;
   private Double thanhTien;
   private Double thueGtgt;
   private Double thanhTienThue;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private String dmloaithuocTen = "";
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private Double tonkhoHT;
   private Double giadauthau;
   private Double giacuoi;
   private DmThuocDelegate dmThuocDelegate;

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     reset();
     this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     return "QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap";
   }

   private List<SelectItem> listDmThuocs = new ArrayList();

   @End
   public void endFunc() {}

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
     if ((this.cnk != null) && (this.cnk.getDmthuocMaso(true) != null))
     {
       String mathang_ma = this.cnk.getDmthuocMaso(true).getDmthuocMa();
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(mathang_ma.toUpperCase());
       if (dmThuoc != null)
       {
         this.cnk.setDmthuocMaso(dmThuoc);
         this.giadauthau = dmThuoc.getDmthuocDonGiaDauThau();
         DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
         this.giacuoi = Double.valueOf(dtutilDelegate.getGiaCuoi(dmThuoc.getDmthuocMa()));
         this.cnk.setCtnhapkhoSodangky(dmThuoc.getDmthuocSoDangKy());
         this.cnk.setDmquocgiaMaso(dmThuoc.getDmthuocNuocdefa());
         this.cnk.setDmnhasanxuatMaso(dmThuoc.getDmnhasanxuatMaso());
         logger.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.cnk.setDmthuocMaso(new DmThuoc());
         this.giacuoi = null;
         this.giadauthau = null;
         this.cnk.setCtnhapkhoSodangky(null);
         this.cnk.setDmquocgiaMaso(new DmQuocGia());
         this.cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
       }
     }
     this.cnk.setCtnhapkhoSoluong(null);
     this.cnk.setCtnhapkhoDongia(0.0D);
     this.cnk.setCtnhapkhoDongiaban(null);
     setTonkhoHT(null);
     this.cnk.setCtnhapkhoLo(null);
     logger.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     logger.info("-----BEGIN onblurTenThuocAction()-----");
     if ((this.cnk != null) && (this.cnk.getDmthuocMaso(true) != null))
     {
       String tenThuoc = this.cnk.getDmthuocMaso(true).getDmthuocTen();
       Boolean hasTenThuoc = Boolean.valueOf(false);
       String maThuoc = null;
       Integer masoThuoc = null;
       if (this.hmDmThuoc != null)
       {
         Set set = this.hmDmThuoc.entrySet();
         Iterator i = set.iterator();
         while (i.hasNext())
         {
           Map.Entry me = (Map.Entry)i.next();
           DmThuoc dmThuoc = (DmThuoc)me.getValue();
           if ((dmThuoc.getDmthuocTen().trim() == tenThuoc.trim()) || (dmThuoc.getDmthuocTen().trim().equals(tenThuoc.trim())))
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
         DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
         DmThuoc dmThuoc = (DmThuoc)dieutriUtilDelegate.findByMaSo(masoThuoc, "DmThuoc", "dmthuocMaso");
         this.cnk.setDmthuocMaso(dmThuoc);
         this.giadauthau = dmThuoc.getDmthuocDonGiaDauThau();
         this.giacuoi = Double.valueOf(dieutriUtilDelegate.getGiaCuoi(dmThuoc.getDmthuocMa()));
         this.cnk.setCtnhapkhoSodangky(dmThuoc.getDmthuocSoDangKy());
         this.cnk.setDmquocgiaMaso(dmThuoc.getDmthuocNuocdefa());
         this.cnk.setDmnhasanxuatMaso(dmThuoc.getDmnhasanxuatMaso());
         logger.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.cnk.setDmthuocMaso(new DmThuoc());
         this.giacuoi = null;
         this.giadauthau = null;
         this.cnk.setCtnhapkhoSodangky(null);
         this.cnk.setDmquocgiaMaso(new DmQuocGia());
         this.cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
       }
     }
     this.cnk.setCtnhapkhoSoluong(null);
     this.cnk.setCtnhapkhoDongia(0.0D);
     this.cnk.setCtnhapkhoDongiaban(null);
     setTonkhoHT(null);
     this.cnk.setCtnhapkhoLo(null);
     logger.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     if ((this.pnk != null) && (this.pnk.getDmloaithuocMaso() != null) && (this.pnk.getDmloaithuocMaso(true).getDmloaithuocMa() != null))
     {
       String lthuoc_ma = this.pnk.getDmloaithuocMaso(true).getDmloaithuocMa();
       List<DmThuoc> lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuoc(lthuoc_ma, "");
       if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
         for (DmThuoc o : lstDmThuoc)
         {
           this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
           this.hmDmThuoc.put(o.getDmthuocMa(), o);
         }
       }
     }
   }

   public String getDmloaithuocTen()
   {
     return this.dmloaithuocTen;
   }

   public void setDmloaithuocTen(String dmloaithuocTen)
   {
     this.dmloaithuocTen = dmloaithuocTen;
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

   public void onblurMaLoaiThuocAction()
   {
     logger.info("-----BEGIN onblurMaLoaiThuocAction()-----");
     if ((this.pnk != null) && (this.pnk.getDmloaithuocMaso() != null) && (this.pnk.getDmloaithuocMaso(true).getDmloaithuocMa() != null))
     {
       String lthuoc_ma = this.pnk.getDmloaithuocMaso(true).getDmloaithuocMa();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(lthuoc_ma.toUpperCase());
       if (dmLoaiThuoc != null)
       {
         setDmloaithuocTen(dmLoaiThuoc.getDmloaithuocTen());
         this.pnk.setDmloaithuocMaso(dmLoaiThuoc);
       }
       else
       {
         setDmloaithuocTen("");
         this.pnk.setDmloaithuocMaso(null);
       }
     }
     this.cnk.setDmthuocMaso(null);
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.cnk.setDmthuocMaso(new DmThuoc());
     this.cnk.setCtnhapkhoSodangky(null);
     this.giacuoi = null;
     this.giadauthau = null;
     this.cnk.setCtnhapkhoSodangky(null);
     this.cnk.setDmquocgiaMaso(new DmQuocGia());
     this.cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
     this.cnk.setCtnhapkhoSoluong(null);
     this.cnk.setCtnhapkhoDongia(0.0D);
     this.cnk.setCtnhapkhoDongiaban(null);
     setTonkhoHT(null);
     this.cnk.setCtnhapkhoLo(null);
     logger.info("-----END onblurMaLoaiThuocAction()-----");
   }

   public void onblurTenLoaiThuocAction()
   {
     logger.info("-----BEGIN onblurTenLoaiThuocAction()-----");
     Boolean hasTenLoai = Boolean.valueOf(false);
     if (this.dmloaithuocTen == "") {
       this.pnk.setDmloaithuocMaso(null);
     }
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
     if (hasTenLoai.booleanValue()) {
       this.pnk.setDmloaithuocMaso(dmLoaiThuoc_Finded);
     } else {
       this.pnk.setDmloaithuocMaso(null);
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.cnk.setDmthuocMaso(null);
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.cnk.setDmthuocMaso(new DmThuoc());
     this.cnk.setCtnhapkhoSodangky(null);
     this.giacuoi = null;
     this.giadauthau = null;
     this.cnk.setCtnhapkhoSodangky(null);
     this.cnk.setDmquocgiaMaso(new DmQuocGia());
     this.cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
     this.cnk.setCtnhapkhoSoluong(null);
     this.cnk.setCtnhapkhoDongia(0.0D);
     this.cnk.setCtnhapkhoDongiaban(null);
     setTonkhoHT(null);
     this.cnk.setCtnhapkhoLo(null);
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

   public void reset()
   {
     this.pnk = new PhieuNhapKho();
     this.cnk = new CtNhapKho();
     this.khoa = new DmKhoa();
     this.ngayHt = Utils.getCurrentDate();
     this.listCtNhapKho = new ArrayList();
     this.selected = new CtNhapKho();
     this.ngayLap = Utils.getCurrentDate();

     this.ngayHD = "";
     this.thangHD = "";
     this.namHD = "";


     this.maPhieu = "";
     this.namNhap = ("" + Calendar.getInstance().get(1));
     this.isUpdate = Boolean.valueOf(false);
     this.count = Integer.valueOf(this.listCtNhapKho.size());
     this.thanhTien = new Double("0");
     this.thueGtgt = new Double("0");
     this.pnk.setPhieunhapkhoMucthue(this.thueGtgt);
     this.thanhTienThue = new Double("0");

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     try
     {
       DtDmNhanVien nvCn = nvDelegate.findByNd(this.identity.getUsername());
       if (nvCn != null) {
         this.pnk.setDtdmnhanvienTieplieu(nvCn);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
     try
     {
       this.khoa = ((DmKhoa)dtDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
       logger.info("----- khoa: " + this.khoa);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     this.listDmLoaiThuocs.clear();
     this.listDmThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();
     setDmloaithuocTen("");
     refreshDmLoaiThuoc();
     refreshDmThuoc();
     this.tonkhoHT = new Double(0.0D);
   }

   public void selectCt(Integer index)
   {
     logger.info("-----selectCt()-----");
     logger.info("-----index: " + index);
     try
     {
       this.cnk = new CtNhapKho();
       this.cnk = ((CtNhapKho)BeanUtils.cloneBean(this.listCtNhapKho.get(index.intValue())));
       System.out.println("Ma so DVT: " + this.cnk.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso());
       DieuTriUtilDelegate dtriDel = DieuTriUtilDelegate.getInstance();
       DmDonViTinh dmDVT = (DmDonViTinh)dtriDel.findByMaSo(this.cnk.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
       if (dmDVT != null) {
         this.cnk.getDmthuocMaso(true).setDmdonvitinhMaso(dmDVT);
       } else {
         this.cnk.getDmthuocMaso(true).setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.ngayHD = "";
       this.thangHD = "";
       this.namHD = "";
       if (this.cnk.getCtnhapkhoNgayhandung().trim() != null) {
         this.ngayHD = this.cnk.getCtnhapkhoNgayhandung();
       }
       if (this.cnk.getCtnhapkhoThanghandung().trim() != null) {
         this.thangHD = this.cnk.getCtnhapkhoThanghandung();
       }
       if (this.cnk.getCtnhapkhoNamhandung().trim() != null) {
         this.namHD = this.cnk.getCtnhapkhoNamhandung();
       }
       TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
       if ((this.cnk != null) && (this.cnk.getDmthuocMaso(true).getDmthuocMa() != null) && (this.cnk.getCtnhapkhoLo() != null))
       {
         TonKho tkHT = tkDel.getTonKhoHienTai(this.cnk.getDmthuocMaso(true).getDmthuocMaso(), this.cnk.getCtnhapkhoLo(), Double.valueOf(this.cnk.getCtnhapkhoDongia()), this.khoa.getDmkhoaMaso());
         if (tkHT != null) {
           this.tonkhoHT = tkHT.getTonkhoTon();
         } else {
           this.tonkhoHT = Double.valueOf(0.0D);
         }
       }
       this.giadauthau = this.cnk.getDmthuocMaso(true).getDmthuocDonGiaDauThau();
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       this.giacuoi = Double.valueOf(dtutilDelegate.getGiaCuoi(this.cnk.getDmthuocMaso(true).getDmthuocMa()));
     }
     catch (Exception er)
     {
       System.out.println("Error: " + er.getMessage());
     }
   }

   public void deleteCt(Integer index)
   {
     logger.info("-----deleteCt()-----");
     logger.info("-----index: " + index);
     this.listCtNhapKho.remove(index.intValue());
     this.count = Integer.valueOf(this.listCtNhapKho.size());
     for (int i = 0; i < this.listCtNhapKho.size(); i++)
     {
       CtNhapKho ct = (CtNhapKho)this.listCtNhapKho.get(i);
       ct.setCtnhapkhoThutu(Integer.valueOf(i));
     }
     tinhTien();
     logger.info("-----listCtNhapKho.size(): " + this.listCtNhapKho.size());
     this.cnk = new CtNhapKho();

     this.ngayHD = "";
     this.thangHD = "";
     this.namHD = "";
   }

   public void themCt()
   {
     logger.info("-----themCt()-----");
     if (!this.isUpdate.booleanValue())
     {
       try
       {
         DieuTriUtilDelegate dtriDel = DieuTriUtilDelegate.getInstance();
         DmNhaSanXuat nhasx = (DmNhaSanXuat)dtriDel.findByMaSo(this.cnk.getDmnhasanxuatMaso().getDmnhasanxuatMaso(), "DmNhaSanXuat", "dmnhasanxuatMaso");
         if (nhasx != null) {
           this.cnk.setDmnhasanxuatMaso(nhasx);
         } else {
           this.cnk.setDmnhasanxuatMaso(new DmNhaSanXuat());
         }
         DmQuocGia nuocsx = (DmQuocGia)dtriDel.findByMaSo(this.cnk.getDmquocgiaMaso().getDmquocgiaMaso(), "DmQuocGia", "dmquocgiaMaso");
         if (nuocsx != null) {
           this.cnk.setDmquocgiaMaso(nuocsx);
         } else {
           this.cnk.setDmquocgiaMaso(new DmQuocGia());
         }
         CtNhapKho ctNew = (CtNhapKho)BeanUtils.cloneBean(this.cnk);
         ctNew.setCtnhapkhoNamnhap(this.namNhap);
         if (this.ngayHD != "")
         {
           if (this.ngayHD.length() == 1) {
             this.ngayHD = ("0" + this.ngayHD);
           }
           ctNew.setCtnhapkhoNgayhandung(this.ngayHD);
         }
         else
         {
           ctNew.setCtnhapkhoNgayhandung("");
         }
         if (this.thangHD != "")
         {
           if (this.thangHD.length() == 1) {
             this.thangHD = ("0" + this.thangHD);
           }
           ctNew.setCtnhapkhoThanghandung(this.thangHD);
         }
         else
         {
           ctNew.setCtnhapkhoThanghandung("");
         }
         if (this.namHD != "") {
           ctNew.setCtnhapkhoNamhandung(this.namHD);
         } else {
           ctNew.setCtnhapkhoNamhandung("");
         }
         if (ctNew.getCtnhapkhoSodangky() == null) {
           ctNew.setCtnhapkhoSodangky("");
         }
         Double mucThue = this.pnk.getPhieunhapkhoMucthue();
         if ((mucThue != null) && (mucThue.doubleValue() > 0.0D))
         {
           Double donGiaBan = ctNew.getCtnhapkhoDongiaban();
           Double donGia = Double.valueOf(donGiaBan.doubleValue() + donGiaBan.doubleValue() * mucThue.doubleValue() / 100.0D);
           ctNew.setCtnhapkhoDongia(donGia.doubleValue());
         }
         else
         {
           ctNew.setCtnhapkhoDongia(ctNew.getCtnhapkhoDongiaban().doubleValue());
         }
         if (ctNew.getCtnhapkhoThutu() != null)
         {
           logger.info("-----cap nhat ct nhap kho: " + ctNew.getCtnhapkhoThutu());
           this.listCtNhapKho.set(ctNew.getCtnhapkhoThutu().intValue(), ctNew);
         }
         else
         {
           logger.info("-----them moi ct nhap kho");
           ctNew.setCtnhapkhoThutu(Integer.valueOf(this.listCtNhapKho.size()));
           this.listCtNhapKho.add(ctNew);
         }
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
       catch (Exception e)
       {
         e.printStackTrace();
       }
       tinhTien();
       this.count = Integer.valueOf(this.listCtNhapKho.size());
       this.cnk = new CtNhapKho();

       this.ngayHD = "";
       this.thangHD = "";
       this.namHD = "";
       this.giacuoi = null;
       this.giadauthau = null;
     }
   }

   public Double getTonkhoHT()
   {
     return this.tonkhoHT;
   }

   public void setTonkhoHT(Double tonkhoHT)
   {
     this.tonkhoHT = tonkhoHT;
   }

   public void onblurTonLoThuocAction()
   {
     TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
     if ((this.cnk != null) && (this.cnk.getDmthuocMaso(true).getDmthuocMa() != null) && (this.cnk.getCtnhapkhoLo() != null))
     {
       TonKho tkHT = tkDel.getTonKhoHienTai(this.cnk.getDmthuocMaso(true).getDmthuocMaso(), this.cnk.getCtnhapkhoLo(), this.cnk.getCtnhapkhoDongiaban(), this.khoa.getDmkhoaMaso());
       if (tkHT != null) {
         this.tonkhoHT = tkHT.getTonkhoTon();
       } else {
         this.tonkhoHT = Double.valueOf(0.0D);
       }
     }
   }

   public void end()
   {
     logger.info("-----end()-----");


     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     if (this.ngayLap != "") {
       try
       {
         Date dNgayHoaDon = df.parse(this.ngayLap);
         this.pnk.setPhieunhapkhoNgayhoadon(dNgayHoaDon);
       }
       catch (ParseException e1)
       {
         e1.printStackTrace();
       }
     }
     PhieuNhapKhoDelegate pnkDelegate1 = PhieuNhapKhoDelegate.getInstance();
     if (pnkDelegate1.checkExisted(this.pnk.getPhieunhapkhoSohoadon(), this.pnk.getPhieunhapkhoNgayhoadon()))
     {
       System.out.println("/////////-----------HOA don va ngya da ton tai*********");FacesMessages.instance().add(IConstantsRes.DUPLICATE_HOADON, new Object[0]);
       return;
     }
     System.out.println("/////////-----------HOA don va ngay HOP LE*********");
     if (this.listCtNhapKho.size() > 0)
     {
       if (!this.isUpdate.booleanValue())
       {
         if (this.ngayHt != "") {
           try
           {
             Date dNgayCn = df.parse(this.ngayHt);
             this.pnk.setPhieunhapkhoNgaygiocn(new Date());
           }
           catch (ParseException e1)
           {
             e1.printStackTrace();
           }
         }
         PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
         removeInfo();
         double tt = 0.0D;
         for (int i = 0; i < this.listCtNhapKho.size(); i++)
         {
           CtNhapKho ct = (CtNhapKho)this.listCtNhapKho.get(i);
           tt += ct.getCtnhapkhoSoluong().doubleValue() * ct.getCtnhapkhoDongia();
         }
         double thue = tt * this.pnk.getPhieunhapkhoMucthue().doubleValue() / 100.0D;
         this.pnk.setPhieunhapkhoThanhtien(Double.valueOf(tt));
         this.pnk.setPhieunhapkhoThue(Double.valueOf(thue));
         try
         {
           this.maPhieu = pnkDelegate.createPhieuNhap(this.pnk, this.listCtNhapKho, this.khoa.getDmkhoaMaso());
           if (!this.maPhieu.equals(""))
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_THANHCONG, new Object[] { this.maPhieu });
             this.isUpdate = Boolean.valueOf(true);
           }
           else
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_THATBAI, new Object[] { this.maPhieu });
           }
         }
         catch (Exception e)
         {
           e.printStackTrace();
         }
       }
     }
     else {
       FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_DMT_NULL, new Object[0]);
     }
   }

   public void loadPhieuNhap()
   {
     logger.info("-----loadPhieuNhap()-----");
     if (!this.maPhieu.equals(""))
     {
       PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
       CtNhapKhoDelegate ctDelegate = CtNhapKhoDelegate.getInstance();
       try
       {
         PhieuNhapKho pnk_temp = pnkDelegate.findByPhieunhapkhoMa(this.maPhieu);
         if (pnk_temp != null)
         {
           this.maPhieu = pnk_temp.getPhieunhapkhoMa();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayHt = df.format(pnk_temp.getPhieunhapkhoNgaygiocn());
           this.ngayLap = df.format(pnk_temp.getPhieunhapkhoNgayhoadon());
           this.listCtNhapKho = ((ArrayList)ctDelegate.findByPhieuNhapKho(this.maPhieu));
           this.count = Integer.valueOf(this.listCtNhapKho.size());
           this.isUpdate = Boolean.valueOf(true);

           this.pnk = pnk_temp;
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, new Object[] { this.maPhieu });
         }
         tinhTien();
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
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

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport()
   {
     this.reportTypeKC = "D01_Phieunhapkhothuocthuong";
     logger.info("Vao Method XuatReport D01_Phieunhapkhothuocthuong");
     String baocao1 = null;
     Date currentDate = new Date();
     if (this.isUpdate.booleanValue()) {
       try
       {
         logger.info("bat dau method XuatReport ");
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieunhapkho_01.jrxml";
         logger.info("da thay file templete 5" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         logger.info("da thay file template ");
         Map<String, Object> params = new HashMap();


         PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
         this.pnk = pnkDelegate.findByPhieunhapkhoMa(this.maPhieu);
         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         logger.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         logger.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         logger.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
         params.put("phieuSo", this.maPhieu.substring(this.maPhieu.length() - 5));
         logger.info("phieuSo: " + params.get("phieuSo"));

         params.put("loaiMa", this.pnk.getDmloaithuocMaso().getDmloaithuocMa());
         logger.info("loaiMa: " + params.get("loaiMa"));

         params.put("tenLoai", this.pnk.getDmloaithuocMaso().getDmloaithuocTen());
         logger.info("tenLoai: " + params.get("tenLoai"));


         params.put("ngayHt", this.ngayHt);
         logger.info("ngayHt: " + params.get("ngayHt"));

         params.put("ngayLap", this.ngayLap);
         logger.info("ngayLap: " + params.get("ngayLap"));

         params.put("tenNhaCungCap", this.pnk.getDtdmnoibanMa().getDmnhacungcapTen());
         logger.info("tenNhaCungCap: " + params.get("tenNhaCungCap"));

         params.put("nvCn", this.pnk.getDtdmnhanvienCn(true).getDtdmnhanvienTen());
         logger.info("nvCn: " + params.get("nvCn"));

         params.put("nguonCt", this.pnk.getDmnctMaso().getDmnctTen());
         logger.info("nguonCt: " + params.get("nguonCt"));

         params.put("chungTu", this.pnk.getPhieunhapkhoChungtu());
         logger.info("chungTu: " + params.get("chungTu"));

         params.put("namNhap", this.namNhap);
         logger.info("namNhap: " + params.get("namNhap"));

         params.put("namNhap", this.namNhap);
         logger.info("namNhap: " + params.get("namNhap"));

         params.put("khoa", this.khoa.getDmkhoaTen());
         logger.info("khoa: " + params.get("khoa"));

         params.put("nguonKp", this.pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiTen());
         logger.info("nguonKp: " + params.get("nguonKp"));

         params.put("maKp", this.pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa());
         logger.info("maKp: " + params.get("maKp"));

         params.put("maPhieu", this.maPhieu);
         logger.info("maPhieu: " + params.get("maPhieu"));

         params.put("mucThue", this.pnk.getPhieunhapkhoMucthue().toString());
         logger.info("mucThue: " + params.get("mucThue"));

         params.put("hoadon", this.pnk.getPhieunhapkhoSohoadon());


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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "D01_Phieunhapkhothuocthuong");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         logger.info("duong dan file xuat report :" + baocao1);
         logger.info("duong dan -------------------- :" + this.reportPathKC);
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
     }
     logger.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "B4111_phieunhapkhochinh";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   private void removeInfo()
   {
     if (this.pnk.getDmloaithuocMaso().getDmloaithuocMaso() == null) {
       this.pnk.setDmloaithuocMaso(null);
     }
     if (this.pnk.getDmnctMaso().getDmnctMaso() == null) {
       this.pnk.setDmnctMaso(null);
     }
     if (this.pnk.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso() == null) {
       this.pnk.setDmnguonkinhphiMaso(null);
     }
     if (this.pnk.getDtdmkhoMaso().getDtdmkhoMaso() == null) {
       this.pnk.setDtdmkhoMaso(null);
     }
     if (this.pnk.getDtdmnhanvienCn().getDtdmnhanvienMaso() == null) {
       this.pnk.setDtdmnhanvienCn(null);
     }
     if (this.pnk.getDtdmnhanvienTieplieu().getDtdmnhanvienMaso() == null) {
       this.pnk.setDtdmnhanvienTieplieu(null);
     }
     if (this.pnk.getDtdmnoibanMa().getDmnhacungcapMaso() == null) {
       this.pnk.setDtdmnoibanMa(null);
     }
     for (CtNhapKho ct : this.listCtNhapKho)
     {
       if (ct.getDmnhasanxuatMaso().getDmnhasanxuatMaso() == null) {
         ct.setDmnhasanxuatMaso(null);
       }
       if (ct.getDmquocgiaMaso().getDmquocgiaMaso() == null) {
         ct.setDmquocgiaMaso(null);
       }
       if (ct.getDmthuocMaso().getDmthuocMaso() == null) {
         ct.setDmthuocMaso(null);
       }
     }
   }

   public TonKho createTonKho(CtNhapKho ct)
   {
     TonKho tk = new TonKho();
     tk.setDmnctMaso(this.pnk.getDmnctMaso());
     tk.setDmnguonkinhphiMaso(this.pnk.getDmnguonkinhphiMaso());
     tk.setDmNhaCungCap(this.pnk.getDtdmnoibanMa());
     tk.setDmnhasanxuatMaso(ct.getDmnhasanxuatMaso());
     tk.setDmquocgiaMaso(ct.getDmquocgiaMaso());
     tk.setDmthuocMaso(ct.getDmthuocMaso());
     tk.setDtdmkhoMaso(this.pnk.getDtdmkhoMaso());
     tk.setDtdmnhanvienCn(this.pnk.getDtdmnhanvienCn());
     tk.setTonkhoDongia(Double.valueOf(ct.getCtnhapkhoDongia()));
     tk.setTonkhoDongiaban(ct.getCtnhapkhoDongiaban());
     tk.setTonkhoHienthi(Boolean.valueOf(true));
     tk.setTonkhoLo(ct.getCtnhapkhoLo());

     tk.setTonkhoNhap(ct.getCtnhapkhoSoluong());
     tk.setTonkhoXuat(null);
     tk.setTonkhoTra(null);
     tk.setTonkhoNgayhandung(ct.getCtnhapkhoNgayhandung());
     tk.setTonkhoThanghandung(ct.getCtnhapkhoThanghandung());
     tk.setTonkhoNamhandung(ct.getCtnhapkhoNamhandung());
     tk.setTonkhoNamnhap(ct.getCtnhapkhoNamnhap());
     tk.setTonkhoSodangky(ct.getCtnhapkhoSodangky());

     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     if (this.ngayHt != "") {
       try
       {
         Date dNgayCn = df.parse(this.ngayHt);
         tk.setTonkhoNgaygiocn(dNgayCn);
       }
       catch (ParseException e1)
       {
         e1.printStackTrace();
       }
     }
     return tk;
   }

   private void tinhTien()
   {
     if (this.pnk == null) {
       return;
     }
     if (this.listCtNhapKho == null) {
       return;
     }
     logger.info("-----tinhTien()-----");
     this.thanhTien = new Double("0");
     for (CtNhapKho ctnk : this.listCtNhapKho)
     {
       Double sl = ctnk.getCtnhapkhoSoluong();
       Double dg = ctnk.getCtnhapkhoDongiaban();
       this.thanhTien = Double.valueOf(this.thanhTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     Double mucThue = this.pnk.getPhieunhapkhoMucthue();
     if (mucThue == null) {
       mucThue = new Double("0");
     }
     this.thueGtgt = Double.valueOf(this.thanhTien.doubleValue() * mucThue.doubleValue() / 100.0D);
     this.thanhTienThue = Double.valueOf(this.thanhTien.doubleValue() + this.thueGtgt.doubleValue());
     logger.info("-----thueGtgt: " + this.thueGtgt);
     logger.info("-----thanhTienThue: " + this.thanhTienThue);
   }

   public void tinhTienThue()
   {
     if (this.pnk == null) {
       return;
     }
     logger.info("-----tinhTienThue()-----");
     Double mucThue = this.pnk.getPhieunhapkhoMucthue();
     if (mucThue == null) {
       mucThue = new Double("0");
     }
     this.thueGtgt = Double.valueOf(this.thanhTien.doubleValue() * mucThue.doubleValue() / 100.0D);
     this.thanhTienThue = Double.valueOf(this.thanhTien.doubleValue() + this.thueGtgt.doubleValue());
     logger.info("-----thueGtgt: " + this.thueGtgt);
     logger.info("-----thanhTienThue: " + this.thanhTienThue);
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public PhieuNhapKho getPnk()
   {
     return this.pnk;
   }

   public void setPnk(PhieuNhapKho pnk)
   {
     this.pnk = pnk;
   }

   public CtNhapKho getCnk()
   {
     return this.cnk;
   }

   public void setCnk(CtNhapKho cnk)
   {
     this.cnk = cnk;
   }

   public ArrayList<CtNhapKho> getListCtNhapKho()
   {
     return this.listCtNhapKho;
   }

   public void setListCtNhapKho(ArrayList<CtNhapKho> listCtNhapKho)
   {
     this.listCtNhapKho = listCtNhapKho;
   }

   public CtNhapKho getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtNhapKho selected)
   {
     this.selected = selected;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getNgayLap()
   {
     return this.ngayLap;
   }

   public void setNgayLap(String ngayLap)
   {
     this.ngayLap = ngayLap;
   }

   public static Logger getLogger()
   {
     return logger;
   }

   public static void setLogger(Logger logger)
   {
     logger = logger;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getThangHD()
   {
     return this.thangHD;
   }

   public void setThangHD(String thangHD)
   {
     this.thangHD = thangHD;
   }

   public String getNamHD()
   {
     return this.namHD;
   }

   public void setNamHD(String namHD)
   {
     this.namHD = namHD;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getNamNhap()
   {
     return this.namNhap;
   }

   public void setNamNhap(String namNhap)
   {
     this.namNhap = namNhap;
   }

   public Boolean getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setIsUpdate(Boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setCount(Integer count)
   {
     this.count = count;
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

   public DmKhoa getKhoa()
   {
     return this.khoa;
   }

   public void setKhoa(DmKhoa khoa)
   {
     this.khoa = khoa;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public Double getThanhTien()
   {
     return this.thanhTien;
   }

   public void setThanhTien(Double thanhTien)
   {
     this.thanhTien = thanhTien;
   }

   public Double getThueGtgt()
   {
     return this.thueGtgt;
   }

   public void setThueGtgt(Double thueGtgt)
   {
     this.thueGtgt = thueGtgt;
   }

   public Double getThanhTienThue()
   {
     return this.thanhTienThue;
   }

   public void setThanhTienThue(Double thanhTienThue)
   {
     this.thanhTienThue = thanhTienThue;
   }

   public String getNgayHD()
   {
     return this.ngayHD;
   }

   public void setNgayHD(String ngayHD)
   {
     this.ngayHD = ngayHD;
   }

   public Double getGiadauthau()
   {
     return this.giadauthau;
   }

   public void setGiadauthau(Double giadauthau)
   {
     this.giadauthau = giadauthau;
   }

   public Double getGiacuoi()
   {
     return this.giacuoi;
   }

   public void setGiacuoi(Double giacuoi)
   {
     this.giacuoi = giacuoi;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieuNhapKhoAction

 * JD-Core Version:    0.7.0.1

 */