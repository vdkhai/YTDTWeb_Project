 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.HoSoThanhToanUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.SESSION)
 @Name("B3242_ThanhtoanbenhanBNbovien")
 @Synchronized(timeout=6000000L)
 public class ThanhToanBNBoVien
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(ThanhToanPhongCapCuuAction.class);
   private BenhNhan benhNhan;
   private HsbaBhyt hsbaBhyt;
   private Hsba hsba;
   private HsbaChuyenVien hsbaChuyenvien;
   private HsThtoan hsThtoan;
   private String gioi;
   private String tuoi;
   private String soThe;
   private String ngayNhapvien;
   private String ngayTtoan;
   private String cacKhoadt;
   private boolean dtNoi;
   private boolean dtNgoai;
   private boolean dtDongy;
   private String hienThiGhiNhan = "";
   private String hienThiInPhieu = "";
   private Double ungTra;
   private DtDmCum cum = null;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private Integer khoaMaso;
   private String khoaMa;

   public void refreshNhanVienThuNgan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   @Create
   public void init()
   {
     log.info("-----init()-----");
     refreshNhanVienThuNgan();
     log.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     log.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       log.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     emtyData();
   }

   private void emtyData()
   {
     log.info("begin emptyData()");
     this.benhNhan = new BenhNhan();

     this.hsbaBhyt = new HsbaBhyt();

     this.hsba = new Hsba();

     this.hsbaChuyenvien = new HsbaChuyenVien();






     this.hsThtoan = new HsThtoan();


     this.tuoi = "";
     this.soThe = "";
     this.ngayNhapvien = "";



     this.cacKhoadt = "";
     this.dtNoi = false;
     this.dtNgoai = false;
     this.dtDongy = false;

     log.info("end emptyData()");
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     this.benhNhan = new BenhNhan();

     this.hsba = new Hsba();

     this.hsbaBhyt = new HsbaBhyt();






     this.hsbaChuyenvien = new HsbaChuyenVien();

     this.hsThtoan = new HsThtoan();

     this.ngayNhapvien = "";
     this.gioi = "";
     this.tuoi = "";
     this.soThe = "";

     this.ungTra = new Double(0.0D);

     this.dtNoi = new Boolean(false).booleanValue();
     this.dtNgoai = new Boolean(false).booleanValue();
     this.dtNoi = new Boolean(false).booleanValue();

     this.cacKhoadt = "";
     log.info("End ResetForm(): ");
   }

   public void ghinhan()
     throws Exception
   {}

   public void nhaplai()
     throws Exception
   {
     try
     {
       ResetForm();

       this.hienThiGhiNhan = "";
       this.hienThiInPhieu = "";
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   public String inphieu(String loaiFile)
   {
     return null;
   }

   private void hasNoKhoaOrSoVaoVien()
   {
     emtyData();
   }

   private void tinhToanChoHSTT(HsThtoan hstt)
   {
     HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.hsba);
     hsthtoanUtil.tinhToanChoHSTT(hstt);
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.khoaMa == null) || (this.khoaMa.equals("")) || (this.hsba.getHsbaSovaovien() == null) || (this.hsba.getHsbaSovaovien().equals("")))
       {
         hasNoKhoaOrSoVaoVien();
         return;
       }
       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();




       log.info("hoSoBenhAn.getHsbaSovaovien():" + this.hsba.getHsbaSovaovien());


       HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienLastHsbaKhoa(this.hsba.getHsbaSovaovien());
       if (hsbaKhoa == null)
       {
         log.info("hsbaKhoa1:" + hsbaKhoa);

         FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });

         ResetForm();

         return;
       }
       log.info("maKhoa:" + this.khoaMa);
       if (!this.khoaMa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa()))
       {
         log.info("hsbaKhoa1:" + hsbaKhoa);

         FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL, new Object[] { "", "" });

         ResetForm();

         return;
       }
       log.info("Begining displayInfor()");

       List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
       for (HsbaKhoa hK : lstHsbaKhoa) {
         this.cacKhoadt = (this.cacKhoadt + hK.getKhoaMa().getDmkhoaMa() + "   ");
       }
       this.hsba = hsbaKhoa.getHsbaSovaovien();
       if (this.hsba.getBenhnhanMa() != null) {
         this.benhNhan = this.hsba.getBenhnhanMa();
       } else {
         this.benhNhan = new BenhNhan();
       }
       if ((this.hsba.getHsbaSovaovien() != null) && (!this.hsba.getHsbaSovaovien().equals("")))
       {
         try
         {
           HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();

           HsbaBhyt hsbaBhyt_temp = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hsba.getHsbaSovaovien());
           if (hsbaBhyt_temp != null) {
             this.hsbaBhyt = hsbaBhyt_temp;
           } else {
             this.hsbaBhyt = new HsbaBhyt();
           }
         }
         catch (Exception ex)
         {
           log.error("Loi trong khi load HSBABHYT : " + ex.toString());
         }
         try
         {
           HsbaChuyenVienDelegate hsbacvDelegate = HsbaChuyenVienDelegate.getInstance();


           HsbaChuyenVien hsbaChuyenVien_temp = hsbacvDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
           if (hsbaChuyenVien_temp != null) {
             this.hsbaChuyenvien = hsbaChuyenVien_temp;
           } else {
             this.hsbaChuyenvien = new HsbaChuyenVien();
           }
         }
         catch (Exception ex)
         {
           log.error("Loi trong khi load Hsba chuyen vien : " + ex.toString());
         }
       }
       else
       {
         this.hsbaBhyt = new HsbaBhyt();

         this.hsbaChuyenvien = new HsbaChuyenVien();
       }
       try
       {
         HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

         HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(this.hsba.getHsbaSovaovien());
         if (hsbaHsThtoan_temp != null)
         {
           this.hsThtoan = hsbaHsThtoan_temp;




           log.error("Tim thay ho so thanh toan : " + this.hsThtoan);
         }
         else
         {
           this.hsThtoan = new HsThtoan();
           this.hsThtoan.setHsbaSovaovien(this.hsba);

           tinhToanChoHSTT(this.hsThtoan);

           Utils.setInforForHsThToan(this.hsThtoan);

           log.info("hsThtoan.getHsthtoanTamung():" + this.hsThtoan.getHsthtoanTamung());

           log.info("hsThtoan.getHsthtoanHoanung():" + this.hsThtoan.getHsthtoanHoanung());


           this.ungTra = Double.valueOf(this.hsThtoan.getHsthtoanTamung().doubleValue() - this.hsThtoan.getHsthtoanHoanung().doubleValue());

           log.info("ungTra:" + this.ungTra);
         }
         this.hienThiGhiNhan = "true";
         this.hienThiInPhieu = "";
       }
       catch (Exception ex)
       {
         log.error("Loi trong khi load ho so thanh toan : " + ex.toString());

         ex.printStackTrace();
       }
       setOtherValue();
     }
     catch (Exception e)
     {
       log.error("error on function displayInfor" + e);
     }
     log.info("End displayInfor()");
   }

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals("")))
     {
       Calendar cal = Calendar.getInstance();
       Integer tuoi_temp = Integer.valueOf(cal.getTime().getYear() - this.benhNhan.getBenhnhanNgaysinh().getYear());
       this.tuoi = tuoi_temp.toString();
       log.info("Tuoi  :" + this.tuoi);
     }
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
     if ((this.hsbaBhyt.getHsbabhytSothebh() != null) || (this.hsbaBhyt.getHsbabhytSothengheo() != null))
     {
       if (this.hsbaBhyt.getHsbabhytSothebh() != null) {
         this.soThe = this.hsbaBhyt.getHsbabhytSothebh();
       } else {
         this.soThe = this.hsbaBhyt.getHsbabhytSothengheo();
       }
     }
     else {
       this.soThe = "";
     }
     if (this.hsba.getHsbaNgaygiovaov() != null) {
       this.ngayNhapvien = formatter.format(Long.valueOf(this.hsba.getHsbaNgaygiovaov().getTime()));
     } else {
       this.ngayNhapvien = "";
     }
     log.info("End setOtherValue()");
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public HsbaChuyenVien getHsbaChuyenvien()
   {
     return this.hsbaChuyenvien;
   }

   public void setHsbaChuyenvien(HsbaChuyenVien hsbaChuyenvien)
   {
     this.hsbaChuyenvien = hsbaChuyenvien;
   }

   public HsThtoan getHsThtoan()
   {
     return this.hsThtoan;
   }

   public void setHsThtoan(HsThtoan hsThtoan)
   {
     this.hsThtoan = hsThtoan;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getSoThe()
   {
     return this.soThe;
   }

   public void setSoThe(String soThe)
   {
     this.soThe = soThe;
   }

   public String getNgayNhapvien()
   {
     return this.ngayNhapvien;
   }

   public void setNgayNhapvien(String ngayNhapvien)
   {
     this.ngayNhapvien = ngayNhapvien;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getCacKhoadt()
   {
     return this.cacKhoadt;
   }

   public void setCacKhoadt(String cacKhoadt)
   {
     this.cacKhoadt = cacKhoadt;
   }

   public boolean isDtNoi()
   {
     return this.dtNoi;
   }

   public void setDtNoi(boolean dtNoi)
   {
     this.dtNoi = dtNoi;
   }

   public boolean isDtNgoai()
   {
     return this.dtNgoai;
   }

   public void setDtNgoai(boolean dtNgoai)
   {
     this.dtNgoai = dtNgoai;
   }

   public boolean isDtDongy()
   {
     return this.dtDongy;
   }

   public void setDtDongy(boolean dtDongy)
   {
     this.dtDongy = dtDongy;
   }

   public String getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }

   public void setHienThiGhiNhan(String hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public String getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(String hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public String getNgayTtoan()
   {
     return this.ngayTtoan;
   }

   public void setNgayTtoan(String ngayTtoan)
   {
     this.ngayTtoan = ngayTtoan;
   }

   public Double getUngTra()
   {
     return this.ungTra;
   }

   public void setUngTra(Double ungTra)
   {
     this.ungTra = ungTra;
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

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public Integer getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(Integer khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThanhToanBNBoVien

 * JD-Core Version:    0.7.0.1

 */