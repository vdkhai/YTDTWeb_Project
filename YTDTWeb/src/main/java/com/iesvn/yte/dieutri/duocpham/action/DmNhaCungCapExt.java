 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNhaCungCap;
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

 @Name("DmNhaCungCap")
 @Scope(ScopeType.SESSION)
 public class DmNhaCungCapExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNhaCungCap nhacungcap;
   @DataModel
   private List<DmNhaCungCap> listDmNhaCungCap;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmNhaCungCapExt....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap";
   }

   public void search()
   {
     this.log.info("Search DmNhaCungCapExt....", new Object[0]);

     this.listDmNhaCungCap = DieuTriUtilDelegate.getInstance().findByAllConditions("DmNhaCungCap", "dmnhacungcapMa", "dmnhacungcapTen", "dmnhacungcapDt", this.nhacungcap.getDmnhacungcapMa(), this.nhacungcap.getDmnhacungcapTen(), true);
     if (this.listDmNhaCungCap.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmNhaCungCapExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmNhaCungCap)this.listDmNhaCungCap.get(rowIndex)).getDmnhacungcapMa();
       try
       {
         DmNhaCungCap be = (DmNhaCungCap)this.listDmNhaCungCap.get(rowIndex);

         be.setDmnhacungcapDt(Boolean.valueOf(false));
         be.setDmnhacungcapNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmNhaCungCap.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.nhacungcap_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.nhacungcap_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmNhaCungCapExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmNhaCungCapExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.nhacungcap = new DmNhaCungCap();
     this.listDmNhaCungCap = new ArrayList();
   }

   public DmNhaCungCap getNhacungcap()
   {
     return this.nhacungcap;
   }

   public void setNhacungcap(DmNhaCungCap nhacungcap)
   {
     this.nhacungcap = nhacungcap;
   }

   public List<DmNhaCungCap> getListDmNhaCungCap()
   {
     return this.listDmNhaCungCap;
   }

   public void setListDmNhaCungCap(List<DmNhaCungCap> listDmNhaCungCap)
   {
     this.listDmNhaCungCap = listDmNhaCungCap;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmNhaCungCapExt

 * JD-Core Version:    0.7.0.1

 */