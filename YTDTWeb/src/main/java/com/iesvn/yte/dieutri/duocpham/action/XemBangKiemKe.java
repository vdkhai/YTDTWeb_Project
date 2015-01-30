 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.PagedList;
 import java.io.PrintStream;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;
 import org.jboss.seam.security.Identity;

 @Name("B4144_XemBangKiemKe")
 @Scope(ScopeType.CONVERSATION)
 public class XemBangKiemKe
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
   @Out(required=false)
   @In(required=false)
   private String resetFormXemBangKiemKe = null;
   @In(required=false)
   @Out(required=false)
   private DmKhoa dmKhoaKhoOut;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private KiemKe kiemkeOut;
   @In(required=false)
   @Out(required=false)
   private KiemKeKho kiemkeKhoOut;
   @In(required=false)
   @Out(required=false)
   private String trangthaittOut;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList();
   private int index = 0;
   private Integer khoa_maso = null;
   private Boolean chon = Boolean.valueOf(false);
   private String tenLoaiThuoc = "";
   private String khoa_ma = "";
   Map<String, Object> params = null;
   private String ngayKiem;
   private String ngaygioCN;
   private String nhanvienKiemKe;
   private String trangthaiKK;
   private String trangthaitt;
   private KiemKe kiemke;
   private int page;
   private int itemsPerPage;
   @DataModel
   private List<KiemKeKho> items;
   private int totalPages;

   public int getItemsPerPage()
   {
     return this.itemsPerPage;
   }

   public void setItemsPerPage(int itemsPerPage)
   {
     this.itemsPerPage = itemsPerPage;
   }

   public int getPage()
   {
     return this.page;
   }

   public void setPage(int page)
   {
     this.page = page;
     fetch();
   }

   public int getTotalPages()
   {
     return this.totalPages;
   }

   public void setTotalPages(int totalPages)
   {
     this.totalPages = totalPages;
   }

   public List<KiemKeKho> getItems()
   {
     return this.items;
   }

   public void setItems(List<KiemKeKho> items)
   {
     this.items = items;
   }

   private void fetch()
   {
     KiemKeKhoDelegate kkkDel = KiemKeKhoDelegate.getInstance();
     int total = kkkDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     int limit = this.itemsPerPage;
     int offset = (this.page - 1) * this.itemsPerPage;
     List<KiemKeKho> list = kkkDel.getItemKiemKeKhos(this.kiemke.getMaphieukiem(), limit, offset);
     this.items = new PagedList(list, total, this.itemsPerPage);
   }

   public void resetItemsPerPage()
   {
     setPage(1);
     KiemKeKhoDelegate kkkDel = KiemKeKhoDelegate.getInstance();
     int total = kkkDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     this.totalPages = (total / this.itemsPerPage + 1);
   }

   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormXemBangKiemKe")
   public void resetFormXemBangKiemKe()
   {
     this.log.info("resetFormXemBangKiemKe()", new Object[0]);
     this.listDmPLThuocKK.clear();
     this.items = new ArrayList();
     this.kiemke = this.kiemkeOut;
     this.trangthaitt = this.trangthaittOut;
     this.itemsPerPage = 20;
     setPage(1);
     KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
     int total = kiemkeKhoDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     this.totalPages = (total / this.itemsPerPage + 1);
     displayPhieuKiemKe();
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     this.listDmPLThuocKK.clear();
     this.items = new ArrayList();
     this.kiemke = this.kiemkeOut;
     this.trangthaitt = this.trangthaittOut;
     this.itemsPerPage = 20;
     setPage(1);
     KiemKeKhoDelegate kiemkeKhoDel = KiemKeKhoDelegate.getInstance();
     int total = kiemkeKhoDel.getAllKiemKeKhoTotal(this.kiemke.getMaphieukiem()).intValue();
     this.totalPages = (total / this.itemsPerPage + 1);
     displayPhieuKiemKe();
     return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
   }

   @End
   public void end() {}

   public String quayve()
     throws Exception
   {
     this.resetFormXemBangKiemKe = null;
     return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
   }

   public void displayPhieuKiemKe()
   {
     this.log.info(" ***** displayPhieuKiemKe() ***** ", new Object[0]);
     if (this.kiemke != null)
     {
       SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
       this.ngayKiem = sf.format(this.kiemke.getNgaykiemke());
       this.ngaygioCN = sf.format(this.kiemke.getNgaygiocn());
       DmLoaiThuoc dmLT = this.kiemke.getDmloaithuocMaso();
       if (dmLT != null)
       {
         if (dmLT.getDmloaithuocTen() != null) {
           setTenLoaiThuoc(dmLT.getDmloaithuocTen());
         } else {
           setTenLoaiThuoc("*All*");
         }
       }
       else {
         setTenLoaiThuoc("*All*");
       }
       KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
       List<KiemKePhanLoaiThuoc> listKKPLThuoc = kiemkeDelegate.getListKiemKePhanLoaiThuoc(this.kiemke.getMaphieukiem());
       this.listDmPLThuocKK.clear();
       if (listKKPLThuoc.size() > 0) {
         for (KiemKePhanLoaiThuoc item : listKKPLThuoc) {
           if (item.getDmphanloaithuocMaso() != null)
           {
             System.out.println("Phan loai thuoc: " + item.getDmphanloaithuocMaso().getDmphanloaithuocMa());
             this.listDmPLThuocKK.add(item.getDmphanloaithuocMaso());
           }
         }
       }
       setNhanvienKiemKe(this.kiemke.getDtdmnhanvienKiemke().getDtdmnhanvienTen());
       String tt = this.kiemke.getTrangthai();
       if (tt.equals("OPEN")) {
         setTrangthaiKK("Đang kiểm kê");
       } else {
         setTrangthaiKK("Đã hoàn tất");
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
     return result;
   }

   public String huyKiemke()
   {
     KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
     if ((this.kiemke.getDmloaithuocMaso() != null) &&
       (this.kiemke.getDmloaithuocMaso().getDmloaithuocMaso() == null)) {
       this.kiemke.setDmloaithuocMaso(null);
     }
     String result = kiemkeDel.huyKiemKe(this.kiemke);
     if (result.equals("SUCCESS"))
     {
       this.resetFormXemBangKiemKe = null;
       return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
     }
     FacesMessages.instance().add("Bạn không thể hủy được bảng kiểm kê này. " + result, new Object[] { this.kiemke.getMaphieukiem() });
     return "";
   }

   public void hoantatKiemke()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv == null)
     {
       FacesMessages.instance().add(this.identity.getUsername() + " không thuộc nhân viên của Bệnh viện", new Object[0]);
       return;
     }
     if ((this.kiemke.getDmloaithuocMaso() != null) &&
       (this.kiemke.getDmloaithuocMaso().getDmloaithuocMaso() == null)) {
       this.kiemke.setDmloaithuocMaso(null);
     }
     this.kiemke.setDtdmnhanvienCn(nv);
     this.kiemke.setNgaygiocn(new Date());
     this.kiemke.setTrangthai("CLOSE");
     KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
     String result = kiemkeDel.hoantatKiemKe(this.kiemke);
     if (result.equals("SUCCESS"))
     {
       setTrangthaiKK("Đã hoàn tất");
       FacesMessages.instance().add("Đã hoàn thành kiểm kê.", new Object[] { this.kiemke.getMaphieukiem() });
     }
     else
     {
       FacesMessages.instance().add("Bạn không thể hoàn tất được bảng kiểm kê này. " + result, new Object[] { this.kiemke.getMaphieukiem() });
     }
   }

   public String capnhatsolieuGUI()
   {
     this.kiemkeOut = this.kiemke;
     this.kiemkeKhoOut = null;
     this.resetFormXemBangKiemKe = null;
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
   }

   public String capnhatsolieuFile()
   {
     this.kiemkeOut = this.kiemke;
     this.kiemkeKhoOut = null;
     this.resetFormXemBangKiemKe = null;
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeFile";
   }

   public String selectKiemKeKho(int index)
   {
     this.kiemkeKhoOut = ((KiemKeKho)this.items.get(index));
     this.items = null;
     this.resetFormXemBangKiemKe = null;
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTeGUI";
   }

   public String xuatSolieu()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     DmLoaiThuoc dmLT = new DmLoaiThuoc();
     dmLT = this.kiemke.getDmloaithuocMaso();
     this.params = new HashMap();
     this.params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
     this.params.put("NGAYKIEM", this.kiemke.getNgaykiemke());
     this.params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
     this.params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
     this.params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
     try
     {
       Date dateTemp = this.kiemke.getNgaykiemke();
       Calendar calTemp = Calendar.getInstance();
       calTemp.setTime(dateTemp);
       calTemp.add(5, -1);
       dateTemp = calTemp.getTime();
       this.params.put("TINHDENNGAY", dateTemp);
     }
     catch (Exception ex) {}
     String temp = null;
     String listPl = "(";
     this.khoa_ma = this.dmKhoaKhoOut.getDmkhoaMa();
     this.log.info("khoa_ma " + this.khoa_ma, new Object[0]);
     String loaihang_ma = "";
     if (dmLT != null)
     {
       loaihang_ma = dmLT.getDmloaithuocMa();
       this.params.put("LOAITHUOC", dmLT.getDmloaithuocTen());
       this.params.put("LTHUOCMASO", dmLT.getDmloaithuocMaso());
     }
     this.log.info("loai hang " + loaihang_ma, new Object[0]);
     this.log.info("phan loai " + this.listDmPLThuocKK.size(), new Object[0]);
     this.log.info("khoa " + this.khoa_ma, new Object[0]);
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     this.params.put("Kho", this.dmKhoaKhoOut.getDmkhoaTen());
     this.params.put("KHOAMASO", this.dmKhoaKhoOut.getDmkhoaMaso());
     if (this.listDmPLThuocKK.size() > 0)
     {
       temp = "";
       List<String> listtemp = new ArrayList();
       for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
         listtemp.add(item.getDmphanloaithuocMa());
       }
       this.params.put("PLTHUOCMASO", getListDVMaParamsHelp(listtemp));
       listPl = listPl + getListDVMaParamsHelp(listtemp) + ")";
       this.log.info("list phan loai " + listPl, new Object[0]);
     }
     else
     {
       temp = null;
       listPl = "('')";
       this.params.put("PLTHUOCMASO", null);
     }
     this.params.put("MaPhieuKiem", this.kiemke.getMaphieukiem());
     this.params.put("NGAYGIOKIEMKE", this.kiemke.getNgaykiemke());
     this.reportTypeKC = "Inbangkiemke";
     this.log.info("Vao Method XuatReport cap nhat bang kiem ke", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Capnhatbangkiemke");
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
     return "B4160_Chonmenuxuattaptin";
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public String getTenLoaiThuoc()
   {
     return this.tenLoaiThuoc;
   }

   public void setTenLoaiThuoc(String tenLoaiThuoc)
   {
     this.tenLoaiThuoc = tenLoaiThuoc;
   }

   public String getNhanvienKiemKe()
   {
     return this.nhanvienKiemKe;
   }

   public void setNhanvienKiemKe(String nhanvienKiemKe)
   {
     this.nhanvienKiemKe = nhanvienKiemKe;
   }

   public String getNgayKiem()
   {
     return this.ngayKiem;
   }

   public void setNgayKiem(String ngayKiem)
   {
     this.ngayKiem = ngayKiem;
   }

   public String getNgaygioCN()
   {
     return this.ngaygioCN;
   }

   public void setNgaygioCN(String ngaygioCN)
   {
     this.ngaygioCN = ngaygioCN;
   }

   public String getTrangthaiKK()
   {
     return this.trangthaiKK;
   }

   public void setTrangthaiKK(String trangthaiKK)
   {
     this.trangthaiKK = trangthaiKK;
   }

   public String getTrangthaitt()
   {
     return this.trangthaitt;
   }

   public void setTrangthaitt(String trangthaitt)
   {
     this.trangthaitt = trangthaitt;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public KiemKe getKiemke()
   {
     return this.kiemke;
   }

   public void setKiemke(KiemKe kiemke)
   {
     this.kiemke = kiemke;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XemBangKiemKe

 * JD-Core Version:    0.7.0.1

 */