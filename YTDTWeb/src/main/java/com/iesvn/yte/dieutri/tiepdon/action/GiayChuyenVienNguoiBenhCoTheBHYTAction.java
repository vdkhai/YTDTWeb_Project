 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.GiayCvNbBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B117_Giaychuyenviennguoibenhcothebhyt")
 @Synchronized(timeout=6000000L)
 public class GiayChuyenVienNguoiBenhCoTheBHYTAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String sMaGiayChuyenVien;
   private String ngaySinh = "";
   private String gioi = "";
   private String sCoSoKCB = "";
   private Boolean bDaKCT;
   private Boolean bDtNgoaiTru;
   private Boolean bDtNoiTru;
   private GiayCvNbBhyt giayChuyenVien = new GiayCvNbBhyt();
   private String sShowDel = "false";
   private String sShowPrint = "false";
   private static Logger log = Logger.getLogger(GiayChuyenVienNguoiBenhCoTheBHYTAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToChuyenVienNguoiBenhCoTheBHYT;
   @Out(required=false)
   private String bacSiKCB;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   private List<ThuocPhongKham> listTPK;
   private String thuocdadung = "";
   private List<ClsKham> listCLS;
   private String cacxetnghiem = "";
   private String resultHidden = "";

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToChuyenVienNguoiBenhCoTheBHYT")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if (this.thamkham != null)
       {
         this.benhNhan = this.thamkham.getTiepdonMa(true).getBenhnhanMa();
         GiayCvNbBhytDelegate objGiayCvNbBhytDel = GiayCvNbBhytDelegate.getInstance();

         this.giayChuyenVien = objGiayCvNbBhytDel.findByMaThamKham(this.thamkham.getThamkhamMa());
         if (this.giayChuyenVien == null)
         {
           this.giayChuyenVien = new GiayCvNbBhyt();
           if (this.thamkham.getThamkhamBacsi() != null) {
             this.giayChuyenVien.setGcvbhytBskham(this.thamkham.getThamkhamBacsi());
           }
           if (this.thamkham.getTiepdonMa(true).getTiepdonLydochvi(true).getDtdmlydocvTen() != null) {
             this.giayChuyenVien.setGcvbhytLidochuyenvien(this.thamkham.getTiepdonMa(true).getTiepdonLydochvi(true).getDtdmlydocvTen());
           }
           if (this.thamkham.getTiepdonMa(true).getTiepdonChvien(true).getDmbenhvienTen() != null) {
             this.giayChuyenVien.setGcvbhytNoichuyenden(this.thamkham.getTiepdonMa(true).getTiepdonChvien(true).getDmbenhvienTen());
           }
           ThuocPhongKhamDelegate tpkDele = ThuocPhongKhamDelegate.getInstance();
           this.listTPK = tpkDele.findByThamKham(this.thamkham.getThamkhamMa(), Utils.KE_TOA_QUAY_BENH_VIEN);
           this.thuocdadung = "";
           if ((this.listTPK != null) && (this.listTPK.size() > 0))
           {
             for (ThuocPhongKham tpk : this.listTPK) {
               this.thuocdadung = (this.thuocdadung + tpk.getThuocphongkhamMathuoc(true).getDmthuocTen() + "; ");
             }
             if (this.thuocdadung.length() > 2040) {
               this.thuocdadung = (this.thuocdadung.substring(0, 2039) + " ...");
             }
           }
           else
           {
             this.thuocdadung = "Không có";
           }
           this.giayChuyenVien.setGcvbhytTemp2(this.thuocdadung);

           ClsKhamDelegate clskhamDele = ClsKhamDelegate.getInstance();
           this.listCLS = clskhamDele.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.thamkham.getTiepdonMa(true).getTiepdonMa());
           this.cacxetnghiem = "";
           if ((this.listCLS != null) && (this.listCLS.size() > 0))
           {
             for (ClsKham cls : this.listCLS) {
               if ((cls.getClskhamMaloai() != null) && ((cls.getClskhamMaloai().getDtdmclsMaso().intValue() == 4) || (cls.getClskhamMaloai().getDtdmclsMaso().intValue() == 5))) {
                 this.cacxetnghiem = (this.cacxetnghiem + cls.getClskhamMahang(true).getDtdmclsbgDiengiai() + "; ");
               }
             }
             if (this.cacxetnghiem.equals("")) {
               this.cacxetnghiem = "Không có";
             } else if (this.cacxetnghiem.length() > 2040) {
               this.cacxetnghiem = (this.cacxetnghiem.substring(0, 2039) + " ...");
             }
           }
           else
           {
             this.cacxetnghiem = "Không có";
           }
           this.giayChuyenVien.setGcvbhytTemp1(this.cacxetnghiem);

           setDaKCT(Boolean.valueOf(false));
           setDtNgoaiTru(Boolean.valueOf(false));
           setDtNoiTru(Boolean.valueOf(false));
           log.info("***KHONG THAY****");
           this.sMaGiayChuyenVien = "";
           this.sShowDel = "false";
           this.sShowPrint = "false";
         }
         else
         {
           log.info("***TIM THAY****");
           this.sMaGiayChuyenVien = this.giayChuyenVien.getGcvbhytMa();
           setDaKCT(this.giayChuyenVien.getGcvbhytKct());
           setDtNgoaiTru(this.giayChuyenVien.getGcvbhytDadtngoaitru());
           setDtNoiTru(this.giayChuyenVien.getGcvbhytDadtnoitru());
           if ((this.giayChuyenVien.getGcvbhytBskham() == null) &&
             (this.thamkham.getThamkhamBacsi() != null)) {
             this.giayChuyenVien.setGcvbhytBskham(this.thamkham.getThamkhamBacsi());
           }
           if ((this.giayChuyenVien.getGcvbhytLidochuyenvien() == null) || (this.giayChuyenVien.getGcvbhytLidochuyenvien().equals(""))) {
             if (this.thamkham.getTiepdonMa(true).getTiepdonLydochvi(true).getDtdmlydocvTen() != null) {
               this.giayChuyenVien.setGcvbhytLidochuyenvien(this.thamkham.getTiepdonMa(true).getTiepdonLydochvi(true).getDtdmlydocvTen());
             }
           }
           if ((this.giayChuyenVien.getGcvbhytNoichuyenden() == null) || (this.giayChuyenVien.getGcvbhytNoichuyenden().equals(""))) {
             if (this.thamkham.getTiepdonMa(true).getTiepdonChvien(true).getDmbenhvienTen() != null) {
               this.giayChuyenVien.setGcvbhytNoichuyenden(this.thamkham.getTiepdonMa(true).getTiepdonChvien(true).getDmbenhvienTen());
             }
           }
           this.sShowDel = "true";
           this.sShowPrint = "false";
         }
         System.out.println("sShowDel=" + this.sShowDel);
         System.out.println("sShowPrint=" + this.sShowPrint);
         if (this.benhNhan.getDmgtMaso() != null)
         {
           if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
             this.gioi = "r1";
           } else {
             this.gioi = "r2";
           }
         }
         else {
           this.gioi = null;
         }
         setOtherValue();
       }
       destroyService();
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
     }
     System.out.println("sShowDel=" + this.sShowDel);
     System.out.println("sShowPrint=" + this.sShowPrint);
     log.info("***Finished init ***");
   }

   @End
   public void end() {}

   public String ghiNhan()
     throws Exception
   {
     log.info("*****Begin ghiNhan() *****");
     try
     {
       GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate.getInstance();
       if (getDaKCT().booleanValue()) {
         this.giayChuyenVien.setGcvbhytKct(Boolean.valueOf(true));
       } else {
         this.giayChuyenVien.setGcvbhytKct(Boolean.valueOf(false));
       }
       if (getDtNgoaiTru().booleanValue()) {
         this.giayChuyenVien.setGcvbhytDadtngoaitru(Boolean.valueOf(true));
       } else {
         this.giayChuyenVien.setGcvbhytDadtngoaitru(Boolean.valueOf(false));
       }
       if (getDtNoiTru().booleanValue()) {
         this.giayChuyenVien.setGcvbhytDadtnoitru(Boolean.valueOf(true));
       } else {
         this.giayChuyenVien.setGcvbhytDadtnoitru(Boolean.valueOf(false));
       }
       this.giayChuyenVien.setGcvbhytThamkham(this.thamkham);
       RemoveUtil.removeIfNullForGiayCvNbBhyt(this.giayChuyenVien);

       this.sMaGiayChuyenVien = GiayCvNbBhytDel.capNhatGiayCvNbBhyt(this.giayChuyenVien, this.sMaGiayChuyenVien);


       FacesMessages.instance().add(IConstantsRes.RPGCV_INSERT_SUCCESS, new Object[] { this.sMaGiayChuyenVien });

       log.info("*****CAP NHAT THANH CONG  GiayChuyenVienNguoiBenhCoTheBHYTAction *****");

       this.sShowDel = "true";
       this.sShowPrint = "true";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.error("*************loi***********" + e.toString());
       return null;
     }
     log.info("*****End ghiNhan() *****");
     return null;
   }

   public void huyPhieu()
   {
     log.info("***** start  huyPhieu() *****");
     if ((this.sMaGiayChuyenVien == null) || (this.sMaGiayChuyenVien.equals(""))) {
       return;
     }
     GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate.getInstance();

     GiayCvNbBhyt obj = GiayCvNbBhytDel.find(this.sMaGiayChuyenVien);
     if (obj == null) {
       return;
     }
     GiayCvNbBhytDel.remove(obj);
     FacesMessages.instance().add(IConstantsRes.RPGCV_DELETE_SUCCESS, new Object[] { this.sMaGiayChuyenVien });

     log.info("***** XOA THANH CONG *****");
     this.giayChuyenVien = new GiayCvNbBhyt();
     this.sMaGiayChuyenVien = "";
     this.sShowDel = "false";
     this.sShowPrint = "false";
     log.info("***** end  huyPhieu() *****");
   }

   public String quayLai()
     throws Exception
   {
     this.goToChuyenVienNguoiBenhCoTheBHYT = null;

     return "ghinhan";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeTD = "giaychuyenviennguoibenhcobhyt";
     log.info("Vao Method XuatReport benhanvaovien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ((this.sMaGiayChuyenVien != null) && (!this.sMaGiayChuyenVien.equals("")))
       {
         GiayCvNbBhyt giayChuyenVienTemp = GiayCvNbBhytDelegate.getInstance().find(this.sMaGiayChuyenVien);
         if (giayChuyenVienTemp != null) {
           this.giayChuyenVien = giayChuyenVienTemp;
         }
       }
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/giaychuyenviennguoibenhcobhyt.jrxml";
       log.info("Da thay file template: " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       if (this.giayChuyenVien.getGcvbhytNoichuyenden() != null) {
         if (IConstantsRes.TINH_DEFAULT.equals("511")) {
           params.put("KINHCHUYENDEN", this.giayChuyenVien.getGcvbhytNoichuyenden());
         } else {
           params.put("KINHCHUYENDEN", "Ban Giám đốc " + this.giayChuyenVien.getGcvbhytNoichuyenden());
         }
       }
       params.put("HOTENBN", this.benhNhan.getBenhnhanHoten());
       if (this.benhNhan.getBenhnhanNgaysinh() != null) {
         params.put("NGAYSINH", sdf.format(this.benhNhan.getBenhnhanNgaysinh()));
       } else {
         params.put("NGAYSINH", this.benhNhan.getBenhnhanNamsinh());
       }
       params.put("GIOITINH", this.benhNhan.getDmgtMaso(true).getDmgtTen());
       params.put("DANTOC", this.benhNhan.getDantocMa(true).getDmdantocTen());
       params.put("NGHENGHIEP", this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());


       String diachistr = "";
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi();
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + ", " + this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen();
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + ", " + this.thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen();
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + ", " + this.thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       String so_bhyt = "";
       if ((this.thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!this.thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         so_bhyt = this.thamkham.getTiepdonMa().getTiepdonSothebh();
         params.put("COQUAN", this.thamkham.getTiepdonMa().getTiepdonMacoquan());
         if (so_bhyt.length() >= 12)
         {
           params.put("khoiBhytMa", so_bhyt.substring(0, 2));
           params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
           params.put("tinhBhytMa", so_bhyt.substring(3, 5));
           params.put("namBhyt", so_bhyt.substring(5, 7));
           params.put("maCoQuan", so_bhyt.substring(7, 10));
           if (this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa() != null) {
             params.put("THEBHYT", so_bhyt.substring(10) + " - " + this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa());
           }
         }
       }
       Date giaTri1 = this.thamkham.getTiepdonMa(true).getTiepdonGiatri1();
       String sGiaTri1 = "";
       if (giaTri1 != null) {
         sGiaTri1 = sdf.format(giaTri1);
       }
       Date giaTri2 = this.thamkham.getTiepdonMa(true).getTiepdonGiatri2();
       String sGiaTri2 = "";
       if (giaTri2 != null) {
         sGiaTri2 = sdf.format(giaTri2);
       }
       params.put("GIATRISUDUNG", sGiaTri1 + " đến " + sGiaTri2);
       params.put("NOICAP", this.thamkham.getTiepdonMa(true).getTinhbhytMa(true).getDmtinhTen());
       if (this.thamkham.getTiepdonMa(true).getTiepdonNgaygio() != null)
       {
         params.put("NGAYVAOVIEN", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonNgaygio()));
         if (this.thamkham.getTiepdonMa(true).getTiepdonNgaygiora() != null) {
           params.put("NGAYRAVIEN", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonNgaygiora()));
         } else {
           params.put("NGAYRAVIEN", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonNgaygio()));
         }
       }
       params.put("NOIDIEUTRI", this.thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
       if (this.giayChuyenVien.getGcvbhytLidochuyenvien() != null) {
         params.put("LIDOCHUYENVIEN", this.giayChuyenVien.getGcvbhytLidochuyenvien());
       }
       if (this.giayChuyenVien.getGcvbhytDauhieulamsang() != null) {
         params.put("DAUHIEULAMSANG", this.giayChuyenVien.getGcvbhytDauhieulamsang());
       }
       if (this.giayChuyenVien.getGcvbhytTinhtrangnguoibenh() != null) {
         params.put("TINHTRANGNGUOIBENH", this.giayChuyenVien.getGcvbhytTinhtrangnguoibenh());
       }
       if (this.giayChuyenVien.getGcvbhytPhuongtienvanchuyen() != null) {
         params.put("PHUONGTIENVANCHUYEN", this.giayChuyenVien.getGcvbhytPhuongtienvanchuyen());
       }
       if (this.giayChuyenVien.getGcvbhytTemp1() != null) {
         params.put("CACXETNGHIEM", this.giayChuyenVien.getGcvbhytTemp1());
       }
       if (this.giayChuyenVien.getGcvbhytTemp2() != null) {
         params.put("THUOCDADUNG", this.giayChuyenVien.getGcvbhytTemp2());
       }
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();

       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((this.thamkham.getThamkhamGhichu() != null) && (!this.thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + this.thamkham.getThamkhamGhichu();
       }
       if ((this.thamkham.getBenhicd10phu1() != null) && (this.thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu2() != null) && (this.thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       params.put("CDCNG", chanDoan);

       long iNgayVaoVien = 0L;
       if ((this.thamkham.getTiepdonMa(true).getTiepdonNgaygio() != null) && (this.thamkham.getTiepdonMa(true).getTiepdonNgaygiora() != null)) {
         iNgayVaoVien = daysBetween(this.thamkham.getTiepdonMa(true).getTiepdonNgaygio(), this.thamkham.getTiepdonMa(true).getTiepdonNgaygiora()) + 1L;
       }
       params.put("SONGAYDIEUTRI", iNgayVaoVien + "");
       if (getDaKCT().booleanValue()) {
         params.put("KCT", "X");
       }
       if (getDtNgoaiTru().booleanValue()) {
         params.put("DTNGOAITRU", "X");
       }
       if (getDtNoiTru().booleanValue()) {
         params.put("DTNOITRU", "X");
       }
       if (this.giayChuyenVien.getGcvbhytGiamdoc(true).getDtdmnhanvienMaso() != null)
       {
         DtDmNhanVien giamdoc = (DtDmNhanVien)dieuTriUtilDelegate.findByMaSo(this.giayChuyenVien.getGcvbhytGiamdoc(true).getDtdmnhanvienMaso(), "DtDmNhanVien", "dtdmnhanvienMaso");
         params.put("GIAMDOC", giamdoc.getDtdmnhanvienTen());
       }
       if (this.giayChuyenVien.getGcvbhytBskham(true).getDtdmnhanvienMaso() != null)
       {
         DtDmNhanVien bskham = (DtDmNhanVien)dieuTriUtilDelegate.findByMaSo(this.giayChuyenVien.getGcvbhytBskham(true).getDtdmnhanvienMaso(), "DtDmNhanVien", "dtdmnhanvienMaso");



         params.put("BSKHAM", bskham.getDtdmnhanvienTen());
       }
       if (this.giayChuyenVien.getGcvbhytCBBHXH() != null) {
         params.put("CANBO", this.giayChuyenVien.getGcvbhytCBBHXH());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonMa() != null) {
         params.put("MATIEPDON", this.thamkham.getTiepdonMa(true).getTiepdonMa());
       }
       Class.forName("com.mysql.jdbc.Driver");

       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "giaychuyenviennguoibenhcobhyt");




       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");


       this.index += 1;
       if (conn != null) {
         conn.close();
       }
       this.sShowPrint = "false";
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   private int index = 0;
   private static final long ONE_HOUR = 3600000L;

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.sMaGiayChuyenVien == null) || (this.sMaGiayChuyenVien.equals(""))) {
         return;
       }
       GiayCvNbBhytDelegate GiayCvNbBhytDel = GiayCvNbBhytDelegate.getInstance();

       this.giayChuyenVien = new GiayCvNbBhyt();
       List<GiayCvNbBhyt> ls = GiayCvNbBhytDel.findByGiayCvNbBhyt(getMaGiayChuyenVien());
       if ((ls == null) || (ls.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.RPGCV_NOT_EXIST, new Object[0]);
         this.sMaGiayChuyenVien = "";
         this.sShowDel = "false";
         this.sShowPrint = "false";
         setDaKCT(Boolean.valueOf(false));
         setDtNgoaiTru(Boolean.valueOf(false));
         setDtNoiTru(Boolean.valueOf(false));
         return;
       }
       this.sShowDel = "true";
       this.sShowPrint = "true";
       this.giayChuyenVien = ((GiayCvNbBhyt)ls.get(0));
       this.sMaGiayChuyenVien = this.giayChuyenVien.getGcvbhytMa();
       setDaKCT(this.giayChuyenVien.getGcvbhytKct());
       setDtNgoaiTru(this.giayChuyenVien.getGcvbhytDadtngoaitru());
       setDtNoiTru(this.giayChuyenVien.getGcvbhytDadtnoitru());
       log.info("*****sMaGiayChuyenVien: " + this.sMaGiayChuyenVien);
     }
     catch (Exception e)
     {
       System.out.println("error on function displayInfor" + e);
     }
   }

   public void destroyService()
   {
     try
     {
       log.debug("***** End destroyService() Giay chuyen vien");
     }
     catch (Exception ex)
     {
       log.debug("*****destroyService Exception: " + ex);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("************************* destroy GiayChuyenVienNguoiBenhCoTheBHYTAction");
   }

   public static void main(String[] args) {}

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public int getStt()
   {
     return this.stt;
   }

   public void setStt(int stt)
   {
     this.stt = stt;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToChuyenVienNguoiBenhCoTheBHYT()
   {
     return this.goToChuyenVienNguoiBenhCoTheBHYT;
   }

   public void setGoToChuyenVienNguoiBenhCoTheBHYT(String str)
   {
     this.goToChuyenVienNguoiBenhCoTheBHYT = str;
   }

   public String getCoSoKCB()
   {
     return this.sCoSoKCB;
   }

   public void setCoSoKCB(String coSoKCB)
   {
     this.sCoSoKCB = coSoKCB;
   }

   public Boolean getDaKCT()
   {
     return this.bDaKCT;
   }

   public void setDaKCT(Boolean daKCT)
   {
     this.bDaKCT = daKCT;
   }

   public Boolean getDtNgoaiTru()
   {
     return this.bDtNgoaiTru;
   }

   public void setDtNgoaiTru(Boolean dtNgoaiTru)
   {
     this.bDtNgoaiTru = dtNgoaiTru;
   }

   public Boolean getDtNoiTru()
   {
     return this.bDtNoiTru;
   }

   public void setDtNoiTru(Boolean dtNoiTru)
   {
     this.bDtNoiTru = dtNoiTru;
   }

   public String getMaGiayChuyenVien()
   {
     return this.sMaGiayChuyenVien;
   }

   public void setMaGiayChuyenVien(String maGiayChuyenVien)
   {
     this.sMaGiayChuyenVien = maGiayChuyenVien;
   }

   public GiayCvNbBhyt getGiayChuyenVien()
   {
     return this.giayChuyenVien;
   }

   public void setGiayChuyenVien(GiayCvNbBhyt giayChuyenVien)
   {
     this.giayChuyenVien = giayChuyenVien;
   }

   public String getsShowDel()
   {
     return this.sShowDel;
   }

   public void setsShowDel(String sShowDel)
   {
     this.sShowDel = sShowDel;
   }

   public String getsShowPrint()
   {
     return this.sShowPrint;
   }

   public void setsShowPrint(String sShowPrint)
   {
     this.sShowPrint = sShowPrint;
   }

   private static long daysBetween(Date d1, Date d2)
   {
     if (d1 == null) {
       d1 = new Date();
     }
     if (d2 == null) {
       d2 = new Date();
     }
     return (d2.getTime() - d1.getTime() + 3600000L) / 86400000L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.GiayChuyenVienNguoiBenhCoTheBHYTAction

 * JD-Core Version:    0.7.0.1

 */