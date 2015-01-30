 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmLyDoCVAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmLyDoCv = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmLyDoCv = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmLyDoCv", "dtdmlydocvNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmLyDoCv != null) {
       for (Object obj : listDmLyDoCv)
       {
         DtDmLyDoCv lydocv = (DtDmLyDoCv)obj;
         buf.append("<record MaSo='" + lydocv.getDtdmlydocvMaso() + "' Ma='" + lydocv.getDtdmlydocvMa() + "' Ten='" + lydocv.getDtdmlydocvTen() + "' NgayChinhSua='" + lydocv.getDtdmlydocvNgaygiocn() + "' DT='" + Utils.reFactorString(lydocv.getDtdmlydocvChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmLyDoCVAction

 * JD-Core Version:    0.7.0.1

 */