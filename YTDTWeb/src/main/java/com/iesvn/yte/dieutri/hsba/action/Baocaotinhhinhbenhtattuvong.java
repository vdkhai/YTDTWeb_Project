 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("BaoCaoTinhHinhBenhTatTuVong")
 @Scope(ScopeType.SESSION)
 public class Baocaotinhhinhbenhtattuvong
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
   private List<DmDoiTuong> listDoituong = new ArrayList();
   @DataModelSelection("listDoituong")
   @Out(required=false)
   private DmDoiTuong dmdoituongSelect;
   @DataModel
   private List<DmKhoa> listKhoa = new ArrayList();
   @DataModelSelection("listKhoa")
   @Out(required=false)
   private DmKhoa dmkhoaSelect;
   private Integer tuoi_tu;
   private Integer tuoi_den;
   private String gioitinh = null;
   private String tungay = null;
   private String denngay = null;
   private String chon = null;
   private static int index = 0;
   private Integer khoa_maso = null;
   private Integer doituong_maso = null;
   private Integer tainan_maso = null;
   private String khoa_ma = null;
   private String doituong_ma = null;
   private String tainan_ma = null;
   private String gtSelect;
   private String kqdtMa = null;
   private String benhICDMa = null;
   private String dtMa = null;
   private String khoaMa = null;

   @Create
   public void init()
   {
     setGtSelect("namnu");
   }

   public String thuchienAction()
     throws ParseException
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void chonlaiAction()
   {
     setTungay("");
     setDenngay("");
   }

   public void enterDmBenhICD()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listDmBenhICD.size() == 0) && (this.benhICDMa != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(this.benhICDMa, "DmBenhIcd", "dmbenhicdMa");
       DmBenhIcd benhicd = (DmBenhIcd)obj;
       this.listDmBenhICD.add(benhicd);
       this.log.info("da add phan tu dau tien" + this.listDmBenhICD.size(), new Object[0]);
     }
     else if ((this.listDmBenhICD.size() > 0) && (this.benhICDMa != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DmBenhIcd item : this.listDmBenhICD) {
         if (item.getDmbenhicdMa().equals(this.benhICDMa)) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(this.benhICDMa, "DmBenhIcd", "dmbenhicdMa");
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

   public void enterDmDoiTuong()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listDoituong.size() == 0) && (this.dtMa != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(this.dtMa, "DmDoiTuong", "dmdoituongMa");
       DmDoiTuong doituong = (DmDoiTuong)obj;
       this.listDoituong.add(doituong);
       this.log.info("da add phan tu dau tien" + this.listDoituong.size(), new Object[0]);
     }
     else if ((this.listDoituong.size() > 0) && (this.dtMa != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DmDoiTuong item : this.listDoituong) {
         if (item.getDmdoituongMa().equals(this.dtMa)) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(this.dtMa, "DmDoiTuong", "dmdoituongMa");
         DmDoiTuong doituong = (DmDoiTuong)obj;
         this.listDoituong.add(doituong);
       }
       this.log.info("da add phan tu" + this.listDoituong.size(), new Object[0]);
     }
   }

   public void deleteDmDoiTuong()
   {
     this.log.info("bat dau xoa , size" + this.listDoituong.size(), new Object[0]);
     this.listDoituong.remove(this.dmdoituongSelect);
     this.log.info("da xoa , size" + this.listDoituong.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void enterDmKhoa()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listKhoa.size() == 0) && (this.khoaMa != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(this.khoaMa, "DmKhoa", "dmkhoaMa");
       DmKhoa khoa = (DmKhoa)obj;
       this.listKhoa.add(khoa);
       this.log.info("da add phan tu dau tien" + this.listKhoa.size(), new Object[0]);
     }
     else if ((this.listKhoa.size() > 0) && (this.khoaMa != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DmKhoa item : this.listKhoa) {
         if (item.getDmkhoaMa().equals(this.khoaMa)) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(this.khoaMa, "DmKhoa", "dmkhoaMa");
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

   public void XuatReport()
     throws ParseException
   {
     this.loaiBCDT = "BCTHTuVong";
     this.jasperPrintDT = null;
     this.duongdanStrDT = null;
     this.log.info("chon la :" + getChon(), new Object[0]);
     this.log.info("Vao Method XuatReport bao cao benh tat tu vong", new Object[0]);
     String resultReportName = null;
     String baocaotuvong = null;
     String pathTemplate = null;
     String tenfilexuat = null;

     Map<String, Object> params = new HashMap();
     if (!this.tungay.equals(""))
     {
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TUNGAY", this.tungay);
       params.put("TUNGAY_DATE", sdf.parse(this.tungay));
       if (!this.denngay.equals(""))
       {
         params.put("DENNGAY", this.denngay);
         params.put("DENNGAY_DATE", sdf.parse(this.denngay));
       }
     }
     else
     {
       params.put("TUNGAY_DATE", null);
       params.put("DENNGAY_DATE", null);
     }
     this.log.info("banh vien xuat bao cao " + IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI, new Object[0]);
     params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
     params.put("DIACHI", IConstantsRes.REPORT_DIEUTRI_DIA_CHI);
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (this.chon != null) {
       if (this.chon.equals(new String("5")))
       {
         this.log.info("tre em duoi 7 ngay", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B19_DSBenhnhi7ngaytuoituvong.jrxml";
         tenfilexuat = "Baocaotinhhinhbenhtattuvong7ngay";
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       }
       else if (this.chon.equals(new String("6")))
       {
         this.log.info("tre em duoi duoi 28 ngay", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B19_DSBenhnhi28ngaytuoituvong.jrxml";
         tenfilexuat = "Baocaotinhhinhbenhtattuvong28ngay";
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       }
       else
       {
         if (this.chon.equals(new String("0")))
         {
           this.log.info("--------Theo ket qua theo benh chinh dc chon--------", new Object[0]);
           if (this.listDmBenhICD.size() > 0)
           {
             List<String> listTemp = new ArrayList();
             for (DmBenhIcd item : this.listDmBenhICD) {
               listTemp.add(item.getDmbenhicdMa());
             }
             String benhicd = getListDVMaParamsHelp(listTemp);
             this.log.info("danh sach benh icd" + benhicd, new Object[0]);
             params.put("LISTBENHICD", benhicd);
           }
         }
         if (this.chon.equals(new String("2")))
         {
           this.log.info("--------Theo ket qua theo doi tuong dc chon--------", new Object[0]);
           if (this.listDoituong.size() > 0)
           {
             List<String> listTemp = new ArrayList();
             for (DmDoiTuong item : this.listDoituong) {
               listTemp.add(item.getDmdoituongMa());
             }
             String doituongstr = getListDVMaParamsHelp(listTemp);
             this.log.info("danh sach doi tuong" + doituongstr, new Object[0]);
             params.put("LISTDOITUONG", doituongstr);
           }
         }
         if (this.chon.equals(new String("4")))
         {
           this.log.info("--------Theo ket qua theo khoa dc chon--------", new Object[0]);
           if (this.listKhoa.size() > 0)
           {
             List<String> listTemp = new ArrayList();
             for (DmKhoa item : this.listKhoa) {
               listTemp.add(item.getDmkhoaMa());
             }
             String khoastr = getListDVMaParamsHelp(listTemp);
             this.log.info("danh sach khoa" + khoastr, new Object[0]);
             params.put("LISTKHOA", khoastr);
           }
         }
         this.log.info("khac", new Object[0]);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B18_DSBenhnhatuvong.jrxml";
         tenfilexuat = "Baocaotinhhinhbenhtattuvong";
         if (this.khoa_maso != null)
         {
           this.log.info("khoa ma " + this.khoa_maso, new Object[0]);
           params.put("KHOAMASO", new Integer(this.khoa_maso.intValue()));
           DmKhoa dmkhoa = new DmKhoa();
           Object obj = dtutilDelegate.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
           dmkhoa = (DmKhoa)obj;
           this.log.info("Dm khoa tim thay" + dmkhoa, new Object[0]);
           params.put("KHOA", dmkhoa.getDmkhoaTen());
         }
         else
         {
           params.put("KHOA", "﻿Toàn viện");
         }
         if (this.doituong_maso != null)
         {
           this.log.info("doi tuong " + this.doituong_maso, new Object[0]);
           params.put("DOITUONGMASO", this.doituong_maso);
         }
         if (this.tainan_maso != null)
         {
           this.log.info("tai nan " + this.tainan_maso, new Object[0]);
           params.put("TAINANMASO", this.tainan_maso);
           DmTaiNan dmtainan = new DmTaiNan();
           Object obj = dtutilDelegate.findByMa(this.tainan_ma, "DmTaiNan", "dmtainanMa");
           dmtainan = (DmTaiNan)obj;
           this.log.info("Dm tai nam tim thay" + dmtainan, new Object[0]);
           params.put("TAINAN", dmtainan.getDmtainanTen());
         }
         if (this.gtSelect != null) {
           if (this.gtSelect.equals("nam"))
           {
             this.log.info("1", new Object[0]);
             params.put("GTMASO", new Integer(2));
             params.put("GIOITINH", "Nam");
           }
           else if (this.gtSelect.equals("nu"))
           {
             this.log.info("2", new Object[0]);
             params.put("GTMASO", new Integer(1));
             params.put("GIOITINH", "﻿Nữ");
           }
         }
         if (this.tuoi_tu != null)
         {
           params.put("TUOITU", this.tuoi_tu);
           params.put("TUOIDEN", this.tuoi_den);
         }
         else
         {
           params.put("TUOITU", null);
           params.put("TUOIDEN", null);
         }
       }
     }
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);

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
       baocaotuvong = XuatReportUtil.ReportUtil(this.jasperPrintDT, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", tenfilexuat);
       this.duongdanStrDT = baocaotuvong.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocaotuvong, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.duongdanStrDT, new Object[0]);
       index += 1;
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
     this.log.info("Output: " + resultReportName, new Object[0]);
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
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

   public void setChon(String chon)
   {
     this.chon = chon;
   }

   public String getChon()
   {
     return this.chon;
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

   public void setGioitinh(String gioitinh)
   {
     this.gioitinh = gioitinh;
   }

   public String getGioitinh()
   {
     return this.gioitinh;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getDoituong_maso()
   {
     return this.doituong_maso;
   }

   public void setDoituong_maso(Integer doituong_maso)
   {
     this.doituong_maso = doituong_maso;
   }

   public Integer getTainan_maso()
   {
     return this.tainan_maso;
   }

   public void setTainan_maso(Integer tainan_maso)
   {
     this.tainan_maso = tainan_maso;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getDoituong_ma()
   {
     return this.doituong_ma;
   }

   public void setDoituong_ma(String doituong_ma)
   {
     this.doituong_ma = doituong_ma;
   }

   public String getTainan_ma()
   {
     return this.tainan_ma;
   }

   public void setTainan_ma(String tainan_ma)
   {
     this.tainan_ma = tainan_ma;
   }

   public void setGtSelect(String gtSelect)
   {
     this.gtSelect = gtSelect;
   }

   public String getGtSelect()
   {
     return this.gtSelect;
   }

   public void setKqdtMa(String kqdtMa)
   {
     this.kqdtMa = kqdtMa;
   }

   public String getKqdtMa()
   {
     return this.kqdtMa;
   }

   public void setBenhICDMa(String benhICDMa)
   {
     this.benhICDMa = benhICDMa;
   }

   public String getBenhICDMa()
   {
     return this.benhICDMa;
   }

   public void setDtMa(String dtMa)
   {
     this.dtMa = dtMa;
   }

   public String getDtMa()
   {
     return this.dtMa;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.Baocaotinhhinhbenhtattuvong

 * JD-Core Version:    0.7.0.1

 */