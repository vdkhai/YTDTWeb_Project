 package com.iesvn.yte.dieutri.vienphi.action;

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
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3335_BaoCaoBHYTNgoaiTru")
 @Synchronized(timeout=6000000L)
 public class B3335_BaoCaoBHYTNgoaiTru
   implements Serializable
 {
   private static Logger log = Logger.getLogger(B3335_BaoCaoBHYTNgoaiTru.class);
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private Integer maSoPLBHYT = null;
   private String maPLBHYT = "";
   private Integer maSoKhoiBHYT = null;
   private String maKhoiBHYT = "";
   private String chonloaibo = "";
   private String chonloaibc = "";
   private String khoa_ma = "";
   private Integer tuyen;
   @DataModel
   private List<DtDmKhoiBhyt> listkhoiBHYT = new ArrayList();
   @DataModelSelection("listkhoiBHYT")
   @Out(required=false)
   private DtDmKhoiBhyt dmkhoiBHYTSelected;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private int index = 0;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");

     SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayhientai = format.format(new Date());

     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru";
   }

   @End
   public void endFunct() {}

   public void emtyData()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     setMaKhoiBHYT("");
     setMaPLBHYT("");
     setKhoa_ma("");
     setTuyen(null);
     setChonloaibo("r1");
     setChonloaibc("l1");
     this.listkhoiBHYT.clear();
   }

   public void resetValue()
   {
     emtyData();
   }

   public void enter()
   {
     log.info("bat dau them du lieu vao luoi" + this.maKhoiBHYT);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maKhoiBHYT.equals("")) {
       if ((this.listkhoiBHYT.size() == 0) && (this.maKhoiBHYT != null))
       {
         log.info("size list bang 0");
         Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
         DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
         this.listkhoiBHYT.add(khoiBHYT);
         log.info("da add phan tu dau tien" + this.listkhoiBHYT.size());
       }
       else if ((this.listkhoiBHYT.size() > 0) && (this.maKhoiBHYT != null))
       {
         log.info("size list lon hon 0");
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
         log.info("da add phan tu" + this.listkhoiBHYT.size());
       }
     }
     setMaKhoiBHYT("");
   }

   private String getListDVMaParamsHelp(List<Integer> inputs)
   {
     log.info("Vao method getListDVMaParamsHelp ");
     log.info("Inputs: " + inputs.toString());
     String result = "";
     for (Integer each : inputs) {
       if (each != null) {
         result = result + each.intValue() + ",";
       }
     }
     result = result.substring(0, result.length() - 1);
     log.info("Thoat method getListDVMaParamsHelp ");
     log.info("Output: " + result);
     return result;
   }

   public void deletedmkhoiBHYT()
   {
     log.info("bat dau xoa , size" + this.listkhoiBHYT.size());
     this.listkhoiBHYT.remove(this.dmkhoiBHYTSelected);
     log.info("da xoa , size" + this.listkhoiBHYT.size());
     log.info("ket thuc xoa");
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "BaocaoBHYTNgoaitru";
     log.info("Vao Method XuatReport bao cao BHYT ngoai tru");
     String baocao1 = null;
     String pathTemplate = null;

     Map<String, Object> params = new HashMap();
     try
     {
       if (this.chonloaibc.equals("l1"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP25a_DSDeNghiThanhToanChiPhiKCBNgoaiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l2"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP25b_ThongBaoThanhToanChiPhiKCBNgoaiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l3"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP25a_TongHopChiPhiKCBNgoaiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l4"))
       {
         params.put("TINH_BH", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("TUYEN_BV", IConstantsRes.MASO_TUYEN_BV_TRIEN_KHAI);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP14a_BaoCaoChiPhiKCBNgoaiTruCacNhomDTTheoTuyenCMKT.jrxml";
       }
       else if (this.chonloaibc.equals("l5"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP25d_ThongBaoThanhToanChiPhiKCBNgoaiTru.jrxml";
       }
       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       params.put("ten_cskcb", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       params.put("maso_cskcb", IConstantsRes.MASO_CO_SO_KCB);

       params.put("TuNgay", sdf.parse(this.tungay));
       params.put("DenNgay", sdf.parse(this.denngay));
       params.put("makhoa", this.khoa_ma);
       if (this.chonloaibo.equals("r1"))
       {
         params.put("chon", "IN");
         log.info("chon:IN");
       }
       else if (this.chonloaibo.equals("r2"))
       {
         params.put("chon", "NOT IN");
         log.info("chon:NOT IN");
       }
       if (this.listkhoiBHYT.size() > 0)
       {
         List<Integer> listtemp = new ArrayList();
         for (DtDmKhoiBhyt item : this.listkhoiBHYT) {
           listtemp.add(item.getDtdmkhoibhytMaso());
         }
         params.put("khoi", getListDVMaParamsHelp(listtemp));
         log.info("list phan loai " + getListDVMaParamsHelp(listtemp));
       }
       else
       {
         params.put("chon", "NOT IN");
         params.put("khoi", "0");
         log.info("khoi " + null);
       }
       if (!this.chonloaibc.equals("l4")) {
         params.put("ma_noi_tinh", IConstantsRes.TINH_BHYT_DEFAULT);
       }
       if ((this.chonloaibc.equals("l1")) || (this.chonloaibc.equals("l3")) || (this.chonloaibc.equals("l5")))
       {
         params.put("maso_cskcb_cung_tinh", IConstantsRes.MASO_CO_SO_KCB_CUNG_TINH);
       }
       else if (this.chonloaibc.equals("l4"))
       {
         params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "BaocaoBHYTNgoaitru");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
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

   public Integer getTuyen()
   {
     return this.tuyen;
   }

   public void setTuyen(Integer tuyen)
   {
     this.tuyen = tuyen;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public Integer getMaSoPLBHYT()
   {
     return this.maSoPLBHYT;
   }

   public void setMaSoPLBHYT(Integer maSoPLBHYT)
   {
     this.maSoPLBHYT = maSoPLBHYT;
   }

   public String getMaPLBHYT()
   {
     return this.maPLBHYT;
   }

   public void setMaPLBHYT(String maPLBHYT)
   {
     this.maPLBHYT = maPLBHYT;
   }

   public void ghinhan()
     throws Exception
   {}

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     emtyData();
     log.info("End ResetForm(): ");
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

   public void setChonloaibo(String chonloaibo)
   {
     this.chonloaibo = chonloaibo;
   }

   public String getChonloaibo()
   {
     return this.chonloaibo;
   }

   public void setChonloaibc(String chonloaibc)
   {
     this.chonloaibc = chonloaibc;
   }

   public String getChonloaibc()
   {
     return this.chonloaibc;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoaMa)
   {
     this.khoa_ma = khoaMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3335_BaoCaoBHYTNgoaiTru

 * JD-Core Version:    0.7.0.1

 */