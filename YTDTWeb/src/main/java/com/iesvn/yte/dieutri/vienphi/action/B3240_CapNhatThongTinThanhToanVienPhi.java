 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
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

 @Name("B3240_CapNhatThongTinThanhToanVienPhi")
 @Scope(ScopeType.CONVERSATION)
 public class B3240_CapNhatThongTinThanhToanVienPhi
   implements Serializable
 {
   @Logger
   private Log log;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private int loaicapnhat = 1;
   private String ngayhientai;

   public String init()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "/B3_Vienphi/ThuVienPhi/B3240_CapNhatThongTinThanhToanVienPhi.xhtml";
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
     this.loaicapnhat = 1;
     FacesMessages.instance().clear();
   }

   public void thuchienCapnhatAction()
   {
     if (this.loaicapnhat == 0) {
       thuchienAction();
     } else {
       thuchien2Action();
     }
   }

   public void thuchienAction()
   {
     this.log.info("Begin thuchienAction", new Object[0]);
     Calendar myCal = Calendar.getInstance();
     myCal.set(2011, 11, 1, 0, 0, 0);
     Date dateUpdate = myCal.getTime();

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String tungayTmp = this.tungay.substring(6) + "-" + this.tungay.substring(3, 5) + "-" + this.tungay.substring(0, 2);
     String denngayTmp = this.denngay.substring(6) + "-" + this.denngay.substring(3, 5) + "-" + this.denngay.substring(0, 2);
     try
     {
       Date dTungay = sdf.parse(tungayTmp);
       Date dDenngay = sdf.parse(denngayTmp);
       ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();

       int updateTpk = tpkDel.updateDaTT2Null(dTungay, dDenngay);
       this.log.info("after updateDaTT2Null, updateTpk = " + updateTpk, new Object[0]);

       HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();

       int updateHsttk = hsDel.updateNgayGioTTAndDaTT2Null(dTungay, dDenngay);
       this.log.info("after updateNgayGioTTAndDaTT2Null, updateHsttk = " + updateHsttk, new Object[0]);
       List<HsThtoank> listHs = hsDel.findByNgayTiepdon(dTungay, dDenngay);
       for (HsThtoank eachHs : listHs) {
         if (eachHs.getHsthtoankNgaygiott() == null)
         {
           eachHs.setHsthtoankNgaygiott(eachHs.getTiepdonMa().getTiepdonNgaygio());
           eachHs.setHsthtoankDatt(Boolean.valueOf(true));
           if (eachHs.getTiepdonMa().getTiepdonNgaygio().before(dateUpdate))
           {
             HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(eachHs.getTiepdonMa());
             hsthtoankUtilTmp.tinhToanChoHSTKKham(eachHs);
           }
           else
           {
             hsDel.edit(eachHs);
           }
         }
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

   public void thuchien2Action()
   {
     this.log.info("Begin thuchien2Action", new Object[0]);
     Calendar myCal = Calendar.getInstance();
     myCal.set(2011, 11, 1, 0, 0, 0);
     Date dateUpdate = myCal.getTime();

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String tungayTmp = this.tungay.substring(6) + "-" + this.tungay.substring(3, 5) + "-" + this.tungay.substring(0, 2);
     String denngayTmp = this.denngay.substring(6) + "-" + this.denngay.substring(3, 5) + "-" + this.denngay.substring(0, 2);
     try
     {
       Date dTungay = sdf.parse(tungayTmp);
       Date dDenngay = sdf.parse(denngayTmp);
       ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();

       int updateTpk = tpkDel.updateDaTT2Null(dTungay, dDenngay);
       this.log.info("after updateDaTT2Null, updateTpk = " + updateTpk, new Object[0]);

       HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();

       List<HsThtoank> listHs = hsDel.findByNgayTiepdonAndDaTT(dTungay, dDenngay);
       this.log.info("listHs.size() = " + listHs.size(), new Object[0]);
       String maHs = "";
       TiepDon td = new TiepDon();
       HsThtoank hosoTTK = new HsThtoank();
       for (HsThtoank eachHs : listHs)
       {
         this.log.info("ma HS = " + eachHs.getHsthtoankMa() + ", maTD = " + eachHs.getTiepdonMa(), new Object[0]);
         if (eachHs.getHsthtoankNgaygiott() != null)
         {
           maHs = eachHs.getHsthtoankMa();
           td = eachHs.getTiepdonMa();
           HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(eachHs.getTiepdonMa());
           hsthtoankUtilTmp.tinhToanChoHSTKKham(eachHs);
           hosoTTK = hsthtoankUtilTmp.getHosoTTK();
           hosoTTK.setHsthtoankMa(maHs);
           hosoTTK.setTiepdonMa(td);
           hsDel.edit(hosoTTK);
         }
       }
       FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
     }
     catch (Exception ex)
     {
       FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
       ex.printStackTrace();
     }
     this.log.info("End thuchien2Action", new Object[0]);
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

   public int getLoaicapnhat()
   {
     return this.loaicapnhat;
   }

   public void setLoaicapnhat(int loaicapnhat)
   {
     this.loaicapnhat = loaicapnhat;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3240_CapNhatThongTinThanhToanVienPhi

 * JD-Core Version:    0.7.0.1

 */