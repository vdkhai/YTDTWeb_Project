 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
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
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import javax.faces.model.SelectItem;
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
 import org.jboss.seam.log.Log;

 @Name("B4415_Inbctiensudungtaikhoa")
 @Scope(ScopeType.CONVERSATION)
 public class Inbctiensudungtaikhoa
   implements Serializable
 {
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
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private static int index = 0;
   private Integer loaiHang;
   private Integer phanLoai;
   private Integer khoaXuat;
   private Integer khoaNhan;
   private String khoaXuatMa;
   private String khoaNhanMa;
   String dmKhoXuat = "";
   private String dmTenKho = "";
   private DmKhoaDelegate dmKhoaDelegate;
   private List<SelectItem> listDmKhos = new ArrayList();
   private HashMap hmDmKho = new HashMap();
   private Integer doituongMaso;
   private String doituong;

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     this.dmKhoXuat = kho;
     refreshDmKhoXuat();
     resetForm();
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa";
   }

   @End
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();
     resetForm();
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.ngayhientai = Utils.getCurrentDate();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.khoaXuatMa = "";
     this.khoaXuat = null;
     this.dmTenKho = "";
     this.khoaNhan = null;
     this.khoaNhanMa = "";
     this.doituong = "";
     this.doituongMaso = null;
   }

   public String getDmTenKho()
   {
     return this.dmTenKho;
   }

   public void setDmTenKho(String dmTenKho)
   {
     this.dmTenKho = dmTenKho;
   }

   public List<SelectItem> getListDmKhos()
   {
     return this.listDmKhos;
   }

   public void setListDmKhos(List<SelectItem> listDmKhos)
   {
     this.listDmKhos = listDmKhos;
   }

   public void onblurMaKhoAction()
   {
     this.log.info("-----BEGIN onblurMaKhoAction()-----", new Object[0]);
     DmKhoa dmKhoa = (DmKhoa)this.hmDmKho.get(this.khoaXuatMa.toUpperCase());
     if (dmKhoa != null)
     {
       setDmTenKho(dmKhoa.getDmkhoaTen());
       setKhoaXuat(dmKhoa.getDmkhoaMaso());
     }
     else
     {
       setDmTenKho("");
       setKhoaXuat(null);
     }
     this.log.info("-----END onblurMaLoaiAction()-----", new Object[0]);
   }

   public void onblurTenKhoAction()
   {
     this.log.info("-----BEGIN onblurTenKhoAction()-----", new Object[0]);
     DmKhoa dmKhoaFinded = new DmKhoa();
     boolean havingData = false;
     Set set = this.hmDmKho.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmKhoa dmKhoa = (DmKhoa)me.getValue();
       if ((dmKhoa.getDmkhoaTen() == this.dmTenKho) || (dmKhoa.getDmkhoaTen().equals(this.dmTenKho)))
       {
         havingData = true;
         dmKhoaFinded = dmKhoa;
         break;
       }
     }
     if (havingData)
     {
       setKhoaXuatMa(dmKhoaFinded.getDmkhoaMa());
       setKhoaXuat(dmKhoaFinded.getDmkhoaMaso());
     }
     else
     {
       setKhoaXuatMa("");
       setKhoaXuat(null);
     }
     this.log.info("-----END onblurTenKhoAction()-----", new Object[0]);
   }

   public void refreshDmKhoXuat()
   {
     this.listDmKhos.clear();
     this.hmDmKho.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();
     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoChinh_KhoLe();
     this.listDmKhos.add(new SelectItem("Tủ trực"));
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhos.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKho.put(o.getDmkhoaMa(), o);
       }
     }
     lstDmKhoa.clear();
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Inbctiensudungtaikhoa";
     this.log.info("Vao Method XuatReport in bao cao tien su dung tai khoa", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D21_solieusudungtaikhoa.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("DENNGAYDATE", sdf.parse(this.denngay));

       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Date dTuNgay = sdf.parse(this.tungay);
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi))) {
         params.put("TUNGAYDATE", dNgayTonDau);
       } else {
         params.put("TUNGAYDATE", dTuNgay);
       }
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       this.log.info("loaiHang:" + this.loaiHang, new Object[0]);
       this.log.info("phanLoai:" + this.phanLoai, new Object[0]);

       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       if (this.loaiHang != null)
       {
         DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
         Object objLThuoc = dtUtil.findByMaSo(this.loaiHang, "DmLoaiThuoc", "dmloaithuocMaso");
         dmloaithuoc = (DmLoaiThuoc)objLThuoc;
         params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
         params.put("LOAITHUOCMASO", this.loaiHang);
       }
       else
       {
         params.put("LOAITHUOC", "Tất cả");
         params.put("LOAITHUOCMASO", null);
       }
       if (this.phanLoai != null)
       {
         DmPhanLoaiThuoc dmplthuoc = new DmPhanLoaiThuoc();
         Object objLThuoc = dtUtil.findByMaSo(this.phanLoai, "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
         dmplthuoc = (DmPhanLoaiThuoc)objLThuoc;
         params.put("PHANLOAI", dmplthuoc.getDmphanloaithuocTen());
         params.put("PLTHUOCMA", this.phanLoai);
       }
       else
       {
         params.put("PHANLOAI", "Tất cả");
         params.put("PLTHUOCMA", null);
       }
       String khoaNhanTen = "";
       if ((this.khoaNhanMa != null) && (!this.khoaNhanMa.equals("")))
       {
         DmKhoa khoanhan_temp = (DmKhoa)dtUtil.findByMa(this.khoaNhanMa, "DmKhoa", "dmkhoaMa");
         if (khoanhan_temp != null)
         {
           params.put("KHOANHANTEN", khoanhan_temp.getDmkhoaTen());
           khoaNhanTen = khoanhan_temp.getDmkhoaTen();
         }
         else
         {
           params.put("KHOANHANTEN", "Tất cả");
         }
       }
       else
       {
         params.put("KHOANHANTEN", "Tất cả");
       }
       params.put("KHOANHAN", this.khoaNhan);
       this.log.info("khoaNhan:" + this.khoaNhan, new Object[0]);
       if ((this.khoaXuatMa != null) && (!this.khoaXuatMa.equals("")))
       {
         DmKhoa khoaxuat_temp = (DmKhoa)dtUtil.findByMa(this.khoaXuatMa, "DmKhoa", "dmkhoaMa");
         if (khoaxuat_temp != null) {
           params.put("KHOAXUATTEN", khoaxuat_temp.getDmkhoaTen());
         } else {
           params.put("KHOAXUATTEN", "Tất cả");
         }
       }
       else if ((this.dmTenKho != null) && (!this.dmTenKho.equals("")))
       {
         this.khoaXuat = this.khoaNhan;
         params.put("KHOAXUATTEN", khoaNhanTen);
       }
       else
       {
         params.put("KHOAXUATTEN", "Tất cả");
       }
       params.put("KHOAXUAT", this.khoaXuat);

       this.log.info("khoaXuat:" + this.khoaXuat, new Object[0]);
       if ((this.khoaNhanMa != null) && (!this.khoaNhanMa.equals("")))
       {
         DmDoiTuong doituong_temp = (DmDoiTuong)dtUtil.findByMa(this.doituong, "DmDoiTuong", "dmdoituongMa");
         if (doituong_temp != null) {
           params.put("DOITUONGTEN", doituong_temp.getDmdoituongTen());
         } else {
           params.put("DOITUONGTEN", "Tất cả");
         }
       }
       else
       {
         params.put("DOITUONGTEN", "Tất cả");
       }
       params.put("DOITUONGMASO", this.doituongMaso);
       this.log.info("doituong:" + this.doituong, new Object[0]);

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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Baocaotiensudung");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
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

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public String getReportPathKC()
   {
     return this.reportPathKC;
   }

   public void setReportPathKC(String reportPathKC)
   {
     this.reportPathKC = reportPathKC;
   }

   public String getReportTypeKC()
   {
     return this.reportTypeKC;
   }

   public void setReportTypeKC(String reportTypeKC)
   {
     this.reportTypeKC = reportTypeKC;
   }

   public JasperPrint getJasperPrintKC()
   {
     return this.jasperPrintKC;
   }

   public void setJasperPrintKC(JasperPrint jasperPrintKC)
   {
     this.jasperPrintKC = jasperPrintKC;
   }

   public Integer getLoaiHang()
   {
     return this.loaiHang;
   }

   public void setLoaiHang(Integer loaiHang)
   {
     this.loaiHang = loaiHang;
   }

   public Integer getPhanLoai()
   {
     return this.phanLoai;
   }

   public void setPhanLoai(Integer phanLoai)
   {
     this.phanLoai = phanLoai;
   }

   public Integer getKhoaXuat()
   {
     return this.khoaXuat;
   }

   public void setKhoaXuat(Integer khoaXuat)
   {
     this.khoaXuat = khoaXuat;
   }

   public Integer getKhoaNhan()
   {
     return this.khoaNhan;
   }

   public void setKhoaNhan(Integer khoaNhan)
   {
     this.khoaNhan = khoaNhan;
   }

   public String getKhoaXuatMa()
   {
     return this.khoaXuatMa;
   }

   public void setKhoaXuatMa(String khoaXuatMa)
   {
     this.khoaXuatMa = khoaXuatMa;
   }

   public String getKhoaNhanMa()
   {
     return this.khoaNhanMa;
   }

   public void setKhoaNhanMa(String khoaNhanMa)
   {
     this.khoaNhanMa = khoaNhanMa;
   }

   public String getDoituong()
   {
     return this.doituong;
   }

   public void setDoituong(String doituong)
   {
     this.doituong = doituong;
   }

   public Integer getDoituongMaso()
   {
     return this.doituongMaso;
   }

   public void setDoituongMaso(Integer doituongMaso)
   {
     this.doituongMaso = doituongMaso;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inbctiensudungtaikhoa

 * JD-Core Version:    0.7.0.1

 */