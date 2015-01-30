 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.GiayNghiViecHuongBhxhDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
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
 @Name("B116_Giaychungnhan")
 @Synchronized(timeout=6000000L)
 public class GiayChungNhanAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String gioThamKham;
   private GiayNghiViecHuongBhxhDelegate gnvhbhxhDAO;
   private static Logger log = Logger.getLogger(GiayChungNhanAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungNhan;
   @Out(required=false)
   private String donViCongTac;
   @Out(required=false)
   private String lyDoNghiViec;
   @Out(required=false)
   private String soNgayChoNghi;
   @Out(required=false)
   private String tuNgay;
   @Out(required=false)
   private String denNgay;
   @In(required=false)
   @Out(required=false)
   private String bacSiKCB;
   private SimpleDateFormat formatter;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   private GiayNghiViecHuongBhxh giayNghiViec;

   public String getLyDoNghiViec()
   {
     return this.lyDoNghiViec;
   }

   public void setLyDoNghiViec(String lyDoNghiViec)
   {
     this.lyDoNghiViec = lyDoNghiViec;
   }

   public String getDonViCongTac()
   {
     return this.donViCongTac;
   }

   public void setDonViCongTac(String donViCongTac)
   {
     this.donViCongTac = donViCongTac;
   }

   public String getSoNgayChoNghi()
   {
     return this.soNgayChoNghi;
   }

   public void setSoNgayChoNghi(String soNgayChoNghi)
   {
     this.soNgayChoNghi = soNgayChoNghi;
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

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToGiayChungNhan()
   {
     return this.goToGiayChungNhan;
   }

   public void setGoToGiayChungNhan(String goToGiayChungNhan)
   {
     this.goToGiayChungNhan = goToGiayChungNhan;
   }

   private String resultHidden = "";

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToGiayChungNhan")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       this.formatter = new SimpleDateFormat("dd/MM/yyyy");
       log.info("maBanKhamOut:" + this.maBanKhamOut);
       log.info("maTiepDonOut:" + this.maTiepDonOut);
       this.formatter = new SimpleDateFormat("dd/MM/yyyy");
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.gnvhbhxhDAO = GiayNghiViecHuongBhxhDelegate.getInstance();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       if (this.thamkham.getThamkhamBacsi() != null) {
         this.bacSiKCB = this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen();
       }
       this.giayNghiViec = this.gnvhbhxhDAO.findByThamKhamMa(this.thamkham.getThamkhamMa());
       if (this.giayNghiViec == null)
       {
         this.giayNghiViec = new GiayNghiViecHuongBhxh();
       }
       else
       {
         this.donViCongTac = this.giayNghiViec.getGnvhbhxhDonvicongtac();
         this.lyDoNghiViec = this.giayNghiViec.getGnvhbhxhLydonghiviec();
         this.soNgayChoNghi = this.giayNghiViec.getGnvhbhxhSongaynghi();
         if (this.giayNghiViec.getGnvhbhxhTungay() != null) {
           this.tuNgay = this.formatter.format(this.giayNghiViec.getGnvhbhxhTungay());
         }
         if (this.giayNghiViec.getGnvhbhxhDenngay() != null) {
           this.denNgay = this.formatter.format(this.giayNghiViec.getGnvhbhxhDenngay());
         }
       }
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

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("*****Begin ghiNhanAjax() *****");
     try
     {
       this.giayNghiViec.setGnvhbhxhTungay(this.formatter.parse(this.tuNgay));
       this.giayNghiViec.setGnvhbhxhDenngay(this.formatter.parse(this.denNgay));
       this.giayNghiViec.setGnvhbhxhDonvicongtac(this.donViCongTac);
       this.giayNghiViec.setGnvhbhxhLydonghiviec(this.lyDoNghiViec);
       this.giayNghiViec.setGnvhbhxhSongaynghi(this.soNgayChoNghi);
       this.giayNghiViec.setThamkhamMa(this.thamkham);
       if (this.giayNghiViec.getGnvhbhxhMaso() == null) {
         this.gnvhbhxhDAO.create(this.giayNghiViec);
       } else {
         this.gnvhbhxhDAO.edit(this.giayNghiViec);
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
     this.goToGiayChungNhan = null;
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
     try
     {
       ghiNhanAjax();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeTD = "giaychungnhan";
     log.info("Vao Method XuatReport giaychungnhan");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/C65-HD_GiaychungnhannghiviechuongBHXH.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");

       Map<String, Object> params = new HashMap();

       params.put("hoTen", this.benhNhan.getBenhnhanHoten());
       if (this.benhNhan.getBenhnhanNamsinh() != null) {
         params.put("namSinh", this.benhNhan.getBenhnhanNamsinh());
       }
       params.put("soNgayNghi", this.soNgayChoNghi);
       params.put("donViCongTac", this.donViCongTac);

       params.put("bacSiKCB", this.bacSiKCB);
       params.put("lyDoNghiViec", this.lyDoNghiViec);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_TINH);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       try
       {
         params.put("tuNgay", this.formatter.parse(this.tuNgay));
         params.put("denNgay", this.formatter.parse(this.denNgay));
       }
       catch (Exception e) {}
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "giaychungnhan");
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

   public void displayInfor()
     throws Exception
   {}

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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.GiayChungNhanAction

 * JD-Core Version:    0.7.0.1

 */