 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmLoaiNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetNguonAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listNguon = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listNguon = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmNguonChuongTrinh", "dmnctNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listNguon != null) {
       for (Object obj : listNguon)
       {
         DmNguonChuongTrinh n = (DmNguonChuongTrinh)obj;
         String loaiNguon = "";
         if (n.getDmlnctMaso() != null) {
           loaiNguon = n.getDmlnctMaso().getDmlnctMaso().toString();
         }
         buf.append("<record MaSo='" + n.getDmnctMaso() + "' Ma='" + n.getDmnctMa() + "' Ten='" + n.getDmnctTen() + "' DMLNCT_MASO='" + loaiNguon + "' DMNCT_THUTUCBC='" + n.getDmnctThutucbc() + "' DMNCT_DEFA='" + Utils.reFactorString(n.getDmnctDefa()) + "' DT='" + Utils.reFactorString(n.getDmnctDt()) + "' QL='" + Utils.reFactorString(n.getDmnctQl()) + "' DP='" + Utils.reFactorString(n.getDmnctDp()) + "' NgayChinhSua='" + n.getDmnctNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetNguonAction

 * JD-Core Version:    0.7.0.1

 */