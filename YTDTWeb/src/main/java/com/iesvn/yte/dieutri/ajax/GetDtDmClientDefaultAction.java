 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import java.util.List;

 public class GetDtDmClientDefaultAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List list = null;


     buf.append("<list>");

     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmClientDefaultAction

 * JD-Core Version:    0.7.0.1

 */