 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmLoaiBenhVien;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmTuyen;
 import com.iesvn.yte.entity.DmVungMien;
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

 @Name("B2610_Benhvien")
 @Scope(ScopeType.SESSION)
 public class B2610_Benhvien
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBenhVien benhvien;
   @DataModel
   private List<DmBenhVien> listB2610;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B2610_Benh vien....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B2610_benhvien";
   }

   public void search()
   {
     this.log.info("Search B2610_Benh vien....", new Object[0]);

     this.listB2610 = DieuTriUtilDelegate.getInstance().findByAllConditions("DmBenhVien", "dmbenhvienMa", "dmbenhvienTen", "dmbenhvienDt", this.benhvien.getDmbenhvienMa(), this.benhvien.getDmbenhvienTen(), true);
     if (this.listB2610.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete B2610 Benh vien....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmBenhVien)this.listB2610.get(rowIndex)).getDmbenhvienMa();
       try
       {
         DmBenhVien be = (DmBenhVien)this.listB2610.get(rowIndex);

         be.setDmbenhvienDt(Boolean.valueOf(false));
         be.setDmbenhvienNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listB2610.remove(rowIndex);


         FacesMessages.instance().add(IConstantsRes.benhvien_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.benhvien_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset B2610 Benh vien....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback B2610 Benh vien....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.benhvien = new DmBenhVien();
     this.benhvien.setDmhuyenMaso(new DmHuyen());
     this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
     this.benhvien.setDmtinhMaso(new DmTinh());
     this.benhvien.setDmtuyenMaso(new DmTuyen());
     this.benhvien.setDmvungmienMaso(new DmVungMien());
     this.listB2610 = new ArrayList();
   }

   public DmBenhVien getBenhvien()
   {
     return this.benhvien;
   }

   public void setBenhvien(DmBenhVien benhvien)
   {
     this.benhvien = benhvien;
   }

   public List<DmBenhVien> getListB2610()
   {
     return this.listB2610;
   }

   public void setListB2610(List<DmBenhVien> listB2610)
   {
     this.listB2610 = listB2610;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B2610_Benhvien

 * JD-Core Version:    0.7.0.1

 */