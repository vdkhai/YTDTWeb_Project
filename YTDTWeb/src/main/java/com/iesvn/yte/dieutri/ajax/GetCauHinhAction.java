 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.CauHinh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetCauHinhAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDieutri = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDieutri = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "CauHinh", "chbvNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDieutri != null) {
       for (Object obj : listDieutri)
       {
         CauHinh cauHinh = (CauHinh)obj;
         buf.append("<record  MaSo='" + cauHinh.getChbvMaso() + "' Ma='" + cauHinh.getChbvName() + "' Ten='" + cauHinh.getChbvGiatri() + "' NgayChinhSua='" + cauHinh.getChbvNgaygiocn() + "' DT='" + Utils.reFactorString(cauHinh.getChbvChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetCauHinhAction

 * JD-Core Version:    0.7.0.1

 */