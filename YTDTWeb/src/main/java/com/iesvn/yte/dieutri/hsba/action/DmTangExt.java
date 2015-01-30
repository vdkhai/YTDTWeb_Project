 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmTang;
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
 import org.jboss.seam.security.Identity;

 @Name("DmTang")
 @Scope(ScopeType.SESSION)
 public class DmTangExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmTang dmtang;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @DataModel
   private List<DmTang> listDmTang;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmTangExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B282_DmTang";
   }

   public void search()
   {
     this.log.info("Search DmTangExt....", new Object[0]);

     this.listDmTang = DieuTriUtilDelegate.getInstance().findByAllConditions("DmTang", "dmtangMa", "dmtangTen", "dmtangChon", this.dmtang.getDmtangMa(), this.dmtang.getDmtangTen(), true);
     if (this.listDmTang.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmTangExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmTang)this.listDmTang.get(rowIndex)).getDmtangMa();
       try
       {
         DmTang be = (DmTang)this.listDmTang.get(rowIndex);

         be.setDmtangDefault(Boolean.valueOf(false));
         be.setDmtangNgaygiocn(date);
         be.setDmtangChon(Boolean.valueOf(false));

         DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
         DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
         if (nv != null) {
           this.dmtang.setDmtangNhanviencn(nv);
         }
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmTang.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.tang_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.tang_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmTangExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmKhoaExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.dmtang = new DmTang();
     this.listDmTang = new ArrayList();
   }

   public DmTang getDmtang()
   {
     return this.dmtang;
   }

   public void setDmtang(DmTang dmtang)
   {
     this.dmtang = dmtang;
   }

   public List<DmTang> getListDmTang()
   {
     return this.listDmTang;
   }

   public void setListDmTang(List<DmTang> listDmTang)
   {
     this.listDmTang = listDmTang;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmTangExt

 * JD-Core Version:    0.7.0.1

 */