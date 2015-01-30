 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuKhamChuyenKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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
 @Name("B119_Phieukhamchuyenkhoa")
 @Synchronized(timeout=6000000L)
 public class PhieuKhamChuyenKhoa
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String sMaPhieuKhamDT;
   private String ngaySinh = "";
   private String gioi = "";
   private com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa objPhieuKhamCK = new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
   private String sShowDel = "";
   private String sShowPrint = "";
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToPhieuKhamChuyenKhoa;
   @Out(required=false)
   private String bacSiKCB;
   private BenhNhan benhNhan;
   private ThamKham thamkham;

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToPhieuKhamChuyenKhoa")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if (this.thamkham != null)
       {
         this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
         this.objPhieuKhamCK = PhieuKhamChuyenKhoaDelegate.getInstance().findByMaThamKham(this.thamkham.getThamkhamMa());
         if (this.objPhieuKhamCK != null)
         {
           this.sMaPhieuKhamDT = this.objPhieuKhamCK.getPkckMa();
           this.sShowDel = "true";
           this.sShowPrint = "true";
         }
         else
         {
           this.objPhieuKhamCK = new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
           this.sMaPhieuKhamDT = "";
           this.sShowDel = "false";
           this.sShowPrint = "false";
         }
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

   public String ghiNhan()
     throws Exception
   {
     log.info("*****Begin ghiNhan() *****");
     try
     {
       PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel = PhieuKhamChuyenKhoaDelegate.getInstance();

       this.objPhieuKhamCK.setPkdtntThamkham(this.thamkham);
       this.sMaPhieuKhamDT = PhieuKhamDtNgoaiTruDel.capNhatPhieuKhamChuyenKhoa(this.objPhieuKhamCK, this.sMaPhieuKhamDT);
       FacesMessages.instance().add(IConstantsRes.RPPKCK_INSERT_SUCCESS, new Object[] { this.sMaPhieuKhamDT });
       log.info("*****CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
       this.sShowDel = "true";
       this.sShowPrint = "true";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.error("*************loi***********" + e.toString());
       return null;
     }
     log.info("*****End ghiNhan() *****");
     return null;
   }

   public void huyPhieu()
   {
     log.info("***** start  huyPhieu() *****");
     if ((this.sMaPhieuKhamDT == null) || (this.sMaPhieuKhamDT.equals(""))) {
       return;
     }
     PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel = PhieuKhamChuyenKhoaDelegate.getInstance();
     com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa obj = PhieuKhamDtNgoaiTruDel.find(this.sMaPhieuKhamDT);
     if (obj == null) {
       return;
     }
     PhieuKhamDtNgoaiTruDel.remove(obj);
     FacesMessages.instance().add(IConstantsRes.RPPKCK_DELETE_SUCCESS, new Object[] { this.sMaPhieuKhamDT });
     log.info("***** XOA THANH CONG *****");
     this.objPhieuKhamCK = new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
     this.sMaPhieuKhamDT = "";
     this.sShowDel = "false";
     this.sShowPrint = "false";
     log.info("***** end  huyPhieu() *****");
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToPhieuKhamChuyenKhoa = null;
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
     this.reportTypeTD = "phieukhamchuyenkhoa";
     log.info("Vao Method XuatReport phieukhamchuyenkhoa");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieukhamchuyenkhoa.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("HOTEN", this.benhNhan.getBenhnhanHoten());
       params.put("GIOI", this.benhNhan.getDmgtMaso(true).getDmgtTen());
       if ((this.sMaPhieuKhamDT != null) && (!this.sMaPhieuKhamDT.equals(""))) {
         params.put("MAPHIEU", this.sMaPhieuKhamDT);
       }
       String diaChi = "";
       String tinh = "";
       String huyen = "";
       String xa = "";
       String duong = "";
       if (this.benhNhan.getTinhMa() == null) {
         tinh = "";
       } else {
         tinh = this.benhNhan.getTinhMa().getDmtinhTen();
       }
       if (this.benhNhan.getHuyenMa() == null) {
         huyen = "";
       } else {
         huyen = this.benhNhan.getHuyenMa().getDmhuyenTen() + "-";
       }
       if (this.benhNhan.getXaMa() == null) {
         xa = "";
       } else {
         xa = this.benhNhan.getXaMa().getDmxaTen() + "-";
       }
       if (this.benhNhan.getBenhnhanDiachi() == null) {
         duong = "";
       } else {
         duong = this.benhNhan.getBenhnhanDiachi() + "-";
       }
       diaChi = duong + xa + huyen + tinh;
       params.put("DIACHI", diaChi);
       params.put("MATHEBHYT", this.thamkham.getTiepdonMa(true).getTiepdonSothebh());
       params.put("KHOA", this.thamkham.getTiepdonMa(true).getTiepdonBankham().getDtdmbankhamTen());
       if ((this.thamkham.getTiepdonMa(true).getTiepdonGiatri1() != null) && (this.thamkham.getTiepdonMa(true).getTiepdonGiatri2() != null))
       {
         params.put("GTSUDUNG1", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonGiatri1()));
         params.put("GTSUDUNG2", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonGiatri2()));
       }
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
       params.put("BUONG", this.thamkham.getThamkhamBankham(true).getDtdmbankhamTen());


       log.info("*****chanDoan: " + chanDoan);
       if (this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen() != null) {
         params.put("BACSI", this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen());
       }
       params.put("KINHGOI", this.objPhieuKhamCK.getPkckKinhgoi());
       params.put("YEUCAU", this.objPhieuKhamCK.getPkckYeucaukhamck());

       String sDonViTuoi = "";
       if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", this.benhNhan.getBenhnhanTuoi() + " " + sDonViTuoi);

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "phieukhamchuyenkhoa");
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

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.sMaPhieuKhamDT == null) || (this.sMaPhieuKhamDT.equals(""))) {
         return;
       }
       PhieuKhamChuyenKhoaDelegate PhieuKhamDtNgoaiTruDel = PhieuKhamChuyenKhoaDelegate.getInstance();
       this.objPhieuKhamCK = new com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa();
       List<com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa> ls = PhieuKhamDtNgoaiTruDel.findByPhieuKhamChuyenKhoa(getMaPhieuKhamDtNgoaiTru());
       if ((ls == null) || (ls.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.RPPKCK_NOT_EXIST, new Object[0]);
         this.sMaPhieuKhamDT = "";
         this.sShowDel = "false";
         this.sShowPrint = "false";
         return;
       }
       this.sShowDel = "true";
       this.sShowPrint = "true";
       this.objPhieuKhamCK = ((com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa)ls.get(0));
       this.sMaPhieuKhamDT = this.objPhieuKhamCK.getPkckMa();



       log.info("*****sMaPhieuKhamDT: " + this.sMaPhieuKhamDT);
     }
     catch (Exception e)
     {
       System.out.println("error on function displayInfor" + e);
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
     log.info("************************* destroy GiayChuyenVienNguoiBenhCoTheBHYTAction");
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

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToPhieuKhamChuyenKhoa()
   {
     return this.goToPhieuKhamChuyenKhoa;
   }

   public void setGoToPhieuKhamChuyenKhoa(String str)
   {
     this.goToPhieuKhamChuyenKhoa = str;
   }

   public String getMaPhieuKhamDtNgoaiTru()
   {
     return this.sMaPhieuKhamDT;
   }

   public void setMaPhieuKhamDtNgoaiTru(String maGiayChuyenVien)
   {
     this.sMaPhieuKhamDT = maGiayChuyenVien;
   }

   public String getShowDel()
   {
     return this.sShowDel;
   }

   public void setShowDel(String showDel)
   {
     this.sShowDel = showDel;
   }

   public String getShowPrint()
   {
     return this.sShowPrint;
   }

   public void setShowPrint(String showPrint)
   {
     this.sShowPrint = showPrint;
   }

   public com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa getPhieuKhamChuyenKhoa()
   {
     return this.objPhieuKhamCK;
   }

   public void setPhieuKhamChuyenKhoa(com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa objPhieuKhamDTNgoaiTru)
   {
     this.objPhieuKhamCK = objPhieuKhamDTNgoaiTru;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.PhieuKhamChuyenKhoa

 * JD-Core Version:    0.7.0.1

 */