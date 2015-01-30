 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBienBanHoiChanPhauThuatDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
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
 @Name("B294_Bienbanhoichanphauthuat")
 @Synchronized(timeout=6000000L)
 public class BienBanHoiChanPhauThuatAction
   implements Serializable
 {
   private static final long serialVersionUID = -5106695460338085910L;
   private static Logger log = Logger.getLogger(BienBanHoiChanPhauThuatAction.class);
   private DtDmNhanVien newNhanVien;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private HsbaBienBanHoiChanPhauThuat bbhcpt;
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
   private DtDmNhanVien newNhanViengm;
   private List<DtDmNhanVien> bacsigmList;
   private DtDmNhanVien bacsigmSelected;
   public String luongbacsigm;
   private DtDmNhanVien newNhanVienptv;
   private List<DtDmNhanVien> bacsiptvList;
   private DtDmNhanVien bacsiptvSelected;
   public String luongbacsiptv;
   private DtDmNhanVien newNhanVientpk;
   private List<DtDmNhanVien> bacsitpkList;
   private DtDmNhanVien bacsitpkSelected;
   public String luongbacsitpk;
   private String gioPTTT;
   private String gioTGHC;
   private String ngayTGHC;
   private String ngayRut;
   private String ngayCatChi;

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

   public void deleteCurrentBacsigmRow(DtDmNhanVien currentBacsigm)
   {
     if ((this.bacsigmList == null) || (this.bacsigmList.size() == 0)) {
       return;
     }
     this.bacsigmList.remove(currentBacsigm);
     this.bacsigmSelected = null;
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

   public void themBacsiptv()
   {
     if (this.bacsiptvList == null) {
       this.bacsiptvList = new ArrayList();
     }
     if (((this.newNhanVienptv == null ? 1 : 0) | (this.newNhanVienptv.getDtdmnhanvienMaso() == null ? 1 : 0)) != 0) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmNhanVien nvptv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(this.newNhanVienptv.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");


     log.info("newNhanVienptv.getDtdmnhanvienTen()=" + this.newNhanVienptv.getDtdmnhanvienTen());
     log.info("nvptv.getDtdmnhanvienTen()=" + nvptv.getDtdmnhanvienTen());
     log.info("bacsiptvList.contains(nv)=" + this.bacsiptvList.contains(nvptv));
     if (!this.bacsiptvList.contains(nvptv)) {
       this.bacsiptvList.add(nvptv);
     }
     log.info("bacsiptvList.size=" + this.bacsiptvList.size());
   }

   public void deleteCurrentBacsiptvRow()
   {
     log.info("deleteCurrentBacsiptvRow");
     if ((this.bacsiptvList == null) || (this.bacsiptvList.size() == 0)) {
       return;
     }
     log.info(this.bacsiptvSelected.getDtdmnhanvienMa());
     this.bacsiptvList.remove(this.bacsiptvSelected);
     this.bacsiptvSelected = null;
   }

   public void deleteCurrentBacsiptvRow(DtDmNhanVien currentBacsiptv)
   {
     if ((this.bacsiptvList == null) || (this.bacsiptvList.size() == 0)) {
       return;
     }
     this.bacsiptvList.remove(currentBacsiptv);
     this.bacsiptvSelected = null;
   }

   public DtDmNhanVien getNewNhanVienptv(boolean create)
   {
     if ((create) && (this.newNhanVienptv == null)) {
       this.newNhanVienptv = new DtDmNhanVien();
     }
     return this.newNhanVienptv;
   }

   public DtDmNhanVien getNewNhanVienptv()
   {
     return this.newNhanVienptv;
   }

   public void setNewNhanVienptv(DtDmNhanVien newNhanVienptv)
   {
     this.newNhanVienptv = newNhanVienptv;
   }

   public List<DtDmNhanVien> getBacsiptvList()
   {
     return this.bacsiptvList;
   }

   public String getLuongbacsiptv()
   {
     if ((this.bacsiptvList == null) || (this.bacsiptvList.size() == 0)) {
       this.luongbacsiptv = null;
     } else {
       this.luongbacsiptv = (this.bacsiptvList.size() + "");
     }
     return this.luongbacsiptv;
   }

   public void setLuongbacsiptv(String luongbacsiptv)
   {
     this.luongbacsiptv = luongbacsiptv;
   }

   public void themBacsitpk()
   {
     if (this.bacsitpkList == null) {
       this.bacsitpkList = new ArrayList();
     }
     if (((this.newNhanVientpk == null ? 1 : 0) | (this.newNhanVientpk.getDtdmnhanvienMaso() == null ? 1 : 0)) != 0) {
       return;
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmNhanVien nvtpk = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(this.newNhanVientpk.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
     if ((nvtpk != null) &&
       (!this.bacsitpkList.contains(nvtpk))) {
       this.bacsitpkList.add(nvtpk);
     }
   }

   public void deleteCurrentBacsitpkRow()
   {
     log.info("deleteCurrentBacsitpkRow");
     if ((this.bacsitpkList == null) || (this.bacsitpkList.size() == 0)) {
       return;
     }
     log.info(this.bacsitpkSelected.getDtdmnhanvienMa());
     this.bacsitpkList.remove(this.bacsitpkSelected);
     this.bacsitpkSelected = null;
   }

   public void deleteCurrentBacsitpkRow(DtDmNhanVien currentBacsitpk)
   {
     if ((this.bacsitpkList == null) || (this.bacsitpkList.size() == 0)) {
       return;
     }
     this.bacsitpkList.remove(currentBacsitpk);
     this.bacsitpkSelected = null;
   }

   public DtDmNhanVien getNewNhanVientpk(boolean create)
   {
     if ((create) && (this.newNhanVientpk == null)) {
       this.newNhanVientpk = new DtDmNhanVien();
     }
     return this.newNhanVientpk;
   }

   public DtDmNhanVien getNewNhanVientpk()
   {
     return this.newNhanVientpk;
   }

   public void setNewNhanVientpk(DtDmNhanVien newNhanVientpk)
   {
     this.newNhanVientpk = newNhanVientpk;
   }

   public List<DtDmNhanVien> getBacsitpkList()
   {
     return this.bacsitpkList;
   }

   public String getLuongbacsitpk()
   {
     if ((this.bacsitpkList == null) || (this.bacsitpkList.size() == 0)) {
       this.luongbacsitpk = null;
     } else {
       this.luongbacsitpk = (this.bacsitpkList.size() + "");
     }
     return this.luongbacsitpk;
   }

   public void setLuongbacsitpk(String luongbacsitpk)
   {
     this.luongbacsitpk = luongbacsitpk;
   }

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
   {
     resetValue();
     System.out.println("end init ");

     return "DieuTri_LapVanBanTheoMau_BienBanHoiChanPhauThuat";
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

   public String getGioTGHC()
   {
     return this.gioTGHC;
   }

   public void setGioTGHC(String gioTGHC)
   {
     this.gioTGHC = gioTGHC;
   }

   public String getNgayTGHC()
   {
     return this.ngayTGHC;
   }

   public void setNgayTGHC(String ngayTGHC)
   {
     this.ngayTGHC = ngayTGHC;
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

   public void findGiuongPhong()
   {
     try
     {
       if (this.bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMaso() != null)
       {
         HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();

         HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(this.bbhcpt.getHsbaSovaovien(true).getHsbaSovaovien(), this.bbhcpt.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());



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
     this.bbhcpt = new HsbaBienBanHoiChanPhauThuat();
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();

     this.bacsigmList = new ArrayList();
     this.newNhanViengm = new DtDmNhanVien();
     this.bacsiptvList = new ArrayList();
     this.newNhanVienptv = new DtDmNhanVien();
     this.bacsitpkList = new ArrayList();
     this.newNhanVientpk = new DtDmNhanVien();

     this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

     this.gioPTTT = (this.ngayPTTT = this.gioTGHC = this.ngayTGHC = this.ngayRut = this.ngayCatChi = "");
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;

     findGiuongPhong();
   }

   public void displayHSBA()
   {
     log.info("---displayHSBAquang");
     this.bacsiList = new ArrayList();
     this.newNhanVien = new DtDmNhanVien();

     this.bacsigmList = new ArrayList();
     this.newNhanViengm = new DtDmNhanVien();

     String maHsba = this.bbhcpt.getHsbaSovaovien().getHsbaSovaovien().trim();
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
         this.bbhcpt.setHsbaSovaovien(hsba_tmp);
       }
       else
       {
         this.bbhcpt.setHsbaSovaovien(hsba_tmp);
         this.bbhcpt.setHsbabbhcptLdvv(hsba_tmp.getHsbaLydovao());
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       setInfoIfNullForHsba(hsba_tmp);
       this.bbhcpt.setHsbaSovaovien(hsba_tmp);
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

   public void displayBienBanHoiChanPhauThuat()
   {
     log.info("---displayBienBanHoiChanPhauThuat");
     String maBbhcpt = this.bbhcpt.getHsbabbhcptMa().trim();
     HsbaBienBanHoiChanPhauThuat bbhcpt_tmp = null;
     if (!maBbhcpt.equals(""))
     {
       HsbaBienBanHoiChanPhauThuatDelegate hsbaBienBanHoiChanPhauThuatDelegate = HsbaBienBanHoiChanPhauThuatDelegate.getInstance();
       bbhcpt_tmp = hsbaBienBanHoiChanPhauThuatDelegate.findByHsbabbhcptMa(maBbhcpt);
       if (bbhcpt_tmp == null)
       {
         this.nofound = "true";
         this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

         bbhcpt_tmp = new HsbaBienBanHoiChanPhauThuat();
         FacesMessages.instance().add(IConstantsRes.BBHCPT_NULL, new Object[] { maBbhcpt });
       }
       this.bbhcpt = bbhcpt_tmp;

       setNgayCap(formatDate(this.bbhcpt.getHsbabbhcptNgaycap()));

       setNgayPTTT(formatDate(this.bbhcpt.getHsbabbhcptNgaypttt()));
       setGioPTTT(formatDateTime(this.bbhcpt.getHsbabbhcptNgaypttt()));

       setNgayTGHC(formatDate(this.bbhcpt.getHsbabbhcptNgaytghc()));
       setGioTGHC(formatDateTime(this.bbhcpt.getHsbabbhcptNgaytghc()));


       this.bacsigmList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsigmByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa());
       this.bacsiptvList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsiptvByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa());
       this.bacsitpkList = hsbaBienBanHoiChanPhauThuatDelegate.findBacsitpkByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa());












       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.crrDate = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

       this.bbhcpt = new HsbaBienBanHoiChanPhauThuat();
     }
     findGiuongPhong();
   }

   public void ghiNhan()
   {
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.bbhcpt.setHsbabbhcptNgaygiocn(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     this.bbhcpt.setHsbabbhcptNgaytghc(Utils.getDBDate(this.gioTGHC, this.ngayTGHC).getTime());
     if (!this.ngayPTTT.trim().equals("")) {
       if (!this.gioPTTT.trim().equals("")) {
         this.bbhcpt.setHsbabbhcptNgaypttt(Utils.getDBDate(this.gioPTTT, this.ngayPTTT).getTime());
       } else {
         this.bbhcpt.setHsbabbhcptNgaypttt(Utils.getDBDate("00:00", this.ngayPTTT).getTime());
       }
     }
     if (!this.ngayCap.trim().equals("")) {
       this.bbhcpt.setHsbabbhcptNgaycap(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     this.bbhcpt.setHsbabbhcptNgaygiocn(new Date());
     this.bbhcpt.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername()));




     RemoveUtil.removeAllNullFromHSBABienBanHoiChanPhauThuat(this.bbhcpt);
     if (this.isUpdate)
     {
       result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().editHsbaBienBanHoiChanPhauThuat(this.bbhcpt, this.bacsiptvList, this.bacsigmList, this.bacsitpkList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.BBHCPT_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.bbhcpt.setHsbabbhcptMa(result);
         FacesMessages.instance().add(IConstantsRes.BBHCPT_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaBienBanHoiChanPhauThuatDelegate.getInstance().createHsbaBienBanHoiChanPhauThuat(this.bbhcpt, this.bacsiptvList, this.bacsigmList, this.bacsitpkList);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.BBHCPT_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.bbhcpt.setHsbabbhcptMa(result);
         FacesMessages.instance().add(IConstantsRes.BBHCPT_LT_THANHCONG, new Object[] { result });
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
     this.loaiBCDT = "bienbanhoichanphauthuat";
     HsbaBienBanHoiChanPhauThuatDelegate hsbaBienBanHoiChanPhauThuatDelegate = HsbaBienBanHoiChanPhauThuatDelegate.getInstance();
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bienbanhoichanphauthuat.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();



       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);


       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
       findGiuongPhong();
       params.put("BUONG", getSobuong() + " ");

       params.put("magiay", this.bbhcpt.getHsbabbhcptMa());
       log.info(this.bbhcpt.getHsbabbhcptMa());
       if (this.bbhcpt.getHsbabbhcptHinhthuc().intValue() == 1) {
         params.put("CAPCUU", "X");
       } else if (this.bbhcpt.getHsbabbhcptHinhthuc().intValue() == 2) {
         params.put("BANKHAN", "X");
       } else if (this.bbhcpt.getHsbabbhcptHinhthuc().intValue() == 3) {
         params.put("CHUONGTRINH", "X");
       }
       if (this.bbhcpt.getHsbaSovaovien() != null)
       {
         Hsba hsba = this.bbhcpt.getHsbaSovaovien();
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

         params.put("PHAUTHUATVIEN_PHUMO", getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsiptvByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa())));
         params.put("NGUOI_GAYME", getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsigmByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa())));
         params.put("THANHPHAN_KHAC", getBacsiFromList(hsbaBienBanHoiChanPhauThuatDelegate.findBacsitpkByHsbabbhcptMa(this.bbhcpt.getHsbabbhcptMa())));


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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "BienBanHoiChanPhauThuat");



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

   public HsbaBienBanHoiChanPhauThuat getBbhcpt()
   {
     return this.bbhcpt;
   }

   public void setBbhcpt(HsbaBienBanHoiChanPhauThuat bbhcpt)
   {
     this.bbhcpt = bbhcpt;
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.BienBanHoiChanPhauThuatAction

 * JD-Core Version:    0.7.0.1

 */