 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhaSxSpddDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.beanutils.BeanUtils;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3147_TTNhaSanXuat")
 public class B3147_TTNhaSanXuatAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private DtDmNhaSxSpdd nsxSpdd;
   private List<DtDmNhaSxSpdd> listNsxSpdd;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strNhaSX;
   @Logger
   private Log log;

   @Factory("strNhaSX")
   public void init()
   {
     this.nsxSpdd = new DtDmNhaSxSpdd();
     this.listNsxSpdd = new ArrayList();
     this.listNsxSpdd = DtDmNhaSxSpddDelegate.getInstance().findAll();
     this.strNhaSX = "";
   }

   public void saveNhaSX()
   {
     this.log.info("begin save,ma so = " + this.nsxSpdd.getDtdmnsxMaso() + ", size = " + this.listNsxSpdd.size(), new Object[0]);

     DtDmNhaSxSpdd nsxTmp = new DtDmNhaSxSpdd();
     DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
     nsxTmp = (DtDmNhaSxSpdd)utilDel.findByMa(this.nsxSpdd.getDtdmnsxMa(), "DtDmNhaSxSpdd", "dtdmnsxMa");
     if (nsxTmp != null) {
       if (!nsxTmp.getDtdmnsxMaso().equals(this.nsxSpdd.getDtdmnsxMaso()))
       {
         FacesMessages.instance().add(IConstantsRes.MA_DA_TON_TAI, new Object[] { this.nsxSpdd.getDtdmnsxMa() });
         return;
       }
     }
     this.nsxSpdd.setDtdmnsxNgaygiocn(new Double(new Date().getTime()));
     DtDmNhaSxSpddDelegate nsxDel = DtDmNhaSxSpddDelegate.getInstance();
     if (this.nsxSpdd.getDtdmnsxMaso() == null)
     {
       nsxDel.create(this.nsxSpdd);
       FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
     }
     else
     {
       nsxDel.edit(this.nsxSpdd);
       FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
     }
     this.listNsxSpdd = nsxDel.findAll();
     this.nsxSpdd = new DtDmNhaSxSpdd();
     this.log.info("End save, size = " + this.listNsxSpdd.size(), new Object[0]);
   }

   public void edit(int index)
   {
     try
     {
       this.nsxSpdd = ((DtDmNhaSxSpdd)BeanUtils.cloneBean(this.listNsxSpdd.get(index)));
     }
     catch (Exception e)
     {
       this.nsxSpdd = new DtDmNhaSxSpdd();
       this.log.info(e.toString(), new Object[0]);
     }
     FacesMessages.instance().clear();
   }

   public DtDmNhaSxSpdd getNsxSpdd()
   {
     return this.nsxSpdd;
   }

   public void setNsxSpdd(DtDmNhaSxSpdd nsxSpdd)
   {
     this.nsxSpdd = nsxSpdd;
   }

   public List<DtDmNhaSxSpdd> getListNsxSpdd()
   {
     return this.listNsxSpdd;
   }

   public void setListNsxSpdd(List<DtDmNhaSxSpdd> listNsxSpdd)
   {
     this.listNsxSpdd = listNsxSpdd;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3147_TTNhaSanXuatAction

 * JD-Core Version:    0.7.0.1

 */