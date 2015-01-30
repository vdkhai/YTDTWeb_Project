 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanNhomThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
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
 import javax.faces.model.SelectItem;
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
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4134_Tinhtonkhovagiatri")
 @Synchronized(timeout=6000000L)
 public class TinhTonKhoVaGiaTri
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4134 = null;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList();
   @DataModelSelection("listDmPLThuocKK")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelectKK;
   private List<SelectItem> ttIn = null;
   private Integer inTheo = null;
   private int index = 0;
   private String ngayhientai;
   private String ngayNHAPLIEU;
   private String nienHAN = "";
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private Integer khoa_maso = null;
   private Integer ct_maso = null;
   private Integer kp_maso = null;
   private Integer nsx_maso = null;
   private Boolean chon = Boolean.valueOf(false);
   private String plthuoc_ma = "";
   private String loaihang_ma = "";
   private String khoa_ma = "";
   private String ct_ma = "";
   private String kp_ma = "";
   private String nsx_ma = "";
   private boolean baoGOMTON = false;
   Map<String, Object> params = null;
   String dmKhoXuat = "";
   private String nhomthuoc_ma = "";
   private Integer nhomthuoc_maso = null;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4134")
   public void initresetFormB4134()
   {
     this.log.info("init()", new Object[0]);

     resetForm();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     if ("ALL".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.baoCaoDuoc;
     }
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri";
   }

   @End
   public void end() {}

   public void resetList()
   {
     this.log.info("=============reset listttttttttttt", new Object[0]);
     this.listDmPLThuocKK.clear();
     setPlthuoc_ma("");
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

   public void resetForm()
   {
     this.resetFormB4134 = "";
     setPlthuoc_ma("");
     this.listDmPLThuocKK.clear();
     setLoaihang_ma("");
     setCt_ma("");
     setKp_ma("");
     setNsx_ma("");
     setNienHAN("");
     setBaoGOMTON(false);
     setInTheo(new Integer(1));
     this.ngayhientai = Utils.getCurrentDate();
     this.log.info("ngayhientai " + this.ngayhientai, new Object[0]);
     this.ngayNHAPLIEU = Utils.getCurrentDate();
     this.nhomthuoc_ma = "";
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.plthuoc_ma.equals("")) {
       if ((this.listDmPLThuocKK.size() == 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
         if (plThuoc != null) {
           this.listDmPLThuocKK.add(plThuoc);
         }
         this.log.info("da add phan tu dau tien" + this.listDmPLThuocKK.size(), new Object[0]);
       }
       else if ((this.listDmPLThuocKK.size() > 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
           if (item.getDmphanloaithuocMa().equals(this.plthuoc_ma)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
           DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
           if (plThuoc != null) {
             this.listDmPLThuocKK.add(plThuoc);
           }
         }
         this.log.info("da add phan tu" + this.listDmPLThuocKK.size(), new Object[0]);
       }
     }
     setPlthuoc_ma("");
   }

   public void deletedmPLThuoc()
   {
     this.log.info("bat dau xoa , size" + this.listDmPLThuocKK.size(), new Object[0]);
     this.listDmPLThuocKK.remove(this.dmPLThuocSelectKK);
     this.log.info("da xoa , size" + this.listDmPLThuocKK.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Tinhtonkhovagiatri";
     this.log.info("Vao Method XuatReport cap nhat bang kiem ke", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.inTheo.intValue() == 1) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D07_Baocaotonkho_gopgia.jrxml";
       } else if (this.inTheo.intValue() == 2) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D07_Baocaotonkho_tachgia.jrxml";
       } else if (this.inTheo.intValue() == 3) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D07_Baocaotonkho_goplo.jrxml";
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);


       this.log.info("da thay file template ", new Object[0]);
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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, this.params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Tinhtonkhovagiatri");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
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

   public String thuchienAction()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     this.params = new HashMap();
     DieuTriUtilDelegate dtutils = DieuTriUtilDelegate.getInstance();
     this.params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
     this.params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
     this.params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
     this.params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
     try
     {
       if (!this.baoGOMTON) {
         this.params.put("TON0", new Integer(0));
       }
       if ((this.loaihang_ma != null) && (!this.loaihang_ma.trim().equals("")))
       {
         DmLoaiThuoc dmlthuoc = new DmLoaiThuoc();
         Object objlt = dtutils.findByMa(this.loaihang_ma, "DmLoaiThuoc", "dmloaithuocMa");
         dmlthuoc = (DmLoaiThuoc)objlt;
         this.params.put("MALOAI", this.loaihang_maso);
         this.params.put("LOAI", dmlthuoc.getDmloaithuocTen());
       }
       else
       {
         this.loaihang_maso = null;
         this.params.put("MALOAI", this.loaihang_maso);
         this.params.put("LOAI", "Tất cả");
       }
       this.log.info("MALOAI " + this.loaihang_maso, new Object[0]);
       if (!this.khoa_ma.trim().equals(""))
       {
         this.params.put("MAKHO", this.khoa_maso);
         DmKhoa dmkhoa = new DmKhoa();
         Object objK = dtutils.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         dmkhoa = (DmKhoa)objK;
         this.params.put("THUOCKHO", dmkhoa.getDmkhoaTen());
       }
       else
       {
         this.khoa_maso = null;
         this.params.put("MAKHO", this.khoa_maso);
       }
       this.log.info("MAKHO " + this.khoa_maso, new Object[0]);
       if (!this.ct_ma.trim().equals(""))
       {
         DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
         Object objnct = dtutils.findByMa(this.ct_ma, "DmNguonChuongTrinh", "dmnctMa");
         dmnct = (DmNguonChuongTrinh)objnct;
         this.params.put("MACT", this.ct_maso);
         this.params.put("NGUON", dmnct.getDmnctTen());
       }
       else
       {
         this.ct_maso = null;
         this.params.put("MACT", this.ct_maso);
         this.params.put("NGUON", "Tất cả");
       }
       this.log.info("MACT " + this.ct_maso, new Object[0]);
       if (!this.kp_ma.trim().equals(""))
       {
         this.params.put("MAKP", this.kp_maso);
         DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
         Object objnkp = dtutils.findByMa(this.kp_ma, "DmNguonKinhPhi", "dmnguonkinhphiMa");
         dmnkp = (DmNguonKinhPhi)objnkp;
         this.params.put("KINHPHI", dmnkp.getDmnguonkinhphiTen());
       }
       else
       {
         this.kp_maso = null;
         this.params.put("KINHPHI", "Tất cả");
         this.params.put("MAKP", this.kp_maso);
       }
       this.log.info("MAKP " + this.kp_maso, new Object[0]);
       if (!this.nsx_ma.trim().equals(""))
       {
         this.params.put("MANSX", this.nsx_maso);
         DmQuocGia dmqg = new DmQuocGia();
         Object objnsx = dtutils.findByMa(this.nsx_ma, "DmQuocGia", "dmquocgiaMa");
         dmqg = (DmQuocGia)objnsx;
         this.params.put("NSX", dmqg.getDmquocgiaTen());
       }
       else
       {
         this.nsx_maso = null;
         this.params.put("NSX", "Tất cả");
         this.params.put("MANSX", this.nsx_maso);
       }
       this.log.info("MANSX " + this.nsx_maso, new Object[0]);

       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       if (!this.ngayNHAPLIEU.trim().equals(""))
       {
         Date dNgayNhapLieu = sdf.parse(this.ngayNHAPLIEU);
         Calendar curentDate = Calendar.getInstance();
         curentDate.add(5, -1);
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         if (dNgayNhapLieu.before(curentDate.getTime())) {
           this.params.put("NGAYBC", sdf.format(dNgayNhapLieu) + " 23:59");
         } else {
           this.params.put("NGAYBC", dateFormat.format(new Date()));
         }
         if ((dNgayNhapLieu.before(dNgayTonCuoi)) || (dNgayNhapLieu.equals(dNgayTonCuoi))) {
           this.params.put("NGAY", dNgayTonCuoi);
         } else {
           this.params.put("NGAY", dNgayNhapLieu);
         }
       }
       if (this.listDmPLThuocKK.size() > 0)
       {
         List<String> listtemp = new ArrayList();
         for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
           listtemp.add(item.getDmphanloaithuocMa());
         }
         this.params.put("PHANLOAI", getListDVMaParamsHelp(listtemp));
         this.log.info("list phan loai " + getListDVMaParamsHelp(listtemp), new Object[0]);
       }
       else
       {
         this.params.put("PHANLOAI", null);
       }
       if (!this.nhomthuoc_ma.equals(""))
       {
         DmPhanNhomThuoc dmpnthuoc = new DmPhanNhomThuoc();
         dmpnthuoc = (DmPhanNhomThuoc)dtutils.findByMa(this.nhomthuoc_ma, "DmPhanNhomThuoc", "dmphannhomthuocMa");
         this.params.put("NHOMTHUOCMASO", dmpnthuoc.getDmphannhomthuocMaso());
       }
       else
       {
         this.params.put("NHOMTHUOCMASO", null);
       }
       XuatReport();
       this.resetFormB4134 = null;
       return "B4160_Chonmenuxuattaptin";
     }
     catch (Exception ex)
     {
       this.log.info("That bai ", new Object[0]);
       ex.printStackTrace();
     }
     return null;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getCt_maso()
   {
     return this.ct_maso;
   }

   public void setCt_maso(Integer ct_maso)
   {
     this.ct_maso = ct_maso;
   }

   public Integer getKp_maso()
   {
     return this.kp_maso;
   }

   public void setKp_maso(Integer kp_maso)
   {
     this.kp_maso = kp_maso;
   }

   public Boolean getChon()
   {
     return this.chon;
   }

   public void setChon(Boolean chon)
   {
     this.chon = chon;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getCt_ma()
   {
     return this.ct_ma;
   }

   public void setCt_ma(String ct_ma)
   {
     this.ct_ma = ct_ma;
   }

   public String getKp_ma()
   {
     return this.kp_ma;
   }

   public void setKp_ma(String kp_ma)
   {
     this.kp_ma = kp_ma;
   }

   public void setTtIn(List<SelectItem> ttIn)
   {
     this.ttIn = ttIn;
   }

   public List<SelectItem> getTtIn()
   {
     if (this.ttIn == null)
     {
       this.ttIn = new ArrayList();
       this.ttIn.add(new SelectItem(Integer.valueOf(1), "In theo mã hàng"));
       this.ttIn.add(new SelectItem(Integer.valueOf(2), "In theo tên hàng"));
     }
     return this.ttIn;
   }

   public void setInTheo(Integer inTheo)
   {
     this.inTheo = inTheo;
   }

   public Integer getInTheo()
   {
     return this.inTheo;
   }

   public Integer getNsx_maso()
   {
     return this.nsx_maso;
   }

   public void setNsx_maso(Integer nsx_maso)
   {
     this.nsx_maso = nsx_maso;
   }

   public String getNsx_ma()
   {
     return this.nsx_ma;
   }

   public void setNsx_ma(String nsx_ma)
   {
     this.nsx_ma = nsx_ma;
   }

   public void setNgayNHAPLIEU(String ngayNHAPLIEU)
   {
     this.ngayNHAPLIEU = ngayNHAPLIEU;
   }

   public String getNgayNHAPLIEU()
   {
     return this.ngayNHAPLIEU;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setNienHAN(String nienHAN)
   {
     this.nienHAN = nienHAN;
   }

   public String getNienHAN()
   {
     return this.nienHAN;
   }

   public boolean isBaoGOMTON()
   {
     return this.baoGOMTON;
   }

   public void setBaoGOMTON(boolean baoGOMTON)
   {
     this.baoGOMTON = baoGOMTON;
   }

   public Integer getNhomthuoc_maso()
   {
     return this.nhomthuoc_maso;
   }

   public void setNhomthuoc_maso(Integer nhomthuoc_maso)
   {
     this.nhomthuoc_maso = nhomthuoc_maso;
   }

   public String getNhomthuoc_ma()
   {
     return this.nhomthuoc_ma;
   }

   public void setNhomthuoc_ma(String nhomthuoc_ma)
   {
     this.nhomthuoc_ma = nhomthuoc_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.TinhTonKhoVaGiaTri

 * JD-Core Version:    0.7.0.1

 */