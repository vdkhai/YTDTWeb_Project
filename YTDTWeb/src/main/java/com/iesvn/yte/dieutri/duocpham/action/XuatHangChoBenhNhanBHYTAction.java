 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.CtXuatBhDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuXuatBhDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.CtXuatBh;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.PhieuXuatBh;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.rmi.RemoteException;
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
 import java.util.StringTokenizer;
 import javax.xml.rpc.ServiceException;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
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
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4311_Xuathangchobenhnhan")
 @Synchronized(timeout=6000000L)
 public class XuatHangChoBenhNhanBHYTAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(XuatHangChoBenhNhanBHYTAction.class);
   private PhieuXuatBh phieuxuatbh;
   private BenhNhan benhnhan;
   private TiepDon tiepdon;
   @DataModel
   private List<CtXuatBh> listCtXuatBh;
   @DataModelSelection("listCtXuatBh")
   private CtXuatBh ctxuatbhSelected;
   private CtXuatBh ctxuatbh;
   @In(required=false)
   @Out(required=false)
   private String goToCLSPhongKham;
   @DataModel
   private ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT;
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
   private int index = 0;
   private TonKho[] listTonkho;
   private String nvBacsi;
   private String nvPhat;
   private Integer nvPhatSo;
   private String tdBankham;
   private String tdDoituong;
   private String tdDoituongMa;
   private String tdKcbBhyt;
   private String tdChandoan;
   private String nvCapnhat;
   private String pxNgaylap;
   private String bnNamsinh;
   private String bnGtinh;
   private String tdTinhBhyt;
   private String tdKhoiBhyt;
   private String pxPhantramBenhnhan;
   private String pxClsBobot;
   private String tkMathuoc;
   private String tkQuocgiaSx;
   private String tkHangSx;
   private String ctxThanhtien;
   private String tkDongiaban;
   private String pxMiengiam;
   private String pxThatthu;
   private TonKho tonKho;
   private String tonkhoMa;
   private String ctxSoluong;
   private String thieuthuoc = "0";
   private String updateCtXuat;
   private double thanhtienCt = 0.0D;
   private String saveResult;
   private String sNoiDungThu;
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Double cls;
   private Double thuoc;
   private DmKhoa dmKhoXuat = new DmKhoa();
   private List<ThuocPhongKham> listCtTPK_temp = null;
   private String reportFinished;
   private String reportFileName;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;

   public String clsphongkham()
     throws ServiceException, RemoteException
   {
     if ((this.benhnhan.getBenhnhanHoten() == null) || (this.benhnhan.getBenhnhanHoten().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);

       return "";
     }
     this.maTiepDonOut = this.tiepdon.getTiepdonMa();

     this.goToCLSPhongKham = null;

     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   @Out(required=false)
   private String isReport = "false";
   private String nosuccess;
   private String nofoundTD;
   private HsThtoank hsttk;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String initB121_3_Xutrithuoctaibankham;
   @In(required=false)
   @Out(required=false)
   private String loaiToaThuocThamKhamVaXuTri;
   @In(required=false)
   @Out(required=false)
   private String returnToXuatHangChoBNBHYT;
   @In(required=false)
   @Out(required=false)
   private String goToThuocBHYT;
   private List<ThuocPhongKham> listTPK;

   public String getNofoundTD()
   {
     return this.nofoundTD;
   }

   public void setNofoundTD(String nofoundTD)
   {
     this.nofoundTD = nofoundTD;
   }

   private void reset()
   {
     this.phieuxuatbh = new PhieuXuatBh();
     this.ctxuatbh = new CtXuatBh();
     this.tiepdon = new TiepDon();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     this.listCtXuatBh = new ArrayList();
     this.pxNgaylap = Utils.getCurrentDate();
     this.updateCtXuat = "0";
     this.pxPhantramBenhnhan = "1";
     this.sNoiDungThu = "";

     this.nosuccess = "false";
     this.nofoundTD = "false";
     this.listCLS_XuatHangChoBNBHYT = new ArrayList();
   }

   public String getGoToCLSPhongKham()
   {
     return this.goToCLSPhongKham;
   }

   public void setGoToCLSPhongKham(String goToCLSPhongKham)
   {
     this.goToCLSPhongKham = goToCLSPhongKham;
   }

   public String getReportPathKC()
   {
     return this.reportPathKC;
   }

   public void setReportPathKC(String reportPathKC)
   {
     this.reportPathKC = reportPathKC;
   }

   public String getReportTypeKC()
   {
     return this.reportTypeKC;
   }

   public void setReportTypeKC(String reportTypeKC)
   {
     this.reportTypeKC = reportTypeKC;
   }

   public JasperPrint getJasperPrintKC()
   {
     return this.jasperPrintKC;
   }

   public void setJasperPrintKC(JasperPrint jasperPrintKC)
   {
     this.jasperPrintKC = jasperPrintKC;
   }

   public String getSNoiDungThu()
   {
     return this.sNoiDungThu;
   }

   public void setSNoiDungThu(String noiDungThu)
   {
     this.sNoiDungThu = noiDungThu;
   }

   public int getPermiengiam()
   {
     return this.permiengiam;
   }

   public void setPermiengiam(int permiengiam)
   {
     this.permiengiam = permiengiam;
   }

   public Double getMiengiam()
   {
     return this.miengiam;
   }

   public void setMiengiam(Double miengiam)
   {
     this.miengiam = miengiam;
   }

   public Double getThatthu()
   {
     return this.thatthu;
   }

   public void setThatthu(Double thatthu)
   {
     this.thatthu = thatthu;
   }

   public int getPerbhytchi()
   {
     return this.perbhytchi;
   }

   public void setPerbhytchi(int perbhytchi)
   {
     this.perbhytchi = perbhytchi;
   }

   public Double getBhytchi()
   {
     return this.bhytchi;
   }

   public void setBhytchi(Double bhytchi)
   {
     this.bhytchi = bhytchi;
   }

   public Double getThanhtien1()
   {
     return this.thanhtien1;
   }

   public void setThanhtien1(Double thanhtien1)
   {
     this.thanhtien1 = thanhtien1;
   }

   public int getPerbntra()
   {
     return this.perbntra;
   }

   public void setPerbntra(int perbntra)
   {
     this.perbntra = perbntra;
   }

   public Double getBntra()
   {
     return this.bntra;
   }

   public void setBntra(Double bntra)
   {
     this.bntra = bntra;
   }

   public Double getCls()
   {
     return this.cls;
   }

   public void setCls(Double cls)
   {
     this.cls = cls;
   }

   public Double getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(Double thuoc)
   {
     this.thuoc = thuoc;
   }

   public List<ThuocPhongKham> getListCtTPK_temp()
   {
     return this.listCtTPK_temp;
   }

   public void setListCtTPK_temp(List<ThuocPhongKham> listCtTPK_temp)
   {
     this.listCtTPK_temp = listCtTPK_temp;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getGoToThuocBHYT()
   {
     return this.goToThuocBHYT;
   }

   public void setGoToThuocBHYT(String goToThuocBHYT)
   {
     this.goToThuocBHYT = goToThuocBHYT;
   }

   public boolean isDaThanhToan()
   {
     return this.daThanhToan;
   }

   public void setDaThanhToan(boolean daThanhToan)
   {
     this.daThanhToan = daThanhToan;
   }

   public double getGioiHanTyLe()
   {
     return this.gioiHanTyLe;
   }

   public void setGioiHanTyLe(double gioiHanTyLe)
   {
     this.gioiHanTyLe = gioiHanTyLe;
   }

   public double getTylebhyt()
   {
     return this.tylebhyt;
   }

   public void setTylebhyt(double tylebhyt)
   {
     this.tylebhyt = tylebhyt;
   }

   public double getTylebhyt2()
   {
     return this.tylebhyt2;
   }

   public void setTylebhyt2(double tylebhyt2)
   {
     this.tylebhyt2 = tylebhyt2;
   }

   public List<ClsKham> getClslist()
   {
     return this.clslist;
   }

   public void setClslist(List<ClsKham> clslist)
   {
     this.clslist = clslist;
   }

   @Begin(join=true)
   public String init(String thuvienphi)
   {
     log.info("---------begin init method-------");
     reset();
     log.info("---------end init method-------");
     this.hsttk = new HsThtoank();
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     if (!"thuvienphi".equals(thuvienphi)) {
       if ("TE".equals(thuvienphi))
       {
         this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
         this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa"));
       }
       else if ("BHYT".equals(thuvienphi))
       {
         this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
         this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa"));
       }
       else if ("KC".equals(thuvienphi))
       {
         this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
         this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
       }
       else if ("NT".equals(thuvienphi))
       {
         this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
         this.dmKhoXuat = ((DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa"));
       }
     }
     return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
   }

   @End
   public void endFucntion() {}

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
       this.tkMathuoc = thuoc.getDmthuocMa();
       this.tkQuocgiaSx = this.tonKho.getDmquocgiaMaso().getDmquocgiaTen();
       this.tkHangSx = this.tonKho.getDmnhasanxuatMaso().getDmnhasanxuatTen();
       double dongiaBan = this.tonKho.getTonkhoDongiaban().doubleValue();
       this.tkDongiaban = ("" + dongiaBan);

       this.ctxThanhtien = ("" + this.ctxuatbh.getCtxuatbhSoluong().doubleValue() * dongiaBan);
       this.updateCtXuat = "1";
     }
     else
     {
       log.info("tonKho IS NULL");
     }
     FacesMessages.instance().add(message, new Object[0]);
     log.info("---End fillCtXuatBh() method----");
   }

   public void createCtXuatBh()
   {
     log.info("---Begin createCtXuatBh() method----");
     String message = "";
     try
     {
       TonKhoDelegate tkPort = TonKhoDelegate.getInstance();
       if (this.updateCtXuat.equals("1"))
       {
         log.info("call updateCtXuatbh method");
         boolean isDuplicate = checkIsDuplicateTonkho(Integer.valueOf(Integer.parseInt(this.tonkhoMa)), this.ctxuatbh.getCtxuatbhThutu().intValue(), "update");
         if (isDuplicate)
         {
           log.info("****update danh muc thuoc da duoc chon****");
           message = IConstantsRes.THUOC_EXIST;
           resetCtXuatBh();
         }
         else
         {
           updateCtXuatBh();
         }
       }
       else if ((this.tonkhoMa != null) && (!this.tonkhoMa.trim().equals("")))
       {
         log.info("create ctxuatbh");
         boolean isDuplicate = checkIsDuplicateTonkho(Integer.valueOf(Integer.parseInt(this.tonkhoMa)), 0, "create");
         if (isDuplicate)
         {
           message = IConstantsRes.THUOC_EXIST;
           log.info("create danh muc thuoc da duoc chon");
           resetCtXuatBh();
         }
         else
         {
           this.tonKho = tkPort.find(Integer.valueOf(Integer.parseInt(this.tonkhoMa)));
           if (this.tonKho != null)
           {
             Double tkTon = this.tonKho.getTonkhoTon();
             if (tkTon == null) {
               tkTon = Double.valueOf(0.0D);
             }
             if (this.ctxuatbh.getCtxuatbhSoluong().doubleValue() > tkTon.doubleValue())
             {
               message = IConstantsRes.TK_TON_NOTENOUGH + " " + this.tonKho.getDmthuocMaso().getDmthuocTen();
               this.thieuthuoc = "1";
             }
             else
             {
               int size = this.listCtXuatBh.size();
               this.ctxuatbh.setCtxuatbhMa(Integer.valueOf(0));

               this.ctxuatbh.setCtxuatbhThutu(Integer.valueOf(size + 1));
               this.listCtXuatBh.add(this.ctxuatbh);

               resetCtXuatBh();
             }
           }
           else
           {
             log.info("tonKho IS NULL");

             resetCtXuatBh();
           }
         }
       }
       else
       {
         log.info("tonkhoMa IS NULL");
         resetCtXuatBh();
       }
       FacesMessages.instance().add(message, new Object[0]);
       log.info("---End createCtXuatBh() method----");
     }
     catch (Exception e)
     {
       this.thieuthuoc = "0";
       log.info(":( ERROR " + e);
     }
   }

   @Factory("goToThuocBHYT")
   public void goToThuocBHYTForm()
     throws Exception
   {
     log.info("begin goToThuocBHYTForm");
     this.returnToXuatHangChoBNBHYT = "done";
     this.goToThuocBHYT = null;
     reset();
     this.tiepdon.setTiepdonMa(this.maTiepDonOut);
     if ((this.tiepdon != null) && (this.tiepdon.getTiepdonMa() != null))
     {
       log.info("loadTiepdonAjax () ");
       loadTiepdonAjax();
     }
     log.info("end goToThuocBHYTForm");
   }

   @Factory("returnToXuatHangChoBNBHYT")
   public void initForReturn()
     throws Exception
   {
     log.info("begin initForReturn");
     this.goToThuocBHYT = "done";
     if ((this.tiepdon != null) && (this.tiepdon.getTiepdonMa() != null))
     {
       log.info("loadTiepdonAjax () ");
       loadTiepdonAjax();
     }
     resetForm();
     log.info("end initForReturn");
   }

   public String nhapPhieuBHYT()
   {
     log.info("*****begin nhapPhieuBHYT()");
     if ((this.phieuxuatbh.getPhieuxuatbhMa() != null) && (!this.phieuxuatbh.getPhieuxuatbhMa().equals(""))) {
       return "";
     }
     this.maTiepDonOut = this.tiepdon.getTiepdonMa();





     this.initB121_3_Xutrithuoctaibankham = null;
     this.loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
     this.returnToXuatHangChoBNBHYT = "1";

     log.info("*****End nhapPhieuBHYT()");
     return "xutrithuoctaibankham";
   }

   private void updateCtXuatBh()
   {
     log.info("---Begin updateCtXuatBh() method----");
     try
     {
       if ((this.tonkhoMa != null) && (!this.tonkhoMa.equals("")) && (this.ctxuatbh.getCtxuatbhSoluong().doubleValue() != 0.0D))
       {
         String message = "";


         CtXuatBhDelegate ctxWS = CtXuatBhDelegate.getInstance();
         TonKhoDelegate tkPort = TonKhoDelegate.getInstance();

         this.tonKho = tkPort.find(Integer.valueOf(Integer.parseInt(this.tonkhoMa)));
         if (this.tonKho != null)
         {
           if (this.ctxuatbh != null)
           {
             double slNew = this.ctxuatbh.getCtxuatbhSoluong().doubleValue();
             if (this.ctxuatbh.getPhieuxuatbhMa() != null)
             {
               Integer ctxMa = this.ctxuatbh.getCtxuatbhMa();

               CtXuatBh ctxOld = null;
               if (ctxMa != null) {
                 ctxOld = ctxWS.find(ctxMa);
               }
               if (ctxOld != null)
               {
                 TonKho tkOld = new TonKho();
                 DmThuoc thuocOld = tkOld.getDmthuocMaso();

                 double tkTonOld = tkOld.getTonkhoTon().doubleValue();
                 double slOld = ctxOld.getCtxuatbhSoluong().doubleValue();
                 if (this.tonKho.getTonkhoMa().equals(tkOld.getTonkhoMa()))
                 {
                   if (tkTonOld + slOld - slNew < 0.0D)
                   {
                     message = "Sá»‘ lÆ°á»£ng tá»“n kho khÃ´ng Ä‘á»§ cho thuá»‘c " + thuocOld.getDmthuocTen();
                     this.thieuthuoc = "1";
                   }
                   else
                   {
                     this.listCtXuatBh.remove(this.ctxuatbh.getCtxuatbhThutu().intValue() - 1);
                     this.listCtXuatBh.add(this.ctxuatbh.getCtxuatbhThutu().intValue() - 1, this.ctxuatbh);
                     resetCtXuatBh();
                   }
                 }
                 else {
                   capnhatCtXuatBhMoi(this.tonKho, slNew, message);
                 }
               }
               else
               {
                 capnhatCtXuatBhMoi(this.tonKho, slNew, message);
               }
             }
             else
             {
               capnhatCtXuatBhMoi(this.tonKho, slNew, message);
             }
           }
           else
           {
             log.info("ctxuatbh IS NULL");
           }
         }
         else {
           log.info("Khong tim thay ton kho ung voi danh muc thuoc");
         }
         FacesMessages.instance().add(message, new Object[0]);
       }
     }
     catch (Exception e)
     {
       log.info(":( ERROR: " + e);
     }
     log.info("---End updateCtXuatBh() method----");
   }

   public void ghiNhan()
   {
     log.info("ghiNhan");
     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     this.hsttk = hsttkDelegate.findBytiepdonMa(this.tiepdon.getTiepdonMa());
     Double thanhtoan = Double.valueOf(0.0D);
     if (this.hsttk == null)
     {
       this.hsttk = new HsThtoank();
       this.hsttk.setTiepdonMa(this.tiepdon);
       tinhToanChoHSTKKham(this.hsttk);
       thanhtoan = this.hsttk.getHsthtoankThtoan();
       if (thanhtoan.doubleValue() == 0.0D)
       {
         this.hsttk.setHsthtoankDatt(Boolean.valueOf(true));
         this.hsttk.setHsthtoankNgaygiott(new Date());
         hsttkDelegate.capNhatTTHsttk(this.hsttk, this.clslist, this.listCtTPK_temp, false);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi, new Object[0]);
         resetTiepDon();
         return;
       }
     }
     List<PhieuXuatBh> phieuXuatBhTemp = PhieuXuatBhDelegate.getInstance().findByTiepDonMa_Kho(this.tiepdon.getTiepdonMa(), this.dmKhoXuat.getDmkhoaMaso());
     if (phieuXuatBhTemp.size() > 0)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_DAXUAT, new Object[0]);
       return;
     }
     if ((this.phieuxuatbh.getPhieuxuatbhMa() != null) && (!this.phieuxuatbh.getPhieuxuatbhMa().equals(""))) {
       return;
     }
     try
     {
       ThamKham tk_tmp = ThamKhamDelegate.getInstance().findByMaTiepDon(this.tiepdon.getTiepdonMa());
       if (tk_tmp == null)
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.XUATHANG_BENHNHAN_BHYT_THATBAI, new Object[0]);
         return;
       }
       log.info("daThanhToan:" + this.daThanhToan);

       String rs = "";
       if ((this.listTPK != null) && (this.listCtXuatBh != null))
       {
         this.phieuxuatbh.setTiepdonMa(this.tiepdon);
         DtDmNhanVien dmNvPhat = new DtDmNhanVien();
         dmNvPhat.setDtdmnhanvienMa(this.nvPhat);
         dmNvPhat.setDtdmnhanvienMaso(this.nvPhatSo);
         this.phieuxuatbh.setDtdmnhanvienPhat(dmNvPhat);

         DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
         DtDmNhanVien nhanViencn = nvDelegate.findByNd(this.identity.getUsername());
         this.phieuxuatbh.setDtdmnhanvienCn(nhanViencn);

         this.phieuxuatbh.setDtdmnhanvienBacsi(this.tiepdon.getTiepdonBacsi());
         Date dateCrr = new Date();
         this.phieuxuatbh.setPhieuxuatbhNgaygiocn(dateCrr);
         this.phieuxuatbh.setPhieuxuatbhNgaygiophat(dateCrr);
         this.phieuxuatbh.setPhieuxuatbhNgaylap(new SimpleDateFormat("dd/MM/yyyy").parse(this.pxNgaylap));
         this.phieuxuatbh.setPhieuxuatbhNoidungthu(this.sNoiDungThu);

         this.phieuxuatbh.setDmkhoaMaso(this.dmKhoXuat);
         rs = PhieuXuatBhDelegate.getInstance().createByThuocPhongKham(this.dmKhoXuat.getDmkhoaMaso(), this.phieuxuatbh, this.listTPK, this.listCtXuatBh, IConstantsRes.PRIORITY_TON_LO_THUOC);
         if ((!rs.equals("")) || (!rs.equals("SLTHET")) || (rs != null) || (rs.indexOf("ERROR") == -1))
         {
           this.phieuxuatbh.setPhieuxuatbhMa(rs);

           String tyleBNtra = "" + (100 - this.hsttk.getHsthtoankTylebh().shortValue());
           if ("MP".equals(this.tiepdon.getDoituongMa(true).getDmdoituongMa())) {
             tyleBNtra = "0";
           }
           this.phieuxuatbh.setPhieuxuatbhThanhtienb(this.hsttk.getHsthtoankBntra());
           this.phieuxuatbh.setPhieuxuatbhThatthu(this.hsttk.getHsthtoankThatthu());
         }
       }
       if ((rs == null) || (rs.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
       else if (rs.indexOf("-") > 0)
       {
         String ketqua = rs.substring(rs.indexOf("-") + 1, rs.length());
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATBH_SLKHONGDUXUAT, new Object[] { ketqua.substring(0, ketqua.indexOf(":")), ketqua.substring(ketqua.indexOf(":") + 1, ketqua.length()) });
       }
       else if (rs.indexOf("ERROR") > -1)
       {
         String ketqua = rs;
         this.nosuccess = "true";
         FacesMessages.instance().add(ketqua, new Object[0]);
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

   private boolean daThanhToan = true;
   private double tongChiPhiCanThiet;
   private double bnTra;
   private double gioiHanTyLe;
   private double tylebhyt;
   private double tylebhyt2;

   public void loadTiepdonAjax()
   {
     log.info("---Begin loadTiepdonAjax() ----");
     this.listCtXuatBh = new ArrayList();



     this.phieuxuatbh = new PhieuXuatBh();
     this.ctxuatbh = new CtXuatBh();
     this.listCtXuatBh = new ArrayList();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     if ((this.tiepdon == null) || (this.tiepdon.getTiepdonMa() == null))
     {
       FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE, new Object[] { "" });
       this.nofoundTD = "true";
       log.info("------ tiepdon == null || tiepdon.getTiepdonMa() == null ------");
       resetForm();
       return;
     }
     System.out.println("tiepdon---->" + this.tiepdon);
     try
     {
       TiepDonDelegate tdWsPort = TiepDonDelegate.getInstance();
       String tdMa = this.tiepdon.getTiepdonMa();
       if ((tdMa != null) && (!tdMa.trim().equals("")))
       {
         this.tiepdon = tdWsPort.findByTiepdonMa_Kho(tdMa, this.dmKhoXuat.getDmkhoaMaso());
         if (this.tiepdon == null)
         {
           FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, new Object[] { tdMa });
           resetTiepDon();
           this.nofoundTD = "true";
           log.info("------Khong tim thay ma tiep don---------");
         }
         else if (this.tiepdon != null)
         {
           log.info("tiepdonBenhnhan: " + this.tiepdon.getBenhnhanMa().getBenhnhanMa());
           List<PhieuXuatBh> phieuXuatBhTemp = PhieuXuatBhDelegate.getInstance().findByTiepDonMa_Kho(this.tiepdon.getTiepdonMa(), this.dmKhoXuat.getDmkhoaMaso());
           if (phieuXuatBhTemp.size() > 0)
           {
             this.phieuxuatbh.setPhieuxuatbhMa(((PhieuXuatBh)phieuXuatBhTemp.get(0)).getPhieuxuatbhMa());
             loadPhieuXuatBhAjax();
             return;
           }
           if (this.tiepdon.getDoituongMa().getDmdoituongMa().equals("TP"))
           {
             FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI, new Object[0]);
             resetForm();
             return;
           }
           HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
           this.hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
           Double thanhtoan = Double.valueOf(0.0D);
           if (this.hsttk == null)
           {
             this.hsttk = new HsThtoank();
             this.hsttk.setTiepdonMa(this.tiepdon);
             tinhToanChoHSTKKham(this.hsttk);
             thanhtoan = this.hsttk.getHsthtoankThtoan();
             if (thanhtoan.doubleValue() != 0.0D)
             {
               FacesMessages.instance().add(IConstantsRes.benh_nhan_phai_thu_vien_phi, new Object[0]);
               resetTiepDon();
               return;
             }
           }
           int tiLeBHYT = 0;
           if (this.tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1() != null) {
             tiLeBHYT = this.tiepdon.getKhoibhytMa(true).getDtdmkhoibhytTylebhyt1().shortValue();
           }
           int iPhanTram = 100 - tiLeBHYT;
           if ("BH".equals(this.tiepdon.getDoituongMa(true).getDmdoituongMa()))
           {
             Short tuyen = this.tiepdon.getTiepdonTuyen();



             Integer tuoi = this.tiepdon.getBenhnhanMa(true).getBenhnhanTuoi();
             if (tuoi == null) {
               tuoi = Integer.valueOf(0);
             }
             Short donvituoi = this.tiepdon.getBenhnhanMa(true).getBenhnhanDonvituoi();
             if (donvituoi == null) {
               donvituoi = Short.valueOf((short)0);
             }
             String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
             int iTuoiKoXetTuyen = 200;
             if ((tuoikhongxettuyen != null) && (!tuoikhongxettuyen.equals(""))) {
               iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
             }
             if ((tuoi.intValue() < iTuoiKoXetTuyen) || (donvituoi.shortValue() != 1)) {
               if ((tuyen != null) && ((tuyen.shortValue() == 2) || (tuyen.shortValue() == 3)))
               {
                 boolean bLaDTuutien = false;

                 String dsUuTien = IConstantsRes.DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN;
                 if ((dsUuTien != null) && (!dsUuTien.equals("")))
                 {
                   StringTokenizer sTk = new StringTokenizer(dsUuTien, ",");
                   ArrayList<String> arrayDS = new ArrayList();
                   while (sTk.hasMoreTokens())
                   {
                     String khoi = sTk.nextToken();
                     arrayDS.add(khoi);
                   }
                   DtDmKhoiBhyt khoiBHYT = this.tiepdon.getKhoibhytMa();
                   if (arrayDS.contains(khoiBHYT.getDtdmkhoibhytMa())) {
                     bLaDTuutien = true;
                   }
                 }
                 if (!bLaDTuutien) {
                   if ((this.tiepdon.getTiepdonCoGiayGioiThieu() == null) || ((this.tiepdon.getTiepdonCoGiayGioiThieu() != null) && (!this.tiepdon.getTiepdonCoGiayGioiThieu().booleanValue()))) {
                     iPhanTram = 50;
                   }
                 }
               }
             }
           }
           this.sNoiDungThu = ("Thu " + iPhanTram + IConstantsRes.NOI_DUNG_THU_BHYT);

           this.listTPK = ThuocPhongKhamDelegate.getInstance().findByKhoaKham(this.tiepdon.getTiepdonMa(), Utils.KE_TOA_QUAY_BENH_VIEN, this.dmKhoXuat.getDmkhoaMaso().intValue());
           if (this.tenChuongTrinh.equals(MyMenuYTDTAction.thuVienPhi)) {
             this.listTPK = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.KE_TOA_QUAY_BENH_VIEN);
           }
           if ((this.listTPK != null) && (this.listTPK.size() > 0)) {
             for (ThuocPhongKham tpk : this.listTPK)
             {
               String maphieud = tpk.getThuocphongkhamMaphieud();
               if ((maphieud == null) || (maphieud.equals("")))
               {
                 this.daThanhToan = false;
                 break;
               }
             }
           }
           log.info("daThanhToan (load tiep don info):" + this.daThanhToan);
           CtXuatBh ctxuatbh_ = null;
           for (ThuocPhongKham obj : this.listTPK)
           {
             obj.setThuocphongkhamMaphieud(this.phieuxuatbh.getPhieuxuatbhMa());
             ctxuatbh_ = new CtXuatBh();
             ctxuatbh_.setPhieuxuatbhMa(this.phieuxuatbh);
             ctxuatbh_.setCtxuatbhThutu(null);
             ctxuatbh_.setCtxuatbhSoluong(Double.valueOf(String.valueOf(obj.getThuocphongkhamSoluong())));
             log.info("***** So luong: " + obj.getThuocphongkhamSoluong());
             ctxuatbh_.setCtxuatbhMalk(obj.getThuocphongkhamMalk());
             ctxuatbh_.setDmthuocMaso(obj.getThuocphongkhamMathuoc());
             ctxuatbh_.setDmnctMaso(obj.getDmnctMaso());
             ctxuatbh_.setCtxuatbhLo(obj.getThuocphongkhamLo());
             ctxuatbh_.setDmnguonkinhphiMaso(obj.getDmnguonkinhphiMaso());
             ctxuatbh_.setDmquocgiaMaso(obj.getThuocphongkhamQuocgia());
             ctxuatbh_.setDmnhasanxuatMaso(obj.getThuocphongkhamHangsx());
             ctxuatbh_.setCtxuatbhNamnhap(obj.getThuocphongkhamNamnhap());
             ctxuatbh_.setCtxuatbhNgayhandung(obj.getThuocphongkhamNgayhd());
             ctxuatbh_.setCtxuatbhThanghandung(obj.getThuocphongkhamThanghd());
             ctxuatbh_.setCtxuatbhNamhandung(obj.getThuocphongkhamNamhd());
             ctxuatbh_.setCtxuatbhDongia(obj.getThuocphongkhamDongia());
             ctxuatbh_.setCtxuatbhDongiaban(obj.getThuocphongkhamDongiaban());
             ctxuatbh_.setCtxuatbhSodangky(null);
             ctxuatbh_.setCtxuatbhNgaygiocn(new Date());

             this.listCtXuatBh.add(ctxuatbh_);
           }
           this.pxNgaylap = Utils.getCurrentDate();
           this.phieuxuatbh.setTiepdonMa(this.tiepdon);
           log.info("set other fields- in form----");
           setFormTiepDon(this.tiepdon);


           Double soTienThuocBHYT = new Double(0.0D);
           for (CtXuatBh ctXuatBh : this.listCtXuatBh) {
             soTienThuocBHYT = Double.valueOf(ctXuatBh.getCtxuatbhDongia().doubleValue() * ctXuatBh.getCtxuatbhSoluong().doubleValue());
           }
           log.info("soTienThuocBHYT:" + soTienThuocBHYT);

           this.phieuxuatbh.setPhieuxuatbhThanhtien(soTienThuocBHYT);


           ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

           ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (ArrayList)clsKhamDelegate.findByTiepdonma(this.tiepdon.getTiepdonMa());
           if ((listCLS_XuatHangChoBNBHYT_temp != null) && (listCLS_XuatHangChoBNBHYT_temp.size() > 0))
           {
             this.listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
             for (ClsKham cls : this.listCLS_XuatHangChoBNBHYT) {
               SetInforUtil.setInforIfNullForClsKham(cls);
             }
           }
         }
         else
         {
           resetTiepDon();
           FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE, new Object[] { tdMa });
           this.nofoundTD = "true";
           log.info("------Ma tiep don khong hop le---------");
         }
       }
       else
       {
         resetTiepDon();
       }
     }
     catch (Exception ex)
     {
       log.info(":( ERROR: " + ex);
       ex.printStackTrace();
     }
     log.info("---End loadTiepdonAjax() method-----");
   }

   private List<ClsKham> clslist = null;

   private void tinhToanChoHSTKKham(HsThtoank hsttk)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.tiepdon);
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);

     this.permiengiam = 0;
     this.miengiam = hsthtoankUtilTmp.getMiengiam();
     this.thatthu = hsthtoankUtilTmp.getThatthu();
     this.perbhytchi = hsthtoankUtilTmp.getPerbhytchi();
     this.bhytchi = hsthtoankUtilTmp.getBhytchi();
     this.thanhtien1 = hsttk.getHsthtoankTongchi();
     this.perbntra = hsthtoankUtilTmp.getPerbntra();
     this.bntra = hsthtoankUtilTmp.getBntra();

     this.cls = Double.valueOf((hsttk.getHsthtoankCls() == null ? 0.0D : hsttk.getHsthtoankCls().doubleValue()) + (hsttk.getHsthtoankClsndm() == null ? 0.0D : hsttk.getHsthtoankClsndm().doubleValue()));


     this.thuoc = Double.valueOf((hsttk.getHsthtoankThuoc() == null ? 0.0D : hsttk.getHsthtoankThuoc().doubleValue()) + (hsttk.getHsthtoankThuocndm() == null ? 0.0D : hsttk.getHsthtoankThuocndm().doubleValue()) + (hsttk.getHsthtoankVtth() == null ? 0.0D : hsttk.getHsthtoankVtth().doubleValue()) + (hsttk.getHsthtoankVtthndm() == null ? 0.0D : hsttk.getHsthtoankVtthndm().doubleValue()));





     this.phieuxuatbh.setPhieuxuatbhMiengiam(hsttk.getHsthtoankXetgiam());







     ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
     this.listCtTPK_temp = thuocDel.find2LoaiByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);

     this.clslist = hsthtoankUtilTmp.getListCtkq_temp();
   }

   public void loadPhieuXuatBhAjax()
   {
     log.info("---Begin loadPhieuXuatBhAjax() method----");
     String message = "";
     try
     {
       this.ctxuatbh = new CtXuatBh();
       this.listCtXuatBh = new ArrayList();
       this.benhnhan = new BenhNhan();
       SetInforUtil.setInforIfNullForBN(this.benhnhan);
       this.tiepdon = new TiepDon();
       String pxMa = this.phieuxuatbh.getPhieuxuatbhMa();
       if ((pxMa != null) && (!pxMa.trim().equals("")))
       {
         log.info("Phieu xuat ma khac null");
         PhieuXuatBhDelegate pxWS = PhieuXuatBhDelegate.getInstance();


         CtXuatBhDelegate ctxWS = CtXuatBhDelegate.getInstance();

         this.phieuxuatbh = pxWS.find(pxMa);
         if (this.phieuxuatbh != null)
         {
           log.info("Tim thay Phieu xuat bh");

           this.tiepdon = this.phieuxuatbh.getTiepdonMa();
           this.benhnhan = this.tiepdon.getBenhnhanMa();
           SetInforUtil.setInforIfNullForBN(this.benhnhan);

           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date ngaylap = this.phieuxuatbh.getPhieuxuatbhNgaylap();
           this.pxNgaylap = df.format(ngaylap);

           setFormTiepDon(this.tiepdon);
           log.info("*****Noi dung thu: " + this.phieuxuatbh.getPhieuxuatbhNoidungthu());
           this.sNoiDungThu = this.phieuxuatbh.getPhieuxuatbhNoidungthu();


           CtXuatBhDelegate ctXuatBHDel = CtXuatBhDelegate.getInstance();
           this.listCtXuatBh = ctXuatBHDel.findByPhieuxuatBhMa(this.phieuxuatbh.getPhieuxuatbhMa());

           ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

           ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (ArrayList)clsKhamDelegate.findByTiepdonma(this.tiepdon.getTiepdonMa());
           log.info("----------------listCLS_XuatHangChoBNBHYT_temp--------:" + listCLS_XuatHangChoBNBHYT_temp);
           if ((listCLS_XuatHangChoBNBHYT_temp != null) && (listCLS_XuatHangChoBNBHYT_temp.size() > 0))
           {
             this.listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
             for (ClsKham cls : this.listCLS_XuatHangChoBNBHYT) {
               SetInforUtil.setInforIfNullForClsKham(cls);
             }
           }
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

   public void deleteCtXuatBhAjax()
   {
     log.info("---Begin deleteCtXuatBhAjax() method----");
     this.ctxuatbh = this.ctxuatbhSelected;
     log.info("ctxuatbh " + this.ctxuatbh.getCtxuatbhThutu());
     if (this.ctxuatbh != null) {
       this.listCtXuatBh.remove(this.ctxuatbh.getCtxuatbhThutu().intValue() - 1);
     }
     log.info("---End deleteCtXuatBhAjax() method-----");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       this.resultReportName = "";
       this.resultReportFileName = "";
       return "B4311_Xuathangchobenhnhan";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return "";
   }

   public void resetForm()
   {
     log.info("---Begin resetForm() method----");
     this.phieuxuatbh = new PhieuXuatBh();
     this.ctxuatbh = new CtXuatBh();
     this.tonKho = null;
     this.thanhtienCt = 0.0D;
     this.updateCtXuat = "0";
     this.thieuthuoc = "0";
     this.tonkhoMa = "";
     this.tkMathuoc = "";
     this.ctxSoluong = "";
     this.tiepdon = new TiepDon();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     this.listCtXuatBh = new ArrayList();

     this.listCLS_XuatHangChoBNBHYT = new ArrayList();

     this.pxNgaylap = Utils.getCurrentDate();
     this.nvBacsi = "";
     this.nvPhat = "";
     this.tdBankham = "";
     this.tdDoituong = "";
     this.tdDoituongMa = "";
     this.tdKcbBhyt = "";
     this.tdChandoan = "";
     this.nvCapnhat = "";
     this.bnNamsinh = "";
     this.bnGtinh = "";
     this.tdTinhBhyt = "";
     this.tdKhoiBhyt = "";
     this.pxPhantramBenhnhan = "1";
     this.pxClsBobot = "";
     this.tkMathuoc = "";
     this.tkQuocgiaSx = "";
     this.tkHangSx = "";
     this.ctxThanhtien = "";

     this.tkDongiaban = "";
     this.pxMiengiam = "";
     this.pxThatthu = "";
     this.thieuthuoc = "0";
     this.updateCtXuat = "0";
     this.reportFileName = "";
     this.reportFinished = "";
     this.sNoiDungThu = "";
     this.hsttk = new HsThtoank();

     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);

     this.cls = new Double(0.0D);
     this.thuoc = new Double(0.0D);

     log.info("---End resetForm() method-----");
   }

   public void resetTiepDon()
   {
     log.info("---End resetTiepDon() method-----");
     String pxMa = this.phieuxuatbh.getPhieuxuatbhMa();
     if ((pxMa != null) && (!pxMa.equals("")))
     {
       this.ctxuatbh = new CtXuatBh();
       this.updateCtXuat = "0";
       this.thanhtienCt = 0.0D;
       this.thieuthuoc = "0";
       this.tonKho = null;
       this.tkMathuoc = "";
       this.tonkhoMa = "";
       this.ctxSoluong = "";
       this.tiepdon = new TiepDon();
       this.benhnhan = new BenhNhan();
       SetInforUtil.setInforIfNullForBN(this.benhnhan);
       this.listCtXuatBh = new ArrayList();
       this.nvBacsi = "";
       this.tdBankham = "";
       this.tdDoituong = "";
       this.tdDoituongMa = "";
       this.tdKcbBhyt = "";
       this.tdChandoan = "";
       this.bnNamsinh = "";
       this.bnGtinh = "";
       this.tdTinhBhyt = "";
       this.tdKhoiBhyt = "";
       this.pxPhantramBenhnhan = "1";
       this.pxClsBobot = "";
       this.tkMathuoc = "";
       this.tkQuocgiaSx = "";
       this.tkHangSx = "";
       this.ctxThanhtien = "";

       this.tkDongiaban = "";
       this.reportFileName = "";
       this.reportFinished = "";
       this.sNoiDungThu = "";
     }
     else
     {
       resetForm();
     }
     log.info("---End resetTiepDon() method-----");
   }

   private void capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message)
   {
     log.info("---begin capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message) method----");
     double tkTonNew = tkNew.getTonkhoTon().doubleValue();
     if (tkTonNew < slNew)
     {
       DmThuoc thuocNew = tkNew.getDmthuocMaso();
       message = "Sá»‘ lÆ°á»£ng tá»“n kho khÃ´ng Ä‘á»§ cho thuá»‘c " + thuocNew.getDmthuocTen();
       this.thieuthuoc = "1";
       thuocNew = null;
     }
     else
     {
       log.info("cap nhat ctxuat vao danh sach ctxuat");

       log.info("ctxThutu: " + this.ctxuatbh.getCtxuatbhThutu());
       this.listCtXuatBh.remove(this.ctxuatbh.getCtxuatbhThutu().intValue() - 1);
       this.listCtXuatBh.add(this.ctxuatbh.getCtxuatbhThutu().intValue() - 1, this.ctxuatbh);
       resetCtXuatBh();
     }
     log.info("---End capnhatCtXuatBhMoi(TonKho tkNew, double slNew, String message) method----");
   }

   private void resetCtXuatBh()
   {
     this.ctxuatbh = new CtXuatBh();
     this.tonKho = null;
     this.updateCtXuat = "0";
     this.thieuthuoc = "0";
     this.tonkhoMa = "";
     this.ctxSoluong = "";
     this.tkDongiaban = "";
     this.tkMathuoc = "";
     this.tkHangSx = "";
     this.tkQuocgiaSx = "";
     this.ctxThanhtien = "";
   }

   private void setFormTiepDon(TiepDon tiepdon)
   {
     log.info("begin setFormTiepDon(TiepDon tiepdon) method");















     this.benhnhan = tiepdon.getBenhnhanMa();
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
     DmTinh tinhBhyt = tiepdon.getTinhbhytMa();
     if (tinhBhyt != null)
     {
       this.tdTinhBhyt = ("" + tinhBhyt.getDmtinhMaso());
       tinhBhyt = null;
     }
     DtDmKhoiBhyt khoiBhyt = tiepdon.getKhoibhytMa();
     if (khoiBhyt != null)
     {
       this.tdKhoiBhyt = khoiBhyt.getDtdmkhoibhytMa();
       khoiBhyt = null;
     }
     DmBenhVien kcbBhyt = tiepdon.getKcbbhytMa();
     if (kcbBhyt != null)
     {
       this.tdKcbBhyt = kcbBhyt.getDmbenhvienMa();
       kcbBhyt = null;
     }
     DmDoiTuong dtuong = tiepdon.getDoituongMa();
     if (dtuong != null)
     {
       this.tdDoituong = dtuong.getDmdoituongTen();
       this.tdDoituongMa = dtuong.getDmdoituongMa();
       dtuong = null;
     }
     DtDmBanKham bankham = tiepdon.getTiepdonBankham();
     if (bankham != null)
     {
       this.tdBankham = bankham.getDtdmbankhamTen();
       bankham = null;
     }
     DtDmNhanVien dmNvPhat = this.phieuxuatbh.getDtdmnhanvienPhat();
     if (dmNvPhat != null)
     {
       this.nvPhat = dmNvPhat.getDtdmnhanvienMa();
       dmNvPhat = null;
     }
     if ((tiepdon.getTiepdonBankham() != null) && (tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null) && (tiepdon.getTiepdonMa() != null))
     {
       ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
       ThamKham thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
       if (thamkham != null)
       {
         if (thamkham.getThamkhamBacsi() != null) {
           this.nvBacsi = thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
         } else {
           this.nvBacsi = "";
         }
         DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
         if ((thamkham.getBenhicd10() != null) && (thamkham.getBenhicd10().getDmbenhicdMaso() != null))
         {
           DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
           if (benh != null) {
             this.tdChandoan = benh.getDmbenhicdTen();
           }
         }
       }
     }
     log.info("End setFormTiepDon(TiepDon tiepdon) method");
   }

   private boolean checkIsDuplicateTonkho(Integer tonkhoMa, int stt, String method)
   {
     boolean rs = false;
     log.info("tonkhoMa: " + tonkhoMa.intValue());
     CtXuatBh ctx;
     if (method.equals("create"))
     {
       log.info("check duplicateTonkho in create");
       for (Iterator i$ = this.listCtXuatBh.iterator(); i$.hasNext(); ctx = (CtXuatBh)i$.next()) {}
     }
     else if (method.equals("update"))
     {
       log.info("check duplicateTonkho in update");
       for (Iterator i$ = this.listCtXuatBh.iterator(); i$.hasNext(); ctx = (CtXuatBh)i$.next()) {}
     }
     return rs;
   }

   public String inPhieu()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeKC = "InphieuXUATTHUOCBNBHYT";
     log.info("Vao Method XuatReport xuat hang cho benh nhan BHYT");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_03_BHYT.jrxml";
       String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_03_BHYT_sub1.jrxml";
       String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_03_BHYT_sub2.jrxml";
       String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieuxuatkho_03_BHYT_sub3.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
       JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
       JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);
       log.info("da thay file template ");

       Map<String, Object> params = new HashMap();
       if ((this.tenChuongTrinh != null) && ((this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoBHYT)) || (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoTE)) || (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh)) || (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoNoiTru))))
       {
         jasperReport = jasperSub1;
       }
       else
       {
         params.put("sub1", jasperSub2);
         params.put("sub2", jasperSub3);
       }
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       Date ngayXp = this.phieuxuatbh.getPhieuxuatbhNgaygiophat();

       String ngayXuat = "";
       if (ngayXp != null) {
         ngayXuat = df.format(ngayXp);
       }
       DtDmNhanVien nvCn = this.phieuxuatbh.getDtdmnhanvienCn();
       if (nvCn != null) {
         params.put("nguoiLapPhieu", "" + nvCn.getDtdmnhanvienTen());
       } else {
         params.put("nguoiLapPhieu", "");
       }
       params.put("namSinh", "" + this.bnNamsinh);

       String sothebh = "";
       String tienkham = "";
       if ((this.tiepdon.getTiepdonBntra() != null) && (!this.tiepdon.getTiepdonBntra().equals(""))) {
         tienkham = "" + this.tiepdon.getTiepdonBntra();
       }
       if ((this.tiepdon.getTiepdonSonambh() != null) && (!this.tiepdon.getTiepdonSothebh().equals(""))) {
         sothebh = this.tiepdon.getTiepdonSothebh();
       }
       if (this.tdTinhBhyt == null) {
         this.tdTinhBhyt = "";
       }
       if (this.tdKhoiBhyt == null) {
         this.tdKhoiBhyt = "";
       }
       if (this.nvBacsi == null) {
         this.nvBacsi = "";
       }
       if (this.tdChandoan == null) {
         this.tdChandoan = "";
       }
       params.put("maPhieuXuat", "" + this.phieuxuatbh.getPhieuxuatbhMa());
       if ((ngayXuat != null) && (!ngayXuat.equals("")))
       {
         params.put("ngayXuat", "" + ngayXuat.substring(0, 10));
         params.put("gioXuat", ngayXuat.subSequence(11, 19));
       }
       sothebh = this.tiepdon.getTiepdonSothebh();
       params.put("tenBenhNhan", "" + this.benhnhan.getBenhnhanHoten());
       params.put("chanDoan", " " + this.tdChandoan);
       params.put("soTheBH", sothebh);
       params.put("kyHieuBS", " " + this.nvBacsi);
       params.put("tongCong", "" + this.listCtXuatBh.size());
       params.put("tienKham", "" + tienkham);

       Double thtoan = this.hsttk.getHsthtoankThtoan();
       if (thtoan == null) {
         thtoan = new Double(0.0D);
       }
       Double thatthu = this.hsttk.getHsthtoankThatthu();
       if (thatthu == null) {
         thatthu = new Double(0.0D);
       }
       Double tienThuoc = Double.valueOf(thtoan.doubleValue() - thatthu.doubleValue());
       params.put("tienThuoc", tienThuoc);


       params.put("TIENBANGCHU", Utils.formatNumberWithSeprator(tienThuoc) + " " + IConstantsRes.dongVN);
       if (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoBHYT)) {
         params.put("xuatTaiKho", "Kho BHYT");
       } else if (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoTE)) {
         params.put("xuatTaiKho", "Kho Trẻ Em");
       } else if (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh)) {
         params.put("xuatTaiKho", "Kho Chính");
       } else if (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoNoiTru)) {
         params.put("xuatTaiKho", "Kho Nội Trú");
       }
       if (this.bnGtinh.equals("1")) {
         params.put("gioiTinh", IConstantsRes.GIOI_TINH_NU);
       } else {
         params.put("gioiTinh", IConstantsRes.GIOI_TINH_NAM);
       }
       Calendar cal = Calendar.getInstance();
       log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("ngay", "" + cal.get(5));
       params.put("thang", "" + (cal.get(2) + 1));
       params.put("nam", "" + cal.get(1));
       params.put("diaPhuong", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);


       params.put("UBNDTINH", IConstantsRes.REPORT_DIEUTRI_UBNDTINH);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("HOTEN", this.benhnhan.getBenhnhanHoten());

       String diachistr = "";
       if (this.benhnhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhnhan.getBenhnhanDiachi() + ",";
       }
       if (this.benhnhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhnhan.getXaMa(true).getDmxaTen() + ",";
       }
       if (this.benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhnhan.getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.benhnhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhnhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("diaChiBenhNhan", diachistr);
       if (this.phieuxuatbh.getPhieuxuatbhNoidungthu() != null) {
         params.put("NOIDUNG", this.phieuxuatbh.getPhieuxuatbhNoidungthu());
       }
       log.info("***** PhieuxuatbhMa: " + this.phieuxuatbh.getPhieuxuatbhMa());

       params.put("SOTIEN", Utils.formatNumberWithSeprator(tienThuoc));
       params.put("TIENBANGCHU", Utils.NumberToString(tienThuoc.doubleValue()));



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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "InphieuXHBNBHYT");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       this.index += 1;
       log.info("Index :" + getIndex());
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public PhieuXuatBh getPhieuxuatbh()
   {
     return this.phieuxuatbh;
   }

   public void setPhieuxuatbh(PhieuXuatBh phieuxuatbh)
   {
     this.phieuxuatbh = phieuxuatbh;
   }

   public BenhNhan getBenhnhan()
   {
     return this.benhnhan;
   }

   public void setBenhnhan(BenhNhan benhnhan)
   {
     this.benhnhan = benhnhan;
   }

   public TiepDon getTiepdon()
   {
     return this.tiepdon;
   }

   public void setTiepdon(TiepDon tiepdon)
   {
     this.tiepdon = tiepdon;
   }

   public List<CtXuatBh> getListCtXuatBh()
   {
     return this.listCtXuatBh;
   }

   public void setListCtXuatBh(List<CtXuatBh> listCtXuatBh)
   {
     this.listCtXuatBh = listCtXuatBh;
   }

   public CtXuatBh getCtxuatbhSelected()
   {
     return this.ctxuatbhSelected;
   }

   public void setCtxuatbhSelected(CtXuatBh ctxuatbhSelected)
   {
     this.ctxuatbhSelected = ctxuatbhSelected;
   }

   public TonKho[] getListTonkho()
   {
     return this.listTonkho;
   }

   public void setListTonkho(TonKho[] listTonkho)
   {
     this.listTonkho = listTonkho;
   }

   public String getTdBankham()
   {
     return this.tdBankham;
   }

   public void setTdBankham(String tdBankham)
   {
     this.tdBankham = tdBankham;
   }

   public String getTdDoituong()
   {
     return this.tdDoituong;
   }

   public void setTdDoituong(String tdDoituong)
   {
     this.tdDoituong = tdDoituong;
   }

   public String getTdKcbBhyt()
   {
     return this.tdKcbBhyt;
   }

   public void setTdKcbBhyt(String tdKcbBhyt)
   {
     this.tdKcbBhyt = tdKcbBhyt;
   }

   public String getTdChandoan()
   {
     return this.tdChandoan;
   }

   public void setTdChandoan(String tdChandoan)
   {
     this.tdChandoan = tdChandoan;
   }

   public String getPxNgaylap()
   {
     return this.pxNgaylap;
   }

   public void setPxNgaylap(String pxNgaylap)
   {
     this.pxNgaylap = pxNgaylap;
   }

   public String getTdTinhBhyt()
   {
     return this.tdTinhBhyt;
   }

   public void setTdTinhBhyt(String tdTinhBhyt)
   {
     this.tdTinhBhyt = tdTinhBhyt;
   }

   public String getTdKhoiBhyt()
   {
     return this.tdKhoiBhyt;
   }

   public void setTdKhoiBhyt(String tdKhoiBhyt)
   {
     this.tdKhoiBhyt = tdKhoiBhyt;
   }

   public String getPxPhantramBenhnhan()
   {
     return this.pxPhantramBenhnhan;
   }

   public void setPxPhantramBenhnhan(String pxPhantramBenhnhan)
   {
     this.pxPhantramBenhnhan = pxPhantramBenhnhan;
   }

   public String getTkMathuoc()
   {
     return this.tkMathuoc;
   }

   public void setTkMathuoc(String tkMathuoc)
   {
     this.tkMathuoc = tkMathuoc;
   }

   public String getTkQuocgiaSx()
   {
     return this.tkQuocgiaSx;
   }

   public void setTkQuocgiaSx(String tkQuocgiaSx)
   {
     this.tkQuocgiaSx = tkQuocgiaSx;
   }

   public String getTkHangSx()
   {
     return this.tkHangSx;
   }

   public void setTkHangSx(String tkHangSx)
   {
     this.tkHangSx = tkHangSx;
   }

   public CtXuatBh getCtxuatbh()
   {
     return this.ctxuatbh;
   }

   public void setCtxuatbh(CtXuatBh ctxuatbh)
   {
     this.ctxuatbh = ctxuatbh;
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

   public String getNvBacsi()
   {
     return this.nvBacsi;
   }

   public void setNvBacsi(String nvBacsi)
   {
     this.nvBacsi = nvBacsi;
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

   public String getPxClsBobot()
   {
     return this.pxClsBobot;
   }

   public void setPxClsBobot(String clsBobot)
   {
     this.pxClsBobot = clsBobot;
   }

   public String getCtxThanhtien()
   {
     return this.ctxThanhtien;
   }

   public void setCtxThanhtien(String ctxThanhtien)
   {
     this.ctxThanhtien = ctxThanhtien;
   }

   public String getTkDongiaban()
   {
     return this.tkDongiaban;
   }

   public void setTkDongiaban(String tkDongiaban)
   {
     this.tkDongiaban = tkDongiaban;
   }

   public String getThieuthuoc()
   {
     return this.thieuthuoc;
   }

   public void setThieuthuoc(String thieuthuoc)
   {
     this.thieuthuoc = thieuthuoc;
   }

   public String getUpdateCtXuat()
   {
     return this.updateCtXuat;
   }

   public void setUpdateCtXuat(String updateCtXuat)
   {
     this.updateCtXuat = updateCtXuat;
   }

   public String getPxMiengiam()
   {
     return this.pxMiengiam;
   }

   public void setPxMiengiam(String pxMiengiam)
   {
     this.pxMiengiam = pxMiengiam;
   }

   public String getPxThatthu()
   {
     return this.pxThatthu;
   }

   public void setPxThatthu(String pxThatthu)
   {
     this.pxThatthu = pxThatthu;
   }

   public String getTonkhoMa()
   {
     return this.tonkhoMa;
   }

   public void setTonkhoMa(String tonkhoMa)
   {
     this.tonkhoMa = tonkhoMa;
   }

   public String getCtxSoluong()
   {
     return this.ctxSoluong;
   }

   public void setCtxSoluong(String ctxSoluong)
   {
     this.ctxSoluong = ctxSoluong;
   }

   public double getThanhtienCt()
   {
     return this.thanhtienCt;
   }

   public void setThanhtienCt(double thanhtienCt)
   {
     this.thanhtienCt = thanhtienCt;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public String getSaveResult()
   {
     return this.saveResult;
   }

   public void setSaveResult(String saveResult)
   {
     this.saveResult = saveResult;
   }

   public String getReportFileName()
   {
     return this.reportFileName;
   }

   public void setReportFileName(String reportFileName)
   {
     this.reportFileName = reportFileName;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
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

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
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

   public TonKho getTonKho()
   {
     return this.tonKho;
   }

   public void setTonKho(TonKho tonKho)
   {
     this.tonKho = tonKho;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }

   public String getInitB121_3_Xutrithuoctaibankham()
   {
     return this.initB121_3_Xutrithuoctaibankham;
   }

   public void setInitB121_3_Xutrithuoctaibankham(String initB121_3_Xutrithuoctaibankham)
   {
     this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
   }

   public String getLoaiToaThuocThamKhamVaXuTri()
   {
     return this.loaiToaThuocThamKhamVaXuTri;
   }

   public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri)
   {
     this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
   }

   public List<ThuocPhongKham> getListTPK()
   {
     return this.listTPK;
   }

   public void setListTPK(List<ThuocPhongKham> listTPK)
   {
     this.listTPK = listTPK;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getReturnToXuatHangChoBNBHYT()
   {
     return this.returnToXuatHangChoBNBHYT;
   }

   public void setReturnToXuatHangChoBNBHYT(String returnToXuatHangChoBNBHYT)
   {
     this.returnToXuatHangChoBNBHYT = returnToXuatHangChoBNBHYT;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getTdDoituongMa()
   {
     return this.tdDoituongMa;
   }

   public void setTdDoituongMa(String tdDoituongMa)
   {
     this.tdDoituongMa = tdDoituongMa;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public double getTongChiPhiCanThiet()
   {
     return this.tongChiPhiCanThiet;
   }

   public void setTongChiPhiCanThiet(double tongChiPhiCanThiet)
   {
     this.tongChiPhiCanThiet = tongChiPhiCanThiet;
   }

   public double getBnTra()
   {
     return this.bnTra;
   }

   public void setBnTra(double bnTra)
   {
     this.bnTra = bnTra;
   }

   public HsThtoank getHsttk()
   {
     return this.hsttk;
   }

   public void setHsttk(HsThtoank hsttk)
   {
     this.hsttk = hsttk;
   }

   public String getNoiDungThu()
   {
     return this.sNoiDungThu;
   }

   public void setNoiDungThu(String noiDungThu)
   {
     this.sNoiDungThu = noiDungThu;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XuatHangChoBenhNhanBHYTAction

 * JD-Core Version:    0.7.0.1

 */