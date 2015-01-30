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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmNhaSanXuat")
 @Scope(ScopeType.SESSION)
 public class DmNhaSanXuatExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNhaSanXuat nhasanxuat;
   @DataModel
   private List<DmNhaSanXuat> listDmNhaSanXuat;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmNhaSanXuatExt....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
   }

   public void search()
   {
     this.log.info("Search DmNhaSanXuatExt....", new Object[0]);

     this.listDmNhaSanXuat = DieuTriUtilDelegate.getInstance().findByAllConditions("DmNhaSanXuat", "dmnhasanxuatMa", "dmnhasanxuatTen", "dmnhasanxuatDt", this.nhasanxuat.getDmnhasanxuatMa(), this.nhasanxuat.getDmnhasanxuatTen(), true);
     if (this.listDmNhaSanXuat.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmNhaSanXuatExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmNhaSanXuat)this.listDmNhaSanXuat.get(rowIndex)).getDmnhasanxuatMa();
       try
       {
         DmNhaSanXuat be = (DmNhaSanXuat)this.listDmNhaSanXuat.get(rowIndex);

         be.setDmnhasanxuatDt(Boolean.valueOf(false));
         be.setDmnhasanxuatNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmNhaSanXuat.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.nhasanxuat_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.nhasanxuat_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmNhaSanXuatExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmNhaSanXuatExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.nhasanxuat = new DmNhaSanXuat();
     this.listDmNhaSanXuat = new ArrayList();
   }

   public DmNhaSanXuat getNhasanxuat()
   {
     return this.nhasanxuat;
   }

   public void setNhasanxuat(DmNhaSanXuat nhasanxuat)
   {
     this.nhasanxuat = nhasanxuat;
   }

   public List<DmNhaSanXuat> getListDmNhaSanXuat()
   {
     return this.listDmNhaSanXuat;
   }

   public void setListDmNhaSanXuat(List<DmNhaSanXuat> listDmNhaSanXuat)
   {
     this.listDmNhaSanXuat = listDmNhaSanXuat;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmNhaSanXuatExt

 * JD-Core Version:    0.7.0.1

 */