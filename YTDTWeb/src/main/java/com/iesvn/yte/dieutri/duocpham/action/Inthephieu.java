 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TmpBaocaoThekho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4133_inthephieu")
 @Scope(ScopeType.CONVERSATION)
 public class Inthephieu
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
   private String resetFormB4133 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private String tonkhomalk;
   private String tonkhoma;
   private Integer thuocmaso;
   String dmKhoXuat = "";
   private String khoma = "";
   private Integer khomaso;
   private Integer loaihang_maso;
   private String loaihang_ma = null;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private String dmLoaiTen = "";
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   private DmThuocDelegate dmThuocDelegate;
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private String dmtTen = "";
   private List<SelectItem> listDmThuocs = new ArrayList();
   private String hang_ma = null;
   private List<SelectItem> listDmTonThuocs = new ArrayList();
   private String dmTonThuoc = "";

   public Integer getKhomaso()
   {
     return this.khomaso;
   }

   public void setKhomaso(Integer khomaso)
   {
     this.khomaso = khomaso;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4133")
   public void initresetFormB4133()
   {
     this.log.info("init()", new Object[0]);

     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     this.resetFormB4133 = "";
     this.ngayhientai = Utils.getCurrentDate();
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.tonkhomalk = "";
     this.tonkhoma = "";
     this.hang_ma = "";
     this.dmtTen = "";
     this.loaihang_ma = "";
     this.dmLoaiTen = "";
     FacesMessages.instance().clear();
     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();
     refreshDmLoaiThuoc();
     refreshDmThuoc();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     initresetFormB4133();
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     if (XuatReport()) {
       return "B4160_Chonmenuxuattaptin";
     }
     initresetFormB4133();
     FacesMessages.instance().add(IConstantsRes.DuocPham_TruyXuatTTKho_InTheKho, new Object[0]);
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
   }

   public void resetForm() {}

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getDmLoaiTen()
   {
     return this.dmLoaiTen;
   }

   public void setDmLoaiTen(String dmLoaiTen)
   {
     this.dmLoaiTen = dmLoaiTen;
   }

   public List<SelectItem> getListDmLoaiThuocs()
   {
     return this.listDmLoaiThuocs;
   }

   public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs)
   {
     this.listDmLoaiThuocs = listDmLoaiThuocs;
   }

   public void onblurMaLoaiAction()
   {
     this.log.info("-----BEGIN onblurMaLoaiAction()-----" + this.loaihang_ma, new Object[0]);
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(this.loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     refreshDmThuoc();
     this.log.info("-----END onblurMaLoaiAction()-----", new Object[0]);
   }

   public void onblurTenLoaiThuocAction()
   {
     this.log.info("-----BEGIN onblurTenLoaiThuocAction()-----", new Object[0]);
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maLoai = null;
     if (this.dmLoaiTen == "") {
       setLoaihang_ma("");
     }
     Set set = this.hmLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
       if ((dmLoaiThuoc.getDmloaithuocTen() == this.dmLoaiTen) || (dmLoaiThuoc.getDmloaithuocTen().equals(this.dmLoaiTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         maLoai = dmLoaiThuoc.getDmloaithuocMa();
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       setLoaihang_ma(maLoai);
     } else {
       setLoaihang_ma("");
     }
     setHang_ma("");
     refreshDmThuoc();
     this.log.info("-----END onblurTenLoaiThuocAction()-----", new Object[0]);
   }

   public void refreshDmLoaiThuoc()
   {
     this.listDmLoaiThuocs.clear();
     this.dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
     this.hmLoaiThuoc.clear();
     this.hmLoaiThuoc = this.dmLoaiThuocDelegate.findAllDm();
     if (this.hmLoaiThuoc != null)
     {
       Set set = this.hmLoaiThuoc.entrySet();
       Iterator i = set.iterator();
       this.listDmLoaiThuocs.add(new SelectItem("All"));
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
         this.listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
       }
     }
   }

   public String getDmtTen()
   {
     return this.dmtTen;
   }

   public void setDmtTen(String dmtTen)
   {
     this.dmtTen = dmtTen;
   }

   public List<SelectItem> getListDmThuocs()
   {
     return this.listDmThuocs;
   }

   public void setListDmThuocs(List<SelectItem> listDmThuocs)
   {
     this.listDmThuocs = listDmThuocs;
   }

   public void onblurMaThuocAction()
   {
     this.log.info("-----BEGIN onblurMaThuocAction()-----", new Object[0]);
     DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.hang_ma.toUpperCase());
     if (dmThuoc != null)
     {
       setDmtTen(dmThuoc.getDmthuocTen());
       setThuocmaso(dmThuoc.getDmthuocMaso());
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setDmtTen("");
       setThuocmaso(null);
     }
     this.log.info("-----END onblurMaThuocAction()-----", new Object[0]);
   }

   public void onblurTenThuocAction()
   {
     this.log.info("-----BEGIN onblurTenThuocAction()-----" + this.dmtTen, new Object[0]);
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = null;
     Integer masoThuoc = null;
     if (this.hmDmThuoc != null)
     {
       Set set = this.hmDmThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmThuoc dmThuoc = (DmThuoc)me.getValue();
         if ((dmThuoc.getDmthuocTen().trim() == this.dmtTen.trim()) || (dmThuoc.getDmthuocTen().trim().equals(this.dmtTen.trim())))
         {
           hasTenThuoc = Boolean.valueOf(true);
           maThuoc = dmThuoc.getDmthuocMa();
           masoThuoc = dmThuoc.getDmthuocMaso();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setHang_ma(maThuoc);
       setThuocmaso(masoThuoc);
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setHang_ma("");
       setThuocmaso(null);
     }
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     List<DmThuoc> lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuoc(this.loaihang_ma, "");
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   public String getHang_ma()
   {
     return this.hang_ma;
   }

   public void setHang_ma(String hang_ma)
   {
     this.hang_ma = hang_ma;
   }

   public boolean XuatReport()
   {
     if ((this.tonkhoma == null) || (this.tonkhoma.equals(""))) {
       return false;
     }
     this.reportTypeKC = "Inthephieu";
     this.log.info("Vao Method XuatReport in the kho", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);

       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       String pathTemplate = "";
       String loaiIn = "";
       if (IConstantsRes.KHOA_KC_MA.equals(this.khoma))
       {
         loaiIn = "KC";
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D06_sochitietvatlieudungcusanphamhanghoa_kc.jrxml";
       }
       else
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D06_sochitietvatlieudungcusanphamhanghoa.jrxml";
         params.put("khomaso", this.khomaso);
         loaiIn = "KL";
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       TonKho tonKho = tkDelegate.find(new Integer(this.tonkhoma));
       if (tonKho == null) {
         return false;
       }
       DieuTriUtilDelegate dtUtilsDeledate = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dtUtilsDeledate.findByMa(this.khoma, "DmKhoa", "dmkhoaMa");


       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);

       SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
       params.put("tenkho", khoa.getDmkhoaTen());

       Date dTuNgay = sdf.parse(this.tungay);
       Date dDenNgay = sdf.parse(this.denngay);

       params.put("nam", String.valueOf(dTuNgay.getYear()));


       params.put("tenhang", tonKho.getDmthuocMaso(true).getDmthuocTen());
       params.put("donvitinh", tonKho.getDmthuocMaso(true).getDmdonvitinhMaso(true).getDmdonvitinhTen());
       params.put("phanloai", tonKho.getDmthuocMaso(true).getDmphanloaithuocMaso(true).getDmphanloaithuocTen());
       params.put("nguonchuongtrinh", tonKho.getDmnctMaso(true).getDmnctTen());
       params.put("nguonkinhphi", tonKho.getDmnguonkinhphiMaso(true).getDmnguonkinhphiTen());
       params.put("losanxuat", tonKho.getTonkhoLo());
       params.put("kyhieu", tonKho.getDmthuocMaso(true).getDmthuocMa());

       params.put("nuocsanxuat", tonKho.getDmquocgiaMaso(true).getDmquocgiaTen());
       params.put("namnhap", tonKho.getTonkhoNamnhap());
       params.put("dongia", tonKho.getTonkhoDongia());


       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Double tonkhoDau = Double.valueOf(0.0D);
       List<TmpBaocaoThekho> dataTheKho = new ArrayList();
       DieuTriUtilDelegate dtriUtilDel = DieuTriUtilDelegate.getInstance();
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi)))
       {
         params.put("tungay", dNgayTonDau);
         tonkhoDau = tkDelegate.findByTonkhoDauMalienket(this.tonkhomalk, khoa.getDmkhoaMaso(), dNgayTonDau, dDenNgay);
         Double tonDau = Double.valueOf(0.0D);
         if (tonkhoDau != null) {
           tonDau = tonkhoDau;
         }
         dtriUtilDel.exportDataForTheKho(khoa.getDmkhoaMaso(), dNgayTonDau, dDenNgay, this.tonkhomalk, tonDau, tonKho.getTonkhoDongia(), loaiIn);
       }
       else
       {
         params.put("tungay", dTuNgay);
         tonkhoDau = tkDelegate.findByTonkhoDauMalienket(this.tonkhomalk, khoa.getDmkhoaMaso(), dTuNgay, dDenNgay);
         Double tonDau = Double.valueOf(0.0D);
         if (tonkhoDau != null) {
           tonDau = tonkhoDau;
         }
         dtriUtilDel.exportDataForTheKho(khoa.getDmkhoaMaso(), dTuNgay, dDenNgay, this.tonkhomalk, tonDau, tonKho.getTonkhoDongia(), loaiIn);
       }
       params.put("denngay", dDenNgay);
       if (tonkhoDau != null) {
         params.put("tonkhobandau", tonkhoDau);
       } else {
         params.put("tonkhobandau", Integer.valueOf(0));
       }
       params.put("malienket", this.tonkhomalk);

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Inthekho");
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
       return false;
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
     return true;
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

   public String getTonkhomalk()
   {
     return this.tonkhomalk;
   }

   public void setTonkhomalk(String tonkhomalk)
   {
     this.tonkhomalk = tonkhomalk;
   }

   public String getTonkhoma()
   {
     return this.tonkhoma;
   }

   public void setTonkhoma(String tonkhoma)
   {
     this.tonkhoma = tonkhoma;
   }

   public String getKhoma()
   {
     return this.khoma;
   }

   public void setKhoma(String khoma)
   {
     this.khoma = khoma;
   }

   public Integer getThuocmaso()
   {
     return this.thuocmaso;
   }

   public void setThuocmaso(Integer thuocmaso)
   {
     this.thuocmaso = thuocmaso;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inthephieu

 * JD-Core Version:    0.7.0.1

 */