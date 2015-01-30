 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("B141_Phantichbenhnhankhambenh")
 @Scope(ScopeType.CONVERSATION)
 public class PhanTichBenhNhanKhamBenh
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB141;
   @Out(required=false)
   @In(required=false)
   private String loaikhambenh;
   @DataModel
   private List<DtDmKhoiBhyt> listkhoiBHYTB141 = new ArrayList();
   @DataModelSelection("listkhoiBHYTB141")
   @Out(required=false)
   private DtDmKhoiBhyt dmkhoiBHYTB3332Selected;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private Integer maSoKhoiBHYT = null;
   private String maKhoiBHYT = "";
   private Integer tuyen;
   private String chonloaibc;
   private String maDoiTuong = null;
   private String bankhamMa = null;
   private Integer bankhamMaSo;

   public String getBankhamMa()
   {
     return this.bankhamMa;
   }

   public void setBankhamMa(String bankhamMa)
   {
     this.bankhamMa = bankhamMa;
   }

   public Integer getBankhamMaSo()
   {
     return this.bankhamMaSo;
   }

   public void setBankhamMaSo(Integer bankhamMaSo)
   {
     this.bankhamMaSo = bankhamMaSo;
   }

   public String getMaDoiTuong()
   {
     return this.maDoiTuong;
   }

   public void setMaDoiTuong(String maDoiTuong)
   {
     this.maDoiTuong = maDoiTuong;
   }

   @Factory("resetFormB141")
   public void initTmp()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init(String loai)
   {
     this.log.info("init()", new Object[0]);
     this.loaikhambenh = loai;
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh";
   }

   @End
   public void end() {}

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);

     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);

     this.maDoiTuong = "";
     this.bankhamMa = "";
     this.maKhoiBHYT = "";
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     this.chonloaibc = "theodoituong";
     this.listkhoiBHYTB141.clear();
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     if ((inputs == null) || (inputs.size() == 0)) {
       return null;
     }
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

     return "B160_Chonmenuxuattaptin";
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi: " + this.maKhoiBHYT, new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maKhoiBHYT.equals("")) {
       if ((this.listkhoiBHYTB141.size() == 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
         DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
         this.listkhoiBHYTB141.add(khoiBHYT);
         this.log.info("da add phan tu dau tien" + this.listkhoiBHYTB141.size(), new Object[0]);
       }
       else if ((this.listkhoiBHYTB141.size() > 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DtDmKhoiBhyt item : this.listkhoiBHYTB141) {
           if (item.getDtdmkhoibhytMa().equals(this.maKhoiBHYT)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
           DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
           this.listkhoiBHYTB141.add(khoiBHYT);
         }
         this.log.info("da add phan tu: " + this.listkhoiBHYTB141.size(), new Object[0]);
       }
     }
     setMaKhoiBHYT("");
   }

   public void deletedmkhoiBHYT()
   {
     this.log.info("bat dau xoa , size" + this.listkhoiBHYTB141.size(), new Object[0]);
     this.listkhoiBHYTB141.remove(this.dmkhoiBHYTB3332Selected);
     this.log.info("da xoa , size" + this.listkhoiBHYTB141.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeTD = "B141_Phantichbenhnhankhambenh";
     this.log.info("Vao Method XuatReport B141_Phantichbenhnhankhambenh", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.chonloaibc.equals("theodoituong")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T04_Phantichbenhnhankhambenhtheodoituong.jrxml";
       } else if (this.chonloaibc.equals("theodoituongBHYT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T05_PhantichbenhnhankhambenhtheoDTBHYT.jrxml";
       } else if (this.chonloaibc.equals("theonoidangkyKCBBD")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T06_PhantichbenhnhankhambenhtheoNoiDKKCBBD.jrxml";
       } else if (this.chonloaibc.equals("theotuyen")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T07_PhantichbenhnhankhambenhtheoTuyen.jrxml";
       } else if (this.chonloaibc.equals("theobankham")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T08_Phantichbenhnhankhambenhtheobankham.jrxml";
       }
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       Calendar cal = Calendar.getInstance();

       Date dateTemp = sdf.parse(this.tungay);
       this.log.info("*****dateTemp " + dateTemp, new Object[0]);
       cal.setTime(dateTemp);
       String sdate = cal.get(1) + "/" + (cal.get(2) + 1) + "/" + cal.get(5);

       params.put("STUNGAY", sdate);
       this.log.info("*****STUNGAY " + sdate, new Object[0]);
       params.put("tungay", dateTemp);
       this.log.info("*****tungay " + this.tungay, new Object[0]);

       dateTemp = sdf.parse(this.denngay);
       this.log.info("*****dateTemp " + dateTemp, new Object[0]);
       cal.setTime(dateTemp);
       sdate = cal.get(1) + "/" + (cal.get(2) + 1) + "/" + cal.get(5);

       params.put("SDENNGAY", sdate);
       this.log.info("*****SDENNGAY " + sdate, new Object[0]);
       params.put("denngay", dateTemp);
       this.log.info("******denngay " + this.denngay, new Object[0]);


       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (this.listkhoiBHYTB141.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (DtDmKhoiBhyt item : this.listkhoiBHYTB141) {
           listtemp.add(item.getDtdmkhoibhytMa());
         }
         params.put("khoi", getListDVMaParamsHelp(listtemp));
         this.log.info("list phan loai " + getListDVMaParamsHelp(listtemp), new Object[0]);

         params.put("tenkhoi", getListDVMaParamsHelp(listtemp));
       }
       if ((this.maDoiTuong != null) && (!this.maDoiTuong.equals("")))
       {
         params.put("MaDoiTuong", this.maDoiTuong);
         this.log.info("***** MaDoiTuong: " + this.maDoiTuong, new Object[0]);
       }
       params.put("loaikhambenh", this.loaikhambenh);
       this.log.info("***** loaikhambenh: " + this.loaikhambenh, new Object[0]);
       if ((this.bankhamMa != null) && (!this.bankhamMa.equals("")))
       {
         params.put("bankhamma", this.bankhamMa);
         this.log.info("***** bankhamma: " + this.bankhamMa, new Object[0]);
       }
       else
       {
         params.put("bankhamma", null);
         this.log.info("*****bankhamma: " + this.bankhamMa, new Object[0]);
       }
       if ((this.bankhamMaSo != null) && (!this.bankhamMaSo.equals("")))
       {
         params.put("bankhammaso", this.bankhamMaSo);
         this.log.info("***** bankhamMaSo: " + this.bankhamMaSo, new Object[0]);
       }
       else
       {
         params.put("bankhammaso", null);
         this.log.info("***** bankhamMaSo: " + this.bankhamMaSo, new Object[0]);
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
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "B141_Phantichbenhnhankhambenh");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathTD, new Object[0]);
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

   public Integer getMaSoKhoiBHYT()
   {
     return this.maSoKhoiBHYT;
   }

   public void setMaSoKhoiBHYT(Integer maSoKhoiBHYT)
   {
     this.maSoKhoiBHYT = maSoKhoiBHYT;
   }

   public String getMaKhoiBHYT()
   {
     return this.maKhoiBHYT;
   }

   public void setMaKhoiBHYT(String maKhoiBHYT)
   {
     this.maKhoiBHYT = maKhoiBHYT;
   }

   public Integer getTuyen()
   {
     return this.tuyen;
   }

   public void setTuyen(Integer tuyen)
   {
     this.tuyen = tuyen;
   }

   public String getChonloaibc()
   {
     return this.chonloaibc;
   }

   public void setChonloaibc(String chonloaibc)
   {
     this.chonloaibc = chonloaibc;
   }

   public String getLoaikhambenh()
   {
     return this.loaikhambenh;
   }

   public void setLoaikhambenh(String loaikhambenh)
   {
     this.loaikhambenh = loaikhambenh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.PhanTichBenhNhanKhamBenh

 * JD-Core Version:    0.7.0.1

 */