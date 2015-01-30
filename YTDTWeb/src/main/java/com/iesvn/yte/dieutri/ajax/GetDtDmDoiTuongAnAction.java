 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;
 import java.util.List;

 public class GetDtDmDoiTuongAnAction
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
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmDoiTuongAn", "dtdmdtaNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DtDmDoiTuongAn pt = (DtDmDoiTuongAn)obj;
         buf.append("<record  MaSo='" + pt.getDtdmdtaMaso() + "'" + " Ma='" + pt.getDtdmdtaMa() + "'" + " Ten='" + pt.getDtdmdtaTen() + "'" + " NgayChinhSua='" + pt.getDtdmdtaNgaygiocn() + "'" + " DT='1'" + "/>");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmDoiTuongAnAction

 * JD-Core Version:    0.7.0.1

 */