 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetNoiBanAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listNoiBan = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listNoiBan = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmNhaCungCap", "dmnhacungcapNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listNoiBan != null) {
       for (Object obj : listNoiBan)
       {
         DmNhaCungCap nb = (DmNhaCungCap)obj;
         buf.append("<record MaSo='" + nb.getDmnhacungcapMaso() + "' Ma='" + nb.getDmnhacungcapMa() + "' Ten='" + nb.getDmnhacungcapTen() + "' QL='" + Utils.reFactorString(nb.getDmnhacungcapQl()) + "' DT='" + Utils.reFactorString(nb.getDmnhacungcapDt()) + "' DP='" + Utils.reFactorString(nb.getDmnhacungcapDp()) + "' NgayChinhSua='" + nb.getDmnhacungcapNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetNoiBanAction

 * JD-Core Version:    0.7.0.1

 */