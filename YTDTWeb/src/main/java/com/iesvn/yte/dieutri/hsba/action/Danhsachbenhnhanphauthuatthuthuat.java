 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTaiNan;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Name("Danhsachbenhnhanphauthuatthuthuat")
 @Scope(ScopeType.SESSION)
 public class Danhsachbenhnhanphauthuatthuthuat
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPath = null;
   @Out(required=false)
   @In(required=false)
   private String reportPath5b = null;
   @Out(required=false)
   @In(required=false)
   private String reportType = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jPrint = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jPrint1 = null;
   @In(scope=ScopeType.SESSION, required=false)
   @Out(scope=ScopeType.SESSION, required=false)
   private String resetFormB225 = null;
   @DataModel
   private List<DtDmLoaiPhauThuat> listDtDmLoaiPT = new ArrayList();
   @DataModelSelection("listDtDmLoaiPT")
   @Out(required=false)
   private DtDmLoaiPhauThuat dtDmLoaiPTSelect;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private static int index = 0;
   private static int index5b = 0;
   private Integer khoa_maso = null;
   private Integer doituong_maso = null;
   private Integer tainan_maso = null;
   private Integer bacsi_maso = null;
   private Integer pt_maso = null;
   private String khoa_ma = null;
   private String doituong_ma = null;
   private String tainan_ma = null;
   private String bacsi_ma = null;
   private String pt_ma = null;
   private String loaiPT_ma = null;
   private String gtSelect;
   private String capcuuSelect;
   private String songSelect;
   private String ngayhientai;

   @Factory("resetFormB225")
   public void init()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public String thuchienAction()
   {
     XuatReport();
     resetForm();
     this.resetFormB225 = null;
     return "B225_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     setThoigian_nam(null);
     setThoigian_thang(null);
     setTungay("");
     setDenngay("");
     setKhoa_ma(null);
     setTainan_ma(null);
     setDoituong_ma(null);
     setBacsi_ma(null);
     setPt_ma(null);
     setLoaiPT_ma(null);
     setGtSelect("namnu");
     setCapcuuSelect("ccphien");
     setSongSelect("songchet");
     this.listDtDmLoaiPT.clear();
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if ((this.listDtDmLoaiPT.size() == 0) && (this.loaiPT_ma != null))
     {
       this.log.info("size list bang 0", new Object[0]);
       Object obj = dtutilDelegate.findByMa(this.loaiPT_ma, "DtDmLoaiPhauThuat", "dtdmloaiptMa");
       DtDmLoaiPhauThuat loaiPT = (DtDmLoaiPhauThuat)obj;
       this.listDtDmLoaiPT.add(loaiPT);
       this.log.info("da add phan tu dau tien" + this.listDtDmLoaiPT.size(), new Object[0]);
     }
     else if ((this.listDtDmLoaiPT.size() > 0) && (getLoaiPT_ma() != null))
     {
       this.log.info("size list lon hon 0", new Object[0]);
       for (DtDmLoaiPhauThuat item : this.listDtDmLoaiPT) {
         if (item.getDtdmloaiptMa().equals(getLoaiPT_ma())) {
           test = true;
         }
       }
       if (!test)
       {
         Object obj = dtutilDelegate.findByMa(this.loaiPT_ma, "DtDmLoaiPhauThuat", "dtdmloaiptMa");
         DtDmLoaiPhauThuat loaiPT = (DtDmLoaiPhauThuat)obj;
         this.listDtDmLoaiPT.add(loaiPT);
       }
       this.log.info("da add phan tu" + this.listDtDmLoaiPT.size(), new Object[0]);
     }
   }

   public void deleteDtDmLoaiPT()
   {
     this.log.info("bat dau xoa , size" + this.listDtDmLoaiPT.size(), new Object[0]);
     this.listDtDmLoaiPT.remove(this.dtDmLoaiPTSelect);
     this.log.info("da xoa , size" + this.listDtDmLoaiPT.size(), new Object[0]);
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
   {
     this.reportType = "DSBNPhauthuatThuThuat";
     this.log.info("Vao Method XuatReport Danh sach benh nhan phau thuat thu thuat", new Object[0]);
     String resultReportName = null;
     String baocao1 = null;
     String baocao2 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B05_Benhnhanphauthuat.jrxml";
       String pathTemplate5b = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B05b_Benhnhanphauthuat.jrxml";

       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       this.log.info("da thay file templete 5b" + pathTemplate5b, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport jasperReport5b = JasperCompileManager.compileReport(pathTemplate5b);

       this.log.info("da thay file template ", new Object[0]);
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       Map<String, Object> params = new HashMap();
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
       if (this.doituong_maso != null)
       {
         this.log.info("doi tuong " + this.doituong_maso, new Object[0]);
         params.put("DOITUONGMASO", this.doituong_maso);
         DmDoiTuong dmdoituong = new DmDoiTuong();
         Object obj = dtutilDelegate.findByMa(this.doituong_ma, "DmDoiTuong", "dmdoituongMa");
         dmdoituong = (DmDoiTuong)obj;
         this.log.info("Dm doi tuong tim thay" + dmdoituong, new Object[0]);
         params.put("DOITUONG", dmdoituong.getDmdoituongTen());
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
       if (this.bacsi_maso != null)
       {
         this.log.info("bac si  ma" + this.bacsi_maso, new Object[0]);
         params.put("BACSIMASO", this.bacsi_maso);
         DtDmNhanVien dtdmnhanvien = new DtDmNhanVien();
         Object obj = dtutilDelegate.findByMa(this.bacsi_ma, "DtDmNhanVien", "dtdmnhanvienMa");
         dtdmnhanvien = (DtDmNhanVien)obj;
         this.log.info("Dm nhan vien tim thay" + dtdmnhanvien, new Object[0]);
         params.put("BACSI", dtdmnhanvien.getDtdmnhanvienTen());
       }
       if (this.pt_maso != null)
       {
         this.log.info("phau thuat " + this.pt_maso, new Object[0]);
         params.put("PTMASO", this.pt_maso);
         params.put("MAMO", this.pt_ma);
       }
       if (!this.tungay.equals(""))
       {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         params.put("TUNGAY", this.tungay);
         params.put("TUNGAYDATE", sdf.parse(this.tungay));
         if (!this.denngay.equals(""))
         {
           params.put("DENNGAY", this.denngay);
           params.put("DENNGAYDATE", sdf.parse(this.denngay));
         }
       }
       this.log.info("banh vien xuat bao cao " + IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI, new Object[0]);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
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
       if (this.capcuuSelect != null) {
         if (this.capcuuSelect.equals("capcuu"))
         {
           this.log.info("3", new Object[0]);
           params.put("CCMASO", new Integer(1));
           params.put("CCPHIEN", "Cấp cứu");
         }
         else if (this.capcuuSelect.equals("phien"))
         {
           this.log.info("4", new Object[0]);
           params.put("CCMASO", new Integer(2));
           params.put("CCPHIEN", "Phiên");
         }
       }
       if (this.songSelect != null) {
         if (this.songSelect.equals("chet"))
         {
           this.log.info("vao method in song chet", new Object[0]);
           params.put("KETQUADT", "Chết");
         }
         else
         {
           params.put("KETQUADT", "Sống");
         }
       }
       if (this.listDtDmLoaiPT.size() > 0)
       {
         this.log.info("bat dau add danh sach loai phau thuat", new Object[0]);
         List<String> listTemp = new ArrayList();
         for (DtDmLoaiPhauThuat item : this.listDtDmLoaiPT) {
           listTemp.add(item.getDtdmloaiptMa());
         }
         String listLoaiPT = getListDVMaParamsHelp(listTemp);
         this.log.info("danh sach loai phau thuat" + listLoaiPT, new Object[0]);
         params.put("LOAIPTMASO", listLoaiPT);
       }
       this.log.info("================= khoa ma" + this.khoa_maso, new Object[0]);
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
       this.jPrint = JasperFillManager.fillReport(jasperReport, params, conn);
       this.jPrint1 = JasperFillManager.fillReport(jasperReport5b, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jPrint, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Danhsachbenhnhanphauthuat");
       baocao2 = XuatReportUtil.ReportUtil(this.jPrint1, index5b, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Danhsachbenhnhanphauthuat5b");
       this.reportPath = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.reportPath5b = baocao2.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPath, new Object[0]);
       this.log.info("duong dan file xuat report 5b:" + baocao2, new Object[0]);
       this.log.info("duong dan 5b-------------------- :" + this.reportPath5b, new Object[0]);
       index += 1;
       index5b += 1;
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

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
   }

   public void setLoaiPT_ma(String loaiPT_ma)
   {
     this.loaiPT_ma = loaiPT_ma;
   }

   public String getLoaiPT_ma()
   {
     return this.loaiPT_ma;
   }

   public String getGtSelect()
   {
     return this.gtSelect;
   }

   public void setGtSelect(String gtSelect)
   {
     this.gtSelect = gtSelect;
   }

   public String getCapcuuSelect()
   {
     return this.capcuuSelect;
   }

   public void setCapcuuSelect(String capcuuSelect)
   {
     this.capcuuSelect = capcuuSelect;
   }

   public String getSongSelect()
   {
     return this.songSelect;
   }

   public void setSongSelect(String songSelect)
   {
     this.songSelect = songSelect;
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

   public Integer getBacsi_maso()
   {
     return this.bacsi_maso;
   }

   public void setBacsi_maso(Integer bacsi_maso)
   {
     this.bacsi_maso = bacsi_maso;
   }

   public Integer getPt_maso()
   {
     return this.pt_maso;
   }

   public void setPt_maso(Integer pt_maso)
   {
     this.pt_maso = pt_maso;
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

   public String getBacsi_ma()
   {
     return this.bacsi_ma;
   }

   public void setBacsi_ma(String bacsi_ma)
   {
     this.bacsi_ma = bacsi_ma;
   }

   public String getPt_ma()
   {
     return this.pt_ma;
   }

   public void setPt_ma(String pt_ma)
   {
     this.pt_ma = pt_ma;
   }

   public static void setIndex5b(int index5b)
   {
     index5b = index5b;
   }

   public static int getIndex5b()
   {
     return index5b;
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

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.Danhsachbenhnhanphauthuatthuthuat

 * JD-Core Version:    0.7.0.1

 */