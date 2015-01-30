 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.NguoiDung;
 import com.iesvn.yte.entity.VaiTro;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.SESSION)
 @Name("mylogin")
 public class MyLogin
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   Identity identity;

   public Boolean myAuthenticate()
   {
     try
     {
       this.log.info("*** myLogin *** Username: " + this.identity.getUsername(), new Object[0]);

       DieuTriUtilDelegate delegate = DieuTriUtilDelegate.getInstance();

       NguoiDung nguoidung = (NguoiDung)delegate.findByMa(this.identity.getUsername(), "NguoiDung", "ndTendangnhap");
       if (nguoidung == null) {
         return Boolean.valueOf(false);
       }
       if (nguoidung.getNdMadangnhap() == null)
       {
         if (!Utils.reFactorString(this.identity.getPassword()).equals("")) {
           return Boolean.valueOf(false);
         }
       }
       else if (!nguoidung.getNdMadangnhap().equals(this.identity.getPassword())) {
         return Boolean.valueOf(false);
       }
       List<VaiTro> lstVaiTro = delegate.getVaiTroByNguoiDungTenDangNhap(this.identity.getUsername());
       if (lstVaiTro != null) {
         for (VaiTro vt : lstVaiTro) {
           this.identity.addRole(vt.getVaitroMa());
         }
       }
       return Boolean.valueOf(true);
     }
     catch (Exception ex) {}
     return Boolean.valueOf(false);
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.MyLogin

 * JD-Core Version:    0.7.0.1

 */