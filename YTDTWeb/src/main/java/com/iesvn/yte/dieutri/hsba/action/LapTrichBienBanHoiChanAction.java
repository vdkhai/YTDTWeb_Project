 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaLapTrichBienBanHoiChanDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B293_Laptrichbienbanhoichan")
 @Synchronized(timeout=6000000L)
 public class LapTrichBienBanHoiChanAction
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(LapTrichBienBanHoiChanAction.class);
   private DtDmNhanVien newNhanVien;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private HsbaLapTrichBienBanHoiChan ltbbhc;
   private String ngayTGHC;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private String crrDate;
   private boolean isUpdate;
   @DataModel
   private List<DtDmNhanVien> bacsiList;
   @In(required=false)
   @Out(required=false)
   @DataModelSelection
   private DtDmNhanVien bacsiSelected;
   String ngayCap;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   private int index = 0;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   public String luongthanhvien;
   private String gioTGHC;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     resetValue();

     return "DieuTri_LapVanBanTheoMau_LapTrichBienBanHoiChan";
   }

   public String getLuongthanhvien()
   {
     if ((this.bacsiList == null) || (this.bacsiList.size() == 0)) {
       this.luongthanhvien = null;
     } else {
       this.luongthanhvien = (this.bacsiList.size() + "");
     }
     return this.luongthanhvien;
   }

   public void setLuongthanhvien(String luongthanhvien)
   {
     this.luongthanhvien = luongthanhvien;
   }

   public String getGioTGHC()
   {
     return this.gioTGHC;
   }

   public void setGioTGHC(String gioTGHC)
   {
     this.gioTGHC = gioTGHC;
   }

   private String sobuong = "";
   private String sogiuong = "";

   public String getSobuong()
   {
     return this.sobuong;
   }

   public void setSobuong(String sobuong)
   {
     this.sobuong = sobuong;
   }

   public String getSogiuong()
   {
     return this.sogiuong;
   }

   public void setSogiuong(String sogiuong)
   {
     this.sogiuong = sogiuong;
   }

   public void themBacsi()
   {
     if (this.bacsiList == null) {
       this.bacsiList = new ArrayList();
     }
     if (((this.newNhanVien == null ? 1 : 0) | (this.newNhanVien.getDtdmnhanvienMaso() == null ? 1 : 0)) != 0) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmNhanVien nv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(this.newNhanVien.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");


     log.info("newNhanVien.getDtdmnhanvienTen()=" + this.newNhanVien.getDtdmnhanvienTen());
     log.info("nv.getDtdmnhanvienTen()=" + nv.getDtdmnhanvienTen());
     log.info("bacsiList.contains(nv)=" + this.bacsiList.contains(nv));
     if (!this.bacsiList.contains(nv)) {
       this.bacsiList.add(nv);
     }
     log.info("bacsiList.size=" + this.bacsiList.size());
   }

   public void deleteCurrentBacsiRow()
   {
     log.info("deleteCurrentBacsiRow");
     if ((this.bacsiList == null) || (this.bacsiList.size() == 0)) {
       return;
     }
     log.info(this.bacsiSelected.getDtdmnhanvienMa());
     this.bacsiList.remove(this.bacsiSelected);
     this.bacsiSelected = null;
   }

   public void deleteCurrentBacsiRow(DtDmNhanVien currentBacsi)
   {
     if ((this.bacsiList == null) || (this.bacsiList.size() == 0)) {
       return;
     }
     this.bacsiList.remove(currentBacsi);
     this.bacsiSelected = null;
   }

   public DtDmNhanVien getNewNhanVien(boolean create)
   {
     if ((create) && (this.newNhanVien == null)) {
       this.newNhanVien = new DtDmNhanVien();
     }
     return this.newNhanVien;
   }

   public DtDmNhanVien getNewNhanVien()
   {
     return this.newNhanVien;
   }

   public void setNewNhanVien(DtDmNhanVien newNhanVien)
   {
     this.newNhanVien = newNhanVien;
   }

   public List<DtDmNhanVien> getBacsiList()
   {
     return this.bacsiList;
   }

   public void findGiuongPhong()
   {
     try
     {
       if (this.ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso() != null)
       {
         HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();

         HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(this.ltbbhc.getHsbaSovaovien(true).getHsbaSovaovien(), this.ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());



         setSobuong(hsbaChuyenMon.getHsbacmSobuong());
         setSogiuong(hsbaChuyenMon.getHsbacmSogiuong());
       }
     }
     catch (Exception e) {}
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");
     this.ltbbhc = new HsbaLapTrichBienBanHoiChan();
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();

     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

     this.gioTGHC = (this.ngayTGHC = "");
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;

     findGiuongPhong();
   }

   public void displayHSBA()
   {
     log.info("---displayHSBAquang");
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();


     String maHsba = this.ltbbhc.getHsbaSovaovien().getHsbaSovaovien().trim();
     Hsba hsba_tmp = null;
     if (!maHsba.equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
       if (hsba_tmp == null)
       {
         this.nofoundHSBA = "true";
         hsba_tmp = new Hsba();
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { maHsba });
         hsba_tmp = new Hsba();
         setInfoIfNullForHsba(hsba_tmp);
         this.ltbbhc.setHsbaSovaovien(hsba_tmp);
         this.ltbbhc.setHsbaltbbhcChuandoanma(hsba_tmp.getHsbaMachdoanbd(true));
       }
       else
       {
         this.ltbbhc.setHsbaSovaovien(hsba_tmp);
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       setInfoIfNullForHsba(hsba_tmp);
       this.ltbbhc.setHsbaSovaovien(hsba_tmp);
     }
     findGiuongPhong();
   }

   private void setInfoIfNullForHsba(Hsba obj)
   {
     if (obj.getBenhnhanMa() == null)
     {
       BenhNhan _benhnhan = new BenhNhan();
       setInfoIfNullForBenhNhan(_benhnhan);
       obj.setBenhnhanMa(_benhnhan);
     }
   }

   private void setInfoIfNullForBenhNhan(BenhNhan obj)
   {
     if (obj.getDmgtMaso() == null) {
       obj.setDmgtMaso(new DmGioiTinh());
     }
     if (obj.getDantocMa() == null) {
       obj.setDantocMa(new DmDanToc());
     }
     if (obj.getTinhMa() == null) {
       obj.setTinhMa(new DmTinh());
     }
     if (obj.getHuyenMa() == null) {
       obj.setHuyenMa(new DmHuyen());
     }
     if (obj.getXaMa() == null) {
       obj.setXaMa(new DmXa());
     }
     if (obj.getBenhnhanNghe() == null) {
       obj.setBenhnhanNghe(new DmNgheNghiep());
     }
   }

   public void displayLapTrichBienBanHoiChan()
   {
     log.info("---displayLapTrichBienBanHoiChan");
     String maLTBBHC = this.ltbbhc.getHsbaltbbhcMa().trim();
     HsbaLapTrichBienBanHoiChan ltbbhc_tmp = null;
     if (!maLTBBHC.equals(""))
     {
       HsbaLapTrichBienBanHoiChanDelegate hsbaLapTrichBienBanHoiChanDelegate = HsbaLapTrichBienBanHoiChanDelegate.getInstance();
       ltbbhc_tmp = hsbaLapTrichBienBanHoiChanDelegate.findByHsbaltbbhcMa(maLTBBHC);
       if (ltbbhc_tmp == null)
       {
         this.nofound = "true";
         this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

         ltbbhc_tmp = new HsbaLapTrichBienBanHoiChan();
         FacesMessages.instance().add(IConstantsRes.LTBBHC_NULL, new Object[] { maLTBBHC });
       }
       this.ltbbhc = ltbbhc_tmp;

       setNgayCap(formatDate(this.ltbbhc.getHsbaltbbhcNgaycap()));
       setNgayTGHC(formatDate(this.ltbbhc.getHsbaltbbhcThoigianhoichan()));
       setGioTGHC(formatDateTime(this.ltbbhc.getHsbaltbbhcThoigianhoichan()));



       this.bacsiList = hsbaLapTrichBienBanHoiChanDelegate.findBacsiByHsbaltbbhcMa(this.ltbbhc.getHsbaltbbhcMa());

       System.out.println("bacsiList.size()=" + this.bacsiList.size());













       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

       this.ltbbhc = new HsbaLapTrichBienBanHoiChan();
     }
     findGiuongPhong();
   }

   public String getdmBenhIcdMaCX()
   {
     return this.ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa();
   }

   public String getdmBenhIcdTenCX()
   {
     return this.ltbbhc.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen();
   }

   public void ghiNhan()
   {
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.ltbbhc.setHsbaltbbhcNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     this.ltbbhc.setHsbaltbbhcThoigianhoichan(Utils.getDBDate(this.gioTGHC, this.ngayTGHC).getTime());
     if (!this.ngayCap.trim().equals("")) {
       this.ltbbhc.setHsbaltbbhcNgaycap(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     this.ltbbhc.setHsbaltbbhcNgaygiocn(new Date());




     RemoveUtil.removeAllNullFromHSBALapTrichBienBanHoiChan(this.ltbbhc);
     if (this.isUpdate)
     {
       result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().editHsbaLapTrichBienBanHoiChan(this.ltbbhc, this.bacsiList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.LTBBHC_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.ltbbhc.setHsbaltbbhcMa(result);
         FacesMessages.instance().add(IConstantsRes.LTBBHC_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaLapTrichBienBanHoiChanDelegate.getInstance().createHsbaLapTrichBienBanHoiChan(this.ltbbhc, this.bacsiList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.LTBBHC_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.ltbbhc.setHsbaltbbhcMa(result);
         FacesMessages.instance().add(IConstantsRes.LTBBHC_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public String getBacsiFromList(List<DtDmNhanVien> bacsi)
   {
     if ((bacsi == null) || (bacsi.size() == 0)) {
       return "";
     }
     String tmp = "";
     String prefix = "";
     for (int i = 0; i < bacsi.size(); i++)
     {
       tmp = tmp + prefix + ((DtDmNhanVien)bacsi.get(i)).getDtdmnhanvienTen();
       if (i == 0) {
         prefix = ", ";
       }
     }
     tmp = tmp + ".";


     return tmp;
   }

   public void XuatReport()
   {
     this.loaiBCDT = "LapTrichBienBanHoiChan";
     log.info("Vao Method XuatReport lap trich bien ban hoi chan");
     HsbaLapTrichBienBanHoiChanDelegate hsbaLapTrichBienBanHoiChanDelegate = HsbaLapTrichBienBanHoiChanDelegate.getInstance();
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Laptrichbienbanhoichan.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);




       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();



       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);


       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);



       params.put("khoaRav", this.ltbbhc.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());

       params.put("KHOA_HIENTAI", this.ltbbhc.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaTen());


       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
       findGiuongPhong();
       System.out.println("getSogiuong() = " + getSogiuong());
       System.out.println("getSobuong() = " + getSobuong());
       params.put("GIUONG", getSogiuong() + " ");
       params.put("BUONG", getSobuong() + " ");


       params.put("magiay", this.ltbbhc.getHsbaltbbhcMa());
       log.info(this.ltbbhc.getHsbaltbbhcMa());
       if (this.ltbbhc.getHsbaSovaovien() != null)
       {
         Hsba hsba = this.ltbbhc.getHsbaSovaovien();
         String diachistr = "";
         if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
         }
         if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
         }
         if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
         }
         if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
           diachistr = diachistr + hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
         }
         params.put("DIACHI", diachistr);
         if (hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen() != null) {
           params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
         } else {
           params.put("gioiTinh", "");
         }
         params.put("SOVAOVIEN", hsba.getHsbaSovaovien());
         if (hsba.getHsbaKhoadangdt().getDmkhoaTen() != null) {
           params.put("KHOA_HIENTAI", hsba.getHsbaKhoadangdt().getDmkhoaTen());
         } else {
           params.put("KHOA_HIENTAI", "");
         }
         params.put("BACSI_THANHVIEN", getBacsiFromList(hsbaLapTrichBienBanHoiChanDelegate.findBacsiByHsbaltbbhcMa(this.ltbbhc.getHsbaltbbhcMa())));


         HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();

         HsbaBhyt hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
         if (hsbaBHYT != null)
         {
           if (hsbaBHYT.getHsbabhytGiatri0() != null) {
             params.put("GIATRITU", sdf.format(hsbaBHYT.getHsbabhytGiatri0()));
           }
           if (hsbaBHYT.getHsbabhytGiatri1() != null) {
             params.put("GIATRIDEN", sdf.format(hsbaBHYT.getHsbabhytGiatri1()));
           }
           String so_bhyt = "";
           if ((hsbaBHYT.getHsbabhytSothebh() != null) && (!hsbaBHYT.getHsbabhytSothebh().equals("")))
           {
             so_bhyt = hsbaBHYT.getHsbabhytSothebh();
             params.put("khoiBhytMa", so_bhyt.substring(0, 2));
             params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
             params.put("tinhBhytMa", so_bhyt.substring(3, 5));
             params.put("namBhyt", so_bhyt.substring(5, 7));
             params.put("maCoQuan", so_bhyt.substring(7, 10));
             if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null) {
               params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());
             } else {
               params.put("THEBHYT", so_bhyt.substring(10));
             }
           }
         }
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "LapTrichBienBanHoiChan");



       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     System.out.println("qdate = " + (date == null ? "isnull" : date.toString()));

     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public HsbaLapTrichBienBanHoiChan getLtbbhc()
   {
     return this.ltbbhc;
   }

   public void setLtbbhcX(HsbaLapTrichBienBanHoiChan ltbbhc)
   {
     this.ltbbhc = ltbbhc;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String getNgayTGHC()
   {
     return this.ngayTGHC;
   }

   public void setNgayTGHC(String ngayTGHC)
   {
     this.ngayTGHC = ngayTGHC;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNofoundHSBA()
   {
     return this.nofoundHSBA;
   }

   public void setNofoundHSBA(String nofoundHSBA)
   {
     this.nofoundHSBA = nofoundHSBA;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getCrrDate()
   {
     return this.crrDate;
   }

   public void setCrrDate(String crrDate)
   {
     this.crrDate = crrDate;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.LapTrichBienBanHoiChanAction

 * JD-Core Version:    0.7.0.1

 */