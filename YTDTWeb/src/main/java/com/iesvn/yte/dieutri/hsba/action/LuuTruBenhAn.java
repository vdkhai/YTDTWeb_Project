 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.rmi.RemoteException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import javax.xml.rpc.ServiceException;
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
 @Name("B215_Luutrubenhan")
 @Synchronized(timeout=6000000L)
 public class LuuTruBenhAn
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   String userLogedIn = "";
   private static final long serialVersionUID = 10L;
   private String ngayhientai = "";
   private static Logger log = Logger.getLogger(LuuTruBenhAn.class);
   private String soBenhAn;
   private String ngayLuuTru;
   private String tuoi;
   private String ngaySinh;
   private BenhNhan benhNhan;
   private Hsba hoSoBenhAn;
   private HsbaNop hsbaNop;
   private HsbaChuyenVien hsbaCV;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioVaoKhoa;
   private String ngayVaoKhoa;
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
     return "DieuTri_CapNhat_LuuTruBenhAn";
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

     this.hsbaNop = new HsbaNop();
     SetInforUtil.setInforIfNullForHSBANop(this.hsbaNop);

     this.hsbaCV = new HsbaChuyenVien();
     SetInforUtil.setInforIfNullForHsbaChuyenVien(this.hsbaCV);


     this.gioVaoVien = "";
     this.ngayVaoVien = "";

     this.gioVaoKhoa = "";
     this.ngayVaoKhoa = "";


     this.gioRaVien = "";
     this.ngayRaVien = "";


     this.resultHidden = "";


     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());

     this.ngayLuuTru = this.ngayhientai;

     this.ngaySinh = "";
   }

   private String gioi = "";
   private String resultHidden = "";
   private String ngayHienTai;
   private int soLuongBenhAnTrongNgay;

   public int getSoLuongBenhAnTrongNgay()
   {
     return this.soLuongBenhAnTrongNgay;
   }

   public void setSoLuongBenhAnTrongNgay(int soLuongBenhAnTrongNgay)
   {
     this.soLuongBenhAnTrongNgay = soLuongBenhAnTrongNgay;
   }

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
     Date ngayGioRaVien = this.hoSoBenhAn.getHsbaNgaygiorav();
     if (ngayGioRaVien != null)
     {
       this.gioRaVien = Utils.getGioPhut(ngayGioRaVien);
       this.ngayRaVien = formatter.format(Long.valueOf(ngayGioRaVien.getTime()));
     }
     if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
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
     Date ngayGioLuuTru = this.hsbaNop.getHsbanopNgaygioluutru();
     if (ngayGioLuuTru != null) {
       this.ngayLuuTru = formatter.format(Long.valueOf(ngayGioLuuTru.getTime()));
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
     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);


     this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();

     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     HsbaNop hoSoBenhAnNop_temp = null;
     HsbaNopDelegate delegate = HsbaNopDelegate.getInstance();


     this.soLuongBenhAnTrongNgay = delegate.soBAtrongngay(new Date());
     hoSoBenhAnNop_temp = delegate.findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());


     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if (hoSoBenhAnNop_temp != null)
     {
       this.hsbaNop = hoSoBenhAnNop_temp;
       SetInforUtil.setInforIfNullForHSBANop(this.hsbaNop);

       Date dNgayGioLuuTru = this.hsbaNop.getHsbanopNgaygioluutru();
       if (dNgayGioLuuTru != null) {
         this.ngayLuuTru = formatter.format(Long.valueOf(dNgayGioLuuTru.getTime()));
       }
       System.out.println("---- hoSoBenhAnNop_temp != null ----");
     }
     else
     {
       this.hsbaNop.setHsbanopNgaygioluutru(new Date());
     }
     HsbaChuyenVien hoSoBenhAnCV_temp = null;

     HsbaChuyenVienDelegate delegateCV = HsbaChuyenVienDelegate.getInstance();
     hoSoBenhAnCV_temp = delegateCV.findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
     if (hoSoBenhAnCV_temp != null)
     {
       this.hsbaCV = hoSoBenhAnCV_temp;
       SetInforUtil.setInforIfNullForHsbaChuyenVien(this.hsbaCV);
       System.out.println("---- hoSoBenhAnCV_temp != null ----");
     }
     setOtherValue();
   }

   public void ghiNhan()
     throws ServiceException, ParseException, RemoteException
   {
     if ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().equals("")))
     {
       resetValue();
       return;
     }
     if ((this.hsbaNop.getHsbanopSoluutru() != null) && (!this.hsbaNop.getHsbanopSoluutru().equals(""))) {
       return;
     }
     RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
     RemoveUtil.removeAllNullFromHSBACV(this.hsbaCV);
     RemoveUtil.removeAllNullFromHSBANop(this.hsbaNop);
     if ((this.ngayLuuTru != null) && (!this.ngayLuuTru.equals("")))
     {
       Calendar hsbaNopLuuTru = Utils.getDBDate("00:00", this.ngayLuuTru);
       this.hsbaNop.setHsbanopNgaygioluutru(hsbaNopLuuTru.getTime());
       this.hoSoBenhAn.setHsbaNgaynop(hsbaNopLuuTru.getTime());
     }
     else
     {
       this.hsbaNop.setHsbanopNgaygioluutru(new Date());
       this.hoSoBenhAn.setHsbaNgaynop(new Date());
     }
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();



     String hsbanopSoluutru = hsbaDelegate.capNhatThongTinHSBA(this.hoSoBenhAn, this.hsbaNop, null);
     this.hsbaNop.setHsbanopSoluutru(hsbanopSoluutru);


     FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + hsbanopSoluutru, new Object[0]);
     this.resultHidden = "success";


     HsbaNopDelegate delegate = HsbaNopDelegate.getInstance();
     this.soLuongBenhAnTrongNgay = delegate.soBAtrongngay(new Date());
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

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public HsbaNop getHsbaNop()
   {
     return this.hsbaNop;
   }

   public void setHsbaNop(HsbaNop hsbaNop)
   {
     this.hsbaNop = hsbaNop;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public HsbaChuyenVien getHsbaCV()
   {
     return this.hsbaCV;
   }

   public void setHsbaCV(HsbaChuyenVien hsbaCV)
   {
     this.hsbaCV = hsbaCV;
   }

   public String getNgayLuuTru()
   {
     return this.ngayLuuTru;
   }

   public void setNgayLuuTru(String ngayLuuTru)
   {
     this.ngayLuuTru = ngayLuuTru;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.LuuTruBenhAn

 * JD-Core Version:    0.7.0.1

 */