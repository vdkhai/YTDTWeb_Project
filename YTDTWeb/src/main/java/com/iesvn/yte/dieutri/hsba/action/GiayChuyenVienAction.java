 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B235_Giaychuyenvien")
 @Synchronized(timeout=6000000L)
 public class GiayChuyenVienAction
   implements Serializable
 {
   private static final long serialVersionUID = 8711728630888663422L;
   private static Logger log = Logger.getLogger(GiayChuyenVienAction.class);
   private HsbaChuyenVien gcv;
   private String nnTru;
   private String ngayCap;
   private String maBATD;
   private String ngayYc;
   private HsbaChuyenMon cm;
   private boolean isUpdate;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private String ngayChuyenVien;
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
   private static final long ONE_HOUR = 3600000L;

   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_GiayChuyenVien";
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");
     this.nnTru = "noi";
     this.gcv = new HsbaChuyenVien();
     setInfoIfNullForHsbaGiayChuyenVien(this.gcv);
     this.nnTru = "noi";
     this.ngayYc = (this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
     this.maBATD = "";
     this.cm = new HsbaChuyenMon();
     setInfoIfNullForHsbaChuyenMon(this.cm);
     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
     this.ngayChuyenVien = Utils.getCurrentDate();
   }

   private void setInfoIfNullForHsbaChuyenMon(HsbaChuyenMon obj)
   {
     if (obj.getHsbacmBenhchinh() == null) {
       obj.setHsbacmBenhchinh(new DmBenhIcd());
     }
   }

   private void setInfoIfNullForHsbaGiayChuyenVien(HsbaChuyenVien obj)
   {
     if (obj.getHsbaSovaovien() == null)
     {
       Hsba _hsba = new Hsba();
       setInfoIfNullForHsba(_hsba);
       obj.setHsbaSovaovien(_hsba);
     }
     if (obj.getHsbacvLydochuyenv() == null) {
       obj.setHsbacvLydochuyenv(new DtDmLyDoCv());
     }
     if (obj.getHsbacvBschuyen() == null) {
       obj.setHsbacvBschuyen(new DtDmNhanVien());
     }
     if (obj.getHsbacvChvienden() == null) {
       obj.setHsbacvChvienden(new DmBenhVien());
     }
     if (obj.getHsbacvBsdieutri() == null) {
       obj.setHsbacvBsdieutri(new DtDmNhanVien());
     }
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

   public void displayHSBA()
   {
     log.info("---displayHSBA");
     Hsba hsba_tmp = null;
     this.gcv = new HsbaChuyenVien();
     if (!this.maBATD.trim().equals(""))
     {
       if (this.nnTru.equals("noi"))
       {
         hsba_tmp = HsbaDelegate.getInstance().find(this.maBATD.trim());
         if (hsba_tmp == null)
         {
           this.nofoundHSBA = "true";
           hsba_tmp = new Hsba();
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.maBATD });
         }
         else
         {
           if (hsba_tmp.getHsbaKhoarav() != null)
           {
             HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
             if (cm_tmp != null)
             {
               this.cm = cm_tmp;
               setInfoIfNullForHsbaChuyenMon(this.cm);
             }
           }
           String tmp_cls = "";
           List<ClsMo> clsmo_arr = ClsMoDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
           if ((clsmo_arr != null) && (clsmo_arr.size() > 0))
           {
             for (ClsMo c : clsmo_arr) {
               if ((c.getClsmoLoaicls() != null) && ((c.getClsmoLoaicls().getDtdmclsMaso().intValue() == 4) || (c.getClsmoLoaicls().getDtdmclsMaso().intValue() == 5))) {
                 tmp_cls = tmp_cls + c.getClsmoMahang(true).getDtdmclsbgDiengiai() + "; ";
               }
             }
             if (tmp_cls.equals("")) {
               tmp_cls = "Không có";
             } else if (tmp_cls.length() > 2040) {
               tmp_cls = tmp_cls.substring(0, 2039) + " ...";
             }
           }
           else
           {
             tmp_cls = "Không có";
           }
           this.gcv.setHsbacvXetnghiem(tmp_cls);

           String tmp_thuoc = "";
           List<ThuocNoiTru> thuocnoitru = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
           if ((thuocnoitru != null) && (thuocnoitru.size() > 0))
           {
             for (ThuocNoiTru tnt : thuocnoitru) {
               tmp_thuoc = tmp_thuoc + tnt.getThuocnoitruMathuoc(true).getDmthuocTen() + "; ";
             }
             if (tmp_thuoc.length() > 2040) {
               tmp_thuoc = tmp_thuoc.substring(0, 2039) + " ...";
             }
           }
           else
           {
             tmp_thuoc = "Không có";
           }
           this.gcv.setHsbacvThuocdadung(tmp_thuoc);



           HsbaChuyenVien gcv_tmp = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
           if (gcv_tmp != null) {
             this.gcv = gcv_tmp;
           }
         }
       }
       else if (this.nnTru.equals("ngoai"))
       {
         hsba_tmp = HsbaDelegate.getInstance().findByTiepDonMa(this.maBATD.trim());
         if (hsba_tmp == null)
         {
           this.nofoundHSBA = "true";
           hsba_tmp = new Hsba();
           FacesMessages.instance().add(IConstantsRes.HSBA_BY_MTD_NULL, new Object[] { this.maBATD });
         }
         else
         {
           if (hsba_tmp.getHsbaKhoarav() != null)
           {
             HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(hsba_tmp.getHsbaSovaovien(), hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
             if (cm_tmp != null)
             {
               this.cm = cm_tmp;
               setInfoIfNullForHsbaChuyenMon(this.cm);
             }
           }
           HsbaChuyenVien gcv_tmp = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
           if (gcv_tmp != null) {
             this.gcv = gcv_tmp;
           }
         }
       }
       setInfoIfNullForHsba(hsba_tmp);
       this.gcv.setHsbaSovaovien(hsba_tmp);
       this.maBATD = hsba_tmp.getHsbaSovaovien();

       SimpleDateFormat sf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Date dNgayChuyenVien = this.gcv.getHsbacvNgagiochvien();
       if (dNgayChuyenVien != null) {
         this.ngayChuyenVien = sf.format(dNgayChuyenVien);
       }
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       setInfoIfNullForHsba(hsba_tmp);
       this.gcv.setHsbaSovaovien(hsba_tmp);
     }
   }

   public void displayGiayChuyenVien()
   {
     log.info("---displayGiayRaVien");
     String maGcv = this.gcv.getHsbacvMa().trim();
     HsbaChuyenVien gcv_tmp = null;
     if (!maGcv.equals(""))
     {
       gcv_tmp = HsbaChuyenVienDelegate.getInstance().findByHsbacvMa(maGcv);
       if (gcv_tmp == null)
       {
         this.nofound = "true";
         gcv_tmp = new HsbaChuyenVien();
         FacesMessages.instance().add(IConstantsRes.GCV_NULL, new Object[] { maGcv });
       }
       else
       {
         HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(gcv_tmp.getHsbaSovaovien(true).getHsbaSovaovien(), gcv_tmp.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());
         if (cm_tmp != null)
         {
           this.cm = cm_tmp;
           setInfoIfNullForHsbaChuyenMon(this.cm);
         }
       }
       setInfoIfNullForHsbaGiayChuyenVien(gcv_tmp);
       this.gcv = gcv_tmp;

       this.maBATD = gcv_tmp.getHsbaSovaovien().getHsbaSovaovien();
       this.nnTru = "noi";
       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.gcv = new HsbaChuyenVien();
       setInfoIfNullForHsbaGiayChuyenVien(this.gcv);
     }
   }

   public void ghiNhan()
     throws ParseException
   {
     log.info("---ghiNhan");

     RemoveUtil.setAllNullForGiayChuyenVien(this.gcv);
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.gcv.setHsbacvNgaycap(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     if (!this.ngayYc.trim().equals("")) {
       this.gcv.setHsbacvNgayyc(Utils.getDBDate("00:00", this.ngayYc).getTime());
     }
     this.gcv.setHsbacvNgaygiocn(new Date());
     SimpleDateFormat sf = new SimpleDateFormat(Utils.FORMAT_DATE);
     Date ngayChuyen = sf.parse(this.ngayChuyenVien);
     this.gcv.setHsbacvNgagiochvien(ngayChuyen);
     String maGcv = this.gcv.getHsbacvMa().trim();
     if (!maGcv.equals("")) {
       this.isUpdate = true;
     }
     if (this.isUpdate)
     {
       result = HsbaChuyenVienDelegate.getInstance().update(this.gcv);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCV_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.gcv.setHsbacvMa(result);
         setInfoIfNullForHsbaGiayChuyenVien(this.gcv);
         FacesMessages.instance().add(IConstantsRes.GCV_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaChuyenVienDelegate.getInstance().insert(this.gcv);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCV_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.gcv.setHsbacvMa(result);
         setInfoIfNullForHsbaGiayChuyenVien(this.gcv);
         FacesMessages.instance().add(IConstantsRes.GCV_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "GiayChuyenVien";
     log.info("Vao Method XuatReport giay chuyen vien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/giaychuyenvienmoi.jrxml";
       log.info("Da thay file template: " + pathTemplate);

       this.gcv = HsbaChuyenVienDelegate.getInstance().findByHsbacvMa(this.gcv.getHsbacvMa());

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       if (this.gcv.getHsbacvChvienden() != null) {
         if (IConstantsRes.TINH_DEFAULT.equals("511")) {
           params.put("KINHCHUYENDEN", this.gcv.getHsbacvChvienden().getDmbenhvienTen());
         } else {
           params.put("KINHCHUYENDEN", "Ban Giám đốc " + this.gcv.getHsbacvChvienden().getDmbenhvienTen());
         }
       }
       BenhNhan benhNhan = this.gcv.getHsbaSovaovien().getBenhnhanMa();

       params.put("HOTENBN", benhNhan.getBenhnhanHoten());
       if (benhNhan.getBenhnhanNgaysinh() != null) {
         params.put("NGAYSINH", sdf.format(benhNhan.getBenhnhanNgaysinh()));
       } else {
         params.put("NGAYSINH", benhNhan.getBenhnhanNamsinh());
       }
       params.put("GIOITINH", benhNhan.getDmgtMaso(true).getDmgtTen());
       params.put("DANTOC", benhNhan.getDantocMa(true).getDmdantocTen());
       params.put("NGHENGHIEP", benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
       params.put("LIDOCHUYENVIEN", this.gcv.getHsbacvLydochuyenv(true).getDtdmlydocvTen());
       if (this.gcv.getHsbacvBsdieutri() != null) {
         params.put("LIDOCHUYENVIEN", this.gcv.getHsbacvBsdieutri().getDtdmnhanvienTen());
       } else {
         params.put("BSKHAM", "");
       }
       String diachistr = "";
       if (benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + benhNhan.getBenhnhanDiachi() + ", ";
       }
       if (benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + benhNhan.getXaMa(true).getDmxaTen() + ", ";
       }
       if (benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + benhNhan.getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       HsbaBhyt hsbaBhyt = HsbaBhytDelegate.getInstance().findBySoVaoVienLastHsbaBhyt(this.gcv.getHsbaSovaovien().getHsbaSovaovien());
       String so_bhyt = "";
       if ((hsbaBhyt != null) && (!hsbaBhyt.getHsbabhytSothebh().equals("")))
       {
         so_bhyt = hsbaBhyt.getHsbabhytSothebh();
         params.put("COQUAN", hsbaBhyt.getHsbabhytCoquanbh());
         if (so_bhyt.length() >= 12)
         {
           params.put("khoiBhytMa", so_bhyt.substring(0, 2));
           params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
           params.put("tinhBhytMa", so_bhyt.substring(3, 5));
           params.put("namBhyt", so_bhyt.substring(5, 7));
           params.put("maCoQuan", so_bhyt.substring(7, 10));
           if (hsbaBhyt.getHsbabhytMakcb() != null) {
             params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa());
           }
         }
         Date giaTri1 = hsbaBhyt.getHsbabhytGiatri0();
         String sGiaTri1 = "";
         if (giaTri1 != null) {
           sGiaTri1 = sdf.format(giaTri1);
         }
         Date giaTri2 = hsbaBhyt.getHsbabhytGiatri1();
         String sGiaTri2 = "";
         if (giaTri2 != null) {
           sGiaTri2 = sdf.format(giaTri2);
         }
         params.put("GIATRISUDUNG", sGiaTri1 + " đến " + sGiaTri2);
         params.put("NOICAP", hsbaBhyt.getHsbabhytTinhbh(true).getDmtinhTen());
       }
       if (this.gcv.getHsbaSovaovien(true).getHsbaNgaygiovaov() != null)
       {
         params.put("NGAYVAOVIEN", sdf.format(this.gcv.getHsbaSovaovien(true).getHsbaNgaygiovaov()));
         if (this.gcv.getHsbaSovaovien(true).getHsbaNgaygiorav() != null) {
           params.put("NGAYRAVIEN", sdf.format(this.gcv.getHsbaSovaovien(true).getHsbaNgaygiorav()));
         } else {
           params.put("NGAYRAVIEN", sdf.format(new Date()));
         }
       }
       params.put("NOIDIEUTRI", this.gcv.getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaTen());
       if (this.gcv.getHsbacvLydochuyenv() != null) {
         params.put("LIDOCHUYENVIEN", this.gcv.getHsbacvLydochuyenv().getDtdmlydocvTen());
       }
       if (this.gcv.getHsbacvDauhieulamsang() != null) {
         params.put("DAUHIEULAMSANG", this.gcv.getHsbacvDauhieulamsang());
       } else {
         params.put("DAUHIEULAMSANG", "");
       }
       if (this.gcv.getHsbacvTinhtrangnguoibenh() != null) {
         params.put("TINHTRANGNGUOIBENH", this.gcv.getHsbacvTinhtrangnguoibenh());
       } else {
         params.put("TINHTRANGNGUOIBENH", "");
       }
       if (this.gcv.getHsbacvPhuongtienvanchuyen() != null) {
         params.put("PHUONGTIENVANCHUYEN", this.gcv.getHsbacvPhuongtienvanchuyen());
       } else {
         params.put("PHUONGTIENVANCHUYEN", "");
       }
       if (this.gcv.getHsbacvBschuyen() != null) {
         params.put("CANBO", this.gcv.getHsbacvBschuyen().getDtdmnhanvienTen());
       } else {
         params.put("CANBO", "");
       }
       if (this.gcv.getHsbacvXetnghiem() != null) {
         params.put("CACXETNGHIEM", this.gcv.getHsbacvXetnghiem());
       } else {
         params.put("CACXETNGHIEM", "");
       }
       if (this.gcv.getHsbacvThuocdadung() != null) {
         params.put("THUOCDADUNG", this.gcv.getHsbacvThuocdadung());
       } else {
         params.put("THUOCDADUNG", "");
       }
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if (this.cm.getHsbacmBenhchinh() != null)
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.cm.getHsbacmBenhchinh().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if (this.cm.getHsbacmDiengiaibc() != null) {
         chanDoan = chanDoan + " , " + this.cm.getHsbacmDiengiaibc();
       }
       params.put("CDCNG", chanDoan);

       long iNgayVaoVien = 0L;
       if ((this.gcv.getHsbaSovaovien().getHsbaNgaygiovaov() != null) && (this.gcv.getHsbaSovaovien().getHsbaNgaygiorav() != null)) {
         iNgayVaoVien = daysBetween(this.gcv.getHsbaSovaovien().getHsbaNgaygiovaov(), this.gcv.getHsbaSovaovien().getHsbaNgaygiorav()) + 1L;
       }
       params.put("SONGAYDIEUTRI", iNgayVaoVien + "");

       params.put("DTNOITRU", "X");



       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
       if (this.gcv.getHsbacvBsdieutri() != null) {
         params.put("BSKHAM", this.gcv.getHsbacvBsdieutri().getDtdmnhanvienTen());
       }
       if (this.gcv.getHsbacvBschuyen() != null) {
         params.put("CANBO", "BS." + this.gcv.getHsbacvBschuyen().getDtdmnhanvienTen());
       }
       if (this.gcv.getHsbaSovaovien().getHsbaSovaovien() != null) {
         params.put("MATIEPDON", this.gcv.getHsbaSovaovien().getHsbaSovaovien());
       }
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

   private void removeInfoIfNullForHsbaGiayChuyenVien(HsbaChuyenVien obj)
   {
     if (Utils.reFactorString(obj.getHsbacvLydochuyenv().getDtdmlydocvMa()).equals("")) {
       obj.setHsbacvLydochuyenv(null);
     }
     if (Utils.reFactorString(obj.getHsbacvBschuyen().getDtdmnhanvienMa()).equals("")) {
       obj.setHsbacvBschuyen(null);
     }
     if (Utils.reFactorString(obj.getHsbacvChvienden().getDmbenhvienMa()).equals("")) {
       obj.setHsbacvChvienden(null);
     }
   }

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public HsbaChuyenVien getGcv()
   {
     return this.gcv;
   }

   public void setGcv(HsbaChuyenVien gcv)
   {
     this.gcv = gcv;
   }

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
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

   public String getNnTru()
   {
     return this.nnTru;
   }

   public void setNnTru(String nnTru)
   {
     this.nnTru = nnTru;
   }

   public String getMaBATD()
   {
     return this.maBATD;
   }

   public void setMaBATD(String maBATD)
   {
     this.maBATD = maBATD;
   }

   public String getNgayYc()
   {
     return this.ngayYc;
   }

   public void setNgayYc(String ngayYc)
   {
     this.ngayYc = ngayYc;
   }

   public HsbaChuyenMon getCm()
   {
     return this.cm;
   }

   public void setCm(HsbaChuyenMon cm)
   {
     this.cm = cm;
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

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public String getNgayChuyenVien()
   {
     return this.ngayChuyenVien;
   }

   public void setNgayChuyenVien(String ngayChuyenVien)
   {
     this.ngayChuyenVien = ngayChuyenVien;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.GiayChuyenVienAction

 * JD-Core Version:    0.7.0.1

 */