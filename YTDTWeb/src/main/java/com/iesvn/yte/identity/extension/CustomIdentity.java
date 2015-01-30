 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Install;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Startup;
 import org.jboss.seam.annotations.intercept.BypassInterceptors;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;
 import org.jboss.seam.security.Identity;

 @Name("org.jboss.seam.security.identity")
 @Scope(ScopeType.SESSION)
 @Install(precedence=20)
 @BypassInterceptors
 @Startup
 public class CustomIdentity
   extends Identity
 {
   @Logger
   private Log log;
   private static final long serialVersionUID = -9154737979944336061L;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private String clientDateHour;
   private String loginErrorMessage;

   public void logout()
   {
     System.out.println("into logout");
     super.logout();
   }

   @Factory("identity")
   public void setmyidentity()
   {
     this.identity = Identity.instance();
   }

   public String getClientDateHour()
   {
     return this.clientDateHour;
   }

   public void setClientDateHour(String clientDateHour)
   {
     this.clientDateHour = clientDateHour;
   }

   public String login()
   {
     String retVal = null;
     try
     {
       this.loginErrorMessage = null;
       if (this.identity.getUsername() != null)
       {
         String upper = this.identity.getUsername().toUpperCase();
         this.identity.setUsername(upper);
       }
       System.out.println("into CustomIdentity (upper): " + this.identity.getUsername());
       retVal = super.login();
       System.out.println("into CustomIdentity: retVal : " + retVal);
     }
     finally {}
     System.out.println("loginErrorMessage : " + this.loginErrorMessage);
     if (retVal == null) {
       return retVal;
     }
     System.out.println("into login --------------Custom identity");
     System.out.println("clientDateHour__2:" + this.clientDateHour);
     Date d = new Date();

     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH");
     String temp = formatter.format(d);


     Date clientDate = new Date();
     try
     {
       clientDate = formatter.parse(this.clientDateHour);
     }
     catch (Exception e) {}
     String serverDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy").format(d);
     String clientDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy").format(clientDate);

     long diffMinutes = d.getTime() - clientDate.getTime() / 60000L;
     if (("YES".equals(IConstantsRes.CHECK_DATE_TIME)) && (this.clientDateHour != null) && (!this.clientDateHour.equals("")) && ((!temp.equals(this.clientDateHour)) || ((serverDDMMYYYY.equals(clientDDMMYYYY)) && (Math.abs(diffMinutes) <= 2L))))
     {
       formatter = new SimpleDateFormat("dd/MM/yyyy");
       temp = formatter.format(d);

       System.out.println(" can chinh sua ngay gio he thong 2");

       FacesMessages.instance().add(IConstantsRes.CHINH_SUA_NGAY_HE_THONG, new Object[] { Integer.valueOf(d.getHours()), temp });

       logout();
       return null;
     }
     System.out.println("identity.hasRole('NV_TiepDon') : " + this.identity.hasRole("NV_TiepDon"));
     System.out.println("identity.hasRole('NV_PhongKham') : " + this.identity.hasRole("NV_PhongKham"));
     return "MyMainForm";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.CustomIdentity

 * JD-Core Version:    0.7.0.1

 */