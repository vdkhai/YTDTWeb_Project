 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmPhanLoaiTaiNanAction
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
       listTaiNan = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmPhanLoaiTaiNan", "dmpltainanNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listTaiNan != null) {
       for (Object obj : listTaiNan)
       {
         DmPhanLoaiTaiNan pt = (DmPhanLoaiTaiNan)obj;
         buf.append("<record MaSo='" + pt.getDmpltainanMaso() + "' Ma='" + pt.getDmpltainanMa() + "' Ten='" + pt.getDmpltainanTen() + "' NgayChinhSua='" + pt.getDmpltainanNgaygiocn() + "' DT='" + Utils.reFactorString(pt.getDmpltainanDt()) + "' QL='" + Utils.reFactorString(pt.getDmpltainanQl()) + "' DP='" + Utils.reFactorString(pt.getDmpltainanDp()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmPhanLoaiTaiNanAction

 * JD-Core Version:    0.7.0.1

 */