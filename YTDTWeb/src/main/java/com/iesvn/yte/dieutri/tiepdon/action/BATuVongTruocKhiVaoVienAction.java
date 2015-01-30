 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TuvongTruocDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TuvongTruoc;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
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
 @Name("B121_11_Batuvongtruockhivaovien")
 @Synchronized(timeout=6000000L)
 public class BATuVongTruocKhiVaoVienAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String gioThamKham;
   private String ngay;
   private String dienbienbenh;
   private String ylenh;
   private String bacSiMa;
   private String giuXac;
   private Integer bacSiMaSo_hidden;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToLapBATuVongTruocKhiVaoVien;
   private SimpleDateFormat formatter;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   public String nguoiduadengioitinh;
   public String nguoidungxingioitinh;
   public String ngaytuvong;
   public String giotuvong;

   public String getNguoiduadengioitinh()
   {
     return this.nguoiduadengioitinh;
   }

   public void setNguoiduadengioitinh(String nguoiduadengioitinh)
   {
     this.nguoiduadengioitinh = nguoiduadengioitinh;
   }

   public String getNguoidungxingioitinh()
   {
     return this.nguoidungxingioitinh;
   }

   public void setNguoidungxingioitinh(String nguoidungxingioitinh)
   {
     this.nguoidungxingioitinh = nguoidungxingioitinh;
   }

   public String getGiotuvong()
   {
     return this.giotuvong;
   }

   public void setGiotuvong(String giotuvong)
   {
     this.giotuvong = giotuvong;
   }

   public String getNgaytuvong()
   {
     return this.ngaytuvong;
   }

   public void setNgaytuvong(String ngaytuvong)
   {
     this.ngaytuvong = ngaytuvong;
   }

   public void resetValue()
   {
     this.ngaytuvong = (this.giotuvong = "");
   }

   private String resultHidden = "";
   private TuvongTruoc tuvongTruoc = null;

   @Begin(nested=true)
   @Factory("goToLapBATuVongTruocKhiVaoVien")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       if (this.thamkham.getThamkhamBacsi() != null) {
         this.bacSiMa = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
       }
       TuvongTruoc tuvongTruocTemp = null;
       try
       {
         tuvongTruocTemp = TuvongTruocDelegate.getInstance().getTuvongTruoc(this.thamkham.getThamkhamMa());
       }
       catch (Exception e)
       {
         log.info("error:" + e);
       }
       this.ngaytuvong = (this.giotuvong = "");
       if (tuvongTruocTemp != null)
       {
         this.tuvongTruoc = tuvongTruocTemp;
         this.giuXac = (this.tuvongTruoc.getTvtGiuxac().booleanValue() ? "0" : "1");
         this.nguoiduadengioitinh = (this.tuvongTruoc.getTvtGioitinhNguoiduaden().booleanValue() ? "1" : "0");
         this.nguoidungxingioitinh = (this.tuvongTruoc.getTvtNguoixinxacGioitinh().booleanValue() ? "1" : "0");
         log.info("%%%%%%%%%%%%%%%%%%%%%tuvongTruoc.getTvtMa(): " + this.tuvongTruoc.getTvtMa());
         if (this.tuvongTruoc.getTvtTuvongluc() != null)
         {
           setNgaytuvong(formatDate(this.tuvongTruoc.getTvtTuvongluc()));
           setGiotuvong(formatDateTime(this.tuvongTruoc.getTvtTuvongluc()));
         }
       }
       else
       {
         this.tuvongTruoc = new TuvongTruoc();
         this.tuvongTruoc.setTvtBenhnhan(this.benhNhan);
         this.tuvongTruoc.setTvtBankham(this.thamkham.getThamkhamBankham(true));
         this.tuvongTruoc.setTvtThamkham(this.thamkham);
         this.giuXac = "0";
         this.nguoiduadengioitinh = "0";
         this.nguoidungxingioitinh = "0";
       }
       setOtherValue();





       destroyService();

       this.goToLapBATuVongTruocKhiVaoVien = "";
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
     }
     log.info("***Finished init ***");
   }

   @End
   public void end()
   {
     this.goToLapBATuVongTruocKhiVaoVien = null;
   }

   public TuvongTruoc getTuvongTruoc()
   {
     return this.tuvongTruoc;
   }

   public void setTuvongTruoc(TuvongTruoc tuvongTruoc)
   {
     this.tuvongTruoc = tuvongTruoc;
   }

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     System.out.println("qdate = " + (date == null ? "isnull" : date.toString()));

     return date == null ? "" : Utils.getGioPhut(date);
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     insertRow();
     reset_ctbant();
     log.info("*****End Enter() *****");
   }

   private void insertRow() {}

   public String getGiuXac()
   {
     return this.giuXac;
   }

   public void setGiuXac(String giuXac)
   {
     this.giuXac = giuXac;
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     reset_ctbant();
     log.info("*****End delete() *****");
   }

   public void reset_ctbant()
   {
     this.dienbienbenh = "";
     this.ylenh = "";
   }

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("***Starting ghinhan **");












     log.info("ngaytuvong = " + this.ngaytuvong);
     log.info("giotuvong = " + this.giotuvong);
     if (!this.ngaytuvong.trim().equals("")) {
       if (this.giotuvong.trim().equals("")) {
         this.tuvongTruoc.setTvtTuvongluc(Utils.getDBDate("00:00", this.ngaytuvong).getTime());
       } else {
         this.tuvongTruoc.setTvtTuvongluc(Utils.getDBDate(this.giotuvong, this.ngaytuvong).getTime());
       }
     }
     log.info("Giu xac = " + this.tuvongTruoc.getTvtGiuxac());
     log.info("Giu xac = " + getGiuXac());
     if ((this.giuXac != null) && (this.giuXac.equals("0")))
     {
       this.tuvongTruoc.setTvtGiuxac(Boolean.valueOf(true));
       this.tuvongTruoc.setTvtXinxackhongkhieunai(Boolean.valueOf(false));
     }
     else
     {
       this.tuvongTruoc.setTvtGiuxac(Boolean.valueOf(false));
       this.tuvongTruoc.setTvtXinxackhongkhieunai(Boolean.valueOf(true));
     }
     if ((this.nguoiduadengioitinh != null) && (this.nguoiduadengioitinh.equals("0"))) {
       this.tuvongTruoc.setTvtGioitinhNguoiduaden(Boolean.valueOf(false));
     } else {
       this.tuvongTruoc.setTvtGioitinhNguoiduaden(Boolean.valueOf(true));
     }
     if ((this.nguoidungxingioitinh != null) && (this.nguoidungxingioitinh.equals("0"))) {
       this.tuvongTruoc.setTvtNguoixinxacGioitinh(Boolean.valueOf(false));
     } else {
       this.tuvongTruoc.setTvtNguoixinxacGioitinh(Boolean.valueOf(true));
     }
     if (this.tuvongTruoc.getTvtMa() == null) {
       TuvongTruocDelegate.getInstance().create(this.tuvongTruoc);
     } else {
       TuvongTruocDelegate.getInstance().edit(this.tuvongTruoc);
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     log.info("***Finished ghinhan **");
     return "/B1_Tiepdon/B121_11_Batuvongtruockhivaovien";
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToLapBATuVongTruocKhiVaoVien = null;
     log.info("*****End quayLai() *****");
     return "ghinhan";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeTD = "benhantuvongtruockhivaovien";

     log.info("Vao Method XuatReport benhantuvongtruockhivaovien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhantuvongtruoc_main.jrxml";
       String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhantuvongtruoc_sub1.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhantuvongtruoc_sub2.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("sub1", sub0Report);
       params.put("sub2", sub1Report);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       log.info("soyte = " + IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       log.info("ten benh vien = " + IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       log.info("****NGAYSINH: " + this.benhNhan.getBenhnhanNgaysinh());
       params.put("sodt", this.thamkham.getThamkhamBankham(true).getDtdmbankhamTen());

       params.put("HoTen", this.benhNhan.getBenhnhanHoten());

       params.put("Tuoi", this.benhNhan.getBenhnhanTuoi());
       String diachistr = "";
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhNhan.getBenhnhanDiachi() + ", ";
       }
       if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhNhan.getXaMa(true).getDmxaTen() + ", ";
       }
       if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhNhan.getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       params.put("gioiTinh", this.benhNhan.getDmgtMaso(true).getDmgtTen());
       params.put("DonViTuoi", this.benhNhan.getBenhnhanDonvituoi() == null ? new Integer(1) : new Integer(this.benhNhan.getBenhnhanDonvituoi().shortValue()));
       params.put("MAGIAY", this.tuvongTruoc.getTvtMa());





       params.put("gioiTinh", this.benhNhan.getDmgtMaso(true).getDmgtTen());



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
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "benhanvaovien");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);
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

   private int index = 0;
   private String tuNgay;
   private String denNgay;

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
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!this.thamkham.getThamkhamNgaygio().equals("")))
     {
       this.thoiGian = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
       this.ngay = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
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

   public void get_thuoc_info()
   {
     this.ylenh = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       Date tuNgay = Utils.getDBDateWithHour(0, this.ngay, true).getTime();
       Date denNgay = Utils.getDBDateWithHour(23, this.ngay, false).getTime();
       String thuocBK = "";
       String thuocBH = "";
       String thuocVe = "";


       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       List<ThuocPhongKham> listThuocPhongKham = tpkDelegate.findByThamKhamVaNgay(this.thamkham.getThamkhamMa(), tuNgay, denNgay);
       if ((listThuocPhongKham != null) && (listThuocPhongKham.size() > 0))
       {
         for (ThuocPhongKham thuoc : listThuocPhongKham) {
           if (thuoc.getThuocphongkhamLoai().equals("3"))
           {
             thuocBH = thuocBH + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBH = thuocBH + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBH = thuocBH + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocBH = thuocBH + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocBH = thuocBH + "\n";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("1"))
           {
             thuocBK = thuocBK + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBK = thuocBK + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBK = thuocBK + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocBK = thuocBK + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocBK = thuocBK + "\n";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("2"))
           {
             thuocVe = thuocVe + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocVe = thuocVe + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocVe = thuocVe + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocVe = thuocVe + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocVe = thuocVe + "\n";
           }
         }
         if (!thuocBH.equals("")) {
           this.ylenh = (this.ylenh + "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH);
         }
         if (!thuocBK.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK);
         }
         if (!thuocVe.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe);
         }
       }
       else
       {
         this.ylenh = IConstantsRes.KHONG_DUNG_THUOC;
       }
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

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getGioThamKham()
   {
     return this.gioThamKham;
   }

   public void setGioThamKham(String gioThamKham)
   {
     this.gioThamKham = gioThamKham;
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

   public TuvongTruoc getBaNgoaiTru()
   {
     return this.tuvongTruoc;
   }

   public void setBaNgoaiTru(TuvongTruoc tuvongTruoc)
   {
     this.tuvongTruoc = tuvongTruoc;
   }

   public String getTuNgay()
   {
     return this.tuNgay;
   }

   public void setTuNgay(String tuNgay)
   {
     this.tuNgay = tuNgay;
   }

   public String getDenNgay()
   {
     return this.denNgay;
   }

   public void setDenNgay(String denNgay)
   {
     this.denNgay = denNgay;
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getDienbienbenh()
   {
     return this.dienbienbenh;
   }

   public void setDienbienbenh(String dienbienbenh)
   {
     this.dienbienbenh = dienbienbenh;
   }

   public String getYlenh()
   {
     return this.ylenh;
   }

   public void setYlenh(String ylenh)
   {
     this.ylenh = ylenh;
   }

   public String getBacSiMa()
   {
     return this.bacSiMa;
   }

   public void setBacSiMa(String bacSiMa)
   {
     this.bacSiMa = bacSiMa;
   }

   public Integer getBacSiMaSo_hidden()
   {
     return this.bacSiMaSo_hidden;
   }

   public void setBacSiMaSo_hidden(Integer bacSiMaSo_hidden)
   {
     this.bacSiMaSo_hidden = bacSiMaSo_hidden;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.BATuVongTruocKhiVaoVienAction

 * JD-Core Version:    0.7.0.1

 */