 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmGioiTinhAction
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
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmGioiTinh", "dmgtNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DmGioiTinh dmgt = (DmGioiTinh)obj;
         buf.append("<record  MaSo='" + dmgt.getDmgtMaso() + "'" + " Ma='" + dmgt.getDmgtMa() + "'" + " Ten='" + dmgt.getDmgtTen() + "'" + " NgayChinhSua='" + Utils.reFactorString(dmgt.getDmgtNgaygiocn()) + "'" + " DMGT_DEFA='" + Utils.reFactorString(dmgt.getDmgtDefa()) + "'" + " DMGT_GHICHU='" + Utils.reFactorString(dmgt.getDmgtGhichu()) + "'" + " DT='" + Utils.reFactorString(Boolean.valueOf(dmgt.getDmgtChon())) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmGioiTinhAction

 * JD-Core Version:    0.7.0.1

 */