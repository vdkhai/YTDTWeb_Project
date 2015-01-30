 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHocVi;
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
 @Name("B292_Phieuphauthuatthuthuat")
 @Synchronized(timeout=6000000L)
 public class PhieuPhauThuatThuThuatAction
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(PhieuPhauThuatThuThuatAction.class);
   private DtDmNhanVien newNhanVien;
   private DtDmNhanVien newNhanViengm;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private HsbaPhieuPhauThuatThuThuat ppttt;
   private String ngayCap;
   private String ngayPTTT;
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
   private List<DtDmNhanVien> bacsigmList;
   private DtDmNhanVien bacsigmSelected;
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
   private String gioPTTT;
   private String gioVV;
   private String ngayVV;
   private String ngayRut;
   private String ngayCatChi;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     resetValue();

     return "DieuTri_LapVanBanTheoMau_PhieuPhauThuatThuThuat";
   }

   public String getGioPTTT()
   {
     return this.gioPTTT;
   }

   public void setGioPTTT(String gioPTTT)
   {
     this.gioPTTT = gioPTTT;
   }

   public String getNgayCatChi()
   {
     return this.ngayCatChi;
   }

   public void setNgayCatChi(String ngayCatChi)
   {
     this.ngayCatChi = ngayCatChi;
   }

   public String getNgayRut()
   {
     return this.ngayRut;
   }

   public void setNgayRut(String ngayRut)
   {
     this.ngayRut = ngayRut;
   }

   public String getGioVV()
   {
     return this.gioVV;
   }

   public void setGioVV(String gioVV)
   {
     this.gioVV = gioVV;
   }

   public String getNgayVV()
   {
     return this.ngayVV;
   }

   public void setNgayVV(String ngayVV)
   {
     this.ngayVV = ngayVV;
   }

   private String sobuong = "";
   private String sogiuong = "";
   public String luongbacsi;
   public String luongbacsigm;

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

   public void themBacsigm()
   {
     if (this.bacsigmList == null) {
       this.bacsigmList = new ArrayList();
     }
     if (((this.newNhanViengm == null ? 1 : 0) | (this.newNhanViengm.getDtdmnhanvienMaso() == null ? 1 : 0)) != 0) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmNhanVien nvgm = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(this.newNhanViengm.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");


     log.info("newNhanViengm.getDtdmnhanvienTen()=" + this.newNhanViengm.getDtdmnhanvienTen());
     log.info("nvgm.getDtdmnhanvienTen()=" + nvgm.getDtdmnhanvienTen());
     log.info("bacsigmList.contains(nv)=" + this.bacsigmList.contains(nvgm));
     if (!this.bacsigmList.contains(nvgm)) {
       this.bacsigmList.add(nvgm);
     }
     log.info("bacsigmList.size=" + this.bacsigmList.size());
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

   public void deleteCurrentBacsigmRow()
   {
     log.info("deleteCurrentBacsigmRow");
     if ((this.bacsigmList == null) || (this.bacsigmList.size() == 0)) {
       return;
     }
     log.info(this.bacsigmSelected.getDtdmnhanvienMa());
     this.bacsigmList.remove(this.bacsigmSelected);
     this.bacsigmSelected = null;
   }

   public void deleteCurrentBacsiRow(DtDmNhanVien currentBacsi)
   {
     if ((this.bacsiList == null) || (this.bacsiList.size() == 0)) {
       return;
     }
     this.bacsiList.remove(currentBacsi);
     this.bacsiSelected = null;
   }

   public void deleteCurrentBacsigmRow(DtDmNhanVien currentBacsigm)
   {
     if ((this.bacsigmList == null) || (this.bacsigmList.size() == 0)) {
       return;
     }
     this.bacsigmList.remove(currentBacsigm);
     this.bacsigmSelected = null;
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

   public DtDmNhanVien getNewNhanViengm(boolean create)
   {
     if ((create) && (this.newNhanViengm == null)) {
       this.newNhanViengm = new DtDmNhanVien();
     }
     return this.newNhanViengm;
   }

   public DtDmNhanVien getNewNhanViengm()
   {
     return this.newNhanViengm;
   }

   public void setNewNhanViengm(DtDmNhanVien newNhanViengm)
   {
     this.newNhanViengm = newNhanViengm;
   }

   public List<DtDmNhanVien> getBacsigmList()
   {
     return this.bacsigmList;
   }

   public void findGiuongPhong()
   {
     try
     {
       if (this.ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso() != null)
       {
         HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();

         HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(this.ppttt.getHsbaSovaovien(true).getHsbaSovaovien(), this.ppttt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());



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
     this.ppttt = new HsbaPhieuPhauThuatThuThuat();
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();

     this.bacsigmList = new ArrayList();
     this.newNhanViengm = new DtDmNhanVien();

     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

     this.gioPTTT = (this.ngayPTTT = this.gioVV = this.ngayVV = this.ngayRut = this.ngayCatChi = "");
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
     Hsba hsba_tmp = new Hsba();
     setInfoIfNullForHsba(hsba_tmp);
     this.ppttt.setHsbaSovaovien(hsba_tmp);
     findGiuongPhong();
     this.nofoundHSBA = "true";
   }

   public void displayHSBA()
   {
     log.info("---displayHSBAquang");
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();

     this.bacsigmList = new ArrayList();
     this.newNhanViengm = new DtDmNhanVien();

     String maHsba = this.ppttt.getHsbaSovaovien().getHsbaSovaovien().trim();
     Hsba hsba_tmp = null;
     if (!maHsba.equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
       if (hsba_tmp == null)
       {
         resetValue();
       }
       else
       {
         this.ppttt.setHsbaSovaovien(hsba_tmp);
         this.ppttt.setHsbapptttKhoachidinh(hsba_tmp.getHsbaKhoadangdt());
         setNgayVV(formatDate(hsba_tmp.getHsbaNgaygiovaov()));
         setGioVV(formatDateTime(hsba_tmp.getHsbaNgaygiovaov()));
         this.nofoundHSBA = "false";
       }
     }
     else
     {
       resetValue();
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

   public void displayPhieuPhauThuatThuThuat()
   {
     log.info("---displayPhieuPhauThuatThuThuat");
     String maPpttt = this.ppttt.getHsbapptttMa().trim();
     HsbaPhieuPhauThuatThuThuat ppttt_tmp = null;
     if (!maPpttt.equals(""))
     {
       HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate = HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
       ppttt_tmp = hsbaPhieuPhauThuatThuThuatDelegate.findByHsbapptttMa(maPpttt);
       if (ppttt_tmp == null)
       {
         this.nofound = "true";
         this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

         ppttt_tmp = new HsbaPhieuPhauThuatThuThuat();
         FacesMessages.instance().add(IConstantsRes.PPTTT_NULL, new Object[] { maPpttt });
       }
       this.ppttt = ppttt_tmp;

       setNgayCap(formatDate(this.ppttt.getHsbapptttNgaycap()));
       setNgayCatChi(formatDate(this.ppttt.getHsbapptttNgaycatchi()));
       setNgayPTTT(formatDate(this.ppttt.getHsbapptttNgaypttt()));
       setNgayRut(formatDate(this.ppttt.getHsbapptttNgayrut()));
       setGioPTTT(formatDateTime(this.ppttt.getHsbapptttNgaypttt()));

       setNgayVV(formatDate(this.ppttt.getHsbaSovaovien(true).getHsbaNgaygiovaov()));

       setGioVV(formatDateTime(this.ppttt.getHsbaSovaovien(true).getHsbaNgaygiovaov()));


       this.bacsiList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(this.ppttt.getHsbapptttMa());
       this.bacsigmList = hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(this.ppttt.getHsbapptttMa());
       System.out.println("bacsiList.size()=" + this.bacsiList.size());
       System.out.println("bacsigmList.size()=" + this.bacsigmList.size());












       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

       this.ppttt = new HsbaPhieuPhauThuatThuThuat();
     }
     findGiuongPhong();
   }

   public String getdmBenhIcdMaCX()
   {
     return this.ppttt.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdMa();
   }

   public String getdmBenhIcdTenCX()
   {
     return this.ppttt.getHsbaSovaovien(true).getHsbaTuvong(true).getDmbenhicdTen();
   }

   public void ghiNhan()
   {
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.ppttt.setHsbapptttNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (!this.ngayVV.trim().equals("")) {
       if (this.gioVV.trim().equals("")) {
         this.ppttt.setHsbapptttNgayvaovien(Utils.getDBDate("00:00", this.ngayVV).getTime());
       } else {
         this.ppttt.setHsbapptttNgayvaovien(Utils.getDBDate(this.gioVV, this.ngayVV).getTime());
       }
     }
     if (this.gioPTTT.trim().equals("")) {
       this.ppttt.setHsbapptttNgaypttt(Utils.getDBDate("00:00", this.ngayPTTT).getTime());
     } else {
       this.ppttt.setHsbapptttNgaypttt(Utils.getDBDate(this.gioPTTT, this.ngayPTTT).getTime());
     }
     if (!this.ngayCap.trim().equals("")) {
       this.ppttt.setHsbapptttNgaycap(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (!this.ngayCatChi.trim().equals("")) {
       this.ppttt.setHsbapptttNgaycatchi(Utils.getDBDate("00:00", this.ngayCatChi).getTime());
     }
     if (!this.ngayRut.trim().equals("")) {
       this.ppttt.setHsbapptttNgayrut(Utils.getDBDate("00:00", this.ngayRut).getTime());
     }
     this.ppttt.setHsbapptttNgaygiocn(new Date());
     this.ppttt.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername()));




     RemoveUtil.removeAllNullFromHSBAPhieuPhauThuatThuThuat(this.ppttt);
     if (this.isUpdate)
     {
       result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().editHsbaPhieuPhauThuatThuThuat(this.ppttt, this.bacsiList, this.bacsigmList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PPTTT_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.ppttt.setHsbapptttMa(result);
         FacesMessages.instance().add(IConstantsRes.PPTTT_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaPhieuPhauThuatThuThuatDelegate.getInstance().createHsbaPhieuPhauThuatThuThuat(this.ppttt, this.bacsiList, this.bacsigmList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PPTTT_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.ppttt.setHsbapptttMa(result);
         FacesMessages.instance().add(IConstantsRes.PPTTT_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public String getLuongbacsi()
   {
     if ((this.bacsiList == null) || (this.bacsiList.size() == 0)) {
       this.luongbacsi = null;
     } else {
       this.luongbacsi = (this.bacsiList.size() + "");
     }
     return this.luongbacsi;
   }

   public void setLuongbacsi(String luongbacsi)
   {
     this.luongbacsi = luongbacsi;
   }

   public String getLuongbacsigm()
   {
     if ((this.bacsigmList == null) || (this.bacsigmList.size() == 0)) {
       this.luongbacsigm = null;
     } else {
       this.luongbacsigm = (this.bacsigmList.size() + "");
     }
     return this.luongbacsigm;
   }

   public void setLuongbacsigm(String luongbacsigm)
   {
     this.luongbacsigm = luongbacsigm;
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
       tmp = tmp + prefix + ((DtDmNhanVien)bacsi.get(i)).getDmhocviMaso().getDmhocviMa() + ". " + ((DtDmNhanVien)bacsi.get(i)).getDtdmnhanvienTen();
       if (i == 0) {
         prefix = ", ";
       }
     }
     tmp = tmp + ".";


     return tmp;
   }

   public void XuatReport()
   {
     this.loaiBCDT = "PhieuPhauThuatThuThuat";
     log.info("Vao Method XuatReport phieu phau thuat thu thuat");
     HsbaPhieuPhauThuatThuThuatDelegate hsbaPhieuPhauThuatThuThuatDelegate = HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Phieuphauthuatthuthuat_main.jrxml";


       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Phieuphauthuatthuthuat_sub1.jrxml";


       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Phieuphauthuatthuthuat_sub2.jrxml";


       log.info("da thay file templete 5" + pathTemplate);
       log.info("da thay file sub 1 templete " + sub1Template);
       log.info("da thay file sub 2 templete " + sub2Template);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);




       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);


       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);



       params.put("khoaRav", this.ppttt.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());



       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);

       params.put("magiay", this.ppttt.getHsbapptttMa());
       log.info(this.ppttt.getHsbapptttMa());
       if (this.ppttt.getHsbaSovaovien() != null)
       {
         Hsba hsba = this.ppttt.getHsbaSovaovien();
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



         findGiuongPhong();
         params.put("BUONG", getSobuong());
         params.put("GIUONG", getSogiuong());
         if (hsba.getHsbaKhoadangdt().getDmkhoaTen() != null) {
           params.put("KHOA_HIENTAI", hsba.getHsbaKhoadangdt().getDmkhoaTen());
         } else {
           params.put("KHOA_HIENTAI", "");
         }
         if (this.ppttt.getHsbapptttTrinhtu() != null) {
           params.put("TRINHTU_PTTT", this.ppttt.getHsbapptttTrinhtu());
         } else {
           params.put("TRINHTU_PTTT", "");
         }
         params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
         log.info("gioitinh = " + hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
         params.put("SOVAOVIEN", hsba.getHsbaSovaovien());
         log.info("sovaovien = " + hsba.getHsbaSovaovien());
         params.put("BACSI_PHAUTHUAT", getBacsiFromList(hsbaPhieuPhauThuatThuThuatDelegate.findBacsiByHsbapptttMa(this.ppttt.getHsbapptttMa())));
         params.put("BACSI_GAYME", getBacsiFromList(hsbaPhieuPhauThuatThuThuatDelegate.findBacsigmByHsbapptttMa(this.ppttt.getHsbapptttMa())));

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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "PhieuPhauThuatThuThuat");



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

   public HsbaPhieuPhauThuatThuThuat getPpttt()
   {
     return this.ppttt;
   }

   public void setPpttt(HsbaPhieuPhauThuatThuThuat ppttt)
   {
     this.ppttt = ppttt;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String getNgayPTTT()
   {
     return this.ngayPTTT;
   }

   public void setNgayPTTT(String ngayPTTT)
   {
     this.ngayPTTT = ngayPTTT;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.PhieuPhauThuatThuThuatAction

 * JD-Core Version:    0.7.0.1

 */