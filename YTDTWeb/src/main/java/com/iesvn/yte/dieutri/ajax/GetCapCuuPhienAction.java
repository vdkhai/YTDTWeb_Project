 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCapCuuPhien;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetCapCuuPhienAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmCCPhien = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmCCPhien = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmCapCuuPhien", "dtdmccpNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmCCPhien != null) {
       for (Object obj : listDtDmCCPhien)
       {
         DtDmCapCuuPhien ccPhien = (DtDmCapCuuPhien)obj;

         buf.append("<record  MaSo='" + ccPhien.getDtdmccpMaso() + "'" + " Ma='" + ccPhien.getDtdmccpMa() + "'" + " Ten='" + ccPhien.getDtdmccpTen() + "'" + " NgayChinhSua='" + ccPhien.getDtdmccpNgaygiocn() + "'" + " DT='" + Utils.reFactorString(ccPhien.getDtdmccpChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetCapCuuPhienAction

 * JD-Core Version:    0.7.0.1

 */