 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKetQua;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HsttThreadUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.commons.beanutils.BeanUtils;
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
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3121_Canlamsangdieutri")
 @Synchronized(timeout=6000000L)
 public class B3121_CanLamSang_DieuTri
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String position = IConstantsRes.WEB_PATH + "/" + IConstantsRes.WEB_NAME + "/" + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
   private String resultReportName = "";
   private String reportFileNameHid = "";
   private String resultReportFileName = "";
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private static final long serialVersionUID = 10L;
   private BenhNhan benhNhan;
   private Hsba hoSoBenhAn;
   private HsbaBhyt hsbaBHYT;
   private HsbaKhoa hsbaKhoa;
   private HsThtoan hsthtoan;
   private ClsMo clsMo;
   private String gioi;
   private String tuoi;
   private String ngaySinh;
   private String ngayTt;
   private String loai = "";
   private String maCLS = "";
   private Integer maSoCLS = null;
   private String tenCLS = "";
   private String ghiChu = "";
   private String maKhoa = "";
   private Boolean mien = new Boolean(false);
   private Boolean ngoaiDanhMuc = new Boolean(false);
   private Boolean yeuCau = new Boolean(false);
   private Boolean khongthu = new Boolean(false);
   private Boolean kyThuatCao = new Boolean(false);
   private short soLuong = 0;
   private Double donGia = new Double(0.0D);
   private Boolean dichVu = new Boolean(false);
   private Double donGiaBH = new Double(0.0D);
   private Double phanDV = new Double(0.0D);
   private int selectedIndex = -1;
   private String resultHidden = "";
   private DtDmNhanVien nhanVienThucHien = new DtDmNhanVien();
   private String maPhieu;
   private String maDoituong;
   private String strNgayBatDauBh;
   private String strNgayHetHanBh;
   private Date ngayBatDauBh;
   private Date ngayHetHanBh;
   private Integer clsMoMaso;
   private static Logger log = Logger.getLogger(B3121_CanLamSang_DieuTri.class);
   private String maKhoaCLS = "";
   private Integer maSoKhoaCLS = Integer.valueOf(0);
   private String listCLSChoose = "";
   private String ngayVv;
   private String khoaCDHA;
   @Out(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   @Out(required=false)
   private String duongdanStrDT = null;
   private int index = 0;

   public Integer getMaSoKhoaCLS()
   {
     return this.maSoKhoaCLS;
   }

   public void setMaSoKhoaCLS(Integer maSoKhoaCLS)
   {
     this.maSoKhoaCLS = maSoKhoaCLS;
   }

   public String getMaKhoaCLS()
   {
     return this.maKhoaCLS;
   }

   public void setMaKhoaCLS(String maKhoaCLS)
   {
     this.maKhoaCLS = maKhoaCLS;
   }

   public List<ClsMo> getListCtkq_B3121_1()
   {
     return this.listCtkq_B3121_1;
   }

   public void setListCtkq_B3121_1(List<ClsMo> listCtkqB3121_1)
   {
     this.listCtkq_B3121_1 = listCtkqB3121_1;
   }

   public List<ClsMo> getListCtkq_B3121_2()
   {
     return this.listCtkq_B3121_2;
   }

   public void setListCtkq_B3121_2(List<ClsMo> listCtkqB3121_2)
   {
     this.listCtkq_B3121_2 = listCtkqB3121_2;
   }

   private List<ClsMo> listCtkq_B3121_1 = new ArrayList();
   private List<ClsMo> listCtkq_B3121_2 = new ArrayList();
   @DataModel
   private List<ClsMo> listCtkq_B3121 = new ArrayList();
   @DataModelSelection
   @Out(required=false)
   private ClsMo nhapctSelected;
   private boolean updateNhapct = false;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private String checkThanhtoan;
   @In(required=false)
   @Out(required=false)
   private String chuyenManHinhThuocCLS_MaKhoa;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   private String chuyenManHinhThuocCLS_SoBenhAn;
   private String focusDochuyenTuManHinhThuocCLS;
   private Double tienTamUng;
   private Double tienSoDu;

   public Double getTienTamUng()
   {
     return this.tienTamUng;
   }

   public void setTienTamUng(Double tienTamUng)
   {
     this.tienTamUng = tienTamUng;
   }

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");






     this.hsbaBHYT = new HsbaBhyt();
     if ((this.chuyenManHinhThuocCLS_MaKhoa != null) && (!this.chuyenManHinhThuocCLS_MaKhoa.equals("")) && (this.chuyenManHinhThuocCLS_SoBenhAn != null) && (!this.chuyenManHinhThuocCLS_SoBenhAn.equals("")))
     {
       this.hoSoBenhAn.setHsbaSovaovien(this.chuyenManHinhThuocCLS_SoBenhAn);
       this.maKhoa = this.chuyenManHinhThuocCLS_MaKhoa;
       displayInfor();

       this.focusDochuyenTuManHinhThuocCLS = "true";
     }
     this.tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;

     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan";
   }

   @End
   public void endFunct() {}

   private void emtyData()
   {
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.hoSoBenhAn = new Hsba();

     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.clsMo = new ClsMo();


     SetInforUtil.setInforIfNullForClsMo(this.clsMo);
     this.nhapctSelected = new ClsMo();
     SetInforUtil.setInforIfNullForClsMo(this.nhapctSelected);
     this.tuoi = "";
     this.ngaySinh = "";
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayTt = formatter.format(new Date());

     this.resultHidden = "";
     this.reportFinished = "";
     this.reportFileNameHid = "";
     this.ngayVv = "";
     this.hsbaBHYT = new HsbaBhyt();
   }

   private void hasNoHSBA()
   {
     emtyData();
     resetValue();
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B3232_Canlamsanphongmo";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void resetValue()
   {
     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.khongthu = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.dichVu = new Boolean(false);
     this.ghiChu = "";

     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);
     this.selectedIndex = -1;
     this.ngayVv = "";
   }

   public void CopyFrom(ClsMo cls)
   {
     log.info("Begining CopyFrom()" + cls);
     this.loai = cls.getClsmoLoai();
     this.maSoCLS = cls.getClsmoMahang().getDtdmclsbgMaso();
     this.maCLS = cls.getClsmoMahang().getDtdmclsbgMa();
     this.tenCLS = cls.getClsmoMahang().getDtdmclsbgDiengiai();

     this.maKhoaCLS = (cls.getClsmoKhoa() != null ? cls.getClsmoKhoa().getDmkhoaMa() : "");
     this.mien = cls.getClsmoMien();
     this.ngoaiDanhMuc = cls.getClsmoNDM();
     this.yeuCau = cls.getClsmoYeucau();
     this.khongthu = cls.getClsmoKhongthu();
     this.kyThuatCao = cls.getClsmoKtcao();
     this.soLuong = cls.getClsmoLan().shortValue();
     this.donGia = cls.getClsmoDongia();
     this.dichVu = cls.getClsmoDichvu();
     this.ghiChu = cls.getClsmoGhichu();

     this.donGiaBH = cls.getClsmoDongiabh();
     this.phanDV = cls.getClsmoPhandv();
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayTt = formatter.format(cls.getClsmoNgay());
     this.clsMoMaso = cls.getClsmoMa();
     log.info("ndm:" + this.ngoaiDanhMuc);
     log.info("End CopyFrom()");
   }

   public void CopyTo(ClsMo cls)
   {
     log.info("Begining CopyTo()" + cls);

     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmClsBangGia dmClsBg = (DtDmClsBangGia)dieuTriUtilDelegate.findByMaSo(this.maSoCLS, "DtDmClsBangGia", "dtdmclsbgMaso");
     log.info("dmClsBg = " + dmClsBg);
     cls.setClsmoMahang(dmClsBg);




     cls.setClsmoLoai(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMa());
     cls.setClsmoLoaicls(dmClsBg.getDtdmclsbgMaloai());
     cls.setClsmoLoai2(dmClsBg.getDtdmclsbgLoai());
     cls.setClsmoSoml(dmClsBg.getDmclsbgSoml());

     List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(dmClsBg.getDtdmclsbgMaso());
     if (dtDmClsKhoaList.size() > 0)
     {
       if (((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso() != null)
       {
         this.maKhoaCLS = ((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso().getDmkhoaMa();
         cls.setClsmoKhoa(((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso());
       }
     }
     else {
       this.maKhoaCLS = "";
     }
     cls.setClsmoMien(this.mien);
     cls.setClsmoNDM(this.ngoaiDanhMuc);
     cls.setClsmoYeucau(this.yeuCau);
     cls.setClsmoKhongthu(this.khongthu);
     cls.setClsmoKtcao(this.kyThuatCao);
     cls.setClsmoLan(Short.valueOf(this.soLuong));
     cls.setClsmoDongia(this.donGia);
     cls.setClsmoDichvu(this.dichVu);
     cls.setClsmoGhichu(this.ghiChu);

     cls.setClsmoDongiabh(this.donGiaBH);
     cls.setClsmoPhandv(this.phanDV);
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     try
     {
       cls.setClsmoNgay(formatter.parse(this.ngayTt));
     }
     catch (Exception ex)
     {
       cls.setClsmoNgay(new Date());
     }
     if (this.updateNhapct == true) {
       cls.setClsmoMa(this.clsMoMaso);
     }
     log.info("ndm:" + this.ngoaiDanhMuc);
     log.info("End CopyTo()");
   }

   public void nhapctAjax(int index)
     throws Exception
   {
     log.info("*****Begin nhapctAjax(), index  =  " + index);
     log.info("ndm:" + this.ngoaiDanhMuc);
     if ((this.maPhieu != null) && (!this.maPhieu.equals("")))
     {
       log.info("maPhieu:" + this.maPhieu);
       return;
     }
     if ((this.nhapctSelected.getClsmoMaphieu() != null) && (!this.nhapctSelected.getClsmoMaphieu().equals("")))
     {
       log.info("nhapctSelected.getClsmoMaphieu():" + this.nhapctSelected.getClsmoMaphieu());
       return;
     }
     this.selectedIndex = index;

     CopyFrom((ClsMo)this.listCtkq_B3121.get(index));
     this.updateNhapct = true;
     log.info("***********end nhapctAjax***********");
   }

   private void insertRow()
   {
     log.info("begin cache chi tiet ket qua");
     ClsMo cls = new ClsMo();
     log.info(" tenCLS0 " + this.tenCLS);

     SetInforUtil.setInforIfNullForClsMo(cls);
     CopyTo(cls);
     log.info(" tenCLS1 " + this.tenCLS);
     setinfor(cls);
     if (this.listCtkq_B3121 == null) {
       this.listCtkq_B3121 = new ArrayList();
     }
     log.info("hoSoBenhAn:" + this.hoSoBenhAn);
     cls.setHsbaKhoa(this.hsbaKhoa);

     this.listCtkq_B3121.add(cls);
     log.info(" tenCLS2 " + this.tenCLS);
   }

   private void updateRow()
   {
     log.info("*****Begin updateRow(), selectedIndex = " + this.selectedIndex);



     ClsMo clsmo = new ClsMo();
     SetInforUtil.setInforIfNullForClsMo(clsmo);
     CopyTo(clsmo);














     this.listCtkq_B3121.set(this.selectedIndex, clsmo);
     this.updateNhapct = false;
   }

   public String showDate(Date ngayCls)
   {
     if (ngayCls == null) {
       return "";
     }
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     return formatter.format(ngayCls);
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     log.info("ndm:" + this.ngoaiDanhMuc);
     if (this.maCLS.equals(""))
     {
       resetValue();
       return;
     }
     if (this.soLuong == 0)
     {
       resetValue();
       return;
     }
     if (this.listCtkq_B3121 == null) {
       this.listCtkq_B3121 = new ArrayList();
     }
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")))
     {
       resetValue();
       return;
     }
     if (this.updateNhapct) {
       updateRow();
     } else {
       insertRow();
     }
     resetValue();
     log.info("*****End Enter() *****");
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete(), delete index =  " + index);
     if ((this.nhapctSelected.getClsmoMaphieu() != null) && (!this.nhapctSelected.getClsmoMaphieu().equals(""))) {
       return;
     }
     this.listCtkq_B3121.remove(index);
     resetValue();
     log.info("*****End delete() *****");
   }

   public void ghinhan()
     throws Exception
   {
     if (this.listCtkq_B3121 == null) {
       this.listCtkq_B3121 = new ArrayList();
     }
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")))
     {
       resetValue();
       return;
     }
     try
     {
       ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();

       HsbaKhoaDelegate hsbaKhoaDel = HsbaKhoaDelegate.getInstance();
       HsbaKhoa hsbaKhoa = hsbaKhoaDel.findBySoVaoVienAndKhoaMa(this.hoSoBenhAn.getHsbaSovaovien(), this.maKhoa);
       Date ngayThToan = new Date();
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       if (!"".equals(this.ngayTt))
       {
         Calendar cal = Calendar.getInstance();

         cal.setTime(df.parse(this.ngayTt));
         ngayThToan = cal.getTime();
       }
       else
       {
         ngayThToan = df.parse(df.format(ngayThToan));
       }
       ArrayList<ClsMo> arrayListHasMaPhieu = new ArrayList();
       for (int i = 0; i < this.listCtkq_B3121.size(); i++)
       {
         RemoveUtil.setAllNullForClsMo((ClsMo)this.listCtkq_B3121.get(i));
         if ((this.nhanVienThucHien != null) && (!this.nhanVienThucHien.getDtdmnhanvienMa().equals(""))) {
           ((ClsMo)this.listCtkq_B3121.get(i)).setClsmoThuchien(this.nhanVienThucHien);
         } else {
           ((ClsMo)this.listCtkq_B3121.get(i)).setClsmoThuchien(null);
         }
         if (((ClsMo)this.listCtkq_B3121.get(i)).getHsbaKhoa() == null) {
           ((ClsMo)this.listCtkq_B3121.get(i)).setHsbaKhoa(hsbaKhoa);
         }
         if ((((ClsMo)this.listCtkq_B3121.get(i)).getClsmoMaphieu() != null) && (!((ClsMo)this.listCtkq_B3121.get(i)).getClsmoMaphieu().equals(""))) {
           arrayListHasMaPhieu.add(this.listCtkq_B3121.get(i));
         }
       }
       for (ClsMo cls : arrayListHasMaPhieu) {
         this.listCtkq_B3121.remove(cls);
       }
       String maPhieu = clsmoDelegate.createClsKhamForCLSPhauThuat(this.listCtkq_B3121, this.hoSoBenhAn.getHsbaSovaovien(), this.maKhoa);
       this.maPhieu = maPhieu;
       try
       {
         if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
         {
           String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



           String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + ":" + IConstantsRes.LABCONN_PORT + ";useUnicode=true;characterEncoding=UTF-8;" + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
           String username = IConstantsRes.LABCONN_USERNAME;
           String password = IConstantsRes.LABCONN_PASSWORD;
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);

           List<ClsMo> listClsMo = ClsMoDelegate.getInstance().findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
           for (int i = 0; i < listClsMo.size(); i++) {
             if ((((ClsMo)listClsMo.get(i)).getClsmoTh() == null) || (!((ClsMo)listClsMo.get(i)).getClsmoTh().booleanValue()))
             {
               List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clsmoMaso", "clskqTen", ((ClsMo)listClsMo.get(i)).getClsmoMa() + "", "");
               for (int j = 0; j < listClsKetQua.size(); j++)
               {
                 String OrderID = this.hoSoBenhAn.getHsbaSovaovien().replaceFirst("BD", "NO");
                 DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                 String DateOrder = formatter.format(((ClsMo)listClsMo.get(i)).getClsmoNgay());
                 String Invoice = this.hsbaBHYT.getHsbabhytSothebh();
                 String ObjectID = this.hoSoBenhAn.getDoituongMa().getDmdoituongMa();
                 String TestCodeHIS = ((ClsKetQua)listClsKetQua.get(j)).getClskqMa();
                 String PID = this.hoSoBenhAn.getBenhnhanMa().getBenhnhanMa();
                 String PName = this.hoSoBenhAn.getBenhnhanMa().getBenhnhanHoten();
                 String Address = "";
                 if ((this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
                   Address = Address + this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi() + ",";
                 }
                 if ((this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))) {
                   Address = Address + this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
                 }
                 if ((this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))) {
                   Address = Address + this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
                 }
                 if ((this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))) {
                   Address = Address + this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
                 }
                 String Age = this.hoSoBenhAn.getBenhnhanMa().getBenhnhanNamsinh();
                 String Sex = "F";
                 if (this.hoSoBenhAn.getBenhnhanMa().getDmgtMaso().getDmgtTen().toLowerCase().equals("nam")) {
                   Sex = "M";
                 }
                 HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.hoSoBenhAn.getHsbaSovaovien());
                 String DoctorID = "a";
                 if (hsbaChuyenMon.getHsbacmBacsi(true) != null) {
                   DoctorID = hsbaChuyenMon.getHsbacmBacsi(true).getDtdmnhanvienTen();
                 }
                 String Diagnostic = "";
                 if (hsbaChuyenMon.getHsbacmBenhchinh() != null) {
                   Diagnostic = hsbaChuyenMon.getHsbacmBenhchinh().getDmbenhicdTen();
                 }
                 String LocationID = "";
                 if (((ClsMo)listClsMo.get(i)).getClsmoKhoa() != null) {
                   LocationID = ((ClsMo)listClsMo.get(i)).getClsmoKhoa().getDmkhoaMa();
                 }
                 String query1 = " Select * from [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Order_HIS]";
                 query1 = query1 + " where 1 = 1 ";
                 query1 = query1 + " and OrderID = '" + OrderID + "' ";
                 query1 = query1 + " and TestCodeHIS = '" + TestCodeHIS + "' ";
                 PreparedStatement stmt1 = conn.prepareStatement(query1, 1);
                 ResultSet rs1 = stmt1.executeQuery();
                 if ((rs1 == null) ||
                   (!rs1.next()))
                 {
                   String query = " INSERT INTO [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Order_HIS] ([OrderID],[DateOrder],[Invoice],[ObjectID],[TestCodeHIS],[PID],[PName],[Address],[Age],[Sex], [DoctorID],[Diagnostic], [LocationID]) " + " VALUES " + " ('" + OrderID + "','" + DateOrder + "','" + Invoice + "','" + ObjectID + "','" + TestCodeHIS + "','" + PID + "',N'" + PName + "',N'" + Address + "','" + Age + "','" + Sex + "','" + DoctorID + "',N'" + Diagnostic + "','" + LocationID + "') ";



                   PreparedStatement stmt = conn.prepareStatement(query, 1);
                   int num = stmt.executeUpdate();
                   if (num == 1)
                   {
                     ResultSet rs = stmt.getGeneratedKeys();
                     log.info("ResultSet: " + rs.toString());
                     if (rs != null) {
                       while (rs.next())
                       {
                         ResultSetMetaData rsMetaData = rs.getMetaData();
                         int columnCount = rsMetaData.getColumnCount();
                         for (int k = 1; k <= columnCount; k++)
                         {
                           String key = rs.getString(k);
                           log.info("key " + k + " is " + key);
                         }
                       }
                     }
                   }
                 }
               }
             }
           }
         }
       }
       catch (Exception e)
       {
         log.error("*************loi***********" + e.toString());
       }
       HsttThreadUtil capnhatHstt = new HsttThreadUtil();
       capnhatHstt.setHoSoBenhAn(this.hoSoBenhAn);
       capnhatHstt.start();
       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
       log.error("*************loi***********" + e.toString());
       e.printStackTrace();
     }
     try
     {
       for (int i = 0; i < this.listCtkq_B3121.size(); i++) {
         SetInforUtil.setInforIfNullForClsMo((ClsMo)this.listCtkq_B3121.get(i));
       }
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
       e.printStackTrace();
     }
     log.info("*****End Ghi nhan() *****");
     this.reportFinished = "";
   }

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   public void sualai()
     throws Exception
   {
     try
     {
       ResetForm();
       this.resultHidden = "";
       this.reportFinished = "";
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if (this.hoSoBenhAn.getBenhnhanMa().getBenhnhanNgaysinh() != null)
     {
       this.ngaySinh = formatter.format(Long.valueOf(this.hoSoBenhAn.getBenhnhanMa().getBenhnhanNgaysinh().getTime()));

       log.info("Ngay sinh :" + this.ngaySinh);
     }
     if ((this.clsMo.getClsmoNgay() != null) && (!this.clsMo.getClsmoNgay().equals("")))
     {
       this.ngayTt = formatter.format(Long.valueOf(this.clsMo.getClsmoNgay().getTime()));
       log.info("Ngay thanh toan :" + this.ngayTt);
     }
     if (this.benhNhan.getDmgtMaso() != null)
     {
       if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
         this.gioi = "r1";
       } else {
         this.gioi = "r2";
       }
     }
     else {
       this.gioi = null;
     }
     log.info("End setOtherValue()");
   }

   private void setinfor(ClsMo nhapctSelected)
   {
     DtDmClsBangGia dmkythuat = nhapctSelected.getClsmoMahang();

     nhapctSelected.setClsmoMahang(dmkythuat);
     if (this.hsbaKhoa != null) {
       nhapctSelected.setHsbaKhoa(this.hsbaKhoa);
     }
     try
     {
       if (!"".equals(this.ngayTt))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(this.ngayTt));
         nhapctSelected.setClsmoNgay(cal.getTime());
       }
     }
     catch (Exception e) {}
     log.info(" tenCLS3 " + this.tenCLS);
   }

   public void subDisplayInfor()
   {
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.maKhoa == null) || (this.maKhoa.equals("")))
     {
       hasNoHSBA();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });
       this.listCtkq_B3121 = new ArrayList();
       return;
     }
     HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
     Hsba hsbaTmp = hsbaDel.find(this.hoSoBenhAn.getHsbaSovaovien());
     this.maDoituong = "";
     if ((hsbaTmp != null) &&
       (hsbaTmp.getDoituongMa() != null)) {
       this.maDoituong = hsbaTmp.getDoituongMa().getDmdoituongMa();
     }
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
     this.hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hoSoBenhAn.getHsbaSovaovien(), this.maKhoa);
     if (this.hsbaKhoa == null)
     {
       hasNoHSBA();
       FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });
       this.listCtkq_B3121 = new ArrayList();
       return;
     }
     this.hoSoBenhAn = this.hsbaKhoa.getHsbaSovaovien();
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);
     Date dNgayVaoV = this.hoSoBenhAn.getHsbaNgaygiovaov();
     if (dNgayVaoV != null)
     {
       SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
       this.ngayVv = formatter.format(dNgayVaoV);
     }
     this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
     this.hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
     if (this.hsbaBHYT == null) {
       this.hsbaBHYT = new HsbaBhyt();
     }
     this.ngayBatDauBh = this.hsbaBHYT.getHsbabhytGiatri2();
     if (this.ngayBatDauBh == null) {
       this.ngayBatDauBh = this.hsbaBHYT.getHsbabhytGiatri0();
     }
     this.ngayHetHanBh = this.hsbaBHYT.getHsbabhytGiatri3();
     if (this.ngayHetHanBh == null) {
       this.ngayHetHanBh = this.hsbaBHYT.getHsbabhytGiatri1();
     }
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.strNgayBatDauBh = (this.ngayBatDauBh == null ? "" : formatter.format(this.ngayBatDauBh));
     this.strNgayHetHanBh = (this.ngayHetHanBh == null ? "" : formatter.format(this.ngayHetHanBh));
   }

   public void SetInforAllCLS(List<ClsMo> clsmo_tmp)
   {
     if ((clsmo_tmp == null) || (clsmo_tmp.size() == 0))
     {
       log.info("displayInfor   tham mo 22 ....." + this.clsMo);
       this.listCtkq_B3121 = new ArrayList();
       log.info("displayInfor   tham mo 23 ....." + this.clsMo);
     }
     else
     {
       this.listCtkq_B3121 = clsmo_tmp;
       for (int i = 0; i < this.listCtkq_B3121.size(); i++)
       {
         ClsMo cls = (ClsMo)this.listCtkq_B3121.get(i);
         if ((cls.getClsmoTh() == null) || ((!cls.getClsmoTh().booleanValue()) && (cls.getClsmoMahang() != null)))
         {
           if ((cls.getClsmoMahang().getDtdmclsbgXn() != null) && (cls.getClsmoMahang().getDtdmclsbgXn().booleanValue() == true))
           {
             cls = connectLabconn(cls);
             ClsMoDelegate.getInstance().edit(cls);
           }
           if ((cls.getClsmoKhoa() != null) && (cls.getClsmoKhoa().getDmkhoaMa().equals("CDH")))
           {
             cls = connectPACS(cls);
             ClsMoDelegate.getInstance().edit(cls);
           }
         }
         SetInforUtil.setInforIfNullForClsMo(cls);
         cls.setHsbaKhoa(this.hsbaKhoa);
       }
     }
   }

   public void displayInforMaPhieu()
   {
     if ((this.maPhieu == null) || (this.maPhieu.equals(""))) {
       return;
     }
     ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();

     List<ClsMo> clsmo_tmp = clsmoDelegate.findByMaPhieu(this.maPhieu);
     if ((clsmo_tmp == null) || (clsmo_tmp.size() == 0)) {
       return;
     }
     this.hoSoBenhAn = ((ClsMo)clsmo_tmp.get(0)).getHsbaKhoa().getHsbaSovaovien();
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     SetInforAllCLS(clsmo_tmp);



     setOtherValue();
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       log.info("Begining displayInfor()");


       subDisplayInfor();
       if ((this.hoSoBenhAn == null) || (this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals(""))) {
         return;
       }
       this.checkThanhtoan = "block";
       this.hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(this.hoSoBenhAn.getHsbaSovaovien());
       if ((this.hsthtoan != null) && (this.hsthtoan.getHsthtoanNgaytt() != null)) {
         if (!this.hsthtoan.getHsthtoanNgaytt().equals(""))
         {
           this.checkThanhtoan = "none";
           FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         }
       }
       if (!this.maKhoa.equals(this.hoSoBenhAn.getHsbaKhoadangdt(true).getDmkhoaMa()))
       {
         this.checkThanhtoan = "none";
         log.info("Khong phai khoa dang dieu tri, chi duoc xem thong tin!");
       }
       ClsMoDelegate clsmoDelegate = ClsMoDelegate.getInstance();



       List<ClsMo> clsmo_tmp = clsmoDelegate.findBySoVaoVienAndKhoaMa(this.hoSoBenhAn.getHsbaSovaovien(), this.maKhoa);

       SetInforAllCLS(clsmo_tmp);

       setOtherValue();

       this.chuyenManHinhThuocCLS_MaKhoa = this.maKhoa;
       this.chuyenManHinhThuocCLS_SoBenhAn = this.hoSoBenhAn.getHsbaSovaovien();
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("error on function displayInfor" + e);
     }
     log.info("End displayInfor()");
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.hoSoBenhAn = new Hsba();
     this.hsbaBHYT = new HsbaBhyt();
     this.tienSoDu = Double.valueOf(0.0D);
     this.tienTamUng = Double.valueOf(0.0D);

     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.clsMo = new ClsMo();

     SetInforUtil.setInforIfNullForClsMo(this.clsMo);
     this.nhanVienThucHien = new DtDmNhanVien();
     this.gioi = "";
     this.tuoi = "";
     this.ngaySinh = "";
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayTt = formatter.format(new Date());

     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.maKhoa = "";
     this.maKhoaCLS = "";
     this.maSoKhoaCLS = Integer.valueOf(0);
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.khongthu = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.dichVu = new Boolean(false);
     this.ghiChu = "";

     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);


     this.listCtkq_B3121 = new ArrayList();
     this.listCtkq_B3121_1 = new ArrayList();
     this.listCtkq_B3121_2 = new ArrayList();
     this.resultHidden = "";
     this.maPhieu = "";
     this.strNgayBatDauBh = "";
     this.strNgayHetHanBh = "";
     this.ngayBatDauBh = null;
     this.ngayHetHanBh = null;
     log.info("-----------dichVu:" + this.dichVu);
     log.info("-----------ngoaiDanhMuc:" + this.ngoaiDanhMuc);

     log.info("End ResetForm(): ");
   }

   private String reportFinished = "";

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

   public String getPosition()
   {
     return this.position;
   }

   public void setPosition(String position)
   {
     this.position = position;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getReportFileNameHid()
   {
     return this.reportFileNameHid;
   }

   public void setReportFileNameHid(String reportFileNameHid)
   {
     this.reportFileNameHid = reportFileNameHid;
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

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public ClsMo getClsMo()
   {
     return this.clsMo;
   }

   public void setClsMo(ClsMo clsMo)
   {
     this.clsMo = clsMo;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgayTt()
   {
     return this.ngayTt;
   }

   public void setNgayTt(String ngayTt)
   {
     this.ngayTt = ngayTt;
   }

   public String getLoai()
   {
     return this.loai;
   }

   public void setLoai(String loai)
   {
     this.loai = loai;
   }

   public String getMaCLS()
   {
     return this.maCLS;
   }

   public void setMaCLS(String maCLS)
   {
     this.maCLS = maCLS;
   }

   public Integer getMaSoCLS()
   {
     return this.maSoCLS;
   }

   public void setMaSoCLS(Integer maSoCLS)
   {
     this.maSoCLS = maSoCLS;
   }

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
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

   public Boolean getYeuCau()
   {
     return this.yeuCau;
   }

   public void setYeuCau(Boolean yeuCau)
   {
     this.yeuCau = yeuCau;
   }

   public Boolean getKhongthu()
   {
     return this.khongthu;
   }

   public void setKhongthu(Boolean khongthu)
   {
     this.khongthu = khongthu;
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

   public DtDmNhanVien getNhanVienThucHien()
   {
     return this.nhanVienThucHien;
   }

   public void setNhanVienThucHien(DtDmNhanVien nhanVienThucHien)
   {
     this.nhanVienThucHien = nhanVienThucHien;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public List<ClsMo> getlistCtkq_B3121()
   {
     return this.listCtkq_B3121;
   }

   public void setlistCtkq_B3121(List<ClsMo> listCtkq_B3121)
   {
     this.listCtkq_B3121 = listCtkq_B3121;
   }

   public ClsMo getNhapctSelected()
   {
     return this.nhapctSelected;
   }

   public void setNhapctSelected(ClsMo nhapctSelected)
   {
     this.nhapctSelected = nhapctSelected;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public Boolean getNgoaiDanhMuc()
   {
     return this.ngoaiDanhMuc;
   }

   public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc)
   {
     this.ngoaiDanhMuc = ngoaiDanhMuc;
   }

   public Boolean getDichVu()
   {
     return this.dichVu;
   }

   public void setDichVu(Boolean dichVu)
   {
     this.dichVu = dichVu;
   }

   public List<ClsMo> getListCtkq_B3121()
   {
     return this.listCtkq_B3121;
   }

   public void setListCtkq_B3121(List<ClsMo> listCtkq_B3121)
   {
     this.listCtkq_B3121 = listCtkq_B3121;
   }

   public HsbaBhyt getHsbaBHYT()
   {
     return this.hsbaBHYT;
   }

   public void setHsbaBHYT(HsbaBhyt hsbaBHYT)
   {
     this.hsbaBHYT = hsbaBHYT;
   }

   public HsbaKhoa getHsbaKhoa()
   {
     return this.hsbaKhoa;
   }

   public void setHsbaKhoa(HsbaKhoa hsbaKhoa)
   {
     this.hsbaKhoa = hsbaKhoa;
   }

   public String getFocusDochuyenTuManHinhThuocCLS()
   {
     return this.focusDochuyenTuManHinhThuocCLS;
   }

   public void setFocusDochuyenTuManHinhThuocCLS(String focusDochuyenTuManHinhThuocCLS)
   {
     this.focusDochuyenTuManHinhThuocCLS = focusDochuyenTuManHinhThuocCLS;
   }

   public Double getTienSoDu()
   {
     return this.tienSoDu;
   }

   public void setTienSoDu(Double tienSoDu)
   {
     this.tienSoDu = tienSoDu;
   }

   public void displayCLS()
     throws Exception
   {
     DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
     ArrayList<DtDmClsBangGia> temp = (ArrayList)dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa(this.maSoKhoaCLS);

     this.listCtkq_B3121_1 = new ArrayList();
     this.listCtkq_B3121_2 = new ArrayList();
     if (temp == null) {
       temp = new ArrayList();
     }
     Collections.sort(temp, new Comparator<DtDmClsBangGia>()
     {
       public int compare(DtDmClsBangGia o1, DtDmClsBangGia o2)
       {
         String o1Ma2 = "";
         String o2Ma2 = "";
         if (o1.getDtdmclsbgMa2() == null)
         {
           o1Ma2 = "0";
           return 1;
         }
         o1Ma2 = o1.getDtdmclsbgMa2();
         if (o2.getDtdmclsbgMa2() == null)
         {
           o2Ma2 = "0";
           return -1;
         }
         o2Ma2 = o2.getDtdmclsbgMa2();
         if (Integer.parseInt(o1Ma2) < Integer.parseInt(o2Ma2)) {
           return -1;
         }
         return 1;
       }
     });
     DmKhoa khoa = (DmKhoa)DieuTriUtilDelegate.getInstance().findByMa(this.maKhoaCLS, "DmKhoa", "dmkhoaMa");
     for (int i = 0; i < temp.size(); i++)
     {
       ClsMo cls = new ClsMo();
       SetInforUtil.setInforIfNullForClsMo(cls);
       DtDmClsBangGia clsBanggia = (DtDmClsBangGia)temp.get(i);
       cls.setClsmoMahang(clsBanggia);







       cls.setClsmoDongia(clsBanggia.getDtdmclsbgDongia());
       cls.setClsmoLan(Short.valueOf(Short.parseShort("1")));
       cls.setClsmoKhoa(khoa);
       cls.setClsmoDongiabh(clsBanggia.getDtdmclsbgDongiabh());
       cls.setClsmoPhandv(clsBanggia.getDtdmclsbgPhandv());
       if (i < temp.size() / 2) {
         this.listCtkq_B3121_1.add(cls);
       } else {
         this.listCtkq_B3121_2.add(cls);
       }
     }
   }

   public void addListCLSChoose()
     throws Exception
   {
     if (this.listCLSChoose != "")
     {
       String[] result = this.listCLSChoose.split(",");
       SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
       for (int i = 0; i < result.length; i++)
       {
         ClsMo cls = new ClsMo();
         for (ClsMo clsItem : this.listCtkq_B3121_1) {
           if (clsItem.getClsmoMahang().getDtdmclsbgMaso().intValue() == Integer.parseInt(result[i])) {
             cls = clsItem;
           }
         }
         for (ClsMo clsItem : this.listCtkq_B3121_2) {
           if (clsItem.getClsmoMahang().getDtdmclsbgMaso().intValue() == Integer.parseInt(result[i])) {
             cls = clsItem;
           }
         }
         cls.setClsmoLoai(cls.getClsmoMahang().getDtdmclsbgPhanloai().getDtdmclsMa());
         cls.setClsmoMien(cls.getClsmoMahang().getDtdmclsbgMien());
         cls.setClsmoNDM(cls.getClsmoMahang().getDtdmclsbgNDM());
         cls.setClsmoYeucau(this.yeuCau);
         cls.setClsmoKhongthu(this.khongthu);
         cls.setClsmoKtcao(Boolean.valueOf(cls.getClsmoMahang().getDtdmclsbgPhanloai().getDtdmclsMaso().intValue() == 9));
         cls.setClsmoDichvu(this.dichVu);
         cls.setClsmoLoaicls(cls.getClsmoMahang().getDtdmclsbgPhanloai());
         cls.setClsmoLoai2(cls.getClsmoMahang().getDtdmclsbgLoai());
         cls.setClsmoSoml(cls.getClsmoMahang().getDmclsbgSoml());
         if ((cls.getClsmoKhongthu() != null) && (cls.getClsmoKhongthu().booleanValue() == true))
         {
           cls.setClsmoDongia(new Double(0.0D));
         }
         else if ((cls.getClsmoYeucau() != null) && (cls.getClsmoYeucau().booleanValue() == true))
         {
           cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiayc());
         }
         else if ((cls.getClsmoMien() != null) && (cls.getClsmoMien().booleanValue() == true))
         {
           cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiamp());
         }
         else if (this.maDoituong.equalsIgnoreCase("BH"))
         {
           Date curDate = formatter.parse(formatter.format(new Date()));
           if (((cls.getClsmoNDM() != null) && (cls.getClsmoNDM().booleanValue() == true)) || (this.ngayBatDauBh == null) || (this.ngayHetHanBh == null) || (curDate.before(this.ngayBatDauBh)) || (curDate.after(this.ngayHetHanBh))) {
             cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
           } else {
             cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiabh());
           }
         }
         else if (this.maDoituong.equalsIgnoreCase("MP"))
         {
           cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiamp());
         }
         else
         {
           cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
         }
         cls.setClsmoDongiabh(cls.getClsmoMahang().getDtdmclsbgDongiabh());
         cls.setClsmoNgay(formatter.parse(this.ngayTt));
         if (this.listCtkq_B3121 == null) {
           this.listCtkq_B3121 = new ArrayList();
         }
         ClsMo clsMoTmp = (ClsMo)BeanUtils.cloneBean(cls);
         this.listCtkq_B3121.add(clsMoTmp);
       }
     }
   }

   public String getListCLSChoose()
   {
     return this.listCLSChoose;
   }

   public void setListCLSChoose(String listCLSChoose)
   {
     this.listCLSChoose = listCLSChoose;
   }

   public String getCheckThanhtoan()
   {
     return this.checkThanhtoan;
   }

   public void setCheckThanhtoan(String checkThanhtoan)
   {
     this.checkThanhtoan = checkThanhtoan;
   }

   public HsThtoan getHsthtoan()
   {
     return this.hsthtoan;
   }

   public void setHsthtoan(HsThtoan hsthtoan)
   {
     this.hsthtoan = hsthtoan;
   }

   public String getGhiChu()
   {
     return this.ghiChu;
   }

   public void setGhiChu(String ghiChu)
   {
     this.ghiChu = ghiChu;
   }

   public Double getDonGiaBH()
   {
     return this.donGiaBH;
   }

   public void setDonGiaBH(Double donGiaBH)
   {
     this.donGiaBH = donGiaBH;
   }

   public Double getPhanDV()
   {
     return this.phanDV;
   }

   public void setPhanDV(Double phanDV)
   {
     this.phanDV = phanDV;
   }

   public String getMaDoituong()
   {
     return this.maDoituong;
   }

   public void setMaDoituong(String maDoituong)
   {
     this.maDoituong = maDoituong;
   }

   public String getStrNgayBatDauBh()
   {
     return this.strNgayBatDauBh;
   }

   public void setStrNgayBatDauBh(String strNgayBatDauBh)
   {
     this.strNgayBatDauBh = strNgayBatDauBh;
   }

   public String getStrNgayHetHanBh()
   {
     return this.strNgayHetHanBh;
   }

   public void setStrNgayHetHanBh(String strNgayHetHanBh)
   {
     this.strNgayHetHanBh = strNgayHetHanBh;
   }

   public String getNgayVv()
   {
     return this.ngayVv;
   }

   public void setNgayVv(String ngayVv)
   {
     this.ngayVv = ngayVv;
   }

   public ClsMo connectLabconn(ClsMo clsMo)
   {
     try
     {
       if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
       {
         log.info("ConnectLabconn");
         String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";




         Hsba hsbaTemp = this.hoSoBenhAn;
         String temp = "";
         String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + "\\" + IConstantsRes.LABCONN_DATABASE + ":" + IConstantsRes.LABCONN_PORT + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
         String username = IConstantsRes.LABCONN_USERNAME;
         String password = IConstantsRes.LABCONN_PASSWORD;
         boolean daThucHien = false;
         String ghichu = "";

         String ketluan = "";
         try
         {
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);
           List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clsmoMaso", "clskqTen", clsMo.getClsmoMa() + "", "");
           for (int j = 0; j < listClsKetQua.size(); j++)
           {
             String query = " Select * from [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload]";
             query = query + " where 1 = 1 ";
             query = query + " and OrderID = '" + hsbaTemp.getHsbaSovaovien().replaceFirst("BD", "NO") + "' ";
             query = query + " and TestCodeHIS = '" + ((ClsKetQua)listClsKetQua.get(j)).getClskqMa() + "' ";
             PreparedStatement stmt = conn.prepareStatement(query, 1);
             ResultSet rs = stmt.executeQuery();
             if (rs != null)
             {
               while (rs.next())
               {
                 daThucHien = true;
                 ResultSetMetaData rsMetaData = rs.getMetaData();
                 temp = temp + ((ClsKetQua)listClsKetQua.get(j)).getClskqTen() + ": " + rs.getString("Result") + "\n";
                 ketluan = rs.getString("Comment");
                 ((ClsKetQua)listClsKetQua.get(j)).setResult(rs.getString("Result"));
                 DieuTriUtilDelegate.getInstance().edit(listClsKetQua.get(j));
               }
               DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
               String today = formatter.format(Calendar.getInstance().getTime());
               query = "UPDATE [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload] SET TimeOut = '" + today + "' ";
               query = query + " where 1 = 1 ";
               query = query + " and OrderID = '" + hsbaTemp.getHsbaSovaovien() + "' ";
               query = query + " and TestCodeHIS = '" + ((ClsKetQua)listClsKetQua.get(j)).getClskqMa() + "' ";
               stmt = conn.prepareStatement(query, 1);
               int num = stmt.executeUpdate();
               if (num == 1)
               {
                 rs = stmt.getGeneratedKeys();
                 log.info("ResultSet: " + rs.toString());
                 if (rs != null) {
                   while (rs.next())
                   {
                     ResultSetMetaData rsMetaData = rs.getMetaData();
                     int columnCount = rsMetaData.getColumnCount();
                     for (int k = 1; k <= columnCount; k++)
                     {
                       String key = rs.getString(k);
                       log.info("key " + k + " is " + key);
                     }
                   }
                 }
               }
             }
           }
           ghichu = temp;
           conn.close();
         }
         catch (ClassNotFoundException e)
         {
           e.printStackTrace();
         }
         catch (SQLException e)
         {
           e.printStackTrace();
         }
         if (daThucHien)
         {
           clsMo.setClsmoTh(Boolean.valueOf(daThucHien));
           clsMo.setClsmoGhichu(ghichu);
           clsMo.setClsmoKetqua(ketluan);
         }
       }
     }
     catch (Exception e) {}
     return clsMo;
   }

   public ClsMo connectPACS(ClsMo clsMo)
   {
     try
     {
       if (IConstantsRes.KET_NOI_SERVER_PACS.equals("YES"))
       {
         log.info("ConnectPACS");

         Hsba hsbaTemp = this.hoSoBenhAn;
         String driver = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://" + IConstantsRes.PACS_SERVER + ":" + IConstantsRes.PACS_PORT + "/" + IConstantsRes.PACS_DATABASE;
         String username = IConstantsRes.PACS_USERNAME;
         String password = IConstantsRes.PACS_PASSWORD;
         boolean daThucHien = false;

         String kqCLS = "";
         String ketluan = "";
         try
         {
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);
           String query = " SELECT patient.*, study.*, series.* FROM patient, study, series ";
           query = query + " where 1 = 1 ";
           query = query + " and patient.pat_id = '" + hsbaTemp.getHsbaSovaovien().replaceFirst("BD", "NO") + "' ";
           query = query + " and study.ma_cls = '" + clsMo.getClsmoMahang(true).getDtdmclsbgMa() + "' ";
           query = query + " AND patient.pk = study.patient_fk AND study.pk = series.study_fk";
           PreparedStatement stmt = conn.prepareStatement(query, 1);
           ResultSet rs = stmt.executeQuery();
           if (rs.first())
           {
             daThucHien = true;
             ketluan = rs.getString("ket_qua");
           }
         }
         catch (ClassNotFoundException e)
         {
           e.printStackTrace();
         }
         if (daThucHien)
         {
           clsMo.setClsmoTh(Boolean.valueOf(daThucHien));
           clsMo.setClsmoKetqua(kqCLS);
           clsMo.setClsmoKetqua(ketluan);
         }
       }
     }
     catch (Exception e) {}
     return clsMo;
   }

   public String getKhoaCDHA()
   {
     return this.khoaCDHA;
   }

   public void setKhoaCDHA(String khoaCDHA)
   {
     this.khoaCDHA = khoaCDHA;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "CanLamSanPhauThuat";
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuchidinhclsNT.jrxml";
       log.info("da thay file templete " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("HOTEN", this.hoSoBenhAn.getBenhnhanMa().getBenhnhanHoten());
       HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.hoSoBenhAn.getHsbaSovaovien());
       String bacsi = hsbaChuyenMon.getHsbacmBacsi(true).getDtdmnhanvienTen();
       params.put("BACSIDIEUTRI", bacsi);
       String diachistr = "";
       if ((this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if ((this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))) {
         diachistr = diachistr + this.hoSoBenhAn.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if ((this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))) {
         diachistr = diachistr + this.hoSoBenhAn.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if ((this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) && (!this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))) {
         diachistr = diachistr + this.hoSoBenhAn.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if ((this.hoSoBenhAn.getDoituongMa() != null) && (!this.hoSoBenhAn.getDoituongMa().equals(""))) {
         params.put("DOITUONG", this.hoSoBenhAn.getDoituongMa(true).getDmdoituongTen());
       }
       params.put("SOTHEBHYT", this.hsbaBHYT.getHsbabhytSothebh());

       params.put("GIOI", this.hoSoBenhAn.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
       params.put("KHOA", this.hsbaKhoa.getKhoaMa().getDmkhoaTen());
       params.put("MATIEPDON", this.hoSoBenhAn.getHsbaSovaovien().replaceFirst("BD", "NO"));
       params.put("HSBAKHOAMASO", this.hsbaKhoa.getHsbakhoaMaso());
       if (this.maKhoaCLS.trim().length() > 0)
       {
         DmKhoa khoa = (DmKhoa)DieuTriUtilDelegate.getInstance().findByMa(this.maKhoaCLS, "DmKhoa", "dmkhoaMa");
         params.put("TENKHOACLS", khoa.getDmkhoaTen());

         params.put("KHOACLS", " = " + khoa.getDmkhoaMaso());
       }
       else
       {
         params.put("TENKHOACLS", "");
         params.put("KHOACLS", " IS NULL ");
       }
       if (this.maKhoaCLS.toUpperCase().equals("CDH")) {
         params.put("KHOACDHA", this.khoaCDHA != "" ? this.khoaCDHA : null);
       }
       String sDonViTuoi = "";
       if (this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (this.hoSoBenhAn.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", this.hoSoBenhAn.getBenhnhanMa().getBenhnhanTuoi() + " " + sDonViTuoi);

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((hsbaChuyenMon.getHsbacmBenhchinh() != null) && (hsbaChuyenMon.getHsbacmBenhchinh().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhchinh().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((hsbaChuyenMon.getHsbacmBenhphu() != null) && (hsbaChuyenMon.getHsbacmBenhphu().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((hsbaChuyenMon.getHsbacmBenhphu2() != null) && (hsbaChuyenMon.getHsbacmBenhphu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((hsbaChuyenMon.getHsbacmBenhphu3() != null) && (hsbaChuyenMon.getHsbacmBenhphu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((hsbaChuyenMon.getHsbacmBenhphu4() != null) && (hsbaChuyenMon.getHsbacmBenhphu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((hsbaChuyenMon.getHsbacmBenhphu5() != null) && (hsbaChuyenMon.getHsbacmBenhphu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(hsbaChuyenMon.getHsbacmBenhphu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       params.put("CHANDOAN", chanDoan);

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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "CanLamSanPhauThuat");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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
     log.info("Thoat Method XuatReport");
   }

   public void displayInforFromMenu()
     throws Exception
   {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3121_CanLamSang_DieuTri

 * JD-Core Version:    0.7.0.1

 */