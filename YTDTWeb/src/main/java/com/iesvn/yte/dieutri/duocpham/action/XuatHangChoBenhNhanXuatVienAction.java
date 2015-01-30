 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtXuatBhXuatVienDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuXuatBhXuatVienDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import net.sf.jasperreports.engine.JasperPrint;
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
 @Name("B4166_Xuathangchobenhnhan")
 @Synchronized(timeout=6000000L)
 public class XuatHangChoBenhNhanXuatVienAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(XuatHangChoBenhNhanXuatVienAction.class);
   private PhieuXuatBhXuatVien phieuXuatBhXuatVien;
   private BenhNhan benhnhan;
   private Hsba hsba;
   private HsbaBhyt hsbaBhyt;
   @DataModel
   private List<CtXuatBhXuatVien> listCtXuatBhXuatVien;
   @DataModelSelection("listCtXuatBhXuatVien")
   private CtXuatBhXuatVien ctxuatbhSelected;
   private CtXuatBhXuatVien ctxuatbh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   private int index = 0;
   private TonKho[] listTonkho;
   private String nvPhat;
   private Integer nvPhatSo;
   private String hsbaDoituong;
   private String hsbaDoituongMa;
   private String hsbaKcbBhyt;
   private String hsbaChandoan;
   private String nvCapnhat;
   private String pxNgaylap;
   private String bnNamsinh;
   private String bnGtinh;
   private String hsbaTinhBhyt;
   private String hsbaKhoiBhyt;
   private String tntMathuoc;
   private String tntQuocgiaSx;
   private String tntHangSx;
   private String ctxThanhtien;
   private String tkDongiaban;
   private TonKho tonKho;
   private String tonkhoMa;
   private String ctxSoluong;
   private String thieuthuoc = "0";
   private String updateCtXuat;
   private double thanhtienCt = 0.0D;
   private String saveResult;
   private String nosuccess;
   private String nofoundHsba;
   private HsThtoan hstt;
   private List<ThuocNoiTru> listTNT;
   private boolean daThanhToan = true;
   private String reportFinished;
   private String reportFileName;
   private DmKhoa dmKhoXuat = new DmKhoa();

   @Begin(join=true)
   public String init(String loaiKho)
   {
     log.info("---------begin init method-------");
     reset();
     log.info("---------end init method-------");
     this.hstt = new HsThtoan();
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     if ("NT".equals(loaiKho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
       this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if ("KC".equals(loaiKho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if ("BHYT".equals(loaiKho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
       this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if ("TE".equals(loaiKho))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
       this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa"));
     }
     return "QuanLyKhoChinh_XuatKhoBHYT_XuatHangChoBenhNhan";
   }

   @End
   public void endFucntion() {}

   private void reset()
   {
     this.phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
     this.ctxuatbh = new CtXuatBhXuatVien();
     this.hsba = new Hsba();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     this.listCtXuatBhXuatVien = new ArrayList();
     this.pxNgaylap = Utils.getCurrentDate();
     this.updateCtXuat = "0";
     this.nosuccess = "false";
     this.nofoundHsba = "false";
   }

   public void fillCtXuatBh()
   {
     log.info("---Begin fillCtXuatBh() method----");
     String message = "";
     this.ctxuatbh = this.ctxuatbhSelected;
     this.tonKho = new TonKho();
     if (this.tonKho != null)
     {
       this.tonkhoMa = ("" + this.tonKho.getTonkhoMa());
       DmThuoc thuoc = this.tonKho.getDmthuocMaso();
       this.tntMathuoc = thuoc.getDmthuocMa();
       this.tntQuocgiaSx = this.tonKho.getDmquocgiaMaso().getDmquocgiaTen();
       this.tntHangSx = this.tonKho.getDmnhasanxuatMaso().getDmnhasanxuatTen();
       double dongiaBan = this.tonKho.getTonkhoDongiaban().doubleValue();
       this.tkDongiaban = ("" + dongiaBan);
       this.ctxThanhtien = ("" + this.ctxuatbh.getCtxuatbhxvSoluong().doubleValue() * dongiaBan);
       this.updateCtXuat = "1";
     }
     else
     {
       log.info("tonKho IS NULL");
     }
     FacesMessages.instance().add(message, new Object[0]);
     log.info("---End fillCtXuatBh() method----");
   }

   public void loadHsbaAjax()
   {
     log.info("---Begin loadHsbaAjax() ----");
     this.listCtXuatBhXuatVien = new ArrayList();
     this.phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
     this.ctxuatbh = new CtXuatBhXuatVien();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     if ((this.hsba == null) || (this.hsba.getHsbaSovaovien() == null))
     {
       FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_UNAVAILABLE, new Object[] { "" });
       this.nofoundHsba = "true";
       log.info("------ hsba == null || hsba.getHsbaSovaovien() == null ------");
       resetForm();
       return;
     }
     System.out.println("hsba---->" + this.hsba);
     try
     {
       HsbaDelegate hsbaWsPort = HsbaDelegate.getInstance();
       String soVaovien = this.hsba.getHsbaSovaovien();
       if ((soVaovien != null) && (!soVaovien.trim().equals("")))
       {
         this.hsba = hsbaWsPort.find(soVaovien);
         if (this.hsba == null)
         {
           FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_NOTFOUND, new Object[] { soVaovien });
           resetHsba();
           this.nofoundHsba = "true";
           log.info("------Khong tim thay so vao vien---------");
         }
         else if (this.hsba != null)
         {
           HsbaKhoaDelegate hsbaKhoaDel = HsbaKhoaDelegate.getInstance();
           HsbaKhoa hsbaKhoa = hsbaKhoaDel.findBySoVaoVienLastHsbaKhoa(soVaovien);

           HsbaBhytDelegate hsbaBhytDel = HsbaBhytDelegate.getInstance();
           this.hsbaBhyt = hsbaBhytDel.findBySoVaoVienLastHsbaBhyt(this.hsba.getHsbaSovaovien());

           setFormHsba(this.hsba, this.hsbaBhyt);
           log.info("HsbaBenhnhan: " + this.hsba.getBenhnhanMa().getBenhnhanMa());
           List<PhieuXuatBhXuatVien> phieuXuatBhTemp = PhieuXuatBhXuatVienDelegate.getInstance().findBySovaovien_Kho(this.hsba.getHsbaSovaovien(), this.dmKhoXuat.getDmkhoaMaso());
           if (phieuXuatBhTemp.size() > 0)
           {
             this.phieuXuatBhXuatVien.setPhieuxuatbhxvMa(((PhieuXuatBhXuatVien)phieuXuatBhTemp.get(0)).getPhieuxuatbhxvMa());
             loadPhieuXuatBhAjax();
             return;
           }
           if (this.hsba.getDoituongMa().getDmdoituongMa().equals("TP"))
           {
             FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI, new Object[0]);
             resetForm();
             return;
           }
           this.listTNT = ThuocNoiTruDelegate.getInstance().findByHsbaKhoa(hsbaKhoa.getHsbakhoaMaso());
           log.info("Populate list thuoc noi tru ung voi loai xuat vien vao list");
           CtXuatBhXuatVien ctxuatbh_ = null;
           for (ThuocNoiTru obj : this.listTNT)
           {
             obj.setThuocnoitruMaphieu(this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa());
             ctxuatbh_ = new CtXuatBhXuatVien();
             ctxuatbh_.setPhieuxuatbhxvMa(this.phieuXuatBhXuatVien);
             ctxuatbh_.setCtxuatbhxvSoluong(Double.valueOf(String.valueOf(obj.getThuocnoitruSoluong())));
             log.info("***** So luong: " + obj.getThuocnoitruSoluong());
             ctxuatbh_.setCtxuatbhxvMalk(obj.getThuocnoitruMalk());
             ctxuatbh_.setDmthuocMaso(obj.getThuocnoitruMathuoc());
             ctxuatbh_.setDmnctMaso(null);
             ctxuatbh_.setDmnguonkinhphiMaso(null);
             ctxuatbh_.setDmquocgiaMaso(obj.getThuocnoitruQuocgia());
             ctxuatbh_.setDmnhasanxuatMaso(obj.getThuocnoitruHangsx());
             ctxuatbh_.setCtxuatbhxvNamnhap(null);
             ctxuatbh_.setCtxuatbhxvNgayhandung(null);
             ctxuatbh_.setCtxuatbhxvThanghandung(null);
             ctxuatbh_.setCtxuatbhxvNamhandung(null);
             ctxuatbh_.setCtxuatbhxvDongia(obj.getThuocnoitruDongia());
             ctxuatbh_.setCtxuatbhxvNgaygiocn(null);

             this.listCtXuatBhXuatVien.add(ctxuatbh_);
           }
           this.pxNgaylap = Utils.getCurrentDate();
           this.phieuXuatBhXuatVien.setHsba(this.hsba);

           Double soTienThuocBHYT = new Double(0.0D);
           for (CtXuatBhXuatVien ctXuatBh : this.listCtXuatBhXuatVien) {
             soTienThuocBHYT = Double.valueOf(ctXuatBh.getCtxuatbhxvDongia().doubleValue() * ctXuatBh.getCtxuatbhxvSoluong().doubleValue());
           }
           log.info("soTienThuocBHYT:" + soTienThuocBHYT);

           this.phieuXuatBhXuatVien.setPhieuxuatbhxvThanhtien(soTienThuocBHYT);
         }
         else
         {
           resetHsba();
           FacesMessages.instance().add(IConstantsRes.SO_VAO_VIEN_UNAVAILABLE, new Object[] { soVaovien });
           this.nofoundHsba = "true";
           log.info("------So vao vien khong hop le---------");
         }
       }
       else
       {
         resetHsba();
       }
     }
     catch (Exception ex)
     {
       log.info(":( ERROR: " + ex);
       ex.printStackTrace();
     }
     log.info("---End loadTiepdonAjax() method-----");
   }

   public void loadPhieuXuatBhAjax()
   {
     log.info("---Begin loadPhieuXuatBhAjax() method----");
     String message = "";
     try
     {
       this.ctxuatbh = new CtXuatBhXuatVien();
       this.listCtXuatBhXuatVien = new ArrayList();
       this.benhnhan = new BenhNhan();
       SetInforUtil.setInforIfNullForBN(this.benhnhan);
       this.hsba = new Hsba();
       String pxMa = this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa();
       if ((pxMa != null) && (!pxMa.trim().equals("")))
       {
         log.info("Phieu xuat ma khac null");
         PhieuXuatBhXuatVienDelegate pxWS = PhieuXuatBhXuatVienDelegate.getInstance();

         CtXuatBhXuatVienDelegate ctXuatBHDel = CtXuatBhXuatVienDelegate.getInstance();
         this.phieuXuatBhXuatVien = pxWS.find(pxMa);
         if (this.phieuXuatBhXuatVien != null)
         {
           log.info("Tim thay Phieu xuat bh");

           this.hsba = this.phieuXuatBhXuatVien.getHsba();
           HsbaBhytDelegate hsbaBhytDel = HsbaBhytDelegate.getInstance();
           this.hsbaBhyt = hsbaBhytDel.findBySoVaoVienLastHsbaBhyt(this.hsba.getHsbaSovaovien());
           this.benhnhan = this.hsba.getBenhnhanMa();

           SetInforUtil.setInforIfNullForBN(this.benhnhan);

           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date ngaylap = this.phieuXuatBhXuatVien.getPhieuxuatbhxvNgaylap();
           this.pxNgaylap = df.format(ngaylap);

           setFormHsba(this.hsba, this.hsbaBhyt);
           this.listCtXuatBhXuatVien = ctXuatBHDel.findByPhieuxuatBhXuatVienMa(this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa());
         }
         else
         {
           log.info("Ko Tim thay Phieu xuat bh");
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { pxMa });
           resetForm();
         }
       }
       else
       {
         resetForm();
       }
     }
     catch (Exception e)
     {
       log.info(":( ERROR : " + e);
     }
     log.info("---End loadPhieuXuatBhAjax() method-----");
   }

   private void setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt)
   {
     log.info("begin setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt) method");
     this.benhnhan = hsba.getBenhnhanMa();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     log.info("benhnhan: " + this.benhnhan);
     if (this.benhnhan != null)
     {
       this.bnNamsinh = this.benhnhan.getBenhnhanNamsinh();
       if (this.benhnhan.getDmgtMaso() != null) {
         if ("1".equals(this.benhnhan.getDmgtMaso().getDmgtMa())) {
           this.bnGtinh = "0";
         } else {
           this.bnGtinh = "1";
         }
       }
     }
     DmTinh tinhBhyt = hsbaBhyt.getHsbabhytTinhbh();
     if (tinhBhyt != null)
     {
       this.hsbaTinhBhyt = ("" + tinhBhyt.getDmtinhMaso());
       tinhBhyt = null;
     }
     DtDmKhoiBhyt khoiBhyt = hsbaBhyt.getHsbabhytKhoibh();
     if (khoiBhyt != null)
     {
       this.hsbaKhoiBhyt = khoiBhyt.getDtdmkhoibhytMa();
       khoiBhyt = null;
     }
     DmBenhVien kcbBhyt = hsbaBhyt.getHsbabhytMakcb();
     if (kcbBhyt != null)
     {
       this.hsbaKcbBhyt = kcbBhyt.getDmbenhvienMa();
       kcbBhyt = null;
     }
     DmDoiTuong dtuong = hsba.getDoituongMa();
     if (dtuong != null)
     {
       this.hsbaDoituong = dtuong.getDmdoituongTen();
       this.hsbaDoituongMa = dtuong.getDmdoituongMa();
       dtuong = null;
     }
     DtDmNhanVien dmNvPhat = this.phieuXuatBhXuatVien.getDtdmnhanvienPhat();
     if (dmNvPhat != null)
     {
       this.nvPhat = dmNvPhat.getDtdmnhanvienMa();
       dmNvPhat = null;
     }
     DmBenhIcd chandoan = hsba.getHsbaMachdravien();
     if (chandoan != null) {
       this.hsbaChandoan = chandoan.getDmbenhicdTen();
     }
     log.info("End setFormHsba(Hsba hsba, HsbaBhyt hsbaBhyt) method");
   }

   public void resetForm()
   {
     log.info("---Begin resetForm() method----");
     this.phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
     this.ctxuatbh = new CtXuatBhXuatVien();
     this.tonKho = null;
     this.thanhtienCt = 0.0D;
     this.updateCtXuat = "0";
     this.thieuthuoc = "0";
     this.tonkhoMa = "";
     this.tntMathuoc = "";
     this.ctxSoluong = "";
     this.hsba = new Hsba();
     this.benhnhan = new BenhNhan();
     this.hsbaBhyt = new HsbaBhyt();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     this.listCtXuatBhXuatVien = new ArrayList();

     this.pxNgaylap = Utils.getCurrentDate();
     this.nvPhat = "";
     this.hsbaDoituong = "";
     this.hsbaDoituongMa = "";
     this.hsbaKcbBhyt = "";
     this.hsbaChandoan = "";
     this.nvCapnhat = "";
     this.bnNamsinh = "";
     this.bnGtinh = "";
     this.hsbaTinhBhyt = "";
     this.hsbaKhoiBhyt = "";
     this.tntMathuoc = "";
     this.tntQuocgiaSx = "";
     this.tntHangSx = "";
     this.ctxThanhtien = "";
     this.tkDongiaban = "";
     this.thieuthuoc = "0";
     this.updateCtXuat = "0";
     this.hstt = new HsThtoan();
     this.reportFileName = "";
     this.reportFinished = "";

     log.info("---End resetForm() method-----");
   }

   public void resetHsba()
   {
     log.info("---End resetHsba() method-----");
     String pxMa = this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa();
     if ((pxMa != null) && (!pxMa.equals("")))
     {
       this.ctxuatbh = new CtXuatBhXuatVien();
       this.updateCtXuat = "0";
       this.thanhtienCt = 0.0D;
       this.thieuthuoc = "0";
       this.tonKho = null;
       this.tntMathuoc = "";
       this.tonkhoMa = "";
       this.ctxSoluong = "";
       this.hsba = new Hsba();
       this.benhnhan = new BenhNhan();
       this.hsbaBhyt = new HsbaBhyt();
       SetInforUtil.setInforIfNullForBN(this.benhnhan);
       this.listCtXuatBhXuatVien = new ArrayList();
       this.hsbaDoituong = "";
       this.hsbaDoituongMa = "";
       this.hsbaKcbBhyt = "";
       this.hsbaChandoan = "";
       this.bnNamsinh = "";
       this.bnGtinh = "";
       this.hsbaTinhBhyt = "";
       this.hsbaKhoiBhyt = "";
       this.tntMathuoc = "";
       this.tntQuocgiaSx = "";
       this.tntHangSx = "";
       this.ctxThanhtien = "";
       this.tkDongiaban = "";
       this.reportFileName = "";
       this.reportFinished = "";
     }
     else
     {
       resetForm();
     }
     log.info("---End resetHsba() method-----");
   }

   public void ghiNhan()
   {
     log.info("ghiNhan");
     List<PhieuXuatBhXuatVien> phieuXuatBhTemp = PhieuXuatBhXuatVienDelegate.getInstance().findBySovaovien_Kho(this.hsba.getHsbaSovaovien(), this.dmKhoXuat.getDmkhoaMaso());
     if (phieuXuatBhTemp.size() > 0)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.DA_XUAT_THUOC_NT, new Object[] { this.benhnhan.getBenhnhanHoten() });
       return;
     }
     if ((this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa() != null) && (!this.phieuXuatBhXuatVien.getPhieuxuatbhxvMa().equals("")))
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.DA_XUAT_THUOC_NT, new Object[] { this.benhnhan.getBenhnhanHoten() });
       return;
     }
     HsThtoanDelegate hsttDel = HsThtoanDelegate.getInstance();
     HsThtoan hstt = hsttDel.findBySovaovien(this.hsba.getHsbaSovaovien());
     if (hstt == null)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.HSBA_NOTFOUND, new Object[0]);
       return;
     }
     Boolean datt = hstt.getHsthtoanDatt();
     Double tienBNTra = hstt.getHsthtoanBntra();
     System.out.println("datt: " + datt);
     System.out.println("tienBNTra: " + tienBNTra);
     if ((tienBNTra.doubleValue() > 0.0D) && (
       (datt == null) || ((datt != null) && (!datt.booleanValue()))))
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.CHUA_THANH_TOAN_VP, new Object[0]);
       return;
     }
     if ((datt != null) && (!datt.booleanValue()))
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.CHUA_THANH_TOAN_VP, new Object[0]);
       return;
     }
     try
     {
       HsbaDelegate hsbaWsPort = HsbaDelegate.getInstance();
       String soVaovien = this.hsba.getHsbaSovaovien();
       Hsba hsba_tmp = hsbaWsPort.find(soVaovien);
       if (hsba_tmp == null)
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.HSBA_NOTFOUND, new Object[0]);
         return;
       }
       String rs = "";
       if ((this.listTNT != null) && (this.listCtXuatBhXuatVien != null))
       {
         this.phieuXuatBhXuatVien.setHsba(hsba_tmp);
         DtDmNhanVien dmNvPhat = new DtDmNhanVien();
         dmNvPhat.setDtdmnhanvienMa(this.nvPhat);
         dmNvPhat.setDtdmnhanvienMaso(this.nvPhatSo);
         this.phieuXuatBhXuatVien.setDtdmnhanvienPhat(dmNvPhat);
         DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
         DtDmNhanVien nhanViencn = nvDelegate.findByNd(this.identity.getUsername());
         this.phieuXuatBhXuatVien.setDtdmnhanvienCn(nhanViencn);
         this.phieuXuatBhXuatVien.setPhieuxuatbhxvNgaygiocn(new Date());
         this.phieuXuatBhXuatVien.setPhieuxuatbhxvNgaygiophat(new Date());
         this.phieuXuatBhXuatVien.setPhieuxuatbhxvNgaylap(new SimpleDateFormat("dd/MM/yyyy").parse(this.pxNgaylap));

         this.phieuXuatBhXuatVien.setDmkhoaMaso(this.dmKhoXuat);
         System.out.println("Dang ghi nhan");
         rs = PhieuXuatBhXuatVienDelegate.getInstance().createByThuocNoiTru(this.dmKhoXuat.getDmkhoaMaso().intValue(), this.phieuXuatBhXuatVien, this.listTNT, this.listCtXuatBhXuatVien);
       }
       if ((rs == null) || (rs.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
       else if (rs.equals("SLTHET"))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_SLKHONGDUXUAT, new Object[0]);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       }
     }
     catch (NumberFormatException e)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
     }
     catch (ParseException e)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
     }
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public List<ThuocNoiTru> getListTNT()
   {
     return this.listTNT;
   }

   public void setListTPK(List<ThuocNoiTru> listTNT)
   {
     this.listTNT = listTNT;
   }

   public String getTonkhoMa()
   {
     return this.tonkhoMa;
   }

   public void setTonkhoMa(String tonkhoMa)
   {
     this.tonkhoMa = tonkhoMa;
   }

   public String getThieuthuoc()
   {
     return this.thieuthuoc;
   }

   public void setThieuthuoc(String thieuthuoc)
   {
     this.thieuthuoc = thieuthuoc;
   }

   public double getThanhtienCt()
   {
     return this.thanhtienCt;
   }

   public void setThanhtienCt(double thanhtienCt)
   {
     this.thanhtienCt = thanhtienCt;
   }

   public String getSaveResult()
   {
     return this.saveResult;
   }

   public void setSaveResult(String saveResult)
   {
     this.saveResult = saveResult;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public String getReportFileName()
   {
     return this.reportFileName;
   }

   public void setReportFileName(String reportFileName)
   {
     this.reportFileName = reportFileName;
   }

   public Integer getNvPhatSo()
   {
     return this.nvPhatSo;
   }

   public void setNvPhatSo(Integer nvPhatSo)
   {
     this.nvPhatSo = nvPhatSo;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getHsbaDoituongMa()
   {
     return this.hsbaDoituongMa;
   }

   public void setHsbaDoituongMa(String hsbaDoituongMa)
   {
     this.hsbaDoituongMa = hsbaDoituongMa;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public HsThtoan getHstt()
   {
     return this.hstt;
   }

   public void setHstt(HsThtoan hstt)
   {
     this.hstt = hstt;
   }

   public String getNofoundHsba()
   {
     return this.nofoundHsba;
   }

   public void setNofoundHsba(String nofoundHsba)
   {
     this.nofoundHsba = nofoundHsba;
   }

   public PhieuXuatBhXuatVien getPhieuxuatbhXuatVien()
   {
     return this.phieuXuatBhXuatVien;
   }

   public PhieuXuatBhXuatVien getPhieuxuatbhXuatVien(boolean created)
   {
     if (created) {
       this.phieuXuatBhXuatVien = new PhieuXuatBhXuatVien();
     }
     return this.phieuXuatBhXuatVien;
   }

   public void setPhieuxuatbhXuatVien(PhieuXuatBhXuatVien phieuXuatBhXuatVien)
   {
     this.phieuXuatBhXuatVien = phieuXuatBhXuatVien;
   }

   public BenhNhan getBenhnhan()
   {
     return this.benhnhan;
   }

   public void setBenhnhan(BenhNhan benhnhan)
   {
     this.benhnhan = benhnhan;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public String getBnNamsinh()
   {
     return this.bnNamsinh;
   }

   public void setBnNamsinh(String bnNamsinh)
   {
     this.bnNamsinh = bnNamsinh;
   }

   public String getBnGtinh()
   {
     return this.bnGtinh;
   }

   public void setBnGtinh(String bnGtinh)
   {
     this.bnGtinh = bnGtinh;
   }

   public String getNvPhat()
   {
     return this.nvPhat;
   }

   public void setNvPhat(String nvPhat)
   {
     this.nvPhat = nvPhat;
   }

   public String getNvCapnhat()
   {
     return this.nvCapnhat;
   }

   public void setNvCapnhat(String nvCapnhat)
   {
     this.nvCapnhat = nvCapnhat;
   }

   public String getHsbaDoituong()
   {
     return this.hsbaDoituong;
   }

   public void setHsbaDoituong(String hsbaDoituong)
   {
     this.hsbaDoituong = hsbaDoituong;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public String getHsbaKcbBhyt()
   {
     return this.hsbaKcbBhyt;
   }

   public void setHsbaKcbBhyt(String hsbaKcbBhyt)
   {
     this.hsbaKcbBhyt = hsbaKcbBhyt;
   }

   public String getHsbaChandoan()
   {
     return this.hsbaChandoan;
   }

   public void setHsbaChandoan(String hsbaChandoan)
   {
     this.hsbaChandoan = hsbaChandoan;
   }

   public String getPxNgaylap()
   {
     return this.pxNgaylap;
   }

   public void setPxNgaylap(String pxNgaylap)
   {
     this.pxNgaylap = pxNgaylap;
   }

   public String getHsbaTinhBhyt()
   {
     return this.hsbaTinhBhyt;
   }

   public void setHsbaTinhBhyt(String hsbaTinhBhyt)
   {
     this.hsbaTinhBhyt = hsbaTinhBhyt;
   }

   public String getHsbaKhoiBhyt()
   {
     return this.hsbaKhoiBhyt;
   }

   public void setHsbaKhoiBhyt(String hsbaKhoiBhyt)
   {
     this.hsbaKhoiBhyt = hsbaKhoiBhyt;
   }

   public List<CtXuatBhXuatVien> getListCtXuatBhXuatVien()
   {
     return this.listCtXuatBhXuatVien;
   }

   public void setListCtXuatBhXuatVien(List<CtXuatBhXuatVien> listCtXuatBhXuatVien)
   {
     this.listCtXuatBhXuatVien = listCtXuatBhXuatVien;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XuatHangChoBenhNhanXuatVienAction

 * JD-Core Version:    0.7.0.1

 */