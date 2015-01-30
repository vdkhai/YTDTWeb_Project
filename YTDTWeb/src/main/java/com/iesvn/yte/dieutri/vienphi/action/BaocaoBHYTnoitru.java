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

 @Name("B3332_BaocaoBHYTnoitru")
 @Scope(ScopeType.CONVERSATION)
 public class BaocaoBHYTnoitru
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
   @Out(scope=ScopeType.PAGE, required=false)
   @In(scope=ScopeType.PAGE, required=false)
   private String resetFormB3332;
   @DataModel
   private List<DtDmKhoiBhyt> listkhoiBHYTB3332 = new ArrayList();
   @DataModelSelection("listkhoiBHYTB3332")
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
   private String chonloaibo = "";
   private String chonloaibc = "";
   private String khoa_ma = "";
   private Integer masoPLBHYT = null;
   private String maPLBHYT = "";

   @Factory("resetFormB3332")
   public void initTmp()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru";
   }

   @End
   public void end() {}

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB3332 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     setChonloaibc("l1");
     setChonloaibo("r1");
     setTuyen(null);
     setKhoa_ma("");
     this.listkhoiBHYTB3332.clear();
     setMaPLBHYT("");
   }

   private String getListDVMaParamsHelp(List<Integer> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     String result = "";
     for (Integer each : inputs) {
       if (each != null) {
         result = result + each.intValue() + ",";
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
     this.resetFormB3332 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi" + this.maKhoiBHYT, new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.maKhoiBHYT.equals("")) {
       if ((this.listkhoiBHYTB3332.size() == 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
         DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
         this.listkhoiBHYTB3332.add(khoiBHYT);
         this.log.info("da add phan tu dau tien" + this.listkhoiBHYTB3332.size(), new Object[0]);
       }
       else if ((this.listkhoiBHYTB3332.size() > 0) && (this.maKhoiBHYT != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DtDmKhoiBhyt item : this.listkhoiBHYTB3332) {
           if (item.getDtdmkhoibhytMa().equals(this.maKhoiBHYT)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.maKhoiBHYT, "DtDmKhoiBhyt", "dtdmkhoibhytMa");
           DtDmKhoiBhyt khoiBHYT = (DtDmKhoiBhyt)obj;
           this.listkhoiBHYTB3332.add(khoiBHYT);
         }
         this.log.info("da add phan tu" + this.listkhoiBHYTB3332.size(), new Object[0]);
       }
     }
     setMaKhoiBHYT("");
   }

   public void deletedmkhoiBHYT()
   {
     this.log.info("bat dau xoa , size" + this.listkhoiBHYTB3332.size(), new Object[0]);
     this.listkhoiBHYTB3332.remove(this.dmkhoiBHYTB3332Selected);
     this.log.info("da xoa , size" + this.listkhoiBHYTB3332.size(), new Object[0]);
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

       Map<String, Object> params = new HashMap();
       if (this.chonloaibc.equals("l1"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP26a_DSDeNghiThanhToanChiPhiKCBNoiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l2"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP26b_ThongBaoThanhToanChiPhiKCBNoiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l3"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP26a_TongHopChiPhiKCBNoiTru.jrxml";
       }
       else if (this.chonloaibc.equals("l4"))
       {
         params.put("TINH_BH", IConstantsRes.REPORT_DIEUTRI_TINH);
         params.put("TUYEN_BV", IConstantsRes.MASO_TUYEN_BV_TRIEN_KHAI);
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP14b_BaoCaoChiPhiKCBNoiTruCacNhomDTTheoTuyenCMKT.jrxml";
       }
       else if (this.chonloaibc.equals("l5"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP26d_ThongBaoThanhToanChiPhiKCBNoiTru.jrxml";
       }
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
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
         this.log.info("chon:IN", new Object[0]);
       }
       else if (this.chonloaibo.equals("r2"))
       {
         params.put("chon", "NOT IN");
         this.log.info("chon:NOT IN", new Object[0]);
       }
       if (this.listkhoiBHYTB3332.size() > 0)
       {
         List<Integer> listtemp = new ArrayList();
         for (DtDmKhoiBhyt item : this.listkhoiBHYTB3332) {
           listtemp.add(item.getDtdmkhoibhytMaso());
         }
         String tempStr = getListDVMaParamsHelp(listtemp);
         params.put("khoi", tempStr);

         this.log.info("khoi " + tempStr, new Object[0]);
       }
       else
       {
         params.put("chon", "NOT IN");
         params.put("khoi", "0");
         this.log.info("khoi " + null, new Object[0]);
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
       Integer congNgayDT = IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI.trim().length() > 0 ? new Integer(IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI) : new Integer("0");
       params.put("cong_ngay_dt", congNgayDT);




























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

   public String getChonloaibo()
   {
     return this.chonloaibo;
   }

   public void setChonloaibo(String chonloaibo)
   {
     this.chonloaibo = chonloaibo;
   }

   public String getChonloaibc()
   {
     return this.chonloaibc;
   }

   public void setChonloaibc(String chonloaibc)
   {
     this.chonloaibc = chonloaibc;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BaocaoBHYTnoitru

 * JD-Core Version:    0.7.0.1

 */