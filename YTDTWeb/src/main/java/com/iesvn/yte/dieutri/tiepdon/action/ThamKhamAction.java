 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.Constructor;
 import java.lang.reflect.Field;
 import java.text.SimpleDateFormat;
 import java.util.Date;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B121_1_Thamkham")
 @Synchronized(timeout=6000000L)
 public class ThamKhamAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String gioThamKham;
   private String diengiai;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToThamKham;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   private boolean updateNhapct = false;
   private String loai = "";
   private String maSo = "";
   private String maKhoa = "";
   private Boolean mien = new Boolean(false);
   private Boolean ngoaiDanhMuc = new Boolean(false);
   private Boolean yeuCau = new Boolean(false);
   private Boolean kyThuatCao = new Boolean(false);
   private String tenCLS = "";
   private short soLuong = 0;
   private Double donGia = new Double(0.0D);
   private DmDoiTuong doiTuong = null;

   public void resetValue()
   {
     this.loai = "";
     this.maSo = "";
     this.maKhoa = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.tenCLS = "";
   }

   private String resultHidden = "";

   public void CopyFrom(ClsKham cls)
   {
     this.loai = cls.getClskhamLoai();
     this.maSo = cls.getClskhamMahang().getDtdmclsbgMa();
     this.maKhoa = cls.getClskhamKhoa().getDmkhoaMa();
     this.mien = cls.getClskhamMien();
     this.ngoaiDanhMuc = cls.getClskhamNdm();
     this.yeuCau = cls.getClskhamYeucau();
     this.kyThuatCao = cls.getClskhamKtcao();
     this.soLuong = cls.getClskhamLan().shortValue();
     this.donGia = cls.getClskhamDongia();
   }

   public void CopyTo(ClsKham cls)
   {
     cls.setClskhamLoai(this.loai);
     cls.getClskhamMahang().setDtdmclsbgMa(this.maSo);
     cls.getClskhamKhoa().setDmkhoaMa(this.maKhoa);
     cls.setClskhamMien(this.mien);
     cls.setClskhamNdm(this.ngoaiDanhMuc);
     cls.setClskhamYeucau(this.yeuCau);
     cls.setClskhamKtcao(this.kyThuatCao);
     cls.setClskhamLan(Short.valueOf(this.soLuong));
     cls.setClskhamDongia(this.donGia);

     System.out.println("tenCLS:" + this.tenCLS);

     cls.getClskhamMahang().setDtdmclsbgDiengiai(this.tenCLS);
   }

   @Begin(nested=true)
   @Factory("goToThamKham")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       initService();

       log.info("maBanKhamOut:" + this.maBanKhamOut);
       log.info("maTiepDonOut:" + this.maTiepDonOut);

       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       this.doiTuong = this.thamkham.getTiepdonMa().getDoituongMa();
       log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen() = " + this.thamkham.getTiepdonMa().getDoituongMa().getDmdoituongTen());
       log.info("thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa() = " + this.thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa());
       log.info("benhNhan.getBenhnhanMa():" + this.benhNhan.getBenhnhanMa());
       log.info("benhNhan.getBenhnhanNgaysinh():" + this.benhNhan.getBenhnhanNgaysinh());

       log.info("thamkham.getThamkhamBankham().getDtdmbankhamMa():" + this.thamkham.getThamkhamBankham().getDtdmbankhamMa());
       log.info("thamkham.getThamkhamBankham().getDtdmbankhamTen():" + this.thamkham.getThamkhamBankham().getDtdmbankhamTen());
       log.info("thamkham.getThamkhamBankham().getThamkhamNgaygio():" + this.thamkham.getThamkhamNgaygio());

       setOtherValue();

       destroyService();
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
     }
     log.info("***Finished init ***");
   }

   @End
   public void end() {}

   public void initService() {}

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("*****Begin ghiNhanAjax() *****");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       if ((this.thamkham != null) && (this.thamkham.getThamkhamMa() != null))
       {
         ThamKham thamKhamEdit = tkDelegate.find(this.thamkham.getThamkhamMa());
         thamKhamEdit.setThamkhamBenhsu(this.thamkham.getThamkhamBenhsu());
         thamKhamEdit.setThamkhamThamkham(this.thamkham.getThamkhamThamkham());
         thamKhamEdit.setThamkhamXutri(this.thamkham.getThamkhamXutri());
         log.info("*****thamKhamWS.edit(thamKhamEdit) *****");
         tkDelegate.edit(thamKhamEdit);
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.error("*************loi***********" + e.toString());
       return null;
     }
     log.info("*****End ghiNhanAjax() *****");
     return quayLai();
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToThamKham = null;
     log.info("*****End quayLai() *****");
     return "ghinhan";
   }

   public String getThoiGian()
   {
     return this.thoiGian;
   }

   public void setThoiGian(String thoiGian)
   {
     this.thoiGian = thoiGian;
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!this.thamkham.getThamkhamNgaygio().equals(""))) {
       this.thoiGian = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
     }
     if (this.thamkham.getThamkhamNgaygio() != null) {
       this.gioThamKham = Utils.getGioPhut(this.thamkham.getThamkhamNgaygio());
     }
   }

   private void setinfor(ClsKham nhapctSelected)
   {
     DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();

     nhapctSelected.setClskhamMahang(dmkythuat);
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!"".equals(this.thamkham.getThamkhamNgaygio()))) {
       nhapctSelected.setClskhamNgaygio(this.thamkham.getThamkhamNgaygio());
     }
   }

   public void setFieldIesVnIfNull(Object obj)
     throws Exception
   {
     Field[] fList = obj.getClass().getDeclaredFields();
     for (Field field : fList)
     {
       Class c = field.getType();
       if (c.getCanonicalName().startsWith("com.iesvn"))
       {
         Constructor ccc = Class.forName(c.getCanonicalName()).getConstructors()[0];


         field.set(obj, ccc.newInstance(new Object[0]));
       }
     }
   }

   public void displayInfor()
     throws Exception
   {}

   private void setAllNullForClsKham(ClsKham cls)
   {
     log.info("**********Begin setAllNullForClsKham()***********");
     if ("".equals(Utils.reFactorString(cls.getClskhamMahang().getDtdmclsbgMa()))) {
       cls.setClskhamMahang(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa()))) {
       cls.setClskhamKhoa(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamChedott().getDmdoituongMa()))) {
       cls.setClskhamChedott(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa2().getDmkhoaMa()))) {
       cls.setClskhamKhoa2(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMabs().getDtdmnhanvienMa()))) {
       cls.setClskhamMabs(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMaloai().getDtdmclsMa()))) {
       cls.setClskhamMaloai(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn().getDtdmnhanvienMa()))) {
       cls.setClskhamNhanviencn(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThuchien().getDtdmnhanvienMa()))) {
       cls.setClskhamThuchien(null);
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThungan().getDtdmnhanvienMa()))) {
       cls.setClskhamThungan(null);
     }
     log.info("**********End setAllNullForClsKham()***********");
   }

   private void setInforIfNullForThamKham()
   {
     if (this.thamkham.getThamkhamBankham() == null) {
       this.thamkham.setThamkhamBankham(new DtDmBanKham());
     }
     if (this.thamkham.getThamkhamBacsi() == null) {
       this.thamkham.setThamkhamBacsi(new DtDmNhanVien());
     }
     if (this.thamkham.getBenhicd10() == null) {
       this.thamkham.setBenhicd10(new DmBenhIcd());
     }
     if (this.thamkham.getThamkhamKetqua() == null) {
       this.thamkham.setThamkhamKetqua(new DmKetQuaDieuTri());
     }
     if (this.thamkham.getThamkhamDieutri() == null) {
       this.thamkham.setThamkhamDieutri(new DmDieuTri());
     }
     if (this.thamkham.getThamkhamChbankham() == null) {
       this.thamkham.setThamkhamChbankham(new DtDmBanKham());
     }
   }

   private void setInforIfNullForBN()
   {
     if (this.benhNhan.getTinhMa() == null) {
       this.benhNhan.setTinhMa(new DmTinh());
     }
     if (this.benhNhan.getHuyenMa() == null) {
       this.benhNhan.setHuyenMa(new DmHuyen());
     }
     if (this.benhNhan.getXaMa() == null) {
       this.benhNhan.setXaMa(new DmXa());
     }
     if (this.benhNhan.getBenhnhanNghe() == null) {
       this.benhNhan.setBenhnhanNghe(new DmNgheNghiep());
     }
     if (this.benhNhan.getDantocMa() == null) {
       this.benhNhan.setDantocMa(new DmDanToc());
     }
   }

   public void destroyService()
   {
     try
     {
       log.debug("===== begin destroyService() method");

       log.debug("***** End destroyService() method");
     }
     catch (Exception ex)
     {
       log.debug("*****destroyService Exception: " + ex);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("************************* destroy ThamKhamAction");
   }

   public static void main(String[] args) {}

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public int getStt()
   {
     return this.stt;
   }

   public void setStt(int stt)
   {
     this.stt = stt;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getDiengiai()
   {
     return this.diengiai;
   }

   public void setDiengiai(String diengiai)
   {
     this.diengiai = diengiai;
   }

   public String getLoai()
   {
     return this.loai;
   }

   public void setLoai(String loai)
   {
     this.loai = loai;
   }

   public String getMaSo()
   {
     return this.maSo;
   }

   public void setMaSo(String maSo)
   {
     this.maSo = maSo;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public Boolean getMien()
   {
     return this.mien;
   }

   public void setMien(Boolean mien)
   {
     this.mien = mien;
   }

   public Boolean getNgoaiDanhMuc()
   {
     return this.ngoaiDanhMuc;
   }

   public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc)
   {
     this.ngoaiDanhMuc = ngoaiDanhMuc;
   }

   public Boolean getYeuCau()
   {
     return this.yeuCau;
   }

   public void setYeuCau(Boolean yeuCau)
   {
     this.yeuCau = yeuCau;
   }

   public Boolean getKyThuatCao()
   {
     return this.kyThuatCao;
   }

   public void setKyThuatCao(Boolean kyThuatCao)
   {
     this.kyThuatCao = kyThuatCao;
   }

   public short getSoLuong()
   {
     return this.soLuong;
   }

   public void setSoLuong(short soLuong)
   {
     this.soLuong = soLuong;
   }

   public Double getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(Double donGia)
   {
     this.donGia = donGia;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
   }

   public String getGioThamKham()
   {
     return this.gioThamKham;
   }

   public void setGioThamKham(String gioThamKham)
   {
     this.gioThamKham = gioThamKham;
   }

   public DmDoiTuong getDoiTuong()
   {
     return this.doiTuong;
   }

   public void setDoiTuong(DmDoiTuong doiTuong)
   {
     this.doiTuong = doiTuong;
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

   public String getGoToThamKham()
   {
     return this.goToThamKham;
   }

   public void setGoToThamKham(String goToThamKham)
   {
     this.goToThamKham = goToThamKham;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ThamKhamAction

 * JD-Core Version:    0.7.0.1

 */