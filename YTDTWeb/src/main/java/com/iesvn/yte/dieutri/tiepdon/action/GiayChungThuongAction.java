 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.GiayChungThuongDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.GiayChungThuong;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmNgheNghiep;
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
 @Name("B121_12_giaychungthuong")
 @Synchronized(timeout=6000000L)
 public class GiayChungThuongAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngay;
   private String thoiGian;
   private String gioThamKham;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungThuong;
   private SimpleDateFormat formatter;
   private BenhNhan benhNhan;
   public String ngaySinh;
   private ThamKham thamkham;
   public String ngaytuvong;
   public String giotuvong;

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
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
   private GiayChungThuong giayChungThuong = null;

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   @Begin(nested=true)
   @Factory("goToGiayChungThuong")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if ((this.thamkham != null) && (this.thamkham.getTiepdonMa() != null)) {
         this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       } else {
         this.benhNhan = new BenhNhan();
       }
       GiayChungThuong giayChungThuongTemp = null;
       try
       {
         giayChungThuongTemp = GiayChungThuongDelegate.getInstance().getGiayChungThuong(this.thamkham.getThamkhamMa());
       }
       catch (Exception e)
       {
         log.info("error:" + e);
       }
       this.ngaytuvong = (this.giotuvong = "");
       if (giayChungThuongTemp != null)
       {
         this.giayChungThuong = giayChungThuongTemp;
       }
       else
       {
         this.giayChungThuong = new GiayChungThuong();
         this.giayChungThuong.setGctThamkham(this.thamkham);
       }
       setOtherValue();


       destroyService();

       this.goToGiayChungThuong = "";
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
     this.goToGiayChungThuong = null;
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

   public GiayChungThuong getGiayChungThuong()
   {
     return this.giayChungThuong;
   }

   public void setGiayChungThuong(GiayChungThuong giayChungThuong)
   {
     this.giayChungThuong = giayChungThuong;
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

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     reset_ctbant();
     log.info("*****End delete() *****");
   }

   public void reset_ctbant() {}

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("***Starting ghinhan **");
     log.info("***giayChungThuong.getGctMa() = **" + this.giayChungThuong.getGctMa());
     if (this.giayChungThuong.getGctMa() == null) {
       GiayChungThuongDelegate.getInstance().create(this.giayChungThuong);
     } else {
       GiayChungThuongDelegate.getInstance().edit(this.giayChungThuong);
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     log.info("***Finished ghinhan **");
     return "/B1_Tiepdon/B121_12_Giaychungthuong";
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToGiayChungThuong = null;
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
     this.reportTypeTD = "giaychungthuong";

     log.info("Vao Method XuatReport giaychungthuong");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/Giaychungthuong.jrxml";
       log.info("da thay file templete 5" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       GiayChungThuong giayChungThuongTemp = null;
       try
       {
         giayChungThuongTemp = GiayChungThuongDelegate.getInstance().getGiayChungThuong(this.thamkham.getThamkhamMa());
       }
       catch (Exception e)
       {
         log.info("error:" + e);
       }
       if (giayChungThuongTemp != null) {
         this.giayChungThuong = giayChungThuongTemp;
       }
       params.put("hoTen", this.benhNhan.getBenhnhanHoten());


       Date dNgaySinh = this.benhNhan.getBenhnhanNgaysinh();
       if (dNgaySinh != null) {
         params.put("ngaySinh", "" + sdf.format(this.benhNhan.getBenhnhanNgaysinh()));
       } else if (this.benhNhan.getBenhnhanNamsinh() != null) {
         params.put("namSinh", this.benhNhan.getBenhnhanNamsinh());
       } else {
         params.put("namSinh", "");
       }
       params.put("ngheTen", this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
       params.put("cmnd", this.benhNhan.getBenhnhanCmnd());


       String diachistr = "";
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhNhan.getBenhnhanDiachi();
       }
       if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + ", " + this.benhNhan.getXaMa(true).getDmxaTen();
       }
       if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + ", " + this.benhNhan.getHuyenMa(true).getDmhuyenTen();
       }
       if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + ", " + this.benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("diaChi", diachistr);


       String maChanDoan = "";
       String tenChanDoan = "";
       if (this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd() != null)
       {
         if (this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdMa() != null) {
           maChanDoan = this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdMa();
         }
         if (this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdTen() != null) {
           tenChanDoan = this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMachdoanbd().getDmbenhicdTen();
         }
       }
       params.put("CHANDOAN", maChanDoan + " - " + tenChanDoan);


       Date dVaov = this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonNgaygio();
       if (dVaov != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dVaov);
         params.put("gioVaov", "" + cal.get(10));
         params.put("phutVaov", "" + cal.get(12));
         params.put("ngayVaov", "" + cal.get(5));
         params.put("thangVaov", "" + (cal.get(2) + 1));
         params.put("namVaov", "" + cal.get(1));
       }
       else
       {
         params.put("gioVaov", "");
         params.put("phutVaov", "");
         params.put("ngayVaov", "");
         params.put("thangVaov", "");
         params.put("namVaov", "");
       }
       Date dRav = this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonNgaygiora();
       if (dRav != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dRav);
         params.put("gioRav", "" + cal.get(10));
         params.put("phutRav", "" + cal.get(12));
         params.put("ngayRav", "" + cal.get(5));
         params.put("thangRav", "" + (cal.get(2) + 1));
         params.put("namRav", "" + cal.get(1));
       }
       else
       {
         params.put("gioRav", "");
         params.put("phutRav", "");
         params.put("ngayRav", "");
         params.put("thangRav", "");
         params.put("namRav", "");
       }
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("tenDonViFull", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("soVaoVien", this.giayChungThuong.getGctThamkham().getTiepdonMa().getTiepdonMa());
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("gioiTinh", this.benhNhan.getDmgtMaso(true).getDmgtTen());

       params.put("soGCT", this.giayChungThuong.getGctMa());
       params.put("thuongtichvaovien", this.giayChungThuong.getGctThuongtichvaovien());
       params.put("thuongtichravien", this.giayChungThuong.getGctThuongtichravien());
       params.put("dieutri", this.giayChungThuong.getGctDieutri());



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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.GiayChungThuongAction

 * JD-Core Version:    0.7.0.1

 */