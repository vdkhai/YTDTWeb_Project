 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
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

 @Name("DmKhoaPhong")
 @Scope(ScopeType.SESSION)
 public class DmKhoaPhongExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmKhoa khoaphong;
   @DataModel
   private List<DmKhoa> listDmKhoa;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmKhoaExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B262_Khoaphong";
   }

   public void search()
   {
     this.log.info("Search DmKhoaExt....", new Object[0]);

     this.listDmKhoa = DieuTriUtilDelegate.getInstance().findByAllConditions("DmKhoa", "dmkhoaMa", "dmkhoaTen", "dmkhoaDt", this.khoaphong.getDmkhoaMa(), this.khoaphong.getDmkhoaTen(), true);
     if (this.listDmKhoa.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmKhoaExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmKhoa)this.listDmKhoa.get(rowIndex)).getDmkhoaMa();
       try
       {
         DmKhoa be = (DmKhoa)this.listDmKhoa.get(rowIndex);

         be.setDmkhoaDt(Boolean.valueOf(false));
         be.setDmkhoaNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmKhoa.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.khoaphong_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.khoaphong_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmKhoaExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmKhoaExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.khoaphong = new DmKhoa();
     this.listDmKhoa = new ArrayList();
   }

   public DmKhoa getKhoaphong()
   {
     return this.khoaphong;
   }

   public void setKhoaphong(DmKhoa khoaphong)
   {
     this.khoaphong = khoaphong;
   }

   public List<DmKhoa> getListDmKhoa()
   {
     return this.listDmKhoa;
   }

   public void setListDmKhoa(List<DmKhoa> listDmKhoa)
   {
     this.listDmKhoa = listDmKhoa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmKhoaPhongExt

 * JD-Core Version:    0.7.0.1

 */