 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetPhuongTienAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listPhuongTien = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listPhuongTien = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmPhuongThucGayTaiNan", "dmptgtnNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listPhuongTien != null) {
       for (Object obj : listPhuongTien)
       {
         DmPhuongThucGayTaiNan pt = (DmPhuongThucGayTaiNan)obj;
         String tainan = "";
         if (pt.getDmtainanMaso() != null) {
           tainan = pt.getDmtainanMaso().getDmtainanMaso().toString();
         }
         buf.append("<record MaSo='" + pt.getDmptgtnMaso() + "' Ma='" + pt.getDmptgtnMa() + "' Ten='" + pt.getDmptgtnTen() + "' DMTAINAN_MASO='" + tainan + "' DMPTGTN_BHYT='" + Utils.reFactorString(pt.getDmptgtnBhyt()) + "' QL='" + Utils.reFactorString(pt.getDmptgtnQl()) + "' DT='" + Utils.reFactorString(pt.getDmptgtnDt()) + "' DP='" + Utils.reFactorString(pt.getDmptgtnDp()) + "' NgayChinhSua='" + pt.getDmptgtnNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetPhuongTienAction

 * JD-Core Version:    0.7.0.1

 */