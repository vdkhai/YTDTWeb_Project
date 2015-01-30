 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("B246_Luotbenhnhanngaydieutri")
 @Scope(ScopeType.SESSION)
 public class Solieuluotbenhnhanngaydieutri
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintDT = null;
   @DataModel
   private List<DmBenhIcd> listDmBenhICD = new ArrayList();
   @DataModelSelection("listDmBenhICD")
   @Out(required=false)
   private DmBenhIcd dmbenhICDSelect;
   @DataModel
   private List<DmKhoa> listKhoa = new ArrayList();
   @DataModelSelection("listKhoa")
   @Out(required=false)
   private DmKhoa dmkhoaSelect;
   private String tungay = null;
   private String denngay = null;
   private Integer tuoi_tu;
   private Integer tuoi_den;
   private String doituong_ma = null;
   private String ketqua_ma = null;
   private String khoa_ma = null;
   private String tainan_ma = null;
   private Integer doituong_maso = null;
   private Integer ketqua_maso = null;
   private Integer khoa_maso = null;
   private Integer tainan_maso = null;
   private String locdulieu_ma = null;
   private String chon = null;
   private String gtselect = null;
   private int index = 0;
   private String benhICDMa = null;
   private String khoaMa = null;

   public void enterDmBenhICD()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listDmBenhICD.size() == 0) && (getBenhICDMa() != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(getBenhICDMa(), "DmBenhIcd", "dmbenhicdMa");
       DmBenhIcd benhicd = (DmBenhIcd)obj;
       this.listDmBenhICD.add(benhicd);
       this.log.info("da add phan tu dau tien" + this.listDmBenhICD.size(), new Object[0]);
     }
     else if ((this.listDmBenhICD.size() > 0) && (getBenhICDMa() != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DmBenhIcd item : this.listDmBenhICD) {
         if (item.getDmbenhicdMa().equals(getBenhICDMa())) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(getBenhICDMa(), "DmBenhIcd", "dmbenhicdMa");
         DmBenhIcd benhicd = (DmBenhIcd)obj;
         this.listDmBenhICD.add(benhicd);
       }
       this.log.info("da add phan tu" + this.listDmBenhICD.size(), new Object[0]);
     }
   }

   public void deleteDmBenhICD()
   {
     this.log.info("bat dau xoa , size" + this.listDmBenhICD.size(), new Object[0]);
     this.listDmBenhICD.remove(this.dmbenhICDSelect);
     this.log.info("da xoa , size" + this.listDmBenhICD.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void enterDmKhoa()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listKhoa.size() == 0) && (getKhoaMa() != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(getKhoaMa(), "DmKhoa", "dmkhoaMa");
       DmKhoa khoa = (DmKhoa)obj;
       this.listKhoa.add(khoa);
       this.log.info("da add phan tu dau tien" + this.listKhoa.size(), new Object[0]);
     }
     else if ((this.listKhoa.size() > 0) && (getKhoaMa() != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DmKhoa item : this.listKhoa) {
         if (item.getDmkhoaMa().equals(getKhoaMa())) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(getKhoaMa(), "DmKhoa", "dmkhoaMa");
         DmKhoa khoa = (DmKhoa)obj;
         this.listKhoa.add(khoa);
       }
       this.log.info("da add phan tu" + this.listKhoa.size(), new Object[0]);
     }
   }

   public void deleteDmKhoa()
   {
     this.log.info("bat dau xoa , size" + this.listKhoa.size(), new Object[0]);
     this.listKhoa.remove(this.dmkhoaSelect);
     this.log.info("da xoa , size" + this.listKhoa.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
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
     return "B260_Chonmenuxuattaptin";
   }

   public void chonlaiAction()
   {
     setTungay("");
     setDenngay("");
   }

   public void XuatReport()
   {
     this.loaiBCDT = "BCSolieuluotBNngayDT";
     this.jasperPrintDT = null;
     this.duongdanStrDT = null;
     this.log.info("Vao Method XuatReport bao cao so lieu benh nhan luot dieu tri", new Object[0]);
     String resultReportName = null;
     String baocaotuvong = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B22_Luotdieutritheoicd10.jrxml";
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);

       Map<String, Object> params = new HashMap();
       this.log.info("=================", new Object[0]);
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocaotuvong = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Solieuluotbenhnhanngaydieutri");
       this.duongdanStrDT = baocaotuvong.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocaotuvong, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.duongdanStrDT, new Object[0]);
       this.index += 1;
       this.log.info("Index :" + this.index, new Object[0]);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Output: " + resultReportName, new Object[0]);
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getDoituong_ma()
   {
     return this.doituong_ma;
   }

   public void setDoituong_ma(String doituong_ma)
   {
     this.doituong_ma = doituong_ma;
   }

   public String getKetqua_ma()
   {
     return this.ketqua_ma;
   }

   public void setKetqua_ma(String ketqua_ma)
   {
     this.ketqua_ma = ketqua_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getTainan_ma()
   {
     return this.tainan_ma;
   }

   public void setTainan_ma(String tainan_ma)
   {
     this.tainan_ma = tainan_ma;
   }

   public String getLocdulieu_ma()
   {
     return this.locdulieu_ma;
   }

   public void setLocdulieu_ma(String locdulieu_ma)
   {
     this.locdulieu_ma = locdulieu_ma;
   }

   public Integer getTuoi_tu()
   {
     return this.tuoi_tu;
   }

   public void setTuoi_tu(Integer tuoi_tu)
   {
     this.tuoi_tu = tuoi_tu;
   }

   public Integer getTuoi_den()
   {
     return this.tuoi_den;
   }

   public void setTuoi_den(Integer tuoi_den)
   {
     this.tuoi_den = tuoi_den;
   }

   public Integer getDoituong_maso()
   {
     return this.doituong_maso;
   }

   public void setDoituong_maso(Integer doituong_maso)
   {
     this.doituong_maso = doituong_maso;
   }

   public Integer getKetqua_maso()
   {
     return this.ketqua_maso;
   }

   public void setKetqua_maso(Integer ketqua_maso)
   {
     this.ketqua_maso = ketqua_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getTainan_maso()
   {
     return this.tainan_maso;
   }

   public void setTainan_maso(Integer tainan_maso)
   {
     this.tainan_maso = tainan_maso;
   }

   public void setGtselect(String gtselect)
   {
     this.gtselect = gtselect;
   }

   public String getGtselect()
   {
     return this.gtselect;
   }

   public void setChon(String chon)
   {
     this.chon = chon;
   }

   public String getChon()
   {
     return this.chon;
   }

   public void setBenhICDMa(String benhICDMa)
   {
     this.benhICDMa = benhICDMa;
   }

   public String getBenhICDMa()
   {
     return this.benhICDMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.Solieuluotbenhnhanngaydieutri

 * JD-Core Version:    0.7.0.1

 */