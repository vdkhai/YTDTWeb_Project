 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;

 public class GetMaPhieuAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();



     return String.format("<result>%s</result>", new Object[] { pnkDelegate.getMaPhieu() });
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetMaPhieuAction

 * JD-Core Version:    0.7.0.1

 */