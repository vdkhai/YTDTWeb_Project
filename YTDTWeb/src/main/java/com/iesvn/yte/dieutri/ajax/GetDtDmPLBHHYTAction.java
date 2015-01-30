 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmPLBHHYTAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listLoaiBHYT = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listLoaiBHYT = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmPlBhyt", "dtdmphloaibhytNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listLoaiBHYT != null) {
       for (Object obj : listLoaiBHYT)
       {
         DtDmPlBhyt dtDmPlBhyt = (DtDmPlBhyt)obj;
         buf.append("<record MaSo='" + dtDmPlBhyt.getDtdmphloaibhytMaso() + "' Ma='" + dtDmPlBhyt.getDtdmphloaibhytMa() + "' Ten='" + dtDmPlBhyt.getDtdmphloaibhytTen() + "' DTDMPHLOAIBHYT_GHICHU='" + Utils.reFactorString(dtDmPlBhyt.getDtdmphloaibhytGhichu()) + "' NgayChinhSua='" + Utils.reFactorString(dtDmPlBhyt.getDtdmphloaibhytNgaygiocn()) + "' DT='" + Utils.reFactorString(dtDmPlBhyt.getDtdmphloaibhytChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmPLBHHYTAction

 * JD-Core Version:    0.7.0.1

 */