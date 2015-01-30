 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayChuyenXacDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
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
 @Name("B290_Giaychuyenxac")
 @Synchronized(timeout=6000000L)
 public class GiayChuyenXacAction
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(GiayChuyenXacAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private HsbaGiayChuyenXac gcx;
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
     return "DieuTri_LapVanBanTheoMau_GiayChuyenXac";
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
     this.gcx = new HsbaGiayChuyenXac();
     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
   }

   public void displayHSBA()
   {
     log.info("---displayHSBAquang");
     String maHsba = this.gcx.getHsbaSovaovien().getHsbaSovaovien().trim();
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
         this.gcx.setHsbaSovaovien(hsba_tmp);
         setNgayVV("");
         setGioVV("");
         setChuandoanMa("");
         setChuandoanMaso(0);
         setChuandoanName("");
       }
       else
       {
         this.gcx.setHsbaSovaovien(hsba_tmp);

         setNgayVV(formatDate(hsba_tmp.getHsbaNgaygiovaov()));
         setGioVV(formatDateTime(hsba_tmp.getHsbaNgaygiovaov()));
         setChuandoanMa(hsba_tmp.getHsbaTuvong(true).getDmbenhicdMa());
         if (hsba_tmp.getHsbaTuvong(true).getDmbenhicdMaso() != null) {
           setChuandoanMaso(hsba_tmp.getHsbaTuvong(true).getDmbenhicdMaso().intValue());
         }
         setChuandoanName(hsba_tmp.getHsbaTuvong(true).getDmbenhicdTen());
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       setInfoIfNullForHsba(hsba_tmp);
       this.gcx.setHsbaSovaovien(hsba_tmp);
       setNgayVV("");
       setGioVV("");
       setChuandoanMa("");
       setChuandoanMaso(0);
       setChuandoanName("");
     }
     System.out.println("Ma so = " + getChuandoanMaso());
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

   public void displayGiayChuyenXac()
   {
     log.info("---displayGiayChuyenXac");
     String maGcx = this.gcx.getHsbagcxMa().trim();
     HsbaGiayChuyenXac gcx_tmp = null;
     if (!maGcx.equals(""))
     {
       gcx_tmp = HsbaGiayChuyenXacDelegate.getInstance().findByHsbagcxMa(maGcx);
       if (gcx_tmp == null)
       {
         this.nofound = "true";
         this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
         gcx_tmp = new HsbaGiayChuyenXac();
         FacesMessages.instance().add(IConstantsRes.GCX_NULL, new Object[] { maGcx });
       }
       this.gcx = gcx_tmp;

       setNgayVV(formatDate(this.gcx.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
       setGioVV(formatDateTime(this.gcx.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
       setChuandoanMa(this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa());
       if (this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMaso() != null) {
         setChuandoanMaso(this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMaso().intValue());
       }
       setNgayCX(formatDate(this.gcx.getHsbagcxNgaycx()));
       setGioCX(formatDateTime(this.gcx.getHsbagcxNgaycx()));
       setChuandoanName(this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen());

       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
       this.gcx = new HsbaGiayChuyenXac();
     }
   }

   public String getdmBenhIcdMaCX()
   {
     return this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa();
   }

   public String getdmBenhIcdTenCX()
   {
     return this.gcx.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen();
   }

   public void ghiNhan()
   {
     log.info("---ghiNhan");
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.gcx.setHsbagcxNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (!this.ngayCX.trim().equals("")) {
       if (this.gioCX.trim().equals("")) {
         this.gcx.setHsbagcxNgaycx(Utils.getDBDate("00:00", this.ngayCX).getTime());
       } else {
         this.gcx.setHsbagcxNgaycx(Utils.getDBDate(this.gioCX, this.ngayCX).getTime());
       }
     }
     if (!this.ngayVV.trim().equals("")) {
       if (this.gioVV.trim().equals("")) {
         this.gcx.setHsbagcxNgayvaovien(Utils.getDBDate("00:00", this.ngayVV).getTime());
       } else {
         this.gcx.setHsbagcxNgayvaovien(Utils.getDBDate(this.gioVV, this.ngayVV).getTime());
       }
     }
     this.gcx.setHsbagcxNgaygiocn(new Date());
     this.gcx.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername()));
     DmBenhIcd dmbTmp = new DmBenhIcd();
     System.out.println("chuan doan ma so = " + getChuandoanMaso());
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();


       dmbTmp = (DmBenhIcd)dtutilDelegate.findByMa(getChuandoanMa(), "DmBenhIcd", "dmbenhicdMa");

       this.gcx.setHsbagcxChuandoanma(dmbTmp);
     }
     catch (Exception ex)
     {
       System.out.println("Loi tai " + ex.toString());
     }
     if (this.isUpdate)
     {
       result = HsbaGiayChuyenXacDelegate.getInstance().update(this.gcx);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCX_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.gcx.setHsbagcxMa(result);
         FacesMessages.instance().add(IConstantsRes.GCX_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaGiayChuyenXacDelegate.getInstance().insert(this.gcx);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCX_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.gcx.setHsbagcxMa(result);
         FacesMessages.instance().add(IConstantsRes.GCX_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "GiayChuyenXac";
     log.info("Vao Method XuatReport giay chuyen xac");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaychuyenxac.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("khoaRav", this.gcx.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());

       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("magiay", this.gcx.getHsbagcxMa());
       log.info(this.gcx.getHsbagcxMa());
       if (this.gcx.getHsbaSovaovien() != null)
       {
         Hsba hsba = this.gcx.getHsbaSovaovien();
         String diachistr = "";
         if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
         }
         if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
         }
         if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
         }
         if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
         }
         params.put("DIACHI", diachistr);

         HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
         HsbaBhyt hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
         if (hsbaBHYT != null)
         {
           if (hsbaBHYT.getHsbabhytGiatri0() != null) {
             params.put("GIATRITU", sdf.format(hsbaBHYT.getHsbabhytGiatri0()));
           }
           if (hsbaBHYT.getHsbabhytGiatri1() != null) {
             params.put("GIATRIDEN", sdf.format(hsbaBHYT.getHsbabhytGiatri1()));
           }
           String so_bhyt = "";
           if ((hsbaBHYT.getHsbabhytSothebh() != null) && (!hsbaBHYT.getHsbabhytSothebh().equals("")))
           {
             so_bhyt = hsbaBHYT.getHsbabhytSothebh();
             params.put("khoiBhytMa", so_bhyt.substring(0, 2));
             params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
             params.put("tinhBhytMa", so_bhyt.substring(3, 5));
             params.put("namBhyt", so_bhyt.substring(5, 7));
             params.put("maCoQuan", so_bhyt.substring(7, 10));
             if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null) {
               params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());
             } else {
               params.put("THEBHYT", so_bhyt.substring(10));
             }
           }
         }
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "GiayChuyenXac");
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

   public HsbaGiayChuyenXac getGcx()
   {
     return this.gcx;
   }

   public void setGcx(HsbaGiayChuyenXac gcx)
   {
     this.gcx = gcx;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String getNgayCX()
   {
     return this.ngayCX;
   }

   public void setNgayCX(String ngayCX)
   {
     this.ngayCX = ngayCX;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.GiayChuyenXacAction

 * JD-Core Version:    0.7.0.1

 */