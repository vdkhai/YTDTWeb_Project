 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B213_Capnhatthongtinchuyenkhoa")
 @Synchronized(timeout=6000000L)
 public class CapNhatThongTinChuyenKhoa
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   String userLogedIn = "";
   private static final long serialVersionUID = 10L;
   private String ngayhientai = "";
   private String maChuyenKhoa;
   private Integer masoChuyenKhoa;
   private static Logger log = Logger.getLogger(CapNhatThongTinChuyenKhoa.class);
   private String soBenhAn;
   private BenhNhan benhNhan;
   private Hsba hoSoBenhAn;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioVaoKhoa;
   private String ngayVaoKhoa;
   private String gioChuyenKhoa;
   private String ngayChuyenKhoa;
   private String khoaVaoVien;
   private String khoaDangDT;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Create
   @Begin(join=true)
   public String init()
   {
     log.info("begin init()");

     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     this.ngayHienTai = formatter.format(new Date());

     resetValue();

     log.info("end init()");

     return "DieuTri_CapNhat_CapNhatThongTinChuyenKhoa";
   }

   @End
   public void end() {}

   public String getUserLogedIn()
   {
     return this.userLogedIn;
   }

   public void setUserLogedIn(String userLogedIn)
   {
     this.userLogedIn = userLogedIn;
   }

   public String getSoBenhAn()
   {
     return this.soBenhAn;
   }

   public void setSoBenhAn(String soBenhAn)
   {
     this.soBenhAn = soBenhAn;
   }

   private void resetValue()
   {
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     this.hoSoBenhAn = new Hsba();
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);


     this.hoSoBenhAnCM = new HsbaChuyenMon();
     SetInforUtil.setInforIfNullForHSBACM(this.hoSoBenhAnCM);


     this.gioVaoVien = "";
     this.ngayVaoVien = "";

     this.gioVaoKhoa = "";
     this.ngayVaoKhoa = "";

     Date date = new Date();
     this.gioChuyenKhoa = Utils.getGioPhut(date);
     this.ngayChuyenKhoa = Utils.getCurrentDate();

     this.khoaVaoVien = (this.khoaDangDT = "");

     this.resultHidden = "";


     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());
     this.maChuyenKhoa = "";
     this.masoChuyenKhoa = null;
   }

   private String resultHidden = "";
   private HsbaChuyenMon hoSoBenhAnCM;
   private String ngayHienTai;

   public void nhaplai()
     throws Exception
   {
     resetValue();
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

     Date ngayGioVaoVien = this.hoSoBenhAn.getHsbaNgaygiovaov();
     if (ngayGioVaoVien != null)
     {
       this.gioVaoVien = Utils.getGioPhut(ngayGioVaoVien);
       this.ngayVaoVien = formatter.format(Long.valueOf(ngayGioVaoVien.getTime()));


       this.gioVaoKhoa = Utils.getGioPhut(ngayGioVaoVien);
       this.ngayVaoKhoa = formatter.format(Long.valueOf(ngayGioVaoVien.getTime()));
     }
     if (this.hoSoBenhAnCM != null)
     {
       Date ngayGioVaoKhoa = this.hoSoBenhAnCM.getHsbacmNgaygiovaok();
       if (ngayGioVaoKhoa != null)
       {
         this.gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa);
         this.ngayVaoKhoa = formatter.format(Long.valueOf(ngayGioVaoKhoa.getTime()));
       }
     }
   }

   public void displayInfor()
     throws Exception
   {
     System.out.println("----------00----------------------------" + this.hoSoBenhAnCM.getHsbacmChuyenkhoa());
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")))
     {
       log.info("hoSoBenhAn.getHsbaSovaovien() == null");
       resetValue();
       return;
     }
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
     Hsba hsbaCur = hsbaDelegate.find(this.hoSoBenhAn.getHsbaSovaovien());
     if (hsbaCur == null)
     {
       FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND, new Object[0]);
       log.info("khong tim thay sobenhan");
       return;
     }
     log.info(" tim thay sobenhan");


     this.hoSoBenhAn = hsbaCur;
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     DmKhoa khoaDangDieuTri = this.hoSoBenhAn.getHsbaKhoadangdt();
     if (khoaDangDieuTri == null)
     {
       log.info("khoaDangDieuTri == null");
       return;
     }
     this.khoaDangDT = khoaDangDieuTri.getDmkhoaTen();

     this.khoaVaoVien = (this.hoSoBenhAn.getHsbaKhoavaov() == null ? "" : this.hoSoBenhAn.getHsbaKhoavaov().getDmkhoaTen());

     log.info("khoaDangDieuTri != null");
     this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();

     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     String khoaDangDieuTriMa = khoaDangDieuTri.getDmkhoaMa();
     log.info("khoaDangDieuTriMa:" + khoaDangDieuTriMa);
     if ((khoaDangDieuTriMa == null) || (khoaDangDieuTriMa.equals("")))
     {
       log.info("khoaDangDieuTri == null or ''");
       return;
     }
     HsbaChuyenMon hoSoBenhAnCM_temp = null;

     HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
     hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(this.hoSoBenhAn.getHsbaSovaovien(), khoaDangDieuTriMa);
     if (hoSoBenhAnCM_temp != null)
     {
       this.hoSoBenhAnCM = hoSoBenhAnCM_temp;
       SetInforUtil.setInforIfNullForHSBACM(this.hoSoBenhAnCM);
     }
     log.info("ma Chuyen khoa = " + this.maChuyenKhoa + ", ma so = " + this.masoChuyenKhoa);

     setOtherValue();
     log.info("khoaDangDieuTriMa--------:" + khoaDangDieuTriMa);
   }

   public String getKhoaVaoVien()
   {
     return this.khoaVaoVien;
   }

   public void ghiNhan()
   {
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.maChuyenKhoa.equals("")) || (this.masoChuyenKhoa == null))
     {
       log.info("Begin ghinhan(), hoSoBenhAn.getHsbaSovaovien() = " + this.hoSoBenhAn.getHsbaSovaovien() + ", ma chuyen khoa = " + this.maChuyenKhoa + ", ma so = " + this.masoChuyenKhoa);
       resetValue();
       return;
     }
     HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();

     RemoveUtil.removeAllNullFromBN(this.benhNhan);
     RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
     RemoveUtil.removeAllNullFromHSBACM(this.hoSoBenhAnCM);
     if ((this.gioVaoKhoa != null) && (!this.gioVaoKhoa.equals("")) && (this.ngayVaoKhoa != null) && (!this.ngayVaoKhoa.equals("")))
     {
       Calendar hsbaNgaygiovaok = Utils.getDBDate(this.gioVaoKhoa, this.ngayVaoKhoa);
       this.hoSoBenhAnCM.setHsbacmNgaygiovaok(hsbaNgaygiovaok.getTime());
     }
     else
     {
       this.hoSoBenhAnCM.setHsbacmNgaygiovaok(null);
     }
     if ((this.gioVaoVien != null) && (!this.gioVaoVien.equals("")) && (this.ngayVaoVien != null) && (!this.ngayVaoVien.equals("")))
     {
       Calendar hsbaNgaygiovaov = Utils.getDBDate(this.gioVaoVien, this.ngayVaoVien);
       this.hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
     }
     else
     {
       this.hoSoBenhAn.setHsbaNgaygiovaov(null);
     }
     if ((this.gioChuyenKhoa != null) && (!this.gioChuyenKhoa.equals("")) && (this.ngayChuyenKhoa != null) && (!this.ngayChuyenKhoa.equals("")))
     {
       Calendar hsbaNgaygiorak = Utils.getDBDate(this.gioChuyenKhoa, this.ngayChuyenKhoa);
       this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());
     }
     else
     {
       this.ngayChuyenKhoa = Utils.getCurrentDate();
       Date date = new Date();
       this.gioChuyenKhoa = Utils.getGioPhut(date);
       Calendar hsbaNgaygiorak = Utils.getDBDate(this.gioChuyenKhoa, this.ngayChuyenKhoa);
       this.hoSoBenhAnCM.setHsbacmNgaygiorak(hsbaNgaygiorak.getTime());
     }
     if (this.masoChuyenKhoa != null)
     {
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
       DmKhoa dmkhoa = (DmKhoa)dtDele.findByMaSo(this.masoChuyenKhoa, "DmKhoa", "dmkhoaMaso");
       log.info("dmkhoa = " + dmkhoa);
       this.hoSoBenhAnCM.setHsbacmChuyenkhoa(dmkhoa);
     }
     this.hoSoBenhAnCM.setHsbacmNgaygiocn(Calendar.getInstance().getTime());


     String soVaoVienInfor = hsbaCMDelegate.capNhatHoSoBenhAn(this.hoSoBenhAn, this.hoSoBenhAnCM, null, null, this.benhNhan);

     FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + soVaoVienInfor, new Object[0]);
     this.resultHidden = "success";
   }

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

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public String getGioVaoVien()
   {
     return this.gioVaoVien;
   }

   public void setGioVaoVien(String gioVaoVien)
   {
     this.gioVaoVien = gioVaoVien;
   }

   public String getNgayVaoVien()
   {
     return this.ngayVaoVien;
   }

   public void setNgayVaoVien(String ngayVaoVien)
   {
     this.ngayVaoVien = ngayVaoVien;
   }

   public String getGioVaoKhoa()
   {
     return this.gioVaoKhoa;
   }

   public void setGioVaoKhoa(String gioVaoKhoa)
   {
     this.gioVaoKhoa = gioVaoKhoa;
   }

   public String getNgayVaoKhoa()
   {
     return this.ngayVaoKhoa;
   }

   public void setNgayVaoKhoa(String ngayVaoKhoa)
   {
     this.ngayVaoKhoa = ngayVaoKhoa;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public HsbaChuyenMon getHoSoBenhAnCM()
   {
     return this.hoSoBenhAnCM;
   }

   public void setHoSoBenhAnCM(HsbaChuyenMon hoSoBenhAnCM)
   {
     this.hoSoBenhAnCM = hoSoBenhAnCM;
   }

   public String getGioChuyenKhoa()
   {
     return this.gioChuyenKhoa;
   }

   public void setGioChuyenKhoa(String gioChuyenKhoa)
   {
     this.gioChuyenKhoa = gioChuyenKhoa;
   }

   public String getNgayChuyenKhoa()
   {
     return this.ngayChuyenKhoa;
   }

   public void setNgayChuyenKhoa(String ngayChuyenKhoa)
   {
     this.ngayChuyenKhoa = ngayChuyenKhoa;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public void setKhoaVaoVien(String khoaVaoVien)
   {
     this.khoaVaoVien = khoaVaoVien;
   }

   public String getKhoaDangDT()
   {
     return this.khoaDangDT;
   }

   public void setKhoaDangDT(String khoaDangDT)
   {
     this.khoaDangDT = khoaDangDT;
   }

   public String getMaChuyenKhoa()
   {
     return this.maChuyenKhoa;
   }

   public void setMaChuyenKhoa(String maChuyenKhoa)
   {
     this.maChuyenKhoa = maChuyenKhoa;
   }

   public Integer getMasoChuyenKhoa()
   {
     return this.masoChuyenKhoa;
   }

   public void setMasoChuyenKhoa(Integer masoChuyenKhoa)
   {
     this.masoChuyenKhoa = masoChuyenKhoa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChuyenKhoa

 * JD-Core Version:    0.7.0.1

 */