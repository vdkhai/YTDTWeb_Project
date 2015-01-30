 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetQuocGiaAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listQuocGia = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listQuocGia = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmQuocGia", "dmquocgiaNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listQuocGia != null) {
       for (Object obj : listQuocGia)
       {
         DmQuocGia qg = (DmQuocGia)obj;

         buf.append("<record MaSo='" + qg.getDmquocgiaMaso() + "' Ma='" + qg.getDmquocgiaMa() + "' Ten='" + qg.getDmquocgiaTen() + "' DMQUOCGIA_TENVN='" + qg.getDmquocgiaTenvn() + "' DT='" + Utils.reFactorString(qg.getDmquocgiaChon()) + "' NgayChinhSua='" + qg.getDmquocgiaNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetQuocGiaAction

 * JD-Core Version:    0.7.0.1

 */