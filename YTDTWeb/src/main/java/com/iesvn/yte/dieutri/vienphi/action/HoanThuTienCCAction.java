 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.SESSION)
 @Name("B3238_HoanThuTienCC")
 @Synchronized(timeout=6000000L)
 public class HoanThuTienCCAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(HoanThuTienCCAction.class);
   @DataModel
   private ArrayList<ThuocPhongKham> listTpk;
   @DataModelSelection
   @Out(required=false)
   private ThuocPhongKham tpkSelected;
   private TiepDon tiepDon;
   private BenhNhan benhNhan;
   private ThamKham thamKham;
   private String ngayHienTai;
   private String maThuocPk;
   private String maBanKham;
   private Integer gioiTinh;
   private String ngaySinh;
   private String tkMa;
   private String soLuong;
   private String maThuoc;
   private int count;
   private Double bnTra;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Double khongThu = new Double(0.0D);
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private String kyhieu;
   private String quyen;
   private String bienlai;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private DtDmCum cum = null;

   @Create
   public void init()
   {
     logger.info("-----init()-----");

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
     logger.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     logger.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       this.nhanVienThungan = pc.getDtdmnhanvienMa();
       logger.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     reset();
   }

   private void tinhToanChiPhi(ArrayList<ThuocPhongKham> listTpk)
   {
     HsThtoank hsttk = new HsThtoank();
     hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());
     HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtil.tinhToanChoHSTKKham(hsttk);

     this.permiengiam = 0;
     this.miengiam = hsthtoankUtil.getMiengiam();
     this.thatthu = hsthtoankUtil.getThatthu();
     this.perbhytchi = hsthtoankUtil.getPerbhytchi();
     this.bhytchi = hsthtoankUtil.getBhytchi();
     this.thanhtien1 = hsthtoankUtil.getThanhtien1();
     this.perbntra = hsthtoankUtil.getPerbntra();
     this.bntra = hsthtoankUtil.getBntra();
     this.khongThu = hsthtoankUtil.getKhongThu();
   }

   public void end()
     throws ParseException
   {
     logger.info("-----end()-----");
     if (this.listTpk.size() > 0)
     {
       for (ThuocPhongKham tpk : this.listTpk) {
         if ((tpk.getThuocphongkhamMaphieud() != null) && (!tpk.getThuocphongkhamMaphieud().equals("")) && ((tpk.getThuocphongkhamMaphieuh() == null) || (tpk.getThuocphongkhamMaphieuh().equals(""))))
         {
           tpk.setThuocphongkhamThunganHoan(this.nhanVienThungan);
           tpk.setThuocphongkhamCumHoan(this.cum);

           SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);


           tpk.setThuocphongkhamKyhieuHoan(this.kyhieu);
           tpk.setThuocphongkhamQuyenHoan(this.quyen);
           tpk.setThuocphongkhamBienlaiHoan(this.bienlai);

           Date d = formatter.parse(this.ngayHienTai);
           Calendar dCalendar = Calendar.getInstance();
           dCalendar.setTime(d);

           tpk.setThuocphongkhamNgaygioHoanThu(dCalendar.getTime());
         }
       }
       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       try
       {
         if (tpkDelegate.createHoanThu(this.listTpk, null).booleanValue()) {
           FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         } else {
           FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         }
       }
       catch (Exception e)
       {
         logger.error(e);
       }
       SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     }
   }

   public void displayInfo()
   {
     logger.info("displayInfo");
     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     List<ThuocPhongKham> listThuoc = null;
     try
     {
       listThuoc = tpkDelegate.findByMaPhieu(this.maThuocPk);
     }
     catch (Exception e)
     {
       logger.info(e);
     }
     logger.info("listThuoc:" + listThuoc);
     if ((listThuoc != null) && (listThuoc.size() > 0))
     {
       this.listTpk = ((ArrayList)listThuoc);
       for (ThuocPhongKham tpk : this.listTpk)
       {
         if ((tpk.getThuocphongkhamMaphieuh() != null) && (!tpk.getThuocphongkhamMaphieuh().equals("")))
         {
           FacesMessages.instance().add(IConstantsRes.PHIEU_DA_HOAN_THU, new Object[] { this.maThuocPk });
           reset();
           return;
         }
         this.thamKham = tpk.getThuocphongkhamThamkham();
         this.tiepDon = this.thamKham.getTiepdonMa();
         this.benhNhan = this.tiepDon.getBenhnhanMa();





         logger.info("-----benh nhan: " + this.benhNhan.getBenhnhanMa());
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         this.ngaySinh = df.format(this.benhNhan.getBenhnhanNgaysinh());
         Date ngayGioTmp = tpk.getThuocphongkhamNgaygioHoanThu();
         if (ngayGioTmp != null) {
           this.ngayHienTai = df.format(ngayGioTmp);
         }
         this.maBanKham = this.thamKham.getThamkhamBankham().getDtdmbankhamMa();
         this.maThuocPk = tpk.getThuocphongkhamMaphieud();
         this.count = this.listTpk.size();
       }
       this.kyhieu = ((ThuocPhongKham)this.listTpk.get(0)).getThuocphongkhamKyhieuHoan();
       this.quyen = ((ThuocPhongKham)this.listTpk.get(0)).getThuocphongkhamQuyenHoan();
       this.bienlai = ((ThuocPhongKham)this.listTpk.get(0)).getThuocphongkhamBienlaiHoan();
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { this.maThuocPk });
       reset();
     }
     tinhToanChiPhi(this.listTpk);

     logger.info("end");
   }

   public void reset()
   {
     logger.info("-----reset()-----");
     this.listTpk = new ArrayList();
     this.tpkSelected = new ThuocPhongKham();
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();
     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     this.tiepDon = new TiepDon();
     SetInforUtil.setInforIfNullForTiepDon(this.tiepDon);


     this.maBanKham = "";
     this.maThuocPk = "";
     this.maThuoc = "";
     this.count = 0;
     this.ngaySinh = "";


     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.khongThu = new Double(0.0D);


     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setThamKham(ThamKham thamKham)
   {
     this.thamKham = thamKham;
   }

   public ThamKham getThamKham()
   {
     return this.thamKham;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     Date dHt = new Date();
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

     return df.format(dHt);
   }

   public void setMaThuocPk(String maThuocPk)
   {
     this.maThuocPk = maThuocPk;
   }

   public String getMaThuocPk()
   {
     return this.maThuocPk;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setGioiTinh(Integer gioiTinh)
   {
     this.gioiTinh = gioiTinh;
   }

   public Integer getGioiTinh()
   {
     return this.gioiTinh;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
   }

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setTkMa(String tkMa)
   {
     this.tkMa = tkMa;
   }

   public String getTkMa()
   {
     return this.tkMa;
   }

   public void setListTpk(ArrayList<ThuocPhongKham> listTpk)
   {
     this.listTpk = listTpk;
   }

   public ArrayList<ThuocPhongKham> getListTpk()
   {
     return this.listTpk;
   }

   public void setTpkSelected(ThuocPhongKham tpkSelected)
   {
     this.tpkSelected = tpkSelected;
   }

   public ThuocPhongKham getTpkSelected()
   {
     return this.tpkSelected;
   }

   public void setSoLuong(String soLuong)
   {
     this.soLuong = soLuong;
   }

   public String getSoLuong()
   {
     return this.soLuong;
   }

   public void setMaThuoc(String maThuoc)
   {
     this.maThuoc = maThuoc;
   }

   public String getMaThuoc()
   {
     return this.maThuoc;
   }

   public void setCount(int count)
   {
     this.count = count;
   }

   public int getCount()
   {
     return this.count;
   }

   public void setBnTra(Double bnTra)
   {
     this.bnTra = bnTra;
   }

   public Double getBnTra()
   {
     return this.bnTra;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public static Logger getLogger()
   {
     return logger;
   }

   public static void setLogger(Logger logger)
   {
     logger = logger;
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

   public Double getKhongThu()
   {
     return this.khongThu;
   }

   public void setKhongThu(Double khongThu)
   {
     this.khongThu = khongThu;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.HoanThuTienCCAction

 * JD-Core Version:    0.7.0.1

 */