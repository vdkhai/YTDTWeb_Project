 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
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
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("B3336_BaocaoBHYTtaiphongphatthuoc")
 @Scope(ScopeType.CONVERSATION)
 public class BaocaoBHYTtaiphongphatthuoc
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
   @Out(required=false)
   @In(required=false)
   private String resetFormB3336 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private String chonKhoi = "";
   private String chonKCB = "";
   private String maKhoiBHYT = "";
   private String maKCBBHYT = "";
   @DataModel
   private List<DtDmKhoiBhyt> listkhoiBHYT = new ArrayList();
   @DataModelSelection("listkhoiBHYT")
   @Out(required=false)
   private DtDmKhoiBhyt dmkhoiBHYTSelected;
   @DataModel
   private List<DtDmKcbBhyt> listKCBBHYT = new ArrayList();
   @DataModelSelection("listKCBBHYT")
   @Out(required=false)
   private DtDmKcbBhyt dmKCBBHYTSelected;

   @Factory("resetFormB3336")
   public void initresetFormB3336()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     initresetFormB3336();
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc";
   }

   @Create
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB3336 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB3336 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     setMaKCBBHYT("");
     setMaKhoiBHYT("");
     setChonKCB("k1");
     setChonKhoi("r1");
     this.listKCBBHYT.clear();
     this.listkhoiBHYT.clear();
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

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi" + this.maKhoiBHYT, new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maKhoiBHYT.equals("")) {
       if ((this.listkhoiBHYT.size() == 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
         DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
         this.listkhoiBHYT.add(khoiBHYT);
         this.log.info("da add phan tu dau tien" + this.listkhoiBHYT.size(), new Object[0]);
       }
       else if ((this.listkhoiBHYT.size() > 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DtDmKhoiBhyt item : this.listkhoiBHYT) {
           if (item.getDtdmkhoibhytMa().equals(this.maKhoiBHYT)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
           DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
           this.listkhoiBHYT.add(khoiBHYT);
         }
         this.log.info("da add phan tu" + this.listkhoiBHYT.size(), new Object[0]);
       }
     }
     setMaKhoiBHYT("");
   }

   public void deletedmkhoiBHYT()
   {
     this.log.info("bat dau xoa , size" + this.listkhoiBHYT.size(), new Object[0]);
     this.listkhoiBHYT.remove(this.dmkhoiBHYTSelected);
     this.log.info("da xoa , size" + this.listkhoiBHYT.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void enterKCB()
   {
     this.log.info("bat dau them du lieu vao luoi KCB" + this.maKCBBHYT, new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maKCBBHYT.equals("")) {
       if ((this.listKCBBHYT.size() == 0) && (this.maKCBBHYT != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
         DtDmKcbBhyt kcbBHYT = (DtDmKcbBhyt)obj;
         this.listKCBBHYT.add(kcbBHYT);
         this.log.info("da add phan tu dau tien" + this.listKCBBHYT.size(), new Object[0]);
       }
       else if ((this.listKCBBHYT.size() > 0) && (this.maKCBBHYT != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DtDmKcbBhyt item : this.listKCBBHYT) {
           if (item.getDtdmkcbbhytMa().equals(this.maKhoiBHYT)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
           DtDmKcbBhyt kcbBHYT = (DtDmKcbBhyt)obj;
           this.listKCBBHYT.add(kcbBHYT);
         }
         this.log.info("da add phan tu" + this.listKCBBHYT.size(), new Object[0]);
       }
     }
     setMaKCBBHYT("");
   }

   public void deletedmKCBBHYT()
   {
     this.log.info("bat dau xoa , size" + this.listKCBBHYT.size(), new Object[0]);
     this.listKCBBHYT.remove(this.dmKCBBHYTSelected);
     this.log.info("da xoa , size" + this.listKCBBHYT.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeVP = "BaocaoBHYTtaiphongphatthuoc";
     this.log.info("Vao Method XuatReport bao cao BHYT tai phong phat thuoc", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V24_Chiphitaikhocapphatthuocngoaitru.jrxml";
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TuNgay", sdf.parse(this.tungay));
       params.put("DenNgay", sdf.parse(this.denngay));
       if (this.chonKhoi.equals("r1")) {
         params.put("chon1", "IN");
       } else if (this.chonKhoi.equals("r1")) {
         params.put("chon1", "NOT IN");
       }
       if (this.chonKCB.equals("k1")) {
         params.put("chon2", "IN");
       } else if (this.chonKCB.equals("k1")) {
         params.put("chon2", "NOT IN");
       }
       if (this.listkhoiBHYT.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (DtDmKhoiBhyt item : this.listkhoiBHYT) {
           listtemp.add(item.getDtdmkhoibhytMa());
         }
         params.put("khoi", getListDVMaParamsHelp(listtemp));
         this.log.info("list khoi " + getListDVMaParamsHelp(listtemp), new Object[0]);
       }
       if (this.listKCBBHYT.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (DtDmKcbBhyt item : this.listKCBBHYT) {
           listtemp.add(item.getDtdmhuyenbhytMa());
         }
         params.put("noiKB", getListDVMaParamsHelp(listtemp));
         this.log.info("list KCB " + getListDVMaParamsHelp(listtemp), new Object[0]);
       }
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "BaocaoBHYTtaiphongphatthuoc");
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

   public String getChonKhoi()
   {
     return this.chonKhoi;
   }

   public void setChonKhoi(String chonKhoi)
   {
     this.chonKhoi = chonKhoi;
   }

   public String getChonKCB()
   {
     return this.chonKCB;
   }

   public void setChonKCB(String chonKCB)
   {
     this.chonKCB = chonKCB;
   }

   public String getMaKhoiBHYT()
   {
     return this.maKhoiBHYT;
   }

   public void setMaKhoiBHYT(String maKhoiBHYT)
   {
     this.maKhoiBHYT = maKhoiBHYT;
   }

   public String getMaKCBBHYT()
   {
     return this.maKCBBHYT;
   }

   public void setMaKCBBHYT(String maKCBBHYT)
   {
     this.maKCBBHYT = maKCBBHYT;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BaocaoBHYTtaiphongphatthuoc

 * JD-Core Version:    0.7.0.1

 */