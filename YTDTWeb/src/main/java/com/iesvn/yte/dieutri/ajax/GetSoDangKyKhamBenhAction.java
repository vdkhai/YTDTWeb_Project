 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;

 public class GetSoDangKyKhamBenhAction
   extends Action
 {
   public String performAction(String request)
   {
     String stt = "0";
     DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
     try
     {
       stt = dtDelegate.getSoKhamBenh(request).toString();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return "<result>" + stt + "</result>";
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetSoDangKyKhamBenhAction

 * JD-Core Version:    0.7.0.1

 */