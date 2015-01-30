 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmTaiNanAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listTaiNan = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listTaiNan = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmTaiNan", "dmtainanNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listTaiNan != null) {
       for (Object obj : listTaiNan)
       {
         DmTaiNan pt = (DmTaiNan)obj;
         buf.append("<record MaSo='" + pt.getDmtainanMaso() + "' Ma='" + pt.getDmtainanMa() + "' Ten='" + pt.getDmtainanTen() + "' NgayChinhSua='" + pt.getDmtainanNgaygiocn() + "' DT='" + Utils.reFactorString(pt.getDmtainanDt()) + "' QL='" + Utils.reFactorString(pt.getDmtainanQl()) + "' DP='" + Utils.reFactorString(pt.getDmtainanDp()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmTaiNanAction

 * JD-Core Version:    0.7.0.1

 */