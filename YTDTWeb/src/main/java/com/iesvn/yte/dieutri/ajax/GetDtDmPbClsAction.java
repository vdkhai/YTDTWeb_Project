 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmPbCls;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.util.List;
 import org.apache.log4j.Logger;

 public class GetDtDmPbClsAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetDtDmPbClsAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.info("begin GetDtDmPbClsAction");
     StringBuffer buf = new StringBuffer();
     List listDtDmPbCls = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmPbCls = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmPbCls", "dtdmpbclsNgaygiocn");
     }
     catch (Exception ex)
     {
       log.info("ERROR:( GetDtDmPbClsAction " + ex);
     }
     buf.append("<list>");
     if (listDtDmPbCls != null) {
       for (Object obj : listDtDmPbCls)
       {
         DtDmPbCls clsPb = (DtDmPbCls)obj;
         buf.append("<record MaSo='" + clsPb.getDtdmpbclsMaso() + "' Ma='" + clsPb.getDtdmpbclsMa() + "' Ten='" + clsPb.getDtdmpbclsTen() + "' NgayChinhSua='" + clsPb.getDtdmpbclsNgaygiocn() + "' DT='" + Utils.reFactorString(clsPb.getDtdmpbclsChon()) + "' />");
       }
     }
     buf.append("</list>");
     System.out.println("buf.toString():" + buf.toString());
     log.info("End GetDtDmPbClsAction");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmPbClsAction

 * JD-Core Version:    0.7.0.1

 */