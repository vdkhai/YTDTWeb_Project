 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeKhoDelegate;
 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4144b_CapNhatSoLieuThucTe")
 @Synchronized(timeout=6000000L)
 public class B4144b_CapNhatSoLieuThucTe
   implements Serializable
 {
   private static final long serialVersionUID = -340255448614143263L;
   private static Logger log = Logger.getLogger(B4144b_CapNhatSoLieuThucTe.class);
   private String khoa;
   private String thuoc;
   private String quocgia;
   private String nsx;
   private String namnhap;
   private String nct;
   private String nkp;
   private String ngayhd;
   private String ngaykiem;
   private String maKiemKe;
   private String soDangKy;
   private String donVi;
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private String plthuoc_ma = "";
   private List<KiemKeKho> listKiemKeKho = new ArrayList();
   private int index = 0;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   Map<String, Object> params = null;

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuocMaso)
   {
     this.plthuoc_maso = plthuocMaso;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuocMa)
   {
     this.plthuoc_ma = plthuocMa;
   }

   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList();
   @DataModelSelection("listDmPLThuocKK")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelectKK;

   public List<DmPhanLoaiThuoc> getListDmPLThuocKK()
   {
     return this.listDmPLThuocKK;
   }

   public void setListDmPLThuocKK(List<DmPhanLoaiThuoc> listDmPLThuocKK)
   {
     this.listDmPLThuocKK = listDmPLThuocKK;
   }

   public DmPhanLoaiThuoc getDmPLThuocSelectKK()
   {
     return this.dmPLThuocSelectKK;
   }

   public void setDmPLThuocSelectKK(DmPhanLoaiThuoc dmPLThuocSelectKK)
   {
     this.dmPLThuocSelectKK = dmPLThuocSelectKK;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihangMaso)
   {
     this.loaihang_maso = loaihangMaso;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihangMa)
   {
     this.loaihang_ma = loaihangMa;
   }

   private String loaihang_ma = "";
   private String donGia;
   @DataModel
   private List<KiemKeKho> listB4144b;

   public String getMaKiemKe()
   {
     return this.maKiemKe;
   }

   public void setMaKiemKe(String maKiemKe)
   {
     this.maKiemKe = maKiemKe;
   }

   public String getSoDangKy()
   {
     return this.soDangKy;
   }

   public void setSoDangKy(String soDangKy)
   {
     this.soDangKy = soDangKy;
   }

   public String getDonVi()
   {
     return this.donVi;
   }

   public void setDonVi(String donVi)
   {
     this.donVi = donVi;
   }

   public String getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(String donGia)
   {
     this.donGia = donGia;
   }

   String dmKhoXuat = "";

   @Begin(join=true)
   public String init(String kho)
     throws Exception
   {
     resetvalue();
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_KiemKeKhoChinh_CapNhatSoLieuKiemKeThucTe_old";
   }

   @End
   public void end() {}

   public void resetvalue()
   {
     log.info("---resetValue");
     this.thuoc = (this.quocgia = this.nsx = this.namnhap = this.nct = this.nkp = this.ngayhd = "");
     this.ngaykiem = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     this.listB4144b = new ArrayList();
     this.maKiemKe = "";
     this.listDmPLThuocKK.clear();
     this.plthuoc_ma = "";
     this.listKiemKeKho = new ArrayList();
   }

   public void search()
   {
     log.info("---search");
     this.listB4144b = new ArrayList();
     String ngay = "";String thang = "";String nam = "";
     if (!this.ngayhd.trim().equals(""))
     {
       String[] ntn = this.ngayhd.split("/");
       ngay = ntn[0];thang = ntn[1];nam = ntn[2];
     }
     List<KiemKeKho> list = new ArrayList();
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     boolean temp = true;
     for (int i = 0; i < this.listKiemKeKho.size(); i++)
     {
       temp = false;
       KiemKeKho kkk = (KiemKeKho)this.listKiemKeKho.get(i);
       DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso(), "DmThuoc", "dmthuocMaso");
       DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso(), "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
       if ((this.khoa.equals("")) || (kkk.getDmkhoaMaso().getDmkhoaMa().equals(this.khoa))) {
         if ((this.nct.equals("")) || (kkk.getDmnctMaso().getDmnctMa().equals(this.nct))) {
           if ((this.nkp.equals("")) || (kkk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa().equals(this.nkp))) {
             if ((this.nsx.equals("")) || (kkk.getDmnhasanxuatMaso().getDmnhasanxuatMa().equals(this.nsx))) {
               if ((this.quocgia.equals("")) || (kkk.getDmquocgiaMaso().getDmquocgiaMa().equals(this.quocgia))) {
                 if ((this.namnhap.equals("")) || (kkk.getKiemkeNamnhap().equals(this.namnhap))) {
                   if ((ngay.equals("")) || (kkk.getKiemkeNgayhandung().equals(ngay))) {
                     if ((thang.equals("")) || (kkk.getKiemkeThanghandung().equals(thang))) {
                       if ((nam.equals("")) || (kkk.getKiemkeNamhandung().equals(nam))) {
                         if ((this.loaihang_ma.equals("")) || (dmLoaiThuoc.getDmloaithuocMa().equals(this.loaihang_ma))) {
                           if (this.listDmPLThuocKK.size() > 0) {
                             for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
                               if (item.getDmphanloaithuocMa().equals(dmPhanLoaiThuoc.getDmphanloaithuocMa())) {
                                 list.add(kkk);
                               }
                             }
                           } else {
                             list.add(kkk);
                           }
                         }
                       }
                     }
                   }
                 }
               }
             }
           }
         }
       }
     }
     if ((list != null) && (list.size() > 0)) {
       this.listB4144b = list;
     } else {
       FacesMessages.instance().add(IConstantsRes.KKK_NULL, new Object[0]);
     }
   }

   public void ghinhan()
   {
     log.info("---ghinhan");
     Date now = new Date();
     KiemKeKhoDelegate delegate = KiemKeKhoDelegate.getInstance();
     boolean result = true;
     for (KiemKeKho obj : this.listB4144b) {
       if (obj.getKiemkeTontt() != null)
       {
         obj.setKiemkeNgaykiem(now);
         obj.setKiemkeNgaygiocn(now);

         String malk = delegate.updateAndEditTonKhoDau(obj);
         if (malk.equals(""))
         {
           result = false;
           break;
         }
       }
     }
     result = true;
     if (result) {
       FacesMessages.instance().add(IConstantsRes.KKK_CN_THANHCONG, new Object[0]);
     } else {
       FacesMessages.instance().add(IConstantsRes.KKK_CN_THATBAI, new Object[0]);
     }
   }

   public void enter()
   {
     log.info("bat dau them du lieu vao luoi");

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.plthuoc_ma.equals("")) {
       if ((this.listDmPLThuocKK.size() == 0) && (this.plthuoc_ma != null))
       {
         log.info("size list bang 0");
         Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
         this.listDmPLThuocKK.add(plThuoc);
         log.info("da add phan tu dau tien" + this.listDmPLThuocKK.size());
       }
       else if ((this.listDmPLThuocKK.size() > 0) && (this.plthuoc_ma != null))
       {
         log.info("size list lon hon 0");
         for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
           if (item.getDmphanloaithuocMa().equals(this.plthuoc_ma)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
           DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
           this.listDmPLThuocKK.add(plThuoc);
         }
         log.info("da add phan tu" + this.listDmPLThuocKK.size());
       }
     }
     setPlthuoc_ma("");
   }

   public void deletedmPLThuoc()
   {
     log.info("bat dau xoa , size" + this.listDmPLThuocKK.size());
     this.listDmPLThuocKK.remove(this.dmPLThuocSelectKK);
     log.info("da xoa , size" + this.listDmPLThuocKK.size());
     log.info("ket thuc xoa");
   }

   public void resetList()
   {
     log.info("=============reset listttttttttttt " + this.listDmPLThuocKK.size());
     if (this.listDmPLThuocKK.size() > 0)
     {
       DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
       dmplt = (DmPhanLoaiThuoc)this.listDmPLThuocKK.get(0);
       log.info(dmplt);
       if (!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(this.loaihang_ma))
       {
         this.listDmPLThuocKK.clear();
         setPlthuoc_ma("");
       }
     }
   }

   public void displayPhieuKiemKe()
   {
     log.info(" ***** displayPhieuKiemKe() ***** ");
     this.listB4144b = new ArrayList();
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     if (!this.maKiemKe.equals(""))
     {
       log.info(String.format(" ***** Phieu kiem ke ma: %s", new Object[] { this.maKiemKe }));
       try
       {
         KiemKeKhoDelegate kkkDel = KiemKeKhoDelegate.getInstance();
         List<KiemKeKho> listKkk = kkkDel.findByMaPhieuKiem(this.maKiemKe);
         if ((listKkk != null) && (listKkk.size() > 0))
         {
           this.listB4144b = listKkk;
           for (int i = 0; i < this.listB4144b.size(); i++) {
             ((KiemKeKho)this.listB4144b.get(i)).setKiemkeTontt(((KiemKeKho)this.listB4144b.get(i)).getKiemkeTon());
           }
           KiemKeKho kkk = (KiemKeKho)listKkk.get(0);
           DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
           DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso(), "DmThuoc", "dmthuocMaso");
           DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso(), "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
           DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
           this.loaihang_ma = dmLoaiThuoc.getDmloaithuocMa();
           this.ngaykiem = sdf.format(kkk.getKiemkeNgaygiocn());
           this.plthuoc_ma = "";
           this.quocgia = "";
           this.nsx = "";
           this.nct = "";
           this.nkp = "";
           this.ngayhd = "";
           this.soDangKy = "";
           this.donVi = "";
           this.donGia = "";
           this.listDmPLThuocKK.clear();
           this.listKiemKeKho = this.listB4144b;
         }
         else
         {
           FacesMessages.instance().add("Không tìm thấy phiếu kiểm kê ", new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add("Xảy ra lỗi ", new Object[] { this.maKiemKe });
         log.error(String.format(" ***** Error: %s", new Object[] { e }));
       }
     }
   }

   public void selectCt(Integer index)
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     KiemKeKho kkk = (KiemKeKho)this.listB4144b.get(index.intValue());
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     DmThuoc dmThuoc = (DmThuoc)dtutilDelegate.findByMaSo(kkk.getDmthuocMaso().getDmthuocMaso(), "DmThuoc", "dmthuocMaso");
     DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)dtutilDelegate.findByMaSo(dmThuoc.getDmphanloaithuocMaso().getDmphanloaithuocMaso(), "DmPhanLoaiThuoc", "dmphanloaithuocMaso");
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)dtutilDelegate.findByMaSo(dmPhanLoaiThuoc.getDmloaithuocMaso().getDmloaithuocMaso(), "DmLoaiThuoc", "dmloaithuocMaso");
     this.loaihang_ma = dmLoaiThuoc.getDmloaithuocMa();
     this.listDmPLThuocKK.clear();
     this.listDmPLThuocKK.add(dmPhanLoaiThuoc);
     this.ngaykiem = sdf.format(kkk.getKiemkeNgaygiocn());
     this.quocgia = kkk.getDmquocgiaMaso().getDmquocgiaMa();
     this.nsx = kkk.getDmnhasanxuatMaso().getDmnhasanxuatMa();
     this.nct = kkk.getDmnctMaso().getDmnctMa();
     this.nkp = kkk.getDmnguonkinhphiMaso().getDmnguonkinhphiMa();
     this.ngayhd = ((kkk.getKiemkeNgayhandung() == null ? "" : new StringBuilder().append(kkk.getKiemkeNgayhandung()).append("/").toString()) + (kkk.getKiemkeThanghandung() == null ? "" : new StringBuilder().append(kkk.getKiemkeThanghandung()).append("/").toString()) + (kkk.getKiemkeNamhandung() == null ? "" : kkk.getKiemkeNamhandung()));
     this.soDangKy = kkk.getKiemkeSodangky();
     this.donVi = dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa();
     this.donGia = (kkk.getKiemkeDongia() + "");
     this.plthuoc_ma = "";
   }

   public String XuatReport()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     this.params = new HashMap();
     this.params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
     this.params.put("NGAYKIEM", this.ngaykiem);
     this.params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
     this.params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
     this.params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
     try
     {
       Date dateTemp = sdf.parse(this.ngaykiem);
       Calendar calTemp = Calendar.getInstance();
       calTemp.setTime(dateTemp);
       calTemp.add(5, -1);
       dateTemp = calTemp.getTime();
       this.params.put("TINHDENNGAY", dateTemp);
     }
     catch (Exception ex) {}
     String temp = null;
     String listPl = "(";
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (this.khoa.trim().equals(""))
     {
       setKhoa(null);
     }
     else
     {
       DmKhoa dmKhoa = (DmKhoa)dtutilDelegate.findByMa(this.khoa, "DmKhoa", "dmkhoaMa");
       this.params.put("Kho", dmKhoa.getDmkhoaTen());
       this.params.put("KHOAMASO", dmKhoa.getDmkhoaMaso());
     }
     this.params.put("MaPhieuKiem", this.maKiemKe);
     this.reportTypeKC = "Capnhatbangkiemke";
     log.info("Vao Method XuatReport cap nhat bang kiem ke");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       log.info("bat dau method XuatReport ");
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";

       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, this.params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Capnhatbangkiemke");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathKC);
       this.index += 1;
       log.info("Index :" + this.index);
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
     return "B4160_Chonmenuxuattaptin";
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     log.info("Vao method getListDVMaParamsHelp ");
     log.info("Inputs: " + inputs.toString());
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "',";
       }
     }
     result = result.substring(0, result.length() - 1);
     log.info("Thoat method getListDVMaParamsHelp ");
     log.info("Output: " + result);
     return result;
   }

   public String getKhoa()
   {
     return this.khoa;
   }

   public void setKhoa(String khoa)
   {
     this.khoa = khoa;
   }

   public String getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(String thuoc)
   {
     this.thuoc = thuoc;
   }

   public String getQuocgia()
   {
     return this.quocgia;
   }

   public void setQuocgia(String quocgia)
   {
     this.quocgia = quocgia;
   }

   public String getNsx()
   {
     return this.nsx;
   }

   public void setNsx(String nsx)
   {
     this.nsx = nsx;
   }

   public String getNamnhap()
   {
     return this.namnhap;
   }

   public void setNamnhap(String namnhap)
   {
     this.namnhap = namnhap;
   }

   public String getNct()
   {
     return this.nct;
   }

   public void setNct(String nct)
   {
     this.nct = nct;
   }

   public String getNkp()
   {
     return this.nkp;
   }

   public void setNkp(String nkp)
   {
     this.nkp = nkp;
   }

   public String getNgayhd()
   {
     return this.ngayhd;
   }

   public void setNgayhd(String ngayhd)
   {
     this.ngayhd = ngayhd;
   }

   public String getNgaykiem()
   {
     return this.ngaykiem;
   }

   public void setNgaykiem(String ngaykiem)
   {
     this.ngaykiem = ngaykiem;
   }

   public List<KiemKeKho> getListB4144b()
   {
     return this.listB4144b;
   }

   public void setListB4144b(List<KiemKeKho> listB4144b)
   {
     this.listB4144b = listB4144b;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public static long getSerialVersionUID()
   {
     return -340255448614143263L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B4144b_CapNhatSoLieuThucTe

 * JD-Core Version:    0.7.0.1

 */