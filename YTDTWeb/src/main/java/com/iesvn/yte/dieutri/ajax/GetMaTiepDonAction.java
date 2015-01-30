 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.CauHinhDelegate;
 import java.io.PrintStream;

 public class GetMaTiepDonAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     System.out.println("-----Begin GetMaTiepDonAction() -----");
     String maTiepDonAndMaBenhNhan = null;
     String maTiepDon = null;
     if (request.indexOf("___") >= 0)
     {
       try
       {
         CauHinhDelegate chDelegate = CauHinhDelegate.getInstance();
         maTiepDon = chDelegate.getMaTiepDon();
       }
       catch (Exception ex)
       {
         System.out.println(ex);
       }
       String[] arr = request.split("___");

       return String.format("<result>%s</result>", new Object[] { arr[0] + "___" + maTiepDon + "___" + arr[1] });
     }
     try
     {
       CauHinhDelegate chDelegate = CauHinhDelegate.getInstance();
       maTiepDonAndMaBenhNhan = chDelegate.getMaTiepDonAndMaBenhNhan();
     }
     catch (Exception ex)
     {
       System.out.println(ex);
     }
     return String.format("<result>%s</result>", new Object[] { request + "___" + maTiepDonAndMaBenhNhan });
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetMaTiepDonAction

 * JD-Core Version:    0.7.0.1

 */