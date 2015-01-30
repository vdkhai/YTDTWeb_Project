 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.util.HoSoThanhToanUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B3247_CapNhatVienPhiNoiTru")
 @Scope(ScopeType.CONVERSATION)
 public class B3247_CapNhatVienPhiNoiTru
   implements Serializable
 {
   @Logger
   private Log log;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;

   public String init()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "/B3_Vienphi/ThuVienPhi/B3247_CapNhatVienPhiNoiTru.xhtml";
   }

   @End
   public void endFunc() {}

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);

     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     FacesMessages.instance().clear();
   }

   public void thuchienAction()
   {
     this.log.info("Begin thuchienAction", new Object[0]);
     Calendar myCal = Calendar.getInstance();
     myCal.set(2011, 11, 15, 0, 0, 0);
     Date dateUpdate = myCal.getTime();

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String tungayTmp = this.tungay.substring(6) + "-" + this.tungay.substring(3, 5) + "-" + this.tungay.substring(0, 2);
     String denngayTmp = this.denngay.substring(6) + "-" + this.denngay.substring(3, 5) + "-" + this.denngay.substring(0, 2);
     try
     {
       Date dTungay = sdf.parse(tungayTmp);
       Date dDenngay = sdf.parse(denngayTmp);





       HsThtoanDelegate hsDel = HsThtoanDelegate.getInstance();


       List<HsThtoan> listHs = hsDel.findByNgayRaVien(dTungay, dDenngay);
       this.log.info("listHs.size() = " + listHs.size(), new Object[0]);
       String hsttMa = "";
       for (HsThtoan eachHs : listHs)
       {
         Hsba hsba = eachHs.getHsbaSovaovien();
         hsttMa = eachHs.getHsthtoanMa();
         HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
         hsthtoanUtil.tinhToanChoHSTT(eachHs);
         Utils.setInforForHsThToan(eachHs);
         this.log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", new Object[0]);
         this.log.info("So vao vien = " + eachHs.getHsbaSovaovien(), new Object[0]);
         this.log.info("Tong chi phi = " + eachHs.getHsthtoanTongchi(), new Object[0]);
         this.log.info("Benh nhan Tra = " + eachHs.getHsthtoanBntra(), new Object[0]);
         this.log.info("Benh nhan Tra = " + eachHs.getHsthtoanBhyt(), new Object[0]);
         this.log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", new Object[0]);
         eachHs.setHsthtoanMa(hsttMa);
         hsDel.edit(eachHs);
       }
       FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
     }
     catch (Exception ex)
     {
       FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
       ex.printStackTrace();
     }
     this.log.info("End thuchienAction", new Object[0]);
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3247_CapNhatVienPhiNoiTru

 * JD-Core Version:    0.7.0.1

 */