 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayRaVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
 import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
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
 @Name("B234_Giayravien")
 @Synchronized(timeout=6000000L)
 public class GiayRaVienAction
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(GiayRaVienAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private HsbaGiayRaVien grv;
   private String ngayCap;
   private String ngayTk;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private String crrDate;
   private boolean isUpdate = false;
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

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_GiayRaVien";
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");
     this.grv = new HsbaGiayRaVien();

     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
     this.ngayTk = "";
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
     this.nofound = "true";
   }

   public void displayHSBA()
   {
     log.info("---displayHSBA");
     String maHsba = this.grv.getHsbaSovaovien().getHsbaSovaovien().trim();
     Hsba hsba_tmp = null;
     HsbaGiayRaVien grv_tmp = null;
     this.grv = new HsbaGiayRaVien();
     if (!maHsba.equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
       if (hsba_tmp == null)
       {
         this.nofoundHSBA = "true";
         hsba_tmp = new Hsba();
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { maHsba });
       }
       grv_tmp = HsbaGiayRaVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
       if (grv_tmp != null)
       {
         this.grv = grv_tmp;
         this.isUpdate = true;
       }
       else
       {
         this.grv.setHsbaSovaovien(hsba_tmp);
         this.isUpdate = false;
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       this.grv.setHsbaSovaovien(hsba_tmp);
     }
   }

   public void displayGiayRaVien()
   {
     log.info("---displayGiayRaVien");
     String maGrv = this.grv.getHsbagrvMa().trim();
     HsbaGiayRaVien grv_tmp = null;
     this.grv = new HsbaGiayRaVien();
     if (!maGrv.equals(""))
     {
       grv_tmp = HsbaGiayRaVienDelegate.getInstance().findByHsbagrvMa(maGrv);
       if (grv_tmp == null)
       {
         FacesMessages.instance().add(IConstantsRes.GRV_NULL, new Object[] { maGrv });
         resetValue();
       }
       else
       {
         this.isUpdate = true;
         this.grv = grv_tmp;
         this.grv.setHsbagrvMa(maGrv);
         if (this.grv.getHsbagrvNgaytaikham() == null) {
           this.ngayTk = "";
         } else {
           this.ngayTk = new SimpleDateFormat("dd/MM/yyyy").format(this.grv.getHsbagrvNgaytaikham());
         }
       }
     }
     else
     {
       this.nofound = "true";
       resetValue();
     }
   }

   public String getdmBenhIcdMaRV()
   {
     return this.grv.getHsbaSovaovien(true).getHsbaMachdravien(true).getDmbenhicdMa();
   }

   public String getdmBenhIcdTenRV()
   {
     return this.grv.getHsbaSovaovien(true).getHsbaMachdravien(true).getDmbenhicdTen();
   }

   public void ghiNhan()
   {
     log.info("---ghiNhan");
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.grv.setHsbagrvNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (!this.ngayTk.trim().equals("")) {
       this.grv.setHsbagrvNgaytaikham(Utils.getDBDate("00:00", this.ngayTk).getTime());
     }
     this.grv.setHsbagrvNgaygiocn(new Date());
     this.grv.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername()));
     System.out.println("isUpdate = " + this.isUpdate);
     if (this.isUpdate)
     {
       result = HsbaGiayRaVienDelegate.getInstance().update(this.grv);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GRV_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.grv.setHsbagrvMa(result);
         FacesMessages.instance().add(IConstantsRes.GRV_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaGiayRaVienDelegate.getInstance().insert(this.grv);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GRV_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.grv.setHsbagrvMa(result);
         FacesMessages.instance().add(IConstantsRes.GRV_LT_THANHCONG, new Object[] { result });
       }
     }
     System.out.println("isUpdate = " + this.isUpdate);
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "GiayRaVien";
     log.info("Vao Method XuatReport giay ra vien");
     String baocao1 = null;
     String pathTemplate = null;
     String subpathTemplate1 = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giayravien_main.jrxml";
       subpathTemplate1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giayravien_sub1.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport rpt1 = JasperCompileManager.compileReport(subpathTemplate1);

       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("khoaRav", this.grv.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());

       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
       params.put("LOIDAN", this.grv.getHsbagrvLoidan());
       params.put("sub1", rpt1);
       params.put("magiay", this.grv.getHsbagrvMa());

       HsbaPhieuPhauThuatThuThuatDelegate hsbapptttDelegate = HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
       HsbaPhieuPhauThuatThuThuat hsbappttt = hsbapptttDelegate.findByHsba(this.grv.getHsbaSovaovien(true).getHsbaSovaovien());
       if (hsbappttt != null)
       {
         params.put("NGAY_PHAUTHUAT", hsbappttt.getHsbapptttNgaypttt());
         params.put("PP_VOCAM", hsbappttt.getHsbapptttPhuongphapVocam(true).getDtdmvocamDiengiai());
         params.put("CACHTHUC_PHAUTHUAT", hsbappttt.getHsbapptttMotaLuocdo());
         String ptv = hsbapptttDelegate.getPtvByHsbappttt(hsbappttt.getHsbapptttMa());
         if (ptv != null) {
           params.put("PHAUTHUATVIEN", ptv);
         }
       }
       log.info(this.grv.getHsbagrvMa());
       if (this.grv.getHsbaSovaovien() != null)
       {
         Hsba hsba = this.grv.getHsbaSovaovien();
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
       log.info("sau conn");
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       log.info("sau fillreport");
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "GiayRaVien");
       log.info("sau xuat bao cao");
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
     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public HsbaGiayRaVien getGrv()
   {
     return this.grv;
   }

   public void setGrv(HsbaGiayRaVien grv)
   {
     this.grv = grv;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String getNgayTk()
   {
     return this.ngayTk;
   }

   public void setNgayTk(String ngayTk)
   {
     this.ngayTk = ngayTk;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.GiayRaVienAction

 * JD-Core Version:    0.7.0.1

 */