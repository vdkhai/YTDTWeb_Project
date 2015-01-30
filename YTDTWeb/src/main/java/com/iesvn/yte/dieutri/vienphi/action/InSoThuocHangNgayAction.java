 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3125_Insothuochangngay")
 @Synchronized(timeout=6000000L)
 public class InSoThuocHangNgayAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(InSoThuocHangNgayAction.class);
   private Integer khoaMaSo;
   private String khoaMa;
   private String ngaySuDung;
   private Boolean thuoc;
   private Boolean vatTuTieuHao;
   private Boolean thuocthuongtv;
   private Boolean thuocthuongtk;
   private Boolean thuocdoc;
   private Boolean gaynghien;
   private Boolean huongthan;
   private Boolean dongy;
   private Boolean hoachat;
   private String tuTrucPhieuDuTru = "pdt";
   private int index;
   private DmKhoa dmKhoa;
   private DmTang dmTang;
   private List<SelectItem> listDmTangs = new ArrayList();
   private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap();
   private List<SelectItem> listDmKhoaNTs = new ArrayList();
   private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
   private List<DmKhoa> listDmKhoaNTAll = new ArrayList();
   private String ngayHienTai;

   @Begin(join=true)
   public String init()
   {
     log.info("init()");
     resetForm();
     refreshDmKhoaNT();
     return "VienPhiTaiKhoa_XemInPhieu_InSoThuoc";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;

   public String getLoaiThuoc()
   {
     String tmp = ",";
     if (this.thuocthuongtv.booleanValue()) {
       tmp = tmp + "1,";
     }
     if (this.thuocthuongtk.booleanValue()) {
       tmp = tmp + "2,";
     }
     if (this.thuocdoc.booleanValue()) {
       tmp = tmp + "3,";
     }
     if (this.gaynghien.booleanValue()) {
       tmp = tmp + "4,";
     }
     if (this.huongthan.booleanValue()) {
       tmp = tmp + "5,";
     }
     if (this.vatTuTieuHao.booleanValue()) {
       tmp = tmp + "6,";
     }
     if (this.dongy.booleanValue()) {
       tmp = tmp + "7,";
     }
     if (this.hoachat.booleanValue()) {
       tmp = tmp + "8,";
     }
     if (tmp.length() > 1) {
       return tmp.substring(1, tmp.length() - 1);
     }
     return "0";
   }

   public void onblurMaKhoaAction()
   {
     log.info("-----BEGIN onblurMaKhoaAction()-----");
     if ((this.dmKhoa != null) && (this.dmKhoa.getDmkhoaMa() != null))
     {
       String maKhoa = this.dmKhoa.getDmkhoaMa();
       if (this.hmDmKhoaNTAll != null)
       {
         DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNTAll.get(maKhoa.toUpperCase());
         if (dmKhoa != null)
         {
           this.dmKhoa.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
           this.dmKhoa.setDmkhoaMa(dmKhoa.getDmkhoaMa());
           this.dmKhoa.setDmkhoaTen(dmKhoa.getDmkhoaTen());
           log.info("Tim ma khoa: Da thay khoa " + dmKhoa.getDmkhoaTen());
         }
         else
         {
           this.dmKhoa = new DmKhoa();
           return;
         }
       }
       this.dmTang = new DmTang();
       refreshDmTang();
     }
     log.info("-----END onblurMaKhoaAction()-----");
   }

   public void refreshDmTang()
   {
     this.listDmTangs = new ArrayList();
     if ((this.dmKhoa != null) && (this.dmKhoa.getDmkhoaMaso() != null))
     {
       String khoaMa = this.dmKhoa.getDmkhoaMa();
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();


       List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
       if ((lstDmTangs != null) && (lstDmTangs.size() > 0))
       {
         for (DmTang dmTang : lstDmTangs) {
           this.listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
         }
         for (DmTang dmTang : lstDmTangs) {
           if (dmTang.getDmtangDefault().booleanValue() == true)
           {
             this.dmTang = dmTang;
             break;
           }
         }
       }
     }
   }

   public void refreshDmKhoaNT()
   {
     this.dmKhoaDel = DmKhoaDelegate.getInstance();
     this.listDmKhoaNTAll.clear();
     this.listDmKhoaNTs.clear();
     this.listDmKhoaNTAll = this.dmKhoaDel.getKhoaNT();
     this.hmDmKhoaNTAll.clear();
     for (DmKhoa o : this.listDmKhoaNTAll) {
       this.hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
     }
     for (DmKhoa each : this.listDmKhoaNTAll) {
       this.listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
     }
   }

   public void onblurTenKhoaAction()
   {
     log.info("-----BEGIN onblurTenKhoaAction()-----");
     if ((this.dmKhoa != null) && (this.dmKhoa.getDmkhoaTen() != null))
     {
       String tenKhoa = this.dmKhoa.getDmkhoaTen();
       Boolean hasTenKhoa = Boolean.valueOf(false);
       Set set = this.hmDmKhoaNTAll.entrySet();
       Iterator i = set.iterator();
       DmKhoa dmKhoa_Finded = new DmKhoa();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == tenKhoa) || (dmKhoa.getDmkhoaTen().equals(tenKhoa)))
         {
           hasTenKhoa = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
       if (hasTenKhoa.booleanValue())
       {
         this.dmKhoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
         this.dmKhoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
         this.dmKhoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
       }
       else
       {
         this.dmKhoa = new DmKhoa();
         return;
       }
       this.dmTang = new DmTang();
       refreshDmTang();
     }
     log.info("-----END onblurTenKhoaAction()-----");
   }

   public void onblurTenTangAction()
   {
     if ((this.dmTang != null) && (this.dmTang.getDmtangTen() != null))
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", this.dmTang.getDmtangTen());
       if ((lstTangs != null) && (lstTangs.size() > 0))
       {
         this.dmTang = ((DmTang)lstTangs.get(0));
         log.info(" onchange " + this.dmTang.getDmtangTen());
       }
       else
       {
         this.dmTang = new DmTang();
       }
     }
   }

   public void resetForm()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayHienTai = formatter.format(new Date());
     this.khoaMa = "";
     this.ngaySuDung = this.ngayHienTai;
     this.thuocthuongtv = Boolean.valueOf(true);
     this.thuocthuongtk = Boolean.valueOf(true);
     this.vatTuTieuHao = Boolean.valueOf(false);
     this.thuocdoc = Boolean.valueOf(false);
     this.gaynghien = Boolean.valueOf(false);
     this.huongthan = Boolean.valueOf(false);
     this.dongy = Boolean.valueOf(false);
     this.hoachat = Boolean.valueOf(false);
     this.tuTrucPhieuDuTru = "pdt";
     this.dmTang = new DmTang();
     this.dmKhoa = new DmKhoa();
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public String XuatReport()
   {
     String baocao1 = null;
     this.reportTypeVP = "B3125_Phieulinhthuoc";
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/Insothuoc.jrxml";


       log.info("da thay file templete  " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
       Date dNgaySuDung = formatter.parse(this.ngaySuDung);
       params.put("ngaySuDung", dNgaySuDung);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("makhoa", this.dmKhoa.getDmkhoaMaso());
       if (this.dmTang != null)
       {
         params.put("matang", this.dmTang.getDmtangMaso());
         log.info("khoaTANG:" + this.dmTang.getDmtangMaso());
       }
       if (this.tuTrucPhieuDuTru != null)
       {
         if (this.tuTrucPhieuDuTru.equals("tt")) {
           params.put("ttorpdt", Boolean.valueOf(true));
         } else {
           params.put("ttorpdt", Boolean.valueOf(false));
         }
       }
       else {
         params.put("ttorpdt", Boolean.valueOf(false));
       }
       params.put("LOAITHUOC", getLoaiThuoc());

       log.info("------------------------------------------------------------------");
       log.info("ngaySuDung:" + this.ngaySuDung);
       log.info("khoaMa:" + this.dmKhoa.getDmkhoaMaso());
       log.info("ttorPhieuDuTru:" + this.tuTrucPhieuDuTru);
       log.info("loaithuoc:" + getLoaiThuoc());

       log.info("------------------------------------------------------------------");


       this.reportTypeVP = "B3125_Phieulinhthuoc";
       log.info("Vao Method XuatReport B3125_Phieulinhthuoc");
       log.info("================= ");

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       log.debug(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       log.debug(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       log.debug(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));

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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3125_Phieulinhthuoc");



       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + this.index);
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
     return "B3360_Chonmenuxuattaptin";
   }

   @End
   public void end() {}

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public Integer getKhoaMaSo()
   {
     return this.khoaMaSo;
   }

   public void setKhoaMaSo(Integer khoaMaSo)
   {
     this.khoaMaSo = khoaMaSo;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getNgaySuDung()
   {
     return this.ngaySuDung;
   }

   public void setNgaySuDung(String ngaySuDung)
   {
     this.ngaySuDung = ngaySuDung;
   }

   public Boolean getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(Boolean thuoc)
   {
     this.thuoc = thuoc;
   }

   public Boolean getVatTuTieuHao()
   {
     return this.vatTuTieuHao;
   }

   public void setVatTuTieuHao(Boolean vatTuTieuHao)
   {
     this.vatTuTieuHao = vatTuTieuHao;
   }

   public String getTuTrucPhieuDuTru()
   {
     return this.tuTrucPhieuDuTru;
   }

   public void setTuTrucPhieuDuTru(String tuTrucPhieuDuTru)
   {
     this.tuTrucPhieuDuTru = tuTrucPhieuDuTru;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public Boolean getDongy()
   {
     return this.dongy;
   }

   public void setDongy(Boolean dongy)
   {
     this.dongy = dongy;
   }

   public Boolean getGaynghien()
   {
     return this.gaynghien;
   }

   public void setGaynghien(Boolean gaynghien)
   {
     this.gaynghien = gaynghien;
   }

   public Boolean getHoachat()
   {
     return this.hoachat;
   }

   public void setHoachat(Boolean hoachat)
   {
     this.hoachat = hoachat;
   }

   public Boolean getHuongthan()
   {
     return this.huongthan;
   }

   public void setHuongthan(Boolean huongthan)
   {
     this.huongthan = huongthan;
   }

   public Boolean getThuocdoc()
   {
     return this.thuocdoc;
   }

   public void setThuocdoc(Boolean thuocdoc)
   {
     this.thuocdoc = thuocdoc;
   }

   public Boolean getThuocthuongtk()
   {
     return this.thuocthuongtk;
   }

   public void setThuocthuongtk(Boolean thuocthuongtk)
   {
     this.thuocthuongtk = thuocthuongtk;
   }

   public Boolean getThuocthuongtv()
   {
     return this.thuocthuongtv;
   }

   public void setThuocthuongtv(Boolean thuocthuongtv)
   {
     this.thuocthuongtv = thuocthuongtv;
   }

   public DmKhoa getDmKhoa()
   {
     return this.dmKhoa;
   }

   public void setDmKhoa(DmKhoa dmKhoa)
   {
     this.dmKhoa = dmKhoa;
   }

   public List<SelectItem> getListDmTangs()
   {
     return this.listDmTangs;
   }

   public void setListDmTangs(List<SelectItem> listDmTangs)
   {
     this.listDmTangs = listDmTangs;
   }

   public DmTang getDmTang()
   {
     return this.dmTang;
   }

   public void setDmTang(DmTang dmTang)
   {
     this.dmTang = dmTang;
   }

   public HashMap<String, DmKhoa> getHmDmKhoaNTAll()
   {
     return this.hmDmKhoaNTAll;
   }

   public void setHmDmKhoaNTAll(HashMap<String, DmKhoa> hmDmKhoaNTAll)
   {
     this.hmDmKhoaNTAll = hmDmKhoaNTAll;
   }

   public List<SelectItem> getListDmKhoaNTs()
   {
     return this.listDmKhoaNTs;
   }

   public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs)
   {
     this.listDmKhoaNTs = listDmKhoaNTs;
   }

   public DmKhoaDelegate getDmKhoaDel()
   {
     return this.dmKhoaDel;
   }

   public void setDmKhoaDel(DmKhoaDelegate dmKhoaDel)
   {
     this.dmKhoaDel = dmKhoaDel;
   }

   public List<DmKhoa> getListDmKhoaNTAll()
   {
     return this.listDmKhoaNTAll;
   }

   public void setListDmKhoaNTAll(List<DmKhoa> listDmKhoaNTAll)
   {
     this.listDmKhoaNTAll = listDmKhoaNTAll;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.InSoThuocHangNgayAction

 * JD-Core Version:    0.7.0.1

 */