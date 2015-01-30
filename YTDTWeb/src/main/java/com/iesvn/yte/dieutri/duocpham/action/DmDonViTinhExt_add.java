 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmDonViTinh_add")
 @Scope(ScopeType.SESSION)
 public class DmDonViTinhExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmDonViTinh donvitinh;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmDonViTinhExt_add....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4153_Donvitinh_add";
   }

   public void save()
   {
     this.log.info("Save DmDonViTinhExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.donvitinh.getDmdonvitinhMa();
     this.donvitinh.setDmdonvitinhNgaygiocn(Double.valueOf(date.getTime()));
     this.donvitinh.setDmdonvitinhDt(Boolean.valueOf(true));
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.donvitinh);
       FacesMessages.instance().add(IConstantsRes.donvitinh_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.donvitinh_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmDonViTinhExt_add....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
   }

   public void reset()
   {
     this.log.info("Reset DmDonViTinhExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.donvitinh = new DmDonViTinh();
   }

   public DmDonViTinh getDonvitinh()
   {
     return this.donvitinh;
   }

   public void setDonvitinh(DmDonViTinh donvitinh)
   {
     this.donvitinh = donvitinh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmDonViTinhExt_add

 * JD-Core Version:    0.7.0.1

 */