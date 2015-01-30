 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
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
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B123_Inphieulinhthuoc")
 @Synchronized(timeout=6000000L)
 public class InPhieuLinhThuoc
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @Logger
   private Log log;
   private String maBanKham;
   private Integer maSoBanKham;
   int index = 0;
   private String tugio = null;
   private String dengio = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayLapPhieu = null;
   private String sotiepdonStr = "";
   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "TiepDon_KhamBenh_InPhieuLinhThuoc";
   }

   private String ngayhientai = "";

   @End
   public void end() {}

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.ngayLapPhieu = sdf.format(currentDate);

     this.ngayhientai = sdf.format(currentDate);
     this.maBanKham = "";

     Calendar cal = Calendar.getInstance();
     cal.set(11, 0);
     cal.set(12, 0);

     sdf = new SimpleDateFormat("HH:mm");
     this.tugio = sdf.format(cal.getTime());
     this.dengio = sdf.format(currentDate);
     this.hotenBNStr = "";
     this.sotiepdonStr = "";
     this.listBN.clear();
   }

   @DataModel
   private List<TiepDon> listBN = new ArrayList();
   @DataModelSelection("listBN")
   @Out(required=false)
   private TiepDon tiepDonSelect;
   private TiepDon tiepDon = null;
   private String hotenBNStr = "";

   public void loadTiepdon()
   {
     this.log.info("-----So vao vien-----" + this.sotiepdonStr, new Object[0]);
     this.tiepDon = new TiepDon();
     if (!this.sotiepdonStr.equals("")) {
       try
       {
         TiepDonDelegate tiepdonDAO = TiepDonDelegate.getInstance();
         this.tiepDon = tiepdonDAO.find(this.sotiepdonStr);
         if (this.tiepDon == null)
         {
           FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[] { this.sotiepdonStr });
           return;
         }
         this.hotenBNStr = this.tiepDon.getBenhnhanMa().getBenhnhanHoten();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[] { this.sotiepdonStr });
         e.printStackTrace();
       }
     }
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);
     boolean test = false;
     this.log.info("tiepDon:" + this.tiepDon, new Object[0]);
     if (this.tiepDon != null)
     {
       this.log.info("tiepDon2:" + this.tiepDon, new Object[0]);
       if (this.listBN.size() == 0)
       {
         this.log.info("size list bang 0", new Object[0]);
         this.listBN.add(this.tiepDon);
         this.log.info("da add phan tu dau tien" + this.listBN.size(), new Object[0]);
       }
       else if (this.listBN.size() > 0)
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (TiepDon item : this.listBN) {
           if (item.getTiepdonMa().equals(this.sotiepdonStr)) {
             test = true;
           }
         }
         if (!test) {
           this.listBN.add(this.tiepDon);
         }
         this.log.info("da add phan tu" + this.listBN.size(), new Object[0]);
       }
     }
     this.sotiepdonStr = "";
     setHotenBNStr("");
     this.tiepDon = null;
     this.log.info("ketthuc them du lieu vao luoi", new Object[0]);
   }

   public String thuchienAction()
   {
     XuatReport();

     return "B160_Chonmenuxuattaptin";
   }

   public void deleteBN()
   {
     this.log.info("bat dau xoa , size" + this.listBN.size(), new Object[0]);
     this.listBN.remove(this.tiepDonSelect);
     this.log.info("da xoa , size" + this.listBN.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "', ";
       }
     }
     result = result.substring(0, result.length() - 2);
     this.log.info("Thoat method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Output: " + result, new Object[0]);
     return result;
   }

   public void XuatReport()
   {
     this.reportTypeTD = "B123_Inphieulinhthuoc";
     this.log.info("Vao Method XuatReport B123_Inphieulinhthuoc", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T15_Inphieulinhthuoc.jrxml";



       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();


       DtDmBanKham dtDmBanKham = new DtDmBanKham();
       DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
       Object obj = dtUtilDAO.findByMa(this.maBanKham, "DtDmBanKham", "dtdmbankhamMa");
       dtDmBanKham = (DtDmBanKham)obj;

       params.put("BANKHAMMASO", this.maSoBanKham);
       params.put("BANKHAMTEN", dtDmBanKham.getDtdmbankhamTen());


       params.put("NGAYLAPPHIEU", sdf.parse(this.ngayLapPhieu));


       Date dTuNgay = null;
       Date dDenNgay = null;
       if ((this.tungay != null) && (!this.tungay.equals("")))
       {
         if ((this.tugio == null) || (this.tugio.equals(""))) {
           this.tugio = "00:00";
         }
         dTuNgay = Utils.getDBDate(this.tugio, this.tungay).getTime();
       }
       if ((this.denngay != null) && (!this.denngay.equals("")))
       {
         if ((this.dengio == null) || (this.dengio.equals(""))) {
           this.dengio = "23:59";
         }
         dDenNgay = Utils.getDBDate(this.dengio, this.denngay).getTime();
       }
       params.put("NGAYDUNGTU", dTuNgay);
       params.put("NGAYDUNGDEN", dDenNgay);

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       if (this.listBN.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (TiepDon item : this.listBN) {
           listtemp.add(item.getTiepdonMa());
         }
         params.put("MATIEPDON", getListDVMaParamsHelp(listtemp));
         this.log.info("list phan loai " + getListDVMaParamsHelp(listtemp), new Object[0]);
       }
       else
       {
         params.put("MATIEPDON", null);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "B123_Inphieulinhthuoc");
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

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
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

   public String getNgayLapPhieu()
   {
     return this.ngayLapPhieu;
   }

   public void setNgayLapPhieu(String ngayLapPhieu)
   {
     this.ngayLapPhieu = ngayLapPhieu;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public String getTugio()
   {
     return this.tugio;
   }

   public void setTugio(String tugio)
   {
     this.tugio = tugio;
   }

   public String getDengio()
   {
     return this.dengio;
   }

   public void setDengio(String dengio)
   {
     this.dengio = dengio;
   }

   public String getReportPathTD()
   {
     return this.reportPathTD;
   }

   public void setReportPathTD(String reportPathTD)
   {
     this.reportPathTD = reportPathTD;
   }

   public String getReportTypeTD()
   {
     return this.reportTypeTD;
   }

   public void setReportTypeTD(String reportTypeTD)
   {
     this.reportTypeTD = reportTypeTD;
   }

   public JasperPrint getJasperPrintTD()
   {
     return this.jasperPrintTD;
   }

   public void setJasperPrintTD(JasperPrint jasperPrintTD)
   {
     this.jasperPrintTD = jasperPrintTD;
   }

   public String getSotiepdonStr()
   {
     return this.sotiepdonStr;
   }

   public void setSotiepdonStr(String sotiepdonStr)
   {
     this.sotiepdonStr = sotiepdonStr;
   }

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public String getHotenBNStr()
   {
     return this.hotenBNStr;
   }

   public void setHotenBNStr(String hotenBNStr)
   {
     this.hotenBNStr = hotenBNStr;
   }

   public Integer getMaSoBanKham()
   {
     return this.maSoBanKham;
   }

   public void setMaSoBanKham(Integer maSoBanKham)
   {
     this.maSoBanKham = maSoBanKham;
   }

   public List<TiepDon> getListBN()
   {
     return this.listBN;
   }

   public void setListBN(List<TiepDon> listBN)
   {
     this.listBN = listBN;
   }

   public TiepDon getTiepDonSelect()
   {
     return this.tiepDonSelect;
   }

   public void setTiepDonSelect(TiepDon tiepDonSelect)
   {
     this.tiepDonSelect = tiepDonSelect;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.InPhieuLinhThuoc

 * JD-Core Version:    0.7.0.1

 */