 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
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
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.ThamKhamUtil;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.rmi.RemoteException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.Timestamp;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3232_ThanhToanBenhNhanBHYT")
 @Synchronized(timeout=6000000L)
 public class ThanhToanBenhNhanBHYTAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(ThanhToanBenhNhanBHYTAction.class);
   private BenhNhan benhnhan;
   private TiepDon tiepdon;
   private ThamKham thamkham;
   @DataModel
   private ArrayList<ThuocPhongKham> listThuocPK;
   private List<ThuocPhongKham> listTPK;
   @DataModel
   private ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT;
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
   Identity identity;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();

   public void refreshnhanvienthungan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   private int index = 0;
   private TonKho[] listTonkho;
   private String nvBacsi;
   private String nvPhat;
   private Integer nvPhatSo;
   private String nvPhatTen;
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
   private String gioThanhToan;
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
   private String kyhieu;
   private String quyen;
   private String bienlai;
   private String maPhieu = "";
   private Double tienThuocDaTT = Double.valueOf(0.0D);
   private String strBankham;
   private Double nguonkhactra = new Double(0.0D);
   private Double nguonkhactra_hid = new Double(0.0D);
   private Double benhnhanTra_hid = new Double(0.0D);
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



     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   @Out(required=false)
   private String isReport = "false";
   private String nosuccess;
   private String nofoundTD;
   private HsThtoank hsttk;
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
   private String returnToThanhToanBNBHYT;
   @In(required=false)
   @Out(required=false)
   private String goToThuocBNBHYT;

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
     this.tiepdon = new TiepDon();
     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     this.listThuocPK = new ArrayList();


     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.pxNgaylap = formatter.format(cal.getTime());
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());

     this.updateCtXuat = "0";
     this.pxPhantramBenhnhan = "1";
     this.sNoiDungThu = "";
     this.nosuccess = "false";
     this.nofoundTD = "false";
     this.listCLS_XuatHangChoBNBHYT = new ArrayList();
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

   public String getGoToThuocBNBHYT()
   {
     return this.goToThuocBNBHYT;
   }

   public void setGoToThuocBNBHYT(String goToThuocBNBHYT)
   {
     this.goToThuocBNBHYT = goToThuocBNBHYT;
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
     refreshnhanvienthungan();
     log.info("---------end init method-------");
     this.hsttk = new HsThtoank();

     return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
   }

   @End
   public void endFucntion() {}

   @Factory("goToThuocBNBHYT")
   public void goToThuocBNBHYTForm()
     throws Exception
   {
     log.info("begin goToThuocBNBHYTForm");
     this.returnToThanhToanBNBHYT = "done";
     this.goToThuocBNBHYT = null;
     reset();
     this.tiepdon.setTiepdonMa(this.maTiepDonOut);
     if ((this.tiepdon != null) && (this.tiepdon.getTiepdonMa() != null))
     {
       log.info("loadTiepdonAjax () ");
       loadTiepdonAjax();
     }
     log.info("end goToThuocBNBHYTForm");
   }

   @Factory("returnToThanhToanBNBHYT")
   public void initForReturn()
     throws Exception
   {
     log.info("begin initForReturn");
     this.goToThuocBNBHYT = "done";
     if ((this.tiepdon != null) && (this.tiepdon.getTiepdonMa() != null))
     {
       log.info("loadTiepdonAjax () ");
       loadTiepdonAjax();
     }
     log.info("end initForReturn");
   }

   public String nhapPhieuBHYT()
   {
     log.info("*****begin nhapPhieuBHYT()");

     this.maTiepDonOut = this.tiepdon.getTiepdonMa();


     this.initB121_3_Xutrithuoctaibankham = null;
     this.loaiToaThuocThamKhamVaXuTri = Utils.KE_TOA_QUAY_BENH_VIEN;
     this.returnToThanhToanBNBHYT = "1";

     log.info("*****End nhapPhieuBHYT()");
     return "xutrithuoctaibankham";
   }

   public void saved()
   {
     log.info("---Begin saved() method----");
     try
     {
       this.reportFileName = "";
       this.reportFinished = "";

       String message = "";

       DtDmNhanVien dmNvPhat = new DtDmNhanVien();
       dmNvPhat.setDtdmnhanvienMa(this.nvPhat);

       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       Calendar cal = Calendar.getInstance();
       cal.setTime(df.parse(df.format(new Date())));

       ThuocPhongKham[] arrayCtxBh = new ThuocPhongKham[this.listThuocPK.size()];
       for (int i = 0; i < this.listThuocPK.size(); i++) {
         arrayCtxBh[i] = ((ThuocPhongKham)this.listThuocPK.get(i));
       }
       log.info("---End saved() method-----");

       FacesMessages.instance().add(message, new Object[0]);
     }
     catch (Exception e)
     {
       this.saveResult = "0";
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.info(":( ERROR: " + e);
     }
   }

   public void ghiNhan()
   {
     refreshnhanvienthungan();
     try
     {
       if (this.daThanhToan == true)
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();

         this.hsttk.setHsthtoankThatthu(this.thatthu);
         this.hsttk.setHsthtoankKyhieu(this.kyhieu);
         this.hsttk.setHsthtoankQuyen(this.quyen);
         this.hsttk.setHsthtoankBienlai(this.bienlai);
         this.hsttk.setHsthtoankDatt(Boolean.valueOf(true));



         Calendar dTemp = Utils.getDBDate(this.gioThanhToan, this.pxNgaylap);
         Date dNgayGioTT = Calendar.getInstance().getTime();
         if (dTemp != null) {
           dNgayGioTT = dTemp.getTime();
         }
         this.hsttk.setHsthtoankNgaygiott(dNgayGioTT);

         tinhToanChoHSTKKham(this.hsttk);
         log.info("After tinhToanChoHSTKKham, hsttk = " + this.hsttk);
         this.hsttk.setHsthtoankThtoan(new Double(0.0D));
         log.info("nhanvien = " + this.nhanVienThungan);
         if ((this.nhanVienThungan != null) && (this.nhanVienThungan.getDtdmnhanvienMaso() != null)) {
           this.hsttk.setHsthtoankThungan(this.nhanVienThungan);
         }
         this.hsttk.setHsthtoankNguonkhactra(this.nguonkhactra);
         hsttkDelegate.capNhatTTHsttk(this.hsttk, this.clslist, this.listCtTPK_temp, false);

         HsThtoank hsttkTemp = hsttkDelegate.findAllBytiepdonMa(this.tiepdon.getTiepdonMa());
         log.info("After findAllBytiepdonMa, hsttk = " + this.hsttk);
         this.maPhieu = hsttkTemp.getHsthtoankMa();
         this.benhnhanTra_hid = this.hsttk.getHsthtoankBntra();
         this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
         this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
         this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
         return;
       }
       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     }
     catch (NumberFormatException e)
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
     }
   }

   private boolean daThanhToan = true;
   private String checkThanhToan = "block";
   private double tongChiPhiCanThiet;
   private double bnTra;
   private double gioiHanTyLe;
   private double tylebhyt;
   private double tylebhyt2;

   public void loadTiepdonAjax()
   {
     log.info("---Begin loadTiepdonAjax() method 123----");
     this.checkThanhToan = "block";
     this.listThuocPK = new ArrayList();

     this.benhnhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhnhan);
     try
     {
       TiepDonDelegate tdWsPort = TiepDonDelegate.getInstance();
       String tdMa = this.tiepdon.getTiepdonMa();
       if ((tdMa != null) && (!tdMa.trim().equals("")))
       {
         this.tiepdon = tdWsPort.find(tdMa);
         if (this.tiepdon == null)
         {
           resetTiepDon();
           FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tdMa, new Object[0]);
           this.nofoundTD = "true";
           resetForm();
           log.info("------Khong tim thay ma tiep don---------");
         }
         else if (this.tiepdon.getTiepdonBankham() == null)
         {
           Hsba hsbaTmp = HsbaDelegate.getInstance().findByTiepDonMa(tdMa);
           if (hsbaTmp != null) {
             FacesMessages.instance().add(IConstantsRes.TIEP_DON_DUOC_TAO_TU_NOI_TRU, new Object[] { tdMa, hsbaTmp.getHsbaSovaovien() });
           } else {
             FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + tdMa, new Object[0]);
           }
           resetTiepDon();
           this.nofoundTD = "true";
           resetForm();

           log.info("------Tiep don ban kham NULL---------");
         }
         else if (this.tiepdon != null)
         {
           log.info("tiepdonBenhnhan: " + this.tiepdon.getBenhnhanMa().getBenhnhanMa());
           ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findByMaTiepDon(this.tiepdon.getTiepdonMa());
           if (cvnt != null)
           {
             FacesMessages.instance().add(IConstantsRes.BN_DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
             resetForm();
             this.nofoundTD = "true";
             return;
           }
           if (this.tiepdon.getDoituongMa().getDmdoituongMa().equals("TP"))
           {
             FacesMessages.instance().add(IConstantsRes.DOITUONG_THUPHI, new Object[0]);

             resetForm();
             this.nofoundTD = "true";
             return;
           }
           if (("CCL".equals(this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa())) || ("CCN".equals(this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa())))
           {
             FacesMessages.instance().add(IConstantsRes.DOI_TUONG_CAP_CUU + ", " + IConstantsRes.KHONG_THANH_TOAN_O_DAY, new Object[0]);
             resetForm();
             this.nofoundTD = "true";
             return;
           }
           if ((this.tiepdon.getTiepdonBankham() != null) && (this.tiepdon.getTiepdonBankham().getDtdmbankhamMa() != null) && (this.tiepdon.getTiepdonMa() != null))
           {
             ThamKhamDelegate thamkhamDelegate = ThamKhamDelegate.getInstance();
             this.thamkham = thamkhamDelegate.findByBanKhamVaMaTiepDon(this.tiepdon.getTiepdonBankham().getDtdmbankhamMa(), this.tiepdon.getTiepdonMa());
             if (this.thamkham != null)
             {
               if (this.thamkham.getThamkhamBacsi() != null) {
                 this.nvBacsi = this.thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
               } else {
                 this.nvBacsi = "";
               }
               DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
               if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
               {
                 DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
                 if (benh != null) {
                   this.tdChandoan = benh.getDmbenhicdTen();
                 }
               }
             }
           }
           int tiLeBHYT = 0;





           String strSotheBHYT = this.tiepdon.getTiepdonSothebh() == null ? "" : this.tiepdon.getTiepdonSothebh();
           if (strSotheBHYT.trim().length() > 3)
           {
             String strMaQuyenloi = strSotheBHYT.substring(2, 3);
             DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
             DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
             if (maQL != null) {
               tiLeBHYT = maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1().shortValue();
             }
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
                     iPhanTram = 100 - new Integer(IConstantsRes.TYLE_BH_VUOT_TUYEN).intValue();
                   }
                 }
               }
             }
           }
           this.sNoiDungThu = ("Thu " + iPhanTram + IConstantsRes.NOI_DUNG_THU_BHYT);
           if ("MP".equals(this.tiepdon.getDoituongMa(true).getDmdoituongMa())) {
             this.sNoiDungThu = IConstantsRes.NOI_DUNG_THU_MP;
           }
           log.info("daThanhToan (load tiep don info):" + this.daThanhToan);



           Calendar cal = Calendar.getInstance();
           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
           this.pxNgaylap = formatter.format(cal.getTime());
           this.gioThanhToan = Utils.getGioPhut(cal.getTime());

           log.info("set other fields- in form----");
           setFormTiepDon(this.tiepdon);



           ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

           ArrayList<ClsKham> listCLS_XuatHangChoBNBHYT_temp = (ArrayList)clsKhamDelegate.findByTiepdonma(this.tiepdon.getTiepdonMa());



           this.listCLS_XuatHangChoBNBHYT = listCLS_XuatHangChoBNBHYT_temp;
           for (ClsKham cls : this.listCLS_XuatHangChoBNBHYT) {
             SetInforUtil.setInforIfNullForClsKham(cls);
           }
           ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
           ArrayList<ThuocPhongKham> thuocTemp = (ArrayList)thuocDel.find2LoaiByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN);
           this.tienThuocDaTT = Double.valueOf(0.0D);
           if ((thuocTemp != null) && (thuocTemp.size() > 0))
           {
             this.listThuocPK = thuocTemp;
             for (ThuocPhongKham thuocPhongKham : this.listThuocPK)
             {
               SetInforUtil.setInforIfNullForThuocPhongKham(thuocPhongKham);
               if (thuocPhongKham.getThuocphongkhamDatt() == null) {
                 this.tienThuocDaTT = Double.valueOf(0.0D);
               } else if (thuocPhongKham.getThuocphongkhamDatt().booleanValue() == true) {
                 this.tienThuocDaTT = Double.valueOf(this.tienThuocDaTT.doubleValue() + (thuocPhongKham.getThuocphongkhamThanhtien() == null ? 0.0D : thuocPhongKham.getThuocphongkhamThanhtien().doubleValue()));
               }
             }
           }
           HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
           this.hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
           if (this.hsttk != null)
           {
             FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
             System.out.println("ThanhToanBenhNhanBHYTAction.loadTiepdonAjax()**********checkThanhToan= " + this.checkThanhToan);
             this.checkThanhToan = "none";
             System.out.println("ThanhToanBenhNhanBHYTAction.loadTiepdonAjax()**********checkThanhToan1= " + this.checkThanhToan);

             this.permiengiam = 0;
             this.miengiam = this.hsttk.getHsthtoankXetgiam();
             this.thatthu = Double.valueOf(this.hsttk.getHsthtoankThatthu() == null ? 0.0D : this.hsttk.getHsthtoankThatthu().doubleValue());
             this.perbhytchi = this.hsttk.getHsthtoankTylebh().shortValue();
             this.thanhtien1 = this.hsttk.getHsthtoankTongchi();
             this.perbntra = (100 - this.perbhytchi);
             this.bntra = this.hsttk.getHsthtoankBntra();
             this.bhytchi = this.hsttk.getHsthtoankBhyt();

             this.cls = Double.valueOf((this.hsttk.getHsthtoankCls() == null ? 0.0D : this.hsttk.getHsthtoankCls().doubleValue()) + (this.hsttk.getHsthtoankClsndm() == null ? 0.0D : this.hsttk.getHsthtoankClsndm().doubleValue()));


             this.thuoc = Double.valueOf((this.hsttk.getHsthtoankThuoc() == null ? 0.0D : this.hsttk.getHsthtoankThuoc().doubleValue()) + (this.hsttk.getHsthtoankThuocndm() == null ? 0.0D : this.hsttk.getHsthtoankThuocndm().doubleValue()) + (this.hsttk.getHsthtoankVtth() == null ? 0.0D : this.hsttk.getHsthtoankVtth().doubleValue()) + (this.hsttk.getHsthtoankVtthndm() == null ? 0.0D : this.hsttk.getHsthtoankVtthndm().doubleValue()));



             this.nofoundTD = "false";
             this.maPhieu = this.hsttk.getHsthtoankMa();
             if (this.hsttk.getHsthtoankNgaygiott() != null)
             {
               Date ngayGioTT = this.hsttk.getHsthtoankNgaygiott();
               this.gioThanhToan = Utils.getGioPhut(ngayGioTT);
               this.pxNgaylap = formatter.format(ngayGioTT);
             }
             this.benhnhanTra_hid = this.hsttk.getHsthtoankBntra();
             this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
             this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
             this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
           }
           else if (this.hsttk == null)
           {
             this.hsttk = new HsThtoank();
             log.info("tiepdon = " + this.tiepdon);
             this.hsttk.setTiepdonMa(this.tiepdon);
             log.info("hsttk is null");
             tinhToanChoHSTKKham(this.hsttk);
             log.info("tyle BH = " + this.hsttk.getHsthtoankTylebh());
             if (this.hsttk.getHsthtoankTylebh() != null) {
               this.sNoiDungThu = ("Thu " + (100 - this.hsttk.getHsthtoankTylebh().intValue()) + IConstantsRes.NOI_DUNG_THU_BHYT);
             }
             if ("MP".equals(this.tiepdon.getDoituongMa(true).getDmdoituongMa())) {
               this.sNoiDungThu = IConstantsRes.NOI_DUNG_THU_MP;
             }
             this.nofoundTD = "true";
             this.maPhieu = "";
             Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(this.tiepdon.getTiepdonMa());
             if (hsba != null)
             {
               HsThtoan hstt = HsThtoanDelegate.getInstance().findBySovaovien(hsba.getHsbaSovaovien());
               if (hstt != null) {
                 FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + (hstt.getHsthtoanNgaytt() != null ? IConstantsRes.DA_THANH_TOAN : IConstantsRes.CHUA_THANH_TOAN) + ")", hsba.getHsbaSovaovien() });
               } else {
                 FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_HSBA_NOI_TRU, new Object[] { "(" + IConstantsRes.CHUA_THANH_TOAN + ")", hsba.getHsbaSovaovien() });
               }
             }
             this.benhnhanTra_hid = this.hsttk.getHsthtoankBntra();
             this.nguonkhactra = this.hsttk.getHsthtoankNguonkhactra();
             this.nguonkhactra_hid = this.hsttk.getHsthtoankNguonkhactra();
             this.bntra = Double.valueOf(this.bntra.doubleValue() - this.nguonkhactra.doubleValue());
           }
           List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(tdMa);
           this.strBankham = "";
           for (ThamKham eachTk : listTk) {
             this.strBankham += (eachTk.getThamkhamBankham() == null ? "" : this.strBankham.trim().length() > 0 ? ", " + eachTk.getThamkhamBankham().getDtdmbankhamMa() : eachTk.getThamkhamBankham() == null ? "" : eachTk.getThamkhamBankham().getDtdmbankhamMa());
           }
         }
         else
         {
           resetTiepDon();
           FacesMessages.instance().add(IConstantsRes.TIEP_DON_UNAVAILABLE + " " + tdMa, new Object[0]);
           this.nofoundTD = "true";
           this.strBankham = "";
           log.info("------Ma tiep don khong hop le---------");
         }
       }
       else
       {
         resetTiepDon();
         this.nofoundTD = "true";
         this.strBankham = "";
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


     this.miengiam = hsttk.getHsthtoankXetgiam();
     this.thatthu = hsthtoankUtilTmp.getThatthu();
     this.perbhytchi = hsthtoankUtilTmp.getPerbhytchi();


     this.bhytchi = hsttk.getHsthtoankBhyt();
     this.thanhtien1 = hsttk.getHsthtoankTongchi();
     this.perbntra = hsthtoankUtilTmp.getPerbntra();


     this.bntra = hsttk.getHsthtoankBntra();

     this.cls = Double.valueOf((hsttk.getHsthtoankCls() == null ? 0.0D : hsttk.getHsthtoankCls().doubleValue()) + (hsttk.getHsthtoankClsndm() == null ? 0.0D : hsttk.getHsthtoankClsndm().doubleValue()));


     this.thuoc = Double.valueOf((hsttk.getHsthtoankThuoc() == null ? 0.0D : hsttk.getHsthtoankThuoc().doubleValue()) + (hsttk.getHsthtoankThuocndm() == null ? 0.0D : hsttk.getHsthtoankThuocndm().doubleValue()) + (hsttk.getHsthtoankVtth() == null ? 0.0D : hsttk.getHsthtoankVtth().doubleValue()) + (hsttk.getHsthtoankVtthndm() == null ? 0.0D : hsttk.getHsthtoankVtthndm().doubleValue()));





     ThuocPhongKhamDelegate thuocDel = ThuocPhongKhamDelegate.getInstance();
     this.listCtTPK_temp = ((ArrayList)thuocDel.find2LoaiByMaTiepDon(this.tiepdon.getTiepdonMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM, Utils.KE_TOA_QUAY_BENH_VIEN));
     this.clslist = hsthtoankUtilTmp.getListCtkq_temp();
   }

   public void loadPhieuXuatBhAjax()
   {
     log.info("---Begin loadPhieuXuatBhAjax() method----");
     try
     {
       if ((this.maPhieu == null) || (this.maPhieu.equals("")))
       {
         this.nofoundTD = "true";
         return;
       }
       this.tiepdon = new TiepDon();

       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       this.hsttk = hsttkDelegate.findByMaPhieu(this.maPhieu);
       if ((this.hsttk == null) || (this.hsttk.equals("")) || (this.hsttk.getHsthtoankNgaygiott() == null))
       {
         this.nofoundTD = "true";
         FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { this.maPhieu });
         this.maPhieu = "";
         resetForm();
         return;
       }
       this.tiepdon = this.hsttk.getTiepdonMa();
       this.kyhieu = this.hsttk.getHsthtoankKyhieu();
       this.bienlai = this.hsttk.getHsthtoankBienlai();
       this.quyen = this.hsttk.getHsthtoankQuyen();
       loadTiepdonAjax();
     }
     catch (Exception e)
     {
       log.info(":( ERROR : " + e);
     }
     log.info("---End loadPhieuXuatBhAjax() method-----");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       this.resultReportName = "";
       this.resultReportFileName = "";
       return "B3232_ThanhToanBenhNhanBHYT";
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
     refreshnhanvienthungan();
     this.maPhieu = "";
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
     this.listThuocPK = new ArrayList();

     this.listCLS_XuatHangChoBNBHYT = new ArrayList();



     Calendar cal = Calendar.getInstance();
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.pxNgaylap = formatter.format(cal.getTime());
     this.gioThanhToan = Utils.getGioPhut(cal.getTime());

     this.nvBacsi = "";
     this.nvPhat = "";
     if (this.nhanVienThungan != null)
     {
       this.nvPhat = this.nhanVienThungan.getDtdmnhanvienMa();
       this.nvPhatTen = this.nhanVienThungan.getDtdmnhanvienTen();
     }
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
     this.strBankham = "";
     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
     this.nguonkhactra = new Double(0.0D);
     this.nguonkhactra_hid = new Double(0.0D);
     this.benhnhanTra_hid = new Double(0.0D);
     log.info("---End resetForm() method-----");
   }

   public void resetTiepDon()
   {
     log.info("---End resetTiepDon() method-----");
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
     this.listThuocPK = new ArrayList();
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



     log.info("---End resetTiepDon() method-----");
   }

   private void setFormTiepDon(TiepDon tiepdon)
   {
     log.info("begin setFormTiepDon(TiepDon tiepdon) method");
     log.info("tiepdon.getTiepdonTylebh() : " + tiepdon.getTiepdonTylebh());

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
     else
     {
       this.tdKcbBhyt = "";
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
     log.info("End setFormTiepDon(TiepDon tiepdon) method");
   }

   public String inPhieu()
   {
     log.info("Begin inPhieu(), tiepdon = " + this.tiepdon + ", Ma TD = " + this.tiepdon.getTiepdonMa());
     String tdMa = this.tiepdon.getTiepdonMa();
     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     this.hsttk = hsttkDelegate.findBytiepdonMa(tdMa);
     if (this.hsttk == null)
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[0]);
       log.info("End InPhieu(): Khong tim thay phieu !");
       return "";
     }
     Double thtoan = Double.valueOf(0.0D);
     if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
     {
       if (this.hsttk.getHsthtoankBntra() == null) {
         thtoan = new Double(0.0D);
       } else {
         thtoan = this.hsttk.getHsthtoankBntra();
       }
     }
     else if (this.hsttk.getHsthtoankThtoan() == null) {
       thtoan = new Double(0.0D);
     } else {
       thtoan = this.hsttk.getHsthtoankThtoan();
     }
     Double nguonkhac = Double.valueOf(this.hsttk.getHsthtoankNguonkhactra() == null ? 0.0D : this.hsttk.getHsthtoankNguonkhactra().doubleValue());
     Double benhnhantra = Double.valueOf(this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue());
     Double xetgiam = Double.valueOf(this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue());
     if ((thtoan.doubleValue() == 0.0D) || (benhnhantra.doubleValue() - xetgiam.doubleValue() - nguonkhac.doubleValue() < 1.0D))
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_IN_BIEN_LAI, new Object[0]);
       log.info("End InPhieu(): Khong in bien lai !");
       return "";
     }
     XuatReport2();
     log.info("End inPhieu() ---------------------------");
     return "B4160_Chonmenuxuattaptin";
   }

   public void XuatReport2()
   {
     this.reportTypeKC = "InphieuXHBNBHYT";
     String baocao1 = null;
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();


     Double mau = Double.valueOf(0.0D);
     Double xntdcn = Double.valueOf(0.0D);
     Double cdha = Double.valueOf(0.0D);
     Double pttt = Double.valueOf(0.0D);
     Double ktc = Double.valueOf(0.0D);
     Double dvp = Double.valueOf(0.0D);
     Double vc = Double.valueOf(0.0D);
     Double ck = Double.valueOf(0.0D);
     Double clskhac = Double.valueOf(0.0D);


     Double mauDV = Double.valueOf(0.0D);
     Double xntdcnDV = Double.valueOf(0.0D);
     Double cdhaDV = Double.valueOf(0.0D);
     Double ptttDV = Double.valueOf(0.0D);
     Double ktcDV = Double.valueOf(0.0D);
     Double dvpDV = Double.valueOf(0.0D);
     Double vcDV = Double.valueOf(0.0D);
     Double ckDV = Double.valueOf(0.0D);
     Double clskhacDV = Double.valueOf(0.0D);

     boolean hasDV = false;
     Double curGia = Double.valueOf(0.0D);
     Double curGiaDV = Double.valueOf(0.0D);
     Double tongtien = Double.valueOf(0.0D);
     Double tongtienDV = Double.valueOf(0.0D);
     Double miengiam = Double.valueOf(0.0D);

     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     this.hsttk = hsttkDelegate.findBytiepdonMa(this.tiepdon.getTiepdonMa());
     if (this.hsttk != null) {
       miengiam = Double.valueOf(this.hsttk.getHsthtoankXetgiam() == null ? 0.0D : this.hsttk.getHsthtoankXetgiam().doubleValue());
     }
     List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonma(this.tiepdon.getTiepdonMa());
     for (ClsKham eachCls : listClsKham)
     {
       curGia = Double.valueOf(eachCls.getClskhamDongiabntra() == null ? 0.0D : eachCls.getClskhamDongiabntra().doubleValue());
       curGiaDV = Double.valueOf(eachCls.getClskhamPhandv() == null ? 0.0D : eachCls.getClskhamPhandv().doubleValue());
       if ((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true))
       {
         hasDV = true;
         if ((this.tiepdon.getDoituongMa() != null) &&
           (this.tiepdon.getDoituongMa().getDmdoituongMa().equalsIgnoreCase("MP")))
         {
           curGia = Double.valueOf(eachCls.getClskhamMahang().getDtdmclsbgDongiamp() == null ? 0.0D : eachCls.getClskhamMahang().getDtdmclsbgDongiamp().doubleValue());

           curGiaDV = Double.valueOf((eachCls.getClskhamDongia() == null ? 0.0D : eachCls.getClskhamDongiabntra().doubleValue()) - curGia.doubleValue());
         }
       }
       tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGiaDV.doubleValue());
       if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("MA"))
       {
         mau = Double.valueOf(mau.doubleValue() + curGia.doubleValue());
         mauDV = Double.valueOf(mauDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("XN"))
       {
         xntdcn = Double.valueOf(xntdcn.doubleValue() + curGia.doubleValue());
         xntdcnDV = Double.valueOf(xntdcnDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("CD"))
       {
         cdha = Double.valueOf(cdha.doubleValue() + curGia.doubleValue());
         cdhaDV = Double.valueOf(cdhaDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("PT")) || (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("TT")))
       {
         pttt = Double.valueOf(pttt.doubleValue() + curGia.doubleValue());
         ptttDV = Double.valueOf(ptttDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamKtcao() != null) && (eachCls.getClskhamKtcao().booleanValue() == true))
       {
         ktc = Double.valueOf(ktc.doubleValue() + curGia.doubleValue());
         ktcDV = Double.valueOf(ktcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("GI"))
       {
         dvp = Double.valueOf(dvp.doubleValue() + curGia.doubleValue());
         dvpDV = Double.valueOf(dvpDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if ((eachCls.getClskhamMaloai() != null) && (eachCls.getClskhamMaloai().getDtdmclsMaso().intValue() == 12))
       {
         vc = Double.valueOf(vc.doubleValue() + curGia.doubleValue());
         vcDV = Double.valueOf(vcDV.doubleValue() + curGiaDV.doubleValue());
       }
       else if (eachCls.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH"))
       {
         ck = Double.valueOf(ck.doubleValue() + curGia.doubleValue());
         ckDV = Double.valueOf(ckDV.doubleValue() + curGiaDV.doubleValue());
       }
       else
       {
         clskhac = Double.valueOf(clskhac.doubleValue() + curGia.doubleValue());
         clskhacDV = Double.valueOf(clskhacDV.doubleValue() + curGiaDV.doubleValue());
       }
     }
     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
     Double thuocDV = Double.valueOf(0.0D);
     Double vtthDV = Double.valueOf(0.0D);
     Double thuocBN = Double.valueOf(0.0D);
     Double vtthBN = Double.valueOf(0.0D);
     List<ThuocPhongKham> listTpk_Bankham = tpkDelegate.findByMaTiepDon(this.tiepdon.getTiepdonMa(), "1");
     log.info("listTpk_Bankham.size() = " + listTpk_Bankham.size());
     for (ThuocPhongKham eachTpk : listTpk_Bankham)
     {
       curGia = Double.valueOf(eachTpk.getThuocphongkhamTienbntra() == null ? 0.0D : eachTpk.getThuocphongkhamTienbntra().doubleValue());
       if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) {
         hasDV = true;
       }
       if ((eachTpk.getThuocphongkhamMathuoc() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10))
       {
         if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
         {
           vtthDV = Double.valueOf(vtthDV.doubleValue() + curGia.doubleValue());
           tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
         }
         else
         {
           vtthBN = Double.valueOf(vtthBN.doubleValue() + curGia.doubleValue());
           tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         }
       }
       else if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
       {
         thuocDV = Double.valueOf(thuocDV.doubleValue() + curGia.doubleValue());
         tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
       }
       else
       {
         thuocBN = Double.valueOf(thuocBN.doubleValue() + curGia.doubleValue());
         tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
       }
     }
     log.info("tongtien 1 = " + tongtien);

     List<ThuocPhongKham> listTpk_BHYT = tpkDelegate.findByMaTiepDon(this.tiepdon.getTiepdonMa(), "3");
     log.info("listTpk_BHYT.size() = " + listTpk_BHYT.size());
     for (ThuocPhongKham eachTpk : listTpk_BHYT)
     {
       log.info("bn tra = " + eachTpk.getThuocphongkhamTienbntra());
       curGia = Double.valueOf(eachTpk.getThuocphongkhamTienbntra() == null ? 0.0D : eachTpk.getThuocphongkhamTienbntra().doubleValue());
       if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) {
         hasDV = true;
       }
       log.info("Loai thuoc ma = " + eachTpk.getThuocphongkhamMathuoc().getDmphanloaithuocMaso().getDmloaithuocMaso().getDmloaithuocMa());
       if ((eachTpk.getThuocphongkhamMathuoc() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (eachTpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10))
       {
         log.info("thuc hien o day 1 ");
         if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
         {
           log.info("thuc hien o day 1.1 ");
           vtthDV = Double.valueOf(vtthDV.doubleValue() + curGia.doubleValue());
           tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
         }
         else
         {
           log.info("thuc hien o day 1.2 ");
           vtthBN = Double.valueOf(vtthBN.doubleValue() + curGia.doubleValue());
           tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         }
       }
       else
       {
         log.info("thuc hien o day 2 ");
         if ((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true))
         {
           log.info("thuc hien o day 2.1 ");
           thuocDV = Double.valueOf(thuocDV.doubleValue() + curGia.doubleValue());
           tongtienDV = Double.valueOf(tongtienDV.doubleValue() + curGia.doubleValue());
         }
         else
         {
           log.info("thuc hien o day 2.2 ");
           thuocBN = Double.valueOf(thuocBN.doubleValue() + curGia.doubleValue());
           tongtien = Double.valueOf(tongtien.doubleValue() + curGia.doubleValue());
         }
       }
     }
     log.info("tongtien 2 = " + tongtien);

     String diachistr = "";
     if (this.benhnhan.getBenhnhanDiachi() != null) {
       diachistr = diachistr + this.benhnhan.getBenhnhanDiachi() + ", ";
     }
     if (this.benhnhan.getXaMa(true).getDmxaTen() != null) {
       diachistr = diachistr + this.benhnhan.getXaMa(true).getDmxaTen() + ", ";
     }
     if (this.benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
       diachistr = diachistr + this.benhnhan.getHuyenMa(true).getDmhuyenTen() + ", ";
     }
     if (this.benhnhan.getTinhMa(true).getDmtinhTen() != null) {
       diachistr = diachistr + this.benhnhan.getTinhMa(true).getDmtinhTen();
     }
     String thungan = "";
     if (this.hsttk.getHsthtoankThungan() != null)
     {
       if (this.hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null) {
         thungan = this.hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
       } else {
         thungan = "";
       }
     }
     else {
       thungan = "";
     }
     log.info("hasDV = " + hasDV);
     tongtien = Utils.rounDoubleForReport(tongtien);
     log.info("tongtien 3 = " + tongtien + ", miengiam = " + miengiam);
     tongtienDV = Utils.rounDoubleForReport(tongtienDV);

     int lanIn = this.hsttk.getHsthtoankLanin() == null ? 1 : Integer.valueOf(this.hsttk.getHsthtoankLanin()).intValue() + 1;
     this.hsttk.setHsthtoankLanin("" + lanIn);
     if ((hasDV) && (tongtienDV.doubleValue() > 0.0D)) {
       try
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         String pathTemplate_sub4 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport0.jrxml";
         String pathTemplate_sub5 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport1.jrxml";
         String pathTemplate_sub6 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_hoadon_subreport2.jrxml";
         log.info("da thay file templete bienlaithulephi_hoadon " + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);

         JasperReport jasperSub4 = JasperCompileManager.compileReport(pathTemplate_sub4);
         JasperReport jasperSub5 = JasperCompileManager.compileReport(pathTemplate_sub5);
         JasperReport jasperSub6 = JasperCompileManager.compileReport(pathTemplate_sub6);
         log.info("da thay file template ");

         Map<String, Object> params = new HashMap();

         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);
         params.put("sub4", jasperSub4);
         params.put("sub5", jasperSub5);
         params.put("sub6", jasperSub6);


         params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
         params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
         params.put("HOTEN", this.benhnhan.getBenhnhanHoten());
         params.put("DIACHI", diachistr);
         params.put("THUNGAN", thungan);


         params.put("NOIDUNG", this.sNoiDungThu);
         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam.doubleValue()));
         params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
         params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
         params.put("MAU", Utils.formatNumberWithSeprator(mau));
         params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
         params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
         params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
         params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
         params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
         params.put("DVP", Utils.formatNumberWithSeprator(Double.valueOf(dvp.doubleValue() + dvpDV.doubleValue())));
         params.put("CV", Utils.formatNumberWithSeprator(vc));
         params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));


         params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         params.put("THUOCDV", Utils.formatNumberWithSeprator(thuocDV));
         params.put("VTTHDV", Utils.formatNumberWithSeprator(vtthDV));
         params.put("CLSDV", Utils.formatNumberWithSeprator(clskhacDV));
         params.put("MAUDV", Utils.formatNumberWithSeprator(mauDV));
         params.put("XNTDCNDV", Utils.formatNumberWithSeprator(xntdcnDV));
         params.put("CDHADV", Utils.formatNumberWithSeprator(cdhaDV));
         params.put("PTTTDV", Utils.formatNumberWithSeprator(ptttDV));
         params.put("DVKTCDV", Utils.formatNumberWithSeprator(ktcDV));
         params.put("DVPDV", Utils.formatNumberWithSeprator(new Double(0.0D)));
         params.put("CVDV", Utils.formatNumberWithSeprator(vcDV));
         params.put("CONGKHAMDV", Utils.formatNumberWithSeprator(ckDV));
         params.put("SOTIENDV", Utils.formatNumberWithSeprator(tongtienDV));
         params.put("TIENBANGCHUDV", Utils.NumberToString(tongtienDV.doubleValue()));

         params.put("LANIN", "" + lanIn);

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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "InphieuXHBNBHYT");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathKC);
         this.index += 1;
         log.info("Index :" + getIndex());
         if (conn != null) {
           conn.close();
         }
         hsttkDelegate.edit(this.hsttk);
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     } else {
       try
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1.jrxml";
         String pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub1.jrxml";
         String pathTemplate_sub2 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub2.jrxml";
         String pathTemplate_sub3 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithulephi_mau1_sub3.jrxml";
         log.info("da thay file templete bienlaithulephi_mau1" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport jasperSub1 = JasperCompileManager.compileReport(pathTemplate_sub1);
         JasperReport jasperSub2 = JasperCompileManager.compileReport(pathTemplate_sub2);
         JasperReport jasperSub3 = JasperCompileManager.compileReport(pathTemplate_sub3);
         log.info("da thay file template ");

         Map<String, Object> params = new HashMap();

         params.put("sub1", jasperSub1);
         params.put("sub2", jasperSub2);
         params.put("sub3", jasperSub3);



         params.put("DIADIEM", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("BVBD", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         params.put("DIACHI_BV", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
         params.put("MASOTHUE", IConstantsRes.REPORT_DIEUTRI_MA_SO_THUE);
         params.put("HOTEN", this.benhnhan.getBenhnhanHoten());

         params.put("DIACHI", diachistr);
         params.put("NOIDUNG", this.sNoiDungThu);
         log.info("tongtien 4 = " + tongtien + ", miengiam = " + miengiam);
         params.put("SOTIEN", Utils.formatNumberWithSeprator(Double.valueOf(tongtien.doubleValue() - miengiam.doubleValue())));
         params.put("TIENBANGCHU", Utils.NumberToString(tongtien.doubleValue() - miengiam.doubleValue()));



         params.put("CLS", Utils.formatNumberWithSeprator(clskhac));
         params.put("THUOC", Utils.formatNumberWithSeprator(thuocBN));
         params.put("MAU", Utils.formatNumberWithSeprator(mau));
         params.put("XNTDCN", Utils.formatNumberWithSeprator(xntdcn));
         params.put("CDHA", Utils.formatNumberWithSeprator(cdha));
         params.put("PTTT", Utils.formatNumberWithSeprator(pttt));
         params.put("DVKTC", Utils.formatNumberWithSeprator(ktc));
         params.put("VTTH", Utils.formatNumberWithSeprator(vtthBN));
         params.put("DVP", Utils.formatNumberWithSeprator(Double.valueOf(dvp.doubleValue() + dvpDV.doubleValue())));
         params.put("CV", Utils.formatNumberWithSeprator(vc));
         params.put("CONGKHAM", Utils.formatNumberWithSeprator(ck));
         params.put("MIENGIAM", Utils.formatNumberWithSeprator(miengiam));
         params.put("THUNGAN", thungan);

         params.put("LANIN", "" + lanIn);

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
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "InphieuXHBNBHYT");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathKC);
         this.index += 1;
         log.info("Index :" + getIndex());
         if (conn != null) {
           conn.close();
         }
         hsttkDelegate.edit(this.hsttk);
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
   }

   public void huyPhieu()
   {
     log.info("Begin huyPhieu(), hsttk = " + this.hsttk + ", maPhieu = " + this.maPhieu);
     if (this.hsttk != null) {
       if (this.hsttk.getHsthtoankNgaygiott() != null)
       {
         if (this.hsttk.getHsthtoankMa() == null) {
           this.hsttk.setHsthtoankMa(this.maPhieu);
         }
         try
         {
           ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
           List<ClsKham> listCls = clsKhamDel.findByTiepdonma(this.hsttk.getTiepdonMa().getTiepdonMa());
           if ((listCls != null) && (listCls.size() > 0)) {
             for (ClsKham eachCls : listCls)
             {
               eachCls.setClskhamDatt(null);
               eachCls.setClskhamNgaygiott(null);
               eachCls.setClskhamMaphieu(null);
               clsKhamDel.edit(eachCls);
             }
           }
           ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
           List<ThuocPhongKham> listTpk = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "1");
           if ((listTpk != null) && (listTpk.size() > 0)) {
             for (ThuocPhongKham eachTpk : listTpk)
             {
               eachTpk.setThuocphongkhamDatt(null);
               eachTpk.setThuocphongkhamNgaygiott(null);
               eachTpk.setThuocphongkhamMaphieud(null);
               tpkDelegate.edit(eachTpk);
             }
           }
           listTpk = tpkDelegate.findByMaTiepDon(this.hsttk.getTiepdonMa().getTiepdonMa(), "3");
           if ((listTpk != null) && (listTpk.size() > 0)) {
             for (ThuocPhongKham eachTpk : listTpk)
             {
               eachTpk.setThuocphongkhamDatt(null);
               eachTpk.setThuocphongkhamNgaygiott(null);
               eachTpk.setThuocphongkhamMaphieud(null);
               tpkDelegate.edit(eachTpk);
             }
           }
           this.hsttk.setHsthtoankDatt(null);
           this.hsttk.setHsthtoankNgaygiott(null);
           this.hsttk.setHsthtoankLanin(null);
           HsThtoankDelegate.getInstance().edit(this.hsttk);
           FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.hsttk.getHsthtoankMa() });
           resetForm();
         }
         catch (Exception ex)
         {
           ex.printStackTrace();
         }
       }
     }
     log.info("End huyPhieu()");
   }

   public String inPhieuThanhToan()
   {
     return XuatPhieuThanhToan();
   }

   public String XuatPhieuThanhToan()
   {
     log.info("begin XuatPhieuThanhToan() tiepdon = " + this.tiepdon);
     if (this.tiepdon == null) {
       return null;
     }
     this.reportTypeKC = "PhieuThanhToanKCB";

     String baocao1 = null;
     try
     {
       log.info("Vao Method XuatPhieuThanhToan() kham chua benh ngoai tru");

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("MATIEPDON", this.tiepdon.getTiepdonMa());
       params.put("HOTENBN", this.benhnhan.getBenhnhanHoten());

       String diachistr = "";
       if (this.benhnhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhnhan.getBenhnhanDiachi() + ", ";
       }
       if (this.benhnhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhnhan.getXaMa(true).getDmxaTen() + ", ";
       }
       if (this.benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhnhan.getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (this.benhnhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhnhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (this.tiepdon.getDoituongMa(true).getDmdoituongMa().equals("BH"))
       {
         params.put("BHYT_CO", "X");
         if (((this.tiepdon.getTiepdonTuyen() != null) && (this.tiepdon.getTiepdonTuyen().toString().equals("1"))) || ((this.tiepdon.getTiepdonCoGiayGioiThieu() != null) && (this.tiepdon.getTiepdonCoGiayGioiThieu().booleanValue()))) {
           params.put("DUNGTUYEN", "X");
         } else {
           params.put("TRAITUYEN", "X");
         }
       }
       else
       {
         params.put("BHYT_KO", "X");
       }
       if (this.tiepdon.getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(this.tiepdon.getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (this.tiepdon.getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(this.tiepdon.getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((this.tiepdon.getTiepdonSothebh() != null) && (!this.tiepdon.getTiepdonSothebh().equals("")) && (this.tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa() != null) && (!this.tiepdon.getKhoibhytMa(true).getDtdmkhoibhytMa().equals("")) && (this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa() != null))
       {
         params.put("MATHEBHYT", this.tiepdon.getTiepdonSothebh());
         params.put("MABENHVIEN", this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa().replace(".", "-"));
       }
       else
       {
         params.put("MABENHVIEN", "");
         params.put("MATHEBHYT", "");
       }
       if (this.tiepdon.getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", this.tiepdon.getKcbbhytMa(true).getDmbenhvienTen());
       }
       if (this.tiepdon.getTinhbhytMa(true).getDmtinhTen() != null) {
         params.put("NOICAPTHE", this.tiepdon.getTinhbhytMa(true).getDmtinhTen());
       }
       if (this.tiepdon.getTiepdonDonvigoi(true).getDmbenhvienMa() != null) {
         params.put("NOIGIOITHIEU", this.tiepdon.getTiepdonDonvigoi(true).getDmbenhvienTen());
       }
       Calendar cal = Calendar.getInstance();
       cal.setTime(this.thamkham.getThamkhamNgaygio());
       params.put("NGAYKHAMBENH", new Timestamp(cal.getTimeInMillis()));

       params.put("LYDOVAOVIEN", this.tiepdon.getTiepdonLydovaov());

       HsThtoankDelegate thanhToanDel = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = new HsThtoank();
       hsttk = thanhToanDel.findBytiepdonMaLast(this.tiepdon.getTiepdonMa());
       if ((hsttk == null) || ((hsttk.getHsthtoankDatt() != null) && (!hsttk.getHsthtoankDatt().booleanValue())))
       {
         hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.tiepdon);

         ThamKhamUtil tkUtil = new ThamKhamUtil();
         tkUtil.tinhToanChoHSTKKham(this.thamkham, hsttk, Boolean.valueOf(false), null, null);
         Utils.setInforForHsThToan(hsttk);
       }
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh() + ", bhyt = " + hsttk.getHsthtoankBhyt());

       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankBntra());
       params.put("NGUONKHAC", hsttk.getHsthtoankNguonkhactra());
       if (hsttk.getHsthtoankNgaygiott() != null)
       {
         params.put("NGAYTHANHTOAN", hsttk.getHsthtoankNgaygiott());
         if (this.thamkham.getThamkhamNgaygio() != null) {
           params.put("SONGAYDT", Long.valueOf(Utils.getDaysBetween(this.thamkham.getThamkhamNgaygio(), hsttk.getHsthtoankNgaygiott())));
         }
       }
       if ((this.tiepdon.getTiepdonBankham() != null) &&
         (this.tiepdon.getTiepdonBankham(true).getDtdmbankhamMa().startsWith("CC")))
       {
         params.put("CAPCUU", "X");
         params.put("DUNGTUYEN", "");
         params.put("TRAITUYEN", "");
       }
       params.put("PHIEUSO", hsttk.getHsthtoankMa());
       log.info("Ty le bao hiem : " + hsttk.getHsthtoankTylebh());
       params.put("TYLEBH", hsttk.getHsthtoankTylebh());
       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       if ("MP".equals(this.tiepdon.getDoituongMa(true).getDmdoituongMa())) {
         tyleBNtra = "0";
       }
       params.put("PHANTRAMBNTRA", tyleBNtra);

       String soBienlai = "";
       if (hsttk.getHsthtoankKyhieu() != null) {
         soBienlai = hsttk.getHsthtoankKyhieu();
       }
       if (hsttk.getHsthtoankQuyen() != null) {
         soBienlai = soBienlai + " - " + hsttk.getHsthtoankQuyen();
       }
       if (hsttk.getHsthtoankBienlai() != null) {
         soBienlai = soBienlai + " - " + hsttk.getHsthtoankBienlai();
       }
       params.put("BIENLAISO", soBienlai);


       String thungan1 = "";
       if (hsttk != null) {
         if (hsttk.getHsthtoankThungan() != null)
         {
           if (hsttk.getHsthtoankThungan().getDtdmnhanvienTen() != null) {
             thungan1 = hsttk.getHsthtoankThungan().getDtdmnhanvienTen();
           } else {
             thungan1 = "";
           }
         }
         else {
           thungan1 = "";
         }
       }
       params.put("THUNGAN1", thungan1);

       String namsinh = "";
       if (this.tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh() != null) {
         namsinh = sdf.format(this.tiepdon.getBenhnhanMa(true).getBenhnhanNgaysinh());
       } else {
         namsinh = this.tiepdon.getBenhnhanMa(true).getBenhnhanNamsinh();
       }
       params.put("namsinh", namsinh);

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           params.put("MABENHICD", maChanDoan);
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((this.thamkham.getThamkhamGhichu() != null) && (!this.thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + this.thamkham.getThamkhamGhichu();
       }
       if ((this.thamkham.getBenhicd10phu1() != null) && (this.thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu2() != null) && (this.thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           if (chanDoan.equals("")) {
             chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
           } else {
             chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
           }
         }
       }
       params.put("CHANDOAN", chanDoan);

       params.put("PHONGKHAM", this.thamkham.getThamkhamBankham().getDtdmbankhamTen());

       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(this.tiepdon.getTiepdonMa());


       StringBuffer bufStr = new StringBuffer();
       Double tongTienDV = Double.valueOf(0.0D);
       List<Integer> listMasoKhoa;
       if ((listTk != null) && (listTk.size() > 0))
       {
         listMasoKhoa = new ArrayList(listTk.size());
         for (ThamKham each : listTk) {
           if (each.getThamkhamBankham().getDmkhoaMaso() != null) {
             if (!listMasoKhoa.contains(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso()))
             {
               listMasoKhoa.add(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaMaso());
               if (bufStr.length() > 0) {
                 bufStr.append(", " + each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
               } else {
                 bufStr.append(each.getThamkhamBankham().getDmkhoaMaso().getDmkhoaTen());
               }
             }
           }
         }
       }
       List<ClsKham> listClsTmp = ClsKhamDelegate.getInstance().findByTiepdonma(this.tiepdon.getTiepdonMa());
       if ((listClsTmp != null) && (listClsTmp.size() > 0)) {
         for (ClsKham eachCls : listClsTmp) {
           if (((eachCls.getClskhamYeucau() != null) && (eachCls.getClskhamYeucau().booleanValue() == true)) || ((eachCls.getClskhamNdm() != null) && (eachCls.getClskhamNdm().booleanValue() == true) && (eachCls.getClskhamPhandv() != null))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachCls.getClskhamPhandv().doubleValue());
           }
         }
       }
       List<ThuocPhongKham> listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.tiepdon.getTiepdonMa(), "1");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamThanhtien().doubleValue());
           }
         }
       }
       listTpk = ThuocPhongKhamDelegate.getInstance().findByMaTiepDon(this.tiepdon.getTiepdonMa(), "3");
       if ((listTpk != null) && (listTpk.size() > 0)) {
         for (ThuocPhongKham eachTpk : listTpk) {
           if (((eachTpk.getThuocphongkhamYeucau() != null) && (eachTpk.getThuocphongkhamYeucau().booleanValue() == true)) || ((eachTpk.getThuocphongkhamNdm() != null) && (eachTpk.getThuocphongkhamNdm().booleanValue() == true))) {
             tongTienDV = Double.valueOf(tongTienDV.doubleValue() + eachTpk.getThuocphongkhamThanhtien().doubleValue());
           }
         }
       }
       params.put("KHOA", bufStr.toString());
       String soTheTe_KhaiSinh_ChungSinh = this.thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.tiepdon.getTiepdonKhaisinh() != null) && (!this.tiepdon.getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.tiepdon.getTiepdonKhaisinh();
         }
       }
       else if ((this.tiepdon.getTiepdonKhaisinh() != null) && (!this.tiepdon.getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.tiepdon.getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((this.tiepdon.getTiepdonChungsinh() != null) && (!this.tiepdon.getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = this.tiepdon.getTiepdonChungsinh();
         }
       }
       else if ((this.tiepdon.getTiepdonChungsinh() != null) && (!this.tiepdon.getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + this.tiepdon.getTiepdonChungsinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       String soTheNgheo = this.tiepdon.getTiepdonThengheo();
       if ((soTheNgheo != null) && (!soTheNgheo.equals(""))) {
         params.put("SOTHENGHEO", soTheNgheo);
       } else {
         params.put("SOTHENGHEO", null);
       }
       params.put("TONGCHIPHI", hsttk.getHsthtoankTongchi());
       params.put("BANGCHU1", Utils.NumberToString(hsttk.getHsthtoankTongchi().doubleValue()));
       params.put("BANGCHU2", Utils.NumberToString(hsttk.getHsthtoankBntra().doubleValue()));
       params.put("BANGCHU3", Utils.NumberToString(hsttk.getHsthtoankBhyt().doubleValue()));
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (hsttk.getHsthtoankBntra().doubleValue() >= 0.0D) {
         params.put("SNGUOIBENHTRA", "0");
       } else {
         params.put("SNGUOIBENHTRA", "-1");
       }
       DmGioiTinh gioi = (DmGioiTinh)DieuTriUtilDelegate.getInstance().findByMaSo(this.tiepdon.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMaso(), "DmGioiTinh", "dmgtMaso");
       if (gioi != null) {
         params.put("GIOI", gioi.getDmgtTen());
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       log.info("tongTienDV = " + tongTienDV);
       JasperReport jasperReport;
       if (tongTienDV.doubleValue() > 0.0D)
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";
         String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport0.jrxml";
         String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_DV_subreport1.jrxml";

         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
         JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
         JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
         params.put("sub2", sub2Report);
         params.put("sub3", sub3Report);
       }
       else
       {
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport0.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuthanhtoanKCBngoaitru_subreport1.jrxml";

         jasperReport = JasperCompileManager.compileReport(pathTemplate);
         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);
       }
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, getIndex(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "PhieuThanhToanKCB");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       setIndex(getIndex() + 1);
       log.info("Index :" + getIndex());
       if (conn != null)
       {
         conn.close();
         conn = null;
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatPhieuThanhToan!!!");
       e.printStackTrace();
       return null;
     }
     log.info("Thoat Method XuatPhieuThanhToan");
     return "B4160_Chonmenuxuattaptin";
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

   public ArrayList<ThuocPhongKham> getListThuocPK()
   {
     return this.listThuocPK;
   }

   public void setListThuocPK(ArrayList<ThuocPhongKham> listThuocPK)
   {
     this.listThuocPK = listThuocPK;
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

   public String getNvPhatTen()
   {
     return this.nvPhatTen;
   }

   public void setNvPhatTen(String nvPhatTen)
   {
     this.nvPhatTen = nvPhatTen;
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

   public String getReturnToThanhToanBNBHYT()
   {
     return this.returnToThanhToanBNBHYT;
   }

   public void setReturnToThanhToanBNBHYT(String returnToThanhToanBNBHYT)
   {
     this.returnToThanhToanBNBHYT = returnToThanhToanBNBHYT;
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

   public String getKyhieu()
   {
     return this.kyhieu;
   }

   public void setKyhieu(String kyhieu)
   {
     this.kyhieu = kyhieu;
   }

   public String getQuyen()
   {
     return this.quyen;
   }

   public void setQuyen(String quyen)
   {
     this.quyen = quyen;
   }

   public String getBienlai()
   {
     return this.bienlai;
   }

   public void setBienlai(String bienlai)
   {
     this.bienlai = bienlai;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getCheckThanhToan()
   {
     return this.checkThanhToan;
   }

   public void setCheckThanhToan(String checkThanhToan)
   {
     this.checkThanhToan = checkThanhToan;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public String getGioThanhToan()
   {
     return this.gioThanhToan;
   }

   public void setGioThanhToan(String gioThanhToan)
   {
     this.gioThanhToan = gioThanhToan;
   }

   public String getStrBankham()
   {
     return this.strBankham;
   }

   public void setStrBankham(String strBankham)
   {
     this.strBankham = strBankham;
   }

   public Double getNguonkhactra()
   {
     return this.nguonkhactra;
   }

   public void setNguonkhactra(Double nguonkhactra)
   {
     this.nguonkhactra = nguonkhactra;
   }

   public Double getNguonkhactra_hid()
   {
     return this.nguonkhactra_hid;
   }

   public void setNguonkhactra_hid(Double nguonkhactraHid)
   {
     this.nguonkhactra_hid = nguonkhactraHid;
   }

   public Double getBenhnhanTra_hid()
   {
     return this.benhnhanTra_hid;
   }

   public void setBenhnhanTra_hid(Double benhnhanTraHid)
   {
     this.benhnhanTra_hid = benhnhanTraHid;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThanhToanBenhNhanBHYTAction

 * JD-Core Version:    0.7.0.1

 */