 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
 import java.util.List;

 public class GetDtDmNhaSxSpddAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmNhaSx = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmNhaSx = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmNhaSxSpdd", "dtdmnsxNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listDtDmNhaSx != null) {
       for (Object obj : listDtDmNhaSx)
       {
         DtDmNhaSxSpdd dmNhaSx = (DtDmNhaSxSpdd)obj;
         buf.append("<record MaSo='" + dmNhaSx.getDtdmnsxMaso() + "' Ma='" + dmNhaSx.getDtdmnsxMa() + "' Ten='" + dmNhaSx.getDtdmnsxTen() + "' NgayChinhSua='" + dmNhaSx.getDtdmnsxNgaygiocn() + "' DT='1' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmNhaSxSpddAction

 * JD-Core Version:    0.7.0.1

 */