 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmGioiTinh;
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
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B216_Capnhatthongtinhanhchanhbenhnhan")
 @Synchronized(timeout=6000000L)
 public class CapNhatThongTinHanhChanhBenhNhan
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String msgFail = "";
   private String msgSuccess = "";
   private static Logger log = Logger.getLogger(CapNhatThongTinHanhChanhBenhNhan.class);
   private static final long serialVersionUID = 10L;
   private String resultHidden = "";
   private String ngayhientai = "";
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   private BenhNhan benhNhan;
   private String ngaySinh;
   private Hsba hoSoBenhAn;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioRaVien;
   private String ngayRaVien;
   private HsbaBhyt hsbaBHYT;

   @Begin(join=true)
   public String init(String loaiCT)
   {
     if ("DieuTri".equals(loaiCT)) {
       this.tenChuongTrinh = MyMenuYTDTAction.dieuTri;
     } else {
       this.tenChuongTrinh = MyMenuYTDTAction.thuVienPhi;
     }
     resetValue();
     return "DieuTri_CapNhat_CapNhatThongTinHanhChinhBenhNhan";
   }

   @End
   public void end() {}

   private void setOtherInfor()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

     Date ngayGioVaoVien = this.hoSoBenhAn.getHsbaNgaygiovaov();
     Calendar dngayGioVaoVien = Calendar.getInstance();
     if (ngayGioVaoVien != null) {
       dngayGioVaoVien.setTime(ngayGioVaoVien);
     }
     if (ngayGioVaoVien != null)
     {
       String hourstr = String.valueOf(dngayGioVaoVien.get(11));
       if (hourstr.length() == 1) {
         hourstr = "0" + hourstr;
       }
       String minstr = String.valueOf(dngayGioVaoVien.get(12));
       if (minstr.length() == 1) {
         minstr = "0" + minstr;
       }
       this.gioVaoVien = (hourstr + ":" + minstr);
       this.ngayVaoVien = formatter.format(ngayGioVaoVien);
     }
     Date ngayGioRaVien = this.hoSoBenhAn.getHsbaNgaygiorav();
     Calendar dngayGioRaVien = Calendar.getInstance();
     if (ngayGioRaVien != null) {
       dngayGioRaVien.setTime(ngayGioRaVien);
     }
     if (ngayGioRaVien != null)
     {
       String hourstr = String.valueOf(dngayGioVaoVien.get(11));
       if (hourstr.length() == 1) {
         hourstr = "0" + hourstr;
       }
       String minstr = String.valueOf(dngayGioVaoVien.get(12));
       if (minstr.length() == 1) {
         minstr = "0" + minstr;
       }
       this.gioVaoVien = (hourstr + ":" + minstr);
       System.out.println("gioRaVien:" + this.gioRaVien);
       this.ngayRaVien = formatter.format(Long.valueOf(ngayGioRaVien.getTime()));
     }
   }

   public void resetValue()
   {
     log.debug("init() ");
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     this.hoSoBenhAn = new Hsba();
     this.hoSoBenhAn.setBenhnhanMa(this.benhNhan);

     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.hsbaBHYT = new HsbaBhyt();
     SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);










































     this.ngaySinh = "";
     this.gioVaoVien = "";
     this.ngayVaoVien = "";
     this.gioRaVien = "";
     this.ngayRaVien = "";
     this.resultHidden = "";

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());
   }

   public void displayInfor()
     throws Exception
   {
     log.info("begin displayInfo=======");
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       if ((sba != null) && (!sba.trim().equals("")))
       {
         Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
         if (hoSoBenhAn_temp != null)
         {
           this.hoSoBenhAn = hoSoBenhAn_temp;
           SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

           this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();


           SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
           if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
             this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
           }
           SetInforUtil.setInforIfNullForBN(this.benhNhan);











           setOtherInfor();
         }
         else
         {
           this.hoSoBenhAn.setHsbaSovaovien("");
           FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND, new Object[0]);
           log.info("khong tim thay sobenhan");
         }
         log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
       }
       else
       {
         log.info("----so vao vien empty");
       }
       log.info("benhNhan.getBenhnhanDonvituoi():" + this.benhNhan.getBenhnhanDonvituoi());
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("ERROR displayInfo=======" + e);
     }
     log.info("End displayInfo=======");
   }

   public void ghiNhan()
   {
     log.debug("ghiNhan()");
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       log.debug("sobenhan: " + this.hoSoBenhAn);
       if ((this.gioVaoVien != null) && (!this.gioVaoVien.equals("")) && (this.ngayVaoVien != null) && (!this.ngayVaoVien.equals("")))
       {
         Calendar hsbaNgaygiovaov = Utils.getDBDate(this.gioVaoVien, this.ngayVaoVien);
         if (hsbaNgaygiovaov != null) {
           this.hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
         }
       }
       if ((this.ngaySinh != null) && (!this.ngaySinh.equals(""))) {
         this.benhNhan.setBenhnhanNgaysinh(Utils.getDBDate("00:00", this.ngaySinh).getTime());
       }
       DmGioiTinh gioiTinh = new DmGioiTinh();
       if (this.benhNhan.getDmgtMaso(true).getDmgtMa().equals("1"))
       {
         gioiTinh.setDmgtMaso(Integer.valueOf(2));
         gioiTinh.setDmgtMa("1");
         this.benhNhan.setDmgtMaso(gioiTinh);
       }
       else
       {
         gioiTinh.setDmgtMaso(Integer.valueOf(1));
         gioiTinh.setDmgtMa("0");
         this.benhNhan.setDmgtMaso(gioiTinh);
       }
       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
       if (hsbaBhytLast != null) {
         this.hsbaBHYT = hsbaBhytLast;
       } else {
         this.hsbaBHYT = null;
       }
       RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
       RemoveUtil.removeAllNullFromBN(this.benhNhan);


       this.hoSoBenhAn.setHsbaNgaygiocn(Calendar.getInstance().getTime());

       String sKetQua = hsbaDelegate.capNhatThongTinNhapVien(this.hoSoBenhAn, this.hsbaBHYT, this.benhNhan, null);

       resetValue();
       if ((sKetQua != null) && (sKetQua.equals("error")))
       {
         FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
         return;
       }
       if ((sba != null) && (sba.trim().equals(""))) {
         FacesMessages.instance().add(IConstantsRes.SUCCESS + ":" + sKetQua, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS + ":" + sKetQua, new Object[0]);
       }
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
     }
   }

   public String getMsgFail()
   {
     return this.msgFail;
   }

   public void setMsgFail(String msgFail)
   {
     this.msgFail = msgFail;
   }

   public String getMsgSuccess()
   {
     return this.msgSuccess;
   }

   public void setMsgSuccess(String msgSuccess)
   {
     this.msgSuccess = msgSuccess;
   }

   public void nhaplai()
     throws Exception
   {
     log.debug("nhaplai()");
     resetValue();
   }

   public static void main(String[] args) {}

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

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinHanhChanhBenhNhan

 * JD-Core Version:    0.7.0.1

 */