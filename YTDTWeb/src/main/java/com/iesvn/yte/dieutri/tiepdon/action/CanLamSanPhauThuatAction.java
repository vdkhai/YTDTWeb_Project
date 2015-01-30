 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKetQua;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HsttKhamThreadUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.Constructor;
 import java.lang.reflect.Field;
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
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B121_2_Canlamsangphauthuat")
 @Synchronized(timeout=6000000L)
 public class CanLamSanPhauThuatAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String strNgayBatDauBh;
   private String strNgayHetHanBh;
   private Date ngayBatDauBh;
   private Date ngayHetHanBh;
   private int cls_index;
   private boolean daThanhtoan;
   private String diengiai;
   @Logger
   private Log log;
   @DataModel
   private List<ClsKham> listCtkq_B121_2;
   @DataModel
   private List<ClsKhamExt> listCLSBanKhamTruoc;
   private ArrayList<ClsKham> listCLSChoose_B121_1;
   private ArrayList<ClsKham> listCLSChoose_B121_2;
   private Integer bacSiChiDinhMaSo;
   private String bacSiChiDinhMa;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String goToCLSThuThuat;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   @DataModelSelection("listCtkq_B121_2")
   @Out(required=false)
   private ClsKham nhapctSelected;
   @Out(required=false)
   private String reportPathTD;
   @Out(required=false)
   private String reportTypeTD;
   @Out(required=false)
   JasperPrint jasperPrintTD;
   @Out(required=false)
   @In(required=false)
   String initclsCapNhatTTCLS;
   @Out(required=false)
   Integer clsmaOut;
   @Out(required=false)
   @In(required=false)
   private String khongQuaylaiTuCapNhatTTCLS;
   private int index;
   @Out(required=false)
   @In(required=false)
   String fromMenu;
   boolean dangGoiGhiNhan;
   private String khoaCDHA;
   private String khoiBHYT;
   private String vuotMoc1;
   private boolean updateNhapct;
   private String loai;
   private String ma;
   private Integer maSo;
   private String maKhoa;
   private String listCLSChoose;
   private Integer masoKhoa;
   private Boolean mien;
   private Boolean yeuCau;
   private Boolean kyThuatCao;
   private Boolean dichVu;
   private Boolean ndm;
   private Boolean khongthu;
   private String tenCLS;
   private String ghiChu;
   private short soLuong;
   private Double donGia;
   private Double donGiaBH;
   private Double phanDV;
   private String resultHidden;

   @Factory("khongQuaylaiTuCapNhatTTCLS")
   @Begin(nested=true)
   public void quaylaitucapnhatketquacls()
   {
     this.log.info("begin quaylaitucapnhatketquacls", new Object[0]);
     if ((this.maBanKhamOut == null) || (this.maBanKhamOut.equals("")) || (this.maTiepDonOut == null) || (this.maTiepDonOut.equals("")))
     {
       resetValueFromMenu();
       return;
     }
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();


     List<ClsKham> listCtkq_B121_2_temp = clsKhamDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
     this.log.info("------------------------", new Object[0]);
     if ((listCtkq_B121_2_temp != null) && (listCtkq_B121_2_temp.size() > 0))
     {
       this.listCtkq_B121_2 = listCtkq_B121_2_temp;
       for (ClsKham cls : this.listCtkq_B121_2)
       {
         SetInforUtil.setInforIfNullForClsKham(cls);
         this.log.info(cls.getClskhamTh(), new Object[0]);
       }
     }
     this.log.info("------------------------", new Object[0]);
     this.khongQuaylaiTuCapNhatTTCLS = "yes";
     this.log.info("end quaylaitucapnhatketquacls", new Object[0]);
   }

   @Begin(join=true)
   public String initFromMenu(String fromMenu)
   {
     this.log.info("begin initFromMenu", new Object[0]);

     this.khongQuaylaiTuCapNhatTTCLS = "yes";
     this.goToCLSThuThuat = "no";

     this.maBanKhamOut = null;
     this.maTiepDonOut = null;
     this.fromMenu = fromMenu;

     resetValueFromMenu();
     this.log.info("end initFromMenu", new Object[0]);
     return "clsthuthat";
   }

   public void resetValueFromMenu()
   {
     this.maBanKhamOut = "";
     this.maTiepDonOut = "";
     this.thamkham = new ThamKham();
     this.benhNhan = new BenhNhan();
     this.ngaySinh = "";
     this.thoiGian = "";
     this.loai = "";
     this.maSo = null;
     this.ma = "";
     this.tenCLS = "";
     this.diengiai = "";
     this.maKhoa = "";
     this.listCLSChoose = "";
     this.kyThuatCao = new Boolean(false);
     this.ndm = new Boolean(false);
     this.mien = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);
     this.listCtkq_B121_2 = new ArrayList();
     this.listCLSBanKhamTruoc = new ArrayList();
     this.listCLSChoose_B121_1 = new ArrayList();
     this.listCLSChoose_B121_2 = new ArrayList();
     this.ghiChu = "";
     this.daThanhtoan = false;
     this.strNgayBatDauBh = (this.strNgayHetHanBh = "");
     this.ngayBatDauBh = (this.ngayHetHanBh = null);
     this.khoaCDHA = "";
   }

   public String getListCLSChoose()
   {
     return this.listCLSChoose;
   }

   public void setListCLSChoose(String listCLSChoose)
   {
     this.listCLSChoose = listCLSChoose;
   }

   public void resetValueFromTK()
   {
     this.thamkham = new ThamKham();
     this.benhNhan = new BenhNhan();this.ngaySinh = "";
     this.thoiGian = "";
     this.loai = "";
     this.maSo = null;
     this.ma = "";
     this.tenCLS = "";
     this.diengiai = "";
     this.maKhoa = "";
     this.listCLSChoose = "";
     this.masoKhoa = Integer.valueOf(0);
     this.kyThuatCao = new Boolean(false);
     this.ndm = new Boolean(false);
     this.mien = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);
     this.listCtkq_B121_2 = new ArrayList();


     this.ghiChu = "";
     this.daThanhtoan = false;
     this.strNgayBatDauBh = (this.strNgayHetHanBh = "");
     this.ngayBatDauBh = (this.ngayHetHanBh = null);
     this.khoaCDHA = "";
   }

   @Factory("goToCLSThuThuat")
   @Begin(nested=true)
   public void comeFromThamKham()
   {
     this.log.info("begin comeFromThamKham", new Object[0]);
     this.goToCLSThuThuat = "gone";
     if (this.dangGoiGhiNhan == true)
     {
       this.log.info("dangGoiGhiNhan do nothing return", new Object[0]);
       return;
     }
     if ("yes".equals(this.fromMenu))
     {
       this.log.info("den tu menu, maybe return", new Object[0]);








       return;
     }
     if (this.maKhoa.equals(""))
     {
       this.listCLSChoose_B121_1 = new ArrayList();
       this.listCLSChoose_B121_2 = new ArrayList();
     }
     if ((this.listCtkq_B121_2 != null) && (this.listCtkq_B121_2.size() > 0))
     {
       this.log.info("---------------------------dang thuc hien------------", new Object[0]);
       return;
     }
     resetValueFromTK();
     this.thamkham.getThamkhamBankham(true).setDtdmbankhamMa(this.maBanKhamOut);
     this.thamkham.getTiepdonMa(true).setTiepdonMa(this.maTiepDonOut);

     this.khongQuaylaiTuCapNhatTTCLS = "yes";

     this.listCtkq_B121_2 = new ArrayList();
     setValueForListMo();
     if ((this.thamkham.getTiepdonMa(true).getTiepdonMa() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonMa().equals(""))) {
       if (!this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP"))
       {
         HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
         HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(this.thamkham.getTiepdonMa(true).getTiepdonMa());
         if (hsttk != null) {
           this.daThanhtoan = true;
         } else {
           this.daThanhtoan = false;
         }
       }
     }
     if ((this.thamkham.getTiepdonMa(true).getTiepdonChkhoa() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonChkhoa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
       this.daThanhtoan = true;
     }
     this.log.info("end comeFromThamKham", new Object[0]);
   }

   public void displayInforFromMenu()
     throws Exception
   {
     setValueForListMo();
   }

   public void setValueForListMo()
   {
     this.log.info("thamkham.getThamkhamBankham(true).getDtdmbankhamMa():" + this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), new Object[0]);
     this.log.info("thamkham.getTiepdonMa(true).getTiepdonMa():" + this.thamkham.getTiepdonMa(true).getTiepdonMa(), new Object[0]);
     if ((this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa() == null) || (this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa().equals("")) || (this.thamkham.getTiepdonMa(true).getTiepdonMa() == null) || (this.thamkham.getTiepdonMa(true).getTiepdonMa().equals("")))
     {
       resetValueFromMenu();
       return;
     }
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();


     List<ClsKham> listCtkq_B121_2_temp = clsKhamDelegate.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.thamkham.getTiepdonMa(true).getTiepdonMa());
     if ((listCtkq_B121_2_temp != null) && (listCtkq_B121_2_temp.size() > 0))
     {
       this.listCtkq_B121_2 = listCtkq_B121_2_temp;
       for (ClsKham cls : this.listCtkq_B121_2)
       {
         if ((cls.getClskhamTh() == null) || ((!cls.getClskhamTh().booleanValue()) && (cls.getClskhamMahang() != null)))
         {
           if ((cls.getClskhamMahang().getDtdmclsbgXn() != null) && (cls.getClskhamMahang().getDtdmclsbgXn().booleanValue() == true))
           {
             cls = connectLabconn(cls);



             ClsKhamDelegate.getInstance().edit(cls);
           }
           if ((cls.getClskhamKhoa() != null) && (cls.getClskhamKhoa().getDmkhoaMa().equals("CDH")))
           {
             cls = connectPACS(cls);



             ClsKhamDelegate.getInstance().edit(cls);
           }
         }
         SetInforUtil.setInforIfNullForClsKham(cls);
       }
     }
     ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
     ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.thamkham.getTiepdonMa(true).getTiepdonMa());
     if (thamkham_temp == null)
     {
       resetValueFromMenu();
       FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);
       return;
     }
     this.thamkham = thamkham_temp;

     this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
     if (this.thamkham.getTiepdonMa().getKhoibhytMa() != null) {
       this.khoiBHYT = this.thamkham.getTiepdonMa().getKhoibhytMa().getDtdmkhoibhytMa();
     }
     if ((this.thamkham.getTiepdonMa().getTiepdonMoc1() != null) && (this.thamkham.getThamkhamNgaygiocn() != null)) {
       if (this.thamkham.getTiepdonMa().getTiepdonMoc1().equals(this.thamkham.getThamkhamNgaygiocn())) {
         this.vuotMoc1 = "true";
       } else if (this.thamkham.getThamkhamNgaygiocn().before(this.thamkham.getTiepdonMa().getTiepdonMoc1())) {
         this.vuotMoc1 = "true";
       }
     }
     setOtherValue();
   }

   public ClsKham connectPACS(ClsKham clsKham)
   {
     try
     {
       if (IConstantsRes.KET_NOI_SERVER_PACS.equals("YES"))
       {
         this.log.info("ConnectPACS", new Object[0]);
         ThamKham thamkham_temp = this.thamkham;
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
           query = query + " and patient.pat_id = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
           query = query + " and study.ma_cls = '" + clsKham.getClskhamMahang(true).getDtdmclsbgMa() + "' ";
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
           clsKham.setClskhamTh(Boolean.valueOf(daThucHien));
           clsKham.setClskhamKetqua(kqCLS);
           clsKham.setClskhamKetqua(ketluan);
         }
       }
     }
     catch (Exception e) {}
     return clsKham;
   }

   public ClsKham connectLabconn(ClsKham clsKham)
   {
     try
     {
       if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
       {
         this.log.info("ConnectLabconn", new Object[0]);
         String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



         ThamKham thamkham_temp = this.thamkham;
         String temp = "";
         String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + "\\" + IConstantsRes.LABCONN_DATABASE + ":" + IConstantsRes.LABCONN_PORT + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
         String username = IConstantsRes.LABCONN_USERNAME;
         String password = IConstantsRes.LABCONN_PASSWORD;
         boolean daThucHien = false;
         String ghichu = "";
         String kqCLS = "";
         String ketluan = "";
         try
         {
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);
           List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", clsKham.getClskhamMa() + "", "");
           for (int j = 0; j < listClsKetQua.size(); j++)
           {
             String query = " Select * from [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[Tbl_Result_Upload]";
             query = query + " where 1 = 1 ";
             query = query + " and OrderID = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
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
               query = query + " and OrderID = '" + thamkham_temp.getTiepdonMa(true).getTiepdonMa() + "' ";
               query = query + " and TestCodeHIS = '" + ((ClsKetQua)listClsKetQua.get(j)).getClskqMa() + "' ";
               stmt = conn.prepareStatement(query, 1);
               int num = stmt.executeUpdate();
               if (num == 1)
               {
                 rs = stmt.getGeneratedKeys();
                 this.log.info("ResultSet: " + rs.toString(), new Object[0]);
                 if (rs != null) {
                   while (rs.next())
                   {
                     ResultSetMetaData rsMetaData = rs.getMetaData();
                     int columnCount = rsMetaData.getColumnCount();
                     for (int k = 1; k <= columnCount; k++)
                     {
                       String key = rs.getString(k);
                       this.log.info("key " + k + " is " + key, new Object[0]);
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
           clsKham.setClskhamTh(Boolean.valueOf(daThucHien));
           clsKham.setClskhamKetqua(kqCLS);
           clsKham.setClskhamGhichu(ghichu);
           clsKham.setClskhamKetqua(ketluan);
         }
       }
     }
     catch (Exception e) {}
     return clsKham;
   }

   public Integer getMasoKhoa()
   {
     return this.masoKhoa;
   }

   public void setMasoKhoa(Integer masoKhoa)
   {
     this.masoKhoa = masoKhoa;
   }

   public void resetValue()
   {
     this.loai = "";
     this.ma = "";

     this.mien = new Boolean(false);

     this.yeuCau = new Boolean(false);
     this.khongthu = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.dichVu = new Boolean(false);
     this.ndm = new Boolean(false);

     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);
     this.tenCLS = "";
     this.listCLSChoose = "";
     this.updateNhapct = false;
     this.ghiChu = "";
     this.listCLSBanKhamTruoc = new ArrayList();
   }

   public CanLamSanPhauThuatAction()
   {
     this.cls_index = 1;












     this.listCLSChoose_B121_1 = new ArrayList();

     this.listCLSChoose_B121_2 = new ArrayList();
























     this.reportPathTD = null;


     this.reportTypeTD = null;


     this.jasperPrintTD = null;


     this.initclsCapNhatTTCLS = null;



     this.clsmaOut = null;






     this.index = 0;





     this.dangGoiGhiNhan = false;


































































































































































































































































































































































































     this.khoiBHYT = "";
     this.vuotMoc1 = "";

     this.updateNhapct = false;

     this.loai = "";
     this.ma = "";

     this.maKhoa = "";
     this.listCLSChoose = "";







     this.masoKhoa = Integer.valueOf(0);
     this.mien = new Boolean(false);

     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.dichVu = new Boolean(false);
     this.ndm = new Boolean(false);
     this.khongthu = new Boolean(false);

     this.tenCLS = "";
     this.ghiChu = "";
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.donGiaBH = new Double(0.0D);
     this.phanDV = new Double(0.0D);
























     this.resultHidden = "";
   }

   public void CopyFrom(ClsKham cls)
   {
     this.loai = cls.getClskhamLoai();
     this.ma = cls.getClskhamMahang().getDtdmclsbgMa();
     this.maSo = cls.getClskhamMahang().getDtdmclsbgMaso();
     this.log.info("In CopyFrom , ma = " + this.ma + ", Maso = " + this.maSo, new Object[0]);
     this.maKhoa = (cls.getClskhamKhoa() != null ? cls.getClskhamKhoa().getDmkhoaMa() : "");
     this.mien = cls.getClskhamMien();

     this.yeuCau = cls.getClskhamYeucau();
     this.khongthu = cls.getClskhamKhongthu();
     this.kyThuatCao = cls.getClskhamKtcao();
     this.soLuong = cls.getClskhamLan().shortValue();
     this.donGia = cls.getClskhamDongia();
     this.dichVu = cls.getClskhamDichvu();
     this.ndm = cls.getClskhamNdm();

     this.tenCLS = cls.getClskhamMahang().getDtdmclsbgDiengiai();
     this.clsmaOut = cls.getClskhamMa();
     this.donGiaBH = cls.getClskhamDongiabh();
     this.phanDV = cls.getClskhamPhandv();
     this.ghiChu = cls.getClskhamGhichu();
     this.log.info("End CopyFrom , tenCLS = " + this.tenCLS + "clskham Maso = " + this.clsmaOut, new Object[0]);
   }

   public void CopyTo(ClsKham cls)
   {
     this.log.info("Begin CopyTo(), Cls Bgia ma so = " + this.maSo, new Object[0]);
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();



     DtDmClsBangGia dmClsBg = (DtDmClsBangGia)dieuTriUtilDelegate.findByMaSo(this.maSo, "DtDmClsBangGia", "dtdmclsbgMaso");
     this.log.info("dmClsBg = " + dmClsBg, new Object[0]);
     cls.setClskhamMahang(dmClsBg);



     cls.setClskhamMaloai(dmClsBg.getDtdmclsbgPhanloai());
     cls.setClskhamLoai(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMa());

     System.out.println("maSomaSomaSomaSomaSo:" + this.maSo);
     List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(dmClsBg.getDtdmclsbgMaso());
     if (dtDmClsKhoaList.size() > 0) {
       this.maKhoa = (((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso() != null ? ((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso().getDmkhoaMa() : "");
     }
     if (this.maKhoa.equals("")) {
       cls.setClskhamKhoa(null);
     } else {
       cls.getClskhamKhoa().setDmkhoaMa(this.maKhoa);
     }
     cls.setClskhamMien(dmClsBg.getDtdmclsbgMien());
     cls.setClskhamNdm(dmClsBg.getDtdmclsbgNDM());
     cls.setClskhamYeucau(this.yeuCau);
     cls.setClskhamKhongthu(this.khongthu);
     cls.setClskhamKtcao(Boolean.valueOf(dmClsBg.getDtdmclsbgPhanloai().getDtdmclsMaso().intValue() == 9));
     cls.setClskhamLan(Short.valueOf(this.soLuong));
     cls.setClskhamLoai2(dmClsBg.getDtdmclsbgLoai());
     cls.setClskhamSoml(dmClsBg.getDmclsbgSoml());




     cls.setClskhamDongia(this.donGia);
     cls.setClskhamDichvu(this.dichVu);



     System.out.println("tenCLS:" + this.tenCLS);
     System.out.println("dongia:" + this.donGia);



     cls.setClskhamDongiabh(this.donGiaBH);
     cls.setClskhamPhandv(this.phanDV);
     cls.setClskhamGhichu(this.ghiChu);
     if (this.updateNhapct == true)
     {
       this.log.info("In CopyTo , clskham Maso = " + this.clsmaOut, new Object[0]);
       cls.setClskhamMa(this.clsmaOut);
     }
   }

   public void XuatReport()
   {
     this.reportTypeTD = "CanLamSanPhauThuat";
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieuchidinhcls.jrxml";
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("HOTEN", this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
       params.put("BACSIDIEUTRI", this.thamkham.getThamkhamBacsi() != null ? this.thamkham.getThamkhamBacsi().getDtdmnhanvienTen() : "");
       String diachistr = "";
       if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if ((this.thamkham.getTiepdonMa(true).getDoituongMa() != null) && (!this.thamkham.getTiepdonMa(true).getDoituongMa().equals(""))) {
         params.put("DOITUONG", this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongTen());
       }
       params.put("SOTHEBHYT", this.thamkham.getTiepdonMa(true).getTiepdonSothebh());

       params.put("GIOI", this.thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
       params.put("KHOA", this.thamkham.getTiepdonMa(true).getTiepdonBankham().getDtdmbankhamTen());
       params.put("MATIEPDON", this.thamkham.getTiepdonMa(true).getTiepdonMa());
       params.put("THAMKHAM", this.thamkham.getThamkhamMa());
       if (this.maKhoa.trim().length() > 0)
       {
         DmKhoa khoa = (DmKhoa)DieuTriUtilDelegate.getInstance().findByMa(this.maKhoa, "DmKhoa", "dmkhoaMa");
         params.put("TENKHOACLS", khoa.getDmkhoaTen());

         params.put("KHOACLS", " = " + khoa.getDmkhoaMaso());
       }
       else
       {
         params.put("TENKHOACLS", "");
         params.put("KHOACLS", " IS NULL ");
       }
       if (this.maKhoa.toUpperCase().equals("CDH")) {
         params.put("KHOACDHA", this.khoaCDHA != "" ? this.khoaCDHA : null);
       }
       String sDonViTuoi = "";
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() + " " + sDonViTuoi);

       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
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
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       params.put("CHANDOAN", chanDoan);
       this.log.info("================= ", new Object[0]);
       Class.forName("com.mysql.jdbc.Driver");
       this.log.info("da thay driver mysql", new Object[0]);
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         this.log.info(e.getMessage(), new Object[0]);
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "CanLamSanPhauThuat");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathTD, new Object[0]);
       this.index += 1;
       if (conn != null)
       {
         conn.close();
         conn = null;
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void nhapctAjax(int index)
     throws Exception
   {
     this.log.info("*****Begin nhapctAjax() *****", new Object[0]);
     if ((this.nhapctSelected.getClskhamMaphieu() != null) && (!this.nhapctSelected.getClskhamMaphieu().equals(""))) {
       return;
     }
     CopyFrom(this.nhapctSelected);
     this.updateNhapct = true;


     this.cls_index = index;

     this.log.info("***********end nhapctAjax***********", new Object[0]);
   }

   public void sualai()
     throws Exception
   {
     this.log.info("*****Begin sualai() *****", new Object[0]);

     resetValue();
     this.resultHidden = "";
   }

   public void enter()
     throws Exception
   {
     if (this.ma.equals(""))
     {
       resetValue();
       return;
     }
     if (this.soLuong == 0)
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
     this.log.info("*****End Enter() *****", new Object[0]);
   }

   private void updateRow()
   {
     this.log.info("*****updateNhapct", new Object[0]);


     ClsKham cls = new ClsKham();
     SetInforUtil.setInforIfNullForClsKham(cls);
     CopyTo(cls);













     this.log.info("====== Index i= " + this.cls_index + " ======", new Object[0]);


     this.listCtkq_B121_2.set(this.cls_index, cls);
     this.log.info("====== listCtkq_B121_2: " + this.listCtkq_B121_2.size() + " ======", new Object[0]);

     this.updateNhapct = false;
   }

   private void insertRow()
   {
     this.log.info("begin cache chi tiet ket qua", new Object[0]);
     if ((this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa() != null) && (!this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP")))
     {
       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(this.thamkham.getTiepdonMa(true).getTiepdonMa());
       if (hsttk_tmp != null)
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         return;
       }
     }
     if ((this.thamkham.getTiepdonMa(true).getTiepdonChkhoa() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonChkhoa().equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.DA_CHUYEN_VAO_NOI_TRU, new Object[0]);
       return;
     }
     ClsKham cls = new ClsKham();
     SetInforUtil.setInforIfNullForClsKham(cls);
     CopyTo(cls);


     this.listCtkq_B121_2.add(cls);
     this.log.info("listCtkq_B121_2:" + this.listCtkq_B121_2, new Object[0]);
     this.log.info("listCtkq_B121_2:" + this.listCtkq_B121_2.size(), new Object[0]);
   }

   public void delete(int index)
     throws Exception
   {
     this.log.info("*****Begin delete() *****", new Object[0]);









     ClsKham clsKham = (ClsKham)this.listCtkq_B121_2.get(index);

     this.log.info("***** ma phieu : " + clsKham.getClskhamMaphieu(), new Object[0]);
     if ((clsKham.getClskhamMaphieu() != null) && (!clsKham.getClskhamMaphieu().equals(""))) {
       return;
     }
     this.listCtkq_B121_2.remove(index);
     this.updateNhapct = false;
     resetValue();
     this.log.info("*****End delete() *****", new Object[0]);
   }

   public String ghinhan()
     throws Exception
   {
     this.dangGoiGhiNhan = true;










     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     if ((this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa() != null) && (!this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("TP")))
     {
       HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(this.thamkham.getTiepdonMa(true).getTiepdonMa());
       if ((hsttk_tmp != null) &&
         (hsttk_tmp.getHsthtoankDatt() != null) && (hsttk_tmp.getHsthtoankDatt().booleanValue()))
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         return null;
       }
     }
     this.log.info("*****Begin Ghi nhan() *****", new Object[0]);
     this.log.info("*****so phan tu insert *****" + this.listCtkq_B121_2.size(), new Object[0]);
     try
     {
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
       for (int i = 0; i < this.listCtkq_B121_2.size(); i++)
       {
         RemoveUtil.setAllNullForClsKham((ClsKham)this.listCtkq_B121_2.get(i));


         ((ClsKham)this.listCtkq_B121_2.get(i)).setClskhamNgaygio(Calendar.getInstance().getTime());

         ((ClsKham)this.listCtkq_B121_2.get(i)).setClskhamThamkham(this.thamkham);
       }
       System.out.println("clsKhamArray:" + this.listCtkq_B121_2);
       System.out.println("clsKhamArray size :" + this.listCtkq_B121_2.size());
       clsKhamDelegate.createClsKhamForCLSPhauThuat(this.listCtkq_B121_2, this.thamkham.getTiepdonMa().getTiepdonMa(), this.thamkham.getThamkhamBankham().getDtdmbankhamMa());
       try
       {
         if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
         {
           System.out.println("KET_NOI_MAY_XET_NGHIEM");
           String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



           String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + ":" + IConstantsRes.LABCONN_PORT + ";useUnicode=true;characterEncoding=UTF-8;" + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
           String username = IConstantsRes.LABCONN_USERNAME;
           String password = IConstantsRes.LABCONN_PASSWORD;
           Class.forName(driver);
           Connection conn = DriverManager.getConnection(url, username, password);
           System.out.println("conn");
           List<ClsKham> listClsKham = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKham", "clskhamThamkham", "clskhamMaphieu", this.thamkham.getThamkhamMa() + "", "");
           System.out.println("listClsKham " + listClsKham.size());
           for (int i = 0; i < listClsKham.size(); i++) {
             if ((((ClsKham)listClsKham.get(i)).getClskhamTh() == null) || (!((ClsKham)listClsKham.get(i)).getClskhamTh().booleanValue()))
             {
               List<ClsKetQua> listClsKetQua = DieuTriUtilDelegate.getInstance().findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", ((ClsKham)listClsKham.get(i)).getClskhamMa() + "", "");
               for (int j = 0; j < listClsKetQua.size(); j++)
               {
                 String OrderID = this.thamkham.getTiepdonMa().getTiepdonMa();
                 DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                 String DateOrder = formatter.format(((ClsKham)listClsKham.get(i)).getClskhamNgaygio());
                 String Invoice = this.thamkham.getTiepdonMa().getTiepdonSothebh();
                 String ObjectID = this.thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa();
                 String TestCodeHIS = ((ClsKetQua)listClsKetQua.get(j)).getClskqMa();
                 String PID = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa();
                 String PName = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten();
                 String Address = "";
                 if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
                   Address = Address + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ",";
                 }
                 if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen().equals(""))) {
                   Address = Address + this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
                 }
                 if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen().equals(""))) {
                   Address = Address + this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
                 }
                 if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen().equals(""))) {
                   Address = Address + this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
                 }
                 String Age = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
                 String Sex = "F";
                 if (this.thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen().toLowerCase().equals("nam")) {
                   Sex = "M";
                 }
                 String DoctorID = "a";
                 if (this.thamkham.getThamkhamBacsi() != null) {
                   DoctorID = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
                 }
                 String Diagnostic = "";
                 if (this.thamkham.getBenhicd10() != null) {
                   Diagnostic = this.thamkham.getBenhicd10().getDmbenhicdTen();
                 }
                 String LocationID = "";
                 if (((ClsKham)listClsKham.get(i)).getClskhamKhoa() != null) {
                   LocationID = ((ClsKham)listClsKham.get(i)).getClskhamKhoa().getDmkhoaMa();
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
                     this.log.info("ResultSet: " + rs.toString(), new Object[0]);
                     if (rs != null) {
                       while (rs.next())
                       {
                         ResultSetMetaData rsMetaData = rs.getMetaData();
                         int columnCount = rsMetaData.getColumnCount();
                         for (int k = 1; k <= columnCount; k++)
                         {
                           String key = rs.getString(k);
                           this.log.info("key " + k + " is " + key, new Object[0]);
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
         this.log.error("*************loi***********" + e.toString(), new Object[0]);
       }
       HsttKhamThreadUtil capnhatHsttKham = new HsttKhamThreadUtil();
       capnhatHsttKham.setTiepDon(this.thamkham.getTiepdonMa());
       capnhatHsttKham.start();

       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.resultHidden = "success";
       for (int i = 0; i < this.listCtkq_B121_2.size(); i++) {
         SetInforUtil.setInforIfNullForClsKham((ClsKham)this.listCtkq_B121_2.get(i));
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";

       e.printStackTrace();
       this.log.error("*************loi***********" + e.toString(), new Object[0]);
     }
     this.log.info("*****End Ghi nhan() *****", new Object[0]);










     this.dangGoiGhiNhan = false;
     return null;
   }

   public String quaylai()
     throws Exception
   {
     resetValue();
     this.resultHidden = "";
     this.listCtkq_B121_2 = null;
     this.listCLSChoose_B121_1 = new ArrayList();
     this.listCLSChoose_B121_2 = new ArrayList();

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
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!this.thamkham.getThamkhamNgaygio().equals(""))) {
       this.thoiGian = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
     }
     this.ngayBatDauBh = this.thamkham.getTiepdonMa().getTiepdonGiatri3();
     if (this.ngayBatDauBh == null) {
       this.ngayBatDauBh = this.thamkham.getTiepdonMa().getTiepdonGiatri1();
     }
     this.ngayHetHanBh = this.thamkham.getTiepdonMa().getTiepdonGiatri4();
     if (this.ngayHetHanBh == null) {
       this.ngayHetHanBh = this.thamkham.getTiepdonMa().getTiepdonGiatri2();
     }
     this.strNgayBatDauBh = (this.ngayBatDauBh == null ? "" : formatter.format(this.ngayBatDauBh));
     this.strNgayHetHanBh = (this.ngayHetHanBh == null ? "" : formatter.format(this.ngayHetHanBh));
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

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public List<ClsKham> getListCtkq_B121_2()
   {
     return this.listCtkq_B121_2;
   }

   public void setListCtkq_B121_2(List<ClsKham> listCtkq_B121_2)
   {
     this.listCtkq_B121_2 = listCtkq_B121_2;
   }

   public List<ClsKhamExt> getListCLSBanKhamTruoc()
   {
     return this.listCLSBanKhamTruoc;
   }

   public void setListCLSBanKhamTruoc(ArrayList<ClsKhamExt> listCLSBanKhamTruoc)
   {
     this.listCLSBanKhamTruoc = listCLSBanKhamTruoc;
   }

   public ArrayList<ClsKham> getListCLSChoose_B121_1()
   {
     return this.listCLSChoose_B121_1;
   }

   public void setListCLSChoose_B121_1(ArrayList<ClsKham> listCLSChooseB121_1)
   {
     this.listCLSChoose_B121_1 = listCLSChooseB121_1;
   }

   public ArrayList<ClsKham> getListCLSChoose_B121_2()
   {
     return this.listCLSChoose_B121_2;
   }

   public void setListCLSChoose_B121_2(ArrayList<ClsKham> listCLSChooseB121_2)
   {
     this.listCLSChoose_B121_2 = listCLSChooseB121_2;
   }

   public ClsKham getNhapctSelected()
   {
     return this.nhapctSelected;
   }

   public void setNhapctSelected(ClsKham nhapctSelected)
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

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
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

   public String getGoToCLSThuThuat()
   {
     return this.goToCLSThuThuat;
   }

   public void setGoToCLSThuThuat(String goToCLSThuThuat)
   {
     this.goToCLSThuThuat = goToCLSThuThuat;
   }

   public String getMa()
   {
     return this.ma;
   }

   public void setMa(String ma)
   {
     this.ma = ma;
   }

   public Integer getMaSo()
   {
     return this.maSo;
   }

   public void setMaSo(Integer maSo)
   {
     this.maSo = maSo;
   }

   public String getKhoiBHYT()
   {
     return this.khoiBHYT;
   }

   public void setKhoiBHYT(String khoiBHYT)
   {
     this.khoiBHYT = khoiBHYT;
   }

   public String getVuotMoc1()
   {
     return this.vuotMoc1;
   }

   public void setVuotMoc1(String vuotMoc1)
   {
     this.vuotMoc1 = vuotMoc1;
   }

   public Boolean getDichVu()
   {
     return this.dichVu;
   }

   public void setDichVu(Boolean dichVu)
   {
     this.dichVu = dichVu;
   }

   public Boolean getNdm()
   {
     return this.ndm;
   }

   public void setNdm(Boolean ndm)
   {
     this.ndm = ndm;
   }

   public Integer getBacSiChiDinhMaSo()
   {
     return this.bacSiChiDinhMaSo;
   }

   public void setBacSiChiDinhMaSo(Integer bacSiChiDinhMaSo)
   {
     this.bacSiChiDinhMaSo = bacSiChiDinhMaSo;
   }

   public String getBacSiChiDinhMa()
   {
     return this.bacSiChiDinhMa;
   }

   public void setBacSiChiDinhMa(String bacSiChiDinhMa)
   {
     this.bacSiChiDinhMa = bacSiChiDinhMa;
   }

   public String getFromMenu()
   {
     return this.fromMenu;
   }

   public void setFromMenu(String fromMenu)
   {
     this.fromMenu = fromMenu;
   }

   public void displayCLS()
     throws Exception
   {
     DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
     ArrayList<DtDmClsBangGia> temp = (ArrayList)dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa(this.masoKhoa);

     this.listCLSChoose_B121_1 = new ArrayList();
     this.listCLSChoose_B121_2 = new ArrayList();
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
     for (int i = 0; i < temp.size(); i++)
     {
       ClsKham cls = new ClsKham();
       SetInforUtil.setInforIfNullForClsKham(cls);
       cls.setClskhamMahang((DtDmClsBangGia)temp.get(i));




       cls.setClskhamDongia(((DtDmClsBangGia)temp.get(i)).getDtdmclsbgDongia());
       cls.setClskhamLan(Short.valueOf(Short.parseShort("1")));
       cls.getClskhamKhoa().setDmkhoaMa(this.maKhoa);
       cls.setClskhamDongiabh(((DtDmClsBangGia)temp.get(i)).getDtdmclsbgDongiabh());
       cls.setClskhamPhandv(((DtDmClsBangGia)temp.get(i)).getDtdmclsbgPhandv());
       if (i < temp.size() / 2) {
         this.listCLSChoose_B121_1.add(cls);
       } else {
         this.listCLSChoose_B121_2.add(cls);
       }
     }
   }

   public void addListCLSChoose()
     throws Exception
   {
     this.log.info("Begin addListCLSChoose, maDT = " + this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa(), new Object[0]);
     System.out.println("addListCLSChoose " + this.listCLSChoose);
     if (this.listCLSChoose != "")
     {
       String maDT = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       String[] result = this.listCLSChoose.split(",");
       for (int i = 0; i < result.length; i++)
       {
         ClsKham cls = new ClsKham();
         System.out.println(this.listCLSChoose_B121_1.size());
         System.out.println(this.listCLSChoose_B121_2.size());
         for (ClsKham clsItem : this.listCLSChoose_B121_1) {
           if (clsItem.getClskhamMahang().getDtdmclsbgMaso().intValue() == Integer.parseInt(result[i]))
           {
             cls = clsItem;
             break;
           }
         }
         for (ClsKham clsItem : this.listCLSChoose_B121_2) {
           if (clsItem.getClskhamMahang().getDtdmclsbgMaso().intValue() == Integer.parseInt(result[i]))
           {
             cls = clsItem;
             break;
           }
         }
         cls.setClskhamLoai(cls.getClskhamMahang().getDtdmclsbgPhanloai().getDtdmclsMa());
         cls.setClskhamMien(cls.getClskhamMahang().getDtdmclsbgMien());
         cls.setClskhamNdm(cls.getClskhamMahang().getDtdmclsbgNDM());
         cls.setClskhamYeucau(this.yeuCau);
         cls.setClskhamKhongthu(this.khongthu);
         cls.setClskhamKtcao(Boolean.valueOf(cls.getClskhamMahang().getDtdmclsbgPhanloai().getDtdmclsMaso().intValue() == 9));
         cls.setClskhamDichvu(this.dichVu);
         cls.setClskhamMaloai(cls.getClskhamMahang().getDtdmclsbgPhanloai());
         cls.setClskhamLoai2(cls.getClskhamMahang().getDtdmclsbgLoai());
         cls.setClskhamSoml(cls.getClskhamMahang().getDmclsbgSoml());
         if (this.khongthu.booleanValue())
         {
           cls.setClskhamDongia(new Double(0.0D));
         }
         else if (this.yeuCau.booleanValue())
         {
           cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiayc());
         }
         else if (maDT.equalsIgnoreCase("BH"))
         {
           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
           Date curDate = formatter.parse(formatter.format(new Date()));
           if (cls.getClskhamMahang().getDtdmclsbgMien().booleanValue()) {
             cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
           } else if (((cls.getClskhamMahang().getDtdmclsbgNDM() != null) && (cls.getClskhamMahang().getDtdmclsbgNDM().booleanValue() == true)) || (this.ngayBatDauBh == null) || (this.ngayHetHanBh == null) || (curDate.before(this.ngayBatDauBh)) || (curDate.after(this.ngayHetHanBh))) {
             cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
           } else {
             cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiabh());
           }
         }
         else if (maDT.equalsIgnoreCase("MP"))
         {
           cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
         }
         else if (maDT.equalsIgnoreCase("TP"))
         {
           if (cls.getClskhamMahang().getDtdmclsbgMien().booleanValue()) {
             cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongiamp());
           } else {
             cls.setClskhamDongia(cls.getClskhamMahang().getDtdmclsbgDongia());
           }
         }
         if (this.listCtkq_B121_2 == null) {
           this.listCtkq_B121_2 = new ArrayList();
         }
         ClsKham clsTmp = (ClsKham)BeanUtils.cloneBean(cls);
         this.listCtkq_B121_2.add(clsTmp);
       }
       System.out.println("test " + this.listCtkq_B121_2.size());
     }
   }

   public void showCLSBanKhamTruoc()
   {
     this.log.info("Begin showCLSBanKhamTruoc", new Object[0]);
     this.listCLSBanKhamTruoc = new ArrayList();
     ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
     if (this.thamkham != null)
     {
       List<ClsKham> clsKhams = thamkhamDel.getListClsBanKhamTruoc(this.thamkham.getTiepdonMa().getTiepdonMa(), this.thamkham.getThamkhamMa());
       for (ClsKham cls : clsKhams)
       {
         ClsKhamExt clsExt = new ClsKhamExt();
         clsExt.setCls(cls);
         clsExt.setBankhamTen(cls.getClskhamThamkham().getThamkhamBankham(true).getDtdmbankhamTen());
         this.listCLSBanKhamTruoc.add(clsExt);
       }
     }
     this.log.info("End showCLSBanKhamTruoc", new Object[0]);
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

   public String getGhiChu()
   {
     return this.ghiChu;
   }

   public void setGhiChu(String ghiChu)
   {
     this.ghiChu = ghiChu;
   }

   public boolean isDaThanhtoan()
   {
     return this.daThanhtoan;
   }

   public void setDaThanhtoan(boolean daThanhtoan)
   {
     this.daThanhtoan = daThanhtoan;
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

   public String getKhoaCDHA()
   {
     return this.khoaCDHA;
   }

   public void setKhoaCDHA(String khoaCDHA)
   {
     this.khoaCDHA = khoaCDHA;
   }

   @End
   public void end() {}

   public void displayInfor()
     throws Exception
   {}

   public static void main(String[] args) {}

   public class ClsKhamExt
   {
     ClsKham cls;
     String bankhamTen;

     public ClsKhamExt() {}

     public ClsKhamExt(ClsKham cls, String bankhamTen)
     {
       this.cls = cls;
       this.bankhamTen = bankhamTen;
     }

     public ClsKham getCls(boolean created)
     {
       if (created) {
         this.cls = new ClsKham();
       }
       return this.cls;
     }

     public ClsKham getCls()
     {
       return this.cls;
     }

     public void setCls(ClsKham cls)
     {
       this.cls = cls;
     }

     public String getBankhamTen()
     {
       return this.bankhamTen;
     }

     public void setBankhamTen(String bankhamTen)
     {
       this.bankhamTen = bankhamTen;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.CanLamSanPhauThuatAction

 * JD-Core Version:    0.7.0.1

 */