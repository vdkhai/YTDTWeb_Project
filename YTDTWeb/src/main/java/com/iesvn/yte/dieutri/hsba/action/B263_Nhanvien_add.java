 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B263_NhanVien_add")
 @Scope(ScopeType.SESSION)
 public class B263_Nhanvien_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private DtDmNhanVien nhanvien;
   @Logger
   private Log log;

   public DtDmNhanVien getNhanvien()
   {
     return this.nhanvien;
   }

   public void setNhanvien(DtDmNhanVien nhanvien)
   {
     this.nhanvien = nhanvien;
   }

   @Create
   public String init()
   {
     this.log.info("Init nhanvien_add...", new Object[0]);

     resetValue();
     return "/B2_Dieutri/B263_Nhanvien_add";
   }

   public void save()
   {
     this.log.info("Save nhanvien...", new Object[0]);

     String ma = "";
     ma = this.nhanvien.getDtdmnhanvienMa();
     setValueDmNhanVien();
     try
     {
       String nvMaTemp = this.nhanvien.getDtdmnhanvienMa();
       int count1 = 0;

       DtDmNhanVienDelegate nhanvienDAO = DtDmNhanVienDelegate.getInstance();
       for (DtDmNhanVien each : nhanvienDAO.findAll()) {
         if ((each.getDtdmnhanvienMa().length() > 5) &&
           (nvMaTemp.equals(each.getDtdmnhanvienMa().substring(0, 3))))
         {
           int count1_temp = Integer.parseInt(each.getDtdmnhanvienMa().substring(3, 6));
           if (count1 <= count1_temp) {
             count1 = count1_temp;
           }
         }
       }
       count1 += 1;
       if ((count1 + "").length() == 1)
       {
         if (count1 == 0) {
           nvMaTemp = nvMaTemp + "001";
         } else {
           nvMaTemp = nvMaTemp + "00" + count1 + "";
         }
       }
       else if ((count1 + "").length() == 2) {
         nvMaTemp = nvMaTemp + "0" + count1 + "";
       } else if ((count1 + "").length() == 3) {
         nvMaTemp = nvMaTemp + count1 + "";
       } else {
         nvMaTemp = nvMaTemp + "999";
       }
       this.nhanvien.setDtdmnhanvienMa(nvMaTemp);

       this.nhanvien.setDtdmnhanvienChon(Boolean.valueOf(true));

       ma = nvMaTemp;

       DtDmNhanVienDelegate.getInstance().create(this.nhanvien);
       FacesMessages.instance().add(IConstantsRes.nhanvien_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nhanvien_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack Danhsachnhanvien...", new Object[0]);

     return "/B2_Dieutri/B263_Nhanvien";
   }

   public void setValueDmNhanVien()
   {
     Date date = new Date();

     this.nhanvien.setDtdmnhanvienNgaygiocn(Double.valueOf(date.getTime()));
   }

   public void reset()
   {
     this.log.info("Reset nhanvien...", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nhanvien = new DtDmNhanVien();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B263_Nhanvien_add

 * JD-Core Version:    0.7.0.1

 */