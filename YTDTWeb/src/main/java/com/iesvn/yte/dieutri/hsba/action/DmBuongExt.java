 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBuong;
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

 @Name("DmBuong")
 @Scope(ScopeType.SESSION)
 public class DmBuongExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmBuong dmbuong;
   @DataModel
   private List<DtDmBuong> listDmBuong;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmBuongExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B273_DmBuong";
   }

   public void search()
   {
     this.log.info("Search DmBuongExt....", new Object[0]);

     this.listDmBuong = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmBuong", "dtdmbMa", "dtdmbTen", "dtdmbChon", this.dmbuong.getDtdmbMa(), this.dmbuong.getDtdmbTen(), true);
     if (this.listDmBuong.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmBuongExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DtDmBuong)this.listDmBuong.get(rowIndex)).getDtdmbMa();
       try
       {
         DtDmBuong be = (DtDmBuong)this.listDmBuong.get(rowIndex);

         be.setDtdmbChon(Boolean.valueOf(false));
         be.setDtdmbNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmBuong.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.buong_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.buong_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmBuongExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmKhoaExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.dmbuong = new DtDmBuong();
     this.listDmBuong = new ArrayList();
   }

   public DtDmBuong getDmbuong()
   {
     return this.dmbuong;
   }

   public void setDmbuong(DtDmBuong dmbuong)
   {
     this.dmbuong = dmbuong;
   }

   public List<DtDmBuong> getListDmBuong()
   {
     return this.listDmBuong;
   }

   public void setListDmBuong(List<DtDmBuong> listDmBuong)
   {
     this.listDmBuong = listDmBuong;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmBuongExt

 * JD-Core Version:    0.7.0.1

 */