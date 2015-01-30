 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("BHYT_ThongKeTheoMaBenh")
 @Scope(ScopeType.CONVERSATION)
 public class BHYT_ThongKeTheoMaBenh
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   @DataModel
   private List<DmBenhIcd> listBenhICDB3332 = new ArrayList();
   @DataModelSelection("listBenhICDB3332")
   @Out(required=false)
   private DmBenhIcd dmBenhICD3332Selected;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private Integer maSoBenhICD = null;
   private String maBenhICD = "";
   private Integer masoPLBHYT = null;
   private String maPLBHYT = "";
   @Out(required=false)
   @In(required=false)
   String loaiKCB = null;

   @Begin(join=true)
   public String init(String tempLoaiKCB)
   {
     this.ngayhientai = Utils.getCurrentDate();
     this.loaiKCB = tempLoaiKCB;
     resetForm();
     return "BaoCaoVienPhi_BHYT_ThongKeTheoMaBenh";
   }

   @End
   public void end() {}

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);


     this.listBenhICDB3332.clear();
     setMaPLBHYT("");
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "',";
       }
     }
     result = result.substring(0, result.length() - 1);
     this.log.info("Thoat method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Output: " + result, new Object[0]);
     return result;
   }

   public String thuchienAction()
   {
     XuatReport();

     return "B3360_Chonmenuxuattaptin";
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi" + this.maBenhICD, new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maBenhICD.equals("")) {
       if ((this.listBenhICDB3332.size() == 0) && (this.maBenhICD != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.maBenhICD, "DmBenhIcd", "dmbenhicdMa");
         DmBenhIcd benhICD = (DmBenhIcd)obj;
         this.listBenhICDB3332.add(benhICD);
         this.log.info("da add phan tu dau tien" + this.listBenhICDB3332.size(), new Object[0]);
       }
       else if ((this.listBenhICDB3332.size() > 0) && (this.maBenhICD != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DmBenhIcd item : this.listBenhICDB3332) {
           if (item.getDmbenhicdMa().equals(this.maBenhICD)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.maBenhICD, "DmBenhIcd", "dmbenhicdMa");
           DmBenhIcd benhICD = (DmBenhIcd)obj;
           this.listBenhICDB3332.add(benhICD);
         }
         this.log.info("da add phan tu" + this.listBenhICDB3332.size(), new Object[0]);
       }
     }
     setMaBenhICD("");
   }

   public void deletedmBenhICD()
   {
     this.log.info("bat dau xoa , size" + this.listBenhICDB3332.size(), new Object[0]);
     this.listBenhICDB3332.remove(this.dmBenhICD3332Selected);
     this.log.info("da xoa , size" + this.listBenhICDB3332.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeVP = "BaocaoBHYTnoitru";
     this.log.info("Vao Method XuatReport bao cao BHYT noi tru", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V22a_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan.jrxml";

       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TuNgay", sdf.parse(this.tungay));

       params.put("DenNgay", sdf.parse(this.denngay));
       if (this.listBenhICDB3332.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (DmBenhIcd item : this.listBenhICDB3332) {
           listtemp.add(item.getDmbenhicdMa());
         }
         params.put("khoi", getListDVMaParamsHelp(listtemp));
         this.log.info("list phan loai " + getListDVMaParamsHelp(listtemp), new Object[0]);
       }
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();

       DtDmPlBhyt plbhyt = new DtDmPlBhyt();
       Object obj = dtUtils.findByMa(this.maPLBHYT, "DtDmPlBhyt", " dtdmphloaibhytMa");
       plbhyt = (DtDmPlBhyt)obj;
       params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen());
       params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa());
       this.log.info(" ma phan loai " + this.maPLBHYT + "ten phan loai " + plbhyt.getDtdmphloaibhytTen(), new Object[0]);


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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "BaocaoBHYTnoitru");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
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

   public Integer getMaSoBenhICD()
   {
     return this.maSoBenhICD;
   }

   public void setMaSoBenhICD(Integer maSoBenhICD)
   {
     this.maSoBenhICD = maSoBenhICD;
   }

   public String getMaBenhICD()
   {
     return this.maBenhICD;
   }

   public void setMaBenhICD(String maBenhICD)
   {
     this.maBenhICD = maBenhICD;
   }

   public Integer getMasoPLBHYT()
   {
     return this.masoPLBHYT;
   }

   public void setMasoPLBHYT(Integer masoPLBHYT)
   {
     this.masoPLBHYT = masoPLBHYT;
   }

   public String getMaPLBHYT()
   {
     return this.maPLBHYT;
   }

   public void setMaPLBHYT(String maPLBHYT)
   {
     this.maPLBHYT = maPLBHYT;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public String getReportPathVP()
   {
     return this.reportPathVP;
   }

   public void setReportPathVP(String reportPathVP)
   {
     this.reportPathVP = reportPathVP;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public JasperPrint getJasperPrintVP()
   {
     return this.jasperPrintVP;
   }

   public void setJasperPrintVP(JasperPrint jasperPrintVP)
   {
     this.jasperPrintVP = jasperPrintVP;
   }

   public List<DmBenhIcd> getListBenhICDB3332()
   {
     return this.listBenhICDB3332;
   }

   public void setListBenhICDB3332(List<DmBenhIcd> listBenhICDB3332)
   {
     this.listBenhICDB3332 = listBenhICDB3332;
   }

   public DmBenhIcd getDmBenhICD3332Selected()
   {
     return this.dmBenhICD3332Selected;
   }

   public void setDmBenhICD3332Selected(DmBenhIcd dmBenhICD3332Selected)
   {
     this.dmBenhICD3332Selected = dmBenhICD3332Selected;
   }

   public String getLoaiKCB()
   {
     return this.loaiKCB;
   }

   public void setLoaiKCB(String loaiKCB)
   {
     this.loaiKCB = loaiKCB;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BHYT_ThongKeTheoMaBenh

 * JD-Core Version:    0.7.0.1

 */