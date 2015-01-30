 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmLoaiSinh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmLoaiSinhAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listObj = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmLoaiSinh", "dmloaisinhNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DmLoaiSinh loaiSinh = (DmLoaiSinh)obj;
         buf.append("<record MaSo='" + loaiSinh.getDmloaisinhMaso() + "' Ma='" + loaiSinh.getDmloaisinhMa() + "' Ten='" + loaiSinh.getDmloaisinhTen() + "' NgayChinhSua='" + loaiSinh.getDmloaisinhNgaygiocn() + "' DT='" + Utils.reFactorString(loaiSinh.getDmloaisinhDt()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmLoaiSinhAction

 * JD-Core Version:    0.7.0.1

 */