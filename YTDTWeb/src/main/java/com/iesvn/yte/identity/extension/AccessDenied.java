 package com.iesvn.yte.identity.extension;

 import java.io.Serializable;
 import org.apache.log4j.Logger;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.security.Identity;

 @Name("accessdenied")
 public class AccessDenied
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static Logger log = Logger.getLogger(AccessDenied.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;

   @Begin
   public String init()
   {
     log.info("Begin init() AccessDenied: Call ---identity.logout()---");
     this.identity.logout();

     return "accessdenied";
   }

   public String MyMainForm()
   {
     log.info("return MyMainForm");
     return "MyMainForm";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.AccessDenied

 * JD-Core Version:    0.7.0.1

 */