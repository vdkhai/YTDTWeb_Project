 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B3135_Tonghopthuocydungcutheophong")
 @Scope(ScopeType.CONVERSATION)
 public class Tonghopthuocydungcutheophong
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
   private String resetFormB3135 = null;
   @DataModel
   private List<Hsba> listBN = new ArrayList();
   @DataModelSelection("listBN")
   @Out(required=false)
   private Hsba hsbaSelect;
   private int index = 0;
   private String ngayhientai = "";
   private String khoaMa = "";
   private String ngaydungtu = "";
   private String ngaydungden = "";
   private String sobenhanStr = "";
   private String hotenBNStr = "";
   private Hsba hsba = null;
   @In(required=false)
   @Out(required=false)
   private String ten3135_3138;

   @Factory("resetFormB3135")
   public void initresetFormB3135()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init(String ten3135_3138)
   {
     this.log.info("init()", new Object[0]);
     this.ten3135_3138 = ten3135_3138;
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB3135 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void loadHsba()
   {
     this.log.info("-----So vao vien-----" + this.sobenhanStr, new Object[0]);
     this.hsba = new Hsba();
     if (!this.sobenhanStr.equals("")) {
       try
       {
         HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
         this.hsba = hsbaDAO.find(this.sobenhanStr);
         if (this.hsba == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sobenhanStr });
           return;
         }
         setHotenBNStr(this.hsba.getBenhnhanMa().getBenhnhanHoten());
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sobenhanStr });
         e.printStackTrace();
       }
     }
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
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);
     boolean test = false;
     if (this.hsba != null) {
       if (this.listBN.size() == 0)
       {
         this.log.info("size list bang 0", new Object[0]);
         this.listBN.add(this.hsba);
         this.log.info("da add phan tu dau tien" + this.listBN.size(), new Object[0]);
       }
       else if (this.listBN.size() > 0)
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (Hsba item : this.listBN) {
           if (item.getHsbaSovaovien().equals(this.sobenhanStr)) {
             test = true;
           }
         }
         if (!test) {
           this.listBN.add(this.hsba);
         }
         this.log.info("da add phan tu" + this.listBN.size(), new Object[0]);
       }
     }
     setSobenhanStr("");
     setHotenBNStr("");
     setHsba(null);
     this.log.info("ketthuc them du lieu vao luoi", new Object[0]);
   }

   public void deleteBN()
   {
     this.log.info("bat dau xoa , size" + this.listBN.size(), new Object[0]);
     this.listBN.remove(this.hsbaSelect);
     this.log.info("da xoa , size" + this.listBN.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void resetForm()
   {
     this.khoaMa = "";
     this.ngaydungtu = "";
     this.ngaydungden = "";
     this.sobenhanStr = "";
     this.hotenBNStr = "";
     this.listBN.clear();
     FacesMessages.instance().clear();
   }

   public void XuatReport()
   {
     this.reportTypeVP = "Tonghopthuocydungcutheophong";
     this.log.info("Vao Method XuatReport Tong hop thuoc y dung cu theo phong", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ("3135".equals(this.ten3135_3138)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/tonghopthuocydungcutheophong.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieulinhthuochangngay.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       DmKhoa dmkhoa = new DmKhoa();
       DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
       Object obj = dtUtilDAO.findByMa(this.khoaMa, "DmKhoa", "dmkhoaMa");
       dmkhoa = (DmKhoa)obj;
       params.put("KHOA", dmkhoa.getDmkhoaTen());
       params.put("KHOAMASO", dmkhoa.getDmkhoaMaso());
       params.put("NGAYLAPPHIEU", sdf.parse(this.ngayhientai));
       params.put("NGAYDUNGTU", sdf.parse(this.ngaydungtu));
       params.put("NGAYDUNGDEN", sdf.parse(this.ngaydungden));

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       if (this.listBN.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (Hsba item : this.listBN) {
           listtemp.add(item.getHsbaSovaovien());
         }
         params.put("SOVAOVIEN", getListDVMaParamsHelp(listtemp));
         this.log.info("list phan loai " + getListDVMaParamsHelp(listtemp), new Object[0]);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Tonghopthuocydungcutheophong");
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getNgaydungtu()
   {
     return this.ngaydungtu;
   }

   public void setNgaydungtu(String ngaydungtu)
   {
     this.ngaydungtu = ngaydungtu;
   }

   public String getNgaydungden()
   {
     return this.ngaydungden;
   }

   public void setNgaydungden(String ngaydungden)
   {
     this.ngaydungden = ngaydungden;
   }

   public String getSobenhanStr()
   {
     return this.sobenhanStr;
   }

   public void setSobenhanStr(String sobenhanStr)
   {
     this.sobenhanStr = sobenhanStr;
   }

   public String getHotenBNStr()
   {
     return this.hotenBNStr;
   }

   public void setHotenBNStr(String hotenBNStr)
   {
     this.hotenBNStr = hotenBNStr;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.Tonghopthuocydungcutheophong

 * JD-Core Version:    0.7.0.1

 */