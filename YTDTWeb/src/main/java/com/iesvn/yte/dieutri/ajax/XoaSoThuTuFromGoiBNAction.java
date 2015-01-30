 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;

 public class XoaSoThuTuFromGoiBNAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     String maTiepDon = request;
     try
     {
       ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
       maTiepDon = tkDele.xoaSoThuThuBNKham(maTiepDon);
     }
     catch (Exception ex) {}
     return String.format("<result>%s</result>", new Object[] { maTiepDon });
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.XoaSoThuTuFromGoiBNAction

 * JD-Core Version:    0.7.0.1

 */