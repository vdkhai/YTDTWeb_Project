 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class GetTonKhoTheoMaThuocAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetTonKhoTheoMaThuocAction.class);

   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     double tonKhoCuoi = 0.0D;
     try
     {
       StringTokenizer sToken = new StringTokenizer(request, ";");
       log.info("sToken" + sToken);
       if (sToken != null)
       {
         String maThuoc = sToken.nextToken().trim();
         String maKho = sToken.nextToken().trim();

         log.info("maThuoc: " + maThuoc);
         log.info("maKho: " + maKho);
         DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
         tonKhoCuoi = dtutilDelegate.getTonKhoByMaThuocAndMaKhoa(maThuoc, maKho);
       }
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");

     buf.append("<record  TonKho='" + tonKhoCuoi + "' />");





     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetTonKhoTheoMaThuocAction

 * JD-Core Version:    0.7.0.1

 */