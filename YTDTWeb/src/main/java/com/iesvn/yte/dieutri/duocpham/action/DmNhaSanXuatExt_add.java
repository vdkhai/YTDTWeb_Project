 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmNhaSanXuat_add")
 @Scope(ScopeType.SESSION)
 public class DmNhaSanXuatExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNhaSanXuat nhasanxuat;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmNhaSanXuatExt_add....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat_add";
   }

   public void save()
   {
     this.log.info("Save DmNhaSanXuatExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.nhasanxuat.getDmnhasanxuatMa();
     this.nhasanxuat.setDmnhasanxuatNgaygiocn(Double.valueOf(date.getTime()));
     this.nhasanxuat.setDmnhasanxuatDt(Boolean.valueOf(true));
     setMaFromTen();
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.nhasanxuat);
       FacesMessages.instance().add(IConstantsRes.nhasanxuat_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nhasanxuat_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmNhaSanXuatExt_add....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
   }

   private void setMaFromTen()
   {
     this.log.info("setMaFromTen DmNhaSanXuatExt_add....", new Object[0]);

     int count = 0;
     String maTemp = this.nhasanxuat.getDmnhasanxuatMa();
     List<DmNhaSanXuat> ls = new ArrayList();

     ls = DieuTriUtilDelegate.getInstance().findMaLike("DmNhaSanXuat", "dmnhasanxuatMa", "dmnhasanxuatDt", this.nhasanxuat.getDmnhasanxuatMa(), true);
     for (DmNhaSanXuat dmNhaSanXuat : ls)
     {
       String temp = dmNhaSanXuat.getDmnhasanxuatMa().substring(6);
       int counTemp = Integer.parseInt(temp);
       if (count < counTemp) {
         count = counTemp;
       }
     }
     count++;
     if ((count + "").length() == 1)
     {
       if (count == 0) {
         maTemp = maTemp + "00001";
       } else {
         maTemp = maTemp + "0000" + count;
       }
     }
     else if ((count + "").length() == 2) {
       maTemp = maTemp + "000" + count;
     } else if ((count + "").length() == 3) {
       maTemp = maTemp + "00" + count;
     } else if ((count + "").length() == 4) {
       maTemp = maTemp + "0" + count;
     } else if ((count + "").length() == 5) {
       maTemp = maTemp + count;
     } else if ((count + "").length() == 4) {
       maTemp = maTemp + "99999" + count;
     }
     this.nhasanxuat.setDmnhasanxuatMa(maTemp);
   }

   public void reset()
   {
     this.log.info("Reset DmNhaSanXuatExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nhasanxuat = new DmNhaSanXuat();
   }

   public DmNhaSanXuat getNhasanxuat()
   {
     return this.nhasanxuat;
   }

   public void setNhasanxuat(DmNhaSanXuat nhasanxuat)
   {
     this.nhasanxuat = nhasanxuat;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmNhaSanXuatExt_add

 * JD-Core Version:    0.7.0.1

 */