 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNhaCungCap;
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
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B4413_Phantichkinhphinhap")
 @Scope(ScopeType.CONVERSATION)
 public class Phantichkinhphinhap
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4413 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private String pttheo = null;
   private Integer lthuoc_maso = null;
   private Integer plthuoc_maso = null;
   private Integer kho_maso = null;
   private Integer ct_maso = null;
   private Integer kinhphi_maso = null;
   private Integer noiban_maso = null;
   private String lthuoc_ma = "";
   private String plthuoc_ma = "";
   private String kho_ma = "";
   private String ct_ma = "";
   private String kinhphi_ma = "";
   private String noiban_ma = "";

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
     return "BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap";
   }

   @End
   public void end() {}

   @Factory("resetFormB4413")
   public void initresetFormB4413()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4413 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.resetFormB4413 = "";
     setPttheo("r1");
     setLthuoc_ma("");
     setPlthuoc_ma("");
     setKho_ma("");
     setCt_ma("");
     setKinhphi_ma("");
     setNoiban_ma("");
   }

   public void XuatReport()
   {
     this.reportTypeKC = "tinhhinhkinhphinhap";
     this.log.info("Vao Method XuatReport tinh hinh kinh phi nhap", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       this.log.info("tu ngay date " + this.tungay, new Object[0]);
       params.put("TUNGAYDATE", sdf.parse(this.tungay));
       params.put("TUNGAY", this.tungay);

       this.log.info("den ngay date " + this.denngay, new Object[0]);
       params.put("DENNGAYDATE", sdf.parse(this.denngay));
       params.put("DENNGAY", this.denngay);

       this.log.info("truyen tham so loai thuoc " + this.lthuoc_maso, new Object[0]);
       if (this.lthuoc_maso != null)
       {
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
         DmLoaiThuoc loai = new DmLoaiThuoc();
         Object obj = dtutilDelegate.findByMa(this.lthuoc_ma, "DmLoaiThuoc", "dmloaithuocMa");
         loai = (DmLoaiThuoc)obj;
         params.put("PHANLOAI", loai.getDmloaithuocTen());
       }
       else
       {
         params.put("PHANLOAI", "ALL");
       }
       this.log.info("truyen tham so  khoa " + this.kho_maso, new Object[0]);
       if (this.kho_maso != null) {
         params.put("KHOMASO", this.kho_maso);
       }
       this.log.info("truyen tham so  nguon chuong trinh " + this.ct_maso, new Object[0]);
       if (this.ct_maso != null) {
         params.put("CTMASO", this.ct_maso);
       }
       this.log.info("truyen tham so  nguon kinh phi " + this.kinhphi_maso, new Object[0]);
       if (this.kinhphi_maso != null) {
         params.put("KPMASO", this.kinhphi_maso);
       }
       this.log.info("truyen tham so  noi ban " + this.noiban_maso, new Object[0]);
       if (this.noiban_maso != null)
       {
         params.put("NOIBANMASO", this.noiban_maso);
         DmNhaCungCap nccap = new DmNhaCungCap();
         Object obj = dtutilDelegate.findByMa(this.noiban_ma, "DmNhaCungCap", "dmnhacungcapMa");
         nccap = (DmNhaCungCap)obj;
         params.put("NOIBAN", nccap.getDmnhacungcapTen());
       }
       else
       {
         params.put("NOIBAN", "ALL");
       }
       if (this.pttheo.equals("r1"))
       {
         this.log.info("Theo nha cung cap", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D18_phantichkinhphinhap_nhasx.jrxml";
       }
       else if (this.pttheo.equals("r2"))
       {
         this.log.info("Theo nguon chuong trinh", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D18_phantichkinhphinhap_nct.jrxml";
       }
       else if (this.pttheo.equals("r3"))
       {
         this.log.info("Theo nguon kinh phi", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D18_phantichkinhphinhap_nkp.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Tinhhinhkinhphinhap");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
       this.index += 1;
       this.log.info("Index :" + getIndex(), new Object[0]);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getPttheo()
   {
     return this.pttheo;
   }

   public void setPttheo(String pttheo)
   {
     this.pttheo = pttheo;
   }

   public Integer getLthuoc_maso()
   {
     return this.lthuoc_maso;
   }

   public void setLthuoc_maso(Integer lthuoc_maso)
   {
     this.lthuoc_maso = lthuoc_maso;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public Integer getKho_maso()
   {
     return this.kho_maso;
   }

   public void setKho_maso(Integer kho_maso)
   {
     this.kho_maso = kho_maso;
   }

   public Integer getCt_maso()
   {
     return this.ct_maso;
   }

   public void setCt_maso(Integer ct_maso)
   {
     this.ct_maso = ct_maso;
   }

   public Integer getKinhphi_maso()
   {
     return this.kinhphi_maso;
   }

   public void setKinhphi_maso(Integer kinhphi_maso)
   {
     this.kinhphi_maso = kinhphi_maso;
   }

   public Integer getNoiban_maso()
   {
     return this.noiban_maso;
   }

   public void setNoiban_maso(Integer noiban_maso)
   {
     this.noiban_maso = noiban_maso;
   }

   public String getLthuoc_ma()
   {
     return this.lthuoc_ma;
   }

   public void setLthuoc_ma(String lthuoc_ma)
   {
     this.lthuoc_ma = lthuoc_ma;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getKho_ma()
   {
     return this.kho_ma;
   }

   public void setKho_ma(String kho_ma)
   {
     this.kho_ma = kho_ma;
   }

   public String getCt_ma()
   {
     return this.ct_ma;
   }

   public void setCt_ma(String ct_ma)
   {
     this.ct_ma = ct_ma;
   }

   public String getKinhphi_ma()
   {
     return this.kinhphi_ma;
   }

   public void setKinhphi_ma(String kinhphi_ma)
   {
     this.kinhphi_ma = kinhphi_ma;
   }

   public String getNoiban_ma()
   {
     return this.noiban_ma;
   }

   public void setNoiban_ma(String noiban_ma)
   {
     this.noiban_ma = noiban_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Phantichkinhphinhap

 * JD-Core Version:    0.7.0.1

 */