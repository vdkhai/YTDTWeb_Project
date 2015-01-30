 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmDongThem;
 import java.util.List;

 public class GetDtDmDongThemAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List list = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmDongThem", "dtdmdtNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DtDmDongThem pt = (DtDmDongThem)obj;
         buf.append("<record  MaSo='" + pt.getDtdmdtMaso() + "'" + " Ma='" + pt.getDtdmdtMa() + "'" + " Ten='" + pt.getDtdmdtTen() + "'" + " NgayChinhSua='" + pt.getDtdmdtNgaygiocn() + "'" + " DT='1'" + "/>");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmDongThemAction

 * JD-Core Version:    0.7.0.1

 */