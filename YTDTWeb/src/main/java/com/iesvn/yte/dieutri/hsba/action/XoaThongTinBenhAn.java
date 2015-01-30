 package com.iesvn.yte.dieutri.hsba.action;

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
 import java.rmi.RemoteException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.xml.rpc.ServiceException;
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
 @Name("B214_Xoathongtinbenhan")
 @Synchronized(timeout=6000000L)
 public class XoaThongTinBenhAn
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   String userLogedIn = "";
   private static final long serialVersionUID = 10L;
   private String ngayhientai = "";
   private static Logger log = Logger.getLogger(XoaThongTinBenhAn.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private String soBenhAn;
   private String gioRaVien;
   private String ngayRaVien;

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
     return "DieuTri_CapNhat_XoaThongTinBenhAn";
   }

   private String loaiXoaThongTinBenhAn = "";
   private BenhNhan benhNhan;
   private Hsba hoSoBenhAn;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioVaoKhoa;
   private String ngayVaoKhoa;
   private String gioChuyenKhoa;
   private String ngayChuyenKhoa;

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

     this.hoSoBenhAnCMdangDT = new HsbaChuyenMon();
     SetInforUtil.setInforIfNullForHSBACM(this.hoSoBenhAnCMdangDT);


     this.gioVaoVien = "";
     this.ngayVaoVien = "";

     this.gioVaoKhoa = "";
     this.ngayVaoKhoa = "";


     this.gioChuyenKhoa = "";
     this.ngayChuyenKhoa = "";

     this.gioRaVien = "";
     this.ngayRaVien = "";

     this.loaiXoaThongTinBenhAn = "";

     this.resultHidden = "";


     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());
   }

   private String resultHidden = "";
   private HsbaChuyenMon hoSoBenhAnCM;
   private HsbaChuyenMon hoSoBenhAnCMdangDT;
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
     }
     Date ngayGioVaoKhoa = this.hoSoBenhAnCMdangDT.getHsbacmNgaygiovaok();
     if (ngayGioVaoKhoa != null)
     {
       this.gioVaoKhoa = Utils.getGioPhut(ngayGioVaoKhoa);
       this.ngayVaoKhoa = formatter.format(Long.valueOf(ngayGioVaoKhoa.getTime()));
     }
     Date ngayGioCK = this.hoSoBenhAnCM.getHsbacmNgaygiorak();
     if (ngayGioCK != null)
     {
       this.gioChuyenKhoa = Utils.getGioPhut(ngayGioCK);
       this.ngayChuyenKhoa = formatter.format(Long.valueOf(ngayGioCK.getTime()));
     }
     Date ngayGioRaVien = this.hoSoBenhAn.getHsbaNgaygiorav();
     if (ngayGioRaVien != null)
     {
       this.gioRaVien = Utils.getGioPhut(ngayGioRaVien);
       this.ngayRaVien = formatter.format(Long.valueOf(ngayGioRaVien.getTime()));
       log.info("gioRaVien  :" + this.gioRaVien);
       log.info("ngayRaVien :" + this.ngayRaVien);
     }
   }

   public void displayInfor()
     throws Exception
   {
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


     DmKhoa khoaDangDieuTri = this.hoSoBenhAn.getHsbaKhoadangdt();
     if (khoaDangDieuTri == null)
     {
       log.info("khoaDangDieuTri == null");
       return;
     }
     log.info("khoaDangDieuTri != null");
     this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();



     String khoaDangDieuTriMa = khoaDangDieuTri.getDmkhoaMa();
     log.info("khoaDangDieuTriMa: " + khoaDangDieuTriMa);
     if ((khoaDangDieuTriMa == null) || (khoaDangDieuTriMa.equals("")))
     {
       log.info("khoaDangDieuTri == null or ''");
       return;
     }
     HsbaChuyenMonDelegate hsbacmdele = HsbaChuyenMonDelegate.getInstance();
     List<HsbaChuyenMon> listCM = hsbacmdele.findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
     if (listCM != null)
     {
       System.out.println("----- List HSBACM Size ----- : " + listCM.size());
       for (HsbaChuyenMon hsbacm : listCM)
       {
         this.hoSoBenhAnCM = hsbacm;
         if (hsbacm.getHsbacmChuyenkhoa() != null)
         {
           if (hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa().equals(khoaDangDieuTriMa))
           {
             System.out.println("HSBACM Chuyen khoa: " + hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa());
             System.out.println("Khoa dang Dieu tri: " + khoaDangDieuTriMa);

             break;
           }
           System.out.println("Khoa dang Dieu tri: " + khoaDangDieuTriMa + " ### HSBACM Chuyen khoa: " + hsbacm.getHsbacmChuyenkhoa().getDmkhoaMa());
         }
       }
     }
     HsbaChuyenMon hoSoBenhAnCM_temp = null;
     HsbaChuyenMonDelegate delegate = HsbaChuyenMonDelegate.getInstance();
     hoSoBenhAnCM_temp = delegate.findBySoVaoVien_MaKhoa(this.hoSoBenhAn.getHsbaSovaovien(), khoaDangDieuTriMa);
     if (hoSoBenhAnCM_temp != null) {
       this.hoSoBenhAnCMdangDT = hoSoBenhAnCM_temp;
     }
     setOtherValue();
   }

   public void ghiNhan()
     throws ServiceException, ParseException, RemoteException
   {
     int hsbadel = 0;
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")) || (this.hoSoBenhAnCMdangDT.getKhoaMa().getDmkhoaMa() == null) || (this.hoSoBenhAnCMdangDT.getKhoaMa().getDmkhoaMa().equals("")))
     {
       resetValue();
       return;
     }
     HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

     RemoveUtil.removeAllNullFromBN(this.benhNhan);
     RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
     RemoveUtil.removeAllNullFromHSBACM(this.hoSoBenhAnCM);
     RemoveUtil.removeAllNullFromHSBACM(this.hoSoBenhAnCMdangDT);
     if ((this.loaiXoaThongTinBenhAn != null) && (!this.loaiXoaThongTinBenhAn.equals(""))) {
       if (this.identity.hasRole("QT_HeThong"))
       {
         if (this.loaiXoaThongTinBenhAn.equals("1"))
         {
           if (hsbaDelegate.deleteHsbacmCuoi(this.hoSoBenhAn.getHsbaSovaovien())) {
             hsbadel = 4;
           } else {
             hsbadel = 5;
           }
         }
         else if (this.loaiXoaThongTinBenhAn.equals("2"))
         {
           if (hsbaDelegate.deleteHsbarv(this.hoSoBenhAn.getHsbaSovaovien())) {
             hsbadel = 6;
           } else {
             hsbadel = 7;
           }
         }
         else if (this.loaiXoaThongTinBenhAn.equals("3"))
         {
           System.out.println("Xoa ho so benh an");
           if (hsbaDelegate.deleteHsba(this.hoSoBenhAn.getHsbaSovaovien())) {
             hsbadel = 1;
           } else {
             hsbadel = 2;
           }
         }
       }
       else {
         hsbadel = 3;
       }
     }
     resetValue();
     if (hsbadel < 1) {
       FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_SUC, new Object[0]);
     } else if (hsbadel == 1) {
       FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBA, new Object[0]);
     } else if (hsbadel == 2) {
       FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBA, new Object[0]);
     } else if (hsbadel == 3) {
       FacesMessages.instance().add(IConstantsRes.DELFAILADMIN_HSBA, new Object[0]);
     } else if (hsbadel == 4) {
       FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBACHUYENKHOA, new Object[0]);
     } else if (hsbadel == 5) {
       FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBACHUYENKHOA, new Object[0]);
     } else if (hsbadel == 6) {
       FacesMessages.instance().add(IConstantsRes.DELSUCESS_HSBARAV, new Object[0]);
     } else if (hsbadel == 7) {
       FacesMessages.instance().add(IConstantsRes.DELFAIL_HSBARAV, new Object[0]);
     }
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

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public String getLoaiXoaThongTinBenhAn()
   {
     return this.loaiXoaThongTinBenhAn;
   }

   public void setLoaiXoaThongTinBenhAn(String loaiXoaThongTinBenhAn)
   {
     this.loaiXoaThongTinBenhAn = loaiXoaThongTinBenhAn;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.XoaThongTinBenhAn

 * JD-Core Version:    0.7.0.1

 */