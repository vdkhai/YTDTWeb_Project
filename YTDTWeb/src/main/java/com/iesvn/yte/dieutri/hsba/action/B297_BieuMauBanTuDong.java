 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
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
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B297_BieuMauBanTuDong")
 @Synchronized(timeout=6000000L)
 public class B297_BieuMauBanTuDong
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(B297_BieuMauBanTuDong.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private Hsba hsba;
   private String ngayCap;
   private String ngayCX;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private String crrDate;
   private boolean isUpdate;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   private int index = 0;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private String gioCX;
   private String gioVV;
   private String ngayVV;
   private int chuandoanMaso;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong";
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public String getGioCX()
   {
     return this.gioCX;
   }

   public void setGioCX(String gioCX)
   {
     this.gioCX = gioCX;
   }

   public String getGioVV()
   {
     return this.gioVV;
   }

   public void setGioVV(String gioVV)
   {
     this.gioVV = gioVV;
   }

   public String getNgayVV()
   {
     return this.ngayVV;
   }

   public void setNgayVV(String ngayVV)
   {
     this.ngayVV = ngayVV;
   }

   private String chuandoanMa = "";
   private String chuandoanName = "";

   public String getChuandoanMa()
   {
     return this.chuandoanMa;
   }

   public void setChuandoanMa(String chuandoanMa)
   {
     this.chuandoanMa = chuandoanMa;
   }

   public int getChuandoanMaso()
   {
     return this.chuandoanMaso;
   }

   public void setChuandoanMaso(int chuandoanMaso)
   {
     this.chuandoanMaso = chuandoanMaso;
   }

   public String getChuandoanName()
   {
     return this.chuandoanName;
   }

   public void setChuandoanName(String chuandoanName)
   {
     this.chuandoanName = chuandoanName;
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");
     this.hsba = new Hsba();
     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
   }

   public void displayHSBA()
   {
     log.info("---displayHSBAquang");
     String maHsba = this.hsba.getHsbaSovaovien().trim();
     log.info("maHsba = " + maHsba);
     Hsba hsba_tmp = null;
     if (!maHsba.equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
       if (hsba_tmp == null)
       {
         this.nofoundHSBA = "true";
         hsba_tmp = new Hsba();
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { maHsba });
         hsba_tmp = new Hsba();
         setInfoIfNullForHsba(hsba_tmp);
         this.hsba = hsba_tmp;
       }
       else
       {
         this.hsba = hsba_tmp;
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       this.hsba = new Hsba();
       setInfoIfNullForHsba(this.hsba);
     }
   }

   private void setInfoIfNullForHsba(Hsba obj)
   {
     if (obj.getBenhnhanMa() == null)
     {
       BenhNhan _benhnhan = new BenhNhan();
       setInfoIfNullForBenhNhan(_benhnhan);
       obj.setBenhnhanMa(_benhnhan);
     }
   }

   private void setInfoIfNullForBenhNhan(BenhNhan obj)
   {
     if (obj.getDmgtMaso() == null) {
       obj.setDmgtMaso(new DmGioiTinh());
     }
     if (obj.getDantocMa() == null) {
       obj.setDantocMa(new DmDanToc());
     }
     if (obj.getTinhMa() == null) {
       obj.setTinhMa(new DmTinh());
     }
     if (obj.getHuyenMa() == null) {
       obj.setHuyenMa(new DmHuyen());
     }
     if (obj.getXaMa() == null) {
       obj.setXaMa(new DmXa());
     }
     if (obj.getBenhnhanNghe() == null) {
       obj.setBenhnhanNghe(new DmNgheNghiep());
     }
   }

   private String loaiBaoCao = "";

   public String getLoaiBaoCao()
   {
     return this.loaiBaoCao;
   }

   public void setLoaiBaoCao(String loaiBaoCao)
   {
     this.loaiBaoCao = loaiBaoCao;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   private String sobuong = "";
   private String sogiuong = "";

   public String getSobuong()
   {
     return this.sobuong;
   }

   public void setSobuong(String sobuong)
   {
     if (sobuong != null) {
       this.sobuong = sobuong;
     }
   }

   public String getSogiuong()
   {
     return this.sogiuong;
   }

   public void setSogiuong(String sogiuong)
   {
     if (sogiuong != null) {
       this.sogiuong = sogiuong;
     }
   }

   public void findGiuongPhong()
   {
     try
     {
       if (this.hsba.getHsbaKhoadangdt(true).getDmkhoaMaso() != null)
       {
         HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();

         HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(this.hsba.getHsbaSovaovien(), this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
         setSobuong(hsbaChuyenMon.getHsbacmSobuong());
         setSogiuong(hsbaChuyenMon.getHsbacmSogiuong());
       }
     }
     catch (Exception e) {}
   }

   public void XuatReport()
   {
     this.loaiBCDT = "BieuMauBanTuDong";
     log.info("Vao Method XuatReport giay chuyen xac");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       System.out.println("loaibaocao = " + this.loaiBaoCao);

       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bieumaubantudong_sub" + this.loaiBaoCao + ".jrxml";
       log.info("da thay file templete " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       params.put("tenDonViTat", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       String diachistr = "";
       String hinh1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bieumaubantudong_sub" + this.loaiBaoCao + ".jpg";
       params.put("HINH1", hinh1);
       if (this.hsba.getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);



       findGiuongPhong();

       params.put("BUONG", getSobuong());
       params.put("GIUONG", getSogiuong());
       if (this.hsba.getHsbaKhoadangdt().getDmkhoaTen() != null) {
         params.put("KHOA_HIENTAI", this.hsba.getHsbaKhoadangdt().getDmkhoaTen());
       } else {
         params.put("KHOA_HIENTAI", "");
       }
       params.put("gioiTinh", this.hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());

       params.put("SOVAOVIEN", this.hsba.getHsbaSovaovien());

       log.info("Sovaovien = " + this.hsba.getHsbaSovaovien());


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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "BieuMauBanTuDong");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     System.out.println("qdate = " + (date == null ? "isnull" : date.toString()));
     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNofoundHSBA()
   {
     return this.nofoundHSBA;
   }

   public void setNofoundHSBA(String nofoundHSBA)
   {
     this.nofoundHSBA = nofoundHSBA;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
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

   public String getCrrDate()
   {
     return this.crrDate;
   }

   public void setCrrDate(String crrDate)
   {
     this.crrDate = crrDate;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B297_BieuMauBanTuDong

 * JD-Core Version:    0.7.0.1

 */