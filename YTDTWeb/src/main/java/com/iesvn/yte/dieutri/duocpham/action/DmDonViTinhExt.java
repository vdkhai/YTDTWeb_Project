 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDonViTinh;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmDonViTinh")
 @Scope(ScopeType.SESSION)
 public class DmDonViTinhExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmDonViTinh donvitinh;
   @DataModel
   private List<DmDonViTinh> listDmDonViTinh;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmDonViTinhExt....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
   }

   public void search()
   {
     this.log.info("Search DmDonViTinhExt....", new Object[0]);

     this.listDmDonViTinh = DieuTriUtilDelegate.getInstance().findByAllConditions("DmDonViTinh", "dmdonvitinhMa", "dmdonvitinhTen", "dmdonvitinhDt", this.donvitinh.getDmdonvitinhMa(), this.donvitinh.getDmdonvitinhTen(), true);
     if (this.listDmDonViTinh.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmDonViTinhExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmDonViTinh)this.listDmDonViTinh.get(rowIndex)).getDmdonvitinhMa();
       try
       {
         DmDonViTinh be = (DmDonViTinh)this.listDmDonViTinh.get(rowIndex);

         be.setDmdonvitinhDt(Boolean.valueOf(false));
         be.setDmdonvitinhNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmDonViTinh.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.donvitinh_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.donvitinh_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmDonViTinhExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmDonViTinhExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.donvitinh = new DmDonViTinh();
     this.listDmDonViTinh = new ArrayList();
   }

   public DmDonViTinh getDonvitinh()
   {
     return this.donvitinh;
   }

   public void setDonvitinh(DmDonViTinh donvitinh)
   {
     this.donvitinh = donvitinh;
   }

   public List<DmDonViTinh> getListDmDonViTinh()
   {
     return this.listDmDonViTinh;
   }

   public void setListDmDonViTinh(List<DmDonViTinh> listDmDonViTinh)
   {
     this.listDmDonViTinh = listDmDonViTinh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmDonViTinhExt

 * JD-Core Version:    0.7.0.1

 */