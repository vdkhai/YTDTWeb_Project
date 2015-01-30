 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmLoaiAnAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmLoaiAn = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmLoaiAn = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmLoaiAn", "dtdmlaNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listDtDmLoaiAn != null) {
       for (Object obj : listDtDmLoaiAn)
       {
         DtDmLoaiAn dmLoaiAn = (DtDmLoaiAn)obj;
         buf.append("<record MaSo='" + dmLoaiAn.getDtdmlaMaso() + "' Ma='" + dmLoaiAn.getDtdmlaMa() + "' Ten='" + dmLoaiAn.getDtdmlaTen() + "' NgayChinhSua='" + dmLoaiAn.getDtdmlaNgaygiocn() + "' DT='" + Utils.reFactorString(dmLoaiAn.getDtdmlaChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmLoaiAnAction

 * JD-Core Version:    0.7.0.1

 */