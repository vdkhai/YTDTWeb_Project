 package com.iesvn.yte.util;

 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Hashtable;
 import javax.naming.NamingEnumeration;
 import javax.naming.directory.Attribute;
 import javax.naming.directory.Attributes;
 import javax.naming.directory.SearchControls;
 import javax.naming.directory.SearchResult;
 import javax.naming.ldap.InitialLdapContext;
 import org.apache.log4j.Logger;

 public class LDAPUtils
 {
   private static Logger log = Logger.getLogger(LDAPUtils.class);

   public static InitialLdapContext getConnectLDAP()
   {
     InitialLdapContext ctx = null;
     Hashtable hashtable = null;


     String url = IConstantsRes.LDAP_URL;
     String username = IConstantsRes.LDAP_USERNAME;
     String password = IConstantsRes.LDAP_PASSWORD;
     try
     {
       hashtable = new Hashtable();
       hashtable.put("java.naming.ldap.version", IConstantsRes.LDAP_VERSION);

       hashtable.put("java.naming.factory.initial", IConstantsRes.LDAP_FACTORY_INITIAL);

       hashtable.put("java.naming.security.authentication", IConstantsRes.LDAP_SECURITY_AUTHENTICATION);

       hashtable.put("java.naming.referral", IConstantsRes.LDAP_REFERRAL);
       hashtable.put("java.naming.provider.url", url);
       hashtable.put("java.naming.security.principal", username);
       hashtable.put("java.naming.security.credentials", password);


       ctx = new InitialLdapContext(hashtable, null);
       log.info("LDAP connection established");
     }
     catch (Exception ex)
     {
       log.error("EXCEPTION = " + ex.toString());
     }
     return ctx;
   }

   public static void closeConnectLDAP(InitialLdapContext ctx)
   {
     try
     {
       ctx.close();
       log.info("LDAP connection closed");
     }
     catch (Exception e)
     {
       log.error(e.toString());
     }
   }

   public static Attributes getUserAttributes(String user)
   {
     InitialLdapContext ctx = null;

     String base = IConstantsRes.DN_BASE;
     try
     {
       ctx = getConnectLDAP();


       SearchControls sc = new SearchControls();
       sc.setSearchScope(2);

       NamingEnumeration results = ctx.search(base, "(uid=" + user + ")", sc);

       int ii = 0;
       if (results.hasMore())
       {
         System.out.println("bien ii:" + ii);

         SearchResult sr = (SearchResult)results.next();
         return sr.getAttributes();
       }
     }
     catch (Exception ex)
     {
       log.error("EXCEPTION = " + ex.toString());
     }
     finally
     {
       closeConnectLDAP(ctx);
     }
     return null;
   }

   public static ArrayList getRolesByUser(String user)
   {
     ArrayList<String> result = new ArrayList();
     InitialLdapContext ctx = null;

     String base = IConstantsRes.DN_BASE;
     try
     {
       ctx = getConnectLDAP();


       SearchControls sc = new SearchControls();
       sc.setSearchScope(2);


       NamingEnumeration results = ctx.search(base, "(member=uid=" + user + ",ou=People," + IConstantsRes.DN_BASE + ")", sc);

       int ii = 0;
       while (results.hasMore())
       {
         System.out.println("bien ii:" + ii);

         SearchResult sr = (SearchResult)results.next();

         int at1 = sr.getName().indexOf("=");
         int at2 = sr.getName().indexOf(",");
         String roleName = sr.getName().substring(at1 + 1, at2);
         result.add(roleName);
         log.debug(roleName);
         System.out.println(roleName);
         ii++;
       }
     }
     catch (Exception ex)
     {
       log.error("EXCEPTION = " + ex.toString());
     }
     finally
     {
       closeConnectLDAP(ctx);
     }
     return result;
   }

   public static String getUserFullname(String user)
   {
     String defaultFullname = "";
     try
     {
       Attributes atts = getUserAttributes(user);
       if (atts == null) {
         return defaultFullname;
       }
       return (String)atts.get("cn").get();
     }
     catch (Exception ex)
     {
       log.error("EXCEPTION = " + ex.toString());
     }
     return defaultFullname;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.LDAPUtils

 * JD-Core Version:    0.7.0.1

 */