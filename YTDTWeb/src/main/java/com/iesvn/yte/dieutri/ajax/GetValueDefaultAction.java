 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;

 public class GetValueDefaultAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     System.out.println("performActionperformAction");

     String value = "";
     if ("tinh".equals(request)) {
       value = IConstantsRes.TINH_DEFAULT;
     } else if ("huyen".equals(request)) {
       value = IConstantsRes.HUYEN_DEFAULT;
     } else if ("xa".equals(request)) {
       value = IConstantsRes.XA_DEFAULT;
     } else if ("dantoc".equals(request)) {
       value = IConstantsRes.DAN_TOC_DEFAULT;
     } else if ("nghenghiep".equals(request)) {
       value = IConstantsRes.NGHE_NGHIEP_DEFAULT;
     } else if ("tinhbhyt".equals(request)) {
       value = IConstantsRes.TINH_BHYT_DEFAULT;
     } else if ("gioi".equals(request)) {
       value = IConstantsRes.GIOI_DEFAULT;
     }
     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     buf.append("<record value=''/>");
     buf.append("<record value='" + value + "'/>");
     buf.append("</list>");
     System.out.println("buf.toString():" + buf.toString());

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetValueDefaultAction

 * JD-Core Version:    0.7.0.1

 */