 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetXaAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmXa = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmXa = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmXa", "dmxaNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmXa != null) {
       for (Object obj : listDmXa)
       {
         DmXa xa = (DmXa)obj;

         String ten = xa.getDmxaTen();
         ten = Utils.findAndreplace(ten);
         String huyenMaso = "";
         if (xa.getDmhuyenMaso() != null) {
           huyenMaso = xa.getDmhuyenMaso().getDmhuyenMaso().toString();
         }
         buf.append("<record MaSo='" + xa.getDmxaMaso() + "' Ma='" + xa.getDmxaMa() + "' Ten='" + ten + "' DMHUYEN_MASO='" + huyenMaso + "' DMXA_DEFA='" + Utils.reFactorString(xa.getDmxaDefa()) + "' DT='" + Utils.reFactorString(xa.getDmxaChon()) + "' NgayChinhSua='" + xa.getDmxaNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetXaAction

 * JD-Core Version:    0.7.0.1

 */