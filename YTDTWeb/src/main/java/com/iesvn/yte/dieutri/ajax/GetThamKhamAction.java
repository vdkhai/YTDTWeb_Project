 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import java.io.PrintStream;

 public class GetThamKhamAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### GetThamKhamAction, request = " + request);

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
       ThamKham tk = tkDel.findByMaTiepDon(request);
       if (tk != null)
       {
         System.out.println("tk ma " + tk.getThamkhamMa());

         String tkBacsi = "" + tk.getThamkhamBacsi().getDtdmnhanvienMaso();

         buf.append("<THAM_KHAM THAMKHAM_BACSI='" + tkBacsi + "'/>");
       }
       else
       {
         buf.append("<THAM_KHAM THAMKHAM_BACSI=''/>");
       }
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

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetThamKhamAction

 * JD-Core Version:    0.7.0.1

 */