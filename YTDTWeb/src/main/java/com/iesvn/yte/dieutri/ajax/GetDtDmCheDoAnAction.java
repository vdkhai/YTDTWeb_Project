 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCheDoAn;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmCheDoAnAction
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
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmCheDoAn", "dtdmcdaNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DtDmCheDoAn pt = (DtDmCheDoAn)obj;
         buf.append("<record  MaSo='" + pt.getDtdmcdaMaso() + "'" + " Ma='" + pt.getDtdmcdaMa() + "'" + " Ten='" + pt.getDtdmcdaTen() + "'" + " NgayChinhSua='" + pt.getDtdmcdaNgaygiocn() + "'" + " DT='" + Utils.reFactorString(pt.getDtdmcdaChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmCheDoAnAction

 * JD-Core Version:    0.7.0.1

 */