 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDanTocAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmDanToc = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmDanToc = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmDanToc", "dmdantocNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmDanToc != null) {
       for (Object obj : listDmDanToc)
       {
         DmDanToc dantoc = (DmDanToc)obj;
         buf.append("<record  MaSo='" + dantoc.getDmdantocMaso() + "' Ma='" + dantoc.getDmdantocMa() + "' Ten='" + dantoc.getDmdantocTen() + "' DMDANTOC_DEFA='" + Utils.reFactorString(dantoc.getDmdantocDefa()) + "' DMDANTOC_MIENPHI='" + Utils.reFactorString(dantoc.getDmdantocMienphi()) + "' NgayChinhSua='" + dantoc.getDmdantocNgaygiocn() + "' DT='" + Utils.reFactorString(dantoc.getDmdantocChon()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDanTocAction

 * JD-Core Version:    0.7.0.1

 */