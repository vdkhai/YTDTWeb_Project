 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import java.io.PrintStream;

 public class CheckHsThtoanKAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### CheckHsThtoanKAction #####");

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(request);
       if (hsttk == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       System.out.println("hsttk.hsthtoankNgaygiott " + hsttk.getHsthtoankNgaygiott());



       buf.append("<HSTHTOANK NGAYTT='" + hsttk.getHsthtoankNgaygiott() + "'/>");
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.CheckHsThtoanKAction

 * JD-Core Version:    0.7.0.1

 */