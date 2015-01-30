 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmPbCls;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmClsAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmCls = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmCls = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmCls", "dtdmclsNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmCls != null) {
       for (Object obj : listDtDmCls)
       {
         DtDmCls cls = (DtDmCls)obj;
         String pb = "";
         if (cls.getDtdmclsPhanbiet() != null) {
           pb = String.valueOf(cls.getDtdmclsPhanbiet().getDtdmpbclsMaso());
         }
         buf.append("<record MaSo='" + cls.getDtdmclsMaso() + "' Ma='" + cls.getDtdmclsMa() + "' Ten='" + cls.getDtdmclsTen() + "' DTDMCLS_PHANBIET='" + pb + "' DTDMCLS_CHUDAU='" + Utils.reFactorString(cls.getDtdmclsChudau()) + "' DTDMCLS_THUTUBC='" + Utils.reFactorString(cls.getDtdmclsThutubc()) + "' NgayChinhSua='" + Utils.reFactorString(cls.getDtdmclsNgaygiocn()) + "' DT='" + Utils.reFactorString(cls.getDtdmclsChon()) + "' DTDMPBCLS_MA='" + Utils.reFactorString(cls.getDtdmpbclsMa()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmClsAction

 * JD-Core Version:    0.7.0.1

 */