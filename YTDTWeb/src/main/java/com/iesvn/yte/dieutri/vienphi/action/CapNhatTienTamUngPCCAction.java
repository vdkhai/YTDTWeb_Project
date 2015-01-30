 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.TamUngKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.lang.time.DateFormatUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3234_CapNhatTienTamUngPCC")
 @Synchronized(timeout=6000000L)
 public class CapNhatTienTamUngPCCAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   public static transient Logger log = Logger.getLogger(CapNhatTienTamUngPCCAction.class);
   @In(required=false)
   private Identity identity;
   private TamUngKham tamUngKham;
   private String ghiNhanException;
   private String maPhu;
   private String soDu;
   private String tamUng_HoanUng;
   private String soTheBH_SoTheNgheo;
   private String tienBangChu;
   private String donViTuoi;
   private String sLiDoNop;
   private String lanTamung = "";
   private DtDmCum cum = null;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   private int index = 0;
   private boolean disabledPrinting = true;

   public void refreshnhanvienthungan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Create
   @Begin(join=true)
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     refreshnhanvienthungan();
     this.sLiDoNop = IConstantsRes.LI_DO_NOP_THU_TAM_UNG_PCC;
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
     log.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     log.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       log.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     this.maPhu = (new Date().getTime() + "");
     getTamUngKham(true).setTamungkhamNgay(new Date());
     log.info("***Finished init **");
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");

     log.info("***Finished back **");
     return "MyMainForm";
   }

   public String xuatReport()
   {
     String gt = null;
     String diachi = "";
     String baocao1 = null;
     try
     {
       if (this.tamUngKham == null) {
         return null;
       }
       if (this.tamUngKham.getTiepdonMa() == null) {
         return null;
       }
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa() == null) {
         return null;
       }
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/Phieutamung.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("NGAYLAP", DateFormatUtils.format(getTamUngKham().getTamungkhamNgay(), Utils.FORMAT_DATE));
       log.info("Ten benh nhan " + this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
       params.put("TEN", this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
       params.put("TUOI", this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi());
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getDmgtMaso() != null) {
         gt = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen();
       }
       log.info("Gioi tinh " + gt);
       params.put("GT", gt);
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa() != null)
       {
         log.info("Dan toc " + this.tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa().getDmdantocTen());
         params.put("DANTOC", this.tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa().getDmdantocTen());
       }
       String sonha;
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() == null) {
         sonha = "";
       } else {
         sonha = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ",";
       }
       String xa;
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getXaMa().getDmxaTen() == null) {
         xa = "";
       } else {
         xa = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getXaMa().getDmxaTen() + ",";
       }
       String huyen;
       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getHuyenMa().getDmhuyenTen() == null) {
         huyen = "";
       } else {
         huyen = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getHuyenMa().getDmhuyenTen() + ",";
       }
       String tinh;

       if (this.tamUngKham.getTiepdonMa().getBenhnhanMa().getTinhMa().getDmtinhTen() == null) {
         tinh = "";
       } else {
         tinh = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getTinhMa().getDmtinhTen();
       }
       diachi = sonha + xa + huyen + tinh;

       log.info("Dia chi " + diachi);
       params.put("DIACHI", diachi);
       if (this.soTheBH_SoTheNgheo == null) {
         params.put("SOTHEBH", "");
       } else {
         params.put("SOTHEBH", this.soTheBH_SoTheNgheo);
       }
       if (getTiepDon().getKcbbhytMa() != null)
       {
         if (getTiepDon().getKcbbhytMa().getDmbenhvienTen() == null) {
           params.put("KCB", "");
         } else {
           params.put("KCB", getTiepDon().getKcbbhytMa().getDmbenhvienTen());
         }
       }
       else {
         params.put("KCB", "");
       }
       log.info("Tuyen " + getTiepDon().getTiepdonTuyen());
       if (getTiepDon().getTiepdonTuyen() == null) {
         params.put("TUYEN", "");
       } else {
         params.put("TUYEN", "" + getTiepDon().getTiepdonTuyen());
       }
       log.info("ta mung " + getTamUngKham().getTamungkhamSotien());
       params.put("TAMUNG", getTamUngKham().getTamungkhamSotien());
       log.info("tam ung bang chu " + this.tienBangChu);
       params.put("BANGCHU", this.tienBangChu);
       if (this.tamUngKham.getTamungkhamLydo() != null) {
         params.put("LIDONOP", this.tamUngKham.getTamungkhamLydo());
       }
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       int lanIn = this.tamUngKham.getTamungkhamInphieu() == null ? 1 : Integer.valueOf(this.tamUngKham.getTamungkhamInphieu()).intValue() + 1;
       this.tamUngKham.setTamungkhamInphieu("" + lanIn);
       params.put("LANIN", "" + lanIn);

       this.reportTypeVP = "capnhattientamung_pcc";
       log.info("Vao Method XuatReport giaytamung");
       log.info("================= ");
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "capnhattientamung_pcc");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;

       TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
       tamUngKhamDelegate.edit(this.tamUngKham);
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
     return "B3360_Chonmenuxuattaptin";
   }

   public void layTheoTiepDonMa()
     throws Exception
   {
     log.info("***Starting layTheoTiepDonMa ");
     if ((this.tamUngKham.getTiepdonMa() != null) && (this.tamUngKham.getTiepdonMa().getTiepdonMa() != null))
     {
       try
       {
         qryBenhNhanBhtyThanhToan(this.tamUngKham.getTiepdonMa().getTiepdonMa());
         TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
         this.lanTamung = ("" + tamUngKhamDelegate.countSolanTamUngByTiepdon(this.tamUngKham.getTiepdonMa().getTiepdonMa()));
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.MA_TIEP_DON, this.tamUngKham.getTiepdonMa().getTiepdonMa() });
         log.error(e.toString());
       }
       this.maPhu = (new Date().getTime() + "");
     }
     this.tamUngKham.setTamungkhamMa(null);
     this.tamUngKham.setTamungkhamSotien(null);
     this.tienBangChu = "";
     this.disabledPrinting = true;
     log.info("***Finished layTheoTiepDonMa **");
   }

   public void layTheoTamUngMa()
     throws Exception
   {
     log.info("***Starting layTheoTamUngMa ***");
     if (this.tamUngKham == null) {
       return;
     }
     String stemp = "";
     stemp = this.tamUngKham.getTamungkhamMa();
     TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
     try
     {
       this.tamUngKham = tamUngKhamDelegate.find(this.tamUngKham.getTamungkhamMa());
       if ((this.tamUngKham != null) && (this.tamUngKham.getTiepdonMa() != null))
       {
         int dvt = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
         this.donViTuoi = Utils.taoDonViTuoi(dvt);
         if (this.tamUngKham.getTamungkhamThungan(true) != null)
         {
           this.nhanVienThungan = this.tamUngKham.getTamungkhamThungan(true);
           if (this.nhanVienThungan == null) {
             refreshnhanvienthungan();
           }
         }
         this.sLiDoNop = this.tamUngKham.getTamungkhamLydo();
         this.lanTamung = ("" + tamUngKhamDelegate.countSolanTamUngByTiepdon(this.tamUngKham.getTiepdonMa().getTiepdonMa()));
         log.info("****LIDO: " + this.tamUngKham.getTamungkhamLydo());
         this.disabledPrinting = false;
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, stemp });
         this.tamUngKham = null;
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.PHIEU_SO, this.tamUngKham.getTamungkhamMa() });
       log.error(e.toString());
     }
     if ((this.tamUngKham != null) && (this.tamUngKham.getTamungkhamMa() != null)) {
       this.maPhu = this.tamUngKham.getTamungkhamMa();
     } else {
       this.maPhu = (new Date().getTime() + "");
     }
     if (this.nhanVienThungan == null) {
       refreshnhanvienthungan();
     }
     log.info("***Finished layTheoTamUngMa **");
   }

   private void qryBenhNhanBhtyThanhToan(String maTiepDon)
   {
     TiepDonDelegate tiepdonDelegate = TiepDonDelegate.getInstance();

     TiepDon tiepDon = tiepdonDelegate.find(maTiepDon);
     if (tiepDon == null)
     {
       log.info("########## tiepDon = null ##########");
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", new Object[] { IConstantsRes.MA_TIEP_DON, maTiepDon });
       return;
     }
     if (tiepDon.getTiepdonBankham() != null)
     {
       if ((tiepDon.getTiepdonBankham().getDtdmbankhamMa() == null) || ((!tiepDon.getTiepdonBankham().getDtdmbankhamMa().equalsIgnoreCase("CCL")) && (!tiepDon.getTiepdonBankham().getDtdmbankhamMa().equalsIgnoreCase("CCN")))) {
         FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU, new Object[0]);
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU, new Object[0]);
       return;
     }
     this.tamUngKham.setTiepdonMa(tiepDon);
     int dvt = this.tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
     this.donViTuoi = Utils.taoDonViTuoi(dvt);
     log.info("tamUngKham.getTiepdonMa().getTiepdonMa()=" + this.tamUngKham.getTiepdonMa().getTiepdonMa());
     if (getTiepDon() == null)
     {
       this.tamUngKham = null;
       return;
     }
     log.info("getTiepDon().getTiepdonTuyen()=" + getTiepDon().getTiepdonTuyen());
     log.info("getTiepDon().getKcbbhytMa()=" + getTiepDon().getKcbbhytMa());
     if (getTiepDon().getKcbbhytMa() != null)
     {
       log.info("getTiepDon().getKcbbhytMa().getDtdmkcbbhytMa() = " + getTiepDon().getKcbbhytMa().getDmbenhvienMa());
       log.info("getTiepDon().getKcbbhytMa().getDtdmkcbbhytTen() = " + getTiepDon().getKcbbhytMa().getDmbenhvienTen());
     }
     log.info("getTiepDon().getTiepdonSothebh()=" + getTiepDon().getTiepdonSothebh());
     this.soTheBH_SoTheNgheo = getTiepDon().getTiepdonSothebh();
     if (StringUtils.isBlank(this.soTheBH_SoTheNgheo))
     {
       log.info("getTiepDon().getTiepdonThengheo()=" + getTiepDon().getTiepdonThengheo());
       this.soTheBH_SoTheNgheo = getTiepDon().getTiepdonThengheo();
     }
     this.disabledPrinting = false;

     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     HsThtoank hsttk = hsttkDelegate.findBytiepdonMaLast(maTiepDon);
     if (hsttk != null)
     {
       Double tamUng = hsttk.getHsthtoankTamung();
       Double hoanUng = hsttk.getHsthtoankHoanung();
       if (tamUng == null) {
         tamUng = Double.valueOf(0.0D);
       }
       if (hoanUng == null) {
         hoanUng = Double.valueOf(0.0D);
       }
       log.info("############# !null ###### Tam ung    :" + tamUng);
       log.info("############# !null ###### Hoan ung   :" + hoanUng);
       log.info("############# !null ###### Thanh toan :" + hsttk.getHsthtoankThtoan());
       this.tamUng_HoanUng = Utils.formatLongDouble(Double.valueOf(tamUng.doubleValue() - hoanUng.doubleValue()));
       this.soDu = Utils.formatLongDouble(hsttk.getHsthtoankThtoan());
     }
     else
     {
       hsttk = new HsThtoank();
       hsttk.setTiepdonMa(tiepDon);

       HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(tiepDon);
       hsthtoankUtil.tinhToanChoHSTKKham(hsttk);

       this.tamUng_HoanUng = Utils.formatLongDouble(Double.valueOf(hsttk.getHsthtoankTamung().doubleValue() - hsttk.getHsthtoankHoanung().doubleValue()));
       this.soDu = Utils.formatLongDouble(hsttk.getHsthtoankThtoan());
       log.info("########################## So du   :" + this.soDu);
       log.info("############ Tam ung - Hoan ung    :" + this.tamUng_HoanUng);
     }
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     log.info(this.tamUngKham.getTamungkhamSotien());
     this.ghiNhanException = null;
     refreshnhanvienthungan();
     TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
     if (this.tamUngKham != null) {
       try
       {
         DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
         if (userNv != null)
         {
           PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
           PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(this.tamUngKham.getTamungkhamNgay(), userNv.getDtdmnhanvienMa());
           if (pcCumThuPhi != null) {
             this.tamUngKham.setTamungkhamCum(pcCumThuPhi.getDtdmcumMa());
           }
         }
         if (StringUtils.isBlank(this.tamUngKham.getTamungkhamMa()))
         {
           this.tamUngKham.setTamungkhamThungan(this.nhanVienThungan);
           this.tamUngKham.setTamungkhamLydo(this.sLiDoNop);
           this.tamUngKham.setTamungkhamNgay(Calendar.getInstance().getTime());
           this.tamUngKham = tamUngKhamDelegate.create(this.tamUngKham);
         }
         else
         {
           this.tamUngKham.setTamungkhamLydo(this.sLiDoNop);
           this.tamUngKham.setTamungkhamNgay(Calendar.getInstance().getTime());
           tamUngKhamDelegate.edit(this.tamUngKham);
         }
         FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.PHIEU_SO, this.tamUngKham.getTamungkhamMa() });
         this.disabledPrinting = false;
       }
       catch (Exception e)
       {
         e.printStackTrace();
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
       }
     }
     log.info("***Finished ghinhan **");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       resetForm();
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void resetForm()
   {
     log.info("Begining ResetForm(): ");
     refreshnhanvienthungan();
     this.sLiDoNop = IConstantsRes.LI_DO_NOP_THU_TAM_UNG_PCC;
     this.tamUngKham = null;
     getTamUngKham(true).setTamungkhamNgay(new Date());

     this.soDu = null;
     this.tamUng_HoanUng = null;
     this.soTheBH_SoTheNgheo = null;
     this.tienBangChu = null;
     this.donViTuoi = null;
     this.disabledPrinting = true;
     this.maPhu = (new Date().getTime() + "");
     this.lanTamung = "";
     log.info("End ResetForm(): ");
   }

   public TamUngKham getTamUngKham()
   {
     return this.tamUngKham;
   }

   public TamUngKham getTamUngKham(boolean create)
   {
     if ((create) && (this.tamUngKham == null)) {
       this.tamUngKham = new TamUngKham();
     }
     return this.tamUngKham;
   }

   public void setTamUng(TamUngKham tamUng)
   {
     this.tamUngKham = tamUng;
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public String getMaPhu()
   {
     return this.maPhu;
   }

   public void setMaPhu(String maPhu)
   {
     this.maPhu = maPhu;
   }

   public String getTamUng_HoanUng()
   {
     return this.tamUng_HoanUng;
   }

   public void setTamUng_HoanUng(String tamUng_HoanUng)
   {
     this.tamUng_HoanUng = tamUng_HoanUng;
   }

   public String getSoTheBH_SoTheNgheo()
   {
     return this.soTheBH_SoTheNgheo;
   }

   public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo)
   {
     this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
   }

   public String getTienBangChu()
   {
     return this.tienBangChu;
   }

   public void setTienBangChu(String tienBangChu)
   {
     this.tienBangChu = tienBangChu;
   }

   public TiepDon getTiepDon(boolean create)
   {
     if ((create) && (getTiepDon() == null)) {
       getTamUngKham().getTiepdonMa(create);
     }
     return getTiepDon();
   }

   public TiepDon getTiepDon()
   {
     return getTamUngKham().getTiepdonMa();
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     getTamUngKham().setTiepdonMa(tiepDon);
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public String getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(String soDu)
   {
     this.soDu = soDu;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public void setTamUngKham(TamUngKham tamUngKham)
   {
     this.tamUngKham = tamUngKham;
   }

   public boolean isDisabledPrinting()
   {
     return this.disabledPrinting;
   }

   public void setDisabledPrinting(boolean disabledPrinting)
   {
     this.disabledPrinting = disabledPrinting;
   }

   public String getLiDoNop()
   {
     return this.sLiDoNop;
   }

   public void setLiDoNop(String liDoNop)
   {
     this.sLiDoNop = liDoNop;
   }

   public String getLanTamung()
   {
     return this.lanTamung;
   }

   public void setLanTamung(String lanTamung)
   {
     this.lanTamung = lanTamung;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.CapNhatTienTamUngPCCAction

 * JD-Core Version:    0.7.0.1

 */