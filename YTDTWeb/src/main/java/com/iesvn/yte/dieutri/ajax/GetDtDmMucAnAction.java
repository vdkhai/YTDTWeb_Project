 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmMucAn;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmMucAnAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmMucAn = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();

       listDtDmMucAn = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmMucAn", "dtdmmaNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listDtDmMucAn != null) {
       for (Object obj : listDtDmMucAn)
       {
         DtDmMucAn dmMucAn = (DtDmMucAn)obj;
         buf.append("<record  MaSo='" + dmMucAn.getDtdmmaMaso() + "'" + " Ma='" + dmMucAn.getDtdmmaMa() + "'" + " Ten='" + dmMucAn.getDtdmmaTen() + "'" + " NgayChinhSua='" + dmMucAn.getDtdmmaNgaygiocn() + "'" + " DT='" + Utils.reFactorString(dmMucAn.getDtdmmaChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmMucAnAction

 * JD-Core Version:    0.7.0.1

 */